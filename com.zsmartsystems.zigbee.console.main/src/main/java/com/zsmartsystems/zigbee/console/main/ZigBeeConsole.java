/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.ZigBeeAddress;
import com.zsmartsystems.zigbee.ZigBeeCommand;
import com.zsmartsystems.zigbee.ZigBeeCommandListener;
import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeGroupAddress;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNetworkNodeListener;
import com.zsmartsystems.zigbee.ZigBeeNetworkState;
import com.zsmartsystems.zigbee.ZigBeeNetworkStateListener;
import com.zsmartsystems.zigbee.ZigBeeNode;
import com.zsmartsystems.zigbee.app.discovery.ZigBeeDiscoveryExtension;
import com.zsmartsystems.zigbee.app.iasclient.ZigBeeIasCieExtension;
import com.zsmartsystems.zigbee.app.otaserver.ZclOtaUpgradeServer;
import com.zsmartsystems.zigbee.app.otaserver.ZigBeeOtaFile;
import com.zsmartsystems.zigbee.app.otaserver.ZigBeeOtaServerStatus;
import com.zsmartsystems.zigbee.app.otaserver.ZigBeeOtaStatusCallback;
import com.zsmartsystems.zigbee.app.otaserver.ZigBeeOtaUpgradeExtension;
import com.zsmartsystems.zigbee.console.ZigBeeConsoleAttributeReadCommand;
import com.zsmartsystems.zigbee.console.ZigBeeConsoleAttributeSupportedCommand;
import com.zsmartsystems.zigbee.console.ZigBeeConsoleAttributeWriteCommand;
import com.zsmartsystems.zigbee.console.ZigBeeConsoleBindCommand;
import com.zsmartsystems.zigbee.console.ZigBeeConsoleBindingTableCommand;
import com.zsmartsystems.zigbee.console.ZigBeeConsoleCommand;
import com.zsmartsystems.zigbee.console.ZigBeeConsoleCommandsSupportedCommand;
import com.zsmartsystems.zigbee.console.ZigBeeConsoleDescribeEndpointCommand;
import com.zsmartsystems.zigbee.console.ZigBeeConsoleDescribeNodeCommand;
import com.zsmartsystems.zigbee.console.ZigBeeConsoleDeviceInformationCommand;
import com.zsmartsystems.zigbee.console.ZigBeeConsoleInstallKeyCommand;
import com.zsmartsystems.zigbee.console.ZigBeeConsoleLinkKeyCommand;
import com.zsmartsystems.zigbee.console.ZigBeeConsoleNetworkBackupCommand;
import com.zsmartsystems.zigbee.console.ZigBeeConsoleNetworkDiscoveryCommand;
import com.zsmartsystems.zigbee.console.ZigBeeConsoleNetworkJoinCommand;
import com.zsmartsystems.zigbee.console.ZigBeeConsoleNetworkLeaveCommand;
import com.zsmartsystems.zigbee.console.ZigBeeConsoleNetworkStartCommand;
import com.zsmartsystems.zigbee.console.ZigBeeConsoleNodeListCommand;
import com.zsmartsystems.zigbee.console.ZigBeeConsoleOtaUpgradeCommand;
import com.zsmartsystems.zigbee.console.ZigBeeConsoleReportingConfigCommand;
import com.zsmartsystems.zigbee.console.ZigBeeConsoleReportingSubscribeCommand;
import com.zsmartsystems.zigbee.console.ZigBeeConsoleReportingUnsubscribeCommand;
import com.zsmartsystems.zigbee.console.ZigBeeConsoleUnbindCommand;
import com.zsmartsystems.zigbee.security.ZigBeeKey;
import com.zsmartsystems.zigbee.transport.TransportConfig;
import com.zsmartsystems.zigbee.transport.TransportConfigOption;
import com.zsmartsystems.zigbee.transport.TrustCentreJoinMode;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportFirmwareCallback;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportFirmwareStatus;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportFirmwareUpdate;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportTransmit;
import com.zsmartsystems.zigbee.zcl.ZclAttribute;
import com.zsmartsystems.zigbee.zcl.ZclCluster;
import com.zsmartsystems.zigbee.zcl.ZclStatus;
import com.zsmartsystems.zigbee.zcl.clusters.ZclOnOffCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclOtaUpgradeCluster;
import com.zsmartsystems.zigbee.zcl.clusters.general.ConfigureReportingResponse;
import com.zsmartsystems.zigbee.zcl.clusters.general.ReportAttributesCommand;
import com.zsmartsystems.zigbee.zcl.clusters.groups.GetGroupMembershipResponse;
import com.zsmartsystems.zigbee.zcl.clusters.groups.ViewGroupResponse;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * ZigBee command line console is an example usage of the ZigBee console.
 *
 * Once refactoring is complete and all commands are migrated to the new format, this class will be deleted and the
 * remaining methods moved into the main class.
 *
 * @author Tommi S.E. Laukkanen
 * @author Chris Jackson
 */
public final class ZigBeeConsole {
    /**
     * The main thread.
     */
    private Thread mainThread = null;

    /**
     * The flag reflecting that shutdown is in process.
     */
    private boolean shutdown = false;

    /**
     * Whether to print attribute reports.
     */
    private boolean printAttributeReports = false;

    /**
     * Map of registered commands and their implementations.
     */
    private Map<String, ConsoleCommand> commands = new TreeMap<String, ConsoleCommand>();
    private Map<String, ZigBeeConsoleCommand> newCommands = new TreeMap<>();

    /**
     * The ZigBee API.
     */
    private ZigBeeApi zigBeeApi;

    private ZigBeeNetworkManager networkManager;
    private final ZigBeeTransportTransmit dongle;

    /**
     * Constructor which configures ZigBee API and constructs commands.
     *
     * @param dongle the {@link ZigBeeTransportTransmit}
     * @param transportCommands list of {@link ZigBeeConsoleCommand} to send to the transport
     */
    public ZigBeeConsole(final ZigBeeNetworkManager networkManager, final ZigBeeTransportTransmit dongle,
            List<Class<? extends ZigBeeConsoleCommand>> transportCommands) {
        this.dongle = dongle;
        this.networkManager = networkManager;

        // Add the extensions to the network
        networkManager.addExtension(new ZigBeeIasCieExtension());
        networkManager.addExtension(new ZigBeeOtaUpgradeExtension());

        ZigBeeDiscoveryExtension discoveryExtension = new ZigBeeDiscoveryExtension();
        discoveryExtension.setUpdatePeriod(60);
        networkManager.addExtension(discoveryExtension);

        createCommands(newCommands, transportCommands);

        commands.put("groupadd", new GroupAddCommand());
        commands.put("groupremove", new GroupRemoveCommand());
        commands.put("grouplist", new GroupListCommand());

        commands.put("membershipadd", new MembershipAddCommand());
        commands.put("membershipremove", new MembershipRemoveCommand());
        commands.put("membershipview", new MembershipViewCommand());
        commands.put("membershiplist", new MembershipListCommand());

        commands.put("quit", new QuitCommand());
        commands.put("help", new HelpCommand());
        commands.put("descriptor", new SetDescriptorCommand());
        commands.put("on", new OnCommand());
        commands.put("off", new OffCommand());
        commands.put("color", new ColorCommand());
        commands.put("level", new LevelCommand());
        commands.put("listen", new ListenCommand());
        commands.put("unlisten", new UnlistenCommand());

        commands.put("ota", new OtaCommand());
        commands.put("otafile", new OtaFileCommand());

        commands.put("lqi", new LqiCommand());
        commands.put("enroll", new EnrollCommand());

        commands.put("firmware", new FirmwareCommand());

        commands.put("supportedcluster", new SupportedClusterCommand());
        commands.put("trustcentre", new TrustCentreCommand());

        commands.put("rediscover", new RediscoverCommand());

        commands.put("stress", new StressCommand());

        newCommands.put("nodes", new ZigBeeConsoleNodeListCommand());
        newCommands.put("endpoint", new ZigBeeConsoleDescribeEndpointCommand());
        newCommands.put("node", new ZigBeeConsoleDescribeNodeCommand());
        newCommands.put("bind", new ZigBeeConsoleBindCommand());
        newCommands.put("unbind", new ZigBeeConsoleUnbindCommand());
        newCommands.put("bindtable", new ZigBeeConsoleBindingTableCommand());

        newCommands.put("read", new ZigBeeConsoleAttributeReadCommand());
        newCommands.put("write", new ZigBeeConsoleAttributeWriteCommand());

        newCommands.put("attsupported", new ZigBeeConsoleAttributeSupportedCommand());
        newCommands.put("cmdsupported", new ZigBeeConsoleCommandsSupportedCommand());

        newCommands.put("info", new ZigBeeConsoleDeviceInformationCommand());
        newCommands.put("join", new ZigBeeConsoleNetworkJoinCommand());
        newCommands.put("leave", new ZigBeeConsoleNetworkLeaveCommand());

        newCommands.put("reporting", new ZigBeeConsoleReportingConfigCommand());
        newCommands.put("subscribe", new ZigBeeConsoleReportingSubscribeCommand());
        newCommands.put("unsubscribe", new ZigBeeConsoleReportingUnsubscribeCommand());

        newCommands.put("installkey", new ZigBeeConsoleInstallKeyCommand());
        newCommands.put("linkkey", new ZigBeeConsoleLinkKeyCommand());

        newCommands.put("netstart", new ZigBeeConsoleNetworkStartCommand());
        newCommands.put("netbackup", new ZigBeeConsoleNetworkBackupCommand());
        newCommands.put("discovery", new ZigBeeConsoleNetworkDiscoveryCommand());

        newCommands.put("otaupgrade", new ZigBeeConsoleOtaUpgradeCommand());

        zigBeeApi = new ZigBeeApi(networkManager);

        networkManager.addNetworkStateListener(new ZigBeeNetworkStateListener() {
            @Override
            public void networkStateUpdated(ZigBeeNetworkState state) {
                print("ZigBee network state updated to " + state.toString(), System.out);
            }
        });

        networkManager.addNetworkNodeListener(new ZigBeeNetworkNodeListener() {
            @Override
            public void nodeAdded(ZigBeeNode node) {
                print("Node Added " + node, System.out);
            }

            @Override
            public void nodeUpdated(ZigBeeNode node) {
                print("Node Updated " + node, System.out);
            }

            @Override
            public void nodeRemoved(ZigBeeNode node) {
                print("Node Removed " + node, System.out);
            }
        });

        networkManager.addCommandListener(new ZigBeeCommandListener() {
            @Override
            public void commandReceived(ZigBeeCommand command) {
                if (printAttributeReports && command instanceof ReportAttributesCommand) {
                    print("Received: " + command.toString(), System.out);
                }
            }
        });

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                shutdown = true;
                try {
                    System.in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    mainThread.interrupt();
                    mainThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }));
    }

    private void createCommands(Map<String, ZigBeeConsoleCommand> commands,
            List<Class<? extends ZigBeeConsoleCommand>> transportCommands) {
        for (Class<? extends ZigBeeConsoleCommand> commandClass : transportCommands) {
            try {
                ZigBeeConsoleCommand command = commandClass.newInstance();
                commands.put(command.getCommand(), command);
            } catch (InstantiationException | IllegalAccessException | SecurityException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Starts this console application
     */
    public void start() {
        mainThread = Thread.currentThread();
        System.out.print("ZigBee API starting up...");

        print("ZigBee console ready.", System.out);

        print("PAN ID          = " + networkManager.getZigBeePanId(), System.out);
        print("Extended PAN ID = " + networkManager.getZigBeeExtendedPanId(), System.out);
        print("Channel         = " + networkManager.getZigBeeChannel(), System.out);

        String inputLine;
        while (!shutdown && (inputLine = readLine()) != null) {
            processInputLine(inputLine, System.out);
        }

        networkManager.shutdown();
    }

    /**
     * Processes text input line.
     *
     * @param inputLine the input line
     * @param out the output stream
     */
    public void processInputLine(final String inputLine, final PrintStream out) {
        if (inputLine.length() == 0) {
            return;
        }
        final String[] args = inputLine.replaceAll("\\s+", " ").split(" ");
        processArgs(args, out);
    }

    /**
     * Processes input arguments.
     *
     * @param args the input arguments
     * @param out the output stream
     */
    public void processArgs(final String[] args, final PrintStream out) {
        try {
            // if (commands.containsKey(args[0])) {
            executeCommand(networkManager, args, out);
            // } else {
            // print("Uknown command. Use 'help' command to list available commands.", out);
            // }
        } catch (final Exception e) {
            print("Exception in command execution: ", out);
            e.printStackTrace(out);
        }
    }

    public ZigBeeApi getZigBeeApi() {
        return zigBeeApi;
    }

    /**
     * Executes command.
     *
     * @param networkManager the {@link ZigBeeNetworkManager}
     * @param args the arguments including the command
     * @param out the output stream
     */
    private void executeCommand(final ZigBeeNetworkManager networkManager, final String[] args, final PrintStream out) {
        final ConsoleCommand consoleCommand = commands.get(args[0].toLowerCase());
        if (consoleCommand != null) {
            try {
                consoleCommand.process(zigBeeApi, args, out);
            } catch (Exception e) {
                out.println("Error executing command: " + e);
                e.printStackTrace(out);
            }
            return;
        }

        final ZigBeeConsoleCommand newCommand = newCommands.get(args[0].toLowerCase());
        if (newCommand != null) {
            try {
                newCommand.process(networkManager, args, out);
            } catch (IllegalArgumentException | IllegalStateException e) {
                out.println("Error executing command: " + e.getMessage());
                out.println(newCommand.getCommand() + " " + newCommand.getSyntax());
            } catch (Exception e) {
                out.println("Error executing command: " + e);
                e.printStackTrace(out);
            }
            return;
        }

        print("Uknown command. Use 'help' command to list available commands.", out);
    }

    /**
     * Prints line to console.
     *
     * @param line the line
     */
    private static void print(final String line, final PrintStream out) {
        out.println("\r" + line);
        // if (out == System.out) {
        // System.out.print("\r> ");
        // }
    }

    /**
     * Reads line from console.
     *
     * @return line readLine from console or null if exception occurred.
     */
    private String readLine() {
        System.out.print("\r> ");
        try {
            final BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
            return bufferRead.readLine();
        } catch (final IOException e) {
            return null;
        }
    }

    /**
     * Gets destination by device identifier or group ID.
     *
     * @param zigbeeApi the ZigBee API
     * @param destinationIdentifier the device identifier or group ID
     * @return the device
     */
    private ZigBeeAddress getDestination(final ZigBeeApi zigbeeApi, final String destinationIdentifier,
            final PrintStream out) {
        final ZigBeeEndpoint device = getDevice(zigbeeApi, destinationIdentifier);

        if (device != null) {
            return device.getEndpointAddress();
        }

        try {
            for (final ZigBeeGroupAddress group : zigbeeApi.getGroups()) {
                if (destinationIdentifier.equals(group.getLabel())) {
                    return group;
                }
            }
            final int groupId = Integer.parseInt(destinationIdentifier);
            return zigbeeApi.getGroup(groupId);
        } catch (final NumberFormatException e) {
            return null;
        }
    }

    /**
     * Gets device by device identifier.
     *
     * @param zigbeeApi the ZigBee API
     * @param deviceIdentifier the device identifier
     * @return the device
     */
    private ZigBeeEndpoint getDevice(ZigBeeApi zigbeeApi, final String deviceIdentifier) {
        for (final ZigBeeNode node : networkManager.getNodes()) {
            for (final ZigBeeEndpoint endpoint : node.getEndpoints()) {
                if (deviceIdentifier.equals(node.getNetworkAddress() + "/" + endpoint.getEndpointId())) {
                    return endpoint;
                }
            }
        }
        return null;
    }

    /**
     * Interface for console commands.
     */
    private interface ConsoleCommand {
        /**
         * Get command description.
         *
         * @return the command description
         */
        String getDescription();

        /**
         * Get command syntax.
         *
         * @return the command syntax
         */
        String getSyntax();

        /**
         * Processes console command.
         *
         * @param zigbeeApi the ZigBee API
         * @param args the command arguments
         * @param out the output PrintStream
         * @return true if command syntax was correct.
         */
        boolean process(final ZigBeeApi zigbeeApi, final String[] args, PrintStream out) throws Exception;
    }

    /**
     * Quits console.
     */
    private class QuitCommand implements ConsoleCommand {
        /**
         * {@inheritDoc}
         */
        @Override
        public String getDescription() {
            return "Quits console.";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getSyntax() {
            return "quit";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean process(final ZigBeeApi zigbeeApi, final String[] args, PrintStream out) {
            shutdown = true;
            return true;
        }
    }

    /**
     * Prints help on console.
     */
    private class HelpCommand implements ConsoleCommand {
        /**
         * {@inheritDoc}
         */
        @Override
        public String getDescription() {
            return "View command help.";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getSyntax() {
            return "help [command]";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean process(final ZigBeeApi zigbeeApi, final String[] args, PrintStream out) {

            if (args.length == 2) {
                if (commands.containsKey(args[1])) {
                    final ConsoleCommand command = commands.get(args[1]);
                    print(command.getDescription(), out);
                    print("", out);
                    print("Syntax: " + command.getSyntax(), out);
                } else {
                    return false;
                }
            } else if (args.length == 1) {
                final List<String> commandList = new ArrayList<String>(commands.keySet());
                Collections.sort(commandList);
                print("Commands:", out);
                for (final String command : commands.keySet()) {
                    print(command + " - " + commands.get(command).getDescription(), out);
                }
                for (final String command : newCommands.keySet()) {
                    print(command + " - " + newCommands.get(command).getDescription(), out);
                }
            } else {
                return false;
            }

            return true;
        }
    }

    /**
     * Lists groups in gateway network state.
     */
    private class GroupListCommand implements ConsoleCommand {
        /**
         * {@inheritDoc}
         */
        @Override
        public String getDescription() {
            return "Lists groups in gateway network state.";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getSyntax() {
            return "grouplist";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean process(final ZigBeeApi zigbeeApi, final String[] args, PrintStream out) {
            final List<ZigBeeGroupAddress> groups = zigbeeApi.getGroups();
            for (final ZigBeeGroupAddress group : groups) {
                print(StringUtils.leftPad(Integer.toString(group.getGroupId()), 10) + "      " + group.getLabel(), out);
            }
            return true;
        }
    }

    /**
     * Switches a device on.
     */
    private class OnCommand implements ConsoleCommand {
        /**
         * {@inheritDoc}
         */
        @Override
        public String getDescription() {
            return "Switches device on.";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getSyntax() {
            return "on DEVICEID/DEVICELABEL/GROUPID";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean process(final ZigBeeApi zigbeeApi, final String[] args, PrintStream out) throws Exception {
            if (args.length != 2) {
                return false;
            }

            final ZigBeeAddress destination = getDestination(zigbeeApi, args[1], out);

            if (destination == null) {
                return false;
            }

            final CommandResult response = zigbeeApi.on(destination).get();
            return defaultResponseProcessing(response, out);
        }
    }

    /**
     * Switches a device off.
     */
    private class OffCommand implements ConsoleCommand {
        /**
         * {@inheritDoc}
         */
        @Override
        public String getDescription() {
            return "Switches device off.";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getSyntax() {
            return "off DEVICEID/DEVICELABEL/GROUPID";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean process(final ZigBeeApi zigbeeApi, final String[] args, PrintStream out) throws Exception {
            if (args.length != 2) {
                return false;
            }

            final ZigBeeAddress destination = getDestination(zigbeeApi, args[1], out);

            if (destination == null) {
                return false;
            }

            final CommandResult response = zigbeeApi.off(destination).get();
            return defaultResponseProcessing(response, out);
        }
    }

    /**
     * Changes a light color on device.
     */
    private class ColorCommand implements ConsoleCommand {
        /**
         * {@inheritDoc}
         */
        @Override
        public String getDescription() {
            return "Changes light color.";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getSyntax() {
            return "color DEVICEID RED GREEN BLUE";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean process(final ZigBeeApi zigbeeApi, final String[] args, PrintStream out) throws Exception {
            if (args.length != 5) {
                return false;
            }

            final ZigBeeAddress destination = getDestination(zigbeeApi, args[1], out);
            if (destination == null) {
                return false;
            }

            float red;
            try {
                red = Float.parseFloat(args[2]);
            } catch (final NumberFormatException e) {
                return false;
            }
            float green;
            try {
                green = Float.parseFloat(args[3]);
            } catch (final NumberFormatException e) {
                return false;
            }
            float blue;
            try {
                blue = Float.parseFloat(args[4]);
            } catch (final NumberFormatException e) {
                return false;
            }

            final CommandResult response = zigbeeApi.color(destination, red, green, blue, 1).get();
            return defaultResponseProcessing(response, out);
        }
    }

    /**
     * Adds group to gateway network state. Does not affect actual ZigBee network.
     */
    private class GroupAddCommand implements ConsoleCommand {
        /**
         * {@inheritDoc}
         */
        @Override
        public String getDescription() {
            return "Adds group to gateway network state.";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getSyntax() {
            return "groupadd GROUPID GROUPLABEL";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean process(final ZigBeeApi zigbeeApi, final String[] args, PrintStream out) throws Exception {
            if (args.length != 3) {
                return false;
            }

            final int groupId;
            try {
                groupId = Integer.parseInt(args[1]);
            } catch (final NumberFormatException e) {
                return false;
            }

            final String label = args[2];
            ZigBeeGroupAddress group = new ZigBeeGroupAddress();
            group.setLabel(label);
            networkManager.addGroup(group);

            return true;
        }
    }

    /**
     * Removes group from network state but does not affect actual ZigBeet network.
     */
    private class GroupRemoveCommand implements ConsoleCommand {
        /**
         * {@inheritDoc}
         */
        @Override
        public String getDescription() {
            return "Removes group from gateway network state.";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getSyntax() {
            return "groupremove GROUP";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean process(final ZigBeeApi zigbeeApi, final String[] args, PrintStream out) throws Exception {
            if (args.length != 2) {
                return false;
            }

            final ZigBeeAddress destination = getDestination(zigbeeApi, args[1], out);
            if (destination == null) {
                return false;
            }
            if (!(destination instanceof ZigBeeGroupAddress)) {
                return false;
            }

            final ZigBeeGroupAddress group = (ZigBeeGroupAddress) destination;

            networkManager.removeGroup(group.getGroupId());

            return true;
        }
    }

    /**
     * Sets device user descriptor.
     */
    private class SetDescriptorCommand implements ConsoleCommand {
        /**
         * {@inheritDoc}
         */
        @Override
        public String getDescription() {
            return "Sets device user descriptor to 0-16 US-ASCII character string.";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getSyntax() {
            return "descriptor DEVICEID DEVICELABEL";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean process(final ZigBeeApi zigbeeApi, final String[] args, PrintStream out) throws Exception {
            if (args.length != 3) {
                return false;
            }

            final ZigBeeEndpoint device = getDevice(zigbeeApi, args[1]);
            if (device == null) {
                return false;
            }

            final String label = args[2];

            return false;
            // final CommandResult response = zigbeeApi.describe(device, label).get();
            // return defaultResponseProcessing(response, out);
        }
    }

    /**
     * Changes a device level for example lamp brightness.
     */
    private class LevelCommand implements ConsoleCommand {
        /**
         * {@inheritDoc}
         */
        @Override
        public String getDescription() {
            return "Changes device level for example lamp brightness, where LEVEL is between 0 and 1.";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getSyntax() {
            return "level DEVICEID LEVEL [RATE]";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean process(final ZigBeeApi zigbeeApi, final String[] args, PrintStream out) throws Exception {
            if (args.length < 3) {
                return false;
            }

            final ZigBeeAddress destination = getDestination(zigbeeApi, args[1], out);
            if (destination == null) {
                return false;
            }

            float level;
            try {
                level = Float.parseFloat(args[2]);
            } catch (final NumberFormatException e) {
                return false;
            }

            float time = (float) 1.0;
            if (args.length == 4) {
                try {
                    time = Float.parseFloat(args[3]);
                } catch (final NumberFormatException e) {
                    return false;
                }
            }

            final CommandResult response = zigbeeApi.level(destination, level, time).get();
            return defaultResponseProcessing(response, out);
        }
    }

    /**
     * Starts listening to reports of given attribute.
     */
    private class ListenCommand implements ConsoleCommand {
        /**
         * {@inheritDoc}
         */
        @Override
        public String getDescription() {
            return "Listen to attribute reports.";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getSyntax() {
            return "listen";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean process(final ZigBeeApi zigbeeApi, final String[] args, PrintStream out) {
            if (args.length != 1) {
                return false;
            }

            printAttributeReports = true;

            out.println("Printing received attribute reports.");

            return true;
        }
    }

    /**
     * Unlisten from reports of given attribute.
     */
    private class UnlistenCommand implements ConsoleCommand {
        /**
         * {@inheritDoc}
         */
        @Override
        public String getDescription() {
            return "Unlisten from attribute reports.";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getSyntax() {
            return "unlisten";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean process(final ZigBeeApi zigbeeApi, final String[] args, PrintStream out) {
            if (args.length != 1) {
                return false;
            }

            printAttributeReports = false;

            out.println("Ignoring received attribute reports.");

            return true;
        }
    }

    /**
     * Subscribes to reports of given attribute.
     */
    private class SubscribeCommand implements ConsoleCommand {
        /**
         * {@inheritDoc}
         */
        @Override
        public String getDescription() {
            return "Subscribe to attribute reports.";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getSyntax() {
            return "subscribe [DEVICE] [CLUSTER] [ATTRIBUTE] [MIN-INTERVAL] [MAX-INTERVAL] [REPORTABLE-CHANGE]";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean process(final ZigBeeApi zigbeeApi, final String[] args, PrintStream out) throws Exception {
            if (args.length != 6 && args.length != 7) {
                return false;
            }

            final ZigBeeEndpoint device = getDevice(zigbeeApi, args[1]);
            if (device == null) {
                print("Device not found.", out);
                return false;
            }

            final int clusterId;
            try {
                clusterId = Integer.parseInt(args[2]);
            } catch (final NumberFormatException e) {
                return false;
            }
            final int attributeId;
            try {
                attributeId = Integer.parseInt(args[3]);
            } catch (final NumberFormatException e) {
                return false;
            }
            final int minInterval;
            try {
                minInterval = Integer.parseInt(args[4]);
            } catch (final NumberFormatException e) {
                return false;
            }
            final int maxInterval;
            try {
                maxInterval = Integer.parseInt(args[5]);
            } catch (final NumberFormatException e) {
                return false;
            }

            final ZclCluster cluster = device.getCluster(clusterId);
            final ZclAttribute attribute = cluster.getAttribute(attributeId);
            Object reportableChange = null;
            if (args.length > 6) {
                reportableChange = parseValue(args[6], attribute.getDataType());
            }

            ZclAttribute zclAttribute = cluster.getAttribute(attributeId);
            if (zclAttribute == null) {
                out.println("Attribute not known.");
                return false;
            }

            final CommandResult result = cluster.setReporting(zclAttribute, minInterval, maxInterval, reportableChange)
                    .get();
            if (result.isSuccess()) {
                final ConfigureReportingResponse response = result.getResponse();
                final ZclStatus statusCode = response.getRecords().get(0).getStatus();
                if (statusCode == ZclStatus.SUCCESS) {
                    out.println("Attribute value configure reporting success.");
                } else {
                    out.println("Attribute value configure reporting error: " + statusCode);
                }
                return true;
            } else {
                out.println("Error executing command: " + result);
                return true;
            }
        }
    }

    /**
     * Unsubscribes from reports of given attribute.
     */
    private class UnsubscribeCommand implements ConsoleCommand {
        /**
         * {@inheritDoc}
         */
        @Override
        public String getDescription() {
            return "Unsubscribe from attribute reports.";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getSyntax() {
            return "unsubscribe [DEVICE] [CLUSTER] [ATTRIBUTE] [REPORTABLE-CHANGE]";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean process(final ZigBeeApi zigbeeApi, final String[] args, PrintStream out) throws Exception {
            if (args.length != 4 && args.length != 5) {
                return false;
            }

            final ZigBeeEndpoint device = getDevice(zigbeeApi, args[1]);
            final int clusterId;
            try {
                clusterId = Integer.parseInt(args[2]);
            } catch (final NumberFormatException e) {
                return false;
            }
            final int attributeId;
            try {
                attributeId = Integer.parseInt(args[3]);
            } catch (final NumberFormatException e) {
                return false;
            }

            final ZclCluster cluster = device.getCluster(clusterId);
            final ZclAttribute attribute = cluster.getAttribute(attributeId);
            Object reportableChange = null;
            if (args.length > 4) {
                reportableChange = parseValue(args[4], attribute.getDataType());
            }

            ZclAttribute zclAttribute = cluster.getAttribute(attributeId);
            if (zclAttribute == null) {
                out.println("Attribute not known.");
                return false;
            }

            final CommandResult result = cluster.setReporting(zclAttribute, 0, 0xffff, reportableChange).get();
            if (result.isSuccess()) {
                final ConfigureReportingResponse response = result.getResponse();
                final ZclStatus statusCode = response.getRecords().get(0).getStatus();
                if (statusCode == ZclStatus.SUCCESS) {
                    out.println("Attribute value configure reporting success.");
                } else {
                    out.println("Attribute value configure reporting error: " + statusCode);
                }
                return true;
            } else {
                out.println("Error executing command: " + result);
                return true;
            }

        }
    }

    /**
     * Support for OTA server.
     */
    private class OtaCommand implements ConsoleCommand {
        /**
         * {@inheritDoc}
         */
        @Override
        public String getDescription() {
            return "Upgrade the firmware of a node using the OTA server.";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getSyntax() {
            return "ota [ENDPOINT] [COMPLETE | FILE]";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean process(final ZigBeeApi zigbeeApi, final String[] args, final PrintStream out) throws Exception {
            if (args.length != 3) {
                return false;
            }

            final ZigBeeEndpoint endpoint = getDevice(zigbeeApi, args[1]);
            if (endpoint == null) {
                print("Endpoint not found.", out);
                return false;
            }

            // Check if the OTA server is already set
            ZclOtaUpgradeServer otaServer = (ZclOtaUpgradeServer) endpoint
                    .getApplication(ZclOtaUpgradeCluster.CLUSTER_ID);
            if (otaServer == null) {
                // Create and add the server
                otaServer = new ZclOtaUpgradeServer();

                endpoint.addApplication(otaServer);

                otaServer.addListener(new ZigBeeOtaStatusCallback() {
                    @Override
                    public void otaStatusUpdate(ZigBeeOtaServerStatus status, int percent) {
                        print("OTA status callback: " + status + ", percent=" + percent, out);
                    }
                });
            }

            if (args[2].toLowerCase().equals("complete")) {
                otaServer.completeUpgrade();
            } else {
                Path file = FileSystems.getDefault().getPath("./", args[2]);
                byte[] fileData = Files.readAllBytes(file);
                ZigBeeOtaFile otaFile = new ZigBeeOtaFile(fileData);
                print("OTA File: " + otaFile, out);

                otaServer.setFirmware(otaFile);
            }
            return true;
        }
    }

    /**
     * Support for OTA server.
     */
    private class OtaFileCommand implements ConsoleCommand {
        /**
         * {@inheritDoc}
         */
        @Override
        public String getDescription() {
            return "Dump information about an OTA file.";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getSyntax() {
            return "ota [FILE]";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean process(final ZigBeeApi zigbeeApi, final String[] args, PrintStream out) throws Exception {
            if (args.length != 2) {
                return false;
            }

            Path file = FileSystems.getDefault().getPath("./", args[1]);
            byte[] fileData = Files.readAllBytes(file);

            ZigBeeOtaFile otaFile = new ZigBeeOtaFile(fileData);
            print("OTA File: " + otaFile, out);
            return true;
        }
    }

    /**
     * Writes an attribute to a device.
     */
    private class LqiCommand implements ConsoleCommand {
        /**
         * {@inheritDoc}
         */
        @Override
        public String getDescription() {
            return "List LQI neighbours list.";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getSyntax() {
            return "lqi";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean process(final ZigBeeApi zigbeeApi, final String[] args, PrintStream out) {
            // ZigBeeDiscoveryManager discoveryMan = zigbeeApi.getZigBeeDiscoveryManager();
            // NetworkNeighbourLinks neighbors = discoveryMan.getLinkQualityInfo();
            // final List<ZigBeeNode> nodes = zigbeeApi.getNodes();
            // for (int i = 0; i < nodes.size(); i++) {
            // final ZigBeeNode src = nodes.get(i);
            //
            // for (int j = 0; j < nodes.size(); j++) {
            // final ZigBeeNode dst = nodes.get(j);
            // int lqiLast = neighbors.getLast(src.getNetworkAddress(), dst.getNetworkAddress());
            // if(lqiLast != -1) {
            // print("Node #" + src.getNetworkAddress() + " receives node #" + dst.getNetworkAddress() +
            // " with LQI " + lqiLast + " (" +
            // neighbors.getMin(src.getNetworkAddress(), dst.getNetworkAddress()) + "/" +
            // neighbors.getAvg(src.getNetworkAddress(), dst.getNetworkAddress()) + "/" +
            // neighbors.getMax(src.getNetworkAddress(), dst.getNetworkAddress()) + ")", out
            // );
            // }
            // }
            //// }
            // return true;

            throw new UnsupportedOperationException();
        }
    }

    /**
     * Enrolls IAS Zone device to this CIE device by setting own address as CIE address to the
     * IAS Zone device.
     */
    private class EnrollCommand implements ConsoleCommand {
        /**
         * {@inheritDoc}
         */
        @Override
        public String getDescription() {
            return "Enrolls IAS Zone device to this CIE device by setting own address as CIE address to the "
                    + " IAS Zone device.";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getSyntax() {
            return "enroll DEVICEID";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean process(final ZigBeeApi zigbeeApi, final String[] args, PrintStream out) {
            if (args.length != 2) {
                return false;
            }

            final ZigBeeEndpoint device = getDevice(zigbeeApi, args[1]);
            if (device == null) {
                return false;
            }

            throw new UnsupportedOperationException();
        }
    }

    /**
     * Adds group membership to device.
     */
    private class MembershipAddCommand implements ConsoleCommand {
        /**
         * {@inheritDoc}
         */
        @Override
        public String getDescription() {
            return "Adds group membership to device.";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getSyntax() {
            return "membershipadd [DEVICE] [GROUPID] [GROUPNAME]";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean process(final ZigBeeApi zigbeeApi, final String[] args, PrintStream out) throws Exception {
            if (args.length != 4) {
                return false;
            }

            final ZigBeeEndpoint device = getDevice(zigbeeApi, args[1]);
            if (device == null) {
                print("Device not found.", out);
                return false;
            }

            final int groupId;
            try {
                groupId = Integer.parseInt(args[2]);
            } catch (final NumberFormatException e) {
                return false;
            }

            final String groupName = args[3];

            final CommandResult result = zigbeeApi.addMembership(device, groupId, groupName).get();

            return defaultResponseProcessing(result, out);

        }
    }

    /**
     * Removes device group membership from device.
     */
    private class MembershipRemoveCommand implements ConsoleCommand {
        /**
         * {@inheritDoc}
         */
        @Override
        public String getDescription() {
            return "Removes group membership from device.";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getSyntax() {
            return "membershipremove [DEVICE] [GROUPID]";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean process(final ZigBeeApi zigbeeApi, final String[] args, PrintStream out) throws Exception {
            if (args.length != 3) {
                return false;
            }

            final ZigBeeEndpoint device = getDevice(zigbeeApi, args[1]);
            if (device == null) {
                print("Device not found.", out);
                return false;
            }

            final int groupId;
            try {
                groupId = Integer.parseInt(args[2]);
            } catch (final NumberFormatException e) {
                return false;
            }

            final CommandResult result = zigbeeApi.removeMembership(device, groupId).get();

            return defaultResponseProcessing(result, out);
        }
    }

    /**
     * Views group name from device group membership.
     */
    private class MembershipViewCommand implements ConsoleCommand {
        /**
         * {@inheritDoc}
         */
        @Override
        public String getDescription() {
            return "Views group name from device group membership.";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getSyntax() {
            return "membershipview [DEVICE] [GROUPID]";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean process(final ZigBeeApi zigbeeApi, final String[] args, PrintStream out) throws Exception {
            if (args.length != 3) {
                return false;
            }

            final ZigBeeEndpoint device = getDevice(zigbeeApi, args[1]);
            if (device == null) {
                print("Device not found.", out);
                return false;
            }

            final int groupId;
            try {
                groupId = Integer.parseInt(args[2]);
            } catch (final NumberFormatException e) {
                return false;
            }

            final CommandResult result = zigbeeApi.viewMembership(device, groupId).get();

            if (result.isSuccess()) {
                final ViewGroupResponse response = result.getResponse();
                final int statusCode = response.getStatus();
                if (statusCode == 0) {
                    out.println("Group name: " + response.getGroupName());
                } else {
                    final ZclStatus status = ZclStatus.getStatus((byte) statusCode);
                    out.println("Error reading group name: " + status);
                }
                return true;
            } else {
                out.println("Error executing command: " + result);
                return true;
            }
        }
    }

    /**
     * Lists group memberships from device.
     */
    private class MembershipListCommand implements ConsoleCommand {
        /**
         * {@inheritDoc}
         */
        @Override
        public String getDescription() {
            return "Lists group memberships from device.";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getSyntax() {
            return "membershiplist [DEVICE]";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean process(final ZigBeeApi zigbeeApi, final String[] args, PrintStream out) throws Exception {
            if (args.length != 2) {
                return false;
            }

            final ZigBeeEndpoint device = getDevice(zigbeeApi, args[1]);
            if (device == null) {
                print("Device not found.", out);
                return false;
            }

            final CommandResult result = zigbeeApi.getGroupMemberships(device).get();
            if (result.isSuccess()) {
                final GetGroupMembershipResponse response = result.getResponse();
                out.print("Member of groups:");
                for (final Integer value : response.getGroupList()) {
                    out.print(' ');
                    out.print(value);
                }
                out.println();
                return true;
            } else {
                out.println("Error executing command: " + result);
                return true;
            }
        }
    }

    /**
     * Trust centre configuration.
     */
    private class TrustCentreCommand implements ConsoleCommand {
        /**
         * {@inheritDoc}
         */
        @Override
        public String getDescription() {
            return "Configures the trust centre.";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getSyntax() {
            return "trustcentre [MODE|KEY] [MODE / KEY]";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean process(final ZigBeeApi zigbeeApi, final String[] args, PrintStream out) throws Exception {
            if (args.length < 3) {
                return false;
            }
            TransportConfig config = new TransportConfig();
            switch (args[1].toLowerCase()) {
                case "mode":
                    config.addOption(TransportConfigOption.TRUST_CENTRE_JOIN_MODE,
                            TrustCentreJoinMode.valueOf(args[2].toUpperCase()));
                    break;
                case "key":
                    String key = "";
                    for (int cnt = 0; cnt < 16; cnt++) {
                        key += args[cnt + 2];
                    }
                    config.addOption(TransportConfigOption.TRUST_CENTRE_LINK_KEY, new ZigBeeKey(key));
                    break;

                default:
                    return false;
            }

            TransportConfigOption option = config.getOptions().iterator().next();
            dongle.updateTransportConfig(config);
            print("Trust Centre configuration for " + option + " returned " + config.getResult(option), out);
            return true;
        }
    }

    /**
     * Locks door.
     */
    private class SupportedClusterCommand implements ConsoleCommand {
        /**
         * {@inheritDoc}
         */
        @Override
        public String getDescription() {
            return "Adds a cluster to the list of supported clusters";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getSyntax() {
            return "supportedcluster CLUSTER";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean process(final ZigBeeApi zigbeeApi, final String[] args, PrintStream out) throws Exception {
            if (args.length != 2) {
                return false;
            }

            int clusterId = 0;
            if (args[1].startsWith("0x")) {
                clusterId = Integer.parseInt(args[1].substring(2), 16);
            } else {
                clusterId = Integer.parseInt(args[1]);
            }

            networkManager.addSupportedCluster(clusterId);

            print("Added cluster " + String.format("0x%X", clusterId) + " to match descriptor list.", out);

            return true;
        }
    }

    /**
     * Dongle firmware update command.
     */
    private class FirmwareCommand implements ConsoleCommand {
        /**
         * {@inheritDoc}
         */
        @Override
        public String getDescription() {
            return "Updates the dongle firmware";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getSyntax() {
            return "firmware [VERSION | CANCEL | FILE]";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean process(final ZigBeeApi zigbeeApi, final String[] args, final PrintStream out) throws Exception {
            if (args.length != 2) {
                return false;
            }

            if (!(dongle instanceof ZigBeeTransportFirmwareUpdate)) {
                print("Dongle does not implement firmware updates.", out);
                return false;
            }
            ZigBeeTransportFirmwareUpdate firmwareUpdate = (ZigBeeTransportFirmwareUpdate) dongle;

            if (args[1].toLowerCase().equals("version")) {
                print("Dongle firmware version is currently " + firmwareUpdate.getFirmwareVersion(), out);
                return true;
            }

            if (args[1].toLowerCase().equals("cancel")) {
                print("Cancelling dongle firmware update!", out);
                firmwareUpdate.cancelUpdateFirmware();
                return true;
            }

            networkManager.shutdown();

            File firmwareFile = new File(args[1]);
            InputStream firmwareStream;
            try {
                firmwareStream = new FileInputStream(firmwareFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return false;
            }

            firmwareUpdate.updateFirmware(firmwareStream, new ZigBeeTransportFirmwareCallback() {
                @Override
                public void firmwareUpdateCallback(ZigBeeTransportFirmwareStatus status) {
                    print("Dongle firmware status: " + status + ".", out);
                }
            });

            out.println("Starting dongle firmware update...");
            return true;
        }
    }

    /**
     * Rediscover a node from its IEEE address.
     */
    private class RediscoverCommand implements ConsoleCommand {
        /**
         * {@inheritDoc}
         */
        @Override
        public String getDescription() {
            return "Rediscover a node from its IEEE address.";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getSyntax() {
            return "rediscover IEEEADDRESS";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean process(final ZigBeeApi zigbeeApi, final String[] args, PrintStream out) throws Exception {
            if (args.length != 2) {
                return false;
            }

            IeeeAddress address = new IeeeAddress(args[1]);

            print("Sending rediscovery request for address " + address, out);
            networkManager.rediscoverNode(address);
            return true;
        }
    }

    /**
     * Stress the system by sending a command to a node
     */
    private class StressCommand implements ConsoleCommand {
        /**
         * {@inheritDoc}
         */
        @Override
        public String getDescription() {
            return "Stress test. Note after sending this command you will need to stop the console.";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getSyntax() {
            return "stress NODEID";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean process(final ZigBeeApi zigbeeApi, final String[] args, final PrintStream out) throws Exception {
            if (args.length != 2) {
                return false;
            }

            final ZigBeeEndpoint endpoint = getDevice(zigbeeApi, args[1]);
            if (endpoint == null) {
                print("Endpoint not found.", out);
                return false;
            }

            new Thread(new Runnable() {
                @Override
                public void run() {
                    int cnt = 0;
                    while (true) {
                        print("STRESSING 1 CNT: " + cnt++, out);
                        ZclOnOffCluster cluster = (ZclOnOffCluster) endpoint
                                .getInputCluster(ZclOnOffCluster.CLUSTER_ID);
                        cluster.onCommand();
                        try {
                            Thread.sleep(167);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    int cnt = 0;
                    while (true) {
                        print("STRESSING 2 CNT: " + cnt++, out);
                        ZclOnOffCluster cluster = (ZclOnOffCluster) endpoint
                                .getInputCluster(ZclOnOffCluster.CLUSTER_ID);
                        cluster.onCommand();
                        try {
                            Thread.sleep(107);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    int cnt = 0;
                    while (true) {
                        print("STRESSING 3 CNT: " + cnt++, out);
                        ZclOnOffCluster cluster = (ZclOnOffCluster) endpoint
                                .getInputCluster(ZclOnOffCluster.CLUSTER_ID);
                        cluster.onCommand();
                        try {
                            Thread.sleep(131);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    int cnt = 0;
                    while (true) {
                        print("STRESSING 4 CNT: " + cnt++, out);
                        ZclOnOffCluster cluster = (ZclOnOffCluster) endpoint
                                .getInputCluster(ZclOnOffCluster.CLUSTER_ID);
                        cluster.onCommand();
                        try {
                            Thread.sleep(187);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();

            return true;
        }
    }

    /**
     * Default processing for command result.
     *
     * @param result the command result
     * @param out the output
     * @return TRUE if result is success
     */
    private boolean defaultResponseProcessing(CommandResult result, PrintStream out) {
        if (result.isSuccess()) {
            out.println("Success response received.");
            return true;
        } else {
            out.println("Error executing command: " + result);
            return true;
        }
    }

    /**
     * Parses string value to Object.
     *
     * @param stringValue the string value
     * @param zclDataType the ZigBee type
     * @return the value Object
     */
    private Object parseValue(String stringValue, ZclDataType zclDataType) {
        Object value = null;
        switch (zclDataType) {
            case BITMAP_16_BIT:
                value = Integer.parseInt(stringValue);
                break;
            // case BITMAP_24_BIT:
            // value = Integer.parseInt(stringValue);
            // break;
            // case BITMAP_32_BIT:
            // value = Integer.parseInt(stringValue);
            // break;
            case BITMAP_8_BIT:
                value = Integer.parseInt(stringValue);
                break;
            case BOOLEAN:
                value = Boolean.parseBoolean(stringValue);
                break;
            case CHARACTER_STRING:
                value = stringValue;
                break;
            // case DATA_16_BIT:
            // value = Integer.parseInt(stringValue);
            // break;
            // case DATA_24_BIT:
            // value = Integer.parseInt(stringValue);
            // break;
            // case DATA_32_BIT:
            // value = Integer.parseInt(stringValue);
            // break;
            case DATA_8_BIT:
                value = Integer.parseInt(stringValue);
                break;
            // case DOUBLE_PRECISION:
            // value = Double.parseDouble(stringValue);
            // break;
            case ENUMERATION_16_BIT:
                value = Integer.parseInt(stringValue);
                break;
            case ENUMERATION_8_BIT:
                value = Integer.parseInt(stringValue);
                break;
            case IEEE_ADDRESS:
                value = new IeeeAddress(stringValue);
                break;
            // case LONG_CHARACTER_STRING:
            // value = stringValue;
            // break;
            // case LONG_OCTET_STRING:
            // value = stringValue;
            // break;
            case OCTET_STRING:
                value = stringValue;
                break;
            // case SEMI_PRECISION:
            // throw new UnsupportedOperationException("SemiPrecision parsing not implemented");
            case SIGNED_8_BIT_INTEGER:
                value = Integer.parseInt(stringValue);
                break;
            case SIGNED_16_BIT_INTEGER:
                value = Integer.parseInt(stringValue);
                break;
            // case SIGNED_24_BIT_INTEGER:
            // value = Integer.parseInt(stringValue);
            // break;
            case SIGNED_32_BIT_INTEGER:
                value = Integer.parseInt(stringValue);
                break;
            // case SINGLE_PRECISION:
            // throw new UnsupportedOperationException("SinglePrecision parsing not implemented");
            case UNSIGNED_8_BIT_INTEGER:
                value = Integer.parseInt(stringValue);
                break;
            case UNSIGNED_16_BIT_INTEGER:
                value = Integer.parseInt(stringValue);
                break;
            // case UNSIGNED_24_BIT_INTEGER:
            // value = Integer.parseInt(stringValue);
            // break;
            case UNSIGNED_32_BIT_INTEGER:
                value = Integer.parseInt(stringValue);
                break;
            default:
                break;
        }
        return value;
    }
}

/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console;

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
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.Future;

import org.apache.commons.lang.StringUtils;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.ZigBeeAddress;
import com.zsmartsystems.zigbee.ZigBeeCommand;
import com.zsmartsystems.zigbee.ZigBeeCommandListener;
import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeGroupAddress;
import com.zsmartsystems.zigbee.ZigBeeKey;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNetworkNodeListener;
import com.zsmartsystems.zigbee.ZigBeeNetworkStateListener;
import com.zsmartsystems.zigbee.ZigBeeNode;
import com.zsmartsystems.zigbee.ZigBeeProfileType;
import com.zsmartsystems.zigbee.app.iasclient.ZigBeeIasCieApp;
import com.zsmartsystems.zigbee.app.otaserver.ZigBeeOtaFile;
import com.zsmartsystems.zigbee.app.otaserver.ZigBeeOtaServer;
import com.zsmartsystems.zigbee.app.otaserver.ZigBeeOtaServerStatus;
import com.zsmartsystems.zigbee.app.otaserver.ZigBeeOtaStatusCallback;
import com.zsmartsystems.zigbee.transport.TransportConfig;
import com.zsmartsystems.zigbee.transport.TransportConfigOption;
import com.zsmartsystems.zigbee.transport.TrustCentreJoinMode;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportFirmwareCallback;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportFirmwareStatus;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportFirmwareUpdate;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportState;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportTransmit;
import com.zsmartsystems.zigbee.zcl.ZclAttribute;
import com.zsmartsystems.zigbee.zcl.ZclCluster;
import com.zsmartsystems.zigbee.zcl.ZclStatus;
import com.zsmartsystems.zigbee.zcl.clusters.ZclBasicCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclIasZoneCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclOnOffCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclOtaUpgradeCluster;
import com.zsmartsystems.zigbee.zcl.clusters.general.ConfigureReportingResponse;
import com.zsmartsystems.zigbee.zcl.clusters.general.ReadAttributesResponse;
import com.zsmartsystems.zigbee.zcl.clusters.general.ReportAttributesCommand;
import com.zsmartsystems.zigbee.zcl.clusters.general.WriteAttributesResponse;
import com.zsmartsystems.zigbee.zcl.clusters.groups.GetGroupMembershipResponse;
import com.zsmartsystems.zigbee.zcl.clusters.groups.ViewGroupResponse;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zdo.field.NeighborTable;
import com.zsmartsystems.zigbee.zdo.field.RoutingTable;

/**
 * ZigBee command line console is an example usage of simple ZigBee API.
 * This implementation is experimental.
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

    /**
     * The ZigBee API.
     */
    private ZigBeeApi zigBeeApi;

    private ZigBeeNetworkManager networkManager;
    private final ZigBeeTransportTransmit dongle;

    /**
     * Constructor which configures ZigBee API and constructs commands.
     *
     * @param dongle the dongle
     */
    public ZigBeeConsole(final ZigBeeNetworkManager networkManager, final ZigBeeTransportTransmit dongle) {
        this.dongle = dongle;

        commands.put("node", new NodeCommand());
        commands.put("info", new InfoCommand());

        commands.put("devicelist", new DeviceListCommand());

        commands.put("groupadd", new GroupAddCommand());
        commands.put("groupremove", new GroupRemoveCommand());
        commands.put("grouplist", new GroupListCommand());

        commands.put("attsupported", new AttSupportedCommand());
        commands.put("cmdsupported", new CmdSupportedCommand());

        commands.put("membershipadd", new MembershipAddCommand());
        commands.put("membershipremove", new MembershipRemoveCommand());
        commands.put("membershipview", new MembershipViewCommand());
        commands.put("membershiplist", new MembershipListCommand());

        commands.put("quit", new QuitCommand());
        commands.put("help", new HelpCommand());
        commands.put("desc", new DescribeCommand());
        commands.put("descriptor", new SetDescriptorCommand());
        commands.put("on", new OnCommand());
        commands.put("off", new OffCommand());
        commands.put("color", new ColorCommand());
        commands.put("leave", new LeaveCommand());
        commands.put("level", new LevelCommand());
        commands.put("listen", new ListenCommand());
        commands.put("unlisten", new UnlistenCommand());
        commands.put("subscribe", new SubscribeCommand());
        commands.put("unsubscribe", new UnsubscribeCommand());

        commands.put("bind", new BindCommand());
        commands.put("unbind", new UnbindCommand());
        commands.put("bindtable", new BindTableCommand());

        commands.put("ota", new OtaCommand());
        commands.put("otafile", new OtaFileCommand());

        commands.put("read", new ReadCommand());
        commands.put("write", new WriteCommand());
        commands.put("reportcfg", new ReportConfigCommand());

        commands.put("join", new JoinCommand());
        commands.put("lqi", new LqiCommand());
        commands.put("warn", new WarnCommand());
        commands.put("squawk", new SquawkCommand());
        commands.put("lock", new DoorLockCommand());
        commands.put("unlock", new DoorUnlockCommand());
        commands.put("enroll", new EnrollCommand());

        commands.put("firmware", new FirmwareCommand());

        commands.put("supportedcluster", new SupportedClusterCommand());
        commands.put("trustcentre", new TrustCentreCommand());

        commands.put("rediscover", new RediscoverCommand());

        commands.put("stress", new StressCommand());

        this.networkManager = networkManager;
        zigBeeApi = new ZigBeeApi(networkManager);

        zigBeeApi.getNetwork().addNetworkStateListener(new ZigBeeNetworkStateListener() {
            @Override
            public void networkStateUpdated(ZigBeeTransportState state) {
                print("ZigBee network state updated to " + state.toString(), System.out);
            }
        });

        /*
         * zigBeeApi.getNetwork().addNetworkEndpointListener(new ZigBeeNetworkEndpointListener() {
         *
         * @Override
         * public void deviceAdded(ZigBeeEndpoint device) {
         * print("Device added:\n" + getDeviceSummary(device), System.out);
         * }
         *
         * @Override
         * public void deviceUpdated(ZigBeeEndpoint device) {
         * // print("Device updated:\n" + getDeviceSummary(device), System.out);
         * }
         *
         * @Override
         * public void deviceRemoved(ZigBeeEndpoint device) {
         * print("Device removed\n" + getDeviceSummary(device), System.out);
         * }
         * });
         */

        zigBeeApi.getNetwork().addNetworkNodeListener(new ZigBeeNetworkNodeListener() {
            @Override
            public void nodeAdded(ZigBeeNode node) {
                print("Node Added " + node, System.out);

                ZigBeeNode coordinator = networkManager.getNode(0);
                for (ZigBeeEndpoint endpoint : node.getEndpoints()) {
                    if (endpoint.getInputCluster(ZclIasZoneCluster.CLUSTER_ID) != null) {
                        endpoint.addExtension(new ZigBeeIasCieApp(coordinator.getIeeeAddress(), 0));
                        break;
                    }
                    if (endpoint.getInputCluster(ZclOtaUpgradeCluster.CLUSTER_ID) != null) {
                        endpoint.addExtension(new ZigBeeOtaServer());
                        break;
                    }
                }
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

        zigBeeApi.getNetwork().addCommandListener(new ZigBeeCommandListener() {
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
            if (commands.containsKey(args[0])) {
                executeCommand(zigBeeApi, args[0], args, out);
            } else {
                print("Uknown command. Use 'help' command to list available commands.", out);
            }
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
     * @param zigbeeApi the ZigBee API
     * @param command the command
     * @param args the arguments including the command
     * @param out the output stream
     */
    private void executeCommand(final ZigBeeApi zigbeeApi, final String command, final String[] args,
            final PrintStream out) {
        final ConsoleCommand consoleCommand = commands.get(command);
        try {
            if (!consoleCommand.process(zigbeeApi, args, out)) {
                print(consoleCommand.getSyntax(), out);
            }
        } catch (Exception e) {
            out.println("Error executing command: " + e);
            e.printStackTrace(out);
        }
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
     * Gets device summary.
     *
     * @param device the device
     * @return the summary
     */
    // private String getDeviceSummary(final ZigBeeEndpoint device) {
    // return StringUtils.leftPad(Integer.toString(device.getEndpoint()), 10) + "/"
    // + StringUtils.rightPad(Integer.toString(device.getEndpoint()), 3) + " " + device.getIeeeAddress()
    // ; // + " "
    // + ZigBeeApiConstants.getDeviceName(device.getProfileId(), device.getDeviceId());
    // }

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
        for (final ZigBeeNode node : zigbeeApi.getNetwork().getNodes()) {
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
            } else {
                return false;
            }

            return true;
        }
    }

    /**
     * Prints list of devices to console.
     */
    private class DeviceListCommand implements ConsoleCommand {
        /**
         * {@inheritDoc}
         */
        @Override
        public String getDescription() {
            return "Lists devices.";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getSyntax() {
            return "devicelist";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean process(final ZigBeeApi zigbeeApi, final String[] args, PrintStream out) {
            final Set<ZigBeeNode> nodes = zigbeeApi.getNetwork().getNodes();
            for (ZigBeeNode node : nodes) {
                print("Node: " + String.format("% 6d", node.getNetworkAddress()) + "  " + node.getIeeeAddress(), out);
                for (final ZigBeeEndpoint endpoint : node.getEndpoints()) {
                    print("    Endpoint: " + String.format("% 3d", endpoint.getEndpointId()), out);
                }
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
     * Prints device information to console.
     */
    private class DescribeCommand implements ConsoleCommand {
        /**
         * {@inheritDoc}
         */
        @Override
        public String getDescription() {
            return "Describes a device.";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getSyntax() {
            return "desc DEVICEID";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean process(final ZigBeeApi zigbeeApi, final String[] args, PrintStream out) {
            if (args.length != 2) {
                return false;
            }

            final ZigBeeEndpoint endpoint = getDevice(zigbeeApi, args[1]);
            if (endpoint == null) {
                return false;
            }

            print("IEEE Address     : " + endpoint.getParentNode().getIeeeAddress(), out);
            print("Network Address  : " + endpoint.getParentNode().getNetworkAddress(), out);
            print("Endpoint         : " + endpoint.getEndpointId(), out);
            print("Device Profile : " + ZigBeeProfileType.getProfileType(endpoint.getProfileId())
                    + String.format(" (0x%04X)", endpoint.getProfileId()), out);
            // print("Device Category : "
            // + ZigBeeApiConstants.getCategoryDeviceName(device.getProfileId(), device.getDeviceId())
            // + String.format(" (0x%04X)", device.getDeviceId()), out);
            // print("Device Type : " + ZigBeeApiConstants.getDeviceName(device.getProfileId(), device.getDeviceId()),
            // out);
            print("Device Version   : " + endpoint.getDeviceVersion(), out);
            print("Input Clusters   : ", out);
            printClusters(endpoint, endpoint.getInputClusterIds(), true, out);
            print("Output Clusters  : ", out);
            printClusters(endpoint, endpoint.getOutputClusterIds(), false, out);

            return true;
        }

        /**
         * Prints out clusters.
         *
         * @param device the device
         * @param collection the cluster IDs
         * @param input
         * @param out the output print stream
         */
        private void printClusters(final ZigBeeEndpoint device, final Collection<Integer> collection, boolean input,
                PrintStream out) {
            for (int clusterId : collection) {
                ZclCluster cluster;
                if (input) {
                    cluster = device.getInputCluster(clusterId);
                } else {
                    cluster = device.getOutputCluster(clusterId);
                }
                if (cluster != null) {
                    print("                 : " + clusterId + " " + cluster.getClusterName(), out);
                    for (ZclAttribute attribute : cluster.getAttributes()) {
                        print("                 :    " + attribute.getId() + " " + (attribute.isReadable() ? "r" : "-")
                                + (attribute.isWritable() ? "w" : "-") + (attribute.isReportable() ? "s" : "-") + " "
                                + attribute.getName() + " " + "  [" + attribute.getDataType() + "]" + " "
                                + (attribute.getLastValue() == null ? ""
                                        : attribute.getLastValue() + " @ " + attribute.getLastReportTime().getTime()),
                                out);
                    }
                }
            }
        }
    }

    /**
     * Prints node information to console.
     */
    private class NodeCommand implements ConsoleCommand {
        /**
         * {@inheritDoc}
         */
        @Override
        public String getDescription() {
            return "Describes a node.";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getSyntax() {
            return "node NODEID";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean process(final ZigBeeApi zigbeeApi, final String[] args, PrintStream out) {
            if (args.length != 2) {
                return false;
            }

            final int address = Integer.parseInt(args[1]);
            final ZigBeeNode node = zigbeeApi.getNetwork().getNode(address);

            if (node == null) {
                return false;
            }

            print("IEEE Address     : " + node.getIeeeAddress(), out);
            print("Network Address  : " + node.getNetworkAddress(), out);
            print("Node Descriptor  : " + node.getNodeDescriptor(), out);
            print("Power Descriptor : " + node.getPowerDescriptor(), out);
            print("Associations     : " + node.getAssociatedDevices().toString(), out);
            print("Endpoints:", out);
            for (ZigBeeEndpoint endpoint : node.getEndpoints()) {
                print(endpoint.toString(), out);
            }
            print("Neighbors:", out);
            for (NeighborTable neighbor : node.getNeighbors()) {
                print(neighbor.toString(), out);
            }
            print("Routes:", out);
            for (RoutingTable route : node.getRoutes()) {
                print(route.toString(), out);
            }

            return true;
        }
    }

    /**
     * Binds client device to server device with given cluster ID.
     */
    private class BindCommand implements ConsoleCommand {
        /**
         * {@inheritDoc}
         */
        @Override
        public String getDescription() {
            return "Binds a device to another device.";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getSyntax() {
            return "bind [SOURCEDEVICE] [DESTINATIONDEVICE] [CLUSTERID]";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean process(final ZigBeeApi zigbeeApi, final String[] args, PrintStream out) throws Exception {
            if (args.length != 4) {
                return false;
            }
            final ZigBeeEndpoint source = getDevice(zigbeeApi, args[1]);
            if (source == null) {
                return false;
            }
            final ZigBeeEndpoint destination = getDevice(zigbeeApi, args[2]);
            if (destination == null) {
                return false;
            }
            final int clusterId;
            try {
                clusterId = Integer.parseInt(args[3]);
            } catch (final NumberFormatException e) {
                return false;
            }
            final CommandResult response = zigBeeApi.bind(source, destination, clusterId).get();
            return defaultResponseProcessing(response, out);
        }
    }

    /**
     * Unbinds device from another device with given cluster ID.
     */
    private class UnbindCommand implements ConsoleCommand {
        /**
         * {@inheritDoc}
         */
        @Override
        public String getDescription() {
            return "Unbinds a device from another device.";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getSyntax() {
            return "unbind [SOURCEDEVICE] [DESTINATIONDEVICE] [CLUSTERID]";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean process(final ZigBeeApi zigbeeApi, final String[] args, PrintStream out) throws Exception {
            if (args.length != 4) {
                return false;
            }
            final ZigBeeEndpoint source = getDevice(zigbeeApi, args[1]);
            if (source == null) {
                return false;
            }
            final ZigBeeEndpoint destination = getDevice(zigbeeApi, args[2]);
            if (destination == null) {
                return false;
            }
            final int clusterId;
            try {
                clusterId = Integer.parseInt(args[3]);
            } catch (final NumberFormatException e) {
                return false;
            }
            final CommandResult response = zigBeeApi.unbind(source, destination, clusterId).get();
            return defaultResponseProcessing(response, out);
        }
    }

    /**
     * Reads the binding table for a node
     */
    private class BindTableCommand implements ConsoleCommand {
        /**
         * {@inheritDoc}
         */
        @Override
        public String getDescription() {
            return "Reads the binding table from a node.";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getSyntax() {
            return "bindtable [NODE]";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean process(final ZigBeeApi zigbeeApi, final String[] args, PrintStream out) throws Exception {
            if (args.length != 2) {
                return false;
            }

            ZigBeeNode node = networkManager.getNode(Integer.parseInt(args[1]));
            if (node == null) {
                print("Can't find node " + args[1], out);
                return false;
            }

            node.updateBindingTable();

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
     * Locks door.
     */
    private class DoorLockCommand implements ConsoleCommand {
        /**
         * {@inheritDoc}
         */
        @Override
        public String getDescription() {
            return "Locks door.";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getSyntax() {
            return "lock DEVICEID PINCODE";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean process(final ZigBeeApi zigbeeApi, final String[] args, PrintStream out) throws Exception {
            if (args.length != 3) {
                return false;
            }

            final ZigBeeAddress destination = getDestination(zigbeeApi, args[1], out);
            if (destination == null) {
                return false;
            }

            final String pinCode = args[2];

            final CommandResult response = zigbeeApi.lock(destination, pinCode).get();
            return defaultResponseProcessing(response, out);
        }
    }

    /**
     * Locks door.
     */
    private class DoorUnlockCommand implements ConsoleCommand {
        /**
         * {@inheritDoc}
         */
        @Override
        public String getDescription() {
            return "Unlocks door.";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getSyntax() {
            return "unlock DEVICEID PINCODE";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean process(final ZigBeeApi zigbeeApi, final String[] args, PrintStream out) throws Exception {
            if (args.length != 3) {
                return false;
            }

            final ZigBeeAddress destination = getDestination(zigbeeApi, args[1], out);
            if (destination == null) {
                return false;
            }

            final String pinCode = args[2];

            final CommandResult response = zigbeeApi.unlock(destination, pinCode).get();
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
            ZigBeeOtaServer otaServer = (ZigBeeOtaServer) endpoint.getExtension(ZclOtaUpgradeCluster.CLUSTER_ID);
            if (otaServer == null) {
                // Create and add the server
                otaServer = new ZigBeeOtaServer();

                endpoint.addExtension(otaServer);

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
     * Reads an attribute from a device.
     */
    private class ReadCommand implements ConsoleCommand {
        /**
         * {@inheritDoc}
         */
        @Override
        public String getDescription() {
            return "Read an attribute.";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getSyntax() {
            return "read [DEVICE] [CLUSTER] [ATTRIBUTE]";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean process(final ZigBeeApi zigbeeApi, final String[] args, PrintStream out) throws Exception {
            if (args.length != 4) {
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

            final ZigBeeEndpoint endpoint = getDevice(zigbeeApi, args[1]);
            if (endpoint == null) {
                print("Endpoint not found.", out);
                return false;
            }

            ZclCluster cluster = endpoint.getInputCluster(clusterId);
            if (cluster != null) {
                print("Using input cluster", out);
            } else {
                cluster = endpoint.getOutputCluster(clusterId);
                if (cluster != null) {
                    print("Using output cluster", out);
                } else {
                    print("Cluster not found", out);
                    return false;
                }

            }
            ZclAttribute attribute = cluster.getAttribute(attributeId);
            if (attribute == null) {
                print("Attribute not found", out);
                return false;
            }

            print("Reading " + cluster.getClusterName() + ", " + attribute.getName(), out);

            final CommandResult result = cluster.read(attribute).get();
            if (result.isSuccess()) {
                final ReadAttributesResponse response = result.getResponse();

                if (response.getRecords().size() == 0) {
                    out.println("No records returned");
                    return true;
                }

                final ZclStatus statusCode = response.getRecords().get(0).getStatus();
                if (statusCode == ZclStatus.SUCCESS) {
                    out.println("Cluster " + response.getClusterId() + ", Attribute "
                            + response.getRecords().get(0).getAttributeIdentifier() + ", type "
                            + response.getRecords().get(0).getAttributeDataType() + ", value: "
                            + response.getRecords().get(0).getAttributeValue());
                } else {
                    out.println("Attribute value read error: " + statusCode);
                }

                return true;
            } else {
                out.println("Error executing command: " + result);
                return true;
            }

        }
    }

    /**
     * Writes an attribute to a device.
     */
    private class WriteCommand implements ConsoleCommand {
        /**
         * {@inheritDoc}
         */
        @Override
        public String getDescription() {
            return "Write an attribute.";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getSyntax() {
            return "write [DEVICE] [CLUSTER] [ATTRIBUTE] [VALUE]";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean process(final ZigBeeApi zigbeeApi, final String[] args, PrintStream out) throws Exception {
            if (args.length != 5) {
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

            final ZigBeeEndpoint device = getDevice(zigbeeApi, args[1]);
            if (device == null) {
                print("Device not found.", out);
                return false;
            }

            final ZclCluster cluster = device.getCluster(clusterId);
            final ZclAttribute attribute = cluster.getAttribute(attributeId);

            final Object value = parseValue(args[4], attribute.getDataType());

            final CommandResult result = zigbeeApi.write(device.getEndpointAddress(), clusterId, attributeId, value)
                    .get();
            if (result.isSuccess()) {
                final WriteAttributesResponse response = result.getResponse();
                final int statusCode = response.getRecords().get(0).getStatus();
                if (statusCode == 0) {
                    out.println("Attribute value write success.");
                } else {
                    final ZclStatus status = ZclStatus.getStatus((byte) statusCode);
                    out.println("Attribute value write error: " + status);
                }
                return true;
            } else {
                out.println("Error executing command: " + result);
                return true;
            }
        }
    }

    /**
     * Reads an attribute from a device.
     */
    private class ReportConfigCommand implements ConsoleCommand {
        /**
         * {@inheritDoc}
         */
        @Override
        public String getDescription() {
            return "Read the report configuration of an attribute.";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getSyntax() {
            return "reportcfg [DEVICE] [CLUSTER] [ATTRIBUTE]";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean process(final ZigBeeApi zigbeeApi, final String[] args, PrintStream out) throws Exception {
            if (args.length != 4) {
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

            final ZigBeeEndpoint device = getDevice(zigbeeApi, args[1]);
            if (device == null) {
                print("Device not found.", out);
                return false;
            }
            ZclCluster cluster = device.getCluster(clusterId);

            ZclAttribute attribute = cluster.getAttribute(attributeId);

            final Future<CommandResult> future = cluster.getReporting(attribute);
            CommandResult result = future.get();

            if (result.isSuccess()) {
                final ReadAttributesResponse response = result.getResponse();

                final ZclStatus statusCode = response.getRecords().get(0).getStatus();
                if (statusCode == ZclStatus.SUCCESS) {
                    out.println("Cluster " + response.getClusterId() + ", Attribute "
                            + response.getRecords().get(0).getAttributeIdentifier() + ", type "
                            + response.getRecords().get(0).getAttributeDataType() + ", value: "
                            + response.getRecords().get(0).getAttributeValue());
                } else {
                    out.println("Attribute value read error: " + statusCode);
                }

                return true;
            } else {
                out.println("Error executing command: " + result);
                return true;
            }

        }
    }

    /**
     * Reads the list of supported commands in a specific cluster of a device.
     */
    private class CmdSupportedCommand implements ConsoleCommand {
        /**
         * {@inheritDoc}
         */
        @Override
        public String getDescription() {
            return "Check what commands are supported.";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getSyntax() {
            return "cmdsupported [DEVICE] [CLUSTER]";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean process(final ZigBeeApi zigbeeApi, final String[] args, PrintStream out) throws Exception {
            if (args.length != 3) {
                return false;
            }

            final int clusterId;
            try {
                clusterId = Integer.parseInt(args[2]);
            } catch (final NumberFormatException e) {
                return false;
            }

            final ZigBeeEndpoint device = getDevice(zigbeeApi, args[1]);
            if (device == null) {
                print("Device not found.", out);
                return false;
            }

            ZclCluster cluster = device.getInputCluster(clusterId);
            if (cluster == null) {
                cluster = device.getOutputCluster(clusterId);
                if (cluster == null) {
                    print("Cluster not found.", out);
                    return false;
                }
            }

            Future<Boolean> future = cluster.discoverCommandsReceived(false);
            Boolean result = future.get();

            if (result) {
                for (Integer cmd : cluster.getSupportedCommandsReceived()) {
                    out.println("Cluster " + cluster.getClusterId() + ", Command=" + cmd);
                }
            } else {
                out.println("Error getting list of commands received");
            }

            future = cluster.discoverCommandsGenerated(false);
            result = future.get();

            if (result) {
                for (Integer cmd : cluster.getSupportedCommandsGenerated()) {
                    out.println("Cluster " + cluster.getClusterId() + ", Command=" + cmd);
                }

            } else {
                out.println("Error getting list of commands generated");
            }

            return true;
        }
    }

    /**
     * Reads the list of supported attributes from a device.
     */
    private class AttSupportedCommand implements ConsoleCommand {
        /**
         * {@inheritDoc}
         */
        @Override
        public String getDescription() {
            return "Check what attributes are supported.";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getSyntax() {
            return "attsupported [DEVICE] [CLUSTER]";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean process(final ZigBeeApi zigbeeApi, final String[] args, PrintStream out) throws Exception {
            if (args.length != 3) {
                return false;
            }

            final int clusterId;
            try {
                clusterId = Integer.parseInt(args[2]);
            } catch (final NumberFormatException e) {
                return false;
            }

            final ZigBeeEndpoint device = getDevice(zigbeeApi, args[1]);
            if (device == null) {
                print("Device not found.", out);
                return false;
            }

            ZclCluster cluster = device.getInputCluster(clusterId);
            if (cluster == null) {
                cluster = device.getOutputCluster(clusterId);
                if (cluster == null) {
                    print("Cluster not found.", out);
                    return false;
                }
            }

            final Future<Boolean> future = cluster.discoverAttributes(false);
            Boolean result = future.get();

            if (result) {
                for (Integer attributeId : cluster.getSupportedAttributes()) {
                    ZclAttribute attribute = cluster.getAttribute(attributeId);
                    out.print("Cluster " + cluster.getClusterId() + ", Attribute=" + attributeId);
                    if (attribute != null) {
                        out.print(",  Type=" + attribute.getDataType() + ", " + attribute.getName());
                    }
                    out.println();
                }

                return true;
            } else {
                out.println("Failed to retrieve supported attributes");
                return true;
            }
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

    private class WarnCommand implements ConsoleCommand {

        @Override
        public String getDescription() {
            return "Warn IAS warning device.";
        }

        @Override
        public String getSyntax() {
            return "warn [DEVICE] [MODE] [STROBE] [DURATION]";
        }

        @Override
        public boolean process(ZigBeeApi zigbeeApi, String[] args, PrintStream out) throws Exception {
            if (args.length != 5) {
                return false;
            }

            final ZigBeeAddress destination = getDestination(zigbeeApi, args[1], out);
            if (destination == null) {
                print("Device not found.", out);
                return false;
            }

            final int mode;
            try {
                mode = Integer.parseInt(args[2]);
                if (mode < 0 || mode > 15) {
                    print("Warning mode should be in range [0, 15].", out);
                    return false;
                }
            } catch (NumberFormatException e) {
                return false;
            }

            final int strobe;
            try {
                strobe = Integer.parseInt(args[3]);
                if (strobe < 0 || strobe > 3) {
                    print("Strobe should be in range [0, 3].", out);
                    return false;
                }
            } catch (NumberFormatException e) {
                return false;
            }

            final int duration;
            try {
                duration = Integer.parseInt(args[4]);
                if (duration < 0) {
                    print("Duration should be an unsigned 16-bit integer.", out);
                    return false;
                }
            } catch (NumberFormatException e) {
                return false;
            }

            final CommandResult response = zigbeeApi.warn(destination, mode, strobe, duration).get();
            return defaultResponseProcessing(response, out);

        }

    }

    private class SquawkCommand implements ConsoleCommand {

        @Override
        public String getDescription() {
            return "Make the specfied IAS warning device squawk.";
        }

        @Override
        public String getSyntax() {
            return "squawk [DEVICE] [MODE] [STROBE] [LEVEL]";
        }

        @Override
        public boolean process(ZigBeeApi zigbeeApi, String[] args, PrintStream out) throws Exception {
            if (args.length != 5) {
                return false;
            }

            final ZigBeeAddress destination = getDestination(zigbeeApi, args[1], out);
            if (destination == null) {
                print("Device not found.", out);
                return false;
            }

            final int mode;
            try {
                mode = Integer.parseInt(args[2]);
                if (mode < 0 || mode > 15) {
                    print("Squawk mode should be in range [0, 15].", out);
                    return false;
                }
            } catch (NumberFormatException e) {
                return false;
            }

            final int strobe;
            try {
                strobe = Integer.parseInt(args[3]);
                if (strobe != 0 && strobe != 1) {
                    print("Strobe should be either 0 or 1.", out);
                    return false;
                }
            } catch (NumberFormatException e) {
                return false;
            }

            final int level;
            try {
                level = Integer.parseInt(args[4]);
                if (level < 0 || level > 3) {
                    print("Squawk level should be in range [0, 3].", out);
                    return false;
                }
            } catch (NumberFormatException e) {
                return false;
            }

            final CommandResult response = zigbeeApi.squawk(destination, mode, strobe, level).get();
            return defaultResponseProcessing(response, out);

        }

    }

    private class JoinCommand implements ConsoleCommand {
        /**
         * {@inheritDoc}
         */
        @Override
        public String getDescription() {
            return "Enable or disable network join.";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getSyntax() {
            return "join [enable|disable|period] [node]";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean process(final ZigBeeApi zigbeeApi, final String[] args, PrintStream out) throws Exception {
            if (args.length < 2 | args.length > 3) {
                return false;
            }

            final int join;
            if (args[1].toLowerCase().equals("enable")) {
                join = 255;
            } else if (args[1].toLowerCase().equals("disable")) {
                join = 0;
            } else {
                join = Integer.parseInt(args[1]);
            }

            if (args.length == 3) {
                // Node defined
                int nodeAddress = Integer.parseInt(args[2]);

                ZigBeeNode node = networkManager.getNode(nodeAddress);
                if (node == null) {
                    out.println("Unable to find node " + nodeAddress);
                    return false;
                }
                node.permitJoin(join);
                if (join != 0) {
                    out.println("Permit join enable node " + nodeAddress + " success.");
                } else {
                    out.println("Permit join disable " + nodeAddress + " success.");
                }
            } else {
                networkManager.permitJoin(join);
                if (join != 0) {
                    out.println("Permit join enable broadcast success.");
                } else {
                    out.println("Permit join disable broadcast success.");
                }
            }
            return true;
        }
    }

    private class LeaveCommand implements ConsoleCommand {
        /**
         * {@inheritDoc}
         */
        @Override
        public String getDescription() {
            return "Remove a node from the network";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getSyntax() {
            return "leave node [parent]";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean process(final ZigBeeApi zigbeeApi, final String[] args, PrintStream out) throws Exception {
            if (args.length > 2) {
                return false;
            }

            int leaver = Integer.parseInt(args[1]);
            ZigBeeNode leaverNode = networkManager.getNode(leaver);
            if (leaverNode == null) {
                print("Leaver node not found.", out);
                return false;
            }

            if (args.length == 2) {
                networkManager.leave(leaverNode.getNetworkAddress(), leaverNode.getIeeeAddress());

                return true;
            }

            if (args.length == 3) {
                int parent = Integer.parseInt(args[2]);

                ZigBeeNode parentNode = networkManager.getNode(parent);
                if (parentNode == null) {
                    print("Parent node not found.", out);
                    return false;
                }

                networkManager.leave(parentNode.getNetworkAddress(), leaverNode.getIeeeAddress());
                return true;
            }

            return false;
        }
    }

    private class InfoCommand implements ConsoleCommand {
        /**
         * {@inheritDoc}
         */
        @Override
        public String getDescription() {
            return "Get basic info about a device";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getSyntax() {
            return "info node [MANUFACTURER|APPVERSION|MODEL|APPVERSION|STKVERSION|HWVERSION|ZCLVERSION|DATE] [REFRESH]";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean process(final ZigBeeApi zigbeeApi, final String[] args, PrintStream out) throws Exception {
            if (args.length < 3) {
                return false;
            }

            long refresh = Long.MAX_VALUE;
            if (args.length == 4) {
                refresh = 0;
            }

            final ZigBeeEndpoint device = getDevice(zigbeeApi, args[1]);
            if (device == null) {
                print("Can't find endpoint " + args[1], out);
                return false;
            }
            final String command = args[2].toUpperCase();

            ZclBasicCluster basicCluster = (ZclBasicCluster) device.getCluster(0);
            if (basicCluster == null) {
                print("Can't find basic cluster for " + device.getEndpointAddress(), out);
                return false;
            }

            String response = null;
            switch (command) {
                case "MANUFACTURER":
                    response = basicCluster.getManufacturerName(refresh);
                    break;
                case "MODEL":
                    response = basicCluster.getModelIdentifier(refresh);
                    break;
                case "APPVERSION":
                    response = basicCluster.getApplicationVersion(refresh).toString();
                    break;
                case "STKVERSION":
                    response = basicCluster.getStackVersion(refresh).toString();
                    break;
                case "ZCLVERSION":
                    response = basicCluster.getZclVersion(refresh).toString();
                    break;
                case "HWVERSION":
                    response = basicCluster.getHwVersion(refresh).toString();
                    break;
                case "DATE":
                    response = basicCluster.getDateCode(refresh).toString();
                    break;
                default:
                    print("Unknown info type: " + command, out);
                    break;
            }

            print(command + " returned " + response, out);
            return true;
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

            dongle.shutdown();

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

/**
 * Copyright (c) 2016-2021 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.ZigBeeAddress;
import com.zsmartsystems.zigbee.ZigBeeCommand;
import com.zsmartsystems.zigbee.ZigBeeCommandListener;
import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeEndpointAddress;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNetworkNodeListener;
import com.zsmartsystems.zigbee.ZigBeeNetworkState;
import com.zsmartsystems.zigbee.ZigBeeNetworkStateListener;
import com.zsmartsystems.zigbee.ZigBeeNode;
import com.zsmartsystems.zigbee.ZigBeeStatus;
import com.zsmartsystems.zigbee.app.discovery.ZigBeeDiscoveryExtension;
import com.zsmartsystems.zigbee.app.iasclient.ZclIasZoneClient;
import com.zsmartsystems.zigbee.console.ZigBeeConsoleAttributeReadCommand;
import com.zsmartsystems.zigbee.console.ZigBeeConsoleAttributeSupportedCommand;
import com.zsmartsystems.zigbee.console.ZigBeeConsoleAttributeWriteCommand;
import com.zsmartsystems.zigbee.console.ZigBeeConsoleBindCommand;
import com.zsmartsystems.zigbee.console.ZigBeeConsoleBindingTableCommand;
import com.zsmartsystems.zigbee.console.ZigBeeConsoleCbkeCommand;
import com.zsmartsystems.zigbee.console.ZigBeeConsoleChannelCommand;
import com.zsmartsystems.zigbee.console.ZigBeeConsoleColorCommand;
import com.zsmartsystems.zigbee.console.ZigBeeConsoleCommand;
import com.zsmartsystems.zigbee.console.ZigBeeConsoleCommandsSupportedCommand;
import com.zsmartsystems.zigbee.console.ZigBeeConsoleDescribeEndpointCommand;
import com.zsmartsystems.zigbee.console.ZigBeeConsoleDescribeNodeCommand;
import com.zsmartsystems.zigbee.console.ZigBeeConsoleDeviceFingerprintCommand;
import com.zsmartsystems.zigbee.console.ZigBeeConsoleDeviceInformationCommand;
import com.zsmartsystems.zigbee.console.ZigBeeConsoleFactoryResetCommand;
import com.zsmartsystems.zigbee.console.ZigBeeConsoleFirmwareCommand;
import com.zsmartsystems.zigbee.console.ZigBeeConsoleGroupCommand;
import com.zsmartsystems.zigbee.console.ZigBeeConsoleInstallKeyCommand;
import com.zsmartsystems.zigbee.console.ZigBeeConsoleLinkKeyCommand;
import com.zsmartsystems.zigbee.console.ZigBeeConsoleNeighborsListCommand;
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
import com.zsmartsystems.zigbee.console.ZigBeeConsoleRoutingTableCommand;
import com.zsmartsystems.zigbee.console.ZigBeeConsoleSceneCommand;
import com.zsmartsystems.zigbee.console.ZigBeeConsoleSetCheckInTimeoutCommand;
import com.zsmartsystems.zigbee.console.ZigBeeConsoleSetPollIntervalCommand;
import com.zsmartsystems.zigbee.console.ZigBeeConsoleSmartEnergyCommand;
import com.zsmartsystems.zigbee.console.ZigBeeConsoleSpamReadCommand;
import com.zsmartsystems.zigbee.console.ZigBeeConsoleSwitchLevelCommand;
import com.zsmartsystems.zigbee.console.ZigBeeConsoleSwitchOffCommand;
import com.zsmartsystems.zigbee.console.ZigBeeConsoleSwitchOnCommand;
import com.zsmartsystems.zigbee.console.ZigBeeConsoleTrustCentreCommand;
import com.zsmartsystems.zigbee.console.ZigBeeConsoleUnbindCommand;
import com.zsmartsystems.zigbee.console.ZigBeeConsoleWindowCoveringCommand;
import com.zsmartsystems.zigbee.console.ember.WhiteListException;
import com.zsmartsystems.zigbee.groups.ZigBeeGroup;
import com.zsmartsystems.zigbee.transaction.ZigBeeTransactionManager;
import com.zsmartsystems.zigbee.transaction.ZigBeeTransactionQueue;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportTransmit;
import com.zsmartsystems.zigbee.zcl.ZclAttribute;
import com.zsmartsystems.zigbee.zcl.ZclCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclBasicCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclIasZoneCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclOnOffCluster;
import com.zsmartsystems.zigbee.zcl.clusters.general.ReportAttributesCommand;
import com.zsmartsystems.zigbee.zcl.clusters.iaszone.ZoneStatusChangeNotificationCommand;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zdo.ZdoStatus;
import com.zsmartsystems.zigbee.zdo.command.ManagementLqiRequest;
import com.zsmartsystems.zigbee.zdo.command.ManagementLqiResponse;

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
    private boolean listeningModeEnabled = false;

    private Integer readAfterReportCount = null;

    private long initialMemory;

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

        Runtime runtime = Runtime.getRuntime();
        // Run the garbage collector
        runtime.gc();
        // Calculate the used memory
        initialMemory = runtime.totalMemory() - runtime.freeMemory();

        createCommands(newCommands, transportCommands);

        commands.put("quit", new QuitCommand());
        commands.put("help", new HelpCommand());
        commands.put("descriptor", new SetDescriptorCommand());
        commands.put("listen", new ListenCommand());
        commands.put("unlisten", new UnlistenCommand());
        commands.put("readafterreport", new ReadAfterReportCommand());

        commands.put("lqi", new LqiCommand());
        commands.put("enroll", new EnrollCommand());

        commands.put("supportedcluster", new SupportedClusterCommand());

        commands.put("rediscover", new RediscoverCommand());

        commands.put("stress", new StressCommand());
        commands.put("memory", new MemoryCommand());
        commands.put("lqipoll", new LqiPollCommand());
        commands.put("reinitialize", new ReinitializeCommand());
        commands.put("queues", new TransactionQueuesCommand());

        newCommands.put("nodes", new ZigBeeConsoleNodeListCommand());
        newCommands.put("endpoint", new ZigBeeConsoleDescribeEndpointCommand());
        newCommands.put("node", new ZigBeeConsoleDescribeNodeCommand());
        newCommands.put("bind", new ZigBeeConsoleBindCommand());
        newCommands.put("unbind", new ZigBeeConsoleUnbindCommand());
        newCommands.put("bindtable", new ZigBeeConsoleBindingTableCommand());

        newCommands.put("read", new ZigBeeConsoleAttributeReadCommand());
        newCommands.put("spamread", new ZigBeeConsoleSpamReadCommand());
        newCommands.put("write", new ZigBeeConsoleAttributeWriteCommand());

        newCommands.put("attsupported", new ZigBeeConsoleAttributeSupportedCommand());
        newCommands.put("cmdsupported", new ZigBeeConsoleCommandsSupportedCommand());

        newCommands.put("fingerprint", new ZigBeeConsoleDeviceFingerprintCommand());
        newCommands.put("info", new ZigBeeConsoleDeviceInformationCommand());
        newCommands.put("join", new ZigBeeConsoleNetworkJoinCommand());
        newCommands.put("leave", new ZigBeeConsoleNetworkLeaveCommand());

        newCommands.put("reporting", new ZigBeeConsoleReportingConfigCommand());
        newCommands.put("subscribe", new ZigBeeConsoleReportingSubscribeCommand());
        newCommands.put("unsubscribe", new ZigBeeConsoleReportingUnsubscribeCommand());

        newCommands.put("installkey", new ZigBeeConsoleInstallKeyCommand());
        newCommands.put("linkkey", new ZigBeeConsoleLinkKeyCommand());
        newCommands.put("trustcentre", new ZigBeeConsoleTrustCentreCommand());

        newCommands.put("netstart", new ZigBeeConsoleNetworkStartCommand());
        newCommands.put("netbackup", new ZigBeeConsoleNetworkBackupCommand());
        newCommands.put("discovery", new ZigBeeConsoleNetworkDiscoveryCommand());

        newCommands.put("otaupgrade", new ZigBeeConsoleOtaUpgradeCommand());
        newCommands.put("channel", new ZigBeeConsoleChannelCommand());

        newCommands.put("smartenergy", new ZigBeeConsoleSmartEnergyCommand());
        newCommands.put("cbke", new ZigBeeConsoleCbkeCommand());
        newCommands.put("group", new ZigBeeConsoleGroupCommand());
        newCommands.put("scene", new ZigBeeConsoleSceneCommand());

        newCommands.put("on", new ZigBeeConsoleSwitchOnCommand());
        newCommands.put("off", new ZigBeeConsoleSwitchOffCommand());
        newCommands.put("level", new ZigBeeConsoleSwitchLevelCommand());
        newCommands.put("color", new ZigBeeConsoleColorCommand());
        newCommands.put("covering", new ZigBeeConsoleWindowCoveringCommand());
        newCommands.put("setpollinterval", new ZigBeeConsoleSetPollIntervalCommand());
        newCommands.put("setcheckintimeout", new ZigBeeConsoleSetCheckInTimeoutCommand());
        newCommands.put("factoryreset", new ZigBeeConsoleFactoryResetCommand());

        newCommands.put("routingtable", new ZigBeeConsoleRoutingTableCommand());
        newCommands.put("neighbors", new ZigBeeConsoleNeighborsListCommand());

        newCommands.put("firmware", new ZigBeeConsoleFirmwareCommand());

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
                if (command instanceof ReportAttributesCommand) {
                    final ZigBeeNode node = networkManager.getNode(command.getSourceAddress().getAddress());

                    if (listeningModeEnabled) {
                        final ZclCluster cluster = node.getEndpoints().stream()
                            .filter(endpoint -> endpoint.getInputCluster(command.getClusterId()) != null)
                            .findFirst()
                            .map(endpoint -> endpoint.getInputCluster(command.getClusterId())).get();
                        ReportAttributesCommand reportAttributesCommand = (ReportAttributesCommand) command;
                        reportAttributesCommand.getReports().stream().forEach(attributeReport -> {
                            ZclAttribute attribute = null;
                            if (cluster != null) {
                                attribute = cluster.getAttribute(attributeReport.getAttributeIdentifier());
                            }
                            print("[" + LocalDateTime.now().toString() + "] - Received attribute report [Device="
                                + command.getSourceAddress() +
                                ", Cluster=" + command.getClusterId() +
                                ", Attribute="
                                + (attribute != null
                                ? attribute.getName() + "(" + attributeReport.getAttributeIdentifier() + ")"
                                : attributeReport.getAttributeIdentifier())
                                + ", AttributeValue = " + attributeReport.getAttributeValue() + "]", System.out);
                        });
                    }

                    if(readAfterReportCount != null) {
                        ZclCluster basicCluster = node.getEndpoints().stream()
                            .filter(endpoint -> endpoint.getInputCluster(ZclBasicCluster.CLUSTER_ID) != null)
                            .findFirst().get()
                            .getInputCluster(ZclBasicCluster.CLUSTER_ID);
                        for (int i = 1; i <= readAfterReportCount; ++i) {
                            basicCluster.readAttribute(ZclBasicCluster.ATTR_ZCLVERSION);
                        }
                    }
                }
                if (command instanceof ZoneStatusChangeNotificationCommand) {
                    if(listeningModeEnabled) {
                        print(command.toString(), System.out);
                    }
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
            } catch (WhiteListException e) {
                out.println("Error executing command: " + e);
            } catch (Exception e) {
                out.println("Error executing command: " + e);
                e.printStackTrace(out);
            }
            return;
        }

        print("Unknown command. Use 'help' command to list available commands.", out);
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
            for (final ZigBeeGroup group : networkManager.getGroups()) {
                if (destinationIdentifier.equals(group.getLabel())) {
                    return group.getAddress();
                }
            }
            final int groupId = Integer.parseInt(destinationIdentifier);
            ZigBeeGroup group = networkManager.getGroup(groupId);
            if (group == null) {
                return null;
            }
            return group.getAddress();
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

        private class CommandEntry implements Comparable<CommandEntry> {

            private final String command;

            private final String description;

            public String getCommand() {
                return command;
            }

            public String getDescription() {
                return description;
            }

            public CommandEntry(String command, String description) {
                this.command = command;
                this.description = description;
            }

            @Override
            public int compareTo(CommandEntry o) {
                return Objects.compare(this.command, o.getCommand(), String::compareTo);
            }
        }

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
                } else if(newCommands.containsKey(args[1])) {
                    final ZigBeeConsoleCommand command = newCommands.get(args[1]);
                    print(command.getDescription(), out);
                    if(command.getSyntax() != null && !command.getSyntax().isEmpty()) {
                        print("", out);
                        print("Syntax: " + command.getSyntax(), out);
                    }
                    if(command.getHelp() != null && !command.getHelp().isEmpty()) {
                        print("", out);
                        print("Help: " + command.getHelp(), out);
                    }
                } else {
                    return false;
                }
            } else if (args.length == 1) {
                final List<CommandEntry> commandList = new ArrayList<>();
                commandList.addAll(commands.entrySet().stream()
                        .map(entry -> new CommandEntry(entry.getKey(), entry.getValue().getDescription()))
                        .collect(Collectors.toList()));
                commandList.addAll(newCommands.entrySet().stream()
                        .map(entry -> new CommandEntry(entry.getKey(), entry.getValue().getDescription()))
                        .collect(Collectors.toList()));
                Collections.sort(commandList);
                print("Commands:", out);
                for (final CommandEntry command : commandList) {
                    print(command.getCommand() + " - " + command.getDescription(), out);
                }
            } else {
                return false;
            }

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
     * Enable console listening mode.
     */
    private class ListenCommand implements ConsoleCommand {
        /**
         * {@inheritDoc}
         */
        @Override
        public String getDescription() {
            return "Enable console listening mode.";
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

            listeningModeEnabled = true;

            out.println("Listening mode enabled. Attribute reports and IAS notifications will be printed.");

            return true;
        }
    }

    /**
     * Disable console listening mode.
     */
    private class UnlistenCommand implements ConsoleCommand {
        /**
         * {@inheritDoc}
         */
        @Override
        public String getDescription() {
            return "Disable console listening mode.";
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

            listeningModeEnabled = false;

            out.println("Listening mode disabled. Attribute reports and IAS notifications will be ignored.");

            return true;
        }
    }

    private class ReadAfterReportCommand implements ConsoleCommand {
        /**
         * {@inheritDoc}
         */
        @Override
        public String getDescription() {
            return "Set read-after-report count (0 to disable).";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getSyntax() {
            return "COUNT";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean process(final ZigBeeApi zigbeeApi, final String[] args, PrintStream out) {
            if (args.length != 2) {
                return false;
            }

            int count = Integer.parseInt(args[1]);

            if(count <= 0) {
                readAfterReportCount = null;
                out.println("Read-after-report disabled.");
            } else {
                readAfterReportCount = count;
                out.println("Read-after-report enabled. The attribute (cluster 0 attribute 0) will be read " + readAfterReportCount + " times after every attribute report.");
            }

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

            final ZigBeeEndpoint endpoint = getDevice(zigbeeApi, args[1]);
            if (endpoint == null) {
                out.println("Unknown node or endpoint.");
                return false;
            }

            if (endpoint.getInputCluster(ZclIasZoneCluster.CLUSTER_ID) == null) {
                out.println("No IAS cluster found in specified endpoint.");
                return false;
            }

            if (endpoint.getApplication(ZclIasZoneCluster.CLUSTER_ID) != null) {
                out.println("IAS enrollment is already enabled.");
                return false;
            }

            if(endpoint.addApplication(new ZclIasZoneClient(networkManager, networkManager.getLocalIeeeAddress(), 0)) != ZigBeeStatus.SUCCESS) {
                out.println("Fail to enable IAS enrollment.");
                return false;
            }

            out.println("IAS enrollment successfully enabled.");
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

            networkManager.addSupportedClientCluster(clusterId);

            print("Added cluster " + String.format("0x%X", clusterId) + " to match descriptor list.", out);

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
            ZigBeeDiscoveryExtension networkDiscoverer = (ZigBeeDiscoveryExtension) networkManager.getExtension(ZigBeeDiscoveryExtension.class);
            if (networkDiscoverer == null) {
                return false;
            }
            networkDiscoverer.rediscoverNode(address);
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
     * Print the amount of memory in use
     */
    private class MemoryCommand implements ConsoleCommand {
        /**
         * {@inheritDoc}
         */
        @Override
        public String getDescription() {
            return "Show memory used";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getSyntax() {
            return "";
        }

        private static final long MEGABYTE = 1024L * 1024L;

        public long bytesToMegabytes(long bytes) {
            return bytes / MEGABYTE;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean process(final ZigBeeApi zigbeeApi, final String[] args, final PrintStream out) throws Exception {
            // Get the Java runtime
            Runtime runtime = Runtime.getRuntime();
            // Run the garbage collector
            runtime.gc();
            // Calculate the used memory
            long memory = runtime.totalMemory() - runtime.freeMemory();
            System.out.println("Total memory        : " + runtime.totalMemory());
            System.out.println("Free memory         : " + runtime.freeMemory());
            System.out.println("Used memory at start: " + initialMemory);
            System.out.println("Used memory in bytes: " + memory);

            return true;
        }
    }

    private class LqiPollCommand implements ConsoleCommand {
        /**
         * {@inheritDoc}
         */
        @Override
        public String getDescription() {
            return "Poll LQI values";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getSyntax() {
            return "lqipoll NODEID";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean process(final ZigBeeApi zigbeeApi, final String[] args, final PrintStream out) throws Exception {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int cnts = 1000;
                    int errors = 0;
                    int cnt = 0;
                    List<Integer> lqi = new ArrayList<>();
                    while (cnt < cnts) {
                        print("LQI Poll CNT: " + cnt++, out);
                        final ManagementLqiRequest neighborRequest = new ManagementLqiRequest(0);
                        neighborRequest.setDestinationAddress(new ZigBeeEndpointAddress(0));

                        CommandResult response;
                        try {
                            response = networkManager.sendTransaction(neighborRequest, neighborRequest).get();
                            final ManagementLqiResponse neighborResponse = response.getResponse();

                            if (neighborResponse == null || neighborResponse.getStatus() != ZdoStatus.SUCCESS) {
                                errors++;
                                continue;
                            }
                            if (neighborResponse.getNeighborTableList().isEmpty()) {
                                print("No neighbors", out);
                                continue;
                            }
                            lqi.add(neighborResponse.getNeighborTableList().get(0).getLqi());
                        } catch (InterruptedException | ExecutionException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    IntSummaryStatistics stats = lqi.stream().mapToInt((x) -> x).summaryStatistics();

                    print("LQI Polling Complete", out);
                    print("Errors: " + errors, out);
                    print("Min   : " + stats.getMin(), out);
                    print("Max   : " + stats.getMax(), out);
                    print("Avg   : " + stats.getAverage(), out);

                }
            }).start();

            return true;
        }
    }

    private class ReinitializeCommand implements ConsoleCommand {
        /**
         * {@inheritDoc}
         */
        @Override
        public String getDescription() {
            return "Put system back into initialise state";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getSyntax() {
            return "";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean process(final ZigBeeApi zigbeeApi, final String[] args, final PrintStream out) throws Exception {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    networkManager.reinitialize();
                }
            }).start();

            return true;
        }
    }

    private class TransactionQueuesCommand implements ConsoleCommand {
        /**
         * {@inheritDoc}
         */
        @Override
        public String getDescription() {
            return "Gets information about transaction queues";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getSyntax() {
            return "";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean process(final ZigBeeApi zigbeeApi, final String[] args, final PrintStream out) throws Exception {
            ZigBeeTransactionManager transactionManager = (ZigBeeTransactionManager) getField(
                    ZigBeeNetworkManager.class, networkManager,
                    "transactionManager");

            final Set<ZigBeeNode> nodes = networkManager.getNodes();
            final List<Integer> nodeIds = new ArrayList<>();

            for (ZigBeeNode node : nodes) {
                nodeIds.add(node.getNetworkAddress());
            }

            Collections.sort(nodeIds);
            for (Integer nodeId : nodeIds) {
                ZigBeeNode node = networkManager.getNode(nodeId);
                ZigBeeTransactionQueue queue = transactionManager.getQueue(node.getIeeeAddress());

                if (queue != null) {
                    out.println(queue.toString());
                }
            }

            new Thread(new Runnable() {
                @Override
                public void run() {
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
            case UNSIGNED_24_BIT_INTEGER:
                value = Integer.parseInt(stringValue);
                break;
            case UNSIGNED_32_BIT_INTEGER:
                value = Integer.parseInt(stringValue);
                break;
            default:
                break;
        }
        return value;
    }

    /**
     * Gets the value of the private field
     *
     * @param clazz the class where the field resides
     * @param object the object where the field resides
     * @param fieldName the field name
     * @return the {@link Object} containing the field value
     * @throws Exception
     */
    private Object getField(Class clazz, Object object, String fieldName) throws Exception {
        Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
        return field.get(object);
    }
}

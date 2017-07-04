package com.zsmartsystems.zigbee.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.Future;

import org.apache.commons.lang.StringUtils;

import com.zsmartsystems.zigbee.Command;
import com.zsmartsystems.zigbee.CommandListener;
import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.ZigBeeAddress;
import com.zsmartsystems.zigbee.ZigBeeApi;
import com.zsmartsystems.zigbee.ZigBeeDevice;
import com.zsmartsystems.zigbee.ZigBeeGroupAddress;
import com.zsmartsystems.zigbee.ZigBeeNetworkDeviceListener;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNetworkNodeListener;
import com.zsmartsystems.zigbee.ZigBeeNetworkStateListener;
import com.zsmartsystems.zigbee.ZigBeeNode;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportState;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportTransmit;
import com.zsmartsystems.zigbee.zcl.ZclAttribute;
import com.zsmartsystems.zigbee.zcl.ZclCluster;
import com.zsmartsystems.zigbee.zcl.ZclStatus;
import com.zsmartsystems.zigbee.zcl.clusters.ZclBasicCluster;
import com.zsmartsystems.zigbee.zcl.clusters.general.ConfigureReportingResponse;
import com.zsmartsystems.zigbee.zcl.clusters.general.ReadAttributesResponse;
import com.zsmartsystems.zigbee.zcl.clusters.general.ReportAttributesCommand;
import com.zsmartsystems.zigbee.zcl.clusters.general.WriteAttributesResponse;
import com.zsmartsystems.zigbee.zcl.clusters.groups.GetGroupMembershipResponse;
import com.zsmartsystems.zigbee.zcl.clusters.groups.ViewGroupResponse;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zdo.descriptors.NeighborTable;
import com.zsmartsystems.zigbee.zdo.descriptors.RoutingTable;

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

    /**
     * Constructor which configures ZigBee API and constructs commands.
     *
     * @param dongle the dongle
     */
    public ZigBeeConsole(final ZigBeeNetworkManager networkManager, final ZigBeeTransportTransmit dongle) {

        commands.put("node", new NodeCommand());
        commands.put("info", new InfoCommand());

        commands.put("devicelist", new DeviceListCommand());
        commands.put("devicelabel", new DeviceLabelCommand());
        commands.put("deviceremove", new DeviceRemoveCommand());

        commands.put("groupadd", new GroupAddCommand());
        commands.put("groupremove", new GroupRemoveCommand());
        commands.put("grouplist", new GroupListCommand());

        commands.put("membershipadd", new MembershipAddCommand());
        commands.put("membershipremove", new MembershipRemoveCommand());
        commands.put("membershipview", new MembershipViewCommand());
        commands.put("membershiplist", new MembershipListCommand());

        commands.put("quit", new QuitCommand());
        commands.put("help", new HelpCommand());
        commands.put("desc", new DescribeCommand());
        commands.put("descriptor", new SetDescriptorCommand());
        commands.put("bind", new BindCommand());
        commands.put("unbind", new UnbindCommand());
        commands.put("on", new OnCommand());
        commands.put("off", new OffCommand());
        commands.put("color", new ColorCommand());
        commands.put("leave", new LeaveCommand());
        commands.put("level", new LevelCommand());
        commands.put("listen", new ListenCommand());
        commands.put("unlisten", new UnlistenCommand());
        commands.put("subscribe", new SubscribeCommand());
        commands.put("unsubscribe", new UnsubscribeCommand());

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

        this.networkManager = networkManager;
        zigBeeApi = new ZigBeeApi(networkManager);

        zigBeeApi.getNetwork().addNetworkStateListener(new ZigBeeNetworkStateListener() {
            @Override
            public void networkStateUpdated(ZigBeeTransportState state) {
                print("ZigBee network state updated to " + state.toString(), System.out);
            }
        });

        zigBeeApi.getNetwork().addNetworkDeviceListener(new ZigBeeNetworkDeviceListener() {
            @Override
            public void deviceAdded(ZigBeeDevice device) {
                print("Device added:\n" + getDeviceSummary(device), System.out);
            }

            @Override
            public void deviceUpdated(ZigBeeDevice device) {
                // print("Device updated:\n" + getDeviceSummary(device), System.out);
            }

            @Override
            public void deviceRemoved(ZigBeeDevice device) {
                print("Device removed\n" + getDeviceSummary(device), System.out);
            }
        });

        zigBeeApi.getNetwork().addNetworkNodeListener(new ZigBeeNetworkNodeListener() {
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

        zigBeeApi.getNetwork().addCommandListener(new CommandListener() {
            @Override
            public void commandReceived(Command command) {
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
        final String[] args = inputLine.split(" ");
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
    private String getDeviceSummary(final ZigBeeDevice device) {
        return StringUtils.leftPad(Integer.toString(device.getNetworkAddress()), 10) + "/"
                + StringUtils.rightPad(Integer.toString(device.getEndpoint()), 3) + " " + device.getIeeeAddress() + "  "
                + StringUtils.rightPad(device.getLabel() != null ? device.getLabel() : "<label>", 20); // + " "
        // + ZigBeeApiConstants.getDeviceName(device.getProfileId(), device.getDeviceId());
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
        final ZigBeeDevice device = getDevice(zigbeeApi, destinationIdentifier);

        if (device != null) {
            return device.getDeviceAddress();
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
    private ZigBeeDevice getDevice(ZigBeeApi zigbeeApi, final String deviceIdentifier) {
        for (final ZigBeeDevice zigBeeDevice : zigbeeApi.getNetwork().getDevices()) {
            if (deviceIdentifier.equals(zigBeeDevice.getNetworkAddress() + "/" + zigBeeDevice.getEndpoint())) {
                return zigBeeDevice;
            }
            if (deviceIdentifier.equals(zigBeeDevice.getLabel())) {
                return zigBeeDevice;
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
            final List<ZigBeeDevice> devices = zigbeeApi.getDevices();
            for (final ZigBeeDevice device : devices) {
                print(getDeviceSummary(device), out);
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

            final ZigBeeDevice device = getDevice(zigbeeApi, args[1]);

            if (device == null) {
                return false;
            }

            print("IEEE Address     : " + device.getIeeeAddress(), out);
            print("Network Address  : " + device.getNetworkAddress(), out);
            print("Endpoint         : " + device.getEndpoint(), out);
            // print("Device Profile : " + ZigBeeApiConstants.getProfileName(device.getProfileId())
            // + String.format(" (0x%04X)", device.getProfileId()), out);
            // print("Device Category : "
            // + ZigBeeApiConstants.getCategoryDeviceName(device.getProfileId(), device.getDeviceId())
            // + String.format(" (0x%04X)", device.getDeviceId()), out);
            // print("Device Type : " + ZigBeeApiConstants.getDeviceName(device.getProfileId(), device.getDeviceId()),
            // out);
            print("Device Version   : " + device.getDeviceVersion(), out);
            print("Input Clusters   : ", out);
            printClusters(device, device.getInputClusterIds(), out);
            print("Output Clusters  : ", out);
            printClusters(device, device.getOutputClusterIds(), out);

            return true;
        }

        /**
         * Prints out clusters.
         *
         * @param device the device
         * @param list the cluster IDs
         * @param out the output print stream
         */
        private void printClusters(final ZigBeeDevice device, final List<Integer> list, PrintStream out) {
            for (int clusterId : list) {
                ZclCluster cluster = device.getCluster(clusterId);
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
            print("Devices:", out);
            for (ZigBeeDevice device : zigbeeApi.getNetwork().getNodeDevices(address)) {
                print(device.toString(), out);
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
            final ZigBeeDevice source = getDevice(zigbeeApi, args[1]);
            if (source == null) {
                return false;
            }
            final ZigBeeDevice destination = getDevice(zigbeeApi, args[2]);
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
            final ZigBeeDevice source = getDevice(zigbeeApi, args[1]);
            if (source == null) {
                return false;
            }
            final ZigBeeDevice destination = getDevice(zigbeeApi, args[2]);
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
     * Sets device label.
     */
    private class DeviceLabelCommand implements ConsoleCommand {
        /**
         * {@inheritDoc}
         */
        @Override
        public String getDescription() {
            return "Sets device label.";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getSyntax() {
            return "devicelabel DEVICEID DEVICELABEL";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean process(final ZigBeeApi zigbeeApi, final String[] args, PrintStream out) throws Exception {
            if (args.length != 3) {
                return false;
            }

            final ZigBeeDevice device = getDevice(zigbeeApi, args[1]);
            if (device == null) {
                return false;
            }

            final String label = args[2];
            device.setLabel(label);
            zigbeeApi.setDeviceLabel(device.getNetworkAddress(), device.getEndpoint(), label);
            return true;
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
     * Removes device from network state but does not affect actual ZigBeet network.
     * Device will be eventually rediscovered if it is still in the network.
     */
    private class DeviceRemoveCommand implements ConsoleCommand {
        /**
         * {@inheritDoc}
         */
        @Override
        public String getDescription() {
            return "Removes device from gateway network state.";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getSyntax() {
            return "deviceremove DEVICE";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean process(final ZigBeeApi zigbeeApi, final String[] args, PrintStream out) throws Exception {
            if (args.length != 2) {
                return false;
            }

            final ZigBeeDevice device = getDevice(zigbeeApi, args[1]);
            if (device == null) {
                return false;
            }

            zigbeeApi.removeDevice(device.getDeviceAddress());
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

            final ZigBeeDevice device = getDevice(zigbeeApi, args[1]);
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
            return "level DEVICEID LEVEL";
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

            float level;
            try {
                level = Float.parseFloat(args[2]);
            } catch (final NumberFormatException e) {
                return false;
            }

            final CommandResult response = zigbeeApi.level(destination, level, 1.0).get();
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

            final ZigBeeDevice device = getDevice(zigbeeApi, args[1]);
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
                final int statusCode = response.getRecords().get(0).getStatus();
                if (statusCode == 0) {
                    out.println("Attribute value configure reporting success.");
                } else {
                    final ZclStatus status = ZclStatus.getStatus((byte) statusCode);
                    out.println("Attribute value configure reporting error: " + status);
                }
                return true;
            } else {
                out.println("Error executing command: " + result.getMessage());
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

            final ZigBeeDevice device = getDevice(zigbeeApi, args[1]);
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
                final int statusCode = response.getRecords().get(0).getStatus();
                if (statusCode == 0) {
                    out.println("Attribute value configure reporting success.");
                } else {
                    final ZclStatus status = ZclStatus.getStatus((byte) statusCode);
                    out.println("Attribute value configure reporting error: " + status);
                }
                return true;
            } else {
                out.println("Error executing command: " + result.getMessage());
                return true;
            }

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

            final ZigBeeDevice device = getDevice(zigbeeApi, args[1]);
            if (device == null) {
                print("Device not found.", out);
                return false;
            }

            final CommandResult result = zigbeeApi.read(device.getDeviceAddress(), clusterId, attributeId).get();

            if (result.isSuccess()) {
                final ReadAttributesResponse response = result.getResponse();

                final int statusCode = response.getRecords().get(0).getStatus();
                if (statusCode == 0) {
                    out.println("Cluster " + response.getClusterId() + ", Attribute "
                            + response.getRecords().get(0).getAttributeIdentifier() + ", type "
                            + response.getRecords().get(0).getAttributeDataType() + ", value: "
                            + response.getRecords().get(0).getAttributeValue());
                } else {
                    final ZclStatus status = ZclStatus.getStatus((byte) statusCode);
                    out.println("Attribute value read error: " + status);
                }

                return true;
            } else {
                out.println("Error executing command: " + result.getMessage());
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

            final ZigBeeDevice device = getDevice(zigbeeApi, args[1]);
            if (device == null) {
                print("Device not found.", out);
                return false;
            }

            final ZclCluster cluster = device.getCluster(clusterId);
            final ZclAttribute attribute = cluster.getAttribute(attributeId);

            final Object value = parseValue(args[4], attribute.getDataType());

            final CommandResult result = zigbeeApi.write(device.getDeviceAddress(), clusterId, attributeId, value)
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
                out.println("Error executing command: " + result.getMessage());
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

            final ZigBeeDevice device = getDevice(zigbeeApi, args[1]);
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

                final int statusCode = response.getRecords().get(0).getStatus();
                if (statusCode == 0) {
                    out.println("Cluster " + response.getClusterId() + ", Attribute "
                            + response.getRecords().get(0).getAttributeIdentifier() + ", type "
                            + response.getRecords().get(0).getAttributeDataType() + ", value: "
                            + response.getRecords().get(0).getAttributeValue());
                } else {
                    final ZclStatus status = ZclStatus.getStatus((byte) statusCode);
                    out.println("Attribute value read error: " + status);
                }

                return true;
            } else {
                out.println("Error executing command: " + result.getMessage());
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
            return "info node [MANUFACTURER|APPVERSION|MODEL|APPVERSION|STKVERSION|HWVERSION|ZCLVERSION|DATE]";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean process(final ZigBeeApi zigbeeApi, final String[] args, PrintStream out) throws Exception {
            if (args.length != 3) {
                return false;
            }

            final ZigBeeDevice device = getDevice(zigbeeApi, args[1]);
            final String command = args[2].toUpperCase();

            ZclBasicCluster basicCluster = (ZclBasicCluster) device.getCluster(0);
            if (basicCluster == null) {
                print("Can't find basic cluster for " + device.getDeviceAddress(), out);
                return false;
            }

            String response = null;
            switch (command) {
                case "MANUFACTURER":
                    response = basicCluster.getManufacturerName(Long.MAX_VALUE);
                    break;
                case "MODEL":
                    response = basicCluster.getModelIdentifier(Long.MAX_VALUE);
                    break;
                case "APPVERSION":
                    response = basicCluster.getApplicationVersion(Long.MAX_VALUE).toString();
                    break;
                case "STKVERSION":
                    response = basicCluster.getStackVersion(Long.MAX_VALUE).toString();
                    break;
                case "ZCLVERSION":
                    response = basicCluster.getZclVersion(Long.MAX_VALUE).toString();
                    break;
                case "HWVERSION":
                    response = basicCluster.getHwVersion(Long.MAX_VALUE).toString();
                    break;
                case "DATE":
                    response = basicCluster.getDateCode(Long.MAX_VALUE).toString();
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

            final ZigBeeDevice device = getDevice(zigbeeApi, args[1]);
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

            final ZigBeeDevice device = getDevice(zigbeeApi, args[1]);
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

            final ZigBeeDevice device = getDevice(zigbeeApi, args[1]);
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

            final ZigBeeDevice device = getDevice(zigbeeApi, args[1]);
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
                out.println("Error executing command: " + result.getMessage());
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

            final ZigBeeDevice device = getDevice(zigbeeApi, args[1]);
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
                out.println("Error executing command: " + result.getMessage());
                return true;
            }
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
            out.println("Error executing command: " + result.getMessage());
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

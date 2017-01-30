package com.zsmartsystems.zigbee;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.serialization.ZigBeeDeserializer;
import com.zsmartsystems.zigbee.serialization.ZigBeeSerializer;
import com.zsmartsystems.zigbee.util.ZigBeeConstants;
import com.zsmartsystems.zigbee.zcl.ZclAttribute;
import com.zsmartsystems.zigbee.zcl.ZclCluster;
import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclCustomResponseMatcher;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFrameType;
import com.zsmartsystems.zigbee.zcl.ZclHeader;
import com.zsmartsystems.zigbee.zcl.ZclResponseMatcher;
import com.zsmartsystems.zigbee.zcl.clusters.general.ReadAttributesCommand;
import com.zsmartsystems.zigbee.zcl.clusters.general.WriteAttributesCommand;
import com.zsmartsystems.zigbee.zcl.field.AttributeIdentifier;
import com.zsmartsystems.zigbee.zcl.field.WriteAttributeRecord;
import com.zsmartsystems.zigbee.zcl.protocol.ZclClusterType;
import com.zsmartsystems.zigbee.zdo.ZdoCommand;
import com.zsmartsystems.zigbee.zdo.ZdoResponseMatcher;
import com.zsmartsystems.zigbee.zdo.command.BindRequest;
import com.zsmartsystems.zigbee.zdo.command.ManagementPermitJoinRequest;
import com.zsmartsystems.zigbee.zdo.command.UnbindRequest;

/**
 * Implements functions for managing the ZigBee interfaces.
 * <p>
 * The ZigBee lifecycle is as follows -:
 * <ul>
 * <li>Instantiate a {@link ZigBeeTransportTransmit} class
 * <li>Instantiate a {@link ZigBeeNetworkManager} class passing the previously created {@link ZigBeeTransportTransmit}
 * class
 * <li>Optionally set the {@link ZigBeeSerializer} and {@link ZigBeeDeserializer} using the {@link #setSerializer}
 * method
 * <li>Call the {@link #initialize} method to perform the initial initialization of the ZigBee network
 * <li>Set the network configuration (see below).
 * <li>Call the {@link #startup} method to start using the configured ZigBee network. Configuration methods may not be
 * used.
 * <li>Call the {@link shutdown} method to close the network
 * </ul>
 * <p>
 * Following a call to {@link #initialize} configuration calls can be made to configure the transport layer. This
 * includes -:
 * <ul>
 * <li>{@link #getZigBeeChannel}
 * <li>{@link #setZigBeeChannel}
 * <li>{@link #getZigBeePanId}
 * <li>{@link #setZigBeePanId}
 * <li>{@link #getZigBeeExtendedPanId}
 * <li>{@link #setZigBeeExtendedPanId}
 * </ul>
 * <p>
 * Once all transport initialization is complete, {@link #startup} must be called.
 *
 * @author Chris Jackson
 */
public class ZigBeeNetworkManager implements ZigBeeNetwork, ZigBeeTransportReceive {
    /**
     * The logger.
     */
    private final Logger logger = LoggerFactory.getLogger(ZigBeeNetworkManager.class);

    /**
     * The nodes in the ZigBee network - maps network address to
     * {@link ZigBeeNode}
     */
    private Map<Integer, ZigBeeNode> networkNodes = new TreeMap<Integer, ZigBeeNode>();

    /**
     * Map of devices in the ZigBee network - maps address to
     * {@link ZigBeeDevice}
     */
    private Map<ZigBeeDeviceAddress, ZigBeeDevice> networkDevices = new TreeMap<ZigBeeDeviceAddress, ZigBeeDevice>();

    /**
     * The groups in the ZigBee network.
     */
    private Map<Integer, ZigBeeGroupAddress> networkGroups = new TreeMap<Integer, ZigBeeGroupAddress>();

    /**
     * The node listeners of the ZigBee network. Registered listeners will be
     * notified of additions, deletions and changes to {@link ZigBeeNode}s.
     */
    private List<ZigBeeNetworkNodeListener> nodeListeners = Collections
            .unmodifiableList(new ArrayList<ZigBeeNetworkNodeListener>());

    /**
     * The device listeners of the ZigBee network. Registered listeners will be
     * notified of additions, deletions and changes to {@link ZigBeeDevice}s.
     */
    private List<ZigBeeNetworkDeviceListener> deviceListeners = Collections
            .unmodifiableList(new ArrayList<ZigBeeNetworkDeviceListener>());

    /**
     * {@link AtomicLong} used to generate transaction sequence numbers
     */
    private final static AtomicInteger sequenceNumber = new AtomicInteger();

    /**
     * The network state serializer
     */
    private ZigBeeNetworkStateSerializer networkStateSerializer;

    /**
     * The {@link ZigBeeTransportTransmit} implementation. This provides the interface
     * for sending data to the network which is an implementation of a ZigBee
     * interface (eg a Dongle).
     */
    private final ZigBeeTransportTransmit transport;

    /**
     * The ZigBee network {@link ZigBeeNetworkDiscoverer}. The discover is
     * responsible for monitoring the network for new devices and the initial
     * interrogation of their capabilities.
     */
    private final ZigBeeNetworkDiscoverer networkDiscoverer;

    /**
     * The command listener creation times. Used to timeout queued commands.
     */
    private final Set<CommandExecution> commandExecutions = new HashSet<CommandExecution>();

    /**
     * The command listeners.
     */
    private List<CommandListener> commandListeners = new ArrayList<CommandListener>();

    /**
     * The listeners of the ZigBee network.
     */
    private List<ZigBeeNetworkStateListener> stateListeners = Collections
            .unmodifiableList(new ArrayList<ZigBeeNetworkStateListener>());

    private Class<ZigBeeSerializer> serializerClass;
    private Class<ZigBeeDeserializer> deserializerClass;

    /**
     * Constructor which configures serial port and ZigBee network.
     *
     * @param transport
     *            the dongle
     * @param resetNetwork
     *            whether network is to be reset
     */
    public ZigBeeNetworkManager(final ZigBeeTransportTransmit transport) {
        this.transport = transport;
        this.networkDiscoverer = new ZigBeeNetworkDiscoverer(this);

        transport.setZigBeeTransportReceive(this);
    }

    /**
     * Set a state {@link ZigBeeNetworkStateSerializer}. This will allow saving and restoring the network.
     * The network manager will call {@link ZigBeeNetworkStateSerializer#deserialize} during the startup and
     * {@link ZigBeeNetworkStateSerializer#serialize} during shutdown.
     *
     * @param networkStateSerializer the {@link ZigBeeNetworkStateSerializer}
     */
    public void setNetworkStateSerializer(ZigBeeNetworkStateSerializer networkStateSerializer) {
        this.networkStateSerializer = networkStateSerializer;
    }

    /**
     * Set the serializer class to be used to convert commands and fields into data to be sent to the dongle.
     * The system instantiates a new serializer for each command.
     *
     * @param serializer the {@link ZigBeeSerializer} class
     * @param deserializer the {@link ZigBeeDeerializer} class
     */
    public void setSerializer(Class<?> serializer, Class<?> deserializer) {
        this.serializerClass = (Class<ZigBeeSerializer>) serializer;
        this.deserializerClass = (Class<ZigBeeDeserializer>) deserializer;
    }

    /**
     * Initializes ZigBee manager components and initializes the transport layer.
     * <p>
     * Following a call to {@link #initialize} configuration calls can be made to configure the transport layer. This
     * includes -:
     * <ul>
     * <li>{@link #getZigBeeChannel}
     * <li>{@link #setZigBeeChannel}
     * <li>{@link #getZigBeePanId}
     * <li>{@link #setZigBeePanId}
     * <li>{@link #getZigBeeExtendedPanId}
     * <li>{@link #setZigBeeExtendedPanId}
     * </ul>
     * <p>
     * Once all transport initialization is complete, {@link #startup} must be called.
     *
     * @return true if startup was successful.
     */
    public boolean initialize() {
        return transport.initialize();
    }

    /**
     * Gets the current ZigBee RF channel.
     *
     * @return the current channel or -1 on error
     * @return
     */
    public int getZigBeeChannel() {
        return transport.getZigBeeChannel();
    }

    /**
     * Sets the ZigBee RF channel.
     * <p>
     * Note that this method may only be called following the {@link #initialize} call, and before the {@link #startup}
     * call.
     *
     * @param channel {@link int} defining the channel to use
     * @return true if the channel was set
     */
    public boolean setZigBeeChannel(int channel) {
        return transport.setZigBeeChannel(channel);
    }

    /**
     * Gets the ZigBee PAN ID currently in use by the transport
     *
     * @return the PAN ID
     */
    public int getZigBeePanId() {
        return transport.getZigBeePanId();
    }

    /**
     * Sets the ZigBee PAN ID to the specified value
     * <p>
     * Note that this method may only be called following the {@link #initialize} call, and before the {@link #startup}
     * call.
     *
     * @param panId the new PAN ID
     * @return true if the PAN Id was set correctly
     */
    public boolean setZigBeePanId(int panId) {
        return transport.setZigBeePanId(panId);
    }

    /**
     * Gets the ZigBee Extended PAN ID currently in use by the transport
     *
     * @return the PAN ID
     */
    public long getZigBeeExtendedPanId() {
        return transport.getZigBeeExtendedPanId();
    }

    /**
     * Sets the ZigBee Extended PAN ID to the specified value
     * <p>
     * Note that this method may only be called following the {@link #initialize} call, and before the {@link #startup}
     * call.
     *
     * @param panId the new PAN ID
     * @return true if the PAN Id was set correctly
     */
    public boolean setZigBeeExtendedPanId(long panId) {
        return transport.setZigBeeExtendedPanId(panId);
    }

    /**
     * Starts up ZigBee manager components.
     *
     * @return true if startup was successful.
     */
    public boolean startup() {
        if (networkStateSerializer != null) {
            networkStateSerializer.deserialize(this);
        }

        if (transport.startup()) {
            networkDiscoverer.startup();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Shuts down ZigBee manager components.
     */
    public void shutdown() {
        networkDiscoverer.shutdown();
        transport.shutdown();

        if (networkStateSerializer != null) {
            networkStateSerializer.serialize(this);
        }
    }

    @Override
    public int sendCommand(Command command) throws ZigBeeException {
        if (command instanceof ZclCommand) {
            // For ZCL commands we pass the NWK and APS headers as classes to the transport layer.
            // The ZCL packet is serialised here.
            ZclCommand zclCommand = (ZclCommand) command;

            int sequence = sequenceNumber.getAndIncrement() & 0xff;
            zclCommand.setTransactionId(sequence);

            // Create the network and application sublayer headers
            ZigBeeNwkHeader nwkHeader = new ZigBeeNwkHeader();
            ZigBeeApsHeader apsHeader = new ZigBeeApsHeader();

            // TODO: Set the source address correctly?
            nwkHeader.setSourceAddress(0);
            if (zclCommand.getDestinationAddress() instanceof ZigBeeDeviceAddress) {
                nwkHeader
                        .setDestinationAddress(((ZigBeeDeviceAddress) zclCommand.getDestinationAddress()).getAddress());
                apsHeader.setDestinationEndpoint(
                        ((ZigBeeDeviceAddress) zclCommand.getDestinationAddress()).getEndpoint());
            } else {
                // TODO: Handle multicast
            }
            nwkHeader.setSequence(sequence);
            // nwkHeader.setRadius(radius);

            // Source endpoint is (currently) set by the dongle since it registers the clusters into an endpoint
            // apsHeader.setSourceEndpoint(sourceEndpoint);
            apsHeader.setCluster(zclCommand.getClusterId());

            // TODO set the profile
            apsHeader.setProfile(0x104);

            // Create the cluster library header
            ZclHeader zclHeader = new ZclHeader();
            zclHeader.setFrameType(zclCommand.isGenericCommand() ? ZclFrameType.ENTIRE_PROFILE_COMMAND
                    : ZclFrameType.CLUSTER_SPECIFIC_COMMAND);
            zclHeader.setCommandId(zclCommand.getCommandId());
            zclHeader.setDirectionServer(zclCommand.getCommandDirection());
            zclHeader.setSequenceNumber(sequence);
            // zclHeader.setDisableDefaultResponse(true);

            if (zclCommand.getDestinationAddress() instanceof ZigBeeDeviceAddress) {
                ZigBeeDeviceAddress deviceAddress = (ZigBeeDeviceAddress) zclCommand.getDestinationAddress();
                nwkHeader.setDestinationAddress(deviceAddress.getAddress());

                apsHeader.setDestinationEndpoint(deviceAddress.getEndpoint());
            }

            int[] payload = null;

            Constructor<? extends ZigBeeSerializer> constructor;
            try {
                constructor = serializerClass.getConstructor();
                ZigBeeSerializer serializer = constructor.newInstance();

                final ZclFieldSerializer fieldSerializer = new ZclFieldSerializer(serializer);
                zclCommand.serialize(fieldSerializer);

                // Serialise the ZCL header and add the payload
                payload = zclHeader.serialize(fieldSerializer, fieldSerializer.getPayload());
            } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
                    | IllegalArgumentException | InvocationTargetException e) {
                e.printStackTrace();
            }

            if (payload == null) {
                // TODO handle error
            }

            transport.sendZclCommand(nwkHeader, apsHeader, payload);

            return sequence;
        }

        if (command instanceof ZdoCommand) {
            // For ZDO commands we pass down the command class since some dongles implement ZDO commands as individual
            // commands rather than a general "sendZDO" type command. This gives the transport implementation maximum
            // flexibility for translating the command as it requires.
            transport.sendZdoCommand((ZdoCommand) command);
        }

        return 0;
    }

    @Override
    public void addCommandListener(CommandListener commandListener) {
        final List<CommandListener> modifiedCommandListeners = new ArrayList<CommandListener>(commandListeners);
        modifiedCommandListeners.add(commandListener);
        commandListeners = Collections.unmodifiableList(modifiedCommandListeners);
    }

    @Override
    public void removeCommandListener(CommandListener commandListener) {
        final List<CommandListener> modifiedCommandListeners = new ArrayList<CommandListener>(commandListeners);
        modifiedCommandListeners.remove(commandListener);
        commandListeners = Collections.unmodifiableList(modifiedCommandListeners);
    }

    @Override
    public void receiveZclCommand(final ZigBeeNwkHeader nwkHeader, final ZigBeeApsHeader apsHeader,
            final int[] payload) {

        // Create an address from the sourceAddress and endpoint
        ZigBeeDeviceAddress srcAddress = new ZigBeeDeviceAddress(nwkHeader.getSourceAddress(),
                apsHeader.getSourceEndpoint());
        ZigBeeDeviceAddress dstAddress = new ZigBeeDeviceAddress(nwkHeader.getDestinationAddress(),
                apsHeader.getDestinationEndpoint());

        // Get the device
        ZigBeeDevice device = getDevice(srcAddress);

        // Create the deserialiser
        Constructor<? extends ZigBeeDeserializer> constructor;
        ZigBeeDeserializer deserializer;
        try {
            constructor = deserializerClass.getConstructor(int[].class);
            deserializer = constructor.newInstance(new Object[] { payload });
        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
                | IllegalArgumentException | InvocationTargetException e) {
            logger.debug(e.getMessage());
            return;
        }
        ZclFieldDeserializer fieldDeserializer = new ZclFieldDeserializer(deserializer);

        // Process the ZCL header
        ZclHeader zclHeader = new ZclHeader(fieldDeserializer);

        ZclCluster cluster = null;
        if (zclHeader.getFrameType() == ZclFrameType.ENTIRE_PROFILE_COMMAND) {
            // cluster = device.getCluster(ZclClusterType.GENERAL.getId());

            Constructor<? extends ZclCluster> clusterConstructor;
            try {
                clusterConstructor = ZclClusterType.GENERAL.getClusterClass().getConstructor(ZigBeeNetworkManager.class,
                        ZigBeeDeviceAddress.class);
                cluster = clusterConstructor.newInstance(this, device.getDeviceAddress());
            } catch (Exception e) {
                // logger.debug("{}: Error instantiating cluster {}", networkAddress, ZclClusterType.GENERAL);
            }

        } else {
            // Get the cluster
            cluster = device.getCluster(apsHeader.getCluster());
        }
        if (cluster == null) {
            logger.debug("No cluster found for {}, cluster {}, command {}", zclHeader.getFrameType(),
                    apsHeader.getCluster(), zclHeader.getCommandId());
            return;
        }

        // Get the command
        ZclCommand command = null;
        if (zclHeader.isDirectionServer()) {
            command = cluster.getCommandFromId(zclHeader.getCommandId());
        } else {
            command = cluster.getResponseFromId(zclHeader.getCommandId());
        }
        if (command == null) {
            logger.debug("No command found for {}, cluster {}, command {}", zclHeader.getFrameType(),
                    apsHeader.getCluster(), zclHeader.getCommandId());
            return;
        }

        command.deserialize(fieldDeserializer);
        command.setDestinationAddress(dstAddress);
        command.setClusterId(apsHeader.getCluster());
        command.setSourceAddress(srcAddress);
        command.setTransactionId(zclHeader.getSequenceNumber());

        // Notify the listeners
        for (final CommandListener commandListener : commandListeners) {
            commandListener.commandReceived(command);
        }
    }

    @Override
    public void receiveZdoCommand(ZdoCommand command) {
        // Notify the listeners
        for (final CommandListener commandListener : commandListeners) {
            commandListener.commandReceived(command);
        }
    }

    @Override
    public void addNetworkStateListener(ZigBeeNetworkStateListener stateListener) {
        final List<ZigBeeNetworkStateListener> modifiedStateListeners = new ArrayList<ZigBeeNetworkStateListener>(
                stateListeners);
        modifiedStateListeners.add(stateListener);
        stateListeners = Collections.unmodifiableList(modifiedStateListeners);
    }

    @Override
    public void setNetworkState(TransportState state) {
        for (final ZigBeeNetworkStateListener stateListener : stateListeners) {
            stateListener.networkStateUpdated(state);
        }
    }

    /**
     * Sets device label.
     *
     * @param networkAddress
     *            the network address
     * @param endPointId
     *            the end point ID
     * @param label
     *            the label
     */
    public void setDeviceLabel(final int networkAddress, final int endPointId, final String label) {
        final ZigBeeDevice device = getDevice(new ZigBeeDeviceAddress(networkAddress, endPointId));
        device.setLabel(label);
        updateDevice(device);
    }

    /**
     * Removes device(s) by network address.
     *
     * @param networkAddress
     *            the network address
     */
    public void removeDevice(final int networkAddress) {
        final List<ZigBeeDevice> devices = getDevices();
        final List<ZigBeeDevice> devicesToRemove = new ArrayList<ZigBeeDevice>();
        for (final ZigBeeDevice device : devices) {
            if (device.getNetworkAddress() == networkAddress) {
                devicesToRemove.add(device);
            }
        }

        for (final ZigBeeDevice device : devicesToRemove) {
            removeDevice(device.getDeviceAddress());
        }
    }

    /**
     * Gets a set of ZigBee devices associated with a node
     *
     * @param networkAddress
     *            {@link Integer} defining the address of the node
     * @return a {@link Set} of {@link ZigBeeDevice}
     */
    public Set<ZigBeeDevice> getNodeDevices(Integer networkAddress) {
        Set<ZigBeeDevice> devices = new HashSet<ZigBeeDevice>();
        for (ZigBeeDevice device : getDevices()) {
            if (device.getDeviceAddress().getAddress() == networkAddress) {
                devices.add(device);
            }
        }

        return devices;
    }

    /**
     * Gets a set of ZigBee devices associated with a node
     *
     * @param ieeeAddress
     *            {@link IeeeAddress} defining the address of the node
     * @return a {@link Set} of {@link ZigBeeDevice}
     */
    public Set<ZigBeeDevice> getNodeDevices(IeeeAddress ieeeAddress) {
        Set<ZigBeeDevice> devices = new HashSet<ZigBeeDevice>();
        for (ZigBeeDevice device : getDevices()) {
            if (device.getIeeeAddress().equals(ieeeAddress)) {
                devices.add(device);
            }
        }
        return devices;
    }

    /**
     * Sets group label.
     *
     * @param groupId
     *            the group ID
     * @param label
     *            the label
     */
    public void addMembership(final int groupId, final String label) {
        if (getGroup(groupId) == null) {
            addGroup(new ZigBeeGroupAddress(groupId, label));
        } else {
            final ZigBeeGroupAddress group = getGroup(groupId);
            group.setLabel(label);
            updateGroup(group);
        }
    }

    /**
     * Removes group label.
     *
     * @param groupId
     *            the group ID
     */
    public void removeMembership(final int groupId) {
        removeGroup(groupId);
    }

    /**
     * Sends command to {@link ZigBeeAddress}.
     *
     * @param destination
     *            the destination
     * @param command
     *            the command
     * @return the command result future
     */
    public Future<CommandResult> send(ZigBeeAddress destination, ZclCommand command) {
        command.setDestinationAddress(destination);
        if (destination.isGroup()) {
            return broadcast(command);
        } else {
            return unicast(command);
        }
    }

    /**
     * Sends command.
     *
     * @param command
     *            the command
     * @return the command result future.
     */
    public Future<CommandResult> unicast(final Command command) {

        final CommandResponseMatcher responseMatcher;
        if (command instanceof ZclCommand) {
            responseMatcher = new ZclResponseMatcher();
        } else {
            responseMatcher = new ZdoResponseMatcher();
        }

        return unicast(command, responseMatcher);
    }

    /**
     * Sends ZCL command.
     *
     * @param command
     *            the command
     * @param responseMatcher
     *            the response matcher.
     * @return the command result future.
     */
    public Future<CommandResult> unicast(final Command command, final CommandResponseMatcher responseMatcher) {
        synchronized (command) {
            final CommandResultFuture future = new CommandResultFuture(this);
            final CommandExecution commandExecution = new CommandExecution(System.currentTimeMillis(), command, future);
            future.setCommandExecution(commandExecution);
            final CommandListener commandListener = new CommandListener() {
                @Override
                public void commandReceived(Command receivedCommand) {
                    // Ensure that received command is not processed before
                    // command is sent and hence transaction ID for the command
                    // set.
                    synchronized (command) {
                        if (responseMatcher.isMatch(command, receivedCommand)) {
                            synchronized (future) {
                                future.set(new CommandResult(receivedCommand));
                                synchronized (future) {
                                    future.notify();
                                }
                                removeCommandExecution(commandExecution);
                            }
                        }
                    }
                }
            };
            commandExecution.setCommandListener(commandListener);
            addCommandExecution(commandExecution);
            try {
                int transactionId = sendCommand(command);
                if (command instanceof ZclCommand) {
                    ((ZclCommand) command).setTransactionId((byte) transactionId);
                }
            } catch (final ZigBeeException e) {
                future.set(new CommandResult(e.toString()));
                removeCommandExecution(commandExecution);
            }
            return future;
        }
    }

    /**
     * Broadcasts command i.e. does not wait for response.
     *
     * @param command
     *            the command
     * @return the command result future.
     */
    private Future<CommandResult> broadcast(final Command command) {
        synchronized (command) {
            final CommandResultFuture future = new CommandResultFuture(this);

            try {
                sendCommand(command);
                future.set(new CommandResult(new BroadcastResponse()));
            } catch (final ZigBeeException e) {
                future.set(new CommandResult(e.toString()));
            }

            return future;
        }
    }

    /**
     * Adds command listener and removes expired command listeners.
     *
     * @param commandExecution
     *            the command execution
     */
    private void addCommandExecution(final CommandExecution commandExecution) {
        synchronized (commandExecutions) {
            final List<CommandExecution> expiredCommandExecutions = new ArrayList<CommandExecution>();
            for (final CommandExecution existingCommandExecution : commandExecutions) {
                if (System.currentTimeMillis() - existingCommandExecution.getStartTime() > 8000) {
                    expiredCommandExecutions.add(existingCommandExecution);
                }
            }
            for (final CommandExecution expiredCommandExecution : expiredCommandExecutions) {
                ((CommandResultFuture) expiredCommandExecution.getFuture()).set(new CommandResult());
                removeCommandExecution(expiredCommandExecution);
            }
            commandExecutions.add(commandExecution);
            addCommandListener(commandExecution.getCommandListener());
        }
    }

    /**
     * Removes command execution.
     *
     * @param expiredCommandExecution
     *            the command execution
     */
    protected void removeCommandExecution(CommandExecution expiredCommandExecution) {
        commandExecutions.remove(expiredCommandExecution);
        removeCommandListener(expiredCommandExecution.getCommandListener());
        synchronized (expiredCommandExecution.getFuture()) {
            expiredCommandExecution.getFuture().notify();
        }
    }

    public void permitJoin(final boolean enable) {

        final ManagementPermitJoinRequest command = new ManagementPermitJoinRequest();

        if (enable) {
            command.setDuration(0xFF);
        } else {
            command.setDuration(0);
        }

        command.setAddressingMode(ZigBeeConstants.BROADCAST_ADDRESS);
        command.setDestinationAddress(ZigBeeConstants.ZCZR_BROADCAST);
        command.setTrustCenterSignificance(1);

        try {
            sendCommand(command);
        } catch (final ZigBeeException e) {
            throw new ZigBeeApiException("Error sending permit join command.", e);
        }
    }

    /**
     * Binds two devices.
     *
     * @param source
     *            the source device
     * @param destination
     *            the destination device
     * @param clusterId
     *            the cluster ID
     * @return TRUE if no errors occurred in sending.
     */
    public Future<CommandResult> bind(final ZigBeeDevice source, final ZigBeeDevice destination, final int clusterId) {
        final int destinationAddress = source.getNetworkAddress();
        final IeeeAddress bindSourceAddress = source.getIeeeAddress();
        final int bindSourceEndpoint = source.getEndpoint();
        final int bindCluster = clusterId;
        final int bindDestinationAddressingMode = 3; // 64 bit addressing
        final IeeeAddress bindDestinationAddress = destination.getIeeeAddress();
        final int bindDestinationEndpoint = destination.getEndpoint();
        final BindRequest command = new BindRequest(destinationAddress, bindSourceAddress.getLong(), bindSourceEndpoint,
                bindCluster, bindDestinationAddressingMode, bindDestinationAddress.getLong(), bindDestinationEndpoint);
        return unicast(command);
    }

    /**
     * Unbinds two devices.
     *
     * @param source the source {@link ZigBeeDevice}
     * @param destination the destination {@link ZigBeeDevice}
     * @param clusterId the cluster ID
     * @return true if no errors occurred in sending.
     */
    public Future<CommandResult> unbind(final ZigBeeDevice source, final ZigBeeDevice destination,
            final int clusterId) {
        final int destinationAddress = source.getNetworkAddress();
        final IeeeAddress bindSourceAddress = source.getIeeeAddress();
        final int bindSourceEndpoint = source.getEndpoint();
        final int bindCluster = clusterId;
        final int bindDestinationAddressingMode = 3; // 64 bit addressing
        final IeeeAddress bindDestinationAddress = destination.getIeeeAddress();
        final int bindDestinationEndpoint = destination.getEndpoint();
        final UnbindRequest command = new UnbindRequest(destinationAddress, bindSourceAddress.getLong(),
                bindSourceEndpoint, bindCluster, bindDestinationAddressingMode, bindDestinationAddress.getLong(),
                bindDestinationEndpoint);
        return unicast(command);
    }

    /**
     * Writes attribute to device.
     *
     * @param cluster the {@link ZclCluster}
     * @param attributeId the {@link ZclAttribute} to write to
     * @param value the value to set (as {@link Object})
     * @return the command result future
     */
    public Future<CommandResult> write(final ZclCluster cluster, final ZclAttribute attribute, final Object value) {
        final WriteAttributesCommand command = new WriteAttributesCommand();

        command.setClusterId(cluster.getClusterId());
        final WriteAttributeRecord attributeIdentifier = new WriteAttributeRecord();
        attributeIdentifier.setAttributeIdentifier(attribute.getId());
        attributeIdentifier.setAttributeDataType(attribute.getDataType());
        attributeIdentifier.setAttributeValue(value);
        command.setRecords(Collections.singletonList(attributeIdentifier));
        command.setDestinationAddress(cluster.getZigBeeAddress());

        return unicast(command, new ZclCustomResponseMatcher());
    }

    /**
     * Reads an attribute from device.
     *
     * @param zigbeeAddress the device {@link ZigBeeDeviceAddress}
     * @param clusterId the cluster ID
     * @param attributeId the attribute ID
     * @return the command result future
     */
    public Future<CommandResult> read(final ZclCluster cluster, final ZclAttribute attribute) {
        final ReadAttributesCommand command = new ReadAttributesCommand();

        command.setClusterId(cluster.getClusterId());
        final AttributeIdentifier attributeIdentifier = new AttributeIdentifier();
        attributeIdentifier.setAttributeIdentifier(attribute.getId());
        command.setIdentifiers(Collections.singletonList(attributeIdentifier));
        command.setDestinationAddress(cluster.getZigBeeAddress());

        return unicast(command, new ZclCustomResponseMatcher());
    }

    /**
     * Configures attribute reporting.
     *
     * @param zigbeeAddress the {@link ZigBeeDeviceAddress}
     * @param clusterId the cluster ID
     * @param attributeId the attribute ID
     * @param minInterval the minimum interval
     * @param maxInterval the maximum interval
     * @param reportableChange the reportable change
     * @return the command result future
     */
    public Future<CommandResult> report(final ZigBeeDeviceAddress zigbeeAddress, final int clusterId,
            final int attributeId, final int minInterval, final int maxInterval, final Object reportableChange) {

        return null;
        // TODO: Fix the report command
        // final ConfigureReportingCommand command = new ConfigureReportingCommand();
        // command.setClusterId(clusterId);

        // final AttributeReportingConfigurationRecord record = new AttributeReportingConfigurationRecord();
        // record.setDirection(0);
        // record.setAttributeIdentifier(attributeId);
        // record.setAttributeDataType(ZclAttributeType.get(clusterId, attributeId).getZigBeeType().getId());
        // record.setMinimumReportingInterval(minInterval);
        // record.setMinimumReportingInterval(maxInterval);
        // record.setReportableChange(reportableChange);
        // record.setTimeoutPeriod(0);
        // command.setRecords(Collections.singletonList(record));

        // command.setDestinationAddress(zigbeeAddress);

        // return unicast(command, new ZclCustomResponseMatcher());
    }

    public void addGroup(final ZigBeeGroupAddress group) {
        synchronized (networkGroups) {
            networkGroups.put(group.getGroupId(), group);
        }
    }

    public void updateGroup(ZigBeeGroupAddress group) {
        synchronized (networkGroups) {
            networkGroups.put(group.getGroupId(), group);
        }
    }

    public ZigBeeGroupAddress getGroup(final int groupId) {
        synchronized (networkGroups) {
            return networkGroups.get(groupId);
        }
    }

    public void removeGroup(final int groupId) {
        synchronized (networkGroups) {
            networkGroups.remove(groupId);
        }
    }

    public List<ZigBeeGroupAddress> getGroups() {
        synchronized (networkGroups) {
            return new ArrayList<ZigBeeGroupAddress>(networkGroups.values());
        }
    }

    public void addDevice(final ZigBeeDevice device) {
        synchronized (networkDevices) {
            networkDevices.put(device.getDeviceAddress(), device);
        }
        synchronized (this) {
            for (final ZigBeeNetworkDeviceListener listener : deviceListeners) {
                listener.deviceAdded(device);
            }
        }
    }

    public void updateDevice(ZigBeeDevice device) {
        synchronized (networkDevices) {
            networkDevices.put(device.getDeviceAddress(), device);
        }
        synchronized (this) {
            for (final ZigBeeNetworkDeviceListener listener : deviceListeners) {
                listener.deviceUpdated(device);
            }
        }
    }

    public ZigBeeDevice getDevice(final ZigBeeAddress networkAddress) {
        if (networkAddress.isGroup()) {
            return null;
        }

        synchronized (networkDevices) {
            return networkDevices.get(networkAddress);
        }
    }

    public void removeDevice(final ZigBeeAddress networkAddress) {
        final ZigBeeDevice device;
        synchronized (networkDevices) {
            device = networkDevices.remove(networkAddress);
        }
        synchronized (this) {
            if (device != null) {
                for (final ZigBeeNetworkDeviceListener listener : deviceListeners) {
                    listener.deviceRemoved(device);
                }
            }
        }
    }

    public List<ZigBeeDevice> getDevices() {
        synchronized (networkDevices) {
            return new ArrayList<ZigBeeDevice>(networkDevices.values());
        }
    }

    public void addNetworkNodeListener(final ZigBeeNetworkNodeListener networkNodeListener) {
        synchronized (this) {
            final List<ZigBeeNetworkNodeListener> modifiedListeners = new ArrayList<ZigBeeNetworkNodeListener>(
                    nodeListeners);
            modifiedListeners.add(networkNodeListener);
            nodeListeners = Collections.unmodifiableList(modifiedListeners);
        }
    }

    public void removeNetworkNodeListener(final ZigBeeNetworkNodeListener networkNodeListener) {
        synchronized (this) {
            final List<ZigBeeNetworkNodeListener> modifiedListeners = new ArrayList<ZigBeeNetworkNodeListener>(
                    nodeListeners);
            modifiedListeners.remove(networkNodeListener);
            nodeListeners = Collections.unmodifiableList(modifiedListeners);
        }
    }

    public void addNetworkDeviceListener(final ZigBeeNetworkDeviceListener networkDeviceListener) {
        synchronized (this) {
            final List<ZigBeeNetworkDeviceListener> modifiedListeners = new ArrayList<ZigBeeNetworkDeviceListener>(
                    deviceListeners);
            modifiedListeners.add(networkDeviceListener);
            deviceListeners = Collections.unmodifiableList(modifiedListeners);
        }
    }

    public void removeNetworkDeviceListener(final ZigBeeNetworkDeviceListener networkDeviceListener) {
        synchronized (this) {
            final List<ZigBeeNetworkDeviceListener> modifiedListeners = new ArrayList<ZigBeeNetworkDeviceListener>(
                    deviceListeners);
            modifiedListeners.remove(networkDeviceListener);
            deviceListeners = Collections.unmodifiableList(modifiedListeners);
        }
    }

    public ZigBeeNode getNode(Integer networkAddress) {
        return networkNodes.get(networkAddress);
    }

    public void removeNode(Integer networkAddress) {
        final ZigBeeNode node;
        synchronized (networkNodes) {
            node = networkNodes.remove(networkAddress);
        }
        synchronized (this) {
            if (node != null) {
                for (final ZigBeeNetworkNodeListener listener : nodeListeners) {
                    listener.nodeRemoved(node);
                }
            }
        }
    }

    public void addNode(final ZigBeeNode node) {
        synchronized (networkNodes) {
            networkNodes.put(node.getNetworkAddress(), node);
        }
        synchronized (this) {
            for (final ZigBeeNetworkNodeListener listener : nodeListeners) {
                listener.nodeAdded(node);
            }
        }
    }

    public void updateNode(ZigBeeNode node) {
        synchronized (this) {
            if (node != null) {
                for (final ZigBeeNetworkNodeListener listener : nodeListeners) {
                    listener.nodeUpdated(node);
                }
            }
        }
    }
}

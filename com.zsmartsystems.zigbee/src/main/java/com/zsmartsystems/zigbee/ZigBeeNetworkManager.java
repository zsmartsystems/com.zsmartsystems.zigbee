/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
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
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.internal.ClusterMatcher;
import com.zsmartsystems.zigbee.internal.NotificationService;
import com.zsmartsystems.zigbee.internal.ZigBeeNetworkDiscoverer;
import com.zsmartsystems.zigbee.serialization.ZigBeeDeserializer;
import com.zsmartsystems.zigbee.serialization.ZigBeeSerializer;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportReceive;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportState;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportTransmit;
import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFrameType;
import com.zsmartsystems.zigbee.zcl.ZclHeader;
import com.zsmartsystems.zigbee.zcl.ZclTransactionMatcher;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandType;
import com.zsmartsystems.zigbee.zdo.ZdoCommand;
import com.zsmartsystems.zigbee.zdo.ZdoCommandType;
import com.zsmartsystems.zigbee.zdo.command.ManagementLeaveRequest;
import com.zsmartsystems.zigbee.zdo.command.ManagementPermitJoiningRequest;
import com.zsmartsystems.zigbee.zdo.command.MatchDescriptorRequest;
import com.zsmartsystems.zigbee.zdo.command.NetworkAddressRequest;

/**
 * Implements functions for managing the ZigBee interfaces.
 * <p>
 * The ZigBeeNetworkManager lifecycle is as follows -:
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
     * The nodes in the ZigBee network - maps {@link IeeeAddress} to {@link ZigBeeNode}
     */
    private final Map<IeeeAddress, ZigBeeNode> networkNodes = new ConcurrentHashMap<IeeeAddress, ZigBeeNode>();

    /**
     * The groups in the ZigBee network.
     */
    private final Map<Integer, ZigBeeGroupAddress> networkGroups = new TreeMap<Integer, ZigBeeGroupAddress>();

    /**
     * The node listeners of the ZigBee network. Registered listeners will be
     * notified of additions, deletions and changes to {@link ZigBeeNode}s.
     */
    private List<ZigBeeNetworkNodeListener> nodeListeners = Collections
            .unmodifiableList(new ArrayList<ZigBeeNetworkNodeListener>());

    /**
     * The announce listeners are notified whenever a new device is discovered.
     * This can be called from the transport layer, or internally by methods watching
     * the network state.
     */
    private List<ZigBeeAnnounceListener> announceListeners = Collections
            .unmodifiableList(new ArrayList<ZigBeeAnnounceListener>());

    /**
     * {@link AtomicInteger} used to generate transaction sequence numbers
     */
    private final static AtomicInteger sequenceNumber = new AtomicInteger();

    /**
     * {@link AtomicInteger} used to generate APS header counters
     */
    private final static AtomicInteger apsCounter = new AtomicInteger();

    /**
     * The network state serializer
     */
    private ZigBeeNetworkStateSerializer networkStateSerializer;

    /**
     * Executor service to execute update threads for discovery or mesh updates etc.
     * We use a {@link Executors.newFixedThreadPool} to provide a fixed number of threads as otherwise this could result
     * in a large number of simultaneous threads in large networks.
     */
    private final ExecutorService executorService = Executors.newFixedThreadPool(6);

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
     * The {@link ZigBeeCommandNotifier}. This is used for sending notifications asynchronously to listeners.
     */
    private final ZigBeeCommandNotifier commandNotifier = new ZigBeeCommandNotifier();

    /**
     * The listeners of the ZigBee network.
     */
    private List<ZigBeeNetworkStateListener> stateListeners = Collections
            .unmodifiableList(new ArrayList<ZigBeeNetworkStateListener>());

    /**
     * The serializer class used to serialize commands to data packets
     */
    private Class<ZigBeeSerializer> serializerClass;

    /**
     * The deserializer class used to deserialize commands from data packets
     */
    private Class<ZigBeeDeserializer> deserializerClass;

    /**
     * A ClusterMatcher used to respond to the {@link MatchDescriptorRequest} command.
     */
    private ClusterMatcher clusterMatcher = null;

    public enum ZigBeeInitializeResponse {
        /**
         * Device is initialized successfully and is currently joined to a network
         */
        JOINED,
        /**
         * Device initialization failed
         */
        FAILED,
        /**
         * Device is initialized successfully and is currently not joined to a network
         */
        NOT_JOINED
    }

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
    @SuppressWarnings("unchecked")
    public void setSerializer(Class<?> serializer, Class<?> deserializer) {
        this.serializerClass = (Class<ZigBeeSerializer>) serializer;
        this.deserializerClass = (Class<ZigBeeDeserializer>) deserializer;
    }

    /**
     * Initializes ZigBee manager components and initializes the transport layer.
     * <p>
     * If a network state was previously serialized, it will be deserialized here if the serializer is set with the
     * {@link #setNetworkStateSerializer} method.
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
     * @return {@link ZigBeeInitializeResponse}
     */
    public ZigBeeInitializeResponse initialize() {
        ZigBeeInitializeResponse transportResponse = transport.initialize();

        IeeeAddress address = transport.getIeeeAddress();
        if (address != null) {
            ZigBeeNode node = getNode(address);
            if (node == null) {
                node = new ZigBeeNode(this, address);
                node.setNetworkAddress(0);

                addNode(node);
            }
        }

        if (networkStateSerializer != null) {
            networkStateSerializer.deserialize(this);
        }

        networkDiscoverer.startup();

        return transportResponse;
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
     * Sets the ZigBee RF channel. The allowable channel range is 11 to 26.
     * <p>
     * Note that this method may only be called following the {@link #initialize} call, and before the {@link #startup}
     * call.
     *
     * @param channel {@link int} defining the channel to use
     * @return true if the channel was set
     */
    public boolean setZigBeeChannel(int channel) {
        if (channel < 11 || channel > 26) {
            logger.debug("Can't set channel to {}", channel);
            return false;
        }
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
     * Sets the ZigBee PAN ID to the specified value. The range of the PAN ID is 0 to 0x3FFF.
     * Additionally a value of 0xFFFF is allowed to indicate the user doesn't care and a random value
     * can be set by the transport.
     * <p>
     * Note that this method may only be called following the {@link #initialize} call, and before the {@link #startup}
     * call.
     *
     * @param panId the new PAN ID
     * @return true if the PAN Id was set correctly
     */
    public boolean setZigBeePanId(int panId) {
        if (panId < 0 || panId > 0xfffe) {
            return false;
        }
        return transport.setZigBeePanId(panId);
    }

    /**
     * Gets the ZigBee Extended PAN ID currently in use by the transport
     *
     * @return the PAN ID
     */
    public ExtendedPanId getZigBeeExtendedPanId() {
        return transport.getZigBeeExtendedPanId();
    }

    /**
     * Sets the ZigBee Extended PAN ID to the specified value
     * <p>
     * Note that this method may only be called following the {@link #initialize} call, and before the {@link #startup}
     * call.
     *
     * @param panId the new {@link ExtendedPanId}
     * @return true if the PAN Id was set correctly
     */
    public boolean setZigBeeExtendedPanId(ExtendedPanId panId) {
        return transport.setZigBeeExtendedPanId(panId);
    }

    /**
     * Set the current network key in use by the system.
     * <p>
     * Note that this method may only be called following the {@link #initialize} call, and before the {@link #startup}
     * call.
     *
     * @param key the new network key as {@link ZigBeeKey}
     * @return true if the key was set
     */
    public boolean setZigBeeNetworkKey(final ZigBeeKey key) {
        return transport.setZigBeeNetworkKey(key);
    }

    /**
     * Set the current link key in use by the system.
     * <p>
     * Note that this method may only be called following the {@link #initialize} call, and before the {@link #startup}
     * call.
     *
     * @param key the new link key as {@link ZigBeeKey}
     * @return true if the key was set
     */
    public boolean setZigBeeLinkKey(final ZigBeeKey key) {
        return transport.setTcLinkKey(key);
    }

    /**
     * Starts up ZigBee manager components.
     * <p>
     *
     * @param reinitialize true if the provider is to reinitialise the network with the parameters configured since the
     *            {@link #initialize} method was called.
     * @return true if startup was successful.
     */
    public boolean startup(boolean reinitialize) {
        if (!transport.startup(reinitialize)) {
            return false;
        }

        return true;
    }

    /**
     * Shuts down ZigBee manager components.
     */
    public void shutdown() {
        executorService.shutdownNow();

        if (networkStateSerializer != null) {
            networkStateSerializer.serialize(this);
        }

        networkDiscoverer.shutdown();
        transport.shutdown();
    }

    /**
     * Schedules a runnable task for execution. This uses a fixed size scheduler to limit thread execution.
     *
     * @param runnableTask
     */
    public void executeTask(Runnable runnableTask) {
        executorService.execute(runnableTask);
    }

    /**
     * Get the transport layer version string
     *
     * @return {@link String} containing the transport layer version
     */
    public String getTransportVersionString() {
        return transport.getVersionString();
    }

    @Override
    public int sendCommand(ZigBeeCommand command) {
        // Create the application frame
        ZigBeeApsFrame apsFrame = new ZigBeeApsFrame();

        int sequence = sequenceNumber.getAndIncrement() & 0xff;
        command.setTransactionId(sequence);

        // Set the source address - should probably be improved!
        // Note that the endpoint is set (currently!) in the transport layer
        // TODO: Use only a single endpoint for HA and fix this here
        command.setSourceAddress(new ZigBeeEndpointAddress(0));

        logger.debug("TX CMD: {}", command);

        apsFrame.setCluster(command.getClusterId());
        apsFrame.setApsCounter(apsCounter.getAndIncrement() & 0xff);

        // TODO: Set the source address correctly?
        apsFrame.setSourceAddress(0);

        apsFrame.setSequence(sequence);
        apsFrame.setRadius(31);

        if (command.getDestinationAddress() instanceof ZigBeeEndpointAddress) {
            apsFrame.setAddressMode(ZigBeeNwkAddressMode.DEVICE);
            apsFrame.setDestinationAddress(((ZigBeeEndpointAddress) command.getDestinationAddress()).getAddress());
            apsFrame.setDestinationEndpoint(((ZigBeeEndpointAddress) command.getDestinationAddress()).getEndpoint());
        } else {
            apsFrame.setAddressMode(ZigBeeNwkAddressMode.GROUP);
            // TODO: Handle multicast
        }

        final ZclFieldSerializer fieldSerializer;
        try {
            Constructor<? extends ZigBeeSerializer> constructor;
            constructor = serializerClass.getConstructor();
            ZigBeeSerializer serializer = constructor.newInstance();
            fieldSerializer = new ZclFieldSerializer(serializer);
        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
                | IllegalArgumentException | InvocationTargetException e) {
            logger.debug("Error serializing ZigBee frame {}", e);
            return 0;
        }

        if (command instanceof ZdoCommand) {
            // Source endpoint is (currently) set by the dongle since it registers the clusters into an endpoint
            // apsHeader.setSourceEndpoint(sourceEndpoint);

            apsFrame.setProfile(0);
            apsFrame.setSourceEndpoint(0);
            apsFrame.setDestinationEndpoint(0);
            command.serialize(fieldSerializer);

            // Serialise the ZCL header and add the payload
            apsFrame.setPayload(fieldSerializer.getPayload());
        }

        if (command instanceof ZclCommand) {
            // For ZCL commands we pass the NWK and APS headers as classes to the transport layer.
            // The ZCL packet is serialised here.
            ZclCommand zclCommand = (ZclCommand) command;

            apsFrame.setSourceEndpoint(1);

            // TODO set the profile properly
            apsFrame.setProfile(0x104);

            // Create the cluster library header
            ZclHeader zclHeader = new ZclHeader();
            zclHeader.setFrameType(zclCommand.isGenericCommand() ? ZclFrameType.ENTIRE_PROFILE_COMMAND
                    : ZclFrameType.CLUSTER_SPECIFIC_COMMAND);
            zclHeader.setCommandId(zclCommand.getCommandId());
            zclHeader.setSequenceNumber(sequence);
            zclHeader.setDirection(zclCommand.getCommandDirection());

            command.serialize(fieldSerializer);

            // Serialise the ZCL header and add the payload
            apsFrame.setPayload(zclHeader.serialize(fieldSerializer, fieldSerializer.getPayload()));

            logger.debug("TX ZCL: {}", zclHeader);
        }
        logger.debug("TX APS: {}", apsFrame);

        transport.sendCommand(apsFrame);

        return sequence;
    }

    @Override
    public void addCommandListener(ZigBeeCommandListener commandListener) {
        commandNotifier.addCommandListener(commandListener);
    }

    @Override
    public void removeCommandListener(ZigBeeCommandListener commandListener) {
        commandNotifier.removeCommandListener(commandListener);
    }

    @Override
    public void receiveCommand(final ZigBeeApsFrame apsFrame) {
        logger.debug("RX APS: {}", apsFrame);

        // Create the deserialiser
        Constructor<? extends ZigBeeDeserializer> constructor;
        ZigBeeDeserializer deserializer;
        try {
            constructor = deserializerClass.getConstructor(int[].class);
            deserializer = constructor.newInstance(new Object[] { apsFrame.getPayload() });
        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
                | IllegalArgumentException | InvocationTargetException e) {
            logger.debug("Error creating deserializer", e);
            return;
        }
        ZclFieldDeserializer fieldDeserializer = new ZclFieldDeserializer(deserializer);

        ZigBeeCommand command = null;
        switch (apsFrame.getProfile()) { // TODO: Use ZigBeeProfileType
            case 0x0000:
                command = receiveZdoCommand(fieldDeserializer, apsFrame);
                break;
            case 0x0104:
            case 0xC05E:
                command = receiveZclCommand(fieldDeserializer, apsFrame);
                break;
            default:
                logger.debug("Received message with unknown profile {}", String.format("%04X", apsFrame.getProfile()));
                break;
        }

        if (command == null) {
            logger.debug("Incoming message did not translate to command.");
            return;
        }

        // Create an address from the sourceAddress and endpoint
        command.setSourceAddress(new ZigBeeEndpointAddress(apsFrame.getSourceAddress(), apsFrame.getSourceEndpoint()));
        command.setDestinationAddress(
                new ZigBeeEndpointAddress(apsFrame.getDestinationAddress(), apsFrame.getDestinationEndpoint()));

        logger.debug("RX CMD: {}", command);

        // Notify the listeners
        commandNotifier.notifyCommandListeners(command);
    }

    private ZigBeeCommand receiveZdoCommand(final ZclFieldDeserializer fieldDeserializer,
            final ZigBeeApsFrame apsFrame) {
        ZdoCommandType commandType = ZdoCommandType.getValueById(apsFrame.getCluster());
        if (commandType == null) {
            return null;
        }

        ZigBeeCommand command;
        try {
            Class<? extends ZdoCommand> commandClass = commandType.getCommandClass();
            Constructor<? extends ZdoCommand> constructor;
            constructor = commandClass.getConstructor();
            command = constructor.newInstance();
        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
                | IllegalArgumentException | InvocationTargetException e) {
            logger.debug("Error instantiating ZDO command", e);
            return null;
        }

        command.deserialize(fieldDeserializer);

        return command;
    }

    private ZigBeeCommand receiveZclCommand(final ZclFieldDeserializer fieldDeserializer,
            final ZigBeeApsFrame apsFrame) {
        // Process the ZCL header
        ZclHeader zclHeader = new ZclHeader(fieldDeserializer);
        logger.debug("RX ZCL: {}", zclHeader);

        // Get the command type
        ZclCommandType commandType = null;
        if (zclHeader.getFrameType() == ZclFrameType.ENTIRE_PROFILE_COMMAND) {
            commandType = ZclCommandType.getGeneric(zclHeader.getCommandId());
        } else {
            commandType = ZclCommandType.getCommandType(apsFrame.getCluster(), zclHeader.getCommandId(),
                    zclHeader.getDirection());
        }

        if (commandType == null) {
            logger.debug("No command type found for {}, cluster={}, command={}, direction={}", zclHeader.getFrameType(),
                    apsFrame.getCluster(), zclHeader.getCommandId(), zclHeader.getDirection());
            return null;
        }

        ZclCommand command = commandType.instantiateCommand();
        if (command == null) {
            logger.debug("No command found for {}, cluster={}, command={}", zclHeader.getFrameType(),
                    apsFrame.getCluster(), zclHeader.getCommandId());
            return null;
        }

        command.setCommandDirection(zclHeader.getDirection());
        command.deserialize(fieldDeserializer);
        command.setClusterId(apsFrame.getCluster());
        command.setTransactionId(zclHeader.getSequenceNumber());

        return command;
    }

    /**
     * Add a {@link ZigBeeAnnounceListener} that will be notified whenever a new device is detected
     * on the network.
     *
     * @param statusListener the new {@link ZigBeeAnnounceListener} to add
     */
    public void addAnnounceListener(ZigBeeAnnounceListener statusListener) {
        final List<ZigBeeAnnounceListener> modifiedStateListeners = new ArrayList<ZigBeeAnnounceListener>(
                announceListeners);
        modifiedStateListeners.add(statusListener);
        announceListeners = Collections.unmodifiableList(modifiedStateListeners);
    }

    /**
     * Remove a {@link ZigBeeAnnounceListener}
     *
     * @param statusListener the new {@link ZigBeeAnnounceListener} to remove
     */
    public void removeAnnounceListener(ZigBeeAnnounceListener statusListener) {
        final List<ZigBeeAnnounceListener> modifiedStateListeners = new ArrayList<ZigBeeAnnounceListener>(
                announceListeners);
        modifiedStateListeners.remove(statusListener);
        announceListeners = Collections.unmodifiableList(modifiedStateListeners);
    }

    @Override
    public void nodeStatusUpdate(final ZigBeeNodeStatus deviceStatus, final Integer networkAddress,
            final IeeeAddress ieeeAddress) {
        // This method should only be called when the transport layer has authoritative information about
        // a devices status. Therefore, we should update the network manager view of a device as appropriate.
        switch (deviceStatus) {
            // Device has gone - lets remove it
            case DEVICE_LEFT:
                // Find the node
                ZigBeeNode node = getNode(networkAddress);
                if (node == null) {
                    logger.debug("{}: Node has left, but wasn't found in the network.", networkAddress);
                } else {
                    // Remove the node from the network
                    removeNode(node);
                }
                break;

            // Leave the join/rejoin notifications for the discovery handler
            case UNSECURED_JOIN:
                break;
            case SECURED_REJOIN:
            case UNSECURED_REJOIN:
                break;
            default:
                break;
        }

        // Notify the listeners
        synchronized (this) {
            for (final ZigBeeAnnounceListener announceListener : announceListeners) {
                NotificationService.execute(new Runnable() {
                    @Override
                    public void run() {
                        announceListener.deviceStatusUpdate(deviceStatus, networkAddress, ieeeAddress);
                    }
                });
            }
        }
    }

    /**
     * Adds a {@link ZigBeeNetworkStateListener} to receive notifications when the network state changes.
     *
     * @param stateListener the {@link ZigBeeNetworkStateListener} to receive the notifications
     */
    public void addNetworkStateListener(ZigBeeNetworkStateListener stateListener) {
        final List<ZigBeeNetworkStateListener> modifiedStateListeners = new ArrayList<ZigBeeNetworkStateListener>(
                stateListeners);
        modifiedStateListeners.add(stateListener);
        stateListeners = Collections.unmodifiableList(modifiedStateListeners);
    }

    /**
     * Removes a {@link ZigBeeNetworkStateListener}.
     *
     * @param stateListener the {@link ZigBeeNetworkStateListener} to stop receiving the notifications
     */
    public void removeNetworkStateListener(ZigBeeNetworkStateListener stateListener) {
        final List<ZigBeeNetworkStateListener> modifiedStateListeners = new ArrayList<ZigBeeNetworkStateListener>(
                stateListeners);
        modifiedStateListeners.remove(stateListener);
        stateListeners = Collections.unmodifiableList(modifiedStateListeners);
    }

    @Override
    public void setNetworkState(final ZigBeeTransportState state) {
        synchronized (this) {
            // Notify the listeners
            for (final ZigBeeNetworkStateListener stateListener : stateListeners) {
                NotificationService.execute(new Runnable() {
                    @Override
                    public void run() {
                        stateListener.networkStateUpdated(state);
                    }
                });
            }
        }
    }

    /**
     * Sends {@link ZclCommand} command to {@link ZigBeeAddress}.
     *
     * @param destination the destination
     * @param command the {@link ZclCommand}
     * @return the command result future
     */
    public Future<CommandResult> send(ZigBeeAddress destination, ZclCommand command) {
        command.setDestinationAddress(destination);
        if (destination.isGroup()) {
            return broadcast(command);
        } else {
            final ZigBeeTransactionMatcher responseMatcher = new ZclTransactionMatcher();
            return unicast(command, responseMatcher);
        }
    }

    /**
     * Sends ZCL command and uses the {@link ZigBeeTransactionMatcher} to match the response.
     *
     * @param command
     *            the {@link ZigBeeCommand}
     * @param responseMatcher
     *            the {@link ZigBeeTransactionMatcher}
     * @return the command result future
     */
    public Future<CommandResult> unicast(final ZigBeeCommand command, final ZigBeeTransactionMatcher responseMatcher) {
        synchronized (command) {
            final CommandResultFuture future = new CommandResultFuture(this);
            final CommandExecution commandExecution = new CommandExecution(System.currentTimeMillis(), command, future);
            future.setCommandExecution(commandExecution);
            final ZigBeeCommandListener commandListener = new ZigBeeCommandListener() {
                @Override
                public void commandReceived(ZigBeeCommand receivedCommand) {
                    // Ensure that received command is not processed before command is sent
                    // and hence transaction ID for the command set.
                    synchronized (command) {
                        if (responseMatcher.isTransactionMatch(command, receivedCommand)) {
                            synchronized (future) {
                                future.set(new CommandResult(receivedCommand));
                                synchronized (future) {
                                    future.notify();
                                }
                            }
                            removeCommandExecution(commandExecution);
                        }
                    }
                }
            };

            commandExecution.setCommandListener(commandListener);
            addCommandExecution(commandExecution);
            int transactionId = sendCommand(command);
            if (command instanceof ZclCommand) {
                ((ZclCommand) command).setTransactionId(transactionId);
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
    private Future<CommandResult> broadcast(final ZigBeeCommand command) {
        synchronized (command) {
            final CommandResultFuture future = new CommandResultFuture(this);

            sendCommand(command);
            future.set(new CommandResult(new BroadcastResponse()));

            return future;
        }
    }

    /**
     * Adds command listener and removes expired command listeners.
     *
     * @param commandExecution the command execution
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
     * @param expiredCommandExecution the command execution
     */
    protected void removeCommandExecution(CommandExecution expiredCommandExecution) {
        synchronized (commandExecutions) {
            commandExecutions.remove(expiredCommandExecution);
            removeCommandListener(expiredCommandExecution.getCommandListener());
            synchronized (expiredCommandExecution.getFuture()) {
                expiredCommandExecution.getFuture().notify();
            }
        }
    }

    /**
     * Enables or disables devices to join the whole network.
     * <p>
     * Devices can only join the network when joining is enabled. It is not advised to leave joining enabled permanently
     * since it allows devices to join the network without the installer knowing.
     *
     * @param duration sets the duration of the join enable. Setting this to 0 disables joining. As per ZigBee 3, a
     *            value of 255 is not permitted and will be ignored.
     */
    public boolean permitJoin(final int duration) {
        return permitJoin(new ZigBeeEndpointAddress(ZigBeeBroadcastDestination.BROADCAST_ROUTERS_AND_COORD.getKey()),
                duration);
    }

    /**
     * Enables or disables devices to join the network.
     * <p>
     * Devices can only join the network when joining is enabled. It is not advised to leave joining enabled permanently
     * since it allows devices to join the network without the installer knowing.
     *
     * @param destination the {@link ZigBeeEndpointAddress} to send the join request to
     * @param duration sets the duration of the join enable. Setting this to 0 disables joining. As per ZigBee 3, a
     *            value of 255 is not permitted and will be ignored.
     */
    public boolean permitJoin(final ZigBeeEndpointAddress destination, final int duration) {
        if (duration < 0 || duration >= 255) {
            logger.debug("Permit join to {} invalid period of {} seconds.", destination, duration);
            return false;
        }
        logger.debug("Permit join to {} for {} seconds.", destination, duration);

        ManagementPermitJoiningRequest command = new ManagementPermitJoiningRequest();
        command.setPermitDuration(duration);
        command.setTcSignificance(true);
        command.setDestinationAddress(destination);
        command.setSourceAddress(new ZigBeeEndpointAddress(0));

        sendCommand(command);

        // If this is a broadcast, then we send it to our own address as well
        // This seems to be required for some stacks (eg ZNP)
        if (ZigBeeBroadcastDestination.getBroadcastDestination(destination.getAddress()) != null) {
            command = new ManagementPermitJoiningRequest();
            command.setPermitDuration(duration);
            command.setTcSignificance(true);
            command.setDestinationAddress(new ZigBeeEndpointAddress(0));
            command.setSourceAddress(new ZigBeeEndpointAddress(0));

            sendCommand(command);
        }

        return true;
    }

    /**
     * Sends a ZDO Leave Request to a device requesting that an end device leave the network.
     *
     * @param destinationAddress the network address to send the request to - this is the device parent or the the
     *            device we want to leave.
     * @param leaveAddress the {@link IeeeAddress} of the end device we want to leave the network
     */
    public void leave(final Integer destinationAddress, final IeeeAddress leaveAddress) {
        final ManagementLeaveRequest command = new ManagementLeaveRequest();

        command.setDeviceAddress(leaveAddress);
        command.setDestinationAddress(new ZigBeeEndpointAddress(destinationAddress));
        command.setSourceAddress(new ZigBeeEndpointAddress(0));
        command.setRemoveChildrenRejoin(false);

        // Start a thread to wait for the response
        // When we receive the response, if it's successful, we assume the device left.
        new Thread() {
            @Override
            public void run() {
                try {
                    CommandResult response = unicast(command, command).get();
                    if (response.getStatusCode() == 0) {
                        ZigBeeNode node = getNode(leaveAddress);
                        if (node != null) {
                            removeNode(node);
                        } else {
                            logger.debug("{}: No response receoved to leave command", leaveAddress);
                        }
                    }
                } catch (InterruptedException | ExecutionException e) {
                    logger.debug("Error sending leave command.", e);
                }
            }
        }.start();
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

    public void addNetworkNodeListener(final ZigBeeNetworkNodeListener networkNodeListener) {
        if (networkNodeListener == null) {
            return;
        }
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

    /**
     * Starts a rediscovery on a node. This will send a {@link NetworkAddressRequest} as a broadcast and will receive
     * the response to trigger a full discovery.
     *
     * @param ieeeAddress the {@link IeeeAddress} of the node to rediscover
     */
    public void rediscoverNode(IeeeAddress address) {
        networkDiscoverer.rediscoverNode(address);
    }

    /**
     * Gets a {@link Set} of {@link ZigBeeNode}s known by the network
     *
     * @return {@link Set} of {@link ZigBeeNode}s
     */
    public Set<ZigBeeNode> getNodes() {
        synchronized (networkNodes) {
            return new HashSet<ZigBeeNode>(networkNodes.values());
        }
    }

    /**
     * Gets a node given the 16 bit network address
     *
     * @param networkAddress the 16 bit network address as {@link Integer}
     * @return the {@link ZigBeeNode}
     */
    public ZigBeeNode getNode(final Integer networkAddress) {
        synchronized (networkNodes) {
            for (ZigBeeNode node : networkNodes.values()) {
                if (node.getNetworkAddress().equals(networkAddress)) {
                    return node;
                }
            }
        }
        return null;
    }

    /**
     * Gets a node given the {@link IeeeAddress}
     *
     * @param ieeeAddress the {@link IeeeAddress}
     * @return the {@link ZigBeeNode} or null if the node was not found
     */
    public ZigBeeNode getNode(final IeeeAddress ieeeAddress) {
        return networkNodes.get(ieeeAddress);
    }

    /**
     * Removes a {@link ZigBeeNode} from the network
     *
     * @param node the {@link ZigBeeNode} to remove - must not be null
     */
    public void removeNode(final ZigBeeNode node) {
        if (node == null) {
            return;
        }

        logger.debug("{}: Node {} is removed from the network", node.getIeeeAddress(), node.getNetworkAddress());

        synchronized (networkNodes) {
            // Don't update if the node is not known
            // We especially don't want to notify listeners of a device we removed, that didn't exist!
            if (!networkNodes.containsKey(node.getIeeeAddress())) {
                return;
            }
            networkNodes.remove(node.getIeeeAddress());
        }
        synchronized (this) {
            for (final ZigBeeNetworkNodeListener listener : nodeListeners) {
                NotificationService.execute(new Runnable() {
                    @Override
                    public void run() {
                        listener.nodeRemoved(node);
                    }
                });
            }

            if (networkStateSerializer != null) {
                networkStateSerializer.serialize(this);
            }
        }
    }

    /**
     * Adds a {@link ZigBeeNode} to the network
     *
     * @param node the {@link ZigBeeNode} to add
     */
    public void addNode(final ZigBeeNode node) {
        if (node == null) {
            return;
        }

        logger.debug("{}: Node {} is added to the network", node.getIeeeAddress(), node.getNetworkAddress());

        synchronized (networkNodes) {
            // Don't add if the node is already known
            // We especially don't want to notify listeners
            if (networkNodes.containsKey(node.getIeeeAddress())) {
                updateNode(node);
                return;
            }
            networkNodes.put(node.getIeeeAddress(), node);
        }

        synchronized (this) {
            for (final ZigBeeNetworkNodeListener listener : nodeListeners) {
                NotificationService.execute(new Runnable() {
                    @Override
                    public void run() {
                        listener.nodeAdded(node);
                    }
                });
            }

            if (networkStateSerializer != null) {
                networkStateSerializer.serialize(this);
            }
        }
    }

    /**
     * Update a {@link ZigBeeNode} within the network
     *
     * @param node the {@link ZigBeeNode} to update
     */
    public void updateNode(final ZigBeeNode node) {
        if (node == null) {
            return;
        }
        logger.debug("{}: Node {} update", node.getIeeeAddress(), node.getNetworkAddress());

        final ZigBeeNode currentNode;
        synchronized (networkNodes) {
            currentNode = networkNodes.get(node.getIeeeAddress());

            // Return if we don't know this node
            if (currentNode == null) {
                logger.debug("{}: Node {} is not known", node.getIeeeAddress(), node.getNetworkAddress());
                return;
            }

            // Return if there were no updates
            if (!currentNode.updateNode(node)) {
                // logger.debug("{}: Node {} is not updated", node.getIeeeAddress(), node.getNetworkAddress());
                // return;
            }
        }

        synchronized (this) {
            for (final ZigBeeNetworkNodeListener listener : nodeListeners) {
                NotificationService.execute(new Runnable() {
                    @Override
                    public void run() {
                        listener.nodeUpdated(currentNode);
                    }
                });
            }

            if (networkStateSerializer != null) {
                networkStateSerializer.serialize(this);
            }
        }
    }

    /**
     * Adds a cluster to the list of clusters we will respond to with the {@link MatchDescriptorRequest}. Adding a
     * cluster here is only required in order to respond to this request. Typically the application should provide
     * further support for such clusters.
     *
     * @param int cluster
     */
    public void addSupportedCluster(int cluster) {
        if (clusterMatcher == null) {
            clusterMatcher = new ClusterMatcher(this);
        }

        clusterMatcher.addCluster(cluster);
    }
}

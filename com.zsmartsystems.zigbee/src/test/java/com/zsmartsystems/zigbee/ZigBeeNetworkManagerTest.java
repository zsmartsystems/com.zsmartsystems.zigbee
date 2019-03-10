/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.awaitility.Awaitility;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import com.zsmartsystems.zigbee.ZigBeeNode.ZigBeeNodeState;
import com.zsmartsystems.zigbee.app.ZigBeeNetworkExtension;
import com.zsmartsystems.zigbee.app.otaserver.ZigBeeOtaUpgradeExtension;
import com.zsmartsystems.zigbee.database.ZigBeeNetworkDataStore;
import com.zsmartsystems.zigbee.database.ZigBeeNetworkDatabaseManager;
import com.zsmartsystems.zigbee.security.ZigBeeKey;
import com.zsmartsystems.zigbee.serialization.DefaultDeserializer;
import com.zsmartsystems.zigbee.serialization.DefaultSerializer;
import com.zsmartsystems.zigbee.transaction.ZigBeeTransactionManager;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportState;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportTransmit;
import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFrameType;
import com.zsmartsystems.zigbee.zcl.ZclHeader;
import com.zsmartsystems.zigbee.zcl.clusters.general.ReadAttributesCommand;
import com.zsmartsystems.zigbee.zcl.clusters.onoff.OnCommand;

/**
 * Tests for {@link ZigBeeNetworkManager}
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeNetworkManagerTest implements ZigBeeNetworkNodeListener, ZigBeeNetworkStateListener,
        ZigBeeNetworkEndpointListener, ZigBeeCommandListener {
    private static int TIMEOUT = 5000;

    private ZigBeeNetworkNodeListener mockedNodeListener;
    private List<ZigBeeNode> nodeNodeListenerCapture;
    private ArgumentCaptor<ZigBeeApsFrame> mockedApsFrameListener;
    private List<ZigBeeNetworkState> networkStateListenerCapture;

    private ZigBeeTransportTransmit mockedTransport;
    private ZigBeeNetworkStateListener mockedStateListener;
    private List<ZigBeeCommand> commandListenerCapture;

    @Test
    public void testAddRemoveNode() throws Exception {
        ZigBeeNetworkManager networkManager = mockZigBeeNetworkManager();

        ZigBeeNode node1 = new ZigBeeNode(Mockito.mock(ZigBeeNetworkManager.class),
                new IeeeAddress("1234567890ABCDEF"));
        node1.setNetworkAddress(1234);
        ZigBeeNode node2 = new ZigBeeNode(Mockito.mock(ZigBeeNetworkManager.class),
                new IeeeAddress("123456789ABCDEF0"));
        node2.setNetworkAddress(5678);

        // Add a node and make sure it's in the list
        TestUtilities.setField(ZigBeeNetworkManager.class, networkManager, "networkState", ZigBeeNetworkState.ONLINE);

        networkManager.addNode(node1);
        assertEquals(1, networkManager.getNodes().size());
        Mockito.verify(mockedNodeListener, Mockito.timeout(TIMEOUT)).nodeAdded(node1);

        // Add it again to make sure it's not duplicated, but we get the added callback again as discovery is incomplete
        networkManager.addNode(node1);
        assertEquals(1, networkManager.getNodes().size());
        Mockito.verify(mockedNodeListener, Mockito.timeout(TIMEOUT).times(2)).nodeAdded(node1);
        Mockito.verify(mockedNodeListener, Mockito.times(0)).nodeUpdated(node1);

        Set<Object> completeList = Collections.synchronizedSet(new HashSet<>());
        completeList.add(new IeeeAddress("1234567890ABCDEF"));
        TestUtilities.setField(ZigBeeNetworkManager.class, networkManager, "nodeDiscoveryComplete", completeList);

        // Add it again to make sure it's not duplicated and we get the updated callback
        networkManager.addNode(node1);
        assertEquals(1, networkManager.getNodes().size());
        Mockito.verify(mockedNodeListener, Mockito.times(2)).nodeAdded(node1);
        Mockito.verify(mockedNodeListener, Mockito.timeout(TIMEOUT)).nodeUpdated(node1);

        // Add a null node to make sure it's not added and we get the updated callback
        networkManager.addNode(null);
        assertEquals(1, networkManager.getNodes().size());
        Mockito.verify(mockedNodeListener, Mockito.times(2)).nodeAdded(node1);

        // Add a new node to make sure it's added
        networkManager.addNode(node2);
        assertEquals(2, networkManager.getNodes().size());
        Mockito.verify(mockedNodeListener, Mockito.timeout(TIMEOUT)).nodeAdded(node2);

        ZigBeeNode foundNode = networkManager.getNode(1234);
        assertNotNull(foundNode);
        assertTrue(node1.equals(foundNode));

        // Remove it and make sure it's gone
        networkManager.removeNode(node1);
        assertEquals(1, networkManager.getNodes().size());
        Mockito.verify(mockedNodeListener, Mockito.timeout(TIMEOUT)).nodeRemoved(node1);

        // Remove again to make sure we're ok
        networkManager.removeNode(node1);
        assertEquals(1, networkManager.getNodes().size());
        Mockito.verify(mockedNodeListener, Mockito.times(1)).nodeRemoved(node1);

        // Remove a null node to make sure we're ok
        networkManager.removeNode(null);
        assertEquals(1, networkManager.getNodes().size());
        Mockito.verify(mockedNodeListener, Mockito.times(1)).nodeRemoved(node1);

        // Really check it's gone
        foundNode = networkManager.getNode(1234);
        assertNull(networkManager.getNode(1234));

        // Check we can also get using the IEEE address
        foundNode = networkManager.getNode(new IeeeAddress("123456789ABCDEF0"));
        assertTrue(node2.equals(foundNode));

        // Check we don't return false address
        foundNode = networkManager.getNode(new IeeeAddress("123456789ABCDEF1"));
        assertNull(foundNode);

        // Remove the listener
        networkManager.addNetworkNodeListener(null);
        networkManager.removeNetworkNodeListener(null);
        networkManager.removeNetworkNodeListener(mockedNodeListener);

        networkManager.shutdown();
    }

    @Test
    public void testAddExistingNode() throws Exception {
        String address = "123456789ABCDEF0";
        ZigBeeNetworkManager networkManager = mockZigBeeNetworkManager();

        ZigBeeNode node1 = new ZigBeeNode(Mockito.mock(ZigBeeNetworkManager.class), new IeeeAddress(address));
        node1.setNetworkAddress(1234);
        ZigBeeNode node2 = new ZigBeeNode(Mockito.mock(ZigBeeNetworkManager.class), new IeeeAddress(address));
        node2.setNetworkAddress(5678);
        networkManager.addNode(node1);
        assertEquals(1, networkManager.getNodes().size());
        ZigBeeNode nodeWeGot = networkManager.getNode(new IeeeAddress(address));
        assertEquals(Integer.valueOf(1234), nodeWeGot.getNetworkAddress());
        networkManager.addNode(node2);
        assertEquals(1, networkManager.getNodes().size());
        assertEquals(Integer.valueOf(5678), nodeWeGot.getNetworkAddress());
    }

    @Test
    public void testAddRemoveGroup() throws Exception {
        ZigBeeNetworkManager networkManager = mockZigBeeNetworkManager();

        networkManager.addGroup(new ZigBeeGroupAddress(1));
        assertEquals(1, networkManager.getGroups().size());
        networkManager.addGroup(new ZigBeeGroupAddress(1));
        assertEquals(1, networkManager.getGroups().size());

        networkManager.addGroup(new ZigBeeGroupAddress(2));
        assertEquals(2, networkManager.getGroups().size());

        networkManager.removeGroup(2);
        assertEquals(1, networkManager.getGroups().size());
        assertNull(networkManager.getGroup(1).getLabel());

        ZigBeeGroupAddress group = networkManager.getGroup(1);
        assertEquals(1, group.getGroupId());
        group.setLabel("Group Label");
        networkManager.updateGroup(group);
        assertEquals(1, networkManager.getGroups().size());

        assertEquals("Group Label", networkManager.getGroup(1).getLabel());
    }

    @Test
    public void testSendCommandZCL() throws Exception {
        ZigBeeNetworkManager networkManager = mockZigBeeNetworkManager();
        networkManager.setSerializer(DefaultSerializer.class, DefaultDeserializer.class);

        ZigBeeEndpointAddress deviceAddress = new ZigBeeEndpointAddress(1234, 56);
        OnCommand cmd = new OnCommand();
        cmd.setClusterId(6);
        cmd.setDestinationAddress(deviceAddress);
        cmd.setTransactionId(22);

        boolean error = false;
        networkManager.sendCommand(cmd);

        assertFalse(error);
        assertEquals(1, mockedApsFrameListener.getAllValues().size());

        ZigBeeApsFrame apsFrame = mockedApsFrameListener.getValue();
        assertEquals(ZigBeeNwkAddressMode.DEVICE, apsFrame.getAddressMode());
        assertEquals(1234, apsFrame.getDestinationAddress());
        assertEquals(0, apsFrame.getSourceAddress());

        assertEquals(0x104, apsFrame.getProfile());
        assertEquals(6, apsFrame.getCluster());
        assertEquals(56, apsFrame.getDestinationEndpoint());
    }

    @Test
    public void testReceiveZclCommand() throws Exception {
        ZigBeeNetworkManager networkManager = mockZigBeeNetworkManager();
        networkManager.setSerializer(DefaultSerializer.class, DefaultDeserializer.class);

        ZigBeeApsFrame apsFrame = new ZigBeeApsFrame();
        apsFrame.setSourceAddress(1234);
        apsFrame.setDestinationAddress(0);
        apsFrame.setApsCounter(1);

        apsFrame.setCluster(6);
        apsFrame.setDestinationEndpoint(2);
        apsFrame.setProfile(0x104);
        apsFrame.setSourceEndpoint(5);

        ZclHeader zclHeader = new ZclHeader();
        zclHeader.setCommandId(0);
        zclHeader.setFrameType(ZclFrameType.ENTIRE_PROFILE_COMMAND);
        zclHeader.setSequenceNumber(1);

        DefaultSerializer serializer = new DefaultSerializer();
        ZclFieldSerializer fieldSerializer = new ZclFieldSerializer(serializer);

        apsFrame.setPayload(zclHeader.serialize(fieldSerializer, new int[] {}));

        networkManager.receiveCommand(apsFrame);
        Awaitility.await().until(() -> commandListenerUpdated());

        ReadAttributesCommand response = (ReadAttributesCommand) commandListenerCapture.get(0);

        assertEquals(6, (int) response.getClusterId());
        assertEquals(0, (int) response.getCommandId());
        assertEquals(1, (int) response.getTransactionId());
        assertEquals(new ZigBeeEndpointAddress(1234, 5), response.getSourceAddress());
    }

    @Test
    public void testNetworkStateListener() {
        // ZigBeeNetworkManager networkManager = mockZigBeeNetworkManager();
        ZigBeeTransportTransmit transport = Mockito.mock(ZigBeeTransportTransmit.class);
        ZigBeeNetworkManager manager = new ZigBeeNetworkManager(transport);

        ZigBeeNetworkStateListener stateListener = Mockito.mock(ZigBeeNetworkStateListener.class);
        manager.addNetworkStateListener(stateListener);

        Mockito.when(transport.getNwkAddress()).thenReturn(Integer.valueOf(123));
        Mockito.when(transport.getIeeeAddress()).thenReturn(new IeeeAddress("1234567890ABCDEF"));

        // Default state is uninitialised
        assertEquals(ZigBeeNetworkState.UNINITIALISED, manager.getNetworkState());

        // This will be ignored as an illegal state transition
        manager.setTransportState(ZigBeeTransportState.ONLINE);

        manager.setTransportState(ZigBeeTransportState.INITIALISING);
        Mockito.verify(stateListener, Mockito.timeout(TIMEOUT)).networkStateUpdated(ZigBeeNetworkState.INITIALISING);

        manager.setTransportState(ZigBeeTransportState.ONLINE);
        Mockito.verify(stateListener, Mockito.timeout(TIMEOUT)).networkStateUpdated(ZigBeeNetworkState.ONLINE);

        assertEquals(Integer.valueOf(123), manager.getLocalNwkAddress());
        assertEquals(new IeeeAddress("1234567890ABCDEF"), manager.getLocalIeeeAddress());

        manager.removeNetworkStateListener(mockedStateListener);
    }

    @Test
    public void testSetChannel() throws Exception {
        ZigBeeNetworkManager networkManager = mockZigBeeNetworkManager();

        assertEquals(ZigBeeStatus.SUCCESS, networkManager.setZigBeeChannel(ZigBeeChannel.CHANNEL_11));
        assertEquals(ZigBeeChannel.CHANNEL_11, networkManager.getZigBeeChannel());
    }

    @Test
    public void testSetPanId() throws Exception {
        ZigBeeNetworkManager networkManager = mockZigBeeNetworkManager();

        assertEquals(ZigBeeStatus.INVALID_ARGUMENTS, networkManager.setZigBeePanId(-1));
        assertEquals(ZigBeeStatus.INVALID_ARGUMENTS, networkManager.setZigBeePanId(0x11111));

        assertEquals(ZigBeeStatus.SUCCESS, networkManager.setZigBeePanId(10));
        assertEquals(0xABCD, networkManager.getZigBeePanId());
    }

    @Test
    public void testSetExtendedPanId() throws Exception {
        ZigBeeNetworkManager networkManager = mockZigBeeNetworkManager();

        ExtendedPanId panId = new ExtendedPanId("1");
        assertEquals(ZigBeeStatus.SUCCESS, networkManager.setZigBeeExtendedPanId(panId));
        assertEquals(new ExtendedPanId("1"), networkManager.getZigBeeExtendedPanId());

        networkManager.removeNetworkStateListener(this);
        networkManager.removeCommandListener(this);
    }

    @Test
    public void setZigBeeInstallKey() {
        ZigBeeTransportTransmit transport = Mockito.mock(ZigBeeTransportTransmit.class);
        ZigBeeNetworkManager manager = new ZigBeeNetworkManager(transport);

        ZigBeeKey key = new ZigBeeKey();

        assertEquals(ZigBeeStatus.INVALID_ARGUMENTS, manager.setZigBeeInstallKey(key));

        key.setAddress(new IeeeAddress());
        manager.setZigBeeInstallKey(key);
    }

    @Test
    public void setZigBeeLinkKey() {
        ZigBeeTransportTransmit transport = Mockito.mock(ZigBeeTransportTransmit.class);
        ZigBeeNetworkManager manager = new ZigBeeNetworkManager(transport);

        ZigBeeKey key = new ZigBeeKey();
        manager.setZigBeeLinkKey(key);
        Mockito.verify(transport, Mockito.times(1)).setTcLinkKey(key);
    }

    @Test
    public void getZigBeeLinkKey() {
        ZigBeeTransportTransmit transport = Mockito.mock(ZigBeeTransportTransmit.class);
        ZigBeeNetworkManager manager = new ZigBeeNetworkManager(transport);

        ZigBeeKey key = new ZigBeeKey();
        Mockito.when(transport.getTcLinkKey()).thenReturn(key);

        assertEquals(key, manager.getZigBeeLinkKey());
    }

    @Test
    public void setZigBeeNetworkKey() {
        ZigBeeTransportTransmit transport = Mockito.mock(ZigBeeTransportTransmit.class);
        ZigBeeNetworkManager manager = new ZigBeeNetworkManager(transport);

        ZigBeeKey key = new ZigBeeKey();
        manager.setZigBeeNetworkKey(key);
        Mockito.verify(transport, Mockito.times(1)).setZigBeeNetworkKey(key);
    }

    @Test
    public void getZigBeeNetworkKey() {
        ZigBeeTransportTransmit transport = Mockito.mock(ZigBeeTransportTransmit.class);
        ZigBeeNetworkManager manager = new ZigBeeNetworkManager(transport);

        ZigBeeKey key = new ZigBeeKey();
        Mockito.when(transport.getZigBeeNetworkKey()).thenReturn(key);

        assertEquals(key, manager.getZigBeeNetworkKey());
    }

    @Test
    public void testPermitJoin() throws Exception {
        ZigBeeNetworkManager networkManager = mockZigBeeNetworkManager();
        networkManager.setSerializer(DefaultSerializer.class, DefaultDeserializer.class);

        ZigBeeTransactionManager transactionManager = Mockito.mock(ZigBeeTransactionManager.class);

        TestUtilities.setField(ZigBeeNetworkManager.class, networkManager, "transactionManager", transactionManager);
        assertEquals(ZigBeeStatus.SUCCESS, networkManager.permitJoin(0));
        Mockito.verify(transactionManager, Mockito.times(2)).sendTransaction(ArgumentMatchers.any(ZigBeeCommand.class));
        assertEquals(ZigBeeStatus.SUCCESS, networkManager.permitJoin(254));
        Mockito.verify(transactionManager, Mockito.times(4)).sendTransaction(ArgumentMatchers.any(ZigBeeCommand.class));
        assertEquals(ZigBeeStatus.INVALID_ARGUMENTS, networkManager.permitJoin(255));
        Mockito.verify(transactionManager, Mockito.times(4)).sendTransaction(ArgumentMatchers.any(ZigBeeCommand.class));

        // Check that the unicast sends 1 frame
        networkManager.permitJoin(new ZigBeeEndpointAddress(1), 1);
        Mockito.verify(transactionManager, Mockito.times(5)).sendTransaction(ArgumentMatchers.any(ZigBeeCommand.class));

        // Check that the broadcast sends 2 frames
        networkManager.permitJoin(1);
        Mockito.verify(transactionManager, Mockito.times(7)).sendTransaction(ArgumentMatchers.any(ZigBeeCommand.class));
    }

    private ZigBeeNetworkManager mockZigBeeNetworkManager() throws Exception {
        mockedTransport = Mockito.mock(ZigBeeTransportTransmit.class);
        mockedStateListener = Mockito.mock(ZigBeeNetworkStateListener.class);
        mockedNodeListener = Mockito.mock(ZigBeeNetworkNodeListener.class);
        nodeNodeListenerCapture = new ArrayList<>();
        networkStateListenerCapture = new ArrayList<>();

        final ZigBeeNetworkManager networkManager = new ZigBeeNetworkManager(mockedTransport);

        TestUtilities.setField(ZigBeeNetworkManager.class, networkManager, "databaseManager",
                Mockito.mock(ZigBeeNetworkDatabaseManager.class));

        networkManager.addNetworkNodeListener(mockedNodeListener);

        commandListenerCapture = new ArrayList<>();

        networkManager.addNetworkNodeListener(this);
        networkManager.addNetworkStateListener(this);
        networkManager.addCommandListener(this);

        Mockito.when(mockedTransport.setZigBeeChannel(ArgumentMatchers.any(ZigBeeChannel.class)))
                .thenReturn(ZigBeeStatus.SUCCESS);
        Mockito.when(mockedTransport.setZigBeePanId(ArgumentMatchers.anyInt())).thenReturn(ZigBeeStatus.SUCCESS);
        Mockito.when(mockedTransport.setZigBeeExtendedPanId(ArgumentMatchers.any(ExtendedPanId.class)))
                .thenReturn(ZigBeeStatus.SUCCESS);

        Mockito.when(mockedTransport.getZigBeePanId()).thenReturn(0xFFFFABCD);
        Mockito.when(mockedTransport.getZigBeeChannel()).thenReturn(ZigBeeChannel.CHANNEL_11);
        Mockito.when(mockedTransport.getZigBeeExtendedPanId()).thenReturn(new ExtendedPanId("1"));

        mockedApsFrameListener = ArgumentCaptor.forClass(ZigBeeApsFrame.class);

        Mockito.doNothing().when(mockedTransport).sendCommand(ArgumentMatchers.anyInt(),
                mockedApsFrameListener.capture());

        return networkManager;
    }

    @Override
    public void deviceAdded(ZigBeeEndpoint device) {
    }

    @Override
    public void deviceUpdated(ZigBeeEndpoint device) {
    }

    @Override
    public void deviceRemoved(ZigBeeEndpoint device) {
    }

    @Override
    public void networkStateUpdated(ZigBeeNetworkState state) {
        networkStateListenerCapture.add(state);
    }

    @Override
    public void nodeAdded(ZigBeeNode node) {
        nodeNodeListenerCapture.add(node);
    }

    @Override
    public void nodeUpdated(ZigBeeNode node) {
        nodeNodeListenerCapture.add(node);
    }

    @Override
    public void nodeRemoved(ZigBeeNode node) {
        nodeNodeListenerCapture.add(node);
    }

    @Override
    public void commandReceived(ZigBeeCommand command) {
        commandListenerCapture.add(command);
    }

    private Callable<Integer> stateListenerUpdated() {
        return new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return networkStateListenerCapture.size(); // The condition that must be fulfilled
            }
        };
    }

    private Callable<Integer> commandListenerUpdated() {
        return new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return commandListenerCapture.size(); // The condition that must be fulfilled
            }
        };
    }

    private ZigBeeCommand getZigBeeCommand(ZigBeeApsFrame apsFrame) throws Exception {
        ZigBeeNetworkManager networkManager = mockZigBeeNetworkManager();
        networkManager.setSerializer(DefaultSerializer.class, DefaultDeserializer.class);

        networkManager.receiveCommand(apsFrame);
        Awaitility.await().until(() -> commandListenerUpdated());
        if (commandListenerCapture.size() == 0) {
            return null;
        }
        return commandListenerCapture.get(0);
    }

    /**
     * Return a ZigBeeApsFrame from a log entry for an APS frame
     *
     * @param log the log line
     * @return the {@link ZigBeeApsFrame}
     */
    private ZigBeeApsFrame getApsFrame(String log) {
        ZigBeeApsFrame apsFrame = new ZigBeeApsFrame();

        String[] segments = log.substring(16, log.length() - 1).split(", ");
        for (String segment : segments) {
            String[] key = segment.split("=");
            if (key.length == 1 || key[1] == null || key[1].equals("null")) {
                continue;
            }
            switch (key[0]) {
                case "sourceAddress":
                    String[] sourceAddress = key[1].split("/");
                    apsFrame.setSourceAddress(Integer.parseInt(sourceAddress[0]));
                    apsFrame.setSourceEndpoint(Integer.parseInt(sourceAddress[1]));
                    break;
                case "destinationAddress":
                    String[] destAddress = key[1].split("/");
                    apsFrame.setDestinationAddress(Integer.parseInt(destAddress[0]));
                    apsFrame.setDestinationEndpoint(Integer.parseInt(destAddress[1]));
                    break;
                case "profile":
                    apsFrame.setProfile(Integer.parseInt(key[1], 16));
                    break;
                case "cluster":
                    apsFrame.setCluster(Integer.parseInt(key[1], 16));
                    break;
                case "addressMode":
                    ZigBeeNwkAddressMode addressMode = ZigBeeNwkAddressMode.valueOf(key[1]);
                    apsFrame.setAddressMode(addressMode);
                    break;
                case "radius":
                    apsFrame.setRadius(Integer.parseInt(key[1]));
                    break;
                case "apsSecurity":
                    apsFrame.setSecurityEnabled(Boolean.valueOf(key[1]));
                    break;
                case "apsCounter":
                    apsFrame.setApsCounter(Integer.parseInt(key[1], 16));
                    break;
                case "payload":
                    String split[] = key[1].trim().split(" ");

                    int[] payload = new int[split.length];
                    int cnt = 0;
                    for (String val : split) {
                        payload[cnt++] = Integer.parseInt(val, 16);
                    }
                    apsFrame.setPayload(payload);
                    break;
            }
        }

        return apsFrame;
    }

    @Test
    public void testExtensions() throws Exception {
        ZigBeeNetworkManager manager = new ZigBeeNetworkManager(Mockito.mock(ZigBeeTransportTransmit.class));
        ZigBeeNetworkDatabaseManager databaseManager = Mockito.mock(ZigBeeNetworkDatabaseManager.class);
        TestUtilities.setField(ZigBeeNetworkManager.class, manager, "databaseManager", databaseManager);

        manager.addExtension(new ZigBeeOtaUpgradeExtension());

        ZigBeeNetworkExtension returnedExtension = manager.getExtension(ZigBeeOtaUpgradeExtension.class);
        assertTrue(returnedExtension instanceof ZigBeeOtaUpgradeExtension);

        manager.shutdown();
        Mockito.verify(databaseManager, Mockito.times(1)).shutdown();
    }

    @Test
    public void initialize() throws Exception {
        ZigBeeTransportTransmit transport = Mockito.mock(ZigBeeTransportTransmit.class);
        Mockito.when(transport.initialize()).thenReturn(ZigBeeStatus.COMMUNICATION_ERROR);
        ZigBeeNetworkDatabaseManager databaseManager = Mockito.mock(ZigBeeNetworkDatabaseManager.class);
        ZigBeeNetworkManager manager = new ZigBeeNetworkManager(transport);
        TestUtilities.setField(ZigBeeNetworkManager.class, manager, "databaseManager", databaseManager);

        ZigBeeNetworkDataStore dataStore = Mockito.mock(ZigBeeNetworkDataStore.class);
        manager.setNetworkDataStore(dataStore);
        Mockito.verify(databaseManager, Mockito.times(1)).setDataStore(dataStore);

        assertEquals(ZigBeeStatus.COMMUNICATION_ERROR, manager.initialize());
        Mockito.verify(databaseManager, Mockito.times(1)).startup();

        transport = Mockito.mock(ZigBeeTransportTransmit.class);
        Mockito.when(transport.initialize()).thenReturn(ZigBeeStatus.SUCCESS);
        Mockito.when(transport.getNwkAddress()).thenReturn(Integer.valueOf(123));
        Mockito.when(transport.getIeeeAddress()).thenReturn(new IeeeAddress("1234567890ABCDEF"));

        transport = Mockito.mock(ZigBeeTransportTransmit.class);
        Mockito.when(transport.initialize()).thenReturn(ZigBeeStatus.SUCCESS);
        Mockito.when(transport.getNwkAddress()).thenReturn(Integer.valueOf(123));
        Mockito.when(transport.getIeeeAddress()).thenReturn(new IeeeAddress("1234567890ABCDEF"));
        manager = new ZigBeeNetworkManager(transport);
        databaseManager = Mockito.mock(ZigBeeNetworkDatabaseManager.class);
        TestUtilities.setField(ZigBeeNetworkManager.class, manager, "databaseManager", databaseManager);

        assertEquals(ZigBeeStatus.SUCCESS, manager.initialize());

        Mockito.verify(databaseManager, Mockito.times(1)).startup();

        ZigBeeNode node = manager.getNode(new IeeeAddress("1234567890ABCDEF"));
        assertNotNull(node);
        assertEquals(Integer.valueOf(123), node.getNetworkAddress());

        TestUtilities.setField(ZigBeeNetworkManager.class, manager, "networkState", ZigBeeNetworkState.INITIALISING);
        assertEquals(ZigBeeStatus.INVALID_STATE, manager.initialize());

        manager.shutdown();
        Mockito.verify(transport, Mockito.times(1)).shutdown();
    }

    @Test
    public void startup() throws Exception {
        ZigBeeTransportTransmit transport = Mockito.mock(ZigBeeTransportTransmit.class);
        Mockito.when(transport.initialize()).thenReturn(ZigBeeStatus.COMMUNICATION_ERROR);
        ZigBeeNetworkManager manager = new ZigBeeNetworkManager(transport);

        assertEquals(ZigBeeStatus.INVALID_STATE, manager.startup(true));

        TestUtilities.setField(ZigBeeNetworkManager.class, manager, "networkState", ZigBeeNetworkState.INITIALISING);

        assertEquals(transport, manager.getZigBeeTransport());

        Mockito.when(transport.startup(false)).thenReturn(ZigBeeStatus.COMMUNICATION_ERROR);
        Mockito.when(transport.startup(true)).thenReturn(ZigBeeStatus.SUCCESS);

        assertEquals(ZigBeeStatus.COMMUNICATION_ERROR, manager.startup(false));
        Mockito.verify(transport, Mockito.times(1)).startup(false);

        TestUtilities.setField(ZigBeeNetworkManager.class, manager, "networkState", ZigBeeNetworkState.INITIALISING);
        manager.setTransportState(ZigBeeTransportState.OFFLINE);
        assertEquals(ZigBeeNetworkState.INITIALISING, manager.getNetworkState());

        assertEquals(ZigBeeStatus.SUCCESS, manager.startup(true));
        Mockito.verify(transport, Mockito.times(1)).startup(true);

        Awaitility.await().until(() -> stateListenerUpdated());
        assertEquals(ZigBeeNetworkState.ONLINE, manager.getNetworkState());
        manager.setTransportState(ZigBeeTransportState.OFFLINE);
        Awaitility.await().until(() -> stateListenerUpdated());
        assertEquals(ZigBeeNetworkState.OFFLINE, manager.getNetworkState());
    }

    @Test
    public void getTransportVersionString() {
        ZigBeeTransportTransmit transport = Mockito.mock(ZigBeeTransportTransmit.class);
        Mockito.when(transport.initialize()).thenReturn(ZigBeeStatus.COMMUNICATION_ERROR);
        ZigBeeNetworkManager manager = new ZigBeeNetworkManager(transport);

        Mockito.when(transport.getVersionString()).thenReturn("Version!");

        assertEquals("Version!", manager.getTransportVersionString());

    }

    @Test
    public void nodeStatusUpdate() {
        ZigBeeTransportTransmit transport = Mockito.mock(ZigBeeTransportTransmit.class);
        ZigBeeNetworkManager manager = new ZigBeeNetworkManager(transport);

        ZigBeeNode node = Mockito.mock(ZigBeeNode.class);
        Mockito.when(node.getIeeeAddress()).thenReturn(new IeeeAddress("1234567890ABCDEF"));
        manager.addNode(node);

        ZigBeeAnnounceListener announceListener = Mockito.mock(ZigBeeAnnounceListener.class);
        manager.addAnnounceListener(announceListener);

        manager.nodeStatusUpdate(ZigBeeNodeStatus.DEVICE_LEFT, 1234, new IeeeAddress("123456789ABCDEF0"));
        Mockito.verify(node, Mockito.times(0)).setNodeState(ArgumentMatchers.any(ZigBeeNodeState.class));

        Mockito.verify(announceListener, Mockito.timeout(TIMEOUT).times(1)).deviceStatusUpdate(
                ArgumentMatchers.any(ZigBeeNodeStatus.class), ArgumentMatchers.any(Integer.class),
                ArgumentMatchers.any(IeeeAddress.class));

        manager.nodeStatusUpdate(ZigBeeNodeStatus.DEVICE_LEFT, 1234, new IeeeAddress("1234567890ABCDEF"));
        Mockito.verify(node, Mockito.times(1)).setNodeState(ZigBeeNodeState.OFFLINE);

        Mockito.verify(announceListener, Mockito.timeout(TIMEOUT).times(2)).deviceStatusUpdate(
                ArgumentMatchers.any(ZigBeeNodeStatus.class), ArgumentMatchers.any(Integer.class),
                ArgumentMatchers.any(IeeeAddress.class));

        manager.removeAnnounceListener(announceListener);
    }

    private Map<String, String> splitPacket(String packet) {
        Map<String, String> tokens = new HashMap<>();
        int start = 0;
        while (start < packet.length()) {
            for (int pos = start; pos < packet.length(); pos++) {
                if (packet.charAt(pos) != ' ') {
                    start = pos;
                    break;
                }
            }

            int end = start;
            for (int pos = start; pos < packet.length(); pos++) {
                if (packet.charAt(pos) == '=') {
                    end = pos;
                    break;
                }
            }

            String key = packet.substring(start, end);
            start = end + 1;

            int bracket = 0;
            end = packet.length();
            for (int pos = start; pos < packet.length(); pos++) {
                if (packet.charAt(pos) == '[') {
                    bracket++;
                }
                if (packet.charAt(pos) == ']') {
                    bracket--;
                }

                if ((packet.charAt(pos) == ',' && bracket == 0) || pos == packet.length()) {
                    end = pos;
                    break;
                }
            }

            tokens.put(key, packet.substring(start, end));
            start = end + 1;
        }

        return tokens;
    }

    private Map<String, String> getZclFrameTokens(String log) {
        Map<String, String> tokens = new HashMap<>();

        tokens.put("class", log.substring(0, log.indexOf(' ')));
        String packet = (log.substring(log.indexOf(" [") + 2, log.length() - 1));
        tokens.putAll(splitPacket(packet.substring(packet.indexOf(", ") + 2)));

        // String[] segments = log.substring(log.indexOf(" [") + 2, log.length() - 1).split(", ");
        // for (String segment : segments) {
        // String[] key = segment.split("=");
        // if (key.length == 2) {
        // tokens.put(key[0], key[1]);
        // }
        // }

        return tokens;
    }

    private String uppercaseFirst(String input) {
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }

    public void processLogEntry(String apsString, String zclString) throws Exception {
        System.out.println("---> Processing log");
        System.out.println("  -> " + apsString);
        System.out.println("  -> " + zclString);

        ZigBeeApsFrame apsFrame = getApsFrame(apsString);
        System.out.println("  <- " + apsFrame);

        ZigBeeCommand command = getZigBeeCommand(apsFrame);
        System.out.println("  <- " + command);

        assertNotNull(apsFrame);
        assertNotNull(command);

        Map<String, String> tokens = getZclFrameTokens(zclString);

        assertEquals(command.getClass().getSimpleName(), tokens.get("class"));
        if (command instanceof ZclCommand) {
            assertEquals((Integer) Integer.parseInt(tokens.get("TID"), 16), command.getTransactionId());
        }
        assertEquals((Integer) Integer.parseInt(tokens.get("cluster"), 16), command.getClusterId());

        tokens.remove("class");
        tokens.remove("TID");
        tokens.remove("cluster");

        for (String token : tokens.keySet()) {
            System.out.println("   get" + uppercaseFirst(token));
            Method method = command.getClass().getMethod("get" + uppercaseFirst(token));
            assertEquals(method.getName(), "get" + uppercaseFirst(token));
            Object data = method.invoke(command);

            Object convertedData;
            if (data == null) {
                convertedData = null;
            } else {
                convertedData = convertData(tokens.get(token), data.getClass());

                if (convertedData == null) {
                    System.out.println("     No data conversion in " + data.getClass().getSimpleName() + " "
                            + command.getClass().getSimpleName() + ".get" + uppercaseFirst(token) + "(). Data is "
                            + tokens.get(token) + ". Using obj.toString().equals();");
                    convertedData = tokens.get(token);
                    data = data.toString();
                }
            }

            assertEquals(convertedData, data);
        }
    }

    private Object convertData(String data, Class clazz) {
        return null;
    }

    @Test
    public void processLogs() throws Exception {
        File dir = FileSystems.getDefault().getPath("./src/test/resource/logs").toFile();

        // File dir = new File(file.toFile());
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File file : directoryListing) {
                System.out.println("-> Processing log file " + file.toString());
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                String apsString = null;
                String zclString = null;
                while ((line = br.readLine()) != null) {
                    if (line.startsWith("#") || line.length() == 0) {
                        continue;
                    }

                    if (apsString == null) {
                        apsString = line;
                        continue;
                    }
                    if (zclString == null) {
                        zclString = line;

                        processLogEntry(apsString, zclString);

                        apsString = null;
                        zclString = null;
                    }
                }
                br.close();
            }
        }
    }

    @Test
    public void scheduleTask() throws Exception {
        ZigBeeNetworkManager networkManager = mockZigBeeNetworkManager();
        ScheduledExecutorService scheduler = Mockito.mock(ScheduledExecutorService.class);
        TestUtilities.setField(ZigBeeNetworkManager.class, networkManager, "executorService", scheduler);

        assertNull(networkManager.scheduleTask(Mockito.mock(Runnable.class), 0, 0));
        assertNull(networkManager.scheduleTask(Mockito.mock(Runnable.class), 0, 1));
        assertNull(networkManager.scheduleTask(Mockito.mock(Runnable.class), 0));
        networkManager.executeTask(Mockito.mock(Runnable.class));
        assertNull(networkManager.rescheduleTask(Mockito.mock(ScheduledFuture.class), Mockito.mock(Runnable.class), 0));

        TestUtilities.setField(ZigBeeNetworkManager.class, networkManager, "networkState", ZigBeeNetworkState.ONLINE);

        networkManager.scheduleTask(Mockito.mock(Runnable.class), 0, 0);
        Mockito.verify(scheduler, Mockito.times(2)).schedule(ArgumentMatchers.any(Runnable.class),
                ArgumentMatchers.anyLong(), ArgumentMatchers.any(TimeUnit.class));

        networkManager.scheduleTask(Mockito.mock(Runnable.class), 0, 1);
        Mockito.verify(scheduler, Mockito.times(2)).scheduleAtFixedRate(ArgumentMatchers.any(Runnable.class),
                ArgumentMatchers.anyLong(), ArgumentMatchers.anyLong(), ArgumentMatchers.any(TimeUnit.class));

        networkManager.scheduleTask(Mockito.mock(Runnable.class), 0);
        Mockito.verify(scheduler, Mockito.times(3)).schedule(ArgumentMatchers.any(Runnable.class),
                ArgumentMatchers.anyLong(), ArgumentMatchers.any(TimeUnit.class));

        networkManager.executeTask(Mockito.mock(Runnable.class));
        Mockito.verify(scheduler, Mockito.times(1)).execute(ArgumentMatchers.any(Runnable.class));

        networkManager.rescheduleTask(Mockito.mock(ScheduledFuture.class), Mockito.mock(Runnable.class), 0);
        Mockito.verify(scheduler, Mockito.times(4)).schedule(ArgumentMatchers.any(Runnable.class),
                ArgumentMatchers.anyLong(), ArgumentMatchers.any(TimeUnit.class));
    }

}

/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import org.junit.Ignore;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Matchers;
import org.mockito.Mockito;

import com.zsmartsystems.zigbee.serialization.DefaultDeserializer;
import com.zsmartsystems.zigbee.serialization.DefaultSerializer;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportState;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportTransmit;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFrameType;
import com.zsmartsystems.zigbee.zcl.ZclHeader;
import com.zsmartsystems.zigbee.zcl.clusters.general.ReadAttributesCommand;
import com.zsmartsystems.zigbee.zcl.clusters.onoff.OnCommand;

public class ZigBeeNetworkManagerTest implements ZigBeeNetworkNodeListener, ZigBeeNetworkStateListener,
        ZigBeeNetworkEndpointListener, ZigBeeCommandListener {
    private ZigBeeNetworkNodeListener mockedNodeListener;
    private List<ZigBeeNode> nodeNodeListenerCapture;
    private ZigBeeNetworkEndpointListener mockedDeviceListener;
    private List<ZigBeeEndpoint> nodeDeviceListenerCapture;
    private ArgumentCaptor<ZigBeeApsFrame> mockedApsFrameListener;
    private List<ZigBeeTransportState> networkStateListenerCapture;

    private ZigBeeTransportTransmit mockedTransport;
    private ZigBeeCommandListener mockedCommandListener;
    private ZigBeeNetworkStateListener mockedStateListener;
    private List<ZigBeeCommand> commandListenerCapture;

    @Ignore
    @Test
    public void testAddRemoveNode() {
        ZigBeeNetworkManager networkManager = mockZigBeeNetworkManager();

        ZigBeeNode node1 = new ZigBeeNode(Mockito.mock(ZigBeeNetworkManager.class), new IeeeAddress());
        node1.setNetworkAddress(1234);
        ZigBeeNode node2 = new ZigBeeNode(Mockito.mock(ZigBeeNetworkManager.class),
                new IeeeAddress("123456789ABCDEF0"));
        node2.setNetworkAddress(5678);

        // Add a node and make sure it's in the list
        networkManager.addNode(node1);
        assertEquals(1, networkManager.getNodes().size());
        org.awaitility.Awaitility.await().until(nodeListenerUpdated(), org.hamcrest.Matchers.equalTo(1));

        // Add it again to make sure it's not duplicated
        networkManager.addNode(node1);
        assertEquals(1, networkManager.getNodes().size());
        org.awaitility.Awaitility.await().until(nodeListenerUpdated(), org.hamcrest.Matchers.equalTo(1));

        // Add a null node to make sure it's not added
        networkManager.addNode(null);
        assertEquals(1, networkManager.getNodes().size());
        org.awaitility.Awaitility.await().until(nodeListenerUpdated(), org.hamcrest.Matchers.equalTo(1));

        // Add a new node to make sure it's added
        networkManager.addNode(node2);
        assertEquals(2, networkManager.getNodes().size());
        org.awaitility.Awaitility.await().until(nodeListenerUpdated(), org.hamcrest.Matchers.equalTo(2));

        ZigBeeNode foundNode = networkManager.getNode(1234);
        assertNotNull(foundNode);
        assertTrue(node1.equals(foundNode));

        // Remove it and make sure it's gone
        networkManager.removeNode(node1);
        assertEquals(1, networkManager.getNodes().size());
        org.awaitility.Awaitility.await().until(nodeListenerUpdated(), org.hamcrest.Matchers.equalTo(3));

        // Remove again to make sure we're ok
        networkManager.removeNode(node1);
        assertEquals(1, networkManager.getNodes().size());
        org.awaitility.Awaitility.await().until(nodeListenerUpdated(), org.hamcrest.Matchers.equalTo(3));

        // Remove a null node to make sure we're ok
        networkManager.removeNode(null);
        assertEquals(1, networkManager.getNodes().size());
        org.awaitility.Awaitility.await().until(nodeListenerUpdated(), org.hamcrest.Matchers.equalTo(3));

        // Really check it's gone
        foundNode = networkManager.getNode(1234);
        assertNull(networkManager.getNode(1234));

        networkManager.updateNode(node2);
        org.awaitility.Awaitility.await().until(nodeListenerUpdated(), org.hamcrest.Matchers.equalTo(3));
        assertEquals(1, networkManager.getNodes().size());

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
    }

    @Test
    public void testAddExistingNode() {
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
    public void testAddRemoveGroup() {
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
    public void testSendCommandZCL() {
        ZigBeeNetworkManager networkManager = mockZigBeeNetworkManager();
        networkManager.setSerializer(DefaultSerializer.class, DefaultDeserializer.class);

        ZigBeeEndpointAddress deviceAddress = new ZigBeeEndpointAddress(1234, 56);
        OnCommand cmd = new OnCommand();
        cmd.setClusterId(6);
        cmd.setDestinationAddress(deviceAddress);

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
    public void testReceiveZclCommand() {
        ZigBeeNetworkManager networkManager = mockZigBeeNetworkManager();
        networkManager.setSerializer(DefaultSerializer.class, DefaultDeserializer.class);

        ZigBeeApsFrame apsFrame = new ZigBeeApsFrame();
        apsFrame.setSourceAddress(1234);
        apsFrame.setDestinationAddress(0);
        apsFrame.setSequence(1);

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
        org.awaitility.Awaitility.await().until(commandListenerUpdated(), org.hamcrest.Matchers.equalTo(1));

        ReadAttributesCommand response = (ReadAttributesCommand) commandListenerCapture.get(0);

        assertEquals(6, (int) response.getClusterId());
        assertEquals(0, (int) response.getCommandId());
        assertEquals(1, (int) response.getTransactionId());
        assertEquals(new ZigBeeEndpointAddress(1234, 5), response.getSourceAddress());
    }

    @Test
    public void testNetworkStateListener() {
        ZigBeeNetworkManager networkManager = mockZigBeeNetworkManager();

        networkManager.setNetworkState(ZigBeeTransportState.INITIALISING);

        org.awaitility.Awaitility.await().until(networkStateUpdatedSize(), org.hamcrest.Matchers.equalTo(1));

        assertEquals(1, networkStateListenerCapture.size());
        assertEquals(ZigBeeTransportState.INITIALISING, networkStateListenerCapture.get(0));

        networkManager.removeNetworkStateListener(mockedStateListener);
    }

    @Test
    public void testSetChannel() {
        ZigBeeNetworkManager networkManager = mockZigBeeNetworkManager();

        assertFalse(networkManager.setZigBeeChannel(10));
        assertFalse(networkManager.setZigBeeChannel(27));
        assertTrue(networkManager.setZigBeeChannel(11));
        assertEquals(11, networkManager.getZigBeeChannel());
    }

    @Test
    public void testSetPanId() {
        ZigBeeNetworkManager networkManager = mockZigBeeNetworkManager();

        assertTrue(networkManager.setZigBeePanId(10));
        assertEquals(999, networkManager.getZigBeePanId());
    }

    @Test
    public void testSetExtendedPanId() {
        ZigBeeNetworkManager networkManager = mockZigBeeNetworkManager();

        ExtendedPanId panId = new ExtendedPanId("1");
        assertTrue(networkManager.setZigBeeExtendedPanId(panId));
        assertEquals(new ExtendedPanId("1"), networkManager.getZigBeeExtendedPanId());
    }

    @Test
    public void testPermitJoin() {
        ZigBeeNetworkManager networkManager = mockZigBeeNetworkManager();
        networkManager.setSerializer(DefaultSerializer.class, DefaultDeserializer.class);

        assertTrue(networkManager.permitJoin(0));
        assertTrue(networkManager.permitJoin(254));
        assertFalse(networkManager.permitJoin(255));

        // Check that the unicast sends 1 frame
        int start = mockedApsFrameListener.getAllValues().size();
        networkManager.permitJoin(new ZigBeeEndpointAddress(1), 1);
        assertEquals(1, mockedApsFrameListener.getAllValues().size() - start);

        // Check that the broadcast sends 2 frames
        start = mockedApsFrameListener.getAllValues().size();
        networkManager.permitJoin(1);
        assertEquals(2, mockedApsFrameListener.getAllValues().size() - start);
    }

    private ZigBeeNetworkManager mockZigBeeNetworkManager() {
        mockedTransport = Mockito.mock(ZigBeeTransportTransmit.class);
        mockedStateListener = Mockito.mock(ZigBeeNetworkStateListener.class);
        mockedNodeListener = Mockito.mock(ZigBeeNetworkNodeListener.class);
        nodeNodeListenerCapture = new ArrayList<ZigBeeNode>();
        mockedDeviceListener = Mockito.mock(ZigBeeNetworkEndpointListener.class);
        // nodeDeviceListenerCapture = ArgumentCaptor.forClass(ZigBeeDevice.class);
        // networkStateListenerCapture = ArgumentCaptor.forClass(ZigBeeTransportState.class);
        networkStateListenerCapture = new ArrayList<ZigBeeTransportState>();

        final ZigBeeNetworkManager networkManager = new ZigBeeNetworkManager(mockedTransport);

        mockedCommandListener = Mockito.mock(ZigBeeCommandListener.class);
        commandListenerCapture = new ArrayList<>();

        networkManager.addNetworkNodeListener(this);
        networkManager.addNetworkStateListener(this);
        // networkManager.addNetworkDeviceListener(this);
        networkManager.addCommandListener(this);

        // Mockito.doNothing().when(mockedNodeListener).nodeAdded(nodeNodeListenerCapture.capture());
        // Mockito.doNothing().when(mockedNodeListener).nodeUpdated(nodeNodeListenerCapture.capture());
        // Mockito.doNothing().when(mockedNodeListener).nodeRemoved(nodeNodeListenerCapture.capture());
        // Mockito.doNothing().when(mockedCommandListener).commandReceived(commandListenerCapture.capture());
        // Mockito.doNothing().when(mockedStateListener).networkStateUpdated(networkStateListenerCapture.capture());

        Mockito.when(mockedTransport.setZigBeeChannel(Matchers.anyInt())).thenReturn(true);
        Mockito.when(mockedTransport.setZigBeePanId(Matchers.anyInt())).thenReturn(true);
        Mockito.when(mockedTransport.setZigBeeExtendedPanId((ExtendedPanId) Matchers.anyObject())).thenReturn(true);

        Mockito.when(mockedTransport.getZigBeePanId()).thenReturn(999);
        Mockito.when(mockedTransport.getZigBeeChannel()).thenReturn(11);
        Mockito.when(mockedTransport.getZigBeeExtendedPanId()).thenReturn(new ExtendedPanId("1"));

        mockedApsFrameListener = ArgumentCaptor.forClass(ZigBeeApsFrame.class);

        // final ZigBeeDevice device = new ZigBeeDevice(networkManager);
        // device.setDeviceAddress(new ZigBeeDeviceAddress(1234, 5));
        // networkManager.addDevice(device);

        Mockito.doNothing().when(mockedTransport).sendCommand(mockedApsFrameListener.capture());

        return networkManager;
    }

    @Override
    public void deviceAdded(ZigBeeEndpoint device) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deviceUpdated(ZigBeeEndpoint device) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deviceRemoved(ZigBeeEndpoint device) {
        // TODO Auto-generated method stub

    }

    @Override
    public void networkStateUpdated(ZigBeeTransportState state) {
        networkStateListenerCapture.add(state);
    }

    private Callable<Integer> networkStateUpdatedSize() {
        return new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return networkStateListenerCapture.size(); // The condition that must be fulfilled
            }
        };
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

    private Callable<Integer> nodeListenerUpdated() {
        return new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return nodeNodeListenerCapture.size(); // The condition that must be fulfilled
            }
        };
    }

    @Override
    public void commandReceived(ZigBeeCommand command) {
        commandListenerCapture.add(command);
    }

    private Callable<Integer> commandListenerUpdated() {
        return new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return commandListenerCapture.size(); // The condition that must be fulfilled
            }
        };
    }
}

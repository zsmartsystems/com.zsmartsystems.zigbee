/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
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
import java.util.Collection;
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
import com.zsmartsystems.zigbee.aps.ZigBeeApsFrame;
import com.zsmartsystems.zigbee.database.ZigBeeNetworkDataStore;
import com.zsmartsystems.zigbee.database.ZigBeeNetworkDatabaseManager;
import com.zsmartsystems.zigbee.security.ZigBeeKey;
import com.zsmartsystems.zigbee.serialization.DefaultDeserializer;
import com.zsmartsystems.zigbee.serialization.DefaultSerializer;
import com.zsmartsystems.zigbee.transaction.ZigBeeTransactionManager;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportState;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportTransmit;
import com.zsmartsystems.zigbee.zcl.ZclCluster;
import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFrameType;
import com.zsmartsystems.zigbee.zcl.ZclHeader;
import com.zsmartsystems.zigbee.zcl.clusters.ZclBasicCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclColorControlCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclDemandResponseAndLoadControlCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclElectricalMeasurementCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclIasZoneCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclKeyEstablishmentCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclLevelControlCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclMeteringCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclOnOffCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclOtaUpgradeCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclPriceCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclThermostatCluster;
import com.zsmartsystems.zigbee.zcl.clusters.general.ReadAttributesCommand;
import com.zsmartsystems.zigbee.zcl.clusters.onoff.OnCommand;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zdo.command.ManagementPermitJoiningRequest;
import com.zsmartsystems.zigbee.zdo.field.NodeDescriptor;
import com.zsmartsystems.zigbee.zdo.field.NodeDescriptor.LogicalType;

/**
 * Tests for {@link ZigBeeNetworkManager}
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeNetworkManagerTest
        implements ZigBeeNetworkNodeListener, ZigBeeNetworkEndpointListener, ZigBeeCommandListener {
    private static int TIMEOUT = 5000;

    private ZigBeeNetworkNodeListener mockedNodeListener;
    private List<ZigBeeNode> nodeNodeListenerCapture;
    private ArgumentCaptor<ZigBeeApsFrame> mockedApsFrameListener;

    private ZigBeeTransportTransmit mockedTransport;
    private ZigBeeNetworkStateListener mockedStateListener;
    private List<ZigBeeCommand> commandListenerCapture;

    @Test
    public void testAddRemoveNode() throws Exception {
        TestUtilities.outputTestHeader();
        ZigBeeNetworkManager networkManager = mockZigBeeNetworkManager();

        NodeDescriptor nodeDescriptor = Mockito.mock(NodeDescriptor.class);
        Mockito.when(nodeDescriptor.getLogicalType()).thenReturn(LogicalType.ROUTER);
        ZigBeeNode node1 = new ZigBeeNode(Mockito.mock(ZigBeeNetworkManager.class),
                new IeeeAddress("1234567890ABCDEF"));
        node1.setNodeDescriptor(nodeDescriptor);
        node1.setNetworkAddress(1234);
        ZigBeeEndpoint node1Endpoint1 = new ZigBeeEndpoint(node1, 1);
        ZclCluster node1Endpoint1Cluster1 = Mockito.mock(ZclCluster.class);
        Mockito.when(node1Endpoint1Cluster1.getClusterId()).thenReturn(1);
        node1Endpoint1.addInputCluster(node1Endpoint1Cluster1);

        ZigBeeNode node1dup = new ZigBeeNode(Mockito.mock(ZigBeeNetworkManager.class),
                new IeeeAddress("1234567890ABCDEF"));
        node1dup.setNetworkAddress(1234);
        ZigBeeEndpoint node1dupEndpoint1 = new ZigBeeEndpoint(node1dup, 1);
        ZclCluster node1dupEndpoint1Cluster = Mockito.mock(ZclCluster.class);
        Mockito.when(node1dupEndpoint1Cluster.getClusterId()).thenReturn(1);
        node1dupEndpoint1.addInputCluster(node1dupEndpoint1Cluster);

        ZigBeeNode node2 = new ZigBeeNode(Mockito.mock(ZigBeeNetworkManager.class),
                new IeeeAddress("123456789ABCDEF0"));
        node2.setNetworkAddress(5678);

        // Add a node and make sure it's in the list
        TestUtilities.setField(ZigBeeNetworkManager.class, networkManager, "networkState", ZigBeeNetworkState.ONLINE);

        networkManager.updateNode(node1);
        assertEquals(1, networkManager.getNodes().size());
        assertEquals(LogicalType.ROUTER, networkManager.getNode(new IeeeAddress("1234567890ABCDEF")).getLogicalType());
        Mockito.verify(mockedNodeListener, Mockito.timeout(TIMEOUT)).nodeAdded(node1);
        Mockito.verify(mockedTransport, Mockito.timeout(TIMEOUT).times(1))
                .setNodeDescriptor(new IeeeAddress("1234567890ABCDEF"), nodeDescriptor);

        // Add it again to make sure it's not duplicated, and we don't get a notification as there is no change
        networkManager.updateNode(node1);
        assertEquals(1, networkManager.getNodes().size());
        Mockito.verify(mockedNodeListener, Mockito.timeout(TIMEOUT).times(1)).nodeAdded(node1);
        Mockito.verify(mockedNodeListener, Mockito.timeout(TIMEOUT).times(0)).nodeUpdated(node1);
        Mockito.verify(mockedTransport, Mockito.timeout(TIMEOUT).times(1))
                .setNodeDescriptor(new IeeeAddress("1234567890ABCDEF"), nodeDescriptor);

        // Adding the endpoint will cause the nodeAdded notification, not nodeUpdated
        node1dup.addEndpoint(node1Endpoint1);
        networkManager.updateNode(node1dup);
        Mockito.verify(mockedNodeListener, Mockito.timeout(TIMEOUT).times(2)).nodeAdded(node1);
        Mockito.verify(mockedNodeListener, Mockito.timeout(TIMEOUT).times(0)).nodeUpdated(node1);

        Set<Object> completeList = Collections.synchronizedSet(new HashSet<>());
        completeList.add(new IeeeAddress("1234567890ABCDEF"));
        TestUtilities.setField(ZigBeeNetworkManager.class, networkManager, "nodeDiscoveryComplete", completeList);

        // Add it again to make sure it's not duplicated and we get the updated callback
        networkManager.updateNode(node1dup);
        assertEquals(1, networkManager.getNodes().size());
        Mockito.verify(mockedNodeListener, Mockito.timeout(TIMEOUT).times(2)).nodeAdded(node1);
        Mockito.verify(mockedNodeListener, Mockito.timeout(TIMEOUT).times(0)).nodeUpdated(node1);

        // Add a null node to make sure it's not added and we get the updated callback
        networkManager.updateNode(null);
        assertEquals(1, networkManager.getNodes().size());
        Mockito.verify(mockedNodeListener, Mockito.timeout(TIMEOUT).times(2)).nodeAdded(node1);

        // Add a new node to make sure it's added
        networkManager.updateNode(node2);
        assertEquals(2, networkManager.getNodes().size());
        Mockito.verify(mockedNodeListener, Mockito.timeout(TIMEOUT).times(1)).nodeAdded(node2);

        ZigBeeNode foundNode = networkManager.getNode(1234);
        assertNotNull(foundNode);
        assertTrue(node1.equals(foundNode));

        // Add a duplicate of the node, but with a different NWK address
        node1dup.setNetworkAddress(4321);
        networkManager.updateNode(node1dup);
        assertEquals(2, networkManager.getNodes().size());
        assertEquals(node1, networkManager.getNode(new IeeeAddress("1234567890ABCDEF")));

        // Check that the old NWK address is not found, and the new NWK is found
        assertNull(networkManager.getNode(1234));
        assertTrue(node1.equals(networkManager.getNode(4321)));

        // Check that reading back the endpoint and clusters from the first node added
        foundNode = networkManager.getNode(4321);
        ZigBeeEndpoint foundEndpoint = foundNode.getEndpoint(1);
        assertEquals(node1Endpoint1, foundEndpoint);

        // The extra cluster from the duplicate node 1 should be transferred to the original node
        ZclCluster foundCluster = foundEndpoint.getInputCluster(1);
        assertEquals(node1Endpoint1Cluster1, foundCluster);
        // foundCluster = foundEndpoint.getInputCluster(2);
        // assertEquals(node1dupEndpoint1Cluster2, foundCluster);

        // Remove it and make sure it's gone
        networkManager.removeNode(node1);
        assertEquals(1, networkManager.getNodes().size());
        Mockito.verify(mockedNodeListener, Mockito.timeout(TIMEOUT)).nodeRemoved(node1);

        // Remove again to make sure we're ok
        networkManager.removeNode(node1);
        assertEquals(1, networkManager.getNodes().size());
        Mockito.verify(mockedNodeListener, Mockito.timeout(TIMEOUT).times(1)).nodeRemoved(node1);

        // Remove a null node to make sure we're ok
        networkManager.removeNode(null);
        assertEquals(1, networkManager.getNodes().size());
        Mockito.verify(mockedNodeListener, Mockito.timeout(TIMEOUT).times(1)).nodeRemoved(node1);

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
        TestUtilities.outputTestHeader();
        String address = "123456789ABCDEF0";
        ZigBeeNetworkManager networkManager = mockZigBeeNetworkManager();

        ZigBeeNode node1 = new ZigBeeNode(Mockito.mock(ZigBeeNetworkManager.class), new IeeeAddress(address));
        node1.setNetworkAddress(1234);
        ZigBeeNode node2 = new ZigBeeNode(Mockito.mock(ZigBeeNetworkManager.class), new IeeeAddress(address));
        node2.setNetworkAddress(5678);
        networkManager.updateNode(node1);
        assertEquals(1, networkManager.getNodes().size());
        ZigBeeNode nodeWeGot = networkManager.getNode(new IeeeAddress(address));
        assertEquals(Integer.valueOf(1234), nodeWeGot.getNetworkAddress());
        networkManager.updateNode(node2);
        assertEquals(1, networkManager.getNodes().size());
        assertEquals(Integer.valueOf(5678), nodeWeGot.getNetworkAddress());
    }

    @Test
    public void testAddRemoveGroup() throws Exception {
        TestUtilities.outputTestHeader();
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
    public void setDefaults() throws Exception {
        TestUtilities.outputTestHeader();
        ZigBeeNetworkManager networkManager = mockZigBeeNetworkManager();

        assertEquals(ZigBeeStatus.INVALID_STATE,
                networkManager.setDefaultProfileId(ZigBeeProfileType.ZIGBEE_SMART_ENERGY.getKey()));

        assertEquals(ZigBeeStatus.INVALID_STATE,
                networkManager.setDefaultDeviceId(ZigBeeDeviceType.IN_HOME_DISPLAY.getKey()));

        TestUtilities.setField(ZigBeeNetworkManager.class, networkManager, "networkState",
                ZigBeeNetworkState.INITIALISING);

        assertEquals(ZigBeeStatus.SUCCESS,
                networkManager.setDefaultProfileId(ZigBeeProfileType.ZIGBEE_SMART_ENERGY.getKey()));
        Mockito.verify(mockedTransport, Mockito.times(1))
                .setDefaultProfileId(ZigBeeProfileType.ZIGBEE_SMART_ENERGY.getKey());

        assertEquals(ZigBeeStatus.SUCCESS,
                networkManager.setDefaultDeviceId(ZigBeeDeviceType.IN_HOME_DISPLAY.getKey()));
        Mockito.verify(mockedTransport, Mockito.times(1)).setDefaultDeviceId(ZigBeeDeviceType.IN_HOME_DISPLAY.getKey());
    }

    @Test
    public void testSendCommandZCL() throws Exception {
        TestUtilities.outputTestHeader();
        ZigBeeNetworkManager networkManager = mockZigBeeNetworkManager();
        networkManager.setSerializer(DefaultSerializer.class, DefaultDeserializer.class);

        ZigBeeEndpointAddress deviceAddress = new ZigBeeEndpointAddress(1234, 56);
        OnCommand cmd = new OnCommand();
        cmd.setClusterId(6);
        cmd.setTransactionId(22);
        assertFalse(networkManager.sendCommand(cmd));

        cmd.setDestinationAddress(deviceAddress);
        assertTrue(networkManager.sendCommand(cmd));

        TestUtilities.setField(ZigBeeNetworkManager.class, networkManager, "networkState",
                ZigBeeNetworkState.INITIALISING);

        assertEquals(ZigBeeStatus.SUCCESS,
                networkManager.setDefaultProfileId(ZigBeeProfileType.ZIGBEE_SMART_ENERGY.getKey()));
        assertTrue(networkManager.sendCommand(cmd));

        List<ZigBeeApsFrame> sentFrames = mockedApsFrameListener.getAllValues();
        assertEquals(2, sentFrames.size());

        ZigBeeApsFrame apsFrame = sentFrames.get(0);
        assertEquals(ZigBeeNwkAddressMode.DEVICE, apsFrame.getAddressMode());
        assertEquals(1234, apsFrame.getDestinationAddress());
        assertEquals(0, apsFrame.getSourceAddress());

        assertEquals(0x104, apsFrame.getProfile());
        assertEquals(6, apsFrame.getCluster());
        assertEquals(56, apsFrame.getDestinationEndpoint());

        apsFrame = sentFrames.get(1);
        assertEquals(ZigBeeNwkAddressMode.DEVICE, apsFrame.getAddressMode());
        assertEquals(1234, apsFrame.getDestinationAddress());
        assertEquals(0, apsFrame.getSourceAddress());

        assertEquals(0x109, apsFrame.getProfile());
        assertEquals(6, apsFrame.getCluster());
        assertEquals(56, apsFrame.getDestinationEndpoint());
    }

    @Test
    public void testReceiveZclCommand() throws Exception {
        TestUtilities.outputTestHeader();
        ZigBeeNetworkManager networkManager = mockZigBeeNetworkManager();
        networkManager.setSerializer(DefaultSerializer.class, DefaultDeserializer.class);
        networkManager.addSupportedServerCluster(6);

        ZigBeeEndpoint endpoint = Mockito.mock(ZigBeeEndpoint.class);
        ZclCluster cluster = new ZclOnOffCluster(endpoint);
        Mockito.when(endpoint.getOutputCluster(6)).thenReturn(cluster);

        ZigBeeNode node = Mockito.mock(ZigBeeNode.class);
        Mockito.when(node.getIeeeAddress()).thenReturn(new IeeeAddress("1111111111111111"));
        Mockito.when(node.getNetworkAddress()).thenReturn(1234);
        Mockito.when(node.getEndpoint(5)).thenReturn(endpoint);

        networkManager.updateNode(node);

        ZigBeeApsFrame apsFrame = new ZigBeeApsFrame();
        apsFrame.setSourceAddress(1234);
        apsFrame.setDestinationAddress(0);
        apsFrame.setApsCounter(1);

        apsFrame.setCluster(6);
        apsFrame.setDestinationEndpoint(1);
        apsFrame.setProfile(0x104);
        apsFrame.setSourceEndpoint(5);

        ZclHeader zclHeader = new ZclHeader();
        zclHeader.setCommandId(0);
        zclHeader.setFrameType(ZclFrameType.ENTIRE_PROFILE_COMMAND);
        zclHeader.setSequenceNumber(1);
        zclHeader.setDirection(ZclCommandDirection.CLIENT_TO_SERVER);

        DefaultSerializer serializer = new DefaultSerializer();
        ZclFieldSerializer fieldSerializer = new ZclFieldSerializer(serializer);

        apsFrame.setPayload(zclHeader.serialize(fieldSerializer, new int[] {}));

        TestUtilities.setField(ZigBeeNetworkManager.class, networkManager, "networkState", ZigBeeNetworkState.ONLINE);
        networkManager.receiveCommand(apsFrame);
        Awaitility.await().until(() -> commandListenerUpdated());

        ReadAttributesCommand response = (ReadAttributesCommand) commandListenerCapture.get(0);

        assertEquals(6, (int) response.getClusterId());
        assertEquals(0, (int) response.getCommandId());
        assertEquals(1, (int) response.getTransactionId());
        assertEquals(new ZigBeeEndpointAddress(1234, 5), response.getSourceAddress());

        ZigBeeAnnounceListener announceListener = Mockito.mock(ZigBeeAnnounceListener.class);
        networkManager.addAnnounceListener(announceListener);

        apsFrame.setSourceAddress(4321);
        networkManager.receiveCommand(apsFrame);
        Mockito.verify(announceListener, Mockito.timeout(TIMEOUT).times(1)).announceUnknownDevice(4321);
    }

    @Test
    public void testReceiveZclCommandDefault() throws Exception {
        TestUtilities.outputTestHeader();
        ZigBeeNetworkManager networkManager = mockZigBeeNetworkManager();
        networkManager.setSerializer(DefaultSerializer.class, DefaultDeserializer.class);

        ZigBeeTransactionManager transactionManager = Mockito.mock(ZigBeeTransactionManager.class);
        TestUtilities.setField(ZigBeeNetworkManager.class, networkManager, "transactionManager", transactionManager);

        ZigBeeEndpoint endpoint = Mockito.mock(ZigBeeEndpoint.class);
        ZclCluster cluster = new ZclOnOffCluster(endpoint);
        Mockito.when(endpoint.getOutputCluster(6)).thenReturn(cluster);

        ZigBeeNode node = Mockito.mock(ZigBeeNode.class);
        Mockito.when(node.getIeeeAddress()).thenReturn(new IeeeAddress("1111111111111111"));
        Mockito.when(node.getNetworkAddress()).thenReturn(1234);
        Mockito.when(node.getEndpoint(5)).thenReturn(endpoint);

        networkManager.updateNode(node);

        TestUtilities.setField(ZigBeeNetworkManager.class, networkManager, "networkState", ZigBeeNetworkState.ONLINE);

        // Test that sending unsupported commands provides a default response
        ArgumentCaptor<Integer> msgTagCaptor = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<ZclCommand> commandCaptor = ArgumentCaptor.forClass(ZclCommand.class);

        DefaultSerializer serializer = new DefaultSerializer();
        ZclFieldSerializer fieldSerializer = new ZclFieldSerializer(serializer);

        ZclHeader zclHeader = new ZclHeader();
        zclHeader.setCommandId(0);
        zclHeader.setFrameType(ZclFrameType.ENTIRE_PROFILE_COMMAND);
        zclHeader.setSequenceNumber(1);
        zclHeader.setDirection(ZclCommandDirection.CLIENT_TO_SERVER);

        ZigBeeApsFrame apsFrame = new ZigBeeApsFrame();

        apsFrame.setPayload(zclHeader.serialize(fieldSerializer, new int[] {}));

        apsFrame.setDestinationAddress(0);
        apsFrame.setApsCounter(1);

        apsFrame.setCluster(6);
        apsFrame.setDestinationEndpoint(1);
        apsFrame.setProfile(0x104);
        apsFrame.setSourceEndpoint(5);
        apsFrame.setSourceAddress(4321);
        networkManager.receiveCommand(apsFrame);
        Mockito.verify(transactionManager, Mockito.never()).sendTransaction(commandCaptor.capture());

        apsFrame.setApsCounter(2);
        apsFrame.setSourceAddress(1234);
        apsFrame.setSourceEndpoint(66);
        networkManager.receiveCommand(apsFrame);
        Mockito.verify(transactionManager, Mockito.never()).sendTransaction(commandCaptor.capture());

        apsFrame.setApsCounter(3);
        apsFrame.setCluster(6);
        apsFrame.setSourceEndpoint(5);
        apsFrame.setDestinationEndpoint(2);
        networkManager.receiveCommand(apsFrame);
        Mockito.verify(transactionManager, Mockito.never()).sendTransaction(commandCaptor.capture());
    }

    @Test
    public void testReceiveDefaultCommand() throws Exception {
        TestUtilities.outputTestHeader();
        ZigBeeNetworkManager networkManager = mockZigBeeNetworkManager();
        ZigBeeCommandListener listener = Mockito.mock(ZigBeeCommandListener.class);
        networkManager.addCommandListener(listener);
        networkManager.setSerializer(DefaultSerializer.class, DefaultDeserializer.class);

        ZigBeeEndpoint endpoint = Mockito.mock(ZigBeeEndpoint.class);
        ZclCluster cluster = new ZclOnOffCluster(endpoint);
        Mockito.when(endpoint.getOutputCluster(cluster.getClusterId())).thenReturn(cluster);

        ZigBeeNode node = Mockito.mock(ZigBeeNode.class);
        Mockito.when(node.getIeeeAddress()).thenReturn(new IeeeAddress("1111111111111111"));
        Mockito.when(node.getNetworkAddress()).thenReturn(1234);
        Mockito.when(node.getEndpoint(5)).thenReturn(endpoint);

        networkManager.updateNode(node);

        ZigBeeApsFrame apsFrame = new ZigBeeApsFrame();
        apsFrame.setSourceAddress(1234);
        apsFrame.setDestinationAddress(0);
        apsFrame.setApsCounter(1);

        apsFrame.setCluster(6);
        apsFrame.setDestinationEndpoint(1);
        apsFrame.setProfile(0x104);
        apsFrame.setSourceEndpoint(5);

        ZclHeader zclHeader = new ZclHeader();
        zclHeader.setCommandId(11);
        zclHeader.setFrameType(ZclFrameType.ENTIRE_PROFILE_COMMAND);
        zclHeader.setSequenceNumber(1);

        DefaultSerializer serializer = new DefaultSerializer();
        ZclFieldSerializer fieldSerializer = new ZclFieldSerializer(serializer);

        apsFrame.setPayload(zclHeader.serialize(fieldSerializer, new int[] {}));

        TestUtilities.setField(ZigBeeNetworkManager.class, networkManager, "networkState", ZigBeeNetworkState.ONLINE);
        networkManager.receiveCommand(apsFrame);
        Mockito.verify(listener, Mockito.never()).commandReceived(ArgumentMatchers.any(ZigBeeCommand.class));
    }

    @Test
    public void testReceiveZclCommandWithNewOutputCluster() throws Exception {
        TestUtilities.outputTestHeader();
        ZigBeeNetworkManager networkManager = mockZigBeeNetworkManager();
        networkManager.setSerializer(DefaultSerializer.class, DefaultDeserializer.class);

        ZigBeeEndpoint endpoint = Mockito.mock(ZigBeeEndpoint.class);
        ZclCluster cluster = new ZclOnOffCluster(endpoint);

        ZigBeeNode node = Mockito.mock(ZigBeeNode.class);
        Mockito.when(node.getIeeeAddress()).thenReturn(new IeeeAddress("1111111111111111"));
        Mockito.when(node.getNetworkAddress()).thenReturn(Integer.valueOf(0x1234));
        Mockito.when(node.isDiscovered()).thenReturn(true);
        Mockito.when(node.getEndpoint(5)).thenReturn(endpoint);
        Mockito.when(node.updateNode(ArgumentMatchers.any())).thenReturn(true);

        networkManager.addNetworkNodeListener(new ZigBeeNetworkNodeListener() {
            @Override
            public void nodeAdded(ZigBeeNode node) {
                Mockito.when(endpoint.getOutputCluster(6)).thenReturn(cluster);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        networkManager.updateNode(node);

        ZigBeeApsFrame apsFrame = new ZigBeeApsFrame();
        apsFrame.setSourceAddress(0x1234);
        apsFrame.setSourceEndpoint(5);
        apsFrame.setDestinationAddress(0);
        apsFrame.setDestinationEndpoint(1);

        apsFrame.setApsCounter(1);
        apsFrame.setCluster(6);
        apsFrame.setProfile(0x104);

        ZclHeader zclHeader = new ZclHeader();
        zclHeader.setCommandId(0);
        zclHeader.setFrameType(ZclFrameType.ENTIRE_PROFILE_COMMAND);
        zclHeader.setSequenceNumber(1);
        zclHeader.setDirection(ZclCommandDirection.CLIENT_TO_SERVER);

        DefaultSerializer serializer = new DefaultSerializer();
        ZclFieldSerializer fieldSerializer = new ZclFieldSerializer(serializer);

        apsFrame.setPayload(zclHeader.serialize(fieldSerializer, new int[] {}));

        TestUtilities.setField(ZigBeeNetworkManager.class, networkManager, "networkState", ZigBeeNetworkState.ONLINE);
        networkManager.receiveCommand(apsFrame);
        Awaitility.await().until(() -> commandListenerUpdated());

        ReadAttributesCommand response = (ReadAttributesCommand) commandListenerCapture.get(0);

        assertEquals(6, (int) response.getClusterId());
        assertEquals(0, (int) response.getCommandId());
        assertEquals(1, (int) response.getTransactionId());
        assertEquals(new ZigBeeEndpointAddress(0x1234, 5), response.getSourceAddress());
    }

    @Test
    public void testReceiveZclCommandWithNewInputCluster() throws Exception {
        TestUtilities.outputTestHeader();
        ZigBeeNetworkManager networkManager = mockZigBeeNetworkManager();
        networkManager.setSerializer(DefaultSerializer.class, DefaultDeserializer.class);

        ZigBeeEndpoint endpoint = Mockito.mock(ZigBeeEndpoint.class);
        ZclCluster cluster = new ZclOnOffCluster(endpoint);

        ZigBeeNode node = Mockito.mock(ZigBeeNode.class);
        Mockito.when(node.getIeeeAddress()).thenReturn(new IeeeAddress("1111111111111111"));
        Mockito.when(node.getNetworkAddress()).thenReturn(Integer.valueOf(0x1234));
        Mockito.when(node.isDiscovered()).thenReturn(true);
        Mockito.when(node.getEndpoint(5)).thenReturn(endpoint);
        Mockito.when(node.updateNode(ArgumentMatchers.any())).thenReturn(true);

        networkManager.addNetworkNodeListener(new ZigBeeNetworkNodeListener() {
            @Override
            public void nodeAdded(ZigBeeNode node) {
                Mockito.when(endpoint.getInputCluster(6)).thenReturn(cluster);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        networkManager.updateNode(node);

        ZigBeeApsFrame apsFrame = new ZigBeeApsFrame();
        apsFrame.setSourceAddress(0x1234);
        apsFrame.setSourceEndpoint(5);
        apsFrame.setDestinationAddress(0);
        apsFrame.setDestinationEndpoint(1);

        apsFrame.setApsCounter(1);
        apsFrame.setCluster(6);
        apsFrame.setProfile(0x104);

        ZclHeader zclHeader = new ZclHeader();
        zclHeader.setCommandId(0);
        zclHeader.setFrameType(ZclFrameType.ENTIRE_PROFILE_COMMAND);
        zclHeader.setSequenceNumber(1);
        zclHeader.setDirection(ZclCommandDirection.SERVER_TO_CLIENT);

        DefaultSerializer serializer = new DefaultSerializer();
        ZclFieldSerializer fieldSerializer = new ZclFieldSerializer(serializer);

        apsFrame.setPayload(zclHeader.serialize(fieldSerializer, new int[] {}));

        TestUtilities.setField(ZigBeeNetworkManager.class, networkManager, "networkState", ZigBeeNetworkState.ONLINE);
        networkManager.receiveCommand(apsFrame);
        Awaitility.await().until(() -> commandListenerUpdated());

        ReadAttributesCommand response = (ReadAttributesCommand) commandListenerCapture.get(0);

        assertEquals(6, (int) response.getClusterId());
        assertEquals(0, (int) response.getCommandId());
        assertEquals(1, (int) response.getTransactionId());
        assertEquals(new ZigBeeEndpointAddress(0x1234, 5), response.getSourceAddress());
    }

    @Test
    public void testNetworkStateListener() throws Exception {
        TestUtilities.outputTestHeader();
        ZigBeeNetworkManager manager = mockZigBeeNetworkManager();
        ZigBeeTransactionManager transactionManager = Mockito.mock(ZigBeeTransactionManager.class);
        TestUtilities.setField(ZigBeeNetworkManager.class, manager, "transactionManager", transactionManager);

        ZigBeeNetworkNodeListener nodeListener = Mockito.mock(ZigBeeNetworkNodeListener.class);
        manager.addNetworkNodeListener(nodeListener);
        ZigBeeNetworkStateListener stateListener = Mockito.mock(ZigBeeNetworkStateListener.class);
        manager.addNetworkStateListener(stateListener);

        NodeDescriptor nodeDescriptor = Mockito.mock(NodeDescriptor.class);
        ZigBeeNode node = Mockito.mock(ZigBeeNode.class);
        Mockito.when(node.getIeeeAddress()).thenReturn(new IeeeAddress("1234567890ABCDEF"));
        Mockito.when(node.getNetworkAddress()).thenReturn(0x1234);
        Mockito.when(node.getNodeDescriptor()).thenReturn(nodeDescriptor);
        manager.updateNode(node);

        Mockito.when(mockedTransport.getNwkAddress()).thenReturn(Integer.valueOf(0));
        Mockito.when(mockedTransport.getIeeeAddress()).thenReturn(new IeeeAddress("1234567890ABCDEF"));

        // Default state is uninitialised
        assertEquals(ZigBeeNetworkState.UNINITIALISED, manager.getNetworkState());

        // This will be ignored as an illegal state transition
        manager.setTransportState(ZigBeeTransportState.ONLINE);

        manager.setTransportState(ZigBeeTransportState.INITIALISING);
        Mockito.verify(stateListener, Mockito.timeout(TIMEOUT)).networkStateUpdated(ZigBeeNetworkState.INITIALISING);

        ZigBeeNetworkExtension extension = Mockito.mock(ZigBeeNetworkExtension.class);
        manager.addExtension(extension);

        // Transport state can only update to ONLINE once the Network state is past INITIALISING
        // In order for there to be a state change, we set this to OFFLINE
        TestUtilities.setField(ZigBeeNetworkManager.class, manager, "networkState", ZigBeeNetworkState.OFFLINE);

        manager.setTransportState(ZigBeeTransportState.ONLINE);
        Mockito.verify(extension, Mockito.timeout(TIMEOUT)).extensionStartup();
        Mockito.verify(stateListener, Mockito.timeout(TIMEOUT)).networkStateUpdated(ZigBeeNetworkState.ONLINE);
        Mockito.verify(nodeListener, Mockito.timeout(TIMEOUT)).nodeAdded(node);
        Mockito.verify(mockedTransport, Mockito.timeout(TIMEOUT)).setNodeDescriptor(new IeeeAddress("1234567890ABCDEF"),
                nodeDescriptor);
        manager.setTransportState(ZigBeeTransportState.ONLINE);

        ArgumentCaptor<ZigBeeCommand> mockedTransactionCaptor = ArgumentCaptor.forClass(ZigBeeCommand.class);

        Mockito.verify(transactionManager, Mockito.timeout(TIMEOUT).atLeast(1))
                .sendTransaction(mockedTransactionCaptor.capture());
        Mockito.verify(stateListener, Mockito.timeout(TIMEOUT)).networkStateUpdated(ZigBeeNetworkState.ONLINE);
        manager.setTransportState(ZigBeeTransportState.ONLINE);
        Mockito.verify(stateListener, Mockito.timeout(TIMEOUT)).networkStateUpdated(ZigBeeNetworkState.ONLINE);

        assertTrue(mockedTransactionCaptor.getValue() instanceof ManagementPermitJoiningRequest);
        ManagementPermitJoiningRequest joinRequest = (ManagementPermitJoiningRequest) mockedTransactionCaptor
                .getValue();
        assertEquals(Integer.valueOf(0), joinRequest.getPermitDuration());
        assertEquals(Integer.valueOf(0), manager.getLocalNwkAddress());
        assertEquals(new IeeeAddress("1234567890ABCDEF"), manager.getLocalIeeeAddress());

        manager.removeNetworkStateListener(mockedStateListener);
    }

    @Test
    public void testSetChannel() throws Exception {
        TestUtilities.outputTestHeader();
        ZigBeeNetworkManager networkManager = mockZigBeeNetworkManager();

        assertEquals(ZigBeeStatus.SUCCESS, networkManager.setZigBeeChannel(ZigBeeChannel.CHANNEL_11));
        assertEquals(ZigBeeChannel.CHANNEL_11, networkManager.getZigBeeChannel());
    }

    @Test
    public void testSetPanId() throws Exception {
        TestUtilities.outputTestHeader();
        ZigBeeNetworkManager networkManager = mockZigBeeNetworkManager();

        assertEquals(ZigBeeStatus.INVALID_ARGUMENTS, networkManager.setZigBeePanId(-1));
        assertEquals(ZigBeeStatus.INVALID_ARGUMENTS, networkManager.setZigBeePanId(0x11111));

        assertEquals(ZigBeeStatus.SUCCESS, networkManager.setZigBeePanId(10));
        assertEquals(0xABCD, networkManager.getZigBeePanId());
    }

    @Test
    public void testSetExtendedPanId() throws Exception {
        TestUtilities.outputTestHeader();
        ZigBeeNetworkManager networkManager = mockZigBeeNetworkManager();

        ExtendedPanId panId = new ExtendedPanId("1");
        assertEquals(ZigBeeStatus.SUCCESS, networkManager.setZigBeeExtendedPanId(panId));
        assertEquals(new ExtendedPanId("1"), networkManager.getZigBeeExtendedPanId());

        networkManager.removeNetworkStateListener(mockedStateListener);
        networkManager.removeCommandListener(this);
    }

    @Test
    public void setZigBeeInstallKey() throws Exception {
        TestUtilities.outputTestHeader();
        ZigBeeNetworkManager manager = mockZigBeeNetworkManager();

        ZigBeeKey key = new ZigBeeKey();

        assertEquals(ZigBeeStatus.INVALID_ARGUMENTS, manager.setZigBeeInstallKey(key));

        key.setAddress(new IeeeAddress());
        manager.setZigBeeInstallKey(key);
    }

    @Test
    public void setZigBeeLinkKey() throws Exception {
        TestUtilities.outputTestHeader();
        ZigBeeNetworkManager manager = mockZigBeeNetworkManager();

        ZigBeeKey key = new ZigBeeKey();
        manager.setZigBeeLinkKey(key);
        Mockito.verify(mockedTransport, Mockito.times(1)).setTcLinkKey(key);
    }

    @Test
    public void getZigBeeLinkKey() throws Exception {
        TestUtilities.outputTestHeader();
        ZigBeeNetworkManager manager = mockZigBeeNetworkManager();

        ZigBeeKey key = new ZigBeeKey();
        Mockito.when(mockedTransport.getTcLinkKey()).thenReturn(key);

        assertEquals(key, manager.getZigBeeLinkKey());
    }

    @Test
    public void setZigBeeNetworkKey() throws Exception {
        TestUtilities.outputTestHeader();
        ZigBeeNetworkManager manager = mockZigBeeNetworkManager();

        ZigBeeKey key = new ZigBeeKey();
        manager.setZigBeeNetworkKey(key);
        Mockito.verify(mockedTransport, Mockito.times(1)).setZigBeeNetworkKey(key);
    }

    @Test
    public void getZigBeeNetworkKey() throws Exception {
        TestUtilities.outputTestHeader();
        ZigBeeNetworkManager manager = mockZigBeeNetworkManager();

        ZigBeeKey key = new ZigBeeKey();
        Mockito.when(mockedTransport.getZigBeeNetworkKey()).thenReturn(key);

        assertEquals(key, manager.getZigBeeNetworkKey());
    }

    @Test
    public void testPermitJoin() throws Exception {
        TestUtilities.outputTestHeader();
        ZigBeeNetworkManager networkManager = mockZigBeeNetworkManager();

        ZigBeeTransactionManager transactionManager = Mockito.mock(ZigBeeTransactionManager.class);

        TestUtilities.setField(ZigBeeNetworkManager.class, networkManager, "transactionManager", transactionManager);
        assertEquals(ZigBeeStatus.SUCCESS, networkManager.permitJoin(0));
        Mockito.verify(transactionManager, Mockito.timeout(TIMEOUT).times(2))
                .sendTransaction(ArgumentMatchers.any(ZigBeeCommand.class));
        assertEquals(ZigBeeStatus.SUCCESS, networkManager.permitJoin(254));
        Mockito.verify(transactionManager, Mockito.timeout(TIMEOUT).times(4))
                .sendTransaction(ArgumentMatchers.any(ZigBeeCommand.class));
        assertEquals(ZigBeeStatus.INVALID_ARGUMENTS, networkManager.permitJoin(255));
        Mockito.verify(transactionManager, Mockito.timeout(TIMEOUT).times(4))
                .sendTransaction(ArgumentMatchers.any(ZigBeeCommand.class));

        // Check that the unicast sends 1 frame
        networkManager.permitJoin(new ZigBeeEndpointAddress(1), 1);
        Mockito.verify(transactionManager, Mockito.timeout(TIMEOUT).times(5))
                .sendTransaction(ArgumentMatchers.any(ZigBeeCommand.class));

        // Check that the broadcast sends 2 frames
        networkManager.permitJoin(1);
        Mockito.verify(transactionManager, Mockito.timeout(TIMEOUT).times(7))
                .sendTransaction(ArgumentMatchers.any(ZigBeeCommand.class));
    }

    private ZigBeeNetworkManager mockZigBeeNetworkManager() throws Exception {
        mockedTransport = Mockito.mock(ZigBeeTransportTransmit.class);
        mockedStateListener = Mockito.mock(ZigBeeNetworkStateListener.class);
        mockedNodeListener = Mockito.mock(ZigBeeNetworkNodeListener.class);
        nodeNodeListenerCapture = new ArrayList<>();

        final ZigBeeNetworkManager networkManager = new ZigBeeNetworkManager(mockedTransport);

        TestUtilities.setField(ZigBeeNetworkManager.class, networkManager, "databaseManager",
                Mockito.mock(ZigBeeNetworkDatabaseManager.class));

        networkManager.addNetworkNodeListener(mockedNodeListener);

        commandListenerCapture = new ArrayList<>();

        networkManager.addNetworkNodeListener(this);
        networkManager.addNetworkStateListener(mockedStateListener);
        networkManager.addCommandListener(this);
        networkManager.setSerializer(DefaultSerializer.class, DefaultDeserializer.class);

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

        ZigBeeEndpoint endpoint = Mockito.mock(ZigBeeEndpoint.class);
        ZclCluster cluster;

        cluster = new ZclBasicCluster(endpoint);
        Mockito.when(endpoint.getInputCluster(0x0000)).thenReturn(cluster);
        Mockito.when(endpoint.getOutputCluster(0x0000)).thenReturn(cluster);
        cluster = new ZclOnOffCluster(endpoint);
        Mockito.when(endpoint.getInputCluster(0x0006)).thenReturn(cluster);
        Mockito.when(endpoint.getOutputCluster(0x0006)).thenReturn(cluster);
        cluster = new ZclLevelControlCluster(endpoint);
        Mockito.when(endpoint.getInputCluster(0x0008)).thenReturn(cluster);
        Mockito.when(endpoint.getOutputCluster(0x0008)).thenReturn(cluster);
        cluster = new ZclOtaUpgradeCluster(endpoint);
        Mockito.when(endpoint.getInputCluster(0x0019)).thenReturn(cluster);
        Mockito.when(endpoint.getOutputCluster(0x0019)).thenReturn(cluster);
        cluster = new ZclThermostatCluster(endpoint);
        Mockito.when(endpoint.getInputCluster(0x0201)).thenReturn(cluster);
        Mockito.when(endpoint.getOutputCluster(0x0201)).thenReturn(cluster);
        cluster = new ZclColorControlCluster(endpoint);
        Mockito.when(endpoint.getInputCluster(0x0300)).thenReturn(cluster);
        Mockito.when(endpoint.getOutputCluster(0x0300)).thenReturn(cluster);
        cluster = new ZclIasZoneCluster(endpoint);
        Mockito.when(endpoint.getInputCluster(0x0500)).thenReturn(cluster);
        Mockito.when(endpoint.getOutputCluster(0x0500)).thenReturn(cluster);
        cluster = new ZclPriceCluster(endpoint);
        Mockito.when(endpoint.getInputCluster(ZclPriceCluster.CLUSTER_ID)).thenReturn(cluster);
        Mockito.when(endpoint.getOutputCluster(ZclPriceCluster.CLUSTER_ID)).thenReturn(cluster);
        cluster = new ZclDemandResponseAndLoadControlCluster(endpoint);
        Mockito.when(endpoint.getInputCluster(ZclDemandResponseAndLoadControlCluster.CLUSTER_ID)).thenReturn(cluster);
        Mockito.when(endpoint.getOutputCluster(ZclDemandResponseAndLoadControlCluster.CLUSTER_ID)).thenReturn(cluster);
        cluster = new ZclMeteringCluster(endpoint);
        Mockito.when(endpoint.getInputCluster(0x0702)).thenReturn(cluster);
        Mockito.when(endpoint.getOutputCluster(0x0702)).thenReturn(cluster);
        cluster = new ZclKeyEstablishmentCluster(endpoint);
        Mockito.when(endpoint.getInputCluster(0x0800)).thenReturn(cluster);
        Mockito.when(endpoint.getOutputCluster(0x0800)).thenReturn(cluster);
        cluster = new ZclElectricalMeasurementCluster(endpoint);
        Mockito.when(endpoint.getInputCluster(0x0B04)).thenReturn(cluster);
        Mockito.when(endpoint.getOutputCluster(0x0B04)).thenReturn(cluster);

        ZigBeeNode node = Mockito.mock(ZigBeeNode.class);
        Mockito.when(node.getIeeeAddress()).thenReturn(new IeeeAddress("1111111111111111"));
        Mockito.when(node.getNetworkAddress()).thenReturn(0);
        Mockito.when(node.getEndpoint(1)).thenReturn(endpoint);

        networkManager.updateNode(node);

        TestUtilities.setField(ZigBeeNetworkManager.class, networkManager, "networkState", ZigBeeNetworkState.ONLINE);
        networkManager.receiveCommand(apsFrame);
        Mockito.verify(node, Mockito.timeout(TIMEOUT).times(1))
                .commandReceived(ArgumentMatchers.any(ZigBeeCommand.class), ArgumentMatchers.any(),
                        ArgumentMatchers.any());
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
        TestUtilities.outputTestHeader();
        ZigBeeNetworkManager manager = mockZigBeeNetworkManager();
        ZigBeeNetworkDatabaseManager databaseManager = Mockito.mock(ZigBeeNetworkDatabaseManager.class);
        TestUtilities.setField(ZigBeeNetworkManager.class, manager, "databaseManager", databaseManager);

        // Can't add extensions if the networkState is not INITIALISING
        assertEquals(ZigBeeStatus.INVALID_STATE, manager.addExtension(new ZigBeeOtaUpgradeExtension()));
        ZigBeeNetworkExtension returnedExtension = manager.getExtension(ZigBeeOtaUpgradeExtension.class);
        assertNull(returnedExtension);

        TestUtilities.setField(ZigBeeNetworkManager.class, manager, "networkState", ZigBeeNetworkState.INITIALISING);

        assertEquals(ZigBeeStatus.SUCCESS, manager.addExtension(new ZigBeeOtaUpgradeExtension()));
        returnedExtension = manager.getExtension(ZigBeeOtaUpgradeExtension.class);
        assertTrue(returnedExtension instanceof ZigBeeOtaUpgradeExtension);

        manager.shutdown();
        Mockito.verify(databaseManager, Mockito.times(1)).shutdown();

        assertEquals(ZigBeeNetworkState.SHUTDOWN, manager.getNetworkState());
    }

    @Test
    public void initialize() throws Exception {
        TestUtilities.outputTestHeader();
        ZigBeeNetworkManager manager = mockZigBeeNetworkManager();
        Mockito.when(mockedTransport.initialize()).thenReturn(ZigBeeStatus.COMMUNICATION_ERROR);
        ZigBeeTransportTransmit transport = Mockito.mock(ZigBeeTransportTransmit.class);
        Mockito.when(transport.initialize()).thenReturn(ZigBeeStatus.COMMUNICATION_ERROR);
        ZigBeeNetworkDatabaseManager databaseManager = Mockito.mock(ZigBeeNetworkDatabaseManager.class);
        TestUtilities.setField(ZigBeeNetworkManager.class, manager, "databaseManager", databaseManager);

        ZigBeeNetworkDataStore dataStore = Mockito.mock(ZigBeeNetworkDataStore.class);
        manager.setNetworkDataStore(dataStore);
        Mockito.verify(databaseManager, Mockito.times(1)).setDataStore(dataStore);

        assertEquals(ZigBeeStatus.COMMUNICATION_ERROR, manager.initialize());
        Mockito.verify(databaseManager, Mockito.times(1)).startup();

        Mockito.when(mockedTransport.initialize()).thenReturn(ZigBeeStatus.SUCCESS);
        Mockito.when(mockedTransport.getNwkAddress()).thenReturn(Integer.valueOf(123));
        Mockito.when(mockedTransport.getIeeeAddress()).thenReturn(new IeeeAddress("1234567890ABCDEF"));

        manager = mockZigBeeNetworkManager();
        Mockito.when(mockedTransport.initialize()).thenReturn(ZigBeeStatus.SUCCESS);
        Mockito.when(mockedTransport.getNwkAddress()).thenReturn(Integer.valueOf(123));
        Mockito.when(mockedTransport.getIeeeAddress()).thenReturn(new IeeeAddress("1234567890ABCDEF"));

        Mockito.when(transport.initialize()).thenReturn(ZigBeeStatus.SUCCESS);
        Mockito.when(transport.getNwkAddress()).thenReturn(Integer.valueOf(123));
        Mockito.when(transport.getIeeeAddress()).thenReturn(new IeeeAddress("1234567890ABCDEF"));

        Mockito.when(mockedTransport.initialize()).thenReturn(ZigBeeStatus.SUCCESS);
        Mockito.when(mockedTransport.getNwkAddress()).thenReturn(Integer.valueOf(123));
        Mockito.when(mockedTransport.getIeeeAddress()).thenReturn(new IeeeAddress("1234567890ABCDEF"));

        databaseManager = Mockito.mock(ZigBeeNetworkDatabaseManager.class);
        TestUtilities.setField(ZigBeeNetworkManager.class, manager, "databaseManager", databaseManager);

        assertEquals(ZigBeeStatus.SUCCESS, manager.initialize());

        Mockito.verify(databaseManager, Mockito.times(1)).startup();

        TestUtilities.setField(ZigBeeNetworkManager.class, manager, "networkState", ZigBeeNetworkState.INITIALISING);
        assertEquals(ZigBeeStatus.INVALID_STATE, manager.initialize());

        Mockito.verify(mockedStateListener, Mockito.timeout(TIMEOUT).atLeast(1))
                .networkStateUpdated(ZigBeeNetworkState.INITIALISING);

        assertEquals(ZigBeeNetworkState.INITIALISING, manager.getNetworkState());

        manager.shutdown();
        Mockito.verify(mockedTransport, Mockito.timeout(TIMEOUT).times(1)).shutdown();
        Mockito.verify(databaseManager, Mockito.times(1)).shutdown();

        Mockito.verify(mockedStateListener, Mockito.timeout(TIMEOUT).atLeast(1))
                .networkStateUpdated(ZigBeeNetworkState.SHUTDOWN);

        assertEquals(ZigBeeNetworkState.SHUTDOWN, manager.getNetworkState());
    }

    @Test
    public void startup() throws Exception {
        TestUtilities.outputTestHeader();
        ZigBeeNetworkManager manager = mockZigBeeNetworkManager();
        Mockito.when(mockedTransport.initialize()).thenReturn(ZigBeeStatus.COMMUNICATION_ERROR);

        ZigBeeNetworkDatabaseManager databaseManager = Mockito.mock(ZigBeeNetworkDatabaseManager.class);
        assertEquals(ZigBeeStatus.INVALID_STATE, manager.startup(true));

        TestUtilities.setField(ZigBeeNetworkManager.class, manager, "networkState", ZigBeeNetworkState.INITIALISING);
        TestUtilities.setField(ZigBeeNetworkManager.class, manager, "databaseManager", databaseManager);

        assertEquals(mockedTransport, manager.getZigBeeTransport());

        Mockito.when(mockedTransport.startup(false)).thenReturn(ZigBeeStatus.COMMUNICATION_ERROR);
        Mockito.when(mockedTransport.startup(true)).thenReturn(ZigBeeStatus.SUCCESS);

        assertEquals(ZigBeeStatus.COMMUNICATION_ERROR, manager.startup(false));

        TestUtilities.setField(ZigBeeNetworkManager.class, manager, "localIeeeAddress",
                new IeeeAddress("1234567890ABCDEF"));

        assertEquals(ZigBeeStatus.INVALID_STATE, manager.startup(false));

        ZigBeeNode localNode = new ZigBeeNode(manager, new IeeeAddress("1234567890ABCDEF"), 0);
        manager.updateNode(localNode);

        ZigBeeNode node = manager.getNode(new IeeeAddress("1234567890ABCDEF"));
        assertNotNull(node);
        assertTrue(node.getEndpoints().isEmpty());

        TestUtilities.setField(ZigBeeNetworkManager.class, manager, "networkState", ZigBeeNetworkState.INITIALISING);

        assertEquals(ZigBeeStatus.SUCCESS, manager.addSupportedClientCluster(1));
        assertEquals(ZigBeeStatus.SUCCESS, manager.addSupportedServerCluster(2));
        assertEquals(ZigBeeStatus.SUCCESS, manager.addSupportedServerCluster(3));

        assertEquals(ZigBeeStatus.COMMUNICATION_ERROR, manager.startup(false));
        Mockito.verify(mockedTransport, Mockito.times(2)).startup(false);
        Mockito.verify(databaseManager, Mockito.never()).clear();

        TestUtilities.setField(ZigBeeNetworkManager.class, manager, "networkState", ZigBeeNetworkState.INITIALISING);
        manager.setTransportState(ZigBeeTransportState.OFFLINE);
        assertEquals(ZigBeeNetworkState.INITIALISING, manager.getNetworkState());

        assertEquals(ZigBeeStatus.SUCCESS, manager.startup(true));
        Mockito.verify(mockedTransport, Mockito.timeout(TIMEOUT).times(1)).startup(true);
        Mockito.verify(databaseManager, Mockito.times(1)).clear();

        Mockito.verify(mockedStateListener, Mockito.timeout(TIMEOUT).times(1))
                .networkStateUpdated(ZigBeeNetworkState.ONLINE);
        assertEquals(ZigBeeNetworkState.ONLINE, manager.getNetworkState());
        manager.setTransportState(ZigBeeTransportState.ONLINE);
        manager.setTransportState(ZigBeeTransportState.OFFLINE);
        Mockito.verify(mockedStateListener, Mockito.timeout(TIMEOUT).atLeast(1))
                .networkStateUpdated(ZigBeeNetworkState.OFFLINE);
        assertEquals(ZigBeeNetworkState.OFFLINE, manager.getNetworkState());

        TestUtilities.setField(ZigBeeNetworkManager.class, manager, "networkState", ZigBeeNetworkState.ONLINE);
        assertEquals(ZigBeeNetworkState.ONLINE, manager.getNetworkState());
        assertEquals(ZigBeeStatus.SUCCESS, manager.reinitialize());
        Mockito.verify(mockedStateListener, Mockito.timeout(TIMEOUT))
                .networkStateUpdated(ZigBeeNetworkState.INITIALISING);
        assertEquals(ZigBeeNetworkState.INITIALISING, manager.getNetworkState());
    }

    @Test
    public void getTransportVersionString() throws Exception {
        TestUtilities.outputTestHeader();
        ZigBeeNetworkManager manager = mockZigBeeNetworkManager();

        Mockito.when(mockedTransport.initialize()).thenReturn(ZigBeeStatus.COMMUNICATION_ERROR);

        Mockito.when(mockedTransport.getVersionString()).thenReturn("Version!");

        assertEquals("Version!", manager.getTransportVersionString());
    }

    @Test
    public void nodeStatusUpdate() throws Exception {
        TestUtilities.outputTestHeader();
        ZigBeeNetworkManager manager = mockZigBeeNetworkManager();

        ZigBeeNode node = Mockito.mock(ZigBeeNode.class);
        Mockito.when(node.getIeeeAddress()).thenReturn(new IeeeAddress("1234567890ABCDEF"));
        manager.updateNode(node);

        ZigBeeAnnounceListener announceListener = Mockito.mock(ZigBeeAnnounceListener.class);
        manager.addAnnounceListener(announceListener);
        assertEquals(1, ((Collection<ZigBeeAnnounceListener>) TestUtilities.getField(ZigBeeNetworkManager.class,
                manager, "announceListeners")).size());
        manager.addAnnounceListener(announceListener);
        assertEquals(1, ((Collection<ZigBeeAnnounceListener>) TestUtilities.getField(ZigBeeNetworkManager.class,
                manager, "announceListeners")).size());

        manager.nodeStatusUpdate(ZigBeeNodeStatus.DEVICE_LEFT, 1234, new IeeeAddress("123456789ABCDEF0"));
        Mockito.verify(node, Mockito.times(0)).setNodeState(ArgumentMatchers.any(ZigBeeNodeState.class));

        Mockito.verify(announceListener, Mockito.timeout(TIMEOUT).times(1)).deviceStatusUpdate(
                ArgumentMatchers.any(ZigBeeNodeStatus.class), ArgumentMatchers.any(Integer.class),
                ArgumentMatchers.any(IeeeAddress.class));

        manager.nodeStatusUpdate(ZigBeeNodeStatus.DEVICE_LEFT, 1234, new IeeeAddress("1234567890ABCDEF"));
        Mockito.verify(node, Mockito.times(1)).updateNode(ArgumentMatchers.any(ZigBeeNode.class));

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
        System.out.println("  => " + apsString);
        System.out.println("  => " + zclString);

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

            assertEquals(data, convertedData);
        }
    }

    private Object convertData(String data, Class clazz) {
        return null;
    }

    @Test
    public void processLogs() throws Exception {
        File dir = FileSystems.getDefault().getPath("./src/test/resource/logs").toFile();

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
        TestUtilities.outputTestHeader();
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

    @Test
    public void addSupportedCluster() throws Exception {
        TestUtilities.outputTestHeader();
        ZigBeeNetworkManager networkManager = mockZigBeeNetworkManager();

        // Always true when no clusters added
        assertTrue(networkManager.isClientClusterSupported(123));
        assertTrue(networkManager.isServerClusterSupported(456));

        assertEquals(ZigBeeStatus.INVALID_STATE, networkManager.addSupportedClientCluster(123));

        TestUtilities.setField(ZigBeeNetworkManager.class, networkManager, "networkState",
                ZigBeeNetworkState.INITIALISING);

        assertEquals(ZigBeeStatus.SUCCESS, networkManager.addSupportedClientCluster(123));
        assertEquals(ZigBeeStatus.SUCCESS, networkManager.addSupportedServerCluster(456));

        assertTrue(networkManager.isClientClusterSupported(123));
        assertTrue(networkManager.isServerClusterSupported(456));

        assertFalse(networkManager.isClientClusterSupported(999));
        assertFalse(networkManager.isServerClusterSupported(999));
    }

}

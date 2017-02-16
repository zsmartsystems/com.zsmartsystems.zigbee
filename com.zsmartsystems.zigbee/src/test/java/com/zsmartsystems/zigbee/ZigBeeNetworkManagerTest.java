package com.zsmartsystems.zigbee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import com.zsmartsystems.zigbee.serialization.DefaultDeserializer;
import com.zsmartsystems.zigbee.serialization.DefaultSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFrameType;
import com.zsmartsystems.zigbee.zcl.ZclHeader;
import com.zsmartsystems.zigbee.zcl.clusters.general.ReadAttributesCommand;
import com.zsmartsystems.zigbee.zcl.clusters.onoff.OnCommand;
import com.zsmartsystems.zigbee.zdo.ZdoCommand;
import com.zsmartsystems.zigbee.zdo.command.ActiveEndpointsResponse;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeNetworkManagerTest {
    private ZigBeeNetworkNodeListener mockedNodeListener;
    private ArgumentCaptor<ZigBeeNode> nodeNodeListenerCapture;
    private ZigBeeNetworkDeviceListener mockedDeviceListener;
    private ArgumentCaptor<ZigBeeDevice> nodeDeviceListenerCapture;
    private ArgumentCaptor<ZigBeeNwkHeader> mockedNwkHeaderListener;
    private ArgumentCaptor<ZigBeeApsHeader> mockedApsHeaderListener;
    private ArgumentCaptor<ZigBeeTransportState> networkStateListenerCapture;

    private ZigBeeTransportTransmit mockedTransport;
    private ArgumentCaptor<int[]> mockedPayloadListener;
    private CommandListener mockedCommandListener;
    private ZigBeeNetworkStateListener mockedStateListener;
    private ArgumentCaptor<Command> commandListenerCapture;

    @Test
    public void testAddRemoveNode() {
        ZigBeeNetworkManager networkManager = mockZigBeeNetworkManager();

        ZigBeeNode node1 = new ZigBeeNode();
        node1.setNetworkAddress(1234);
        ZigBeeNode node2 = new ZigBeeNode();
        node2.setNetworkAddress(5678);

        // Add a node and make sure it's in the list
        networkManager.addNode(node1);
        // Mockito.verify(mockedNodeListener, Mockito.timeout(100)).nodeUpdated(nodeNodeListenerCapture.capture());

        assertEquals(1, networkManager.getNodes().size());
        // assertEquals(1, nodeNodeListenerCapture.getAllValues().size());

        // Add it again to make sure it's not duplicated
        networkManager.addNode(node1);
        assertEquals(1, networkManager.getNodes().size());
        // assertEquals(1, nodeNodeListenerCapture.getAllValues().size());
        //
        // Add a null node to make sure it's not added
        networkManager.addNode(null);
        assertEquals(1, networkManager.getNodes().size());
        // assertEquals(1, nodeNodeListenerCapture.getAllValues().size());

        // Add a new node to make sure it's added
        networkManager.addNode(node2);
        // Mockito.verify(mockedNodeListener, Mockito.timeout(100)).nodeUpdated(nodeNodeListenerCapture.capture());

        assertEquals(2, networkManager.getNodes().size());
        // assertEquals(2, nodeNodeListenerCapture.getAllValues().size());

        ZigBeeNode foundNode = networkManager.getNode(1234);
        assertNotNull(foundNode);
        assertTrue(node1.equals(foundNode));

        // Remove it and make sure it's gone
        networkManager.removeNode(node1);
        // Mockito.verify(mockedNodeListener, Mockito.timeout(100)).nodeUpdated(nodeNodeListenerCapture.capture());

        assertEquals(1, networkManager.getNodes().size());
        // assertEquals(3, nodeNodeListenerCapture.getAllValues().size());

        // Remove again to make sure we're ok
        networkManager.removeNode(node1);
        // Mockito.verify(mockedNodeListener, Mockito.timeout(100)).nodeUpdated(nodeNodeListenerCapture.capture());

        assertEquals(1, networkManager.getNodes().size());
        // assertEquals(3, nodeNodeListenerCapture.getAllValues().size());

        // Remove a null node to make sure we're ok
        networkManager.removeNode(null);
        assertEquals(1, networkManager.getNodes().size());
        // assertEquals(3, nodeNodeListenerCapture.getAllValues().size());

        // Really check it's gone
        foundNode = networkManager.getNode(1234);
        assertNull(networkManager.getNode(1234));

        node2.setIeeeAddress(new IeeeAddress("123456789ABCDEF0"));
        networkManager.updateNode(node2);
        // Mockito.verify(mockedNodeListener, Mockito.timeout(100)).nodeUpdated(nodeNodeListenerCapture.capture());

        // assertEquals(4, nodeNodeListenerCapture.getAllValues().size());
        networkManager.updateNode(null);
        // assertEquals(4, nodeNodeListenerCapture.getAllValues().size());
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
    public void testAddRemoveDevice() {
        ZigBeeNetworkManager networkManager = mockZigBeeNetworkManager();

        ZigBeeDevice device1 = new ZigBeeDevice(networkManager);
        device1.setDeviceAddress(new ZigBeeDeviceAddress(1234, 5));
        networkManager.addDevice(device1);
        assertEquals(1, networkManager.getDevices().size());

        ZigBeeDevice device2 = new ZigBeeDevice(networkManager);
        device2.setDeviceAddress(new ZigBeeDeviceAddress(6789, 0));
        networkManager.addDevice(device2);
        assertEquals(2, networkManager.getDevices().size());

        device2 = new ZigBeeDevice(networkManager);
        device2.setDeviceAddress(new ZigBeeDeviceAddress(1234, 1));
        device2.setIeeeAddress(new IeeeAddress("1234567890ABCDEF"));
        networkManager.addDevice(device2);
        device2 = new ZigBeeDevice(networkManager);
        device2.setDeviceAddress(new ZigBeeDeviceAddress(1234, 2));
        device2.setIeeeAddress(new IeeeAddress("1234567890ABCDEF"));
        networkManager.addDevice(device2);
        device2 = new ZigBeeDevice(networkManager);
        device2.setDeviceAddress(new ZigBeeDeviceAddress(1234, 3));
        device2.setIeeeAddress(new IeeeAddress("1234567890ABCDEF"));
        networkManager.addDevice(device2);
        device2 = new ZigBeeDevice(networkManager);
        device2.setDeviceAddress(new ZigBeeDeviceAddress(1234, 4));
        device2.setIeeeAddress(new IeeeAddress("1234567890ABCDEF"));
        networkManager.addDevice(device2);

        // We should now have 6 devices, 5 of then in node 1234, and 4 of them have IEEE address
        assertEquals(6, networkManager.getDevices().size());
        assertEquals(5, networkManager.getNodeDevices(1234).size());
        assertEquals(4, networkManager.getNodeDevices(new IeeeAddress("1234567890ABCDEF")).size());

        device2 = networkManager.getDevice(new ZigBeeDeviceAddress(6789, 0));
        device2.setLabel("Device Label");
        networkManager.updateDevice(device2);
        assertEquals(6, networkManager.getDevices().size());
        assertEquals("Device Label", networkManager.getDevice(new ZigBeeDeviceAddress(6789, 0)).getLabel());

        networkManager.removeDevice(new ZigBeeDeviceAddress(6789, 0));
        assertEquals(5, networkManager.getDevices().size());

        assertNull(networkManager.getDevice(null));
        assertNull(networkManager.getDevice(new ZigBeeGroupAddress(1)));

        networkManager.addNetworkDeviceListener(null);
        networkManager.removeNetworkDeviceListener(null);
        networkManager.removeNetworkDeviceListener(mockedDeviceListener);
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

        ZigBeeDeviceAddress deviceAddress = new ZigBeeDeviceAddress(1234, 56);
        OnCommand cmd = new OnCommand();
        cmd.setClusterId(6);
        cmd.setDestinationAddress(deviceAddress);

        boolean error = false;
        try {
            networkManager.sendCommand(cmd);
        } catch (ZigBeeException e) {
            error = true;
        }

        assertFalse(error);
        assertEquals(1, mockedNwkHeaderListener.getAllValues().size());
        assertEquals(1, mockedApsHeaderListener.getAllValues().size());
        assertEquals(1, mockedPayloadListener.getAllValues().size());

        ZigBeeNwkHeader nwkHeader = mockedNwkHeaderListener.getValue();
        assertEquals(ZigBeeNwkAddressMode.DEVICE, nwkHeader.getAddressMode());
        assertEquals(1234, nwkHeader.getDestinationAddress());
        assertEquals(0, nwkHeader.getSequence());
        assertEquals(0, nwkHeader.getSourceAddress());

        ZigBeeApsHeader apsHeader = mockedApsHeaderListener.getValue();
        assertEquals(0x104, apsHeader.getProfile());
        assertEquals(6, apsHeader.getCluster());
        assertEquals(56, apsHeader.getDestinationEndpoint());
    }

    @Test
    public void testReceiveZclCommand() {
        ZigBeeNetworkManager networkManager = mockZigBeeNetworkManager();
        networkManager.setSerializer(DefaultSerializer.class, DefaultDeserializer.class);

        ZigBeeNwkHeader nwkHeader = new ZigBeeNwkHeader();
        nwkHeader.setSourceAddress(1234);
        nwkHeader.setDestinationAddress(0);
        nwkHeader.setSequence(1);

        ZigBeeApsHeader apsHeader = new ZigBeeApsHeader();
        apsHeader.setCluster(6);
        apsHeader.setDestinationEndpoint(2);
        apsHeader.setProfile(0x104);
        apsHeader.setSourceEndpoint(5);

        ZclHeader zclHeader = new ZclHeader();
        zclHeader.setCommandId(0);
        zclHeader.setFrameType(ZclFrameType.ENTIRE_PROFILE_COMMAND);
        zclHeader.setSequenceNumber(1);

        DefaultSerializer serializer = new DefaultSerializer();
        ZclFieldSerializer fieldSerializer = new ZclFieldSerializer(serializer);

        int[] payload = zclHeader.serialize(fieldSerializer, new int[] {});

        networkManager.receiveZclCommand(nwkHeader, apsHeader, payload);
        // assertEquals(1, commandListenerCapture.getAllValues().size());
        Mockito.verify(mockedCommandListener, Mockito.timeout(100)).commandReceived(commandListenerCapture.capture());
        ReadAttributesCommand response = (ReadAttributesCommand) commandListenerCapture.getValue();

        assertEquals(6, (int) response.getClusterId());
        assertEquals(0, (int) response.getCommandId());
        assertEquals(1, (int) response.getTransactionId());
        assertEquals(new ZigBeeDeviceAddress(1234, 5), response.getSourceAddress());
    }

    @Test
    public void testReceiveZdoCommand() {
        ZigBeeNetworkManager networkManager = mockZigBeeNetworkManager();
        networkManager.setSerializer(DefaultSerializer.class, DefaultDeserializer.class);

        ZdoCommand cmd = new ActiveEndpointsResponse();
        networkManager.receiveZdoCommand(cmd);
        Mockito.verify(mockedCommandListener, Mockito.timeout(100)).commandReceived(commandListenerCapture.capture());
    }

    @Test
    public void testNetworkStateListener() {
        ZigBeeNetworkManager networkManager = mockZigBeeNetworkManager();

        networkManager.setNetworkState(ZigBeeTransportState.INITIALISING);

        Mockito.verify(mockedStateListener, Mockito.timeout(100))
                .networkStateUpdated(networkStateListenerCapture.capture());

        assertEquals(1, networkStateListenerCapture.getAllValues().size());
        assertEquals(ZigBeeTransportState.INITIALISING, networkStateListenerCapture.getValue());

        networkManager.removeNetworkStateListener(mockedStateListener);
    }

    private ZigBeeNetworkManager mockZigBeeNetworkManager() {
        mockedTransport = Mockito.mock(ZigBeeTransportTransmit.class);
        mockedStateListener = Mockito.mock(ZigBeeNetworkStateListener.class);
        mockedNodeListener = Mockito.mock(ZigBeeNetworkNodeListener.class);
        nodeNodeListenerCapture = ArgumentCaptor.forClass(ZigBeeNode.class);
        mockedDeviceListener = Mockito.mock(ZigBeeNetworkDeviceListener.class);
        nodeDeviceListenerCapture = ArgumentCaptor.forClass(ZigBeeDevice.class);
        networkStateListenerCapture = ArgumentCaptor.forClass(ZigBeeTransportState.class);

        final ZigBeeNetworkManager networkManager = new ZigBeeNetworkManager(mockedTransport);

        mockedCommandListener = Mockito.mock(CommandListener.class);
        commandListenerCapture = ArgumentCaptor.forClass(Command.class);

        networkManager.addNetworkNodeListener(mockedNodeListener);
        networkManager.addNetworkStateListener(mockedStateListener);
        networkManager.addNetworkDeviceListener(mockedDeviceListener);
        networkManager.addCommandListener(mockedCommandListener);

        Mockito.doNothing().when(mockedNodeListener).nodeAdded(nodeNodeListenerCapture.capture());
        // Mockito.doNothing().when(mockedNodeListener).nodeUpdated(nodeNodeListenerCapture.capture());
        // Mockito.doNothing().when(mockedNodeListener).nodeRemoved(nodeNodeListenerCapture.capture());
        Mockito.doNothing().when(mockedCommandListener).commandReceived(commandListenerCapture.capture());

        mockedNwkHeaderListener = ArgumentCaptor.forClass(ZigBeeNwkHeader.class);
        mockedApsHeaderListener = ArgumentCaptor.forClass(ZigBeeApsHeader.class);
        mockedPayloadListener = ArgumentCaptor.forClass(int[].class);

        final ZigBeeDevice device = new ZigBeeDevice(networkManager);
        device.setDeviceAddress(new ZigBeeDeviceAddress(1234, 5));
        networkManager.addDevice(device);

        try {
            Mockito.doNothing().when(mockedTransport).sendZclCommand(mockedNwkHeaderListener.capture(),
                    mockedApsHeaderListener.capture(), mockedPayloadListener.capture());
        } catch (ZigBeeException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return networkManager;
    }
}

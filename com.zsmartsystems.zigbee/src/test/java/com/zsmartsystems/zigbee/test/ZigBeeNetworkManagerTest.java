package com.zsmartsystems.zigbee.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.ZigBeeDevice;
import com.zsmartsystems.zigbee.ZigBeeNetworkDeviceListener;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNetworkNodeListener;
import com.zsmartsystems.zigbee.ZigBeeNode;
import com.zsmartsystems.zigbee.ZigBeeTransportTransmit;

public class ZigBeeNetworkManagerTest {
    private ZigBeeNetworkNodeListener mockedNodeListener;
    private ArgumentCaptor<ZigBeeNode> nodeNodeListenerCapture;
    private ZigBeeNetworkDeviceListener mockedDeviceListener;
    private ArgumentCaptor<ZigBeeDevice> nodeDeviceListenerCapture;

    @Test
    public void testAddRemoveNode() {
        ZigBeeNetworkManager networkManager = mockZigBeeNetworkManager();

        ZigBeeNode node1 = new ZigBeeNode();
        node1.setNetworkAddress(1234);
        ZigBeeNode node2 = new ZigBeeNode();
        node2.setNetworkAddress(5678);

        // Add a node and make sure it's in the list
        networkManager.addNode(node1);
        assertEquals(1, networkManager.getNodes().size());
        assertEquals(1, nodeNodeListenerCapture.getAllValues().size());

        // Add it again to make sure it's not duplicated
        networkManager.addNode(node1);
        assertEquals(1, networkManager.getNodes().size());
        assertEquals(1, nodeNodeListenerCapture.getAllValues().size());

        // Add a null node to make sure it's not added
        networkManager.addNode(null);
        assertEquals(1, networkManager.getNodes().size());
        assertEquals(1, nodeNodeListenerCapture.getAllValues().size());

        // Add a new node to make sure it's added
        networkManager.addNode(node2);
        assertEquals(2, networkManager.getNodes().size());
        assertEquals(2, nodeNodeListenerCapture.getAllValues().size());

        ZigBeeNode foundNode = networkManager.getNode(1234);
        assertNotNull(foundNode);
        assertTrue(node1.equals(foundNode));

        // Remove it and make sure it's gone
        networkManager.removeNode(node1);
        assertEquals(1, networkManager.getNodes().size());
        assertEquals(3, nodeNodeListenerCapture.getAllValues().size());

        // Remove again to make sure we're ok
        networkManager.removeNode(node1);
        assertEquals(1, networkManager.getNodes().size());
        assertEquals(3, nodeNodeListenerCapture.getAllValues().size());

        // Remove a null node to make sure we're ok
        networkManager.removeNode(null);
        assertEquals(1, networkManager.getNodes().size());
        assertEquals(3, nodeNodeListenerCapture.getAllValues().size());

        // Really check it's gone
        foundNode = networkManager.getNode(1234);
        assertNull(networkManager.getNode(1234));

        node2.setIeeeAddress(new IeeeAddress("123456789ABCDEF0"));
        networkManager.updateNode(node2);
        assertEquals(4, nodeNodeListenerCapture.getAllValues().size());
        networkManager.updateNode(null);
        assertEquals(4, nodeNodeListenerCapture.getAllValues().size());
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

    private ZigBeeNetworkManager mockZigBeeNetworkManager() {
        ZigBeeTransportTransmit mockedTransport = Mockito.mock(ZigBeeTransportTransmit.class);
        mockedNodeListener = Mockito.mock(ZigBeeNetworkNodeListener.class);
        nodeNodeListenerCapture = ArgumentCaptor.forClass(ZigBeeNode.class);
        mockedDeviceListener = Mockito.mock(ZigBeeNetworkDeviceListener.class);
        nodeDeviceListenerCapture = ArgumentCaptor.forClass(ZigBeeDevice.class);

        ZigBeeNetworkManager networkManager = new ZigBeeNetworkManager(mockedTransport);

        networkManager.addNetworkNodeListener(mockedNodeListener);
        networkManager.addNetworkDeviceListener(mockedDeviceListener);

        Mockito.doNothing().when(mockedNodeListener).nodeAdded(nodeNodeListenerCapture.capture());
        Mockito.doNothing().when(mockedNodeListener).nodeUpdated(nodeNodeListenerCapture.capture());
        Mockito.doNothing().when(mockedNodeListener).nodeRemoved(nodeNodeListenerCapture.capture());

        return networkManager;
    }
}

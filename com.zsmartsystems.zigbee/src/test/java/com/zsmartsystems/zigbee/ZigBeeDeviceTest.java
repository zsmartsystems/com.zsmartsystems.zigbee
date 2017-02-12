package com.zsmartsystems.zigbee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.ZigBeeDevice;
import com.zsmartsystems.zigbee.ZigBeeDeviceAddress;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeTransportTransmit;
import com.zsmartsystems.zigbee.zcl.clusters.ZclAlarmsCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclBasicCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclColorControlCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclDoorLockCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclLevelControlCluster;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeDeviceTest {

    @Test
    public void testOutputClusterIds() {
        ZigBeeDevice device = getDevice();
        device.setDeviceAddress(new ZigBeeDeviceAddress(1234, 5));

        List<Integer> clusterIdList = new ArrayList<Integer>();
        clusterIdList.add(ZclAlarmsCluster.CLUSTER_ID);
        clusterIdList.add(ZclBasicCluster.CLUSTER_ID);
        clusterIdList.add(ZclColorControlCluster.CLUSTER_ID);
        clusterIdList.add(ZclDoorLockCluster.CLUSTER_ID);
        clusterIdList.add(ZclLevelControlCluster.CLUSTER_ID);
        device.setOutputClusterIds(clusterIdList);

        assertEquals(5, device.getOutputClusterIds().size());

        assertNotNull(device.getCluster(ZclAlarmsCluster.CLUSTER_ID));
        assertTrue(device.getCluster(ZclAlarmsCluster.CLUSTER_ID).isClient());
        assertFalse(device.getCluster(ZclAlarmsCluster.CLUSTER_ID).isServer());

        assertNotNull(device.getCluster(ZclLevelControlCluster.CLUSTER_ID));
        assertTrue(device.getCluster(ZclLevelControlCluster.CLUSTER_ID).isClient());
        assertFalse(device.getCluster(ZclLevelControlCluster.CLUSTER_ID).isServer());
    }

    @Test
    public void testInputClusterIds() {
        ZigBeeDevice device = getDevice();
        device.setDeviceAddress(new ZigBeeDeviceAddress(1234, 5));

        List<Integer> clusterIdList = new ArrayList<Integer>();
        clusterIdList.add(ZclAlarmsCluster.CLUSTER_ID);
        clusterIdList.add(ZclBasicCluster.CLUSTER_ID);
        clusterIdList.add(ZclColorControlCluster.CLUSTER_ID);
        clusterIdList.add(ZclDoorLockCluster.CLUSTER_ID);
        clusterIdList.add(ZclLevelControlCluster.CLUSTER_ID);
        device.setInputClusterIds(clusterIdList);

        assertEquals(5, device.getInputClusterIds().size());

        assertNotNull(device.getCluster(ZclAlarmsCluster.CLUSTER_ID));
        assertFalse(device.getCluster(ZclAlarmsCluster.CLUSTER_ID).isClient());
        assertTrue(device.getCluster(ZclAlarmsCluster.CLUSTER_ID).isServer());

        assertNotNull(device.getCluster(ZclLevelControlCluster.CLUSTER_ID));
        assertFalse(device.getCluster(ZclLevelControlCluster.CLUSTER_ID).isClient());
        assertTrue(device.getCluster(ZclLevelControlCluster.CLUSTER_ID).isServer());
    }

    @Test
    public void testIeeeAddress() {
        ZigBeeDevice device = getDevice();

        device.setIeeeAddress(new IeeeAddress("1234567890ABCDEF"));
        assertEquals(new IeeeAddress("1234567890ABCDEF"), device.getIeeeAddress());
    }

    @Test
    public void testProfileId() {
        ZigBeeDevice device = getDevice();

        device.setProfileId(0x104);
        assertEquals(0x104, device.getProfileId());
    }

    @Test
    public void testLabel() {
        ZigBeeDevice device = getDevice();

        device.setLabel("Test Label");
        assertEquals("Test Label", device.getLabel());
    }

    @Test
    public void testGetSetNetworkAddress() {
        ZigBeeDevice device = getDevice();

        device.setDeviceAddress(new ZigBeeDeviceAddress(1234, 5));
        assertEquals(new ZigBeeDeviceAddress(1234, 5), device.getDeviceAddress());
    }

    private ZigBeeDevice getDevice() {
        ZigBeeTransportTransmit mockedTransport = Mockito.mock(ZigBeeTransportTransmit.class);
        ZigBeeNetworkManager networkManager = new ZigBeeNetworkManager(mockedTransport);
        return new ZigBeeDevice(networkManager);
    }

}

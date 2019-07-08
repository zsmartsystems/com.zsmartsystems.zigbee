package com.zsmartsystems.zigbee.app.iasclient;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.TestUtilities;
import com.zsmartsystems.zigbee.ZigBeeEndpointAddress;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeStatus;
import com.zsmartsystems.zigbee.zcl.ZclAttribute;
import com.zsmartsystems.zigbee.zcl.clusters.ZclIasZoneCluster;
import com.zsmartsystems.zigbee.zcl.clusters.iaszone.EnrollResponseCodeEnum;
import com.zsmartsystems.zigbee.zcl.clusters.iaszone.ZoneEnrollRequestCommand;
import com.zsmartsystems.zigbee.zcl.clusters.iaszone.ZoneStateEnum;
import com.zsmartsystems.zigbee.zcl.clusters.iaszone.ZoneTypeEnum;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZclIasZoneClientTest {
    static final int TIMEOUT = 5000;

    @Test
    public void testAlreadyEnrolled() {
        int zoneId = 1;
        IeeeAddress ieeeAddress = new IeeeAddress("1234567890ABCDEF");
        ZigBeeNetworkManager networkManager = Mockito.mock(ZigBeeNetworkManager.class);
        ZclIasZoneClient client = new ZclIasZoneClient(networkManager, ieeeAddress, zoneId);

        assertEquals(1, client.getZoneId());

        ZclIasZoneCluster cluster = Mockito.mock(ZclIasZoneCluster.class);
        Mockito.when(cluster.getZigBeeAddress()).thenReturn(new ZigBeeEndpointAddress(1, 1));

        ZclAttribute attributeZoneState = Mockito.mock(ZclAttribute.class);
        Mockito.when(attributeZoneState.readValue(0)).thenReturn(Integer.valueOf(ZoneStateEnum.ENROLLED.getKey()));

        Mockito.when(cluster.getAttribute(ZclIasZoneCluster.ATTR_ZONESTATE)).thenReturn(attributeZoneState);

        assertEquals(ZigBeeStatus.SUCCESS, client.appStartup(cluster));
        Mockito.verify(networkManager, Mockito.timeout(TIMEOUT).times(0)).scheduleTask(ArgumentMatchers.any(),
                ArgumentMatchers.anyLong());
    }

    @Test
    public void testNotEnrolled() {
        int zoneId = 1;
        IeeeAddress ieeeAddress = new IeeeAddress("1234567890ABCDEF");
        ZigBeeNetworkManager networkManager = Mockito.mock(ZigBeeNetworkManager.class);
        ZclIasZoneClient client = new ZclIasZoneClient(networkManager, ieeeAddress, zoneId);

        assertNull(client.getZoneType());
        assertNull(client.getZoneTypeId());
        assertEquals(1, client.getZoneId());

        ZclIasZoneCluster cluster = Mockito.mock(ZclIasZoneCluster.class);
        Mockito.when(cluster.getZigBeeAddress()).thenReturn(new ZigBeeEndpointAddress(1, 1));

        ZclAttribute attributeZoneState = Mockito.mock(ZclAttribute.class);
        Mockito.when(attributeZoneState.readValue(0)).thenReturn(Integer.valueOf(ZoneStateEnum.NOT_ENROLLED.getKey()));

        ZclAttribute attributeIasCieAddress = Mockito.mock(ZclAttribute.class);
        Mockito.when(attributeIasCieAddress.readValue(0)).thenReturn(new IeeeAddress());

        ZclAttribute attributeZoneId = Mockito.mock(ZclAttribute.class);
        Mockito.when(attributeZoneId.readValue(0)).thenReturn(Integer.valueOf(1));

        ZclAttribute attributeZoneType = Mockito.mock(ZclAttribute.class);
        Mockito.when(attributeZoneType.readValue(Long.MAX_VALUE)).thenReturn(ZoneTypeEnum.FIRE_SENSOR.getKey());

        Mockito.when(cluster.getAttribute(ZclIasZoneCluster.ATTR_ZONESTATE)).thenReturn(attributeZoneState);
        Mockito.when(cluster.getAttribute(ZclIasZoneCluster.ATTR_IASCIEADDRESS)).thenReturn(attributeIasCieAddress);
        Mockito.when(cluster.getAttribute(ZclIasZoneCluster.ATTR_ZONEID)).thenReturn(attributeZoneId);
        Mockito.when(cluster.getAttribute(ZclIasZoneCluster.ATTR_ZONETYPE)).thenReturn(attributeZoneType);

        assertEquals(ZigBeeStatus.SUCCESS, client.appStartup(cluster));

        Mockito.verify(attributeIasCieAddress, Mockito.timeout(TIMEOUT).times(1)).writeValue(ieeeAddress);
        Mockito.verify(networkManager, Mockito.timeout(TIMEOUT).times(1)).scheduleTask(ArgumentMatchers.any(),
                ArgumentMatchers.anyLong());

        assertEquals(ZoneTypeEnum.FIRE_SENSOR, client.getZoneType());
        assertEquals(Integer.valueOf(ZoneTypeEnum.FIRE_SENSOR.getKey()), client.getZoneTypeId());
    }

    @Test
    public void testIeeeAlreadySet() {
        int zoneId = 1;
        IeeeAddress ieeeAddress = new IeeeAddress("1234567890ABCDEF");
        ZigBeeNetworkManager networkManager = Mockito.mock(ZigBeeNetworkManager.class);
        ZclIasZoneClient client = new ZclIasZoneClient(networkManager, ieeeAddress, zoneId);

        client.setAutoEnrollDelay(2000);
        assertEquals(1, client.getZoneId());

        ZclIasZoneCluster cluster = Mockito.mock(ZclIasZoneCluster.class);
        Mockito.when(cluster.getZigBeeAddress()).thenReturn(new ZigBeeEndpointAddress(1, 1));

        ZclAttribute attributeZoneState = Mockito.mock(ZclAttribute.class);
        Mockito.when(attributeZoneState.readValue(0)).thenReturn(Integer.valueOf(ZoneStateEnum.NOT_ENROLLED.getKey()));

        ZclAttribute attributeIasCieAddress = Mockito.mock(ZclAttribute.class);
        Mockito.when(attributeIasCieAddress.readValue(0)).thenReturn(new IeeeAddress("1234567890ABCDEF"));

        ZclAttribute attributeZoneId = Mockito.mock(ZclAttribute.class);
        Mockito.when(attributeZoneId.readValue(0)).thenReturn(Integer.valueOf(1));

        ZclAttribute attributeZoneType = Mockito.mock(ZclAttribute.class);
        Mockito.when(attributeZoneType.readValue(Long.MAX_VALUE)).thenReturn(ZoneTypeEnum.FIRE_SENSOR.getKey());

        Mockito.when(cluster.getAttribute(ZclIasZoneCluster.ATTR_ZONESTATE)).thenReturn(attributeZoneState);
        Mockito.when(cluster.getAttribute(ZclIasZoneCluster.ATTR_IASCIEADDRESS)).thenReturn(attributeIasCieAddress);
        Mockito.when(cluster.getAttribute(ZclIasZoneCluster.ATTR_ZONEID)).thenReturn(attributeZoneId);
        Mockito.when(cluster.getAttribute(ZclIasZoneCluster.ATTR_ZONETYPE)).thenReturn(attributeZoneType);

        assertEquals(ZigBeeStatus.SUCCESS, client.appStartup(cluster));

        Mockito.verify(attributeIasCieAddress, Mockito.timeout(TIMEOUT).times(0)).writeValue(ieeeAddress);
        Mockito.verify(networkManager, Mockito.timeout(TIMEOUT).times(1)).scheduleTask(ArgumentMatchers.any(),
                ArgumentMatchers.anyLong());
    }

    @Test
    public void testZoneEnrollRequestCommand() throws Exception {
        int zoneId = 1;
        IeeeAddress ieeeAddress = new IeeeAddress("1234567890ABCDEF");
        ZigBeeNetworkManager networkManager = Mockito.mock(ZigBeeNetworkManager.class);
        ZclIasZoneClient client = new ZclIasZoneClient(networkManager, ieeeAddress, zoneId);

        ZclIasZoneCluster cluster = Mockito.mock(ZclIasZoneCluster.class);

        TestUtilities.setField(ZclIasZoneClient.class, client, "iasZoneCluster", cluster);

        ZoneEnrollRequestCommand command = new ZoneEnrollRequestCommand();
        command.setZoneType(ZoneTypeEnum.FIRE_SENSOR.getKey());
        client.commandReceived(command);

        Mockito.verify(cluster, Mockito.timeout(TIMEOUT).times(1))
                .zoneEnrollResponse(EnrollResponseCodeEnum.SUCCESS.getKey(), 1);
    }
}

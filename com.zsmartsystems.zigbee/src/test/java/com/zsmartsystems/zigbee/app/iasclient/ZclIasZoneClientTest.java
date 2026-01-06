/**
 * Copyright (c) 2016-2026 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.app.iasclient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.TestUtilities;
import com.zsmartsystems.zigbee.ZigBeeEndpointAddress;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeStatus;
import com.zsmartsystems.zigbee.zcl.ZclAttribute;
import com.zsmartsystems.zigbee.zcl.clusters.ZclIasZoneCluster;
import com.zsmartsystems.zigbee.zcl.clusters.iaszone.EnrollResponseCodeEnum;
import com.zsmartsystems.zigbee.zcl.clusters.iaszone.ZoneEnrollRequestCommand;
import com.zsmartsystems.zigbee.zcl.clusters.iaszone.ZoneEnrollResponse;
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
        Mockito.when(attributeZoneState.readValue(Long.MAX_VALUE))
                .thenReturn(Integer.valueOf(ZoneStateEnum.ENROLLED.getKey()));

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
        Mockito.verify(cluster, Mockito.times(1)).addCommandListener(client);

        Mockito.verify(attributeIasCieAddress, Mockito.timeout(TIMEOUT).times(0)).writeValue(ieeeAddress);
        Mockito.verify(networkManager, Mockito.timeout(TIMEOUT).times(1)).scheduleTask(ArgumentMatchers.any(),
                ArgumentMatchers.anyLong());

        client.appShutdown();
        Mockito.verify(cluster, Mockito.times(1)).removeCommandListener(client);
    }

    @Test
    public void testZoneEnrollRequestCommand() throws Exception {
        int zoneId = 1;
        IeeeAddress ieeeAddress = new IeeeAddress("1234567890ABCDEF");
        ZigBeeNetworkManager networkManager = Mockito.mock(ZigBeeNetworkManager.class);
        ZclIasZoneClient client = new ZclIasZoneClient(networkManager, ieeeAddress, zoneId);

        ZclIasZoneCluster cluster = Mockito.mock(ZclIasZoneCluster.class);

        TestUtilities.setField(ZclIasZoneClient.class, client, "iasZoneCluster", cluster);

        ZoneEnrollRequestCommand command = new ZoneEnrollRequestCommand(ZoneTypeEnum.FIRE_SENSOR.getKey(), 12);
        client.commandReceived(command);

        ArgumentCaptor<ZoneEnrollResponse> zoneResposeCapture = ArgumentCaptor.forClass(ZoneEnrollResponse.class);
        Mockito.verify(cluster, Mockito.timeout(TIMEOUT).times(1))
                .sendResponse(eq(command), zoneResposeCapture.capture());
        ZoneEnrollResponse capturedResponse = zoneResposeCapture.getValue();
        assertEquals(EnrollResponseCodeEnum.SUCCESS.getKey(), capturedResponse.getEnrollResponseCode().intValue());
        assertEquals(1, capturedResponse.getZoneId().intValue());
    }

    @Test
    public void testAutoEnrollResponse() {

        int zoneId = 1;
        IeeeAddress ieeeAddress = new IeeeAddress("1234567890ABCDEF");
        ZigBeeNetworkManager networkManager = Mockito.mock(ZigBeeNetworkManager.class);
        ZclIasZoneClient client = new ZclIasZoneClient(networkManager, ieeeAddress, zoneId);
        client.setAutoEnrollDelay(0);

        ZclIasZoneCluster cluster = Mockito.mock(ZclIasZoneCluster.class);
        Mockito.when(cluster.getZigBeeAddress()).thenReturn(new ZigBeeEndpointAddress(1, 1));

        ZclAttribute attributeZoneState = Mockito.mock(ZclAttribute.class);
        Mockito.when(attributeZoneState.readValue(0)).thenReturn(ZoneStateEnum.NOT_ENROLLED.getKey());

        ZclAttribute attributeIasCieAddress = Mockito.mock(ZclAttribute.class);
        Mockito.when(attributeIasCieAddress.readValue(0)).thenReturn(new IeeeAddress("1234567890ABCDEF"));

        ZclAttribute attributeZoneId = Mockito.mock(ZclAttribute.class);
        Mockito.when(attributeZoneId.readValue(0)).thenReturn(zoneId);

        ZclAttribute attributeZoneType = Mockito.mock(ZclAttribute.class);
        Mockito.when(attributeZoneType.readValue(Long.MAX_VALUE)).thenReturn(ZoneTypeEnum.FIRE_SENSOR.getKey());

        Mockito.when(cluster.getAttribute(ZclIasZoneCluster.ATTR_ZONESTATE)).thenReturn(attributeZoneState);
        Mockito.when(cluster.getAttribute(ZclIasZoneCluster.ATTR_IASCIEADDRESS)).thenReturn(attributeIasCieAddress);
        Mockito.when(cluster.getAttribute(ZclIasZoneCluster.ATTR_ZONEID)).thenReturn(attributeZoneId);
        Mockito.when(cluster.getAttribute(ZclIasZoneCluster.ATTR_ZONETYPE)).thenReturn(attributeZoneType);

        Mockito.doAnswer((Answer<Void>) invocation -> {
            Runnable runnable = (Runnable) invocation.getArguments()[0];
            new Thread(runnable).start();
            return null;
        }).when(networkManager).scheduleTask(ArgumentMatchers.any(Runnable.class), ArgumentMatchers.any(long.class));

        client.appStartup(cluster);
        ArgumentCaptor<ZoneEnrollResponse> zoneResponseCapture = ArgumentCaptor.forClass(ZoneEnrollResponse.class);
        Mockito.verify(cluster, Mockito.timeout(TIMEOUT).times(1))
                .sendCommand(zoneResponseCapture.capture());
        ZoneEnrollResponse capturedResponse = zoneResponseCapture.getValue();
        assertTrue(capturedResponse.isDisableDefaultResponse());
        assertEquals(EnrollResponseCodeEnum.SUCCESS.getKey(), capturedResponse.getEnrollResponseCode().intValue());
        assertEquals(zoneId, capturedResponse.getZoneId().intValue());
    }
}

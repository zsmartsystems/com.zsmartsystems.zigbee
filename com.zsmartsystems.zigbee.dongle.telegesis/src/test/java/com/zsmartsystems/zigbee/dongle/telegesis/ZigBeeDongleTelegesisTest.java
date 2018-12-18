/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.telegesis;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import com.zsmartsystems.zigbee.ExtendedPanId;
import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.ZigBeeNodeStatus;
import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisAckMessageEvent;
import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisDeviceJoinedNetworkEvent;
import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisDeviceLeftNetworkEvent;
import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisNackMessageEvent;
import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisNetworkJoinedEvent;
import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisNetworkLeftEvent;
import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisNetworkLostEvent;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportProgressState;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportReceive;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportState;

/**
 * Tests for {@link ZigBeeDongleTelegesis}
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeDongleTelegesisTest {
    private static int TIMEOUT = 5000;

    protected void setField(Class clazz, Object object, String fieldName, Object newValue) throws Exception {
        Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
        field.set(object, newValue);
    }

    @Test
    public void setZigBeeExtendedPanId() {
        ZigBeeDongleTelegesis dongle = new ZigBeeDongleTelegesis(null);

        dongle.setZigBeeExtendedPanId(new ExtendedPanId("123456789abcdef"));
        assertEquals(new ExtendedPanId("123456789abcdef"), dongle.getZigBeeExtendedPanId());
    }

    @Test
    public void setZigBeePanId() {
        ZigBeeDongleTelegesis dongle = new ZigBeeDongleTelegesis(null);

        dongle.setZigBeePanId(0x1234);
        assertEquals(0x1234, dongle.getZigBeePanId());
    }

    @Test
    public void getVersionString() {
        ZigBeeDongleTelegesis dongle = new ZigBeeDongleTelegesis(null);

        assertEquals("Unknown", dongle.getVersionString());
    }

    @Test
    public void getFirmwareVersion() {
        ZigBeeDongleTelegesis dongle = new ZigBeeDongleTelegesis(null);

        assertEquals("", dongle.getFirmwareVersion());
    }

    @Test
    public void testTelegesisAckMessageEvent() throws Exception {
        ZigBeeTransportReceive transport = Mockito.mock(ZigBeeTransportReceive.class);

        ZigBeeDongleTelegesis dongle = new ZigBeeDongleTelegesis(null);
        dongle.setZigBeeTransportReceive(transport);

        setField(ZigBeeDongleTelegesis.class, dongle, "startupComplete", true);

        TelegesisAckMessageEvent response = Mockito.mock(TelegesisAckMessageEvent.class);
        Mockito.when(response.getMessageId()).thenReturn(123);

        dongle.telegesisEventReceived(response);
        Mockito.verify(transport, Mockito.times(0)).receiveCommandStatus(ArgumentMatchers.anyInt(),
                ArgumentMatchers.any(ZigBeeTransportProgressState.class));

        Map<Integer, Integer> messageIdMap = new ConcurrentHashMap<>();
        messageIdMap.put(123, 44);
        setField(ZigBeeDongleTelegesis.class, dongle, "messageIdMap", messageIdMap);

        dongle.telegesisEventReceived(response);
        Mockito.verify(transport, Mockito.timeout(TIMEOUT)).receiveCommandStatus(44,
                ZigBeeTransportProgressState.RX_ACK);
    }

    @Test
    public void testTelegesisNackMessageEvent() throws Exception {
        ZigBeeTransportReceive transport = Mockito.mock(ZigBeeTransportReceive.class);

        ZigBeeDongleTelegesis dongle = new ZigBeeDongleTelegesis(null);
        dongle.setZigBeeTransportReceive(transport);

        setField(ZigBeeDongleTelegesis.class, dongle, "startupComplete", true);

        TelegesisNackMessageEvent response = Mockito.mock(TelegesisNackMessageEvent.class);
        Mockito.when(response.getMessageId()).thenReturn(123);

        dongle.telegesisEventReceived(response);
        Mockito.verify(transport, Mockito.times(0)).receiveCommandStatus(ArgumentMatchers.anyInt(),
                ArgumentMatchers.any(ZigBeeTransportProgressState.class));

        Map<Integer, Integer> messageIdMap = new ConcurrentHashMap<>();
        messageIdMap.put(123, 44);
        setField(ZigBeeDongleTelegesis.class, dongle, "messageIdMap", messageIdMap);

        dongle.telegesisEventReceived(response);
        Mockito.verify(transport, Mockito.timeout(TIMEOUT)).receiveCommandStatus(44,
                ZigBeeTransportProgressState.RX_NAK);
    }

    @Test
    public void testTelegesisDeviceJoinedNetworkEvent() throws Exception {
        ZigBeeTransportReceive transport = Mockito.mock(ZigBeeTransportReceive.class);

        ZigBeeDongleTelegesis dongle = new ZigBeeDongleTelegesis(null);
        dongle.setZigBeeTransportReceive(transport);

        setField(ZigBeeDongleTelegesis.class, dongle, "startupComplete", true);

        TelegesisDeviceJoinedNetworkEvent response = Mockito.mock(TelegesisDeviceJoinedNetworkEvent.class);
        Mockito.when(response.getNetworkAddress()).thenReturn(123);
        Mockito.when(response.getIeeeAddress()).thenReturn(new IeeeAddress("1234567890ABCDEF"));
        dongle.telegesisEventReceived(response);

        Mockito.verify(transport, Mockito.timeout(TIMEOUT)).nodeStatusUpdate(ZigBeeNodeStatus.UNSECURED_JOIN, 123,
                new IeeeAddress("1234567890ABCDEF"));
    }

    @Test
    public void testTelegesisDeviceLeftNetworkEvent() throws Exception {
        ZigBeeTransportReceive transport = Mockito.mock(ZigBeeTransportReceive.class);

        ZigBeeDongleTelegesis dongle = new ZigBeeDongleTelegesis(null);
        dongle.setZigBeeTransportReceive(transport);

        setField(ZigBeeDongleTelegesis.class, dongle, "startupComplete", true);

        TelegesisDeviceLeftNetworkEvent response = Mockito.mock(TelegesisDeviceLeftNetworkEvent.class);
        Mockito.when(response.getNetworkAddress()).thenReturn(123);
        Mockito.when(response.getIeeeAddress()).thenReturn(new IeeeAddress("1234567890ABCDEF"));
        dongle.telegesisEventReceived(response);

        Mockito.verify(transport, Mockito.timeout(TIMEOUT)).nodeStatusUpdate(ZigBeeNodeStatus.DEVICE_LEFT, 123,
                new IeeeAddress("1234567890ABCDEF"));
    }

    @Test
    public void testTelegesisNetworkLeftEvent() throws Exception {
        ZigBeeTransportReceive transport = Mockito.mock(ZigBeeTransportReceive.class);

        ZigBeeDongleTelegesis dongle = new ZigBeeDongleTelegesis(null);
        dongle.setZigBeeTransportReceive(transport);

        setField(ZigBeeDongleTelegesis.class, dongle, "startupComplete", true);

        TelegesisNetworkLeftEvent response = Mockito.mock(TelegesisNetworkLeftEvent.class);
        dongle.telegesisEventReceived(response);

        Mockito.verify(transport, Mockito.timeout(TIMEOUT)).setNetworkState(ZigBeeTransportState.OFFLINE);
    }

    @Test
    public void testTelegesisNetworkLostEvent() throws Exception {
        ZigBeeTransportReceive transport = Mockito.mock(ZigBeeTransportReceive.class);

        ZigBeeDongleTelegesis dongle = new ZigBeeDongleTelegesis(null);
        dongle.setZigBeeTransportReceive(transport);

        setField(ZigBeeDongleTelegesis.class, dongle, "startupComplete", true);

        TelegesisNetworkLostEvent response = Mockito.mock(TelegesisNetworkLostEvent.class);
        dongle.telegesisEventReceived(response);

        Mockito.verify(transport, Mockito.timeout(TIMEOUT)).setNetworkState(ZigBeeTransportState.OFFLINE);
    }

    @Test
    public void testTelegesisNetworkJoinedEvent() throws Exception {
        ZigBeeTransportReceive transport = Mockito.mock(ZigBeeTransportReceive.class);

        ZigBeeDongleTelegesis dongle = new ZigBeeDongleTelegesis(null);
        dongle.setZigBeeTransportReceive(transport);

        setField(ZigBeeDongleTelegesis.class, dongle, "startupComplete", true);

        TelegesisNetworkJoinedEvent response = Mockito.mock(TelegesisNetworkJoinedEvent.class);
        dongle.telegesisEventReceived(response);

        Mockito.verify(transport, Mockito.timeout(TIMEOUT)).setNetworkState(ZigBeeTransportState.ONLINE);
    }
}

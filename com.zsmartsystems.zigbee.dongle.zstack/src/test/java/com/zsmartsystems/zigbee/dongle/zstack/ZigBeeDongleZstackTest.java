/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.zstack;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import com.zsmartsystems.zigbee.ExtendedPanId;
import com.zsmartsystems.zigbee.TestUtilities;
import com.zsmartsystems.zigbee.ZigBeeChannel;
import com.zsmartsystems.zigbee.ZigBeeNwkAddressMode;
import com.zsmartsystems.zigbee.ZigBeeProfileType;
import com.zsmartsystems.zigbee.ZigBeeStatus;
import com.zsmartsystems.zigbee.aps.ZigBeeApsFrame;
import com.zsmartsystems.zigbee.dongle.zstack.api.ZstackFrameRequest;
import com.zsmartsystems.zigbee.dongle.zstack.api.ZstackResponseCode;
import com.zsmartsystems.zigbee.dongle.zstack.api.af.ZstackAfDataConfirmAreq;
import com.zsmartsystems.zigbee.dongle.zstack.api.sys.ZstackZdoState;
import com.zsmartsystems.zigbee.dongle.zstack.api.zdo.ZstackZdoStateChangeIndAreq;
import com.zsmartsystems.zigbee.dongle.zstack.internal.ZstackProtocolHandler;
import com.zsmartsystems.zigbee.dongle.zstack.internal.transaction.ZstackTransaction;
import com.zsmartsystems.zigbee.transport.ZigBeePort;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportProgressState;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportReceive;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportState;

/**
 * Tests for {@link ZigBeeDongleZstack}
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeDongleZstackTest {
    private static int TIMEOUT = 5000;

    @Test
    public void setZigBeeExtendedPanId() {
        ZigBeeDongleZstack dongle = new ZigBeeDongleZstack(null);

        dongle.setZigBeeExtendedPanId(new ExtendedPanId("123456789abcdef"));
    }

    @Test
    public void setZigBeePanId() {
        ZigBeeDongleZstack dongle = new ZigBeeDongleZstack(null);

        dongle.setZigBeePanId(0x1234);
    }

    @Test
    public void testEzspStackStatusHandler() throws Exception {
        ZigBeeTransportReceive transport = Mockito.mock(ZigBeeTransportReceive.class);

        final ZstackNcp ncp = Mockito.mock(ZstackNcp.class);
        Mockito.when(ncp.getNwkAddress()).thenReturn(1243);
        ZigBeeDongleZstack dongle = new ZigBeeDongleZstack(null) {
            @Override
            public ZstackNcp getZstackNcp() {
                return ncp;
            }
        };
        dongle.setZigBeeTransportReceive(transport);

        TestUtilities.setField(ZigBeeDongleZstack.class, dongle, "initialised", true);

        ZstackZdoStateChangeIndAreq response = Mockito.mock(ZstackZdoStateChangeIndAreq.class);
        Mockito.when(response.getState()).thenReturn(ZstackZdoState.DEV_ZB_COORD);
        Mockito.verify(transport, Mockito.timeout(TIMEOUT).times(0)).setTransportState(ZigBeeTransportState.ONLINE);

        response = Mockito.mock(ZstackZdoStateChangeIndAreq.class);
        Mockito.when(response.getState()).thenReturn(ZstackZdoState.DEV_ROUTER);
        dongle.handlePacket(response);
        Mockito.verify(transport, Mockito.timeout(TIMEOUT)).setTransportState(ZigBeeTransportState.ONLINE);
        assertEquals(Integer.valueOf(1243), dongle.getNwkAddress());

        response = Mockito.mock(ZstackZdoStateChangeIndAreq.class);
        Mockito.when(response.getState()).thenReturn(ZstackZdoState.DEV_INIT);
        dongle.handlePacket(response);
        Mockito.verify(transport, Mockito.timeout(TIMEOUT)).setTransportState(ZigBeeTransportState.OFFLINE);
    }

    @Test
    public void setZigBeeChannel() {
        ZigBeeDongleZstack dongle = new ZigBeeDongleZstack(null);

        assertEquals(ZigBeeStatus.INVALID_ARGUMENTS, dongle.setZigBeeChannel(ZigBeeChannel.CHANNEL_03));

        assertEquals(ZigBeeStatus.SUCCESS, dongle.setZigBeeChannel(ZigBeeChannel.CHANNEL_11));

        assertEquals(ZigBeeStatus.SUCCESS, dongle.setZigBeeChannel(ZigBeeChannel.CHANNEL_24));
    }

    @Test
    public void testEzspMessageSentHandler() throws Exception {
        ZigBeeTransportReceive transport = Mockito.mock(ZigBeeTransportReceive.class);

        final ZstackNcp ncp = Mockito.mock(ZstackNcp.class);
        Mockito.when(ncp.getNwkAddress()).thenReturn(1243);
        ZigBeeDongleZstack dongle = new ZigBeeDongleZstack(null);
        dongle.setZigBeeTransportReceive(transport);

        TestUtilities.setField(ZigBeeDongleZstack.class, dongle, "initialised", true);
        TestUtilities.setField(ZigBeeDongleZstack.class, dongle, "executorService",
                Executors.newScheduledThreadPool(1));

        ZstackAfDataConfirmAreq response = Mockito.mock(ZstackAfDataConfirmAreq.class);
        Mockito.when(response.getTransId()).thenReturn(231);
        Mockito.when(response.getStatus()).thenReturn(ZstackResponseCode.SUCCESS);
        dongle.handlePacket(response);
        Mockito.verify(transport, Mockito.timeout(TIMEOUT)).receiveCommandState(231,
                ZigBeeTransportProgressState.RX_ACK);

        response = Mockito.mock(ZstackAfDataConfirmAreq.class);
        Mockito.when(response.getTransId()).thenReturn(231);
        Mockito.when(response.getStatus()).thenReturn(ZstackResponseCode.FAILURE);
        dongle.handlePacket(response);
        Mockito.verify(transport, Mockito.timeout(TIMEOUT)).receiveCommandState(231,
                ZigBeeTransportProgressState.RX_NAK);
    }

    @Test
    public void sendCommandUnicast() throws Exception {
        ZigBeeDongleZstack dongle = new ZigBeeDongleZstack(null);

        ZstackProtocolHandler handler = Mockito.mock(ZstackProtocolHandler.class);
        TestUtilities.setField(ZigBeeDongleZstack.class, dongle, "frameHandler", handler);

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        TestUtilities.setField(ZigBeeDongleZstack.class, dongle, "executorService", executorService);

        ZigBeeApsFrame apsFrame = new ZigBeeApsFrame();
        apsFrame.setCluster(0);
        apsFrame.setProfile(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION.getKey());
        apsFrame.setAddressMode(ZigBeeNwkAddressMode.DEVICE);
        apsFrame.setDestinationAddress(1234);
        apsFrame.setApsCounter(1);
        apsFrame.setRadius(30);
        apsFrame.setPayload(new int[] {});

        dongle.sendCommand(1, apsFrame);
        Mockito.verify(handler, Mockito.timeout(TIMEOUT).times(1))
                .sendTransaction(ArgumentMatchers.any(ZstackTransaction.class));
    }

    @Test
    public void sendCommandBroadcast() throws Exception {
        ZigBeeDongleZstack dongle = new ZigBeeDongleZstack(null);

        ZstackProtocolHandler handler = Mockito.mock(ZstackProtocolHandler.class);
        TestUtilities.setField(ZigBeeDongleZstack.class, dongle, "frameHandler", handler);

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        TestUtilities.setField(ZigBeeDongleZstack.class, dongle, "executorService", executorService);

        ZigBeeApsFrame apsFrame = new ZigBeeApsFrame();
        apsFrame.setCluster(0);
        apsFrame.setProfile(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION.getKey());
        apsFrame.setAddressMode(ZigBeeNwkAddressMode.DEVICE);
        apsFrame.setDestinationAddress(0xfff9);
        apsFrame.setApsCounter(1);
        apsFrame.setRadius(30);
        apsFrame.setPayload(new int[] {});

        dongle.sendCommand(1, apsFrame);
        Mockito.verify(handler, Mockito.timeout(TIMEOUT).times(1))
                .sendTransaction(ArgumentMatchers.any(ZstackTransaction.class));
    }

    @Test
    public void shutdown() throws Exception {
        ZigBeeDongleZstack dongle = new ZigBeeDongleZstack(null);
        TestUtilities.setField(ZigBeeDongleZstack.class, dongle, "serialPort", Mockito.mock(ZigBeePort.class));
        TestUtilities.setField(ZigBeeDongleZstack.class, dongle, "frameHandler",
                Mockito.mock(ZstackProtocolHandler.class));

        dongle.shutdown();
    }

    @Test
    public void scheduleNetworkStatePolling() throws Exception {
        ZigBeeDongleZstack dongle = new ZigBeeDongleZstack(null);

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

        ZstackProtocolHandler frameHandler = Mockito.mock(ZstackProtocolHandler.class);

        TestUtilities.setField(ZigBeeDongleZstack.class, dongle, "pollRate", 1);
        TestUtilities.setField(ZigBeeDongleZstack.class, dongle, "frameHandler", frameHandler);
        TestUtilities.setField(ZigBeeDongleZstack.class, dongle, "executorService", executorService);

        TestUtilities.invokeMethod(ZigBeeDongleZstack.class, dongle, "scheduleNetworkStatePolling");
        Mockito.verify(frameHandler, Mockito.timeout(TIMEOUT).times(0))
                .queueFrame(ArgumentMatchers.any(ZstackFrameRequest.class));

        TestUtilities.setField(ZigBeeDongleZstack.class, dongle, "lastSendCommandTime", Long.MAX_VALUE - 1);
        TestUtilities.setField(ZigBeeDongleZstack.class, dongle, "networkStateUp", true);
        TestUtilities.invokeMethod(ZigBeeDongleZstack.class, dongle, "scheduleNetworkStatePolling");
        Mockito.verify(frameHandler, Mockito.timeout(TIMEOUT).times(0))
                .queueFrame(ArgumentMatchers.any(ZstackFrameRequest.class));

        TestUtilities.setField(ZigBeeDongleZstack.class, dongle, "lastSendCommandTime", 0);
        TestUtilities.invokeMethod(ZigBeeDongleZstack.class, dongle, "scheduleNetworkStatePolling");
        Mockito.verify(frameHandler, Mockito.timeout(TIMEOUT).atLeast(1))
                .queueFrame(ArgumentMatchers.any(ZstackFrameRequest.class));
    }

}

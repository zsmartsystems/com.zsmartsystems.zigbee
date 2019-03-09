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
import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.TestUtilities;
import com.zsmartsystems.zigbee.ZigBeeApsFrame;
import com.zsmartsystems.zigbee.ZigBeeChannel;
import com.zsmartsystems.zigbee.ZigBeeNodeStatus;
import com.zsmartsystems.zigbee.ZigBeeNwkAddressMode;
import com.zsmartsystems.zigbee.ZigBeeProfileType;
import com.zsmartsystems.zigbee.ZigBeeStatus;
import com.zsmartsystems.zigbee.dongle.zstack.api.ZstackFrameRequest;
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
        assertEquals(new ExtendedPanId("123456789abcdef"), dongle.getZigBeeExtendedPanId());
    }

    @Test
    public void setZigBeePanId() {
        ZigBeeDongleZstack dongle = new ZigBeeDongleZstack(null);

        dongle.setZigBeePanId(0x1234);
        assertEquals(0x1234, dongle.getZigBeePanId());
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

        EzspStackStatusHandler response = Mockito.mock(EzspStackStatusHandler.class);
        Mockito.when(response.getStatus()).thenReturn(EmberStatus.EMBER_NETWORK_BUSY);
        Mockito.verify(transport, Mockito.timeout(TIMEOUT).times(0))
                .setNetworkState(ArgumentMatchers.any(ZigBeeTransportState.class));

        response = Mockito.mock(EzspStackStatusHandler.class);
        Mockito.when(response.getStatus()).thenReturn(EmberStatus.EMBER_NETWORK_UP);
        dongle.handlePacket(response);
        Mockito.verify(transport, Mockito.timeout(TIMEOUT)).setNetworkState(ZigBeeTransportState.ONLINE);
        assertEquals(Integer.valueOf(1243), dongle.getNwkAddress());

        response = Mockito.mock(EzspStackStatusHandler.class);
        Mockito.when(response.getStatus()).thenReturn(EmberStatus.EMBER_NETWORK_DOWN);
        dongle.handlePacket(response);
        Mockito.verify(transport, Mockito.timeout(TIMEOUT)).setNetworkState(ZigBeeTransportState.OFFLINE);
    }

    @Test
    public void testEzspChildJoinHandler() throws Exception {
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

        EzspChildJoinHandler response = Mockito.mock(EzspChildJoinHandler.class);
        Mockito.when(response.getChildId()).thenReturn(123);
        Mockito.when(response.getChildEui64()).thenReturn(new IeeeAddress("1234567890ABCDEF"));
        dongle.handlePacket(response);
        Mockito.verify(transport, Mockito.timeout(TIMEOUT).times(1)).nodeStatusUpdate(ZigBeeNodeStatus.UNSECURED_JOIN,
                123, new IeeeAddress("1234567890ABCDEF"));
    }

    @Test
    public void setZigBeeChannel() {
        ZigBeeDongleZstack dongle = new ZigBeeDongleZstack(null);

        assertEquals(ZigBeeStatus.INVALID_ARGUMENTS, dongle.setZigBeeChannel(ZigBeeChannel.CHANNEL_03));

        assertEquals(ZigBeeStatus.SUCCESS, dongle.setZigBeeChannel(ZigBeeChannel.CHANNEL_11));
        assertEquals(ZigBeeChannel.CHANNEL_11, dongle.getZigBeeChannel());

        assertEquals(ZigBeeStatus.SUCCESS, dongle.setZigBeeChannel(ZigBeeChannel.CHANNEL_24));
        assertEquals(ZigBeeChannel.CHANNEL_24, dongle.getZigBeeChannel());
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

        EzspMessageSentHandler response = Mockito.mock(EzspMessageSentHandler.class);
        Mockito.when(response.getMessageTag()).thenReturn(231);
        Mockito.when(response.getStatus()).thenReturn(EmberStatus.EMBER_SUCCESS);
        dongle.handlePacket(response);
        Mockito.verify(transport, Mockito.timeout(TIMEOUT)).receiveCommandState(231,
                ZigBeeTransportProgressState.RX_ACK);

        response = Mockito.mock(EzspMessageSentHandler.class);
        Mockito.when(response.getMessageTag()).thenReturn(231);
        Mockito.when(response.getStatus()).thenReturn(EmberStatus.EMBER_NETWORK_DOWN);
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

        TestUtilities.setField(ZigBeeDongleZstack.class, dongle, "lastSendCommand", Long.MAX_VALUE - 1);
        TestUtilities.setField(ZigBeeDongleZstack.class, dongle, "networkStateUp", true);
        TestUtilities.invokeMethod(ZigBeeDongleZstack.class, dongle, "scheduleNetworkStatePolling");
        Mockito.verify(frameHandler, Mockito.timeout(TIMEOUT).times(0))
                .queueFrame(ArgumentMatchers.any(ZstackFrameRequest.class));

        TestUtilities.setField(ZigBeeDongleZstack.class, dongle, "lastSendCommand", 0);
        TestUtilities.invokeMethod(ZigBeeDongleZstack.class, dongle, "scheduleNetworkStatePolling");
        Mockito.verify(frameHandler, Mockito.timeout(TIMEOUT).atLeast(1))
                .queueFrame(ArgumentMatchers.any(ZstackFrameRequest.class));
    }

}

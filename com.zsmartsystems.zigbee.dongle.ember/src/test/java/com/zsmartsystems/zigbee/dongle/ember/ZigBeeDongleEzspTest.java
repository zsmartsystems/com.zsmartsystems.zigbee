/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
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
import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrame;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspChildJoinHandler;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspMessageSentHandler;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspNetworkStateRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspStackStatusHandler;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspTrustCenterJoinHandler;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberDeviceUpdate;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberStatus;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspDecisionId;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspPolicyId;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspStatus;
import com.zsmartsystems.zigbee.dongle.ember.internal.EzspProtocolHandler;
import com.zsmartsystems.zigbee.dongle.ember.internal.transaction.EzspTransaction;
import com.zsmartsystems.zigbee.transport.TransportConfig;
import com.zsmartsystems.zigbee.transport.TransportConfigOption;
import com.zsmartsystems.zigbee.transport.TrustCentreJoinMode;
import com.zsmartsystems.zigbee.transport.ZigBeePort;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportProgressState;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportReceive;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportState;

/**
 * Tests for {@link ZigBeeDongleEzsp}
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeDongleEzspTest {
    private static int TIMEOUT = 5000;

    @Test
    public void setZigBeeExtendedPanId() {
        ZigBeeDongleEzsp dongle = new ZigBeeDongleEzsp(null);

        dongle.setZigBeeExtendedPanId(new ExtendedPanId("123456789abcdef"));
        assertEquals(new ExtendedPanId("123456789abcdef"), dongle.getZigBeeExtendedPanId());
    }

    @Test
    public void setZigBeePanId() {
        ZigBeeDongleEzsp dongle = new ZigBeeDongleEzsp(null);

        dongle.setZigBeePanId(0x1234);
        assertEquals(0x1234, dongle.getZigBeePanId());
    }

    @Test
    public void testEzspVersions() {
        EzspFrame.setEzspVersion(4);
        assertEquals(4, EzspFrame.getEzspVersion());
        assertFalse(EzspFrame.setEzspVersion(3));
        assertEquals(4, EzspFrame.getEzspVersion());
        assertTrue(EzspFrame.setEzspVersion(4));
        assertEquals(4, EzspFrame.getEzspVersion());
        assertTrue(EzspFrame.setEzspVersion(5));
        assertEquals(5, EzspFrame.getEzspVersion());
        assertTrue(EzspFrame.setEzspVersion(7));
        assertEquals(7, EzspFrame.getEzspVersion());
        assertFalse(EzspFrame.setEzspVersion(8));
        assertEquals(7, EzspFrame.getEzspVersion());
        EzspFrame.setEzspVersion(4);
    }

    @Test
    public void setTcJoinMode() {
        ArgumentCaptor<EzspPolicyId> policyId = ArgumentCaptor.forClass(EzspPolicyId.class);
        ArgumentCaptor<EzspDecisionId> decisionId = ArgumentCaptor.forClass(EzspDecisionId.class);

        final EmberNcp ncp = Mockito.mock(EmberNcp.class);
        Mockito.when(ncp.setPolicy(policyId.capture(), decisionId.capture())).thenReturn(EzspStatus.EZSP_SUCCESS);
        ZigBeeDongleEzsp dongle = new ZigBeeDongleEzsp(null) {
            @Override
            public EmberNcp getEmberNcp() {
                return ncp;
            }
        };

        TransportConfig configuration = new TransportConfig();
        configuration.addOption(TransportConfigOption.TRUST_CENTRE_JOIN_MODE, TrustCentreJoinMode.TC_JOIN_DENY);
        dongle.updateTransportConfig(configuration);
        assertEquals(ZigBeeStatus.SUCCESS, configuration.getResult(TransportConfigOption.TRUST_CENTRE_JOIN_MODE));
        assertEquals(policyId.getValue(), EzspPolicyId.EZSP_TRUST_CENTER_POLICY);
        assertEquals(decisionId.getValue(), EzspDecisionId.EZSP_DISALLOW_ALL_JOINS_AND_REJOINS);

        configuration = new TransportConfig();
        configuration.addOption(TransportConfigOption.TRUST_CENTRE_JOIN_MODE, TrustCentreJoinMode.TC_JOIN_INSECURE);
        dongle.updateTransportConfig(configuration);
        assertEquals(ZigBeeStatus.SUCCESS, configuration.getResult(TransportConfigOption.TRUST_CENTRE_JOIN_MODE));
        assertEquals(policyId.getValue(), EzspPolicyId.EZSP_TRUST_CENTER_POLICY);
        assertEquals(decisionId.getValue(), EzspDecisionId.EZSP_ALLOW_JOINS);

        configuration = new TransportConfig();
        configuration.addOption(TransportConfigOption.TRUST_CENTRE_JOIN_MODE, TrustCentreJoinMode.TC_JOIN_SECURE);
        dongle.updateTransportConfig(configuration);
        assertEquals(ZigBeeStatus.SUCCESS, configuration.getResult(TransportConfigOption.TRUST_CENTRE_JOIN_MODE));
        assertEquals(policyId.getValue(), EzspPolicyId.EZSP_TRUST_CENTER_POLICY);
        assertEquals(decisionId.getValue(), EzspDecisionId.EZSP_ALLOW_PRECONFIGURED_KEY_JOINS);

        configuration = new TransportConfig();
        configuration.addOption(TransportConfigOption.TRUST_CENTRE_JOIN_MODE, Integer.valueOf(0));
        dongle.updateTransportConfig(configuration);
        assertEquals(ZigBeeStatus.INVALID_ARGUMENTS,
                configuration.getResult(TransportConfigOption.TRUST_CENTRE_JOIN_MODE));
    }

    @Test
    public void testEzspStackStatusHandler() throws Exception {
        ZigBeeTransportReceive transport = Mockito.mock(ZigBeeTransportReceive.class);

        final EmberNcp ncp = Mockito.mock(EmberNcp.class);
        Mockito.when(ncp.getNwkAddress()).thenReturn(1243);
        ZigBeeDongleEzsp dongle = new ZigBeeDongleEzsp(null) {
            @Override
            public EmberNcp getEmberNcp() {
                return ncp;
            }
        };
        dongle.setZigBeeTransportReceive(transport);

        TestUtilities.setField(ZigBeeDongleEzsp.class, dongle, "initialised", true);

        EzspStackStatusHandler response = Mockito.mock(EzspStackStatusHandler.class);
        Mockito.when(response.getStatus()).thenReturn(EmberStatus.EMBER_NETWORK_BUSY);
        Mockito.verify(transport, Mockito.timeout(TIMEOUT).times(0))
                .setTransportState(ArgumentMatchers.any(ZigBeeTransportState.class));

        response = Mockito.mock(EzspStackStatusHandler.class);
        Mockito.when(response.getStatus()).thenReturn(EmberStatus.EMBER_NETWORK_UP);
        dongle.handlePacket(response);
        Mockito.verify(transport, Mockito.timeout(TIMEOUT)).setTransportState(ZigBeeTransportState.ONLINE);
        assertEquals(Integer.valueOf(1243), dongle.getNwkAddress());

        response = Mockito.mock(EzspStackStatusHandler.class);
        Mockito.when(response.getStatus()).thenReturn(EmberStatus.EMBER_NETWORK_DOWN);
        dongle.handlePacket(response);
        Mockito.verify(transport, Mockito.timeout(TIMEOUT)).setTransportState(ZigBeeTransportState.OFFLINE);
    }

    @Test
    public void testEzspTrustCenterJoinHandler() throws Exception {
        ZigBeeTransportReceive transport = Mockito.mock(ZigBeeTransportReceive.class);

        final EmberNcp ncp = Mockito.mock(EmberNcp.class);
        Mockito.when(ncp.getNwkAddress()).thenReturn(1243);
        ZigBeeDongleEzsp dongle = new ZigBeeDongleEzsp(null) {
            @Override
            public EmberNcp getEmberNcp() {
                return ncp;
            }
        };
        dongle.setZigBeeTransportReceive(transport);

        TestUtilities.setField(ZigBeeDongleEzsp.class, dongle, "initialised", true);

        EzspTrustCenterJoinHandler response = Mockito.mock(EzspTrustCenterJoinHandler.class);
        Mockito.when(response.getStatus()).thenReturn(EmberDeviceUpdate.EMBER_HIGH_SECURITY_UNSECURED_JOIN);
        Mockito.when(response.getNewNodeId()).thenReturn(123);
        Mockito.when(response.getNewNodeEui64()).thenReturn(new IeeeAddress("1234567890ABCDEF"));
        dongle.handlePacket(response);
        Mockito.verify(transport, Mockito.timeout(TIMEOUT).times(1)).nodeStatusUpdate(ZigBeeNodeStatus.UNSECURED_JOIN,
                123, new IeeeAddress("1234567890ABCDEF"));

        Mockito.when(response.getStatus()).thenReturn(EmberDeviceUpdate.EMBER_STANDARD_SECURITY_UNSECURED_JOIN);
        dongle.handlePacket(response);
        Mockito.verify(transport, Mockito.timeout(TIMEOUT).times(2)).nodeStatusUpdate(ZigBeeNodeStatus.UNSECURED_JOIN,
                123, new IeeeAddress("1234567890ABCDEF"));

        Mockito.when(response.getStatus()).thenReturn(EmberDeviceUpdate.EMBER_HIGH_SECURITY_UNSECURED_REJOIN);
        dongle.handlePacket(response);
        Mockito.verify(transport, Mockito.timeout(TIMEOUT).times(1)).nodeStatusUpdate(ZigBeeNodeStatus.UNSECURED_REJOIN,
                123, new IeeeAddress("1234567890ABCDEF"));

        Mockito.when(response.getStatus()).thenReturn(EmberDeviceUpdate.EMBER_STANDARD_SECURITY_UNSECURED_REJOIN);
        dongle.handlePacket(response);
        Mockito.verify(transport, Mockito.timeout(TIMEOUT).times(2)).nodeStatusUpdate(ZigBeeNodeStatus.UNSECURED_REJOIN,
                123, new IeeeAddress("1234567890ABCDEF"));

        Mockito.when(response.getStatus()).thenReturn(EmberDeviceUpdate.EMBER_HIGH_SECURITY_SECURED_REJOIN);
        dongle.handlePacket(response);
        Mockito.verify(transport, Mockito.timeout(TIMEOUT).times(1)).nodeStatusUpdate(ZigBeeNodeStatus.SECURED_REJOIN,
                123, new IeeeAddress("1234567890ABCDEF"));

        Mockito.when(response.getStatus()).thenReturn(EmberDeviceUpdate.EMBER_STANDARD_SECURITY_SECURED_REJOIN);
        dongle.handlePacket(response);
        Mockito.verify(transport, Mockito.timeout(TIMEOUT).times(2)).nodeStatusUpdate(ZigBeeNodeStatus.SECURED_REJOIN,
                123, new IeeeAddress("1234567890ABCDEF"));

        Mockito.when(response.getStatus()).thenReturn(EmberDeviceUpdate.EMBER_DEVICE_LEFT);
        dongle.handlePacket(response);
        Mockito.verify(transport, Mockito.timeout(TIMEOUT).times(1)).nodeStatusUpdate(ZigBeeNodeStatus.DEVICE_LEFT, 123,
                new IeeeAddress("1234567890ABCDEF"));
    }

    @Test
    public void testEzspChildJoinHandler() throws Exception {
        ZigBeeTransportReceive transport = Mockito.mock(ZigBeeTransportReceive.class);

        final EmberNcp ncp = Mockito.mock(EmberNcp.class);
        Mockito.when(ncp.getNwkAddress()).thenReturn(1243);
        ZigBeeDongleEzsp dongle = new ZigBeeDongleEzsp(null) {
            @Override
            public EmberNcp getEmberNcp() {
                return ncp;
            }
        };
        dongle.setZigBeeTransportReceive(transport);

        TestUtilities.setField(ZigBeeDongleEzsp.class, dongle, "initialised", true);

        EzspChildJoinHandler response = Mockito.mock(EzspChildJoinHandler.class);
        Mockito.when(response.getChildId()).thenReturn(123);
        Mockito.when(response.getChildEui64()).thenReturn(new IeeeAddress("1234567890ABCDEF"));
        dongle.handlePacket(response);
        Mockito.verify(transport, Mockito.timeout(TIMEOUT).times(1)).nodeStatusUpdate(ZigBeeNodeStatus.UNSECURED_JOIN,
                123, new IeeeAddress("1234567890ABCDEF"));
    }

    @Test
    public void setZigBeeChannel() {
        ZigBeeDongleEzsp dongle = new ZigBeeDongleEzsp(null);

        assertEquals(ZigBeeStatus.INVALID_ARGUMENTS, dongle.setZigBeeChannel(ZigBeeChannel.CHANNEL_03));

        assertEquals(ZigBeeStatus.SUCCESS, dongle.setZigBeeChannel(ZigBeeChannel.CHANNEL_11));
        assertEquals(ZigBeeChannel.CHANNEL_11, dongle.getZigBeeChannel());

        assertEquals(ZigBeeStatus.SUCCESS, dongle.setZigBeeChannel(ZigBeeChannel.CHANNEL_24));
        assertEquals(ZigBeeChannel.CHANNEL_24, dongle.getZigBeeChannel());
    }

    @Test
    public void testEzspMessageSentHandler() throws Exception {
        ZigBeeTransportReceive transport = Mockito.mock(ZigBeeTransportReceive.class);

        final EmberNcp ncp = Mockito.mock(EmberNcp.class);
        Mockito.when(ncp.getNwkAddress()).thenReturn(1243);
        ZigBeeDongleEzsp dongle = new ZigBeeDongleEzsp(null);
        dongle.setZigBeeTransportReceive(transport);

        TestUtilities.setField(ZigBeeDongleEzsp.class, dongle, "initialised", true);
        TestUtilities.setField(ZigBeeDongleEzsp.class, dongle, "executorService", Executors.newScheduledThreadPool(1));

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
    public void getCounters() throws Exception {
        ZigBeeDongleEzsp dongle = new ZigBeeDongleEzsp(null);

        assertNotNull(dongle.getCounters());
        assertEquals(0, dongle.getCounters().size());

        Map<String, Long> counters = new HashMap<String, Long>();
        counters.put("A", 1L);

        EzspProtocolHandler handler = Mockito.mock(EzspProtocolHandler.class);
        Mockito.when(handler.getCounters()).thenReturn(counters);

        TestUtilities.setField(ZigBeeDongleEzsp.class, dongle, "frameHandler", handler);

        assertNotNull(dongle.getCounters());
        assertEquals(1, dongle.getCounters().size());
        assertEquals(Long.valueOf(1), dongle.getCounters().get("A"));
    }

    @Test
    public void sendCommandUnicast() throws Exception {
        ZigBeeDongleEzsp dongle = new ZigBeeDongleEzsp(null);

        EzspProtocolHandler handler = Mockito.mock(EzspProtocolHandler.class);
        TestUtilities.setField(ZigBeeDongleEzsp.class, dongle, "frameHandler", handler);

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        TestUtilities.setField(ZigBeeDongleEzsp.class, dongle, "executorService", executorService);

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
                .sendEzspTransaction(ArgumentMatchers.any(EzspTransaction.class));
    }

    @Test
    public void sendCommandBroadcast() throws Exception {
        ZigBeeDongleEzsp dongle = new ZigBeeDongleEzsp(null);

        EzspProtocolHandler handler = Mockito.mock(EzspProtocolHandler.class);
        TestUtilities.setField(ZigBeeDongleEzsp.class, dongle, "frameHandler", handler);

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        TestUtilities.setField(ZigBeeDongleEzsp.class, dongle, "executorService", executorService);

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
                .sendEzspTransaction(ArgumentMatchers.any(EzspTransaction.class));
    }

    @Test
    public void getFirmwareVersion() throws Exception {
        ZigBeeDongleEzsp dongle = new ZigBeeDongleEzsp(null);

        assertEquals("", dongle.getFirmwareVersion());
        TestUtilities.setField(ZigBeeDongleEzsp.class, dongle, "versionString", "Stack Version=123.456");
        assertEquals("123.456", dongle.getFirmwareVersion());
    }

    @Test
    public void scheduleNetworkStatePolling() throws Exception {
        ZigBeeDongleEzsp dongle = new ZigBeeDongleEzsp(null);

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

        EzspProtocolHandler frameHandler = Mockito.mock(EzspProtocolHandler.class);

        TestUtilities.setField(ZigBeeDongleEzsp.class, dongle, "pollRate", 1);
        TestUtilities.setField(ZigBeeDongleEzsp.class, dongle, "frameHandler", frameHandler);
        TestUtilities.setField(ZigBeeDongleEzsp.class, dongle, "executorService", executorService);

        TestUtilities.invokeMethod(ZigBeeDongleEzsp.class, dongle, "scheduleNetworkStatePolling");
        Mockito.verify(frameHandler, Mockito.timeout(TIMEOUT).times(0))
                .queueFrame(ArgumentMatchers.any(EzspFrameRequest.class));

        TestUtilities.setField(ZigBeeDongleEzsp.class, dongle, "lastSendCommand", Long.MAX_VALUE - 1);
        TestUtilities.setField(ZigBeeDongleEzsp.class, dongle, "networkStateUp", true);
        TestUtilities.invokeMethod(ZigBeeDongleEzsp.class, dongle, "scheduleNetworkStatePolling");
        Mockito.verify(frameHandler, Mockito.timeout(TIMEOUT).times(0))
                .queueFrame(ArgumentMatchers.any(EzspFrameRequest.class));

        TestUtilities.setField(ZigBeeDongleEzsp.class, dongle, "lastSendCommand", 0);
        TestUtilities.invokeMethod(ZigBeeDongleEzsp.class, dongle, "scheduleNetworkStatePolling");
        Mockito.verify(frameHandler, Mockito.timeout(TIMEOUT).atLeast(1))
                .queueFrame(ArgumentMatchers.any(EzspFrameRequest.class));

        dongle.handlePacket(new EzspNetworkStateRequest());
    }

    @Test
    public void shutdown() throws Exception {
        ZigBeeDongleEzsp dongle = new ZigBeeDongleEzsp(null);
        TestUtilities.setField(ZigBeeDongleEzsp.class, dongle, "serialPort", Mockito.mock(ZigBeePort.class));
        TestUtilities.setField(ZigBeeDongleEzsp.class, dongle, "frameHandler", Mockito.mock(EzspProtocolHandler.class));

        dongle.shutdown();
    }
}

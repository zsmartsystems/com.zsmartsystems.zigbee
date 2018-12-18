/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
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

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import com.zsmartsystems.zigbee.ExtendedPanId;
import com.zsmartsystems.zigbee.ZigBeeStatus;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrame;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspMessageSentHandler;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspSendBroadcastResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspSendUnicastResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspStackStatusHandler;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberStatus;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspDecisionId;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspPolicyId;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspStatus;
import com.zsmartsystems.zigbee.dongle.ember.internal.EzspProtocolHandler;
import com.zsmartsystems.zigbee.transport.TransportConfig;
import com.zsmartsystems.zigbee.transport.TransportConfigOption;
import com.zsmartsystems.zigbee.transport.TrustCentreJoinMode;
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

        setField(ZigBeeDongleEzsp.class, dongle, "initialised", true);

        EzspStackStatusHandler response = Mockito.mock(EzspStackStatusHandler.class);
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
    public void testEzspSendUnicastResponse() throws Exception {
        ZigBeeTransportReceive transport = Mockito.mock(ZigBeeTransportReceive.class);

        final EmberNcp ncp = Mockito.mock(EmberNcp.class);
        Mockito.when(ncp.getNwkAddress()).thenReturn(1243);
        ZigBeeDongleEzsp dongle = new ZigBeeDongleEzsp(null);
        dongle.setZigBeeTransportReceive(transport);

        setField(ZigBeeDongleEzsp.class, dongle, "initialised", true);

        EzspSendUnicastResponse response = Mockito.mock(EzspSendUnicastResponse.class);
        Mockito.when(response.getSequence()).thenReturn(123);
        Mockito.when(response.getStatus()).thenReturn(EmberStatus.EMBER_SUCCESS);
        dongle.handlePacket(response);

        response = Mockito.mock(EzspSendUnicastResponse.class);
        Mockito.when(response.getSequence()).thenReturn(123);
        Mockito.when(response.getStatus()).thenReturn(EmberStatus.EMBER_NETWORK_DOWN);
        dongle.handlePacket(response);

        Mockito.verify(transport, Mockito.timeout(TIMEOUT)).receiveCommandStatus(123,
                ZigBeeTransportProgressState.TX_NAK);
    }

    @Test
    public void testEzspSendBroadcastResponse() throws Exception {
        ZigBeeTransportReceive transport = Mockito.mock(ZigBeeTransportReceive.class);

        final EmberNcp ncp = Mockito.mock(EmberNcp.class);
        Mockito.when(ncp.getNwkAddress()).thenReturn(1243);
        ZigBeeDongleEzsp dongle = new ZigBeeDongleEzsp(null);
        dongle.setZigBeeTransportReceive(transport);

        setField(ZigBeeDongleEzsp.class, dongle, "initialised", true);

        EzspSendBroadcastResponse response = Mockito.mock(EzspSendBroadcastResponse.class);
        Mockito.when(response.getSequence()).thenReturn(123);
        Mockito.when(response.getStatus()).thenReturn(EmberStatus.EMBER_SUCCESS);
        dongle.handlePacket(response);

        response = Mockito.mock(EzspSendBroadcastResponse.class);
        Mockito.when(response.getSequence()).thenReturn(123);
        Mockito.when(response.getStatus()).thenReturn(EmberStatus.EMBER_NETWORK_DOWN);
        dongle.handlePacket(response);

        Mockito.verify(transport, Mockito.timeout(TIMEOUT)).receiveCommandStatus(123,
                ZigBeeTransportProgressState.TX_NAK);
    }

    @Test
    public void testEzspMessageSentHandler() throws Exception {
        ZigBeeTransportReceive transport = Mockito.mock(ZigBeeTransportReceive.class);

        final EmberNcp ncp = Mockito.mock(EmberNcp.class);
        Mockito.when(ncp.getNwkAddress()).thenReturn(1243);
        ZigBeeDongleEzsp dongle = new ZigBeeDongleEzsp(null);
        dongle.setZigBeeTransportReceive(transport);

        setField(ZigBeeDongleEzsp.class, dongle, "initialised", true);

        EzspMessageSentHandler response = Mockito.mock(EzspMessageSentHandler.class);
        Mockito.when(response.getMessageTag()).thenReturn(231);
        Mockito.when(response.getStatus()).thenReturn(EmberStatus.EMBER_SUCCESS);
        dongle.handlePacket(response);
        Mockito.verify(transport, Mockito.timeout(TIMEOUT)).receiveCommandStatus(231,
                ZigBeeTransportProgressState.TX_ACK);

        response = Mockito.mock(EzspMessageSentHandler.class);
        Mockito.when(response.getMessageTag()).thenReturn(231);
        Mockito.when(response.getStatus()).thenReturn(EmberStatus.EMBER_NETWORK_DOWN);
        dongle.handlePacket(response);
        Mockito.verify(transport, Mockito.timeout(TIMEOUT)).receiveCommandStatus(231,
                ZigBeeTransportProgressState.TX_NAK);
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

        setField(ZigBeeDongleEzsp.class, dongle, "frameHandler", handler);

        assertNotNull(dongle.getCounters());
        assertEquals(1, dongle.getCounters().size());
        assertEquals(Long.valueOf(1), dongle.getCounters().get("A"));
    }
}

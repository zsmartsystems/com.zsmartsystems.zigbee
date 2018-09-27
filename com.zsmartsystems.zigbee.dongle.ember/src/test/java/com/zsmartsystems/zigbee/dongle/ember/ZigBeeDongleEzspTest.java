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
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import com.zsmartsystems.zigbee.ExtendedPanId;
import com.zsmartsystems.zigbee.ZigBeeStatus;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrame;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspDecisionId;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspPolicyId;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspStatus;
import com.zsmartsystems.zigbee.transport.TransportConfig;
import com.zsmartsystems.zigbee.transport.TransportConfigOption;
import com.zsmartsystems.zigbee.transport.TrustCentreJoinMode;

/**
 * Tests for {@link ZigBeeDongleEzsp}
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeDongleEzspTest {
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
        assertTrue(EzspFrame.setEzspVersion(6));
        assertEquals(6, EzspFrame.getEzspVersion());
        assertFalse(EzspFrame.setEzspVersion(7));
        assertEquals(6, EzspFrame.getEzspVersion());
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
}

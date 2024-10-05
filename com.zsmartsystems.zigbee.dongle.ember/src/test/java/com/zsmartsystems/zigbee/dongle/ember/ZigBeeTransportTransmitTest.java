/**
 * Copyright (c) 2016-2024 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember;

import org.junit.Before;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.TestUtilities;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspGetNetworkParametersResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspGetParentChildParametersResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspVersionResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberCurrentSecurityState;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberNetworkParameters;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberNetworkStatus;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberStatus;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspDecisionId;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspPolicyId;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspStatus;
import com.zsmartsystems.zigbee.dongle.ember.internal.EzspProtocolHandler;
import com.zsmartsystems.zigbee.transport.ZigBeePort;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportReceive;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportTransmitAbstractTest;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeTransportTransmitTest extends ZigBeeTransportTransmitAbstractTest {

    @Before
    public void initialiseTransport() throws Exception {
        EmberNetworkParameters networkParameters = Mockito.mock(EmberNetworkParameters.class);
        Mockito.when(networkParameters.getRadioTxPower()).thenReturn(Integer.valueOf(0));

        EzspGetNetworkParametersResponse nwkParametersResponse = Mockito.mock(EzspGetNetworkParametersResponse.class);
        Mockito.when(nwkParametersResponse.getParameters()).thenReturn(networkParameters);

        final EmberNcp ncp = Mockito.mock(EmberNcp.class);
        Mockito.when(
                ncp.setPolicy(ArgumentMatchers.any(EzspPolicyId.class), ArgumentMatchers.any(EzspDecisionId.class)))
                .thenReturn(EzspStatus.EZSP_SUCCESS);
        Mockito.when(ncp.getNetworkState()).thenReturn(EmberNetworkStatus.EMBER_JOINED_NETWORK);
        Mockito.when(ncp.getNetworkParameters()).thenReturn(nwkParametersResponse);
        Mockito.when(ncp.getCurrentSecurityState()).thenReturn(new EmberCurrentSecurityState());
        Mockito.when(ncp.getChildParameters()).thenReturn(Mockito.mock(EzspGetParentChildParametersResponse.class));
        Mockito.when(ncp.setRadioPower(ArgumentMatchers.anyInt())).thenReturn(EmberStatus.EMBER_SUCCESS);
        Mockito.when(ncp.getNwkAddress()).thenReturn(Integer.valueOf(0));
        Mockito.when(ncp.getIeeeAddress()).thenReturn(new IeeeAddress("1234567890ABCDEF"));
        final EzspVersionResponse version = Mockito.mock(EzspVersionResponse.class);
        Mockito.when(version.getProtocolVersion()).thenReturn(4);

        Mockito.when(ncp.getVersion()).thenReturn(version);
        Mockito.when(ncp.getNetworkParameters()).thenReturn(Mockito.mock(EzspGetNetworkParametersResponse.class));

        ZigBeePort port = Mockito.mock(ZigBeePort.class);
        Mockito.when(port.open()).thenReturn(Boolean.TRUE);

        ZigBeeDongleEzsp dongle = new ZigBeeDongleEzsp(port, EmberSerialProtocol.NONE) {
            @Override
            public EmberNcp getEmberNcp() {
                return ncp;
            }
        };
        EzspProtocolHandler frameHandler = Mockito.mock(EzspProtocolHandler.class);
        Mockito.when(frameHandler.isAlive()).thenReturn(Boolean.TRUE);
        TestUtilities.setField(ZigBeeDongleEzsp.class, dongle, "frameHandler", frameHandler);

        final ZigBeeTransportReceive receiver = Mockito.mock(ZigBeeTransportReceive.class);

        dongle.setZigBeeTransportReceive(receiver);

        transport = dongle;
    }
}

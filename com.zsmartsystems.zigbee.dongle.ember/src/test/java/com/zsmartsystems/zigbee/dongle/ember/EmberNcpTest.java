/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.ZigBeeChannelMask;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspClearKeyTableRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspClearKeyTableResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspGetEui64Request;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspGetEui64Response;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspGetNetworkParametersRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspGetNetworkParametersResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspGetNodeIdRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspGetNodeIdResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspGetParentChildParametersRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspGetParentChildParametersResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspLeaveNetworkRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspLeaveNetworkResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspNetworkInitRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspNetworkInitResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspNetworkStateRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspNetworkStateResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspReadCountersRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspReadCountersResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspScanCompleteHandler;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspSetRadioPowerRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspSetRadioPowerResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspStartScanRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberStatus;
import com.zsmartsystems.zigbee.dongle.ember.internal.EzspProtocolHandler;
import com.zsmartsystems.zigbee.dongle.ember.internal.transaction.EzspTransaction;

/**
 *
 * @author Chris Jackson
 *
 */
public class EmberNcpTest {
    private ArgumentCaptor<EzspTransaction> ezspTransactionCapture = ArgumentCaptor.forClass(EzspTransaction.class);
    private EzspProtocolHandler handler;

    private EmberNcp getEmberNcp(EzspFrameResponse response) {
        handler = Mockito.mock(EzspProtocolHandler.class);
        EmberNcp ncp = new EmberNcp(handler);

        EzspTransaction transaction = Mockito.mock(EzspTransaction.class);
        Mockito.when(transaction.getResponse()).thenReturn(response);
        Mockito.doAnswer(new Answer<EzspTransaction>() {
            @Override
            public EzspTransaction answer(InvocationOnMock invocation) {
                return transaction;
            }
        }).when(handler).sendEzspTransaction(ArgumentMatchers.any(EzspTransaction.class));

        return ncp;
    }

    @Test
    public void networkInit() {
        EmberNcp ncp = getEmberNcp(Mockito.mock(EzspNetworkInitResponse.class));

        ncp.networkInit();

        Mockito.verify(handler, Mockito.times(1)).sendEzspTransaction(ezspTransactionCapture.capture());

        EzspFrameRequest request = ezspTransactionCapture.getValue().getRequest();
        assertTrue(request instanceof EzspNetworkInitRequest);
    }

    @Test
    public void leaveNetwork() {
        EmberNcp ncp = getEmberNcp(Mockito.mock(EzspLeaveNetworkResponse.class));

        ncp.leaveNetwork();

        Mockito.verify(handler, Mockito.times(1)).sendEzspTransaction(ezspTransactionCapture.capture());

        EzspFrameRequest request = ezspTransactionCapture.getValue().getRequest();
        assertTrue(request instanceof EzspLeaveNetworkRequest);
    }

    @Test
    public void getNetworkParameters() {
        EmberNcp ncp = getEmberNcp(Mockito.mock(EzspGetNetworkParametersResponse.class));

        ncp.getNetworkParameters();

        Mockito.verify(handler, Mockito.times(1)).sendEzspTransaction(ezspTransactionCapture.capture());

        EzspFrameRequest request = ezspTransactionCapture.getValue().getRequest();
        assertTrue(request instanceof EzspGetNetworkParametersRequest);
    }

    @Test
    public void getNetworkState() {
        EmberNcp ncp = getEmberNcp(Mockito.mock(EzspNetworkStateResponse.class));

        ncp.getNetworkState();

        Mockito.verify(handler, Mockito.times(1)).sendEzspTransaction(ezspTransactionCapture.capture());

        EzspFrameRequest request = ezspTransactionCapture.getValue().getRequest();
        assertTrue(request instanceof EzspNetworkStateRequest);
    }

    @Test
    public void getChildParameters() {
        EmberNcp ncp = getEmberNcp(Mockito.mock(EzspGetParentChildParametersResponse.class));

        ncp.getChildParameters();

        Mockito.verify(handler, Mockito.times(1)).sendEzspTransaction(ezspTransactionCapture.capture());

        EzspFrameRequest request = ezspTransactionCapture.getValue().getRequest();
        assertTrue(request instanceof EzspGetParentChildParametersRequest);
    }

    @Test
    public void getCounters() {
        EmberNcp ncp = getEmberNcp(Mockito.mock(EzspReadCountersResponse.class));

        ncp.getCounters();

        Mockito.verify(handler, Mockito.times(1)).sendEzspTransaction(ezspTransactionCapture.capture());

        EzspFrameRequest request = ezspTransactionCapture.getValue().getRequest();
        assertTrue(request instanceof EzspReadCountersRequest);
    }

    @Test
    public void getIeeeAddress() {
        EzspGetEui64Response response = Mockito.mock(EzspGetEui64Response.class);
        Mockito.when(response.getEui64()).thenReturn(new IeeeAddress("1234567890ABCDEF"));
        EmberNcp ncp = getEmberNcp(response);

        ncp.getIeeeAddress();

        Mockito.verify(handler, Mockito.times(1)).sendEzspTransaction(ezspTransactionCapture.capture());

        EzspFrameRequest request = ezspTransactionCapture.getValue().getRequest();
        assertTrue(request instanceof EzspGetEui64Request);
    }

    @Test
    public void getNwkAddress() {
        EmberNcp ncp = getEmberNcp(Mockito.mock(EzspGetNodeIdResponse.class));

        ncp.getNwkAddress();

        Mockito.verify(handler, Mockito.times(1)).sendEzspTransaction(ezspTransactionCapture.capture());

        EzspFrameRequest request = ezspTransactionCapture.getValue().getRequest();
        assertTrue(request instanceof EzspGetNodeIdRequest);
    }

    @Test
    public void setRadioPower() {
        EmberNcp ncp = getEmberNcp(Mockito.mock(EzspSetRadioPowerResponse.class));

        ncp.setRadioPower(6);

        Mockito.verify(handler, Mockito.times(1)).sendEzspTransaction(ezspTransactionCapture.capture());

        EzspFrameRequest request = ezspTransactionCapture.getValue().getRequest();
        assertTrue(request instanceof EzspSetRadioPowerRequest);
        assertEquals(6, ((EzspSetRadioPowerRequest) request).getPower());
    }

    @Test
    public void doActiveScan() {
        EzspScanCompleteHandler scanComplete = Mockito.mock(EzspScanCompleteHandler.class);
        Mockito.when(scanComplete.getStatus()).thenReturn(EmberStatus.EMBER_SUCCESS);
        EmberNcp ncp = getEmberNcp(scanComplete);

        ncp.doActiveScan(ZigBeeChannelMask.CHANNEL_MASK_2GHZ, 123);

        Mockito.verify(handler, Mockito.times(1)).sendEzspTransaction(ezspTransactionCapture.capture());

        EzspFrameRequest request = ezspTransactionCapture.getValue().getRequest();
        assertTrue(request instanceof EzspStartScanRequest);
        assertEquals(ZigBeeChannelMask.CHANNEL_MASK_2GHZ, ((EzspStartScanRequest) request).getChannelMask());
    }

    @Test
    public void doEnergyScan() {
        EzspScanCompleteHandler scanComplete = Mockito.mock(EzspScanCompleteHandler.class);
        Mockito.when(scanComplete.getStatus()).thenReturn(EmberStatus.EMBER_SUCCESS);
        EmberNcp ncp = getEmberNcp(scanComplete);

        ncp.doEnergyScan(ZigBeeChannelMask.CHANNEL_MASK_2GHZ, 123);

        Mockito.verify(handler, Mockito.times(1)).sendEzspTransaction(ezspTransactionCapture.capture());

        EzspFrameRequest request = ezspTransactionCapture.getValue().getRequest();
        assertTrue(request instanceof EzspStartScanRequest);
        assertEquals(ZigBeeChannelMask.CHANNEL_MASK_2GHZ, ((EzspStartScanRequest) request).getChannelMask());
    }

    @Test
    public void clearKeyTable() {
        EmberNcp ncp = getEmberNcp(Mockito.mock(EzspClearKeyTableResponse.class));

        ncp.clearKeyTable();

        Mockito.verify(handler, Mockito.times(1)).sendEzspTransaction(ezspTransactionCapture.capture());

        EzspFrameRequest request = ezspTransactionCapture.getValue().getRequest();
        assertTrue(request instanceof EzspClearKeyTableRequest);
    }

}

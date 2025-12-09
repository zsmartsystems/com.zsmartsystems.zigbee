/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.zstack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.dongle.zstack.api.ZstackFrameRequest;
import com.zsmartsystems.zigbee.dongle.zstack.api.ZstackFrameResponse;
import com.zsmartsystems.zigbee.dongle.zstack.api.sapi.ZstackZbWriteConfigurationSreq;
import com.zsmartsystems.zigbee.dongle.zstack.api.sapi.ZstackZbWriteConfigurationSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.sys.ZstackResetType;
import com.zsmartsystems.zigbee.dongle.zstack.api.sys.ZstackSysPingSreq;
import com.zsmartsystems.zigbee.dongle.zstack.api.sys.ZstackSysPingSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.sys.ZstackSysResetIndAreq;
import com.zsmartsystems.zigbee.dongle.zstack.api.sys.ZstackSysResetReqAcmd;
import com.zsmartsystems.zigbee.dongle.zstack.api.sys.ZstackSystemCapabilities;
import com.zsmartsystems.zigbee.dongle.zstack.api.sys.ZstackZdoState;
import com.zsmartsystems.zigbee.dongle.zstack.api.util.ZstackUtilGetDeviceInfoSreq;
import com.zsmartsystems.zigbee.dongle.zstack.api.util.ZstackUtilGetDeviceInfoSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.internal.ZstackProtocolHandler;
import com.zsmartsystems.zigbee.dongle.zstack.internal.transaction.ZstackTransaction;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZStackNcpTest {
    private ArgumentCaptor<ZstackTransaction> transactionCapture = ArgumentCaptor.forClass(ZstackTransaction.class);
    private ZstackProtocolHandler handler;

    private ZstackNcp getZstackNcp(ZstackFrameResponse response) {
        handler = Mockito.mock(ZstackProtocolHandler.class);
        ZstackNcp ncp = new ZstackNcp(handler);

        ZstackTransaction transaction = Mockito.mock(ZstackTransaction.class);
        Mockito.when(transaction.getResponse()).thenReturn(response);
        Mockito.doAnswer(new Answer<ZstackTransaction>() {
            @Override
            public ZstackTransaction answer(InvocationOnMock invocation) {
                return transaction;
            }
        }).when(handler).sendTransaction(ArgumentMatchers.any(ZstackTransaction.class));

        return ncp;
    }

    @Test
    public void resetNcp() {
        ZstackNcp ncp = getZstackNcp(Mockito.mock(ZstackSysResetIndAreq.class));

        ncp.resetNcp(ZstackResetType.SERIAL_BOOTLOADER);
        Mockito.verify(handler, Mockito.times(1)).sendTransaction(transactionCapture.capture());

        ZstackFrameRequest request = transactionCapture.getValue().getRequest();
        System.out.println(request);
        assertTrue(request instanceof ZstackSysResetReqAcmd);
        assertEquals(ZstackResetType.SERIAL_BOOTLOADER, ((ZstackSysResetReqAcmd) request).getType());

        ncp.resetNcp(ZstackResetType.TARGET_DEVICE);
        Mockito.verify(handler, Mockito.times(2)).sendTransaction(transactionCapture.capture());

        request = transactionCapture.getValue().getRequest();
        System.out.println(request);
        assertTrue(request instanceof ZstackSysResetReqAcmd);
        assertEquals(ZstackResetType.TARGET_DEVICE, ((ZstackSysResetReqAcmd) request).getType());
    }

    @Test
    public void pingNcp() {
        ZstackSysPingSrsp response = Mockito.mock(ZstackSysPingSrsp.class);
        Mockito.when(response.getCapabilities()).thenReturn(0x17D);
        ZstackNcp ncp = getZstackNcp(response);

        Set<ZstackSystemCapabilities> capabilities = ncp.pingNcp();
        System.out.println("Capabilities returned " + capabilities);

        Mockito.verify(handler, Mockito.times(1)).sendTransaction(transactionCapture.capture());

        ZstackFrameRequest request = transactionCapture.getValue().getRequest();
        System.out.println(request);
        assertTrue(request instanceof ZstackSysPingSreq);
        assertEquals(7, capabilities.size());
    }

    @Test
    public void getDeviceInfo() {
        ZstackUtilGetDeviceInfoSrsp response = Mockito.mock(ZstackUtilGetDeviceInfoSrsp.class);
        Mockito.when(response.getDeviceState()).thenReturn(ZstackZdoState.DEV_INIT);
        Mockito.when(response.getIeeeAddress()).thenReturn(new IeeeAddress("1234567890ABCDEF"));
        Mockito.when(response.getShortAddr()).thenReturn(0x1234);
        ZstackNcp ncp = getZstackNcp(response);

        ZstackUtilGetDeviceInfoSrsp deviceInfo = ncp.getDeviceInfo();
        System.out.println("Device info returned " + deviceInfo);

        Mockito.verify(handler, Mockito.times(1)).sendTransaction(transactionCapture.capture());
        assertEquals(0x1234, deviceInfo.getShortAddr());
        assertEquals(new IeeeAddress("1234567890ABCDEF"), deviceInfo.getIeeeAddress());

        ZstackFrameRequest request = transactionCapture.getValue().getRequest();
        System.out.println(request);
        assertTrue(request instanceof ZstackUtilGetDeviceInfoSreq);

        assertEquals(0x1234, ncp.getNwkAddress());
        assertEquals(new IeeeAddress("1234567890ABCDEF"), ncp.getIeeeAddress());
    }

    @Test
    public void setStartupOptions() {
        ZstackNcp ncp = getZstackNcp(Mockito.mock(ZstackZbWriteConfigurationSrsp.class));

        ncp.setStartupOptions(false, false);
        Mockito.verify(handler, Mockito.times(1)).sendTransaction(transactionCapture.capture());
        assertEquals(0, ((ZstackZbWriteConfigurationSreq) transactionCapture.getValue().getRequest()).getValue()[0]);

        ncp.setStartupOptions(true, false);
        Mockito.verify(handler, Mockito.times(2)).sendTransaction(transactionCapture.capture());
        assertEquals(1, ((ZstackZbWriteConfigurationSreq) transactionCapture.getValue().getRequest()).getValue()[0]);

        ncp.setStartupOptions(false, true);
        Mockito.verify(handler, Mockito.times(3)).sendTransaction(transactionCapture.capture());
        assertEquals(2, ((ZstackZbWriteConfigurationSreq) transactionCapture.getValue().getRequest()).getValue()[0]);

        ncp.setStartupOptions(true, true);
        Mockito.verify(handler, Mockito.times(4)).sendTransaction(transactionCapture.capture());
        assertEquals(3, ((ZstackZbWriteConfigurationSreq) transactionCapture.getValue().getRequest()).getValue()[0]);
    }
}

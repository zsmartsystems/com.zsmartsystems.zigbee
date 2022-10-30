/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.zstack;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import com.zsmartsystems.zigbee.dongle.zstack.api.ZstackFrameRequest;
import com.zsmartsystems.zigbee.dongle.zstack.api.sys.ZstackResetType;
import com.zsmartsystems.zigbee.dongle.zstack.api.sys.ZstackSysPingSreq;
import com.zsmartsystems.zigbee.dongle.zstack.api.sys.ZstackSysPingSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.sys.ZstackSysResetIndAreq;
import com.zsmartsystems.zigbee.dongle.zstack.api.sys.ZstackSysResetReqAcmd;
import com.zsmartsystems.zigbee.dongle.zstack.api.sys.ZstackSystemCapabilities;
import com.zsmartsystems.zigbee.dongle.zstack.internal.ZstackProtocolHandler;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZStackNcpTest {
    private final ArgumentCaptor<ZstackFrameRequest> transactionCapture = ArgumentCaptor.forClass(ZstackFrameRequest.class);
    private final ZstackProtocolHandler handler = Mockito.mock(ZstackProtocolHandler.class);

    private final ZstackNcp ncp = new ZstackNcp(handler);

    @Test
    public void resetNcp() throws InterruptedException, ExecutionException {
        final Future<ZstackSysResetIndAreq> future = Mockito.mock(Future.class);

        final ZstackSysResetIndAreq resetEvent = new ZstackSysResetIndAreq(new int[] {0, 0, 1, 0, 0, 0, 0, 0});
        when(future.get())
            .thenReturn(resetEvent);
        when(handler.waitForEvent(Mockito.eq(ZstackSysResetIndAreq.class), Mockito.any()))
            .thenReturn(future);

        ZstackSysResetIndAreq result = ncp.resetNcp(ZstackResetType.SERIAL_BOOTLOADER);

        Mockito.verify(handler).waitForEvent(Mockito.eq(ZstackSysResetIndAreq.class), Mockito.any());
        Mockito.verify(handler).queueFrame(transactionCapture.capture());
        Mockito.verify(future).get();
        Mockito.verifyNoMoreInteractions(handler, future);

        ZstackFrameRequest request = transactionCapture.getValue();
        assertThat(request, is(instanceOf(ZstackSysResetReqAcmd.class)));
        assertThat(((ZstackSysResetReqAcmd) request).getType(), is(ZstackResetType.SERIAL_BOOTLOADER));
        assertThat(result, is(sameInstance(resetEvent)));
    }

    @Test
    public void pingNcp() {
        ZstackSysPingSrsp response = Mockito.mock(ZstackSysPingSrsp.class);
        Mockito.when(response.getCapabilities())
            .thenReturn(0x17D);
        Mockito.when(handler.sendTransaction(transactionCapture.capture(), Mockito.eq(ZstackSysPingSrsp.class)))
            .thenReturn(response);

        Set<ZstackSystemCapabilities> capabilities = ncp.pingNcp();

        Mockito.verify(handler).sendTransaction(transactionCapture.capture(), Mockito.eq(ZstackSysPingSrsp.class));

        ZstackFrameRequest request = transactionCapture.getValue();
        System.out.println(request);
        assertTrue(request instanceof ZstackSysPingSreq);
        assertEquals(7, capabilities.size());
    }
}

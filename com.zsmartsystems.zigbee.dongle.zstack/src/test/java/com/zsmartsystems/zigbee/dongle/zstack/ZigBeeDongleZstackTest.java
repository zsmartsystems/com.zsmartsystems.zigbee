/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.zstack;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.zsmartsystems.zigbee.ExtendedPanId;
import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.TestUtilities;
import com.zsmartsystems.zigbee.ZigBeeChannel;
import com.zsmartsystems.zigbee.ZigBeeChannelMask;
import com.zsmartsystems.zigbee.ZigBeeNwkAddressMode;
import com.zsmartsystems.zigbee.ZigBeeProfileType;
import com.zsmartsystems.zigbee.ZigBeeStatus;
import com.zsmartsystems.zigbee.aps.ZigBeeApsFrame;
import com.zsmartsystems.zigbee.dongle.zstack.api.ZstackResponseCode;
import com.zsmartsystems.zigbee.dongle.zstack.api.sys.ZstackConfigId;
import com.zsmartsystems.zigbee.dongle.zstack.api.sys.ZstackResetType;
import com.zsmartsystems.zigbee.dongle.zstack.api.sys.ZstackSysResetIndAreq;
import com.zsmartsystems.zigbee.dongle.zstack.api.sys.ZstackSysVersionSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.sys.ZstackSystemCapabilities;
import com.zsmartsystems.zigbee.dongle.zstack.api.util.ZstackUtilGetDeviceInfoSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.zdo.ZstackZdoExtNwkInfoSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.zdo.ZstackZdoStateChangeIndAreq;
import com.zsmartsystems.zigbee.dongle.zstack.internal.ZstackProtocolHandler;
import com.zsmartsystems.zigbee.security.ZigBeeKey;
import com.zsmartsystems.zigbee.transport.ZigBeePort;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportReceive;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportState;

/**
 * Tests for {@link ZigBeeDongleZstack}
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeDongleZstackTest {
    private final ZigBeePort port = Mockito.mock(ZigBeePort.class);
    private final ZigBeeTransportReceive transport = Mockito.mock(ZigBeeTransportReceive.class);
    private final ZstackNcp ncp = Mockito.mock(ZstackNcp.class);
    private final ZstackProtocolHandler protocolHandler = Mockito.mock(ZstackProtocolHandler.class);
    private final ScheduledExecutorService executorService = Mockito.mock(ScheduledExecutorService.class);

    private final ZigBeeDongleZstack dongle = new ZigBeeDongleZstack(port);

    @Before
    public void beforeTest() throws Exception {
        TestUtilities.setField(ZigBeeDongleZstack.class, dongle, "protocolHandler", protocolHandler);
        TestUtilities.setField(ZigBeeDongleZstack.class, dongle, "ncp", ncp);
        TestUtilities.setField(ZigBeeDongleZstack.class, dongle, "executorService", executorService);

        dongle.setZigBeeTransportReceive(transport);
    }

    @Test
    public void testInitialize() {
        when(port.open()).thenReturn(true);

        when(ncp.pingNcp()).thenReturn(Collections.singleton(ZstackSystemCapabilities.MT_CAP_AF));

        final ZstackSysVersionSrsp version = new ZstackSysVersionSrsp(new int[] {
            0, 0, 
            1, 2, 3, 4, 5
        });
        when(ncp.getVersion()).thenReturn(version);
        when(ncp.readConfiguration(ZstackConfigId.ZCD_NV_BDBNODEISONANETWORK)).thenReturn(new int[] {0});

        final ZstackUtilGetDeviceInfoSrsp deviceInfo = new ZstackUtilGetDeviceInfoSrsp(new int[] {
            0, 0, 
            0, 
            2, 3, 4, 5, 6, 7, 8, 9, 
            0, 0,
            7,
            0,
            0
        });
        when(ncp.getDeviceInfo()).thenReturn(deviceInfo);

        assertThat(dongle.initialize(), is(ZigBeeStatus.SUCCESS));

        assertThat(dongle.getVersionString(), is("Software=3.4.5 Product=2 Transport=1"));
        assertThat(dongle.getIeeeAddress(), is(new IeeeAddress(new int[] {2, 3, 4, 5, 6, 7, 8, 9})));
        assertThat(dongle.getNwkAddress(), is(nullValue()));
        assertThat(dongle.getTcLinkKey(), is(nullValue()));
        assertThat(dongle.getZigBeeNetworkKey(), is(nullValue()));
        assertThat(dongle.getZigBeePanId(), is(-1));
        assertThat(dongle.getZigBeeExtendedPanId(), is(nullValue()));

        verify(port).open();

        verify(protocolHandler).start(port);

        verify(transport).setTransportState(ZigBeeTransportState.INITIALISING);

        verify(ncp).pingNcp();
        verify(ncp).getVersion();
        verify(ncp).getDeviceInfo();
        verify(ncp).readConfiguration(ZstackConfigId.ZCD_NV_BDBNODEISONANETWORK);

        verifyNoMoreInteractions(port, transport, ncp, protocolHandler, executorService);
    }

    @Test
    public void testInitializeOnANetwork() {
        when(port.open()).thenReturn(true);

        when(ncp.pingNcp()).thenReturn(Collections.singleton(ZstackSystemCapabilities.MT_CAP_AF));

        final ZstackSysVersionSrsp version = new ZstackSysVersionSrsp(new int[] {
            0, 0, 
            1, 2, 3, 4, 5
        });
        when(ncp.getVersion()).thenReturn(version);
        when(ncp.readConfiguration(ZstackConfigId.ZCD_NV_BDBNODEISONANETWORK)).thenReturn(new int[] {1});
        when(ncp.readConfiguration(ZstackConfigId.ZCD_NV_EXTPANID)).thenReturn(new int[] {0, 1, 2, 3, 4, 5, 6, 7});
        when(ncp.readConfiguration(ZstackConfigId.ZCD_NV_PANID)).thenReturn(new int[] {1, 2});
        when(ncp.readConfiguration(ZstackConfigId.ZCD_NV_PRECFGKEY)).thenReturn(new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15});
        when(ncp.readConfiguration(ZstackConfigId.ZCD_NV_CHANLIST)).thenReturn(new int[] {0, 1, 2, 3});
        when(ncp.readConfiguration(ZstackConfigId.ZCD_NV_USE_DEFAULT_TCLK)).thenReturn(new int[] {1});
        when(ncp.readChannelFromNV()).thenReturn(ZigBeeChannel.CHANNEL_17);

        final ZstackUtilGetDeviceInfoSrsp deviceInfo = new ZstackUtilGetDeviceInfoSrsp(new int[] {
            0, 0, 
            0, 
            2, 3, 4, 5, 6, 7, 8, 9, 
            0, 0,
            7,
            0,
            0
        });
        when(ncp.getDeviceInfo()).thenReturn(deviceInfo);

        assertThat(dongle.initialize(), is(ZigBeeStatus.SUCCESS));

        assertThat(dongle.getVersionString(), is("Software=3.4.5 Product=2 Transport=1"));
        assertThat(dongle.getIeeeAddress(), is(new IeeeAddress(new int[] {2, 3, 4, 5, 6, 7, 8, 9})));

        assertThat(dongle.getNwkAddress(), is(nullValue()));
        assertThat(dongle.getTcLinkKey(), is(new ZigBeeKey("5A6967426565416C6C69616E63653039")));
        assertThat(dongle.getZigBeeNetworkKey(), is(new ZigBeeKey("000102030405060708090A0B0C0D0E0F")));
        assertThat(dongle.getZigBeePanId(), is(513));
        assertThat(dongle.getZigBeeExtendedPanId(), is(new ExtendedPanId("0706050403020100")));

        verify(port).open();

        verify(protocolHandler).start(port);

        verify(transport).setTransportState(ZigBeeTransportState.INITIALISING);

        verify(ncp).pingNcp();
        verify(ncp).getVersion();
        verify(ncp).getDeviceInfo();
        verify(ncp).readChannelFromNV();
        verify(ncp).readConfiguration(ZstackConfigId.ZCD_NV_BDBNODEISONANETWORK);
        verify(ncp).readConfiguration(ZstackConfigId.ZCD_NV_EXTPANID);
        verify(ncp).readConfiguration(ZstackConfigId.ZCD_NV_PANID);
        verify(ncp).readConfiguration(ZstackConfigId.ZCD_NV_PRECFGKEY);
        verify(ncp).readConfiguration(ZstackConfigId.ZCD_NV_CHANLIST);
        verify(ncp).readConfiguration(ZstackConfigId.ZCD_NV_USE_DEFAULT_TCLK);

        verifyNoMoreInteractions(port, transport, ncp, protocolHandler, executorService);
    }

    @Test
    public void testStartup() throws InterruptedException, ExecutionException {
        final Future<ZstackZdoStateChangeIndAreq> future = Mockito.mock(Future.class);

        when(protocolHandler.waitForEvent(Mockito.eq(ZstackZdoStateChangeIndAreq.class), Mockito.any()))
            .thenReturn(future);
        
        when(ncp.startupApplication())
            .thenReturn(ZstackResponseCode.SUCCESS);

        final ZstackZdoExtNwkInfoSrsp networkInfo = new ZstackZdoExtNwkInfoSrsp(new int[] {
            0, 0,
            1, 2,
            9,
            3, 4,
            0, 0,
            0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0,
            12
        });
        when(ncp.getNetworkInfo())
            .thenReturn(networkInfo);

        assertThat(dongle.startup(false), is(ZigBeeStatus.SUCCESS));

        verify(ncp, times(2)).readConfiguration(Mockito.any());
        verify(transport).setTransportState(ZigBeeTransportState.ONLINE);
        verify(ncp, times(19)).zdoRegisterCallback(Mockito.anyInt());
        verify(ncp).addEndpoint(1, 0x50, 0x104, new int[] {}, new int[] {});
        verify(ncp).setChannelMask(new ZigBeeChannelMask(ZigBeeChannelMask.CHANNEL_MASK_2GHZ));
        verify(ncp).startupApplication();
        verify(ncp).getNetworkInfo();
        verify(protocolHandler, times(5)).listener(Mockito.any(), Mockito.any());
        verify(protocolHandler).waitForEvent(Mockito.any(), Mockito.any());
        verify(executorService).scheduleWithFixedDelay(Mockito.any(), Mockito.eq(10000L), Mockito.eq(10000L), Mockito.eq(TimeUnit.MILLISECONDS));
        verify(future).get();

        verifyNoMoreInteractions(port, transport, ncp, protocolHandler, future, executorService);
    }

    @Test
    public void testStartupReinitialize() throws InterruptedException, ExecutionException {
        final Future<ZstackZdoStateChangeIndAreq> future = Mockito.mock(Future.class);

        when(protocolHandler.waitForEvent(Mockito.eq(ZstackZdoStateChangeIndAreq.class), Mockito.any()))
            .thenReturn(future);

        when(ncp.writeConfiguration(ZstackConfigId.ZCD_NV_STARTUP_OPTION, new int[] {3}))
            .thenReturn(ZstackResponseCode.SUCCESS);

        final ZstackSysResetIndAreq reset = new ZstackSysResetIndAreq(new int[] {
            0, 0,
            2,
            1, 2, 3, 4, 5
        });
        when(ncp.resetNcp(ZstackResetType.TARGET_DEVICE))
            .thenReturn(reset);
        
        when(ncp.startupApplication())
            .thenReturn(ZstackResponseCode.SUCCESS);

        final ZstackZdoExtNwkInfoSrsp networkInfo = new ZstackZdoExtNwkInfoSrsp(new int[] {
            0, 0,
            1, 2,
            9,
            3, 4,
            0, 0,
            0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0,
            12
        });
        when(ncp.getNetworkInfo())
            .thenReturn(networkInfo);

        assertThat(dongle.startup(true), is(ZigBeeStatus.SUCCESS));

        verify(ncp, times(4)).readConfiguration(Mockito.any());
        verify(ncp, times(3)).writeConfiguration(Mockito.any(), Mockito.any());
        verify(ncp, times(2)).resetNcp(Mockito.any());
        verify(transport).setTransportState(ZigBeeTransportState.ONLINE);
        verify(ncp, times(19)).zdoRegisterCallback(Mockito.anyInt());
        verify(ncp).addEndpoint(1, 0x50, 0x104, new int[] {}, new int[] {});
        verify(ncp).setChannelMask(new ZigBeeChannelMask(ZigBeeChannelMask.CHANNEL_MASK_2GHZ));
        verify(ncp).startupApplication();
        verify(ncp).getNetworkInfo();
        verify(protocolHandler, times(5)).listener(Mockito.any(), Mockito.any());
        verify(protocolHandler).waitForEvent(Mockito.any(), Mockito.any());
        verify(executorService).scheduleWithFixedDelay(Mockito.any(), Mockito.eq(10000L), Mockito.eq(10000L), Mockito.eq(TimeUnit.MILLISECONDS));
        verify(future).get();

        verifyNoMoreInteractions(port, transport, ncp, protocolHandler, future, executorService);
    }

    @Test
    public void setZigBeeExtendedPanId() {
        final ExtendedPanId ePanId = new ExtendedPanId("123456789abcdef");

        assertThat(dongle.setZigBeeExtendedPanId(ePanId), is(ZigBeeStatus.SUCCESS));
        assertThat(dongle.getZigBeeExtendedPanId(), is(ePanId));
    }

    @Test
    public void setZigBeePanId() {
        final int panId = 0x1234;

        assertThat(dongle.setZigBeePanId(panId), is(ZigBeeStatus.SUCCESS));
        assertThat(dongle.getZigBeePanId(), is(panId));
    }

    @Test
    public void setZigBeeChannel() {
        assertThat(dongle.setZigBeeChannel(ZigBeeChannel.CHANNEL_03), is(ZigBeeStatus.INVALID_ARGUMENTS));

        assertThat(dongle.setZigBeeChannel(ZigBeeChannel.CHANNEL_11), is(ZigBeeStatus.SUCCESS));

        assertThat(dongle.setZigBeeChannel(ZigBeeChannel.CHANNEL_24), is(ZigBeeStatus.SUCCESS));
    }

    @Test
    public void sendCommandUnicast() throws Exception {
        ZigBeeApsFrame apsFrame = new ZigBeeApsFrame();
        apsFrame.setCluster(0);
        apsFrame.setProfile(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION.getKey());
        apsFrame.setAddressMode(ZigBeeNwkAddressMode.DEVICE);
        apsFrame.setDestinationAddress(1234);
        apsFrame.setApsCounter(1);
        apsFrame.setRadius(30);
        apsFrame.setPayload(new int[] {});

        dongle.sendCommand(1, apsFrame);

        verify(executorService).execute(Mockito.any());

        verifyNoMoreInteractions(port, transport, ncp, protocolHandler, executorService);
    }

    @Test
    public void shutdown() throws Exception {
        dongle.shutdown();

        verify(protocolHandler).setClosing();
        verify(port).close();
        verify(protocolHandler).close();
        verify(executorService).shutdown();

        verifyNoMoreInteractions(port, transport, ncp, protocolHandler, executorService);
    }
}

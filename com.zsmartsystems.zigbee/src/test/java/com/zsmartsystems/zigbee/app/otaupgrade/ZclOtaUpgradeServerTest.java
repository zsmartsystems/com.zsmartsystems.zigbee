/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.app.otaupgrade;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import org.awaitility.Awaitility;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.ZigBeeCommand;
import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeEndpointAddress;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNode;
import com.zsmartsystems.zigbee.ZigBeeStatus;
import com.zsmartsystems.zigbee.app.otaserver.ZclOtaUpgradeServer;
import com.zsmartsystems.zigbee.app.otaserver.ZigBeeOtaFile;
import com.zsmartsystems.zigbee.app.otaserver.ZigBeeOtaServerStatus;
import com.zsmartsystems.zigbee.app.otaserver.ZigBeeOtaStatusCallback;
import com.zsmartsystems.zigbee.internal.NotificationService;
import com.zsmartsystems.zigbee.zcl.ZclAttribute;
import com.zsmartsystems.zigbee.zcl.clusters.ZclOtaUpgradeCluster;
import com.zsmartsystems.zigbee.zcl.clusters.otaupgrade.QueryNextImageCommand;
import com.zsmartsystems.zigbee.zdo.field.NodeDescriptor;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZclOtaUpgradeServerTest implements ZigBeeOtaStatusCallback {
    private List<ZigBeeOtaServerStatus> otaStatusCapture;

    @Before
    public void resetNotificationService() throws Exception {
        NotificationService.initialize();
    }

    @Test
    public void testNotify() throws Exception {
        ArgumentCaptor<ZigBeeCommand> mockedCommandCaptor = ArgumentCaptor.forClass(ZigBeeCommand.class);

        NodeDescriptor nodeDescriptor = new NodeDescriptor();
        IeeeAddress ieeeAddress = new IeeeAddress("1234567890ABCDEF");
        ZigBeeEndpointAddress networkAddress = new ZigBeeEndpointAddress(1234, 56);
        ZigBeeNetworkManager mockedNetworkManager = Mockito.mock(ZigBeeNetworkManager.class);
        ZigBeeNode node = new ZigBeeNode(mockedNetworkManager, ieeeAddress);
        node.setNetworkAddress(networkAddress.getAddress());
        node.setNodeDescriptor(nodeDescriptor);
        ZigBeeEndpoint endpoint = new ZigBeeEndpoint(node, networkAddress.getEndpoint());
        // device.setIeeeAddress(ieeeAddress);
        List<Integer> outClusters = new ArrayList<Integer>();
        outClusters.add(ZclOtaUpgradeCluster.CLUSTER_ID);
        endpoint.setOutputClusterIds(outClusters);
        ZigBeeOtaStatusCallback mockedCallback = Mockito.mock(ZigBeeOtaStatusCallback.class);
        otaStatusCapture = new ArrayList<ZigBeeOtaServerStatus>();

        Set<ZigBeeEndpoint> devices = new HashSet<ZigBeeEndpoint>();
        devices.add(endpoint);

        Mockito.when(mockedNetworkManager.getNode((IeeeAddress) ArgumentMatchers.anyObject())).thenReturn(node);
        // Mockito.when(mockedNetworkManager.getDevice((ZigBeeAddress) Matchers.anyObject())).thenReturn(endpoint);
        // Mockito.when(mockedNetworkManager.getNodeDevices((IeeeAddress) Matchers.anyObject())).thenReturn(devices);

        // ZigBeeTransportTransmit mockedTransport = Mockito.mock(ZigBeeTransportTransmit.class);
        // ArgumentCaptor<ZigBeeApsFrame> mockedApsFrameListener = ArgumentCaptor.forClass(ZigBeeApsFrame.class);

        // try {
        // Mockito.doNothing().when(mockedTransport).sendCommand(mockedApsFrameListener.capture());
        // } catch (ZigBeeException e) {
        // e.printStackTrace();
        // }

        Mockito.doAnswer(new Answer<Integer>() {
            @Override
            public Integer answer(InvocationOnMock invocation) {
                return 0;
            }
        }).when(mockedNetworkManager).sendCommand(mockedCommandCaptor.capture());

        ZclOtaUpgradeCluster cluster = Mockito.mock(ZclOtaUpgradeCluster.class);

        ZclOtaUpgradeServer server = new ZclOtaUpgradeServer();
        assertEquals(ZigBeeStatus.SUCCESS, server.appStartup(cluster));
        Mockito.verify(cluster, Mockito.times(1)).addCommandListener(server);
        server.addListener(this);
        server.addListener(this);

        ZigBeeOtaFile otaFile = Mockito.mock(ZigBeeOtaFile.class);

        // Set the firmware and send notification
        server.setFirmware(otaFile);
        Mockito.verify(cluster, Mockito.times(1)).imageNotifyCommand(Mockito.any(), Mockito.any(), Mockito.any(),
                Mockito.any(), Mockito.any());

        Awaitility.await().atMost(1000, TimeUnit.MILLISECONDS).until(() -> otaListenerUpdated());

        assertEquals(1, otaStatusCapture.size());
        ZigBeeOtaServerStatus status = otaStatusCapture.get(0);
        assertEquals(ZigBeeOtaServerStatus.OTA_WAITING, status);

        server.removeListener(this);

        server.appShutdown();
        Mockito.verify(cluster, Mockito.times(1)).removeCommandListener(server);

        System.out.println(server.toString());
    }

    @Test
    public void getCurrentFileVersion() {
        ZclAttribute attribute = Mockito.mock(ZclAttribute.class);
        Mockito.when(attribute.readValue(ArgumentMatchers.anyLong())).thenReturn(1234);
        ZclOtaUpgradeCluster cluster = Mockito.mock(ZclOtaUpgradeCluster.class);
        Mockito.when(cluster.getAttribute(ZclOtaUpgradeCluster.ATTR_CURRENTFILEVERSION)).thenReturn(attribute);

        ZclOtaUpgradeServer server = new ZclOtaUpgradeServer();
        server.appStartup(cluster);
        assertEquals(Integer.valueOf(1234), server.getCurrentFileVersion());
    }

    @Test
    public void cancelUpgrade() throws Exception {
        otaStatusCapture = new ArrayList<ZigBeeOtaServerStatus>();

        ZclOtaUpgradeCluster cluster = Mockito.mock(ZclOtaUpgradeCluster.class);
        ZclOtaUpgradeServer server = new ZclOtaUpgradeServer();
        assertEquals(ZigBeeStatus.SUCCESS, server.appStartup(cluster));
        server.addListener(this);
        server.setDataSize(30);
        server.setAutoUpgrade(false);
        server.setAllowExistingFile(true);
        server.setTransferTimeoutPeriod(Integer.MAX_VALUE);

        server.cancelUpgrade();
        await().atMost(1, SECONDS).until(() -> assertEquals(0, otaStatusCapture.size()));

        ZigBeeOtaFile otaFile = Mockito.mock(ZigBeeOtaFile.class);
        Mockito.when(otaFile.getManufacturerCode()).thenReturn(123);
        Mockito.when(otaFile.getImageType()).thenReturn(987);

        server.setFirmware(otaFile);
        await().atMost(1, SECONDS)
                .until(() -> assertTrue(otaStatusCapture.contains(ZigBeeOtaServerStatus.OTA_WAITING)));

        QueryNextImageCommand query = new QueryNextImageCommand();
        query.setApsSecurity(true);
        query.setClusterId(ZclOtaUpgradeCluster.CLUSTER_ID);
        query.setManufacturerCode(123);
        query.setImageType(987);

        server.commandReceived(query);
        await().atMost(1, SECONDS)
                .until(() -> assertTrue(otaStatusCapture.contains(ZigBeeOtaServerStatus.OTA_TRANSFER_IN_PROGRESS)));

        otaStatusCapture.clear();
        server.cancelUpgrade();
        await().atMost(1, SECONDS).until(() -> otaListenerUpdated());

        assertTrue(otaStatusCapture.contains(ZigBeeOtaServerStatus.OTA_CANCELLED));

        assertEquals(ZigBeeOtaServerStatus.OTA_CANCELLED, server.getServerStatus());
    }

    @Test
    public void getClusterId() {
        ZclOtaUpgradeServer server = new ZclOtaUpgradeServer();
        // Use static number for independent check of cluster ID!
        assertEquals(25, server.getClusterId());
    }

    @Override
    public void otaStatusUpdate(ZigBeeOtaServerStatus status, int percent) {
        otaStatusCapture.add(status);
    }

    private Callable<Integer> otaListenerUpdated() {
        return new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return otaStatusCapture.size(); // The condition that must be fulfilled
            }
        };
    }

}

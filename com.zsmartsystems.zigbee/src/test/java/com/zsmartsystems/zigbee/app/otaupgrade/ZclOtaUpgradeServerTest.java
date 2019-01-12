/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.app.otaupgrade;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.awaitility.Awaitility;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.zsmartsystems.zigbee.CommandResult;
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
import com.zsmartsystems.zigbee.transaction.ZigBeeTransactionMatcher;
import com.zsmartsystems.zigbee.zcl.clusters.ZclOtaUpgradeCluster;
import com.zsmartsystems.zigbee.zcl.clusters.otaupgrade.ImageNotifyCommand;
import com.zsmartsystems.zigbee.zcl.clusters.otaupgrade.QueryNextImageCommand;
import com.zsmartsystems.zigbee.zdo.field.NodeDescriptor;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZclOtaUpgradeServerTest implements ZigBeeOtaStatusCallback {
    private List<ZigBeeOtaServerStatus> otaStatusCapture;

    @Test
    public void testNotify() {
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

        Mockito.doAnswer(new Answer<Future<CommandResult>>() {
            @Override
            public Future<CommandResult> answer(InvocationOnMock invocation) {
                return null;
            }
        }).when(mockedNetworkManager).sendTransaction(mockedCommandCaptor.capture(),
                (ZigBeeTransactionMatcher) ArgumentMatchers.anyObject());

        ZclOtaUpgradeCluster cluster = new ZclOtaUpgradeCluster(endpoint);

        ZclOtaUpgradeServer server = new ZclOtaUpgradeServer();
        assertEquals(ZigBeeStatus.SUCCESS, server.appStartup(cluster));
        server.addListener(this);

        ZigBeeOtaFile otaFile = Mockito.mock(ZigBeeOtaFile.class);

        // Set the firmware and send notification
        server.setFirmware(otaFile);

        assertEquals(1, mockedCommandCaptor.getAllValues().size());

        Awaitility.await().atMost(1000, TimeUnit.MILLISECONDS).until(() -> otaListenerUpdated());

        assertEquals(1, otaStatusCapture.size());
        ZigBeeOtaServerStatus status = otaStatusCapture.get(0);
        assertEquals(ZigBeeOtaServerStatus.OTA_WAITING, status);

        ZigBeeCommand command = mockedCommandCaptor.getValue();

        assertEquals(Integer.valueOf(0x19), command.getClusterId());
        assertTrue(command instanceof ImageNotifyCommand);

        ImageNotifyCommand notifyCommand = (ImageNotifyCommand) command;
        assertEquals(new ZigBeeEndpointAddress(1234, 56), notifyCommand.getDestinationAddress());
        assertTrue(notifyCommand.getQueryJitter() >= 1 && notifyCommand.getQueryJitter() <= 100);

        server.removeListener(this);

        System.out.println(server.toString());
    }

    @Test
    public void getCurrentFileVersion() {
        ZclOtaUpgradeCluster cluster = Mockito.mock(ZclOtaUpgradeCluster.class);
        Mockito.when(cluster.getCurrentFileVersion(ArgumentMatchers.anyLong())).thenReturn(1234);

        ZclOtaUpgradeServer server = new ZclOtaUpgradeServer();
        server.appStartup(cluster);
        assertEquals(Integer.valueOf(1234), server.getCurrentFileVersion());
    }

    @Test
    public void cancelUpgrade() {
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
        assertEquals(0, otaStatusCapture.size());

        ZigBeeOtaFile otaFile = Mockito.mock(ZigBeeOtaFile.class);
        Mockito.when(otaFile.getManufacturerCode()).thenReturn(123);
        Mockito.when(otaFile.getImageType()).thenReturn(987);

        server.setFirmware(otaFile);
        assertTrue(otaStatusCapture.contains(ZigBeeOtaServerStatus.OTA_WAITING));

        QueryNextImageCommand query = new QueryNextImageCommand();
        query.setApsSecurity(true);
        query.setClusterId(ZclOtaUpgradeCluster.CLUSTER_ID);
        query.setManufacturerCode(123);
        query.setImageType(987);

        server.commandReceived(query);
        assertTrue(otaStatusCapture.contains(ZigBeeOtaServerStatus.OTA_TRANSFER_IN_PROGRESS));

        otaStatusCapture.clear();
        server.cancelUpgrade();
        Awaitility.await().atMost(1000, TimeUnit.MILLISECONDS).until(() -> otaListenerUpdated());

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

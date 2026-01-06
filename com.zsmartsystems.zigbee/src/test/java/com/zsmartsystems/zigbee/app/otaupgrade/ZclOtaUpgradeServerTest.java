/**
 * Copyright (c) 2016-2026 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.app.otaupgrade;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.timeout;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.awaitility.Awaitility;
import org.junit.jupiter.api.Test;
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
import com.zsmartsystems.zigbee.app.otaserver.ImageUpgradeStatus;
import com.zsmartsystems.zigbee.app.otaserver.ZclOtaUpgradeServer;
import com.zsmartsystems.zigbee.app.otaserver.ZigBeeOtaFile;
import com.zsmartsystems.zigbee.app.otaserver.ZigBeeOtaServerStatus;
import com.zsmartsystems.zigbee.app.otaserver.ZigBeeOtaStatusCallback;
import com.zsmartsystems.zigbee.internal.NotificationService;
import com.zsmartsystems.zigbee.zcl.ZclAttribute;
import com.zsmartsystems.zigbee.zcl.ZclStatus;
import com.zsmartsystems.zigbee.zcl.clusters.ZclOtaUpgradeCluster;
import com.zsmartsystems.zigbee.zcl.clusters.general.ReadAttributesCommand;
import com.zsmartsystems.zigbee.zcl.clusters.general.ReadAttributesResponse;
import com.zsmartsystems.zigbee.zcl.clusters.otaupgrade.ImageNotifyCommand;
import com.zsmartsystems.zigbee.zcl.clusters.otaupgrade.QueryNextImageCommand;
import com.zsmartsystems.zigbee.zcl.clusters.otaupgrade.UpgradeEndCommand;
import com.zsmartsystems.zigbee.zcl.clusters.otaupgrade.UpgradeEndResponse;
import com.zsmartsystems.zigbee.zcl.field.ReadAttributeStatusRecord;
import com.zsmartsystems.zigbee.zdo.field.NodeDescriptor;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZclOtaUpgradeServerTest implements ZigBeeOtaStatusCallback {
    private List<ZigBeeOtaServerStatus> otaStatusCapture;

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

        Mockito.when(mockedNetworkManager.getNode((IeeeAddress) ArgumentMatchers.any())).thenReturn(node);
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
        Mockito.when(cluster.getNotificationService()).thenReturn(new NotificationService());

        ZclOtaUpgradeServer server = new ZclOtaUpgradeServer();
        assertEquals(ZigBeeStatus.SUCCESS, server.appStartup(cluster));
        Mockito.verify(cluster, Mockito.times(1)).addCommandListener(server);
        server.addListener(this);
        server.addListener(this);

        ZigBeeOtaFile otaFile = Mockito.mock(ZigBeeOtaFile.class);

        // Set the firmware and send notification
        server.setFirmware(otaFile);
        Mockito.verify(cluster, Mockito.times(1)).sendCommand(ArgumentMatchers.any(ImageNotifyCommand.class));

        Awaitility.await().atMost(1000, TimeUnit.MILLISECONDS).until(() -> otaStatusCapture.size() > 0);

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
        Mockito.when(cluster.getNotificationService()).thenReturn(new NotificationService());
        ZclOtaUpgradeServer server = new ZclOtaUpgradeServer();
        assertEquals(ZigBeeStatus.SUCCESS, server.appStartup(cluster));
        server.addListener(this);
        server.setDataSize(30);
        server.setAutoUpgrade(false);
        server.setAllowExistingFile(true);
        server.setTransferTimeoutPeriod(Integer.MAX_VALUE);

        server.cancelUpgrade();
        await().atMost(1, SECONDS).until(() -> otaStatusCapture.size() == 0);
        assertEquals(0, otaStatusCapture.size());

        ZigBeeOtaFile otaFile = Mockito.mock(ZigBeeOtaFile.class);
        Mockito.when(otaFile.getManufacturerCode()).thenReturn(123);
        Mockito.when(otaFile.getImageType()).thenReturn(987);

        server.setFirmware(otaFile);
        await().atMost(1, SECONDS)
                .until(() -> otaStatusCapture.contains(ZigBeeOtaServerStatus.OTA_WAITING));
        assertTrue(otaStatusCapture.contains(ZigBeeOtaServerStatus.OTA_WAITING));

        QueryNextImageCommand query = new QueryNextImageCommand(0, 123, 987, 0, 0);
        query.setApsSecurity(true);

        server.commandReceived(query);
        await().atMost(1, SECONDS)
                .until(() -> otaStatusCapture.contains(ZigBeeOtaServerStatus.OTA_TRANSFER_IN_PROGRESS));
        assertTrue(otaStatusCapture.contains(ZigBeeOtaServerStatus.OTA_TRANSFER_IN_PROGRESS));

        otaStatusCapture.clear();
        server.cancelUpgrade();
        await().atMost(1, SECONDS).until(() -> otaStatusCapture.size() > 0);

        assertTrue(otaStatusCapture.contains(ZigBeeOtaServerStatus.OTA_CANCELLED));

        assertEquals(ZigBeeOtaServerStatus.OTA_CANCELLED, server.getServerStatus());
    }

    @Test
    public void queryNextImageCommand() {
        otaStatusCapture = new ArrayList<ZigBeeOtaServerStatus>();

        ZclOtaUpgradeCluster cluster = Mockito.mock(ZclOtaUpgradeCluster.class);
        Mockito.when(cluster.getNotificationService()).thenReturn(new NotificationService());
        ZclOtaUpgradeServer server = new ZclOtaUpgradeServer();
        assertEquals(ZigBeeStatus.SUCCESS, server.appStartup(cluster));

        ZigBeeOtaStatusCallback callback1 = Mockito.mock(ZigBeeOtaStatusCallback.class);
        Mockito.when(callback1.otaIncomingRequest(ArgumentMatchers.any())).thenReturn(null);
        ZigBeeOtaStatusCallback callback2 = Mockito.mock(ZigBeeOtaStatusCallback.class);
        ZigBeeOtaFile file2 = Mockito.mock(ZigBeeOtaFile.class);
        Mockito.when(callback2.otaIncomingRequest(ArgumentMatchers.any()))
                .thenReturn(file2);

        ZigBeeOtaStatusCallback callback3 = Mockito.mock(ZigBeeOtaStatusCallback.class);
        ZigBeeOtaFile file3 = Mockito.mock(ZigBeeOtaFile.class);
        Mockito.when(callback3.otaIncomingRequest(ArgumentMatchers.any()))
                .thenReturn(file3);

        server.addListener(this);
        server.addListener(callback1);
        server.setDataSize(30);
        server.setAutoUpgrade(false);
        server.setAllowExistingFile(true);
        server.setTransferTimeoutPeriod(Integer.MAX_VALUE);

        QueryNextImageCommand query = new QueryNextImageCommand(0, 123, 987, 0, 0);
        query.setApsSecurity(true);
        server.commandReceived(query);
        Mockito.verify(callback1, Mockito.times(1)).otaIncomingRequest(query);

        server.addListener(callback2);

        server.commandReceived(query);
        Mockito.verify(callback1, Mockito.times(2)).otaIncomingRequest(query);
        Mockito.verify(callback2, Mockito.times(1)).otaIncomingRequest(query);

        await().atMost(1, SECONDS)
                .until(() -> otaStatusCapture.contains(ZigBeeOtaServerStatus.OTA_WAITING));
        assertTrue(otaStatusCapture.contains(ZigBeeOtaServerStatus.OTA_WAITING));

        server.addListener(callback2);

        server.commandReceived(query);
        Mockito.verify(callback1, Mockito.times(2)).otaIncomingRequest(query);
        Mockito.verify(callback2, Mockito.times(1)).otaIncomingRequest(query);

        await().atMost(1, SECONDS)
                .until(() -> otaStatusCapture.contains(ZigBeeOtaServerStatus.OTA_WAITING));
        assertTrue(otaStatusCapture.contains(ZigBeeOtaServerStatus.OTA_WAITING));

        // Transfer is now in progress so we shouldn't notify the listener this time
        server.commandReceived(query);
        Mockito.verify(callback1, Mockito.times(2)).otaIncomingRequest(query);
        Mockito.verify(callback2, Mockito.times(1)).otaIncomingRequest(query);

        server.cancelUpgrade();
        server.addListener(callback3);

        server.commandReceived(query);
        Mockito.verify(callback1, Mockito.times(3)).otaIncomingRequest(query);
        Mockito.verify(callback2, Mockito.times(2)).otaIncomingRequest(query);
        Mockito.verify(callback3, Mockito.times(1)).otaIncomingRequest(query);

        await().atMost(1, SECONDS)
                .until(() -> otaStatusCapture.contains(ZigBeeOtaServerStatus.OTA_WAITING));
        assertTrue(otaStatusCapture.contains(ZigBeeOtaServerStatus.OTA_WAITING));
    }

    @Test
    public void getClusterId() {
        ZclOtaUpgradeServer server = new ZclOtaUpgradeServer();
        // Use static number for independent check of cluster ID!
        assertEquals(25, server.getClusterId());
    }

    @Test
    public void completeUpgrade() throws Exception {
        completeUpgrade(null);
    }

    @Test
    public void completeUpgradeWithCommand() throws Exception {
        completeUpgrade(Mockito.mock(UpgradeEndCommand.class));
    }

    private void completeUpgrade(UpgradeEndCommand command) throws Exception {
        ZclOtaUpgradeCluster cluster = Mockito.mock(ZclOtaUpgradeCluster.class);

        // Prepare attribute for upgrade status
        ZclAttribute attributeImageUpgradeStatus = Mockito.mock(ZclAttribute.class);
        Mockito.when(attributeImageUpgradeStatus.readValue(ArgumentMatchers.any(Long.class)))
                .thenReturn(ImageUpgradeStatus.DOWNLOAD_COMPLETE.getId());
        Mockito.when(cluster.getAttribute(ZclOtaUpgradeCluster.ATTR_IMAGEUPGRADESTATUS))
                .thenReturn(attributeImageUpgradeStatus);

        Future<CommandResult> upgradeEndResponseFuture = mockUpgradeEndResponseCommandFuture();
        Mockito.when(cluster.sendCommand(ArgumentMatchers.any(UpgradeEndResponse.class)))
                .thenReturn(upgradeEndResponseFuture);
        Mockito.when(cluster.sendResponse(ArgumentMatchers.eq(command), ArgumentMatchers.any(UpgradeEndResponse.class)))
                .thenReturn(upgradeEndResponseFuture);

        Future<CommandResult> readCurrentVersionCommandFuture = mockReadCurrentVersionCommandFuture();
        Mockito.when(cluster.sendCommand(ArgumentMatchers.any(ReadAttributesCommand.class)))
                .thenReturn(readCurrentVersionCommandFuture);

        // Prepare the otaFile for the server
        ZigBeeOtaFile otaFile = Mockito.mock(ZigBeeOtaFile.class);
        Mockito.when(otaFile.getFileVersion()).thenReturn(42);

        ZclOtaUpgradeServer server = new ZclOtaUpgradeServer();
        server.appStartup(cluster);
        server.setFirmware(otaFile);

        if (command == null) {
            server.completeUpgrade();
            Mockito.verify(cluster, timeout(1000)).sendCommand(ArgumentMatchers.any(UpgradeEndResponse.class));
        } else {
            server.completeUpgrade(command);
            Mockito.verify(cluster, timeout(1000)).sendResponse(ArgumentMatchers.eq(command),
                    ArgumentMatchers.any(UpgradeEndResponse.class));
        }
    }

    private Future<CommandResult> mockUpgradeEndResponseCommandFuture() throws Exception {
        // Prepare response for the future returned by sendCommand
        CommandResult upgradeEndResponseCommandResult = Mockito.mock(CommandResult.class);
        Mockito.when(upgradeEndResponseCommandResult.isSuccess()).thenReturn(Boolean.TRUE);

        // Prepare the future returned by sendCommand
        @SuppressWarnings("unchecked")
        Future<CommandResult> upgradeEndResponseFuture = Mockito.mock(Future.class);
        Mockito.when(upgradeEndResponseFuture.get()).thenReturn(upgradeEndResponseCommandResult);

        return upgradeEndResponseFuture;
    }

    private Future<CommandResult> mockReadCurrentVersionCommandFuture() throws Exception {
        // Prepare response for the future returned by sendCommand
        CommandResult readCurrentVersionCommandResult = Mockito.mock(CommandResult.class);
        Mockito.when(readCurrentVersionCommandResult.isSuccess()).thenReturn(Boolean.TRUE);

        // Prepare the records for the response
        ReadAttributeStatusRecord record = Mockito.mock(ReadAttributeStatusRecord.class);
        Mockito.when(record.getStatus()).thenReturn(ZclStatus.SUCCESS);
        Mockito.when(record.getAttributeValue()).thenReturn(42);
        List<ReadAttributeStatusRecord> records = new ArrayList<>();
        records.add(record);

        ReadAttributesResponse currentVersionResponse = Mockito.mock(ReadAttributesResponse.class);
        Mockito.when(currentVersionResponse.getRecords()).thenReturn(records);
        Mockito.when(readCurrentVersionCommandResult.getResponse()).thenReturn(currentVersionResponse);

        // Prepare the future returned by sendCommand
        @SuppressWarnings("unchecked")
        Future<CommandResult> readCurrentVersionCommandFuture = Mockito.mock(Future.class);
        Mockito.when(readCurrentVersionCommandFuture.get()).thenReturn(readCurrentVersionCommandResult);

        return readCurrentVersionCommandFuture;
    }

    @Override
    public void otaStatusUpdate(ZigBeeOtaServerStatus status, int percent) {
        otaStatusCapture.add(status);
    }

}

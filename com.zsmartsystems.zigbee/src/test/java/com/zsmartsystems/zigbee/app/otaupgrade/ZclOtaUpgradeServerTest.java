/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
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

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Matchers;
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
import com.zsmartsystems.zigbee.ZigBeeTransactionMatcher;
import com.zsmartsystems.zigbee.app.otaserver.ZclOtaUpgradeServer;
import com.zsmartsystems.zigbee.app.otaserver.ZigBeeOtaFile;
import com.zsmartsystems.zigbee.app.otaserver.ZigBeeOtaServerStatus;
import com.zsmartsystems.zigbee.app.otaserver.ZigBeeOtaStatusCallback;
import com.zsmartsystems.zigbee.zcl.clusters.ZclOtaUpgradeCluster;
import com.zsmartsystems.zigbee.zcl.clusters.otaupgrade.ImageNotifyCommand;
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
        ZigBeeEndpoint endpoint = new ZigBeeEndpoint(mockedNetworkManager, node, networkAddress.getEndpoint());
        // device.setIeeeAddress(ieeeAddress);
        List<Integer> outClusters = new ArrayList<Integer>();
        outClusters.add(ZclOtaUpgradeCluster.CLUSTER_ID);
        endpoint.setOutputClusterIds(outClusters);
        ZigBeeOtaStatusCallback mockedCallback = Mockito.mock(ZigBeeOtaStatusCallback.class);
        otaStatusCapture = new ArrayList<ZigBeeOtaServerStatus>();

        Set<ZigBeeEndpoint> devices = new HashSet<ZigBeeEndpoint>();
        devices.add(endpoint);

        Mockito.when(mockedNetworkManager.getNode((IeeeAddress) Matchers.anyObject())).thenReturn(node);
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
        }).when(mockedNetworkManager).unicast(mockedCommandCaptor.capture(),
                (ZigBeeTransactionMatcher) Matchers.anyObject());

        ZclOtaUpgradeCluster cluster = new ZclOtaUpgradeCluster(mockedNetworkManager, endpoint);

        ZclOtaUpgradeServer server = new ZclOtaUpgradeServer();
        assertTrue(server.appStartup(cluster));
        server.addListener(this);

        ZigBeeOtaFile otaFile = Mockito.mock(ZigBeeOtaFile.class);

        // Set the firmware and send notification
        server.setFirmware(otaFile);

        assertEquals(1, mockedCommandCaptor.getAllValues().size());

        org.awaitility.Awaitility.await().until(otaListenerUpdated(), org.hamcrest.Matchers.equalTo(1));

        assertEquals(1, otaStatusCapture.size());
        ZigBeeOtaServerStatus status = otaStatusCapture.get(0);
        assertEquals(ZigBeeOtaServerStatus.OTA_WAITING, status);

        ZigBeeCommand command = mockedCommandCaptor.getValue();

        assertEquals(Integer.valueOf(0x19), command.getClusterId());
        assertTrue(command instanceof ImageNotifyCommand);

        ImageNotifyCommand notifyCommand = (ImageNotifyCommand) command;
        assertEquals(new ZigBeeEndpointAddress(1234, 56), notifyCommand.getDestinationAddress());
        assertTrue(notifyCommand.getQueryJitter() >= 1 && notifyCommand.getQueryJitter() <= 100);
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

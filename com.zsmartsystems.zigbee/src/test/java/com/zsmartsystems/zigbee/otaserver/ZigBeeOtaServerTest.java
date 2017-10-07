/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.otaserver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.ZigBeeCommand;
import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeEndpointAddress;
import com.zsmartsystems.zigbee.ZigBeeException;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNode;
import com.zsmartsystems.zigbee.otaserver.ZigBeeOtaFile;
import com.zsmartsystems.zigbee.otaserver.ZigBeeOtaServer;
import com.zsmartsystems.zigbee.otaserver.ZigBeeOtaStatusCallback;
import com.zsmartsystems.zigbee.zcl.clusters.otaupgrade.ImageNotifyCommand;
import com.zsmartsystems.zigbee.zdo.field.NodeDescriptor;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeOtaServerTest {
    @Test
    public void testNotify() {
        ArgumentCaptor<ZigBeeCommand> mockedCommandCaptor = ArgumentCaptor.forClass(ZigBeeCommand.class);

        NodeDescriptor nodeDescriptor = new NodeDescriptor();
        IeeeAddress ieeeAddress = new IeeeAddress("1234567890ABCDEF");
        ZigBeeEndpointAddress networkAddress = new ZigBeeEndpointAddress(1234, 56);
        ZigBeeNetworkManager mockedNetworkManager = Mockito.mock(ZigBeeNetworkManager.class);
        ZigBeeNode node = new ZigBeeNode(mockedNetworkManager);
        node.setIeeeAddress(ieeeAddress);
        node.setNodeDescriptor(nodeDescriptor);
        ZigBeeEndpoint device = new ZigBeeEndpoint(mockedNetworkManager, node, 56);
        ZigBeeOtaStatusCallback mockedCallback = Mockito.mock(ZigBeeOtaStatusCallback.class);

        Mockito.when(mockedNetworkManager.getNode((IeeeAddress) Matchers.anyObject())).thenReturn(node);
        // Mockito.when(mockedNetworkManager.getDevice((ZigBeeAddress) Matchers.anyObject())).thenReturn(device);

        try {
            Mockito.doAnswer(new Answer<Integer>() {
                @Override
                public Integer answer(InvocationOnMock invocation) {
                    return 0;
                }
            }).when(mockedNetworkManager).sendCommand(mockedCommandCaptor.capture());
        } catch (ZigBeeException e) {
            e.printStackTrace();
        }

        ZigBeeOtaServer server = new ZigBeeOtaServer(mockedNetworkManager, device, mockedCallback);
        ZigBeeOtaFile otaFile = Mockito.mock(ZigBeeOtaFile.class);

        // Set the firmware and send notification
        server.setFirmware(otaFile);

        assertEquals(1, mockedCommandCaptor.getAllValues().size());

        ZigBeeCommand command = mockedCommandCaptor.getValue();

        assertEquals(Integer.valueOf(0x19), command.getClusterId());
        assertTrue(command instanceof ImageNotifyCommand);

        ImageNotifyCommand notifyCommand = (ImageNotifyCommand) command;
        assertEquals(new ZigBeeEndpointAddress(1234, 56), notifyCommand.getDestinationAddress());
        assertTrue(notifyCommand.getQueryJitter() >= 1 && notifyCommand.getQueryJitter() <= 100);
    }

}

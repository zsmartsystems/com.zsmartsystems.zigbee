/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.app.basic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import com.zsmartsystems.zigbee.ZigBeeCommand;
import com.zsmartsystems.zigbee.ZigBeeEndpointAddress;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclStatus;
import com.zsmartsystems.zigbee.zcl.clusters.ZclBasicCluster;
import com.zsmartsystems.zigbee.zcl.clusters.general.DiscoverAttributesCommand;
import com.zsmartsystems.zigbee.zcl.clusters.general.DiscoverAttributesResponse;
import com.zsmartsystems.zigbee.zcl.clusters.general.ReadAttributesCommand;
import com.zsmartsystems.zigbee.zcl.clusters.general.ReadAttributesResponse;
import com.zsmartsystems.zigbee.zcl.field.ReadAttributeStatusRecord;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Tests for the {@link ZclBasicServer}
 *
 * @author Chris Jackson
 */
public class ZclBasicServerTest {

    @Test
    public void test() {
        ArgumentCaptor<ZigBeeCommand> commandListener = ArgumentCaptor.forClass(ZigBeeCommand.class);
        ZigBeeNetworkManager networkManager = Mockito.mock(ZigBeeNetworkManager.class);
        Mockito.when(networkManager.sendCommand(commandListener.capture())).thenReturn(true);

        ZclBasicServer basicServer = new ZclBasicServer(networkManager);

        assertTrue(basicServer.setAttribute(ZclBasicCluster.ATTR_MODELIDENTIFIER, "ModelId"));
        assertTrue(basicServer.setAttribute(ZclBasicCluster.ATTR_MANUFACTURERNAME, "ZSmartSystems"));

        assertFalse(basicServer.setAttribute(65535, 0));

        List<Integer> identifiers = new ArrayList<>();
        identifiers.add(ZclBasicCluster.ATTR_MODELIDENTIFIER);
        identifiers.add(ZclBasicCluster.ATTR_MANUFACTURERNAME);
        identifiers.add(ZclBasicCluster.ATTR_ZCLVERSION);
        identifiers.add(ZclBasicCluster.ATTR_LOCATIONDESCRIPTION);
        identifiers.add(9999);

        ReadAttributesCommand command = new ReadAttributesCommand();
        command.setClusterId(ZclBasicCluster.CLUSTER_ID);
        command.setSourceAddress(new ZigBeeEndpointAddress(1234));
        command.setDestinationAddress(new ZigBeeEndpointAddress(5678));
        command.setCommandDirection(ZclCommandDirection.CLIENT_TO_SERVER);
        command.setTransactionId(55);
        command.setIdentifiers(identifiers);

        basicServer.commandReceived(command);

        assertEquals(1, commandListener.getAllValues().size());
        ReadAttributesResponse response = (ReadAttributesResponse) commandListener.getValue();
        assertEquals(Integer.valueOf(ZclBasicCluster.CLUSTER_ID), response.getClusterId());
        assertEquals(ZclCommandDirection.SERVER_TO_CLIENT, response.getCommandDirection());
        assertEquals(new ZigBeeEndpointAddress(1234), response.getDestinationAddress());
        assertEquals(Integer.valueOf(55), response.getTransactionId());

        List<ReadAttributeStatusRecord> responseIndentifiers = response.getRecords();
        assertEquals(5, responseIndentifiers.size());

        assertEquals(ZclBasicCluster.ATTR_MODELIDENTIFIER, responseIndentifiers.get(0).getAttributeIdentifier());
        assertEquals(ZclStatus.SUCCESS, responseIndentifiers.get(0).getStatus());
        assertEquals(ZclDataType.CHARACTER_STRING, responseIndentifiers.get(0).getAttributeDataType());
        assertEquals("ModelId", responseIndentifiers.get(0).getAttributeValue());

        assertEquals(ZclBasicCluster.ATTR_MANUFACTURERNAME, responseIndentifiers.get(1).getAttributeIdentifier());
        assertEquals(ZclStatus.SUCCESS, responseIndentifiers.get(1).getStatus());
        assertEquals(ZclDataType.CHARACTER_STRING, responseIndentifiers.get(1).getAttributeDataType());
        assertEquals("ZSmartSystems", responseIndentifiers.get(1).getAttributeValue());

        assertEquals(ZclBasicCluster.ATTR_ZCLVERSION, responseIndentifiers.get(2).getAttributeIdentifier());
        assertEquals(ZclStatus.SUCCESS, responseIndentifiers.get(2).getStatus());
        assertEquals(ZclDataType.UNSIGNED_8_BIT_INTEGER, responseIndentifiers.get(2).getAttributeDataType());
        assertEquals(Integer.valueOf(2), responseIndentifiers.get(2).getAttributeValue());

        assertEquals(ZclBasicCluster.ATTR_LOCATIONDESCRIPTION, responseIndentifiers.get(3).getAttributeIdentifier());
        assertEquals(ZclStatus.UNSUPPORTED_ATTRIBUTE, responseIndentifiers.get(3).getStatus());

        assertEquals(9999, responseIndentifiers.get(4).getAttributeIdentifier());
        assertEquals(ZclStatus.UNSUPPORTED_ATTRIBUTE, responseIndentifiers.get(4).getStatus());

        basicServer.commandReceived(Mockito.mock(ZigBeeCommand.class));
        assertEquals(1, commandListener.getAllValues().size());

        ZclCommand zclCommand = Mockito.mock(ReadAttributesCommand.class);
        Mockito.when(zclCommand.getClusterId()).thenReturn(1);
        basicServer.commandReceived(zclCommand);
        assertEquals(1, commandListener.getAllValues().size());

        Mockito.when(zclCommand.getClusterId()).thenReturn(0);
        Mockito.when(zclCommand.getCommandDirection()).thenReturn(ZclCommandDirection.SERVER_TO_CLIENT);
        basicServer.commandReceived(zclCommand);
        assertEquals(1, commandListener.getAllValues().size());

        DiscoverAttributesCommand discoverCommand = new DiscoverAttributesCommand();
        discoverCommand.setClusterId(ZclBasicCluster.CLUSTER_ID);
        discoverCommand.setSourceAddress(new ZigBeeEndpointAddress(1234));
        discoverCommand.setDestinationAddress(new ZigBeeEndpointAddress(5678));
        discoverCommand.setCommandDirection(ZclCommandDirection.CLIENT_TO_SERVER);
        discoverCommand.setTransactionId(55);
        discoverCommand.setStartAttributeIdentifier(0);
        discoverCommand.setMaximumAttributeIdentifiers(10);

        basicServer.commandReceived(discoverCommand);
        assertEquals(2, commandListener.getAllValues().size());
        DiscoverAttributesResponse discoverResponse = (DiscoverAttributesResponse) commandListener.getValue();
        System.out.println(discoverResponse);
        assertEquals(4, discoverResponse.getAttributeInformation().size());
        assertEquals(0, discoverResponse.getAttributeInformation().get(0).getIdentifier());
        assertEquals(ZclDataType.UNSIGNED_8_BIT_INTEGER,
                discoverResponse.getAttributeInformation().get(0).getDataType());

        discoverCommand = new DiscoverAttributesCommand();
        discoverCommand.setClusterId(ZclBasicCluster.CLUSTER_ID);
        discoverCommand.setSourceAddress(new ZigBeeEndpointAddress(1234));
        discoverCommand.setDestinationAddress(new ZigBeeEndpointAddress(5678));
        discoverCommand.setCommandDirection(ZclCommandDirection.CLIENT_TO_SERVER);
        discoverCommand.setTransactionId(55);
        discoverCommand.setStartAttributeIdentifier(3);
        discoverCommand.setMaximumAttributeIdentifiers(2);

        basicServer.commandReceived(discoverCommand);
        assertEquals(3, commandListener.getAllValues().size());
        discoverResponse = (DiscoverAttributesResponse) commandListener.getValue();
        System.out.println(discoverResponse);
        assertEquals(2, discoverResponse.getAttributeInformation().size());
        assertEquals(4, discoverResponse.getAttributeInformation().get(0).getIdentifier());
        assertEquals(ZclDataType.CHARACTER_STRING, discoverResponse.getAttributeInformation().get(0).getDataType());

        basicServer.shutdown();
    }
}

/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.app.time;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import com.zsmartsystems.zigbee.ZigBeeCommand;
import com.zsmartsystems.zigbee.ZigBeeEndpointAddress;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.zcl.clusters.ZclTimeCluster;
import com.zsmartsystems.zigbee.zcl.clusters.general.ReadAttributesCommand;
import com.zsmartsystems.zigbee.zcl.clusters.general.ReadAttributesResponse;
import com.zsmartsystems.zigbee.zcl.field.ReadAttributeStatusRecord;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZclTimeServerTest {

    @Test
    public void test() {
        ArgumentCaptor<ZigBeeCommand> commandListener = ArgumentCaptor.forClass(ZigBeeCommand.class);

        ZigBeeNetworkManager manager = Mockito.mock(ZigBeeNetworkManager.class);
        Mockito.when(manager.sendCommand(commandListener.capture())).thenReturn(true);

        ZclTimeServer server = new ZclTimeServer(manager);

        List<Integer> identifiers = new ArrayList<>();
        identifiers.add(ZclTimeCluster.ATTR_DSTSTART);
        identifiers.add(ZclTimeCluster.ATTR_DSTEND);
        identifiers.add(ZclTimeCluster.ATTR_DSTSHIFT);
        identifiers.add(ZclTimeCluster.ATTR_TIMEZONE);
        identifiers.add(ZclTimeCluster.ATTR_TIMESTATUS);

        ReadAttributesCommand readAttributes = new ReadAttributesCommand();
        readAttributes.setClusterId(ZclTimeCluster.CLUSTER_ID);
        readAttributes.setCommandDirection(ZclCommandDirection.CLIENT_TO_SERVER);
        readAttributes.setSourceAddress(new ZigBeeEndpointAddress(1234));
        readAttributes.setDestinationAddress(new ZigBeeEndpointAddress(0));
        readAttributes.setIdentifiers(identifiers);
        readAttributes.setTransactionId(88);

        server.commandReceived(readAttributes);

        assertEquals(1, commandListener.getAllValues().size());

        ReadAttributesResponse response = (ReadAttributesResponse) commandListener.getValue();

        assertEquals(Integer.valueOf(ZclTimeCluster.CLUSTER_ID), response.getClusterId());
        assertEquals(ZclCommandDirection.SERVER_TO_CLIENT, response.getCommandDirection());
        assertEquals(new ZigBeeEndpointAddress(1234), response.getDestinationAddress());
        assertEquals(new ZigBeeEndpointAddress(0), response.getSourceAddress());
        assertEquals(Integer.valueOf(88), response.getTransactionId());
        assertEquals(5, response.getRecords().size());

        for (ReadAttributeStatusRecord record : response.getRecords()) {
            switch (record.getAttributeIdentifier()) {
                case ZclTimeCluster.ATTR_DSTSTART:
                    break;
                case ZclTimeCluster.ATTR_DSTEND:
                    break;
                case ZclTimeCluster.ATTR_DSTSHIFT:
                    break;
                case ZclTimeCluster.ATTR_TIMEZONE:
                    break;
                case ZclTimeCluster.ATTR_TIMESTATUS:
                    break;
            }
        }
    }
}

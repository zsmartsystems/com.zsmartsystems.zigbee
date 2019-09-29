/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.app.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.zsmartsystems.zigbee.ZigBeeCommand;
import com.zsmartsystems.zigbee.ZigBeeCommandListener;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.zcl.ZclAttribute;
import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclStatus;
import com.zsmartsystems.zigbee.zcl.clusters.ZclBasicCluster;
import com.zsmartsystems.zigbee.zcl.clusters.general.DiscoverAttributesCommand;
import com.zsmartsystems.zigbee.zcl.clusters.general.DiscoverAttributesResponse;
import com.zsmartsystems.zigbee.zcl.clusters.general.ReadAttributesCommand;
import com.zsmartsystems.zigbee.zcl.clusters.general.ReadAttributesResponse;
import com.zsmartsystems.zigbee.zcl.field.AttributeInformation;
import com.zsmartsystems.zigbee.zcl.field.ReadAttributeStatusRecord;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;

/**
 * Client to manage requests for the {@link ZclBasicCluster}
 *
 * @author Chris Jackson
 *
 */
public class ZclBasicServer implements ZigBeeCommandListener {
    private ZigBeeNetworkManager networkManager;

    private final static int DEFAULT_ZCLVERSION = 2;
    private final static int DEFAULT_STACKVERSION = 2;

    private final Map<Integer, ZclAttribute> attributes = new TreeMap<>();

    protected ZclBasicServer(ZigBeeNetworkManager networkManager) {
        ZclBasicCluster cluster = new ZclBasicCluster(null);
        for (ZclAttribute attribute : cluster.getAttributes()) {
            attributes.put(attribute.getId(), attribute);
        }

        setAttribute(ZclBasicCluster.ATTR_STACKVERSION, DEFAULT_STACKVERSION);
        setAttribute(ZclBasicCluster.ATTR_ZCLVERSION, DEFAULT_ZCLVERSION);

        this.networkManager = networkManager;
        networkManager.addCommandListener(this);
    }

    protected void shutdown() {
        networkManager.removeCommandListener(this);
    }

    @Override
    public void commandReceived(ZigBeeCommand command) {
        if (!(command instanceof ZclCommand)) {
            return;
        }

        ZclCommand zclCommand = (ZclCommand) command;
        if (zclCommand.getClusterId() != ZclBasicCluster.CLUSTER_ID
                || zclCommand.getCommandDirection() != ZclCommandDirection.CLIENT_TO_SERVER) {
            return;
        }

        if (command instanceof ReadAttributesCommand) {
            handleReadAttributes((ReadAttributesCommand) command);
            return;
        }

        if (command instanceof DiscoverAttributesCommand) {
            handleDiscoverAttributes((DiscoverAttributesCommand) command);
            return;
        }
    }

    private void handleReadAttributes(ReadAttributesCommand readCommand) {
        List<ReadAttributeStatusRecord> attributeRecords = new ArrayList<>();
        for (int attributeId : readCommand.getIdentifiers()) {
            attributeRecords.add(getAttributeRecord(attributeId));
        }

        ReadAttributesResponse readResponse = new ReadAttributesResponse();
        readResponse.setRecords(attributeRecords);
        readResponse.setDestinationAddress(readCommand.getSourceAddress());
        readResponse.setCommandDirection(ZclCommandDirection.SERVER_TO_CLIENT);
        readResponse.setTransactionId(readCommand.getTransactionId());
        networkManager.sendCommand(readResponse);
    }

    private void handleDiscoverAttributes(DiscoverAttributesCommand discoverCommand) {
        List<AttributeInformation> attributeInformation = new ArrayList<>();
        for (ZclAttribute attribute : attributes.values()) {
            if (attribute.getId() < discoverCommand.getStartAttributeIdentifier()) {
                continue;
            }

            if (attribute.getLastValue() != null) {
                AttributeInformation attributeInfo = new AttributeInformation();
                attributeInfo.setIdentifier(attribute.getId());
                attributeInfo.setDataType(attribute.getDataType());
                attributeInformation.add(attributeInfo);
            }

            if (attributeInformation.size() >= discoverCommand.getMaximumAttributeIdentifiers()) {
                break;
            }
        }

        DiscoverAttributesResponse discoverResponse = new DiscoverAttributesResponse();
        discoverResponse.setAttributeInformation(attributeInformation);
        discoverResponse.setDestinationAddress(discoverCommand.getSourceAddress());
        discoverResponse.setCommandDirection(ZclCommandDirection.SERVER_TO_CLIENT);
        discoverResponse.setTransactionId(discoverCommand.getTransactionId());
        networkManager.sendCommand(discoverResponse);
    }

    /**
     * Sets an attribute value in the basic server.
     *
     * @param attributeId the attribute identifier to set
     * @param attributeValue the value related to the attribute ID
     * @return true if the attribute was set
     */
    public boolean setAttribute(Integer attributeId, Object attributeValue) {
        if (!attributes.containsKey(attributeId)) {
            return false;
        }
        attributes.get(attributeId).updateValue(attributeValue);
        return true;
    }

    private ReadAttributeStatusRecord getAttributeRecord(int attributeId) {
        ReadAttributeStatusRecord record = new ReadAttributeStatusRecord();
        record.setAttributeIdentifier(attributeId);

        if (attributes.containsKey(attributeId) && attributes.get(attributeId) != null
                && attributes.get(attributeId).getLastValue() != null) {
            record.setStatus(ZclStatus.SUCCESS);
            record.setAttributeDataType(attributes.get(attributeId).getDataType());
            record.setAttributeValue(attributes.get(attributeId).getLastValue());
        } else {
            record.setStatus(ZclStatus.UNSUPPORTED_ATTRIBUTE);
        }

        return record;
    }
}

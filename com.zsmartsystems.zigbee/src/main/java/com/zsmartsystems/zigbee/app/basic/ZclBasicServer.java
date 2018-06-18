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
import java.util.concurrent.ConcurrentHashMap;

import com.zsmartsystems.zigbee.ZigBeeCommand;
import com.zsmartsystems.zigbee.ZigBeeCommandListener;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.zcl.ZclAttribute;
import com.zsmartsystems.zigbee.zcl.ZclStatus;
import com.zsmartsystems.zigbee.zcl.clusters.ZclBasicCluster;
import com.zsmartsystems.zigbee.zcl.clusters.general.ReadAttributesCommand;
import com.zsmartsystems.zigbee.zcl.clusters.general.ReadAttributesResponse;
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

    private final Map<Integer, ZclAttribute> attributes = new ConcurrentHashMap<>();

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
        // We are only interested in READ ATTRIBUTE commands
        if (!(command instanceof ReadAttributesCommand)) {
            return;
        }

        ReadAttributesCommand readCommand = (ReadAttributesCommand) command;
        if (readCommand.getClusterId() != ZclBasicCluster.CLUSTER_ID
                && readCommand.getCommandDirection() != ZclCommandDirection.SERVER_TO_CLIENT) {
            return;
        }

        List<ReadAttributeStatusRecord> attributeRecords = new ArrayList<>();
        for (int attributeId : readCommand.getIdentifiers()) {
            attributeRecords.add(getAttributeRecord(attributeId));
        }

        ReadAttributesResponse readResponse = new ReadAttributesResponse();

        readResponse.setRecords(attributeRecords);
        readResponse.setDestinationAddress(readCommand.getSourceAddress());
        readResponse.setCommandDirection(ZclCommandDirection.CLIENT_TO_SERVER);
        readResponse.setTransactionId(command.getTransactionId());
        networkManager.sendCommand(readResponse);
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

        if (attributes.containsKey(attributeId) && attributes.get(attributeId) != null) {
            record.setStatus(ZclStatus.SUCCESS);
            record.setAttributeDataType(attributes.get(attributeId).getDataType());
            record.setAttributeValue(attributes.get(attributeId).getLastValue());
        } else {
            record.setStatus(ZclStatus.UNSUPPORTED_ATTRIBUTE);
        }

        return record;
    }
}

/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.app.time;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclCommandListener;
import com.zsmartsystems.zigbee.zcl.ZclStatus;
import com.zsmartsystems.zigbee.zcl.clusters.ZclTimeCluster;
import com.zsmartsystems.zigbee.zcl.clusters.general.ReadAttributesCommand;
import com.zsmartsystems.zigbee.zcl.clusters.general.ReadAttributesResponse;
import com.zsmartsystems.zigbee.zcl.clusters.time.TimeStatusBitmap;
import com.zsmartsystems.zigbee.zcl.field.ReadAttributeStatusRecord;
import com.zsmartsystems.zigbee.zcl.field.ZigBeeUtcTime;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Local time server. This allows any clients on the network to read time as required.
 *
 * @author Chris Jackson
 *
 */
public class ZclTimeServer implements ZclCommandListener {
    /**
     * The {@link Logger}.
     */
    private final Logger logger = LoggerFactory.getLogger(ZclTimeServer.class);

    /**
     * True if we are a time master server on the network
     */
    private boolean master = true;

    /**
     * True if we are a superceding time server on the network
     */
    private boolean superceding = true;

    private ZigBeeUtcTime dstStart;
    private ZigBeeUtcTime dstEnd;
    private int dstOffset;
    private boolean dstSet = false;

    private final ZigBeeNetworkManager networkManager;

    /**
     * Map holding the last update times for each remote
     */
    private final Map<Integer, ZigBeeUtcTime> lastUpdate = new HashMap<>();

    protected ZclTimeServer(ZigBeeNetworkManager networkManager) {
        this.networkManager = networkManager;
    }

    /**
     * Shuts down the time server and frees any resources
     */
    protected void shutdown() {
    }

    /**
     * Gets the time the server last set the time on the requested client
     *
     * @param address the address of the remote client
     * @return the {@link ZigBeeUtcTime} the time was last provided to the client, or null if it has not been set.
     */
    public ZigBeeUtcTime getLastUpdateTime(int address) {
        return lastUpdate.get(address);
    }

    /**
     * Enables or disables the master flag. When set, we publish that our server is internally set to a time standard.
     *
     * @param master true if this is a master clock source.
     */
    protected void setMaster(boolean master) {
        this.master = master;
    }

    /**
     * Returns true if the server is a master clock source.
     *
     * @return true if the server is a master clock source.
     */
    protected boolean isMaster() {
        return master;
    }

    /**
     * Enables or disables the superceding flag. When set, we publish that our server is superceding other masters.
     *
     * @param master true if this is a master clock source.
     */

    protected void setSuperceding(boolean superceding) {
        this.superceding = superceding;
    }

    /**
     * Returns true of the server is marked as a superceding server.
     *
     * @return true of the server is marked as a superceding server.
     */
    protected boolean isSuperceding() {
        return superceding;
    }

    /**
     * Set the daylight savings time parameters, including the start and end of daylight saving, and the offset. This
     * will be passed on to all remote nodes at the next update.
     *
     * @param dstStart an {@link ZigBeeUtcTime} defining the time daylight savings time starts
     * @param dstEnd an {@link ZigBeeUtcTime} defining the time daylight savings time ends
     * @param dstOffset the number of seconds that daylight savings is adjusted
     */
    protected void setDst(ZigBeeUtcTime dstStart, ZigBeeUtcTime dstEnd, int dstOffset) {
        this.dstStart = dstStart;
        this.dstEnd = dstEnd;
        this.dstOffset = dstOffset;
        dstSet = true;
    }

    @Override
    public boolean commandReceived(ZclCommand command) {
        if (command.getClusterId() != ZclTimeCluster.CLUSTER_ID) {
            // TODO: This should check the message is addressed to the local NWK address
            return false;
        }

        if (command instanceof ReadAttributesCommand) {
            ReadAttributesCommand readRequest = (ReadAttributesCommand) command;
            if (readRequest.getCommandDirection() != ZclCommandDirection.CLIENT_TO_SERVER) {
                return false;
            }

            List<ReadAttributeStatusRecord> records = new ArrayList<>();
            for (Integer attributeId : readRequest.getIdentifiers()) {
                ReadAttributeStatusRecord record = new ReadAttributeStatusRecord();
                record.setAttributeIdentifier(attributeId);
                record.setStatus(ZclStatus.SUCCESS); // Assume SUCCESS - failure will be set below if needed

                switch (attributeId) {
                    case ZclTimeCluster.ATTR_TIMESTATUS:
                        int status = TimeStatusBitmap.SYNCHRONIZED.getKey();
                        status |= master ? TimeStatusBitmap.MASTER.getKey() : 0x0;
                        status |= superceding ? TimeStatusBitmap.SUPERSEDING.getKey() : 0x0;
                        status |= dstSet ? TimeStatusBitmap.MASTER_ZONE_DST.getKey() : 0x0;
                        record.setAttributeDataType(ZclDataType.BITMAP_8_BIT);
                        record.setAttributeValue(status);
                        break;
                    case ZclTimeCluster.ATTR_DSTSTART:
                        record.setAttributeDataType(ZclDataType.UTCTIME);
                        record.setAttributeValue(dstStart);
                        break;
                    case ZclTimeCluster.ATTR_DSTEND:
                        record.setAttributeDataType(ZclDataType.UTCTIME);
                        record.setAttributeValue(dstEnd);
                        break;
                    case ZclTimeCluster.ATTR_DSTSHIFT:
                        record.setAttributeDataType(ZclDataType.SIGNED_32_BIT_INTEGER);
                        record.setAttributeValue(dstOffset);
                        break;
                    case ZclTimeCluster.ATTR_TIME:
                        record.setAttributeDataType(ZclDataType.UTCTIME);
                        record.setAttributeValue(ZigBeeUtcTime.now());
                        lastUpdate.put(command.getSourceAddress().getAddress(), ZigBeeUtcTime.now());
                        break;
                    case ZclTimeCluster.ATTR_LASTSETTIME:
                        record.setAttributeDataType(ZclDataType.UTCTIME);
                        record.setAttributeValue(ZigBeeUtcTime.now());
                        break;
                    case ZclTimeCluster.ATTR_LOCALTIME:
                    case ZclTimeCluster.ATTR_STANDARDTIME:
                        record.setAttributeDataType(ZclDataType.UTCTIME);
                        record.setAttributeValue(ZigBeeUtcTime.now().plus(dstOffset));
                        lastUpdate.put(command.getSourceAddress().getAddress(), ZigBeeUtcTime.now());
                        break;
                    case ZclTimeCluster.ATTR_TIMEZONE:
                        record.setAttributeDataType(ZclDataType.SIGNED_32_BIT_INTEGER);
                        record.setAttributeValue(dstOffset);
                        break;
                    case ZclTimeCluster.ATTR_VALIDUNTILTIME:
                        record.setAttributeDataType(ZclDataType.UTCTIME);
                        record.setAttributeValue(new ZigBeeUtcTime());
                        break;
                    default:
                        record.setStatus(ZclStatus.UNSUPPORTED_ATTRIBUTE);
                        logger.debug("TIME Server unknown request {} from {}", attributeId, command.getSourceAddress());
                        break;
                }

                records.add(record);
            }

            ReadAttributesResponse readResponse = new ReadAttributesResponse(records);
            readResponse.setDestinationAddress(command.getSourceAddress());
            readResponse.setCommandDirection(ZclCommandDirection.SERVER_TO_CLIENT);
            readResponse.setClusterId(ZclTimeCluster.CLUSTER_ID);
            readResponse.setSourceAddress(command.getDestinationAddress());
            readResponse.setTransactionId(command.getTransactionId());

            networkManager.sendCommand(readResponse);

            return true;
        }

        return false;
    }

    /**
     * Adds a remote client to be managed by the local server
     *
     * @param timeClient the remote client
     */
    public void addRemote(ZclTimeCluster timeClient) {
        timeClient.addCommandListener(this);
    }
}

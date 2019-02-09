/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zdo.command;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zdo.ZdoRequest;
import com.zsmartsystems.zigbee.zdo.ZdoStatus;

/**
 * Management Network Update Notify value object class.
 * <p>
 * <p>
 * The Mgmt_NWK_Update_notify is provided to enable ZigBee devices to report the condition on
 * local channels to a network manager. The scanned channel list is the report of channels
 * scanned and it is followed by a list of records, one for each channel scanned, each record
 * including one byte of the energy level measured during the scan, or 0xff if there is too much
 * interference on this channel. <br> When sent in response to a Mgmt_NWK_Update_req command
 * the status field shall represent the status of the request. When sent unsolicited the status
 * field shall be set to SUCCESS. A Status of NOT_SUPPORTED indicates that the request was
 * directed to a device which was not the ZigBee Coordinator or that the ZigBee Coordinator does
 * not support End Device Binding. Otherwise, End_Device_Bind_req processing is performed
 * as described below, including transmission of the End_Device_Bind_rsp.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class ManagementNetworkUpdateNotify extends ZdoRequest {
    /**
     * Status command message field.
     */
    private ZdoStatus status;

    /**
     * Scanned Channels command message field.
     */
    private Integer scannedChannels;

    /**
     * Total Transmissions command message field.
     */
    private Integer totalTransmissions;

    /**
     * Transmission Failures command message field.
     */
    private Integer transmissionFailures;

    /**
     * Energy Values command message field.
     */
    private List<Integer> energyValues;

    /**
     * Default constructor.
     */
    public ManagementNetworkUpdateNotify() {
        clusterId = 0x8038;
    }

    /**
     * Gets Status.
     *
     * @return the Status
     */
    public ZdoStatus getStatus() {
        return status;
    }

    /**
     * Sets Status.
     *
     * @param status the Status
     */
    public void setStatus(final ZdoStatus status) {
        this.status = status;
    }

    /**
     * Gets Scanned Channels.
     *
     * @return the Scanned Channels
     */
    public Integer getScannedChannels() {
        return scannedChannels;
    }

    /**
     * Sets Scanned Channels.
     *
     * @param scannedChannels the Scanned Channels
     */
    public void setScannedChannels(final Integer scannedChannels) {
        this.scannedChannels = scannedChannels;
    }

    /**
     * Gets Total Transmissions.
     *
     * @return the Total Transmissions
     */
    public Integer getTotalTransmissions() {
        return totalTransmissions;
    }

    /**
     * Sets Total Transmissions.
     *
     * @param totalTransmissions the Total Transmissions
     */
    public void setTotalTransmissions(final Integer totalTransmissions) {
        this.totalTransmissions = totalTransmissions;
    }

    /**
     * Gets Transmission Failures.
     *
     * @return the Transmission Failures
     */
    public Integer getTransmissionFailures() {
        return transmissionFailures;
    }

    /**
     * Sets Transmission Failures.
     *
     * @param transmissionFailures the Transmission Failures
     */
    public void setTransmissionFailures(final Integer transmissionFailures) {
        this.transmissionFailures = transmissionFailures;
    }

    /**
     * Gets Energy Values.
     *
     * @return the Energy Values
     */
    public List<Integer> getEnergyValues() {
        return energyValues;
    }

    /**
     * Sets Energy Values.
     *
     * @param energyValues the Energy Values
     */
    public void setEnergyValues(final List<Integer> energyValues) {
        this.energyValues = energyValues;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        super.serialize(serializer);

        serializer.serialize(status, ZclDataType.ZDO_STATUS);
        serializer.serialize(scannedChannels, ZclDataType.UNSIGNED_32_BIT_INTEGER);
        serializer.serialize(totalTransmissions, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(transmissionFailures, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(energyValues.size(), ZclDataType.UNSIGNED_8_BIT_INTEGER);
        for (int cnt = 0; cnt < energyValues.size(); cnt++) {
            serializer.serialize(energyValues.get(cnt), ZclDataType.UNSIGNED_8_BIT_INTEGER);
        }
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        super.deserialize(deserializer);

        // Create lists
        energyValues = new ArrayList<Integer>();

        status = (ZdoStatus) deserializer.deserialize(ZclDataType.ZDO_STATUS);
        if (status != ZdoStatus.SUCCESS) {
            // Don't read the full response if we have an error
            return;
        }
        scannedChannels = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
        totalTransmissions = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        transmissionFailures = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        Integer scannedChannelsListCount = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        if (scannedChannelsListCount != null) {
            for (int cnt = 0; cnt < scannedChannelsListCount; cnt++) {
                energyValues.add((Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER));
            }
        }
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(247);
        builder.append("ManagementNetworkUpdateNotify [");
        builder.append(super.toString());
        builder.append(", status=");
        builder.append(status);
        builder.append(", scannedChannels=");
        builder.append(scannedChannels);
        builder.append(", totalTransmissions=");
        builder.append(totalTransmissions);
        builder.append(", transmissionFailures=");
        builder.append(transmissionFailures);
        builder.append(", energyValues=");
        builder.append(energyValues);
        builder.append(']');
        return builder.toString();
    }

}

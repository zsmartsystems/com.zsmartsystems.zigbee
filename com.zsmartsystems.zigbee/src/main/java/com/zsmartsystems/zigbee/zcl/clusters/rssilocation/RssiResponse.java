/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.rssilocation;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * RSSI Response value object class.
 * <p>
 * Cluster: <b>RSSI Location</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the RSSI Location cluster.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class RssiResponse extends ZclCommand {
    /**
     * Replying Device command message field.
     */
    private IeeeAddress replyingDevice;

    /**
     * Coordinate 1 command message field.
     */
    private Integer coordinate1;

    /**
     * Coordinate 2 command message field.
     */
    private Integer coordinate2;

    /**
     * Coordinate 3 command message field.
     */
    private Integer coordinate3;

    /**
     * RSSI command message field.
     */
    private Integer rssi;

    /**
     * Number RSSI Measurements command message field.
     */
    private Integer numberRssiMeasurements;

    /**
     * Default constructor.
     */
    public RssiResponse() {
        genericCommand = false;
        clusterId = 0x000B;
        commandId = 4;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Gets Replying Device.
     *
     * @return the Replying Device
     */
    public IeeeAddress getReplyingDevice() {
        return replyingDevice;
    }

    /**
     * Sets Replying Device.
     *
     * @param replyingDevice the Replying Device
     */
    public void setReplyingDevice(final IeeeAddress replyingDevice) {
        this.replyingDevice = replyingDevice;
    }

    /**
     * Gets Coordinate 1.
     *
     * @return the Coordinate 1
     */
    public Integer getCoordinate1() {
        return coordinate1;
    }

    /**
     * Sets Coordinate 1.
     *
     * @param coordinate1 the Coordinate 1
     */
    public void setCoordinate1(final Integer coordinate1) {
        this.coordinate1 = coordinate1;
    }

    /**
     * Gets Coordinate 2.
     *
     * @return the Coordinate 2
     */
    public Integer getCoordinate2() {
        return coordinate2;
    }

    /**
     * Sets Coordinate 2.
     *
     * @param coordinate2 the Coordinate 2
     */
    public void setCoordinate2(final Integer coordinate2) {
        this.coordinate2 = coordinate2;
    }

    /**
     * Gets Coordinate 3.
     *
     * @return the Coordinate 3
     */
    public Integer getCoordinate3() {
        return coordinate3;
    }

    /**
     * Sets Coordinate 3.
     *
     * @param coordinate3 the Coordinate 3
     */
    public void setCoordinate3(final Integer coordinate3) {
        this.coordinate3 = coordinate3;
    }

    /**
     * Gets RSSI.
     *
     * @return the RSSI
     */
    public Integer getRssi() {
        return rssi;
    }

    /**
     * Sets RSSI.
     *
     * @param rssi the RSSI
     */
    public void setRssi(final Integer rssi) {
        this.rssi = rssi;
    }

    /**
     * Gets Number RSSI Measurements.
     *
     * @return the Number RSSI Measurements
     */
    public Integer getNumberRssiMeasurements() {
        return numberRssiMeasurements;
    }

    /**
     * Sets Number RSSI Measurements.
     *
     * @param numberRssiMeasurements the Number RSSI Measurements
     */
    public void setNumberRssiMeasurements(final Integer numberRssiMeasurements) {
        this.numberRssiMeasurements = numberRssiMeasurements;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(replyingDevice, ZclDataType.IEEE_ADDRESS);
        serializer.serialize(coordinate1, ZclDataType.SIGNED_16_BIT_INTEGER);
        serializer.serialize(coordinate2, ZclDataType.SIGNED_16_BIT_INTEGER);
        serializer.serialize(coordinate3, ZclDataType.SIGNED_16_BIT_INTEGER);
        serializer.serialize(rssi, ZclDataType.SIGNED_8_BIT_INTEGER);
        serializer.serialize(numberRssiMeasurements, ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        replyingDevice = (IeeeAddress) deserializer.deserialize(ZclDataType.IEEE_ADDRESS);
        coordinate1 = (Integer) deserializer.deserialize(ZclDataType.SIGNED_16_BIT_INTEGER);
        coordinate2 = (Integer) deserializer.deserialize(ZclDataType.SIGNED_16_BIT_INTEGER);
        coordinate3 = (Integer) deserializer.deserialize(ZclDataType.SIGNED_16_BIT_INTEGER);
        rssi = (Integer) deserializer.deserialize(ZclDataType.SIGNED_8_BIT_INTEGER);
        numberRssiMeasurements = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(208);
        builder.append("RssiResponse [");
        builder.append(super.toString());
        builder.append(", replyingDevice=");
        builder.append(replyingDevice);
        builder.append(", coordinate1=");
        builder.append(coordinate1);
        builder.append(", coordinate2=");
        builder.append(coordinate2);
        builder.append(", coordinate3=");
        builder.append(coordinate3);
        builder.append(", rssi=");
        builder.append(rssi);
        builder.append(", numberRssiMeasurements=");
        builder.append(numberRssiMeasurements);
        builder.append(']');
        return builder.toString();
    }

}

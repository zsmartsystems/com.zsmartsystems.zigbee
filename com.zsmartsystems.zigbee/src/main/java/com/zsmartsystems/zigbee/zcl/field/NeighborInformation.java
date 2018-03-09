/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.field;

import com.zsmartsystems.zigbee.serialization.ZigBeeDeserializer;
import com.zsmartsystems.zigbee.serialization.ZigBeeSerializer;
import com.zsmartsystems.zigbee.zcl.ZclListItemField;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Attribute Identifier field.
 *
 * @author Tommi S.E. Laukkanen
 * @author Chris Jackson
 */
public class NeighborInformation implements ZclListItemField {
    /**
     * The neighbor address.
     */
    private long neighborAddress;
    /**
     * The coordinate 1
     */
    private int coordinate1;
    /**
     * The coordinate 2
     */
    private int coordinate2;
    /**
     * The coordinate 3
     */
    private int coordinate3;
    /**
     * The RSSI.
     */
    private int rssi;
    /**
     * The RSSI measurement count.
     */
    private int measurementCount;

    /**
     * Gets coordinate 1.
     *
     * @return the coordinate 1.
     */
    public int getCoordinate1() {
        return coordinate1;
    }

    /**
     * Sets coordinate 1
     *
     * @param coordinate1 the coordinate 1
     */
    public void setCoordinate1(int coordinate1) {
        this.coordinate1 = coordinate1;
    }

    /**
     * Gets coordinate 2.
     *
     * @return the coordinate 2
     */
    public int getCoordinate2() {
        return coordinate2;
    }

    /**
     * Sets coordinate 2.
     *
     * @param coordinate2 the coordinate 2
     */
    public void setCoordinate2(int coordinate2) {
        this.coordinate2 = coordinate2;
    }

    /**
     * Gets coordinate 3.
     *
     * @return the coordinate 3
     */
    public int getCoordinate3() {
        return coordinate3;
    }

    /**
     * Sets coordinate 3.
     *
     * @param coordinate3 the coordinate 3
     */
    public void setCoordinate3(int coordinate3) {
        this.coordinate3 = coordinate3;
    }

    /**
     * Gets measurement count.
     *
     * @return the measurement count
     */
    public int getMeasurementCount() {
        return measurementCount;
    }

    /**
     * Sets measurement count
     *
     * @param measurementCount the measurement count
     */
    public void setMeasurementCount(int measurementCount) {
        this.measurementCount = measurementCount;
    }

    /**
     * Gets neighbor address.
     *
     * @return the neighbor address
     */
    public long getNeighborAddress() {
        return neighborAddress;
    }

    /**
     * Sets neighbor address.
     *
     * @param neighborAddress the neighbor address.
     */
    public void setNeighborAddress(long neighborAddress) {
        this.neighborAddress = neighborAddress;
    }

    /**
     * Gets RSSI.
     *
     * @return the RSSI
     */
    public int getRssi() {
        return rssi;
    }

    /**
     * Sets RSSI.
     *
     * @param rssi the RSSI
     */
    public void setRssi(int rssi) {
        this.rssi = rssi;
    }

    @Override
    public void serialize(final ZigBeeSerializer serializer) {
        serializer.appendZigBeeType(neighborAddress, ZclDataType.IEEE_ADDRESS);
        serializer.appendZigBeeType((short) coordinate1, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.appendZigBeeType((short) coordinate2, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.appendZigBeeType((short) coordinate3, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.appendZigBeeType((byte) rssi, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.appendZigBeeType((byte) measurementCount, ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZigBeeDeserializer deserializer) {
        neighborAddress = (Long) deserializer.readZigBeeType(ZclDataType.IEEE_ADDRESS);
        coordinate1 = (int) deserializer.readZigBeeType(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        coordinate2 = (int) deserializer.readZigBeeType(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        coordinate3 = (int) deserializer.readZigBeeType(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        rssi = (int) deserializer.readZigBeeType(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        measurementCount = (int) deserializer.readZigBeeType(ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public String toString() {
        return "Neighbor Information: coordinate1=" + coordinate1 + ", neighborAddress=" + neighborAddress
                + ", coordinate2=" + coordinate2 + ", coordinate3=" + coordinate3 + ", rssi=" + rssi
                + ", measurementCount=" + measurementCount;
    }
}

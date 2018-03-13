/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.iasace;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;

/**
 * Get Zone ID Map Response value object class.
 * <p>
 * Cluster: <b>IAS ACE</b>. Command is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the IAS ACE cluster.
 * <p>
 * The IAS ACE cluster defines an interface to the functionality of any Ancillary
 * Control Equipment of the IAS system. Using this cluster, a ZigBee enabled ACE
 * device can access a IAS CIE device and manipulate the IAS system, on behalf of a
 * level-2 user.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZclProtocolCodeGenerator", date = "2018-03-12T23:36:29Z")
public class GetZoneIdMapResponse extends ZclCommand {
    /**
     * Zone ID Map section 0 command message field.
     */
    private Integer zoneIdMapSection0;

    /**
     * Zone ID Map section 1 command message field.
     */
    private Integer zoneIdMapSection1;

    /**
     * Zone ID Map section 2 command message field.
     */
    private Integer zoneIdMapSection2;

    /**
     * Zone ID Map section 3 command message field.
     */
    private Integer zoneIdMapSection3;

    /**
     * Zone ID Map section 4 command message field.
     */
    private Integer zoneIdMapSection4;

    /**
     * Zone ID Map section 5 command message field.
     */
    private Integer zoneIdMapSection5;

    /**
     * Zone ID Map section 6 command message field.
     */
    private Integer zoneIdMapSection6;

    /**
     * Zone ID Map section 7 command message field.
     */
    private Integer zoneIdMapSection7;

    /**
     * Zone ID Map section 8 command message field.
     */
    private Integer zoneIdMapSection8;

    /**
     * Zone ID Map section 9 command message field.
     */
    private Integer zoneIdMapSection9;

    /**
     * Zone ID Map section 10 command message field.
     */
    private Integer zoneIdMapSection10;

    /**
     * Zone ID Map section 11 command message field.
     */
    private Integer zoneIdMapSection11;

    /**
     * Zone ID Map section 12 command message field.
     */
    private Integer zoneIdMapSection12;

    /**
     * Zone ID Map section 13 command message field.
     */
    private Integer zoneIdMapSection13;

    /**
     * Zone ID Map section 14 command message field.
     */
    private Integer zoneIdMapSection14;

    /**
     * Zone ID Map section 15 command message field.
     */
    private Integer zoneIdMapSection15;

    /**
     * Default constructor.
     */
    public GetZoneIdMapResponse() {
        genericCommand = false;
        clusterId = 1281;
        commandId = 1;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;
    }

    /**
     * Gets Zone ID Map section 0.
     *
     * @return the Zone ID Map section 0
     */
    public Integer getZoneIdMapSection0() {
        return zoneIdMapSection0;
    }

    /**
     * Sets Zone ID Map section 0.
     *
     * @param zoneIdMapSection0 the Zone ID Map section 0
     */
    public void setZoneIdMapSection0(final Integer zoneIdMapSection0) {
        this.zoneIdMapSection0 = zoneIdMapSection0;
    }

    /**
     * Gets Zone ID Map section 1.
     *
     * @return the Zone ID Map section 1
     */
    public Integer getZoneIdMapSection1() {
        return zoneIdMapSection1;
    }

    /**
     * Sets Zone ID Map section 1.
     *
     * @param zoneIdMapSection1 the Zone ID Map section 1
     */
    public void setZoneIdMapSection1(final Integer zoneIdMapSection1) {
        this.zoneIdMapSection1 = zoneIdMapSection1;
    }

    /**
     * Gets Zone ID Map section 2.
     *
     * @return the Zone ID Map section 2
     */
    public Integer getZoneIdMapSection2() {
        return zoneIdMapSection2;
    }

    /**
     * Sets Zone ID Map section 2.
     *
     * @param zoneIdMapSection2 the Zone ID Map section 2
     */
    public void setZoneIdMapSection2(final Integer zoneIdMapSection2) {
        this.zoneIdMapSection2 = zoneIdMapSection2;
    }

    /**
     * Gets Zone ID Map section 3.
     *
     * @return the Zone ID Map section 3
     */
    public Integer getZoneIdMapSection3() {
        return zoneIdMapSection3;
    }

    /**
     * Sets Zone ID Map section 3.
     *
     * @param zoneIdMapSection3 the Zone ID Map section 3
     */
    public void setZoneIdMapSection3(final Integer zoneIdMapSection3) {
        this.zoneIdMapSection3 = zoneIdMapSection3;
    }

    /**
     * Gets Zone ID Map section 4.
     *
     * @return the Zone ID Map section 4
     */
    public Integer getZoneIdMapSection4() {
        return zoneIdMapSection4;
    }

    /**
     * Sets Zone ID Map section 4.
     *
     * @param zoneIdMapSection4 the Zone ID Map section 4
     */
    public void setZoneIdMapSection4(final Integer zoneIdMapSection4) {
        this.zoneIdMapSection4 = zoneIdMapSection4;
    }

    /**
     * Gets Zone ID Map section 5.
     *
     * @return the Zone ID Map section 5
     */
    public Integer getZoneIdMapSection5() {
        return zoneIdMapSection5;
    }

    /**
     * Sets Zone ID Map section 5.
     *
     * @param zoneIdMapSection5 the Zone ID Map section 5
     */
    public void setZoneIdMapSection5(final Integer zoneIdMapSection5) {
        this.zoneIdMapSection5 = zoneIdMapSection5;
    }

    /**
     * Gets Zone ID Map section 6.
     *
     * @return the Zone ID Map section 6
     */
    public Integer getZoneIdMapSection6() {
        return zoneIdMapSection6;
    }

    /**
     * Sets Zone ID Map section 6.
     *
     * @param zoneIdMapSection6 the Zone ID Map section 6
     */
    public void setZoneIdMapSection6(final Integer zoneIdMapSection6) {
        this.zoneIdMapSection6 = zoneIdMapSection6;
    }

    /**
     * Gets Zone ID Map section 7.
     *
     * @return the Zone ID Map section 7
     */
    public Integer getZoneIdMapSection7() {
        return zoneIdMapSection7;
    }

    /**
     * Sets Zone ID Map section 7.
     *
     * @param zoneIdMapSection7 the Zone ID Map section 7
     */
    public void setZoneIdMapSection7(final Integer zoneIdMapSection7) {
        this.zoneIdMapSection7 = zoneIdMapSection7;
    }

    /**
     * Gets Zone ID Map section 8.
     *
     * @return the Zone ID Map section 8
     */
    public Integer getZoneIdMapSection8() {
        return zoneIdMapSection8;
    }

    /**
     * Sets Zone ID Map section 8.
     *
     * @param zoneIdMapSection8 the Zone ID Map section 8
     */
    public void setZoneIdMapSection8(final Integer zoneIdMapSection8) {
        this.zoneIdMapSection8 = zoneIdMapSection8;
    }

    /**
     * Gets Zone ID Map section 9.
     *
     * @return the Zone ID Map section 9
     */
    public Integer getZoneIdMapSection9() {
        return zoneIdMapSection9;
    }

    /**
     * Sets Zone ID Map section 9.
     *
     * @param zoneIdMapSection9 the Zone ID Map section 9
     */
    public void setZoneIdMapSection9(final Integer zoneIdMapSection9) {
        this.zoneIdMapSection9 = zoneIdMapSection9;
    }

    /**
     * Gets Zone ID Map section 10.
     *
     * @return the Zone ID Map section 10
     */
    public Integer getZoneIdMapSection10() {
        return zoneIdMapSection10;
    }

    /**
     * Sets Zone ID Map section 10.
     *
     * @param zoneIdMapSection10 the Zone ID Map section 10
     */
    public void setZoneIdMapSection10(final Integer zoneIdMapSection10) {
        this.zoneIdMapSection10 = zoneIdMapSection10;
    }

    /**
     * Gets Zone ID Map section 11.
     *
     * @return the Zone ID Map section 11
     */
    public Integer getZoneIdMapSection11() {
        return zoneIdMapSection11;
    }

    /**
     * Sets Zone ID Map section 11.
     *
     * @param zoneIdMapSection11 the Zone ID Map section 11
     */
    public void setZoneIdMapSection11(final Integer zoneIdMapSection11) {
        this.zoneIdMapSection11 = zoneIdMapSection11;
    }

    /**
     * Gets Zone ID Map section 12.
     *
     * @return the Zone ID Map section 12
     */
    public Integer getZoneIdMapSection12() {
        return zoneIdMapSection12;
    }

    /**
     * Sets Zone ID Map section 12.
     *
     * @param zoneIdMapSection12 the Zone ID Map section 12
     */
    public void setZoneIdMapSection12(final Integer zoneIdMapSection12) {
        this.zoneIdMapSection12 = zoneIdMapSection12;
    }

    /**
     * Gets Zone ID Map section 13.
     *
     * @return the Zone ID Map section 13
     */
    public Integer getZoneIdMapSection13() {
        return zoneIdMapSection13;
    }

    /**
     * Sets Zone ID Map section 13.
     *
     * @param zoneIdMapSection13 the Zone ID Map section 13
     */
    public void setZoneIdMapSection13(final Integer zoneIdMapSection13) {
        this.zoneIdMapSection13 = zoneIdMapSection13;
    }

    /**
     * Gets Zone ID Map section 14.
     *
     * @return the Zone ID Map section 14
     */
    public Integer getZoneIdMapSection14() {
        return zoneIdMapSection14;
    }

    /**
     * Sets Zone ID Map section 14.
     *
     * @param zoneIdMapSection14 the Zone ID Map section 14
     */
    public void setZoneIdMapSection14(final Integer zoneIdMapSection14) {
        this.zoneIdMapSection14 = zoneIdMapSection14;
    }

    /**
     * Gets Zone ID Map section 15.
     *
     * @return the Zone ID Map section 15
     */
    public Integer getZoneIdMapSection15() {
        return zoneIdMapSection15;
    }

    /**
     * Sets Zone ID Map section 15.
     *
     * @param zoneIdMapSection15 the Zone ID Map section 15
     */
    public void setZoneIdMapSection15(final Integer zoneIdMapSection15) {
        this.zoneIdMapSection15 = zoneIdMapSection15;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(zoneIdMapSection0, ZclDataType.BITMAP_16_BIT);
        serializer.serialize(zoneIdMapSection1, ZclDataType.BITMAP_16_BIT);
        serializer.serialize(zoneIdMapSection2, ZclDataType.BITMAP_16_BIT);
        serializer.serialize(zoneIdMapSection3, ZclDataType.BITMAP_16_BIT);
        serializer.serialize(zoneIdMapSection4, ZclDataType.BITMAP_16_BIT);
        serializer.serialize(zoneIdMapSection5, ZclDataType.BITMAP_16_BIT);
        serializer.serialize(zoneIdMapSection6, ZclDataType.BITMAP_16_BIT);
        serializer.serialize(zoneIdMapSection7, ZclDataType.BITMAP_16_BIT);
        serializer.serialize(zoneIdMapSection8, ZclDataType.BITMAP_16_BIT);
        serializer.serialize(zoneIdMapSection9, ZclDataType.BITMAP_16_BIT);
        serializer.serialize(zoneIdMapSection10, ZclDataType.BITMAP_16_BIT);
        serializer.serialize(zoneIdMapSection11, ZclDataType.BITMAP_16_BIT);
        serializer.serialize(zoneIdMapSection12, ZclDataType.BITMAP_16_BIT);
        serializer.serialize(zoneIdMapSection13, ZclDataType.BITMAP_16_BIT);
        serializer.serialize(zoneIdMapSection14, ZclDataType.BITMAP_16_BIT);
        serializer.serialize(zoneIdMapSection15, ZclDataType.BITMAP_16_BIT);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        zoneIdMapSection0 = (Integer) deserializer.deserialize(ZclDataType.BITMAP_16_BIT);
        zoneIdMapSection1 = (Integer) deserializer.deserialize(ZclDataType.BITMAP_16_BIT);
        zoneIdMapSection2 = (Integer) deserializer.deserialize(ZclDataType.BITMAP_16_BIT);
        zoneIdMapSection3 = (Integer) deserializer.deserialize(ZclDataType.BITMAP_16_BIT);
        zoneIdMapSection4 = (Integer) deserializer.deserialize(ZclDataType.BITMAP_16_BIT);
        zoneIdMapSection5 = (Integer) deserializer.deserialize(ZclDataType.BITMAP_16_BIT);
        zoneIdMapSection6 = (Integer) deserializer.deserialize(ZclDataType.BITMAP_16_BIT);
        zoneIdMapSection7 = (Integer) deserializer.deserialize(ZclDataType.BITMAP_16_BIT);
        zoneIdMapSection8 = (Integer) deserializer.deserialize(ZclDataType.BITMAP_16_BIT);
        zoneIdMapSection9 = (Integer) deserializer.deserialize(ZclDataType.BITMAP_16_BIT);
        zoneIdMapSection10 = (Integer) deserializer.deserialize(ZclDataType.BITMAP_16_BIT);
        zoneIdMapSection11 = (Integer) deserializer.deserialize(ZclDataType.BITMAP_16_BIT);
        zoneIdMapSection12 = (Integer) deserializer.deserialize(ZclDataType.BITMAP_16_BIT);
        zoneIdMapSection13 = (Integer) deserializer.deserialize(ZclDataType.BITMAP_16_BIT);
        zoneIdMapSection14 = (Integer) deserializer.deserialize(ZclDataType.BITMAP_16_BIT);
        zoneIdMapSection15 = (Integer) deserializer.deserialize(ZclDataType.BITMAP_16_BIT);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(621);
        builder.append("GetZoneIdMapResponse [");
        builder.append(super.toString());
        builder.append(", zoneIdMapSection0=");
        builder.append(zoneIdMapSection0);
        builder.append(", zoneIdMapSection1=");
        builder.append(zoneIdMapSection1);
        builder.append(", zoneIdMapSection2=");
        builder.append(zoneIdMapSection2);
        builder.append(", zoneIdMapSection3=");
        builder.append(zoneIdMapSection3);
        builder.append(", zoneIdMapSection4=");
        builder.append(zoneIdMapSection4);
        builder.append(", zoneIdMapSection5=");
        builder.append(zoneIdMapSection5);
        builder.append(", zoneIdMapSection6=");
        builder.append(zoneIdMapSection6);
        builder.append(", zoneIdMapSection7=");
        builder.append(zoneIdMapSection7);
        builder.append(", zoneIdMapSection8=");
        builder.append(zoneIdMapSection8);
        builder.append(", zoneIdMapSection9=");
        builder.append(zoneIdMapSection9);
        builder.append(", zoneIdMapSection10=");
        builder.append(zoneIdMapSection10);
        builder.append(", zoneIdMapSection11=");
        builder.append(zoneIdMapSection11);
        builder.append(", zoneIdMapSection12=");
        builder.append(zoneIdMapSection12);
        builder.append(", zoneIdMapSection13=");
        builder.append(zoneIdMapSection13);
        builder.append(", zoneIdMapSection14=");
        builder.append(zoneIdMapSection14);
        builder.append(", zoneIdMapSection15=");
        builder.append(zoneIdMapSection15);
        builder.append(']');
        return builder.toString();
    }

}

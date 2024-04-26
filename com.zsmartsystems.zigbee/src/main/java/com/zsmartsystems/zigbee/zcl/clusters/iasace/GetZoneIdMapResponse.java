/**
 * Copyright (c) 2016-2024 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.iasace;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Get Zone ID Map Response value object class.
 * <p>
 * Cluster: <b>IAS ACE</b>. Command ID 0x01 is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the IAS ACE cluster.
 * <p>
 * The 16 fields of the payload indicate whether each of the Zone IDs from 0x00 to 0xff is
 * allocated or not. If bit n of Zone ID Map section N is set to 1, then Zone ID (16 x N + n ) is
 * allocated, else it is not allocated.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-12-25T10:11:19Z")
public class GetZoneIdMapResponse extends ZclIasAceCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0501;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x01;

    /**
     * Zone ID Map Section 0 command message field.
     */
    private Integer zoneIdMapSection0;

    /**
     * Zone ID Map Section 1 command message field.
     */
    private Integer zoneIdMapSection1;

    /**
     * Zone ID Map Section 2 command message field.
     */
    private Integer zoneIdMapSection2;

    /**
     * Zone ID Map Section 3 command message field.
     */
    private Integer zoneIdMapSection3;

    /**
     * Zone ID Map Section 4 command message field.
     */
    private Integer zoneIdMapSection4;

    /**
     * Zone ID Map Section 5 command message field.
     */
    private Integer zoneIdMapSection5;

    /**
     * Zone ID Map Section 6 command message field.
     */
    private Integer zoneIdMapSection6;

    /**
     * Zone ID Map Section 7 command message field.
     */
    private Integer zoneIdMapSection7;

    /**
     * Zone ID Map Section 8 command message field.
     */
    private Integer zoneIdMapSection8;

    /**
     * Zone ID Map Section 9 command message field.
     */
    private Integer zoneIdMapSection9;

    /**
     * Zone ID Map Section 10 command message field.
     */
    private Integer zoneIdMapSection10;

    /**
     * Zone ID Map Section 11 command message field.
     */
    private Integer zoneIdMapSection11;

    /**
     * Zone ID Map Section 12 command message field.
     */
    private Integer zoneIdMapSection12;

    /**
     * Zone ID Map Section 13 command message field.
     */
    private Integer zoneIdMapSection13;

    /**
     * Zone ID Map Section 14 command message field.
     */
    private Integer zoneIdMapSection14;

    /**
     * Zone ID Map Section 15 command message field.
     */
    private Integer zoneIdMapSection15;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public GetZoneIdMapResponse() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param zoneIdMapSection0 {@link Integer} Zone ID Map Section 0
     * @param zoneIdMapSection1 {@link Integer} Zone ID Map Section 1
     * @param zoneIdMapSection2 {@link Integer} Zone ID Map Section 2
     * @param zoneIdMapSection3 {@link Integer} Zone ID Map Section 3
     * @param zoneIdMapSection4 {@link Integer} Zone ID Map Section 4
     * @param zoneIdMapSection5 {@link Integer} Zone ID Map Section 5
     * @param zoneIdMapSection6 {@link Integer} Zone ID Map Section 6
     * @param zoneIdMapSection7 {@link Integer} Zone ID Map Section 7
     * @param zoneIdMapSection8 {@link Integer} Zone ID Map Section 8
     * @param zoneIdMapSection9 {@link Integer} Zone ID Map Section 9
     * @param zoneIdMapSection10 {@link Integer} Zone ID Map Section 10
     * @param zoneIdMapSection11 {@link Integer} Zone ID Map Section 11
     * @param zoneIdMapSection12 {@link Integer} Zone ID Map Section 12
     * @param zoneIdMapSection13 {@link Integer} Zone ID Map Section 13
     * @param zoneIdMapSection14 {@link Integer} Zone ID Map Section 14
     * @param zoneIdMapSection15 {@link Integer} Zone ID Map Section 15
     */
    public GetZoneIdMapResponse(
            Integer zoneIdMapSection0,
            Integer zoneIdMapSection1,
            Integer zoneIdMapSection2,
            Integer zoneIdMapSection3,
            Integer zoneIdMapSection4,
            Integer zoneIdMapSection5,
            Integer zoneIdMapSection6,
            Integer zoneIdMapSection7,
            Integer zoneIdMapSection8,
            Integer zoneIdMapSection9,
            Integer zoneIdMapSection10,
            Integer zoneIdMapSection11,
            Integer zoneIdMapSection12,
            Integer zoneIdMapSection13,
            Integer zoneIdMapSection14,
            Integer zoneIdMapSection15) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;

        this.zoneIdMapSection0 = zoneIdMapSection0;
        this.zoneIdMapSection1 = zoneIdMapSection1;
        this.zoneIdMapSection2 = zoneIdMapSection2;
        this.zoneIdMapSection3 = zoneIdMapSection3;
        this.zoneIdMapSection4 = zoneIdMapSection4;
        this.zoneIdMapSection5 = zoneIdMapSection5;
        this.zoneIdMapSection6 = zoneIdMapSection6;
        this.zoneIdMapSection7 = zoneIdMapSection7;
        this.zoneIdMapSection8 = zoneIdMapSection8;
        this.zoneIdMapSection9 = zoneIdMapSection9;
        this.zoneIdMapSection10 = zoneIdMapSection10;
        this.zoneIdMapSection11 = zoneIdMapSection11;
        this.zoneIdMapSection12 = zoneIdMapSection12;
        this.zoneIdMapSection13 = zoneIdMapSection13;
        this.zoneIdMapSection14 = zoneIdMapSection14;
        this.zoneIdMapSection15 = zoneIdMapSection15;
    }

    /**
     * Gets Zone ID Map Section 0.
     *
     * @return the Zone ID Map Section 0
     */
    public Integer getZoneIdMapSection0() {
        return zoneIdMapSection0;
    }

    /**
     * Sets Zone ID Map Section 0.
     *
     * @param zoneIdMapSection0 the Zone ID Map Section 0
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setZoneIdMapSection0(final Integer zoneIdMapSection0) {
        this.zoneIdMapSection0 = zoneIdMapSection0;
    }

    /**
     * Gets Zone ID Map Section 1.
     *
     * @return the Zone ID Map Section 1
     */
    public Integer getZoneIdMapSection1() {
        return zoneIdMapSection1;
    }

    /**
     * Sets Zone ID Map Section 1.
     *
     * @param zoneIdMapSection1 the Zone ID Map Section 1
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setZoneIdMapSection1(final Integer zoneIdMapSection1) {
        this.zoneIdMapSection1 = zoneIdMapSection1;
    }

    /**
     * Gets Zone ID Map Section 2.
     *
     * @return the Zone ID Map Section 2
     */
    public Integer getZoneIdMapSection2() {
        return zoneIdMapSection2;
    }

    /**
     * Sets Zone ID Map Section 2.
     *
     * @param zoneIdMapSection2 the Zone ID Map Section 2
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setZoneIdMapSection2(final Integer zoneIdMapSection2) {
        this.zoneIdMapSection2 = zoneIdMapSection2;
    }

    /**
     * Gets Zone ID Map Section 3.
     *
     * @return the Zone ID Map Section 3
     */
    public Integer getZoneIdMapSection3() {
        return zoneIdMapSection3;
    }

    /**
     * Sets Zone ID Map Section 3.
     *
     * @param zoneIdMapSection3 the Zone ID Map Section 3
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setZoneIdMapSection3(final Integer zoneIdMapSection3) {
        this.zoneIdMapSection3 = zoneIdMapSection3;
    }

    /**
     * Gets Zone ID Map Section 4.
     *
     * @return the Zone ID Map Section 4
     */
    public Integer getZoneIdMapSection4() {
        return zoneIdMapSection4;
    }

    /**
     * Sets Zone ID Map Section 4.
     *
     * @param zoneIdMapSection4 the Zone ID Map Section 4
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setZoneIdMapSection4(final Integer zoneIdMapSection4) {
        this.zoneIdMapSection4 = zoneIdMapSection4;
    }

    /**
     * Gets Zone ID Map Section 5.
     *
     * @return the Zone ID Map Section 5
     */
    public Integer getZoneIdMapSection5() {
        return zoneIdMapSection5;
    }

    /**
     * Sets Zone ID Map Section 5.
     *
     * @param zoneIdMapSection5 the Zone ID Map Section 5
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setZoneIdMapSection5(final Integer zoneIdMapSection5) {
        this.zoneIdMapSection5 = zoneIdMapSection5;
    }

    /**
     * Gets Zone ID Map Section 6.
     *
     * @return the Zone ID Map Section 6
     */
    public Integer getZoneIdMapSection6() {
        return zoneIdMapSection6;
    }

    /**
     * Sets Zone ID Map Section 6.
     *
     * @param zoneIdMapSection6 the Zone ID Map Section 6
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setZoneIdMapSection6(final Integer zoneIdMapSection6) {
        this.zoneIdMapSection6 = zoneIdMapSection6;
    }

    /**
     * Gets Zone ID Map Section 7.
     *
     * @return the Zone ID Map Section 7
     */
    public Integer getZoneIdMapSection7() {
        return zoneIdMapSection7;
    }

    /**
     * Sets Zone ID Map Section 7.
     *
     * @param zoneIdMapSection7 the Zone ID Map Section 7
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setZoneIdMapSection7(final Integer zoneIdMapSection7) {
        this.zoneIdMapSection7 = zoneIdMapSection7;
    }

    /**
     * Gets Zone ID Map Section 8.
     *
     * @return the Zone ID Map Section 8
     */
    public Integer getZoneIdMapSection8() {
        return zoneIdMapSection8;
    }

    /**
     * Sets Zone ID Map Section 8.
     *
     * @param zoneIdMapSection8 the Zone ID Map Section 8
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setZoneIdMapSection8(final Integer zoneIdMapSection8) {
        this.zoneIdMapSection8 = zoneIdMapSection8;
    }

    /**
     * Gets Zone ID Map Section 9.
     *
     * @return the Zone ID Map Section 9
     */
    public Integer getZoneIdMapSection9() {
        return zoneIdMapSection9;
    }

    /**
     * Sets Zone ID Map Section 9.
     *
     * @param zoneIdMapSection9 the Zone ID Map Section 9
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setZoneIdMapSection9(final Integer zoneIdMapSection9) {
        this.zoneIdMapSection9 = zoneIdMapSection9;
    }

    /**
     * Gets Zone ID Map Section 10.
     *
     * @return the Zone ID Map Section 10
     */
    public Integer getZoneIdMapSection10() {
        return zoneIdMapSection10;
    }

    /**
     * Sets Zone ID Map Section 10.
     *
     * @param zoneIdMapSection10 the Zone ID Map Section 10
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setZoneIdMapSection10(final Integer zoneIdMapSection10) {
        this.zoneIdMapSection10 = zoneIdMapSection10;
    }

    /**
     * Gets Zone ID Map Section 11.
     *
     * @return the Zone ID Map Section 11
     */
    public Integer getZoneIdMapSection11() {
        return zoneIdMapSection11;
    }

    /**
     * Sets Zone ID Map Section 11.
     *
     * @param zoneIdMapSection11 the Zone ID Map Section 11
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setZoneIdMapSection11(final Integer zoneIdMapSection11) {
        this.zoneIdMapSection11 = zoneIdMapSection11;
    }

    /**
     * Gets Zone ID Map Section 12.
     *
     * @return the Zone ID Map Section 12
     */
    public Integer getZoneIdMapSection12() {
        return zoneIdMapSection12;
    }

    /**
     * Sets Zone ID Map Section 12.
     *
     * @param zoneIdMapSection12 the Zone ID Map Section 12
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setZoneIdMapSection12(final Integer zoneIdMapSection12) {
        this.zoneIdMapSection12 = zoneIdMapSection12;
    }

    /**
     * Gets Zone ID Map Section 13.
     *
     * @return the Zone ID Map Section 13
     */
    public Integer getZoneIdMapSection13() {
        return zoneIdMapSection13;
    }

    /**
     * Sets Zone ID Map Section 13.
     *
     * @param zoneIdMapSection13 the Zone ID Map Section 13
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setZoneIdMapSection13(final Integer zoneIdMapSection13) {
        this.zoneIdMapSection13 = zoneIdMapSection13;
    }

    /**
     * Gets Zone ID Map Section 14.
     *
     * @return the Zone ID Map Section 14
     */
    public Integer getZoneIdMapSection14() {
        return zoneIdMapSection14;
    }

    /**
     * Sets Zone ID Map Section 14.
     *
     * @param zoneIdMapSection14 the Zone ID Map Section 14
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setZoneIdMapSection14(final Integer zoneIdMapSection14) {
        this.zoneIdMapSection14 = zoneIdMapSection14;
    }

    /**
     * Gets Zone ID Map Section 15.
     *
     * @return the Zone ID Map Section 15
     */
    public Integer getZoneIdMapSection15() {
        return zoneIdMapSection15;
    }

    /**
     * Sets Zone ID Map Section 15.
     *
     * @param zoneIdMapSection15 the Zone ID Map Section 15
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
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
        zoneIdMapSection0 = deserializer.deserialize(ZclDataType.BITMAP_16_BIT);
        zoneIdMapSection1 = deserializer.deserialize(ZclDataType.BITMAP_16_BIT);
        zoneIdMapSection2 = deserializer.deserialize(ZclDataType.BITMAP_16_BIT);
        zoneIdMapSection3 = deserializer.deserialize(ZclDataType.BITMAP_16_BIT);
        zoneIdMapSection4 = deserializer.deserialize(ZclDataType.BITMAP_16_BIT);
        zoneIdMapSection5 = deserializer.deserialize(ZclDataType.BITMAP_16_BIT);
        zoneIdMapSection6 = deserializer.deserialize(ZclDataType.BITMAP_16_BIT);
        zoneIdMapSection7 = deserializer.deserialize(ZclDataType.BITMAP_16_BIT);
        zoneIdMapSection8 = deserializer.deserialize(ZclDataType.BITMAP_16_BIT);
        zoneIdMapSection9 = deserializer.deserialize(ZclDataType.BITMAP_16_BIT);
        zoneIdMapSection10 = deserializer.deserialize(ZclDataType.BITMAP_16_BIT);
        zoneIdMapSection11 = deserializer.deserialize(ZclDataType.BITMAP_16_BIT);
        zoneIdMapSection12 = deserializer.deserialize(ZclDataType.BITMAP_16_BIT);
        zoneIdMapSection13 = deserializer.deserialize(ZclDataType.BITMAP_16_BIT);
        zoneIdMapSection14 = deserializer.deserialize(ZclDataType.BITMAP_16_BIT);
        zoneIdMapSection15 = deserializer.deserialize(ZclDataType.BITMAP_16_BIT);
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

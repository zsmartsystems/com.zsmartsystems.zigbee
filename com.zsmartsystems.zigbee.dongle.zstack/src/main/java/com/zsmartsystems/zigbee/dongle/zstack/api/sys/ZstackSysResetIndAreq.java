/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.zstack.api.sys;

import com.zsmartsystems.zigbee.dongle.zstack.api.ZstackFrameResponse;

/**
 * Class to implement the Z-Stack command <b>SYS_RESET_IND</b>.
 * <p>
 * This command is generated by the CC2530 device automatically immediately after a reset.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson
 */
public class ZstackSysResetIndAreq extends ZstackFrameResponse {

    /**
     * One of the following values indicating the reason for the reset. Power=0x00, External=0x01, Watchdog=0x02
     */
    private ZstackResetReason reason;

    /**
     * Transport protocol revision
     */
    private int transportRev;

    /**
     * Product Id
     */
    private int product;

    /**
     * Software major release number
     */
    private int majorRel;

    /**
     * Software minor release number
     */
    private int minorRel;

    /**
     * Hardware revision number.
     */
    private int hwRev;

    /**
     * Response and Handler constructor
     */
    public ZstackSysResetIndAreq(int[] inputBuffer) {
        // Super creates deserializer and reads header fields
        super(inputBuffer);

        // Deserialize the fields
        reason = ZstackResetReason.valueOf(deserializer.deserializeUInt8());
        transportRev = deserializer.deserializeUInt8();
        product = deserializer.deserializeUInt8();
        majorRel = deserializer.deserializeUInt8();
        minorRel = deserializer.deserializeUInt8();
        hwRev = deserializer.deserializeUInt8();
    }

    /**
     * One of the following values indicating the reason for the reset. Power=0x00, External=0x01, Watchdog=0x02
     *
     * @return the current reason as {@link ZstackResetReason}
     */
    public ZstackResetReason getReason() {
        return reason;
    }

    /**
     * One of the following values indicating the reason for the reset. Power=0x00, External=0x01, Watchdog=0x02
     *
     * @param reason the Reason to set as {@link ZstackResetReason}
     */
    public void setReason(ZstackResetReason reason) {
        this.reason = reason;
    }

    /**
     * Transport protocol revision
     *
     * @return the current transportRev as {@link int}
     */
    public int getTransportRev() {
        return transportRev;
    }

    /**
     * Transport protocol revision
     *
     * @param transportRev the TransportRev to set as {@link int}
     */
    public void setTransportRev(int transportRev) {
        this.transportRev = transportRev;
    }

    /**
     * Product Id
     *
     * @return the current product as {@link int}
     */
    public int getProduct() {
        return product;
    }

    /**
     * Product Id
     *
     * @param product the Product to set as {@link int}
     */
    public void setProduct(int product) {
        this.product = product;
    }

    /**
     * Software major release number
     *
     * @return the current majorRel as {@link int}
     */
    public int getMajorRel() {
        return majorRel;
    }

    /**
     * Software major release number
     *
     * @param majorRel the MajorRel to set as {@link int}
     */
    public void setMajorRel(int majorRel) {
        this.majorRel = majorRel;
    }

    /**
     * Software minor release number
     *
     * @return the current minorRel as {@link int}
     */
    public int getMinorRel() {
        return minorRel;
    }

    /**
     * Software minor release number
     *
     * @param minorRel the MinorRel to set as {@link int}
     */
    public void setMinorRel(int minorRel) {
        this.minorRel = minorRel;
    }

    /**
     * Hardware revision number.
     *
     * @return the current hwRev as {@link int}
     */
    public int getHwRev() {
        return hwRev;
    }

    /**
     * Hardware revision number.
     *
     * @param hwRev the HwRev to set as {@link int}
     */
    public void setHwRev(int hwRev) {
        this.hwRev = hwRev;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(174);
        builder.append("ZstackSysResetIndAreq [reason=");
        builder.append(reason);
        builder.append(", transportRev=");
        builder.append(transportRev);
        builder.append(", product=");
        builder.append(product);
        builder.append(", majorRel=");
        builder.append(majorRel);
        builder.append(", minorRel=");
        builder.append(minorRel);
        builder.append(", hwRev=");
        builder.append(hwRev);
        builder.append(']');
        return builder.toString();
    }
}

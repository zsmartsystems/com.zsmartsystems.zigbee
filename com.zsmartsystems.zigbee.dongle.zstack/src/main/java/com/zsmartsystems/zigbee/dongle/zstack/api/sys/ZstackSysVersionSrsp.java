/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.zstack.api.sys;

import com.zsmartsystems.zigbee.dongle.zstack.api.ZstackFrameResponse;
import javax.annotation.Generated;

/**
 * Class to implement the Z-Stack command <b>SYS_VERSION</b>.
 * <p>
 * This command issues PING requests to verify if a device is active and check the capability of the device.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 */

@Generated(value = "com.zsmartsystems.zigbee.dongle.zstack.autocode.CommandGenerator", date = "Sun Mar 26 09:52:47 CEST 2023")
public class ZstackSysVersionSrsp extends ZstackFrameResponse {

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
     * Software maintenance release number
     */
    private int maintRel;

    /**
     * Response and Handler constructor
     */
    public ZstackSysVersionSrsp(int[] inputBuffer) {
        // Super creates deserializer and reads header fields
        super(inputBuffer);

        synchronousCommand = true;

        // Deserialize the fields
        transportRev = deserializer.deserializeUInt8();
        product = deserializer.deserializeUInt8();
        majorRel = deserializer.deserializeUInt8();
        minorRel = deserializer.deserializeUInt8();
        maintRel = deserializer.deserializeUInt8();
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
     * Software maintenance release number
     *
     * @return the current maintRel as {@link int}
     */
    public int getMaintRel() {
        return maintRel;
    }

    /**
     * Software maintenance release number
     *
     * @param maintRel the MaintRel to set as {@link int}
     */
    public void setMaintRel(int maintRel) {
        this.maintRel = maintRel;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(148);
        builder.append("ZstackSysVersionSrsp [transportRev=");
        builder.append(transportRev);
        builder.append(", product=");
        builder.append(product);
        builder.append(", majorRel=");
        builder.append(majorRel);
        builder.append(", minorRel=");
        builder.append(minorRel);
        builder.append(", maintRel=");
        builder.append(maintRel);
        builder.append(']');
        return builder.toString();
    }
}

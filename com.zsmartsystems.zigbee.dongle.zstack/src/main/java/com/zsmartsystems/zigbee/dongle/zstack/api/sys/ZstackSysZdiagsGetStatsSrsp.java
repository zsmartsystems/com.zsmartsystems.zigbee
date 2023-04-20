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
 * Class to implement the Z-Stack command <b>SYS_ZDIAGS_GET_STATS</b>.
 * <p>
 * This command is used to read a specific system (attribute) ID statistics and/or metrics value.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 */

@Generated(value = "com.zsmartsystems.zigbee.dongle.zstack.autocode.CommandGenerator", date = "Sun Mar 26 09:52:47 CEST 2023")
public class ZstackSysZdiagsGetStatsSrsp extends ZstackFrameResponse {

    /**
     * Value of the requested attribute.
     */
    private int attributeValue;

    /**
     * Response and Handler constructor
     */
    public ZstackSysZdiagsGetStatsSrsp(int[] inputBuffer) {
        // Super creates deserializer and reads header fields
        super(inputBuffer);

        synchronousCommand = true;

        // Deserialize the fields
        attributeValue = deserializer.deserializeUInt32();
    }

    /**
     * Value of the requested attribute.
     *
     * @return the current attributeValue as {@link int}
     */
    public int getAttributeValue() {
        return attributeValue;
    }

    /**
     * Value of the requested attribute.
     *
     * @param attributeValue the AttributeValue to set as {@link int}
     */
    public void setAttributeValue(int attributeValue) {
        this.attributeValue = attributeValue;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(55);
        builder.append("ZstackSysZdiagsGetStatsSrsp [attributeValue=");
        builder.append(attributeValue);
        builder.append(']');
        return builder.toString();
    }
}

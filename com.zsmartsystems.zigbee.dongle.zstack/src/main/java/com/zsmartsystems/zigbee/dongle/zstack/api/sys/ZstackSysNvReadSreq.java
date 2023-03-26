/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.zstack.api.sys;

import com.zsmartsystems.zigbee.dongle.zstack.api.ZstackFrameRequest;
import com.zsmartsystems.zigbee.dongle.zstack.api.rpc.ZstackRpcSreqErrorSrsp;
import javax.annotation.Generated;

/**
 * Class to implement the Z-Stack command <b>SYS_NV_READ</b>.
 * <p>
 * This command is used to read a single memory item from the target non-volatile memory. The command returns the item requested
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 */

@Generated(value = "com.zsmartsystems.zigbee.dongle.zstack.autocode.CommandGenerator", date = "Sun Mar 26 09:52:47 CEST 2023")
public class ZstackSysNvReadSreq extends ZstackFrameRequest {

    /**
     * The system id (1 for zstack)
     */
    private int sysId;

    /**
     * The sub id (0 for legacy)
     */
    private int itemId;

    /**
     * The Id of the NV item.
     */
    private int subId;

    /**
     * Number of bytes offset from the beginning or the NV value.
     */
    private int offset;

    /**
     * Number of bytes to read
     */
    private int length;

    /**
     * Request constructor
     */
    public ZstackSysNvReadSreq() {
        synchronousCommand = true;
    }

    /**
     * The system id (1 for zstack)
     *
     * @return the current sysId as {@link int}
     */
    public int getSysId() {
        return sysId;
    }

    /**
     * The system id (1 for zstack)
     *
     * @param sysId the sysId to set as {@link int}
     */
    public void setSysId(int sysId) {
        this.sysId = sysId;
    }

    /**
     * The sub id (0 for legacy)
     *
     * @return the current itemId as {@link int}
     */
    public int getItemId() {
        return itemId;
    }

    /**
     * The sub id (0 for legacy)
     *
     * @param itemId the itemId to set as {@link int}
     */
    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    /**
     * The Id of the NV item.
     *
     * @return the current subId as {@link int}
     */
    public int getSubId() {
        return subId;
    }

    /**
     * The Id of the NV item.
     *
     * @param subId the subId to set as {@link int}
     */
    public void setSubId(int subId) {
        this.subId = subId;
    }

    /**
     * Number of bytes offset from the beginning or the NV value.
     *
     * @return the current offset as {@link int}
     */
    public int getOffset() {
        return offset;
    }

    /**
     * Number of bytes offset from the beginning or the NV value.
     *
     * @param offset the offset to set as {@link int}
     */
    public void setOffset(int offset) {
        this.offset = offset;
    }

    /**
     * Number of bytes to read
     *
     * @return the current length as {@link int}
     */
    public int getLength() {
        return length;
    }

    /**
     * Number of bytes to read
     *
     * @param length the length to set as {@link int}
     */
    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public boolean matchSreqError(ZstackRpcSreqErrorSrsp response) {
        return (((response.getReqCmd0() & 0x1F) == ZSTACK_SYS) && (response.getReqCmd1() == 0x33));
    }

    @Override
    public int[] serialize() {
        // Serialize the header
        serializeHeader(ZSTACK_SREQ, ZSTACK_SYS, 0x33);

        // Serialize the fields
        serializer.serializeUInt8(sysId);
        serializer.serializeUInt16(itemId);
        serializer.serializeUInt16(subId);
        serializer.serializeUInt16(offset);
        serializer.serializeUInt8(length);
        return getPayload();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(147);
        builder.append("ZstackSysNvReadSreq [sysId=");
        builder.append(sysId);
        builder.append(", itemId=");
        builder.append(itemId);
        builder.append(", subId=");
        builder.append(subId);
        builder.append(", offset=");
        builder.append(offset);
        builder.append(", length=");
        builder.append(length);
        builder.append(']');
        return builder.toString();
    }
}

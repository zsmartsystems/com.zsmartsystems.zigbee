/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.zstack.api.sbl;

import com.zsmartsystems.zigbee.dongle.zstack.api.ZstackFrameResponse;
import com.zsmartsystems.zigbee.dongle.zstack.api.ZstackResponseCode;
import javax.annotation.Generated;

/**
 * Class to implement the Z-Stack command <b>SB_HANDSHAKE_CMD</b>.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 */

@Generated(value = "com.zsmartsystems.zigbee.dongle.zstack.autocode.CommandGenerator", date = "Sun Mar 26 09:52:48 CEST 2023")
public class ZstackSbHandshakeCmdSrsp extends ZstackFrameResponse {

    /**
     * 0x00 - SUCCESS 0x01 - FAILURE
     */
    private ZstackResponseCode status;

    /**
     */
    private int bootloaderRevision;

    /**
     */
    private int deviceType;

    /**
     * The maximum data size to use with Read / Write command
     */
    private int bufferLength;

    /**
     * 0x800 – CC2538 flash page size
     */
    private int pageSize;

    /**
     * Response and Handler constructor
     */
    public ZstackSbHandshakeCmdSrsp(int[] inputBuffer) {
        // Super creates deserializer and reads header fields
        super(inputBuffer);

        synchronousCommand = true;

        // Deserialize the fields
        status = ZstackResponseCode.valueOf(deserializer.deserializeUInt8());
        bootloaderRevision = deserializer.deserializeUInt32();
        deviceType = deserializer.deserializeUInt8();
        bufferLength = deserializer.deserializeUInt32();
        pageSize = deserializer.deserializeUInt32();
    }

    /**
     * 0x00 - SUCCESS 0x01 - FAILURE
     *
     * @return the current status as {@link ZstackResponseCode}
     */
    public ZstackResponseCode getStatus() {
        return status;
    }

    /**
     * 0x00 - SUCCESS 0x01 - FAILURE
     *
     * @param status the Status to set as {@link ZstackResponseCode}
     */
    public void setStatus(ZstackResponseCode status) {
        this.status = status;
    }

    /**
     *
     *
     * @return the current bootloaderRevision as {@link int}
     */
    public int getBootloaderRevision() {
        return bootloaderRevision;
    }

    /**
     *
     *
     * @param bootloaderRevision the BootloaderRevision to set as {@link int}
     */
    public void setBootloaderRevision(int bootloaderRevision) {
        this.bootloaderRevision = bootloaderRevision;
    }

    /**
     *
     *
     * @return the current deviceType as {@link int}
     */
    public int getDeviceType() {
        return deviceType;
    }

    /**
     *
     *
     * @param deviceType the DeviceType to set as {@link int}
     */
    public void setDeviceType(int deviceType) {
        this.deviceType = deviceType;
    }

    /**
     * The maximum data size to use with Read / Write command
     *
     * @return the current bufferLength as {@link int}
     */
    public int getBufferLength() {
        return bufferLength;
    }

    /**
     * The maximum data size to use with Read / Write command
     *
     * @param bufferLength the BufferLength to set as {@link int}
     */
    public void setBufferLength(int bufferLength) {
        this.bufferLength = bufferLength;
    }

    /**
     * 0x800 – CC2538 flash page size
     *
     * @return the current pageSize as {@link int}
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 0x800 – CC2538 flash page size
     *
     * @param pageSize the PageSize to set as {@link int}
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(152);
        builder.append("ZstackSbHandshakeCmdSrsp [status=");
        builder.append(status);
        builder.append(", bootloaderRevision=");
        builder.append(bootloaderRevision);
        builder.append(", deviceType=");
        builder.append(deviceType);
        builder.append(", bufferLength=");
        builder.append(bufferLength);
        builder.append(", pageSize=");
        builder.append(pageSize);
        builder.append(']');
        return builder.toString();
    }
}

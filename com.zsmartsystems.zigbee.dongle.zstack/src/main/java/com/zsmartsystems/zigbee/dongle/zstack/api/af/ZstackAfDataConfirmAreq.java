/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.zstack.api.af;

import com.zsmartsystems.zigbee.dongle.zstack.api.ZstackFrameResponse;
import com.zsmartsystems.zigbee.dongle.zstack.api.ZstackResponseCode;

/**
 * Class to implement the Z-Stack command <b>AF_DATA_CONFIRM</b>.
 * <p>
 * This command is sent by the device to the user after it receives an AF_DATA_REQUEST. For each AF_DATA_REQUEST, a
 * AF_DATA_CONFIRM is always returned. If APS acknowledgement was used for the AF_DATA_REQUEST, the confirm carries the status
 * of whether the APS acknowledgement was received or not (ZApsNoAck – 0xb7). If APS acknowledgement was not used, then the confirm
 * carries the status of whether the MAC acknowledgement (“next hop” acknowledgment) was received or not (ZMacNoACK – 0xe9). This
 * also applies to packets that are sent using AF_DATA_REQUEST_EXT and AF_DATA_STORE. For APS fragmented packets, the value of
 * the configuration item ZCD_NV_APSF_WINDOW_SIZE determines when an AF_DATA_CONFIRM that carries the status of the APS
 * acknowledgement is received.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson
 */
public class ZstackAfDataConfirmAreq extends ZstackFrameResponse {

    /**
     * Status is either Success (0) or Failure (1).
     */
    private ZstackResponseCode status;

    /**
     * Endpoint of the device.
     */
    private int endpoint;

    /**
     * Specifies the transaction sequence number of the message.
     */
    private int transId;

    /**
     * Response and Handler constructor
     */
    public ZstackAfDataConfirmAreq(int[] inputBuffer) {
        // Super creates deserializer and reads header fields
        super(inputBuffer);

        // Deserialize the fields
        status = ZstackResponseCode.valueOf(deserializer.deserializeUInt8());
        endpoint = deserializer.deserializeUInt8();
        transId = deserializer.deserializeUInt8();
    }

    /**
     * Status is either Success (0) or Failure (1).
     *
     * @return the current status as {@link ZstackResponseCode}
     */
    public ZstackResponseCode getStatus() {
        return status;
    }

    /**
     * Status is either Success (0) or Failure (1).
     *
     * @param status the Status to set as {@link ZstackResponseCode}
     */
    public void setStatus(ZstackResponseCode status) {
        this.status = status;
    }

    /**
     * Endpoint of the device.
     *
     * @return the current endpoint as {@link int}
     */
    public int getEndpoint() {
        return endpoint;
    }

    /**
     * Endpoint of the device.
     *
     * @param endpoint the Endpoint to set as {@link int}
     */
    public void setEndpoint(int endpoint) {
        this.endpoint = endpoint;
    }

    /**
     * Specifies the transaction sequence number of the message.
     *
     * @return the current transId as {@link int}
     */
    public int getTransId() {
        return transId;
    }

    /**
     * Specifies the transaction sequence number of the message.
     *
     * @param transId the TransId to set as {@link int}
     */
    public void setTransId(int transId) {
        this.transId = transId;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(101);
        builder.append("ZstackAfDataConfirmAreq [status=");
        builder.append(status);
        builder.append(", endpoint=");
        builder.append(String.format("%02X", endpoint));
        builder.append(", transId=");
        builder.append(String.format("%02X", transId));
        builder.append(']');
        return builder.toString();
    }
}

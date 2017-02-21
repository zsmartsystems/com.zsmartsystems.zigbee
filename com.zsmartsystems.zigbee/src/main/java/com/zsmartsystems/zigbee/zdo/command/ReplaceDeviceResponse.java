package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zdo.ZdoResponse;

/**
 * Replace Device Response value object class.
 * <p>
 * The Replace_Device_rsp is generated from a primary binding table cache device
 * in response to a Replace_Device_req and contains the status of the request. This
 * command shall be unicast to the requesting device. If the device receiving the
 * Replace_Device_req is not a primary binding table cache, a Status of
 * NOT_SUPPORTED is returned. The primary binding table cache shall search its
 * binding table for entries whose source address and source endpoint, or whose
 * destination address and destination endpoint match OldAddress and OldEndpoint,
 * as described in the text for Replace_Device_req. It shall change these entries to
 * have NewAddress and possibly NewEndpoint. It shall then return a response of
 * SUCCESS.
 * <p>
 * Cluster: <b>General</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>generic</b> command used across the profile.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class ReplaceDeviceResponse extends ZdoResponse {
    /**
     * Default constructor.
     */
    public ReplaceDeviceResponse() {
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("ReplaceDeviceResponse");
        builder.append(super.toString());
        return builder.toString();
    }

}

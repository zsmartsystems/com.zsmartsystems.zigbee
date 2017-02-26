package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zdo.ZdoResponse;

/**
 * Match Descriptor Response value object class.
 * <p>
 * The Match_Desc_rsp is generated by a remote device in response to a
 * Match_Desc_req either broadcast or directed to the remote device. This command
 * shall be unicast to the originator of the Match_Desc_req command.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class MatchDescriptorResponse extends ZdoResponse {
    /**
     * Default constructor.
     */
    public MatchDescriptorResponse() {
        clusterId = 0x8006;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("MatchDescriptorResponse");
        builder.append(super.toString());
        return builder.toString();
    }

}

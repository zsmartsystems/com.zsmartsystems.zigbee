package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zdo.ZdoResponse;

/**
 * Extended Simple Descriptor Response value object class.
 * <p>
 * The Extended_Simple_Desc_rsp is generated by a remote device in response to an
 * Extended_Simple_Desc_req directed to the remote device. This command shall
 * be unicast to the originator of the Extended_Simple_Desc_req command.
 * <p>
 * Cluster: <b>General</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>generic</b> command used across the profile.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class ExtendedSimpleDescriptorResponse extends ZdoResponse {
    /**
     * Default constructor.
     */
    public ExtendedSimpleDescriptorResponse() {
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("ExtendedSimpleDescriptorResponse");
        builder.append(super.toString());
        return builder.toString();
    }

}

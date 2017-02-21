package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zdo.ZdoRequest;

/**
 * Match Descriptor Request value object class.
 * <p>
 * The Match_Desc_req command is generated from a local device wishing to find
 * remote devices supporting a specific simple descriptor match criterion. This
 * command shall either be broadcast to all devices for which macRxOnWhenIdle =
 * TRUE, or unicast. If the command is unicast, it shall be directed either to the
 * remote device itself or to an alternative device that contains the discovery
 * information of the remote device.
 * <p>
 * Cluster: <b>General</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>generic</b> command used across the profile.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class MatchDescriptorRequest extends ZdoRequest {
    /**
     * Default constructor.
     */
    public MatchDescriptorRequest() {
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("MatchDescriptorRequest");
        builder.append(super.toString());
        return builder.toString();
    }

}

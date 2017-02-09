package com.zsmartsystems.zigbee.zcl.clusters.groups;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

import java.util.List;
import com.zsmartsystems.zigbee.zcl.field.Unsigned16BitInteger;

/**
 * <p>
 * Get Group Membership Command value object class.
 * <p>
 * Cluster: <b>Groups</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Groups cluster.
 * <p>
 * The ZigBee specification provides the capability for group addressing. That is,
 * any endpoint on any device may be assigned to one or more groups, each labeled
 * with a 16-bit identifier (0x0001 – 0xfff7), which acts for all intents and purposes
 * like a network address. Once a group is established, frames, sent using the
 * APSDE-DATA.request primitive and having a DstAddrMode of 0x01, denoting
 * group addressing, will be delivered to every endpoint assigned to the group
 * address named in the DstAddr parameter of the outgoing APSDE-DATA.request
 * primitive on every device in the network for which there are such endpoints.
 * <br>
 * Management of group membership on each device and endpoint is implemented
 * by the APS, but the over-the-air messages that allow for remote management and
 * commissioning of groups are defined here in the cluster library on the theory that,
 * while the basic group addressing facilities are integral to the operation of the
 * stack, not every device will need or want to implement this management cluster.
 * Furthermore, the placement of the management commands here allows developers
 * of proprietary profiles to avoid implementing the library cluster but still exploit
 * group addressing
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class GetGroupMembershipCommand extends ZclCommand {
    /**
     * Group count command message field.
     */
    private Integer groupCount;

    /**
     * Group list command message field.
     */
    private List<Unsigned16BitInteger> groupList;

    /**
     * Default constructor.
     */
    public GetGroupMembershipCommand() {
        genericCommand = false;
        clusterId = 4;
        commandId = 2;
        commandDirection = true;
    }

    /**
     * Gets Group count.
     * @return the Group count
     */
    public Integer getGroupCount() {
        return groupCount;
    }

    /**
     * Sets Group count.
     * @param groupCount the Group count
     */
    public void setGroupCount(final Integer groupCount) {
        this.groupCount = groupCount;
    }

    /**
     * Gets Group list.
     * @return the Group list
     */
    public List<Unsigned16BitInteger> getGroupList() {
        return groupList;
    }

    /**
     * Sets Group list.
     * @param groupList the Group list
     */
    public void setGroupList(final List<Unsigned16BitInteger> groupList) {
        this.groupList = groupList;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(groupCount, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(groupList, ZclDataType.N_X_UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        groupCount = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        groupList = (List<Unsigned16BitInteger>) deserializer.deserialize(ZclDataType.N_X_UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(super.toString());
        builder.append(", groupCount=");
        builder.append(groupCount);
        builder.append(", groupList=");
        builder.append(groupList);
        return builder.toString();
    }

}

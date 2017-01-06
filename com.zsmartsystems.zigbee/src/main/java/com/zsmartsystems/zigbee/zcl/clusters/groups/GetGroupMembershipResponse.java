package com.zsmartsystems.zigbee.zcl.clusters.groups;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zcl.protocol.ZclClusterType;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.zsmartsystems.zigbee.zcl.field.Unsigned16BitInteger;

/**
 * <p>
 * Get Group Membership Response value object class.
 * </p>
 * <p>
 * Cluster: <b>Groups</b>. Command is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the Groups cluster.
 * </p>
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
 * </p>
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 * </p>
 */
public class GetGroupMembershipResponse extends ZclCommand {
    /**
     * Capacity command message field.
     */
    private Integer capacity;

    /**
     * Group count command message field.
     */
    private Integer groupCount;

    /**
     * Group list command message field.
     */
    private List<Unsigned16BitInteger> groupList;

    /**
     * Default constructor setting the command type field.
     */
    public GetGroupMembershipResponse() {
        genericCommand = false;
        clusterId = 4;
        commandId = 2;
        commandDirection = false;
    }

    /**
     * Constructor copying field values from command message.
     *
     * @param fields a {@link Map} containing the value {@link Object}s
     */
    public GetGroupMembershipResponse(final Map<Integer, Object> fields) {
        this();
        capacity = (Integer) fields.get(0);
        groupCount = (Integer) fields.get(1);
        groupList = (List<Unsigned16BitInteger>) fields.get(2);
    }

    /**
     * Gets Capacity.
     * @return the Capacity
     */
    public Integer getCapacity() {
        return capacity;
    }

    /**
     * Sets Capacity.
     * @param capacity the Capacity
     */
    public void setCapacity(final Integer capacity) {
        this.capacity = capacity;
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
        serializer.serialize(capacity, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(groupCount, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(groupList, ZclDataType.N_X_UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        capacity = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        groupCount = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        groupList = (List<Unsigned16BitInteger>) deserializer.deserialize(ZclDataType.N_X_UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(super.toString());
        builder.append(", ");
        builder.append("capacity = ");
        builder.append(capacity);
        builder.append(", ");
        builder.append("groupCount = ");
        builder.append(groupCount);
        builder.append(", ");
        builder.append("groupList = ");
        builder.append(groupList);
        return builder.toString();
    }

}

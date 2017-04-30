package com.zsmartsystems.zigbee.zcl.clusters.general;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

import java.util.List;
import com.zsmartsystems.zigbee.zcl.field.AttributeInformation;

/**
 * Discover Attributes Response value object class.
 * <p>
 * The discover attributes response command is generated in response to a discover
 * attributes command.
 * <p>
 * Cluster: <b>General</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>generic</b> command used across the profile.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class DiscoverAttributesResponse extends ZclCommand {
    /**
     * Command identifier command message field.
     */
    private Boolean commandIdentifier;

    /**
     * Information command message field.
     */
    private List<AttributeInformation> information;

    /**
     * Default constructor.
     */
    public DiscoverAttributesResponse() {
        genericCommand = true;
        commandId = 13;
        commandDirection = true;
    }

    /**
     * Sets the cluster ID for <i>generic</i> commands. {@link DiscoverAttributesResponse} is a <i>generic</i> command.
     * <p>
     * For commands that are not <i>generic</i>, this method will do nothing as the cluster ID is fixed.
     * To test if a command is <i>generic</i>, use the {@link #isGenericCommand} method.
     *
     * @param clusterId the cluster ID used for <i>generic</i> commands as an {@link Integer}
     */
    @Override
    public void setClusterId(Integer clusterId) {
        this.clusterId = clusterId;
    }

    /**
     * Gets Command identifier.
     *
     * @return the Command identifier
     */
    public Boolean getCommandIdentifier() {
        return commandIdentifier;
    }

    /**
     * Sets Command identifier.
     *
     * @param commandIdentifier the Command identifier
     */
    public void setCommandIdentifier(final Boolean commandIdentifier) {
        this.commandIdentifier = commandIdentifier;
    }

    /**
     * Gets Information.
     *
     * @return the Information
     */
    public List<AttributeInformation> getInformation() {
        return information;
    }

    /**
     * Sets Information.
     *
     * @param information the Information
     */
    public void setInformation(final List<AttributeInformation> information) {
        this.information = information;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(commandIdentifier, ZclDataType.BOOLEAN);
        serializer.serialize(information, ZclDataType.N_X_ATTRIBUTE_INFORMATION);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        commandIdentifier = (Boolean) deserializer.deserialize(ZclDataType.BOOLEAN);
        information = (List<AttributeInformation>) deserializer.deserialize(ZclDataType.N_X_ATTRIBUTE_INFORMATION);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("DiscoverAttributesResponse [");
        builder.append(super.toString());
        builder.append(", commandIdentifier=");
        builder.append(commandIdentifier);
        builder.append(", information=");
        builder.append(information);
        builder.append("]");
        return builder.toString();
    }

}

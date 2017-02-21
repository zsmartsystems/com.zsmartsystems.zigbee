package com.zsmartsystems.zigbee.zcl.clusters.general;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Discover Attributes Command value object class.
 * <p>
 * The discover attributes command is generated when a remote device wishes to
 * discover the identifiers and types of the attributes on a device which are supported
 * within the cluster to which this command is directed.
 * <p>
 * Cluster: <b>General</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>generic</b> command used across the profile.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class DiscoverAttributesCommand extends ZclCommand {
    /**
     * Start attribute identifier command message field.
     */
    private Integer startAttributeIdentifier;

    /**
     * Maximum attribute identifiers command message field.
     */
    private Integer maximumAttributeIdentifiers;

    /**
     * Default constructor.
     */
    public DiscoverAttributesCommand() {
        genericCommand = true;
        commandId = 12;
        commandDirection = true;
    }

    /**
     * Sets the cluster ID for <i>generic</i> commands. {@link DiscoverAttributesCommand} is a <i>generic</i> command.
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
     * Gets Start attribute identifier.
     *
     * @return the Start attribute identifier
     */
    public Integer getStartAttributeIdentifier() {
        return startAttributeIdentifier;
    }

    /**
     * Sets Start attribute identifier.
     *
     * @param startAttributeIdentifier the Start attribute identifier
     */
    public void setStartAttributeIdentifier(final Integer startAttributeIdentifier) {
        this.startAttributeIdentifier = startAttributeIdentifier;
    }

    /**
     * Gets Maximum attribute identifiers.
     *
     * @return the Maximum attribute identifiers
     */
    public Integer getMaximumAttributeIdentifiers() {
        return maximumAttributeIdentifiers;
    }

    /**
     * Sets Maximum attribute identifiers.
     *
     * @param maximumAttributeIdentifiers the Maximum attribute identifiers
     */
    public void setMaximumAttributeIdentifiers(final Integer maximumAttributeIdentifiers) {
        this.maximumAttributeIdentifiers = maximumAttributeIdentifiers;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(startAttributeIdentifier, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(maximumAttributeIdentifiers, ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        startAttributeIdentifier = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        maximumAttributeIdentifiers = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(super.toString());
        builder.append(", startAttributeIdentifier=");
        builder.append(startAttributeIdentifier);
        builder.append(", maximumAttributeIdentifiers=");
        builder.append(maximumAttributeIdentifiers);
        return builder.toString();
    }

}

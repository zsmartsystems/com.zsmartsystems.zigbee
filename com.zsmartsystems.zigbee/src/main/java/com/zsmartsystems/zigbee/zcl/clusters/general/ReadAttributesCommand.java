package com.zsmartsystems.zigbee.zcl.clusters.general;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

import java.util.List;
import com.zsmartsystems.zigbee.zcl.field.AttributeIdentifier;

/**
 * <p>
 * Read Attributes Command value object class.
 * <p>
 * The read attributes command is generated when a device wishes to determine the
 * values of one or more attributes located on another device. Each attribute
 * identifier field shall contain the identifier of the attribute to be read.
 * <p>
 * Cluster: <b>General</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>generic</b> command used across the profile.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class ReadAttributesCommand extends ZclCommand {
    /**
     * Identifiers command message field.
     */
    private List<AttributeIdentifier> identifiers;

    /**
     * Default constructor.
     */
    public ReadAttributesCommand() {
        genericCommand = true;
        commandId = 0;
        commandDirection = true;
    }

    /**
     * Sets the cluster ID for <i>generic</i> commands. {@link ReadAttributesCommand} is a <i>generic</i> command.
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
     * Gets Identifiers.
     * @return the Identifiers
     */
    public List<AttributeIdentifier> getIdentifiers() {
        return identifiers;
    }

    /**
     * Sets Identifiers.
     * @param identifiers the Identifiers
     */
    public void setIdentifiers(final List<AttributeIdentifier> identifiers) {
        this.identifiers = identifiers;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(identifiers, ZclDataType.N_X_ATTRIBUTE_IDENTIFIER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        identifiers = (List<AttributeIdentifier>) deserializer.deserialize(ZclDataType.N_X_ATTRIBUTE_IDENTIFIER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(super.toString());
        builder.append(", ");
        builder.append("identifiers = ");
        builder.append(identifiers);
        return builder.toString();
    }

}

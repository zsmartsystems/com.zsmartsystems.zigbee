package com.zsmartsystems.zigbee.zcl;

import java.util.Map;

import com.zsmartsystems.zigbee.Command;
import com.zsmartsystems.zigbee.ZigBeeAddress;
import com.zsmartsystems.zigbee.zcl.protocol.ZclClusterType;

/**
 * Base class for value object classes holding ZCL commands.
 *
 * @author Tommi S.E. Laukkanen
 * @author Chris Jackson
 */
public abstract class ZclCommand extends Command {
    /**
     * The source address.
     */
    private ZigBeeAddress sourceAddress;

    /**
     * The destination address.
     */
    private ZigBeeAddress destinationAddress;

    /**
     *
     */
    protected boolean genericCommand;

    /**
     * The cluster ID
     */
    protected int clusterId;

    /**
     * The command ID
     */
    protected int commandId;

    /**
     * The transaction ID.
     */
    private Integer transactionId;

    /**
     * <p>
     * The command direction for this command.
     * </p>
     * <p>
     * If this command is to be sent <b>to</b> the server, this will return <i>true</i>.
     * If this command is to be sent <b>from</b> the server, this will return <i>false</i>.
     * </p>
     */
    protected boolean commandDirection;

    /**
     * Field configuration
     */
    // protected final static Map<Integer, ZclField> fields = new HashMap<Integer, ZclField>();

    /**
     * Default constructor.
     */
    public ZclCommand() {
    }

    /**
     * Constructor which copies field values from command message.
     *
     * @param commandMessage the command message
     */
    // public ZclCommand(final ZclCommandMessage commandMessage) {
    // this.sourceAddress = commandMessage.getSourceAddress();
    // this.destinationAddress = commandMessage.getDestinationAddress();
    // this.type = commandMessage.getType();
    // this.clusterId = commandMessage.getClusterId();
    // this.transactionId = commandMessage.getTransactionId();
    // }

    /**
     * Gets the type
     *
     * @return the type
     */
    // public ZclCommandType getType() {
    // return type;
    // }

    /**
     * Sets the command
     *
     * @param type the command
     */
    // public void setType(final ZclCommandType type) {
    // this.type = type;
    // }

    /**
     * Gets destination address.
     *
     * @return the destination address.
     */
    public ZigBeeAddress getDestinationAddress() {
        return destinationAddress;
    }

    /**
     * Sets destination address.
     *
     * @param destinationAddress the destination address.
     */
    public void setDestinationAddress(final ZigBeeAddress destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    /**
     * Gets source address.
     *
     * @return the source address
     */
    public ZigBeeAddress getSourceAddress() {
        return sourceAddress;
    }

    /**
     * Sets source address.
     *
     * @param sourceAddress the source address
     */
    public void setSourceAddress(final ZigBeeAddress sourceAddress) {
        this.sourceAddress = sourceAddress;
    }

    /**
     * <p>
     * Sets the cluster ID for <i>generic</i> commands.
     * <p/p>
     * <p>
     * For commands that are not <i>generic</i>, this method will do nothing as the cluster ID is fixed.
     * To test if a command is <i>generic</i>, use the {@link #isGenericCommand} method.
     * </p>
     *
     * @param clusterId the cluster ID used for <i>generic</i> commands as an {@link Integer}
     */
    public void setClusterId(Integer clusterId) {
        // Default implementation does nothing. Generic commands will override this.
    }

    /**
     * Gets the cluster ID.
     *
     * @return the cluster ID.
     */
    public Integer getClusterId() {
        return clusterId;
    }

    /**
     * Gets the command ID.
     *
     * @return the cluster ID.
     */
    public Integer getCommandId() {
        return commandId;
    }

    /**
     * Returns true if this is a <i>generic</i> command. <i>Generic</i> commands are not cluster specific but are
     * available across the profile.
     *
     * @return true if this is a <i>generic</i> command.
     */
    public boolean isGenericCommand() {
        return genericCommand;
    }

    /**
     * <p>
     * Gets the command direction for this command.
     * </p>
     * <p>
     * If this command is to be sent <b>to</b> the server, this will return <i>true</i>.
     * If this command is to be sent <b>from</b> the server, this will return <i>false</i>.
     * </p>
     *
     * @return true if this is sent to the server, false if this is a response from the server.
     */
    public boolean getCommandDirection() {
        return commandDirection;
    }

    /**
     * Gets the transaction ID.
     *
     * @return the transaction ID
     */
    public Integer getTransactionId() {
        return transactionId;
    }

    /**
     * Sets the transaction ID.
     *
     * @param transactionId the transaction ID
     */
    public void setTransactionId(final int transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * Converts ZclCommand to ZclCommandMessage.
     *
     * @return the ZclCommandMessage
     */
    // public ZclCommandMessage toCommandMessage() {
    // final ZclCommandMessage commandMessage = new ZclCommandMessage();
    // commandMessage.setSourceAddress(sourceAddress);
    // commandMessage.setDestinationAddress(destinationAddress);
    // commandMessage.setType(type);
    // commandMessage.setClusterId(clusterId);
    // commandMessage.setTransactionId(transactionId);
    // return commandMessage;
    // }

    /**
     * Get the list of fields for this command
     *
     * @return {@link Collection} of {@link ZclField}
     */
    // public Collection<ZclField> getFields() {
    // return fields.values();
    // }

    /**
     * Update the field values
     *
     * @param values a {@link Map} of field numbers mapped to {@link Object}
     */
    // public void setFieldValues(final Map<Integer, Object> values) {
    // for (Integer field : values.keySet()) {
    // fields.get(field).setValue(values.get(field));
    // }
    // }

    public void serialize(ZclFieldSerializer serializer) {
    }

    public void deserialize(final ZclFieldDeserializer deserializer) {
    }

    @Override
    public String toString() {
        Integer resolvedClusterId = getClusterId();
        if (resolvedClusterId == null) {
            // resolvedClusterId = type.getClusterType().getId();
        }
        return ZclClusterType.getValueById(resolvedClusterId).getLabel() + " - " + sourceAddress + " -> "
                + destinationAddress + " tid=" + transactionId;
        // return ZclClusterType.getValueById(resolvedClusterId).getLabel() + " - " + type + " " + sourceAddress + " ->
        // "
        // + destinationAddress + " tid=" + transactionId;
    }
}

package com.zsmartsystems.zigbee.zcl;

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

    public void serialize(ZclFieldSerializer serializer) {
        // Default implementation does nothing - overridden by each class
    }

    public void deserialize(final ZclFieldDeserializer deserializer) {
        // Default implementation does nothing - overridden by each class
    }

    @Override
    public String toString() {
        Integer resolvedClusterId = getClusterId();
        return ZclClusterType.getValueById(resolvedClusterId).getLabel() + " - " + sourceAddress + " -> "
                + destinationAddress + " TransID=" + transactionId;
    }
}

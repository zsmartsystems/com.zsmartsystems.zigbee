package com.zsmartsystems.zigbee;

import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;

/**
 * Base class for all commands.
 * <p>
 * The base class contains methods that are not part of the application layer (eg ZCL or ZDO).
 * These members may be part of other layers but are provide in the command for convenience.
 *
 * @author Chris Jackson
 */
public class Command {
    /**
     * The source address.
     */
    private ZigBeeAddress sourceAddress;

    /**
     * The destination address.
     */
    private ZigBeeAddress destinationAddress;

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
     * Serialize the command class to the format required by the transport layer.
     *
     * @param serializer the {@link ZclFieldSerializer} to use
     */
    public void serialize(ZclFieldSerializer serializer) {
        // Default implementation does nothing - overridden by each class
    }

    /**
     * Deserialize the command from the format required by the transport layer and into the command class.
     *
     * @param serializer the {@link ZclFieldSerializer} to use
     */
    public void deserialize(final ZclFieldDeserializer deserializer) {
        // Default implementation does nothing - overridden by each class
    }

    @Override
    public String toString() {
        return sourceAddress + " -> " + destinationAddress;
    }
}

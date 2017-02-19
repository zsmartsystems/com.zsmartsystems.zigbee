package com.zsmartsystems.zigbee;

import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;

/**
 * Common base class for all commands.
 *
 * @author Chris Jackson
 */
public class Command {
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
}

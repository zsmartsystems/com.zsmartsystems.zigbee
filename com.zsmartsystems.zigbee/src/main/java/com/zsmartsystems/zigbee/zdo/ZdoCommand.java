package com.zsmartsystems.zigbee.zdo;

import com.zsmartsystems.zigbee.Command;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Base class for value object classes holding ZDO commands.
 *
 * @author Chris Jackson
 */
public abstract class ZdoCommand extends Command {

    @Override
    public void serialize(ZclFieldSerializer serializer) {
        serializer.serialize(0, ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("=");
        // builder.append(sourceAddress);
        return builder.toString();
    }
}

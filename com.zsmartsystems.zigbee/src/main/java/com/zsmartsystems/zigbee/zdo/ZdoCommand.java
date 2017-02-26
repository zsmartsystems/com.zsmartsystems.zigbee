package com.zsmartsystems.zigbee.zdo;

import com.zsmartsystems.zigbee.Command;

/**
 * Base class for value object classes holding ZDO commands.
 *
 * @author Chris Jackson
 */
public abstract class ZdoCommand extends Command {
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("=");
        // builder.append(sourceAddress);
        return builder.toString();
    }
}

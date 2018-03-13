/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl;

import com.zsmartsystems.zigbee.ZigBeeCommand;
import com.zsmartsystems.zigbee.zcl.protocol.ZclClusterType;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;

/**
 * Base class for value object classes holding ZCL commands, extended from {@link ZigBeeCommand}.
 *
 * @author Tommi S.E. Laukkanen
 * @author Chris Jackson
 */
public abstract class ZclCommand extends ZigBeeCommand {

    /**
     * True if this is a generic command
     */
    protected boolean genericCommand;

    /**
     * The command ID
     */
    protected int commandId;

    /**
     * The command direction for this command.
     * <p>
     * If this command is to be sent <b>to</b> the server, this will return <i>true</i>.
     * If this command is to be sent <b>from</b> the server, this will return <i>false</i>.
     */
    protected ZclCommandDirection commandDirection;

    /**
     * Sets the cluster ID for <i>generic</i> commands.
     * <p>
     * For commands that are not <i>generic</i>, this method will do nothing as the cluster ID is fixed.
     * To test if a command is <i>generic</i>, use the {@link #isGenericCommand} method.
     *
     * @param clusterId the cluster ID used for <i>generic</i> commands as an {@link Integer}
     */
    public void setClusterId(Integer clusterId) {
        // Default implementation does nothing. Generic commands will override this.
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
     * Gets the command direction for this command.
     *
     * @return {@link ZclCommandDirection} with the command direction.
     */
    public ZclCommandDirection getCommandDirection() {
        return commandDirection;
    }

    /**
     * Sets the command direction for this command.
     *
     * @param commandDirection the {@link ZclCommandDirection}
     */
    public void setCommandDirection(ZclCommandDirection commandDirection) {
        this.commandDirection = commandDirection;
    }

    @Override
    public String toString() {
        Integer resolvedClusterId = getClusterId();
        final StringBuilder builder = new StringBuilder();
        builder.append(ZclClusterType.getValueById(resolvedClusterId).getLabel());
        builder.append(": ");
        builder.append(super.toString());
        return builder.toString();
    }
}

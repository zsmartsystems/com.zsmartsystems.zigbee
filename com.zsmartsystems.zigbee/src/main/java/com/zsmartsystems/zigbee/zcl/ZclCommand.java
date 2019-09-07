/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
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
     * True if the default response is disabled
     */
    protected boolean disableDefaultResponse;

    /**
     * The command direction for this command.
     * <p>
     * If this command is to be sent <b>to</b> the server, this will return <i>true</i>.
     * If this command is to be sent <b>from</b> the server, this will return <i>false</i>.
     */
    protected ZclCommandDirection commandDirection;

    /**
     * The manufacturer code; this value needs to be set for manufacturer-specific commands. If this value is null, then
     * the command is assumed to be not manufacturer-specific.
     * <p>
     * Examples for manufacturer-specific commands are:
     * <ul>
     * <li>Commands from a manufacturer-specific cluster
     * <li>Manufacturer-specific commands added to a non-manufacturer-specific cluster
     * <li>Generic commands that target manufacturer-specific attributes
     * </ul>
     */
    private Integer manufacturerCode = null;

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

    /**
     * Gets whether the manufacturer-specific bit needs to be set for this command.
     *
     * @return whether the manufacturer-specific bit needs to be set for this command
     */
    public boolean isManufacturerSpecific() {
        return manufacturerCode != null;
    }

    /**
     * Returns the manufacturer code (should be ignored if this command does not need the manufacturer-specific bit to
     * be set).
     *
     * @return the manufacturer code; null if no manufacturer code has been set
     */
    public Integer getManufacturerCode() {
        return manufacturerCode;
    }

    /**
     * Sets the manufacturer code
     *
     * @param manufacturerCode the manufacturer code
     */
    protected void setManufacturerCode(int manufacturerCode) {
        this.manufacturerCode = manufacturerCode;
    }

    /**
     * When set to true, the default response message will not be generated
     * 
     * @return the disableDefaultResponse flag - when set to true, the default response message will not be generated
     */
    public boolean isDisableDefaultResponse() {
        return disableDefaultResponse;
    }

    /**
     * When set to true, the default response message will not be generated
     *
     * @param disableDefaultResponse true if the default response is not required
     */
    public void setDisableDefaultResponse(boolean disableDefaultResponse) {
        this.disableDefaultResponse = disableDefaultResponse;
    }

    @Override
    public String toString() {
        Integer resolvedClusterId = getClusterId();
        final StringBuilder builder = new StringBuilder(50);
        ZclClusterType clusterType = ZclClusterType.getValueById(resolvedClusterId);
        builder.append(clusterType != null ? clusterType.getLabel() : Integer.toHexString(resolvedClusterId));
        builder.append(": ");
        builder.append(super.toString());
        return builder.toString();
    }
}

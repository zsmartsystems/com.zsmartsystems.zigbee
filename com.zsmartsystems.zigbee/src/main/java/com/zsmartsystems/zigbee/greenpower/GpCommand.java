package com.zsmartsystems.zigbee.greenpower;

import com.zsmartsystems.zigbee.ZigBeeCommand;

/**
 * Base class for the implementation of Green Power commands, extended from {@link ZigBeeCommand}.
 */
public class GpCommand extends ZigBeeCommand{

	/**
	 * The command ID
	 */
	protected int commandId;

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
	
	public void setCommandId(int commandId) {
		this.commandId=commandId;
	}
}

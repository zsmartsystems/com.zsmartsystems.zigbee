package com.zsmartsystems.zigbee.greenpower;

import com.zsmartsystems.zigbee.ZigBeeCommand;

/**
 * Base class for the implementation of Green Power commands, extended from {@link ZigBeeCommand}.
 */
public class GpCommand {
	
	/**
     * The source GPD's ID.
     */
	private int SourceID;
	/**
	 * The source Endpoint.
	 */
	private int Endpoint;

	/**
	 * The security frame counter used to prevent the reception of duplicate frames.
	 */
	private int SecurityFrameCounter;
	
	/**
	 * The Command ID
	 */
	protected int commandId;

	/**
	 * The GPD command payload.
	 */
	private int[] payload;

	/**
	 * Gets the command ID.
	 *
	 * @return the cluster ID.
	 */
	public Integer getCommandId() {
		return commandId;
	}
	
	/**
	 * Sets the commandId.
	 * 
	 * @param commandId
	 */
	public void setCommandId(int commandId) {
		this.commandId=commandId;
	}
	public int getSourceID() {
		return SourceID;
	}

	public void setSourceID(int sourceID) {
		SourceID = sourceID;
	}

	public int getEndpoint() {
		return Endpoint;
	}

	public void setEndpoint(int endpoint) {
		Endpoint = endpoint;
	}

	public int getSecurityFrameCounter() {
		return SecurityFrameCounter;
	}

	public void setSecurityFrameCounter(int securityFrameCounter) {
		SecurityFrameCounter = securityFrameCounter;
	}

	public int[] getPayload() {
		return payload;
	}

	public void setPayload(int[] payload) {
		this.payload = payload;
	}
}

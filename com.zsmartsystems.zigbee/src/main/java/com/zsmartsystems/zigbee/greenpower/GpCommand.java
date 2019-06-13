package com.zsmartsystems.zigbee.greenpower;

import com.zsmartsystems.zigbee.ZigBeeCommand;

/**
 * Base class for the implementation of Green Power commands, extended from {@link ZigBeeCommand}.
 */
public class GpCommand {
	
	private boolean autoCommissioning;
	/**
     * The transaction ID.
     */
    private Integer transactionId;
    
    /**
	 * The source GPD's address
	 */
	private GpAddress sourceAddress;

	/**
	 * The GPDF's source endpoint
	 */
	private int endpoint;
	
	/**
	 * the GPDF's sourceID
	 */
	private int sourceId;
	
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
	 * The GPD's mic.
	 */
	private int mic;
	
	public Integer getCommandId() {
		return commandId;
	}
	public void setCommandId(int commandId) {
		this.commandId=commandId;
	}
	public boolean isAutoCommissioning() {
		return this.autoCommissioning;
	}
	public void setAutoCommissioning(boolean autoCommissioning) {
		this.autoCommissioning=autoCommissioning;
	}
	public Integer getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(Integer transactionId) {
		this.transactionId=transactionId;
	}
	public GpAddress getAddress() {
		return sourceAddress;
	}

	public void setAddress(GpAddress address) {
		sourceAddress = address;
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
	
	public int getMic() {
		return this.mic;
	}
	
	public void setMic(int mic) {
		this.mic=mic;
	}
	
	public int getEndpoint() {
		return endpoint;
	}
	
	public void setEndpoint(int endpoint) {
		this.endpoint = endpoint;
	}
	
	public int getSourceId() {
		return sourceId;
	}
	
	public void setSourceId(int sourceid) {
		this.sourceId = sourceid;
	}
	
	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
        builder.append("Gp Command = [");
        builder.append("transactionId=");
        builder.append(transactionId);
        builder.append(", Source Address=");
        builder.append(sourceAddress);
        builder.append(", SecurityFrameCounter=");
        builder.append(SecurityFrameCounter);
        builder.append(", CommandId=");
        builder.append(commandId);
        builder.append(", payload=");
        if (payload != null) {
            for (int c = 0; c < payload.length; c++) {
                if (c != 0) {
                    builder.append(' ');
                }
                builder.append(String.format("%02X", payload[c]));
            }
        }
        builder.append(", Mic=");
        builder.append(mic);
        builder.append(']');
        return builder.toString();
	}
}

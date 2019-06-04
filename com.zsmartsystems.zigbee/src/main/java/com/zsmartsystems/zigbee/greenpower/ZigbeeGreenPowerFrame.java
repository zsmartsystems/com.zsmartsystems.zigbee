package com.zsmartsystems.zigbee.greenpower;

public class ZigbeeGreenPowerFrame {

	/**
	 * Whether the GPDF as autoComissioning bit set or no.
	 */
	private boolean AutoCommissioning;

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
	private int CommandId;

	/**
	 * The GPD command payload.
	 */
	private int[] payload;
	
	/**
	 * The frame's MIC.
	 */
	private int mic;

	public boolean isAutoCommissioning() {
		return AutoCommissioning;
	}
	public void setAutoCommissioning(boolean autoCommissioning) {
		AutoCommissioning = autoCommissioning;
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
	public int getCommandId() {
		return CommandId;
	}
	public void setCommandId(int commandId) {
		CommandId = commandId;
	}
	public int[] getPayload() {
		return payload;
	}
	public void setPayload(int[] payload) {
		this.payload = payload;
	}
	public int getMic() {
		return mic;
	}
	public void setMic(int mic) {
		this.mic = mic;
	}
	
	@Override
    public String toString() {
        StringBuilder builder = new StringBuilder(164);
        builder.append("ZigBeeGreenPowerFrame [sourceID=");
        builder.append(SourceID);
        builder.append(", Endpoint=");
        builder.append(Endpoint);
        builder.append(", SecurityFrameCounter=");
        builder.append(SecurityFrameCounter);
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

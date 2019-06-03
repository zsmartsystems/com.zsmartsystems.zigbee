package com.zsmartsystems.zigbee.greenpower;

public class ZigbeeGreenPowerFrame {

	private int SourceID;
	private int Endpoint;
	private int SecurityFrameCounter;
	private int[] payload;
	private int mic;
	
	
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
	public int getMic() {
		return mic;
	}
	public void setMic(int mic) {
		this.mic = mic;
	}
}

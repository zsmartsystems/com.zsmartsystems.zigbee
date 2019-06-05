package com.zsmartsystems.zigbee.greenpower;

public interface ZigBeeGpTransportTransmit {
	
    void sendGpCommand(final int msgTag, final ZigBeeGreenPowerFrame gpFrame);
    
    void setZigBeeGpTransportReceive(ZigBeeGpTransportReceive zigbeegpTransportReceive);
}

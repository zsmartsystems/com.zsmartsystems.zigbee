package com.zsmartsystems.zigbee.greenpower;

public interface ZigBeeGpTransportReceive {
    
    void receiveGpCommand(ZigBeeGreenPowerFrame gpFrame);

}

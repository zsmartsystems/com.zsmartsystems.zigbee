package com.zsmartsystems.zigbee.greenpower;

public interface ZigbeeGpTransportReceive {
    
    void receiveGpCommand(ZigbeeGreenPowerFrame gpFrame);

}

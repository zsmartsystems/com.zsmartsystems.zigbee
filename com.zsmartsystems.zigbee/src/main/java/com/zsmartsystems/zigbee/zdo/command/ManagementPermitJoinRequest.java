package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zdo.ZdoRequest;

/**
 * ManagementPermitJoinRequest.
 *
 * @author Tommi S.E. Laukkanen
 */
public class ManagementPermitJoinRequest extends ZdoRequest {
    /**
     * Destination address type: 0x02 - Address 16 bit, 0x0F - Broadcast.
     */
    private int addressingMode;
    /**
     * Duration to permit joining. 0 = join disabled. 0xff = join enabled. 0x01-0xfe = number of seconds to permit
     * joining.
     */
    private int duration;
    /**
     * Trust center significance.
     */
    private int trustCenterSignificance;

    public ManagementPermitJoinRequest() {
    }

    public ManagementPermitJoinRequest(int addressingMode, int destinationAddress, int duration,
            int trustCenterSignificance) {
        this.addressingMode = addressingMode;
        this.destinationAddress = destinationAddress;
        this.duration = duration;
        this.trustCenterSignificance = trustCenterSignificance;
    }

    public int getAddressingMode() {
        return addressingMode;
    }

    public void setAddressingMode(int addressingMode) {
        this.addressingMode = addressingMode;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getTrustCenterSignificance() {
        return trustCenterSignificance;
    }

    public void setTrustCenterSignificance(int trustCenterSignificance) {
        this.trustCenterSignificance = trustCenterSignificance;
    }

    @Override
    public String toString() {
        return "ManagementPermitJoinRequest: addressingMode=" + addressingMode + ", destinationAddress="
                + destinationAddress + ", duration=" + duration + ", trustCenterSignificance="
                + trustCenterSignificance;
    }
}

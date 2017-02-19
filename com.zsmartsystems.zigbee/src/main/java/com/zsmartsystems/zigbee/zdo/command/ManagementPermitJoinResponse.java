package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zdo.ZdoResponse;

/**
 * ManagementPermitJoinResponse.
 *
 * @author Tommi S.E. Laukkanen
 * @author Chris Jackson
 */
public class ManagementPermitJoinResponse extends ZdoResponse {

    public ManagementPermitJoinResponse() {
    }

    public ManagementPermitJoinResponse(int status, int sourceAddress) {
        this.sourceAddress = sourceAddress;
        this.status = status;
    }

    @Override
    public String toString() {
        return "ManagementPermitJoinResponse: sourceAddress=" + sourceAddress + ", status=" + status;
    }
}

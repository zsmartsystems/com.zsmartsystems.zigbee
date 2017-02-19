package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zdo.ZdoResponse;

/**
 * BindResponse.
 *
 * @author Tommi S.E. Laukkanen
 * @author Chris Jackson
 */
public class BindResponse extends ZdoResponse {
    /**
     * The status.
     */
    private int status;

    public BindResponse() {
    }

    public BindResponse(int status, int sourceAddress) {
        this.sourceAddress = sourceAddress;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Bind Response: sourceAddress=" + sourceAddress + ", status=" + status;
    }
}

package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zdo.ZdoResponse;

/**
 * UnbindResponse.
 *
 * @author Tommi S.E. Laukkanen
 * @author Chris Jackson
 */
public class UnbindResponse extends ZdoResponse {

    public UnbindResponse() {
    }

    public UnbindResponse(int status, int sourceAddress) {
        this.sourceAddress = sourceAddress;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Unbind Response: sourceAddress=" + sourceAddress + ", status=" + status;
    }
}

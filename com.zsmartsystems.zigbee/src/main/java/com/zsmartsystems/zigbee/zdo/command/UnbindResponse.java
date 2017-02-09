package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zdo.ZdoCommand;
import com.zsmartsystems.zigbee.zdo.ZdoResponse;

/**
 * UnbindResponse.
 *
 * @author Tommi S.E. Laukkanen
 */
public class UnbindResponse extends ZdoCommand implements ZdoResponse {
    /**
     * The status.
     */
    private int status;
    /**
     * The source address.
     */
    private int sourceAddress;

    public UnbindResponse() {
    }

    public UnbindResponse(int status, int sourceAddress) {
        this.sourceAddress = sourceAddress;
        this.status = status;
    }

    @Override
    public int getSourceAddress() {
        return sourceAddress;
    }

    public void setSourceAddress(int sourceAddress) {
        this.sourceAddress = sourceAddress;
    }

    @Override
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Unbind Response: sourceAddress=" + sourceAddress + ", status=" + status;
    }
}

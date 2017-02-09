package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zdo.ZdoCommand;
import com.zsmartsystems.zigbee.zdo.ZdoResponse;
import com.zsmartsystems.zigbee.zdo.descriptors.PowerDescriptor;

/**
 *
 * @author Chris Jackson
 *
 */
public class PowerDescriptorResponse extends ZdoCommand implements ZdoResponse {
    /**
     * this field indicates either SUCCESS or FAILURE.
     */
    public int status;
    /**
     * the message's source network address.
     */
    public int sourceAddress;

    /**
     * The {@link PowerDescriptor}
     */
    private PowerDescriptor powerDescriptor;

    public PowerDescriptorResponse() {
    }

    public PowerDescriptorResponse(int status, int sourceAddress, PowerDescriptor powerDescriptor) {
        this.status = status;
        this.sourceAddress = sourceAddress;
        this.powerDescriptor = powerDescriptor;
    }

    @Override
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public int getSourceAddress() {
        return sourceAddress;
    }

    public void setSourceAddress(int sourceAddress) {
        this.sourceAddress = sourceAddress;
    }

    public PowerDescriptor getPowerDescriptor() {
        return powerDescriptor;
    }

    @Override
    public String toString() {
        return "Power Descriptor Response: status=" + status + ", sourceAddress=" + sourceAddress + ", powerDescriptor="
                + powerDescriptor;
    }
}

package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zdo.ZdoResponse;
import com.zsmartsystems.zigbee.zdo.descriptors.PowerDescriptor;

/**
 *
 * @author Chris Jackson
 *
 */
public class PowerDescriptorResponse extends ZdoResponse {

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

    public PowerDescriptor getPowerDescriptor() {
        return powerDescriptor;
    }

    @Override
    public String toString() {
        return "Power Descriptor Response: status=" + status + ", sourceAddress=" + sourceAddress + ", powerDescriptor="
                + powerDescriptor;
    }
}

package com.zsmartsystems.zigbee.dongle.ember.ezsp;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberStatus;

/**
 * Reads a configuration value from the NCP.
 * 
 * @author Chris Jackson
 *
 */
public class EzspGetConfigurationValueResponse extends EzspFrameResponse {
    /**
     * The configuration value.
     */
    private int value;

    /**
     * 
     * @param configId
     *            Identifies which configuration value to read.
     */
    public EzspGetConfigurationValueResponse(int[] inputBuffer) {
        super(inputBuffer);
        value = inputUInt16();

        emberStatus = EmberStatus.EMBER_SUCCESS;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "EzspGetConfigurationValue RESPONSE [" + emberStatus + ": value=" + value + "]";
    }
}

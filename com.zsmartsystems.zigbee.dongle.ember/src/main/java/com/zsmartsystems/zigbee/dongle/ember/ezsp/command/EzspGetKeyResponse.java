package com.zsmartsystems.zigbee.dongle.ember.ezsp.command;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberKeyStruct;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberStatus;

public class EzspGetKeyResponse extends EzspFrameResponse {
    public static int FRAME_ID = 0x6A;

    /**
     * EMBER_ERR_FATAL if the index is greater or equal to the number of active neighbors, or if the
     * device is an end device. Returns EMBER_SUCCESS otherwise.
     * <p>
     * EZSP type is <i>EmberStatus</i> - Java type is {@link EmberStatus}
     */
    private EmberStatus status;

    /**
     * The contents of the neighbor table entry.
     * <p>
     * EZSP type is <i>EmberNeighborTableEntry</i> - Java type is {@link EmberNeighborTableEntry}
     */
    private EmberKeyStruct keyStruct;

    /**
     * Response and Handler constructor
     */
    public EzspGetKeyResponse(int[] inputBuffer) {
        // Super creates deserializer and reads header fields
        super(inputBuffer);

        // Deserialize the fields
        status = deserializer.deserializeEmberStatus();
        keyStruct = deserializer.deserializeEmberKeyStruct();
    }

    /**
     * EMBER_ERR_FATAL if the index is greater or equal to the number of active neighbors, or if the
     * device is an end device. Returns EMBER_SUCCESS otherwise.
     * <p>
     * EZSP type is <i>EmberStatus</i> - Java type is {@link EmberStatus}
     *
     * @return the current status as {@link EmberStatus}
     */
    public EmberStatus getStatus() {
        return status;
    }

    /**
     * EMBER_ERR_FATAL if the index is greater or equal to the number of active neighbors, or if the
     * device is an end device. Returns EMBER_SUCCESS otherwise.
     *
     * @param status the status to set as {@link EmberStatus}
     */
    public void setStatus(EmberStatus status) {
        this.status = status;
    }

    /**
     * The contents of the neighbor table entry.
     * <p>
     * EZSP type is <i>EmberNeighborTableEntry</i> - Java type is {@link EmberNeighborTableEntry}
     *
     * @return the current value as {@link EmberNeighborTableEntry}
     */
    public EmberKeyStruct getKeyStruct() {
        return keyStruct;
    }

    /**
     * The contents of the neighbor table entry.
     *
     * @param value the value to set as {@link EmberNeighborTableEntry}
     */
    public void setKeyStruct(EmberKeyStruct keyStruct) {
        this.keyStruct = keyStruct;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(76);
        builder.append("EzspGetKeyResponse [status=");
        builder.append(status);
        builder.append(", keyStruct=");
        builder.append(keyStruct.toString());
        builder.append(']');
        return builder.toString();
    }

}

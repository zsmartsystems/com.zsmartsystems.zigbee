package com.zsmartsystems.zigbee.dongle.ember.ezsp.command;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.serializer.EzspSerializer;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberKeyType;

public class EzspGetKeyRequest extends EzspFrameRequest {
    public static int FRAME_ID = 0x6A;

    /**
     * The index of the neighbor of interest. Neighbors are stored in ascending order by node id,
     * with all unused entries at the end of the table.
     * <p>
     * EZSP type is <i>uint8_t</i> - Java type is {@link int}
     */
    private int keyType;

    /**
     * Serialiser used to serialise to binary line data
     */
    private EzspSerializer serializer;

    /**
     * Request constructor
     */
    public EzspGetKeyRequest() {
        frameId = FRAME_ID;
        serializer = new EzspSerializer();
    }

    /**
     * The index of the neighbor of interest. Neighbors are stored in ascending order by node id,
     * with all unused entries at the end of the table.
     * <p>
     * EZSP type is <i>uint8_t</i> - Java type is {@link int}
     *
     * @return the current index as {@link int}
     */
    public int getKeyType() {
        return keyType;
    }

    /**
     * The index of the neighbor of interest. Neighbors are stored in ascending order by node id,
     * with all unused entries at the end of the table.
     *
     * @param index the index to set as {@link int}
     */
    public void setKeyType(int keyType) {
        this.keyType = keyType;
    }

    @Override
    public int[] serialize() {
        // Serialize the header
        serializeHeader(serializer);

        // Serialize the fields
        serializer.serializeUInt8(keyType);
        return serializer.getPayload();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(50);
        builder.append("EzspGetKeyRequest [keyType=");
        builder.append(EmberKeyType.getEmberKeyType(keyType));
        builder.append(']');
        return builder.toString();
    }

}

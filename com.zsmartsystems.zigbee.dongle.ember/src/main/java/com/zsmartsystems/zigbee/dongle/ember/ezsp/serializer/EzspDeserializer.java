package com.zsmartsystems.zigbee.dongle.ember.ezsp.serializer;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberKeyData;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberNodeType;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberStatus;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberStructure;

/**
 *
 * @author Chris Jackson
 *
 */
public class EzspDeserializer {
    private int[] buffer = new int[131];
    private int position = 0;

    public EzspDeserializer(int[] inputBuffer) {
        buffer = inputBuffer;
    }

    /**
     * Reads a uint8_t from the output stream
     *
     * @return value read from input
     */
    public int deserializeUInt8() {
        return buffer[position++];
    }

    /**
     * Reads a int8s from the output stream
     *
     * @return value read from input
     */
    public int deserializeInt8s() {
        return buffer[position] > 127 ? buffer[position++] - 256 : buffer[position++];
    }

    /**
     * Reads a boolean from the output stream
     *
     * @return value read from input
     */
    public boolean deserializeBool() {
        return buffer[position++] == 0 ? false : true;
    }

    /**
     * Reads an Eui64 address from the stream
     *
     * @return value read from input
     */
    public IeeeAddress deserializeEmberEui64() {
        long address = buffer[position++] + (buffer[position++] << 8) + (buffer[position++] << 16)
                + (buffer[position++] << 24) + (buffer[position++] << 32) + (buffer[position++] << 40)
                + (buffer[position++] << 48) + (buffer[position++] << 56);
        return new IeeeAddress(address);
    }

    /**
     * Reads a uint16_t from the output stream
     *
     * @return value read from input
     */
    public int deserializeUInt16() {
        return buffer[position++] + (buffer[position++] << 8);
    }

    public int[] deserializeUInt8Array(int length) {
        int[] val = new int[length];

        for (int cnt = 0; cnt < length; cnt++) {
            val[cnt] = deserializeUInt8();
        }

        return val;
    }

    public EmberKeyData deserializeEmberKeyData() {
        return new EmberKeyData(deserializeUInt8Array(8));
    }

    /**
     * Reads a uint32_t from the output stream
     *
     * @return value read from input
     */
    public int deserializeUInt32() {
        return buffer[position++] + (buffer[position++] << 8) + (buffer[position++] << 16) + (buffer[position++] << 24);
    }

    protected EmberStatus deserializeEmberStatus() {
        return EmberStatus.getEmberStatus(buffer[position++]);
    }

    protected EmberNodeType deserializeEmberNodeType() {
        return EmberNodeType.getEmberNodeType(buffer[position++]);
    }

    public EmberStructure deserializeStructure(Class<?> structureClass) {

        Constructor<?> ctor;
        try {
            ctor = structureClass.getConstructor(int[].class, int.class);
            return (EmberStructure) ctor.newInstance(buffer, position);
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }

}

package com.zsmartsystems.zigbee.dongle.ember.ezsp;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberKeyData;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberStructure;

/**
 * The EmberZNet Serial Protocol Data Representation
 * 
 * This class contains low level methods for serialising Ember data packets and
 * structures
 * 
 * @author Chris Jackson
 *
 */
public abstract class EzspData {
    protected int[] buffer = new int[131];
    protected int length = 0;
    protected int position = 0;

    /**
     * Resets the output buffer ready to update its data
     */
    protected void resetOutputBuffer() {
        length = 0;
    }

    /**
     * Adds a uint8_t into the output stream
     * 
     * @param val
     */
    protected void outputUInt8(int val) {
        buffer[length++] = val & 0xFF;
    }

    /**
     * Adds a uint16_t into the output stream
     * 
     * @param val
     */
    protected void outputUInt16(int val) {
        buffer[length++] = val & 0xFF;
        buffer[length++] = (val >> 8) & 0xFF;
    }

    /**
     * Adds a uint32_t into the output stream
     * 
     * @param val
     */
    protected void outputUInt32(int val) {
        buffer[length++] = val & 0xFF;
        buffer[length++] = (val >> 8) & 0xFF;
        buffer[length++] = (val >> 16) & 0xFF;
        buffer[length++] = (val >> 24) & 0xFF;
    }

    protected void outputStructure(EmberStructure structure) {
        for (int val : structure.getOutputBuffer()) {
            buffer[length++] = val;
        }
    }

    protected void outputUInt16Array(int[] array) {
        for (int val : array) {
            outputUInt16(val);
        }
    }

    protected void outputUInt8Array(int[] array) {
        for (int val : array) {
            outputUInt8(val);
        }
    }

    protected void outputBool(boolean val) {
        buffer[length++] = val ? 1 : 0;
    }
    
    protected void outputEmberKeyData(EmberKeyData keyData) {
        outputUInt8Array(keyData.getKey());
    }
    
    protected void outputEmberEui64(IeeeAddress address) {
       long val = address.getLong();
       
       buffer[length++] = (int) (val & 0xFF);
       buffer[length++] = (int) ((val >> 8) & 0xFF);
       buffer[length++] = (int) ((val >> 16) & 0xFF);
       buffer[length++] = (int) ((val >> 24) & 0xFF);
       buffer[length++] = (int) ((val >> 32) & 0xFF);
       buffer[length++] = (int) ((val >> 40) & 0xFF);
       buffer[length++] = (int) ((val >> 48) & 0xFF);
       buffer[length++] = (int) ((val >> 56) & 0xFF);
    }

    /**
     * Reads a uint8_t from the output stream
     *
     * @return value read from input
     */
    protected int inputUInt8() {
        return buffer[position++];
    }

    /**
     * Reads a int8s from the output stream
     *
     * @return value read from input
     */
    protected int inputInt8s() {
        return buffer[position] > 127 ? buffer[position++] - 256 : buffer[position++];
    }

    /**
     * Reads a boolean from the output stream
     *
     * @return value read from input
     */
    protected boolean inputBool() {
        return buffer[position++] == 0 ? false : true;
    }

    /**
     * Reads an Eui64 address from the stream
     * 
     * @return value read from input
     */
    protected IeeeAddress inputEmberEui64() {
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
    protected int inputUInt16() {
        return buffer[position++] + (buffer[position++] << 8);
    }

    protected int[] inputUInt8Array(int length) {
        int[] val = new int[length];

        for (int cnt = 0; cnt < length; cnt++) {
            val[cnt] = inputUInt8();
        }

        return val;
    }
    
    protected EmberKeyData inputEmberKeyData() {
        return new EmberKeyData(inputUInt8Array(8));
    }

    /**
     * Reads a uint32_t from the output stream
     *
     * @return value read from input
     */
    protected int inputUInt32() {
        return buffer[position++] + (buffer[position++] << 8) + (buffer[position++] << 16) + (buffer[position++] << 24);
    }

    protected EmberStructure inputStructure(Class<?> structureClass) {

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

    /**
     * Returns the data to be sent to the NCP
     * 
     * @return integer array of data to be sent
     */
    public int[] getOutputBuffer() {
        return Arrays.copyOfRange(buffer, 0, length);
    }
}

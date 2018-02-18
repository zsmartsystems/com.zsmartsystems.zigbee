/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.xbee.internal.protocol;

import java.util.Arrays;

import com.zsmartsystems.zigbee.IeeeAddress;

/**
 * Base class for all XBee frames. Provides methods to serialise and deserialise data.
 *
 * @author Chris Jackson
 *
 */
public class XBeeFrame {
    protected int[] buffer = new int[131];
    protected int length = 0;
    protected int position = 0;
    protected int pushPosition = 0;
    private int frameType;

    private final int START_DELIMITER = 0x7E;

    /**
     * Initialise the deserialiser. This sets the data for the deserialiser methods to use.
     *
     * @param data the incoming data to deserialise
     */
    protected void initialiseDeserializer(int[] data) {
        buffer = data;
        length = data[0] << 8 + data[1];
        frameType = data[2];
        position = 3;
    }

    /**
     * Serialises the command. This should be the first method called when serialising a command. In addition to
     * serialising the command, it initialises internal variables used to properly create the command packet.
     *
     * @param command
     */
    protected void serializeCommand(int command) {
        length = 4;
        buffer[0] = START_DELIMITER;
        buffer[1] = 0;
        buffer[2] = 0;
        buffer[3] = command;
    }

    /**
     * Serializes an 8 bit integer in hexadecimal (2 characters long)
     *
     * @param value the value to serialize
     */
    protected void serializeInt8(int value) {
        buffer[length++] = value & 0xFF;
    }

    /**
     * Deserializes an 8 bit integer
     *
     * @return the deserialized value
     */
    protected Integer deserializeInt8() {
        return buffer[position++];
    }

    /**
     * Serializes an 16 bit integer
     *
     * @param value the value to serialize
     */
    protected void serializeInt16(Integer value) {
        buffer[length++] = (value >> 8) & 0xFF;
        buffer[length++] = value & 0xFF;
    }

    /**
     * Deserializes an 16 bit integer in hexadecimal
     *
     * @return the deserialized value
     */
    protected Integer deserializeInt16() {
        return (buffer[position++] << 8) + buffer[position++];
    }

    /**
     * Serializes a string. Used by other serialize methods to commit data to the output packet
     *
     * @param value the string to serialize
     */
    protected void serializeAtCommand(String value) {
        for (int cnt = 0; cnt < value.length(); cnt++) {
            buffer[length++] = value.charAt(cnt);
        }
    }

    protected String deserializeAtCommand() {
        StringBuilder builder = new StringBuilder();
        builder.append((char) buffer[position++]);
        builder.append((char) buffer[position++]);

        return builder.toString();
    }

    /**
     * Serializes a {@link ExtendedPanId}
     *
     * @param address the {@link ExtendedPanId}
     */
    // protected void serializeExtendedPanId(ExtendedPanId epanId) {
    // serializeUpperCaseString(epanId.toString());
    // }

    /**
     * Deserializes a {@link ExtendedPanId}
     *
     * @return the {@link ExtendedPanId}
     */
    // protected ExtendedPanId deserializeExtendedPanId() {
    // return new ExtendedPanId("");
    // }

    /**
     * Serializes an integer data array
     *
     * @param value the int[] array to send
     */
    protected void serializeData(int[] value) {
        if (value == null) {
            return;
        }
        for (int val : value) {
            buffer[length++] = val;
        }
    }

    /**
     * Deserializes binary data
     *
     * @return the int[] array containing the received data
     */
    protected int[] deserializeData() {
        if (position == buffer.length - 1) {
            return null;
        }

        int[] data = new int[buffer.length - position - 1];
        for (int cnt = 0; cnt < data.length; cnt++) {
            data[cnt] = buffer[position++];
        }

        return data;
    }

    /**
     * Serializes an {@link IeeeAddress}
     *
     * @param address the {@link vIeeeAddress}
     */
    protected void serializeIeeeAddress(IeeeAddress address) {
        buffer[length++] = address.getValue()[7];
        buffer[length++] = address.getValue()[6];
        buffer[length++] = address.getValue()[5];
        buffer[length++] = address.getValue()[4];
        buffer[length++] = address.getValue()[3];
        buffer[length++] = address.getValue()[2];
        buffer[length++] = address.getValue()[1];
        buffer[length++] = address.getValue()[0];
    }

    /**
     * Deserializes a {@link IeeeAddress}
     *
     * @return the {@link IeeeAddress}
     */
    protected IeeeAddress deserializeIeeeAddress() {
        int address[] = new int[8];
        for (int cnt = 7; cnt >= 0; cnt--) {
            address[cnt] = buffer[position++];
        }
        return new IeeeAddress(address);
    }

    /**
     * Serializes a {@link TransmitOptions}
     *
     * @param options
     */
    protected void serializeTransmitOptions(TransmitOptions options) {
        // serializeUpperCaseString(key.toString());
    }

    /**
     * Serializes a {@link ReceiveOptions}
     *
     * @param key
     */
    protected void serializeReceiveOptions(ReceiveOptions key) {
        // serializeUpperCaseString(key.toString());
    }

    /**
     * Deserializes a {link ModemStatus}
     *
     * @return the {@link ModemStatus}
     */
    protected ModemStatus deserializeModemStatus() {
        return ModemStatus.getModemStatus(deserializeInt8());
    }

    /**
     * Deserializes a {link ReceiveOptions}
     *
     * @return the {@link ReceiveOptions}
     */
    protected ReceiveOptions deserializeReceiveOptions() {
        return ReceiveOptions.getReceiveOptions(deserializeInt8());
    }

    /**
     * Deserializes a {link DiscoveryStatus}
     *
     * @return the {@link DiscoveryStatus}
     */
    protected DiscoveryStatus deserializeDiscoveryStatus() {
        return DiscoveryStatus.getDiscoveryStatus(deserializeInt8());
    }

    /**
     * Deserializes a {link CommandStatus}
     *
     * @return the {@link CommandStatus}
     */
    protected CommandStatus deserializeCommandStatus() {
        return CommandStatus.getCommandStatus(deserializeInt8());
    }

    /**
     * Deserializes a {link DeliveryStatus}
     *
     * @return the {@link DeliveryStatus}
     */
    protected DeliveryStatus deserializeDeliveryStatus() {
        return DeliveryStatus.getDeliveryStatus(deserializeInt8());
    }

    protected void serializeInt16Array(int[] array) {
        for (int value : array) {
            serializeInt16(value);
        }
    }

    protected int[] deserializeInt16Array(int size) {
        int[] array = new int[size];
        for (int cnt = 0; cnt < size; cnt++) {
            array[cnt] = deserializeInt16();
        }

        return array;
    }

    public void setFrameType(int frameType) {
        this.frameType = frameType;
    }

    public int getFrameType() {
        return frameType;
    }

    /**
     * Returns the serialized data. This is called at the send of the desrialization process is complete to allow
     * sending of the data
     *
     * @return the int array to send over the wire
     */
    protected int[] getPayload() {
        // Update the length
        int dataLen = length - 3;
        buffer[1] = (dataLen >> 8) & 0xff;
        buffer[2] = dataLen & 0xff;

        int checksum = 0;
        for (int cnt = 3; cnt < length; cnt++) {
            checksum += buffer[cnt];
        }

        buffer[length++] = 0xff - (checksum & 0xff);

        return Arrays.copyOfRange(buffer, 0, length);
    }
}

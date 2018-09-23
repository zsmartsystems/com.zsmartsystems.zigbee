/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol;

import java.util.Arrays;

import com.zsmartsystems.zigbee.ExtendedPanId;
import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.security.ZigBeeKey;

/**
 * Base class for all Telegesis frames. Provides methods to serialise and deserialise data.
 *
 * @author Chris Jackson
 *
 */
public class TelegesisFrame {
    private boolean firstDelimiter = true;

    protected int[] buffer = new int[131];
    protected int length = 0;
    protected int position = 0;
    protected int pushPosition = 0;

    private final int[] RESPONSE_OK = new int[] { 'O', 'K' };
    private final int[] RESPONSE_ERROR = new int[] { 'E', 'R', 'R', 'O', 'R', ':' };

    protected TelegesisStatusCode status = null;

    /**
     * Initialise the deserialiser. This sets the data for the deserialiser methods to use.
     *
     * @param data the incoming data to deserialise
     */
    protected void initialiseDeserializer(int[] data) {
        buffer = data;
        length = data.length;
        position = 0;
    }

    /**
     * Serialises the command. This should be the first method called when serialising a command. In addition to
     * serialising the command, it initialises internal variables used to properly create the command packet.
     *
     * @param command the command to serialize
     */
    protected void serializeCommand(String command) {
        position = 0;
        firstDelimiter = true;
        serializeUpperCaseString(command);
    }

    /**
     * Sets the position of the deserialiser
     *
     * @param start position to start deserialising
     */
    protected void setDeserializer(int start) {
        position = start;
    }

    /**
     * Increments the deserialiser position by 1
     */
    protected void stepDeserializer() {
        position++;
    }

    /**
     * Saves the deserializer position. This allows us to restore the position if there is an error deserializing data.
     * This is mainly used to support optional items where there may be a need to revert to the position before a field
     * was deserialized.
     */
    protected void pushDeserializer() {
        pushPosition = position;
    }

    /**
     * Restores the deserializer position to the last call of {@link #pushDeserializer()}
     */
    protected void popDeserializer() {
        position = pushPosition;
    }

    /**
     * Serializes an 4 bit integer in hexadecimal (1 character long)
     *
     * @param value the value to serialize
     */
    protected void serializeInt4(Integer value) {
        serializeUpperCaseString(Integer.toHexString(value));
    }

    /**
     * Serializes an 8 bit integer in hexadecimal (2 characters long)
     *
     * @param value the value to serialize
     */
    protected void serializeInt8(Integer value) {
        String strValue = Integer.toHexString(value);

        for (int cnt = strValue.length(); cnt < 2; cnt++) {
            buffer[length++] = '0';
        }
        serializeUpperCaseString(strValue);
    }

    /**
     * Deserializes an 8 bit integer in hexadecimal
     *
     * @return the deserialized value
     */
    protected Integer deserializeInt8() {
        if (buffer.length < position + 2) {
            return null;
        }

        StringBuilder builder = new StringBuilder();
        builder.append((char) buffer[position++]);
        builder.append((char) buffer[position++]);
        try {
            return Integer.parseInt(builder.toString(), 16);
        } catch (NumberFormatException e) {
            // Eat the exception
            return null;
        }
    }

    /**
     * Serializes an 8 bit integer in decimal (2 characters long)
     *
     * @param value the value to serialize
     */
    protected void serializeDec8(Integer value) {
        String strValue = Integer.toString(value);

        for (int cnt = strValue.length(); cnt < 2; cnt++) {
            buffer[length++] = '0';
        }
        serializeString(strValue);
    }

    /**
     * Deserializes an 8 bit integer in decimal
     *
     * @return the deserialized value
     */
    protected Integer deserializeDec8() {
        if (buffer.length < position + 2) {
            return null;
        }

        StringBuilder builder = new StringBuilder();
        builder.append((char) buffer[position++]);
        builder.append((char) buffer[position++]);
        try {
            return Integer.parseInt(builder.toString());
        } catch (NumberFormatException e) {
            // Eat the exception
            return null;
        }
    }

    /**
     * Serializes an 16 bit integer in hexadecimal (4 characters long)
     *
     * @param value the value to serialize
     */
    protected void serializeInt16(Integer value) {
        String strValue = Integer.toHexString(value);

        for (int cnt = strValue.length(); cnt < 4; cnt++) {
            buffer[length++] = '0';
        }
        serializeUpperCaseString(strValue);
    }

    /**
     * Deserializes an 16 bit integer in hexadecimal
     *
     * @return the deserialized value
     */
    protected Integer deserializeInt16() {
        if (buffer.length < position + 4) {
            return null;
        }

        StringBuilder builder = new StringBuilder();
        builder.append((char) buffer[position++]);
        builder.append((char) buffer[position++]);
        builder.append((char) buffer[position++]);
        builder.append((char) buffer[position++]);
        return Integer.parseInt(builder.toString(), 16);
    }

    /**
     * Serializes an 32 bit long in hexadecimal (8 characters long)
     *
     * @param value the value to serialize
     */
    protected void serializeInt32(Long value) {
        String strValue = Long.toHexString(value);

        for (int cnt = strValue.length(); cnt < 8; cnt++) {
            buffer[length++] = '0';
        }
        serializeUpperCaseString(strValue);
    }

    /**
     * Deserializes an 32 bit long in hexadecimal
     *
     * @return the deserialized value
     */
    protected Long deserializeInt32() {
        if (buffer.length < position + 8) {
            return null;
        }

        StringBuilder builder = new StringBuilder();
        for (int cnt = 0; cnt < 8; cnt++) {
            builder.append((char) buffer[position++]);
        }
        return Long.parseLong(builder.toString(), 16);
    }

    /**
     * Deserializes an 8 bit signed integer in hexadecimal
     *
     * @return the deserialized value
     */
    protected Integer deserializeSInt8() {
        if (buffer.length < position + 2) {
            return null;
        }

        boolean negate = false;
        StringBuilder builder = new StringBuilder();
        if ((char) buffer[position] == '-') {
            negate = true;
            position++;
        }
        builder.append((char) buffer[position++]);
        builder.append((char) buffer[position++]);
        try {
            Integer value = Integer.parseInt(builder.toString(), 16);
            return negate ? (value * -1) : value;
        } catch (NumberFormatException e) {
            // Eat the exception
            return null;
        }
    }

    /**
     * Serialises the signed 8 bit integer
     * 
     * @param value
     */
    protected void serializeSInt8(Integer value) {
        serializeString(value.toString());
    }

    protected void serializeBoolean(boolean value) {
        buffer[length++] = value ? '1' : '0';
    }

    protected Boolean deserializeBoolean() {
        if (buffer.length < position + 1) {
            return null;
        }

        return buffer[position++] == '1';
    }

    /**
     * Serializes an integer
     *
     * @param value the value to serialize
     */
    protected void serializeInteger(Integer value) {
        serializeUpperCaseString(Integer.toString(value));
    }

    /**
     * Deserializes an unsigned integer in decimal
     *
     * @return the deserialized value
     */
    protected Integer deserializeInteger() {
        StringBuilder builder = new StringBuilder();
        do {
            if (position >= length || buffer[position] == ',' || buffer[position] == '\n') {
                break;
            }

            builder.append((char) buffer[position++]);
        } while (true);

        if (builder.length() == 0) {
            return null;
        }

        return Integer.parseInt(builder.toString());
    }

    /**
     * Serializes a string. Used by other serialize methods to commit data to the output packet
     *
     * @param value the string to serialize
     */
    protected void serializeString(String value) {
        for (int cnt = 0; cnt < value.length(); cnt++) {
            buffer[length++] = value.charAt(cnt);
        }
    }

    /**
     * Serializes a string. Used by other serialize methods to commit data to the output packet
     *
     * @param value the string to serialize
     */
    protected void serializeUpperCaseString(String value) {
        serializeString(value.toUpperCase());
    }

    protected String deserializeString() {
        StringBuilder builder = new StringBuilder();
        for (; position < length; position++) {
            if (buffer[position] == '\n' || buffer[position] == '\r') {
                break;
            }
            builder.append((char) buffer[position]);
        }

        return builder.toString();
    }

    /**
     * Deserializes a hexadecimal stream of the specified length
     *
     * @param length the length to read
     * @return the deserialized string
     */
    protected String deserializeHexString(int length) {
        StringBuilder builder = new StringBuilder();
        for (int cnt = 0; cnt < length; cnt++) {
            if (!isHex(buffer[position])) {
                return null;
            }
            builder.append((char) buffer[position++]);
        }

        return builder.toString();
    }

    /**
     * Serializes a {@link ExtendedPanId}
     *
     * @param epanId the {@link ExtendedPanId}
     */
    protected void serializeExtendedPanId(ExtendedPanId epanId) {
        serializeUpperCaseString(epanId.toString());
    }

    /**
     * Deserializes a {@link ExtendedPanId}
     *
     * @return the {@link ExtendedPanId}
     */
    protected ExtendedPanId deserializeExtendedPanId() {
        String string = deserializeHexString(16);
        if (string == null) {
            return null;
        }
        return new ExtendedPanId(string);
    }

    /**
     * Serializes an integer data array
     *
     * @param value the int[] array to send
     */
    protected void serializeData(int[] value) {
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
        int length = deserializeInt8();
        if (buffer[position] != '=' && buffer[position] != ':') {
            return null;
        }
        position++;

        int[] data = new int[length];
        for (int cnt = 0; cnt < length; cnt++) {
            data[cnt] = buffer[position++];
        }

        return data;
    }

    /**
     * Deserializes the Telegesis device type
     *
     * @return the {@link TelegesisDeviceType}
     */
    protected TelegesisDeviceType deserializeTelegesisDeviceType() {
        StringBuilder builder = new StringBuilder();

        for (; position < length; position++) {
            if (buffer[position] == ',') {
                break;
            }
            builder.append((char) buffer[position]);
        }

        return TelegesisDeviceType.valueOf(builder.toString().toUpperCase());
    }

    /**
     * Serializes a delimiter between two fields. This will put either a comma or a colon into the output stream - colon
     * is used for the first delimiter, then a comma is used for subsequent delimiters.
     */
    protected void serializeDelimiter() {
        if (firstDelimiter) {
            buffer[length++] = ':';
            firstDelimiter = false;
        } else {
            buffer[length++] = ',';
        }
    }

    /**
     * Serializes a specified delimiter between two fields.
     *
     * @param delimiter the delimiter to serialize
     */
    protected void serializeDelimiter(int delimiter) {
        buffer[length++] = delimiter;
        firstDelimiter = false;
    }

    /**
     * Serializes an {@link IeeeAddress}
     *
     * @param address the {@link IeeeAddress}
     */
    protected void serializeIeeeAddress(IeeeAddress address) {
        serializeUpperCaseString(address.toString());
    }

    /**
     * Deserializes a {@link IeeeAddress}
     *
     * @return the {@link IeeeAddress}
     */
    protected IeeeAddress deserializeIeeeAddress() {
        String string = deserializeHexString(16);
        if (string == null) {
            return null;
        }

        return new IeeeAddress(string);
    }

    /**
     * Serializes a {@link ZigBeeKey}
     *
     * @param key the {@link ZigBeeKey} to serialize
     */
    protected void serializeZigBeeKey(ZigBeeKey key) {
        serializeUpperCaseString(key.toString());
    }

    /**
     * Deserializes a {link ZigBeeKey}
     *
     * @return the {@link ZigBeeKey}
     */
    protected ZigBeeKey deserializeZigBeeKey() {
        String string = deserializeHexString(32);
        if (string == null) {
            return null;
        }
        return new ZigBeeKey(string);
    }

    /**
     * Tests if the data contains the prompt
     *
     * @param data the data to test
     * @param prompt the prompt to test against
     * @return true of the data contains the prompt
     */
    protected boolean testPrompt(int[] data, String prompt) {
        if (data.length < prompt.length()) {
            return false;
        }

        int cnt = 0;
        for (int val : prompt.getBytes()) {
            if (data[cnt++] != val) {
                return false;
            }
        }

        return true;
    }

    /**
     * Returns the serialized data. This is called at the send of the desrialization process is complete to allow
     * sending of the data
     *
     * @return the int array to send over the wire
     */
    protected int[] getPayload() {
        // Add the CRLF to the end of the command
        buffer[length++] = '\r';
        buffer[length++] = '\n';

        return Arrays.copyOfRange(buffer, 0, length);
    }

    /**
     * Gets the {@link TelegesisStatusCode} if the OK or ERROR prompts have been received
     *
     * @return the {@link TelegesisStatusCode}
     */
    public TelegesisStatusCode getStatus() {
        return status;
    }

    /**
     * Handles standard return status codes (ERROR and OK)
     *
     * @param data the incoming data to check
     * @return true is the handler processed the message
     */
    protected boolean handleIncomingStatus(int[] data) {
        if (Arrays.equals(Arrays.copyOfRange(data, 0, 2), RESPONSE_OK)) {
            status = TelegesisStatusCode.SUCCESS;

            return true;
        }

        if (Arrays.equals(Arrays.copyOfRange(data, 0, 6), RESPONSE_ERROR)) {
            StringBuilder builder = new StringBuilder();
            builder.append((char) data[6]);
            builder.append((char) data[7]);
            status = TelegesisStatusCode.getTelegesisStatusCode(Integer.parseInt(builder.toString(), 16));

            return true;
        }

        return false;
    }

    protected TelegesisStatusCode deserializeTelegesisStatusCode() {
        return TelegesisStatusCode.getTelegesisStatusCode(deserializeInt8());
    }

    private boolean isHex(int value) {
        return ((value >= 0x30 && value <= 0x39) || (value >= 0x41 && value <= 0x46)
                || (value >= 0x61 && value <= 0x66));
    }
}

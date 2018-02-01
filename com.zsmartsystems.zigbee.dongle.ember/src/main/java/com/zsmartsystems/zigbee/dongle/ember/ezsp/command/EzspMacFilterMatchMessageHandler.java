/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp.command;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberMacPassthroughType;

/**
 * Class to implement the Ember EZSP command <b>macFilterMatchMessageHandler</b>.
 * <p>
 * A callback invoked by the EmberZNet stack when a raw MAC message that has matched one of the
 * application's configured MAC filters.
 * <p>
 * This class provides methods for processing EZSP commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class EzspMacFilterMatchMessageHandler extends EzspFrameResponse {
    public static int FRAME_ID = 0x46;

    /**
     * The index of the filter that was matched.
     * <p>
     * EZSP type is <i>uint8_t</i> - Java type is {@link int}
     */
    private int filterIndexMatch;

    /**
     * The type of MAC passthrough message received.
     * <p>
     * EZSP type is <i>EmberMacPassthroughType</i> - Java type is {@link EmberMacPassthroughType}
     */
    private EmberMacPassthroughType legacyPassthroughType;

    /**
     * The link quality from the node that last relayed the message.
     * <p>
     * EZSP type is <i>uint8_t</i> - Java type is {@link int}
     */
    private int lastHopLqi;

    /**
     * The energy level (in units of dBm) observed during reception.
     * <p>
     * EZSP type is <i>int8s</i> - Java type is {@link int}
     */
    private int lastHopRssi;

    /**
     * The raw message that was received.
     * <p>
     * EZSP type is <i>uint8_t[]</i> - Java type is {@link int[]}
     */
    private int[] messageContents;

    /**
     * Response and Handler constructor
     */
    public EzspMacFilterMatchMessageHandler(int[] inputBuffer) {
        // Super creates deserializer and reads header fields
        super(inputBuffer);

        // Deserialize the fields
        filterIndexMatch = deserializer.deserializeUInt8();
        legacyPassthroughType = deserializer.deserializeEmberMacPassthroughType();
        lastHopLqi = deserializer.deserializeUInt8();
        lastHopRssi = deserializer.deserializeInt8S();
        int messageLength = deserializer.deserializeUInt8();
        messageContents= deserializer.deserializeUInt8Array(messageLength);
    }

    /**
     * The index of the filter that was matched.
     * <p>
     * EZSP type is <i>uint8_t</i> - Java type is {@link int}
     *
     * @return the current filterIndexMatch as {@link int}
     */
    public int getFilterIndexMatch() {
        return filterIndexMatch;
    }

    /**
     * The index of the filter that was matched.
     *
     * @param filterIndexMatch the filterIndexMatch to set as {@link int}
     */
    public void setFilterIndexMatch(int filterIndexMatch) {
        this.filterIndexMatch = filterIndexMatch;
    }

    /**
     * The type of MAC passthrough message received.
     * <p>
     * EZSP type is <i>EmberMacPassthroughType</i> - Java type is {@link EmberMacPassthroughType}
     *
     * @return the current legacyPassthroughType as {@link EmberMacPassthroughType}
     */
    public EmberMacPassthroughType getLegacyPassthroughType() {
        return legacyPassthroughType;
    }

    /**
     * The type of MAC passthrough message received.
     *
     * @param legacyPassthroughType the legacyPassthroughType to set as {@link EmberMacPassthroughType}
     */
    public void setLegacyPassthroughType(EmberMacPassthroughType legacyPassthroughType) {
        this.legacyPassthroughType = legacyPassthroughType;
    }

    /**
     * The link quality from the node that last relayed the message.
     * <p>
     * EZSP type is <i>uint8_t</i> - Java type is {@link int}
     *
     * @return the current lastHopLqi as {@link int}
     */
    public int getLastHopLqi() {
        return lastHopLqi;
    }

    /**
     * The link quality from the node that last relayed the message.
     *
     * @param lastHopLqi the lastHopLqi to set as {@link int}
     */
    public void setLastHopLqi(int lastHopLqi) {
        this.lastHopLqi = lastHopLqi;
    }

    /**
     * The energy level (in units of dBm) observed during reception.
     * <p>
     * EZSP type is <i>int8s</i> - Java type is {@link int}
     *
     * @return the current lastHopRssi as {@link int}
     */
    public int getLastHopRssi() {
        return lastHopRssi;
    }

    /**
     * The energy level (in units of dBm) observed during reception.
     *
     * @param lastHopRssi the lastHopRssi to set as {@link int}
     */
    public void setLastHopRssi(int lastHopRssi) {
        this.lastHopRssi = lastHopRssi;
    }

    /**
     * The raw message that was received.
     * <p>
     * EZSP type is <i>uint8_t[]</i> - Java type is {@link int[]}
     *
     * @return the current messageContents as {@link int[]}
     */
    public int[] getMessageContents() {
        return messageContents;
    }

    /**
     * The raw message that was received.
     *
     * @param messageContents the messageContents to set as {@link int[]}
     */
    public void setMessageContents(int[] messageContents) {
        this.messageContents = messageContents;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(185);
        builder.append("EzspMacFilterMatchMessageHandler [filterIndexMatch=");
        builder.append(filterIndexMatch);
        builder.append(", legacyPassthroughType=");
        builder.append(legacyPassthroughType);
        builder.append(", lastHopLqi=");
        builder.append(lastHopLqi);
        builder.append(", lastHopRssi=");
        builder.append(lastHopRssi);
        builder.append(", messageContents=");
        for (int c = 0; c < messageContents.length; c++) {
            if (c > 0) {
                builder.append(' ');
            }
            builder.append(String.format("%02X", messageContents[c]));
        }
        builder.append(']');
        return builder.toString();
    }
}

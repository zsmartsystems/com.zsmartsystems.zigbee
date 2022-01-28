/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to implement the Telegesis command <b>Energy Scan</b>.
 * <p>
 * Scan The Energy Of All Channels
 * <p>
 * This class provides methods for processing Telegesis AT API commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class TelegesisEnergyScanCommand extends TelegesisFrame implements TelegesisCommand {
    /**
     * +ESCAN required response flag
     */
     private boolean receivedPlusescan = false;

    /**
     * Response field
     */
    private List<ScanResult> scanResults = new ArrayList<ScanResult>();

    /**
     *
     * @return the scanResults as a {@link List} of {@link ScanResult}
     */
    public List<ScanResult> getScanResults() {
        return scanResults;
    }

    @Override
    public int[] serialize() {
        // Serialize the command fields
        serializeCommand("AT+ESCAN");

        return getPayload();
    }

    @Override
    public boolean deserialize(int[] data) {
        // Handle standard status responses (ie. OK / ERROR)
        if (handleIncomingStatus(data)) {
            return true;
        }

        initialiseDeserializer(data);

        // Deserialize the fields for the "+ESCAN" response
        if (testPrompt(data, "+ESCAN")) {
            receivedPlusescan = true;
            return false;
        }
        if (!receivedPlusescan) {
            return false;
        }
        // Deserialize the fields for the response
        scanResults.add(new ScanResult(data));

        return false;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(296);
        builder.append("TelegesisEnergyScanCommand [scanResults=");
        builder.append(scanResults);
        if (status != null) {
            builder.append(", status=");
            builder.append(status);
        }
        builder.append(']');
        return builder.toString();
    }

    /**
     *
     */
    public class ScanResult extends TelegesisFrame {
        /**
         */
        private Integer channel;

        /**
         */
        private Integer rssi;

        /**
         * Constructor to deserialize the received data
         */
        ScanResult(final int[] data) {
            initialiseDeserializer(data);

            // Deserialize field "channel"
            channel = deserializeDec8();
            stepDeserializer();

            // Deserialize field "rssi"
            rssi = deserializeInt8();
        }

        /**
         *
         * @return the channel as {@link Integer}
         */
        public Integer getChannel() {
            return channel;
        }

        /**
         *
         * @return the rssi as {@link Integer}
         */
        public Integer getRssi() {
            return rssi;
        }

        @Override
        public String toString() {
            final StringBuilder builder = new StringBuilder(104);
            builder.append("ScanResult [channel=");
            builder.append(channel);
            builder.append(", rssi=");
            builder.append(rssi);
            builder.append(']');
            return builder.toString();
        }
    }
}

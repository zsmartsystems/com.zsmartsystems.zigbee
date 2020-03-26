/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp.command;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameResponse;

/**
 * Class to implement the Ember EZSP command <b>incomingRouteRecordHandler</b>.
 * <p>
 * Reports the arrival of a route record command frame.
 * <p>
 * This class provides methods for processing EZSP commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class EzspIncomingRouteRecordHandler extends EzspFrameResponse {
    public static final int FRAME_ID = 0x59;

    /**
     * The source of the route record.
     * <p>
     * EZSP type is <i>EmberNodeId</i> - Java type is {@link int}
     */
    private int source;

    /**
     * The EUI64 of the source.
     * <p>
     * EZSP type is <i>EmberEUI64</i> - Java type is {@link IeeeAddress}
     */
    private IeeeAddress sourceEui;

    /**
     * The link quality from the node that last relayed the route record.
     * <p>
     * EZSP type is <i>uint8_t</i> - Java type is {@link int}
     */
    private int lastHopLqi;

    /**
     * The energy level (in units of dBm) observed during the reception.
     * <p>
     * EZSP type is <i>int8s</i> - Java type is {@link int}
     */
    private int lastHopRssi;

    /**
     * The route record. Each relay in the list is an uint16_t node ID.
     * <p>
     * EZSP type is <i>uint16_t[]</i> - Java type is {@link int[]}
     */
    private int[] relayList;

    /**
     * Response and Handler constructor
     */
    public EzspIncomingRouteRecordHandler(int[] inputBuffer) {
        // Super creates deserializer and reads header fields
        super(inputBuffer);

        // Deserialize the fields
        source = deserializer.deserializeUInt16();
        sourceEui = deserializer.deserializeEmberEui64();
        lastHopLqi = deserializer.deserializeUInt8();
        lastHopRssi = deserializer.deserializeInt8S();
        int relayCount = deserializer.deserializeUInt8();
        relayList= deserializer.deserializeUInt16Array(relayCount);
    }

    /**
     * The source of the route record.
     * <p>
     * EZSP type is <i>EmberNodeId</i> - Java type is {@link int}
     *
     * @return the current source as {@link int}
     */
    public int getSource() {
        return source;
    }

    /**
     * The source of the route record.
     *
     * @param source the source to set as {@link int}
     */
    public void setSource(int source) {
        this.source = source;
    }

    /**
     * The EUI64 of the source.
     * <p>
     * EZSP type is <i>EmberEUI64</i> - Java type is {@link IeeeAddress}
     *
     * @return the current sourceEui as {@link IeeeAddress}
     */
    public IeeeAddress getSourceEui() {
        return sourceEui;
    }

    /**
     * The EUI64 of the source.
     *
     * @param sourceEui the sourceEui to set as {@link IeeeAddress}
     */
    public void setSourceEui(IeeeAddress sourceEui) {
        this.sourceEui = sourceEui;
    }

    /**
     * The link quality from the node that last relayed the route record.
     * <p>
     * EZSP type is <i>uint8_t</i> - Java type is {@link int}
     *
     * @return the current lastHopLqi as {@link int}
     */
    public int getLastHopLqi() {
        return lastHopLqi;
    }

    /**
     * The link quality from the node that last relayed the route record.
     *
     * @param lastHopLqi the lastHopLqi to set as {@link int}
     */
    public void setLastHopLqi(int lastHopLqi) {
        this.lastHopLqi = lastHopLqi;
    }

    /**
     * The energy level (in units of dBm) observed during the reception.
     * <p>
     * EZSP type is <i>int8s</i> - Java type is {@link int}
     *
     * @return the current lastHopRssi as {@link int}
     */
    public int getLastHopRssi() {
        return lastHopRssi;
    }

    /**
     * The energy level (in units of dBm) observed during the reception.
     *
     * @param lastHopRssi the lastHopRssi to set as {@link int}
     */
    public void setLastHopRssi(int lastHopRssi) {
        this.lastHopRssi = lastHopRssi;
    }

    /**
     * The route record. Each relay in the list is an uint16_t node ID.
     * <p>
     * EZSP type is <i>uint16_t[]</i> - Java type is {@link int[]}
     *
     * @return the current relayList as {@link int[]}
     */
    public int[] getRelayList() {
        return relayList;
    }

    /**
     * The route record. Each relay in the list is an uint16_t node ID.
     *
     * @param relayList the relayList to set as {@link int[]}
     */
    public void setRelayList(int[] relayList) {
        this.relayList = relayList;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(208);
        builder.append("EzspIncomingRouteRecordHandler [networkId=");
        builder.append(networkId);
        builder.append(", source=");
        builder.append(String.format("%04X", source));
        builder.append(", sourceEui=");
        builder.append(sourceEui);
        builder.append(", lastHopLqi=");
        builder.append(lastHopLqi);
        builder.append(", lastHopRssi=");
        builder.append(lastHopRssi);
        builder.append(", relayList=");
        for (int cnt = 0; cnt < relayList.length; cnt++) {
            if (cnt > 0) {
                builder.append(' ');
            }
            builder.append(String.format("%04X", relayList[cnt]));
        }
        builder.append(']');
        return builder.toString();
    }
}

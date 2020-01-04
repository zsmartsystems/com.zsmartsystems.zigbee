/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.xbee.internal.protocol;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.dongle.xbee.internal.protocol.ReceiveOptions;

/**
 * Class to implement the XBee command <b>Receive Packet Explicit</b>.
 * <p>
 * When a device configured with explicit API Rx Indicator (AO = 1) receives an RF packet, it
 * sends it out the serial interface using this message type. The Cluster ID and endpoints must
 * be used to identify the type of transaction that occurred.
 * <p>
 * This class provides methods for processing XBee API commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class XBeeReceivePacketExplicitEvent extends XBeeFrame implements XBeeEvent {
    /**
     * Response field
     * MSB first, LSB last. The sender's 64-bit address. Set to 0xFFFFFFFFFFFFFFFF (unknown
     * 64-bit address) if the sender's 64-bit address is unknown.
     */
    private IeeeAddress ieeeAddress;

    /**
     * Response field
     * The sender's 16-bit address.
     */
    private Integer networkAddress;

    /**
     * Response field
     * Endpoint of the source that initiates transmission. The default value is shown when
     * Transmit Request frame - 0x10 is used to send data from the source. Non-defaults are shown if
     * Explicit Addressing Command frame - 0x11 is used to send data from the source, or if a
     * non-default value was used, otherwise the default value remains.
     */
    private Integer sourceEndpoint;

    /**
     * Response field
     * Endpoint of the destination that the message is addressed to. The default value is shown when
     * Transmit Request frame - 0x10 is used to send data from the source. Non-defaults are shown if
     * Explicit Addressing Command frame - 0x11 is used to send data from the source, or if a
     * non-default value was used, otherwise the default value remains.
     */
    private Integer destinationEndpoint;

    /**
     * Response field
     * The Cluster ID that the frame is addressed to. The default value is shown when Transmit
     * Request frame - 0x10 is used to send data from the source. Non-defaults are shown if Explicit
     * Addressing Command frame - 0x11 is used to send data from the source, or if a non-default value
     * was used, otherwise the default value remains.
     */
    private Integer clusterId;

    /**
     * Response field
     * The Profile ID that the fame is addressed to.
     */
    private Integer profileId;

    /**
     * Response field
     */
    private ReceiveOptions receiveOptions;

    /**
     * Response field
     * The RF data that the device receives.
     */
    private int[] data;

    /**
     * MSB first, LSB last. The sender's 64-bit address. Set to 0xFFFFFFFFFFFFFFFF (unknown
     * 64-bit address) if the sender's 64-bit address is unknown.
     *
     * @return the ieeeAddress as {@link IeeeAddress}
     */
    public IeeeAddress getIeeeAddress() {
        return ieeeAddress;
    }

    /**
     * The sender's 16-bit address.
     *
     * @return the networkAddress as {@link Integer}
     */
    public Integer getNetworkAddress() {
        return networkAddress;
    }

    /**
     * Endpoint of the source that initiates transmission. The default value is shown when
     * Transmit Request frame - 0x10 is used to send data from the source. Non-defaults are shown if
     * Explicit Addressing Command frame - 0x11 is used to send data from the source, or if a
     * non-default value was used, otherwise the default value remains.
     *
     * @return the sourceEndpoint as {@link Integer}
     */
    public Integer getSourceEndpoint() {
        return sourceEndpoint;
    }

    /**
     * Endpoint of the destination that the message is addressed to. The default value is shown when
     * Transmit Request frame - 0x10 is used to send data from the source. Non-defaults are shown if
     * Explicit Addressing Command frame - 0x11 is used to send data from the source, or if a
     * non-default value was used, otherwise the default value remains.
     *
     * @return the destinationEndpoint as {@link Integer}
     */
    public Integer getDestinationEndpoint() {
        return destinationEndpoint;
    }

    /**
     * The Cluster ID that the frame is addressed to. The default value is shown when Transmit
     * Request frame - 0x10 is used to send data from the source. Non-defaults are shown if Explicit
     * Addressing Command frame - 0x11 is used to send data from the source, or if a non-default value
     * was used, otherwise the default value remains.
     *
     * @return the clusterId as {@link Integer}
     */
    public Integer getClusterId() {
        return clusterId;
    }

    /**
     * The Profile ID that the fame is addressed to.
     *
     * @return the profileId as {@link Integer}
     */
    public Integer getProfileId() {
        return profileId;
    }

    /**
     *
     * @return the receiveOptions as {@link ReceiveOptions}
     */
    public ReceiveOptions getReceiveOptions() {
        return receiveOptions;
    }

    /**
     * The RF data that the device receives.
     *
     * @return the data as {@link int[]}
     */
    public int[] getData() {
        return data;
    }


    @Override
    public void deserialize(int[] incomingData) {
        initialiseDeserializer(incomingData);

        // Deserialize the fields for the response

        // Deserialize field "Ieee Address"
        ieeeAddress = deserializeIeeeAddress();

        // Deserialize field "Network Address"
        networkAddress = deserializeInt16();

        // Deserialize field "Source Endpoint"
        sourceEndpoint = deserializeInt8();

        // Deserialize field "Destination Endpoint"
        destinationEndpoint = deserializeInt8();

        // Deserialize field "Cluster ID"
        clusterId = deserializeInt16();

        // Deserialize field "Profile ID"
        profileId = deserializeInt16();

        // Deserialize field "Receive Options"
        receiveOptions = deserializeReceiveOptions();

        // Deserialize field "Data"
        data = deserializeData();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(840);
        builder.append("XBeeReceivePacketExplicitEvent [ieeeAddress=");
        builder.append(ieeeAddress);
        builder.append(", networkAddress=");
        builder.append(networkAddress);
        builder.append(", sourceEndpoint=");
        builder.append(sourceEndpoint);
        builder.append(", destinationEndpoint=");
        builder.append(destinationEndpoint);
        builder.append(", clusterId=");
        builder.append(clusterId);
        builder.append(", profileId=");
        builder.append(profileId);
        builder.append(", receiveOptions=");
        builder.append(receiveOptions);
        builder.append(", data=");
        if (data == null) {
            builder.append("null");
        } else {
            for (int cnt = 0; cnt < data.length; cnt++) {
                if (cnt > 0) {
                    builder.append(' ');
                }
                builder.append(String.format("%02X", data[cnt]));
            }
        }
        builder.append(']');
        return builder.toString();
    }
}

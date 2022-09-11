/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.xbee.internal.protocol;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.dongle.xbee.internal.protocol.ReceiveOptions;

/**
 * Class to implement the XBee command <b>OTA Firmware Update Status</b>.
 * <p>
 * The Over-the-Air Firmware Update Status frame provides an indication of the status of a
 * firmware update transmission attempt. A query command (0x01 0x51) sent to a target with a
 * 64-bit address of 0x0013A200 40522BAA through an updater with 64-bit address
 * 0x0013A200403E0750 and 16-bit address 0x0000, generates the following expected
 * response.
 * <p>
 * This class provides methods for processing XBee API commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class XBeeOtaFirmwareUpdateStatusEvent extends XBeeFrame implements XBeeEvent {
    /**
     * Response field
     * MSB first, LSB last. The address of the remote radio returning this response.
     */
    private IeeeAddress ieeeAddress;

    /**
     * Response field
     * The 16-bit address of the updater device.
     */
    private Integer networkAddress;

    /**
     * Response field
     */
    private ReceiveOptions receiveOptions;

    /**
     * Response field
     * Block number used in the update request. Set to 0 if not applicable.
     */
    private Integer blockNumber;

    /**
     * Response field
     * The 64-bit Address of remote device that is being updated (target)
     */
    private IeeeAddress targetAddress;

    /**
     * MSB first, LSB last. The address of the remote radio returning this response.
     *
     * @return the ieeeAddress as {@link IeeeAddress}
     */
    public IeeeAddress getIeeeAddress() {
        return ieeeAddress;
    }

    /**
     * The 16-bit address of the updater device.
     *
     * @return the networkAddress as {@link Integer}
     */
    public Integer getNetworkAddress() {
        return networkAddress;
    }

    /**
     *
     * @return the receiveOptions as {@link ReceiveOptions}
     */
    public ReceiveOptions getReceiveOptions() {
        return receiveOptions;
    }

    /**
     * Block number used in the update request. Set to 0 if not applicable.
     *
     * @return the blockNumber as {@link Integer}
     */
    public Integer getBlockNumber() {
        return blockNumber;
    }

    /**
     * The 64-bit Address of remote device that is being updated (target)
     *
     * @return the targetAddress as {@link IeeeAddress}
     */
    public IeeeAddress getTargetAddress() {
        return targetAddress;
    }


    @Override
    public void deserialize(int[] incomingData) {
        initialiseDeserializer(incomingData);

        // Deserialize the fields for the response

        // Deserialize field "Ieee Address"
        ieeeAddress = deserializeIeeeAddress();

        // Deserialize field "Network Address"
        networkAddress = deserializeInt16();

        // Deserialize field "Receive Options"
        receiveOptions = deserializeReceiveOptions();

        // Deserialize field "Bootloader Message Type"
        int bootloaderMessageType = deserializeInt8();

        // Deserialize field "Block Number"
        blockNumber = deserializeInt8();

        // Deserialize field "Target Address"
        targetAddress = deserializeIeeeAddress();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(662);
        builder.append("XBeeOtaFirmwareUpdateStatusEvent [ieeeAddress=");
        builder.append(ieeeAddress);
        builder.append(", networkAddress=");
        builder.append(networkAddress);
        builder.append(", receiveOptions=");
        builder.append(receiveOptions);
        builder.append(", blockNumber=");
        builder.append(blockNumber);
        builder.append(", targetAddress=");
        builder.append(targetAddress);
        builder.append(']');
        return builder.toString();
    }
}

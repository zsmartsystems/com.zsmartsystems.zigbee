/**
 * Copyright (c) 2016-2021 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.xbee.internal.protocol;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.dongle.xbee.internal.protocol.TransmitOptions;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Class to implement the XBee command <b>Transmit Request</b>.
 * <p>
 * This frame causes the device to send payload data as an RF packet to a specific destination. n
 * For broadcast transmissions, set the 64-bit destination address to 0x000000000000FFFF.
 * Address the coordinator by either setting the 64-bit address to all 0x00s and the 16-bit
 * address to 0xFFFE, or setting the 64-bit address to the coordinator's 64-bit address and the
 * 16-bit address to 0x0000. n For all other transmissions, setting the 16-bit address to the
 * correct 16-bit address helps improve performance when transmitting to multiple
 * destinations. If you do not know a 16-bit address, set this field to 0xFFFE (unknown). If
 * successful, the Transmit Status frame (0x8B) indicates the discovered 16-bit address. You
 * can set the broadcast radius from 0 up to NH. If set to 0, the value of NH specifies the broadcast
 * radius (recommended). This parameter is only used for broadcast transmissions. You can
 * read the maximum number of payload bytes with the NP command.
 * <p>
 * This class provides methods for processing XBee API commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class XBeeTransmitRequestCommand extends XBeeFrame implements XBeeCommand {
    /**
     * The frame Id
     */
    private Integer frameId;

    /**
     * 64-bit destination address. MSB first, LSB last. Set to the 64-bit address of the
     * destination device. Reserved 64-bit address for the coordinator = 0x0000000000000000
     * Broadcast = 0x000000000000FFFF.
     */
    private IeeeAddress ieeeAddress;

    /**
     * 16-bit destination network address. Set to the 16-bit address of the destination device, if
     * known. If the address is unknown or if sending a broadcast, set to 0xFFFE.
     */
    private Integer networkAddress;

    /**
     * 0x01 - Disable retries. 0x20 - Enable APS encryption (if EE=1). 0x40 - Use the extended
     * transmission timeout for this destination. Enabling APS encryption decreases the maximum
     * number of RF payload bytes by 4 (below the value reported by NP). Setting the extended timeout
     * bit causes the stack to set the extended transmission timeout for the destination address.
     * See Transmission, addressing, and routing. All unused and unsupported bits must be set to 0.
     */
    private List<TransmitOptions> options = new ArrayList<TransmitOptions>();

    /**
     * Data sent to the destination device.
     */
    private int[] data;

    /**
     * The frame Id
     *
     * @param frameId the frameId to set as {@link Integer}
     */
    public void setFrameId(Integer frameId) {
        this.frameId = frameId;
    }

    /**
     * 64-bit destination address. MSB first, LSB last. Set to the 64-bit address of the
     * destination device. Reserved 64-bit address for the coordinator = 0x0000000000000000
     * Broadcast = 0x000000000000FFFF.
     *
     * @param ieeeAddress the ieeeAddress to set as {@link IeeeAddress}
     */
    public void setIeeeAddress(IeeeAddress ieeeAddress) {
        this.ieeeAddress = ieeeAddress;
    }

    /**
     * 16-bit destination network address. Set to the 16-bit address of the destination device, if
     * known. If the address is unknown or if sending a broadcast, set to 0xFFFE.
     *
     * @param networkAddress the networkAddress to set as {@link Integer}
     */
    public void setNetworkAddress(Integer networkAddress) {
        this.networkAddress = networkAddress;
    }

    /**
     * 0x01 - Disable retries. 0x20 - Enable APS encryption (if EE=1). 0x40 - Use the extended
     * transmission timeout for this destination. Enabling APS encryption decreases the maximum
     * number of RF payload bytes by 4 (below the value reported by NP). Setting the extended timeout
     * bit causes the stack to set the extended transmission timeout for the destination address.
     * See Transmission, addressing, and routing. All unused and unsupported bits must be set to 0.
     *
     * @param options the options to add to the Set as {@link TransmitOptions}
     */
    public void addOptions(TransmitOptions options) {
        this.options.add(options);
    }

    /**
     * 0x01 - Disable retries. 0x20 - Enable APS encryption (if EE=1). 0x40 - Use the extended
     * transmission timeout for this destination. Enabling APS encryption decreases the maximum
     * number of RF payload bytes by 4 (below the value reported by NP). Setting the extended timeout
     * bit causes the stack to set the extended transmission timeout for the destination address.
     * See Transmission, addressing, and routing. All unused and unsupported bits must be set to 0.
     *
     * @param options the options to remove to the Set as {@link TransmitOptions}
     */
    public void removeOptions(TransmitOptions options) {
        this.options.remove(options);
    }

    /**
     * 0x01 - Disable retries. 0x20 - Enable APS encryption (if EE=1). 0x40 - Use the extended
     * transmission timeout for this destination. Enabling APS encryption decreases the maximum
     * number of RF payload bytes by 4 (below the value reported by NP). Setting the extended timeout
     * bit causes the stack to set the extended transmission timeout for the destination address.
     * See Transmission, addressing, and routing. All unused and unsupported bits must be set to 0.
     *
     * @param options the options to set to the Set as {@link TransmitOptions}
     */
    public void setOptions(Collection<TransmitOptions> options) {
        this.options.addAll(options);
    }

    /**
     * Data sent to the destination device.
     *
     * @param data the data to set as {@link int[]}
     */
    public void setData(int[] data) {
        this.data = data;
    }

    @Override
    public int[] serialize() {
        // Serialize the command fields
        serializeCommand(0x10);
        serializeInt8(frameId);
        serializeIeeeAddress(ieeeAddress);
        serializeInt16(networkAddress);
        serializeTransmitOptions(options);
        serializeData(data);

        return getPayload();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(566);
        // First present the command parameters...
        // Then the responses later if they are available
        builder.append("XBeeTransmitRequestCommand [frameId=");
        builder.append(frameId);
        builder.append(", ieeeAddress=");
        builder.append(ieeeAddress);
        builder.append(", networkAddress=");
        builder.append(networkAddress);
        builder.append(", options=");
        builder.append(options);
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

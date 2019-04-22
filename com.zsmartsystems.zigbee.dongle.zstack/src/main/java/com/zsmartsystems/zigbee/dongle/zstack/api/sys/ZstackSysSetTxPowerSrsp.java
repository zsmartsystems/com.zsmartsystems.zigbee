/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.zstack.api.sys;

import com.zsmartsystems.zigbee.dongle.zstack.api.ZstackFrameResponse;

/**
 * Class to implement the Z-Stack command <b>SYS_SET_TX_POWER</b>.
 * <p>
 * This command is used by the tester to set the target system radio transmit power. The returned TX power is the actual setting
 * applied to the radio – nearest characterized value for the specific radio.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson
 */
public class ZstackSysSetTxPowerSrsp extends ZstackFrameResponse {

    /**
     * Requested TX power setting, in dBm.
     */
    private int txPower;

    /**
     * Response and Handler constructor
     */
    public ZstackSysSetTxPowerSrsp(int[] inputBuffer) {
        // Super creates deserializer and reads header fields
        super(inputBuffer);

        synchronousCommand = true;

        // Deserialize the fields
        txPower = deserializer.deserializeUInt8();
    }

    /**
     * Requested TX power setting, in dBm.
     *
     * @return the current txPower as {@link int}
     */
    public int getTxPower() {
        return txPower;
    }

    /**
     * Requested TX power setting, in dBm.
     *
     * @param txPower the TxPower to set as {@link int}
     */
    public void setTxPower(int txPower) {
        this.txPower = txPower;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(51);
        builder.append("ZstackSysSetTxPowerSrsp [txPower=");
        builder.append(txPower);
        builder.append(']');
        return builder.toString();
    }
}

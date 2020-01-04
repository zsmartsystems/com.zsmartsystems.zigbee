/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp.command;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameResponse;

/**
 * Class to implement the Ember EZSP command <b>setExtendedTimeout</b>.
 * <p>
 * Tells the stack whether or not the normal interval between retransmissions of a retried
 * unicast message should be increased by EMBER_INDIRECT_TRANSMISSION_TIMEOUT. The
 * interval needs to be increased when sending to a sleepy node so that the message is not
 * retransmitted until the destination has had time to wake up and poll its parent. The stack
 * will automatically extend the timeout: - For our own sleepy children. - When an address
 * response is received from a parent on behalf of its child. - When an indirect transaction
 * expiry route error is received. - When an end device announcement is received from a sleepy
 * node.
 * <p>
 * This class provides methods for processing EZSP commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class EzspSetExtendedTimeoutResponse extends EzspFrameResponse {
    public static final int FRAME_ID = 0x7E;

    /**
     * Response and Handler constructor
     */
    public EzspSetExtendedTimeoutResponse(int[] inputBuffer) {
        // Super creates deserializer and reads header fields
        super(inputBuffer);

        // Deserialize the fields
    }

    @Override
    public String toString() {
        return "EzspSetExtendedTimeoutResponse []";
    }
}

/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp.command;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberStatus;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspStatus;

/**
 * Class to implement the Ember EZSP command <b>setConfigurationValue</b>.
 * <p>
 * Returns the current radio power setting, as previously set via mfglibSetPower().
 * <p>
 * This class provides methods for processing EZSP commands.
 * <p>
 */
public class EzspMfgLibGetPowerResponse extends EzspFrameResponse {
    public static int FRAME_ID = 0x8d;

    /**
     * Power in units of dBm. Refer to radio data sheet for valid range.
     * <p>
     */
    private int power;

    /**
     * Response and Handler constructor
     */
    public EzspMfgLibGetPowerResponse(int[] inputBuffer) {
        // Super creates deserializer and reads header fields
        super(inputBuffer);

        // Deserialize the fields
        power = deserializer.deserializeInt8S();
    }



    public int getPowerl() {
		return power;
	}



	@Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(61);
        builder.append("EzspMfgLibGetPowerResponse [power=");
        builder.append(power);
        builder.append(']');
        return builder.toString();
    }
}

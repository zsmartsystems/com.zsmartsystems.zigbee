/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp.command;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.serializer.EzspSerializer;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberConfigTxPowerMode;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspConfigId;

/**
 * Class to implement the Ember EZSP command <b>mfglibStart</b>.
 * <p>
 * First select the transmit power mode, and then include a method for selecting the radio transmit power. The valid power
 * settings depend upon the specific radio in use. Ember radios have discrete power settings, and then requested power is rounded to a
 * valid power setting; the actual power output is available to the caller via mfglibGetPower().
 * <p>
 * This class provides methods for processing EZSP commands.
 * <p>
 */
public class EzspMfgLibSetPowerRequest extends EzspFrameRequest {
    public static int FRAME_ID = 0x8c;

    /**
     * Power mode. Refer to txPowerModes in stack/include/ember-types.h for possible values.
     */
    private EmberConfigTxPowerMode txPowerMode;
    
    /**
     * Power in units of dBm. Refer to radio data sheet for valid range.
     */
    private int power;

    /**
     * Serialiser used to serialise to binary line data
     */
    private EzspSerializer serializer;

    /**
     * Request constructor
     */
    public EzspMfgLibSetPowerRequest() {
        frameId = FRAME_ID;
        serializer = new EzspSerializer();
    }



	public EmberConfigTxPowerMode getTxPowerMode() {
		return txPowerMode;
	}



	public void setTxPowerMode(EmberConfigTxPowerMode txPowerMode) {
		this.txPowerMode = txPowerMode;
	}



	public int getPower() {
		return power;
	}



	public void setPower(int power) {
		this.power = power;
	}



	@Override
    public int[] serialize() {
        // Serialize the header
        serializeHeader(serializer);

        // Serialize the fields
        serializer.serializeEmberConfigTxPowerMode(txPowerMode);
        serializer.serializeUInt8(power);
        return serializer.getPayload();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(85);
        builder.append("EzspMfgLibSetPowerRequest [txPowerMode=");
        builder.append(txPowerMode);
        builder.append(" ,power=");
        builder.append(power);
        builder.append(']');
        return builder.toString();
    }
}

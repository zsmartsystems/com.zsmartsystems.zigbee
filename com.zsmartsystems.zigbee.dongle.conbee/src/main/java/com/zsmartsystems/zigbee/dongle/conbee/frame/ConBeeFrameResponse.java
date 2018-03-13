/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.conbee.frame;

/**
 *
 * @author Chris Jackson
 *
 */
public class ConBeeFrameResponse extends ConBeeFrame {
    protected ConBeeStatus status;

    protected ConBeeDeviceState state = new ConBeeDeviceState(0);

    public ConBeeFrameResponse(int[] response) {
        this.buffer = response;
    }

    /**
     * @return the status
     */
    public ConBeeStatus getStatus() {
        return status;
    }

    /**
     * @return the device state
     */
    public ConBeeDeviceState getDeviceState() {
        return state;
    }

}

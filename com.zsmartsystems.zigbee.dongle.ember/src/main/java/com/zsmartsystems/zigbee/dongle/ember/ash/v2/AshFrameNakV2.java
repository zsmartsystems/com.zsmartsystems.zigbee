/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ash.v2;

import com.zsmartsystems.zigbee.dongle.ember.ash.AshFrame.FrameType;
import com.zsmartsystems.zigbee.dongle.ember.ash.AshFrameNak;

/**
 * ASH NAK Frame
 *
 * @author Chris Jackson
 *
 */
public class AshFrameNakV2 extends AshFrameNak {

    public AshFrameNakV2(int[] frameBuffer) {
        this.frameType = FrameType.NAK;
        processHeader(frameBuffer);
    }

    @Override
    public String toString() {
        return "AshFrameNakV2 [ackNum=" + ackNum + "]";
    }
}

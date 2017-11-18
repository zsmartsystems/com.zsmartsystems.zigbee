/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ash.v2;

import com.zsmartsystems.zigbee.dongle.ember.ash.AshFrameRst;

/**
 * ASH Reset Frame
 *
 * @author Chris Jackson
 *
 */
public class AshFrameRstV2 extends AshFrameRst {
    /**
     * Constructor to create an ASH Reset frame.
     *
     * @param buffer
     */
    public AshFrameRstV2() {
        this.frameType = FrameType.RST;
    }

    public AshFrameRstV2(int[] frameBuffer) {
        this();
    }

    @Override
    public String toString() {
        return "AshFrameRstV2 []";
    }
}

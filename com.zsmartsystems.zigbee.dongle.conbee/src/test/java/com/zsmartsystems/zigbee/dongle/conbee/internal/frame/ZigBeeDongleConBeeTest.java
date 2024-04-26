/**
 * Copyright (c) 2016-2024 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.conbee.internal.frame;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.zsmartsystems.zigbee.ZigBeeStatus;
import com.zsmartsystems.zigbee.dongle.conbee.ZigBeeDongleConBee;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeDongleConBeeTest {

    @Test
    public void setZigBeePanId() {
        ZigBeeDongleConBee dongle = new ZigBeeDongleConBee(null);

        assertEquals(ZigBeeStatus.UNSUPPORTED, dongle.setZigBeePanId(0));
    }
}

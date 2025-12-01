/**
 * Copyright (c) 2016-2025 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.cc2531;

import org.junit.jupiter.api.Test;

import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.ZToolPacket;

/**
 *
 * @author Chris Jackson
 *
 */
public class UTIL_LED_CONTROL_Test extends Cc2351TestPacket {

    @Test
    public void testReceive() {
        ZToolPacket data = getPacket("FE 01 67 0A 01 6D");

        System.out.println(data);
    }

}

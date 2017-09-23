/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.telegesis.internal;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisDeviceLeftNetworkEvent;
import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisEvent;
import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisFrameBaseTest;

/**
 *
 * @author Chris Jackson
 *
 */
public class TelegesisEventFactoryTest extends TelegesisFrameBaseTest {

    @Test
    public void testGetEvent() {

        TelegesisEvent event = TelegesisEventFactory
                .getTelegesisFrame(stringToIntArray("NODELEFT:1234,1234567890ABCDEF"));

        assertTrue(event instanceof TelegesisDeviceLeftNetworkEvent);
    }
}

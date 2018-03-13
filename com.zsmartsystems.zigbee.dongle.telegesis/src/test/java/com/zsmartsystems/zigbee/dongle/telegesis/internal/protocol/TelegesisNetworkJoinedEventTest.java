/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.zsmartsystems.zigbee.ExtendedPanId;

/**
 *
 * @author Chris Jackson
 *
 */
public class TelegesisNetworkJoinedEventTest extends TelegesisFrameBaseTest {
    @Test
    public void test() {
        TelegesisNetworkJoinedEvent event = new TelegesisNetworkJoinedEvent();
        event.deserialize(stringToIntArray("JPAN:18,9876,0793E14FFB220A38"));
        System.out.println(event);
        assertEquals(Integer.valueOf(18), event.getChannel());
        assertEquals(Integer.valueOf(0x9876), event.getPanId());
        assertEquals(new ExtendedPanId("0793E14FFB220A38"), event.getEpanId());
    }

}

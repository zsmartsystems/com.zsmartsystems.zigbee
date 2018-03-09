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

import com.zsmartsystems.zigbee.IeeeAddress;

/**
 *
 * @author Chris Jackson
 *
 */
public class TelegesisRouteRecordMessageEventTest extends TelegesisFrameBaseTest {
    @Test
    public void test() {
        TelegesisRouteRecordMessageEvent event = new TelegesisRouteRecordMessageEvent();
        event.deserialize(stringToIntArray("SR:03,1234567890ABCDEF,1234,5678,9ABC"));
        System.out.println(event);

        assertEquals(Integer.valueOf(3), event.getHops());
        assertEquals(new IeeeAddress("1234567890ABCDEF"), event.getRemoteAddress());
    }
}

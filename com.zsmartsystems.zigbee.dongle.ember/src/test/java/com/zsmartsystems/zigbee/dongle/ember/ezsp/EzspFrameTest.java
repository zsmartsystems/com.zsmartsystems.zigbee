/**
 * Copyright (c) 2016-2024 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp;

import static org.junit.Assert.assertNull;

import org.junit.Test;

/**
 *
 * @author Chris Jackson
 *
 */
public class EzspFrameTest {
    protected int[] getPacketData(String stringData) {
        String split[] = stringData.split(" ");

        int[] response = new int[split.length];
        int cnt = 0;
        for (String val : split) {
            response[cnt++] = Integer.parseInt(val, 16);
        }

        return response;
    }

    @Test
    public void createHandler() {
        assertNull(EzspFrame.createHandler(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }));
        assertNull(EzspFrame.createHandler(new int[] { 0 }));
        assertNull(EzspFrame.createHandler(new int[] { 0, 67, 33 }));
    }
}

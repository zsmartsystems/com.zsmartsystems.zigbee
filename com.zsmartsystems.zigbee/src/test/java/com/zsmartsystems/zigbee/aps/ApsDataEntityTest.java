/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.aps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.zsmartsystems.zigbee.ZigBeeApsFrame;

/**
 *
 * @author Chris Jackson
 *
 */
public class ApsDataEntityTest {
    @Test
    public void duplicateRemoval() {
        ApsDataEntity aps = new ApsDataEntity();
        aps.setDuplicateTimeWindow(Long.MAX_VALUE);

        ZigBeeApsFrame apsFrame = new ZigBeeApsFrame();
        ZigBeeApsFrame response;
        apsFrame.setApsCounter(1);
        apsFrame.setSourceAddress(1);

        response = aps.receive(apsFrame);
        assertEquals(apsFrame, response);

        // Duplicate fails
        response = aps.receive(apsFrame);
        assertNull(response);

        // Non-duplicate within time window passes
        apsFrame.setApsCounter(2);
        response = aps.receive(apsFrame);
        assertEquals(apsFrame, response);
    }
}

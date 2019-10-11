/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.junit.Assert.*;

public class EzspFrameRequestTest {
    private class TestRequestFrame extends EzspFrameRequest {

    }

    @Test
    public void testSequenceNumbers() {
        TestRequestFrame frame = new TestRequestFrame();
        int k = 255;
        do {
            frame = new TestRequestFrame();
        } while(frame.sequenceNumber != 254 && --k>0);
        assertNotEquals("k",0,k);
        for (int i = 1; i < 255; i++) {
            frame = new TestRequestFrame();
            assertEquals("sequenceNumber", i, frame.getSequenceNumber());
        }
        for (int i = 1; i < 255; i++) {
            frame = new TestRequestFrame();
            assertEquals("sequenceNumber", i, frame.getSequenceNumber());
        }

    }
}
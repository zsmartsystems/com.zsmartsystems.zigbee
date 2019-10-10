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
        TestRequestFrame frame;

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
package com.zsmartsystems.zigbee.dongle.conbee.frame;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

/**
 *
 * @author Chris Jackson
 *
 */
public class ConBeeReadParameterRequestTest {
    @Test
    public void test() {
        ConBeeReadParameterRequest readParameter = new ConBeeReadParameterRequest();
        readParameter.setParameter(ConBeeNetworkParameter.MAC_ADDRESS);
        readParameter.setSequence(0x15);
        assertTrue(Arrays.equals(new int[] { 0x0a, 0x15, 0x00, 0x08, 0x00, 0x01, 0x00, 0x01, 0xd7, 0xff },
                readParameter.getOutputBuffer()));
    }
}

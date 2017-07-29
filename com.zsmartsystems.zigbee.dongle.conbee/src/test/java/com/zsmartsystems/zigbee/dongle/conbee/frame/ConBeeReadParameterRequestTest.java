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
        ConBeeReadParameterRequest request = new ConBeeReadParameterRequest();
        request.setParameter(ConBeeNetworkParameter.MAC_ADDRESS);
        request.setSequence(0x15);
        System.out.println(request);

        assertTrue(Arrays.equals(new int[] { 0x0A, 0x15, 0x00, 0x08, 0x00, 0x01, 0x00, 0x01, 0xD7, 0xff },
                request.getOutputBuffer()));
    }
}

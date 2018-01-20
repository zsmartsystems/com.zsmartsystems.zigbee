/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 *
 * @author Chris Jackson
 *
 */
public class TelegesisFrameTest extends TelegesisFrameBaseTest {
    @Test
    public void testIsHex() {
        TelegesisFrame frame = new TelegesisFrame();

        List<Integer> allowed = Arrays.asList(new Integer[] { (int) '0', (int) '1', (int) '2', (int) '3', (int) '4',
                (int) '5', (int) '6', (int) '7', (int) '8', (int) '9', (int) 'A', (int) 'B', (int) 'C', (int) 'D',
                (int) 'E', (int) 'F', (int) 'a', (int) 'b', (int) 'c', (int) 'd', (int) 'e', (int) 'f' });

        Method isHex;
        try {
            isHex = TelegesisFrame.class.getDeclaredMethod("isHex", new Class[] { int.class });
            isHex.setAccessible(true);

            for (int value = 0; value < 256; value++) {
                assertEquals(allowed.contains(value), (boolean) isHex.invoke(frame, value));
            }
        } catch (NoSuchMethodException | SecurityException | IllegalArgumentException | IllegalAccessException
                | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testHandleIncomingStatus() {
        TelegesisFrame frame = new TelegesisFrame();

        Method handleIncomingStatus;
        try {
            handleIncomingStatus = TelegesisFrame.class.getDeclaredMethod("handleIncomingStatus",
                    new Class[] { int[].class });
            handleIncomingStatus.setAccessible(true);

            assertTrue((boolean) handleIncomingStatus.invoke(frame, stringToIntArray("OK\r\n")));
            assertTrue((boolean) handleIncomingStatus.invoke(frame, stringToIntArray("ERROR:20\r\n")));

        } catch (NoSuchMethodException | SecurityException | IllegalArgumentException | IllegalAccessException
                | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}

/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol;

/**
 * Base class for Telegesis frame tests. Contains helper methods for converting data types.
 *
 * @author Chris Jackson
 *
 */
public class TelegesisFrameBaseTest {
    protected int[] stringToIntArray(String string) {
        int[] intarray = new int[string.length()];

        int cnt = 0;
        for (int ch : string.getBytes()) {
            intarray[cnt++] = ch & 0xFF;
        }

        return intarray;
    }

    protected String intArrayToString(int[] intarray) {
        StringBuilder builder = new StringBuilder();
        for (int value : intarray) {
            builder.append((char) value);
        }
        return builder.toString();
    }

}

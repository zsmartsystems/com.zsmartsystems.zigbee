/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console.zstack;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.console.ZigBeeConsoleCommand;
import com.zsmartsystems.zigbee.dongle.zstack.ZigBeeDongleZstack;
import com.zsmartsystems.zigbee.dongle.zstack.ZstackNcp;

/**
 *
 * @author Chris Jackson - Initial Contribution
 *
 */
public abstract class ZstackConsoleAbstractCommand implements ZigBeeConsoleCommand {
    private static String CFG_UNSUPPORTED = "Not Supported";

    protected ZstackNcp getZstackNcp(ZigBeeNetworkManager networkManager)
            throws IllegalArgumentException, IllegalStateException {
        if (!(networkManager.getZigBeeTransport() instanceof ZigBeeDongleZstack)) {
            throw new IllegalArgumentException("Dongle is not an ZStack NCP.");
        }
        ZigBeeDongleZstack dongle = (ZigBeeDongleZstack) networkManager.getZigBeeTransport();
        if (dongle == null) {
            throw new IllegalStateException("Dongle is not an ZStack NCP.");
        }
        return dongle.getZstackNcp();
    }

    protected String hex2Boolean(int[] bytes) {
        if (bytes == null) {
            return CFG_UNSUPPORTED;
        }

        return Boolean.valueOf(bytes[0] != 0).toString();
    }

    protected String hex2Uint8(int[] bytes) {
        if (bytes == null) {
            return CFG_UNSUPPORTED;
        }

        return Integer.valueOf(bytes[0]).toString();
    }

    protected String hex2Uint16(int[] bytes) {
        if (bytes == null) {
            return CFG_UNSUPPORTED;
        }

        return Integer.valueOf(bytes[0] + (bytes[1] * 256)).toString();
    }

    protected String hex2String(int[] bytes) {
        if (bytes == null) {
            return CFG_UNSUPPORTED;
        }

        int length = bytes.length;
        byte[] output = new byte[length];

        for (int cnt = 0; cnt < bytes.length; cnt++) {
            output[cnt] = (byte) bytes[cnt];
            if (output[cnt] == 0) {
                length = cnt;
                break;
            }
        }
        try {
            return new String(Arrays.copyOfRange(output, 0, length), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    protected String hexDump(int[] bytes) {
        if (bytes == null) {
            return CFG_UNSUPPORTED;
        }

        StringBuilder builder = new StringBuilder(bytes.length * 3);

        for (int value : bytes) {
            builder.append(String.format("%02X ", value));
        }

        return builder.toString();
    }
}

/**
 * Copyright (c) 2016-2021 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console.main;

import com.zsmartsystems.zigbee.dongle.ember.EmberNcpResetProvider;
import com.zsmartsystems.zigbee.serial.ZigBeeSerialPort;
import com.zsmartsystems.zigbee.transport.ZigBeePort;

/**
 * Class to perform reset using DTR
 *
 * @author Chris Jackson
 *
 */
public class EmberNcpHardwareReset implements EmberNcpResetProvider {

    @Override
    public void emberNcpReset(ZigBeePort port) {
        ZigBeeSerialPort serialPort = (ZigBeeSerialPort) port;

        try {
            serialPort.setRts(false);
            serialPort.setDtr(false);
            Thread.sleep(50);
            serialPort.setRts(true);
            serialPort.setDtr(true);
        } catch (InterruptedException e) {
        }
    }

}

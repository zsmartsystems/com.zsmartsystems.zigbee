/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console.telegesis;

import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.console.ZigBeeConsoleCommand;
import com.zsmartsystems.zigbee.dongle.telegesis.TelegesisNcp;
import com.zsmartsystems.zigbee.dongle.telegesis.ZigBeeDongleTelegesis;

/**
 *
 * @author Chris Jackson - Initial Contribution
 *
 */
public abstract class TelegesisConsoleAbstractCommand implements ZigBeeConsoleCommand {

    protected TelegesisNcp getTelegesisNcp(ZigBeeNetworkManager networkManager)
            throws IllegalArgumentException, IllegalStateException {
        if (!(networkManager.getZigBeeTransport() instanceof ZigBeeDongleTelegesis)) {
            throw new IllegalArgumentException("Dongle is not an Ember NCP.");
        }
        ZigBeeDongleTelegesis dongle = (ZigBeeDongleTelegesis) networkManager.getZigBeeTransport();
        if (dongle == null) {
            throw new IllegalStateException("Dongle is not an Telegesis NCP.");
        }
        return dongle.getTelegesisNcp();
    }
}

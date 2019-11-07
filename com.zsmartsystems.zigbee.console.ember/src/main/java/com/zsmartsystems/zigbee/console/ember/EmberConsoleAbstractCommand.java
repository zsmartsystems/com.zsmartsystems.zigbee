/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console.ember;

import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.console.ZigBeeConsoleAbstractCommand;
import com.zsmartsystems.zigbee.dongle.ember.EmberNcp;
import com.zsmartsystems.zigbee.dongle.ember.ZigBeeDongleEzsp;

/**
 *
 * @author Chris Jackson - Initial Contribution
 *
 */
public abstract class EmberConsoleAbstractCommand extends ZigBeeConsoleAbstractCommand {

    protected EmberNcp getEmberNcp(ZigBeeNetworkManager networkManager)
            throws IllegalArgumentException, IllegalStateException {
        if (!(networkManager.getZigBeeTransport() instanceof ZigBeeDongleEzsp)) {
            throw new IllegalArgumentException("Dongle is not an Ember NCP.");
        }
        ZigBeeDongleEzsp dongle = (ZigBeeDongleEzsp) networkManager.getZigBeeTransport();
        if (dongle == null) {
            throw new IllegalStateException("Dongle is not an Ember NCP.");
        }
        return dongle.getEmberNcp();
    }
}

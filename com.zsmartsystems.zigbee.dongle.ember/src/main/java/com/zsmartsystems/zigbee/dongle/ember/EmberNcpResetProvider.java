/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember;

import com.zsmartsystems.zigbee.transport.ZigBeePort;

/**
 * An interface to perform a reset on the Ember NCP.
 *
 * @author Chris Jackson
 *
 */
public interface EmberNcpResetProvider {
    /**
     * A callback made by {@link ZigBeeDongleEzsp} to perform a hardware reset on the NCP
     *
     * @param port the {@link ZigBeePort} on which the dongle is connected
     */
    void emberNcpReset(ZigBeePort port);
}

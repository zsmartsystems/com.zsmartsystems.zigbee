/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.greenpower;

import com.zsmartsystems.zigbee.ZigBeeCommand;

/**
 * Command listener provides a callback when commands are received.
 *
 * @author Chris Jackson
 */
public interface ZigBeeGpCommandListener {

    /**
     * Called when a command has been received.
     *
     * @param command the received {@link ZigBeeCommand}
     */
    void gpCommandReceived(final GpCommand command);
}
/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.zstack.internal;

import com.zsmartsystems.zigbee.dongle.zstack.api.ZstackCommand;

/**
 * Interface to exchange asynchronous packets and link state changes from the low level protocol handlers
 * to the dongle layer.
 *
 * @author Chris Jackson
 *
 */
public interface ZstackFrameHandler {
    /**
     * Passes received asynchronous frames from the ZStack protocol handler to the dongle layer
     *
     * @param response incoming {@link ZstackCommand} response frame
     */
    public void handlePacket(ZstackCommand response);

    /**
     * Called when the ZStack handler link state changes
     *
     * @param state true if the link is UP, false if the link is DOWN
     */
    public void handleLinkStateChange(boolean state);
}

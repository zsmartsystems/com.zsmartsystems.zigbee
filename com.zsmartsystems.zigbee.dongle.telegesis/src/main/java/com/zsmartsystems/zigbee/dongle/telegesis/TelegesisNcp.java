/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.telegesis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.dongle.telegesis.internal.TelegesisFrameHandler;
import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisGetFrameCntCommand;

/**
 * This class provides utility methods for accessing the Telegesis dongle.
 *
 * @author Chris Jackson
 *
 */
public class TelegesisNcp {
    /**
     * The {@link Logger}.
     */
    private final Logger logger = LoggerFactory.getLogger(TelegesisNcp.class);

    /**
     * The ASH protocol handler used to send and receive EZSP packets
     */
    private TelegesisFrameHandler frameHandler;

    /**
     * Create the NCP instance
     *
     * @param frameHandler the {@link TelegesisFrameHandler} used for communicating with the dongle
     */
    public TelegesisNcp(TelegesisFrameHandler frameHandler) {
        this.frameHandler = frameHandler;
    }

    /**
     * Gets the security frame counter
     *
     * @return the security frame counter or null on error
     */
    public Long getSecurityFrameCounter() {
        TelegesisGetFrameCntCommand command = new TelegesisGetFrameCntCommand();
        if (frameHandler.sendRequest(command) == null) {
            logger.debug("Error getting Telegesis security frame counter");
            return null;
        }

        return command.getFrameCnt();
    }

}

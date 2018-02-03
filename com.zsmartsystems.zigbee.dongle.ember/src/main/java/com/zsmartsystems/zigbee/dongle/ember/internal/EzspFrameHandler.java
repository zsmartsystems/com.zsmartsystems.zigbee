/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.internal;

import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.EzspFrame;

/**
 * Interface to exchange asynchronous packets and link state changes from the
 * ASH handler to the EZSP layer.
 *
 * @author Chris Jackson
 *
 */
public interface EzspFrameHandler {
    /**
     * Passes received asynchronous frames from the ASH handler to the EZSP layer
     *
     * @param response
     *            incoming {@link EzspFrame} response frame
     */
    public void handlePacket(EzspFrame response);

    /**
     * Called when the ASH link state changes
     *
     * @param state
     *            true if the link is UP, false if the link is DOWN
     */
    public void handleLinkStateChange(boolean state);
}

/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.xbee.internal;

import com.zsmartsystems.zigbee.dongle.xbee.internal.protocol.XBeeEvent;

/**
 * Interface to receive notifications of received {@link XBeeEvent}s from the {@link XBeeFrameHandler}
 *
 * @author Chris Jackson
 *
 */
public interface XBeeEventListener {
    /**
     * Listeners are called when a new {@link XBeeEvent} is received
     *
     * @param event the received {@link XBeeEvent}
     */
    void xbeeEventReceived(XBeeEvent event);
}

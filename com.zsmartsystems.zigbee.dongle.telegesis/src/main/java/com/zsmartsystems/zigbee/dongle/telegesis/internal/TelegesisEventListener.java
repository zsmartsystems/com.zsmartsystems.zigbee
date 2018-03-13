/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.telegesis.internal;

import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisEvent;

/**
 * Interface to receive notifications of received {@link TelegesisEvent}s from the {@link TelegesisFrameHandler}
 *
 * @author Chris Jackson
 *
 */
public interface TelegesisEventListener {
    /**
     * Listeners are called when a new {@link TelegesisEvent} is received
     * 
     * @param event the received {@link TelegesisEvent}
     */
    void telegesisEventReceived(TelegesisEvent event);

}

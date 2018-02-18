/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.xbee.internal.protocol;

/**
 * Interface for the Telegesis receive event
 *
 * @author Chris Jackson
 *
 */
public interface XBeeEvent {
    /**
     * Deserialize an incoming data packet.
     * <p>
     * A command handler may require multiple data packets to be processed before it is complete. True should only be
     * returned once it has received all data.
     * <p>
     * A prompt handler will always process the data and should always return true since it can not handle multiple
     * responses.
     */
    public void deserialize(int[] data);
}

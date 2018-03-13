/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.xbee.internal.protocol;

/**
 * Interface for the XBee command
 *
 * @author Chris Jackson
 *
 */
public interface XBeeCommand {

    /**
     * Sets the frame ID used to correlate responses with commands
     *
     * @param frameId
     */
    public void setFrameId(Integer frameId);

    /**
     * Serializes the command to the int[] array for sending to the XBee stick
     *
     * @return int[] containing the data to send
     */
    public int[] serialize();
}

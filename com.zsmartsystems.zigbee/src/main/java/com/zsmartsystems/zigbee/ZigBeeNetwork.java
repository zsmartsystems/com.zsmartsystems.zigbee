/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee;

import com.zsmartsystems.zigbee.transport.ZigBeeTransportTransmit;

/**
 * ZigBee network interface. It provides an interface for higher layers to receive information about the network and
 * also provides services for the {@link ZigBeeTransportTransmit} to provide network updates and incoming commands.
 *
 * @author Chris Jackson
 */
public interface ZigBeeNetwork {
    /**
     * Sends ZigBee library command without waiting for response.
     *
     * @param command the command
     * @return transactionId an {@link int} specifying the transaction ID for this transaction
     */
    int sendCommand(final ZigBeeCommand command);

    /**
     * Adds ZigBee library command listener.
     *
     * @param commandListener the {@link ZigBeeCommandListener}
     */
    void addCommandListener(final ZigBeeCommandListener commandListener);

    /**
     * Removes ZigBee library command listener.
     *
     * @param commandListener the {@link ZigBeeCommandListener}
     */
    void removeCommandListener(final ZigBeeCommandListener commandListener);
}

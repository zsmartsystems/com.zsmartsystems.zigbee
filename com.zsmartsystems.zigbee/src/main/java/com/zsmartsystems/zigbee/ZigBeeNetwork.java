/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee;

import java.util.concurrent.Future;

import com.zsmartsystems.zigbee.transaction.ZigBeeTransactionMatcher;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportTransmit;

/**
 * ZigBee network interface. It provides an interface for higher layers to receive information about the network and
 * also provides services for the {@link ZigBeeTransportTransmit} to provide network updates and incoming commands.
 *
 * @author Chris Jackson
 */
public interface ZigBeeNetwork {
    /**
     * Sends ZigBee command without waiting for response.
     *
     * @param command the {@link ZigBeeCommand} to send
     */
    void sendTransaction(final ZigBeeCommand command);

    /**
     * Sends {@link ZigBeeCommand} command and uses the {@link ZigBeeTransactionMatcher} to match the response.
     *
     * @param command the {@link ZigBeeCommand} to send
     * @param responseMatcher the {@link ZigBeeTransactionMatcher} used to match the response to the request
     * @return the {@link CommandResult} future.
     */
    Future<CommandResult> sendTransaction(final ZigBeeCommand command, final ZigBeeTransactionMatcher responseMatcher);

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

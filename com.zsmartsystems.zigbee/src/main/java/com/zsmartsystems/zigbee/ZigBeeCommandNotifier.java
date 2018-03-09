/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.zsmartsystems.zigbee.internal.NotificationService;

/**
 * Class to manage notifications of received commands. The library distributes commands hierarchically so this class
 * provides a centralised function for managing this.
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeCommandNotifier {
    /**
     * The command listeners.
     */
    private List<ZigBeeCommandListener> commandListeners = new ArrayList<ZigBeeCommandListener>();

    /**
     * Adds a command listener
     *
     * @param commandListener the {@link CommandListener} to add
     */
    public void addCommandListener(ZigBeeCommandListener commandListener) {
        final List<ZigBeeCommandListener> modifiedCommandListeners = new ArrayList<ZigBeeCommandListener>(
                commandListeners);
        modifiedCommandListeners.add(commandListener);
        commandListeners = Collections.unmodifiableList(modifiedCommandListeners);
    }

    /**
     * Removes a command listener
     *
     * @param commandListener the {@link CommandListener} to remove
     */
    public void removeCommandListener(ZigBeeCommandListener commandListener) {
        final List<ZigBeeCommandListener> modifiedCommandListeners = new ArrayList<ZigBeeCommandListener>(
                commandListeners);
        modifiedCommandListeners.remove(commandListener);
        commandListeners = Collections.unmodifiableList(modifiedCommandListeners);
    }

    /**
     * Notify registered command listeners of the received command
     *
     * @param command the {@link ZigBeeCommand} to send to the listeners
     */
    public void notifyCommandListeners(final ZigBeeCommand command) {
        synchronized (this) {
            // Notify the listeners
            for (final ZigBeeCommandListener commandListener : commandListeners) {
                NotificationService.execute(new Runnable() {
                    @Override
                    public void run() {
                        commandListener.commandReceived(command);
                    }
                });
            }
        }
    }

}

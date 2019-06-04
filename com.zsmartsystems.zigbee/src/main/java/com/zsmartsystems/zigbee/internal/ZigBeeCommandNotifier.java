/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.internal;

import java.util.HashSet;
import java.util.Set;

import com.zsmartsystems.zigbee.ZigBeeCommand;
import com.zsmartsystems.zigbee.ZigBeeCommandListener;
import com.zsmartsystems.zigbee.greenpower.GpCommand;

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
    private Set<ZigBeeCommandListener> commandListeners = new HashSet<ZigBeeCommandListener>();

    /**
     * Adds a command listener
     *
     * @param commandListener the {@link CommandListener} to add
     */
    public synchronized void addCommandListener(ZigBeeCommandListener commandListener) {
        commandListeners.add(commandListener);
    }

    /**
     * Removes a command listener
     *
     * @param commandListener the {@link CommandListener} to remove
     */
    public synchronized void removeCommandListener(ZigBeeCommandListener commandListener) {
        commandListeners.remove(commandListener);
    }

    /**
     * Notify registered command listeners of the received command
     *
     * @param command the {@link ZigBeeCommand} to send to the listeners
     */
    public synchronized void notifyCommandListeners(final ZigBeeCommand command) {
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

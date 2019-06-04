package com.zsmartsystems.zigbee.greenpower;

import java.util.HashSet;
import java.util.Set;

import com.zsmartsystems.zigbee.ZigBeeCommand;
import com.zsmartsystems.zigbee.ZigBeeCommandListener;
import com.zsmartsystems.zigbee.greenpower.GpCommand;
import com.zsmartsystems.zigbee.internal.NotificationService;

public class ZigBeeGpCommandNotifier {
    /**
     * The command listeners.
     */
    private Set<ZigBeeGpCommandListener> commandListeners = new HashSet<ZigBeeGpCommandListener>();

    /**
     * Adds a command listener
     *
     * @param commandListener the {@link CommandListener} to add
     */
    public synchronized void addCommandListener(ZigBeeGpCommandListener gpCommandListener) {
        commandListeners.add(gpCommandListener);
    }

    /**
     * Removes a command listener
     *
     * @param commandListener the {@link CommandListener} to remove
     */
    public synchronized void removeCommandListener(ZigBeeGpCommandListener gpCommandListener) {
        commandListeners.remove(gpCommandListener);
    }

    /**
     * Notify registered command listeners of the received command
     *
     * @param command the {@link ZigBeeCommand} to send to the listeners
     */
    public synchronized void notifyCommandListeners(final GpCommand gpCommand) {
        // Notify the listeners
        for (final ZigBeeGpCommandListener commandListener : commandListeners) {
            NotificationService.execute(new Runnable() {
                @Override
                public void run() {
                    commandListener.gpCommandReceived(gpCommand);
                }
            });
        }
    }   
    
}

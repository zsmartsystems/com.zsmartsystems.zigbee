/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.internal;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import org.junit.Test;
import org.mockito.Mockito;

import com.zsmartsystems.zigbee.TestUtilities;
import com.zsmartsystems.zigbee.ZigBeeCommand;
import com.zsmartsystems.zigbee.ZigBeeCommandListener;
import com.zsmartsystems.zigbee.zcl.ZclCommand;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeCommandNotifierTest {
    private static final int TIMEOUT = 5000;

    @Test
    public void addCommandListener() throws Exception {
        ZigBeeCommandNotifier notifier = new ZigBeeCommandNotifier();

        ZigBeeCommandListener commandListener = Mockito.mock(ZigBeeCommandListener.class);
        notifier.addCommandListener(commandListener);

        Set<ZigBeeCommandListener> commandListeners = (Set<ZigBeeCommandListener>) TestUtilities
                .getField(ZigBeeCommandNotifier.class, notifier, "commandListeners");
        assertEquals(1, commandListeners.size());
        assertEquals(commandListener, commandListeners.iterator().next());

        notifier.addCommandListener(commandListener);
        assertEquals(1, commandListeners.size());

        notifier.addCommandListener(Mockito.mock(ZigBeeCommandListener.class));
        assertEquals(2, commandListeners.size());

        ZigBeeCommand command = Mockito.mock(ZigBeeCommand.class);
        notifier.notifyCommandListeners(command);
        Mockito.verify(commandListener, Mockito.timeout(TIMEOUT).times(1)).commandReceived(command);

        ZclCommand zclCommand = Mockito.mock(ZclCommand.class);
        Mockito.when(zclCommand.isDisableDefaultResponse()).thenReturn(true);
        notifier.notifyCommandListeners(zclCommand);

        Mockito.when(zclCommand.isDisableDefaultResponse()).thenReturn(false);
        notifier.notifyCommandListeners(zclCommand);

        notifier.removeCommandListener(commandListener);
        assertEquals(1, commandListeners.size());
    }
}

/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.transaction;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Matchers;
import org.mockito.Mockito;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.ZigBeeCommand;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeTransactionTest {
    @Test
    public void testTimeout() {
        ZigBeeNetworkManager networkManager = Mockito.mock(ZigBeeNetworkManager.class);
        ScheduledFuture timerFuture = Mockito.mock(ScheduledFuture.class);
        ZigBeeTransaction transaction = new ZigBeeTransaction(networkManager);

        ArgumentCaptor<Runnable> timerCaptor = ArgumentCaptor.forClass(Runnable.class);
        Mockito.when(networkManager.scheduleTask(timerCaptor.capture(), Matchers.anyLong())).thenReturn(timerFuture);

        ZigBeeCommand command = Mockito.mock(ZigBeeCommand.class);
        ZigBeeTransactionMatcher transactionMatcher = Mockito.mock(ZigBeeTransactionMatcher.class);
        Future<CommandResult> transactionFuture = transaction.sendTransaction(command, transactionMatcher);
        Mockito.verify(networkManager, Mockito.times(1)).addCommandListener(transaction);

        Runnable timeout = timerCaptor.getValue();
        assertNotNull(timeout);

        assertFalse(transactionFuture.isDone());
        assertFalse(transactionFuture.isCancelled());

        timeout.run();
        Mockito.verify(networkManager, Mockito.times(1)).removeCommandListener(transaction);

        assertTrue(transactionFuture.isDone());
        assertTrue(transactionFuture.isCancelled());
    }
}

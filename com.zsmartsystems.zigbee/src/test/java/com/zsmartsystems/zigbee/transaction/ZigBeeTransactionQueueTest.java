/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.transaction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.concurrent.Future;

import org.junit.Test;
import org.mockito.Mockito;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.ZigBeeCommand;
import com.zsmartsystems.zigbee.ZigBeeEndpointAddress;
import com.zsmartsystems.zigbee.transaction.ZigBeeTransaction.TransactionState;

/**
 * Tests for {@link ZigBeeTransactionQueue}
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeTransactionQueueTest {
    private final long TIMEOUT = 5000;

    @Test
    public void testQueueFifo() {
        ZigBeeTransactionQueue queue = new ZigBeeTransactionQueue("QueueName");
        assertFalse(queue.isSleepy());
        assertFalse(queue.setSleepy(true));
        assertTrue(queue.setSleepy(true));
        assertTrue(queue.isSleepy());
        queue.getNextReleaseTime();

        // Set duplicate removal and delays to 0 so we can immediately read back the queued transaction
        queue.setProfile(new ZigBeeTransactionProfile(2, 2, 0));

        assertEquals(2, queue.getProfile().getMaxRetries());
        assertEquals(0, queue.getProfile().getInterTransactionDelay());
        assertEquals(2, queue.getProfile().getMaxOutstandingTransactions());

        ZigBeeTransaction transactionQueued1 = Mockito.mock(ZigBeeTransaction.class);
        ZigBeeTransaction transactionQueued2 = new ZigBeeTransaction(Mockito.mock(ZigBeeTransactionManager.class),
                Mockito.mock(ZigBeeCommand.class), Mockito.mock(ZigBeeTransactionMatcher.class));
        ZigBeeTransaction transactionQueued3 = new ZigBeeTransaction(Mockito.mock(ZigBeeTransactionManager.class),
                Mockito.mock(ZigBeeCommand.class), Mockito.mock(ZigBeeTransactionMatcher.class));

        assertTrue(queue.isEmpty());

        Mockito.when(transactionQueued1.getSendCnt()).thenReturn(0); // Not sent yet, so 0
        Future<CommandResult> future1 = queue.addToQueue(transactionQueued1);
        Future<CommandResult> future2 = queue.addToQueue(transactionQueued2);
        assertNotNull(future2);
        Future<CommandResult> future3 = queue.addToQueue(transactionQueued3);
        assertNotNull(future3);

        assertFalse(queue.isEmpty());
        assertEquals(3, queue.size());

        ZigBeeTransaction transactionSent1 = queue.getTransaction();
        ZigBeeTransaction transactionSent2 = queue.getTransaction();
        ZigBeeTransaction transactionSent3 = queue.getTransaction();

        assertEquals(transactionQueued1, transactionSent1);
        assertEquals(transactionQueued2, transactionSent2);

        // Max Outstanding transactions is set to 2, so the last transaction we queued should not get released yet
        assertNull(transactionSent3);

        // Complete transaction 1 without success - transaction 1 should be sent again
        Mockito.when(transactionQueued1.getSendCnt()).thenReturn(1); // Retry, so 1
        queue.transactionComplete(transactionSent1, TransactionState.FAILED);
        Mockito.verify(transactionSent1, Mockito.times(1)).resetTransaction();
        Mockito.verify(transactionSent1, Mockito.times(0)).cancel();
        transactionSent3 = queue.getTransaction();
        assertEquals(transactionQueued1, transactionSent3);
        assertEquals(1, queue.size());

        // Complete transaction 1 without success - transaction 1 should be aborted
        Mockito.when(transactionQueued1.getSendCnt()).thenReturn(2); // Retry, so 2 as we want this to terminate
        queue.transactionComplete(transactionSent1, TransactionState.FAILED);
        Mockito.verify(transactionSent1, Mockito.times(1)).cancel();
        transactionSent3 = queue.getTransaction();
        assertEquals(transactionQueued3, transactionSent3);

        // Disable retries
        queue.getProfile().setMaxRetries(0);

        // Complete transaction 2 without success
        queue.transactionComplete(transactionSent2, TransactionState.FAILED);
        assertTrue(future2.isDone());
        assertTrue(future2.isCancelled());

        // No more transactions
        assertNull(queue.getTransaction());
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
        System.out.println(queue);

        // Completing transactions that are not outstanding will be cancelled
        ZigBeeTransaction transactionUnqueued = Mockito.mock(ZigBeeTransaction.class);
        queue.transactionComplete(transactionUnqueued, TransactionState.TRANSMITTED);
        Mockito.verify(transactionUnqueued, Mockito.times(1)).cancel();

        System.out.println(queue);
        queue.shutdown();

        // Can't add transactions after shutdown
        assertNull(queue.addToQueue(Mockito.mock(ZigBeeTransaction.class)));

        // Unknown transactions, or transactions after shutdown are cancelled
        ZigBeeTransaction transactionShutdown = Mockito.mock(ZigBeeTransaction.class);
        queue.transactionComplete(transactionShutdown, TransactionState.TRANSMITTED);
        Mockito.verify(transactionShutdown, Mockito.times(1)).cancel();

        assertTrue(queue.isEmpty());
    }

    @Test
    public void testRewriteTransactions() {
        ZigBeeTransactionQueue queue = new ZigBeeTransactionQueue("QueueName", null);
        int oldAddress = 1;
        int newAddress = 2;

        ZigBeeCommand command = new ZigBeeCommand();
        command.setDestinationAddress(new ZigBeeEndpointAddress(oldAddress));
        ZigBeeTransaction transaction = new ZigBeeTransaction(mock(ZigBeeTransactionManager.class), command,
                mock(ZigBeeTransactionMatcher.class));
        queue.addToQueue(transaction);

        queue.rewriteDestinationAddresses(newAddress);

        ZigBeeTransaction rewrittenTransaction = queue.getTransaction();

        assertEquals(newAddress, rewrittenTransaction.getDestinationAddress().getAddress());
    }
}

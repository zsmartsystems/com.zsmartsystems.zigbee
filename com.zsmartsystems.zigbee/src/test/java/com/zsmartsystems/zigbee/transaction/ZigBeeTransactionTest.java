/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
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

import java.util.concurrent.ScheduledFuture;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import com.zsmartsystems.zigbee.ZigBeeCommand;
import com.zsmartsystems.zigbee.transaction.ZigBeeTransaction.TransactionState;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportProgressState;
import com.zsmartsystems.zigbee.zcl.clusters.onoff.OffCommand;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeTransactionTest {
    @Test
    public void testTimeout() {
        ZigBeeTransactionManager transactionManager = Mockito.mock(ZigBeeTransactionManager.class);
        ScheduledFuture timerFuture = Mockito.mock(ScheduledFuture.class);
        ZigBeeCommand command = Mockito.mock(ZigBeeCommand.class);
        ZigBeeTransactionMatcher matcher = Mockito.mock(ZigBeeTransactionMatcher.class);

        ZigBeeTransactionFuture transactionFuture = new ZigBeeTransactionFuture();

        ZigBeeTransaction transaction = new ZigBeeTransaction(transactionManager, command, matcher);
        transaction.setFuture(transactionFuture);

        ArgumentCaptor<Runnable> timerCaptor = ArgumentCaptor.forClass(Runnable.class);
        Mockito.when(transactionManager.scheduleTask(timerCaptor.capture(), ArgumentMatchers.anyLong()))
                .thenReturn(timerFuture);

        assertEquals(0, transaction.getSendCnt());
        assertEquals(command, transaction.startTransaction());
        Mockito.verify(transactionManager, Mockito.times(1)).scheduleTask(ArgumentMatchers.any(Runnable.class),
                ArgumentMatchers.eq(10000L));

        assertEquals(1, transaction.getSendCnt());

        Runnable timeout = timerCaptor.getValue();
        assertNotNull(timeout);

        assertFalse(transactionFuture.isDone());
        assertFalse(transactionFuture.isCancelled());

        timeout.run();
        Mockito.verify(transactionManager, Mockito.times(1)).transactionComplete(transaction, TransactionState.FAILED);

        // A timeout doesn't complete the transaction - this is done by the queue so it can account for retries
        assertFalse(transactionFuture.isDone());
        assertFalse(transactionFuture.isCancelled());
    }

    @Test
    public void testTxNak() {
        ZigBeeTransactionManager transactionManager = Mockito.mock(ZigBeeTransactionManager.class);
        ZigBeeCommand command = Mockito.mock(ZigBeeCommand.class);
        Mockito.when(command.getTransactionId()).thenReturn(12);
        ZigBeeTransactionMatcher matcher = Mockito.mock(ZigBeeTransactionMatcher.class);

        ZigBeeTransactionFuture transactionFuture = new ZigBeeTransactionFuture();

        ZigBeeTransaction transaction = new ZigBeeTransaction(transactionManager, command, matcher);
        transaction.setFuture(transactionFuture);

        transaction.resetTransaction();

        assertEquals(command, transaction.startTransaction());
        Mockito.verify(transactionManager, Mockito.times(1)).scheduleTask(ArgumentMatchers.any(Runnable.class),
                ArgumentMatchers.anyLong());

        // Wrong TID so gets ignored
        transaction.transactionStatusReceived(ZigBeeTransportProgressState.TX_NAK, 123);
        assertFalse(transactionFuture.isDone());
        assertFalse(transactionFuture.isCancelled());

        // Correct TID
        transaction.transactionStatusReceived(ZigBeeTransportProgressState.TX_NAK, 12);

        Mockito.verify(transactionManager, Mockito.times(1)).transactionComplete(transaction, TransactionState.FAILED);

        // Doesn't complete the transaction - this is done by the queue so it can account for retries
        assertFalse(transactionFuture.isDone());
        assertFalse(transactionFuture.isCancelled());

        transaction.cancel();
        assertTrue(transactionFuture.isDone());
        assertTrue(transactionFuture.isCancelled());

        System.out.println(transaction.toString());
    }

    @Test
    public void testRxNak() {
        ZigBeeTransactionManager transactionManager = Mockito.mock(ZigBeeTransactionManager.class);
        ZigBeeCommand command = Mockito.mock(ZigBeeCommand.class);
        Mockito.when(command.getTransactionId()).thenReturn(12);
        ZigBeeTransactionMatcher matcher = Mockito.mock(ZigBeeTransactionMatcher.class);

        ZigBeeTransactionFuture transactionFuture = new ZigBeeTransactionFuture();

        ZigBeeTransaction transaction = new ZigBeeTransaction(transactionManager, command, matcher);
        transaction.setFuture(transactionFuture);

        assertEquals(command, transaction.startTransaction());
        Mockito.verify(transactionManager, Mockito.times(1)).scheduleTask(ArgumentMatchers.any(Runnable.class),
                ArgumentMatchers.anyLong());

        transaction.transactionStatusReceived(ZigBeeTransportProgressState.TX_ACK, 12);

        Mockito.verify(transactionManager, Mockito.times(1)).scheduleTask(ArgumentMatchers.any(Runnable.class),
                ArgumentMatchers.eq(8000L));

        transaction.transactionStatusReceived(ZigBeeTransportProgressState.RX_NAK, 12);

        Mockito.verify(transactionManager, Mockito.times(1)).transactionComplete(transaction, TransactionState.FAILED);

        // Doesn't complete the transaction - this is done by the queue so it can account for retries
        assertFalse(transactionFuture.isDone());
        assertFalse(transactionFuture.isCancelled());
    }

    @Test
    public void testMatchedBeforeRxAck() {
        ZigBeeTransactionManager transactionManager = Mockito.mock(ZigBeeTransactionManager.class);
        ZigBeeCommand command = Mockito.mock(ZigBeeCommand.class);
        Mockito.when(command.getTransactionId()).thenReturn(12);
        ZigBeeTransactionMatcher matcher = Mockito.mock(ZigBeeTransactionMatcher.class);

        ZigBeeCommand request = Mockito.mock(ZigBeeCommand.class);
        Mockito.when(matcher.isTransactionMatch(command, request)).thenReturn(true);

        ZigBeeTransactionFuture transactionFuture = new ZigBeeTransactionFuture();

        ZigBeeTransaction transaction = new ZigBeeTransaction(transactionManager, command, matcher);
        transaction.setFuture(transactionFuture);

        assertEquals(command, transaction.startTransaction());
        Mockito.verify(transactionManager, Mockito.times(1)).scheduleTask(ArgumentMatchers.any(Runnable.class),
                ArgumentMatchers.anyLong());

        transaction.transactionStatusReceived(ZigBeeTransportProgressState.TX_ACK, 12);

        Mockito.verify(transactionManager, Mockito.times(1)).scheduleTask(ArgumentMatchers.any(Runnable.class),
                ArgumentMatchers.eq(8000L));

        // We've updated state with TX_ACK, but not RX_ACK,
        // so providing the application response here should not complete the transaction
        transaction.commandReceived(request);
        Mockito.verify(transactionManager, Mockito.never()).transactionComplete(transaction, TransactionState.COMPLETE);

        transaction.transactionStatusReceived(ZigBeeTransportProgressState.RX_ACK, 12);

        Mockito.verify(transactionManager, Mockito.times(1)).transactionComplete(transaction,
                TransactionState.COMPLETE);
    }

    @Test
    public void testMatchedBeforeRxNak() {
        ZigBeeTransactionManager transactionManager = Mockito.mock(ZigBeeTransactionManager.class);
        ZigBeeCommand command = Mockito.mock(ZigBeeCommand.class);
        Mockito.when(command.getTransactionId()).thenReturn(12);
        ZigBeeTransactionMatcher matcher = Mockito.mock(ZigBeeTransactionMatcher.class);

        ZigBeeCommand request = Mockito.mock(ZigBeeCommand.class);
        Mockito.when(matcher.isTransactionMatch(command, request)).thenReturn(true);

        ZigBeeTransactionFuture transactionFuture = new ZigBeeTransactionFuture();

        ZigBeeTransaction transaction = new ZigBeeTransaction(transactionManager, command, matcher);
        transaction.setFuture(transactionFuture);

        assertEquals(command, transaction.startTransaction());
        Mockito.verify(transactionManager, Mockito.times(1)).scheduleTask(ArgumentMatchers.any(Runnable.class),
                ArgumentMatchers.anyLong());

        transaction.transactionStatusReceived(ZigBeeTransportProgressState.TX_ACK, 12);

        Mockito.verify(transactionManager, Mockito.times(1)).scheduleTask(ArgumentMatchers.any(Runnable.class),
                ArgumentMatchers.eq(8000L));

        // We've updated state with TX_ACK, but not RX_ACK,
        // so providing the application response here should not complete the transaction
        transaction.commandReceived(request);
        Mockito.verify(transactionManager, Mockito.never()).transactionComplete(transaction, TransactionState.COMPLETE);

        transaction.transactionStatusReceived(ZigBeeTransportProgressState.RX_NAK, 12);

        Mockito.verify(transactionManager, Mockito.times(1)).transactionComplete(transaction,
                TransactionState.COMPLETE);
    }

    @Test
    public void testSendOnly() {
        ZigBeeTransactionManager transactionManager = Mockito.mock(ZigBeeTransactionManager.class);
        ZigBeeCommand command = Mockito.mock(ZigBeeCommand.class);
        Mockito.when(command.getTransactionId()).thenReturn(12);

        ZigBeeTransactionFuture transactionFuture = new ZigBeeTransactionFuture();

        ZigBeeTransaction transaction = new ZigBeeTransaction(transactionManager, command, null);
        transaction.setFuture(transactionFuture);

        // transaction.startTimer();
        assertEquals(command, transaction.startTransaction());
        Mockito.verify(transactionManager, Mockito.times(1)).scheduleTask(ArgumentMatchers.any(Runnable.class),
                ArgumentMatchers.anyLong());

        transaction.transactionStatusReceived(ZigBeeTransportProgressState.RX_ACK, 12);

        // Wrong TID so gets ignored
        transaction.transactionStatusReceived(ZigBeeTransportProgressState.TX_NAK, 123);
        assertFalse(transactionFuture.isDone());
        assertFalse(transactionFuture.isCancelled());

        // Correct TID
        transaction.transactionStatusReceived(ZigBeeTransportProgressState.TX_ACK, 12);

        Mockito.verify(transactionManager, Mockito.times(1)).transactionComplete(transaction,
                TransactionState.COMPLETE);

        assertTrue(transactionFuture.isDone());
        assertFalse(transactionFuture.isCancelled());
    }

    @Test
    public void commandReceived() {
        // Note that this test does not use the transport transaction state feedback,
        // so the transaction should complete as soon as the matcher is happy.
        ZigBeeTransactionManager transactionManager = Mockito.mock(ZigBeeTransactionManager.class);
        ZigBeeCommand command = Mockito.mock(ZigBeeCommand.class);
        Mockito.when(command.getTransactionId()).thenReturn(12);

        ZigBeeTransactionFuture transactionFuture = new ZigBeeTransactionFuture();

        ZigBeeCommand matchRequest = Mockito.mock(ZigBeeCommand.class);
        ZigBeeCommand nomatchRequest = Mockito.mock(ZigBeeCommand.class);
        ZigBeeTransactionMatcher matcher = Mockito.mock(ZigBeeTransactionMatcher.class);
        Mockito.when(matcher.isTransactionMatch(command, matchRequest)).thenReturn(true);
        Mockito.when(matcher.isTransactionMatch(command, nomatchRequest)).thenReturn(false);

        ZigBeeTransaction transaction = new ZigBeeTransaction(transactionManager, command, matcher);
        transaction.setFuture(transactionFuture);
        assertEquals(transactionFuture, transaction.getFuture());

        transaction.commandReceived(nomatchRequest);
        assertFalse(transactionFuture.isDone());
        assertFalse(transactionFuture.isCancelled());

        transaction.commandReceived(matchRequest);
        assertTrue(transactionFuture.isDone());
        assertFalse(transactionFuture.isCancelled());
    }

    @Test
    public void getTimeout() {
        ZigBeeTransaction transaction = new ZigBeeTransaction(null, null, null);
        System.out.println(transaction);
        assertNull(transaction.getQueueTime());
        transaction.setQueueTime();
        System.out.println(transaction);
        assertNotNull(transaction.getQueueTime());

        int timeout1 = transaction.getTimerPeriod1();
        transaction.setTimerPeriod1(timeout1 + 23);
        assertEquals(timeout1 + 23, transaction.getTimerPeriod1());

        int timeout2 = transaction.getTimerPeriod1();
        transaction.setTimerPeriod1(timeout2 + 23);
        assertEquals(timeout2 + 23, transaction.getTimerPeriod1());
    }

    @Test
    public void setTransactionId() {
        ZigBeeTransaction transaction = new ZigBeeTransaction(null, new OffCommand(), null);
        assertNull(transaction.getTransactionId());
        transaction.setTransactionId(1);
        assertEquals(Integer.valueOf(1), transaction.getTransactionId());
    }
}

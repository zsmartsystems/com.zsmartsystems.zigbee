/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.transaction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.ScheduledFuture;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import com.zsmartsystems.zigbee.ZigBeeCommand;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportProgressState;

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

        transaction.send();
        Mockito.verify(transactionManager, Mockito.times(1)).addTransactionListener(transaction);
        Mockito.verify(transactionManager, Mockito.times(1)).send(command);

        Runnable timeout = timerCaptor.getValue();
        assertNotNull(timeout);

        assertFalse(transactionFuture.isDone());
        assertFalse(transactionFuture.isCancelled());

        timeout.run();
        Mockito.verify(transactionManager, Mockito.times(1)).removeTransactionListener(transaction);

        assertTrue(transactionFuture.isDone());
        assertTrue(transactionFuture.isCancelled());
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

        transaction.send();
        Mockito.verify(transactionManager, Mockito.times(1)).scheduleTask(ArgumentMatchers.any(Runnable.class),
                ArgumentMatchers.anyLong());
        Mockito.verify(transactionManager, Mockito.times(1)).addTransactionListener(transaction);
        Mockito.verify(transactionManager, Mockito.times(1)).send(ArgumentMatchers.any(ZigBeeCommand.class));

        // Wrong TID so gets ignored
        transaction.commandStatusReceived(ZigBeeTransportProgressState.TX_NAK, 123);
        assertFalse(transactionFuture.isDone());
        assertFalse(transactionFuture.isCancelled());

        // Correct TID
        transaction.commandStatusReceived(ZigBeeTransportProgressState.TX_NAK, 12);

        Mockito.verify(transactionManager, Mockito.times(1)).removeTransactionListener(transaction);

        assertTrue(transactionFuture.isDone());
        assertTrue(transactionFuture.isCancelled());
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

        transaction.send();
        Mockito.verify(transactionManager, Mockito.times(1)).scheduleTask(ArgumentMatchers.any(Runnable.class),
                ArgumentMatchers.anyLong());
        Mockito.verify(transactionManager, Mockito.times(1)).addTransactionListener(transaction);
        Mockito.verify(transactionManager, Mockito.times(1)).send(command);

        transaction.commandStatusReceived(ZigBeeTransportProgressState.TX_ACK, 12);

        Mockito.verify(transactionManager, Mockito.times(2)).scheduleTask(ArgumentMatchers.any(Runnable.class),
                ArgumentMatchers.anyLong());

        transaction.commandStatusReceived(ZigBeeTransportProgressState.RX_NAK, 12);

        Mockito.verify(transactionManager, Mockito.times(1)).removeTransactionListener(transaction);

        assertTrue(transactionFuture.isDone());
        assertTrue(transactionFuture.isCancelled());
    }

    @Test
    public void testSendOnly() {
        ZigBeeTransactionManager transactionManager = Mockito.mock(ZigBeeTransactionManager.class);
        ZigBeeCommand command = Mockito.mock(ZigBeeCommand.class);
        Mockito.when(command.getTransactionId()).thenReturn(12);

        ZigBeeTransactionFuture transactionFuture = new ZigBeeTransactionFuture();

        ZigBeeTransaction transaction = new ZigBeeTransaction(transactionManager, command, null);
        transaction.setFuture(transactionFuture);

        transaction.send();
        Mockito.verify(transactionManager, Mockito.times(1)).scheduleTask(ArgumentMatchers.any(Runnable.class),
                ArgumentMatchers.anyLong());
        Mockito.verify(transactionManager, Mockito.times(1)).send(command);
        Mockito.verify(transactionManager, Mockito.times(0)).addTransactionListener(transaction);

        // Wrong TID so gets ignored
        transaction.commandStatusReceived(ZigBeeTransportProgressState.TX_NAK, 123);
        assertFalse(transactionFuture.isDone());
        assertFalse(transactionFuture.isCancelled());

        // Correct TID
        transaction.commandStatusReceived(ZigBeeTransportProgressState.TX_ACK, 12);

        Mockito.verify(transactionManager, Mockito.times(0)).removeTransactionListener(transaction);

        assertTrue(transactionFuture.isDone());
        assertFalse(transactionFuture.isCancelled());
    }

    @Test
    public void commandReceived() {
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

        int timeout = transaction.getTimeout();
        transaction.setTimeout(timeout + 23);
        assertEquals(timeout + 23, transaction.getTimeout());
    }
}

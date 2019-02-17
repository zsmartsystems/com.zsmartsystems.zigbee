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

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;

import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.TestUtilities;
import com.zsmartsystems.zigbee.ZigBeeBroadcastDestination;
import com.zsmartsystems.zigbee.ZigBeeCommand;
import com.zsmartsystems.zigbee.ZigBeeEndpointAddress;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNode;
import com.zsmartsystems.zigbee.transaction.ZigBeeTransaction.TransactionState;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportProgressState;

/**
 * Tests for {@link ZigBeeTransactionManager}
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeTransactionManagerTest {
    private static int TIMEOUT = 5000;

    @Test
    public void sendTransaction() {
        ZigBeeNetworkManager networkManager = Mockito.mock(ZigBeeNetworkManager.class);
        ZigBeeCommand command = Mockito.mock(ZigBeeCommand.class);
        Mockito.when(command.getDestinationAddress()).thenReturn(new ZigBeeEndpointAddress(123));
        Mockito.when(command.getTransactionId()).thenReturn(null);
        ZigBeeTransactionMatcher responseMatcher = Mockito.mock(ZigBeeTransactionMatcher.class);
        ZigBeeNode node = Mockito.mock(ZigBeeNode.class);
        Mockito.when(node.getIeeeAddress()).thenReturn(new IeeeAddress("1234567890ABCDEF"));

        Mockito.when(networkManager.getNode(123)).thenReturn(node);

        ZigBeeTransactionManager transactionManager = new ZigBeeTransactionManager(networkManager) {
            @Override
            protected ScheduledFuture<?> scheduleTask(Runnable runnableTask, long delay) {
                return Mockito.mock(ScheduledFuture.class);
            }
        };

        transactionManager.setDefaultProfile(new ZigBeeTransactionProfile(0, 12, 0, false));
        transactionManager.setMulticastProfile(new ZigBeeTransactionProfile(2, 100, 0, true));
        transactionManager.setBroadcastProfile(new ZigBeeTransactionProfile(2, 100, 0, true));
        transactionManager.setMaxOutstandingTransactions(4);
        assertEquals(4, transactionManager.getMaxOutstandingTransactions());

        assertNull(transactionManager.getQueue(new IeeeAddress("1234567890ABCDEF")));

        // Send a transaction to a node that is known by the network manager - the transaction should be sent
        Future<CommandResult> cmdResult = transactionManager.sendTransaction(command, responseMatcher);
        assertNotNull(cmdResult);
        Mockito.verify(command, Mockito.times(1)).setTransactionId(ArgumentMatchers.anyInt());
        Mockito.verify(networkManager, Mockito.timeout(TIMEOUT).times(1)).sendCommand(command);
        assertNotNull(transactionManager.getQueue(new IeeeAddress("1234567890ABCDEF")));

        // Send a transaction to a node that is unknown by the transaction manager. This will not be sent.
        ZigBeeCommand unknownCommand = Mockito.mock(ZigBeeCommand.class);
        Mockito.when(unknownCommand.getDestinationAddress()).thenReturn(new ZigBeeEndpointAddress(456));
        cmdResult = transactionManager.sendTransaction(unknownCommand, responseMatcher);
        assertNull(cmdResult);

        // Send a transaction without matcher
        command = Mockito.mock(ZigBeeCommand.class);
        Mockito.when(command.getDestinationAddress()).thenReturn(new ZigBeeEndpointAddress(123));
        transactionManager.sendTransaction(command);
        Mockito.verify(networkManager, Mockito.timeout(TIMEOUT).times(1)).sendCommand(command);

        // Send a broadcast transaction
        command = Mockito.mock(ZigBeeCommand.class);
        Mockito.when(command.getDestinationAddress())
                .thenReturn(new ZigBeeEndpointAddress(ZigBeeBroadcastDestination.BROADCAST_ALL_DEVICES.getKey()));
        transactionManager.sendTransaction(command);
        Mockito.verify(networkManager, Mockito.timeout(TIMEOUT).times(1)).sendCommand(command);

        transactionManager.removeNode(new IeeeAddress("1234567890ABCDEF"));
        assertNull(transactionManager.getQueue(new IeeeAddress("1234567890ABCDEF")));

        transactionManager.removeNode(new IeeeAddress("9999999999999999"));

        transactionManager.shutdown();
    }

    @Test
    public void receiveAck() throws Exception {
        ZigBeeNetworkManager networkManager = Mockito.mock(ZigBeeNetworkManager.class);
        ZigBeeTransactionManager transactionManager = new ZigBeeTransactionManager(networkManager);

        ZigBeeTransaction transaction = Mockito.mock(ZigBeeTransaction.class);

        IeeeAddress ieeeAddress = new IeeeAddress("1234567890ABCDEF");
        ZigBeeTransactionQueue queue = Mockito.mock(ZigBeeTransactionQueue.class);
        ZigBeeCommand command = Mockito.mock(ZigBeeCommand.class);
        Mockito.when(command.getDestinationAddress()).thenReturn(new ZigBeeEndpointAddress(123));

        Map<IeeeAddress, ZigBeeTransactionQueue> nodeQueue = new ConcurrentHashMap<>();
        nodeQueue.put(ieeeAddress, queue);

        TestUtilities.setField(ZigBeeTransactionManager.class, transactionManager, "nodeQueue", nodeQueue);
        TestUtilities.invokeMethod(ZigBeeTransactionManager.class, transactionManager, "addTransactionListener",
                ZigBeeTransaction.class, transaction);

        transactionManager.receive(command);
        Mockito.verify(transaction, Mockito.timeout(TIMEOUT)).commandReceived(command);

        transactionManager.receiveCommandState(123, ZigBeeTransportProgressState.RX_ACK);
        Mockito.verify(transaction, Mockito.timeout(TIMEOUT))
                .transactionStatusReceived(ZigBeeTransportProgressState.RX_ACK, 123);

        transactionManager.transactionComplete(transaction, TransactionState.COMPLETE);

        // First
        Mockito.verify(transaction, Mockito.times(0)).cancel();

        Mockito.verify(queue, Mockito.timeout(TIMEOUT).times(0))
                .addToQueue(ArgumentMatchers.any(ZigBeeTransaction.class));

        // Send another command and make sure the commandReceived method is not called again
        transactionManager.receive(command);
        Mockito.verify(transaction, Mockito.times(1)).commandReceived(command);

        transactionManager.shutdown();
        Mockito.verify(queue, Mockito.times(1)).shutdown();
    }

    @Test
    public void receiveNak() throws Exception {
        ZigBeeNetworkManager networkManager = Mockito.mock(ZigBeeNetworkManager.class);
        ZigBeeTransactionManager transactionManager = new ZigBeeTransactionManager(networkManager);

        ZigBeeTransaction transaction = Mockito.mock(ZigBeeTransaction.class);

        IeeeAddress ieeeAddress = new IeeeAddress("1234567890ABCDEF");
        ZigBeeTransactionQueue queue = Mockito.mock(ZigBeeTransactionQueue.class);
        ZigBeeCommand rxCommand = Mockito.mock(ZigBeeCommand.class);
        Mockito.when(rxCommand.getDestinationAddress()).thenReturn(new ZigBeeEndpointAddress(123));
        ZigBeeNode node = Mockito.mock(ZigBeeNode.class);
        Mockito.when(node.getIeeeAddress()).thenReturn(ieeeAddress);
        Mockito.when(networkManager.getNode(123)).thenReturn(node);

        ZigBeeCommand txCommand = Mockito.mock(ZigBeeCommand.class);
        Mockito.when(transaction.startTransaction()).thenReturn(txCommand);
        Mockito.when(transaction.getDestinationAddress()).thenReturn(new ZigBeeEndpointAddress(123));

        Queue<ZigBeeTransaction> transactions = new LinkedList<>();
        transactions.add(transaction);
        Mockito.when(queue.getTransaction()).thenReturn(transactions.poll());

        Mockito.when(queue.isEmpty()).thenReturn(transactions.isEmpty());

        Map<IeeeAddress, ZigBeeTransactionQueue> nodeQueue = new ConcurrentHashMap<>();
        nodeQueue.put(ieeeAddress, queue);

        TestUtilities.setField(ZigBeeTransactionManager.class, transactionManager, "nodeQueue", nodeQueue);
        TestUtilities.invokeMethod(ZigBeeTransactionManager.class, transactionManager, "addTransactionListener",
                ZigBeeTransaction.class, transaction);

        // Pass a received command to the transaction manager and make sure it is received by the transaction
        assertEquals(rxCommand, transactionManager.receive(rxCommand));
        Mockito.verify(transaction, Mockito.timeout(TIMEOUT)).commandReceived(rxCommand);

        // Update the state with an ACK received from the remote
        transactionManager.receiveCommandState(123, ZigBeeTransportProgressState.RX_ACK);
        Mockito.verify(transaction, Mockito.timeout(TIMEOUT))
                .transactionStatusReceived(ZigBeeTransportProgressState.RX_ACK, 123);

        // If the transaction aborts, it sends a FAILED state - the transaction should be notified the queue
        transactionManager.transactionComplete(transaction, TransactionState.FAILED);
        Mockito.verify(queue, Mockito.times(1)).transactionComplete(transaction, TransactionState.FAILED);

        // Now complete the transaction - we also get the completed notification to the queue
        transactionManager.transactionComplete(transaction, TransactionState.COMPLETE);
        Mockito.verify(queue, Mockito.times(1)).transactionComplete(transaction, TransactionState.COMPLETE);

        // Send another command and make sure the commandReceived method is not called again
        transactionManager.receive(rxCommand);
        Mockito.verify(transaction, Mockito.times(1)).commandReceived(rxCommand);
    }

    @Test
    public void transactionTimer() throws Exception {
        ZigBeeNetworkManager networkManager = Mockito.mock(ZigBeeNetworkManager.class);

        final List<Long> delayCapture = new ArrayList<>();
        ZigBeeTransactionManager transactionManager = new ZigBeeTransactionManager(networkManager) {
            @Override
            protected ScheduledFuture<?> scheduleTask(Runnable runnableTask, long delay) {
                delayCapture.add(delay);
                return Mockito.mock(ScheduledFuture.class);
            }
        };

        ZigBeeTransactionQueue queue1 = Mockito.mock(ZigBeeTransactionQueue.class);
        Mockito.when(queue1.getNextReleaseTime()).thenReturn(111L);
        ZigBeeTransactionQueue queue2 = Mockito.mock(ZigBeeTransactionQueue.class);
        Mockito.when(queue2.getNextReleaseTime()).thenReturn(222L);
        ZigBeeTransactionQueue queue3 = Mockito.mock(ZigBeeTransactionQueue.class);
        Mockito.when(queue3.getNextReleaseTime()).thenReturn(333L);

        Map<IeeeAddress, ZigBeeTransactionQueue> nodeQueue = new ConcurrentHashMap<>();
        nodeQueue.put(new IeeeAddress("1111111111111111"), queue1);
        nodeQueue.put(new IeeeAddress("2222222222222222"), queue2);
        nodeQueue.put(new IeeeAddress("3333333333333333"), queue3);

        List<ZigBeeTransactionQueue> outstandingQueues = new ArrayList<>();
        outstandingQueues.add(queue1);
        outstandingQueues.add(queue2);
        outstandingQueues.add(queue3);

        TestUtilities.setField(ZigBeeTransactionManager.class, transactionManager, "nodeQueue", nodeQueue);
        TestUtilities.setField(ZigBeeTransactionManager.class, transactionManager, "outstandingQueues",
                outstandingQueues);
        TestUtilities.invokeMethod(ZigBeeTransactionManager.class, transactionManager, "sendNextTransaction");

        assertFalse(delayCapture.isEmpty());
        assertEquals(Long.valueOf(111), delayCapture.get(0));
    }
}

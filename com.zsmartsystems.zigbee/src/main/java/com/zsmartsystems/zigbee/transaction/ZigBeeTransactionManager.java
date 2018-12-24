/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.transaction;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.ZigBeeCommand;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.internal.NotificationService;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportProgressState;

/**
 * The centralised transaction manager
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeTransactionManager {
    /**
     * The {@link ZigBeeNetworkManager} to which this manager belongs
     */
    private final ZigBeeNetworkManager networkManager;

    /**
     * The set of outstanding transactions - used to notify transactions when responses are received.
     */
    private Set<ZigBeeTransaction> outstandingTransactions = new HashSet<>();

    /**
     * Executor service to execute update threads for discovery or mesh updates etc.
     * We use a {@link Executors.newScheduledThreadPool} to provide a fixed number of threads as otherwise this could
     * result in a large number of simultaneous threads in large networks.
     */
    private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(6);

    public ZigBeeTransactionManager(ZigBeeNetworkManager manager) {
        this.networkManager = manager;
    }

    /**
     * Sends a command without waiting for a response
     *
     * @param command the {@link ZigBeeCommand} to send
     */
    public void sendTransaction(ZigBeeCommand command) {
        sendTransaction(command, null);
    }

    /**
     * Sends a command, and uses the {@link ZigBeeTransactionMatcher} to match the response which will complete the
     * transaction.
     *
     * @param command the {@link ZigBeeCommand} to send
     * @param responseMatcher the {@link ZigBeeTransactionMatcher} to match the response which will complete the
     *            transaction.
     * @return the future {@link CommandResult}
     */
    public Future<CommandResult> sendTransaction(ZigBeeCommand command, ZigBeeTransactionMatcher responseMatcher) {
        ZigBeeTransactionFuture transactionFuture = new ZigBeeTransactionFuture();
        ZigBeeTransaction transaction = new ZigBeeTransaction(this, command, responseMatcher);
        transaction.setFuture(transactionFuture);
        transaction.send();

        return transactionFuture;
    }

    /**
     * Sends the command to the transport layer
     *
     * @param command the {@link ZigBeeCommand} to send
     */
    protected void send(ZigBeeCommand command) {
        networkManager.sendCommand(command);
    }

    /**
     * Processes a received frame within the transaction manager, and returns the frame that is to fed up the stack. The
     * transaction manager may return null from this command if it should not be processed up the stack.
     *
     * @param command the received {@link ZigBeeCommand}
     * @return the {@link ZigBeeCommand} to be used within the library or null if the frame is not to be fed into the
     *         rest of the system
     */
    public ZigBeeCommand receive(final ZigBeeCommand command) {
        notifyTransactionCommand(command);
        return command;
    }

    /**
     * Callback from the transport layer when it has progressed the state of the transaction.
     *
     * @param transactionId the transaction ID whose state is updated
     * @param status the updated {@link ZigBeeTransportProgressState} for the transaction
     */
    public void receiveCommandStatus(int transactionId, ZigBeeTransportProgressState status) {
        notifyTransactionProgress(transactionId, status);
    }

    /**
     * Adds a transaction to the list of outstanding transactions. Transactions will receive notifications when a
     * command is received, or when the status is updated
     *
     * @param transaction the {@link ZigBeeTransaction} that will receive the notifications
     */
    public void addTransactionListener(ZigBeeTransaction transaction) {
        synchronized (outstandingTransactions) {
            outstandingTransactions.add(transaction);
        }
    }

    /**
     * Removes a transaction from the list of outstanding transactions.
     *
     * @param transaction the {@link ZigBeeTransaction} to remove
     */
    public void removeTransactionListener(ZigBeeTransaction transaction) {
        synchronized (outstandingTransactions) {
            outstandingTransactions.remove(transaction);
        }
    }

    protected ScheduledFuture<?> scheduleTask(Runnable runnableTask, long delay) {
        return executorService.schedule(runnableTask, delay, TimeUnit.MILLISECONDS);
    }

    /**
     * Notify transactions of the received command
     *
     * @param command the {@link ZigBeeCommand} to send to the transactions
     */
    private void notifyTransactionCommand(final ZigBeeCommand command) {
        synchronized (outstandingTransactions) {
            // Notify the listeners
            for (final ZigBeeTransaction transaction : outstandingTransactions) {
                NotificationService.execute(new Runnable() {
                    @Override
                    public void run() {
                        transaction.commandReceived(command);
                    }
                });
            }
        }
    }

    /**
     * Notify transactions of the progress
     *
     * @param command the {@link ZigBeeCommand} to send to the listeners
     */
    private void notifyTransactionProgress(final int transactionId, ZigBeeTransportProgressState state) {
        synchronized (outstandingTransactions) {
            // Notify the listeners
            for (final ZigBeeTransaction transaction : outstandingTransactions) {
                NotificationService.execute(new Runnable() {
                    @Override
                    public void run() {
                        transaction.commandStatusReceived(state, transactionId);
                    }
                });
            }
        }
    }
}

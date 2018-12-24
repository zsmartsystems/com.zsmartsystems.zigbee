/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.transaction;

import java.util.concurrent.ScheduledFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.ZigBeeCommand;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportProgressState;

/**
 * Transaction class to handle the sending of commands and timeout in the event there is no response.
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeTransaction {
    /**
     * The logger.
     */
    private final Logger logger = LoggerFactory.getLogger(ZigBeeTransaction.class);

    public enum TransactionState {
        /**
         * Transaction is idle and should be waiting in the queue to be sent
         */
        WAITING,

        /**
         * Transaction has been sent, but no response has been received from the transport
         */
        DISPATCHED,

        /**
         * Transaction has been sent, and the ACK received from the transport to confirm it has been sent over the air
         */
        TRANSMITTED,

        /**
         * The low level ACK was received to confirm the command was received by the remote device
         */
        ACKED,

        /**
         * Transaction has been completed successfully
         */
        COMPLETE,

        /**
         * Transaction failed - no response received
         */
        FAILED
    }

    private final ZigBeeTransactionManager transactionManager;
    private ZigBeeTransactionFuture transactionFuture;
    private ZigBeeTransactionMatcher responseMatcher;
    private ZigBeeCommand command;
    private ScheduledFuture<?> timeoutTask;

    private TransactionState state = TransactionState.WAITING;

    /**
     * The amount of time (in milliseconds) from when the command is sent to the transport, until when the transport
     * acknowledges it has been transmitted.
     * This must account for the total time from when the data is sent, until the response is expected, included any
     * queueing delay in the transport.
     */
    private final static int TRANSACTION_TIMER = 12000;

    /**
     * The amount of time (in milliseconds) to wait for a response from the transport to acknowledge the command was
     * transmitted.
     */
    private final static int TRANSACTION_TIMER_BEFORE_TX = 4000;

    /**
     * The amount of time (in milliseconds) to wait for a response from the transport once the command has been
     * transmitted.
     */
    private final static int TRANSACTION_TIMER_AFTER_TX = 8000;

    private int timeout = TRANSACTION_TIMER;

    /**
     * Transaction constructor
     *
     * @param networkManager the {@link ZigBeeNetworkManager} to which the transaction is being sent.
     * @param command the {@link ZigBeeCommand}.
     * @param responseMatcher the {@link ZigBeeTransactionMatcher} to match the response and complete the transaction.
     *            May be null if no response is expected.
     */
    public ZigBeeTransaction(ZigBeeTransactionManager transactionManager, final ZigBeeCommand command,
            final ZigBeeTransactionMatcher responseMatcher) {
        this.transactionManager = transactionManager;
        this.command = command;
        this.responseMatcher = responseMatcher;
    }

    /**
     * Sends {@link ZigBeeCommand} command and uses the {@link ZigBeeTransactionMatcher} to match the response.
     * The task will be timed out if there is no response.
     *
     * @return the {@link CommandResult} future.
     */
    public void send() {
        logger.debug("Sending transaction: {} ==== {}", command, responseMatcher);
        synchronized (command) {
            // If we have no response matcher then we don't worry about adding the listener, or starting the
            if (responseMatcher != null) {
                transactionManager.addTransactionListener(this);

                // Schedule a task to timeout the transaction
                startTimer(timeout);
            } else {
                // Wait for the transport layer to confirm the command was sent
                startTimer(TRANSACTION_TIMER_BEFORE_TX);
            }

            transactionManager.send(command);
        }
    }

    /**
     * @return the current timeout in milliseconds
     */
    public int getTimeout() {
        return timeout;
    }

    /**
     * @param timeout the timeout to set in milliseconds
     */
    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    protected void setFuture(ZigBeeTransactionFuture transactionFuture) {
        this.transactionFuture = transactionFuture;
    }

    protected ZigBeeTransactionFuture getFuture() {
        return transactionFuture;
    }

    /**
     * Called by the transaction manager when a {@link ZigBeeCommand} is received. The transaction should check this
     * command to see if it completes the transaction.
     *
     * @param receivedCommand the incoming {@link ZigBeeCommand}
     */
    public void commandReceived(ZigBeeCommand receivedCommand) {
        // Ensure that received command is not processed before command is sent
        // and hence transaction ID for the command set.
        synchronized (command) {
            if (responseMatcher.isTransactionMatch(command, receivedCommand)) {
                completeTransaction(receivedCommand);
            }
        }
    }

    private void startTimer(int timeout) {
        if (timeoutTask != null) {
            timeoutTask.cancel(false);
        }

        // Schedule a task to timeout the transaction
        timeoutTask = transactionManager.scheduleTask(new Runnable() {
            @Override
            public void run() {
                cancelTransaction();
            }
        }, timeout);
    }

    private void completeTransaction(ZigBeeCommand receivedCommand) {
        if (timeoutTask != null) {
            timeoutTask.cancel(false);
        }
        logger.debug("Transaction complete: {}", command);
        if (transactionFuture != null) {
            synchronized (transactionFuture) {
                transactionFuture.set(new CommandResult(receivedCommand));
                transactionFuture.notify();
            }
        }
        if (responseMatcher != null) {
            transactionManager.removeTransactionListener(this);
        }
        state = TransactionState.COMPLETE;
    }

    private void cancelTransaction() {
        if (timeoutTask != null) {
            timeoutTask.cancel(false);
        }
        logger.debug("Transaction cancelled: {}", command);
        if (transactionFuture != null) {
            synchronized (transactionFuture) {
                transactionFuture.cancel(false);
                transactionFuture.notify();
            }
        }
        if (responseMatcher != null) {
            transactionManager.removeTransactionListener(this);
        }
        state = TransactionState.FAILED;
    }

    /**
     *
     * @param state
     * @param transactionId
     */
    public void commandStatusReceived(ZigBeeTransportProgressState progress, int transactionId) {
        synchronized (command) {
            if (command.getTransactionId() != transactionId) {
                return;
            }

            logger.debug("Transaction state update : TID {} -> {} == {}", transactionId, progress, state);

            switch (progress) {
                case TX_NAK:
                    // The transport layer failed to send the command
                    cancelTransaction();
                    break;
                case TX_ACK:
                    // If we aren't waiting for a response, then we're done
                    if (responseMatcher == null) {
                        completeTransaction(null);
                        break;
                    }

                    // The command was transmitted ok
                    state = TransactionState.TRANSMITTED;

                    // The timer is reset here as we have confirmation the command is sent
                    startTimer(TRANSACTION_TIMER_AFTER_TX);
                    break;
                case RX_NAK:
                    // The transport layer failed to get an ack from the remote device
                    cancelTransaction();
                    break;
                case RX_ACK:
                    // The remote device confirmed receipt of the command
                    state = TransactionState.ACKED;
                    break;
                default:
                    break;
            }
        }
        logger.debug("Transaction state updated: TID {} -> {} == {}", transactionId, progress, state);
    }
}

/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.transaction;

import java.util.Objects;
import java.util.concurrent.ScheduledFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.ZigBeeAddress;
import com.zsmartsystems.zigbee.ZigBeeCommand;
import com.zsmartsystems.zigbee.ZigBeeStatus;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportProgressState;
import com.zsmartsystems.zigbee.zcl.ZclCommand;

/**
 * Transaction class to handle the sending of commands and timeout in the event there is no response.
 * <p>
 * The transaction handles the feedback from the dongle via the
 * {@link #transactionStatusReceived(ZigBeeTransportProgressState, int)} callback, and if these notifications are
 * provided, will ensure that the dongle sends an APS ack/nak before the transaction is completed. This is done to avoid
 * the situation where at application level the transaction is considered complete as the response command was received,
 * but at transport level the APS ACK has not yet been received and the transport has not released the transaction.
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeTransaction {
    /**
     * The logger.
     */
    private final Logger logger = LoggerFactory.getLogger(ZigBeeTransaction.class);

    /**
     * Enumeration defining the transaction states
     *
     */
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
         * The low level APS ACK was received to confirm the command was received by the remote device
         */
        ACKED,

        /**
         * The transaction has received a response that the TransactionMatcher has used to complete the transaction.
         * This state is used only if we have not received the RX_NAK/RX_ACK updates from the transport to avoid sending
         * further transactions to the transport when it is still handling the APS level transaction.
         */
        RESPONDED,

        /**
         * Transaction has been completed successfully
         */
        COMPLETE,

        /**
         * Transaction failed - no response received
         */
        FAILED,

        /**
         * Transaction cancelled
         */
        CANCELLED
    }

    /**
     * The {@link ZigBeeTransactionManager} through which the transaction is being managed
     */
    private final ZigBeeTransactionManager transactionManager;

    /**
     * The {@link ZigBeeTransactionFuture} that will be fulfilled once the transaction completes
     */
    private ZigBeeTransactionFuture transactionFuture;

    /**
     * The {@link ZigBeeTransactionMatcher} used to match the response to the command
     */
    private final ZigBeeTransactionMatcher responseMatcher;

    /**
     * The {@link ZigBeeCommand} that we are sending
     */
    private final ZigBeeCommand command;

    /**
     * The @{link {@link IeeeAddress} of the device that we are sending the command of this transaction to
     */
    private IeeeAddress ieeeAddress;

    /**
     * The task used for transaction timeouts
     */
    private ScheduledFuture<?> timeoutTask;

    /**
     * The number of times we've sent this command. Used for retry management.
     */
    private int sendCnt = 0;

    /**
     * The current state of the transaction
     */
    private TransactionState state = TransactionState.WAITING;

    /**
     * In the event that the application level response is received before the transport response, we need to remember
     * the command that completed the transaction.
     */
    private ZigBeeCommand completionCommand;

    /**
     * The amount of time (in milliseconds) from when the command is sent to the transport, until when the transport
     * acknowledges it has been transmitted.
     * This must account for the total time from when the data is sent, until the response is expected, included any
     * queueing delay in the transport.
     */
    private final static int TRANSACTION_TIMER_1 = 10000;
    private int timeout1 = TRANSACTION_TIMER_1;

    /**
     * The amount of time (in milliseconds) to wait for a response from the transport once the command has been
     * transmitted.
     */
    private final static int TRANSACTION_TIMER_2 = 8000;
    private int timeout2 = TRANSACTION_TIMER_2;

    /**
     * A {@link Long} that records the time this transaction was first added to the queue in milliseconds
     */
    private Long queueTime;

    /**
     * Transaction constructor
     *
     * @param transactionManager the {@link ZigBeeTransactionManager} through which the transaction is being sent.
     * @param command the {@link ZigBeeCommand}.
     * @param responseMatcher the {@link ZigBeeTransactionMatcher} to match the response and complete the
     *            transaction.
     *            May be null if no response is expected.
     */
    public ZigBeeTransaction(ZigBeeTransactionManager transactionManager, final ZigBeeCommand command,
            final ZigBeeTransactionMatcher responseMatcher) {
        this.transactionManager = transactionManager;
        this.command = command;
        this.responseMatcher = responseMatcher;
    }

    /**
     * Set the transaction ID - this is set just before the command is first transmitted
     *
     * @param transactionId the transaction ID
     */
    public void setTransactionId(int transactionId) {
        command.setTransactionId(transactionId);
    }

    /**
     * Gets the transaction ID from the command
     *
     * @return the transaction ID
     */
    public Integer getTransactionId() {
        return command.getTransactionId();
    }

    /**
     * Gets the time that the command was added to the queue, or null if the command is not in a queue
     *
     * @return the queueTime
     */
    protected Long getQueueTime() {
        if (queueTime == null) {
            return null;
        }
        return System.currentTimeMillis() - queueTime;
    }

    /**
     * Records the time the transaction is added to a queue using the current system time.
     */
    protected void setQueueTime() {
        this.queueTime = System.currentTimeMillis();
    }

    /**
     * Resets the transaction
     */
    protected void resetTransaction() {
        state = TransactionState.WAITING;
    }

    /**
     * Starts the transaction. Called by the {@link ZigBeeTransactionManager} when it sends the transaction.
     *
     * @return the {@link ZigBeeCommand} to be transmitted
     */
    protected ZigBeeCommand startTransaction() {
        state = TransactionState.DISPATCHED;
        startTimer(timeout1);
        sendCnt++;
        return command;
    }

    /**
     * Gets the {@link ZigBeeAddress} that this transaction is being sent to
     *
     * @return the {@link ZigBeeAddress} for the transaction
     */
    protected ZigBeeAddress getDestinationAddress() {
        return command.getDestinationAddress();
    }

    /**
     * Gets the number of times this transaction has been sent. The sendCnt is incremented in the
     * {@link #startTransaction()} method.
     *
     * @return the number of times the transaction has been sent
     */
    protected int getSendCnt() {
        return sendCnt;
    }

    /**
     * Gets the value of timer 1.
     * <p>
     * This timer is started when the transaction is first released. If the transport does not provide state updates to
     * confirm transmission of the frame, then this timer must account for the full period required to receive the
     * response. If the transport does provide state updates, then this timer only needs to account for the time to
     * receive this expected state response.
     *
     * @return the current timeout in milliseconds
     */
    public int getTimerPeriod1() {
        return timeout1;
    }

    /**
     * Sets the value of timer 1.
     * <p>
     * This timer is started when the transaction is first released. If the transport does not provide state updates to
     * confirm transmission of the frame, then this timer must account for the full period required to receive the
     * response. If the transport does provide state updates, then this timer only needs to account for the time to
     * receive this expected state response.
     *
     * @param timeout the timeout to set in milliseconds
     */
    public void setTimerPeriod1(int timeout) {
        this.timeout1 = timeout;
    }

    /**
     * Gets the value of timer 2.
     * <p>
     * This timer is started when the transport provides a state update to confirm the frame has been transmitted.
     *
     * @return the current timeout in milliseconds
     */
    public int getTimerPeriod2() {
        return timeout2;
    }

    /**
     * Sets the value of timer 2.
     * <p>
     * This timer is started when the transport provides a state update to confirm the frame has been transmitted.
     *
     * @param timeout the timeout to set in milliseconds
     */
    public void setTimerPeriod2(int timeout) {
        this.timeout2 = timeout;
    }

    /**
     * Sets the future for this transaction. The transaction will be completed when the transaction finishes or aborts.
     *
     * @param transactionFuture the {@link ZigBeeTransactionFuture} to be completed when the transaction completes.
     */
    protected void setFuture(ZigBeeTransactionFuture transactionFuture) {
        this.transactionFuture = transactionFuture;
    }

    /**
     * Gets the future for this transaction. The transaction will be completed when the transaction finishes or aborts.
     *
     * @return the {@link ZigBeeTransactionFuture} to be completed when the transaction completes.
     */
    protected ZigBeeTransactionFuture getFuture() {
        return transactionFuture;
    }

    /**
     * Gets the {@link IeeeAddress} of the device for which the transaction is targeted at
     *
     * @return {@link IeeeAddress} of the device for which the transaction is targeted at
     */
    public IeeeAddress getIeeeAddress() {
        return ieeeAddress;
    }

    /**
     * Sets the {@link IeeeAddress} of the device for which the transaction is targeted at
     *
     * @param ieeeAddress the {@link IeeeAddress} of the device for which the transaction is targeted at
     */
    public void setIeeeAddress(IeeeAddress ieeeAddress) {
        this.ieeeAddress = ieeeAddress;
    }

    /**
     * Cancels the transaction. The transaction will immediately be cancelled and the state will be set to
     * {@link TransactionState#CANCELLED}.
     * <p>
     * No further retry will be conducted and the transaction manager will not be notified (the assumption being that
     * the cancellation is made through the transaction manager).
     */
    protected void cancel() {
        if (state == TransactionState.CANCELLED) {
            return;
        }

        state = TransactionState.CANCELLED;
        stopTimer();

        logger.debug("Transaction cancelled: {}", this);
        if (transactionFuture != null) {
            synchronized (transactionFuture) {
                transactionFuture.cancel(false);
                transactionFuture = null;
            }
        }

        transactionManager.transactionComplete(this, state);
    }

    /**
     * Called by the transaction manager when a {@link ZigBeeCommand} is received. The transaction should check this
     * command to see if it completes the transaction.
     *
     * @param receivedCommand the incoming {@link ZigBeeCommand}
     */
    public void commandReceived(ZigBeeCommand receivedCommand) {
        if (responseMatcher == null) {
            return;
        }

        // Ensure that received command is not processed before command is sent
        // and hence transaction ID for the command set.
        synchronized (command) {
            if (Objects.equals(command.getTransactionId(), receivedCommand.getTransactionId())
                    && responseMatcher.isTransactionMatch(command, receivedCommand)) {
                // If the transaction state is TRANSMITTED, then we know that the transport layer
                // is providing state updates. We need to ensure in this case that we get the RX_ACK or RX_NAK
                // states so that we know that the transport layer has completed the APS transaction.
                // If the state is TRANSMITTED, then we have not yet received this, and we need to hold off
                // completing this transaction to avoid sending another transaction to the transport that it
                // Cannot currently handle.
                if (state == TransactionState.TRANSMITTED) {
                    state = TransactionState.RESPONDED;
                    completionCommand = receivedCommand;
                    logger.debug("Transaction response received - waiting TX_ACK: {}", this);
                } else {
                    completeTransaction(receivedCommand);
                }
            }
        }
    }

    private void stopTimer() {
        if (timeoutTask != null) {
            timeoutTask.cancel(false);
            timeoutTask = null;
        }
    }

    private void startTimer(int timeout) {
        stopTimer();

        // Schedule a task to timeout the transaction
        timeoutTask = transactionManager.scheduleTask(new Runnable() {
            @Override
            public void run() {
                if (transactionFuture.isCancelled() || state == TransactionState.RESPONDED) {
                    // Even though this transaction has timed out waiting for the transport,
                    // we did receive a response that completed the transaction at application level
                    // Additionally, if the future was cancelled, we assume this was done by the application
                    // so it's treated as complete.
                    completeTransaction(completionCommand);
                } else {
                    failTransaction();
                }
            }
        }, timeout);
    }

    private void completeTransaction(ZigBeeCommand receivedCommand) {
        if (isTransactionComplete()) {
            return;
        }

        state = TransactionState.COMPLETE;
        stopTimer();

        if (transactionFuture != null) {
            synchronized (transactionFuture) {
                transactionFuture.set(new CommandResult(ZigBeeStatus.SUCCESS, receivedCommand));
                transactionFuture.notify();
            }
        }

        transactionManager.transactionComplete(this, TransactionState.COMPLETE);
    }

    private void failTransaction() {
        if (isTransactionComplete()) {
            return;
        }

        state = TransactionState.FAILED;
        stopTimer();

        transactionManager.transactionComplete(this, TransactionState.FAILED);
    }

    private boolean isTransactionComplete() {
        return (state == TransactionState.COMPLETE || state == TransactionState.FAILED);
    }

    /**
     * Notifies of an updated state from the transport layer. This is used to see if the transaction state can be
     * progressed.
     *
     * @param progress the {@link ZigBeeTransportProgressState} of the transaction
     * @param transactionId the transaction ID
     */
    public void transactionStatusReceived(ZigBeeTransportProgressState progress, int transactionId) {
        synchronized (command) {
            if (command.getTransactionId() != transactionId) {
                return;
            }

            switch (progress) {
                case TX_NAK:
                    // The transport layer failed to send the command
                    failTransaction();
                    break;
                case TX_ACK:
                    // If we aren't waiting for a response, then we're done
                    if (responseMatcher == null || command.isAckRequest() == false) {
                        completeTransaction(null);
                        break;
                    }

                    // The command was transmitted ok
                    state = TransactionState.TRANSMITTED;

                    // The timer is reset here as we have confirmation the command is sent
                    startTimer(timeout2);
                    break;
                case RX_NAK:
                    // The transport layer failed to get an ack from the remote device
                    if (state == TransactionState.RESPONDED) {
                        // Even though the transport thinks this transaction was not ACKed at APS level,
                        // we did receive a response that completed the transaction at application level
                        completeTransaction(completionCommand);
                    } else {
                        failTransaction();
                    }
                    break;
                case RX_ACK:
                    // The remote device confirmed receipt of the command (ie APS ACK received)
                    if (responseMatcher == null || state == TransactionState.RESPONDED
                            || (command instanceof ZclCommand && ((ZclCommand) command).isDisableDefaultResponse())) {
                        // We've already received a response that completed the application level transaction,
                        // or we weren't waiting for a response - either way, we're done.
                        completeTransaction(completionCommand);
                    } else {
                        state = TransactionState.ACKED;
                    }
                    break;
                default:
                    logger.debug("Unknown transaction state update: TID {} to {}", String.format("%02X", transactionId),
                            progress);
                    break;
            }
        }
        logger.debug("Transaction state changed: nwk={}, TID={}, event={}, state={}",
                String.format("%04X", command.getDestinationAddress().getAddress()),
                String.format("%02X", transactionId), progress, state);
    }

    @Override
    public String toString() {
        String queuedTime = queueTime == null ? "-" : Long.toString(System.currentTimeMillis() - queueTime);
        return "ZigBeeTransaction [ieeeAddress=" + ieeeAddress + " queueTime=" + queuedTime + ", state=" + state
                + ", sendCnt=" + sendCnt + ", command=" + command + "]";
    }
}

package com.zsmartsystems.zigbee.transaction;

import java.util.concurrent.ScheduledFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.ZigBeeAddress;
import com.zsmartsystems.zigbee.greenpower.GpCommand;
import com.zsmartsystems.zigbee.greenpower.GpCommandResult;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportProgressState;

public class ZigBeeGpTransaction {
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
        FAILED
    }

    /**
     * The {@link ZigBeeTransactionManager} through which the transaction is being managed
     */
    private final ZigBeeGpTransactionManager transactionManager;

    /**
     * The {@link ZigBeeGpTransactionFuture} that will be fulfilled once the transaction completes
     */
    private ZigBeeGpTransactionFuture transactionFuture;

    /**
     * The {@link ZigBeeGpTransactionMatcher} used to match the response to the command
     */
    private ZigBeeGpTransactionMatcher responseMatcher;

    /**
     * The {@link Command} that we are sending
     */
    private GpCommand command;

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
    private GpCommand completionCommand;

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
     * @param command the {@link GpCommand}.
     * @param responseMatcher the {@link ZigBeeGpTransactionMatcher} to match the response and complete the transaction.
     *            May be null if no response is expected.
     */
    public ZigBeeGpTransaction(ZigBeeGpTransactionManager transactionManager, final GpCommand command,
            final ZigBeeGpTransactionMatcher responseMatcher) {
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
     * @return the {@link GpCommand} to be transmitted
     */
    protected GpCommand startTransaction() {
        state = TransactionState.DISPATCHED;
        startTimer(timeout1);
        sendCnt++;
        return command;
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
        return timeout1;
    }

    /**
     * Sets the value of timer 2.
     * <p>
     * This timer is started when the transport provides a state update to confirm the frame has been transmitted.
     *
     * @param timeout the timeout to set in milliseconds
     */
    public void setTimerPeriod2(int timeout) {
        this.timeout1 = timeout;
    }

    /**
     * Sets the future for this transaction. The transaction will be completed when the transaction finishes or aborts.
     *
     * @param transactionFuture the {@link ZigBeeGpTransactionFuture} to be completed when the transaction completes.
     */
    protected void setFuture(ZigBeeGpTransactionFuture transactionFuture) {
        this.transactionFuture = transactionFuture;
    }

    /**
     * Gets the future for this transaction. The transaction will be completed when the transaction finishes or aborts.
     *
     * @return the {@link ZigBeeGpTransactionFuture} to be completed when the transaction completes.
     */
    protected ZigBeeGpTransactionFuture getFuture() {
        return transactionFuture;
    }

    /**
     * Cancels the transaction. The transaction will immediately be cancelled and the state will be set to
     * {@link TransactionState#FAILED}.
     * No further retry will be conducted and the transaction manager will not be notified (the assumption being that
     * the cancellation is made through the transaction manager).
     */
    protected void cancel() {
        state = TransactionState.FAILED;

        if (timeoutTask != null) {
            timeoutTask.cancel(false);
        }
        logger.debug("Transaction terminated: {}", this);
        if (transactionFuture != null) {
            synchronized (transactionFuture) {
                transactionFuture.cancel(false);
                transactionFuture.notify();
            }
        }
    }

    /**
     * Called by the transaction manager when a {@link GpCommand} is received. The transaction should check this
     * command to see if it completes the transaction.
     *
     * @param receivedCommand the incoming {@link GpCommand}
     */
    public void commandReceived(GpCommand receivedCommand) {
        if (responseMatcher == null) {
            return;
        }

        // Ensure that received command is not processed before command is sent
        // and hence transaction ID for the command set.
        synchronized (command) {
            if (responseMatcher.isTransactionMatch(command, receivedCommand)) {
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
    
    private void startTimer(int timeout) {
        if (timeoutTask != null) {
            timeoutTask.cancel(false);
        }

        // Schedule a task to timeout the transaction
        timeoutTask = transactionManager.scheduleTask(new Runnable() {
            @Override
            public void run() {
                if (state == TransactionState.RESPONDED) {
                    // Even though this transaction has timed out waiting for the transport,
                    // we did receive a response that completed the transaction at application level
                    completeTransaction(completionCommand);
                } else {
                    cancelTransaction();
                }
            }
        }, timeout);
    }

    private void completeTransaction(GpCommand receivedCommand) {
        state = TransactionState.COMPLETE;
        if (timeoutTask != null) {
            timeoutTask.cancel(false);
        }
        if (transactionFuture != null) {
            synchronized (transactionFuture) {
                transactionFuture.set(new GpCommandResult(receivedCommand));
                transactionFuture.notify();
            }
        }

        transactionManager.transactionComplete(this, TransactionState.COMPLETE);
    }

    private void cancelTransaction() {
        state = TransactionState.FAILED;
        if (timeoutTask != null) {
            timeoutTask.cancel(false);
        }

        transactionManager.transactionComplete(this, TransactionState.FAILED);
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
                    startTimer(timeout2);
                    break;
                case RX_NAK:
                    // The transport layer failed to get an ack from the remote device
                    if (state == TransactionState.RESPONDED) {
                        // Even though the transport thinks this transaction was not ACKed at APS level,
                        // we did receive a response that completed the transaction at application level
                        completeTransaction(completionCommand);
                    } else {
                        cancelTransaction();
                    }
                    break;
                case RX_ACK:
                    // The remote device confirmed receipt of the command
                    if (responseMatcher == null || state == TransactionState.RESPONDED) {
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
        logger.debug("Transaction state updated: TID {} -> {} == {}", String.format("%02X", transactionId), progress,
                state);
    }

    @Override
    public String toString() {
        String queuedTime = queueTime == null ? "-" : Long.toString(System.currentTimeMillis() - queueTime);
        return "ZigBeeTransaction [queueTime=" + queuedTime + ", state=" + state + ", sendCnt=" + sendCnt + ", command="
                + command + "]";
    }

}

/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.transaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.ZigBeeAddress;
import com.zsmartsystems.zigbee.ZigBeeBroadcastDestination;
import com.zsmartsystems.zigbee.ZigBeeCommand;
import com.zsmartsystems.zigbee.ZigBeeEndpointAddress;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNode;
import com.zsmartsystems.zigbee.internal.NotificationService;
import com.zsmartsystems.zigbee.transaction.ZigBeeTransaction.TransactionState;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportProgressState;

/**
 * The centralised transaction manager is designed to solve a number of issues associated with the sending
 * of transactions to devices -:
 * <ul>
 * <li>ZigBee specifies a maximum rate at which broadcast transactions can be sent. This is primarily designed to limit
 * the
 * amount of memory required in a ZigBee router to buffer frames to sleepy child devices.
 * <li>Sending multiple transactions to a single device without waiting for the response to the first transactions may
 * result in lost transactions.
 * <li>Retry management.
 * <li>Account for transport layer feedback.
 * </ul>
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeTransactionManager {
    /**
     * The logger.
     */
    private final Logger logger = LoggerFactory.getLogger(ZigBeeTransactionManager.class);

    private final int OUTSTANDING_TRANSACTIONS = 10;

    private final int NODE_RETRIES = 2;
    private final int NODE_TRANSACTIONS = 2;
    private final int NODE_DELAY = 50;
    private final boolean NODE_DUPLICATES = true;

    private final int MCAST_RETRIES = 0;
    private final int MCAST_TRANSACTIONS = 3;
    private final int MCAST_DELAY = 1200;
    private final boolean MCAST_DUPLICATES = true;

    private final int BCAST_RETRIES = 0;
    private final int BCAST_TRANSACTIONS = 3;
    private final int BCAST_DELAY = 1200;
    private final boolean BDCST_DUPLICATES = true;

    /**
     * The {@link ZigBeeNetworkManager} to which this manager belongs
     */
    private final ZigBeeNetworkManager networkManager;

    /**
     * The set of outstanding transactions - used to notify transactions when responses are received.
     */
    private Set<ZigBeeTransaction> outstandingTransactions = new HashSet<>();

    /**
     * The maximum number of transactions the manager will allow at any time
     */
    private int maxOutstandingTransactions = OUTSTANDING_TRANSACTIONS;

    /**
     * Executor service to execute update threads for discovery or mesh updates etc.
     * We use a {@link Executors.newScheduledThreadPool} to provide a fixed number of threads as otherwise this could
     * result in a large number of simultaneous threads in large networks. The threads are only used to time out a
     * transaction which is a short running event so should not block other threads from running in any practical sense.
     */
    private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(6);

    /**
     * A Map containing the queue for each node. This provides quick access when adding commands to queue, or performing
     * any queue function where we know the node.
     */
    private final Map<IeeeAddress, ZigBeeTransactionQueue> nodeQueue = new ConcurrentHashMap<>();

    /**
     * {@link AtomicInteger} used to generate transaction IDs
     */
    private final static AtomicInteger transactionIdCounter = new AtomicInteger();

    /**
     * A List of queues with outstanding commands. This is used for random access when sending transactions.
     */
    private final List<ZigBeeTransactionQueue> outstandingQueues = new ArrayList<>();

    private final ZigBeeTransactionQueue broadcastQueue;
    private final ZigBeeTransactionQueue multicastQueue;

    private ZigBeeTransactionProfile defaultProfile;

    /**
     * Timer used to keep the queue running
     */
    private ScheduledFuture<?> timeoutTask;

    public ZigBeeTransactionManager(ZigBeeNetworkManager manager) {
        this.networkManager = manager;

        defaultProfile = new ZigBeeTransactionProfile(NODE_RETRIES, NODE_TRANSACTIONS, NODE_DELAY, NODE_DUPLICATES);

        broadcastQueue = new ZigBeeTransactionQueue("Broadcast");
        broadcastQueue.setProfile(
                new ZigBeeTransactionProfile(BCAST_RETRIES, BCAST_TRANSACTIONS, BCAST_DELAY, BDCST_DUPLICATES));

        multicastQueue = new ZigBeeTransactionQueue("Multicast");
        multicastQueue.setProfile(
                new ZigBeeTransactionProfile(MCAST_RETRIES, MCAST_TRANSACTIONS, MCAST_DELAY, MCAST_DUPLICATES));
    }

    /**
     * Shuts down the manager and releases all resources
     */
    public void shutdown() {
        logger.debug("Shutting down transaction manager");

        executorService.shutdown();

        if (timeoutTask != null) {
            timeoutTask.cancel(false);
        }

        broadcastQueue.shutdown();
        multicastQueue.shutdown();
        for (ZigBeeTransactionQueue queue : nodeQueue.values()) {
            queue.shutdown();
        }

        nodeQueue.clear();

        synchronized (outstandingTransactions) {
            // Notify the listeners
            for (final ZigBeeTransaction transaction : outstandingTransactions) {
                NotificationService.execute(new Runnable() {
                    @Override
                    public void run() {
                        transaction.cancel();
                    }
                });
            }
        }
    }

    /**
     * Gets the maximum number of transactions permitted at any time.
     *
     * @return the the maximum number of transactions permitted at any time
     */
    public int getMaxOutstandingTransactions() {
        return maxOutstandingTransactions;
    }

    /**
     * Sets the maximum number of transactions permitted at any time.
     *
     * @param maxOutstandingTransactions the maximum number of transactions permitted at any time
     */
    public void setMaxOutstandingTransactions(int maxOutstandingTransactions) {
        this.maxOutstandingTransactions = maxOutstandingTransactions;
    }

    /**
     * Gets the maximum number of transactions the broadcast queue will release at once.
     *
     * @return the maximum number of transactions the queue is allowed to have outstanding
     */
    public ZigBeeTransactionProfile getBroadcastProfile() {
        return broadcastQueue.getProfile();
    }

    /**
     * Sets the default {@link ZigBeeTransactionProfile} for the node queues
     *
     * @param profile the {@link ZigBeeTransactionProfile}
     */
    public void setDefaultProfile(ZigBeeTransactionProfile profile) {
        defaultProfile = profile;
    }

    /**
     * Gets the default {@link ZigBeeTransactionProfile} for the node queues
     *
     * return the {@link ZigBeeTransactionProfile}
     */
    public ZigBeeTransactionProfile setDefaultProfile() {
        return defaultProfile;
    }

    /**
     * Sets the {@link ZigBeeTransactionProfile} for the multicast queue
     *
     * @param profile the {@link ZigBeeTransactionProfile}
     */
    public void setMulticastProfile(ZigBeeTransactionProfile profile) {
        broadcastQueue.setProfile(profile);
    }

    /**
     * Sets the {@link ZigBeeTransactionProfile} for the broadcast queue
     *
     * @param profile the {@link ZigBeeTransactionProfile}
     */
    public void setBroadcastProfile(ZigBeeTransactionProfile profile) {
        broadcastQueue.setProfile(profile);
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
        if (command.getTransactionId() == null) {
            command.setTransactionId(transactionIdCounter.getAndIncrement() & 0xff);
        }

        ZigBeeTransaction transaction = new ZigBeeTransaction(this, command, responseMatcher);

        synchronized (this) {
            ZigBeeTransactionQueue queue = getTransactionQueue(transaction);
            if (queue == null) {
                logger.debug("Error getting queue for {}", transaction);
                return null;
            }

            return queueTransaction(queue, transaction);
        }
    }

    /**
     * Adds a {@link ZigBeeTransaction} to the respective {@link ZigBeeTransactionQueue}
     *
     * @param transaction the {@link ZigBeeTransaction} to add to the queue. Not null.
     * @return the future {@link CommandResult}
     */
    private ZigBeeTransactionFuture queueTransaction(ZigBeeTransactionQueue queue, ZigBeeTransaction transaction) {
        queue.addToQueue(transaction);
        if (!queue.isEmpty() && !outstandingQueues.contains(queue)) {
            outstandingQueues.add(queue);
        }

        // TODO: ???
        sendNextTransaction();

        return transaction.getFuture();
    }

    /**
     * Gets the transaction queue for a specific transaction.
     *
     * @param transaction the {@link ZigBeeTransaction}
     * @return the {@link ZigBeeTransactionQueue} or null if no queue could be derived
     */
    private ZigBeeTransactionQueue getTransactionQueue(ZigBeeTransaction transaction) {
        ZigBeeAddress address = transaction.getDestinationAddress();
        if (address instanceof ZigBeeEndpointAddress && !ZigBeeBroadcastDestination.isBroadcast(address.getAddress())) {
            // Get the IEEE address
            ZigBeeNode node = networkManager.getNode(address.getAddress());
            if (node == null) {
                logger.debug("Attempt to send command with unknown destination: {}", transaction);
                return null;
            }
            // Add the transaction to the device queue - if it doesn't currently exist, create it
            ZigBeeTransactionQueue queue = nodeQueue.get(node.getIeeeAddress());
            if (queue == null) {
                logger.debug("{}: Creating new Transaction Queue", node.getIeeeAddress());
                queue = new ZigBeeTransactionQueue(node.getIeeeAddress().toString());

                queue.setProfile(defaultProfile);

                nodeQueue.put(node.getIeeeAddress(), queue);
            }
            return queue;
        } else if (address instanceof ZigBeeEndpointAddress
                && ZigBeeBroadcastDestination.isBroadcast(address.getAddress())) {
            return broadcastQueue;
        } else {
            return multicastQueue;
        }
    }

    /**
     * Sends the transaction to the transport layer.
     *
     * @param transaction the {@link ZigBeeTransaction} to send
     */
    private void send(ZigBeeTransaction transaction) {
        logger.debug("{}: Sending {}", transaction.getDestinationAddress(), transaction);
        addTransactionListener(transaction);
        networkManager.sendCommand(transaction.startTransaction());
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
     * @param state the updated {@link ZigBeeTransportProgressState} for the transaction
     */
    public void receiveCommandState(int transactionId, ZigBeeTransportProgressState state) {
        notifyTransactionProgress(transactionId, state);
    }

    /**
     * Adds a transaction to the list of outstanding transactions. Transactions will receive notifications when a
     * command is received, or when the status is updated
     *
     * @param transaction the {@link ZigBeeTransaction} that will receive the notifications
     */
    private void addTransactionListener(ZigBeeTransaction transaction) {
        logger.debug("addTransactionListener: {}", transaction);
        synchronized (outstandingTransactions) {
            outstandingTransactions.add(transaction);
        }
        logger.debug("transactionListenerAdded: {} outstanding", outstandingTransactions.size());
    }

    /**
     * Removes a transaction from the list of outstanding transactions.
     * <p>
     * This is called by the {@link ZigBeeTransaction} when it terminates
     *
     * @param transaction the {@link ZigBeeTransaction} to remove
     */
    private void removeTransactionListener(ZigBeeTransaction transaction) {
        logger.debug("removeTransactionListener: {}", transaction);
        synchronized (outstandingTransactions) {
            outstandingTransactions.remove(transaction);
        }
        logger.debug("transactionListenerRemoved: {} outstanding", outstandingTransactions.size());
    }

    /**
     * Schedules a task with a timeout. Used by {@link ZigBeeTransaction}s to time out failed transactions
     *
     * @param runnableTask the {@link Runnable} to call when the timer expires
     * @param delay the delay in milliseconds
     * @return the {@link ScheduledFuture} for this task
     */
    protected ScheduledFuture<?> scheduleTask(Runnable runnableTask, long delay) {
        return executorService.schedule(runnableTask, delay, TimeUnit.MILLISECONDS);
    }

    /**
     * Callback from {@link ZigBeeTransaction} when a transaction has completed or failed.
     *
     * @param transaction the {@link ZigBeeTransaction} to complete. Not null.
     * @param state the {@link TransactionState} of the transaction on completion
     */
    protected void transactionComplete(ZigBeeTransaction transaction, TransactionState state) {
        logger.debug("transactionComplete: {}  {}", transaction, state);
        removeTransactionListener(transaction);

        synchronized (this) {
            ZigBeeTransactionQueue queue = getTransactionQueue(transaction);
            queue.transactionComplete(transaction, state);
        }
    }

    /**
     * Notify transactions of the received command
     *
     * @param command the {@link ZigBeeCommand} to send to the transactions
     */
    private void notifyTransactionCommand(final ZigBeeCommand command) {
        logger.debug("notifyTransactionCommand: {} ", command);
        synchronized (outstandingTransactions) {
            // Notify the listeners
            for (final ZigBeeTransaction transaction : outstandingTransactions) {
                logger.debug("notifyTransactionCommand: {} {}", command, transaction);
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
     * Notify transactions of the current {@link ZigBeeTransportProgressState} from the transport layer
     *
     * @param transactionId the ID of the transaction whose state has been updated
     * @param state the current {@link ZigBeeTransportProgressState} for the transaction
     */
    private void notifyTransactionProgress(final int transactionId, ZigBeeTransportProgressState state) {
        logger.debug("notifyTransactionProgress: TID {} -> {} == {}", transactionId, state,
                outstandingTransactions.size());
        synchronized (outstandingTransactions) {
            // Notify the listeners
            for (final ZigBeeTransaction transaction : outstandingTransactions) {
                NotificationService.execute(new Runnable() {
                    @Override
                    public void run() {
                        transaction.transactionStatusReceived(state, transactionId);
                    }
                });
            }
        }
    }

    /**
     * Gets the {@link ZigBeeTransactionQueue} for the specified address
     *
     * @param address the {@link IeeeAddress} of the node
     * @return the {@link ZigBeeTransactionQueue} for the specified address or null if there is no current queue for the
     *         requested address
     */
    public ZigBeeTransactionQueue getQueue(IeeeAddress address) {
        return nodeQueue.get(address);
    }

    /**
     * Removes resources associated with a specific node
     *
     * @param address the {@link IeeeAddress} of the node
     */
    public void removeNode(IeeeAddress address) {
        ZigBeeTransactionQueue queue = nodeQueue.remove(address);
        if (queue == null) {
            return;
        }

        queue.shutdown();
    }

    /**
     * Polls the queues to send outstanding transactions. This will send as many transactions as necessary, or available
     * within the constraints that have been set (e.g. the maxOutstandingTransactions).
     * <p>
     * The order of transmission from each queue is randomised to ensure a fair ordering of transactions to each device.
     * If a queue returns null, then it does not have transactions to send at that time and we let the timer take care
     * of
     * rescheduling the transmission.
     */
    private void sendNextTransaction() {
        synchronized (this) {
            if (timeoutTask != null) {
                timeoutTask.cancel(false);
            }

            ZigBeeTransaction transaction;

            // Randomly loop through all queues, taking a transaction from each one in turn
            // If we have more transactions outstanding than we're allowed, then exit
            // If we get through an iteration of all queues without sending anything, then exit
            //
            // Points to note -:
            // * Queues may have more than one transaction to send
            // * Queues may have transactions to send, but be unable to send them at this time

            List<ZigBeeTransactionQueue> emptyQueues = new ArrayList<>();
            boolean sendDone;
            do {
                // Exit unless we send at least one transaction
                sendDone = true;

                // Randomise the list
                Collections.shuffle(outstandingQueues);

                // Clear the list of queues that have been emptied
                emptyQueues.clear();

                for (ZigBeeTransactionQueue queue : outstandingQueues) {
                    // Check if we've reached the maximum number of commands we can send
                    if (outstandingTransactions.size() >= maxOutstandingTransactions) {
                        sendDone = true;
                        break;
                    }

                    // Queue may return null if it has transactions queued, but it can't release any at this time
                    transaction = queue.getTransaction();
                    if (transaction != null) {
                        // Send the transaction.
                        send(transaction);
                        sendDone = false;
                    }

                    // If the queue has no more transactions,
                    // remove it from the list of queues with outstanding transactions.
                    if (queue.isEmpty()) {
                        emptyQueues.add(queue);
                    }
                }

                outstandingQueues.removeAll(emptyQueues);
            } while (!sendDone);

            // Loop through all outstanding queues and find the next release time
            long timeout = Long.MAX_VALUE;
            for (ZigBeeTransactionQueue queue : outstandingQueues) {
                long nextTime = queue.getNextReleaseTime();
                if (nextTime < timeout) {
                    timeout = nextTime;
                }
            }

            if (timeout > 0) {
                startRequeueTimer(timeout);
            }
        }
    }

    /**
     * Starts the timer to send the next transaction
     *
     * @param timeout the number of milliseconds to wait
     */
    private void startRequeueTimer(final long timeout) {
        // Schedule a task to kick off the next transaction
        timeoutTask = scheduleTask(new Runnable() {
            @Override
            public void run() {
                sendNextTransaction();
            }
        }, timeout);
    }
}

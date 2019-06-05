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
import com.zsmartsystems.zigbee.ZigBeeEndpointAddress;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNetworkNodeListener;
import com.zsmartsystems.zigbee.ZigBeeNode;
import com.zsmartsystems.zigbee.greenpower.GpCommand;
import com.zsmartsystems.zigbee.greenpower.GpCommandResult;
import com.zsmartsystems.zigbee.internal.NotificationService;
import com.zsmartsystems.zigbee.transaction.ZigBeeGpTransaction.TransactionState;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportProgressState;
import com.zsmartsystems.zigbee.zdo.field.NodeDescriptor.MacCapabilitiesType;

/**
 * The centralised transaction manager is designed to solve a number of issues associated with the sending
 * of transactions to devices, and ensure a smooth flow of data under most circumstances. It sits between the
 * applications that are sending commands with no knowledge of other applications, or knowledge of the network state,
 * and the transport layer.
 * <p>
 * The following issues are covered -:
 * <ul>
 * <li>ZigBee specifies a maximum rate at which broadcast transactions can be sent. This is primarily designed to limit
 * the amount of memory required in a ZigBee router to buffer frames to sleepy child devices.
 * <li>Sending multiple transactions to a single device without waiting for the response to the first transactions may
 * result in lost transactions.
 * <li>Retry management. Each queue can be configured to allow retries. The transaction futures are not completed until
 * the transaction completes successfully, or the maximum number of retries are attempted.
 * <li>Account for transport layer feedback.
 * <li>Manage sleepy devices. The queue manager will only send a certain number of transactions to sleepy devices at
 * once. This ensures that there is always bandwidth reserved for mains powered devices, thus ensuring that the queue
 * can not be filled with commands to sleepy devices, which would adversely affect the overall performance of the
 * system.
 * </ul>
 * <p>
 * Each time a transaction is sent to the transaction manager, or a transaction completes, the transaction manager will
 * attempt to send the next transaction in order to keep the transport layer full, while also fulfilling the various
 * constraints in the queues.
 * <p>
 * When sending, queues are polled in random order to ensure that all queues get a fair chance at sending data.
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeGpTransactionManager implements ZigBeeNetworkNodeListener {
    /**
     * The logger.
     */
    private final Logger logger = LoggerFactory.getLogger(ZigBeeTransactionManager.class);

    /**
     * The maximum number of outstanding transactions from all queues
     */
    private final int MAX_OUTSTANDING_TRANSACTIONS = 9;

    /**
     * The maximum number of outstanding transactions from sleepy queues
     */
    private final int MAX_SLEEPY_TRANSACTIONS = 5;

    private final int NODE_RETRIES = 2;
    private final int NODE_TRANSACTIONS = 2;
    private final int NODE_DELAY = 50;

    private final int SLEEPY_RETRIES = 2;
    private final int SLEEPY_TRANSACTIONS = 1;
    private final int SLEEPY_DELAY = 50;

    private final int MCAST_RETRIES = 0;
    private final int MCAST_TRANSACTIONS = 3;
    private final int MCAST_DELAY = 1200;

    private final int BCAST_RETRIES = 0;
    private final int BCAST_TRANSACTIONS = 3;
    private final int BCAST_DELAY = 1200;

    /**
     * The {@link ZigBeeNetworkManager} to which this manager belongs
     */
    private final ZigBeeNetworkManager networkManager;

    /**
     * The set of outstanding transactions - used to notify transactions when responses are received.
     */
    private Set<ZigBeeGpTransaction> outstandingTransactions = new HashSet<>();

    /**
     * The maximum number of transactions the manager will allow at any time
     */
    private int maxOutstandingTransactions = MAX_OUTSTANDING_TRANSACTIONS;

    /**
     * The maximum number of outstanding transactions from sleepy queues
     */
    private int maxSleepyTransactions = MAX_SLEEPY_TRANSACTIONS;

    /**
     * A counter holding the number of sleepy transactions
     */
    private int sleepyTransactions = 0;

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
    private final Map<IeeeAddress, ZigBeeGpTransactionQueue> nodeQueue = new ConcurrentHashMap<>();

    /**
     * {@link AtomicInteger} used to generate transaction IDs
     */
    private final AtomicInteger transactionIdCounter = new AtomicInteger();

    /**
     * A List of queues with outstanding commands. This is used for random access when sending transactions.
     */
    private final List<ZigBeeGpTransactionQueue> outstandingQueues = new ArrayList<>();

    private final ZigBeeGpTransactionQueue defaultQueue;
    private final ZigBeeGpTransactionQueue broadcastQueue;
    private final ZigBeeGpTransactionQueue multicastQueue;

    private ZigBeeTransactionProfile defaultProfile;
    private ZigBeeTransactionProfile defaultSleepyProfile;

    /**
     * Timer used to keep the queue running
     */
    private ScheduledFuture<?> timeoutTask;

    public ZigBeeGpTransactionManager(ZigBeeNetworkManager manager) {
        this.networkManager = manager;

        defaultProfile = new ZigBeeTransactionProfile(NODE_RETRIES, NODE_TRANSACTIONS, NODE_DELAY);
        defaultSleepyProfile = new ZigBeeTransactionProfile(SLEEPY_RETRIES, SLEEPY_TRANSACTIONS, SLEEPY_DELAY);

        defaultQueue = new ZigBeeGpTransactionQueue("Default");
        defaultQueue.setProfile(defaultProfile);
        defaultQueue.setSleepy(false);

        broadcastQueue = new ZigBeeGpTransactionQueue("Broadcast");
        broadcastQueue.setProfile(new ZigBeeTransactionProfile(BCAST_RETRIES, BCAST_TRANSACTIONS, BCAST_DELAY));

        multicastQueue = new ZigBeeGpTransactionQueue("Multicast");
        multicastQueue.setProfile(new ZigBeeTransactionProfile(MCAST_RETRIES, MCAST_TRANSACTIONS, MCAST_DELAY));

        networkManager.addNetworkNodeListener(this);
    }

    /**
     * Shuts down the manager and releases all resources
     */
    public void shutdown() {
        logger.debug("Shutting down transaction manager");

        networkManager.removeNetworkNodeListener(this);

        executorService.shutdown();

        if (timeoutTask != null) {
            timeoutTask.cancel(false);
        }

        broadcastQueue.shutdown();
        multicastQueue.shutdown();
        for (ZigBeeGpTransactionQueue queue : nodeQueue.values()) {
            queue.shutdown();
        }

        nodeQueue.clear();

        synchronized (outstandingTransactions) {
            // Notify the listeners
            for (final ZigBeeGpTransaction transaction : outstandingTransactions) {
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
     * Gets the maximum number of transactions permitted to be outstanding at any time.
     *
     * @return the the maximum number of transactions permitted to be outstanding at any time
     */
    public int getMaxOutstandingTransactions() {
        return maxOutstandingTransactions;
    }

    /**
     * Sets the maximum number of transactions permitted to be outstanding at any time.
     *
     * @param maxOutstandingTransactions the maximum number of transactions permitted to be outstanding at any time
     */
    public void setMaxOutstandingTransactions(int maxOutstandingTransactions) {
        this.maxOutstandingTransactions = maxOutstandingTransactions;
    }

    /**
     * Gets the maximum number of sleepy transactions permitted to be outstanding at any time.
     *
     * @return the maximum number of sleepy transactions permitted to be outstanding at any time
     */
    public int getMaxSleepyTransactions() {
        return maxSleepyTransactions;
    }

    /**
     * Sets the maximum number of sleepy transactions permitted to be sent at any time.
     *
     * @param maxSleepyTransactions the maximum number of sleepy transactions permitted to be outstanding at any time
     */
    public void setMaxSleepyTransactions(int maxSleepyTransactions) {
        this.maxSleepyTransactions = maxSleepyTransactions;
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
    public ZigBeeTransactionProfile getDefaultProfile() {
        return defaultProfile;
    }

    /**
     * Sets the {@link ZigBeeTransactionProfile} for the sleepy node queues
     *
     * @param profile the {@link ZigBeeTransactionProfile}
     */
    public void setSleepyProfile(ZigBeeTransactionProfile profile) {
        defaultSleepyProfile = profile;
    }

    /**
     * Gets the {@link ZigBeeTransactionProfile} for the sleepy node queues
     *
     * return the {@link ZigBeeTransactionProfile}
     */
    public ZigBeeTransactionProfile getSleepyProfile() {
        return defaultSleepyProfile;
    }

    /**
     * Sets the {@link ZigBeeTransactionProfile} for the multicast queue
     *
     * @param profile the {@link ZigBeeTransactionProfile}
     */
    public void setMulticastProfile(ZigBeeTransactionProfile profile) {
        multicastQueue.setProfile(profile);
    }

    /**
     * Gets the {@link ZigBeeTransactionProfile} for the multicast queue
     *
     * return the {@link ZigBeeTransactionProfile}
     */
    public ZigBeeTransactionProfile getMulticastProfile() {
        return multicastQueue.getProfile();
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
     * Gets the {@link ZigBeeTransactionProfile} for the broadcast queue
     *
     * return the {@link ZigBeeTransactionProfile}
     */
    public ZigBeeTransactionProfile getBroadcastProfile() {
        return broadcastQueue.getProfile();
    }

    /**
     * Sends a command without waiting for a response
     *
     * @param command the {@link GpCommand} to send
     */
    public void sendTransaction(GpCommand command) {
        sendTransaction(command, null);
    }

    /**
     * Sends a command, and uses the {@link ZigBeeGpTransactionMatcher} to match the response which will complete the
     * transaction.
     *
     * @param command the {@link GpCommand} to send
     * @param responseMatcher the {@link ZigBeeGpTransactionMatcher} to match the response which will complete the
     *            transaction.
     * @return the future {@link CommandResult}
     */
    public Future<GpCommandResult> sendTransaction(GpCommand command, ZigBeeGpTransactionMatcher responseMatcher) {
        ZigBeeGpTransaction transaction = new ZigBeeGpTransaction(this, command, responseMatcher);

        synchronized (this) {
            ZigBeeGpTransactionQueue queue = getTransactionQueue(transaction);
            if (queue == null) {
                logger.debug("Error getting queue for {}", transaction);
                return null;
            }

            return queueTransaction(queue, transaction);
        }
    }

    /**
     * Adds a {@link ZigBeeGpTransaction} to the respective {@link ZigBeeGpTransactionQueue}
     *
     * @param transaction the {@link ZigBeeGpTransaction} to add to the queue. Not null.
     * @return the future {@link CommandResult}
     */
    private ZigBeeGpTransactionFuture queueTransaction(ZigBeeGpTransactionQueue queue, ZigBeeGpTransaction transaction) {
        queue.addToQueue(transaction);
        if (!queue.isEmpty() && !outstandingQueues.contains(queue)) {
            outstandingQueues.add(queue);
        }

        sendNextTransaction();

        return transaction.getFuture();
    }

    /**
     * Gets the transaction queue for a specific transaction. If there is no existing queue relevant for the
     * transaction, one will be created.
     * <p>
     * This method will only return null if the node address is unknown, and therefore no queue can be created.
     *
     * @param transaction the {@link ZigBeeGpTransaction}
     * @return the {@link ZigBeeGpTransactionQueue} or null if no queue could be derived
     */
    private ZigBeeGpTransactionQueue getTransactionQueue(ZigBeeGpTransaction transaction) {
//        ZigBeeAddress address = transaction.getDestinationAddress();
//        if (address instanceof ZigBeeEndpointAddress && !ZigBeeBroadcastDestination.isBroadcast(address.getAddress())) {
//            // Get the IEEE address
//            ZigBeeNode node = networkManager.getNode(address.getAddress());
//            if (node == null) {
//                logger.debug("Attempt to send command with unknown destination: {}", transaction);
//                return defaultQueue;
//            }
//            // Add the transaction to the device queue - if it doesn't currently exist, create it
//            ZigBeeGpTransactionQueue queue = nodeQueue.get(node.getIeeeAddress());
//            if (queue == null) {
//                logger.debug("{}: Creating new Transaction Queue", node.getIeeeAddress());
//                queue = new ZigBeeGpTransactionQueue(node.getIeeeAddress().toString());
//                setQueueType(node, queue);
//
//                nodeQueue.put(node.getIeeeAddress(), queue);
//            }
//            return queue;
//        } else if (address instanceof ZigBeeEndpointAddress
//                && ZigBeeBroadcastDestination.isBroadcast(address.getAddress())) {
//            return broadcastQueue;
//        } else {
//            return multicastQueue;
//        }
    	//No need to use the address to decide which type of queue to use as all GP frames are broadcasted.
    	return multicastQueue;
    }

    /**
     * Sets the queue type to sleepy or non-sleepy. This will update the profile, and the sleepy flag in the quque
     *
     * @param node the {@link ZigBeeNode} of the queue to update
     * @param queue the {@link ZigBeeGpTransactionQueue} to update
     * @return true if the queue state was changed
     */
    private boolean setQueueType(ZigBeeNode node, ZigBeeGpTransactionQueue queue) {
        boolean sleepy;
        if (!node.getNodeDescriptor().getMacCapabilities().contains(MacCapabilitiesType.RECEIVER_ON_WHEN_IDLE)) {
            queue.setProfile(defaultSleepyProfile);
            sleepy = true;
        } else {
            queue.setProfile(defaultProfile);
            sleepy = false;
        }
        return queue.setSleepy(sleepy) != sleepy;
    }

    /**
     * Sends the transaction to the transport layer.
     *
     * @param transaction the {@link ZigBeeGpTransaction} to send
     */
    private void send(ZigBeeGpTransaction transaction) {
        if (transaction.getTransactionId() == null) {
            transaction.setTransactionId(transactionIdCounter.getAndIncrement() & 0xff);
        }
        logger.debug("{}: Sending {}", transaction);
        addTransactionListener(transaction);
        networkManager.sendGpCommand(transaction.startTransaction());
    }

    /**
     * Processes a received frame within the transaction manager, and returns the frame that is to fed up the stack. The
     * transaction manager may return null from this command if it should not be processed up the stack.
     *
     * @param command the received {@link GpCommand}
     * @return the {@link GpCommand} to be used within the library or null if the frame is not to be fed into the
     *         rest of the system
     */
    public GpCommand receive(final GpCommand command) {
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
     * @param transaction the {@link ZigBeeGpTransaction} that will receive the notifications
     */
    private void addTransactionListener(ZigBeeGpTransaction transaction) {
        synchronized (outstandingTransactions) {
            outstandingTransactions.add(transaction);
        }
        logger.debug("transactionListenerAdded: {} outstanding", outstandingTransactions.size());
    }

    /**
     * Removes a transaction from the list of outstanding transactions.
     * <p>
     * This is called by the {@link ZigBeeGpTransaction} when it terminates
     *
     * @param transaction the {@link ZigBeeGpTransaction} to remove
     */
    private void removeTransactionListener(ZigBeeGpTransaction transaction) {
        synchronized (outstandingTransactions) {
            outstandingTransactions.remove(transaction);
        }
        logger.debug("transactionListenerRemoved: {} outstanding", outstandingTransactions.size());
    }

    /**
     * Schedules a task with a timeout. Used by {@link ZigBeeGpTransaction}s to time out failed transactions
     *
     * @param runnableTask the {@link Runnable} to call when the timer expires
     * @param delay the delay in milliseconds
     * @return the {@link ScheduledFuture} for this task
     */
    protected ScheduledFuture<?> scheduleTask(Runnable runnableTask, long delay) {
        return executorService.schedule(runnableTask, delay, TimeUnit.MILLISECONDS);
    }

    /**
     * Callback from {@link ZigBeeGpTransaction} when a transaction has completed or failed.
     *
     * @param transaction the {@link ZigBeeGpTransaction} to complete. Not null.
     * @param state the {@link TransactionState} of the transaction on completion
     */
    protected void transactionComplete(ZigBeeGpTransaction transaction, TransactionState state) {
        logger.debug("Transaction complete: {}", transaction);
        removeTransactionListener(transaction);

        synchronized (this) {
            ZigBeeGpTransactionQueue queue = getTransactionQueue(transaction);
            if (queue == null) {
                logger.debug("Transaction complete: No queue found {}", transaction);
            } else {
                queue.transactionComplete(transaction, state);

                if (queue.isSleepy()) {
                    sleepyTransactions--;
                }
            }
        }

        sendNextTransaction();
    }

    /**
     * Notify transactions of the received command
     *
     * @param command the {@link GpCommand} to send to the transactions
     */
    private void notifyTransactionCommand(final GpCommand command) {
        logger.debug("notifyTransactionCommand: {} ", command);
        synchronized (outstandingTransactions) {
            // Notify the listeners
            for (final ZigBeeGpTransaction transaction : outstandingTransactions) {
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
        logger.debug("notifyTransactionProgress: TID={}, state={}, outstanding={}",
                String.format("%02X", transactionId), state, outstandingTransactions.size());
        synchronized (outstandingTransactions) {
            // Notify the listeners
            for (final ZigBeeGpTransaction transaction : outstandingTransactions) {
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
     * Gets the {@link ZigBeeGpTransactionQueue} for the specified address
     *
     * @param address the {@link IeeeAddress} of the node
     * @return the {@link ZigBeeGpTransactionQueue} for the specified address or null if there is no current queue for the
     *         requested address
     */
    public ZigBeeGpTransactionQueue getQueue(IeeeAddress address) {
        return nodeQueue.get(address);
    }

    /**
     * Removes resources associated with a specific node
     *
     * @param address the {@link IeeeAddress} of the node
     */
    public void removeNode(IeeeAddress address) {
        ZigBeeGpTransactionQueue queue = nodeQueue.get(address);
        if (queue == null) {
            return;
        }
        queue.shutdown();

        logger.debug("{}: Removing queue from transaction manager", address);

        // Remove any outstanding transactions from this queue that have already been sent
        synchronized (outstandingTransactions) {
            for (ZigBeeGpTransaction transaction : outstandingTransactions) {
                if (getTransactionQueue(transaction) == queue) {
                    transaction.cancel();
                }
            }
        }
        nodeQueue.remove(address);
        outstandingQueues.remove(queue);
    }

    /**
     * Polls the queues to send outstanding transactions. This will send as many transactions as necessary, or available
     * within the constraints that have been set (e.g. the maxOutstandingTransactions).
     * <p>
     * The order of transmission from each queue is randomised to ensure a fair ordering of transactions to each device.
     * If a queue returns null, then it does not have transactions to send at that time and we let the timer take care
     * of rescheduling the transmission.
     */
    private void sendNextTransaction() {
        synchronized (this) {
            if (timeoutTask != null) {
                timeoutTask.cancel(false);
            }

            ZigBeeGpTransaction transaction;

            // Randomly loop through all queues, taking a transaction from each one in turn
            // If we have more transactions outstanding than we're allowed, then exit
            // If we get through an iteration of all queues without sending anything, then exit
            //
            // Points to note -:
            // * Queues may have more than one transaction to send
            // * Queues may have transactions to send, but be unable to send them at this time

            List<ZigBeeGpTransactionQueue> emptyQueues = new ArrayList<>();
            boolean sendDone;
            do {
                // Exit unless we send at least one transaction
                sendDone = true;

                // Randomise the lists
                Collections.shuffle(outstandingQueues);

                // Clear the list of queues that have been emptied
                emptyQueues.clear();

                for (ZigBeeGpTransactionQueue queue : outstandingQueues) {
                    // Check if we've reached the maximum number of commands we can send
                    if (outstandingTransactions.size() >= maxOutstandingTransactions) {
                        sendDone = true;
                        break;
                    }

                    // If this is a sleepy queue, and we've exceeded the sleepy transmissions, then ignore the queue
                    if (queue.isSleepy() && sleepyTransactions >= maxSleepyTransactions) {
                        continue;
                    }

                    // Queue may return null if it has transactions queued, but it can't release any at this time
                    transaction = queue.getTransaction();
                    if (transaction != null) {
                        if (queue.isSleepy()) {
                            sleepyTransactions++;
                        }

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
            for (ZigBeeGpTransactionQueue queue : outstandingQueues) {
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

    @Override
    public void nodeAdded(ZigBeeNode node) {
        nodeUpdated(node);
    }

    @Override
    public void nodeUpdated(ZigBeeNode node) {
        // Make sure that the queue is set to the correct type for this node
        // This is handled here as this may change after the device is initially paired
        // since the {@link NodeDescriptor} isn't initially known
        ZigBeeGpTransactionQueue queue = nodeQueue.get(node.getIeeeAddress());
        if (queue == null) {
            return;
        }

        if (setQueueType(node, queue)) {
            synchronized (outstandingTransactions) {
                int sleepyCnt = 0;
                // The queue type changed - resync the sleepyTransactions counter
                for (ZigBeeGpTransaction transaction : outstandingTransactions) {
                    ZigBeeGpTransactionQueue transactionQueue = getTransactionQueue(transaction);
                    if (transactionQueue != null && transactionQueue.isSleepy()) {
                        sleepyCnt++;
                        continue;
                    }
                }
                logger.debug("Sleepy transaction count resynchronised: was {}, now {}", sleepyCnt, sleepyTransactions);
                sleepyTransactions = sleepyCnt;
            }
        }
    }

    @Override
    public void nodeRemoved(ZigBeeNode node) {
        // Node is gone, remove the queue
        removeNode(node.getIeeeAddress());
    }
}

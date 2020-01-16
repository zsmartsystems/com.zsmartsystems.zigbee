/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Extension of the {@link Executors} class to create threads with custom names. This allows better profiling of the
 * system as the source of all threads can be determined.
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeExecutors {

    private static Logger logger = LoggerFactory.getLogger(ZigBeeExecutors.class);

    /**
     * Creates a thread pool that creates new threads as needed, but will reuse previously constructed threads when they
     * are available, and uses the provided ThreadFactory to create new threads when needed.
     *
     * @param name the thread pool name
     * @return the newly created thread pool
     */
    public static ExecutorService newCachedThreadPool(String name) {
        return Executors.newCachedThreadPool(new ThreadFactoryWithNamePrefix(name));
    }

    /**
     * Creates a thread pool that can schedule commands to run after a given delay, or to execute periodically.
     *
     * @param corePoolSize the number of threads to keep in the pool, even if they are idle
     * @param name the thread pool name
     * @return a newly created scheduled thread pool
     */
    public static ScheduledExecutorService newScheduledThreadPool(int corePoolSize, String name) {
        return Executors.newScheduledThreadPool(corePoolSize, new ThreadFactoryWithNamePrefix(name));
    }

    /**
     * Creates a single-threaded executor that can schedule commands to run after a given delay, or to execute
     * periodically. (Note however that if this single thread terminates due to a failure during execution prior to
     * shutdown, a new one will take its place if needed to execute subsequent tasks.) Tasks are guaranteed to execute
     * sequentially, and no more than one task will be active at any given time. Unlike the otherwise equivalent
     * newScheduledThreadPool(1, threadFactory) the returned executor is guaranteed not to be reconfigurable to use
     * additional threads.
     *
     * @param name the thread pool name
     * @return a newly created scheduled executor
     */
    public static ScheduledExecutorService newSingleThreadScheduledExecutor(String name) {
        return Executors.newSingleThreadScheduledExecutor(new ThreadFactoryWithNamePrefix(name));
    }

    /**
     * Creates a thread pool that reuses a fixed number of threads operating off a shared unbounded queue, using the
     * provided ThreadFactory to create new threads when needed. At any point, at most nThreads threads will be active
     * processing tasks. If additional tasks are submitted when all threads are active, they will wait in the queue
     * until a thread is available. If any thread terminates due to a failure during execution prior to shutdown, a new
     * one will take its place if needed to execute subsequent tasks. The threads in the pool will exist until it is
     * explicitly shutdown.
     *
     * @param nThreads the number of threads in the pool
     * @param name the thread pool name
     * @return the newly created thread pool
     */
    public static ExecutorService newFixedThreadPool(int nThreads, String name) {
        return Executors.newFixedThreadPool(nThreads, new ThreadFactoryWithNamePrefix(name));
    }

    /**
     * ThreadFactory with the ability to set the thread name prefix. This class is the same as
     * {@link java.util.concurrent.Executors#defaultThreadFactory()} from JDK8, except for the thread naming feature.
     * <p>
     * The factory creates threads that have names on the form <i>prefix-thread-M</i>, where <i>prefix</i> is a string
     * provided in the constructor and <i>M</i> is the sequence number of the thread created by this factory.
     */
    private static class ThreadFactoryWithNamePrefix implements ThreadFactory {
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        /**
         * Creates a new ThreadFactory where threads are created with a name prefix
         * of <code>prefix</code>.
         *
         * @param prefix Thread name prefix.
         */
        public ThreadFactoryWithNamePrefix(String prefix) {
            SecurityManager secMac = System.getSecurityManager();
            group = (secMac != null) ? secMac.getThreadGroup() : Thread.currentThread().getThreadGroup();
            namePrefix = prefix + "-thread-";
        }

        @Override
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(group, runnable, namePrefix + threadNumber.getAndIncrement(), 0);
            if (thread.isDaemon()) {
                thread.setDaemon(false);
            }
            if (thread.getPriority() != Thread.NORM_PRIORITY) {
                thread.setPriority(Thread.NORM_PRIORITY);
            }
            thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                @Override
                public void uncaughtException(Thread t, Throwable e) {
                    logger.warn("Uncaught exception in thread {}", t.getName(), e);
                }
            });
            return thread;
        }
    }

}

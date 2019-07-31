/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.internal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * {@link NotificationService} is used to provide notifications to our listeners "safely". A separate
 * thread is used so that the notifier is not blocked.
 * <p>
 * This helper class ensures that the stack handles threads efficiently throughout the system.
 *
 * @author Chris Jackson
 */
public class NotificationService {
    /**
     * The logger
     */
    private static Logger logger = LoggerFactory.getLogger(NotificationService.class);

    private static ExecutorService executorService = Executors.newCachedThreadPool();

    /**
     * Initializes the notification service
     */
    public static void initialize() {
        if (executorService.isShutdown()) {
            executorService = Executors.newCachedThreadPool();
        }
    }

    public static void execute(Runnable command) {
        if (executorService.isShutdown()) {
            logger.debug("NotificationService is shutdown. Not scheduling {}", command.getClass().getName());
            return;
        }
        try {
            executorService.execute(command);
        } catch (Exception e) {
            logger.error("NotificationService scheduler error ", e);
        }
    }

    /**
     * Shuts down the notification service. This will wait for the specified period before terminating all threads.
     *
     * @param wait the number of milliseconds to wait for all threads to close before terminating
     */
    public static void shutdown(long wait) {
        executorService.shutdown();
        try {
            executorService.awaitTermination(wait, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
        }
        executorService.shutdownNow();
    }

}

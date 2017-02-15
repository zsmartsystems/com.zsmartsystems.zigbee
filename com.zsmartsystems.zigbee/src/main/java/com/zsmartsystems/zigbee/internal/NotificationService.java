package com.zsmartsystems.zigbee.internal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * {@link NotificationService} is used to provide notifications to our listeners "safely". A separate
 * thread is used so that the notifier is not blocked.
 * <p>
 * This helper class ensures that the stack handles threads efficiently throughout the system.
 *
 * @author Chris Jackson
 */
public class NotificationService {
    private static ExecutorService executorService = Executors.newCachedThreadPool();

    public static void execute(Runnable command) {
        executorService.execute(command);
    }

}

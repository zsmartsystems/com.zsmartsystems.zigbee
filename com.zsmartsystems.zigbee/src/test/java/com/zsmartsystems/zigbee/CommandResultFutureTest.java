package com.zsmartsystems.zigbee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.Test;

/**
 *
 * @author Chris Jackson
 *
 */
public class CommandResultFutureTest {
    @Test
    public void testIsDone() {
        CommandResultFuture future = new CommandResultFuture(null);
        assertFalse(future.isDone());

        CommandResult result = new CommandResult();
        future.set(result);
        assertTrue(future.isDone());

        try {
            assertEquals(result, future.get());
            assertEquals(result, future.get(1, TimeUnit.MICROSECONDS));
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testCancel() {
        CommandResultFuture future = new CommandResultFuture(null);
        future.cancel(true);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testIsCancelled() {
        CommandResultFuture future = new CommandResultFuture(null);
        future.isCancelled();
    }
}

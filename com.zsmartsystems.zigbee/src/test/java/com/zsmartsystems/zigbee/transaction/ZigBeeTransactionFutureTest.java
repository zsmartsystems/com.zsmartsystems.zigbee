/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.transaction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.Test;

import com.zsmartsystems.zigbee.CommandResult;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeTransactionFutureTest {
    @Test
    public void testIsDone() {
        ZigBeeTransactionFuture future = new ZigBeeTransactionFuture();
        assertFalse(future.isDone());

        CommandResult result = new CommandResult();
        future.set(result);
        assertTrue(future.isDone());

        try {
            assertEquals(result, future.get());
            assertEquals(result, future.get(1, TimeUnit.MICROSECONDS));
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }
    }

    public void testCancel() {
        ZigBeeTransactionFuture future = new ZigBeeTransactionFuture();
        assertFalse(future.isCancelled());
        assertTrue(future.cancel(true));
        assertFalse(future.cancel(true));
        assertTrue(future.isCancelled());
    }

}

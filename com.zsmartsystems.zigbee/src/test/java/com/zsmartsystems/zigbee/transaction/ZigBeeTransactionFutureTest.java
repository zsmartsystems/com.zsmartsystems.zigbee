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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
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
    protected void setField(Class clazz, Object object, String fieldName, Object newValue) throws Exception {
        Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
        field.set(object, newValue);
    }

    @Test
    public void testIsDone() throws InterruptedException, ExecutionException, TimeoutException {
        ZigBeeTransactionFuture future = new ZigBeeTransactionFuture();
        assertFalse(future.isDone());

        CommandResult result = new CommandResult();
        future.set(result);
        assertTrue(future.isDone());

        assertEquals(result, future.get());
        assertEquals(result, future.get(0, TimeUnit.MICROSECONDS));
    }

    @Test
    public void testDefaultTimeout() throws Exception {
        ZigBeeTransactionFuture future = new ZigBeeTransactionFuture();
        assertFalse(future.isDone());

        setField(ZigBeeTransactionFuture.class, future, "TIMEOUT_MILLISECONDS", (long) 0);

        CommandResult result = future.get();
        assertNull(result.getResponse());
    }

    @Test
    public void testTimeout() throws InterruptedException, ExecutionException, TimeoutException {
        ZigBeeTransactionFuture future = new ZigBeeTransactionFuture();
        assertFalse(future.isDone());

        assertNotNull(future.get(0, TimeUnit.MICROSECONDS));
        assertTrue(future.isDone());

        CommandResult result = future.get();
        assertNull(result.getResponse());

        assertFalse(future.cancel(true));
    }

    @Test
    public void testCancel() {
        ZigBeeTransactionFuture future = new ZigBeeTransactionFuture();
        assertFalse(future.isCancelled());
        assertTrue(future.cancel(true));
        assertFalse(future.cancel(true));
        assertTrue(future.isCancelled());
    }

}

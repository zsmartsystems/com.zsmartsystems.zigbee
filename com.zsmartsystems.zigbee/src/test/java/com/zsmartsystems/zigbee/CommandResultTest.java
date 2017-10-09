/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.zsmartsystems.zigbee.zcl.ZclStatus;
import com.zsmartsystems.zigbee.zcl.clusters.general.DefaultResponse;

/**
 *
 * @author Chris Jackson
 *
 */
public class CommandResultTest {

    @Test
    public void testIsError() {
        CommandResult result = new CommandResult();
        assertTrue(result.isError());

        result = new CommandResult("Test");
        assertTrue(result.isError());
        assertFalse(result.isSuccess());

        result = new CommandResult(new Command());
        assertFalse(result.isError());
        assertTrue(result.isSuccess());

        DefaultResponse response = new DefaultResponse();
        response.setStatusCode(ZclStatus.SUCCESS);
        result = new CommandResult(response);
        assertFalse(result.isError());
        assertTrue(result.isSuccess());

        response = new DefaultResponse();
        response.setStatusCode(ZclStatus.FAILURE);
        result = new CommandResult(response);
        assertTrue(result.isError());
        assertFalse(result.isSuccess());
    }

    @Test
    public void testGetMessage() {
        CommandResult result = new CommandResult();
        assertTrue(result.isError());
        assertTrue(result.isTimeout());

        assertEquals("Timeout.", result.getMessage());

        result = new CommandResult("Test");
        assertEquals("Test", result.getMessage());
    }
}

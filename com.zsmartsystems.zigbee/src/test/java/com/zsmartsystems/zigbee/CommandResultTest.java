package com.zsmartsystems.zigbee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

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
        assertFalse(result.isError());
        assertTrue(result.isSuccess());

        result = new CommandResult(new Command());
        assertTrue(result.isError());
        assertFalse(result.isSuccess());

        DefaultResponse response = new DefaultResponse();
        response.setStatusCode(0);
        result = new CommandResult(response);
        assertFalse(result.isError());
        assertTrue(result.isSuccess());

        response = new DefaultResponse();
        response.setStatusCode(1);
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

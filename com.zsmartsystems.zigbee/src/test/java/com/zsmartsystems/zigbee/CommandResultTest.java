/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.zsmartsystems.zigbee.zcl.clusters.iaszone.ZoneEnrollResponse;
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
    public void testIsSuccessIfResponseReceived() {
        CommandResult commandResult = new CommandResult(ZigBeeStatus.SUCCESS, new ZoneEnrollResponse(1, 2));
        assertTrue(commandResult.isSuccess());

        commandResult = new CommandResult(ZigBeeStatus.SUCCESS, new DefaultResponse(1, ZclStatus.SUCCESS));
        assertTrue(commandResult.isSuccess());

        commandResult = new CommandResult(ZigBeeStatus.SUCCESS, new DefaultResponse(1, ZclStatus.FAILURE));
        assertFalse(commandResult.isSuccess());
    }

    @Test
    public void testIsSuccessIfNoResponseReceived() {
        CommandResult commandResult = new CommandResult(ZigBeeStatus.SUCCESS, null);
        assertTrue(commandResult.isSuccess());

        commandResult = new CommandResult(ZigBeeStatus.FAILURE, null);
        assertFalse(commandResult.isSuccess());
    }

    @Test
    public void testIsErrorIfResponseReceived() {
        CommandResult commandResult = new CommandResult(ZigBeeStatus.SUCCESS, new ZoneEnrollResponse(1, 2));
        assertFalse(commandResult.isError());

        commandResult = new CommandResult(ZigBeeStatus.SUCCESS, new DefaultResponse(1, ZclStatus.SUCCESS));
        assertFalse(commandResult.isError());

        commandResult = new CommandResult(ZigBeeStatus.SUCCESS, new DefaultResponse(1, ZclStatus.FAILURE));
        assertTrue(commandResult.isError());
    }

    @Test
    public void testIsErrorIfNoResponseReceived() {
        CommandResult commandResult = new CommandResult(ZigBeeStatus.SUCCESS, null);
        assertFalse(commandResult.isError());

        commandResult = new CommandResult(ZigBeeStatus.FAILURE, null);
        assertTrue(commandResult.isError());
    }

    @Test
    public void testIsTimeoutIfResponseReceived() {
        CommandResult commandResult = new CommandResult(ZigBeeStatus.SUCCESS, new ZoneEnrollResponse(1, 2));
        assertFalse(commandResult.isTimeout());
    }

    @Test
    public void testIsTimeoutIfNoResponseReceived() {
        CommandResult commandResult = new CommandResult(ZigBeeStatus.FAILURE, null);
        assertTrue(commandResult.isTimeout());
    }

}

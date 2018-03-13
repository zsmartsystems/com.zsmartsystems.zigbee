/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.protocol;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.clusters.groups.AddGroupCommand;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZclCommandTypeTest {
    @Test
    public void instantiateCommand() {
        ZclCommandType cmd = ZclCommandType.ADD_GROUP_COMMAND;

        ZclCommand cmdClass = cmd.instantiateCommand();
        assertTrue(cmdClass instanceof AddGroupCommand);
    }

    @Test
    public void getClusterType() {
        ZclCommandType cmd = ZclCommandType.ALARM_COMMAND;

        assertEquals(0x0009, cmd.getClusterType());
    }

    @Test
    public void getId() {
        ZclCommandType cmd = ZclCommandType.ALARM_COMMAND;

        assertEquals(0x0000, cmd.getId());
    }

    @Test
    public void getDirection() {
        ZclCommandType cmd = ZclCommandType.ALARM_COMMAND;

        assertEquals(ZclCommandDirection.SERVER_TO_CLIENT, cmd.getDirection());
    }

    @Test
    public void isGeneric() {
        ZclCommandType cmd = ZclCommandType.ALARM_COMMAND;
        assertEquals(false, cmd.isGeneric());

        cmd = ZclCommandType.READ_ATTRIBUTES_COMMAND;
        assertEquals(true, cmd.isGeneric());
    }

    @Test
    public void getGeneric() {
        assertEquals(ZclCommandType.READ_ATTRIBUTES_COMMAND, ZclCommandType.getGeneric(0));
    }

    @Test
    public void getResponse() {
        assertEquals(ZclCommandType.ADD_GROUP_COMMAND,
                ZclCommandType.getCommandType(4, 0, ZclCommandDirection.CLIENT_TO_SERVER));
    }
}

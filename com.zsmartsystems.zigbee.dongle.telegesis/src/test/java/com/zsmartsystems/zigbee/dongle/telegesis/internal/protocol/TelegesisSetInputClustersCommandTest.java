/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 *
 * @author Chris Jackson
 *
 */
public class TelegesisSetInputClustersCommandTest extends TelegesisFrameBaseTest {
    @Test
    public void testFrame() {
        List<Integer> clusterList = new ArrayList<Integer>();
        clusterList.add(1);
        clusterList.add(2);
        TelegesisSetInputClustersCommand command = new TelegesisSetInputClustersCommand();
        command.setClusterList(clusterList);
        command.addClusterList(3);
        command.addClusterList(4);
        command.removeClusterList(3);
        System.out.println(command);
        assertEquals("ATS4B=0001,0002,0004\r\n", intArrayToString(command.serialize()));

        command.deserialize(stringToIntArray("OK\r"));
        System.out.println(command);

        assertEquals(TelegesisStatusCode.SUCCESS, command.getStatus());
    }

}

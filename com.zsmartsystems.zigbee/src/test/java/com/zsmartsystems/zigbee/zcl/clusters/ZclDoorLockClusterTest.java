/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.mockito.Mockito;

import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.zcl.ZclFrameType;
import com.zsmartsystems.zigbee.zcl.clusters.doorlock.LockDoorCommand;
import com.zsmartsystems.zigbee.zcl.clusters.doorlock.LockDoorResponse;
import com.zsmartsystems.zigbee.zcl.clusters.doorlock.Toggle;
import com.zsmartsystems.zigbee.zcl.clusters.doorlock.ToggleResponse;
import com.zsmartsystems.zigbee.zcl.clusters.doorlock.UnlockDoorCommand;
import com.zsmartsystems.zigbee.zcl.clusters.doorlock.UnlockDoorResponse;
import com.zsmartsystems.zigbee.zcl.clusters.doorlock.UnlockWithTimeout;
import com.zsmartsystems.zigbee.zcl.clusters.doorlock.UnlockWithTimeoutResponse;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZclDoorLockClusterTest {
    @Test
    public void test() {
        ZclDoorLockCluster cluster = new ZclDoorLockCluster(Mockito.mock(ZigBeeEndpoint.class));

        assertTrue(cluster.getCommandFromId(ZclFrameType.CLUSTER_SPECIFIC_COMMAND, 0) instanceof LockDoorCommand);
        assertTrue(cluster.getCommandFromId(ZclFrameType.CLUSTER_SPECIFIC_COMMAND, 1) instanceof UnlockDoorCommand);
        assertTrue(cluster.getCommandFromId(ZclFrameType.CLUSTER_SPECIFIC_COMMAND, 2) instanceof Toggle);
        assertTrue(cluster.getCommandFromId(ZclFrameType.CLUSTER_SPECIFIC_COMMAND, 3) instanceof UnlockWithTimeout);

        assertTrue(cluster.getResponseFromId(ZclFrameType.CLUSTER_SPECIFIC_COMMAND, 0) instanceof LockDoorResponse);
        assertTrue(cluster.getResponseFromId(ZclFrameType.CLUSTER_SPECIFIC_COMMAND, 1) instanceof UnlockDoorResponse);
        assertTrue(cluster.getResponseFromId(ZclFrameType.CLUSTER_SPECIFIC_COMMAND, 2) instanceof ToggleResponse);
        assertTrue(cluster.getResponseFromId(ZclFrameType.CLUSTER_SPECIFIC_COMMAND,
                3) instanceof UnlockWithTimeoutResponse);
    }
}

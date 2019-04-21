/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.cluster;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.mockito.Mockito;

import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.zcl.ZclFrameType;
import com.zsmartsystems.zigbee.zcl.clusters.ZclAlarmsCluster;
import com.zsmartsystems.zigbee.zcl.clusters.alarms.AlarmCommand;
import com.zsmartsystems.zigbee.zcl.clusters.alarms.GetAlarmCommand;
import com.zsmartsystems.zigbee.zcl.clusters.alarms.GetAlarmResponse;
import com.zsmartsystems.zigbee.zcl.clusters.alarms.ResetAlarmCommand;
import com.zsmartsystems.zigbee.zcl.clusters.alarms.ResetAllAlarmsCommand;
import com.zsmartsystems.zigbee.zcl.clusters.general.ReadAttributesCommand;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZclAlarmsClusterTest {
    @Test
    public void getCommandFromId() {
        ZclAlarmsCluster cluster = new ZclAlarmsCluster(Mockito.mock(ZigBeeEndpoint.class));

        assertTrue(cluster.getCommandFromId(ZclFrameType.CLUSTER_SPECIFIC_COMMAND, 0) instanceof ResetAlarmCommand);
        assertTrue(cluster.getCommandFromId(ZclFrameType.CLUSTER_SPECIFIC_COMMAND, 1) instanceof ResetAllAlarmsCommand);
        assertTrue(cluster.getCommandFromId(ZclFrameType.CLUSTER_SPECIFIC_COMMAND, 2) instanceof GetAlarmCommand);

        assertTrue(cluster.getCommandFromId(ZclFrameType.ENTIRE_PROFILE_COMMAND, 0) instanceof ReadAttributesCommand);
    }

    @Test
    public void getResponseFromId() {
        ZclAlarmsCluster cluster = new ZclAlarmsCluster(Mockito.mock(ZigBeeEndpoint.class));

        assertTrue(cluster.getResponseFromId(ZclFrameType.CLUSTER_SPECIFIC_COMMAND, 0) instanceof AlarmCommand);
        assertTrue(cluster.getResponseFromId(ZclFrameType.CLUSTER_SPECIFIC_COMMAND, 1) instanceof GetAlarmResponse);

        assertTrue(cluster.getResponseFromId(ZclFrameType.ENTIRE_PROFILE_COMMAND, 0) instanceof ReadAttributesCommand);
    }
}

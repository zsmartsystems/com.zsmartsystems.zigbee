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
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.ColorLoopSetCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.EnhancedMoveToHueAndSaturationCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.EnhancedMoveToHueCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.EnhancedStepHueCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.MoveColorCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.MoveHueCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.MoveSaturationCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.MoveToColorCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.MoveToColorTemperatureCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.MoveToHueAndSaturationCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.MoveToHueCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.MoveToSaturationCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.StepColorCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.StepHueCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.StepSaturationCommand;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZclColorControlClusterTest {
    @Test
    public void test() {
        ZclColorControlCluster cluster = new ZclColorControlCluster(Mockito.mock(ZigBeeEndpoint.class));

        assertTrue(cluster.getCommandFromId(ZclFrameType.CLUSTER_SPECIFIC_COMMAND, 0) instanceof MoveToHueCommand);
        assertTrue(cluster.getCommandFromId(ZclFrameType.CLUSTER_SPECIFIC_COMMAND, 1) instanceof MoveHueCommand);
        assertTrue(cluster.getCommandFromId(ZclFrameType.CLUSTER_SPECIFIC_COMMAND, 2) instanceof StepHueCommand);
        assertTrue(
                cluster.getCommandFromId(ZclFrameType.CLUSTER_SPECIFIC_COMMAND, 3) instanceof MoveToSaturationCommand);
        assertTrue(cluster.getCommandFromId(ZclFrameType.CLUSTER_SPECIFIC_COMMAND, 4) instanceof MoveSaturationCommand);
        assertTrue(cluster.getCommandFromId(ZclFrameType.CLUSTER_SPECIFIC_COMMAND, 5) instanceof StepSaturationCommand);
        assertTrue(cluster.getCommandFromId(ZclFrameType.CLUSTER_SPECIFIC_COMMAND,
                6) instanceof MoveToHueAndSaturationCommand);
        assertTrue(cluster.getCommandFromId(ZclFrameType.CLUSTER_SPECIFIC_COMMAND, 7) instanceof MoveToColorCommand);
        assertTrue(cluster.getCommandFromId(ZclFrameType.CLUSTER_SPECIFIC_COMMAND, 8) instanceof MoveColorCommand);
        assertTrue(cluster.getCommandFromId(ZclFrameType.CLUSTER_SPECIFIC_COMMAND, 9) instanceof StepColorCommand);
        assertTrue(cluster.getCommandFromId(ZclFrameType.CLUSTER_SPECIFIC_COMMAND,
                10) instanceof MoveToColorTemperatureCommand);
        assertTrue(cluster.getCommandFromId(ZclFrameType.CLUSTER_SPECIFIC_COMMAND,
                64) instanceof EnhancedMoveToHueCommand);
        assertTrue(
                cluster.getCommandFromId(ZclFrameType.CLUSTER_SPECIFIC_COMMAND, 65) instanceof EnhancedStepHueCommand);
        assertTrue(cluster.getCommandFromId(ZclFrameType.CLUSTER_SPECIFIC_COMMAND,
                66) instanceof EnhancedMoveToHueAndSaturationCommand);
        assertTrue(cluster.getCommandFromId(ZclFrameType.CLUSTER_SPECIFIC_COMMAND, 67) instanceof ColorLoopSetCommand);
    }
}

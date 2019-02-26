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
import com.zsmartsystems.zigbee.zcl.clusters.ZclOnOffCluster;
import com.zsmartsystems.zigbee.zcl.clusters.onoff.OffCommand;
import com.zsmartsystems.zigbee.zcl.clusters.onoff.OnCommand;
import com.zsmartsystems.zigbee.zcl.clusters.onoff.ToggleCommand;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZclOnOffClusterTest {
    @Test
    public void getCommandFromId() {
        ZclOnOffCluster cluster = new ZclOnOffCluster(Mockito.mock(ZigBeeEndpoint.class));

        assertTrue(cluster.getCommandFromId(0) instanceof OffCommand);
        assertTrue(cluster.getCommandFromId(1) instanceof OnCommand);
        assertTrue(cluster.getCommandFromId(2) instanceof ToggleCommand);
    }
}

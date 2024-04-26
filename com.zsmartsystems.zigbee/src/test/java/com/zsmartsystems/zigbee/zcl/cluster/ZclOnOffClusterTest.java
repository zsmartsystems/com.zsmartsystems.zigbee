/**
 * Copyright (c) 2016-2024 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.cluster;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.mockito.Mockito;

import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.zcl.ZclFrameType;
import com.zsmartsystems.zigbee.zcl.clusters.ZclOnOffCluster;
import com.zsmartsystems.zigbee.zcl.clusters.general.ReadAttributesCommand;
import com.zsmartsystems.zigbee.zcl.clusters.onoff.OffCommand;
import com.zsmartsystems.zigbee.zcl.clusters.onoff.OnCommand;
import com.zsmartsystems.zigbee.zcl.clusters.onoff.ToggleCommand;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZclOnOffClusterTest {
    @Test
    public void getCommandFromId() {
        ZclOnOffCluster cluster = new ZclOnOffCluster(Mockito.mock(ZigBeeEndpoint.class));

        assertTrue(cluster.getCommandFromId(ZclFrameType.CLUSTER_SPECIFIC_COMMAND, 0) instanceof OffCommand);
        assertTrue(cluster.getCommandFromId(ZclFrameType.CLUSTER_SPECIFIC_COMMAND, 1) instanceof OnCommand);
        assertTrue(cluster.getCommandFromId(ZclFrameType.CLUSTER_SPECIFIC_COMMAND, 2) instanceof ToggleCommand);

        assertTrue(cluster.getCommandFromId(ZclFrameType.ENTIRE_PROFILE_COMMAND, 0) instanceof ReadAttributesCommand);
    }

    @Test
    public void get() {
        ZclOnOffCluster cluster = new ZclOnOffCluster(Mockito.mock(ZigBeeEndpoint.class));

        cluster.setClient();
        assertNull(cluster.getAttribute(0));

        cluster.setServer();
        assertNotNull(cluster.getAttribute(0));
        assertEquals(ZclDataType.BOOLEAN, cluster.getAttribute(0).getDataType());
    }
}

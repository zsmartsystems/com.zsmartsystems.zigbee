/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.zsmartsystems.zigbee.ZigBeeEndpointAddress;
import com.zsmartsystems.zigbee.zcl.clusters.general.DefaultResponse;
import com.zsmartsystems.zigbee.zcl.clusters.onoff.OnCommand;
import com.zsmartsystems.zigbee.zdo.ZdoCommand;
import com.zsmartsystems.zigbee.zdo.command.DeviceAnnounce;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZclResponseMatcherTest {

    @Test
    public void testMatch() {
        ZclTransactionMatcher matcher = new ZclTransactionMatcher();

        ZclCommand zclCommand = new OnCommand();
        zclCommand.setDestinationAddress(new ZigBeeEndpointAddress(1234, 5));
        ZclCommand zclResponse = new DefaultResponse();
        zclResponse.setSourceAddress(new ZigBeeEndpointAddress(1234, 5));

        assertFalse(matcher.isTransactionMatch(zclCommand, zclResponse));

        zclCommand.setTransactionId(22);
        zclResponse.setTransactionId(22);
        assertTrue(matcher.isTransactionMatch(zclCommand, zclResponse));

        zclResponse.setTransactionId(222);
        assertFalse(matcher.isTransactionMatch(zclCommand, zclResponse));

        ZdoCommand zdoResponse = new DeviceAnnounce();
        assertFalse(matcher.isTransactionMatch(zclCommand, zdoResponse));

        zclResponse.setTransactionId(22);
        assertTrue(matcher.isTransactionMatch(zclCommand, zclResponse));

        zclResponse.setSourceAddress(new ZigBeeEndpointAddress(1234, 6));
        assertFalse(matcher.isTransactionMatch(zclCommand, zclResponse));
    }
}

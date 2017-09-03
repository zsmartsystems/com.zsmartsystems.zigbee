/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zdo;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.zsmartsystems.zigbee.ZigBeeDeviceAddress;
import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.clusters.onoff.OffCommand;
import com.zsmartsystems.zigbee.zdo.command.BindRequest;
import com.zsmartsystems.zigbee.zdo.command.BindResponse;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZdoResponseMatcherTest {

    @Test
    public void testMatch() {
        ZdoResponseMatcher matcher = new ZdoResponseMatcher();

        ZdoRequest zdoCommand = new BindRequest();
        BindResponse zdoResponse = new BindResponse();

        zdoCommand.setDestinationAddress(new ZigBeeDeviceAddress(1234));
        zdoResponse.setSourceAddress(new ZigBeeDeviceAddress(1234));
        assertTrue(matcher.isMatch(zdoCommand, zdoResponse));

        zdoResponse.setSourceAddress(new ZigBeeDeviceAddress(5678));
        assertFalse(matcher.isMatch(zdoCommand, zdoResponse));

        ZclCommand zclResponse = new OffCommand();
        assertFalse(matcher.isMatch(zdoCommand, zclResponse));
    }
}

/**
 * Copyright (c) 2016-2025 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zdo;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.zsmartsystems.zigbee.ZigBeeEndpointAddress;
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
        ZdoTransactionMatcher matcher = new ZdoTransactionMatcher();

        ZdoRequest zdoCommand = new BindRequest(null);
        BindResponse zdoResponse = new BindResponse(null);

        zdoCommand.setDestinationAddress(new ZigBeeEndpointAddress(1234));
        zdoResponse.setSourceAddress(new ZigBeeEndpointAddress(1234));
        assertTrue(matcher.isTransactionMatch(zdoCommand, zdoResponse));

        zdoResponse.setSourceAddress(new ZigBeeEndpointAddress(5678));
        assertFalse(matcher.isTransactionMatch(zdoCommand, zdoResponse));

        ZclCommand zclResponse = new OffCommand();
        assertFalse(matcher.isTransactionMatch(zdoCommand, zclResponse));
    }
}

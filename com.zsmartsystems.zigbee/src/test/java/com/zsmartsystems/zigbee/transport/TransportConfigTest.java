/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.transport;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.zsmartsystems.zigbee.ZigBeeStatus;

/**
 *
 * @author Chris Jackson
 *
 */
public class TransportConfigTest {
    @Test
    public void testConfig() {
        TransportConfig config;

        config = new TransportConfig();
        assertEquals(0, config.getOptions().size());

        config = new TransportConfig(TransportConfigOption.TRUST_CENTRE_JOIN_MODE, TrustCentreJoinMode.TC_JOIN_DENY);
        assertEquals(1, config.getOptions().size());

        assertTrue(config.addOption(TransportConfigOption.CONCENTRATOR_TYPE, ConcentratorType.DISABLED));
        assertEquals(2, config.getOptions().size());

        assertFalse(config.addOption(TransportConfigOption.TRUST_CENTRE_JOIN_MODE, TrustCentreJoinMode.TC_JOIN_DENY));
        assertEquals(2, config.getOptions().size());

        assertEquals(TrustCentreJoinMode.TC_JOIN_DENY, config.getOption(TransportConfigOption.TRUST_CENTRE_JOIN_MODE));
        assertNull(config.getOption(TransportConfigOption.TRUST_CENTRE_LINK_KEY));

        assertTrue(config.setResult(TransportConfigOption.CONCENTRATOR_TYPE, ZigBeeStatus.SUCCESS));
        assertFalse(config.setResult(TransportConfigOption.TRUST_CENTRE_LINK_KEY, ZigBeeStatus.SUCCESS));
        assertFalse(config.setResult(TransportConfigOption.CONCENTRATOR_TYPE, ZigBeeStatus.SUCCESS));

        assertEquals(ZigBeeStatus.BAD_RESPONSE, config.getResult(TransportConfigOption.TRUST_CENTRE_JOIN_MODE));
        assertEquals(ZigBeeStatus.INVALID_ARGUMENTS, config.getResult(TransportConfigOption.TRUST_CENTRE_LINK_KEY));
    }
}

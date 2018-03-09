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

        assertTrue(config.setResult(TransportConfigOption.CONCENTRATOR_TYPE, TransportConfigResult.SUCCESS));
        assertFalse(config.setResult(TransportConfigOption.TRUST_CENTRE_LINK_KEY, TransportConfigResult.SUCCESS));
        assertFalse(config.setResult(TransportConfigOption.CONCENTRATOR_TYPE, TransportConfigResult.SUCCESS));

        assertEquals(TransportConfigResult.SUCCESS, config.getResult(TransportConfigOption.CONCENTRATOR_TYPE));
        assertEquals(TransportConfigResult.ERROR_NO_RESULT,
                config.getResult(TransportConfigOption.TRUST_CENTRE_JOIN_MODE));
        assertEquals(TransportConfigResult.ERROR_REQUEST_NOT_SET,
                config.getResult(TransportConfigOption.TRUST_CENTRE_LINK_KEY));
    }
}

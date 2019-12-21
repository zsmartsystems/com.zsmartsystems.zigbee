/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.mockito.Mockito;

import com.zsmartsystems.zigbee.ZigBeeStatus;

/**
 *
 * @author Chris Jackson
 *
 */
public class EmberCbkeProviderTest {

    @Test
    public void setClientServer() {
        ZigBeeDongleEzsp dongle = Mockito.mock(ZigBeeDongleEzsp.class);
        EmberCbkeProvider cbkeProvider = new EmberCbkeProvider(dongle);

        assertEquals(ZigBeeStatus.SUCCESS, cbkeProvider.setClientServer(true));
    }

}

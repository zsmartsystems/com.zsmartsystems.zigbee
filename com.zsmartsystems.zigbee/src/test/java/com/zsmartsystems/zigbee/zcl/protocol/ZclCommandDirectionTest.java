/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.protocol;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZclCommandDirectionTest {
    @Test
    public void test() {
        ZclCommandDirection direction = ZclCommandDirection.CLIENT_TO_SERVER;
        assertEquals(ZclCommandDirection.SERVER_TO_CLIENT, direction.getResponseDirection());

        direction = ZclCommandDirection.SERVER_TO_CLIENT;
        assertEquals(ZclCommandDirection.CLIENT_TO_SERVER, direction.getResponseDirection());
    }
}

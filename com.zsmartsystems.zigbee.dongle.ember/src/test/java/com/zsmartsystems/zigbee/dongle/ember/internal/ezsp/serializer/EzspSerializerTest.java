/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.serializer;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.serializer.EzspSerializer;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.structure.EmberInitialSecurityBitmask;

/**
 *
 * @author Chris Jackson
 *
 */
public class EzspSerializerTest {
    @Test
    public void serializeEmberInitialSecurityBitmask() {
        EzspSerializer serializer;
        Set<EmberInitialSecurityBitmask> securityBitmask;

        serializer = new EzspSerializer();
        securityBitmask = new HashSet<EmberInitialSecurityBitmask>();
        securityBitmask.add(EmberInitialSecurityBitmask.EMBER_HAVE_PRECONFIGURED_KEY);
        securityBitmask.add(EmberInitialSecurityBitmask.EMBER_HAVE_NETWORK_KEY);
        securityBitmask.add(EmberInitialSecurityBitmask.EMBER_REQUIRE_ENCRYPTED_KEY);
        serializer.serializeEmberInitialSecurityBitmask(securityBitmask);
        assertTrue(Arrays.equals(new int[] { 0x00, 0x0B }, serializer.getPayload()));

        serializer = new EzspSerializer();
        securityBitmask = new HashSet<EmberInitialSecurityBitmask>();
        securityBitmask.add(EmberInitialSecurityBitmask.EMBER_TRUST_CENTER_USES_HASHED_LINK_KEY);
        serializer.serializeEmberInitialSecurityBitmask(securityBitmask);
        assertTrue(Arrays.equals(new int[] { 0x84, 0x00 }, serializer.getPayload()));

        serializer = new EzspSerializer();
        securityBitmask = new HashSet<EmberInitialSecurityBitmask>();
        securityBitmask.add(EmberInitialSecurityBitmask.EMBER_DISTRIBUTED_TRUST_CENTER_MODE);
        securityBitmask.add(EmberInitialSecurityBitmask.EMBER_HAVE_NETWORK_KEY);
        securityBitmask.add(EmberInitialSecurityBitmask.EMBER_NO_FRAME_COUNTER_RESET);
        serializer.serializeEmberInitialSecurityBitmask(securityBitmask);
        assertTrue(Arrays.equals(new int[] { 0x02, 0x12 }, serializer.getPayload()));
    }
}

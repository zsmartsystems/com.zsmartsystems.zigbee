package com.zsmartsystems.zigbee.dongle.ember.ezsp.serializer;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberInitialSecurityBitmask;

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

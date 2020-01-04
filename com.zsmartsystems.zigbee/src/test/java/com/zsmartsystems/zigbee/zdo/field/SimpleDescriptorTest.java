/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zdo.field;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;

import com.zsmartsystems.zigbee.CommandTest;

/**
 *
 * @author Chris Jackson
 *
 */
public class SimpleDescriptorTest extends CommandTest {

    @Test
    public void testDescriptor() {
        SimpleDescriptor descriptor = new SimpleDescriptor();

        descriptor.setDeviceId(1);
        descriptor.setDeviceVersion(2);
        descriptor.setEndpoint(3);
        descriptor.setProfileId(0x104);
        descriptor.setInputClusterList(Arrays.asList(1, 2, 256));
        descriptor.setOutputClusterList(Collections.emptyList());

        assertEquals(1, descriptor.getDeviceId());
        assertEquals(2, descriptor.getDeviceVersion());
        assertEquals(3, descriptor.getEndpoint());
        assertEquals(0x104, descriptor.getProfileId());
        assertEquals(3, descriptor.getInputClusterList().size());
        assertTrue(descriptor.getInputClusterList().contains(256));
        assertTrue(descriptor.getOutputClusterList().isEmpty());

        System.out.println(descriptor);
    }

}

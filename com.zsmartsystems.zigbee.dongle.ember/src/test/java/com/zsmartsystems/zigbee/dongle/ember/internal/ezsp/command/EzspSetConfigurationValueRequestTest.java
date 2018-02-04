/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.EzspFrame;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.EzspFrameTest;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.structure.EmberZdoConfigurationFlags;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.structure.EzspConfigId;

/**
 *
 * @author Chris Jackson
 *
 */
public class EzspSetConfigurationValueRequestTest extends EzspFrameTest {
    @Test
    public void testVersion() {
        EzspFrame.setEzspVersion(4);
        EzspSetConfigurationValueRequest request = new EzspSetConfigurationValueRequest();
        request.setSequenceNumber(2);
        request.setConfigId(EzspConfigId.EZSP_CONFIG_APPLICATION_ZDO_FLAGS);
        request.setValue(EmberZdoConfigurationFlags.EMBER_APP_RECEIVES_SUPPORTED_ZDO_REQUESTS.getKey());

        assertTrue(Arrays.equals(getPacketData("02 00 53 2A 01 00"), request.serialize()));
    }
}

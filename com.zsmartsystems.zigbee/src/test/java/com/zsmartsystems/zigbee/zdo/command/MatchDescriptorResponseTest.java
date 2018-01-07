/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zdo.command;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.zsmartsystems.zigbee.CommandTest;
import com.zsmartsystems.zigbee.ZigBeeEndpointAddress;
import com.zsmartsystems.zigbee.serialization.DefaultSerializer;
import com.zsmartsystems.zigbee.serialization.ZigBeeSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zdo.ZdoStatus;

/**
 *
 * @author Chris Jackson
 *
 */
public class MatchDescriptorResponseTest extends CommandTest {

    @Test
    public void testSendEndpoint1() {
        MatchDescriptorResponse matchResponse = new MatchDescriptorResponse();
        matchResponse.setStatus(ZdoStatus.SUCCESS);
        List<Integer> matchList = new ArrayList<Integer>();
        matchList.add(1);
        matchResponse.setMatchList(matchList);

        matchResponse.setDestinationAddress(new ZigBeeEndpointAddress(1234, 5));
        matchResponse.setNwkAddrOfInterest(1234);
        System.out.println(matchResponse);

        ZigBeeSerializer serializer = new DefaultSerializer();
        ZclFieldSerializer fieldSerializer = new ZclFieldSerializer(serializer);
        matchResponse.serialize(fieldSerializer);
        assertTrue(Arrays.equals(getPacketData("00 00 D2 04 01 01"), serializer.getPayload()));
    }

    @Test
    public void testSendEndpoint2() {
        MatchDescriptorResponse matchResponse = new MatchDescriptorResponse();
        matchResponse.setStatus(ZdoStatus.SUCCESS);
        List<Integer> matchList = new ArrayList<Integer>();
        matchList.add(1);
        matchList.add(2);
        matchResponse.setMatchList(matchList);

        matchResponse.setDestinationAddress(new ZigBeeEndpointAddress(1234, 5));
        matchResponse.setNwkAddrOfInterest(1234);
        System.out.println(matchResponse);

        ZigBeeSerializer serializer = new DefaultSerializer();
        ZclFieldSerializer fieldSerializer = new ZclFieldSerializer(serializer);
        matchResponse.serialize(fieldSerializer);
        assertTrue(Arrays.equals(getPacketData("00 00 D2 04 02 01 02"), serializer.getPayload()));
    }
}

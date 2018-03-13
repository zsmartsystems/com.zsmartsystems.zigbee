/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.scenes;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.zsmartsystems.zigbee.CommandTest;
import com.zsmartsystems.zigbee.serialization.DefaultSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.field.ExtensionFieldSet;

/**
 *
 * @author Chris Jackson
 *
 */
public class AddScenesCommandTest extends CommandTest {
    @Test
    public void testAddSceneCommand() throws Exception {
        int[] packet = getPacketData("01 00 02 03 00 0A 54 65 73 74 20 53 63 65 6E 65 04 00 05 06");
        AddSceneCommand request = new AddSceneCommand();
        request.setGroupId(1);
        request.setSceneId(2);
        request.setSceneName("Test Scene");
        request.setTransitionTime(3);
        final List<ExtensionFieldSet> list = new ArrayList<ExtensionFieldSet>();
        final ExtensionFieldSet data = new ExtensionFieldSet();
        data.setClusterId(4);
        data.setData(new int[] { 5, 6 });
        list.add(data);
        request.setExtensionFieldSets(list);
        System.out.println(request);

        DefaultSerializer serializer = new DefaultSerializer();
        ZclFieldSerializer fieldSerializer = new ZclFieldSerializer(serializer);

        request.serialize(fieldSerializer);
        assertTrue(Arrays.equals(packet, serializer.getPayload()));
    }
}

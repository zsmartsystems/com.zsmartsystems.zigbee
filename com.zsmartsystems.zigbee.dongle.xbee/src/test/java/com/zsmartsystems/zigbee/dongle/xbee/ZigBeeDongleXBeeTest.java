/**
 * Copyright (c) 2016-2025 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.xbee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.ZigBeeEndpointAddress;
import com.zsmartsystems.zigbee.ZigBeeNwkAddressMode;
import com.zsmartsystems.zigbee.aps.ZigBeeApsFrame;
import com.zsmartsystems.zigbee.dongle.xbee.internal.XBeeFrameHandler;
import com.zsmartsystems.zigbee.dongle.xbee.internal.protocol.XBeeCommand;
import com.zsmartsystems.zigbee.dongle.xbee.internal.protocol.XBeeTransmitRequestExplicitCommand;
import com.zsmartsystems.zigbee.zdo.ZdoStatus;
import com.zsmartsystems.zigbee.zdo.command.MatchDescriptorResponse;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeDongleXBeeTest {

    @Test
    public void getVersionString() {
        ZigBeeDongleXBee dongle = new ZigBeeDongleXBee(null);

        assertEquals("Unknown", dongle.getVersionString());
    }

    @Test
    public void sendCommand() {
        XBeeFrameHandler frameHandler = Mockito.mock(XBeeFrameHandler.class);
        ArgumentCaptor<XBeeCommand> commandCapture = ArgumentCaptor.forClass(XBeeCommand.class);
        Mockito.when(frameHandler.sendRequestAsync(commandCapture.capture())).thenReturn(null);

        ZigBeeDongleXBee dongle = new ZigBeeDongleXBee(null);

        Field field;
        try {
            field = dongle.getClass().getDeclaredField("frameHandler");
            field.setAccessible(true);
            field.set(dongle, frameHandler);
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }

        List<Integer> matchList = new ArrayList<>();
        matchList.add(1);
        MatchDescriptorResponse command = new MatchDescriptorResponse(ZdoStatus.SUCCESS, 46946, matchList);
        command.setDestinationAddress(new ZigBeeEndpointAddress(46946));
        command.setTransactionId(0x2A);

        ZigBeeApsFrame apsFrame = new ZigBeeApsFrame();
        apsFrame.setDestinationAddress(46946);
        apsFrame.setDestinationEndpoint(0);
        apsFrame.setDestinationIeeeAddress(new IeeeAddress("000D6F00057CF7C6"));
        apsFrame.setCluster(32774);
        apsFrame.setAddressMode(ZigBeeNwkAddressMode.DEVICE);
        apsFrame.setRadius(31);
        apsFrame.setApsCounter(42);
        apsFrame.setAckRequest(true);
        apsFrame.setPayload(new int[] { 0x00, 0x00, 0x2E, 0x5B, 0x01, 0x01 });

        System.out.println(command);
        System.out.println(apsFrame);

        dongle.sendCommand(1, apsFrame);
        assertEquals(1, commandCapture.getAllValues().size());
        XBeeTransmitRequestExplicitCommand sentCommand = (XBeeTransmitRequestExplicitCommand) commandCapture.getValue();
        sentCommand.setFrameId(32);
        System.out.println(sentCommand);

        int[] payload = new int[] { 0, 26, 17, 32, 0, 13, 111, 0, 5, 124, 247, 198, 183, 98, 0, 0, 128, 6, 0, 0, 0, 0,
                0, 0, 46, 91, 1, 1, 234 };

        int[] output = sentCommand.serialize();
        assertTrue(Arrays.equals(payload, output));
    }

}

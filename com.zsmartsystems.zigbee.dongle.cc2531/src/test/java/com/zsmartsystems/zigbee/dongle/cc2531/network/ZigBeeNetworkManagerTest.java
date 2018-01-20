/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.cc2531.network;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.ZToolPacket;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeNetworkManagerTest {
    protected ArgumentCaptor<ZToolPacket> argumentPacket;
    protected ArgumentCaptor<SynchronousCommandListener> argumentListener;
    protected ArgumentCaptor<Long> argumentTimeout;

    private CommandInterface getCommandInterface() {
        CommandInterface commandInterface = Mockito.mock(CommandInterface.class);
        argumentPacket = ArgumentCaptor.forClass(ZToolPacket.class);
        argumentListener = ArgumentCaptor.forClass(SynchronousCommandListener.class);
        argumentTimeout = ArgumentCaptor.forClass(Long.class);
        try {
            Mockito.doNothing().when(commandInterface).sendSynchronousCommand(argumentPacket.capture(),
                    argumentListener.capture(), argumentTimeout.capture());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return commandInterface;
    }

    @Test
    public void dongleSetChannel() {
        CommandInterface commandInterface = getCommandInterface();
        ZigBeeNetworkManager networkManager = new ZigBeeNetworkManager(commandInterface, null, 0);
        networkManager.setRetryConfiguration(1, 0);

        Method privateMethod;
        try {
            privateMethod = ZigBeeNetworkManager.class.getDeclaredMethod("dongleSetChannel",
                    new Class[] { int[].class });
            privateMethod.setAccessible(true);

            // Channel 11
            privateMethod.invoke(networkManager, new int[] { 0x00, 0x08, 0x00, 0x00 });
            ZToolPacket packet = argumentPacket.getAllValues().get(0);
            assertTrue(Arrays.equals(new int[] { 254, 6, 38, 5, 132, 0x04, 0x00, 0x08, 0x00, 0x00, 173 },
                    packet.getPacket()));

            // Check channel 11 is set even when incorrect data
            privateMethod.invoke(networkManager, new int[] { 0x01, 0x08, 0x00, 0x00 });
            packet = argumentPacket.getAllValues().get(1);
            assertTrue(Arrays.equals(new int[] { 254, 6, 38, 5, 132, 0x04, 0x00, 0x08, 0x00, 0x00, 173 },
                    packet.getPacket()));

            // Check channel 11 is set when no channels are set
            privateMethod.invoke(networkManager, new int[] { 0x00, 0x00, 0x00, 0x00 });
            packet = argumentPacket.getAllValues().get(2);
            assertTrue(Arrays.equals(new int[] { 254, 6, 38, 5, 132, 0x04, 0x00, 0x08, 0x00, 0x00, 173 },
                    packet.getPacket()));

            // Check a number of channels are set
            privateMethod.invoke(networkManager, new int[] { 0x00, 0xff, 0x00, 0x00 });
            packet = argumentPacket.getAllValues().get(3);
            assertTrue(Arrays.equals(new int[] { 254, 6, 38, 5, 132, 0x04, 0x00, 0xf8, 0x00, 0x00, 0x5D },
                    packet.getPacket()));

            networkManager.setZigBeeChannel(12);
            packet = argumentPacket.getAllValues().get(4);
            assertTrue(Arrays.equals(new int[] { 254, 6, 38, 5, 132, 0x04, 0x00, 0x10, 0x00, 0x00, 0xB5 },
                    packet.getPacket()));
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void buildChannelMask() {
        ZigBeeNetworkManager networkManager = new ZigBeeNetworkManager(null, null, 0);

        int[] mask;
        Method privateMethod;
        try {
            privateMethod = ZigBeeNetworkManager.class.getDeclaredMethod("buildChannelMask", new Class[] { int.class });
            privateMethod.setAccessible(true);

            mask = (int[]) privateMethod.invoke(networkManager, -1);
            assertTrue(Arrays.equals(new int[] { 0x00, 0x00, 0x00, 0x00 }, mask));
            mask = (int[]) privateMethod.invoke(networkManager, 32);
            assertTrue(Arrays.equals(new int[] { 0x00, 0x00, 0x00, 0x00 }, mask));
            mask = (int[]) privateMethod.invoke(networkManager, 11);
            assertTrue(Arrays.equals(new int[] { 0x00, 0x08, 0x00, 0x00 }, mask));
            mask = (int[]) privateMethod.invoke(networkManager, 27);
            assertTrue(Arrays.equals(new int[] { 0x00, 0x00, 0x00, 0x08 }, mask));

        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}

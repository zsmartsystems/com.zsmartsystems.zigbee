/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.telegesis.internal;

import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisAckMessageEvent;
import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisAddressResponseEvent;
import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisDeviceJoinedNetworkEvent;
import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisDeviceLeftNetworkEvent;
import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisEndDeviceAnnounceEvent;
import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisEvent;
import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisMobileDeviceAnnounceEvent;
import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisNackMessageEvent;
import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisNetworkJoinedEvent;
import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisNetworkLeftEvent;
import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisNetworkLostEvent;
import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisReceiveBroadcastEvent;
import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisReceiveMessageEvent;
import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisReceiveMulticastEvent;
import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisReceiveUnicastEvent;
import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisRouteRecordMessageEvent;
import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisRouterAnnounceEvent;
import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisSleepyDeviceAnnounceEvent;
import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Helper factory class to create Telegesis event classes.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class TelegesisEventFactory {
    private final static Logger logger = LoggerFactory.getLogger(TelegesisEventFactory.class);

    private static Map<Integer, Class<?>> events = new ConcurrentHashMap<Integer, Class<?>>();

    static {
        events.put(0x91AEACF6, TelegesisNetworkLeftEvent.class);
        events.put(0x91AEDEF9, TelegesisNetworkLostEvent.class);
        events.put(0xBA9587C2, TelegesisDeviceJoinedNetworkEvent.class);
        events.put(0xE70293DF, TelegesisAddressResponseEvent.class);
        events.put(0x00000A41, TelegesisRouteRecordMessageEvent.class);
        events.put(0x00000AFA, TelegesisReceiveMessageEvent.class);
        events.put(0x000107EC, TelegesisMobileDeviceAnnounceEvent.class);
        events.put(0x000107F2, TelegesisSleepyDeviceAnnounceEvent.class);
        events.put(0x000107F9, TelegesisEndDeviceAnnounceEvent.class);
        events.put(0x00010804, TelegesisRouterAnnounceEvent.class);
        events.put(0x000121E9, TelegesisAckMessageEvent.class);
        events.put(0x00231B85, TelegesisNackMessageEvent.class);
        events.put(0x002472ED, TelegesisNetworkJoinedEvent.class);
        events.put(0x04C66D81, TelegesisReceiveBroadcastEvent.class);
        events.put(0x04C66D8C, TelegesisReceiveMulticastEvent.class);
        events.put(0x04C66D94, TelegesisReceiveUnicastEvent.class);
        events.put(0x06E4B0D7, TelegesisDeviceLeftNetworkEvent.class);
    }

    public static TelegesisEvent getTelegesisFrame(int[] data) {
        // Create the hash of the prompt
        int hash = 0;
        int multiplier = 1;
        for (int value : data) {
            if (value == '\n' || value == ':' || value == '=') {
                break;
            }

            hash += (value & 0xff) * multiplier;
            int shifted = multiplier << 5;
            multiplier = shifted - multiplier;
        }

        Class<?> telegesisClass = events.get(hash);
        if (telegesisClass == null) {
            return null;
        }

        Constructor<?> ctor;
        try {
            ctor = telegesisClass.getConstructor();
            TelegesisEvent telegesisEvent = (TelegesisEvent) ctor.newInstance();
            telegesisEvent.deserialize(data);
            return telegesisEvent;
        } catch (Exception e) {
            logger.debug("Error creating instance of Telegesis event", e);
        }

        return null;
    }
}

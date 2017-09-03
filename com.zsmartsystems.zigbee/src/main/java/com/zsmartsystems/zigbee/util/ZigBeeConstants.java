/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.util;

/**
 * Created by tlaukkan on 7/3/2016.
 */
public class ZigBeeConstants {
    /**
     * Broadcast address.
     */
    public static final byte BROADCAST_ADDRESS = 0x0F;

    /**
     * Unicast address.
     */
    public static final byte UNICAST_ADRESS = 0x02;

    public static final int BROADCAST = 0xffff;
    public static final int ZNET_BROADCAST = 0xfffe;
    public static final int ZCZR_BROADCAST = 0xfffc;
}

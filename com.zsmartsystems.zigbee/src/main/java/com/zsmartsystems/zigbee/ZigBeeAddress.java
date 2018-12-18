/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee;

/**
 * Defines an abstract ZigBee address. All addresses must provide a 16 bit network address.
 * <p>
 * Addresses in the range 0x0000 to 0xfff7 are used for node addresses while
 * 0xfff8 to 0xffff are used for broadcast addresses.
 *
 * @author Tommi S.E. Laukkanen
 * @author Chris Jackson
 */
public abstract class ZigBeeAddress implements Comparable<ZigBeeAddress> {
    /**
     * Gets the network address for this address.
     *
     * @return network address as int
     */
    public abstract int getAddress();

    /**
     * Sets the network address for this address
     *
     * @param address the network address as int
     */
    public abstract void setAddress(final int address);

    /**
     * Check whether this address is ZigBee group.
     *
     * @return TRUE if this is ZigBee group.
     */
    public abstract boolean isGroup();
}

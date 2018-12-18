/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee;

import java.util.Objects;

/**
 * ZigBee group address
 *
 * @author Tommi S.E. Laukkanen
 * @author Chris Jackson
 */
public class ZigBeeGroupAddress extends ZigBeeAddress {

    /**
     * The group ID.
     */
    private int groupId;

    /**
     * The group label.
     */
    private String label;

    /**
     * Default constructor.
     */
    public ZigBeeGroupAddress() {
    }

    /**
     * Constructor which sets group ID.
     *
     * @param groupId the group ID
     */
    public ZigBeeGroupAddress(final int groupId) {
        this.groupId = groupId;
    }

    /**
     * Constructor which sets group ID and label.
     *
     * @param groupId the group ID
     * @param label the group label
     */
    public ZigBeeGroupAddress(final int groupId, final String label) {
        this.groupId = groupId;
        this.label = label;
    }

    @Override
    public int getAddress() {
        return getGroupId();
    }

    @Override
    public void setAddress(final int address) {
        setGroupId(address);
    }

    /**
     * Gets group ID.
     *
     * @return the group ID.
     */
    public int getGroupId() {
        return groupId;
    }

    /**
     * Sets group ID.
     *
     * @param groupId the group ID
     */
    public void setGroupId(final int groupId) {
        this.groupId = groupId;
    }

    /**
     * Gets group label.
     *
     * @return the group label
     */
    public String getLabel() {
        return label;
    }

    /**
     * Sets group name.
     *
     * @param label the group label
     */
    public void setLabel(final String label) {
        this.label = label;
    }

    @Override
    public boolean isGroup() {
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId);
    }

    @Override
    public int compareTo(ZigBeeAddress that) {
        if (this == that) {
            return 0;
        }

        ZigBeeGroupAddress thatAddr = (ZigBeeGroupAddress) that;

        if (thatAddr.getGroupId() == getGroupId()) {
            return 0;
        }

        return (thatAddr.getGroupId() < getGroupId()) ? -1 : 1;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!ZigBeeGroupAddress.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final ZigBeeGroupAddress other = (ZigBeeGroupAddress) obj;
        return other.getGroupId() == getGroupId();
    }

    @Override
    public String toString() {
        return "ZigBeeGroupAddress [groupId=" + groupId + ", label=" + label + "]";
    }
}
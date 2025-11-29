/**
 * Copyright (c) 2016-2025 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.groups;

import java.util.HashSet;
import java.util.Set;

/**
 * ZigBee group DAO definition. This maintains the group ID and label, along with the group members.
 *
 * @author Chris Jackson
 */
public class ZigBeeGroupDao {
    /**
     * The group ID.
     */
    private int groupId;

    /**
     * The group label.
     */
    private String label;

    /**
     * Set of members of this group
     */
    private final Set<ZigBeeGroupMember> members = new HashSet<>();

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
     * Sets group label.
     *
     * @param label the group label. Note that label is limited to 16 characters long.
     */
    public void setLabel(final String label) {
        this.label = label;
    }

    /**
     * Gets the set of {@link ZigBeeGroupMember}s that constitute the members of the group.
     * <p>
     * This method will return all configured addresses even if the device is unknown by the framework.
     *
     * @return the set of {@link ZigBeeGroupMember}s that constitute the members of the group
     */
    public Set<ZigBeeGroupMember> getMemberAddresses() {
        return members;
    }

    /**
     * Sets the groups members
     *
     * @param members the Set of {@link ZigBeeGroupMember}s
     */
    public void setMemberAddresses(Set<ZigBeeGroupMember> members) {
        this.members.addAll(members);
    }
}

/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.groups;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.zsmartsystems.zigbee.ZigBeeStatus;

/**
 * A helper class to hold the responses from a group request on an endpoint
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeGroupResponse {
    private ZigBeeStatus status;
    private Integer capacity;

    private Map<Integer, String> groups = new ConcurrentHashMap<>();

    public ZigBeeStatus getStatus() {
        return status;
    }

    public void setStatus(ZigBeeStatus status) {
        this.status = status;
    }

    /**
     * Returns the list of groups as a map of group ID and name
     *
     * @return
     */
    public Map<Integer, String> getGroups() {
        return groups;
    }

    public void setGroups(Map<Integer, String> groups) {
        this.groups = groups;
    }

    public void addGroup(int groupId) {
        groups.put(groupId, "");
    }

    public void addGroup(int groupId, String label) {
        groups.put(groupId, label);
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getCapacity() {
        return capacity;
    }

}

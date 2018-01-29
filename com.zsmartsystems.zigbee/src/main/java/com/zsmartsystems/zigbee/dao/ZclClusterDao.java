/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.zcl.ZclAttribute;

/**
 * This class provides a clean class to hold a data object for serialisation of a {@link ZigBeeEndpoint}
 *
 * @author Chris Jackson
 *
 */
public class ZclClusterDao {
    private String label;

    private int clusterId;

    private boolean isClient;

    private Map<Integer, ZclAttribute> attributes;

    private Set<Integer> supportedCommandsReceived;

    private Set<Integer> supportedCommandsGenerated;

    private Set<Integer> supportedAttributes;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setAttributes(Map<Integer, ZclAttribute> attributes) {
        this.attributes = new HashMap<Integer, ZclAttribute>(attributes);
    }

    public void setSupportedCommandsReceived(Set<Integer> supportedCommandsReceived) {
        this.supportedCommandsReceived = supportedCommandsReceived;
    }

    public void setSupportedCommandsGenerated(Set<Integer> supportedCommandsGenerated) {
        this.supportedCommandsGenerated = supportedCommandsGenerated;
    }

    public void setSupportedAttributes(Set<Integer> supportedAttributes) {
        this.supportedAttributes = supportedAttributes;
    }

    public void setClusterId(int clusterId) {
        this.clusterId = clusterId;
    }

    public void setClient(boolean isClient) {
        this.isClient = isClient;
    }

    public int getClusterId() {
        return clusterId;
    }

    public boolean getClient() {
        return isClient;
    }

    public Set<Integer> getSupportedAttributes() {
        return supportedAttributes;
    }

    public Map<Integer, ZclAttribute> getAttributes() {
        return attributes;
    }

    public Set<Integer> getSupportedCommandsGenerated() {
        return supportedCommandsGenerated;
    }

    public Set<Integer> getSupportedCommandsReceived() {
        return supportedCommandsReceived;
    }

}

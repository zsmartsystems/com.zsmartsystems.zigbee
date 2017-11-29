/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee;

/**
 * Enumeration of ZigBee profile types
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 *
 * @author Chris Jackson
 */
public enum ZigBeeProfileType {
    HOME_AUTOMATION(0x0104, "Home Automation");

    private final int profileId;
    private final String label;

    ZigBeeProfileType(final int profileId, final String label) {
        this.profileId = profileId;
        this.label = label;
    }

    public int getId() {
        return profileId;
    }

    public String getLabel() {
        return label;
    }

    public String toString() {
        return label;
    }

}

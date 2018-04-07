/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee;

import java.util.Map;
import java.util.HashMap;

import javax.annotation.Generated;

/**
 * Enumeration of ZigBee profile types
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 *
 * @author Chris Jackson
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZclProtocolCodeGenerator", date = "2018-04-07T17:43:10Z")
public enum ZigBeeProfileType {
    UNKNOWN(-1, "Unknown Profile"),
    ZIGBEE_HOME_AUTOMATION(0x0104, "ZigBee Home Automation"),
    ZIGBEE_SMART_ENERGY(0x0109, "ZigBee Smart Energy"),
    ZIGBEE_LIGHT_LINK(0xC05E, "ZigBee Light Link"),
    MANUFACTURER_TELEGESIS(0xC091, "Manufacturer Telegesis"),
    MANUFACTURER_DIGI(0xC105, "Manufacturer Digi");

    /*
     * The ZigBee profile ID
     */
    private final int profileId;

    /*
     * The ZigBee profile label
     */
    private final String label;

    /**
     * Map containing the link of profile type value to the enum
     */
    private static Map<Integer, ZigBeeProfileType> map = null;

    static {
        map = new HashMap<Integer, ZigBeeProfileType>();
        for (ZigBeeProfileType profileType : values()) {
            map.put(profileType.profileId, profileType);
        }
    }

    ZigBeeProfileType(final int profileId, final String label) {
        this.profileId = profileId;
        this.label = label;
    }

    /*
     * Get the ZigBee profile ID
     *
     * @ return the profile ID
     */
    public int getId() {
        return profileId;
    }

    /*
     * Get the ZigBee profile label
     *
     * @ return the profile label
     */
    public String getLabel() {
        return label;
    }

    /**
     * Get a {@link ZigBeeProfileType} from an integer
     *
     * @param profileTypeValue integer value defining the profile type
     * @return {@link ZigBeeProfileType} or {@link #UNKNOWN} if the value could not be converted
     */
    public static ZigBeeProfileType getProfileType(int profileTypeValue) {
        if (map.get(profileTypeValue) == null) {
            return UNKNOWN;
        }
        return map.get(profileTypeValue);
    }
}

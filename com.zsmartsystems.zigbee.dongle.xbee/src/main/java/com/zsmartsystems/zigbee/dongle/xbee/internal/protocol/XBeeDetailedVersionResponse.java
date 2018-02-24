/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.xbee.internal.protocol;


/**
 * Class to implement the XBee command <b>Detailed Version</b>.
 * <p>
 * Shows detailed version information, device type, time stamp for the build, Ember stack
 * version, and bootloader version.
		
 * <p>
 * This class provides methods for processing XBee API commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class XBeeDetailedVersionResponse extends XBeeFrame implements XBeeResponse {
    /**
     * Response field
     */
    private Integer frameId;

    /**
     * Response field
     * 
				
     */
    private int[] versionInfo;

    /**
     *
     * @return the frameId as {@link Integer}
     */
    public Integer getFrameId() {
        return frameId;
    }

    /**
     * 
				
     *
     * @return the versionInfo as {@link int[]}
     */
    public int[] getVersionInfo() {
        return versionInfo;
    }


    @Override
    public void deserialize(int[] incomingData) {
        initialiseDeserializer(incomingData);

        // Deserialize the fields for the response

        // Deserialize field "Frame ID"
        frameId = deserializeInt8();
        deserializeAtCommand();

        // Deserialize field "Version Info"
        versionInfo = deserializeData();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(387);
        builder.append("XBeeDetailedVersionResponse [frameId=");
        builder.append(frameId);
        builder.append(", versionInfo=");
        if (versionInfo == null) {
            builder.append("null");
        } else {
            for (int cnt = 0; cnt < versionInfo.length; cnt++) {
                if (cnt > 0) {
                    builder.append(' ');
                }
                builder.append(String.format("%02X", versionInfo[cnt]));
            }
        }
        builder.append(']');
        return builder.toString();
    }
}

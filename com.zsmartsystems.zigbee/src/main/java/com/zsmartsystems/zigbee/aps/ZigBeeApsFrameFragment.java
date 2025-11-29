/**
 * Copyright (c) 2016-2025 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.aps;

/**
 * Implements a {@link ZigBeeApsFrame} that returns part of the payload based on a fragmented transfer.
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeApsFrameFragment extends ZigBeeApsFrame {
    /**
     * The number of this fragment
     */
    private int fragmentNumber;

    /**
     * Creates a fragment
     *
     * @param fragmentNumber the fragment number
     */
    public ZigBeeApsFrameFragment(int fragmentNumber) {
        this.setFragmentNumber(fragmentNumber);
    }

    /**
     * Gets the fragment number
     *
     * @return the fragment number
     */
    public int getFragmentNumber() {
        return fragmentNumber;
    }

    /**
     * Sets the fragment number
     *
     * @param fragmentNumber the fragment number
     */
    public void setFragmentNumber(int fragmentNumber) {
        this.fragmentNumber = fragmentNumber;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(184);
        builder.append("ZigBeeApsFrameFragment [sourceAddress=");
        builder.append(super.getSourceAddress());
        builder.append('/');
        builder.append(super.getSourceEndpoint());
        builder.append(", destinationAddress=");
        builder.append(super.getDestinationAddress());
        builder.append('/');
        builder.append(super.getDestinationEndpoint());
        builder.append(", fragment=");
        builder.append(fragmentNumber);
        builder.append('/');
        builder.append(super.getFragmentTotal());
        builder.append(String.format(", profile=%04X", super.getProfile()));
        builder.append(String.format(", cluster=%04X", super.getCluster()));
        builder.append(", addressMode=");
        builder.append(super.getAddressMode());
        builder.append(", radius=");
        builder.append(super.getRadius());
        builder.append(", apsSecurity=");
        builder.append(super.getSecurityEnabled());
        builder.append(String.format(", apsCounter=%02X", super.getApsCounter()));
        builder.append(", payload=");

        if (getPayload() != null) {
            for (int c = 0; c < getPayload().length; c++) {
                if (c != 0) {
                    builder.append(' ');
                }
                builder.append(String.format("%02X", getPayload()[c]));
            }
        }

        builder.append(']');
        return builder.toString();
    }
}

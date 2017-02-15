/**
 * Copyright (c) 2014-2017 by the respective copyright holders.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp.structure;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to implement the Ember Structure <b>EmberZigbeeNetwork</b>.
 * <p>
 * The parameters of a ZigBee network.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class EmberZigbeeNetwork {

    /**
     * The 802.15.4 channel associated with the network.
     * <p>
     * EZSP type is <i>uint8_t</i> - Java type is {@link int}
     */
    private int channel;

    /**
     * The network's PAN identifier
     * <p>
     * EZSP type is <i>uint16_t</i> - Java type is {@link int}
     */
    private int panId;

    /**
     * The network's extended PAN identifier.
     * <p>
     * EZSP type is <i>uint8_t[8]</i> - Java type is {@link int[]}
     */
    private int[] extendedPanId;

    /**
     * Whether the network is allowing MAC associations.
     * <p>
     * EZSP type is <i>bool</i> - Java type is {@link boolean}
     */
    private boolean allowingJoin;

    /**
     * The Stack Profile associated with the network.
     * <p>
     * EZSP type is <i>uint8_t</i> - Java type is {@link int}
     */
    private int stackProfile;

    /**
     * The instance of the Network.
     * <p>
     * EZSP type is <i>uint8_t</i> - Java type is {@link int}
     */
    private int nwkUpdateId;

    /**
     * The 802.15.4 channel associated with the network.
     * <p>
     * EZSP type is <i>uint8_t</i> - Java type is {@link int}
     *
     * @return the current channel as {@link int}
     */
    public int getChannel() {
        return channel;
    }

    /**
     * The 802.15.4 channel associated with the network.
     *
     * @param channel the channel to set as {@link int}
     */
    public void setChannel(int channel) {
        this.channel = channel;
    }

    /**
     * The network's PAN identifier
     * <p>
     * EZSP type is <i>uint16_t</i> - Java type is {@link int}
     *
     * @return the current panId as {@link int}
     */
    public int getPanId() {
        return panId;
    }

    /**
     * The network's PAN identifier
     *
     * @param panId the panId to set as {@link int}
     */
    public void setPanId(int panId) {
        this.panId = panId;
    }

    /**
     * The network's extended PAN identifier.
     * <p>
     * EZSP type is <i>uint8_t[8]</i> - Java type is {@link int[]}
     *
     * @return the current extendedPanId as {@link int[]}
     */
    public int[] getExtendedPanId() {
        return extendedPanId;
    }

    /**
     * The network's extended PAN identifier.
     *
     * @param extendedPanId the extendedPanId to set as {@link int[]}
     */
    public void setExtendedPanId(int[] extendedPanId) {
        this.extendedPanId = extendedPanId;
    }

    /**
     * Whether the network is allowing MAC associations.
     * <p>
     * EZSP type is <i>bool</i> - Java type is {@link boolean}
     *
     * @return the current allowingJoin as {@link boolean}
     */
    public boolean getAllowingJoin() {
        return allowingJoin;
    }

    /**
     * Whether the network is allowing MAC associations.
     *
     * @param allowingJoin the allowingJoin to set as {@link boolean}
     */
    public void setAllowingJoin(boolean allowingJoin) {
        this.allowingJoin = allowingJoin;
    }

    /**
     * The Stack Profile associated with the network.
     * <p>
     * EZSP type is <i>uint8_t</i> - Java type is {@link int}
     *
     * @return the current stackProfile as {@link int}
     */
    public int getStackProfile() {
        return stackProfile;
    }

    /**
     * The Stack Profile associated with the network.
     *
     * @param stackProfile the stackProfile to set as {@link int}
     */
    public void setStackProfile(int stackProfile) {
        this.stackProfile = stackProfile;
    }

    /**
     * The instance of the Network.
     * <p>
     * EZSP type is <i>uint8_t</i> - Java type is {@link int}
     *
     * @return the current nwkUpdateId as {@link int}
     */
    public int getNwkUpdateId() {
        return nwkUpdateId;
    }

    /**
     * The instance of the Network.
     *
     * @param nwkUpdateId the nwkUpdateId to set as {@link int}
     */
    public void setNwkUpdateId(int nwkUpdateId) {
        this.nwkUpdateId = nwkUpdateId;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("EmberZigbeeNetwork [channel=");
        builder.append(channel);
        builder.append(", panId=");
        builder.append(panId);
        builder.append(", extendedPanId=");
        builder.append(extendedPanId);
        builder.append(", allowingJoin=");
        builder.append(allowingJoin);
        builder.append(", stackProfile=");
        builder.append(stackProfile);
        builder.append(", nwkUpdateId=");
        builder.append(nwkUpdateId);
        builder.append("]");
        return builder.toString();
    }
}

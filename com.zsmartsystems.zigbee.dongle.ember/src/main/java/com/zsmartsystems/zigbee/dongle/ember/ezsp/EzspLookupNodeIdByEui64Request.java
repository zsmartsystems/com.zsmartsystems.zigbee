/**
 * Copyright (c) 2014-2017 by the respective copyright holders.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.serializer.EzspSerializer;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to implement the Ember EZSP command <b>lookupNodeIdByEui64</b>.
 * <p>
 * Returns the node ID that corresponds to the specified EUI64. The node ID is found by searching
 * through all stack tables for the specified EUI64.
 * <p>
 * This class provides methods for processing EZSP commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class EzspLookupNodeIdByEui64Request extends EzspFrameRequest {
    private static final Logger logger = LoggerFactory.getLogger(EzspLookupNodeIdByEui64Request.class);

    /**
     * The EUI64 of the node to look up.
     * <p>
     * EZSP type is <i>EmberEUI64</i> - Java type is {@link IeeeAddress}
     */
    private IeeeAddress eui64;

    private EzspSerializer serializer;

    /**
     * Request constructor
     */
    public EzspLookupNodeIdByEui64Request() {
        serializer = new EzspSerializer();
    }

    /**
     * The EUI64 of the node to look up.
     * <p>
     * EZSP type is <i>EmberEUI64</i> - Java type is {@link IeeeAddress}
     *
     * @return the current eui64 as {@link IeeeAddress}
     */
    public IeeeAddress getEui64() {
        return eui64;
    }

    /**
     * The EUI64 of the node to look up.
     *
     * @param eui64 the eui64 to set as {@link IeeeAddress}
     */
    public void setEui64(IeeeAddress eui64) {
        this.eui64 = eui64;
    }

    @Override
    public int[] serialize() {
        serializer.serializeEmberEui64(eui64);
        return serializer.getPayload();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("EzspLookupNodeIdByEui64Request [eui64=");
        builder.append(eui64);
        builder.append("]");
        return builder.toString();
    }
}

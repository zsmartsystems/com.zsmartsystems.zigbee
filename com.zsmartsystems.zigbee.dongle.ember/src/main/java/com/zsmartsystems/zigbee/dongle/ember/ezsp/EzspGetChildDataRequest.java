/**
 * Copyright (c) 2014-2017 by the respective copyright holders.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.serializer.EzspSerializer;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to implement the Ember EZSP command <b>getChildData</b>.
 * <p>
 * Returns information about a child of the local node.
 * <p>
 * This class provides methods for processing EZSP commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class EzspGetChildDataRequest extends EzspFrameRequest {
    private static final Logger logger = LoggerFactory.getLogger(EzspGetChildDataRequest.class);

    /**
     * The index of the child of interest in the child table. Possible indexes range from zero to
     * EMBER_CHILD_TABLE_SIZE.
     * <p>
     * EZSP type is <i>uint8_t</i> - Java type is {@link int}
     */
    private int index;

    private EzspSerializer serializer;

    /**
     * Request constructor
     */
    public EzspGetChildDataRequest() {
        serializer = new EzspSerializer();
    }

    /**
     * The index of the child of interest in the child table. Possible indexes range from zero to
     * EMBER_CHILD_TABLE_SIZE.
     * <p>
     * EZSP type is <i>uint8_t</i> - Java type is {@link int}
     *
     * @return the current index as {@link int}
     */
    public int getIndex() {
        return index;
    }

    /**
     * The index of the child of interest in the child table. Possible indexes range from zero to
     * EMBER_CHILD_TABLE_SIZE.
     *
     * @param index the index to set as {@link int}
     */
    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public int[] serialize() {
        serializer.serializeUInt8(index);
        return serializer.getPayload();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("EzspGetChildDataRequest [index=");
        builder.append(index);
        builder.append("]");
        return builder.toString();
    }
}

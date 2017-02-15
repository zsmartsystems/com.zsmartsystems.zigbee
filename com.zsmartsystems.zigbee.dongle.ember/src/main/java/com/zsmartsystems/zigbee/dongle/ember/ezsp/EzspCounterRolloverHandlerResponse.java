/**
 * Copyright (c) 2014-2017 by the respective copyright holders.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.serializer.EzspDeserializer;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to implement the Ember EZSP command <b>counterRolloverHandler</b>.
 * <p>
 * This call is fired when a counter exceeds its threshold.
 * <p>
 * This class provides methods for processing EZSP commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class EzspCounterRolloverHandlerResponse extends EzspFrameResponse {
    private static final Logger logger = LoggerFactory.getLogger(EzspCounterRolloverHandlerResponse.class);

    /**
     * Type of Counter.
     * <p>
     * EZSP type is <i>EmberCounterType</i> - Java type is {@link int}
     */
    private int type;

    /**
     * Response and Handler constructor
     */
    public EzspCounterRolloverHandlerResponse(int[] inputBuffer) {
        super(inputBuffer);
    }

    /**
     * Type of Counter.
     * <p>
     * EZSP type is <i>EmberCounterType</i> - Java type is {@link int}
     *
     * @return the current type as {@link int}
     */
    public int getType() {
        return type;
    }

    /**
     * Type of Counter.
     *
     * @param type the type to set as {@link int}
     */
    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("EzspCounterRolloverHandlerResponse [type=");
        builder.append(type);
        builder.append("]");
        return builder.toString();
    }
}

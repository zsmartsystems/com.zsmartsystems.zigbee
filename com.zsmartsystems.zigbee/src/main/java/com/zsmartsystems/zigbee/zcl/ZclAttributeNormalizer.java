/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * The attribute normalizer allows attribute type conversion to ensure that attribute data stored in the
 * {@link ZclAttribute} class is always of the type defined in the library. This ensures that any devices not conforming
 * to the ZCL definition of the attribute type can be normalized before updating the {@link ZclAttribute}. This in turn
 * guarantees that applications can rely on the data type.
 *
 * @author Chris Jackson
 *
 */
public class ZclAttributeNormalizer {
    /**
     * The logger
     */
    private Logger logger = LoggerFactory.getLogger(ZclAttributeNormalizer.class);

    protected Object normalizeZclData(ZclDataType dataType, Object data) {
        switch (dataType) {
            case BOOLEAN:
                if (data instanceof Integer) {
                    logger.debug("Normalizing data Integer {} to BOOLEAN", data);
                    return Boolean.valueOf(!((Integer) data).equals(0));
                }
                break;
            default:
                break;
        }
        return data;
    }
}

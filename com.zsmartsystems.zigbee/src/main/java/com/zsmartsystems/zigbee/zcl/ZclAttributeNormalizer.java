/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
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

    /**
     * Normalize ZCL data
     *
     * @param dataType The {@link ZclDataType} used for the normalised output
     * @param data the input data
     * @return the normalised output data
     */
    protected Object normalizeZclData(ZclDataType dataType, Object data) {
        try {
            switch (dataType) {
                case BOOLEAN:
                    if (data instanceof Integer) {
                        logger.debug("Normalizing data Integer {} to BOOLEAN", data);
                        return Boolean.valueOf(!((Integer) data).equals(0));
                    }
                    break;
                case UNSIGNED_8_BIT_INTEGER:
                    if (data instanceof String) {
                        logger.debug("Normalizing data String {} to UNSIGNED_8_BIT_INTEGER", data);
                        return Integer.parseInt((String) data);
                    }
                    if (data instanceof Double) {
                        logger.debug("Normalizing data Double {} to UNSIGNED_8_BIT_INTEGER", data);
                        return ((Double) data).intValue();
                    }
                    break;
                case UNSIGNED_16_BIT_INTEGER:
                    if (data instanceof Double) {
                        logger.debug("Normalizing data Double {} to UNSIGNED_16_BIT_INTEGER", data);
                        return ((Double) data).intValue();
                    }
                    break;
                case SIGNED_8_BIT_INTEGER:
                    if (data instanceof Double) {
                        logger.debug("Normalizing data Double {} to SIGNED_8_BIT_INTEGER", data);
                        return ((Double) data).intValue();
                    }
                    break;
                case SIGNED_16_BIT_INTEGER:
                    if (data instanceof Double) {
                        logger.debug("Normalizing data Double {} to SIGNED_16_BIT_INTEGER", data);
                        return ((Double) data).intValue();
                    }
                    break;
                default:
                    break;
            }
            return data;
        } catch (NumberFormatException e) {
            logger.warn("Exception normalizing data: Returning default value of {}", dataType);
            return getDefaultValue(dataType);
        }
    }

    /**
     * Gets a default value of the specified {@link ZclDataType} which is used in the event that the normalizer fails to
     * convert the data.
     *
     * @param dataType the {@link ZclDataType} to return
     * @return an {@link Object} of the {@link ZclDataType}, or null if no conversion is provided
     */
    private Object getDefaultValue(ZclDataType dataType) {
        switch (dataType) {
            case BOOLEAN:
                return Boolean.FALSE;
            case UNSIGNED_8_BIT_INTEGER:
            case UNSIGNED_16_BIT_INTEGER:
                return Integer.valueOf(0);
            default:
                return null;
        }
    }
}

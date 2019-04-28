/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.field;

import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * A class holding a {@link ZclDataType} and its corresponding value as an {@link Object}
 *
 * @author Chris Jackson
 *
 */
public class ZclDataPair {
    private ZclDataType dataType;
    private Object value;

    public ZclDataPair(ZclDataType dataType, Object value) {
        this.dataType = dataType;
        this.value = value;
    }

    /**
     * @return the dataType
     */
    public ZclDataType getDataType() {
        return dataType;
    }

    /**
     * @param dataType the dataType to set
     */
    public void setDataType(ZclDataType dataType) {
        this.dataType = dataType;
    }

    /**
     * @return the value
     */
    public Object getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ZclDataPair [dataType=" + dataType + ", value=" + value + "]";
    }

}

/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zdo.field;

/**
 * Complex Descriptor
 *
 * @author Chris Jackson
 *
 */
public class ComplexDescriptor {
    private final String manufacturerName;
    private final String modelName;
    private final String serialNumber;

    ComplexDescriptor(final String manufacturerName, final String modelName, final String serialNumber) {
        this.manufacturerName = manufacturerName;
        this.modelName = modelName;
        this.serialNumber = serialNumber;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public String getModelName() {
        return modelName;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    @Override
    public String toString() {
        return "ComplexDescriptor [manufacturerName=" + manufacturerName + ", modelName=" + modelName
                + ", serialNumber=" + serialNumber + "]";
    }
}

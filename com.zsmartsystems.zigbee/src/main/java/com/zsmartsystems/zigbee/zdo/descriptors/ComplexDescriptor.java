package com.zsmartsystems.zigbee.zdo.descriptors;

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
}

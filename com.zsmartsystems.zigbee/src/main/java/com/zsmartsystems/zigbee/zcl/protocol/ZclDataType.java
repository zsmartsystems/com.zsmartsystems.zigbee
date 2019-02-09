/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.protocol;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.ExtendedPanId;
import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.zcl.ZclStatus;
import com.zsmartsystems.zigbee.zcl.field.AttributeInformation;
import com.zsmartsystems.zigbee.zcl.field.AttributeRecord;
import com.zsmartsystems.zigbee.zcl.field.AttributeReport;
import com.zsmartsystems.zigbee.zcl.field.AttributeReportingConfigurationRecord;
import com.zsmartsystems.zigbee.zcl.field.AttributeStatusRecord;
import com.zsmartsystems.zigbee.zcl.field.ByteArray;
import com.zsmartsystems.zigbee.zcl.field.ExtendedAttributeInformation;
import com.zsmartsystems.zigbee.zcl.field.ExtensionFieldSet;
import com.zsmartsystems.zigbee.zcl.field.NeighborInformation;
import com.zsmartsystems.zigbee.zcl.field.ReadAttributeStatusRecord;
import com.zsmartsystems.zigbee.zcl.field.WriteAttributeRecord;
import com.zsmartsystems.zigbee.zcl.field.WriteAttributeStatusRecord;
import com.zsmartsystems.zigbee.zdo.ZdoStatus;
import com.zsmartsystems.zigbee.zdo.field.BindingTable;
import com.zsmartsystems.zigbee.zdo.field.ComplexDescriptor;
import com.zsmartsystems.zigbee.zdo.field.NeighborTable;
import com.zsmartsystems.zigbee.zdo.field.NodeDescriptor;
import com.zsmartsystems.zigbee.zdo.field.PowerDescriptor;
import com.zsmartsystems.zigbee.zdo.field.RoutingTable;
import com.zsmartsystems.zigbee.zdo.field.SimpleDescriptor;
import com.zsmartsystems.zigbee.zdo.field.UserDescriptor;

/**
 * Enumeration of the ZCL data types.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T19:41:47Z")
public enum ZclDataType {
    BINDING_TABLE(BindingTable.class, 0x00, false),
    BITMAP_16_BIT(Integer.class, 0x19, false),
    BITMAP_24_BIT(Integer.class, 0x1A, false),
    BITMAP_32_BIT(Integer.class, 0x1B, false),
    BITMAP_48_BIT(Integer.class, 0x1D, false),
    BITMAP_64_BIT(Integer.class, 0x1F, false),
    BITMAP_8_BIT(Integer.class, 0x18, false),
    BOOLEAN(Boolean.class, 0x10, false),
    BYTE_ARRAY(ByteArray.class, 0x00, false),
    CHARACTER_STRING(String.class, 0x42, false),
    CLUSTERID(Integer.class, 0x00, false),
    COMPLEX_DESCRIPTOR(ComplexDescriptor.class, 0x00, false),
    DATA_8_BIT(Integer.class, 0x08, false),
    ENDPOINT(Integer.class, 0x00, false),
    ENUMERATION_16_BIT(Integer.class, 0x31, false),
    ENUMERATION_8_BIT(Integer.class, 0x30, false),
    EXTENDED_PANID(ExtendedPanId.class, 0x00, false),
    FLOAT_32_BIT(Double.class, 0x39, true),
    IEEE_ADDRESS(IeeeAddress.class, 0xF0, false),
    NEIGHBOR_TABLE(NeighborTable.class, 0x00, false),
    NODE_DESCRIPTOR(NodeDescriptor.class, 0x00, false),
    NWK_ADDRESS(Integer.class, 0x00, false),
    N_X_ATTRIBUTE_IDENTIFIER(Integer.class, 0x00, false),
    N_X_ATTRIBUTE_INFORMATION(AttributeInformation.class, 0x00, false),
    N_X_ATTRIBUTE_RECORD(AttributeRecord.class, 0x00, false),
    N_X_ATTRIBUTE_REPORT(AttributeReport.class, 0x00, false),
    N_X_ATTRIBUTE_REPORTING_CONFIGURATION_RECORD(AttributeReportingConfigurationRecord.class, 0x00, false),
    N_X_ATTRIBUTE_SELECTOR(Object.class, 0x00, false),
    N_X_ATTRIBUTE_STATUS_RECORD(AttributeStatusRecord.class, 0x00, false),
    N_X_EXTENDED_ATTRIBUTE_INFORMATION(ExtendedAttributeInformation.class, 0x00, false),
    N_X_EXTENSION_FIELD_SET(ExtensionFieldSet.class, 0x00, false),
    N_X_IEEE_ADDRESS(Long.class, 0x00, false),
    N_X_NEIGHBORS_INFORMATION(NeighborInformation.class, 0x00, false),
    N_X_READ_ATTRIBUTE_STATUS_RECORD(ReadAttributeStatusRecord.class, 0x00, false),
    N_X_UNSIGNED_16_BIT_INTEGER(Integer.class, 0x00, false),
    N_X_UNSIGNED_8_BIT_INTEGER(Integer.class, 0x00, false),
    N_X_WRITE_ATTRIBUTE_RECORD(WriteAttributeRecord.class, 0x00, false),
    N_X_WRITE_ATTRIBUTE_STATUS_RECORD(WriteAttributeStatusRecord.class, 0x00, false),
    OCTET_STRING(ByteArray.class, 0x41, false),
    POWER_DESCRIPTOR(PowerDescriptor.class, 0x00, false),
    RAW_OCTET(ByteArray.class, 0x00, false),
    ROUTING_TABLE(RoutingTable.class, 0x00, false),
    SIGNED_16_BIT_INTEGER(Integer.class, 0x29, true),
    SIGNED_24_BIT_INTEGER(Integer.class, 0x2A, true),
    SIGNED_32_BIT_INTEGER(Integer.class, 0x2B, true),
    SIGNED_8_BIT_INTEGER(Integer.class, 0x28, true),
    SIMPLE_DESCRIPTOR(SimpleDescriptor.class, 0x00, false),
    UNSIGNED_16_BIT_INTEGER(Integer.class, 0x21, true),
    UNSIGNED_24_BIT_INTEGER(Integer.class, 0x22, true),
    UNSIGNED_32_BIT_INTEGER(Integer.class, 0x23, true),
    UNSIGNED_48_BIT_INTEGER(Integer.class, 0x25, true),
    UNSIGNED_8_BIT_INTEGER(Integer.class, 0x20, true),
    UNSIGNED_8_BIT_INTEGER_ARRAY(int[].class, 0x00, false),
    USER_DESCRIPTOR(UserDescriptor.class, 0x00, false),
    UTCTIME(Calendar.class, 0xE2, true),
    X_UNSIGNED_8_BIT_INTEGER(Integer.class, 0x00, false),
    ZCL_STATUS(ZclStatus.class, 0x00, false),
    ZDO_STATUS(ZdoStatus.class, 0x00, false),
    ZIGBEE_DATA_TYPE(ZclDataType.class, 0x00, false);

    private final Class<?> dataClass;
    private final int typeId;
    private final boolean analogue;
    private static Map<Integer, ZclDataType> codeTypeMapping;

    static {
        codeTypeMapping = new HashMap<Integer, ZclDataType>();
        for (ZclDataType value : values()) {
            codeTypeMapping.put(value.typeId, value);
        }
    }

    ZclDataType(final Class<?> dataClass, final int typeId, final boolean analogue) {
        this.dataClass = dataClass;
        this.typeId = typeId;
        this.analogue = analogue;
    }

    public static ZclDataType getType(int typeId) {
        return codeTypeMapping.get(typeId);
    }

    public Class<?> getDataClass() {
        return dataClass;
    }

    public int getId() {
        return typeId;
    }

    public boolean isAnalog() {
        return analogue;
    }
}

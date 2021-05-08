/**
 * Copyright (c) 2016-2021 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.protocol;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zsmartsystems.zigbee.ExtendedPanId;
import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.security.ZigBeeKey;
import com.zsmartsystems.zigbee.zcl.ZclStatus;
import com.zsmartsystems.zigbee.zcl.field.AttributeInformation;
import com.zsmartsystems.zigbee.zcl.field.AttributeRecord;
import com.zsmartsystems.zigbee.zcl.field.AttributeReport;
import com.zsmartsystems.zigbee.zcl.field.AttributeReportingConfigurationRecord;
import com.zsmartsystems.zigbee.zcl.field.AttributeReportingStatusRecord;
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
import com.zsmartsystems.zigbee.zdo.field.ParentAnnounceChildInfo;

/**
 * Enumeration of the ZCL data types
 *
 * @author Chris Jackson
 */
public enum ZclDataType {
    DATA_8_BIT(Integer.class, 0x08, false),
    DATA_16_BIT(null, 0x09, false),
    DATA_24_BIT(null, 0x0A, false),
    DATA_32_BIT(null, 0x0B, false),
    DATA_40_BIT(null, 0x0C, false),
    DATA_48_BIT(null, 0x0D, false),
    DATA_56_BIT(null, 0x0E, false),
    DATA_64_BIT(null, 0x0F, false),
    BOOLEAN(Boolean.class, 0x10, false),
    BITMAP_8_BIT(Integer.class, 0x18, false),
    BITMAP_16_BIT(Integer.class, 0x19, false),
    BITMAP_24_BIT(Integer.class, 0x1A, false),
    BITMAP_32_BIT(Integer.class, 0x1B, false),
    BITMAP_40_BIT(Long.class, 0x1C, false),
    BITMAP_48_BIT(Long.class, 0x1D, false),
    BITMAP_56_BIT(Long.class, 0x1E, false),
    BITMAP_64_BIT(Long.class, 0x1F, false),
    UNSIGNED_8_BIT_INTEGER(Integer.class, 0x20, true),
    UNSIGNED_16_BIT_INTEGER(Integer.class, 0x21, true),
    UNSIGNED_24_BIT_INTEGER(Integer.class, 0x22, true),
    UNSIGNED_32_BIT_INTEGER(Integer.class, 0x23, true),
    UNSIGNED_40_BIT_INTEGER(Long.class, 0x24, true),
    UNSIGNED_48_BIT_INTEGER(Long.class, 0x25, true),
    UNSIGNED_56_BIT_INTEGER(Long.class, 0x26, true),
    UNSIGNED_64_BIT_INTEGER(Long.class, 0x27, true),
    SIGNED_8_BIT_INTEGER(Integer.class, 0x28, true),
    SIGNED_16_BIT_INTEGER(Integer.class, 0x29, true),
    SIGNED_24_BIT_INTEGER(Integer.class, 0x2A, true),
    SIGNED_32_BIT_INTEGER(Integer.class, 0x2B, true),
    SIGNED_40_BIT_INTEGER(null, 0x2C, true),
    SIGNED_48_BIT_INTEGER(null, 0x2D, true),
    SIGNED_56_BIT_INTEGER(null, 0x2E, true),
    SIGNED_64_BIT_INTEGER(null, 0x2F, true),
    ENUMERATION_8_BIT(Integer.class, 0x30, false),
    ENUMERATION_16_BIT(Integer.class, 0x31, false),
    ENUMERATION_32_BIT(Integer.class, 0x33, false),
    FLOAT_16_BIT(null, 0x38, true),
    FLOAT_32_BIT(Double.class, 0x39, true),
    FLOAT_64_BIT(null, 0x3A, true),
    OCTET_STRING(ByteArray.class, 0x41, false),
    CHARACTER_STRING(String.class, 0x42, false),
    LONG_OCTET_STRING(ByteArray.class, 0x43, false),
    LONG_CHARACTER_STRING(null, 0x44, false),
    ORDERED_SEQUENCE_ARRAY(null, 0x48, false),
    ORDERED_SEQUENCE_STRUCTURE(List.class, 0x4C, false),
    COLLECTION_SET(null, 0x50, false),
    COLLECTION_BAG(null, 0x51, false),
    TIME_OF_DAY(null, 0xE0, true),
    DATE(null, 0xE1, true),
    UTCTIME(Calendar.class, 0xE2, true),
    CLUSTERID(Integer.class, 0xE8, false),
    ATTRIBUTEID(null, 0xE9, false),
    BACNET_OID(null, 0xEA, false),
    IEEE_ADDRESS(IeeeAddress.class, 0xF0, false),
    SECURITY_KEY(ZigBeeKey.class, 0xF1, false),

    BYTE_ARRAY(ByteArray.class, 0x00, false),
    N_X_ATTRIBUTE_IDENTIFIER(Integer.class, 0x00, false),
    N_X_ATTRIBUTE_INFORMATION(AttributeInformation.class, 0x00, false),
    N_X_ATTRIBUTE_RECORD(AttributeRecord.class, 0x00, false),
    N_X_ATTRIBUTE_REPORT(AttributeReport.class, 0x00, false),
    N_X_ATTRIBUTE_REPORTING_STATUS_RECORD(AttributeReportingStatusRecord.class, 0x00, false),
    N_X_ATTRIBUTE_REPORTING_CONFIGURATION_RECORD(AttributeReportingConfigurationRecord.class, 0x00, false),
    N_X_ATTRIBUTE_SELECTOR(Object.class, 0x00, false),
    N_X_ATTRIBUTE_STATUS_RECORD(AttributeStatusRecord.class, 0x00, false),
    N_X_EXTENDED_ATTRIBUTE_INFORMATION(ExtendedAttributeInformation.class, 0x00, false),
    N_X_EXTENSION_FIELD_SET(ExtensionFieldSet.class, 0x00, false),
    N_X_NEIGHBORS_INFORMATION(NeighborInformation.class, 0x00, false),
    N_X_READ_ATTRIBUTE_STATUS_RECORD(ReadAttributeStatusRecord.class, 0x00, false),
    N_X_UNSIGNED_8_BIT_INTEGER(Integer.class, 0x00, false),
    N_X_UNSIGNED_16_BIT_INTEGER(Integer.class, 0x00, false),
    N_X_WRITE_ATTRIBUTE_RECORD(WriteAttributeRecord.class, 0x00, false),
    N_X_WRITE_ATTRIBUTE_STATUS_RECORD(WriteAttributeStatusRecord.class, 0x00, false),
    X_UNSIGNED_8_BIT_INTEGER(Integer.class, 0x00, false),
    ZCL_STATUS(ZclStatus.class, 0x00, false),
    EXTENDED_PANID(ExtendedPanId.class, 0x00, false),
    BINDING_TABLE(BindingTable.class, 0x00, false),
    COMPLEX_DESCRIPTOR(ComplexDescriptor.class, 0x00, false),
    ENDPOINT(Integer.class, 0x00, false),
    NEIGHBOR_TABLE(NeighborTable.class, 0x00, false),
    NODE_DESCRIPTOR(NodeDescriptor.class, 0x00, false),
    NWK_ADDRESS(Integer.class, 0x00, false),
    N_X_BINDING_TABLE(BindingTable.class, 0x00, false),
    N_X_IEEE_ADDRESS(IeeeAddress.class, 0x00, false),
    POWER_DESCRIPTOR(PowerDescriptor.class, 0x00, false),
    ROUTING_TABLE(RoutingTable.class, 0x00, false),
    SIMPLE_DESCRIPTOR(SimpleDescriptor.class, 0x00, false),
    USER_DESCRIPTOR(UserDescriptor.class, 0x00, false),
    ZDO_STATUS(ZdoStatus.class, 0x00, false),
    UNSIGNED_8_BIT_INTEGER_ARRAY(int[].class, 0x00, false),
    RAW_OCTET(ByteArray.class, 0x00, false),
    ZIGBEE_DATA_TYPE(ZclDataType.class, 0x00, false),
    PARENT_ANNOUNCE_CHILD_INFO(ParentAnnounceChildInfo.class, 0x00, false);

    private final Class<?> dataClass;
    private final int typeId;
    private final boolean analogue;
    private static Map<Integer, ZclDataType> codeTypeMapping;

    static {
        codeTypeMapping = new HashMap<Integer, ZclDataType>();
        for (ZclDataType dataType : values()) {
            codeTypeMapping.put(dataType.typeId, dataType);
        }
    }

    private ZclDataType(final Class<?> dataClass, final int typeId, final boolean analogue) {
        this.dataClass = dataClass;
        this.typeId = typeId;
        this.analogue = analogue;
    }

    /**
     * Gets the {@link ZclDataType} given the ZCL defined type ID
     *
     * @param typeId the ZCL defined type ID
     * @return the {@link ZclDataType} corresponding to the type ID
     */
    public static ZclDataType getType(int typeId) {
        return codeTypeMapping.get(typeId);
    }

    /**
     * Gets the Java class used in the framework for this data type
     *
     * @return the Java class used for this data type in the framework
     */
    public Class<?> getDataClass() {
        return dataClass;
    }

    /**
     * Gets the ZCL data type as defined in the ZigBee Standard
     *
     * @return the ZCL data type
     */
    public int getId() {
        return typeId;
    }

    /**
     * Returns true if this data type is considered analogue, or false if it is discrete.
     * Analogue data types may set the change value when configuring reporting.
     *
     * @return true if this is an analogue data type
     */
    public boolean isAnalog() {
        return analogue;
    }
}

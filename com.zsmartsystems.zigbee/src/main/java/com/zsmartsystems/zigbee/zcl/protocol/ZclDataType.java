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
import com.zsmartsystems.zigbee.zcl.field.AttributeStatusRecord;
import com.zsmartsystems.zigbee.zcl.field.ByteArray;
import com.zsmartsystems.zigbee.zcl.field.ExtendedAttributeInformation;
import com.zsmartsystems.zigbee.zcl.field.ExtensionFieldSet;
import com.zsmartsystems.zigbee.zcl.field.NeighborInformation;
import com.zsmartsystems.zigbee.zcl.field.ReadAttributeStatusRecord;
import com.zsmartsystems.zigbee.zcl.field.WriteAttributeRecord;
import com.zsmartsystems.zigbee.zcl.field.WriteAttributeStatusRecord;
import com.zsmartsystems.zigbee.zcl.field.ZclArrayList;
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
 * Enumeration of the ZCL data types
 *
 * @author Chris Jackson
 */
public enum ZclDataType {
    DATA_8_BIT("8-bit data", Integer.class, 0x08, false),
    DATA_16_BIT("16-bit data", null, 0x09, false),
    DATA_24_BIT("24-bit data", null, 0x0A, false),
    DATA_32_BIT("32-bit data", null, 0x0B, false),
    DATA_40_BIT("40-bit data", null, 0x0C, false),
    DATA_48_BIT("48-bit data", null, 0x0D, false),
    DATA_56_BIT("56-bit data", null, 0x0E, false),
    DATA_64_BIT("64-bit data", null, 0x0F, false),
    BOOLEAN("Boolean", Boolean.class, 0x10, false),
    BITMAP_8_BIT("8-bit Bitmap", Integer.class, 0x18, false),
    BITMAP_16_BIT("16-bit Bitmap", Integer.class, 0x19, false),
    BITMAP_24_BIT("24-bit Bitmap", Integer.class, 0x1A, false),
    BITMAP_32_BIT("32-bit Bitmap", Integer.class, 0x1B, false),
    BITMAP_40_BIT("40-bit Bitmap", Long.class, 0x1C, false),
    BITMAP_48_BIT("48-bit Bitmap", Long.class, 0x1D, false),
    BITMAP_56_BIT("56-bit Bitmap", Long.class, 0x1E, false),
    BITMAP_64_BIT("64-bit Bitmap", Long.class, 0x1F, false),
    UNSIGNED_8_BIT_INTEGER("Unsigned 8-bit integer", Integer.class, 0x20, true),
    UNSIGNED_16_BIT_INTEGER("Unsigned 16-bit integer", Integer.class, 0x21, true),
    UNSIGNED_24_BIT_INTEGER("Unsigned 24-bit integer", Integer.class, 0x22, true),
    UNSIGNED_32_BIT_INTEGER("Unsigned 32-bit integer", Integer.class, 0x23, true),
    UNSIGNED_40_BIT_INTEGER("Unsigned 40-bit integer", Long.class, 0x24, true),
    UNSIGNED_48_BIT_INTEGER("Unsigned 48-bit integer", Long.class, 0x25, true),
    UNSIGNED_56_BIT_INTEGER("Unsigned 56-bit integer", Long.class, 0x26, true),
    UNSIGNED_64_BIT_INTEGER("Unsigned 64-bit integer", Long.class, 0x27, true),
    SIGNED_8_BIT_INTEGER("Signed 8-bit Integer", Integer.class, 0x28, true),
    SIGNED_16_BIT_INTEGER("Signed 16-bit Integer", Integer.class, 0x29, true),
    SIGNED_24_BIT_INTEGER("Signed 24-bit Integer", Integer.class, 0x2A, true),
    SIGNED_32_BIT_INTEGER("Signed 32-bit Integer", Integer.class, 0x2B, true),
    SIGNED_40_BIT_INTEGER("Signed 40-bit Integer", null, 0x2C, true),
    SIGNED_48_BIT_INTEGER("Signed 48-bit Integer", null, 0x2D, true),
    SIGNED_56_BIT_INTEGER("Signed 56-bit Integer", null, 0x2E, true),
    SIGNED_64_BIT_INTEGER("Signed 64-bit Integer", null, 0x2F, true),
    ENUMERATION_8_BIT("8-bit Enumeration", Integer.class, 0x30, false),
    ENUMERATION_16_BIT("16-bit enumeration", Integer.class, 0x31, false),
    ENUMERATION_32_BIT("32-bit enumeration", Integer.class, 0x33, false),
    FLOAT_16_BIT("Semi precision float", null, 0x38, true),
    FLOAT_32_BIT("Single precision float", Double.class, 0x39, true),
    FLOAT_64_BIT("Double precision float", null, 0x3A, true),
    OCTET_STRING("Octet string", ByteArray.class, 0x41, false),
    CHARACTER_STRING("Character String", String.class, 0x42, false),
    LONG_OCTET_STRING("Long Octet string", ByteArray.class, 0x43, false),
    LONG_CHARACTER_STRING("Long Character String", null, 0x44, false),
    ORDERED_SEQUENCE_ARRAY("ARRAY", ZclArrayList.class, 0x48, false),
    ORDERED_SEQUENCE_STRUCTURE("ORDERED_SEQUENCE_STRUCTURE", List.class, 0x4C, false),
    COLLECTION_SET("SET", null, 0x50, false),
    COLLECTION_BAG("BAG", null, 0x51, false),
    TIME_OF_DAY("Time", null, 0xE0, true),
    DATE("Date", null, 0xE1, true),
    UTCTIME("UTCTime", Calendar.class, 0xE2, true),
    CLUSTERID("ClusterId", Integer.class, 0xE8, false),
    ATTRIBUTEID("AttributeId", null, 0xE9, false),
    BACNET_OID("BACNetId", null, 0xEA, false),
    IEEE_ADDRESS("IEEE Address", IeeeAddress.class, 0xF0, false),
    SECURITY_KEY("ZigBee Key", ZigBeeKey.class, 0xF1, false),
    BYTE_ARRAY("Byte array", ByteArray.class, 0x00, false),
    N_X_ATTRIBUTE_IDENTIFIER("N X Attribute identifier", Integer.class, 0x00, false),
    N_X_ATTRIBUTE_INFORMATION("N X Attribute information", AttributeInformation.class, 0x00, false),
    N_X_ATTRIBUTE_RECORD("N X Attribute record", AttributeRecord.class, 0x00, false),
    N_X_ATTRIBUTE_REPORT("N X Attribute report", AttributeReport.class, 0x00, false),
    N_X_ATTRIBUTE_REPORTING_CONFIGURATION_RECORD("N X Attribute reporting configuration record",
            AttributeReportingConfigurationRecord.class, 0x00, false),
    N_X_ATTRIBUTE_SELECTOR("N X Attribute selector", Object.class, 0x00, false),
    N_X_ATTRIBUTE_STATUS_RECORD("N X Attribute status record", AttributeStatusRecord.class, 0x00, false),
    N_X_EXTENDED_ATTRIBUTE_INFORMATION("N x Extended Attribute Information", ExtendedAttributeInformation.class, 0x00,
            false),
    N_X_EXTENSION_FIELD_SET("N X Extension field set", ExtensionFieldSet.class, 0x00, false),
    N_X_NEIGHBORS_INFORMATION("N X Neighbors information", NeighborInformation.class, 0x00, false),
    N_X_READ_ATTRIBUTE_STATUS_RECORD("N X Read attribute status record", ReadAttributeStatusRecord.class, 0x00, false),
    N_X_UNSIGNED_8_BIT_INTEGER("N x Unsigned 8-bit Integer", Integer.class, 0x00, false),
    N_X_UNSIGNED_16_BIT_INTEGER("N X Unsigned 16-bit integer", Integer.class, 0x00, false),
    N_X_WRITE_ATTRIBUTE_RECORD("N X Write attribute record", WriteAttributeRecord.class, 0x00, false),
    N_X_WRITE_ATTRIBUTE_STATUS_RECORD("N X Write attribute status record", WriteAttributeStatusRecord.class, 0x00,
            false),
    X_UNSIGNED_8_BIT_INTEGER("X Unsigned 8-bit integer", Integer.class, 0x00, false),
    ZCL_STATUS("ZCL Status", ZclStatus.class, 0x00, false),
    EXTENDED_PANID("EXTENDED_PANID", ExtendedPanId.class, 0x00, false),
    BINDING_TABLE("Binding Table", BindingTable.class, 0x00, false),
    COMPLEX_DESCRIPTOR("Complex Descriptor", ComplexDescriptor.class, 0x00, false),
    ENDPOINT("Endpoint", Integer.class, 0x00, false),
    NEIGHBOR_TABLE("Neighbor Table", NeighborTable.class, 0x00, false),
    NODE_DESCRIPTOR("Node Descriptor", NodeDescriptor.class, 0x00, false),
    NWK_ADDRESS("NWK address", Integer.class, 0x00, false),
    N_X_BINDING_TABLE("N x Binding Table", BindingTable.class, 0x00, false),
    N_X_IEEE_ADDRESS("N X IEEE Address", IeeeAddress.class, 0x00, false),
    POWER_DESCRIPTOR("Power Descriptor", PowerDescriptor.class, 0x00, false),
    ROUTING_TABLE("Routing Table", RoutingTable.class, 0x00, false),
    SIMPLE_DESCRIPTOR("Simple Descriptor", SimpleDescriptor.class, 0x00, false),
    USER_DESCRIPTOR("User Descriptor", UserDescriptor.class, 0x00, false),
    ZDO_STATUS("ZDO Status", ZdoStatus.class, 0x00, false),
    UNSIGNED_8_BIT_INTEGER_ARRAY("Unsigned 8 bit Integer Array", int[].class, 0x00, false),
    RAW_OCTET("RAW_OCTET", ByteArray.class, 0x00, false),
    ZIGBEE_DATA_TYPE("ZigBee Data Type", ZclDataType.class, 0x00, false);

    private final String label;
    private final Class<?> dataClass;
    private final int id;
    private final boolean analogue;
    private static Map<Integer, ZclDataType> codeTypeMapping;

    static {
        codeTypeMapping = new HashMap<Integer, ZclDataType>();
        for (ZclDataType s : values()) {
            codeTypeMapping.put(s.id, s);
        }
    }

    ZclDataType(final String label, final Class<?> dataClass, final int id, final boolean analogue) {
        this.label = label;
        this.dataClass = dataClass;
        this.id = id;
        this.analogue = analogue;
    }

    public static ZclDataType getType(int id) {
        return codeTypeMapping.get(id);
    }

    /**
     * Gets the label for the data type
     *
     * @return
     * @deprecated use the enumeration
     */
    @Deprecated
    public String getLabel() {
        return label;
    }

    public Class<?> getDataClass() {
        return dataClass;
    }

    public int getId() {
        return id;
    }

    public boolean isAnalog() {
        return analogue;
    }
}

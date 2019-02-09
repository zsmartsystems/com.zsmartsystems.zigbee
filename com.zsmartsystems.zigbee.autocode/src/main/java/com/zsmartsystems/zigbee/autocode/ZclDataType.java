/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.autocode;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZclDataType {

    public static class DataTypeMap {
        public String dataClass;
        public Integer id;
        public Integer length;
        public Integer invalid;
        public boolean analogue;

        DataTypeMap(String dataClass, int id, int length, boolean analogue) {
            this(dataClass, id, length, analogue, 0);
        }

        DataTypeMap(String dataClass, int id, int length, boolean analogue, int invalid) {
            this.dataClass = dataClass;
            this.id = id;
            this.length = length;
            this.invalid = invalid;
            this.analogue = analogue;
        }
    };

    final static Map<String, DataTypeMap> dataTypeMapping;

    static {
        dataTypeMapping = new HashMap<String, DataTypeMap>();

        dataTypeMapping.put("CHARACTER_STRING", new DataTypeMap("String", 0x42, -1, false));
        dataTypeMapping.put("IEEE_ADDRESS", new DataTypeMap("IeeeAddress", 0xf0, 8, false, 0xffffffff));
        dataTypeMapping.put("EXTENDED_PANID", new DataTypeMap("Long", 0, 0, false));
        dataTypeMapping.put("NODE_DESCRIPTOR", new DataTypeMap("NodeDescriptor", 0, 0, false));
        dataTypeMapping.put("SIMPLE_DESCRIPTOR", new DataTypeMap("SimpleDescriptor", 0, 0, false));
        dataTypeMapping.put("COMPLEX_DESCRIPTOR", new DataTypeMap("ComplexDescriptor", 0, 0, false));
        dataTypeMapping.put("POWER_DESCRIPTOR", new DataTypeMap("PowerDescriptor", 0, 0, false));
        dataTypeMapping.put("USER_DESCRIPTOR", new DataTypeMap("UserDescriptor", 0, 0, false));
        dataTypeMapping.put("NEIGHBOR_TABLE", new DataTypeMap("NeighborTable", 0, 0, false));
        dataTypeMapping.put("ROUTING_TABLE", new DataTypeMap("RoutingTable", 0, 0, false));
        dataTypeMapping.put("NWK_ADDRESS", new DataTypeMap("Integer", 0, 0, false, 0xffff));
        dataTypeMapping.put("N_X_IEEE_ADDRESS", new DataTypeMap("List<Long>", 0, 0, false));
        dataTypeMapping.put("N_X_NWK_ADDRESS", new DataTypeMap("List<Integer>", 0, 0, false));
        dataTypeMapping.put("CLUSTERID", new DataTypeMap("Integer", 0, 0, false));
        dataTypeMapping.put("N_X_CLUSTERID", new DataTypeMap("List<Integer>", 0, 0, false));
        dataTypeMapping.put("ENDPOINT", new DataTypeMap("Integer", 0, 0, false));
        dataTypeMapping.put("N_X_ENDPOINT", new DataTypeMap("List<Integer>", 0, 0, false));
        dataTypeMapping.put("N_X_EXTENSION_FIELD_SET", new DataTypeMap("List<ExtensionFieldSet>", 0, 0, false));
        dataTypeMapping.put("N_X_NEIGHBORS_INFORMATION", new DataTypeMap("List<NeighborInformation>", 0, 0, false));
        dataTypeMapping.put("N_X_UNSIGNED_16_BIT_INTEGER", new DataTypeMap("List<Integer>", 0, 0, false));
        dataTypeMapping.put("UNSIGNED_8_BIT_INTEGER_ARRAY", new DataTypeMap("int[]", 0, 0, false));
        dataTypeMapping.put("X_UNSIGNED_8_BIT_INTEGER", new DataTypeMap("List<Integer>", 0, 0, false));
        dataTypeMapping.put("N_X_UNSIGNED_8_BIT_INTEGER", new DataTypeMap("List<Integer>", 0, 0, false));
        dataTypeMapping.put("N_X_ATTRIBUTE_IDENTIFIER", new DataTypeMap("List<Integer>", 0, 0, false));
        dataTypeMapping.put("N_X_READ_ATTRIBUTE_STATUS_RECORD",
                new DataTypeMap("List<ReadAttributeStatusRecord>", 0, 0, false));
        dataTypeMapping.put("N_X_WRITE_ATTRIBUTE_RECORD", new DataTypeMap("List<WriteAttributeRecord>", 0, 0, false));
        dataTypeMapping.put("N_X_WRITE_ATTRIBUTE_STATUS_RECORD",
                new DataTypeMap("List<WriteAttributeStatusRecord>", 0, 0, false));
        dataTypeMapping.put("N_X_ATTRIBUTE_REPORTING_CONFIGURATION_RECORD",
                new DataTypeMap("List<AttributeReportingConfigurationRecord>", 0, 0, false));
        dataTypeMapping.put("N_X_ATTRIBUTE_STATUS_RECORD", new DataTypeMap("List<AttributeStatusRecord>", 0, 0, false));
        dataTypeMapping.put("N_X_ATTRIBUTE_RECORD", new DataTypeMap("List<AttributeRecord>", 0, 0, false));
        dataTypeMapping.put("N_X_ATTRIBUTE_REPORT", new DataTypeMap("List<AttributeReport>", 0, 0, false));
        dataTypeMapping.put("N_X_ATTRIBUTE_INFORMATION", new DataTypeMap("List<AttributeInformation>", 0, 0, false));
        dataTypeMapping.put("N_X_ATTRIBUTE_SELECTOR", new DataTypeMap("Object", 0, 0, false));
        dataTypeMapping.put("N_X_EXTENDED_ATTRIBUTE_INFORMATION",
                new DataTypeMap("List<ExtendedAttributeInformation>", 0, 0, false));
        dataTypeMapping.put("BOOLEAN", new DataTypeMap("Boolean", 0x10, 1, false, 0xff));
        dataTypeMapping.put("SIGNED_8_BIT_INTEGER", new DataTypeMap("Integer", 0x28, 1, true, 0x80));
        dataTypeMapping.put("SIGNED_16_BIT_INTEGER", new DataTypeMap("Integer", 0x29, 2, true, 0x8000));
        dataTypeMapping.put("SIGNED_24_BIT_INTEGER", new DataTypeMap("Integer", 0x2a, 3, true, 0x800000));
        dataTypeMapping.put("SIGNED_32_BIT_INTEGER", new DataTypeMap("Integer", 0x2b, 4, true, 0x80000000));
        dataTypeMapping.put("UNSIGNED_8_BIT_INTEGER", new DataTypeMap("Integer", 0x20, 1, true, 0xff));
        dataTypeMapping.put("UNSIGNED_16_BIT_INTEGER", new DataTypeMap("Integer", 0x21, 2, true, 0xffff));
        dataTypeMapping.put("UNSIGNED_24_BIT_INTEGER", new DataTypeMap("Integer", 0x22, 3, true, 0xffffff));
        dataTypeMapping.put("UNSIGNED_32_BIT_INTEGER", new DataTypeMap("Integer", 0x23, 4, true, 0xffffffff));
        dataTypeMapping.put("UNSIGNED_40_BIT_INTEGER", new DataTypeMap("Integer", 0x24, 4, true, 0));// 0xffffffffff));
        dataTypeMapping.put("UNSIGNED_48_BIT_INTEGER", new DataTypeMap("Integer", 0x25, 6, true, 0));// 0xffffffffffff));
        dataTypeMapping.put("BITMAP_8_BIT", new DataTypeMap("Integer", 0x18, 1, false));
        dataTypeMapping.put("BITMAP_16_BIT", new DataTypeMap("Integer", 0x19, 2, false));
        dataTypeMapping.put("BITMAP_24_BIT", new DataTypeMap("Integer", 0x1a, 3, false));
        dataTypeMapping.put("BITMAP_32_BIT", new DataTypeMap("Integer", 0x1b, 4, false));
        dataTypeMapping.put("BITMAP_40_BIT", new DataTypeMap("Integer", 0x1c, 5, false));
        dataTypeMapping.put("BITMAP_48_BIT", new DataTypeMap("Integer", 0x1d, 6, false));
        dataTypeMapping.put("BITMAP_56_BIT", new DataTypeMap("Integer", 0x1e, 7, false));
        dataTypeMapping.put("BITMAP_64_BIT", new DataTypeMap("Integer", 0x1f, 8, false));
        dataTypeMapping.put("ENUMERATION_16_BIT", new DataTypeMap("Integer", 0x31, 2, false, 0xffff));
        dataTypeMapping.put("ENUMERATION_8_BIT", new DataTypeMap("Integer", 0x30, 1, false, 0xff));
        dataTypeMapping.put("DATA_8_BIT", new DataTypeMap("Integer", 0x08, 1, false));
        dataTypeMapping.put("OCTET_STRING", new DataTypeMap("ByteArray", 0x41, -1, false));
        dataTypeMapping.put("UTCTIME", new DataTypeMap("Calendar", 0xe2, 4, true, 0xffffffff));
        dataTypeMapping.put("ZDO_STATUS", new DataTypeMap("ZdoStatus", 0, 0, false));
        dataTypeMapping.put("ZCL_STATUS", new DataTypeMap("ZclStatus", 0, 0, false));
        dataTypeMapping.put("ZIGBEE_DATA_TYPE", new DataTypeMap("ZclDataType", 0, 0, false));
        dataTypeMapping.put("EXTENDED_PANID", new DataTypeMap("ExtendedPanId", 0, 0, false));
        dataTypeMapping.put("BINDING_TABLE", new DataTypeMap("BindingTable", 0, 0, false));
        dataTypeMapping.put("N_X_BINDING_TABLE", new DataTypeMap("List<BindingTable>", 0, 0, false));
        dataTypeMapping.put("BYTE_ARRAY", new DataTypeMap("ByteArray", 0, 0, false));
        dataTypeMapping.put("RAW_OCTET", new DataTypeMap("ByteArray", 0, 0, false));
        dataTypeMapping.put("IMAGE_UPGRADE_STATUS", new DataTypeMap("ImageUpgradeStatus", 0, 0, false));
    };

    public static Map<String, DataTypeMap> getDataTypeMapping() {
        return dataTypeMapping;
    }
}

package com.zsmartsystems.zigbee.zcl.protocol;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import com.zsmartsystems.zigbee.zcl.field.*;

/**
 * Code is auto-generated. Modifications may be overwritten!
 */
public enum ZclDataType {
    BITMAP_16_BIT("16-bit Bitmap",Integer.class, 0x19, false),
    BITMAP_8_BIT("8-bit Bitmap",Integer.class, 0x18, false),
    BOOLEAN("Boolean",Boolean.class, 0x10, false),
    CHARACTER_STRING("Character string",String.class, 0x42, false),
    DATA_8_BIT("8-bit data",Integer.class, 0x08, false),
    ENUMERATION_16_BIT("16-bit Enumeration",Integer.class, 0x31, false),
    ENUMERATION_8_BIT("8-bit Enumeration",Integer.class, 0x30, false),
    IEEE_ADDRESS("IEEE Address",Long.class, 0xF0, false),
    N_X_ATTRIBUTE_IDENTIFIER("N X Attribute identifier",AttributeIdentifier.class, 0x00, false),
    N_X_ATTRIBUTE_INFORMATION("N X Attribute information",AttributeInformation.class, 0x00, false),
    N_X_ATTRIBUTE_RECORD("N X Attribute record",AttributeRecord.class, 0x00, false),
    N_X_ATTRIBUTE_REPORT("N X Attribute report",AttributeReport.class, 0x00, false),
    N_X_ATTRIBUTE_REPORTING_CONFIGURATION_RECORD("N X Attribute reporting configuration record",AttributeReportingConfigurationRecord.class, 0x00, false),
    N_X_ATTRIBUTE_SELECTOR("N X Attribute selector",Object.class, 0x00, false),
    N_X_ATTRIBUTE_STATUS_RECORD("N X Attribute status record",AttributeStatusRecord.class, 0x00, false),
    N_X_EXTENSION_FIELD_SET("N X Extension field set",ExtensionFieldSet.class, 0x00, false),
    N_X_NEIGHBORS_INFORMATION("N X Neighbors information",NeighborInformation.class, 0x00, false),
    N_X_READ_ATTRIBUTE_STATUS_RECORD("N X Read attribute status record",ReadAttributeStatusRecord.class, 0x00, false),
    N_X_UNSIGNED_16_BIT_INTEGER("N X Unsigned 16-bit integer",Unsigned16BitInteger.class, 0x00, false),
    N_X_UNSIGNED_8_BIT_INTEGER("N X Unsigned 8-bit integer",Unsigned8BitInteger.class, 0x00, false),
    N_X_WRITE_ATTRIBUTE_RECORD("N X Write attribute record",WriteAttributeRecord.class, 0x00, false),
    N_X_WRITE_ATTRIBUTE_STATUS_RECORD("N X Write attribute status record",WriteAttributeStatusRecord.class, 0x00, false),
    OCTET_STRING("Octet string",String.class, 0x41, false),
    SIGNED_16_BIT_INTEGER("Signed 16-bit integer",Integer.class, 0x29, true),
    SIGNED_32_BIT_INTEGER("Signed 32-bit integer",Integer.class, 0x2B, true),
    SIGNED_8_BIT_INTEGER("Signed 8-bit integer",Integer.class, 0x28, true),
    UNSIGNED_16_BIT_INTEGER("Unsigned 16-bit integer",Integer.class, 0x21, true),
    UNSIGNED_32_BIT_INTEGER("Unsigned 32-bit integer",Integer.class, 0x23, true),
    UNSIGNED_8_BIT_INTEGER("Unsigned 8-bit integer",Integer.class, 0x20, true),
    UTCTIME("UTCTime",Calendar.class, 0xE2, true);

    private final String label;
    private final Class<?> dataClass;
    private final int id;
    private final boolean analogue;
    private static Map<Integer, ZclDataType> codeTypeMapping;

    ZclDataType(final String label, final Class<?> dataClass, final int id, final boolean analogue) {
        this.label = label;
        this.dataClass = dataClass;
        this.id = id;
        this.analogue = analogue;
    }

    private static void initMapping() {
        codeTypeMapping = new HashMap<Integer, ZclDataType>();
        for (ZclDataType s : values()) {
            codeTypeMapping.put(s.id, s);
        }
    }
    public static ZclDataType getType(int id) {
        if (codeTypeMapping == null) {
            initMapping();
        }
        return codeTypeMapping.get(id);
    }

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

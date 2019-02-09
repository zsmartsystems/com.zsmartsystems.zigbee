/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.autocode;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.zsmartsystems.zigbee.autocode.ZclDataType.DataTypeMap;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeZclDataTypeGenerator extends ZigBeeBaseClassGenerator {
    ZigBeeZclDataTypeGenerator(Set<String> types, String generatedDate) {
        try {
            this.generatedDate = generatedDate;
            generateZclDataTypeEnumeration(types);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void generateZclDataTypeEnumeration(Set<String> dataTypes) throws IOException {
        final String className = "ZclDataType";

        dataTypes.add("EXTENDED_PANID");
        dataTypes.add("UNSIGNED_8_BIT_INTEGER_ARRAY");
        dataTypes.add("ZIGBEE_DATA_TYPE");

        List<String> sortedTypes = new LinkedList<>();
        sortedTypes.addAll(dataTypes);
        Collections.sort(sortedTypes);

        final String packagePath = getPackagePath(new File(sourceRootPath), packageRoot + packageZclProtocol);
        final File packageFile = getPackageFile(packagePath);

        final PrintWriter out = getClassOut(packageFile, className);
        outputLicense(out);

        importsClear();
        out.println("package " + packageRoot + packageZclProtocol + ";");
        out.println();

        importsAdd("javax.annotation.Generated");
        importsAdd("java.util.Calendar");
        importsAdd("java.util.HashMap");
        importsAdd("java.util.Map");
        importsAdd("com.zsmartsystems.zigbee.ExtendedPanId");
        importsAdd("com.zsmartsystems.zigbee.IeeeAddress");
        importsAdd("com.zsmartsystems.zigbee.zcl.ZclStatus");
        importsAdd("com.zsmartsystems.zigbee.zcl.field.ByteArray");
        importsAdd("com.zsmartsystems.zigbee.zcl.field.ExtensionFieldSet");
        importsAdd("com.zsmartsystems.zigbee.zcl.field.AttributeInformation");
        importsAdd("com.zsmartsystems.zigbee.zcl.field.AttributeRecord");
        importsAdd("com.zsmartsystems.zigbee.zcl.field.AttributeReport");
        importsAdd("com.zsmartsystems.zigbee.zcl.field.NeighborInformation");
        importsAdd("com.zsmartsystems.zigbee.zcl.field.AttributeReportingConfigurationRecord");
        importsAdd("com.zsmartsystems.zigbee.zcl.field.AttributeStatusRecord");
        importsAdd("com.zsmartsystems.zigbee.zcl.field.ExtendedAttributeInformation");
        importsAdd("com.zsmartsystems.zigbee.zcl.field.ReadAttributeStatusRecord");
        importsAdd("com.zsmartsystems.zigbee.zcl.field.WriteAttributeRecord");
        importsAdd("com.zsmartsystems.zigbee.zcl.field.WriteAttributeStatusRecord");
        importsAdd("com.zsmartsystems.zigbee.zdo.ZdoStatus");
        importsAdd("com.zsmartsystems.zigbee.zdo.field.BindingTable");
        importsAdd("com.zsmartsystems.zigbee.zdo.field.ComplexDescriptor");
        importsAdd("com.zsmartsystems.zigbee.zdo.field.NeighborTable");
        importsAdd("com.zsmartsystems.zigbee.zdo.field.NodeDescriptor");
        importsAdd("com.zsmartsystems.zigbee.zdo.field.PowerDescriptor");
        importsAdd("com.zsmartsystems.zigbee.zdo.field.RoutingTable");
        importsAdd("com.zsmartsystems.zigbee.zdo.field.SimpleDescriptor");
        importsAdd("com.zsmartsystems.zigbee.zdo.field.UserDescriptor");

        outputImports(out);

        out.println();
        out.println("/**");
        out.println(" * Enumeration of the ZCL data types.");
        out.println(" * <p>");
        out.println(" * Code is auto-generated. Modifications may be overwritten!");
        out.println(" */");
        outputClassGenerated(out);

        out.println("public enum " + className + " {");

        boolean first = true;
        for (final String dataTypeString : sortedTypes) {
            DataTypeMap zclDataType = ZclDataType.getDataTypeMapping().get(dataTypeString);
            if (zclDataType == null) {
                System.out.println("Unable to map data type \"" + dataTypeString + "\"");
                continue;
            }

            if (!first) {
                out.println(',');
            }
            first = false;

            final String dataTypeClass;
            if (zclDataType.dataClass.contains("<")) {
                dataTypeClass = zclDataType.dataClass.substring(zclDataType.dataClass.indexOf("<") + 1,
                        zclDataType.dataClass.indexOf(">"));
            } else {
                dataTypeClass = zclDataType.dataClass;
            }
            out.print("    " + dataTypeString + "(" + dataTypeClass + ".class" + ", "
                    + String.format("0x%02X", zclDataType.id) + ", " + zclDataType.analogue + ")");
        }
        out.println(';');

        out.println();
        out.println("    private final Class<?> dataClass;");
        out.println("    private final int typeId;");
        out.println("    private final boolean analogue;");
        out.println("    private static Map<Integer, " + className + "> codeTypeMapping;");
        out.println();

        out.println("    static {");
        out.println("        codeTypeMapping = new HashMap<Integer, " + className + ">();");
        out.println("        for (" + className + " value : values()) {");
        out.println("            codeTypeMapping.put(value.typeId, value);");
        out.println("        }");
        out.println("    }");
        out.println();
        out.println("    " + className + "(final Class<?> dataClass, final int typeId, final boolean analogue) {");
        out.println("        this.dataClass = dataClass;");
        out.println("        this.typeId = typeId;");
        out.println("        this.analogue = analogue;");
        out.println("    }");
        out.println();

        out.println("    public static " + className + " getType(int typeId) {");
        out.println("        return codeTypeMapping.get(typeId);");
        out.println("    }");

        out.println();
        out.println("    public Class<?> getDataClass() {");
        out.println("        return dataClass;");
        out.println("    }");
        out.println();
        out.println("    public int getId() {");
        out.println("        return typeId;");
        out.println("    }");
        out.println();
        out.println("    public boolean isAnalog() {");
        out.println("        return analogue;");
        out.println("    }");
        out.println("}");

        out.flush();
        out.close();
    }
}

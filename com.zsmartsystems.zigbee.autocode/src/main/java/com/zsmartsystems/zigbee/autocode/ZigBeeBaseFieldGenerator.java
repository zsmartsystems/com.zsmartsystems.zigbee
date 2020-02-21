/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.autocode;

import java.io.PrintStream;
import java.util.List;

import com.zsmartsystems.zigbee.autocode.xml.ZigBeeXmlField;

/**
 *
 * @author Chris Jackson (zsmartsystems.com)
 *
 */
public class ZigBeeBaseFieldGenerator extends ZigBeeBaseClassGenerator {
    private final String OPERATOR_LOGIC_AND = "LOGIC_AND";
    private final String OPERATOR_EQUAL = "EQUAL";
    private final String OPERATOR_NOT_EQUAL = "NOT_EQUAL";
    private final String OPERATOR_GREATER_THAN = "GREATER_THAN";
    private final String OPERATOR_GREATER_THAN_OR_EQUAL = "GREATER_THAN_OR_EQUAL";
    private final String OPERATOR_LESS_THAN = "LESS_THAN";
    private final String OPERATOR_LESS_THAN_OR_EQUAL = "LESS_THAN_OR_EQUAL";

    protected void generateFields(PrintStream out, String parentClass, String className, List<ZigBeeXmlField> fields,
            List<String> reservedFields) {
        for (final ZigBeeXmlField field : fields) {
            if (reservedFields.contains(stringToLowerCamelCase(field.name))) {
                continue;
            }
            if (getAutoSized(fields, stringToLowerCamelCase(field.name)) != null) {
                continue;
            }

            out.println();
            out.println("    /**");
            out.println("     * Gets " + field.name + ".");
            if (field.description.size() != 0) {
                out.println("     * <p>");
                outputWithLinebreak(out, "    ", field.description);
            }
            out.println("     *");
            out.println("     * @return the " + field.name);
            out.println("     */");
            out.println("    public " + getDataTypeClass(field) + " get" + stringToUpperCamelCase(field.name) + "() {");
            out.println("        return " + stringToLowerCamelCase(field.name) + ";");
            out.println("    }");
            out.println();
            out.println("    /**");
            out.println("     * Sets " + field.name + ".");
            if (field.description.size() != 0) {
                out.println("     * <p>");
                outputWithLinebreak(out, "    ", field.description);
            }
            out.println("     *");
            out.println("     * @param " + stringToLowerCamelCase(field.name) + " the " + field.name);
            out.println(
                    "     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.");
            out.println("     */");
            out.println("    @Deprecated");
            out.println("    public void set" + stringToUpperCamelCase(field.name) + "(final " + getDataTypeClass(field)
                    + " " + stringToLowerCamelCase(field.name) + ") {");
            out.println("        this." + stringToLowerCamelCase(field.name) + " = "
                    + stringToLowerCamelCase(field.name) + ";");
            out.println("    }");

        }

        if (fields.size() > 0) {
            out.println();
            out.println("    @Override");
            out.println("    public void serialize(final ZclFieldSerializer serializer) {");
            if (parentClass.startsWith("Zdo")) {
                out.println("        super.serialize(serializer);");
                out.println();
            }

            for (final ZigBeeXmlField field : fields) {
                // if (reservedFields.contains(stringToLowerCamelCase(field.name))) {
                // continue;
                // }

                // Rules...
                // if listSizer == null, then just output the field
                // if listSizer != null and contains && then check the param bit
                if (getAutoSized(fields, stringToLowerCamelCase(field.name)) != null) {
                    ZigBeeXmlField sizedField = getAutoSized(fields, stringToLowerCamelCase(field.name));
                    out.println("        serializer.serialize(" + stringToLowerCamelCase(sizedField.name)
                            + ".size(), ZclDataType." + field.type + ");");

                    continue;
                }

                if (field.sizer != null) {
                    out.println("        for (int cnt = 0; cnt < " + stringToLowerCamelCase(field.name)
                            + ".size(); cnt++) {");
                    out.println("            serializer.serialize(" + stringToLowerCamelCase(field.name)
                            + ".get(cnt), ZclDataType." + field.type + ");");
                    out.println("        }");
                } else if (field.condition != null) {
                    if (field.condition.value.equals("statusResponse")) {
                        // Special case where a ZclStatus may be sent, or, a list of results.
                        // This checks for a single response
                        out.println("        if (status == ZclStatus.SUCCESS) {");
                        out.println("            serializer.serialize(status, ZclDataType.ZCL_STATUS);");
                        out.println("            return;");
                        out.println("        }");
                        continue;
                    } else if (field.condition.operator.equals(OPERATOR_LOGIC_AND)) {
                        out.println(
                                "        if ((" + field.condition.field + " & " + field.condition.value + ") != 0) {");
                    } else {
                        out.println("        if (" + field.condition.field + " " + getOperator(field.condition.operator)
                                + " " + field.condition.value + ") {");
                    }
                    out.println("            serializer.serialize(" + stringToLowerCamelCase(field.name)
                            + ", ZclDataType." + field.type + ");");
                    out.println("        }");
                } else {
                    if (field.type != null && !field.type.isEmpty()) {
                        out.println("        serializer.serialize(" + stringToLowerCamelCase(field.name)
                                + ", ZclDataType." + field.type + ");");
                    } else {
                        out.println("        " + stringToLowerCamelCase(field.name) + ".serialize(serializer);");
                    }
                }
            }
            out.println("    }");

            out.println();
            out.println("    @Override");
            out.println("    public void deserialize(final ZclFieldDeserializer deserializer) {");
            if (parentClass.startsWith("Zdo")) {
                out.println("        super.deserialize(deserializer);");
                out.println();
            }
            boolean first = true;
            for (final ZigBeeXmlField field : fields) {
                if (field.sizer != null) {
                    if (first) {
                        out.println("        // Create lists");
                        first = false;
                    }
                    out.println("        " + stringToLowerCamelCase(field.name) + " = new Array"
                            + getDataTypeClass(field) + "();");
                }
            }
            if (first == false) {
                out.println();
            }
            for (final ZigBeeXmlField field : fields) {
                // if (reservedFields.contains(stringToLowerCamelCase(field.name))) {
                // continue;
                // }

                if (field.completeOnZero) {
                    out.println("        if (deserializer.isEndOfStream()) {");
                    out.println("            return;");
                    out.println("        }");
                }
                if (getAutoSized(fields, stringToLowerCamelCase(field.name)) != null) {
                    out.println(
                            "        Integer " + stringToLowerCamelCase(field.name) + " = (" + getDataTypeClass(field)
                                    + ") deserializer.deserialize(" + "ZclDataType." + field.type + ");");
                    continue;
                }

                if (field.sizer != null) {
                    String dataType = getDataTypeClass(field).substring(getDataTypeClass(field).indexOf('<') + 1,
                            getDataTypeClass(field).indexOf('>'));

                    out.println("        if (" + field.sizer + " != null) {");
                    out.println("            for (int cnt = 0; cnt < " + field.sizer + "; cnt++) {");
                    out.println("                " + stringToLowerCamelCase(field.name) + ".add((" + dataType
                            + ") deserializer.deserialize(" + "ZclDataType." + field.type + "));");
                    out.println("            }");
                    out.println("        }");
                } else if (field.condition != null) {
                    if (field.condition.value.equals("statusResponse")) {
                        // Special case where a ZclStatus may be sent, or, a list of results.
                        // This checks for a single response
                        out.println("        if (deserializer.getRemainingLength() == 1) {");
                        out.println(
                                "            status = (ZclStatus) deserializer.deserialize(ZclDataType.ZCL_STATUS);");
                        out.println("            return;");
                        out.println("        }");
                        continue;
                    } else if (field.condition.operator.equals(OPERATOR_LOGIC_AND)) {
                        out.println(
                                "        if ((" + field.condition.field + " & " + field.condition.value + ") != 0) {");
                    } else {
                        out.println("        if (" + field.condition.field + " " + getOperator(field.condition.operator)
                                + " " + field.condition.value + ") {");
                    }
                    out.println("            " + stringToLowerCamelCase(field.name) + " = (" + getDataTypeClass(field)
                            + ") deserializer.deserialize(" + "ZclDataType." + field.type + ");");
                    out.println("        }");
                } else {
                    if (!field.type.isEmpty()) {
                        out.println("        " + stringToLowerCamelCase(field.name) + " = (" + getDataTypeClass(field)
                                + ") deserializer.deserialize(" + "ZclDataType." + field.type + ");");
                    } else {
                        out.println("        " + stringToLowerCamelCase(field.name) + " = new "
                                + getDataTypeClass(field) + "();");
                        out.println("        " + stringToLowerCamelCase(field.name) + ".deserialize(deserializer);");
                    }
                }

                if (field.name.toLowerCase().equals("status") && field.type.equals("ZDO_STATUS")) {
                    out.println("        if (status != ZdoStatus.SUCCESS) {");
                    out.println("            // Don't read the full response if we have an error");
                    out.println("            return;");
                    out.println("        }");
                }
            }
            out.println("    }");
        }
    }

    protected void generateToString(PrintStream out, String className, List<ZigBeeXmlField> fields,
            List<String> reservedFields) {
        int fieldLen = 0;
        for (final ZigBeeXmlField field : fields) {
            fieldLen += stringToLowerCamelCase(field.name).length() + 20;
        }

        out.println();
        out.println("    @Override");
        out.println("    public String toString() {");
        out.println("        final StringBuilder builder = new StringBuilder(" + (className.length() + 3 + fieldLen)
                + ");");

        out.println("        builder.append(\"" + className + " [\");");
        out.println("        builder.append(super.toString());");
        for (final ZigBeeXmlField field : fields) {
            // if (reservedFields.contains(stringToLowerCamelCase(field.name))) {
            // continue;
            // }
            if (getAutoSized(fields, stringToLowerCamelCase(field.name)) != null) {
                continue;
            }

            out.println("        builder.append(\", " + stringToLowerCamelCase(field.name) + "=\");");
            if (field.format != null) {
                String displayType;
                String modifier = "";
                if (field.format.contains("[") & field.format.contains("]")) {
                    displayType = field.format.substring(0, field.format.indexOf('['));
                    modifier = "0" + Integer
                            .parseInt(field.format.substring(field.format.indexOf('[') + 1, field.format.indexOf(']')));
                } else {
                    displayType = field.format;
                }
                switch (displayType) {
                    case "hex":
                        out.println("        builder.append(String.format(\"%" + modifier + "X\", "
                                + stringToLowerCamelCase(field.name) + "));");
                        break;
                    default:
                        break;
                }
            } else {
                out.println("        builder.append(" + stringToLowerCamelCase(field.name) + ");");
            }
        }
        out.println("        builder.append(\']\');");
        out.println("        return builder.toString();");
        out.println("    }");
    }

    private String getOperator(String operator) {
        switch (operator) {
            case OPERATOR_LOGIC_AND:
                return "&&";
            case OPERATOR_EQUAL:
                return "==";
            case OPERATOR_NOT_EQUAL:
                return "!=";
            case OPERATOR_GREATER_THAN:
                return ">";
            case OPERATOR_GREATER_THAN_OR_EQUAL:
                return ">=";
            case OPERATOR_LESS_THAN:
                return "<";
            case OPERATOR_LESS_THAN_OR_EQUAL:
                return "<";
            default:
                return "<<Unknown " + operator + ">>";
        }
    }

    protected ZigBeeXmlField getAutoSized(List<ZigBeeXmlField> fields, String name) {
        if (name == null) {
            return null;
        }
        for (ZigBeeXmlField field : fields) {
            if (field.sizer != null) {
                System.out.println();
            }
            if (name.equals(field.sizer)) {
                return field;
            }
        }
        return null;
    }
}

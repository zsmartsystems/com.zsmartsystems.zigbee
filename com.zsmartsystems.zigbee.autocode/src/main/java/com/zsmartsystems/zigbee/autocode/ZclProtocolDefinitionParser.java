package com.zsmartsystems.zigbee.autocode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;

import com.zsmartsystems.zigbee.autocode.zcl.Attribute;
import com.zsmartsystems.zigbee.autocode.zcl.Cluster;
import com.zsmartsystems.zigbee.autocode.zcl.Command;
import com.zsmartsystems.zigbee.autocode.zcl.Context;
import com.zsmartsystems.zigbee.autocode.zcl.DataType;
import com.zsmartsystems.zigbee.autocode.zcl.Field;
import com.zsmartsystems.zigbee.autocode.zcl.Profile;
import com.zsmartsystems.zigbee.autocode.zcl.ZclDataType;

/**
 * @author tlaukkan - Created on 4/10/2016.
 * @author Chris Jackson
 */
public class ZclProtocolDefinitionParser {
    public static void parseProfiles(Context context) {
        while (context.lines.size() > 0) {
            final String line = context.lines.remove(0);

            if (line.startsWith("# ") && line.contains("[")) {
                context.profile = new Profile();
                context.profile.profileName = getHeaderTitle(line);
                context.profile.profileAbbreviation = getHeaderAbbreviation(line);
                context.profile.profileType = CodeGeneratorUtil.labelToEnumerationValue(context.profile.profileName);
                context.profile.profileId = getHeaderId(line);
                context.profiles.put(context.profile.profileId, context.profile);
                System.out.println("Profile: " + context.profile.profileName + " "
                        + CodeGeneratorUtil.toHex(context.profile.profileId));
                parseFunctionalDomains(context);
            }
        }
    }

    private static void parseFunctionalDomains(Context context) {
        while (context.lines.size() > 0) {
            final String line = context.lines.remove(0);

            // Returning to previous level.
            if (line.startsWith("# ") && line.contains("[")) {
                context.lines.add(0, line);
                return;
            }

            if (line.startsWith("# ")) {
                final String functionalDomainName = getHeaderTitle(line);
                System.out.println(" Functional domain: " + functionalDomainName);

                parseClusters(context);
            }
        }
    }

    private static void parseClusters(Context context) {
        while (context.lines.size() > 0) {
            final String line = context.lines.remove(0);

            // Returning to previous level.
            if (line.startsWith("# ")) {
                context.lines.add(0, line);
                return;
            }

            if (line.startsWith("## ")) {
                context.cluster = new Cluster();
                context.cluster.clusterName = getHeaderTitle(line);
                context.cluster.clusterDescription = new ArrayList<String>();
                context.cluster.clusterType = CodeGeneratorUtil.labelToEnumerationValue(context.cluster.clusterName);
                context.cluster.clusterId = getHeaderId(line);
                context.cluster.nameUpperCamelCase = CodeGeneratorUtil
                        .labelToUpperCamelCase(context.cluster.clusterName);
                context.cluster.nameLowerCamelCase = CodeGeneratorUtil
                        .upperCamelCaseToLowerCamelCase(context.cluster.clusterName);
                context.profile.clusters.put(context.cluster.clusterId, context.cluster);
                System.out.println(
                        "  " + CodeGeneratorUtil.toHex(context.cluster.clusterId) + ") " + context.cluster.clusterName);

                parseDirections(context);
            }
        }
    }

    private static void parseDirections(Context context) {
        boolean addBreak = false;
        while (context.lines.size() > 0) {
            final String line = context.lines.remove(0);

            // Returning to previous level.
            if (line.startsWith("# ") || line.startsWith("## ")) {
                context.lines.add(0, line);
                return;
            }

            if (line.startsWith("### ")) {
                addBreak = false;

                context.received = line.toLowerCase().contains("received");
                context.generated = line.toLowerCase().contains("generated");
                context.attribute = line.toLowerCase().contains("attributes");
                if (context.received) {
                    System.out.println("   Received:");
                } else if (context.generated) {
                    System.out.println("   Generated:");
                } else if (context.attribute) {
                    System.out.println("   Attributes:");

                    parseAttributes(context);
                    continue;
                } else {
                    System.out.println("   Unknown:");
                }

                parseCommands(context);

                continue;
            }

            if (context.cluster.clusterDescription.size() == 0 && line.trim().length() == 0) {
                continue;
            }
            if (line.trim().length() == 0) {
                addBreak = true;
                continue;
            }
            if (addBreak && context.cluster.clusterDescription.size() > 0) {
                context.cluster.clusterDescription.add("<p>");
                addBreak = false;
            }
            context.cluster.clusterDescription.add(line.trim());
        }
    }

    private static void parseCommands(Context context) {
        boolean addBreak = false;
        Field field = null;
        while (context.lines.size() > 0) {
            final String line = context.lines.remove(0);

            // Returning to previous level.
            if (line.startsWith("# ") || line.startsWith("## ") || line.startsWith("### ")) {
                context.lines.add(0, line);
                return;
            }

            if (line.startsWith("##### Expected Response")) {
                parseExpectedResponse(context);
                continue;
            }

            if (line.startsWith("##### ")) {
                addBreak = false;

                for (Field fieldLoop : context.command.fields.values()) {
                    if (fieldLoop.fieldLabel.equals(line.trim().substring(6))) {
                        field = fieldLoop;
                        break;
                    }
                }
                if (field == null) {
                    System.out.println("Error finding field \"" + line.trim().substring(6) + "\"");
                }
                continue;
            }

            if (line.startsWith("#### ")) {
                context.command = new Command();
                context.command.commandLabel = getHeaderTitle(line).trim();
                String splits[] = context.command.commandLabel.split(" ");

                if ("RESPONSE".equals(splits[splits.length - 2].toUpperCase())
                        && "COMMAND".equals(splits[splits.length - 1].toUpperCase())) {
                    StringBuilder sb = new StringBuilder();
                    for (int c = 0; c < splits.length - 1; c++) {
                        if (c != 0) {
                            sb.append(" ");
                        }
                        sb.append(splits[c]);
                    }

                    context.command.commandLabel = sb.toString();
                }

                context.command.commandDescription = new ArrayList<String>();
                context.command.commandType = CodeGeneratorUtil.labelToEnumerationValue(context.command.commandLabel);
                context.command.commandId = getHeaderId(line);
                context.command.nameUpperCamelCase = CodeGeneratorUtil
                        .labelToUpperCamelCase(context.command.commandLabel);
                context.command.nameLowerCamelCase = CodeGeneratorUtil
                        .upperCamelCaseToLowerCamelCase(context.command.nameUpperCamelCase);
                if (context.received) {
                    context.cluster.received.put(context.command.commandId, context.command);
                } else {
                    context.cluster.generated.put(context.command.commandId, context.command);
                }
                System.out.println("     " + CodeGeneratorUtil.toHex(context.command.commandId) + ") "
                        + context.command.commandLabel);

                parseField(context);
                continue;
            }

            if (field == null) {
                continue;
            }

            if (line.startsWith("|") && !line.startsWith("|Id") && !line.startsWith("|-")) {
                final String row = line.trim().substring(1, line.length() - 1);
                final String[] columns = row.split("\\|");
                int value = Integer.parseInt(columns[0].trim().substring(2), 16);
                String label = columns[1].trim();

                field.valueMap.put(value, label);
                continue;
            }
            if (line.startsWith("|") && (line.startsWith("|Id") || line.startsWith("|-"))) {
                continue;
            }

            if (field.description.size() == 0 && line.trim().length() == 0) {
                continue;
            }
            if (line.trim().length() == 0) {
                addBreak = true;
                continue;
            }
            if (addBreak && field.description.size() > 0) {
                field.description.add("<p>");
                addBreak = false;
            }
            field.description.add(line.trim());
        }

    }

    private static void parseField(Context context) {
        int fieldIndex = 0;
        boolean addBreak = false;
        while (context.lines.size() > 0) {
            final String line = context.lines.remove(0);

            // Returning to previous level.
            if (line.startsWith("#")) {
                context.lines.add(0, line);
                return;
            }

            if (line.startsWith("|") && !line.startsWith("|Field Name") && !line.startsWith("|-")) {
                final String row = line.trim().substring(1, line.length() - 1);
                final String[] columns = row.split("\\|");
                final Field field = new Field();
                field.description = new ArrayList<String>();
                field.fieldId = fieldIndex;
                field.valueMap = new TreeMap<Integer, String>();

                field.fieldLabel = columns[0].trim();
                if (field.fieldLabel.contains("[")) {
                    String option = field.fieldLabel.substring(field.fieldLabel.indexOf("[") + 1,
                            field.fieldLabel.indexOf("]"));
                    field.fieldLabel = field.fieldLabel.substring(0, field.fieldLabel.indexOf("["));
                    field.completeOnZero = true;
                }

                field.fieldType = context.command.commandType + "_"
                        + CodeGeneratorUtil.labelToEnumerationValue(field.fieldLabel);
                field.nameUpperCamelCase = CodeGeneratorUtil.labelToEnumerationValue(field.fieldLabel);
                field.nameUpperCamelCase = CodeGeneratorUtil.labelToUpperCamelCase(field.fieldLabel);
                field.nameLowerCamelCase = CodeGeneratorUtil.upperCamelCaseToLowerCamelCase(field.nameUpperCamelCase);

                String dataTypeName = columns[1].trim();
                if (dataTypeName.contains("[")) {
                    String fieldString = dataTypeName.substring(dataTypeName.indexOf("[") + 1,
                            dataTypeName.indexOf("]"));
                    if (fieldString.length() != 0) {
                        String conditionOperator = "";
                        String condition = "";
                        if (fieldString.contains("&&")) {
                            conditionOperator = "&&";
                        }
                        if (fieldString.contains(">=")) {
                            conditionOperator = ">=";
                        }
                        if (fieldString.contains("==")) {
                            conditionOperator = "==";
                        }

                        if (conditionOperator.length() != 0) {
                            field.listSizer = fieldString.substring(0, fieldString.indexOf(conditionOperator));
                            condition = fieldString
                                    .substring(fieldString.indexOf(conditionOperator) + conditionOperator.length());

                            field.condition = condition;
                            field.conditionOperator = conditionOperator;
                        } else {
                            field.listSizer = fieldString;
                        }
                        field.listSizer = CodeGeneratorUtil.labelToUpperCamelCase(field.listSizer);
                        field.listSizer = CodeGeneratorUtil.upperCamelCaseToLowerCamelCase(field.listSizer);

                        dataTypeName = dataTypeName.substring(0, dataTypeName.indexOf("["));
                    }
                }
                field.dataType = CodeGeneratorUtil.labelToEnumerationValue(dataTypeName);

                final DataType dataType = new DataType();
                dataType.dataTypeName = dataTypeName;
                dataType.dataTypeType = field.dataType;

                dataType.dataTypeClass = ZclDataType.getDataTypeMapping().get(field.dataType).dataClass;
                if (dataType.dataTypeClass == null) {
                    throw new IllegalArgumentException("Type not mapped: " + field.dataType);
                }

                field.dataTypeClass = dataType.dataTypeClass;

                context.dataTypes.put(field.dataType, dataType);
                context.command.fields.put(field.fieldId, field);
                System.out.println("      " + CodeGeneratorUtil.toHex(fieldIndex) + ") " + field.fieldLabel + ": "
                        + dataType.dataTypeName);
                fieldIndex++;
            }

            if (line.startsWith("|Id") || line.startsWith("|-")) {
                continue;
            }

            if (line.startsWith("|")) {
                addBreak = false;
                continue;
            }

            if (context.command.commandDescription.size() == 0 && line.trim().length() == 0) {
                continue;
            }
            if (line.trim().length() == 0) {
                addBreak = true;
                continue;
            }
            if (addBreak) {
                context.command.commandDescription.add("<br>");
                addBreak = false;
            }
            context.command.commandDescription.add(line.trim());
        }
    }

    private static void parseExpectedResponse(Context context) {
        context.command.responseMatchers = new HashMap<String, String>();
        while (context.lines.size() > 0) {
            final String line = context.lines.remove(0);

            // Returning to previous level.
            if (line.startsWith("#")) {
                context.lines.add(0, line);
                return;
            }

            if (line.startsWith("Packet: ")) {
                String cmd = line.substring(7);
                String splits[] = cmd.split(" ");
                StringBuilder sb = new StringBuilder();
                for (int c = 0; c < splits.length - 1; c++) {
                    if (c != 0) {
                        sb.append(" ");
                    }
                    sb.append(splits[c]);
                }
                context.command.responseCommand = CodeGeneratorUtil.labelToUpperCamelCase(line.substring(7));
            }

            if (line.startsWith("Match: ")) {
                String response = line.substring(7).trim();
                String[] matcher = response.split("==");

                String responseRequest = getMatcherResponse(matcher[0].trim());
                String responseResponse = getMatcherResponse(matcher[1].trim());
                context.command.responseMatchers.put(responseRequest, responseResponse);
            }
        }
    }

    private static String getMatcherResponse(String definition) {
        if (!definition.contains(".")) {
            return CodeGeneratorUtil.labelToUpperCamelCase(definition.trim());
        }

        String[] parts = definition.split("\\.");
        parts[0] = CodeGeneratorUtil.labelToUpperCamelCase(parts[0].trim());
        parts[1] = CodeGeneratorUtil.labelToUpperCamelCase(parts[1].trim());
        return parts[0] + "().get" + parts[1];
    }

    private static String getHeaderTitle(String line) {
        line = line.substring(line.lastIndexOf("#") + 1);
        if (line.contains("[")) {
            return StringUtils.substringBefore(line, "[").trim();
        } else {
            return line.trim();
        }
    }

    private static int getHeaderId(String line) {
        final String headerIdString = StringUtils.substringBetween(line, "[", "]").trim();
        return CodeGeneratorUtil.fromHex(headerIdString);
    }

    private static String getHeaderAbbreviation(String line) {
        return StringUtils.substringAfter(line, "]").trim().toLowerCase();
    }

    private static void parseAttributes(Context context) {
        Attribute attribute = null;
        while (context.lines.size() > 0) {
            final String line = context.lines.remove(0);

            // Returning to previous level.
            if (line.startsWith("# ") || line.startsWith("## ") || line.startsWith("### ")) {
                context.lines.add(0, line);
                return;
            }

            if (line.startsWith("|") && !line.startsWith("|Id") && !line.startsWith("|-")) {
                parseAttributeTable(context, line);
            }

            if (line.startsWith("#### ")) {
                attribute = null;
                for (Attribute attr : context.cluster.attributes.values()) {
                    if (attr.attributeLabel
                            .equals(getHeaderTitle(line).substring(0, getHeaderTitle(line).indexOf(" ")))) {
                        attribute = attr;
                        break;
                    }
                }

                if (attribute == null) {
                    System.out.println("***** Attribute not found: " + line);
                    continue;
                }
                parseAttribute(context, attribute);
                continue;
            }

        }
    }

    private static void parseAttributeTable(Context context, String line) {
        final String row = line.trim().substring(1, line.length() - 1);
        final String[] columns = row.split("\\|");
        final Attribute attribute = new Attribute();
        attribute.valueMap = new TreeMap<Integer, String>();
        attribute.attributeId = Integer.parseInt(columns[0].trim().substring(2), 16);
        attribute.attributeLabel = columns[1].trim();
        attribute.attributeDescription = new ArrayList<String>();
        attribute.attributeAccess = columns[3].trim();
        attribute.attributeImplementation = columns[4].trim();
        attribute.attributeReporting = columns[5].trim();
        attribute.nameUpperCamelCase = CodeGeneratorUtil.labelToEnumerationValue(attribute.attributeLabel);
        attribute.nameUpperCamelCase = CodeGeneratorUtil.labelToUpperCamelCase(attribute.attributeLabel);
        attribute.nameLowerCamelCase = CodeGeneratorUtil.upperCamelCaseToLowerCamelCase(attribute.nameUpperCamelCase);
        attribute.dataType = CodeGeneratorUtil.labelToEnumerationValue(columns[2].trim());
        attribute.enumName = "ATTR_" + attribute.attributeLabel.toUpperCase();
        final DataType dataType = new DataType();
        dataType.dataTypeName = columns[2].trim();
        dataType.dataTypeType = attribute.dataType;
        dataType.dataTypeClass = ZclDataType.getDataTypeMapping().get(attribute.dataType).dataClass;
        if (dataType.dataTypeClass == null) {
            throw new IllegalArgumentException("Type not mapped: " + attribute.dataType);
        }
        attribute.dataTypeClass = dataType.dataTypeClass;

        System.out.println(" Type::::: " + dataType.dataTypeType);

        context.dataTypes.put(attribute.dataType, dataType);
        context.cluster.attributes.put(attribute.attributeId, attribute);
    }

    private static void parseAttribute(Context context, Attribute attribute) {
        boolean addBreak = false;
        while (context.lines.size() > 0) {
            final String line = context.lines.remove(0);

            // Returning to previous level.
            if (line.startsWith("# ") || line.startsWith("## ") || line.startsWith("### ")
                    || line.startsWith("#### ")) {
                context.lines.add(0, line);
                return;
            }

            if (line.startsWith("|") && !line.startsWith("|Id") && !line.startsWith("|-")) {
                final String row = line.trim().substring(1, line.length() - 1);
                final String[] columns = row.split("\\|");
                int value = Integer.parseInt(columns[0].trim().substring(2), 16);
                String label = columns[1].trim();

                attribute.valueMap.put(value, label);
                continue;
            }

            if (line.startsWith("|Id") || line.startsWith("|-")) {
                continue;
            }

            if ((attribute.attributeDescription.size() == 0 && line.trim().length() == 0)) {
                continue;
            }
            if (line.trim().length() == 0 && attribute.attributeDescription.size() > 0) {
                addBreak = true;
                continue;
            }
            if (addBreak && attribute.attributeDescription.size() > 0) {
                attribute.attributeDescription.add("<p>");
                addBreak = false;
            }
            attribute.attributeDescription.add(line.trim());
        }
    }

}

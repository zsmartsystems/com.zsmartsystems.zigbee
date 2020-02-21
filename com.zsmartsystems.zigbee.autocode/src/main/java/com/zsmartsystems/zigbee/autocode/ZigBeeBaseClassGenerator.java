/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.autocode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zsmartsystems.zigbee.autocode.ZclDataType.DataTypeMap;
import com.zsmartsystems.zigbee.autocode.xml.ZigBeeXmlAttribute;
import com.zsmartsystems.zigbee.autocode.xml.ZigBeeXmlCluster;
import com.zsmartsystems.zigbee.autocode.xml.ZigBeeXmlDescription;
import com.zsmartsystems.zigbee.autocode.xml.ZigBeeXmlField;

/**
 *
 * @author Chris Jackson (zsmartsystems.com)
 *
 */

public abstract class ZigBeeBaseClassGenerator {
    String generatedDate;

    Map<String, String> dependencies;

    int lineLen = 80;
    String sourceRootPath = "target/src/main/java/";
    List<String> importList = new ArrayList<String>();

    static String packageRoot = "com.zsmartsystems.zigbee";
    static String packageZcl = ".zcl";
    static String packageZclField = packageZcl + ".field";
    String packageZclCluster = packageZcl + ".clusters";
    String packageZclProtocol = packageZcl + ".protocol";
    String packageZclProtocolCommand = packageZclCluster;

    static String packageZdp = ".zdo";
    static String packageZdpField = packageZdp + ".field";
    String packageZdpCommand = packageZdp + ".command";
    String packageZdpTransaction = packageZdp + ".transaction";
    String packageZdpDescriptors = packageZdpField;

    private static List<String> standardTypes = new ArrayList<>();
    private static Map<String, String> customTypes = new HashMap<>();
    private static List<String> fixedCaseAcronyms = new ArrayList<>();
    static {
        fixedCaseAcronyms.add("AA");
        fixedCaseAcronyms.add("AC");
        fixedCaseAcronyms.add("AAA");
        fixedCaseAcronyms.add("ACE");
        fixedCaseAcronyms.add("APS");
        fixedCaseAcronyms.add("CIE");
        fixedCaseAcronyms.add("CR");
        fixedCaseAcronyms.add("CO");
        fixedCaseAcronyms.add("CO2");
        fixedCaseAcronyms.add("DC");
        fixedCaseAcronyms.add("DRLC");
        fixedCaseAcronyms.add("DST");
        fixedCaseAcronyms.add("ECC");
        fixedCaseAcronyms.add("ECDSA");
        fixedCaseAcronyms.add("EUI");
        fixedCaseAcronyms.add("FC");
        fixedCaseAcronyms.add("HAN");
        fixedCaseAcronyms.add("HW");
        fixedCaseAcronyms.add("ID");
        fixedCaseAcronyms.add("IAS");
        fixedCaseAcronyms.add("IEEE");
        fixedCaseAcronyms.add("LQI");
        fixedCaseAcronyms.add("MAC");
        fixedCaseAcronyms.add("MMO");
        fixedCaseAcronyms.add("NWK");
        fixedCaseAcronyms.add("OTA");
        fixedCaseAcronyms.add("PIN");
        fixedCaseAcronyms.add("PIR");
        fixedCaseAcronyms.add("RMS");
        fixedCaseAcronyms.add("RSSI");
        fixedCaseAcronyms.add("SMAC");
        fixedCaseAcronyms.add("SW");
        fixedCaseAcronyms.add("UTC");
        fixedCaseAcronyms.add("WAN");
        fixedCaseAcronyms.add("WD");
        fixedCaseAcronyms.add("XY");
        fixedCaseAcronyms.add("ZCL");
        fixedCaseAcronyms.add("ZED");
        fixedCaseAcronyms.add("ZR");

        fixedCaseAcronyms.add("may");
        fixedCaseAcronyms.add("shall");
        fixedCaseAcronyms.add("should");

        fixedCaseAcronyms.add("ZigBee");

        standardTypes.add("Integer");
        standardTypes.add("Boolean");
        standardTypes.add("Object");
        standardTypes.add("Long");
        standardTypes.add("Double");
        standardTypes.add("String");
        standardTypes.add("int[]");

        customTypes.put("IeeeAddress", packageRoot + ".IeeeAddress");
        customTypes.put("ZigBeeKey", packageRoot + ".security.ZigBeeKey");
        customTypes.put("ByteArray", packageRoot + packageZclField + ".ByteArray");
        customTypes.put("ZclStatus", packageRoot + packageZcl + ".ZclStatus");
        customTypes.put("ZdoStatus", packageRoot + packageZdp + ".ZdoStatus");
        customTypes.put("BindingTable", packageRoot + packageZdpField + ".BindingTable");
        customTypes.put("NeighborTable", packageRoot + packageZdpField + ".NeighborTable");
        customTypes.put("RoutingTable", packageRoot + packageZdpField + ".RoutingTable");
        customTypes.put("Calendar", "java.util.Calendar");
        customTypes.put("ImageUpgradeStatus", packageRoot + packageZclField + ".ImageUpgradeStatus");
    }

    protected String stringToConstantEnum(String value) {
        return stringToConstant(value).replaceAll("_", "");
    }

    protected String stringToConstant(String value) {
        // value = value.replaceAll("\\(.*?\\) ?", "");
        value = value.trim();
        value = value.replace("+", "_Plus");
        value = value.replace("(", "_");
        value = value.replace(")", "_");
        value = value.replace(" ", "_");
        value = value.replace("-", "_");
        value = value.replace(".", "_");
        value = value.replace("/", "_");
        value = value.replace("#", "_");
        value = value.replaceAll("_+", "_");
        if (value.endsWith("_")) {
            value = value.substring(0, value.length() - 1);
        }
        return value.toUpperCase();
    }

    private String toProperCase(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }

    private String toCamelCase(String value) {
        // value = value.replaceAll("\\(.*?\\) ?", "");
        value = value.replace("(", "_");
        value = value.replace(")", "_");
        value = value.replace("+", "_Plus");
        value = value.replace(" ", "_");
        value = value.replace("-", "_");
        value = value.replace(".", "_");
        value = value.replace("/", "_");
        value = value.replace("#", "_");
        value = value.replaceAll("_+", "_");
        String[] parts = value.split("_");
        String camelCaseString = "";
        for (String part : parts) {
            camelCaseString = camelCaseString + toProperCase(part);
        }
        return camelCaseString;
    }

    protected String stringToUpperCamelCase(String value) {
        return toCamelCase(value);
    }

    protected String stringToLowerCamelCase(String value) {
        String cc = toCamelCase(value);

        return cc.substring(0, 1).toLowerCase() + cc.substring(1);
    }

    protected String upperCaseFirstCharacter(String val) {
        return val.substring(0, 1).toUpperCase() + val.substring(1);
    }

    protected String lowerCaseFirstCharacter(String val) {
        return val.substring(0, 1).toLowerCase() + val.substring(1);
    }

    protected PrintStream  getClassOut(File packageFile, String className) throws FileNotFoundException, UnsupportedEncodingException {
        packageFile.mkdirs();
        final File classFile = new File(packageFile + File.separator + className + ".java");
        System.out.println("Generating: " + classFile.getAbsolutePath());
        final FileOutputStream fileOutputStream = new FileOutputStream(classFile, false);
        return new PrintStream (fileOutputStream,false,"UTF-8");
    }

    protected void importsClear() {
        importList.clear();
    }

    protected void importsAdd(String importClass) {
        if (importList.contains(importClass)) {
            return;
        }
        importList.add(importClass);
    }

    protected void outputImports(final PrintStream out) {
        Collections.sort(importList);
        boolean found = false;
        for (final String importClass : importList) {
            if (!importClass.startsWith("java.")) {
                continue;
            }
            found = true;
            out.println("import " + importClass + ";");
        }
        if (found) {
            out.println();
            found = false;
        }
        for (final String importClass : importList) {
            if (!importClass.startsWith("javax.")) {
                continue;
            }
            found = true;
            out.println("import " + importClass + ";");
        }
        if (found) {
            out.println();
            found = false;
        }
        for (final String importClass : importList) {
            if (importClass.startsWith("java.") || importClass.startsWith("javax.")) {
                continue;
            }
            out.println("import " + importClass + ";");
        }
    }

    protected void outputWithLinebreak(PrintStream out, String indent, List<ZigBeeXmlDescription> descriptions) {
        boolean firstDescription = true;
        for (ZigBeeXmlDescription description : descriptions) {
            if (description.description == null) {
                continue;
            }
            String[] words = description.description.split("\\s+");
            if (words.length == 0) {
                continue;
            }

            if (!firstDescription) {
                out.println(indent + " * <p>");
            }
            firstDescription = false;

            out.print(indent + " *");
            int len = 2 + indent.length();

            for (String word : words) {
                if (word.equalsIgnoreCase("note:")) {
                    if (len > 2) {
                        out.println();
                    }
                    out.println(indent + " * <p>");
                    out.print(indent + " * <b>Note:</b>");
                    continue;
                }
                if (len + word.length() > lineLen) {
                    out.println();
                    out.print(indent + " *");
                    len = 2 + indent.length();
                }
                out.print(" ");

                for (String acronym : fixedCaseAcronyms) {
                    if (acronym.equalsIgnoreCase(word)) {
                        word = acronym;
                    }
                }

                out.print(word);
                len += word.length();
            }

            if (len != 2 + indent.length()) {
                out.println();
            }
        }
    }

    protected void outputLicense(PrintStream out) {
        String year = "XXXX";

        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader("../pom.xml"));
            String line = br.readLine();
            while (line != null) {
                if (line.contains("<license.year>") && line.contains("</license.year>")) {
                    year = line.substring(line.indexOf("<license.year>") + 14, line.indexOf("</license.year>"));
                    break;
                }
                line = br.readLine();
            }

            br.close();

            br = new BufferedReader(new FileReader("../src/etc/header.txt"));
            line = br.readLine();

            out.println("/**");
            while (line != null) {
                out.println(" * " + line.replaceFirst("\\$\\{year\\}", year));
                line = br.readLine();
            }
            out.println(" */");
            br.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    protected File getPackageFile(String packagePath) {
        final File packageFile = new File(packagePath);
        if (!packageFile.exists()) {
            packageFile.mkdirs();
        }
        return packageFile;
    }

    protected String getPackagePath(File sourceRootPath, String packageRoot) {
        return sourceRootPath.getAbsolutePath() + File.separator + packageRoot.replace(".", File.separator);
    }

    protected void outputClassGenerated(PrintStream out) {
        out.println("@Generated(value = \"" + ZigBeeCodeGenerator.class.getName() + "\", date = \"" + generatedDate
                + "\")");
    }

    protected void outputAttributeJavaDoc(PrintStream out, String type, ZigBeeXmlAttribute attribute,
            DataTypeMap zclDataType) {
        out.println();
        out.println("    /**");
        out.println("     * " + type + " the <i>" + attribute.name + "</i> attribute [attribute ID <b>0x"
                + String.format("%04X", attribute.code) + "</b>].");
        if (attribute.description.size() != 0) {
            out.println("     * <p>");
            outputWithLinebreak(out, "    ", attribute.description);
        }
        if ("Synchronously get".equals(type)) {
            out.println("     * <p>");
            out.println("     * This method can return cached data if the attribute has already been received.");
            out.println(
                    "     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received");
            out.println(
                    "     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value");
            out.println(
                    "     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.");
            out.println("     * <p>");
            out.println(
                    "     * This method will block until the response is received or a timeout occurs unless the current value is returned.");
        }
        out.println("     * <p>");
        out.println("     * The attribute is of type {@link " + getDataTypeClass(attribute) + "}.");
        out.println("     * <p>");
        out.println("     * The implementation of this attribute by a device is "
                + (attribute.optional ? "OPTIONAL" : "MANDATORY"));
        out.println("     *");
        if (attribute.arrayCount != null && attribute.arrayStart != null) {
            out.println("     * @param arrayOffset attribute array offset (" + attribute.arrayStart
                    + " < arrayOffset < " + (attribute.arrayStart + attribute.arrayCount - 1) + ")");
        }
        if ("Set reporting for".equals(type)) {
            out.println("     * @param minInterval minimum reporting period");
            out.println("     * @param maxInterval maximum reporting period");
            if (zclDataType.analogue) {
                out.println("     * @param reportableChange {@link Object} delta required to trigger report");
            }
        } else if ("Set".equals(type)) {
            out.println("     * @param " + stringToLowerCamelCase(attribute.name) + " the {@link "
                    + getDataTypeClass(attribute) + "} attribute value to be set");
        }

        if ("Synchronously get".equals(type)) {
            out.println(
                    "     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed");
            out.println(
                    "     * @return the {@link " + getDataTypeClass(attribute) + "} attribute value, or null on error");
        } else {
            out.println("     * @return the {@link Future<CommandResult>} command result future");
        }

        String replacedBy;
        if ("Set reporting for".equals(type)) {
            replacedBy = "setReporting(int attributeId, int minInterval, int maxInterval";
            if (zclDataType.analogue) {
                replacedBy += ", Object reportableChange";
            }
        } else if (type.contains("Set")) {
            replacedBy = "writeAttribute(int attributeId, Object value";
        } else if ("Synchronously get".equals(type)) {
            replacedBy = "ZclAttribute#readValue(long refreshPeriod";
        } else {
            replacedBy = "readAttribute(int attributeId";
        }
        replacedBy += ")";

        out.println("     * @deprecated As of release 1.2.0, replaced by {@link #" + replacedBy + "}");

        out.println("     */");
    }

    protected String getDataTypeClass(ZigBeeXmlAttribute attribute) {
        // if (attribute.implementationClass.isEmpty()) {
        if (ZclDataType.getDataTypeMapping().get(attribute.type) != null) {
            return ZclDataType.getDataTypeMapping().get(attribute.type).dataClass;
        }

        if (dependencies.containsKey(attribute.implementationClass)) {
            // importsAdd(dependencies.get(type));
            return attribute.implementationClass;
        }

        System.out.println("Unknown data type " + attribute.type);
        return "(UNKNOWN::" + attribute.type + ")";
        // }
        // return attribute.implementationClass;
    }

    protected String getDataTypeClass(ZigBeeXmlField field) {
        String dataType = "";

        // if (field.implementationClass.isEmpty()) {
        if (ZclDataType.getDataTypeMapping().get(field.type) != null) {
            dataType = ZclDataType.getDataTypeMapping().get(field.type).dataClass;
        } else if (dependencies.containsKey(field.implementationClass)) {
            // importsAdd(dependencies.get(type));
            dataType = field.implementationClass;
        }

        if (dataType.isEmpty()) {
            System.out.println("Unknown data type " + field.type);
            return "(UNKNOWN::" + field.type + ")";
        }

        if (field.sizer == null) {
            return dataType;
        } else {
            return "List<" + dataType + ">";
        }

        // }
        // return field.implementationClass;
    }

    protected void importsAddClass(ZigBeeXmlField field) {
        importsAddClassInternal(getDataTypeClass(field));
    }

    protected void importsAddClass(ZigBeeXmlAttribute attribute) {
        importsAddClassInternal(getDataTypeClass(attribute));
    }

    protected void importsAddClassInternal(String type) {
        String typeName = type;
        if (type.startsWith("List")) {
            importsAdd("java.util.List");
            typeName = typeName.substring(typeName.indexOf("<") + 1, typeName.indexOf(">"));
        }

        if (standardTypes.contains(typeName)) {
            return;
        }

        if (customTypes.containsKey(typeName)) {
            importsAdd(customTypes.get(typeName));
            return;
        }

        if (dependencies.containsKey(type)) {
            importsAdd(dependencies.get(type));
            return;
        }

        String packageName;
        if (type.contains("Descriptor")) {
            packageName = packageZdpDescriptors;
        } else {
            packageName = packageZclField;
        }
        importsAdd(packageRoot + packageName + "." + typeName);
    }

    protected String getZclClusterCommandPackage(ZigBeeXmlCluster cluster) {
        if (cluster.name.startsWith("ZDO")) {
            return packageRoot + packageZdpCommand;
        } else {
            return packageRoot + packageZclProtocolCommand + "."
                    + stringToLowerCamelCase(cluster.name).replace("_", "").toLowerCase();
        }
    }
}

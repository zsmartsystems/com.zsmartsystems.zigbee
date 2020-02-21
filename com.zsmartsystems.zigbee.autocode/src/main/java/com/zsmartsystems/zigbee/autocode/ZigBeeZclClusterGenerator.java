/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.autocode;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.zsmartsystems.zigbee.autocode.ZclDataType.DataTypeMap;
import com.zsmartsystems.zigbee.autocode.xml.ZigBeeXmlAttribute;
import com.zsmartsystems.zigbee.autocode.xml.ZigBeeXmlCluster;
import com.zsmartsystems.zigbee.autocode.xml.ZigBeeXmlCommand;
import com.zsmartsystems.zigbee.autocode.xml.ZigBeeXmlField;

/**
 *
 * @author Chris Jackson (zsmartsystems.com)
 *
 */
public class ZigBeeZclClusterGenerator extends ZigBeeBaseClassGenerator {

    ZigBeeZclClusterGenerator(List<ZigBeeXmlCluster> clusters, String generatedDate, Map<String, String> dependencies) {
        this.generatedDate = generatedDate;
        this.dependencies = dependencies;

        for (ZigBeeXmlCluster cluster : clusters) {
            // Suppress GENERAL cluster as it's not really a cluster!
            if (cluster.name.equalsIgnoreCase("GENERAL")) {
                continue;
            }

            try {
                generateZclClusterClasses(cluster, packageRoot, new File(sourceRootPath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void generateZclClusterClasses(ZigBeeXmlCluster cluster, String packageRootPrefix, File sourceRootPath)
            throws IOException {

        final String packageRoot = packageRootPrefix;
        final String packagePath = getPackagePath(sourceRootPath, packageRoot);
        final File packageFile = getPackageFile(packagePath + (packageZclCluster).replace('.', '/'));

        final String clusterClassName = "Zcl" + stringToUpperCamelCase(cluster.name) + "Cluster";
        final String commandClassName = "Zcl" + stringToUpperCamelCase(cluster.name) + "Command";
        final PrintStream out = getClassOut(packageFile, clusterClassName);

        outputLicense(out);

        out.println("package " + packageRoot + packageZclCluster + ";");
        out.println();

        importsClear();

        int commandsServer = 0;
        int commandsClient = 0;
        for (final ZigBeeXmlCommand command : cluster.commands) {
            importsAdd(packageRoot + packageZclCluster + "." + stringToLowerCamelCase(cluster.name).toLowerCase() + "."
                    + stringToUpperCamelCase(command.name));

            if (command.source.equals("server")) {
                commandsServer++;
            }
            if (command.source.equals("client")) {
                commandsClient++;
            }

            for (final ZigBeeXmlField field : command.fields) {
                importsAddClass(field);
            }
        }

        boolean addAttributeTypes = false;
        boolean readAttributes = false;
        boolean writeAttributes = false;
        List<ZigBeeXmlAttribute> attributesClient = new ArrayList<>();
        List<ZigBeeXmlAttribute> attributesServer = new ArrayList<>();
        for (final ZigBeeXmlAttribute attribute : cluster.attributes) {
            if (attribute.writable) {
                addAttributeTypes = true;
                writeAttributes = true;
            }
            readAttributes = true;
            if (attribute.side.equals("server")) {
                attributesServer.add(attribute);
            }
            if (attribute.side.equals("client")) {
                attributesClient.add(attribute);
            }

            importsAddClass(attribute);
        }

        if (addAttributeTypes) {
            importsAdd(packageRoot + packageZclProtocol + ".ZclDataType");
        }

        importsAdd(packageRoot + packageZcl + ".ZclCluster");
        if (cluster.attributes.size() > 0) {
            importsAdd(packageRoot + packageZclProtocol + ".ZclDataType");
            // importsAdd(packageRoot + packageZclProtocol + ".ZclClusterType");
        }

        if (!cluster.commands.isEmpty()) {
            importsAdd(packageRoot + packageZcl + ".ZclCommand");
            importsAdd(getZclClusterCommandPackage(cluster) + "." + commandClassName);
        }
        importsAdd("javax.annotation.Generated");

        // imports.add(packageRoot + ".ZigBeeDestination");
        importsAdd(packageRoot + ".ZigBeeEndpoint");
        if (!cluster.attributes.isEmpty() | !cluster.commands.isEmpty()) {
            importsAdd(packageRoot + ".CommandResult");
            importsAdd("java.util.concurrent.Future");
        }
        // imports.add(packageRoot + ".ZigBeeEndpoint");
        importsAdd(packageRoot + packageZcl + ".ZclAttribute");
        importsAdd("java.util.Map");
        importsAdd("java.util.concurrent.ConcurrentSkipListMap");

        outputImports(out);

        out.println();
        out.println("/**");
        out.println(" * <b>" + cluster.name + "</b> cluster implementation (<i>Cluster ID "
                + String.format("0x%04X", cluster.code) + "</i>).");
        if (!cluster.description.isEmpty()) {
            out.println(" * <p>");
            outputWithLinebreak(out, "", cluster.description);
        }

        out.println(" * <p>");
        out.println(" * Code is auto-generated. Modifications may be overwritten!");

        out.println(" */");
        // outputClassJavaDoc(out);
        outputClassGenerated(out);
        out.println("public class " + clusterClassName + " extends ZclCluster {");

        out.println("    /**");
        out.println("     * The ZigBee Cluster Library Cluster ID");
        out.println("     */");
        out.println("    public static final int CLUSTER_ID = " + String.format("0x%04X;", cluster.code));
        out.println();
        out.println("    /**");
        out.println("     * The ZigBee Cluster Library Cluster Name");
        out.println("     */");
        out.println("    public static final String CLUSTER_NAME = \"" + cluster.name + "\";");
        out.println();

        if (cluster.attributes.size() != 0) {
            out.println("    // Attribute constants");
            for (final ZigBeeXmlAttribute attribute : cluster.attributes) {
                if (attribute.arrayStart != null && attribute.arrayCount != null && attribute.arrayCount > 0) {
                    int arrayCount = attribute.arrayStart;
                    int arrayStep = attribute.arrayStep == null ? 1 : attribute.arrayStep;
                    for (int count = 0; count < attribute.arrayCount; count++) {
                        if (!attribute.description.isEmpty()) {
                            out.println("    /**");
                            outputWithLinebreak(out, "    ", attribute.description);
                            out.println("     */");
                        }
                        String name = attribute.name.replaceAll("\\{\\{count\\}\\}", Integer.toString(arrayCount));
                        out.println("    public static final int " + getEnum(name) + " = "
                                + String.format("0x%04X", attribute.code + arrayCount) + ";");
                        arrayCount += arrayStep;
                    }
                } else {
                    if (!attribute.description.isEmpty()) {
                        out.println("    /**");
                        outputWithLinebreak(out, "    ", attribute.description);
                        out.println("     */");
                    }
                    out.println("    public static final int " + getEnum(attribute.name) + " = "
                            + String.format("0x%04X", attribute.code) + ";");
                }
            }
            out.println();
        }

        out.println("    @Override");
        out.println("    protected Map<Integer, ZclAttribute> initializeClientAttributes() {");
        createInitializeAttributes(out, cluster.name, attributesClient);
        out.println();

        out.println("    @Override");
        out.println("    protected Map<Integer, ZclAttribute> initializeServerAttributes() {");
        createInitializeAttributes(out, cluster.name, attributesServer);
        out.println();

        // TODO: Add client attributes

        if (commandsServer != 0) {
            out.println("    @Override");
            out.println("    protected Map<Integer, Class<? extends ZclCommand>> initializeServerCommands() {");
            out.println(
                    "        Map<Integer, Class<? extends ZclCommand>> commandMap = new ConcurrentSkipListMap<>();");
            out.println();
            for (final ZigBeeXmlCommand command : cluster.commands) {
                if (command.source.equalsIgnoreCase("server")) {
                    out.println("        commandMap.put(0x" + String.format("%04X", command.code) + ", "
                            + stringToUpperCamelCase(command.name) + ".class);");
                }
            }
            out.println();

            out.println("        return commandMap;");
            out.println("    }");
            out.println();
        }

        if (commandsClient != 0) {
            out.println("    @Override");
            out.println("    protected Map<Integer, Class<? extends ZclCommand>> initializeClientCommands() {");
            out.println(
                    "        Map<Integer, Class<? extends ZclCommand>> commandMap = new ConcurrentSkipListMap<>();");
            out.println();
            for (final ZigBeeXmlCommand command : cluster.commands) {
                if (command.source.equalsIgnoreCase("client")) {
                    out.println("        commandMap.put(0x" + String.format("%04X", command.code) + ", "
                            + stringToUpperCamelCase(command.name) + ".class);");
                }
            }
            out.println();

            out.println("        return commandMap;");
            out.println("    }");
        }

        out.println();
        out.println("    /**");
        out.println("     * Default constructor to create a " + cluster.name + " cluster.");
        out.println("     *");
        out.println("     * @param zigbeeEndpoint the {@link ZigBeeEndpoint} this cluster is contained within");
        out.println("     */");
        out.println("    public " + clusterClassName + "(final ZigBeeEndpoint zigbeeEndpoint) {");
        out.println("        super(zigbeeEndpoint, CLUSTER_ID, CLUSTER_NAME);");
        out.println("    }");

        if (!cluster.commands.isEmpty()) {
            out.println();
            out.println("    /**");
            out.println(
                    "     * Sends a {@link " + commandClassName
                            + "} and returns the {@link Future} to the result which will complete when the remote");
            out.println("     * device response is received, or the request times out.");
            out.println("     *");
            out.println("     * @param command the {@link " + commandClassName + "} to send");
            out.println("     * @return the command result future");
            out.println("     */");
            out.println("    public Future<CommandResult> sendCommand(" + commandClassName + " command) {");
            out.println("        return super.sendCommand(command);");
            out.println("    }");
            out.println();
            out.println("    /**");
            out.println(
                    "     * Sends a response to the command. This method sets all the common elements of the response based on the command -");
            out.println("     * eg transactionId, direction, address...");
            out.println("     *");
            out.println(
                    "     * @param command the {@link " + commandClassName + "} to which the response is being sent");
            out.println("     * @param response the {@link " + commandClassName + "} to send");
            out.println("     */");
            out.println(
                    "    public void sendResponse(" + commandClassName + " command, " + commandClassName
                            + " response) {");
            out.println("        super.sendResponse(command, response);");
            out.println("    }");
        }

        for (final ZigBeeXmlAttribute attribute : cluster.attributes) {
            if (attribute.side.equals("client")) {
                continue;
            }

            DataTypeMap zclDataType = ZclDataType.getDataTypeMapping().get(attribute.type);
            if (zclDataType == null) {
                throw new IllegalArgumentException(
                        "Unknown ZCL Type \"" + attribute.type + "\" for attribute \"" + attribute.name + "\".");
            }

            if (attribute.writable) {
                outputAttributeJavaDoc(out, "Set", attribute, zclDataType);
                out.println("    @Deprecated");
                if (attribute.arrayStart != null && attribute.arrayCount != null && attribute.arrayCount > 0) {
                    String name = attribute.name.replaceAll("\\{\\{count\\}\\}", "");
                    out.println("    public Future<CommandResult> set" + stringToUpperCamelCase(name).replace("_", "")
                            + "(final int arrayOffset, final " + getDataTypeClass(attribute) + " value) {");
                    name = attribute.name.replaceAll("\\{\\{count\\}\\}", Integer.toString(attribute.arrayStart));
                    out.println(
                            "        return write(serverAttributes.get(" + getEnum(name) + " + arrayOffset), value);");
                } else {
                    out.println("    public Future<CommandResult> set"
                            + stringToUpperCamelCase(attribute.name).replace("_", "") + "(final "
                            + getDataTypeClass(attribute) + " value) {");
                    out.println("        return write(serverAttributes.get(" + getEnum(attribute.name) + "), value);");
                }
                out.println("    }");
            }

            // if (attribute.attributeAccess.toLowerCase().contains("read")) {
            outputAttributeJavaDoc(out, "Get", attribute, zclDataType);
            out.println("    @Deprecated");
            if (attribute.arrayStart != null && attribute.arrayCount != null && attribute.arrayCount > 0) {
                String name = attribute.name.replaceAll("\\{\\{count\\}\\}", "");
                out.println("    public Future<CommandResult> get" + stringToUpperCamelCase(name).replace("_", "")
                        + "Async(final int arrayOffset) {");
                out.println("        if (arrayOffset < " + attribute.arrayStart + " || arrayOffset > "
                        + (attribute.arrayStart + attribute.arrayCount - 1) + ") {");
                out.println("            throw new IllegalArgumentException(\"arrayOffset out of bounds\");");
                out.println("        }");
                out.println();
                name = attribute.name.replaceAll("\\{\\{count\\}\\}", Integer.toString(attribute.arrayStart));
                out.println("        return read(serverAttributes.get(" + getEnum(name) + " + arrayOffset));");
            } else {
                out.println("    public Future<CommandResult> get"
                        + stringToUpperCamelCase(attribute.name).replace("_", "") + "Async() {");
                out.println("        return read(serverAttributes.get(" + getEnum(attribute.name) + "));");
            }
            out.println("    }");

            // TODO: Needs to document the counter
            outputAttributeJavaDoc(out, "Synchronously get", attribute, zclDataType);
            out.println("    @Deprecated");
            if (attribute.arrayStart != null && attribute.arrayCount != null && attribute.arrayCount > 0) {
                String name = attribute.name.replaceAll("\\{\\{count\\}\\}", "");
                out.println("    public " + getDataTypeClass(attribute) + " get"
                        + stringToUpperCamelCase(name).replace("_", "")
                        + "(final int arrayOffset, final long refreshPeriod) {");
                name = attribute.name.replaceAll("\\{\\{count\\}\\}", Integer.toString(attribute.arrayStart));
                out.println("        if (serverAttributes.get(" + getEnum(name) + " + arrayOffset"
                        + ").isLastValueCurrent(refreshPeriod)) {");
                out.println("            return (" + getDataTypeClass(attribute) + ") serverAttributes.get("
                        + getEnum(name) + " + arrayOffset).getLastValue();");
                out.println("        }");
                out.println();
                out.println("        return (" + getDataTypeClass(attribute) + ") readSync(serverAttributes.get("
                        + getEnum(name) + " + arrayOffset));");
            } else {
                out.println("    public " + getDataTypeClass(attribute) + " get"
                        + stringToUpperCamelCase(attribute.name).replace("_", "") + "(final long refreshPeriod) {");
                out.println("        if (serverAttributes.get(" + getEnum(attribute.name)
                        + ").isLastValueCurrent(refreshPeriod)) {");
                out.println("            return (" + getDataTypeClass(attribute) + ") serverAttributes.get("
                        + getEnum(attribute.name) + ").getLastValue();");
                out.println("        }");
                out.println();
                out.println("        return (" + getDataTypeClass(attribute) + ") readSync(serverAttributes.get("
                        + getEnum(attribute.name) + "));");
            }
            out.println("    }");
            // }

            if (!attribute.optional) {
                outputAttributeJavaDoc(out, "Set reporting for", attribute, zclDataType);
                if (attribute.arrayStart != null && attribute.arrayCount != null && attribute.arrayCount > 0) {
                    String name = attribute.name.replaceAll("\\{\\{count\\}\\}",
                            Integer.toString(attribute.arrayStart));
                    String offset;
                    if (attribute.arrayStep == null) {
                        offset = "arrayOffset - 1";
                    } else {
                        offset = "(arrayOffset - 1) * " + attribute.arrayStep;
                    }

                    out.println("    @Deprecated");
                    if (zclDataType.analogue) {
                        out.println("    public Future<CommandResult> set" + stringToUpperCamelCase(name)
                                + "Reporting(final int arrayOffset, final int minInterval, final int maxInterval, final Object reportableChange) {");
                        out.println("        return setReporting(serverAttributes.get(" + getEnum(name) + " + " + offset
                                + "), minInterval, maxInterval, reportableChange);");
                    } else {
                        out.println("    public Future<CommandResult> set" + stringToUpperCamelCase(name)
                                + "Reporting(final int arrayOffset, final int minInterval, final int maxInterval) {");
                        out.println("        return setReporting(serverAttributes.get(" + getEnum(name) + " + " + offset
                                + "), minInterval, maxInterval);");
                    }
                } else {
                    out.println("    @Deprecated");
                    if (zclDataType.analogue) {
                        out.println("    public Future<CommandResult> set" + stringToUpperCamelCase(attribute.name)
                                + "Reporting(final int minInterval, final int maxInterval, final Object reportableChange) {");
                        out.println("        return setReporting(serverAttributes.get(" + getEnum(attribute.name)
                                + "), minInterval, maxInterval, reportableChange);");
                    } else {
                        out.println("    public Future<CommandResult> set" + stringToUpperCamelCase(attribute.name)
                                + "Reporting(final int minInterval, final int maxInterval) {");
                        out.println("        return setReporting(serverAttributes.get(" + getEnum(attribute.name)
                                + "), minInterval, maxInterval);");
                    }
                }
                out.println("    }");
            }
        }

        for (final ZigBeeXmlCommand command : cluster.commands) {
            out.println();
            out.println("    /**");
            out.println("     * The " + command.name);
            if (!command.description.isEmpty()) {
                out.println("     * <p>");
                outputWithLinebreak(out, "    ", command.description);
            }
            out.println("     *");

            final LinkedList<ZigBeeXmlField> fields = new LinkedList<ZigBeeXmlField>(command.fields);
            for (final ZigBeeXmlField field : fields) {
                out.println("     * @param " + stringToLowerCamelCase(field.name) + " {@link " + getDataTypeClass(field)
                        + "} " + field.name);
            }

            out.println("     * @return the {@link Future<CommandResult>} command result future");
            out.println("     * @deprecated As of release 1.3.0.");
            out.println("     * Use extended ZclCommand class constructors to instantiate the command");
            out.println("     * and {@link #sendCommand} or {@link #sendResponse} to send the command.");
            out.println("     * This provides further control when sending the command by allowing customisation");
            out.println("     * of the command (for example by disabling the <i>DefaultResponse</i>.");
            out.println("     * <p>");
            out.println("     * e.g. replace <code>cluster." + stringToLowerCamelCase(command.name)
                    + "(parameters ...)</code>");
            out.println("     * with <code>cluster.sendCommand(new " + stringToLowerCamelCase(command.name)
                    + "(parameters ...))</code>");
            out.println("     */");
            out.println("    @Deprecated");
            out.print("    public Future<CommandResult> " + stringToLowerCamelCase(command.name) + "(");

            boolean first = true;
            for (final ZigBeeXmlField field : fields) {
                if (first == false) {
                    out.print(", ");
                }
                out.print(getDataTypeClass(field) + " " + stringToLowerCamelCase(field.name));
                first = false;
            }

            out.println(") {");
            if (fields.size() == 0) {
                out.println("        return sendCommand(new " + stringToUpperCamelCase(command.name) + "());");
            } else {
                out.println("        " + stringToUpperCamelCase(command.name) + " command = new "
                        + stringToUpperCamelCase(command.name) + "();");
                out.println();
                out.println("        // Set the fields");

                for (final ZigBeeXmlField field : fields) {
                    out.println("        command.set" + stringToUpperCamelCase(field.name) + "("
                            + stringToLowerCamelCase(field.name) + ");");
                }
                out.println();
                out.println("        return sendCommand(command);");
            }
            out.println("    }");
        }

        out.println("}");

        out.flush();
        out.close();

    }

    private void createInitializeAttributes(PrintStream out, String clusterName, List<ZigBeeXmlAttribute> attributes) {
        out.println("        Map<Integer, ZclAttribute> attributeMap = new ConcurrentSkipListMap<>();");

        if (attributes.size() != 0) {
            out.println();
            for (final ZigBeeXmlAttribute attribute : attributes) {

                if (attribute.arrayStart != null && attribute.arrayCount != null && attribute.arrayCount > 0) {
                    int arrayCount = attribute.arrayStart;
                    int arrayStep = attribute.arrayStep == null ? 1 : attribute.arrayStep;
                    for (int count = 0; count < attribute.arrayCount; count++) {
                        String name = attribute.name.replaceAll("\\{\\{count\\}\\}", Integer.toString(arrayCount));
                        out.println("        attributeMap.put(" + getEnum(name) + ", "
                                + defineAttribute(attribute, clusterName, name, 0) + ");");
                        arrayCount += arrayStep;
                    }
                } else {
                    out.println("        attributeMap.put(" + getEnum(attribute.name) + ", "
                            + defineAttribute(attribute, clusterName, attribute.name, 0) + ");");
                }
            }
        }
        out.println();
        out.println("        return attributeMap;");
        out.println("    }");
    }

    private String defineAttribute(ZigBeeXmlAttribute attribute, String clusterName, String attributeName, int count) {
        return "new ZclAttribute(this, " + getEnum(attributeName) + ", \"" + attributeName + "\", " + "ZclDataType."
                + attribute.type + ", " + !attribute.optional + ", " + true + ", " + attribute.writable + ", "
                + attribute.reportable + ")";
    }

    private String getEnum(String name) {
        return "ATTR_" + stringToConstantEnum(name);
    }
}

/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.autocode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zsmartsystems.zigbee.autocode.xml.ZigBeeXmlCluster;
import com.zsmartsystems.zigbee.autocode.xml.ZigBeeXmlCommand;
import com.zsmartsystems.zigbee.autocode.xml.ZigBeeXmlField;
import com.zsmartsystems.zigbee.autocode.xml.ZigBeeXmlMatcher;

/**
 *
 * @author Chris Jackson (zsmartsystems.com)
 *
 */
public class ZigBeeZclCommandGenerator extends ZigBeeBaseFieldGenerator {

    ZigBeeZclCommandGenerator(List<ZigBeeXmlCluster> clusters, String generatedDate, Map<String, String> dependencies) {
        this.generatedDate = generatedDate;
        this.dependencies = dependencies;

        for (ZigBeeXmlCluster cluster : clusters) {
            try {
                if (cluster.commands.isEmpty()) {
                    continue;
                }
                generateZclAbstractClusterCommand(cluster, packageRoot, new File(sourceRootPath));
                generateZclClusterCommands(cluster, packageRoot, new File(sourceRootPath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void generateZclClusterCommands(ZigBeeXmlCluster cluster, String packageRootPrefix, File sourceRootPath)
            throws IOException {

        for (final ZigBeeXmlCommand command : cluster.commands) {
            final String packageRoot = getZclClusterCommandPackage(cluster);
            final String packagePath = getPackagePath(sourceRootPath, packageRoot);
            final File packageFile = getPackageFile(packagePath);

            final String className = stringToUpperCamelCase(command.name);
            final String baseClassName = "Zcl" + stringToUpperCamelCase(cluster.name) + "Command";

            final PrintStream out = getClassOut(packageFile, className);

            // List of fields that are handled internally by super class
            List<String> reservedFields = new ArrayList<>();

            importsClear();

            for (final ZigBeeXmlField field : command.fields) {
                if (getDataTypeClass(field).startsWith("List")) {
                    importsAdd("java.util.List");
                }

                if (field.sizer != null) {
                    importsAdd("java.util.ArrayList");
                }
            }
            outputLicense(out);

            out.println("package " + packageRoot + ";");
            out.println();
            importsAdd("javax.annotation.Generated");

            if (command.response != null) {
                importsAdd(packageRootPrefix + ".transaction.ZigBeeTransactionMatcher");
                importsAdd(packageRootPrefix + ".ZigBeeCommand");

                importsAdd(packageRoot + "." + command.response.command);
            }

            String commandExtends = "";
            if (packageRoot.contains(".zcl.")) {
                importsAdd(packageRootPrefix + packageZclProtocol + ".ZclCommandDirection");
                commandExtends = baseClassName;
            } else {
                if (command.name.contains("Response")) {
                    commandExtends = "ZdoResponse";
                    reservedFields.add("status");
                } else {
                    commandExtends = "ZdoRequest";
                }
                importsAdd(packageRootPrefix + packageZdp + "." + commandExtends);
            }

            if (command.fields.size() > 0) {
                importsAdd(packageRootPrefix + packageZcl + ".ZclFieldSerializer");
                importsAdd(packageRootPrefix + packageZcl + ".ZclFieldDeserializer");
                importsAdd(packageRootPrefix + packageZclProtocol + ".ZclDataType");
            }

            for (final ZigBeeXmlField field : command.fields) {
                importsAddClass(field);
            }
            outputImports(out);

            out.println();
            out.println("/**");
            out.println(" * " + command.name + " value object class.");

            out.println(" * <p>");
            if (packageRoot.contains(".zcl.")) {
                out.println(" * Cluster: <b>" + cluster.name + "</b>. Command ID 0x"
                        + String.format("%02X", command.code) + " is sent <b>"
                        + (command.source.equals("client") ? "TO" : "FROM") + "</b> the server.");
                out.println(" * This command is " + ((cluster.name.equalsIgnoreCase("GENERAL"))
                        ? "a <b>generic</b> command used across the profile."
                        : "a <b>specific</b> command used for the " + cluster.name + " cluster."));
            }

            if (command.description.size() > 0) {
                out.println(" * <p>");
                outputWithLinebreak(out, "", command.description);
            }

            out.println(" * <p>");
            out.println(" * Code is auto-generated. Modifications may be overwritten!");
            out.println(" */");
            outputClassGenerated(out);
            out.print("public class " + className + " extends " + commandExtends);
            if (command.response != null) {
                out.print(" implements ZigBeeTransactionMatcher");
            }
            out.println(" {");

            if (commandExtends.startsWith("Zcl")) {
                if (!cluster.name.equalsIgnoreCase("GENERAL")) {
                    out.println("    /**");
                    out.println("     * The cluster ID to which this command belongs.");
                    out.println("     */");
                    out.println("    public static int CLUSTER_ID = 0x" + String.format("%04X", cluster.code) + ";");
                    out.println();
                }
                out.println("    /**");
                out.println("     * The command ID.");
                out.println("     */");
                out.println("    public static int COMMAND_ID = 0x" + String.format("%02X", command.code) + ";");
                out.println();
            } else {
                out.println("    /**");
                out.println("     * The ZDO cluster ID.");
                out.println("     */");
                out.println("    public static int CLUSTER_ID = 0x" + String.format("%04X", command.code) + ";");
                out.println();
            }

            for (final ZigBeeXmlField field : command.fields) {
                if (reservedFields.contains(stringToLowerCamelCase(field.name))) {
                    continue;
                }
                if (getAutoSized(command.fields, stringToLowerCamelCase(field.name)) != null) {
                    continue;
                }

                out.println("    /**");
                out.println("     * " + field.name + " command message field.");
                if (field.description.size() != 0) {
                    out.println("     * <p>");
                    outputWithLinebreak(out, "    ", field.description);
                }
                out.println("     */");
                out.println("    private " + getDataTypeClass(field) + " " + stringToLowerCamelCase(field.name) + ";");
                out.println();
            }

            out.println("    /**");
            out.println("     * Default constructor.");
            out.println("     *");
            out.println(
                    "     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default contructor and setters.");
            out.println("     */");
            if (!command.fields.isEmpty()) {
                out.println("    @Deprecated");
            }
            out.println("    public " + className + "() {");
            if (!cluster.name.equalsIgnoreCase("GENERAL")) {
                out.println("        clusterId = CLUSTER_ID;");
            }
            if (commandExtends.startsWith("Zcl")) {
                out.println("        commandId = COMMAND_ID;");
                out.println("        genericCommand = "
                        + ((cluster.name.equalsIgnoreCase("GENERAL")) ? "true" : "false") + ";");
                out.println("        commandDirection = ZclCommandDirection."
                        + (command.source.equals("client") ? "CLIENT_TO_SERVER" : "SERVER_TO_CLIENT") + ";");
            }
            out.println("    }");
            out.println();

            if (!command.fields.isEmpty()) {
                out.println("    /**");

                out.println("     * Constructor providing all required parameters.");
                out.println("     *");
                for (final ZigBeeXmlField field : command.fields) {
                    if (getAutoSized(command.fields, stringToLowerCamelCase(field.name)) != null) {
                        continue;
                    }
                    out.println(
                            "     * @param " + stringToLowerCamelCase(field.name) + " {@link " + getDataTypeClass(field)
                                    + "} " + field.name);
                }
                out.println("     */");
                out.println("    public " + className + "(");

                boolean first = true;
                for (final ZigBeeXmlField field : command.fields) {
                    if (getAutoSized(command.fields, stringToLowerCamelCase(field.name)) != null) {
                        continue;
                    }

                    if (!first) {
                        out.println(",");
                    }
                    out.print("            " + getDataTypeClass(field) + " " + stringToLowerCamelCase(field.name));
                    first = false;
                }

                out.println(") {");
                out.println();

                if (!cluster.name.equalsIgnoreCase("GENERAL")) {
                    out.println("        clusterId = CLUSTER_ID;");
                }
                if (commandExtends.startsWith("Zcl")) {
                    out.println("        commandId = COMMAND_ID;");
                    out.println("        genericCommand = "
                            + ((cluster.name.equalsIgnoreCase("GENERAL")) ? "true" : "false") + ";");
                    out.println("        commandDirection = ZclCommandDirection."
                            + (command.source.equals("client") ? "CLIENT_TO_SERVER" : "SERVER_TO_CLIENT") + ";");
                }

                first = true;
                for (final ZigBeeXmlField field : command.fields) {
                    if (getAutoSized(command.fields, stringToLowerCamelCase(field.name)) != null) {
                        continue;
                    }

                    if (first) {
                        out.println();
                    }

                    out.println("        this." + stringToLowerCamelCase(field.name) + " = "
                            + stringToLowerCamelCase(field.name) + ";");
                    first = false;
                }

                out.println("    }");
            }

            if (cluster.name.equalsIgnoreCase("GENERAL")) {
                out.println();
                out.println("    /**");
                out.println("     * Sets the cluster ID for <i>generic</i> commands. {@link " + className
                        + "} is a <i>generic</i> command.");
                out.println("     * <p>");
                out.println(
                        "     * For commands that are not <i>generic</i>, this method will do nothing as the cluster ID is fixed.");
                out.println("     * To test if a command is <i>generic</i>, use the {@link #isGenericCommand} method.");
                out.println("     *");
                out.println(
                        "     * @param clusterId the cluster ID used for <i>generic</i> commands as an {@link Integer}");

                out.println("     */");
                out.println("    @Override");
                out.println("    public void setClusterId(Integer clusterId) {");
                out.println("        this.clusterId = clusterId;");
                out.println("    }");
            }

            generateFields(out, commandExtends, className, command.fields, reservedFields);

            if (command.response != null) {
                out.println();
                out.println("    @Override");
                out.println("    public boolean isTransactionMatch(ZigBeeCommand request, ZigBeeCommand response) {");
                if (command.response.matchers.isEmpty()) {
                    out.println("        return (response instanceof " + command.response.command + ")");
                    out.println("                && ((ZdoRequest) request).getDestinationAddress().equals((("
                            + command.response.command + ") response).getSourceAddress());");
                } else {
                    out.println("        if (!(response instanceof " + command.response.command + ")) {");
                    out.println("            return false;");
                    out.println("        }");
                    out.println();
                    out.print("        return ");

                    boolean first = true;
                    for (ZigBeeXmlMatcher matcher : command.response.matchers) {
                        if (first == false) {
                            out.println();
                            out.print("                && ");
                        }
                        first = false;
                        out.println("(((" + stringToUpperCamelCase(command.name) + ") request).get"
                                + matcher.commandField + "()");
                        out.print("                .equals(((" + command.response.command + ") response).get"
                                + matcher.responseField + "()))");
                    }
                    out.println(";");
                }
                out.println("    }");
            }

            generateToString(out, className, command.fields, reservedFields);

            out.println();
            out.println("}");

            out.flush();
            out.close();
        }

    }

    private void generateZclAbstractClusterCommand(ZigBeeXmlCluster cluster, String packageRootPrefix,
            File sourceRootPath) throws FileNotFoundException, UnsupportedEncodingException {
        final String packageRoot = getZclClusterCommandPackage(cluster);
        final String packagePath = getPackagePath(sourceRootPath, packageRoot);
        final File packageFile = getPackageFile(packagePath);

        final String clusterClassName = "Zcl" + stringToUpperCamelCase(cluster.name) + "Cluster";
        final String commandClassName = "Zcl" + stringToUpperCamelCase(cluster.name) + "Command";
        final PrintStream out = getClassOut(packageFile, commandClassName);

        importsClear();
        importsAdd(packageRootPrefix + packageZcl + ".ZclCommand");
        importsAdd("javax.annotation.Generated");

        outputLicense(out);

        out.println("package " + packageRoot + ";");
        out.println();
        outputImports(out);

        out.println("/**");
        out.println(" * Abstract base command class for all commands in the <b>" + cluster.name
                + "</b> cluster (<i>Cluster ID "
                + String.format("0x%04X", cluster.code) + "</i>).");
        out.println(" * All commands sent through the {@link " + clusterClassName + "} must extend this class.");

        out.println(" * <p>");
        out.println(" * Code is auto-generated. Modifications may be overwritten!");

        out.println(" */");
        outputClassGenerated(out);

        out.println("public abstract class " + commandClassName + " extends ZclCommand {");
        out.println("}");

        out.flush();
        out.close();
    }

}

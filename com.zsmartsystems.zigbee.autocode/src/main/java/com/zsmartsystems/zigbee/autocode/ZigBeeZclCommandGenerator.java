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
                generateZclClusterCommands(cluster, packageRoot, new File(sourceRootPath));
            } catch (IOException e) {
                // TODO Auto-generated catch block
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
            final PrintWriter out = getClassOut(packageFile, className);

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
                importsAdd(packageRootPrefix + packageZcl + ".ZclCommand");
                importsAdd(packageRootPrefix + packageZclProtocol + ".ZclCommandDirection");
                commandExtends = "ZclCommand";
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
                out.println(" * Cluster: <b>" + cluster.name + "</b>. Command is sent <b>"
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
            out.println("     */");
            out.println("    public " + className + "() {");
            if (commandExtends.equals("ZclCommand")) {
                out.println("        genericCommand = "
                        + ((cluster.name.equalsIgnoreCase("GENERAL")) ? "true" : "false") + ";");

                if (!cluster.name.equalsIgnoreCase("GENERAL")) {
                    out.println("        clusterId = 0x" + String.format("%04X", cluster.code) + ";");
                }

                out.println("        commandId = " + command.code + ";");
                out.println("        commandDirection = ZclCommandDirection."
                        + (command.source.equals("client") ? "CLIENT_TO_SERVER" : "SERVER_TO_CLIENT") + ";");
            } else {
                out.println("        clusterId = 0x" + String.format("%04X", command.code) + ";");
            }
            out.println("    }");

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

}

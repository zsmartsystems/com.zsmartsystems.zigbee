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
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.zsmartsystems.zigbee.autocode.xml.ZigBeeXmlCluster;
import com.zsmartsystems.zigbee.autocode.xml.ZigBeeXmlCommand;

/**
 *
 * @author Chris Jackson (zsmartsystems.com)
 *
 */
public class ZigBeeZclCommandTypeGenerator extends ZigBeeBaseClassGenerator {

    ZigBeeZclCommandTypeGenerator(List<ZigBeeXmlCluster> clusters, String generatedDate,
            Map<String, String> dependencies) {
        this.generatedDate = generatedDate;
        this.dependencies = dependencies;

        try {
            generateZclClusterCommands(clusters, packageRoot, new File(sourceRootPath));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void generateZclClusterCommands(List<ZigBeeXmlCluster> clusters, String packageRootPrefix,
            File sourceRootPath) throws IOException {

        final String className = "ZclCommandType";

        final String packageRoot = packageRootPrefix + packageZclProtocol;
        final String packagePath = getPackagePath(sourceRootPath, packageRoot);
        final File packageFile = getPackageFile(packagePath);

        final PrintWriter out = getClassOut(packageFile, className);

        Map<String, CommandId> commandEnum = new TreeMap<>();

        outputLicense(out);

        importsClear();
        out.println("package " + packageRoot + ";");
        out.println();
        importsAdd("javax.annotation.Generated");
        importsAdd("java.lang.reflect.Constructor");
        importsAdd(packageRootPrefix + packageZcl + ".ZclCommand");
        importsAdd(packageRootPrefix + packageZclProtocol + ".ZclCommandDirection");

        // Produce an ordered list of clusters/commands and add the imports...
        for (final ZigBeeXmlCluster cluster : clusters) {
            for (ZigBeeXmlCommand command : cluster.commands) {
                importsAdd(getZclClusterCommandPackage(cluster) + "." + stringToUpperCamelCase(command.name));
                commandEnum.put(getZclClusterCommandPackage(cluster) + "." + stringToUpperCamelCase(command.name),
                        new CommandId(cluster, command));
            }
        }

        outputImports(out);
        out.println();

        // outputClassJavaDoc(out, "Enumeration of ZigBee Cluster Library commands");
        outputClassGenerated(out);
        out.println("public enum " + className + " {");
        boolean first = true;
        for (CommandId commandId : commandEnum.values()) {
            ZigBeeXmlCluster cluster = commandId.cluster;
            ZigBeeXmlCommand command = commandId.command;
            if (!first) {
                out.println(",");
            }
            first = false;

            String commandType = stringToConstant(command.name);
            String commandClass = stringToUpperCamelCase(command.name);
            out.println("    /**");
            out.println("     * " + commandType + ": " + command.name);
            out.println("     * <p>");
            out.println("     * See {@link " + commandClass + "}");
            out.println("     */");

            out.print("    " + commandType + "(" + String.format("0x%04X", cluster.code) + ", " + command.code + ", "
                    + commandClass + ".class" + ", ZclCommandDirection."
                    + (command.source.equals("client") ? "CLIENT_TO_SERVER" : "SERVER_TO_CLIENT") + ")");
        }
        out.println(";");
        out.println();

        out.println("    private final int commandId;");
        out.println("    private final int clusterType;");
        out.println("    private final Class<? extends ZclCommand> commandClass;");
        // out.println(" private final String label;");
        out.println("    private final ZclCommandDirection direction;");
        out.println();
        out.println("    " + className
                + "(final int clusterType, final int commandId, final Class<? extends ZclCommand> commandClass, final ZclCommandDirection direction) {");
        out.println("        this.clusterType = clusterType;");
        out.println("        this.commandId = commandId;");
        out.println("        this.commandClass = commandClass;");
        // out.println(" this.label = label;");
        out.println("        this.direction = direction;");
        out.println("    }");
        out.println();

        out.println("    public int getClusterType() {");
        out.println("        return clusterType;");
        out.println("    }");
        out.println();
        out.println("    public int getId() {");
        out.println("        return commandId;");
        out.println("    }");
        out.println();
        // out.println(" public String getLabel() { return label; }");
        out.println("    public boolean isGeneric() {");
        out.println("        return clusterType==0xFFFF;");
        out.println("    }");
        out.println();
        out.println("    public ZclCommandDirection getDirection() {");
        out.println("        return direction;");
        out.println("    }");
        out.println();
        out.println("    public Class<? extends ZclCommand> getCommandClass() {");
        out.println("        return commandClass;");
        out.println("    }");
        out.println();
        out.println(
                "        public static ZclCommandType getCommandType(final int clusterType, final int commandId,\n");
        out.println("            ZclCommandDirection direction) {\n");
        out.println("        for (final ZclCommandType value : values()) {\n");
        out.println(
                "            if (value.direction == direction && value.clusterType == clusterType && value.commandId == commandId) {\n");
        out.println("                return value;\n");
        out.println("            }\n");
        out.println("        }\n");
        out.println("        return null;\n");
        out.println("    }");

        out.println();
        out.println("    public static ZclCommandType getGeneric(final int commandId) {");
        out.println("        for (final ZclCommandType value : values()) {");
        out.println("            if (value.clusterType == 0xFFFF && value.commandId == commandId) {");
        out.println("                return value;");
        out.println("            }");
        out.println("        }");
        out.println("        return null;");
        out.println("    }");

        out.println();
        out.println("    public ZclCommand instantiateCommand() {");
        out.println("        Constructor<? extends ZclCommand> cmdConstructor;");
        out.println("        try {");
        out.println("            cmdConstructor = commandClass.getConstructor();");
        out.println("            return cmdConstructor.newInstance();");
        out.println("        } catch (Exception e) {");
        out.println("            // logger.debug(\"Error instantiating cluster command {}\", this);");
        out.println("        }");
        out.println("        return null;");
        out.println("    }");

        out.println("}");

        out.flush();
        out.close();
    }

    private class CommandId {
        ZigBeeXmlCluster cluster;
        ZigBeeXmlCommand command;

        public CommandId(ZigBeeXmlCluster cluster, ZigBeeXmlCommand command) {
            this.cluster = cluster;
            this.command = command;
        }
    }
}

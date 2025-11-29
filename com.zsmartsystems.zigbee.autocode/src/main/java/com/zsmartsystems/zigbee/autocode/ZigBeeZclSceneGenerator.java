/**
 * Copyright (c) 2016-2025 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.autocode;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;

import com.zsmartsystems.zigbee.autocode.ZclDataType.DataTypeMap;
import com.zsmartsystems.zigbee.autocode.xml.ZigBeeXmlAttribute;
import com.zsmartsystems.zigbee.autocode.xml.ZigBeeXmlCluster;

/**
 *
 * @author Chris Jackson (zsmartsystems.com)
 *
 */
public class ZigBeeZclSceneGenerator extends ZigBeeBaseClassGenerator {

    ZigBeeZclSceneGenerator(List<ZigBeeXmlCluster> clusters, String generatedDate, Map<String, String> dependencies) {
        this.generatedDate = generatedDate;
        this.dependencies = dependencies;

        for (ZigBeeXmlCluster cluster : clusters) {
            try {
                generateZclSceneExtension(cluster, packageRoot, new File(sourceRootPath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void generateZclSceneExtension(ZigBeeXmlCluster cluster, String packageRootPrefix, File sourceRootPath)
            throws IOException {

        if (cluster.scenes == null) {
            return;
        }
        importsClear();

        for (final String attributeName : cluster.scenes.extensionField.attributes) {
            importsAddClass(getSceneAttribute(cluster, attributeName));
        }

        final String packageRoot = getZclClusterCommandPackage(cluster);
        final String packagePath = getPackagePath(sourceRootPath, packageRoot);
        final File packageFile = getPackageFile(packagePath);

        final String clusterClassName = "Zcl" + stringToUpperCamelCase(cluster.name) + "Cluster";
        final String sceneClassName = "Zcl" + stringToUpperCamelCase(cluster.name) + "ExtensionField";
        final PrintStream out = getClassOut(packageFile, sceneClassName);

        outputLicense(out);

        out.println("package " + packageRoot + ";");
        out.println();

        importsAdd("javax.annotation.Generated");

        importsAdd(packageRootPrefix + ".zcl.clusters." + clusterClassName);
        importsAdd(packageRootPrefix + ".serialization.ZigBeeDeserializer");
        importsAdd(packageRootPrefix + ".serialization.ZigBeeSerializer");
        importsAdd(packageRootPrefix + ".zcl.protocol.ZclDataType");
        importsAdd(packageRootPrefix + ".zcl.field.ExtensionFieldSet");

        outputImports(out);

        out.println();
        out.println("/**");
        out.println(
                " * <b>" + cluster.name + "</b> cluster {@link ExtensionFieldSet} implementation for use with scenes.");

        out.println(" * <p>");
        out.println(" * Code is auto-generated. Modifications may be overwritten!");

        out.println(" */");
        // outputClassJavaDoc(out);
        outputClassGenerated(out);
        out.println("public class " + sceneClassName + " extends ExtensionFieldSet {");

        for (final String attributeName : cluster.scenes.extensionField.attributes) {
            ZigBeeXmlAttribute attribute = getSceneAttribute(cluster, attributeName);

            out.println();
            if (!attribute.description.isEmpty()) {
                out.println("    /**");
                outputWithLinebreak(out, "    ", attribute.description);
                out.println("     */");
            }
            out.println("    private " + getDataTypeClass(attribute) + " "
                    + stringToLowerCamelCase(attribute.name) + ";");
        }

        out.println();
        out.println("    /**");
        out.println("     * Default constructor to create a " + cluster.name + " {@link ExtensionFieldSet}.");
        out.println("     *");
        for (final String attributeName : cluster.scenes.extensionField.attributes) {
            ZigBeeXmlAttribute attribute = getSceneAttribute(cluster, attributeName);
            out.println(
                    "     * @param " + stringToLowerCamelCase(attribute.name) + " {@link " + getDataTypeClass(attribute)
                            + "} " + attribute.name);
        }
        out.println("     */");
        out.println("    public " + sceneClassName + "(");

        boolean first = true;
        for (final String attributeName : cluster.scenes.extensionField.attributes) {
            ZigBeeXmlAttribute attribute = getSceneAttribute(cluster, attributeName);

            if (!first) {
                out.println(",");
            }
            out.print("            " + getDataTypeClass(attribute) + " " + stringToLowerCamelCase(attribute.name));
            first = false;
        }

        out.println(") {");
        out.println("        clusterId = " + clusterClassName + ".CLUSTER_ID;");
        out.println();
        for (final String attributeName : cluster.scenes.extensionField.attributes) {
            ZigBeeXmlAttribute attribute = getSceneAttribute(cluster, attributeName);

            out.println("        this." + stringToLowerCamelCase(attribute.name) + " = "
                    + stringToLowerCamelCase(attribute.name) + ";");
        }
        out.println("    }");

        for (final String attributeName : cluster.scenes.extensionField.attributes) {
            ZigBeeXmlAttribute attribute = getSceneAttribute(cluster, attributeName);

            DataTypeMap zclDataType = ZclDataType.getDataTypeMapping().get(attribute.type);
            if (zclDataType == null) {
                throw new IllegalArgumentException(
                        "Unknown ZCL Type \"" + attribute.type + "\" for attribute \"" + attribute.name + "\".");
            }

            out.println();
            if (!attribute.description.isEmpty()) {
                out.println("    /**");
                outputWithLinebreak(out, "    ", attribute.description);
                out.println("     *");
                out.println("     * @return the " + attribute.name);
                out.println("     */");
            }
            out.println("    public " + getDataTypeClass(attribute) + " get"
                    + stringToUpperCamelCase(attribute.name).replace("_", "") + "() {");
            out.println("        return " + stringToLowerCamelCase(attribute.name) + ";");
            out.println("    }");
        }

        out.println();

        int serialiserLength = 0;
        for (final String attributeName : cluster.scenes.extensionField.attributes) {
            ZigBeeXmlAttribute attribute = getSceneAttribute(cluster, attributeName);

            serialiserLength += ZclDataType.getDataTypeMapping().get(attribute.type).length;
        }

        out.println("    @Override");
        out.println("    public void serialize(final ZigBeeSerializer serializer) {");
        out.println("        serializer.appendZigBeeType(clusterId, ZclDataType.UNSIGNED_16_BIT_INTEGER);");
        out.println(
                "        serializer.appendZigBeeType(" + serialiserLength + ", ZclDataType.UNSIGNED_8_BIT_INTEGER);");
        for (final String attributeName : cluster.scenes.extensionField.attributes) {
            ZigBeeXmlAttribute attribute = getSceneAttribute(cluster, attributeName);

            out.println("        serializer.appendZigBeeType(" + stringToLowerCamelCase(attribute.name)
                    + ", ZclDataType." + attribute.type + ");");
        }

        out.println("    }");

        int length = 0;
        out.println();
        out.println("    @Override");
        out.println("    public void deserialize(final ZigBeeDeserializer deserializer) {");
        out.println("        clusterId = deserializer.readZigBeeType(ZclDataType.UNSIGNED_16_BIT_INTEGER);");
        out.println("        int size = deserializer.readZigBeeType(ZclDataType.UNSIGNED_8_BIT_INTEGER);");
        for (final String attributeName : cluster.scenes.extensionField.attributes) {
            ZigBeeXmlAttribute attribute = getSceneAttribute(cluster, attributeName);

            length += ZclDataType.getDataTypeMapping().get(attribute.type).length;
            out.println("        if (size >= " + length + ") {");
            out.println("            " + stringToLowerCamelCase(attribute.name) +
                    " = deserializer.readZigBeeType(ZclDataType." + attribute.type + ");");
            out.println("        }");
        }
        out.println("    }");

        out.println();
        out.println("    @Override");
        out.println("    public String toString() {");
        out.println("        StringBuilder builder = new StringBuilder(100);");
        out.println("        builder.append(\"" + sceneClassName + " [clusterId=\");");
        out.println("        builder.append(clusterId);");

        for (final String attributeName : cluster.scenes.extensionField.attributes) {
            ZigBeeXmlAttribute attribute = getSceneAttribute(cluster, attributeName);

            out.println("        builder.append(\", " + stringToLowerCamelCase(attribute.name) + "=\");");
            out.println("        builder.append(" + stringToLowerCamelCase(attribute.name) + ");");
        }
        out.println("        builder.append(']');");
        out.println("        return builder.toString();");
        out.println("    }");

        out.println("}");

        out.flush();
        out.close();
    }

    private ZigBeeXmlAttribute getSceneAttribute(ZigBeeXmlCluster cluster, String name) {
        for (final ZigBeeXmlAttribute attribute : cluster.attributes) {
            if (attribute.name.equals(name)) {
                return attribute;
            }
        }

        System.out.println("Can't find scene attribute '" + name + "' in cluster '" + cluster.name + "'");
        return null;
    }
}

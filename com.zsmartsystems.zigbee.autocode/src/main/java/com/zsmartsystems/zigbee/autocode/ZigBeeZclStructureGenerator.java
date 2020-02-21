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
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.zsmartsystems.zigbee.autocode.xml.ZigBeeXmlCluster;
import com.zsmartsystems.zigbee.autocode.xml.ZigBeeXmlField;
import com.zsmartsystems.zigbee.autocode.xml.ZigBeeXmlStructure;

/**
 *
 * @author Chris Jackson (zsmartsystems.com)
 *
 */
public class ZigBeeZclStructureGenerator extends ZigBeeBaseFieldGenerator {

    ZigBeeZclStructureGenerator(List<ZigBeeXmlCluster> clusters, String generatedDate,
            Map<String, String> dependencies) {
        this.generatedDate = generatedDate;
        this.dependencies = dependencies;

        for (ZigBeeXmlCluster cluster : clusters) {
            try {
                generateZclClusterStructures(cluster, packageRoot, new File(sourceRootPath));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private void generateZclClusterStructures(ZigBeeXmlCluster cluster, String packageRootPrefix, File sourceRootPath)
            throws IOException {
        if (cluster.structures == null) {
            return;
        }

        for (final ZigBeeXmlStructure structure : cluster.structures) {
            final String packageRoot = packageRootPrefix + packageZclProtocolCommand + "."
                    + stringToLowerCamelCase(cluster.name).replace("_", "").toLowerCase();
            final String packagePath = getPackagePath(sourceRootPath, packageRoot);
            final File packageFile = getPackageFile(packagePath);

            final String className = structure.className;
            final PrintStream out = getClassOut(packageFile, className);

            outputLicense(out);

            importsClear();
            out.println("package " + packageRoot + ";");

            importsAdd("javax.annotation.Generated");
            // importsAdd("java.util.HashMap");
            // importsAdd("java.util.Map");

            importsAdd(packageRootPrefix + ".serialization.ZigBeeSerializable");
            importsAdd(packageRootPrefix + packageZcl + ".ZclFieldSerializer");
            importsAdd(packageRootPrefix + packageZcl + ".ZclFieldSerializer");
            importsAdd(packageRootPrefix + packageZcl + ".ZclFieldDeserializer");
            importsAdd(packageRootPrefix + packageZclProtocol + ".ZclDataType");

            for (final ZigBeeXmlField field : structure.fields) {
                // String packageName;
                // if (getDataTypeClass(field).contains("Descriptor")) {
                // packageName = packageZdpDescriptors;
                // } else {
                // packageName = packageZclField;
                // }
                importsAddClass(field);
            }

            outputImports(out);

            out.println();
            out.println("/**");
            out.println(" * " + structure.name + " structure implementation.");

            if (structure.description.size() > 0) {
                out.println(" * <p>");
                outputWithLinebreak(out, "", structure.description);
            }

            out.println(" * <p>");
            out.println(" * Code is auto-generated. Modifications may be overwritten!");

            out.println(" */");
            outputClassGenerated(out);
            out.println("public class " + className + " implements ZigBeeSerializable {");

            for (final ZigBeeXmlField field : structure.fields) {
                out.println("    /**");
                out.println("     * " + field.name + " structure field.");
                if (field.description.size() != 0) {
                    out.println("     * <p>");
                    outputWithLinebreak(out, "    ", field.description);
                }
                out.println("     */");
                out.println("    private " + getDataTypeClass(field) + " " + stringToLowerCamelCase(field.name) + ";");
                out.println();
            }
            out.println();

            out.println("    /**");
            out.println("     * Default constructor.");
            out.println("     *");
            out.println(
                    "     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default contructor and setters.");
            out.println("     */");
            out.println("    @Deprecated");
            out.println("    public " + className + "() {");
            out.println("    }");
            out.println();

            out.println("    /**");
            out.println("     * Constructor providing all required parameters.");
            out.println("     *");
            for (final ZigBeeXmlField field : structure.fields) {
                if (getAutoSized(structure.fields, stringToLowerCamelCase(field.name)) != null) {
                    continue;
                }
                out.println(
                        "     * @param " + stringToLowerCamelCase(field.name) + " {@link " + getDataTypeClass(field)
                                + "} " + field.name);
            }
            out.println("     */");
            out.println("    public " + className + "(");

            boolean first = true;
            for (final ZigBeeXmlField field : structure.fields) {
                if (getAutoSized(structure.fields, stringToLowerCamelCase(field.name)) != null) {
                    continue;
                }

                if (!first) {
                    out.println(",");
                }
                out.print("            " + getDataTypeClass(field) + " " + stringToLowerCamelCase(field.name));
                first = false;
            }

            out.println(") {");

            first = true;
            for (final ZigBeeXmlField field : structure.fields) {
                if (getAutoSized(structure.fields, stringToLowerCamelCase(field.name)) != null) {
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

            generateFields(out, "ZigBeeSerializable", className, structure.fields, Collections.emptyList());
            generateToString(out, className, structure.fields, Collections.emptyList());

            out.println("}");

            out.flush();
            out.close();
        }
    }

}

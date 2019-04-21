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
            final PrintWriter out = getClassOut(packageFile, className);

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

            generateFields(out, "ZigBeeSerializable", className, structure.fields, Collections.emptyList());
            generateToString(out, className, structure.fields, Collections.emptyList());

            out.println("}");

            out.flush();
            out.close();
        }
    }

}

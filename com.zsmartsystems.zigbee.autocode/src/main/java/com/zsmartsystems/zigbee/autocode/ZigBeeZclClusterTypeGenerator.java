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
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.zsmartsystems.zigbee.autocode.xml.ZigBeeXmlCluster;

/**
 *
 * @author Chris Jackson (zsmartsystems.com)
 *
 */
public class ZigBeeZclClusterTypeGenerator extends ZigBeeBaseClassGenerator {
    ZigBeeZclClusterTypeGenerator(List<ZigBeeXmlCluster> clusters, String generatedDate,
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

        final String className = "ZclClusterType";

        final String packageRoot = packageRootPrefix + packageZclProtocol;
        final String packagePath = getPackagePath(sourceRootPath, packageRoot);
        final File packageFile = getPackageFile(packagePath);

        final PrintStream out = getClassOut(packageFile, className);

        outputLicense(out);
        importsClear();

        Map<Integer, ZigBeeXmlCluster> clusterEnum = new TreeMap<>();
        for (final ZigBeeXmlCluster cluster : clusters) {
            // Suppress GENERAL cluster as it's not really a cluster!
            if (cluster.name.equalsIgnoreCase("GENERAL")) {
                continue;
            }
            clusterEnum.put(cluster.code, cluster);
            importsAdd(
                    packageRootPrefix + packageZclCluster + ".Zcl" + stringToUpperCamelCase(cluster.name) + "Cluster");
        }

        out.println("package " + packageRoot + ";");
        out.println();
        importsAdd(packageRootPrefix + packageZcl + ".ZclCluster");
        importsAdd("java.util.Map");
        importsAdd("java.util.concurrent.ConcurrentHashMap");
        importsAdd("javax.annotation.Generated");
        outputImports(out);
        out.println();
        out.println("/**");
        out.println(" * Enumeration of ZigBee Clusters\n" + " * <p>\n"
                + " * Code is auto-generated. Modifications may be overwritten!\n" + " *\n"
                + " * @author Chris Jackson");
        out.println(" */");
        outputClassGenerated(out);
        out.println("public enum " + className + " {");

        boolean first = true;
        for (final ZigBeeXmlCluster cluster : clusterEnum.values()) {
            if (first == false) {
                out.println(",");
            }
            first = false;
            out.print("    " + stringToConstant(cluster.name) + "(" + String.format("0x%04X", cluster.code) + ", Zcl"
                    + stringToUpperCamelCase(cluster.name) + "Cluster.class, \"" + cluster.name + "\")");
        }
        out.println(";");

        out.println();
        out.println("    private static final Map<Integer, ZclClusterType> idValueMap = new ConcurrentHashMap<>();");
        out.println();
        out.println("    private final int clusterId;");
        out.println("    private final String label;");
        out.println("    private final Class<? extends ZclCluster> clusterClass;");
        out.println();
        out.println("    " + className
                + "(final int clusterId, final Class<? extends ZclCluster>clusterClass, final String label) {");
        out.println("        this.clusterId = clusterId;");
        out.println("        this.clusterClass = clusterClass;");
        out.println("        this.label = label;");
        out.println("    }");
        out.println();
        out.println("    static {");
        out.println("        for (final ZclClusterType value : values()) {");
        out.println("            idValueMap.put(value.clusterId, value);");
        out.println("        }");
        out.println("    }");
        out.println();
        out.println("    public int getId() {");
        out.println("        return clusterId;");
        out.println("    }");
        out.println();
        out.println("    public String getLabel() {");
        out.println("        return label;");
        out.println("    }");
        out.println();
        // out.println(" public String toString() {");
        // out.println(" return label;");
        // out.println(" }");
        // out.println();
        out.println("    public Class<? extends ZclCluster> getClusterClass() {");
        out.println("        return clusterClass;");
        out.println("    }");
        out.println();
        out.println("    public static ZclClusterType getValueById(final int clusterId) {");
        out.println("        return idValueMap.get(clusterId);");
        out.println("    }");
        out.println();
        out.println("}");

        out.flush();
        out.close();

    }

}

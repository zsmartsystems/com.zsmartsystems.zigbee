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
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.zsmartsystems.zigbee.autocode.xml.ZigBeeXmlCluster;
import com.zsmartsystems.zigbee.autocode.xml.ZigBeeXmlConstant;

/**
 *
 * @author Chris Jackson (zsmartsystems.com)
 *
 */
public class ZigBeeZclConstantGenerator extends ZigBeeBaseClassGenerator {

    ZigBeeZclConstantGenerator(List<ZigBeeXmlCluster> clusters, String generatedDate,
            Map<String, String> dependencies) {
        this.generatedDate = generatedDate;
        this.dependencies = dependencies;

        for (ZigBeeXmlCluster cluster : clusters) {
            try {
                generateZclClusterConstants(cluster, packageRoot, new File(sourceRootPath));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    ZigBeeZclConstantGenerator(ZigBeeXmlConstant constant, String generatedDate) {
        this.generatedDate = generatedDate;
        String packageRoot = "com.zsmartsystems.zigbee";

        try {
            generateZclConstant(new File(sourceRootPath), packageRoot, constant);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void generateZclClusterConstants(ZigBeeXmlCluster cluster, String packageRootPrefix, File sourceRootPath)
            throws IOException {
        if (cluster.constants == null) {
            return;
        }

        for (final ZigBeeXmlConstant constant : cluster.constants) {
            final String packageRoot = packageRootPrefix + packageZclProtocolCommand + "."
                    + stringToLowerCamelCase(cluster.name).replace("_", "").toLowerCase();

            generateZclConstant(sourceRootPath, packageRoot, constant);
        }
    }

    private void generateZclConstant(File sourceRootPath, String packageRoot, ZigBeeXmlConstant constant)
            throws FileNotFoundException, UnsupportedEncodingException {
        final String packagePath = getPackagePath(sourceRootPath, packageRoot);
        final File packageFile = getPackageFile(packagePath);

        final String className = constant.className;
        final PrintStream out = getClassOut(packageFile, className);

        outputLicense(out);

        importsClear();
        out.println("package " + packageRoot + ";");
        out.println();

        importsAdd("javax.annotation.Generated");
        importsAdd("java.util.HashMap");
        importsAdd("java.util.Map");

        outputImports(out);

        out.println("/**");
        out.println(" * " + constant.name + " value enumeration.");

        if (constant.description.size() > 0) {
            out.println(" * <p>");
            outputWithLinebreak(out, "", constant.description);
        }

        out.println(" * <p>");
        out.println(" * Code is auto-generated. Modifications may be overwritten!");

        out.println(" */");
        outputClassGenerated(out);
        out.println("public enum " + className + " {");

        boolean first = true;
        for (final Entry<BigInteger, String> value : constant.values.entrySet()) {
            if (!first) {
                out.println(",");
            }
            first = false;

            out.println();
            out.println("    /**");
            out.println("     * " + value.getValue());
            // if (constant.description.size() != 0) {
            // out.println(" * <p>");
            // outputWithLinebreak(out, " ", constant.description);
            // }
            out.println("     */");
            out.print(
                    "    " + stringToConstant(value.getValue()) + "(0x" + String.format("%04X", value.getKey()) + ")");
        }
        out.println(";");

        out.println();

        out.println("    /**");
        out.println("     * A mapping between the integer code and its corresponding " + className
                + " type to facilitate lookup by value.");
        out.println("     */");
        out.println("    private static Map<Integer, " + className + "> idMap;");
        out.println();
        out.println("    static {");
        out.println("        idMap = new HashMap<Integer, " + className + ">();");
        out.println("        for (" + className + " enumValue : values()) {");
        out.println("            idMap.put(enumValue.key, enumValue);");
        out.println("        }");
        out.println("    }");
        out.println();
        out.println("    private final int key;");
        out.println();
        out.println("    private " + className + "(final int key) {");
        out.println("        this.key = key;");
        out.println("    }");
        out.println();

        out.println("    public int getKey() {");
        out.println("        return key;");
        out.println("    }");
        out.println();
        out.println("    public static " + className + " getByValue(final int value) {");
        out.println("        return idMap.get(value);");
        out.println("    }");

        out.println("}");

        out.flush();
        out.close();
    }

}

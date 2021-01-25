/**
 * Copyright (c) 2016-2021 by the respective copyright holders.
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
import java.util.List;
import java.util.Map;

import com.zsmartsystems.zigbee.autocode.xml.ZigBeeXmlCluster;
import com.zsmartsystems.zigbee.autocode.xml.ZigBeeXmlConstant;
import com.zsmartsystems.zigbee.autocode.xml.ZigBeeXmlConstantValue;

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
        for (final ZigBeeXmlConstantValue value : constant.values) {
            if (!first) {
                out.println(",");
            }
            first = false;

            out.println();
            out.println("    /**");
            out.println("     * " + value.name + ", " + value.code + ", 0x" + String.format("%04X", value.code));
            // if (constant.description.size() != 0) {
            // out.println(" * <p>");
            // outputWithLinebreak(out, " ", constant.description);
            // }
            out.println("     */");
            if ("ZigBeeDeviceType".equals(className)) {
                out.print(
                        "    " + stringToConstant(value.name) + "(" +
                                value.scope + ", 0x" + String.format("%04X", value.code)
                                + ")");
            } else {
                out.print(
                        "    " + stringToConstant(value.name) + "(0x" + String.format("%04X", value.code)
                                + ")");
            }
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
        if ("ZigBeeDeviceType".equals(className)) {
            out.println("            idMap.put(enumValue.profilekey, enumValue);");
        } else {
            out.println("            idMap.put(enumValue.key, enumValue);");
        }
        out.println("        }");
        out.println("    }");
        out.println();
        out.println("    private final int key;");
        if ("ZigBeeDeviceType".equals(className)) {
            out.println("    private final int profilekey;");
        }
        out.println();

        if ("ZigBeeDeviceType".equals(className)) {
            out.println("    private " + className + "(final ZigBeeProfileType profile, final int key) {");
            out.println("        this.key = key;");
            out.println("        this.profilekey = key & 0xffff + (profile.ordinal() << 16);");
            out.println("    }");
        } else {
            out.println("    private " + className + "(final int key) {");
            out.println("        this.key = key;");
            out.println("    }");
        }

        out.println();

        out.println("    public int getKey() {");
        out.println("        return key;");
        out.println("    }");
        out.println();
        out.println("    public static " + className + " getByValue(final int value) {");
        if ("ZigBeeDeviceType".equals(className)) {
            out.println(
                    "        int id = value & 0xffff + (ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION.ordinal() << 16);");
            out.println("        return idMap.get(id);");
        } else {
            out.println("        return idMap.get(value);");
        }
        out.println("    }");

        if ("ZigBeeDeviceType".equals(className)) {
            out.println();
            out.println("    public static " + className
                    + " getByValue(final ZigBeeProfileType profile, final int value) {");
            out.println("        int id = value & 0xffff + (profile.ordinal() << 16);");
            out.println("        return idMap.get(id);");
            out.println("    }");
        }

        out.println("}");

        out.flush();
        out.close();
    }

}

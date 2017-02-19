package com.zsmartsystems.zigbee.dongle.ember.autocode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Chris Jackson
 *
 */
public abstract class ClassGenerator {
    int lineLen = 80;
    String sourceRootPath = "../com.zsmartsystems.zigbee.dongle.ember/src/main/java/";
    List<String> importList = new ArrayList<String>();

    protected String stringToConstant(String value) {
        value = value.replaceAll("\\(.*?\\) ?", "");
        value = value.trim();
        value = value.replace("+", "_Plus");
        value = value.replace(" ", "_");
        value = value.replace("-", "_");
        value = value.replace(".", "_");
        value = value.replace("/", "_");
        value = value.replaceAll("_+", "_");
        return value.toUpperCase();
    }

    private String toProperCase(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }

    private String toCamelCase(String value) {
        value = value.replaceAll("\\(.*?\\) ?", "");
        value = value.replace("+", "_Plus");
        value = value.replace(" ", "_");
        value = value.replace("-", "_");
        value = value.replace(".", "_");
        value = value.replace("/", "_");
        value = value.replaceAll("_+", "_");
        String[] parts = value.split("_");
        String camelCaseString = "";
        for (String part : parts) {
            camelCaseString = camelCaseString + toProperCase(part);
        }
        return camelCaseString;
    }

    protected String stringToUpperCamelCase(String value) {
        return toCamelCase(value);
    }

    protected String stringToLowerCamelCase(String value) {
        String cc = toCamelCase(value);

        return cc.substring(0, 1).toLowerCase() + cc.substring(1);
    }

    protected String upperCaseFirstCharacter(String val) {
        return val.substring(0, 1).toUpperCase() + val.substring(1);
    }

    protected PrintWriter getClassOut(File packageFile, String className) throws FileNotFoundException {
        packageFile.mkdirs();
        final File classFile = new File(packageFile + File.separator + className + ".java");
        System.out.println("Generating: " + classFile.getAbsolutePath());
        final FileOutputStream fileOutputStream = new FileOutputStream(classFile, false);
        return new PrintWriter(fileOutputStream);
    }

    protected void clearImports() {
        importList.clear();
    }

    protected void addImport(String importClass) {
        if (importList.contains(importClass)) {
            return;
        }
        importList.add(importClass);
    }

    protected void outputImports(final PrintWriter out) {
        Collections.sort(importList);
        for (final String importClass : importList) {
            out.println("import " + importClass + ";");
        }
    }

    protected void outputCopywrite(final PrintWriter out) {
        out.println("/**");
        out.println(" * Copyright (c) 2014-2017 by the respective copyright holders.");
        out.println(" *");
        out.println(" * All rights reserved. This program and the accompanying materials");
        out.println(" * are made available under the terms of the Eclipse Public License v1.0");
        out.println(" * which accompanies this distribution, and is available at");
        out.println(" * http://www.eclipse.org/legal/epl-v10.html");
        out.println(" */");
    }

    protected String getDataType(String dataType) {
        switch (dataType) {
            case "BYTE":
                return "int";
            case "CONST":
                return "String";
            case "STRUCT_BYTE":
                break;
            case "VARIANT":
                return "List<Integer>";
        }

        return null;
    }

    protected void outputWithLinebreak(PrintWriter out, String indent, String line) {
        String[] words = line.split(" ");
        if (words.length == 0) {
            return;
        }

        out.print(indent + " *");

        int len = 2;
        for (String word : words) {
            if (len + word.length() > lineLen) {
                out.println();
                out.print(indent + " *");
                len = 2;
            }
            out.print(" ");
            out.print(word);
            len += word.length();
        }

        if (len != 0) {
            out.println();
        }
    }
}

/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.telegesis.autocode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.zsmartsystems.zigbee.dongle.telegesis.autocode.xml.Parameter;

/**
 *
 * @author Chris Jackson
 *
 */
public abstract class ClassGenerator {
    int lineLen = 80;
    String sourceRootPath = "../com.zsmartsystems.zigbee.dongle.telegesis/src/main/java/";
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

    protected String lowerCaseFirstCharacter(String val) {
        return val.substring(0, 1).toLowerCase() + val.substring(1);
    }

    protected PrintStream getClassOut(File packageFile, String className) throws FileNotFoundException, UnsupportedEncodingException {
        packageFile.mkdirs();
        final File classFile = new File(packageFile + File.separator + className + ".java");
        System.out.println("Generating: " + classFile.getAbsolutePath());
        final FileOutputStream fileOutputStream = new FileOutputStream(classFile, false);
        return new PrintStream(fileOutputStream,false,"UTF-8");
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

    protected void outputImports(final PrintStream outFile) {
        Collections.sort(importList);
        for (final String importClass : importList) {
            outFile.println("import " + importClass + ";");
        }
    }

    protected void outputCopywrite(PrintStream outFile) {
        String year = "XXXX";

        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader("../pom.xml"));
            String line = br.readLine();
            while (line != null) {
                if (line.contains("<license.year>") && line.contains("</license.year>")) {
                    year = line.substring(line.indexOf("<license.year>") + 14, line.indexOf("</license.year>"));
                    break;
                }
                line = br.readLine();
            }

            br.close();

            br = new BufferedReader(new FileReader("../src/etc/header.txt"));
            line = br.readLine();

            outFile.println("/**");
            while (line != null) {
                outFile.println(" * " + line.replaceFirst("\\$\\{year\\}", year));
                line = br.readLine();
            }
            outFile.println(" */");
            br.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    protected void outputWithLinebreak(PrintStream out, String indent, String line) {
        String[] words = line.split(" ");
        if (words.length == 0) {
            return;
        }

        out.print(indent + " *");

        int len = 2;
        for (String word : words) {
            if (word.toLowerCase().equals("note:")) {
                if (len > 2) {
                    out.println();
                }
                out.println(indent + " * <p>");
                out.print(indent + " * <b>Note:</b>");
                continue;
            }
            if (len + word.length() > lineLen) {
                out.println();
                out.print(indent + " *");
                len = 2;
            }
            out.print(" ");
            out.print(word);
            len += word.length();
        }

        if (len != 2) {
            out.println();
        }
    }

    protected String formatParameterString(Parameter parameter) {
        if (parameter.displayType != null) {
            switch (parameter.displayType.toLowerCase()) {
                case "hex":
                    String size = "";
                    if (parameter.displayLength != 0) {
                        size = "0" + parameter.displayLength;
                    }
                    return "String.format(\"%" + size + "X\", " + stringToLowerCamelCase(parameter.name) + ")";
            }
        }
        return stringToLowerCamelCase(parameter.name);
    }

}

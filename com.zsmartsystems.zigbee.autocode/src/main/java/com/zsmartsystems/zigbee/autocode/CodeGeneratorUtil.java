package com.zsmartsystems.zigbee.autocode;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;

/**
 * Created by tlaukkan on 4/10/2016.
 */
public class CodeGeneratorUtil {
    public static String toHex(int profileId) {
        return "0x" + Integer.toHexString(profileId);
    }

    public static Integer fromHex(String headerIdString) {
        return Integer.valueOf(StringUtils.substringAfter(headerIdString, "0x"), 16);
    }

    public static String labelToEnumerationValue(String dataType) {
        String val = dataType.toUpperCase().trim().replace(" ", "_").replace("-", "_").replace("/", "_")
                .replace("(", "_").replace(")", "_");
        if (val.endsWith("_")) {
            val = val.substring(0, val.length() - 1);
        }
        if ("0123456789".indexOf(val.charAt(0)) >= 0) {
            // Swap the last word to the beginning
            String partsInitial[] = val.split("_");
            StringBuilder sb = new StringBuilder();
            sb.append(partsInitial[partsInitial.length - 1]);
            for (int c = 0; c < partsInitial.length - 1; c++) {
                sb.append("_");
                sb.append(partsInitial[c]);
            }
            return sb.toString();
        }
        return val;
    }

    public static String labelToUpperCamelCase(String value) {
        return WordUtils.capitalizeFully(splitCamelCase(value).replace("-", " ").replace("_", " ").replace("/", " ")
                .replace("(", " ").replace(")", " "), new char[] { ' ' }).replaceAll(" ", "");
    }

    public static String upperCamelCaseToLowerCamelCase(String value) {
        if (value.length() == 0) {
            return "";
        }

        return value.substring(0, 1).toLowerCase() + value.substring(1);
    }

    public static String splitCamelCase(String value) {
        return value.replaceAll(String.format("%s|%s|%s", "(?<=[A-Z])(?=[A-Z][a-z])", "(?<=[^A-Z])(?=[A-Z])",
                "(?<=[A-Za-z])(?=[^A-Za-z])"), " ");
    }

    protected static void outputLicense(PrintWriter out) {
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader("../src/etc/header.txt"));
            String line = br.readLine();

            out.println("/**");
            while (line != null) {
                out.println(" * " + line.replaceFirst("\\$\\{year\\}", "2018"));
                line = br.readLine();
            }
            out.println(" */");
            br.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

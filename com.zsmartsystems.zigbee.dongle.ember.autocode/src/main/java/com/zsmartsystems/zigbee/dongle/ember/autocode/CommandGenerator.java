package com.zsmartsystems.zigbee.dongle.ember.autocode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zsmartsystems.zigbee.dongle.ember.autocode.xml.Command;
import com.zsmartsystems.zigbee.dongle.ember.autocode.xml.Enumeration;
import com.zsmartsystems.zigbee.dongle.ember.autocode.xml.Parameter;
import com.zsmartsystems.zigbee.dongle.ember.autocode.xml.Protocol;
import com.zsmartsystems.zigbee.dongle.ember.autocode.xml.Structure;
import com.zsmartsystems.zigbee.dongle.ember.autocode.xml.Value;

/**
 *
 * @author Chris Jackson
 *
 */
public class CommandGenerator extends ClassGenerator {
    final String ezspCommandPackage = "com.zsmartsystems.zigbee.dongle.ember.ezsp.command";
    final String ezspStructurePackage = "com.zsmartsystems.zigbee.dongle.ember.ezsp.structure";

    public void go(Protocol protocol) throws FileNotFoundException {
        String className;
        for (Command command : protocol.commands) {
            if (command.name.endsWith("Handler")) {
                className = "Ezsp" + command.name.substring(0, 1).toUpperCase() + command.name.substring(1);
                createCommandClass(className, command, command.response_parameters);
            } else {
                className = "Ezsp" + upperCaseFirstCharacter(command.name) + "Request";
                createCommandClass(className, command, command.command_parameters);

                className = "Ezsp" + command.name.substring(0, 1).toUpperCase() + command.name.substring(1)
                        + "Response";
                createCommandClass(className, command, command.response_parameters);
            }
        }

        for (Structure structure : protocol.structures) {
            createStructureClass(structure);
        }

        for (Enumeration enumeration : protocol.enumerations) {
            createEnumClass(enumeration);
        }
    }

    private void createCommandClass(String className, Command command, List<Parameter> parameters)
            throws FileNotFoundException {

        System.out.println("Processing command class " + command.name + "  [" + className + "()]");

        StringWriter stringWriter = new StringWriter();
        PrintWriter out = new PrintWriter(stringWriter);

        clearImports();
        // addImport("org.slf4j.Logger");
        // addImport("org.slf4j.LoggerFactory");

        // addImport("java.util.Map");
        // addImport("java.util.HashMap");

        out.println("/**");
        out.println(" * Class to implement the Ember EZSP command <b>" + command.name + "</b>.");
        out.println(" * <p>");
        outputWithLinebreak(out, "", command.description);
        out.println(" * <p>");
        out.println(" * This class provides methods for processing EZSP commands.");
        out.println(" * <p>");
        out.println(" * Note that this code is autogenerated. Manual changes may be overwritten.");
        out.println(" *");
        out.println(" * @author Chris Jackson - Initial contribution of Java code generator");
        out.println(" */");

        if (className.endsWith("Request")) {
            addImport("com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameRequest");
            out.println("public class " + className + " extends EzspFrameRequest {");
        } else {
            addImport("com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameResponse");
            out.println("public class " + className + " extends EzspFrameResponse {");
        }

        out.println("    public static int FRAME_ID = " + String.format("0x%02X", command.id) + ";");

        // out.println(" private static final Logger logger = LoggerFactory.getLogger(" + className + ".class);");

        for (Parameter parameter : parameters) {
            if (parameter.auto_size != null) {
                continue;
            }

            out.println();
            out.println("    /**");
            outputWithLinebreak(out, "    ", parameter.description);
            out.println("     * <p>");
            out.println("     * EZSP type is <i>" + parameter.data_type + "</i> - Java type is {@link "
                    + getTypeClass(parameter.data_type) + "}");
            out.println("     */");
            out.println("    private " + getTypeClass(parameter.data_type) + " " + parameter.name + ";");
        }

        if (className.endsWith("Request")) {
            addImport("com.zsmartsystems.zigbee.dongle.ember.ezsp.serializer.EzspSerializer");
            out.println();
            out.println("    /**");
            out.println("     * Serialiser used to seialise to binary line data");
            out.println("     */");
            out.println("    private EzspSerializer serializer;");
            out.println();
            out.println("    /**");
            out.println("     * Request constructor");
            out.println("     */");
            out.println("    public " + className + "() {");
            out.println("        frameId = FRAME_ID;");
            out.println("        serializer = new EzspSerializer();");
            out.println("    }");
        } else {
            // addImport("com.zsmartsystems.zigbee.dongle.ember.ezsp.serializer.EzspDeserializer");
            out.println();
            // out.println(" private EzspDeserializer serializer;");
            // out.println();
            out.println("    /**");
            out.println("     * Response and Handler constructor");
            out.println("     */");
            out.println("    public " + className + "(int[] inputBuffer) {");
            out.println("        // Super creates deserializer and reads header fields");
            out.println("        super(inputBuffer);");
            out.println();
            out.println("        // Deserialize the fields");
            Map<String, String> autoSizers = new HashMap<String, String>();
            for (Parameter parameter : parameters) {
                if (parameter.auto_size != null) {
                    out.println("        int " + parameter.name + " = deserializer.deserialize"
                            + getTypeSerializer(parameter.data_type) + "();");
                    autoSizers.put(parameter.auto_size, parameter.name);
                    continue;
                }
                if (autoSizers.get(parameter.name) != null) {
                    out.println("        " + parameter.name + "= deserializer.deserialize"
                            + getTypeSerializer(parameter.data_type) + "(" + autoSizers.get(parameter.name) + ");");
                    continue;
                }
                if (parameter.data_type.contains("[") && parameter.data_type.contains("]")
                        && !parameter.data_type.contains("[]")) {
                    int length = Integer.parseInt(parameter.data_type.substring(parameter.data_type.indexOf("[") + 1,
                            parameter.data_type.indexOf("]")));
                    out.println("        " + parameter.name + " = deserializer.deserialize"
                            + getTypeSerializer(parameter.data_type) + "(" + length + ");");
                    continue;
                }
                out.println("        " + parameter.name + " = deserializer.deserialize"
                        + getTypeSerializer(parameter.data_type) + "();");
            }
            out.println("    }");
        }

        for (Parameter parameter : parameters) {
            if (parameter.auto_size != null) {
                continue;
            }

            out.println();
            out.println("    /**");
            outputWithLinebreak(out, "    ", parameter.description);
            out.println("     * <p>");
            out.println("     * EZSP type is <i>" + parameter.data_type + "</i> - Java type is {@link "
                    + getTypeClass(parameter.data_type) + "}");
            out.println("     *");
            out.println("     * @return the current " + parameter.name + " as {@link "
                    + getTypeClass(parameter.data_type) + "}");
            out.println("     */");
            out.println("    public " + getTypeClass(parameter.data_type) + " get"
                    + upperCaseFirstCharacter(parameter.name) + "() {");
            out.println("        return " + parameter.name + ";");
            out.println("    }");
            out.println();
            out.println("    /**");
            outputWithLinebreak(out, "    ", parameter.description);
            out.println("     *");
            out.println("     * @param " + parameter.name + " the " + parameter.name + " to set as {@link "
                    + getTypeClass(parameter.data_type) + "}");
            out.println("     */");
            out.println("    public void set" + upperCaseFirstCharacter(parameter.name) + "("
                    + getTypeClass(parameter.data_type) + " " + parameter.name + ") {");
            out.println("        this." + parameter.name + " = " + parameter.name + ";");
            out.println("    }");
        }

        if (className.endsWith("Request")) {
            out.println();
            out.println("    @Override");
            out.println("    public int[] serialize() {");
            // out.println(" EzspSerializer serializer = new EzspSerializer();");
            out.println("        // Serialize the header");
            out.println("        serializeHeader(serializer);");
            out.println();
            out.println("        // Serialize the fields");
            for (Parameter parameter : parameters) {
                if (parameter.auto_size != null) {
                    out.println("        serializer.serialize" + getTypeSerializer(parameter.data_type) + "("
                            + parameter.auto_size + ".length);");
                    continue;
                }
                out.println("        serializer.serialize" + getTypeSerializer(parameter.data_type) + "("
                        + parameter.name + ");");
            }
            out.println("        return serializer.getPayload();");
            out.println("    }");
        } else {

        }

        out.println();
        out.println("    @Override");
        out.println("    public String toString() {");
        out.println("        final StringBuilder builder = new StringBuilder();");
        boolean first = true;
        for (Parameter parameter : parameters) {
            if (parameter.auto_size != null) {
                continue;
            }

            if (first) {
                out.println("        builder.append(\"" + className + " [" + parameter.name + "=\");");
            } else {
                out.println("        builder.append(\", " + parameter.name + "=\");");
            }
            first = false;
            if (parameter.data_type.contains("[")) {
                out.println("        for (int c = 0; c < " + parameter.name + ".length; c++) {");
                out.println("            if (c > 0) {");
                out.println("                builder.append(\" \");");
                out.println("            }");
                out.println("            builder.append(String.format(\"%02X\", " + parameter.name + "[c]));");
                out.println("        }");
            } else {
                out.println("        builder.append(" + parameter.name + ");");
            }
        }
        out.println("        builder.append(\"]\");");
        out.println("        return builder.toString();");
        out.println("    }");

        out.println("}");

        out.flush();

        File packageFile = new File(sourceRootPath + ezspCommandPackage.replace(".", "/"));
        PrintWriter outFile = getClassOut(packageFile, className);

        outputCopywrite(outFile);
        outFile.println("package " + ezspCommandPackage + ";");

        outFile.println();

        outputImports(outFile);

        outFile.println();
        outFile.print(stringWriter.toString());

        outFile.flush();
        outFile.close();

        out.close();
    }

    private void createStructureClass(Structure structure) throws FileNotFoundException {
        String className = upperCaseFirstCharacter(structure.name);
        System.out.println("Processing structure class " + structure.name + "  [" + className + "()]");

        StringWriter stringWriter = new StringWriter();
        PrintWriter out = new PrintWriter(stringWriter);

        clearImports();
        // addImport("org.slf4j.Logger");
        // addImport("org.slf4j.LoggerFactory");

        // addImport("java.util.Map");
        // addImport("java.util.HashMap");

        addImport("com.zsmartsystems.zigbee.dongle.ember.ezsp.serializer.EzspSerializer");
        addImport("com.zsmartsystems.zigbee.dongle.ember.ezsp.serializer.EzspDeserializer");

        out.println("/**");
        out.println(" * Class to implement the Ember Structure <b>" + structure.name + "</b>.");
        out.println(" * <p>");
        outputWithLinebreak(out, "", structure.description);
        out.println(" * <p>");
        out.println(" * Note that this code is autogenerated. Manual changes may be overwritten.");
        out.println(" *");
        out.println(" * @author Chris Jackson - Initial contribution of Java code generator");
        out.println(" */");

        out.println("public class " + className + " {");

        for (Parameter parameter : structure.parameters) {
            if (parameter.auto_size != null) {
                continue;
            }

            out.println();
            out.println("    /**");
            outputWithLinebreak(out, "    ", parameter.description);
            out.println("     * <p>");
            out.println("     * EZSP type is <i>" + parameter.data_type + "</i> - Java type is {@link "
                    + getTypeClass(parameter.data_type) + "}");
            out.println("     */");
            out.println("    private " + getTypeClass(parameter.data_type) + " " + parameter.name + ";");
        }

        out.println();
        out.println("    /**");
        out.println("     * Default Constructor");
        out.println("     */");
        out.println("    public " + className + "() {");
        out.println("    }");

        out.println();
        out.println("    public " + className + "(EzspDeserializer deserializer) {");
        out.println("        deserialize(deserializer);");
        out.println("    }");

        for (Parameter parameter : structure.parameters) {
            if (parameter.auto_size != null) {
                continue;
            }

            out.println();
            out.println("    /**");
            outputWithLinebreak(out, "    ", parameter.description);
            out.println("     * <p>");
            out.println("     * EZSP type is <i>" + parameter.data_type + "</i> - Java type is {@link "
                    + getTypeClass(parameter.data_type) + "}");
            out.println("     *");
            out.println("     * @return the current " + parameter.name + " as {@link "
                    + getTypeClass(parameter.data_type) + "}");
            out.println("     */");
            out.println("    public " + getTypeClass(parameter.data_type) + " get"
                    + upperCaseFirstCharacter(parameter.name) + "() {");
            out.println("        return " + parameter.name + ";");
            out.println("    }");
            out.println();
            out.println("    /**");
            outputWithLinebreak(out, "    ", parameter.description);
            out.println("     *");
            out.println("     * @param " + parameter.name + " the " + parameter.name + " to set as {@link "
                    + getTypeClass(parameter.data_type) + "}");
            out.println("     */");
            out.println("    public void set" + upperCaseFirstCharacter(parameter.name) + "("
                    + getTypeClass(parameter.data_type) + " " + parameter.name + ") {");
            out.println("        this." + parameter.name + " = " + parameter.name + ";");
            out.println("    }");
        }

        out.println();
        out.println("    /**");
        out.println("     * Serialise the contents of the EZSP structure.");
        out.println("     *");
        out.println("     * @param serializer the {@link EzspSerializer} used to serialize");
        out.println("     */");
        out.println("    public int[] serialize(EzspSerializer serializer) {");
        // out.println(" EzspSerializer serializer = new EzspSerializer();");
        out.println("        // Serialize the fields");
        for (Parameter parameter : structure.parameters) {
            if (parameter.auto_size != null) {
                out.println("        serializer.serialize" + getTypeSerializer(parameter.data_type) + "("
                        + parameter.auto_size + ".length);");
                out.println("        serializer.serialize" + getTypeSerializer(parameter.data_type) + "("
                        + parameter.auto_size + ".length);");
                continue;
            }
            out.println("        serializer.serialize" + getTypeSerializer(parameter.data_type) + "(" + parameter.name
                    + ");");
        }
        out.println("        return serializer.getPayload();");
        out.println("    }");

        out.println();
        out.println("    /**");
        out.println("     * Deserialise the contents of the EZSP structure.");
        out.println("     *");
        out.println("     * @param deserializer the {@link EzspDeserializer} used to deserialize");
        out.println("     */");
        out.println("    public void deserialize(EzspDeserializer deserializer) {");
        // out.println(" EzspSerializer serializer = new EzspSerializer();");
        out.println("        // Deserialize the fields");
        Map<String, String> autoSizers = new HashMap<String, String>();
        for (Parameter parameter : structure.parameters) {
            if (parameter.auto_size != null) {
                out.println("        int " + parameter.name + " = deserializer.deserialize"
                        + getTypeSerializer(parameter.data_type) + "();");
                autoSizers.put(parameter.auto_size, parameter.name);
                continue;
            }
            if (autoSizers.get(parameter.name) != null) {
                out.println("        " + parameter.name + "= deserializer.deserialize"
                        + getTypeSerializer(parameter.data_type) + "(" + autoSizers.get(parameter.name) + ");");
                continue;
            }
            if (parameter.data_type.contains("[") && parameter.data_type.contains("]")
                    && !parameter.data_type.contains("[]")) {
                int length = Integer.parseInt(parameter.data_type.substring(parameter.data_type.indexOf("[") + 1,
                        parameter.data_type.indexOf("]")));
                out.println("        " + parameter.name + " = deserializer.deserialize"
                        + getTypeSerializer(parameter.data_type) + "(" + length + ");");
                continue;
            }
            out.println("        " + parameter.name + " = deserializer.deserialize"
                    + getTypeSerializer(parameter.data_type) + "();");
        }
        out.println("    }");

        out.println();
        out.println("    @Override");
        out.println("    public String toString() {");
        out.println("        final StringBuilder builder = new StringBuilder();");
        boolean first = true;
        for (Parameter parameter : structure.parameters) {
            if (parameter.auto_size != null) {
                continue;
            }

            if (first) {
                out.println("        builder.append(\"" + className + " [" + parameter.name + "=\");");
            } else {
                out.println("        builder.append(\", " + parameter.name + "=\");");
            }
            first = false;

            // If it's an array, then we want to print hex data
            if (parameter.data_type.contains("[")) {
                out.println("        builder.append(\"{\");");
                out.println("        if (" + parameter.name + " == null) {");
                out.println("            builder.append(\"null\");");
                out.println("        } else {");
                out.println("            for (int cnt = 0; cnt < " + parameter.name + ".length; cnt++) {");
                out.println("                if (cnt != 0) {");
                out.println("                    builder.append(\" \");");
                out.println("                }");
                out.println("                builder.append(String.format(\"%02X\", " + parameter.name + "[cnt]));");
                out.println("            }");
                out.println("        }");
                out.println("        builder.append(\"}\");");
            } else {
                out.println("        builder.append(" + parameter.name + ");");
            }
        }
        out.println("        builder.append(\"]\");");
        out.println("        return builder.toString();");
        out.println("    }");

        out.println("}");

        out.flush();

        File packageFile = new File(sourceRootPath + ezspStructurePackage.replace(".", "/"));
        PrintWriter outFile = getClassOut(packageFile, className);

        outputCopywrite(outFile);
        outFile.println("package " + ezspStructurePackage + ";");

        outFile.println();

        outputImports(outFile);

        outFile.println();
        outFile.print(stringWriter.toString());

        outFile.flush();
        outFile.close();

        out.close();
    }

    private void createEnumClass(Enumeration enumeration) throws FileNotFoundException {
        String className = upperCaseFirstCharacter(enumeration.name);
        System.out.println("Processing enum class " + enumeration.name + "  [" + className + "()]");

        StringWriter stringWriter = new StringWriter();
        PrintWriter out = new PrintWriter(stringWriter);

        clearImports();

        addImport("java.util.Map");
        addImport("java.util.HashMap");

        out.println("/**");
        out.println(" * Class to implement the Ember Enumeration <b>" + enumeration.name + "</b>.");
        if (enumeration.description != null && enumeration.description.trim().length() > 0) {
            out.println(" * <p>");
            outputWithLinebreak(out, "", enumeration.description);
        }
        out.println(" * <p>");
        out.println(" * Note that this code is autogenerated. Manual changes may be overwritten.");
        out.println(" *");
        out.println(" * @author Chris Jackson - Initial contribution of Java code generator");
        out.println(" */");

        out.println("public enum " + className + " {");

        out.println("    /**");
        out.println("     * Default unknown value");
        out.println("     */");
        out.println("    UNKNOWN(-1),");

        boolean first = true;
        for (Value value : enumeration.values) {
            if (!first) {
                out.println(",");
            }
            first = false;
            out.println();
            out.println("    /**");
            outputWithLinebreak(out, "    ", value.description);
            out.println("     */");
            out.print("    " + value.name + "(0x" + String.format("%04X", value.enum_value) + ")");
        }

        out.println(";");

        out.println();
        out.println("    /**");
        out.println("     * A mapping between the integer code and its corresponding type to");
        out.println("     * facilitate lookup by code.");
        out.println("     */");
        out.println("    private static Map<Integer, " + className + "> codeMapping;");
        out.println();

        out.println("    private int key;");
        out.println();

        out.println("    private " + className + "(int key) {");
        out.println("        this.key = key;");
        out.println("    }");
        out.println();

        out.println("    private static void initMapping() {");
        out.println("        codeMapping = new HashMap<Integer, " + className + ">();");
        out.println("        for (" + className + " s : values()) {");
        out.println("            codeMapping.put(s.key, s);");
        out.println("        }");
        out.println("    }");
        out.println();

        out.println("    /**");
        out.println("     * Lookup function based on the EmberStatus type code. Returns null if the");
        out.println("     * code does not exist.");
        out.println("     *");
        out.println("     * @param i");
        out.println("     *            the code to lookup");
        out.println("     * @return enumeration value of the alarm type.");
        out.println("     */");
        out.println("    public static " + className + " get" + className + "(int i) {");
        out.println("        if (codeMapping == null) {");
        out.println("            initMapping();");
        out.println("        }");
        out.println();

        out.println("        if (codeMapping.get(i) == null) {");
        out.println("            return UNKNOWN;");
        out.println("        }");
        out.println();

        out.println("        return codeMapping.get(i);");
        out.println("    }");
        out.println();
        out.println("    /**");
        out.println("     * Returns the EZSP protocol defined value for this enum");
        out.println("     *");
        out.println("     * @return the EZSP protocol key");
        out.println("     */");
        out.println("    public int getKey() {");
        out.println("        return key;");
        out.println("    }");

        out.println("}");

        out.flush();

        File packageFile = new File(sourceRootPath + ezspStructurePackage.replace(".", "/"));
        PrintWriter outFile = getClassOut(packageFile, className);

        outputCopywrite(outFile);
        outFile.println("package " + ezspStructurePackage + ";");

        outFile.println();

        outputImports(outFile);

        outFile.println();
        outFile.print(stringWriter.toString());

        outFile.flush();
        outFile.close();

        out.close();
    }

    protected String getTypeClass(String dataType) {
        String dataTypeLocal = new String(dataType);
        if (dataType.contains("[")) {
            dataTypeLocal = dataTypeLocal.substring(0, dataTypeLocal.indexOf("[") + 1);
        }

        switch (dataTypeLocal) {
            case "bool":
                return "boolean";
            case "EzspValueId":
            case "EmberNodeId":
            case "EmberCounterType":
            case "EzspConfigId":
            case "int8s":
            case "uint8_u":
            case "uint8_t":
            case "uint16_t":
            case "uint32_t":
                return "int";
            case "uint8_t[":
            case "uint16_t[":
                // if (dataType.endsWith("[]")) {
                return "int[]";
            // }
            // int size = Integer.parseInt(dataType.substring(dataType.indexOf("[") + 1, dataType.indexOf("]")));
            // return "int[" + size + "]";
            case "EmberEUI64":
                addImport("com.zsmartsystems.zigbee.IeeeAddress");
                return "IeeeAddress";
            case "EmberRouteTableEntry":
                addImport("com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberRouteTableEntry");
                return "EmberRouteTableEntry";
            case "EmberBindingTableEntry":
                addImport("com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberBindingTableEntry");
                return "EmberBindingTableEntry";
            case "EmberZigbeeNetwork":
                addImport("com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberZigbeeNetwork");
                return "EmberZigbeeNetwork";
            case "EmberNeighborTableEntry":
                addImport("com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberNeighborTableEntry");
                return "EmberNeighborTableEntry";
            case "EmberInitialSecurityState":
                addImport("com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberInitialSecurityState");
                return "EmberInitialSecurityState";
            case "EmberCurrentSecurityState":
                addImport("com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberCurrentSecurityState");
                return "EmberCurrentSecurityState";
            case "EmberOutgoingMessageType":
                addImport("com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberOutgoingMessageType");
                return "EmberOutgoingMessageType";
            case "EzspDecisionId":
                addImport("com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspDecisionId");
                return "EzspDecisionId";
            case "EmberIncomingMessageType":
                addImport("com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberIncomingMessageType");
                return "EmberIncomingMessageType";
            case "EmberApsFrame":
                addImport("com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberApsFrame");
                return "EmberApsFrame";
            case "EzspPolicyId":
                addImport("com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspPolicyId");
                return "EzspPolicyId";
            case "EzspNetworkScanType":
                addImport("com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspNetworkScanType");
                return "EzspNetworkScanType";
            case "EzspStatus":
                addImport("com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspStatus");
                return "EzspStatus";
            case "EmberStatus":
                addImport("com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberStatus");
                return "EmberStatus";
            case "EmberNetworkParameters":
                addImport("com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberNetworkParameters");
                return "EmberNetworkParameters";
            case "EmberNodeType":
                addImport("com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberNodeType");
                return "EmberNodeType";
            default:
                return dataType;
        }
    }

    protected String getTypeSerializer(String dataType) {
        String dataTypeLocal = new String(dataType);
        if (dataType.contains("[")) {
            dataTypeLocal = dataTypeLocal.substring(0, dataTypeLocal.indexOf("[") + 1);
        }
        switch (dataTypeLocal) {
            case "EzspValueId":
            case "EmberCounterType":
            case "EzspConfigId":
            case "uint8_t":
            case "uint8_u":
                return "UInt8";
            case "EmberNodeId":
            case "uint16_t":
                return "UInt16";
            case "uint32_t":
                return "UInt32";
            case "uint8_t[":
            case "uint8_u[":
                return "UInt8Array";
            case "int8s":
                return "Int8S";
            case "uint16_t[":
                return "UInt16Array";
            case "Bool":
                return "Boolean";
            case "EmberEUI64":
                return "EmberEui64";
            case "bool":
                return "Bool";
            default:
                return dataType;
        }
    }
}

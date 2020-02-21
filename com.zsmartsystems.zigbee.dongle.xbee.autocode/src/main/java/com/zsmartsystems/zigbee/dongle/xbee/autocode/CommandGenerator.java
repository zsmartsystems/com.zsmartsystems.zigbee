/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.xbee.autocode;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.zsmartsystems.zigbee.dongle.xbee.autocode.xml.Command;
import com.zsmartsystems.zigbee.dongle.xbee.autocode.xml.Enumeration;
import com.zsmartsystems.zigbee.dongle.xbee.autocode.xml.Parameter;
import com.zsmartsystems.zigbee.dongle.xbee.autocode.xml.ParameterGroup;
import com.zsmartsystems.zigbee.dongle.xbee.autocode.xml.Protocol;
import com.zsmartsystems.zigbee.dongle.xbee.autocode.xml.Value;

/**
 *
 * @author Chris Jackson
 *
 */
public class CommandGenerator extends ClassGenerator {
    final String zigbeePackage = "com.zsmartsystems.zigbee";
    final String zigbeeSecurityPackage = "com.zsmartsystems.zigbee.security";
    final String internalPackage = "com.zsmartsystems.zigbee.dongle.xbee.internal";
    final String commandPackage = "com.zsmartsystems.zigbee.dongle.xbee.internal.protocol";
    final String enumPackage = "com.zsmartsystems.zigbee.dongle.xbee.internal.protocol";

    Map<Integer, String> events = new TreeMap<Integer, String>();

    public void go(Protocol protocol) throws FileNotFoundException, UnsupportedEncodingException {
        // Create "API" commands for AT commands
        for (Command atCommand : protocol.at_commands) {
            Parameter idParameter = new Parameter();
            idParameter.name = "Frame ID";
            idParameter.data_type = "uint8";
            idParameter.multiple = false;
            idParameter.bitfield = false;

            Parameter stateParameter = new Parameter();
            stateParameter.name = "Command Status";
            stateParameter.data_type = "CommandStatus";
            stateParameter.multiple = false;
            stateParameter.bitfield = false;

            Parameter atParameter = new Parameter();
            atParameter.name = "AT Parameter";
            atParameter.data_type = "AtCommand";
            atParameter.value = '"' + atCommand.command + '"';

            String description = "AT Command <b>" + atCommand.command + "</b></p>" + atCommand.description;

            if (atCommand.getter) {
                Command command = new Command();
                command.id = 0x08;
                command.name = "Get " + atCommand.name;
                command.description = description;
                command.command_parameters = new ArrayList<ParameterGroup>();
                command.response_parameters = new ArrayList<ParameterGroup>();
                ParameterGroup commandGroup = new ParameterGroup();
                commandGroup.parameters = new ArrayList<Parameter>();
                commandGroup.parameters.add(idParameter);
                commandGroup.parameters.add(atParameter);
                command.command_parameters.add(commandGroup);
                protocol.commands.add(command);
            }

            if (atCommand.setter) {
                Command command = new Command();
                command.id = 0x08;
                command.name = "Set " + atCommand.name;
                command.description = description;
                command.command_parameters = new ArrayList<ParameterGroup>();
                command.response_parameters = new ArrayList<ParameterGroup>();
                ParameterGroup commandGroup = new ParameterGroup();
                commandGroup.parameters = new ArrayList<Parameter>();
                commandGroup.parameters.add(idParameter);
                commandGroup.parameters.add(atParameter);

                if (atCommand.command_parameters != null && atCommand.command_parameters.size() != 0) {
                    commandGroup.parameters.addAll(atCommand.command_parameters.get(0).parameters);
                }

                command.command_parameters.add(commandGroup);
                protocol.commands.add(command);
            }

            Command response = new Command();
            response.id = 0x88;
            response.name = atCommand.name;
            response.description = description;
            response.command_parameters = new ArrayList<ParameterGroup>();
            response.response_parameters = new ArrayList<ParameterGroup>();
            ParameterGroup responseGroup = new ParameterGroup();
            responseGroup.parameters = new ArrayList<Parameter>();
            responseGroup.parameters.add(idParameter);
            responseGroup.parameters.add(atParameter);
            responseGroup.parameters.add(stateParameter);
            if (atCommand.response_parameters != null && atCommand.response_parameters.size() != 0) {
                responseGroup.parameters.addAll(atCommand.response_parameters.get(0).parameters);
            }
            response.response_parameters.add(responseGroup);
            protocol.commands.add(response);
        }

        String packageName;
        String className;
        for (Command command : protocol.commands) {
            packageName = commandPackage;
            if (command.command_parameters.size() > 0) {
                className = "XBee" + stringToUpperCamelCase(command.name) + "Command";
            } else {
                String responseType = "Event";
                for (Parameter parameter : command.response_parameters.get(0).parameters) {
                    if (parameter.name.toUpperCase().equals("FRAME ID")) {
                        responseType = "Response";
                    }

                }
                className = "XBee" + stringToUpperCamelCase(command.name) + responseType;
            }

            createCommandClass(packageName, className, command, command.command_parameters,
                    command.response_parameters);
        }

        for (Enumeration enumeration : protocol.enumerations) {
            createEnumClass(enumeration);
        }

        createEventFactory("Event", protocol);
        createEventFactory("Response", protocol);
    }

    private void createCommandClass(String packageName, String className, Command command,
            List<ParameterGroup> commandParameterGroup, List<ParameterGroup> responseParameterGroup)
            throws FileNotFoundException, UnsupportedEncodingException {
        if (className == "XBeeZigBeeTransmitStatusCommand") {
            System.out.println();
        }
        // Create a list of async events that we need to handle
        if (className.endsWith("Event")) {
            events.put(command.id, className);
        }

        // Create a list of responses that we need to handle
        if (className.endsWith("Response")) {
            // responses.put(command.id, className);
        }

        System.out.println("Processing command class " + command.name + "  [" + className + "()]");

        OutputStream stringWriter = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(stringWriter,false,"UTF-8");

        clearImports();

        out.println("/**");
        out.println(" * Class to implement the XBee command <b>" + command.name + "</b>.");
        out.println(" * <p>");
        if (command.description != null && command.description.length() != 0) {
            outputWithLinebreak(out, "", command.description);
            out.println(" * <p>");
        }
        out.println(" * This class provides methods for processing XBee API commands.");
        out.println(" * <p>");
        out.println(" * Note that this code is autogenerated. Manual changes may be overwritten.");
        out.println(" *");
        out.println(" * @author Chris Jackson - Initial contribution of Java code generator");
        out.println(" */");

        out.print("public class " + className + " extends XBeeFrame implements ");

        if (commandParameterGroup.size() > 0) {
            out.print("XBeeCommand ");
        }

        if (className.endsWith("Event")) {
            out.print("XBeeEvent ");
        }
        if (className.endsWith("Response")) {
            out.print("XBeeResponse ");
        }

        out.println("{");

        for (ParameterGroup group : commandParameterGroup) {
            for (Parameter parameter : group.parameters) {
                if (parameter.auto_size != null) {
                    continue;
                }
                // Constant...
                if (parameter.value != null && parameter.value.length() != 0) {
                    continue;
                }

                out.println("    /**");
                if (parameter.description != null && parameter.description.length() > 0) {
                    outputWithLinebreak(out, "    ", parameter.description);
                }
                out.println("     */");

                createParameterDefinition(out, "    ", parameter);

                out.println();
            }
        }

        for (ParameterGroup group : responseParameterGroup) {
            for (Parameter parameter : group.parameters) {
                if (parameter.auto_size != null) {
                    continue;
                }
                // Constant
                if (parameter.value != null && parameter.value.length() != 0) {
                    continue;
                }

                out.println("    /**");
                out.println("     * Response field");
                if (Boolean.TRUE.equals(group.multiple)) {
                    out.println("     * Field accepts multiple responses.");
                }
                if (parameter.description != null && parameter.description.length() > 0) {
                    outputWithLinebreak(out, "    ", parameter.description);
                }
                out.println("     */");

                createParameterDefinition(out, "    ", parameter);

                out.println();
            }
        }

        for (ParameterGroup group : commandParameterGroup) {
            createParameterSetter(out, "    ", group.parameters);
        }

        for (ParameterGroup group : responseParameterGroup) {
            // Multiple elements at group level get their own class
            if (Boolean.TRUE.equals(group.multiple)) {
                out.println("    /**");
                out.println("     *");
                out.println("     * @return the " + stringToLowerCamelCase(group.name) + "s as a List of {@link "
                        + stringToUpperCamelCase(group.name) + "}");
                out.println("     */");
                out.println("    public List<" + stringToUpperCamelCase(group.name) + "> get"
                        + stringToUpperCamelCase(group.name) + "s() {");
                out.println("        return " + stringToLowerCamelCase(group.name) + "s;");
                out.println("    }");
                out.println();

                continue;
            }

            createParameterGetter(out, "    ", group.parameters);
        }

        if (commandParameterGroup != null && commandParameterGroup.size() != 0) {
            out.println("    @Override");
            out.println("    public int[] serialize() {");
            out.println("        // Serialize the command fields");
            boolean first = true;
            for (ParameterGroup group : commandParameterGroup) {
                out.println("        serializeCommand(0x" + String.format("%02X", command.id) + ");");

                for (Parameter parameter : group.parameters) {
                    String valueName = stringToLowerCamelCase(parameter.name);
                    String indent = "        ";
                    if (parameter.optional != null && parameter.optional) {
                        out.println(indent + "if (" + stringToLowerCamelCase(parameter.name) + " != null) {");
                        indent = "            ";

                    }

                    first = false;

                    if (parameter.value != null && parameter.value.length() != 0) {
                        out.println(indent + "serialize" + getTypeSerializer(parameter.data_type) + "("
                                + parameter.value + ");");
                        continue;
                    }

                    if (parameter.multiple) {
                        out.println(indent + "boolean first" + stringToUpperCamelCase(parameter.name) + " = true;");
                        out.println(indent + "for (" + getTypeClass(parameter.data_type) + " value : "
                                + stringToLowerCamelCase(parameter.name) + ") {");
                        out.println(indent + "    first" + stringToUpperCamelCase(parameter.name) + " = false;");
                        valueName = "value";
                        indent = "            ";
                    } else if (parameter.auto_size != null) {
                        out.println(indent + "serialize" + getTypeSerializer(parameter.data_type) + "("
                                + stringToLowerCamelCase(parameter.auto_size) + ".length);");
                        continue;
                    }
                    out.println(indent + "serialize" + getTypeSerializer(parameter.data_type) + "(" + valueName + ");");

                    if (parameter.multiple || (parameter.optional != null && parameter.optional)) {
                        out.println("        }");
                    }
                }
            }
            out.println();
            out.println("        return getPayload();");
        }

        if (responseParameterGroup != null && responseParameterGroup.size() != 0) {
            out.println();
            out.println("    @Override");
            out.println("    public void deserialize(int[] incomingData) {");

            out.println("        initialiseDeserializer(incomingData);");
            out.println();

            for (ParameterGroup group : responseParameterGroup) {
                if (group.parameters.size() == 0 && group.required == false && group.complete == false) {
                    continue;
                }

                String indent;
                out.println("        // Deserialize the fields for the response");
                indent = "        ";

                if (!Boolean.TRUE.equals(group.multiple) && group.parameters != null && group.parameters.size() > 0) {
                }
                indent = "        ";

                if (Boolean.TRUE.equals(group.multiple)) {
                    out.println(indent + stringToLowerCamelCase(group.name) + "s.add(new "
                            + stringToUpperCamelCase(group.name) + "(incomingData));");
                } else {
                    createDeserializer(out, indent, group);
                }
            }
        }

        out.println("    }");

        out.println();
        out.println("    @Override");
        out.println("    public String toString() {");

        int parameterCount = 0;
        for (ParameterGroup group : commandParameterGroup) {
            if (group.parameters != null) {
                parameterCount += group.parameters.size();
            }
        }
        for (ParameterGroup group : responseParameterGroup) {
            if (group.parameters != null) {
                parameterCount += group.parameters.size();
            }
        }

        if (parameterCount == 0 && !className.endsWith("Command")) {
            out.println("        return \"" + className + " []\";");
        } else {
            out.println("        final StringBuilder builder = new StringBuilder("
                    + (className.length() + 3 * ((parameterCount + 1) * 30)) + ");");

            boolean first = true;
            if (className.endsWith("Command") && commandParameterGroup != null && commandParameterGroup.size() > 0
                    && commandParameterGroup.get(0).parameters != null
                    && commandParameterGroup.get(0).parameters.size() != 0) {
                out.println("        // First present the command parameters...");
                out.println("        // Then the responses later if they are available");
                for (ParameterGroup group : commandParameterGroup) {
                    createToString(out, "        ", first, className, group);
                    first = false;
                }
            }

            for (ParameterGroup group : responseParameterGroup) {
                if (group == null || group.parameters == null || group.parameters.isEmpty()) {
                    continue;
                }

                if (Boolean.TRUE.equals(group.multiple)) {
                    if (first) {
                        out.println("        builder.append(\"" + className + " [" + stringToLowerCamelCase(group.name)
                                + "s=\");");
                    } else {
                        out.println("        builder.append(\", " + stringToLowerCamelCase(group.name) + "s=\");");
                    }
                    out.println("        builder.append(" + stringToLowerCamelCase(group.name) + "s);");
                    first = false;
                    continue;
                }
                createToString(out, "        ", first, className, group);
                first = false;
            }

            if (className.endsWith("Command")) {
                if (first) {
                    out.println("        builder.append(\"" + className + " [\");");
                }
            }

            out.println("        builder.append(']');");
            out.println("        return builder.toString();");
        }
        out.println("    }");

        for (ParameterGroup group : responseParameterGroup) {
            // Multiple response elements at group level get their own class
            if (!Boolean.TRUE.equals(group.multiple)) {
                continue;
            }

            out.println();
            out.println("    /**");
            out.println("     *");
            out.println("     */");
            out.println("    class " + stringToUpperCamelCase(group.name) + " extends XBeeFrame {");
            for (Parameter parameter : group.parameters) {
                if (parameter.auto_size != null) {
                    continue;
                }

                out.println("        /**");
                if (parameter.description != null && parameter.description.length() > 0) {
                    outputWithLinebreak(out, "        ", parameter.description);
                }
                out.println("         */");

                createParameterDefinition(out, "        ", parameter);
                out.println();
            }

            out.println("        /**");
            out.println("         * Constructor to deserialize the received data");
            out.println("         */");
            out.println("        " + stringToUpperCamelCase(group.name) + "(final int[] data) {");
            out.println("            initialiseDeserializer(data);");
            createDeserializer(out, "            ", group);
            out.println("        }");

            out.println();

            createParameterGetter(out, "        ", group.parameters);
            // createParameterSetter(out, group.parameters);

            out.println("        @Override");
            out.println("        public String toString() {");

            if (group.parameters == null || group.parameters.size() == 0) {
                out.println("            return \"" + className + " []\";");
            } else {
                out.println("            final StringBuilder builder = new StringBuilder("
                        + (group.name.length() + 3 + ((group.parameters.size() + 1) * 30)) + ");");

                createToString(out, "            ", true, stringToUpperCamelCase(group.name), group);

                out.println("            builder.append(']');");
                out.println("            return builder.toString();");
            }
            out.println("        }");

            out.println("    }");
        }

        out.println("}");

        out.flush();

        File packageFile = new File(sourceRootPath + packageName.replace(".", "/"));
        PrintStream outFile = getClassOut(packageFile, className);

        outputCopywrite(outFile);
        outFile.println("package " + packageName + ";");

        outFile.println();

        outputImports(outFile);

        outFile.println();
        outFile.print(stringWriter.toString());

        outFile.flush();
        outFile.close();

        out.close();
    }

    private void createParameterDefinition(PrintStream out, String indent, Parameter parameter) {
        if (parameter.multiple | parameter.bitfield) {
            addImport("java.util.List");
            addImport("java.util.ArrayList");
            out.println(indent + "private List<" + getTypeClass(parameter.data_type) + "> "
                    + stringToLowerCamelCase(parameter.name) + " = new ArrayList<" + getTypeClass(parameter.data_type)
                    + ">();");
        } else {
            out.print(indent + "private " + getTypeClass(parameter.data_type) + " "
                    + stringToLowerCamelCase(parameter.name));
            if (parameter.defaultValue != null && parameter.defaultValue.length() != 0) {
                out.print(" = " + parameter.defaultValue);
            }
            out.println(";");
        }
    }

    private void createParameterGetter(PrintStream out, String indent, List<Parameter> parameters) {
        for (Parameter parameter : parameters) {
            if (parameter.auto_size != null) {
                continue;
            }
            // Constant...
            if (parameter.value != null && parameter.value.length() != 0) {
                continue;
            }

            out.println(indent + "/**");
            if (parameter.description != null && parameter.description.length() != 0) {
                outputWithLinebreak(out, indent, parameter.description);
            }
            out.println(indent + " *");
            if (parameter.multiple) {
                out.println(indent + " * @return the " + stringToLowerCamelCase(parameter.name) + " as List of {@link "
                        + getTypeClass(parameter.data_type) + "}");
            } else {
                out.println(indent + " * @return the " + stringToLowerCamelCase(parameter.name) + " as {@link "
                        + getTypeClass(parameter.data_type) + "}");
            }
            out.println(indent + " */");
            if (parameter.multiple | parameter.bitfield) {
                out.println(indent + "public List<" + getTypeClass(parameter.data_type) + "> get"
                        + stringToUpperCamelCase(parameter.name) + "() {");

            } else {
                out.println(indent + "public " + getTypeClass(parameter.data_type) + " get"
                        + stringToUpperCamelCase(parameter.name) + "() {");
            }
            out.println(indent + "    return " + stringToLowerCamelCase(parameter.name) + ";");
            out.println(indent + "}");
            out.println();
        }
    }

    private void createParameterSetter(PrintStream out, String indent, List<Parameter> parameters) {
        for (Parameter parameter : parameters) {
            if (parameter.auto_size != null) {
                continue;
            }

            // Constant...
            if (parameter.value != null && parameter.value.length() != 0) {
                continue;
            }

            out.println(indent + "/**");
            if (parameter.description != null && parameter.description.length() != 0) {
                outputWithLinebreak(out, "    ", parameter.description);
            }
            out.println(indent + " *");
            if (parameter.multiple | parameter.bitfield) {
                addImport("java.util.Collection");
                out.println(indent + " * @param " + stringToLowerCamelCase(parameter.name) + " the "
                        + stringToLowerCamelCase(parameter.name) + " to add to the Set as {@link "
                        + getTypeClass(parameter.data_type) + "}");
                out.println(indent + " */");
                out.println(indent + "public void add" + stringToUpperCamelCase(parameter.name) + "("
                        + getTypeClass(parameter.data_type) + " " + stringToLowerCamelCase(parameter.name) + ") {");
                out.println(indent + "    this." + stringToLowerCamelCase(parameter.name) + ".add("
                        + stringToLowerCamelCase(parameter.name) + ");");
                out.println(indent + "}");
                out.println();
                out.println(indent + "/**");
                if (parameter.description != null && parameter.description.length() != 0) {
                    outputWithLinebreak(out, "    ", parameter.description);
                }
                out.println(indent + " *");
                out.println(indent + " * @param " + stringToLowerCamelCase(parameter.name) + " the "
                        + stringToLowerCamelCase(parameter.name) + " to remove to the Set as {@link "
                        + getTypeClass(parameter.data_type) + "}");
                out.println(indent + " */");
                out.println(indent + "public void remove" + stringToUpperCamelCase(parameter.name) + "("
                        + getTypeClass(parameter.data_type) + " " + stringToLowerCamelCase(parameter.name) + ") {");
                out.println(indent + "    this." + stringToLowerCamelCase(parameter.name) + ".remove("
                        + stringToLowerCamelCase(parameter.name) + ");");
                out.println(indent + "}");
                out.println();
                out.println(indent + "/**");
                if (parameter.description != null && parameter.description.length() != 0) {
                    outputWithLinebreak(out, "    ", parameter.description);
                }
                out.println(indent + " *");
                out.println(indent + " * @param " + stringToLowerCamelCase(parameter.name) + " the "
                        + stringToLowerCamelCase(parameter.name) + " to set to the Set as {@link "
                        + getTypeClass(parameter.data_type) + "}");
                out.println(indent + " */");
                out.println(indent + "public void set" + stringToUpperCamelCase(parameter.name) + "(Collection<"
                        + getTypeClass(parameter.data_type) + "> " + stringToLowerCamelCase(parameter.name) + ") {");
                out.println(indent + "    this." + stringToLowerCamelCase(parameter.name) + ".addAll("
                        + stringToLowerCamelCase(parameter.name) + ");");
            } else {
                out.println(indent + " * @param " + stringToLowerCamelCase(parameter.name) + " the "
                        + stringToLowerCamelCase(parameter.name) + " to set as {@link "
                        + getTypeClass(parameter.data_type) + "}");
                out.println(indent + " */");
                out.println(indent + "public void set" + stringToUpperCamelCase(parameter.name) + "("
                        + getTypeClass(parameter.data_type) + " " + stringToLowerCamelCase(parameter.name) + ") {");

                if (parameter.minimum != null && parameter.maximum != null) {
                    out.println(indent + "    if (" + stringToLowerCamelCase(parameter.name) + " < " + parameter.minimum
                            + " || " + stringToLowerCamelCase(parameter.name) + " > " + parameter.maximum + ") {");
                    out.println(indent
                            + "        throw(new IllegalArgumentException(\"Illegal value passed for channel. Range is "
                            + parameter.minimum + " to " + parameter.maximum + ".\"));");
                    out.println(indent + "    }");
                } else if (parameter.minimum != null) {
                    out.println(indent + "    if (" + stringToLowerCamelCase(parameter.name) + " < " + parameter.minimum
                            + " || " + stringToLowerCamelCase(parameter.name) + " > " + parameter.maximum + ") {");
                    out.println(indent
                            + "        throw(new IllegalArgumentException(\"Illegal value passed for channel. Value must be greater than "
                            + parameter.minimum + ".\"));");
                    out.println(indent + "    }");
                } else if (parameter.maximum != null) {
                    out.println(indent + "    if (" + stringToLowerCamelCase(parameter.name) + " < " + parameter.minimum
                            + " || " + stringToLowerCamelCase(parameter.name) + " > " + parameter.maximum + ") {");
                    out.println(indent
                            + "        throw(new IllegalArgumentException(\"Illegal value passed for channel. Value must be less than "
                            + parameter.maximum + ".\"));");
                    out.println(indent + "    }");
                }

                out.println(indent + "    this." + stringToLowerCamelCase(parameter.name) + " = "
                        + stringToLowerCamelCase(parameter.name) + ";");
            }
            out.println(indent + "}");
            out.println();
        }
    }

    private void createDeserializer(PrintStream out, String indent, ParameterGroup group) {
        Map<String, String> autoSizers = new HashMap<String, String>();
        String conditional = null;

        int cnt = 0;
        for (Parameter parameter : group.parameters) {
            // Constant
            if (parameter.value != null && parameter.value.length() != 0) {
                out.println(indent + "deserialize" + getTypeSerializer(parameter.data_type) + "();");
                continue;
            }

            if (parameter.conditional != null && parameter.conditional.length() != 0) {
                if (conditional == null) {
                    out.print(indent + "if (" + parameter.conditional + ") {");
                    conditional = parameter.conditional;
                    indent = indent + "    ";
                } else {
                    if (!parameter.conditional.equals(conditional)) {
                        // New condition
                        indent = indent.substring(0, indent.length() - 4);
                        out.println(indent + "}");
                        out.println();
                        out.print(indent + "if (" + parameter.conditional + ") {");
                        conditional = parameter.conditional;
                        indent = indent + "    ";
                    }
                }
            } else if (conditional != null) {
                conditional = null;
                indent = indent.substring(0, indent.length() - 4);
                out.println(indent + "}");
            }

            cnt++;
            out.println();
            out.print(indent + "// Deserialize field \"" + parameter.name + "\"");
            if (parameter.optional != null && parameter.optional == true) {
                out.println(" [optional]");
            } else {
                out.println();
            }

            if (parameter.auto_size != null) {
                out.println(indent + "int " + stringToLowerCamelCase(parameter.name) + " = deserialize"
                        + getTypeSerializer(parameter.data_type) + "();");
                autoSizers.put(parameter.auto_size, parameter.name);
                continue;
            }
            if (autoSizers.get(parameter.name) != null) {
                out.println(indent + stringToLowerCamelCase(parameter.name) + " = deserialize"
                        + getTypeSerializer(parameter.data_type) + "("
                        + stringToLowerCamelCase(autoSizers.get(parameter.name)) + ");");
                continue;
            }
            if (parameter.data_type.contains("[") && parameter.data_type.contains("]")
                    && !parameter.data_type.contains("[]")) {
                int length = Integer.parseInt(parameter.data_type.substring(parameter.data_type.indexOf("[") + 1,
                        parameter.data_type.indexOf("]")));
                out.println(indent + stringToLowerCamelCase(parameter.name) + " = deserialize"
                        + getTypeSerializer(parameter.data_type) + "(" + length + ");");
                continue;
            }

            if (parameter.multiple) {
                out.println(indent + "while (true) {");
                out.println(indent + "    " + getTypeClass(parameter.data_type) + " tmp"
                        + stringToUpperCamelCase(parameter.name) + " = deserialize"
                        + getTypeSerializer(parameter.data_type) + "();");

                out.println(indent + "    if (tmp" + stringToUpperCamelCase(parameter.name) + " == null) {");
                out.println(indent + "        break;");
                out.println(indent + "    }");
                // out.println(indent + " stepDeserializer();");
                out.println(indent + "    " + stringToLowerCamelCase(parameter.name) + ".add(tmp"
                        + stringToUpperCamelCase(parameter.name) + ");");
                out.println(indent + "}");
            } else {
                out.println(indent + stringToLowerCamelCase(parameter.name) + " = deserialize"
                        + getTypeSerializer(parameter.data_type) + "();");
            }

            if (getTypeSerializer(parameter.data_type).equals("CommandStatus")
                    && !(group.parameters.indexOf(parameter) == (group.parameters.size() - 1))) {
                out.println(indent + "if (commandStatus != CommandStatus.OK || isComplete()) {");
                out.println(indent + "    return;");
                out.println(indent + "}");
            }

            if (cnt < group.parameters.size()) {
                if (parameter.optional != null && parameter.optional == true) {
                } else {
                }

            }
        }

        if (conditional != null) {
            indent = indent.substring(0, indent.length() - 4);
            out.println(indent + "}");
        }
    }

    private void createToString(PrintStream out, String indent, boolean first, String className, ParameterGroup group) {
        boolean extraIndent = false;
        for (Parameter parameter : group.parameters) {
            if (parameter.auto_size != null) {
                continue;
            }
            // Constant...
            if (parameter.value != null && parameter.value.length() != 0) {
                continue;
            }

            if (first) {
                out.println(indent + "builder.append(\"" + className + " [" + stringToLowerCamelCase(parameter.name)
                        + "=\");");
            } else {
                out.println(indent + "builder.append(\", " + stringToLowerCamelCase(parameter.name) + "=\");");
            }
            first = false;
            if (parameter.data_type.equals("Data")) {
                out.println(indent + "if (" + stringToLowerCamelCase(parameter.name) + " == null) {");
                out.println(indent + "    builder.append(\"null\");");
                out.println(indent + "} else {");
                out.println(indent + "    for (int cnt = 0; cnt < " + stringToLowerCamelCase(parameter.name)
                        + ".length; cnt++) {");
                out.println(indent + "        if (cnt > 0) {");
                out.println(indent + "            builder.append(' ');");
                out.println(indent + "        }");
                out.println(indent + "        builder.append(String.format(\"%02X\", "
                        + formatParameterString(parameter) + "[cnt]));");
                out.println(indent + "    }");
                out.println(indent + "}");
            } else if (parameter.data_type.contains("[]")) {
                String sizer = "2";
                if (parameter.data_type.contains("16")) {
                    sizer = "4";
                }
                out.println(indent + "if (" + stringToLowerCamelCase(parameter.name) + " == null) {");
                out.println(indent + "    builder.append(\"null\");");
                out.println(indent + "} else {");
                out.println(indent + "    for (int cnt = 0; cnt < " + stringToLowerCamelCase(parameter.name)
                        + ".length; cnt++) {");
                out.println(indent + "        if (cnt > 0) {");
                out.println(indent + "            builder.append(' ');");
                out.println(indent + "        }");
                out.println(indent + "        builder.append(String.format(\"%0" + sizer + "X\", "
                        + formatParameterString(parameter) + "[cnt]));");
                out.println(indent + "    }");
                out.println(indent + "}");
            } else {
                out.println(indent + "builder.append(" + formatParameterString(parameter) + ");");
            }

            if (getTypeSerializer(parameter.data_type).equals("CommandStatus")
                    && !(group.parameters.indexOf(parameter) == (group.parameters.size() - 1))) {
                out.println(indent + "if (commandStatus == CommandStatus.OK) {");
                indent += "    ";
                extraIndent = true;
            }
        }

        if (extraIndent) {
            indent = indent.substring(0, indent.length() - 4);
            out.println(indent + "}");
        }
    }

    private void createEnumClass(Enumeration enumeration) throws FileNotFoundException, UnsupportedEncodingException {
        String className = upperCaseFirstCharacter(enumeration.name);
        System.out.println("Processing enum class " + enumeration.name + "  [" + className + "()]");

        OutputStream stringWriter = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(stringWriter,false,"UTF-8");

        clearImports();

        boolean hasValues = false;
        for (Value value : enumeration.values) {
            if (value.enum_value != null) {
                hasValues = true;
                break;
            }
        }

        if (hasValues) {
            addImport("java.util.Map");
            addImport("java.util.HashMap");
        }

        out.println("/**");
        out.println(" * Class to implement the XBee Enumeration <b>" + enumeration.name + "</b>.");
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
        if (hasValues) {
            out.println("    UNKNOWN(-1),");
        } else {
            out.println("    UNKNOWN,");
        }

        boolean first = true;
        for (Value value : enumeration.values) {
            if (!first) {
                out.println(",");
            }
            first = false;
            out.println();
            out.println("    /**");
            if (hasValues) {
                outputWithLinebreak(out, "    ", "[" + value.enum_value + "] " + value.description);
            } else {
                outputWithLinebreak(out, "    ", value.description);
            }
            out.println("     */");
            if (hasValues) {
                out.print(
                        "    " + stringToConstant(value.name) + "(0x" + String.format("%04X", value.enum_value) + ")");
            } else {
                out.print("    " + stringToConstant(value.name));
            }
        }

        out.println(";");

        if (hasValues) {
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
            out.println("     * Lookup function based on the type code. Returns null if the code does not exist.");
            out.println("     *");
            out.println("     * @param " + lowerCaseFirstCharacter(className));
            out.println("     *            the code to lookup");
            out.println("     * @return enumeration value.");
            out.println("     */");
            out.println("    public static " + className + " get" + className + "(int "
                    + lowerCaseFirstCharacter(className) + ") {");
            out.println("        if (codeMapping == null) {");
            out.println("            initMapping();");
            out.println("        }");
            out.println();

            out.println("        if (codeMapping.get(" + lowerCaseFirstCharacter(className) + ") == null) {");
            out.println("            return UNKNOWN;");
            out.println("        }");
            out.println();

            out.println("        return codeMapping.get(" + lowerCaseFirstCharacter(className) + ");");
            out.println("    }");
            out.println();
            out.println("    /**");
            out.println("     * Returns the XBee protocol defined value for this enum");
            out.println("     *");
            out.println("     * @return the XBee enumeration key");
            out.println("     */");
            out.println("    public int getKey() {");
            out.println("        return key;");
            out.println("    }");
        }
        out.println("}");

        out.flush();

        File packageFile = new File(sourceRootPath + enumPackage.replace(".", "/"));
        PrintStream outFile = getClassOut(packageFile, className);

        outputCopywrite(outFile);
        outFile.println("package " + enumPackage + ";");

        outFile.println();

        outputImports(outFile);

        outFile.println();
        outFile.print(stringWriter.toString());

        outFile.flush();
        outFile.close();

        out.close();
    }

    private void createEventFactory(String className, Protocol protocol) throws FileNotFoundException, UnsupportedEncodingException {
        OutputStream stringWriter = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(stringWriter,false,"UTF-8");

        clearImports();
        addImport(commandPackage + ".XBee" + className);
        addImport("java.lang.reflect.Constructor");
        addImport("org.slf4j.Logger");
        addImport("org.slf4j.LoggerFactory");
        addImport("java.util.Map");
        addImport("java.util.concurrent.ConcurrentHashMap");

        out.println();

        out.println("/**");
        out.println(" * Helper factory class to create {@link XBee" + className + "} classes.");
        out.println(" * <p>");
        out.println(" * Note that this code is autogenerated. Manual changes may be overwritten.");
        out.println(" *");
        out.println(" * @author Chris Jackson - Initial contribution of Java code generator");
        out.println(" */");

        out.println("public class XBee" + className + "Factory {");
        out.println("    private final static Logger logger = LoggerFactory.getLogger(XBeeEventFactory.class);");
        out.println();

        out.println("    private static Map<Integer, Class<?>> events = new ConcurrentHashMap<Integer, Class<?>>();");
        if (className == "Response") {
            out.println(
                    "    private static Map<Integer, Class<?>> atCommands = new ConcurrentHashMap<Integer, Class<?>>();");
        }
        out.println();

        Map<Integer, String> sortedEvents = new TreeMap<Integer, String>();
        for (Command command : protocol.commands) {
            if (command.command_parameters.size() > 0) {
                continue;
            }
            String responseType = "Event";
            for (Parameter parameter : command.response_parameters.get(0).parameters) {
                if (parameter.name.toUpperCase().equals("FRAME ID")) {
                    responseType = "Response";
                    break;
                }
            }
            if (className != responseType) {
                continue;
            }

            // The following check is necessary to prevent the AT command class from overwriting the actual AT Response
            if (sortedEvents.get(command.id) != null) {
                continue;
            }

            String eventClassName = "XBee" + stringToUpperCamelCase(command.name) + responseType;
            sortedEvents.put(command.id, eventClassName);
            addImport(commandPackage + "." + eventClassName);
        }

        out.println("    static {");
        out.println("        // Define the API commands");
        for (Integer event : sortedEvents.keySet()) {
            out.println("        events.put(" + String.format("0x%02X", event) + ", " + sortedEvents.get(event)
                    + ".class);");
        }

        if (className == "Response") {
            Map<String, String> sortedAt = new TreeMap<String, String>();

            out.println();
            out.println("        // Define the AT commands");
            for (Command atCommand : protocol.at_commands) {
                addImport(commandPackage + ".XBee" + stringToUpperCamelCase(atCommand.name) + "Response");
                sortedAt.put(atCommand.command, atCommand.name);
            }

            for (String cmd : sortedAt.keySet()) {
                Integer cmdInt = Integer.valueOf(cmd.charAt(1) + (cmd.charAt(0) << 8));
                out.println("        atCommands.put(" + String.format("0x%04X", cmdInt) + ", XBee"
                        + stringToUpperCamelCase(sortedAt.get(cmd)) + "Response.class); // " + cmd);
            }

        }
        out.println("    }");
        out.println();

        out.println("    public static XBee" + className + " getXBeeFrame(int[] data) {");

        if (className == "Response") {
            out.println("        Class<?> xbeeClass = null;");
            out.println();
            out.println("        // Try and correlate any AT command responses first");
            out.println("        if (data[2] == 0x88) {");
            out.println("            xbeeClass = atCommands.get((data[4] << 8) + data[5]);");
            out.println("        }");
            out.println();
            out.println("        // If not found, then use the API commands");
            out.println("        if (xbeeClass == null) {");
            out.println("            xbeeClass = events.get(data[2]);");
            out.println("        }");
        } else {
            out.println("        Class<?> xbeeClass = events.get(data[2]);");
        }
        out.println();
        out.println("        // No handler found");
        out.println("        if (xbeeClass == null) {");
        out.println("            return null;");
        out.println("        }");
        out.println();
        out.println("        Constructor<?> ctor;");
        out.println("        try {");
        out.println("            ctor = xbeeClass.getConstructor();");
        out.println("            XBee" + className + " xbeeFrame = (XBee" + className + ") ctor.newInstance();");
        out.println("            xbeeFrame.deserialize(data);");
        out.println("            return xbeeFrame;");
        out.println("        } catch (Exception e) {");
        out.println("            logger.debug(\"Error creating instance of XBee" + className + "\", e);");
        out.println("        }");
        out.println();
        out.println("        return null;");
        out.println("    }");

        out.println("}");

        out.flush();

        File packageFile = new File(sourceRootPath + internalPackage.replace(".", "/"));
        PrintStream outFile = getClassOut(packageFile, "XBee" + className + "Factory");

        outputCopywrite(outFile);
        outFile.println("package " + internalPackage + ";");

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

        switch (dataTypeLocal) {
            case "uint8[]":
                return "int[]";
            case "Data":
                return "int[]";
            case "uint16[]":
                return "int[]";
            case "uint8":
            case "uint16":
            case "Integer":
                return "Integer";
            case "Boolean":
                return "Boolean";
            case "AtCommand":
                return "String";
            case "String":
                return "String";
            case "ZigBeeKey":
                addImport(zigbeeSecurityPackage + ".ZigBeeKey");
                return "ZigBeeKey";
            case "IeeeAddress":
                addImport(zigbeePackage + ".IeeeAddress");
                return "IeeeAddress";
            case "ExtendedPanId":
                addImport(zigbeePackage + ".ExtendedPanId");
                return "ExtendedPanId";
            case "ZigBeeDeviceAddress":
                addImport(zigbeePackage + ".ZigBeeDeviceAddress");
                return "ZigBeeDeviceAddress";
            case "ZigBeeGroupAddress":
                addImport(zigbeePackage + ".ZigBeeGroupAddress");
                return "ZigBeeGroupAddress";
            default:
                addImport(enumPackage + "." + dataTypeLocal);
                return dataTypeLocal;
        }
    }

    protected String getTypeSerializer(String dataType) {
        String dataTypeLocal = new String(dataType);

        switch (dataTypeLocal) {
            case "uint8[]":
                return "Data";
            case "uint16[]":
                return "Int16Array";
            case "uint8":
                return "Int8";
            case "uint16":
                return "Int16";
            case "Data":
                return "Data";
            default:
                return dataTypeLocal;
        }
    }
}

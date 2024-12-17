/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.zstack.autocode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.zsmartsystems.zigbee.dongle.zstack.autocode.xml.Command;
import com.zsmartsystems.zigbee.dongle.zstack.autocode.xml.Command.ZstackRequestType;
import com.zsmartsystems.zigbee.dongle.zstack.autocode.xml.Enumeration;
import com.zsmartsystems.zigbee.dongle.zstack.autocode.xml.Parameter;
import com.zsmartsystems.zigbee.dongle.zstack.autocode.xml.Protocol;
import com.zsmartsystems.zigbee.dongle.zstack.autocode.xml.Structure;
import com.zsmartsystems.zigbee.dongle.zstack.autocode.xml.Value;

/**
 * Autocoder to generate Java class files for Z-Stack dongle
 *
 * @author Chris Jackson
 *
 */
public class ZstackAutocoder {
    public static void main(final String[] args) {

        Protocol protocol;
        try {
            // Load the class definitions
            File fXmlFile = new File("src/main/resources/zstack_protocol.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("protocol");
            protocol = (Protocol) processNode(nList.item(0));

        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        System.out.println("Generating code...");

        try {
            new CommandGenerator().go(protocol);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static Object processNode(Node node) {
        System.out.println("\nCurrent Element :" + node.getNodeName());

        NodeList nodes = node.getChildNodes();

        switch (node.getNodeName()) {
            case "protocol":
                Protocol protocol = new Protocol();
                protocol.commands = new ArrayList<>();
                protocol.structures = new ArrayList<>();
                protocol.enumerations = new ArrayList<>();

                for (int temp = 0; temp < nodes.getLength(); temp++) {
                    if (nodes.item(temp).getNodeName().equals("command")) {
                        protocol.commands.add((Command) processNode(nodes.item(temp)));
                    }
                    if (nodes.item(temp).getNodeName().equals("structure")) {
                        protocol.structures.add((Structure) processNode(nodes.item(temp)));
                    }
                    if (nodes.item(temp).getNodeName().equals("enum")) {
                        protocol.enumerations.add((Enumeration) processNode(nodes.item(temp)));
                    }
                }
                return protocol;
            case "command":
                Command command = new Command();

                for (int temp = 0; temp < nodes.getLength(); temp++) {
                    if (nodes.item(temp).getNodeName().equals("name")) {
                        command.name = nodes.item(temp).getTextContent().trim();
                    }
                    if (nodes.item(temp).getNodeName().equals("id")) {
                        String id = nodes.item(temp).getTextContent();
                        if (id.startsWith("0x")) {
                            command.id = Integer.parseInt(id.substring(2), 16);
                        } else {
                            command.id = Integer.parseInt(id.substring(2));
                        }
                    }
                    if (nodes.item(temp).getNodeName().equals("description")) {
                        command.description = nodes.item(temp).getTextContent().trim();
                    }
                    if (nodes.item(temp).getNodeName().equals("subsystem")) {
                        command.subsystem = nodes.item(temp).getTextContent().trim();
                    }
                    if (nodes.item(temp).getNodeName().equals("type")) {
                        command.requestType = ZstackRequestType.valueOf(nodes.item(temp).getTextContent().trim());
                    }

                    if (nodes.item(temp).getNodeName().equals("request")) {
                        if (command.request_parameters == null) {
                            command.request_parameters = new ArrayList<>();
                        }
                        command.request_parameters = (List<Parameter>) processNode(nodes.item(temp));
                    }
                    if (nodes.item(temp).getNodeName().equals("response")) {
                        if (command.response_parameters == null) {
                            command.response_parameters = new ArrayList<>();
                        }
                        command.response_parameters = (List<Parameter>) processNode(nodes.item(temp));
                    }
                }
                System.out.println("Done: Command - " + command.name);
                return command;
            case "request":
            case "response":
            case "parameters":
                List<Parameter> parameters = new ArrayList<>();

                for (int temp = 0; temp < nodes.getLength(); temp++) {
                    if (nodes.item(temp).getNodeName().equals("parameter")) {
                        parameters.add((Parameter) processNode(nodes.item(temp)));
                    }
                }
                return parameters;
            case "parameter":
                Parameter parameter = new Parameter();
                for (int temp = 0; temp < nodes.getLength(); temp++) {
                    if (nodes.item(temp).getNodeName().equals("data_type")) {
                        parameter.data_type = nodes.item(temp).getTextContent();
                        Element dataTypeElement = (Element) nodes.item(temp);
                        parameter.multiple = dataTypeElement.getAttribute("multiple").equalsIgnoreCase("true");
                    }
                    if (nodes.item(temp).getNodeName().equals("name")) {
                        parameter.name = nodes.item(temp).getTextContent().trim();
                    }
                    if (nodes.item(temp).getNodeName().equals("description")) {
                        parameter.description = nodes.item(temp).getTextContent().trim();
                    }
                    if (nodes.item(temp).getNodeName().equals("auto_size")) {
                        parameter.auto_size = nodes.item(temp).getTextContent().trim();
                    }
                    if (nodes.item(temp).getNodeName().equals("display")) {
                        String display = nodes.item(temp).getTextContent().trim();
                        if (display.contains("[") && display.contains("]")) {
                            parameter.displayType = display.substring(0, display.indexOf('['));
                            parameter.displayLength = Integer
                                    .parseInt(display.substring(display.indexOf('[') + 1, display.indexOf(']')));
                        }
                    }
                }
                System.out.println("Done: Parameter - " + parameter.name);
                return parameter;
            case "structure":
                Structure structure = new Structure();
                structure.parameters = new ArrayList<>();

                for (int temp = 0; temp < nodes.getLength(); temp++) {
                    if (nodes.item(temp).getNodeName().equals("name")) {
                        structure.name = nodes.item(temp).getTextContent().trim();
                    }
                    if (nodes.item(temp).getNodeName().equals("description")) {
                        structure.description = nodes.item(temp).getTextContent().trim();
                    }
                    if (nodes.item(temp).getNodeName().equals("subsystem")) {
                        structure.subsystem = nodes.item(temp).getTextContent().trim();
                    }

                    if (nodes.item(temp).getNodeName().equals("parameters")) {
                        structure.parameters = (List<Parameter>) processNode(nodes.item(temp));
                    }
                }
                System.out.println("Done: Structure - " + structure.name);
                return structure;
            case "enum":
                Enumeration enumeration = new Enumeration();
                enumeration.values = new ArrayList<Value>();

                for (int temp = 0; temp < nodes.getLength(); temp++) {
                    if (nodes.item(temp).getNodeName().equals("name")) {
                        enumeration.name = nodes.item(temp).getTextContent().trim();
                    }
                    if (nodes.item(temp).getNodeName().equals("data_type")) {
                        enumeration.data_type = nodes.item(temp).getTextContent().trim();
                    }
                    if (nodes.item(temp).getNodeName().equals("description")) {
                        enumeration.description = nodes.item(temp).getTextContent().trim();
                    }
                    if (nodes.item(temp).getNodeName().equals("values")) {
                        Element dataTypeElement = (Element) nodes.item(temp);
                        enumeration.fullyDefined = "true"
                                .equalsIgnoreCase(dataTypeElement.getAttribute("fully_defined"));
                        enumeration.values = (List<Value>) processNode(nodes.item(temp));
                    }
                    if (nodes.item(temp).getNodeName().equals("format")) {
                        enumeration.format = nodes.item(temp).getTextContent().trim();
                    }
                    if (nodes.item(temp).getNodeName().equals("subsystem")) {
                        enumeration.subsystem = nodes.item(temp).getTextContent().trim();
                    }
                }
                System.out.println("Done: Enum - " + enumeration.name);
                return enumeration;
            case "values":
                List<Value> values = new ArrayList<>();

                for (int temp = 0; temp < nodes.getLength(); temp++) {
                    if (nodes.item(temp).getNodeName().equals("value")) {
                        values.add((Value) processNode(nodes.item(temp)));
                    }
                }
                return values;
            case "value":
                Value value = new Value();
                for (int temp = 0; temp < nodes.getLength(); temp++) {
                    if (nodes.item(temp).getNodeName().equals("name")) {
                        value.name = nodes.item(temp).getTextContent();
                    }
                    if (nodes.item(temp).getNodeName().equals("enum_value")) {
                        String id = nodes.item(temp).getTextContent().trim();
                        if (id.startsWith("0x")) {
                            value.enum_value = Integer.parseInt(id.substring(2), 16);
                        } else {
                            value.enum_value = Integer.parseInt(id);
                        }
                    }
                    if (nodes.item(temp).getNodeName().equals("description")) {
                        value.description = nodes.item(temp).getTextContent().trim();
                    }
                }
                System.out.println("Done: Value - " + value.name);
                return value;
            default:
                System.out.println("Uknown node " + node.getNodeName());
                break;
        }

        return null;
    }

}

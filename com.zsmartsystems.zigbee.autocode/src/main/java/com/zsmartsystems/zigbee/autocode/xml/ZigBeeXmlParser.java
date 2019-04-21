/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.autocode.xml;

import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * XML file parser - reads the XML cluster definitions
 *
 * @author Chris Jackson (zsmartsystems.com)
 *
 */
public class ZigBeeXmlParser {
    List<String> files = new ArrayList<String>();

    public ZigBeeXmlParser() {
    }

    public void addFile(String filename) {
        files.add(filename);
    }

    public List<ZigBeeXmlCluster> parseClusterConfiguration() {
        List<ZigBeeXmlCluster> clusters = new ArrayList<>();

        try {
            for (String file : files) {
                System.out.println("Parsing cluster file: " + file);
                // Load the class definitions
                File fXmlFile = new File(file);
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(fXmlFile);
                doc.getDocumentElement().normalize();

                // Get all cluster specific definitions
                NodeList nList = doc.getElementsByTagName("cluster");
                ZigBeeXmlCluster cluster = (ZigBeeXmlCluster) processNode(nList.item(0));
                clusters.add(cluster);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return clusters;
    }

    public ZigBeeXmlGlobal parseGlobalConfiguration() {
        ZigBeeXmlGlobal globals = new ZigBeeXmlGlobal();
        globals.constants = new ArrayList<ZigBeeXmlConstant>();

        try {
            for (String file : files) {
                System.out.println("Parsing globals file: " + file);
                // Load the class definitions
                File fXmlFile = new File(file);
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(fXmlFile);
                doc.getDocumentElement().normalize();

                // Get all global specific definitions
                NodeList nList = doc.getElementsByTagName("zigbee");
                ZigBeeXmlGlobal global = (ZigBeeXmlGlobal) processNode(nList.item(0));
                globals.constants.addAll(global.constants);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return globals;
    }

    Object processNode(Node node) {
        NodeList nodes = node.getChildNodes();
        Element e;

        switch (node.getNodeName()) {
            case "zigbee":
                ZigBeeXmlGlobal global = new ZigBeeXmlGlobal();
                global.constants = new ArrayList<>();
                for (int temp = 0; temp < nodes.getLength(); temp++) {
                    if (nodes.item(temp).getNodeName().equals("constant")) {
                        global.constants.add((ZigBeeXmlConstant) processNode(nodes.item(temp)));
                    }
                }
                System.out.println("Done: Global");
                return global;

            case "cluster":
                ZigBeeXmlCluster cluster = new ZigBeeXmlCluster();

                e = (Element) node;
                cluster.code = getInteger(e.getAttribute("code")).intValue();

                cluster.description = new ArrayList<ZigBeeXmlDescription>();
                cluster.commands = new ArrayList<ZigBeeXmlCommand>();
                cluster.attributes = new ArrayList<ZigBeeXmlAttribute>();
                cluster.constants = new ArrayList<ZigBeeXmlConstant>();
                cluster.structures = new ArrayList<ZigBeeXmlStructure>();

                for (int temp = 0; temp < nodes.getLength(); temp++) {
                    if (nodes.item(temp).getNodeName().equals("name")) {
                        cluster.name = nodes.item(temp).getTextContent().trim();
                    }
                    if (nodes.item(temp).getNodeName().equals("description")) {
                        cluster.description.add((ZigBeeXmlDescription) processNode(nodes.item(temp)));
                    }
                    if (nodes.item(temp).getNodeName().equals("command")) {
                        cluster.commands.add((ZigBeeXmlCommand) processNode(nodes.item(temp)));
                    }
                    if (nodes.item(temp).getNodeName().equals("attribute")) {
                        cluster.attributes.add((ZigBeeXmlAttribute) processNode(nodes.item(temp)));
                    }
                    if (nodes.item(temp).getNodeName().equals("constant")) {
                        cluster.constants.add((ZigBeeXmlConstant) processNode(nodes.item(temp)));
                    }
                    if (nodes.item(temp).getNodeName().equals("struct")) {
                        cluster.structures.add((ZigBeeXmlStructure) processNode(nodes.item(temp)));
                    }
                }
                System.out.println("Done: Cluster - " + cluster.name);
                return cluster;

            case "attribute":
                ZigBeeXmlAttribute attribute = new ZigBeeXmlAttribute();
                attribute.description = new ArrayList<>();

                e = (Element) node;
                attribute.code = getInteger(e.getAttribute("code")).intValue();
                attribute.side = e.getAttribute("side");
                attribute.writable = Boolean.valueOf(e.getAttribute("writable"));
                attribute.reportable = Boolean.valueOf(e.getAttribute("reportable"));
                attribute.optional = Boolean.valueOf(e.getAttribute("optional"));
                attribute.type = e.getAttribute("type").trim();
                attribute.implementationClass = e.getAttribute("class").trim();
                attribute.minimumValue = getInteger(e.getAttribute("minimum"));
                attribute.maximumValue = getInteger(e.getAttribute("maximum"));
                attribute.defaultValue = getInteger(e.getAttribute("default"));

                if (getInteger(e.getAttribute("arraycount")) != null) {
                    attribute.arrayCount = getInteger(e.getAttribute("arraycount")).intValue();
                }
                if (getInteger(e.getAttribute("arraystart")) != null) {
                    attribute.arrayStart = getInteger(e.getAttribute("arraystart")).intValue();
                }
                if (getInteger(e.getAttribute("arraystep")) != null) {
                    attribute.arrayStep = getInteger(e.getAttribute("arraystep")).intValue();
                }

                for (int temp = 0; temp < nodes.getLength(); temp++) {
                    if (nodes.item(temp).getNodeName().equals("name")) {
                        attribute.name = nodes.item(temp).getTextContent().trim();
                    }
                    if (nodes.item(temp).getNodeName().equals("description")) {
                        attribute.description.add((ZigBeeXmlDescription) processNode(nodes.item(temp)));
                    }
                }

                return attribute;

            case "command":
                ZigBeeXmlCommand command = new ZigBeeXmlCommand();
                command.fields = new ArrayList<>();
                command.description = new ArrayList<>();

                e = (Element) node;

                command.name = e.getAttribute("name").trim();
                command.code = getInteger(e.getAttribute("code")).intValue();
                command.source = e.getAttribute("source");

                for (int temp = 0; temp < nodes.getLength(); temp++) {
                    if (nodes.item(temp).getNodeName().equals("name")) {
                        command.name = nodes.item(temp).getTextContent().trim();
                    }
                    if (nodes.item(temp).getNodeName().equals("description")) {
                        command.description.add((ZigBeeXmlDescription) processNode(nodes.item(temp)));
                    }
                    if (nodes.item(temp).getNodeName().equals("field")) {
                        command.fields.add((ZigBeeXmlField) processNode(nodes.item(temp)));
                    }
                    if (nodes.item(temp).getNodeName().equals("response")) {
                        command.response = (ZigBeeXmlResponse) processNode(nodes.item(temp));
                    }
                }
                System.out.println("Done: Command - " + command.name);
                return command;

            case "field":
                ZigBeeXmlField field = new ZigBeeXmlField();
                field.description = new ArrayList<>();

                e = (Element) node;
                if (e.getAttribute("completeOnZero").length() > 0) {
                    String x = e.getAttribute("completeOnZero");
                    System.out.println(x);

                }
                field.type = e.getAttribute("type").trim();
                field.completeOnZero = "true".equals(e.getAttribute("completeOnZero").trim());
                field.implementationClass = e.getAttribute("class").trim();
                field.array = Boolean.valueOf(e.getAttribute("array"));
                for (int temp = 0; temp < nodes.getLength(); temp++) {
                    if (nodes.item(temp).getNodeName().equals("name")) {
                        field.name = nodes.item(temp).getTextContent().trim();
                    }
                    if (nodes.item(temp).getNodeName().equals("sizer")) {
                        field.sizer = nodes.item(temp).getTextContent().trim();
                    }
                    if (nodes.item(temp).getNodeName().equals("description")) {
                        field.description.add((ZigBeeXmlDescription) processNode(nodes.item(temp)));
                    }
                    if (nodes.item(temp).getNodeName().equals("conditional")) {
                        field.condition = (ZigBeeXmlCondition) processNode(nodes.item(temp));
                    }
                }
                System.out.println("Done: Field - " + field.name);
                return field;

            case "constant":
                ZigBeeXmlConstant constant = new ZigBeeXmlConstant();
                constant.description = new ArrayList<>();

                e = (Element) node;

                constant.className = e.getAttribute("class").trim();

                constant.values = new TreeMap<>();

                for (int temp = 0; temp < nodes.getLength(); temp++) {
                    if (nodes.item(temp).getNodeName().equals("name")) {
                        constant.name = nodes.item(temp).getTextContent().trim();
                    }
                    if (nodes.item(temp).getNodeName().equals("description")) {
                        constant.description.add((ZigBeeXmlDescription) processNode(nodes.item(temp)));
                    }
                    if (nodes.item(temp).getNodeName().equals("value")) {
                        e = (Element) nodes.item(temp);
                        String name = e.getAttribute("name").trim();
                        BigInteger value = getInteger(e.getAttribute("code").trim());
                        constant.values.put(value, name);
                    }
                }

                return constant;

            case "struct":
                ZigBeeXmlStructure structure = new ZigBeeXmlStructure();
                structure.fields = new ArrayList<>();
                structure.description = new ArrayList<>();

                e = (Element) node;

                structure.name = e.getAttribute("name").trim();
                structure.className = e.getAttribute("class").trim();

                for (int temp = 0; temp < nodes.getLength(); temp++) {
                    if (nodes.item(temp).getNodeName().equals("name")) {
                        structure.name = nodes.item(temp).getTextContent().trim();
                    }
                    if (nodes.item(temp).getNodeName().equals("description")) {
                        structure.description.add((ZigBeeXmlDescription) processNode(nodes.item(temp)));
                    }
                    if (nodes.item(temp).getNodeName().equals("field")) {
                        structure.fields.add((ZigBeeXmlField) processNode(nodes.item(temp)));
                    }
                }
                System.out.println("Done: Structure - " + structure.name);
                return structure;

            case "description":
                ZigBeeXmlDescription description = new ZigBeeXmlDescription();

                e = (Element) node;

                if (nodes.item(0) != null && nodes.item(0).getTextContent() != null) {
                    description.description = nodes.item(0).getTextContent().trim();
                }
                if (e.getAttribute("format") != null) {
                    description.format = e.getAttribute("format").trim();
                }

                return description;

            case "conditional":
                ZigBeeXmlCondition condition = new ZigBeeXmlCondition();

                e = (Element) node;
                for (int temp = 0; temp < nodes.getLength(); temp++) {
                    if (nodes.item(temp).getNodeName().equals("field")) {
                        condition.field = nodes.item(temp).getTextContent().trim();
                    }
                    if (nodes.item(temp).getNodeName().equals("operator")) {
                        condition.operator = nodes.item(temp).getTextContent().trim();
                    }
                    if (nodes.item(temp).getNodeName().equals("value")) {
                        condition.value = nodes.item(temp).getTextContent().trim();
                    }
                }
                System.out.println("Done: Condition - " + condition.field);
                return condition;

            case "response":
                ZigBeeXmlResponse response = new ZigBeeXmlResponse();
                response.matchers = new ArrayList<>();

                e = (Element) node;
                response.command = e.getAttribute("command").trim();

                for (int temp = 0; temp < nodes.getLength(); temp++) {
                    if (nodes.item(temp).getNodeName().equals("matcher")) {
                        response.matchers.add((ZigBeeXmlMatcher) processNode(nodes.item(temp)));
                    }
                }
                System.out.println("Done: Response - " + response.command);
                return response;

            case "matcher":
                ZigBeeXmlMatcher matcher = new ZigBeeXmlMatcher();

                e = (Element) node;
                matcher.commandField = e.getAttribute("commandField").trim();
                matcher.responseField = e.getAttribute("responseField").trim();

                System.out.println("Done: Matcher - " + matcher.commandField);
                return matcher;
        }

        return null;
    }

    private static BigInteger getInteger(String value) {
        if (value == null || value.length() == 0) {
            return null;
        }

        String lwrValue = value.toLowerCase();
        try {
            if (lwrValue.startsWith("0x")) {
                return new BigInteger(lwrValue.substring(2), 16);
            } else {
                return new BigInteger(lwrValue);
            }
        } catch (NumberFormatException e) {
            return null;
        }
    }
}

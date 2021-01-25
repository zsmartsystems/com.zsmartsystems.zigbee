/**
 * Copyright (c) 2016-2021 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.database.ZclAttributeDao;
import com.zsmartsystems.zigbee.database.ZclClusterDao;
import com.zsmartsystems.zigbee.database.ZigBeeEndpointDao;
import com.zsmartsystems.zigbee.database.ZigBeeNetworkDataStore;
import com.zsmartsystems.zigbee.database.ZigBeeNodeDao;
import com.zsmartsystems.zigbee.security.ZigBeeKey;
import com.zsmartsystems.zigbee.zdo.field.BindingTable;
import com.zsmartsystems.zigbee.zdo.field.NodeDescriptor.FrequencyBandType;
import com.zsmartsystems.zigbee.zdo.field.NodeDescriptor.MacCapabilitiesType;
import com.zsmartsystems.zigbee.zdo.field.NodeDescriptor.ServerCapabilitiesType;
import com.zsmartsystems.zigbee.zdo.field.PowerDescriptor.PowerSourceType;

/**
 * Serializes and deserializes the ZigBee network state.
 *
 * @author Chris Jackson
 */
public class ZigBeeDataStore implements ZigBeeNetworkDataStore {
    /**
     * The logger.
     */
    private final static Logger logger = LoggerFactory.getLogger(ZigBeeDataStore.class);

    private final String networkId;

    public ZigBeeDataStore(String networkId) {
        this.networkId = "database/" + networkId + "/";
        File file = new File(this.networkId);
        if (file.exists()) {
            return;
        }
        if (!file.mkdirs()) {
            logger.error("Error creating network database folder {}", file);
        }
    }

    private XStream openStream() {
        try {
            XStream stream = new XStream(new StaxDriver());
            XStream.setupDefaultSecurity(stream);
            stream.alias("ZigBeeKey", ZigBeeKey.class);
            stream.alias("ZigBeeNode", ZigBeeNodeDao.class);
            stream.alias("ZigBeeEndpoint", ZigBeeEndpointDao.class);
            stream.alias("ZclCluster", ZclClusterDao.class);
            stream.alias("ZclAttribute", ZclAttributeDao.class);
            stream.alias("MacCapabilitiesType", MacCapabilitiesType.class);
            stream.alias("ServerCapabilitiesType", ServerCapabilitiesType.class);
            stream.alias("PowerSourceType", PowerSourceType.class);
            stream.alias("FrequencyBandType", FrequencyBandType.class);
            stream.alias("BindingTable", BindingTable.class);
            stream.alias("IeeeAddress", IeeeAddress.class);
            stream.registerConverter(new IeeeAddressConverter());

            // stream.registerLocalConverter(ZigBeeKey.class, "key", new KeyArrayConverter());
            // stream.registerLocalConverter(ZigBeeKey.class, "address", new IeeeAddressConverter());
            // stream.registerLocalConverter(BindingTable.class, "srcAddr", new IeeeAddressConverter());
            // stream.registerLocalConverter(BindingTable.class, "dstAddr", new IeeeAddressConverter());

            stream.allowTypesByWildcard(new String[] {
                    "com.zsmartsystems.zigbee.**"
            });

            return stream;
        } catch (Exception e) {
            logger.debug("Error opening XStream ", e);
            return null;
        }
    }

    private File getFile(IeeeAddress address) {
        return new File(networkId + address + ".xml");
    }

    private File getFile(String key) {
        return new File(networkId + "keystore/" + key + ".xml");
    }

    @Override
    public Set<IeeeAddress> readNetworkNodes() {
        Set<IeeeAddress> nodes = new HashSet<>();
        File dir = new File(networkId);
        File[] files = dir.listFiles();

        if (files == null) {
            return nodes;
        }

        for (File file : files) {
            if (!file.getName().toLowerCase().endsWith(".xml")) {
                continue;
            }

            try {
                IeeeAddress address = new IeeeAddress(file.getName().substring(0, 16));
                nodes.add(address);
            } catch (IllegalArgumentException e) {
                logger.error("Error parsing database filename: {}", file.getName());
            }
        }

        return nodes;
    }

    @Override
    public ZigBeeNodeDao readNode(IeeeAddress address) {
        XStream stream = openStream();
        File file = getFile(address);

        ZigBeeNodeDao node = null;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"))) {
            node = (ZigBeeNodeDao) stream.fromXML(reader);
            reader.close();
            logger.info("{}: ZigBee reading network state complete.", address);
        } catch (Exception e) {
            logger.error("{}: Error reading network state: ", address, e);
        }

        return node;
    }

    @Override
    public void writeNode(ZigBeeNodeDao node) {
        XStream stream = openStream();
        File file = getFile(node.getIeeeAddress());

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"))) {
            stream.marshal(node, new PrettyPrintWriter(writer));
            writer.close();
            logger.info("{}: ZigBee saving network state complete.", node.getIeeeAddress());
        } catch (Exception e) {
            logger.error("{}: Error writing network state: ", node.getIeeeAddress(), e);
        }
    }

    @Override
    public void removeNode(IeeeAddress address) {
        File file = getFile(address);
        if (!file.delete()) {
            logger.error("{}: Error removing network state", address);
        }
    }

    @Override
    public void writeObject(String key, Object object) {
        XStream stream = openStream();
        File file = getFile(key);

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"))) {
            stream.marshal(object, new PrettyPrintWriter(writer));
            writer.close();
            logger.info("{}: ZigBee saving key complete.", key);
        } catch (Exception e) {
            logger.error("{}: Error writing key: ", key, e);
        }
    }

    @Override
    public Object readObject(String key) {
        return null;
    }

}

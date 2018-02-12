/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNetworkStateSerializer;
import com.zsmartsystems.zigbee.ZigBeeNode;
import com.zsmartsystems.zigbee.dao.ZclClusterDao;
import com.zsmartsystems.zigbee.dao.ZigBeeEndpointDao;
import com.zsmartsystems.zigbee.dao.ZigBeeNodeDao;
import com.zsmartsystems.zigbee.zcl.ZclAttribute;
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
public class ZigBeeNetworkStateSerializerImpl implements ZigBeeNetworkStateSerializer {
    /**
     * The logger.
     */
    private final static Logger logger = LoggerFactory.getLogger(ZigBeeNetworkStateSerializerImpl.class);

    /**
     * The network state file path.
     */
    private final String networkStateFilePath = "simple-network.xml";

    private XStream openStream() {
        XStream stream = new XStream(new StaxDriver());
        stream.alias("ZigBeeNode", ZigBeeNodeDao.class);
        stream.alias("ZigBeeEndpoint", ZigBeeEndpointDao.class);
        stream.alias("ZclCluster", ZclClusterDao.class);
        stream.alias("ZclAttribute", ZclAttribute.class);
        stream.alias("MacCapabilitiesType", MacCapabilitiesType.class);
        stream.alias("ServerCapabilitiesType", ServerCapabilitiesType.class);
        stream.alias("PowerSourceType", PowerSourceType.class);
        stream.alias("FrequencyBandType", FrequencyBandType.class);
        stream.alias("BindingTable", BindingTable.class);
        stream.registerLocalConverter(BindingTable.class, "srcAddr", new IeeeAddressConverter());
        stream.registerLocalConverter(BindingTable.class, "dstAddr", new IeeeAddressConverter());
        return stream;
    }

    /**
     * Serializes the network state.
     *
     * @param networkState the network state
     * @return the serialized network state as json {@link String}.
     */
    @Override
    public void serialize(final ZigBeeNetworkManager networkState) {
        XStream stream = openStream();

        final List<ZigBeeNodeDao> destinations = new ArrayList<ZigBeeNodeDao>();

        for (ZigBeeNode node : networkState.getNodes()) {
            ZigBeeNodeDao nodeDao = node.getDao();
            destinations.add(nodeDao);
        }

        final File file = new File(networkStateFilePath);

        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
            stream.marshal(destinations, new PrettyPrintWriter(writer));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            logger.error("Error writing network state", e);
        }

        logger.info("ZigBee saving network state complete.");
    }

    /**
     * Deserializes the network state.
     *
     * @param networkState the network state
     * @param networkStateString the network state as {@link String}
     */
    @Override
    public void deserialize(final ZigBeeNetworkManager networkState) {
        final File file = new File(networkStateFilePath);
        boolean networkStateExists = file.exists();
        if (networkStateExists == false) {
            return;
        }

        logger.info("Loading network state...");

        try {
            XStream stream = openStream();
            BufferedReader reader;
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            final List<Object> objects = (List<Object>) stream.fromXML(reader);
            for (final Object object : objects) {
                if (object instanceof ZigBeeNodeDao) {
                    ZigBeeNodeDao nodeDao = (ZigBeeNodeDao) object;
                    ZigBeeNode node = new ZigBeeNode(networkState, new IeeeAddress(nodeDao.getIeeeAddress()));
                    node.setDao(nodeDao);
                    networkState.addNode(node);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        logger.info("Loading network state complete.");
    }

}

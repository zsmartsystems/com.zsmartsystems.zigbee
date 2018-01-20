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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNetworkStateSerializer;
import com.zsmartsystems.zigbee.ZigBeeNode;
import com.zsmartsystems.zigbee.dao.ZigBeeEndpointDao;
import com.zsmartsystems.zigbee.dao.ZigBeeNodeDao;
import com.zsmartsystems.zigbee.zdo.field.NodeDescriptor.FrequencyBandType;
import com.zsmartsystems.zigbee.zdo.field.NodeDescriptor.MacCapabilitiesType;
import com.zsmartsystems.zigbee.zdo.field.NodeDescriptor.ServerCapabilitiesType;
import com.zsmartsystems.zigbee.zdo.field.PowerDescriptor.PowerSourceType;

/**
 * Serializes and deserializes the ZigBee network state.
 *
 * @author Tommi S.E. Laukkanen
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
        stream.alias("ZigBeeDevice", ZigBeeEndpointDao.class);
        stream.alias("MacCapabilitiesType", MacCapabilitiesType.class);
        stream.alias("ServerCapabilitiesType", ServerCapabilitiesType.class);
        stream.alias("PowerSourceType", PowerSourceType.class);
        stream.alias("FrequencyBandType", FrequencyBandType.class);
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

        final List<Object> destinations = new ArrayList<Object>();

        for (ZigBeeNode node : networkState.getNodes()) {
            ZigBeeNodeDao nodeDao = ZigBeeNodeDao.createFromZigBeeNode(node);
            destinations.add(nodeDao);
        }
        // for (ZigBeeEndpoint device : networkState.getDevices()) {
        // ZigBeeDeviceDao deviceDao = ZigBeeDeviceDao.createFromZigBeeDevice(device);
        // destinations.add(deviceDao);
        // }

        final File file = new File(networkStateFilePath);

        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
            stream.marshal(destinations, new PrettyPrintWriter(writer));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            logger.error("Error writing network state", e);
        }

        logger.info("ZigBee saving network state done.");
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
                    networkState.addNode(ZigBeeNodeDao.createFromZigBeeDao(networkState, (ZigBeeNodeDao) object));
                    // } else {
                    // networkState.addEndpoint(ZigBeeDeviceDao.createFromZigBeeDao(networkState, (ZigBeeDeviceDao)
                    // object));
                }
            }
        } catch (UnsupportedEncodingException | FileNotFoundException e) {
            e.printStackTrace();
        }

        logger.info("Loading network state done.");
    }

}

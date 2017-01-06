package com.zsmartsystems.zigbee.console;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.ZigBeeDevice;
import com.zsmartsystems.zigbee.ZigBeeGroupAddress;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNetworkStateSerializer;

/**
 * Serializes and deserializes the ZigBee network state.
 */
public class ZigBeeNetworkStateSerializerImpl implements ZigBeeNetworkStateSerializer {
    /**
     * The logger.
     */
    private final static Logger logger = LoggerFactory.getLogger(ZigBeeNetworkStateSerializerImpl.class);

    /**
     * The network state file path.
     */
    private final String networkStateFilePath = "simple-network.json";

    /**
     * Serializes the network state.
     *
     * @param networkState the network state
     * @return the serialized network state as json {@link String}.
     */
    @Override
    public void serialize(final ZigBeeNetworkManager networkState) {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enableDefaultTyping();
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        final List<Object> destinations = new ArrayList<Object>();
        destinations.addAll(networkState.getDevices());
        destinations.addAll(networkState.getGroups());

        final File networkStateFile = new File(networkStateFilePath);
        try {
            FileUtils.writeStringToFile(networkStateFile, objectMapper.writeValueAsString(destinations), false);
        } catch (final IOException e) {
            logger.error("Error loading network state from file: " + networkStateFile.getAbsolutePath());
            return;
        }
        logger.info("ZigBeeApi saving network state done.");
    }

    /**
     * Deserializes the network state.
     *
     * @param networkState the network state
     * @param networkStateString the network state as {@link String}
     */
    @Override
    public void deserialize(final ZigBeeNetworkManager networkState) {
        final File networkStateFile = new File(networkStateFilePath);
        final boolean networkStateExists = networkStateFile.exists();
        if (networkStateExists == false) {
            return;
        }

        logger.info("Loading network state...");
        final String networkStateString;
        try {
            networkStateString = FileUtils.readFileToString(networkStateFile);
        } catch (final IOException e) {
            logger.error("Error loading network state from file: " + networkStateFile.getAbsolutePath());
            return;
        }

        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enableDefaultTyping();
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        final List<Object> devices;
        try {
            devices = objectMapper.readValue(networkStateString, ArrayList.class);
        } catch (final IOException e) {
            throw new RuntimeException("Error serializing network state.", e);
        }
        for (final Object destination : devices) {
            if (destination instanceof ZigBeeGroupAddress) {
                networkState.addGroup((ZigBeeGroupAddress) destination);
            } else {
                networkState.addDevice((ZigBeeDevice) destination);
            }
        }

        logger.info("Loading network state done.");
    }

}

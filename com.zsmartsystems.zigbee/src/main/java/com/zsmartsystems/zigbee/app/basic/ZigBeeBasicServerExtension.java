/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.app.basic;

import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNetworkNodeListener;
import com.zsmartsystems.zigbee.ZigBeeNode;
import com.zsmartsystems.zigbee.ZigBeeStatus;
import com.zsmartsystems.zigbee.app.ZigBeeNetworkExtension;
import com.zsmartsystems.zigbee.zcl.ZclAttribute;
import com.zsmartsystems.zigbee.zcl.clusters.ZclBasicCluster;
import com.zsmartsystems.zigbee.zcl.clusters.basic.PowerSourceEnum;

/**
 * Extension to provide responses to basic client requests from remote devices. This provides a basic wrapper around the
 * {@link ZclBasicServer} class by implementing the standard {@link ZigBeeNetworkExtension} interface.
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeBasicServerExtension implements ZigBeeNetworkExtension, ZigBeeNetworkNodeListener {
    /**
     * The logger.
     */
    private final Logger logger = LoggerFactory.getLogger(ZigBeeBasicServerExtension.class);

    // Note that ZclVersion and PowerSource are defined as MANDATORY
    private final static int DEFAULT_ZCLVERSION = 2;
    private final static int DEFAULT_STACKVERSION = 2;
    private final static int DEFAULT_POWERSOURCE = PowerSourceEnum.UNKNOWN.getKey();

    private ZigBeeNetworkManager networkManager;

    private final Map<Integer, ZclAttribute> attributes = new TreeMap<>();

    @Override
    public ZigBeeStatus extensionInitialize(ZigBeeNetworkManager networkManager) {
        this.networkManager = networkManager;
        setAttribute(ZclBasicCluster.ATTR_STACKVERSION, DEFAULT_STACKVERSION);
        setAttribute(ZclBasicCluster.ATTR_ZCLVERSION, DEFAULT_ZCLVERSION);
        setAttribute(ZclBasicCluster.ATTR_POWERSOURCE, DEFAULT_POWERSOURCE);

        return ZigBeeStatus.SUCCESS;
    }

    @Override
    public ZigBeeStatus extensionStartup() {
        networkManager.addNetworkNodeListener(this);
        return ZigBeeStatus.SUCCESS;
    }

    @Override
    public void extensionShutdown() {
    }

    @Override
    public void nodeAdded(final ZigBeeNode node) {
        logger.debug("{}: Basic Server Extension: Updating attributes", node.getIeeeAddress());
        updateAttributes();
    }

    /**
     * Sets an attribute value in the basic server.
     *
     * @param attributeId the attribute identifier to set
     * @param attributeValue the value related to the attribute ID
     * @return true if the attribute was set
     */
    public boolean setAttribute(Integer attributeId, Object attributeValue) {
        if (attributes.get(attributeId) == null) {
            ZclBasicCluster cluster = new ZclBasicCluster(null);
            ZclAttribute attribute = cluster.getAttribute(attributeId);
            if (attribute == null) {
                return false;
            }
            attributes.put(attributeId, attribute);
        }
        attributes.get(attributeId).updateValue(attributeValue);

        for (ZigBeeNode node : networkManager.getNodes()) {
            logger.debug("Basic Server Extension: Updating attribute {} on {} {}", attributeId, node.getIeeeAddress(),
                    String.format("%04X", node.getNetworkAddress()));
            for (ZigBeeEndpoint endpoint : node.getEndpoints()) {
                ZclBasicCluster cluster = (ZclBasicCluster) endpoint.getOutputCluster(ZclBasicCluster.CLUSTER_ID);
                if (cluster != null) {
                    logger.debug("Basic Server Extension: Updating {} on {} {}, Endpoint {}", attributeId,
                            node.getIeeeAddress(), String.format("%04X", node.getNetworkAddress()),
                            endpoint.getEndpointId());
                    ZclAttribute attribute = cluster.getLocalAttribute(attributeId);
                    if (attribute == null) {
                        logger.debug("Basic Server Extension: Updating {} on {} {}, Endpoint {} UNSUPPORTED",
                                attributeId, node.getIeeeAddress(), String.format("%04X", node.getNetworkAddress()),
                                endpoint.getEndpointId());
                        continue;
                    }
                    attribute.setValue(attributeValue);

                    // Go through and mark attributes we support as implemented
                    for (ZclAttribute localAttribute : cluster.getLocalAttributes()) {
                        localAttribute.setImplemented(attributes.containsKey(localAttribute.getId()));
                    }
                }
            }
        }

        return true;
    }

    private void updateAttributes() {
        logger.debug("Basic Server Extension: Updating attributes");
        for (ZclAttribute attribute : attributes.values()) {
            setAttribute(attribute.getId(), attribute.getLastValue());
        }
    }
}

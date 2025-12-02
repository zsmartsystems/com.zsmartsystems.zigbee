/**
 * Copyright (c) 2016-2024 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.database;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.zsmartsystems.zigbee.ExtendedPanId;
import com.zsmartsystems.zigbee.ZigBeeChannel;
import com.zsmartsystems.zigbee.security.ZigBeeKey;
import com.zsmartsystems.zigbee.transport.DeviceType;

/**
 * This class provides a clean class for serialising data relating to a system backup
 *
 * @author Chris Jackson
 */
public class ZigBeeNetworkBackupDao {
    private String macAddress;
    private Long gatewayId;
    private UUID uuid;
    private Date date;
    private DeviceType deviceType;
    private Integer pan;
    private ExtendedPanId epan;
    private ZigBeeChannel channel;
    private ZigBeeKey networkKey;
    private ZigBeeKey linkKey;
    private Set<ZigBeeNodeDao> nodes = new HashSet<>();
    
    
    public Long getGatewayId() {
        return gatewayId;
    }

    public void setGatewayId(Long gatewayId) {
        this.gatewayId = gatewayId;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    /**
     * @return the uuid
     */
    public UUID getUuid() {
        return uuid;
    }

    /**
     * @param uuid the uuid to set
     */
    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the deviceType
     */
    public DeviceType getDeviceType() {
        return deviceType;
    }

    /**
     * @param deviceType the deviceType to set
     */
    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    /**
     * @return the pan
     */
    public Integer getPan() {
        return pan;
    }

    /**
     * @param pan the pan to set
     */
    public void setPan(Integer pan) {
        this.pan = pan;
    }

    /**
     * @return the epan
     */
    public ExtendedPanId getEpan() {
        return epan;
    }

    /**
     * @param epan the epan to set
     */
    public void setEpan(ExtendedPanId epan) {
        this.epan = epan;
    }

    /**
     * @return the channel
     */
    public ZigBeeChannel getChannel() {
        return channel;
    }

    /**
     * @param channel the channel to set
     */
    public void setChannel(ZigBeeChannel channel) {
        this.channel = channel;
    }

    /**
     * @return the networkKey
     */
    public ZigBeeKey getNetworkKey() {
        return networkKey;
    }

    /**
     * @param networkKey the networkKey to set
     */
    public void setNetworkKey(ZigBeeKey networkKey) {
        this.networkKey = networkKey;
    }

    /**
     * @return the linkKey
     */
    public ZigBeeKey getLinkKey() {
        return linkKey;
    }

    /**
     * @param linkKey the linkKey to set
     */
    public void setLinkKey(ZigBeeKey linkKey) {
        this.linkKey = linkKey;
    }

    /**
     * @return the nodes
     */
    public Set<ZigBeeNodeDao> getNodes() {
        return nodes;
    }

    /**
     * @param nodes the nodes to set
     */
    public void setNodes(Set<ZigBeeNodeDao> nodes) {
        this.nodes = nodes;
    }

}

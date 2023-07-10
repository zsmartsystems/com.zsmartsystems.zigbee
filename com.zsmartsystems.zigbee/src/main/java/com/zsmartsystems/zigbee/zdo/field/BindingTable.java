/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zdo.field;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.serialization.ZigBeeDeserializer;
import com.zsmartsystems.zigbee.serialization.ZigBeeSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Binding Table field.
 *
 * @author Chris Jackson
 */
public class BindingTable {
    /**
     * The source IEEE address for the binding entry.
     */
    private IeeeAddress srcAddr;

    /**
     * The source endpoint for the binding entry.
     */
    private int srcEndpoint;

    /**
     * The identifier of the cluster on the source device that is bound to the destination device.
     */
    private int clusterId;

    /**
     * Destination address mode
     * <p>
     * <ul>
     * <li>0x01 - Group address
     * <li>0x03 - IEEE address
     * </ul>
     */
    private int dstAddrMode;

    /**
     * Destination address if the address mode is group addressing
     */
    private int dstGroupAddr;

    /**
     * Destination address if the address mode is a node address
     */
    private IeeeAddress dstAddr;

    /**
     * Destination endpoint if the address mode is a node address
     */
    private int dstNodeEndpoint;

    /**
     * @return the srcAddr
     */
    public IeeeAddress getSrcAddr() {
        return srcAddr;
    }

    /**
     * @return the srcEndpoint
     */
    public int getSrcEndpoint() {
        return srcEndpoint;
    }

    /**
     * @return the clusterId
     */
    public int getClusterId() {
        return clusterId;
    }

    /**
     * @return the dstAddrMode
     */
    public int getDstAddrMode() {
        return dstAddrMode;
    }

    /**
     * @return the dstGroupAddr
     */
    public int getDstGroupAddr() {
        return dstGroupAddr;
    }

    /**
     * @return the dstNodeAddr
     */
    public IeeeAddress getDstNodeAddr() {
        return dstAddr;
    }

    /**
     * @return the dstNodeEndpoint
     */
    public int getDstNodeEndpoint() {
        return dstNodeEndpoint;
    }

    /**
     * @param srcAddr the srcAddr to set
     */
    public void setSrcAddr(IeeeAddress srcAddr) {
        this.srcAddr = srcAddr;
    }

    /**
     * @param srcEndpoint the srcEndpoint to set
     */
    public void setSrcEndpoint(int srcEndpoint) {
        this.srcEndpoint = srcEndpoint;
    }

    /**
     * @param clusterId the clusterId to set
     */
    public void setClusterId(int clusterId) {
        this.clusterId = clusterId;
    }

    /**
     * @param dstAddrMode the dstAddrMode to set
     */
    public void setDstAddrMode(int dstAddrMode) {
        this.dstAddrMode = dstAddrMode;
    }

    /**
     * @param dstGroupAddr the dstGroupAddr to set
     */
    public void setDstGroupAddr(int dstGroupAddr) {
        this.dstGroupAddr = dstGroupAddr;
    }

    /**
     * @param dstAddr the dstAddr to set
     */
    public void setDstAddr(IeeeAddress dstAddr) {
        this.dstAddr = dstAddr;
    }

    /**
     * @param dstNodeEndpoint the dstNodeEndpoint to set
     */
    public void setDstNodeEndpoint(int dstNodeEndpoint) {
        this.dstNodeEndpoint = dstNodeEndpoint;
    }

    public void serialize(final ZigBeeSerializer serializer) {
        serializer.appendZigBeeType(srcAddr, ZclDataType.IEEE_ADDRESS);
        serializer.appendZigBeeType(srcEndpoint, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.appendZigBeeType(clusterId, ZclDataType.CLUSTERID);
        serializer.appendZigBeeType(dstAddrMode, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        if (dstAddrMode == 1) {
            serializer.appendZigBeeType(dstGroupAddr, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        } else if (dstAddrMode == 3) {
            serializer.appendZigBeeType(dstAddr, ZclDataType.IEEE_ADDRESS);
            serializer.appendZigBeeType(dstNodeEndpoint, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        }
    }

    public void deserialize(final ZigBeeDeserializer deserializer) {
        srcAddr = deserializer.readZigBeeType(ZclDataType.IEEE_ADDRESS);
        srcEndpoint = deserializer.readZigBeeType(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        clusterId = deserializer.readZigBeeType(ZclDataType.CLUSTERID);
        dstAddrMode = deserializer.readZigBeeType(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        if (dstAddrMode == 1) {
            dstGroupAddr = deserializer.readZigBeeType(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        } else if (dstAddrMode == 3) {
            dstAddr = deserializer.readZigBeeType(ZclDataType.IEEE_ADDRESS);
            dstNodeEndpoint = deserializer.readZigBeeType(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + clusterId;
        result = prime * result + dstAddrMode;
        result = prime * result + dstGroupAddr;
        result = prime * result + ((dstAddr == null) ? 0 : dstAddr.hashCode());
        result = prime * result + dstNodeEndpoint;
        result = prime * result + ((srcAddr == null) ? 0 : srcAddr.hashCode());
        result = prime * result + srcEndpoint;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        BindingTable other = (BindingTable) obj;
        if (clusterId != other.clusterId) {
            return false;
        }
        if (dstAddrMode != other.dstAddrMode) {
            return false;
        }
        if (dstGroupAddr != other.dstGroupAddr) {
            return false;
        }
        if (dstAddr == null) {
            if (other.dstAddr != null) {
                return false;
            }
        } else if (!dstAddr.equals(other.dstAddr)) {
            return false;
        }
        if (dstNodeEndpoint != other.dstNodeEndpoint) {
            return false;
        }
        if (srcAddr == null) {
            if (other.srcAddr != null) {
                return false;
            }
        } else if (!srcAddr.equals(other.srcAddr)) {
            return false;
        }
        if (srcEndpoint != other.srcEndpoint) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(120);
        builder.append("BindingTable [srcAddr=");
        builder.append(srcAddr);
        builder.append('/');
        builder.append(srcEndpoint);
        builder.append(", addrMode=");
        switch (dstAddrMode) {
            case 1:
                builder.append("GROUP, dstAddr=");
                builder.append(dstGroupAddr);
                break;
            case 3:
                builder.append("DEVICE, dstAddr=");
                builder.append(dstAddr);
                builder.append('/');
                builder.append(dstNodeEndpoint);
                break;
            default:
                builder.append(", Unknown destination mode");
                break;
        }
        builder.append(String.format(", clusterId=%04X]", clusterId));
        return builder.toString();
    }
}

/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zdo.command;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zdo.ZdoResponse;
import com.zsmartsystems.zigbee.zdo.ZdoStatus;

/**
 * Network Address Response value object class.
 * <p>
 * <p>
 * The NWK_addr_rsp is generated by a Remote Device in response to a NWK_addr_req command
 * inquiring as to the NWK address of the Remote Device or the NWK address of an address held in a
 * local discovery cache. The destination addressing on this command is unicast.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-02-01T18:52:16Z")
public class NetworkAddressResponse extends ZdoResponse {
    /**
     * The ZDO cluster ID.
     */
    public static int CLUSTER_ID = 0x8000;

    /**
     * IEEE Addr Remote Dev command message field.
     */
    private IeeeAddress ieeeAddrRemoteDev;

    /**
     * NWK Addr Remote Dev command message field.
     */
    private Integer nwkAddrRemoteDev;

    /**
     * Start Index command message field.
     */
    private Integer startIndex;

    /**
     * NWK Addr Assoc Dev List command message field.
     */
    private List<Integer> nwkAddrAssocDevList;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default contructor and setters.
     */
    @Deprecated
    public NetworkAddressResponse() {
        clusterId = CLUSTER_ID;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param status {@link ZdoStatus} Status
     * @param ieeeAddrRemoteDev {@link IeeeAddress} IEEE Addr Remote Dev
     * @param nwkAddrRemoteDev {@link Integer} NWK Addr Remote Dev
     * @param startIndex {@link Integer} Start Index
     * @param nwkAddrAssocDevList {@link List<Integer>} NWK Addr Assoc Dev List
     */
    public NetworkAddressResponse(
            ZdoStatus status,
            IeeeAddress ieeeAddrRemoteDev,
            Integer nwkAddrRemoteDev,
            Integer startIndex,
            List<Integer> nwkAddrAssocDevList) {

        clusterId = CLUSTER_ID;

        this.status = status;
        this.ieeeAddrRemoteDev = ieeeAddrRemoteDev;
        this.nwkAddrRemoteDev = nwkAddrRemoteDev;
        this.startIndex = startIndex;
        this.nwkAddrAssocDevList = nwkAddrAssocDevList;
    }

    /**
     * Gets IEEE Addr Remote Dev.
     *
     * @return the IEEE Addr Remote Dev
     */
    public IeeeAddress getIeeeAddrRemoteDev() {
        return ieeeAddrRemoteDev;
    }

    /**
     * Sets IEEE Addr Remote Dev.
     *
     * @param ieeeAddrRemoteDev the IEEE Addr Remote Dev
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setIeeeAddrRemoteDev(final IeeeAddress ieeeAddrRemoteDev) {
        this.ieeeAddrRemoteDev = ieeeAddrRemoteDev;
    }

    /**
     * Gets NWK Addr Remote Dev.
     *
     * @return the NWK Addr Remote Dev
     */
    public Integer getNwkAddrRemoteDev() {
        return nwkAddrRemoteDev;
    }

    /**
     * Sets NWK Addr Remote Dev.
     *
     * @param nwkAddrRemoteDev the NWK Addr Remote Dev
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setNwkAddrRemoteDev(final Integer nwkAddrRemoteDev) {
        this.nwkAddrRemoteDev = nwkAddrRemoteDev;
    }

    /**
     * Gets Start Index.
     *
     * @return the Start Index
     */
    public Integer getStartIndex() {
        return startIndex;
    }

    /**
     * Sets Start Index.
     *
     * @param startIndex the Start Index
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setStartIndex(final Integer startIndex) {
        this.startIndex = startIndex;
    }

    /**
     * Gets NWK Addr Assoc Dev List.
     *
     * @return the NWK Addr Assoc Dev List
     */
    public List<Integer> getNwkAddrAssocDevList() {
        return nwkAddrAssocDevList;
    }

    /**
     * Sets NWK Addr Assoc Dev List.
     *
     * @param nwkAddrAssocDevList the NWK Addr Assoc Dev List
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setNwkAddrAssocDevList(final List<Integer> nwkAddrAssocDevList) {
        this.nwkAddrAssocDevList = nwkAddrAssocDevList;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        super.serialize(serializer);

        serializer.serialize(status, ZclDataType.ZDO_STATUS);
        serializer.serialize(ieeeAddrRemoteDev, ZclDataType.IEEE_ADDRESS);
        serializer.serialize(nwkAddrRemoteDev, ZclDataType.NWK_ADDRESS);
        serializer.serialize(nwkAddrAssocDevList.size(), ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(startIndex, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        for (int cnt = 0; cnt < nwkAddrAssocDevList.size(); cnt++) {
            serializer.serialize(nwkAddrAssocDevList.get(cnt), ZclDataType.NWK_ADDRESS);
        }
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        super.deserialize(deserializer);

        // Create lists
        nwkAddrAssocDevList = new ArrayList<Integer>();

        status = (ZdoStatus) deserializer.deserialize(ZclDataType.ZDO_STATUS);
        if (status != ZdoStatus.SUCCESS) {
            // Don't read the full response if we have an error
            return;
        }
        ieeeAddrRemoteDev = (IeeeAddress) deserializer.deserialize(ZclDataType.IEEE_ADDRESS);
        nwkAddrRemoteDev = (Integer) deserializer.deserialize(ZclDataType.NWK_ADDRESS);
        Integer numAssocDev = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        startIndex = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        if (numAssocDev != null) {
            for (int cnt = 0; cnt < numAssocDev; cnt++) {
                nwkAddrAssocDevList.add((Integer) deserializer.deserialize(ZclDataType.NWK_ADDRESS));
            }
        }
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(224);
        builder.append("NetworkAddressResponse [");
        builder.append(super.toString());
        builder.append(", status=");
        builder.append(status);
        builder.append(", ieeeAddrRemoteDev=");
        builder.append(ieeeAddrRemoteDev);
        builder.append(", nwkAddrRemoteDev=");
        builder.append(String.format("%04X", nwkAddrRemoteDev));
        builder.append(", startIndex=");
        builder.append(startIndex);
        builder.append(", nwkAddrAssocDevList=");
        builder.append(nwkAddrAssocDevList);
        builder.append(']');
        return builder.toString();
    }

}

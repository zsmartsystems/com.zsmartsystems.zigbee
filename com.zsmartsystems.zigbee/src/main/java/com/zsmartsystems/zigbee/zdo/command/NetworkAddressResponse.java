/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zdo.ZdoResponse;

import java.util.List;
import java.util.ArrayList;
import javax.annotation.Generated;
import com.zsmartsystems.zigbee.zdo.ZdoStatus;
import com.zsmartsystems.zigbee.IeeeAddress;

/**
 * Network Address Response value object class.
 * <p>
 * The NWK_addr_rsp is generated by a Remote Device in response to a
 * NWK_addr_req command inquiring as to the NWK address of the Remote Device
 * or the NWK address of an address held in a local discovery cache. The
 * destination addressing on this command is unicast.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */

@Generated(value = "com.zsmartsystems.zigbee.autocode.ZclProtocolCodeGenerator", date = "2018-03-12T23:36:29Z")
public class NetworkAddressResponse extends ZdoResponse {
    /**
     * IEEEAddrRemoteDev command message field.
     */
    private IeeeAddress ieeeAddrRemoteDev;

    /**
     * NWKAddrRemoteDev command message field.
     */
    private Integer nwkAddrRemoteDev;

    /**
     * StartIndex command message field.
     */
    private Integer startIndex;

    /**
     * NWKAddrAssocDevList command message field.
     */
    private List<Integer> nwkAddrAssocDevList;

    /**
     * Default constructor.
     */
    public NetworkAddressResponse() {
        clusterId = 0x8000;
    }

    /**
     * Gets IEEEAddrRemoteDev.
     *
     * @return the IEEEAddrRemoteDev
     */
    public IeeeAddress getIeeeAddrRemoteDev() {
        return ieeeAddrRemoteDev;
    }

    /**
     * Sets IEEEAddrRemoteDev.
     *
     * @param ieeeAddrRemoteDev the IEEEAddrRemoteDev
     */
    public void setIeeeAddrRemoteDev(final IeeeAddress ieeeAddrRemoteDev) {
        this.ieeeAddrRemoteDev = ieeeAddrRemoteDev;
    }

    /**
     * Gets NWKAddrRemoteDev.
     *
     * @return the NWKAddrRemoteDev
     */
    public Integer getNwkAddrRemoteDev() {
        return nwkAddrRemoteDev;
    }

    /**
     * Sets NWKAddrRemoteDev.
     *
     * @param nwkAddrRemoteDev the NWKAddrRemoteDev
     */
    public void setNwkAddrRemoteDev(final Integer nwkAddrRemoteDev) {
        this.nwkAddrRemoteDev = nwkAddrRemoteDev;
    }

    /**
     * Gets StartIndex.
     *
     * @return the StartIndex
     */
    public Integer getStartIndex() {
        return startIndex;
    }

    /**
     * Sets StartIndex.
     *
     * @param startIndex the StartIndex
     */
    public void setStartIndex(final Integer startIndex) {
        this.startIndex = startIndex;
    }

    /**
     * Gets NWKAddrAssocDevList.
     *
     * @return the NWKAddrAssocDevList
     */
    public List<Integer> getNwkAddrAssocDevList() {
        return nwkAddrAssocDevList;
    }

    /**
     * Sets NWKAddrAssocDevList.
     *
     * @param nwkAddrAssocDevList the NWKAddrAssocDevList
     */
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
        builder.append(nwkAddrRemoteDev);
        builder.append(", startIndex=");
        builder.append(startIndex);
        builder.append(", nwkAddrAssocDevList=");
        builder.append(nwkAddrAssocDevList);
        builder.append(']');
        return builder.toString();
    }

}

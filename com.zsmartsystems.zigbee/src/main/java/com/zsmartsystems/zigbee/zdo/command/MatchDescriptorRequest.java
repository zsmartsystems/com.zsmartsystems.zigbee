/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zdo.ZdoRequest;

import java.util.List;
import java.util.ArrayList;

/**
 * Match Descriptor Request value object class.
 * <p>
 * The Match_Desc_req command is generated from a local device wishing to find
 * remote devices supporting a specific simple descriptor match criterion. This
 * command shall either be broadcast to all devices for which macRxOnWhenIdle =
 * TRUE, or unicast. If the command is unicast, it shall be directed either to the
 * remote device itself or to an alternative device that contains the discovery
 * information of the remote device.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class MatchDescriptorRequest extends ZdoRequest {
    /**
     * NWKAddrOfInterest command message field.
     */
    private Integer nwkAddrOfInterest;

    /**
     * ProfileID command message field.
     */
    private Integer profileId;

    /**
     * InClusterList command message field.
     */
    private List<Integer> inClusterList;

    /**
     * OutClusterList command message field.
     */
    private List<Integer> outClusterList;

    /**
     * Default constructor.
     */
    public MatchDescriptorRequest() {
        clusterId = 0x0006;
    }

    /**
     * Gets NWKAddrOfInterest.
     *
     * @return the NWKAddrOfInterest
     */
    public Integer getNwkAddrOfInterest() {
        return nwkAddrOfInterest;
    }

    /**
     * Sets NWKAddrOfInterest.
     *
     * @param nwkAddrOfInterest the NWKAddrOfInterest
     */
    public void setNwkAddrOfInterest(final Integer nwkAddrOfInterest) {
        this.nwkAddrOfInterest = nwkAddrOfInterest;
    }

    /**
     * Gets ProfileID.
     *
     * @return the ProfileID
     */
    public Integer getProfileId() {
        return profileId;
    }

    /**
     * Sets ProfileID.
     *
     * @param profileId the ProfileID
     */
    public void setProfileId(final Integer profileId) {
        this.profileId = profileId;
    }

    /**
     * Gets InClusterList.
     *
     * @return the InClusterList
     */
    public List<Integer> getInClusterList() {
        return inClusterList;
    }

    /**
     * Sets InClusterList.
     *
     * @param inClusterList the InClusterList
     */
    public void setInClusterList(final List<Integer> inClusterList) {
        this.inClusterList = inClusterList;
    }

    /**
     * Gets OutClusterList.
     *
     * @return the OutClusterList
     */
    public List<Integer> getOutClusterList() {
        return outClusterList;
    }

    /**
     * Sets OutClusterList.
     *
     * @param outClusterList the OutClusterList
     */
    public void setOutClusterList(final List<Integer> outClusterList) {
        this.outClusterList = outClusterList;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        super.serialize(serializer);

        serializer.serialize(nwkAddrOfInterest, ZclDataType.NWK_ADDRESS);
        serializer.serialize(profileId, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(inClusterList.size(), ZclDataType.UNSIGNED_8_BIT_INTEGER);
        for (int cnt = 0; cnt < inClusterList.size(); cnt++) {
            serializer.serialize(inClusterList.get(cnt), ZclDataType.CLUSTERID);
        }
        serializer.serialize(outClusterList.size(), ZclDataType.UNSIGNED_8_BIT_INTEGER);
        for (int cnt = 0; cnt < outClusterList.size(); cnt++) {
            serializer.serialize(outClusterList.get(cnt), ZclDataType.CLUSTERID);
        }
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        super.deserialize(deserializer);

        // Create lists
        inClusterList = new ArrayList<Integer>();
        outClusterList = new ArrayList<Integer>();

        nwkAddrOfInterest = (Integer) deserializer.deserialize(ZclDataType.NWK_ADDRESS);
        profileId = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        Integer inClusterCount = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        if (inClusterCount != null) {
            for (int cnt = 0; cnt < inClusterCount; cnt++) {
                inClusterList.add((Integer) deserializer.deserialize(ZclDataType.CLUSTERID));
            }
        }
        Integer outClusterCount = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        if (outClusterCount != null) {
            for (int cnt = 0; cnt < outClusterCount; cnt++) {
                outClusterList.add((Integer) deserializer.deserialize(ZclDataType.CLUSTERID));
            }
        }
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(227);
        builder.append("MatchDescriptorRequest [");
        builder.append(super.toString());
        builder.append(", nwkAddrOfInterest=");
        builder.append(nwkAddrOfInterest);
        builder.append(", profileId=");
        builder.append(profileId);
        builder.append(", inClusterList=");
        builder.append(inClusterList);
        builder.append(", outClusterList=");
        builder.append(outClusterList);
        builder.append(']');
        return builder.toString();
    }

}

/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zdo.command;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zdo.ZdoRequest;

/**
 * Match Descriptor Request value object class.
 * <p>
 * <p>
 * The Match_Desc_req command is generated from a local device wishing to find remote devices
 * supporting a specific simple descriptor match criterion. This command shall either be
 * broadcast to all devices for which macRxOnWhenIdle = TRUE, or unicast. If the command is
 * unicast, it shall be directed either to the remote device itself or to an alternative device
 * that contains the discovery information of the remote device.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class MatchDescriptorRequest extends ZdoRequest {
    /**
     * NWK Addr Of Interest command message field.
     */
    private Integer nwkAddrOfInterest;

    /**
     * Profile ID command message field.
     */
    private Integer profileId;

    /**
     * In Cluster List command message field.
     */
    private List<Integer> inClusterList;

    /**
     * Out Cluster List command message field.
     */
    private List<Integer> outClusterList;

    /**
     * Default constructor.
     */
    public MatchDescriptorRequest() {
        clusterId = 0x0006;
    }

    /**
     * Gets NWK Addr Of Interest.
     *
     * @return the NWK Addr Of Interest
     */
    public Integer getNwkAddrOfInterest() {
        return nwkAddrOfInterest;
    }

    /**
     * Sets NWK Addr Of Interest.
     *
     * @param nwkAddrOfInterest the NWK Addr Of Interest
     */
    public void setNwkAddrOfInterest(final Integer nwkAddrOfInterest) {
        this.nwkAddrOfInterest = nwkAddrOfInterest;
    }

    /**
     * Gets Profile ID.
     *
     * @return the Profile ID
     */
    public Integer getProfileId() {
        return profileId;
    }

    /**
     * Sets Profile ID.
     *
     * @param profileId the Profile ID
     */
    public void setProfileId(final Integer profileId) {
        this.profileId = profileId;
    }

    /**
     * Gets In Cluster List.
     *
     * @return the In Cluster List
     */
    public List<Integer> getInClusterList() {
        return inClusterList;
    }

    /**
     * Sets In Cluster List.
     *
     * @param inClusterList the In Cluster List
     */
    public void setInClusterList(final List<Integer> inClusterList) {
        this.inClusterList = inClusterList;
    }

    /**
     * Gets Out Cluster List.
     *
     * @return the Out Cluster List
     */
    public List<Integer> getOutClusterList() {
        return outClusterList;
    }

    /**
     * Sets Out Cluster List.
     *
     * @param outClusterList the Out Cluster List
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

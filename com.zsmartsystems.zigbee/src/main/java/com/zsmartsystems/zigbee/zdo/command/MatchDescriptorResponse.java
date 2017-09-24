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
import com.zsmartsystems.zigbee.zdo.ZdoResponse;

import java.util.List;
import java.util.ArrayList;
import com.zsmartsystems.zigbee.zdo.ZdoStatus;

/**
 * Match Descriptor Response value object class.
 * <p>
 * The Match_Desc_rsp is generated by a remote device in response to a
 * Match_Desc_req either broadcast or directed to the remote device. This command
 * shall be unicast to the originator of the Match_Desc_req command.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class MatchDescriptorResponse extends ZdoResponse {
    /**
     * NWKAddrOfInterest command message field.
     */
    private Integer nwkAddrOfInterest;

    /**
     * MatchLength command message field.
     */
    private Integer matchLength;

    /**
     * MatchList command message field.
     */
    private List<Integer> matchList;

    /**
     * Default constructor.
     */
    public MatchDescriptorResponse() {
        clusterId = 0x8006;
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
     * Gets MatchLength.
     *
     * @return the MatchLength
     */
    public Integer getMatchLength() {
        return matchLength;
    }

    /**
     * Sets MatchLength.
     *
     * @param matchLength the MatchLength
     */
    public void setMatchLength(final Integer matchLength) {
        this.matchLength = matchLength;
    }

    /**
     * Gets MatchList.
     *
     * @return the MatchList
     */
    public List<Integer> getMatchList() {
        return matchList;
    }

    /**
     * Sets MatchList.
     *
     * @param matchList the MatchList
     */
    public void setMatchList(final List<Integer> matchList) {
        this.matchList = matchList;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        super.serialize(serializer);

        serializer.serialize(status, ZclDataType.ZDO_STATUS);
        serializer.serialize(nwkAddrOfInterest, ZclDataType.NWK_ADDRESS);
        serializer.serialize(matchLength, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        for (int cnt = 0; cnt < matchList.size(); cnt++) {
            serializer.serialize(matchList.get(cnt), ZclDataType.ENDPOINT);
        }
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        super.deserialize(deserializer);

        // Create lists
        matchList = new ArrayList<Integer>();

        status = (ZdoStatus) deserializer.deserialize(ZclDataType.ZDO_STATUS);
        if (status != ZdoStatus.SUCCESS) {
            // Don't read the full response if we have an error
            return;
        }
        nwkAddrOfInterest = (Integer) deserializer.deserialize(ZclDataType.NWK_ADDRESS);
        matchLength = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        for (int cnt = 0; cnt < matchLength; cnt++) {
            matchList.add((Integer) deserializer.deserialize(ZclDataType.ENDPOINT));
        }
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("MatchDescriptorResponse [");
        builder.append(super.toString());
        builder.append(", status=");
        builder.append(status);
        builder.append(", nwkAddrOfInterest=");
        builder.append(nwkAddrOfInterest);
        builder.append(", matchLength=");
        builder.append(matchLength);
        builder.append(", matchList=");
        builder.append(matchList);
        builder.append("]");
        return builder.toString();
    }

}

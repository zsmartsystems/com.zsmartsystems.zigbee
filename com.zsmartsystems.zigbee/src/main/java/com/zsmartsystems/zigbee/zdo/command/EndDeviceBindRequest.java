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

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zdo.ZdoRequest;

/**
 * End Device Bind Request value object class.
 * <p>
 * <p>
 * The End_Device_Bind_req is generated from a Local Device wishing to perform End Device Bind
 * with a Remote Device. The End_Device_Bind_req is generated, typically based on some user
 * action like a button press. The destination addressing on this command shall be unicast, and
 * the destination address shall be that of the ZigBee Coordinator.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class EndDeviceBindRequest extends ZdoRequest {
    /**
     * Binding Target command message field.
     */
    private Integer bindingTarget;

    /**
     * Src Address command message field.
     */
    private IeeeAddress srcAddress;

    /**
     * Src Endpoint command message field.
     */
    private Integer srcEndpoint;

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
    public EndDeviceBindRequest() {
        clusterId = 0x0020;
    }

    /**
     * Gets Binding Target.
     *
     * @return the Binding Target
     */
    public Integer getBindingTarget() {
        return bindingTarget;
    }

    /**
     * Sets Binding Target.
     *
     * @param bindingTarget the Binding Target
     */
    public void setBindingTarget(final Integer bindingTarget) {
        this.bindingTarget = bindingTarget;
    }

    /**
     * Gets Src Address.
     *
     * @return the Src Address
     */
    public IeeeAddress getSrcAddress() {
        return srcAddress;
    }

    /**
     * Sets Src Address.
     *
     * @param srcAddress the Src Address
     */
    public void setSrcAddress(final IeeeAddress srcAddress) {
        this.srcAddress = srcAddress;
    }

    /**
     * Gets Src Endpoint.
     *
     * @return the Src Endpoint
     */
    public Integer getSrcEndpoint() {
        return srcEndpoint;
    }

    /**
     * Sets Src Endpoint.
     *
     * @param srcEndpoint the Src Endpoint
     */
    public void setSrcEndpoint(final Integer srcEndpoint) {
        this.srcEndpoint = srcEndpoint;
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

        serializer.serialize(bindingTarget, ZclDataType.NWK_ADDRESS);
        serializer.serialize(srcAddress, ZclDataType.IEEE_ADDRESS);
        serializer.serialize(srcEndpoint, ZclDataType.UNSIGNED_8_BIT_INTEGER);
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

        bindingTarget = (Integer) deserializer.deserialize(ZclDataType.NWK_ADDRESS);
        srcAddress = (IeeeAddress) deserializer.deserialize(ZclDataType.IEEE_ADDRESS);
        srcEndpoint = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
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
        final StringBuilder builder = new StringBuilder(282);
        builder.append("EndDeviceBindRequest [");
        builder.append(super.toString());
        builder.append(", bindingTarget=");
        builder.append(bindingTarget);
        builder.append(", srcAddress=");
        builder.append(srcAddress);
        builder.append(", srcEndpoint=");
        builder.append(srcEndpoint);
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

/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.metering;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Start Sampling Response value object class.
 * <p>
 * Cluster: <b>Metering</b>. Command ID 0x0D is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the Metering cluster.
 * <p>
 * This command is transmitted by a Metering Device in response to a StartSampling command.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-01-12T12:33:13Z")
public class StartSamplingResponse extends ZclMeteringCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0702;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x0D;

    /**
     * Sample ID command message field.
     * <p>
     * 16 Bit Unsigned Integer indicating the ID allocated by the Metering Device for the
     * requested Sampling session. If the Metering Device is unable to support a further
     * Sampling session, Sample ID shall be returned as 0xFFFF. If valid, the Sample ID shall be
     * used for all further communication regarding this Sampling session.
     * <p>
     * NOTE that the Metering Device may reserve a Sample ID of 0x0000 in order to provide an
     * alternative mechanism for retrieving Profile data. This mechanism will allow an
     * increased number of samples to be returned than is available via the existing
     * (automatically started) Profile mechanism.
     */
    private Integer sampleId;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default contructor and setters.
     */
    @Deprecated
    public StartSamplingResponse() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param sampleId {@link Integer} Sample ID
     */
    public StartSamplingResponse(
            Integer sampleId) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;

        this.sampleId = sampleId;
    }

    /**
     * Gets Sample ID.
     * <p>
     * 16 Bit Unsigned Integer indicating the ID allocated by the Metering Device for the
     * requested Sampling session. If the Metering Device is unable to support a further
     * Sampling session, Sample ID shall be returned as 0xFFFF. If valid, the Sample ID shall be
     * used for all further communication regarding this Sampling session.
     * <p>
     * NOTE that the Metering Device may reserve a Sample ID of 0x0000 in order to provide an
     * alternative mechanism for retrieving Profile data. This mechanism will allow an
     * increased number of samples to be returned than is available via the existing
     * (automatically started) Profile mechanism.
     *
     * @return the Sample ID
     */
    public Integer getSampleId() {
        return sampleId;
    }

    /**
     * Sets Sample ID.
     * <p>
     * 16 Bit Unsigned Integer indicating the ID allocated by the Metering Device for the
     * requested Sampling session. If the Metering Device is unable to support a further
     * Sampling session, Sample ID shall be returned as 0xFFFF. If valid, the Sample ID shall be
     * used for all further communication regarding this Sampling session.
     * <p>
     * NOTE that the Metering Device may reserve a Sample ID of 0x0000 in order to provide an
     * alternative mechanism for retrieving Profile data. This mechanism will allow an
     * increased number of samples to be returned than is available via the existing
     * (automatically started) Profile mechanism.
     *
     * @param sampleId the Sample ID
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setSampleId(final Integer sampleId) {
        this.sampleId = sampleId;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(sampleId, ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        sampleId = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(52);
        builder.append("StartSamplingResponse [");
        builder.append(super.toString());
        builder.append(", sampleId=");
        builder.append(sampleId);
        builder.append(']');
        return builder.toString();
    }

}

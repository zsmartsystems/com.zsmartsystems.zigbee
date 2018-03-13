/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command;

import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.EzspFrameRequest;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.serializer.EzspSerializer;

/**
 * Class to implement the Ember EZSP command <b>addEndpoint</b>.
 * <p>
 * Configures endpoint information on the NCP. The NCP does not remember these settings after a
 * reset. Endpoints can be added by the Host after the NCP has reset. Once the status of the stack
 * changes to EMBER_NETWORK_UP, endpoints can no longer be added and this command will respond
 * with EZSP_ERROR_INVALID_CALL.
 * <p>
 * This class provides methods for processing EZSP commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class EzspAddEndpointRequest extends EzspFrameRequest {
    public static int FRAME_ID = 0x02;

    /**
     * The application endpoint to be added.
     * <p>
     * EZSP type is <i>uint8_t</i> - Java type is {@link int}
     */
    private int endpoint;

    /**
     * The endpoint's application profile.
     * <p>
     * EZSP type is <i>uint16_t</i> - Java type is {@link int}
     */
    private int profileId;

    /**
     * The endpoint's device ID within the application profile.
     * <p>
     * EZSP type is <i>uint16_t</i> - Java type is {@link int}
     */
    private int deviceId;

    /**
     * The device version and flags indicating description availability.
     * <p>
     * EZSP type is <i>uint8_t</i> - Java type is {@link int}
     */
    private int appFlags;

    /**
     * Input cluster IDs the endpoint will accept.
     * <p>
     * EZSP type is <i>uint16_t[]</i> - Java type is {@link int[]}
     */
    private int[] inputClusterList;

    /**
     * Output cluster IDs the endpoint may send.
     * <p>
     * EZSP type is <i>uint16_t[]</i> - Java type is {@link int[]}
     */
    private int[] outputClusterList;

    /**
     * Serialiser used to seialise to binary line data
     */
    private EzspSerializer serializer;

    /**
     * Request constructor
     */
    public EzspAddEndpointRequest() {
        frameId = FRAME_ID;
        serializer = new EzspSerializer();
    }

    /**
     * The application endpoint to be added.
     * <p>
     * EZSP type is <i>uint8_t</i> - Java type is {@link int}
     *
     * @return the current endpoint as {@link int}
     */
    public int getEndpoint() {
        return endpoint;
    }

    /**
     * The application endpoint to be added.
     *
     * @param endpoint the endpoint to set as {@link int}
     */
    public void setEndpoint(int endpoint) {
        this.endpoint = endpoint;
    }

    /**
     * The endpoint's application profile.
     * <p>
     * EZSP type is <i>uint16_t</i> - Java type is {@link int}
     *
     * @return the current profileId as {@link int}
     */
    public int getProfileId() {
        return profileId;
    }

    /**
     * The endpoint's application profile.
     *
     * @param profileId the profileId to set as {@link int}
     */
    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    /**
     * The endpoint's device ID within the application profile.
     * <p>
     * EZSP type is <i>uint16_t</i> - Java type is {@link int}
     *
     * @return the current deviceId as {@link int}
     */
    public int getDeviceId() {
        return deviceId;
    }

    /**
     * The endpoint's device ID within the application profile.
     *
     * @param deviceId the deviceId to set as {@link int}
     */
    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * The device version and flags indicating description availability.
     * <p>
     * EZSP type is <i>uint8_t</i> - Java type is {@link int}
     *
     * @return the current appFlags as {@link int}
     */
    public int getAppFlags() {
        return appFlags;
    }

    /**
     * The device version and flags indicating description availability.
     *
     * @param appFlags the appFlags to set as {@link int}
     */
    public void setAppFlags(int appFlags) {
        this.appFlags = appFlags;
    }

    /**
     * Input cluster IDs the endpoint will accept.
     * <p>
     * EZSP type is <i>uint16_t[]</i> - Java type is {@link int[]}
     *
     * @return the current inputClusterList as {@link int[]}
     */
    public int[] getInputClusterList() {
        return inputClusterList;
    }

    /**
     * Input cluster IDs the endpoint will accept.
     *
     * @param inputClusterList the inputClusterList to set as {@link int[]}
     */
    public void setInputClusterList(int[] inputClusterList) {
        this.inputClusterList = inputClusterList;
    }

    /**
     * Output cluster IDs the endpoint may send.
     * <p>
     * EZSP type is <i>uint16_t[]</i> - Java type is {@link int[]}
     *
     * @return the current outputClusterList as {@link int[]}
     */
    public int[] getOutputClusterList() {
        return outputClusterList;
    }

    /**
     * Output cluster IDs the endpoint may send.
     *
     * @param outputClusterList the outputClusterList to set as {@link int[]}
     */
    public void setOutputClusterList(int[] outputClusterList) {
        this.outputClusterList = outputClusterList;
    }

    @Override
    public int[] serialize() {
        // Serialize the header
        serializeHeader(serializer);

        // Serialize the fields
        serializer.serializeUInt8(endpoint);
        serializer.serializeUInt16(profileId);
        serializer.serializeUInt16(deviceId);
        serializer.serializeUInt8(appFlags);
        serializer.serializeUInt8(inputClusterList.length);
        serializer.serializeUInt8(outputClusterList.length);
        serializer.serializeUInt16Array(inputClusterList);
        serializer.serializeUInt16Array(outputClusterList);
        return serializer.getPayload();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(225);
        builder.append("EzspAddEndpointRequest [endpoint=");
        builder.append(endpoint);
        builder.append(", profileId=");
        builder.append(profileId);
        builder.append(", deviceId=");
        builder.append(deviceId);
        builder.append(", appFlags=");
        builder.append(appFlags);
        builder.append(", inputClusterList=");
        for (int c = 0; c < inputClusterList.length; c++) {
            if (c > 0) {
                builder.append(' ');
            }
            builder.append(String.format("%02X", inputClusterList[c]));
        }
        builder.append(", outputClusterList=");
        for (int c = 0; c < outputClusterList.length; c++) {
            if (c > 0) {
                builder.append(' ');
            }
            builder.append(String.format("%02X", outputClusterList[c]));
        }
        builder.append(']');
        return builder.toString();
    }
}

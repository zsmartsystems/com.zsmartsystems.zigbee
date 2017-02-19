/**
 * Copyright (c) 2014-2017 by the respective copyright holders.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp.command;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.serializer.EzspDeserializer;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspStatus;

/**
 * Class to implement the Ember EZSP command <b>getXncpInfo</b>.
 * <p>
 * Allows the HOST to know whether the NCP is running the XNCP library. If so, the response
 * contains also the manufacturer ID and the version number of the XNCP application that is
 * running on the NCP.
 * <p>
 * This class provides methods for processing EZSP commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class EzspGetXncpInfoResponse extends EzspFrameResponse {
    public static int FRAME_ID = 0x13;

    /**
     * EMBER_SUCCESS if the NCP is running the XNCP library. EMBER_INVALID_CALL otherwise.
     * <p>
     * EZSP type is <i>EzspStatus</i> - Java type is {@link EzspStatus}
     */
    private EzspStatus status;

    /**
     * The manufactured ID the user has defined in the XNCP application.
     * <p>
     * EZSP type is <i>uint16_t</i> - Java type is {@link int}
     */
    private int manufacturerId;

    /**
     * The version number of the XNCP application.
     * <p>
     * EZSP type is <i>uint16_t</i> - Java type is {@link int}
     */
    private int versionNumber;

    /**
     * Response and Handler constructor
     */
    public EzspGetXncpInfoResponse(int[] inputBuffer) {
        // Super creates deserializer and reads header fields
        super(inputBuffer);

        // Deserialize the fields
        status = deserializer.deserializeEzspStatus();
        manufacturerId = deserializer.deserializeUInt16();
        versionNumber = deserializer.deserializeUInt16();
    }

    /**
     * EMBER_SUCCESS if the NCP is running the XNCP library. EMBER_INVALID_CALL otherwise.
     * <p>
     * EZSP type is <i>EzspStatus</i> - Java type is {@link EzspStatus}
     *
     * @return the current status as {@link EzspStatus}
     */
    public EzspStatus getStatus() {
        return status;
    }

    /**
     * EMBER_SUCCESS if the NCP is running the XNCP library. EMBER_INVALID_CALL otherwise.
     *
     * @param status the status to set as {@link EzspStatus}
     */
    public void setStatus(EzspStatus status) {
        this.status = status;
    }

    /**
     * The manufactured ID the user has defined in the XNCP application.
     * <p>
     * EZSP type is <i>uint16_t</i> - Java type is {@link int}
     *
     * @return the current manufacturerId as {@link int}
     */
    public int getManufacturerId() {
        return manufacturerId;
    }

    /**
     * The manufactured ID the user has defined in the XNCP application.
     *
     * @param manufacturerId the manufacturerId to set as {@link int}
     */
    public void setManufacturerId(int manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    /**
     * The version number of the XNCP application.
     * <p>
     * EZSP type is <i>uint16_t</i> - Java type is {@link int}
     *
     * @return the current versionNumber as {@link int}
     */
    public int getVersionNumber() {
        return versionNumber;
    }

    /**
     * The version number of the XNCP application.
     *
     * @param versionNumber the versionNumber to set as {@link int}
     */
    public void setVersionNumber(int versionNumber) {
        this.versionNumber = versionNumber;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("EzspGetXncpInfoResponse [status=");
        builder.append(status);
        builder.append(", manufacturerId=");
        builder.append(manufacturerId);
        builder.append(", versionNumber=");
        builder.append(versionNumber);
        builder.append("]");
        return builder.toString();
    }
}

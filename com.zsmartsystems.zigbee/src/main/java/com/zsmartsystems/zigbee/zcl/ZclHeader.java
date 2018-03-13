/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl;

import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Defines the ZigBee Cluster Library (ZCL) Header fields-:
 * <ul>
 * <li>Frame Control (1 byte)
 * <li>Manufacturer Code (2 bytes) [optional]
 * <li>Transaction Sequence Number (1 byte)
 * <li>Command ID (1 byte)
 * </ul>
 * <p>
 * The <b>frame type</b> sub-field specifies if this is a <i>generic</i> command used across the entire profile
 * {@link #ENTIRE_PROFILE_COMMAND}, or a command that is specific to a single cluster {@link #CLUSTER_SPECIFIC_COMMAND}.
 * <p>
 * The <b>manufacturer specific</b> sub-field is 1 bit in length and specifies whether this command refers to a
 * manufacturer specific extension to a profile. If this value is set to 1, the manufacturer code field shall be
 * present in the ZCL frame. If this value is set to 0, the manufacturer code field shall not be included in the ZCL
 * frame.
 * <p>
 * The <b>direction</b> sub-field specifies the client/server direction for this command. If this value is set to 1, the
 * command is being sent from the server side of a cluster to the client side of a cluster. If this value is set to
 * 0, the command is being sent from the client side of a cluster to the server side of a cluster.
 * <p>
 * The <b>disable default response</b> sub-field is 1 bit in length. If it is set to 0, the Default response command
 * will be returned. If it is set to 1, the Default response command will only be returned if there is an error.
 * <p>
 * The <b>command identifier</b> field is 8 bits in length and specifies the cluster command being used. If the frame
 * type sub-field of the frame control field is set to 0b00, the command identifier corresponds to one of the
 * non-reserved values of Table 2.9. If the frame type sub-field of the frame control field is set to 0b01, the
 * command identifier corresponds to a cluster specific command. The cluster specific command identifiers can be
 * found in each individual document describing the clusters.
 * <p>
 * The <b>transaction sequence number</b> field is 8 bits in length and specifies an identification number for the
 * transaction so that a response-style command frame can be related to a request-style command frame. The
 * application object itself shall maintain an 8-bit counter that is copied into this field and incremented by one
 * for each command sent. When a value of 0xff is reached, the next command shall re-start the counter with a value
 * of 0x00.
 * The <b>transaction sequence number</b> field can be used by a controlling device, which may have issued multiple
 * commands, so that it can match the incoming responses to the relevant command.
 *
 * @author Chris Jackson
 *
 */
public class ZclHeader {
    private final int MASK_FRAME_TYPE = 0b00000011;
    private final int MASK_MANUFACTURER_SPECIFIC = 0b00000100;
    private final int MASK_DIRECTION = 0b00001000;
    private final int MASK_DEFAULT_RESPONSE = 0b00010000;

    private final int FRAME_TYPE_ENTIRE_PROFILE = 0x00;
    private final int FRAME_TYPE_CLUSTER_SPECIFIC = 0x01;

    /**
     * The frame type sub-field.
     * <p>
     * Specifies if this is a <i>generic</i> command used across the entire profile {@link #ENTIRE_PROFILE_COMMAND},
     * or a command that is specific to a single cluster {@link #CLUSTER_SPECIFIC_COMMAND}.
     */
    private ZclFrameType frameType;

    /**
     * The manufacturer specific sub-field is 1 bit in length and specifies whether this command refers to a
     * manufacturer specific extension to a profile. If this value is set to 1, the manufacturer code field shall be
     * present in the ZCL frame. If this value is set to 0, the manufacturer code field shall not be included in the ZCL
     * frame.
     */
    private boolean manufacturerSpecific;

    /**
     * The direction sub-field specifies the client/server direction for this command. If this value is set to 1, the
     * command is being sent from the server side of a cluster to the client side of a cluster. If this value is set to
     * 0, the command is being sent from the client side of a cluster to the server side of a cluster.
     */
    private ZclCommandDirection direction;

    /**
     * The disable default response sub-field is 1 bit in length. If it is set to 0, the Default response command will
     * be returned. If it is set to 1, the Default response command will only be returned if there is an error.
     */
    private boolean disableDefaultResponse;

    /**
     * The manufacturer code field is 16 bits in length and specifies the ZigBee assigned manufacturer code for
     * proprietary extensions to a profile. This field shall only be included in the ZCL frame if the manufacturer
     * specific sub-field of the frame control field is set to 1.
     */
    private int manufacturerCode;

    /**
     * The transaction sequence number field is 8 bits in length and specifies an identification number for the
     * transaction so that a response-style command frame can be related to a request-style command frame. The
     * application object itself shall maintain an 8-bit counter that is copied into this field and incremented by one
     * for each command sent. When a value of 0xff is reached, the next command shall re-start the counter with a value
     * of 0x00.
     * </p>
     * The transaction sequence number field can be used by a controlling device, which may have issued multiple
     * commands, so that it can match the incoming responses to the relevant command.
     */
    private int sequenceNumber;

    /**
     * The command identifier field is 8 bits in length and specifies the cluster command being used. If the frame type
     * sub-field of the frame control field is set to 0b00, the command identifier corresponds to one of the
     * non-reserved values of Table 2.9. If the frame type sub-field of the frame control field is set to 0b01, the
     * command identifier corresponds to a cluster specific command. The cluster specific command identifiers can be
     * found in each individual document describing the clusters.
     */
    private int commandId;

    /**
     * Default constructor. Sets appropriate defaults for configuration that is not normally used.
     */
    public ZclHeader() {
        manufacturerSpecific = false;
        manufacturerCode = 0;
        disableDefaultResponse = false;
    }

    /**
     * Constructor taking a {@link ZclFieldDeserializer} to deserialize the received header.
     *
     * @param fieldDeserializer {@link ZclFieldDeserializer} for deserialization of the header from the received
     *            payload.
     */
    public ZclHeader(ZclFieldDeserializer fieldDeserializer) {
        int frameControl = (int) fieldDeserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);

        switch (frameControl & MASK_FRAME_TYPE) {
            case FRAME_TYPE_CLUSTER_SPECIFIC:
                frameType = ZclFrameType.CLUSTER_SPECIFIC_COMMAND;
                break;
            case FRAME_TYPE_ENTIRE_PROFILE:
                frameType = ZclFrameType.ENTIRE_PROFILE_COMMAND;
                break;
            default:
                break;
        }

        disableDefaultResponse = (frameControl & MASK_DEFAULT_RESPONSE) != 0;
        direction = (frameControl & MASK_DIRECTION) == 0 ? ZclCommandDirection.CLIENT_TO_SERVER
                : ZclCommandDirection.SERVER_TO_CLIENT;
        manufacturerSpecific = (frameControl & MASK_MANUFACTURER_SPECIFIC) != 0;

        // If manufacturerSpecific is set then get the manufacturer code
        if (manufacturerSpecific) {
            manufacturerCode = (int) fieldDeserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        }
        sequenceNumber = (int) fieldDeserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        commandId = (int) fieldDeserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    /**
     * <p>
     * Gets the frame type sub-field.
     * </p>
     * <p>
     * Specifies if this is a <i>generic</i> command used across the entire profile {@link #ENTIRE_PROFILE_COMMAND},
     * or a command that is specific to a single cluster {@link #CLUSTER_SPECIFIC_COMMAND}.
     * </p>
     *
     * @return the {@link ZclFrameType} for this ZCL frame
     */
    public ZclFrameType getFrameType() {
        return frameType;
    }

    /**
     * <p>
     * Sets the frame type sub-field.
     * </p>
     * <p>
     * Specifies if this is a <i>generic</i> command used across the entire profile {@link #ENTIRE_PROFILE_COMMAND},
     * or a command that is specific to a single cluster {@link #CLUSTER_SPECIFIC_COMMAND}.
     * </p>
     *
     * @param frameType the {@link ZclFrameType} for this ZCL frame
     */
    public void setFrameType(ZclFrameType frameType) {
        this.frameType = frameType;
    }

    /**
     * The manufacturer specific sub-field is 1 bit in length and specifies whether this command refers to a
     * manufacturer specific extension to a profile. If this value is set to 1, the manufacturer code field shall be
     * present in the ZCL frame. If this value is set to 0, the manufacturer code field shall not be included in the ZCL
     * frame.
     *
     * @return true if the command uses the manufacturer specific extension
     */
    public boolean isManufacturerSpecific() {
        return manufacturerSpecific;
    }

    /**
     * The manufacturer specific sub-field is 1 bit in length and specifies whether this command refers to a
     * manufacturer specific extension to a profile. If this value is set to 1, the manufacturer code field shall be
     * present in the ZCL frame. If this value is set to 0, the manufacturer code field shall not be included in the ZCL
     * frame.
     *
     * @param manufacturerSpecific true if the command uses the manufacturer specific extension
     */
    public void setManufacturerSpecific(boolean manufacturerSpecific) {
        this.manufacturerSpecific = manufacturerSpecific;
    }

    /**
     * The direction sub-field specifies the client/server direction for this command. If this value is set to 1, the
     * command is being sent from the server side of a cluster to the client side of a cluster. If this value is set to
     * 0, the command is being sent from the client side of a cluster to the server side of a cluster.
     *
     * @return the {@link ZclCommandDirection}
     */
    public ZclCommandDirection getDirection() {
        return direction;
    }

    /**
     * The direction sub-field specifies the client/server direction for this command. If this value is set to 1, the
     * command is being sent from the server side of a cluster to the client side of a cluster. If this value is set to
     * 0, the command is being sent from the client side of a cluster to the server side of a cluster.
     *
     * @param zclCommandDirection true if the command is sent from the server
     */
    public void setDirection(ZclCommandDirection zclCommandDirection) {
        this.direction = zclCommandDirection;
    }

    /**
     * The disable default response sub-field is 1 bit in length. If it is set to 0, the Default response command will
     * be returned. If it is set to 1, the Default response command will only be returned if there is an error.
     *
     * @return true if the default response is disabled
     */
    public boolean isDisableDefaultResponse() {
        return disableDefaultResponse;
    }

    /**
     * The disable default response sub-field is 1 bit in length. If it is set to 0, the Default response command will
     * be returned. If it is set to 1, the Default response command will only be returned if there is an error.
     *
     * @param disableDefaultResponse true if the default response is disabled
     */
    public void setDisableDefaultResponse(boolean disableDefaultResponse) {
        this.disableDefaultResponse = disableDefaultResponse;
    }

    /**
     * The manufacturer code field is 16 bits in length and specifies the ZigBee assigned manufacturer code for
     * proprietary extensions to a profile. This field shall only be included in the ZCL frame if the manufacturer
     * specific sub-field of the frame control field is set to 1.
     * <br>
     * Note that this is only valid if {@link #isManufacturerSpecific} returns true
     *
     * @return the manufacturer code
     */
    public int getManufacturerCode() {
        return manufacturerCode;
    }

    public void setManufacturerCode(int manufacturerCode) {
        this.manufacturerCode = manufacturerCode;
    }

    /**
     * Gets the sequence number.
     * <br>
     * The transaction sequence number field is 8 bits in length and specifies an identification number for the
     * transaction so that a response-style command frame can be related to a request-style command frame. The
     * application object itself shall maintain an 8-bit counter that is copied into this field and incremented by one
     * for each command sent. When a value of 0xff is reached, the next command shall re-start the counter with a value
     * of 0x00.
     * <br>
     * The transaction sequence number field can be used by a controlling device, which may have issued multiple
     * commands, so that it can match the incoming responses to the relevant command.
     *
     * @return the sequence number as {@link int}
     */
    public int getSequenceNumber() {
        return sequenceNumber;
    }

    /**
     * Sets the sequence number.
     * <br>
     * The transaction sequence number field is 8 bits in length and specifies an identification number for the
     * transaction so that a response-style command frame can be related to a request-style command frame. The
     * application object itself shall maintain an 8-bit counter that is copied into this field and incremented by one
     * for each command sent. When a value of 0xff is reached, the next command shall re-start the counter with a value
     * of 0x00.
     * <br>
     * The transaction sequence number field can be used by a controlling device, which may have issued multiple
     * commands, so that it can match the incoming responses to the relevant command.
     *
     * param sequenceNumber the sequence number as {@link int}
     */
    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public int getCommandId() {
        return commandId;
    }

    public void setCommandId(int commandId) {
        this.commandId = commandId;
    }

    /**
     * Serializes the ZCL header and adds the payload, thus producing the final ZCL packet
     *
     * @param fieldSerializer the {@link ZclFieldSerializer}
     * @param payload the ZCL Payload as {@link int[]}
     * @return the ZCL frame as {@link int[]}
     */
    public int[] serialize(ZclFieldSerializer fieldSerializer, int[] payload) {
        int frameControl = 0;
        switch (frameType) {
            case CLUSTER_SPECIFIC_COMMAND:
                frameControl |= FRAME_TYPE_CLUSTER_SPECIFIC;
                break;
            case ENTIRE_PROFILE_COMMAND:
                frameControl |= FRAME_TYPE_ENTIRE_PROFILE;
                break;
            default:
                break;
        }

        frameControl |= direction == ZclCommandDirection.SERVER_TO_CLIENT ? MASK_DIRECTION : 0b00000000;
        frameControl |= disableDefaultResponse ? MASK_DEFAULT_RESPONSE : 0b00000000;

        int[] zclFrame = new int[payload.length + 3];
        zclFrame[0] = frameControl;
        zclFrame[1] = sequenceNumber;
        zclFrame[2] = commandId;

        for (int cnt = 0; cnt < payload.length; cnt++) {
            zclFrame[cnt + 3] = payload[cnt];
        }
        return zclFrame;
    }

    @Override
    public String toString() {
        return "ZclHeader [frameType=" + frameType + ", manufacturerSpecific=" + manufacturerSpecific + ", direction="
                + direction + ", disableDefaultResponse=" + disableDefaultResponse + ", manufacturerCode="
                + manufacturerCode + ", sequenceNumber=" + sequenceNumber + ", commandId=" + commandId + "]";
    }
}

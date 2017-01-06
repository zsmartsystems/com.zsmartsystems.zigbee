package com.zsmartsystems.zigbee.dongle.ember.ezsp;

import java.util.Arrays;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberApsFrame;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberOutgoingMessageType;

/**
 * Sends a unicast message as per the ZigBee specification. The message will arrive at its destination only if there is
 * a known route to the destination node. Setting the ENABLE_ROUTE_DISCOVERY option will cause a route to be discovered
 * if none is known. Setting the FORCE_ROUTE_DISCOVERY option will force route discovery. Routes to end-device children
 * of the local node are always known. Setting the APS_RETRY option will cause the message to be retransmitted until
 * either a matching acknowledgement is received or three transmissions have been made.
 * <br>
 * <b>Note</b>: Using the FORCE_ROUTE_DISCOVERY option will cause the first transmission to be consumed by a route
 * request as part of discovery, so the application payload of this packet will not reach its destination on the first
 * attempt. If you want the packet to reach its destination, the APS_RETRY option must be set so that another attempt is
 * made to transmit the message with its application payload after the route has been constructed.
 * <br>
 * <b>Note</b>: When sending fragmented messages, the stack will only assign a new APS sequence number for the first
 * fragment of the message (i.e., EMBER_APS_OPTION_FRAGMENT is set and the low-order byte of the groupId field in the
 * APS frame is zero). For all subsequent fragments of the same message, the application must set the sequence number
 * field in the APS frame to the sequence number assigned by the stack to the first fragment.
 *
 * @author Chris Jackson
 *
 */
public class EzspSendUnicast extends EzspFrameRequest {
    /**
     * Specifies the outgoing message type. Must be one of EMBER_OUTGOING_DIRECT, EMBER_OUTGOING_VIA_ADDRESS_TABLE, or
     * EMBER_OUTGOING_VIA_BINDING.
     */
    private EmberOutgoingMessageType type;

    /**
     * Depending on the type of addressing used, this is either the EmberNodeId of the destination, an index into the
     * address table, or an index into the binding table.
     */
    private int indexOrDestination;

    /**
     * The APS frame which is to be added to the message.
     */
    private EmberApsFrame apsFrame;

    /**
     * A value chosen by the Host. This value is used in the ezspMessageSentHandler response to refer to this message.
     */
    private int messageTag;

    /**
     * Content of the message.
     */
    private int[] messageContents;

    /**
     * Creates an EZSP <i>version</i> request frame
     *
     */
    public EzspSendUnicast() {
        super(FRAME_ID_SEND_UNICAST);
    }

    public EmberOutgoingMessageType getType() {
        return type;
    }

    public void setType(EmberOutgoingMessageType type) {
        this.type = type;
    }

    public int getIndexOrDestination() {
        return indexOrDestination;
    }

    public void setIndexOrDestination(int indexOrDestination) {
        this.indexOrDestination = indexOrDestination;
    }

    public EmberApsFrame getApsFrame() {
        return apsFrame;
    }

    public void setApsFrame(EmberApsFrame apsFrame) {
        this.apsFrame = apsFrame;
    }

    public int getMessageTag() {
        return messageTag;
    }

    public void setMessageTag(int messageTag) {
        this.messageTag = messageTag;
    }

    public int[] getMessageContents() {
        return messageContents;
    }

    public void setMessageContents(int[] messageContents) {
        this.messageContents = messageContents;
    }

    @Override
    public int[] getOutputBuffer() {
        initialiseOutputBuffer();
        outputUInt8(type.getKey());
        outputUInt16(indexOrDestination);
        outputStructure(apsFrame);
        outputUInt8(messageTag);
        outputUInt8(messageContents.length);
        outputUInt8Array(messageContents);

        return Arrays.copyOfRange(buffer, 0, length);
    }

    @Override
    public boolean processResponse(EzspFrameResponse ezspResponse) {
        if (getSequenceNumber() != ezspResponse.getSequenceNumber()) {
            return false;
        }
        if (getFrameId() != ezspResponse.getFrameId()) {
            return false;
        }

        response = ezspResponse;
        emberStatus = ezspResponse.getEmberStatus();

        return true;
    }

    @Override
    public String toString() {
        return "EzspSendUnicast [type=" + type + ", indexOrDestination=" + indexOrDestination + ", apsFrame=" + apsFrame
                + ", messageTag=" + messageTag + ", messageContents=" + Arrays.toString(messageContents) + "]";
    }
}

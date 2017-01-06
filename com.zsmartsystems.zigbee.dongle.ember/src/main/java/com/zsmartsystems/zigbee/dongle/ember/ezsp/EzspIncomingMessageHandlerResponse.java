package com.zsmartsystems.zigbee.dongle.ember.ezsp;

import java.util.Arrays;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberApsFrame;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberIncomingMessageType;

/**
 * A callback indicating a message has been received.
 *
 * @author Chris Jackson
 *
 */
public class EzspIncomingMessageHandlerResponse extends EzspFrameResponse {
    /**
     * The type of the incoming message. One of the following: EMBER_INCOMING_UNICAST, EMBER_INCOMING_UNICAST_REPLY,
     * EMBER_INCOMING_MULTICAST, EMBER_INCOMING_MULTICAST_LOOPBACK, EMBER_INCOMING_BROADCAST,
     * EMBER_INCOMING_BROADCAST_LOOPBACK
     */
    private EmberIncomingMessageType type;

    /**
     * The APS frame from the incoming message.
     */
    private EmberApsFrame apsFrame;

    /**
     * The link quality from the node that last relayed the message.
     */
    private int lastHopLqi;

    /**
     * The energy level (in units of dBm) observed during the reception.
     */
    private int lastHopRssi;

    /**
     * The sender of the message.
     */
    private int sender;

    /**
     * The index of a binding that matches the message or 0xFF if there is no matching binding.
     */
    private int bindingIndex;

    /**
     * The index of the entry in the address table that matches the sender of the message or 0xFF if there is no
     * matching entry.
     */
    private int addressIndex;

    /**
     * The incoming message
     */
    private int[] messageContents;

    /**
     * Creates an EZSP <i>stackStatusHandler</i> from a response frame
     *
     * @param inputBuffer
     *            the received response data
     */
    public EzspIncomingMessageHandlerResponse(int[] inputBuffer) {
        super(inputBuffer);

        emberStatus = inputEmberStatus();
    }

    public EmberIncomingMessageType getType() {
        return type;
    }

    public void setType(EmberIncomingMessageType type) {
        this.type = type;
    }

    public EmberApsFrame getApsFrame() {
        return apsFrame;
    }

    public void setApsFrame(EmberApsFrame apsFrame) {
        this.apsFrame = apsFrame;
    }

    public int getLastHopLqi() {
        return lastHopLqi;
    }

    public void setLastHopLqi(int lastHopLqi) {
        this.lastHopLqi = lastHopLqi;
    }

    public int getLastHopRssi() {
        return lastHopRssi;
    }

    public void setLastHopRssi(int lastHopRssi) {
        this.lastHopRssi = lastHopRssi;
    }

    public int getSender() {
        return sender;
    }

    public void setSender(int sender) {
        this.sender = sender;
    }

    public int getBindingIndex() {
        return bindingIndex;
    }

    public void setBindingIndex(int bindingIndex) {
        this.bindingIndex = bindingIndex;
    }

    public int getAddressIndex() {
        return addressIndex;
    }

    public void setAddressIndex(int addressIndex) {
        this.addressIndex = addressIndex;
    }

    public int[] getMessageContents() {
        return messageContents;
    }

    public void setMessageContents(int[] messageContents) {
        this.messageContents = messageContents;
    }

    @Override
    public String toString() {
        return "EzspIncomingMessageHandlerResponse [type=" + type + ", apsFrame=" + apsFrame + ", lastHopLqi="
                + lastHopLqi + ", lastHopRssi=" + lastHopRssi + ", sender=" + sender + ", bindingIndex=" + bindingIndex
                + ", addressIndex=" + addressIndex + ", messageContents=" + Arrays.toString(messageContents) + "]";
    }
}

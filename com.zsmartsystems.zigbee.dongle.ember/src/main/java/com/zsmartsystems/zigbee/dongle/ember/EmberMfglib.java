/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.ZigBeeChannel;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspMfglibEndRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspMfglibEndResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspMfglibGetChannelRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspMfglibGetChannelResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspMfglibGetPowerRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspMfglibGetPowerResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspMfglibSendPacketRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspMfglibSendPacketResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspMfglibSetChannelRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspMfglibSetChannelResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspMfglibSetPowerRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspMfglibSetPowerResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspMfglibStartRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspMfglibStartResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspMfglibStartStreamRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspMfglibStartStreamResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspMfglibStartToneRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspMfglibStartToneResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspMfglibStopStreamRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspMfglibStopStreamResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspMfglibStopToneRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspMfglibStopToneResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberPowerMode;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberStatus;
import com.zsmartsystems.zigbee.dongle.ember.internal.EzspProtocolHandler;
import com.zsmartsystems.zigbee.dongle.ember.internal.transaction.EzspSingleResponseTransaction;

/**
 * This class provides access to the Ember NCP Manufacturing Library. This interface is intended to be used for testing
 * of final products.
 *
 * @author Chris Jackson
 *
 */
public class EmberMfglib {
    /**
     * The {@link Logger}.
     */
    private final Logger logger = LoggerFactory.getLogger(EmberMfglib.class);

    /**
     * The frame handler used to send the EZSP frames to the NCP
     */
    private EzspProtocolHandler protocolHandler;

    /**
     * Constructor for {@link EmberMfglib} taking an instance of the {@link EzspProtocolHandler} that has been opened to
     * communicate with the Ember dongle.
     * 
     * @param protocolHandler an instance of {@link EzspProtocolHandler} to communicate with the dongle
     */
    public EmberMfglib(EzspProtocolHandler protocolHandler) {
        this.protocolHandler = protocolHandler;
    }

    /**
     * Activate use of mfglib test routines and enables the radio receiver to report packets it receives to the
     * mfgLibRxHandler() callback. These packets will not be passed up with a CRC failure. All other mfglib functions
     * will return an error until the mfglibStart() has been called
     *
     * @return true if the request succeeded
     */
    public boolean doMfglibStart() {
        EzspMfglibStartRequest mfgStartRequest = new EzspMfglibStartRequest();
        mfgStartRequest.setRxCallback(true);
        EzspSingleResponseTransaction transaction = new EzspSingleResponseTransaction(mfgStartRequest,
                EzspMfglibStartResponse.class);
        protocolHandler.sendEzspTransaction(transaction);

        EzspMfglibStartResponse response = (EzspMfglibStartResponse) transaction.getResponse();
        logger.debug(response.toString());
        if (response.getStatus() != EmberStatus.EMBER_SUCCESS) {
            logger.debug("Error with mfgStart: {}", response);
            return false;
        }

        return true;
    }

    /**
     * Deactivate use of mfglib test routines; restores the hardware to the state it was in prior to mfglibStart() and
     * stops receiving packets started by mfglibStart() at the same time.
     *
     * @return true if the request succeeded
     */
    public boolean doMfglibEnd() {
        EzspMfglibEndRequest mfgEndRequest = new EzspMfglibEndRequest();
        EzspSingleResponseTransaction transaction = new EzspSingleResponseTransaction(mfgEndRequest,
                EzspMfglibEndResponse.class);
        protocolHandler.sendEzspTransaction(transaction);

        EzspMfglibEndResponse response = (EzspMfglibEndResponse) transaction.getResponse();
        logger.debug(response.toString());
        if (response.getStatus() != EmberStatus.EMBER_SUCCESS) {
            logger.debug("Error with mfgEnd: {}", response);
            return false;
        }

        return true;
    }

    /**
     * Starts transmitting an unmodulated tone on the currently set channel and power level. Upon successful return, the
     * tone will be transmitting. To stop transmitting tone, application must call mfglibStopTone(), allowing it the
     * flexibility to determine its own criteria for tone duration (time, event, etc.)
     *
     * @return true if the request succeeded
     */
    public boolean doMfglibStartTone() {
        EzspMfglibStartToneRequest request = new EzspMfglibStartToneRequest();
        EzspSingleResponseTransaction transaction = new EzspSingleResponseTransaction(request,
                EzspMfglibStartToneResponse.class);
        protocolHandler.sendEzspTransaction(transaction);

        EzspMfglibStartToneResponse response = (EzspMfglibStartToneResponse) transaction.getResponse();
        logger.debug(response.toString());
        if (response.getStatus() != EmberStatus.EMBER_SUCCESS) {
            logger.debug("Error with mfgStartTone: {}", response);
            return false;
        }

        return true;
    }

    /**
     * Stops transmitting tone started by doMfglibStartTone().
     *
     * @return true if the request succeeded
     */
    public boolean doMfglibStopTone() {
        EzspMfglibStopToneRequest request = new EzspMfglibStopToneRequest();
        EzspSingleResponseTransaction transaction = new EzspSingleResponseTransaction(request,
                EzspMfglibStopToneResponse.class);
        protocolHandler.sendEzspTransaction(transaction);

        EzspMfglibStopToneResponse response = (EzspMfglibStopToneResponse) transaction.getResponse();
        logger.debug(response.toString());
        if (response.getStatus() != EmberStatus.EMBER_SUCCESS) {
            logger.debug("Error with mfgStopTone: {}", response);
            return false;
        }

        return true;
    }

    /**
     * Starts transmitting a random stream of characters. This is so that the radio modulation can be measured.
     *
     * @return true if the request succeeded
     */
    public boolean doMfglibStartStream() {
        EzspMfglibStartStreamRequest request = new EzspMfglibStartStreamRequest();
        EzspSingleResponseTransaction transaction = new EzspSingleResponseTransaction(request,
                EzspMfglibStartStreamResponse.class);
        protocolHandler.sendEzspTransaction(transaction);

        EzspMfglibStartStreamResponse response = (EzspMfglibStartStreamResponse) transaction.getResponse();
        logger.debug(response.toString());
        if (response.getStatus() != EmberStatus.EMBER_SUCCESS) {
            logger.debug("Error with mfgStartStream: {}", response);
            return false;
        }

        return true;
    }

    /**
     * Stops transmitting tone started by doMfglibStartTone().
     *
     * @return true if the request succeeded
     */
    public boolean doMfglibStopStream() {
        EzspMfglibStopStreamRequest request = new EzspMfglibStopStreamRequest();
        EzspSingleResponseTransaction transaction = new EzspSingleResponseTransaction(request,
                EzspMfglibStopStreamResponse.class);
        protocolHandler.sendEzspTransaction(transaction);

        EzspMfglibStopStreamResponse response = (EzspMfglibStopStreamResponse) transaction.getResponse();
        logger.debug(response.toString());
        if (response.getStatus() != EmberStatus.EMBER_SUCCESS) {
            logger.debug("Error with mfgStopStream: {}", response);
            return false;
        }

        return true;
    }

    /**
     * Sets the radio channel. Calibration occurs if this is the first time the channel has been used.
     *
     * @param channel the {@link ZigBeeChannel} to use
     * @return true if the request succeeded
     */
    public boolean doMfglibSetChannel(ZigBeeChannel channel) {
        EzspMfglibSetChannelRequest channelRequest = new EzspMfglibSetChannelRequest();
        channelRequest.setChannel(channel.getChannel());
        EzspSingleResponseTransaction transaction = new EzspSingleResponseTransaction(channelRequest,
                EzspMfglibSetChannelResponse.class);
        protocolHandler.sendEzspTransaction(transaction);

        EzspMfglibSetChannelResponse response = (EzspMfglibSetChannelResponse) transaction.getResponse();
        logger.debug(response.toString());
        if (response.getStatus() != EmberStatus.EMBER_SUCCESS) {
            logger.debug("Error with mfgSetChannel: {}", response);
            return false;
        }

        return true;
    }

    /**
     * Returns the current radio channel, as previously set via mfglibSetChannel().
     *
     * return the {@link ZigBeeChannel} in use
     */
    public ZigBeeChannel doMfglibGetChannel() {
        EzspMfglibGetChannelRequest channelRequest = new EzspMfglibGetChannelRequest();
        EzspSingleResponseTransaction transaction = new EzspSingleResponseTransaction(channelRequest,
                EzspMfglibGetChannelResponse.class);
        protocolHandler.sendEzspTransaction(transaction);

        EzspMfglibGetChannelResponse response = (EzspMfglibGetChannelResponse) transaction.getResponse();
        logger.debug(response.toString());
        return ZigBeeChannel.create(response.getChannel());
    }

    /**
     * First select the transmit power mode, and then include a method for selecting the radio transmit power. The valid
     * power settings depend upon the specific radio in use. Ember radios have discrete power settings, and then
     * requested power is rounded to a valid power setting; the actual power output is available to the caller via
     * mfglibGetPower().
     *
     * @param mode the {@link EmberPowerMode} to use
     * @param power the power to set
     * @return true if the request succeeded
     */
    public boolean doMfglibSetPower(EmberPowerMode mode, int power) {
        EzspMfglibSetPowerRequest channelRequest = new EzspMfglibSetPowerRequest();
        channelRequest.setTxPowerMode(mode);
        channelRequest.setPower(power);
        EzspSingleResponseTransaction transaction = new EzspSingleResponseTransaction(channelRequest,
                EzspMfglibSetPowerResponse.class);
        protocolHandler.sendEzspTransaction(transaction);

        EzspMfglibSetPowerResponse response = (EzspMfglibSetPowerResponse) transaction.getResponse();
        logger.debug(response.toString());
        if (response.getStatus() != EmberStatus.EMBER_SUCCESS) {
            logger.debug("Error with mfgSetPower: {}", response);
            return false;
        }

        return true;
    }

    /**
     * Power in units of dBm. Refer to radio data sheet for valid range.
     *
     * return the current transmit power in dBm
     */
    public int doMfglibGetTxPower() {
        EzspMfglibGetPowerRequest channelRequest = new EzspMfglibGetPowerRequest();
        EzspSingleResponseTransaction transaction = new EzspSingleResponseTransaction(channelRequest,
                EzspMfglibGetPowerResponse.class);
        protocolHandler.sendEzspTransaction(transaction);

        EzspMfglibGetPowerResponse response = (EzspMfglibGetPowerResponse) transaction.getResponse();
        logger.debug(response.toString());
        return response.getPower();
    }

    /**
     * First select the transmit power mode, and then include a method for selecting the radio transmit power. The valid
     * power settings depend upon the specific radio in use. Ember radios have discrete power settings, and then
     * requested power is rounded to a valid power setting; the actual power output is available to the caller via
     * mfglibGetPower().
     *
     * @param data the data to send. Note that the last two bytes will be replaced with the CRC when transmitted.
     * @return true if the request succeeded
     */
    public boolean doMfglibSendPacket(int[] data) {
        EzspMfglibSendPacketRequest request = new EzspMfglibSendPacketRequest();
        request.setPacketContents(data);
        EzspSingleResponseTransaction transaction = new EzspSingleResponseTransaction(request,
                EzspMfglibSendPacketResponse.class);
        protocolHandler.sendEzspTransaction(transaction);

        EzspMfglibSendPacketResponse response = (EzspMfglibSendPacketResponse) transaction.getResponse();
        logger.debug(response.toString());
        if (response.getStatus() != EmberStatus.EMBER_SUCCESS) {
            logger.debug("Error with mfgSendPacket: {}", response);
            return false;
        }

        return true;
    }

}

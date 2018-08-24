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
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspMfglibSetChannelRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspMfglibSetChannelResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspMfglibStartRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspMfglibStartResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberStatus;
import com.zsmartsystems.zigbee.dongle.ember.internal.EmberNetworkInitialisation;
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
    private final Logger logger = LoggerFactory.getLogger(EmberNetworkInitialisation.class);

    /**
     * The frame handler used to send the EZSP frames to the NCP
     */
    private EzspProtocolHandler protocolHandler;

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

        EzspMfglibStartResponse mfgStartResponse = (EzspMfglibStartResponse) transaction.getResponse();
        logger.debug(mfgStartResponse.toString());
        if (mfgStartResponse.getStatus() != EmberStatus.EMBER_SUCCESS) {
            logger.debug("Error with mfgStart: {}", mfgStartResponse);
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

        EzspMfglibEndResponse mfgEndResponse = (EzspMfglibEndResponse) transaction.getResponse();
        logger.debug(mfgEndResponse.toString());
        if (mfgEndResponse.getStatus() != EmberStatus.EMBER_SUCCESS) {
            logger.debug("Error with mfgEnd: {}", mfgEndResponse);
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

        EzspMfglibSetChannelResponse channelResponse = (EzspMfglibSetChannelResponse) transaction.getResponse();
        logger.debug(channelResponse.toString());
        if (channelResponse.getStatus() != EmberStatus.EMBER_SUCCESS) {
            logger.debug("Error with mfgChannel: {}", channelResponse);
            return false;
        }

        return true;
    }

}

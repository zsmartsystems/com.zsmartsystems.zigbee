/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.aps;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.transaction.ZigBeeTransactionManager;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportProgressState;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportTransmit;

/**
 * This class provides services in the APS Data Entity. Not all APSDE services are provided here - reliable delivery for
 * example is provided in the {@link ZigBeeTransactionManager}.
 * <p>
 * The APSDE shall provide a data service to the network layer and both ZDO and application objects to enable the
 * transport of application PDUs between two or more devices. The devices themselves must be located on the same
 * network.
 * <p>
 * The APSDE will provide the following services:
 * <ul>
 * <li>Duplicate rejection: messages offered for transmission will not be received more than once.
 * <li>Fragmentation: this enables segmentation and reassembly of messages longer than the payload of a single NWK layer
 * frame.
 * </ul>
 * <p>
 * This class performs checks using the network address for speed (since this is always known in the APS frame). This
 * may cause duplicate checks to fail if a device changes NWK address within the duplicate time window. Given address
 * changes are rare, and probably only occur when a device has been absent from the network for some period (ie it is
 * rejoining), and the duplicate time window is relatively short, this is considered acceptable.
 *
 * @author Chris Jackson
 *
 */
public class ApsDataEntity {
    private static final long DUPLICATE_TIME_WINDOW = 5000;

    private static final int FRAGMENTATION_LENGTH = 65;
    private static final int FRAGMENTATION_WINDOW = 1;

    /**
     * The logger.
     */
    private final Logger logger = LoggerFactory.getLogger(ApsDataEntity.class);

    /**
     * The {@link ZigBeeTransportTransmit} used to send data
     */
    private final ZigBeeTransportTransmit transport;

    /**
     * APS counter for each node in the network to allow duplicate packet removal
     */
    private final Map<Integer, Integer> apsCounters = new ConcurrentHashMap<>();

    /**
     * Last frame received time for each node. This allows us to only remove duplicates received within a specified time
     */
    private final Map<Integer, Long> lastFrameTimes = new ConcurrentHashMap<>();

    /**
     * The number of milliseconds within which a frame with the same APS counter will be considered a duplicate
     */
    private Long duplicateTimeWindow = DUPLICATE_TIME_WINDOW;

    /**
     * The maximum number of outstanding fragment allowed to be sent without acknowledgement
     */
    private int fragmentationWindow = FRAGMENTATION_WINDOW;

    /**
     * The maximum length of data sent in each fragment
     * TODO: Should this use the MTU from the node descriptors?
     */
    private int fragmentationLength = FRAGMENTATION_LENGTH;

    /**
     * Queue of fragmented frames being sent
     * TODO: Should this be per device to avoid ID conflicts from different devices
     */
    private Map<Integer, ZigBeeApsFrame> fragmentTxQueue = new HashMap<>();

    /**
     * Queue of fragmented frames being received
     */
    private Map<Integer, ZigBeeApsFrame> fragmentRxQueue = new HashMap<>();

    public ApsDataEntity(ZigBeeTransportTransmit transport) {
        this.transport = transport;
    }

    /**
     * Sets the number of milliseconds within which an APS frame with the same counter will be considered a duplicate.
     *
     * @param duplicateTimeWindow the number of milliseconds within which an APS frame with the same counter will be
     *            considered a duplicate
     */
    public void setDuplicateTimeWindow(Long duplicateTimeWindow) {
        this.duplicateTimeWindow = duplicateTimeWindow;
    }

    /**
     * Sets the fragmentation window - the number of fragments that may be outstanding at any time.
     *
     * @param fragmentationWindow the fragmentation window
     */
    public void setFragmentationWindow(int fragmentationWindow) {
        this.fragmentationWindow = fragmentationWindow;
    }

    /**
     * Sets the length of a fragment - ie the maximum number of bytes in a fragment.
     *
     * @param fragmentationLength the maximum number of bytes in a fragment
     */
    public void setFragmentationLength(int fragmentationLength) {
        this.fragmentationLength = fragmentationLength;
    }

    /**
     * Processes a received {@link ZigBeeApsFrame}, and returns the frame that is to fed up the stack. The APS layer may
     * return null from this command if it should not be processed up the stack, or it may return a different frame if
     * defragmentation is performed.
     * <p>
     * If the APS frame counter is set to -1, then duplicate packet checks will not be performed.
     *
     * @param apsFrame the received {@link ZigBeeApsFrame}
     * @return the {@link ZigBeeApsFrame} to be used within the upper layers or null if the frame is not to be fed into
     *         the rest of the system
     */
    public synchronized ZigBeeApsFrame receive(final ZigBeeApsFrame apsFrame) {
        if (apsFrame instanceof ZigBeeApsFrameFragment) {
            return receiveFragment((ZigBeeApsFrameFragment) apsFrame);
        }

        Integer apsCounter = apsCounters.get(apsFrame.getSourceAddress());
        if (apsCounter != null && apsCounter == apsFrame.getApsCounter() && (lastFrameTimes
                .get(apsFrame.getSourceAddress()) > System.currentTimeMillis() - duplicateTimeWindow)) {
            logger.debug("{}: APS Data: Duplicate frame from {}ms ago dropped: {}", apsFrame.getSourceAddress(),
                    System.currentTimeMillis() - lastFrameTimes.get(apsFrame.getSourceAddress()), apsFrame);
            return null;
        }

        if (apsFrame.getApsCounter() != -1) {
            apsCounters.put(apsFrame.getSourceAddress(), apsFrame.getApsCounter());
            lastFrameTimes.put(apsFrame.getSourceAddress(), System.currentTimeMillis());
        }

        return apsFrame;
    }

    public synchronized boolean send(final int msgTag, final ZigBeeApsFrame apsFrame) {
        // TODO: Should we check that there is not already a fragmented frame being sent to this destination?

        // Check that we have fragmentation enabled and that this frame requires fragmenting
        // TODO: Don't fragment unicast or broadcast
        if (apsFrame.getPayload().length < fragmentationLength || fragmentationWindow == 0) {
            transport.sendCommand(msgTag, apsFrame);
            return true;
        }

        int totalFragments = ((apsFrame.getPayload().length + fragmentationLength - 1) / fragmentationLength);
        logger.debug("Fragmenting APS Frame: frameLength={}, totalFragments={}, fragmentationLength={}",
                apsFrame.getPayload().length, totalFragments, fragmentationLength);

        apsFrame.setMsgTag(msgTag);
        apsFrame.setFragmentBase(0);
        apsFrame.setFragmentTotal(totalFragments);
        apsFrame.setFragmentSize(fragmentationLength);
        logger.debug("Fragmenting APS Frame {}: {}", msgTag, apsFrame);

        if (fragmentTxQueue.put(msgTag, apsFrame) != null) {
            logger.debug("Fragmenting msgTag {} was already queued", msgTag);
        }

        sendNextFragments(apsFrame);
        return true;
    }

    private void sendNextFragments(final ZigBeeApsFrame apsFrame) {
        logger.debug("sendNextFragments tag={}: frame={}", apsFrame.getMsgTag(), apsFrame);

        boolean first = true;
        for (int fragmentNumber = apsFrame.getFragmentBase()
                + apsFrame.getFragmentOutstanding(); fragmentNumber < apsFrame.getFragmentBase() + fragmentationWindow
                        && fragmentNumber < apsFrame.getFragmentTotal(); fragmentNumber++) {
            if (first) {
                first = false;
            } else {
                // TODO: Delay
            }

            ZigBeeApsFrameFragment fragment = new ZigBeeApsFrameFragment(0);
            fragment.setCluster(apsFrame.getCluster());
            fragment.setApsCounter(apsFrame.getApsCounter());
            fragment.setSecurityEnabled(apsFrame.getSecurityEnabled());
            fragment.setSourceAddress(apsFrame.getSourceAddress());
            fragment.setSourceEndpoint(apsFrame.getSourceEndpoint());
            fragment.setRadius(apsFrame.getRadius());
            fragment.setAddressMode(apsFrame.getAddressMode());
            fragment.setDestinationAddress(apsFrame.getDestinationAddress());
            fragment.setDestinationEndpoint(apsFrame.getDestinationEndpoint());
            fragment.setDestinationIeeeAddress(apsFrame.getDestinationIeeeAddress());
            fragment.setProfile(apsFrame.getProfile());
            fragment.setFragmentNumber(fragmentNumber);
            fragment.setFragmentTotal(apsFrame.getFragmentTotal());
            fragment.setFragmentSize(fragmentationLength);
            fragment.setMsgTag(apsFrame.getMsgTag());

            int offset = fragmentNumber * fragmentationLength;
            int end = offset + ((offset + fragmentationLength < apsFrame.getPayload().length) ? fragmentationLength
                    : (apsFrame.getPayload().length - offset));

            logger.debug("Fragmenting APS Frame: fragment={}, offset={}, end={}", fragmentNumber, offset, end);

            fragment.setPayload(Arrays.copyOfRange(apsFrame.getPayload(), offset, end));

            apsFrame.setFragmentOutstanding(apsFrame.getFragmentOutstanding() + 1);
            logger.debug("Sending APS Frame Fragment: outstanding={} {}", apsFrame.getFragmentOutstanding(), fragment);

            transport.sendCommand(apsFrame.getMsgTag(), fragment);
        }
    }

    /**
     * Callback from the transport layer when it has progressed the state of the transaction.
     * This will return false if the fragment is not yet complete - this may be used by the system to propagate the
     * command state within the transaction manager.
     *
     * @param msgTag the message tag whose state is updated
     * @param state the updated ZigBeeTransportProgressState for the transaction
     * @return false if this frame is part of a fragmented packet and this is not the last frame. true otherwise.
     */
    public boolean receiveCommandState(int msgTag, ZigBeeTransportProgressState state) {
        ZigBeeApsFrame outgoingFrame = getFragmentedFrameFromQueue(msgTag);
        if (outgoingFrame == null) {
            // Not a fragmented packet
            return true;
        }
        logger.debug("receiveCommandState tag={}-{}: Fragment APS Frame: {}", msgTag, state, outgoingFrame);

        // Handle a failed transmission by aborting the sequence and passing the error up the stack
        if (state == ZigBeeTransportProgressState.RX_NAK || state == ZigBeeTransportProgressState.TX_NAK) {
            abortFragmentedFrame(msgTag);
            return true;
        }

        logger.debug("receiveCommandState tag={}, fragmentBase={}, fragmentOutstanding={}", msgTag,
                outgoingFrame.getFragmentBase(), outgoingFrame.getFragmentOutstanding());

        outgoingFrame.setFragmentBase(outgoingFrame.getFragmentBase() + 1);
        outgoingFrame.setFragmentOutstanding(outgoingFrame.getFragmentOutstanding() - 1);
        if (outgoingFrame.getFragmentBase() == outgoingFrame.getFragmentTotal()) {
            logger.debug("Completed Sending Fragment APS Frame: {}", outgoingFrame);
            return true;
        }
        logger.debug("receiveCommandState tag={}, fragmentBase={}, fragmentOutstanding={}", msgTag,
                outgoingFrame.getFragmentBase(), outgoingFrame.getFragmentOutstanding());

        sendNextFragments(outgoingFrame);
        logger.debug("receiveCommandState DONE");

        return false;
    }

    /**
     * Gets the queued {@link ZigBeeApsFrame} being sent given the outgoing msgTag
     *
     * @param msgTag the msgTag used to send the frame
     * @return the {@link ZigBeeApsFrame} from the queue, or null if a corresponding frame could not be found
     */
    private ZigBeeApsFrame getFragmentedFrameFromQueue(int msgTag) {
        return fragmentTxQueue.get(msgTag);
    }

    private void abortFragmentedFrame(int msgTag) {
        logger.debug("Aborting APS Frame Fragment: {}", msgTag);
        fragmentTxQueue.remove(msgTag);
    }

    private ZigBeeApsFrame receiveFragment(ZigBeeApsFrameFragment fragment) {
        ZigBeeApsFrame apsFrame = fragmentRxQueue.get(fragment.getApsCounter());
        if (apsFrame == null) {
            logger.debug("Fragment frame from unknown frame: {}", fragment);

            if (fragment.getFragmentNumber() != 0) {
                logger.debug("Fragment frame from unknown fragment not first fragment: {}", fragment);
                // Fragment received out of sequence
                return null;
            }

            fragment.setFragmentBase(1);
            fragmentRxQueue.put(fragment.getApsCounter(), fragment);

            return null;
        }

        if (fragment.getFragmentNumber() != apsFrame.getFragmentBase()) {
            // Abort
            fragmentRxQueue.remove(fragment.getApsCounter());
            logger.debug("Fragment out of order: {} - expected {}", fragment.getFragmentNumber(),
                    apsFrame.getFragmentBase());
            return null;
        }

        int[] combined = new int[apsFrame.getPayload().length + fragment.getPayload().length];
        System.arraycopy(apsFrame.getPayload(), 0, combined, 0, apsFrame.getPayload().length);
        System.arraycopy(fragment.getPayload(), 0, combined, apsFrame.getPayload().length,
                fragment.getPayload().length);
        apsFrame.setPayload(combined);
        apsFrame.setFragmentBase(fragment.getFragmentNumber() + 1);

        logger.debug("Received fragment frame {} of {}: {}", fragment.getFragmentNumber(), apsFrame.getFragmentTotal(),
                apsFrame);

        if (apsFrame.getFragmentBase() == apsFrame.getFragmentTotal()) {
            fragmentRxQueue.remove(fragment.getApsCounter());
            logger.debug("Fragment completed frame: {}", fragment);
            return apsFrame;
        }

        return null;
    }

}

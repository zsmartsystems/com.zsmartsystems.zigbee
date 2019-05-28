/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.aps;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.transaction.ZigBeeTransactionManager;

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

    /**
     * The logger.
     */
    private final Logger logger = LoggerFactory.getLogger(ApsDataEntity.class);

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
     * Sets the number of milliseconds within which an APS frame with the same counter will be considered a duplicate
     *
     * @param duplicateTimeWindow the number of milliseconds within which an APS frame with the same counter will be
     *            considered a duplicate
     */
    public void setDuplicateTimeWindow(Long duplicateTimeWindow) {
        this.duplicateTimeWindow = duplicateTimeWindow;
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
}

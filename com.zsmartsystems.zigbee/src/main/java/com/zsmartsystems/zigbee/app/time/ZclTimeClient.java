/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.app.time;

import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.ZigBeeEndpointAddress;
import com.zsmartsystems.zigbee.zcl.clusters.ZclTimeCluster;
import com.zsmartsystems.zigbee.zcl.clusters.time.TimeStatusBitmap;
import com.zsmartsystems.zigbee.zcl.field.ZigBeeUtcTime;

/**
 * Time client used to set the time on a time server.
 * <p>
 * This class provides methods used by the {@link ZigBeeTimeExtension} to maintain the remote device clock.
 * {@link #doPoll()} is used to periodically get the remote time in order to observe the remote clock, and
 * {@link #doUpdate()} is used to set the remote clock.
 * <p>
 * This class will attempt to calculate the drift rate of the clock on the remote device, and will estimate the time at
 * which the clock will be out of specification.
 *
 * @author Chris Jackson
 *
 */
public class ZclTimeClient {
    /**
     * The {@link Logger}.
     */
    private final Logger logger = LoggerFactory.getLogger(ZclTimeClient.class);

    /**
     * Don't use interval data of less than MINIMUM_USABLE_DELTA_DURATION seconds in the drift calculation unless the
     * drift is above MINIMUM_USABLE_DELTA_DRIFT seconds.
     */
    private final static int MINIMUM_USABLE_DELTA_DURATION = 1800;

    /**
     * Don't use interval data of less than MINIMUM_USABLE_DELTA_DURATION seconds in the drift calculation unless the
     * drift is above MINIMUM_USABLE_DELTA_DRIFT seconds.
     */
    private final static int MINIMUM_USABLE_DELTA_DRIFT = 10;

    /**
     * The time cluster linked to the remote server
     */
    private final ZclTimeCluster timeCluster;

    private ZigBeeUtcTime dstStart;
    private ZigBeeUtcTime dstEnd;
    private int dstOffset;

    /**
     * Calculation of the daily drift rate. This is updated following each time set or poll.
     */
    private double dailyDriftRate;

    /**
     * The local time we last synchronised the remote
     */
    private ZigBeeUtcTime lastUpdate;

    private Integer lastStatus = Integer.valueOf(0);
    private final Set<TimeStatusBitmap> statusBitmap = new HashSet<>();

    /**
     * The local time we last read the remote time
     */
    private ZigBeeUtcTime lastRequestedTime;

    /**
     * The time the remote device last set its time - as reported by the remote
     */
    private ZigBeeUtcTime lastSetTime;

    /**
     * The number of seconds difference between the remote and local times at the last time sync
     */
    private int lastTimeDelta = 0;

    /**
     * The number of seconds difference between the remote and local times at the last poll
     */
    private int currentTimeDelta = 0;

    /**
     * Flag that denotes if we need to update the daylight savings configuration.
     * This is set to false if the DST data is updated,
     * and then set to true when the remote is configured with this data.
     */
    private boolean dstUpdated = true;

    /**
     * A map used to calculate the remote clock drift. We save subsequent updates into this map so we don't have to work
     * just with the current time which only has a precision of 1 second. This would make 24hr drift calculations very
     * inaccurate over short intervals.
     */
    private final Map<ZigBeeUtcTime, ZigBeeUtcTime> driftMap = new TreeMap<>();

    protected ZclTimeClient(ZclTimeCluster timeCluster) {
        this.timeCluster = timeCluster;
    }

    /**
     * Set the daylight savings time parameters, including the start and end of daylight saving, and the offset. This
     * will be passed on to all remote nodes at the next update.
     *
     * @param dstStart an {@link ZigBeeUtcTime} defining the time daylight savings time starts
     * @param dstEnd an {@link ZigBeeUtcTime} defining the time daylight savings time ends
     * @param dstOffset the number of seconds that daylight savings is adjusted
     */
    protected void setDst(ZigBeeUtcTime dstStart, ZigBeeUtcTime dstEnd, int dstOffset) {
        this.dstStart = dstStart;
        this.dstEnd = dstEnd;
        this.dstOffset = dstOffset;

        /**
         * Remember that we need to update the DST next time we update the time
         */
        dstUpdated = false;
    }

    /**
     * Gets the current state of the DST setting on the remote device. If the client has set the DST data on the remote
     * device this will return true.
     * <p>
     * This is a client side status as opposed to the {@link #isRemoteMasterDst()} method which is a server side status
     *
     * @return true if the DST data has been set by the client on the remote device
     */
    public boolean isDstSet() {
        return dstUpdated;
    }

    /**
     * Get the {@link ZigBeeEndpointAddress} for this client
     *
     * @return the {@link ZigBeeEndpointAddress} for this client
     */
    public ZigBeeEndpointAddress getZigBeeAddress() {
        return timeCluster.getZigBeeAddress();
    }

    /**
     * Reads the current time from the remote device
     *
     * @return the {@link ZigBeeUtcTime} from the remote device
     */
    public ZigBeeUtcTime getTime() {
        return timeCluster.getTime(0);
    }

    /**
     * Gets the time returned from the device after it is updated
     *
     * @return
     */
    public ZigBeeUtcTime getLastSetTime() {
        return lastSetTime;
    }

    /**
     * Returns the last update {@link ZigBeeUtcTime} that the client last completed the time synchronisation
     *
     * @return the last update {@link ZigBeeUtcTime} that the client last completed the time synchronisation
     */
    public ZigBeeUtcTime getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Returns the last {@link ZigBeeUtcTime} that the client last requested the remote time.
     *
     * @return the last {@link ZigBeeUtcTime} that the client last requested the remote time, or null if the time has
     *         never
     *         been requested
     */
    public ZigBeeUtcTime getLastRequestedTime() {
        return lastRequestedTime;
    }

    /**
     * Gets the number of seconds difference between local time and remote time at the point the time was last set on
     * the remote.
     *
     * @return remote time difference when the time was last updated
     */
    public int getLastDelta() {
        return lastTimeDelta;
    }

    /**
     * Gets the number of seconds difference between local time and remote time at the last poll
     *
     * @return remote time difference when the time was last polled
     */
    public int getCurrentDelta() {
        return currentTimeDelta;
    }

    /**
     * Gets the time up until when we have calculated the remote device clock will remain valid.
     *
     * @return {@link ZigBeeUtcTime} defining the time we expect the clock will drift out of spec
     */
    public ZigBeeUtcTime getValidUntil() {
        return null;
    }

    /**
     * Returns true if the remote device time is synchronised
     *
     * @return true if the remote device time is synchronised
     */
    public boolean isRemoteSynchronized() {
        return statusBitmap.contains(TimeStatusBitmap.SYNCHRONIZED);
    }

    /**
     * Returns true if the remote device is a master time server
     *
     * @return true if the remote device is a master time server
     */
    public boolean isRemoteMaster() {
        return statusBitmap.contains(TimeStatusBitmap.MASTER);
    }

    /**
     * Returns true if the remote device is a superceding time server
     *
     * @return true if the remote device is a superceding time server
     */
    public boolean isRemoteSuperceding() {
        return statusBitmap.contains(TimeStatusBitmap.SUPERSEDING);
    }

    /**
     * Returns true if the remote device is synchronised to the time server for DST configuration
     *
     * @return true if the remote device is synchronised to the time server for DST configuration
     */
    public boolean isRemoteMasterDst() {
        return statusBitmap.contains(TimeStatusBitmap.MASTER_ZONE_DST);
    }

    /**
     * Gets the approximate drift rate of the remote device over a 24 hour period. This is the number of seconds the
     * time would drift on the remote device if it was not updated.
     *
     * @return the 24 hour drift rate, in seconds, of the remote device.
     */
    public double getDailyDriftRate() {
        return dailyDriftRate;
    }

    /**
     * Performs a poll on the remote device to get the current time delta.
     * <p>
     * Note that this method will block and is intended to be run within a worker thread.
     *
     * @return true if the poll was performed successfully
     */
    protected boolean doPoll() {
        logger.debug("{}: Time client: poll start", timeCluster.getZigBeeAddress());
        ZigBeeUtcTime currentTime = timeCluster.getTime(0);
        if (currentTime == null) {
            logger.debug("{}: Time client: poll failed getting time", timeCluster.getZigBeeAddress());
            return false;
        }
        lastRequestedTime = ZigBeeUtcTime.now();

        currentTimeDelta = (int) (lastRequestedTime.getEpochSecond() - currentTime.getEpochSecond());

        // If we have no records in the drift map, then add this as a baseline
        if (driftMap.isEmpty()) {
            driftMap.put(lastRequestedTime, currentTime);
        } else {
            updateDrift(ZigBeeUtcTime.now(), currentTime);
        }

        updateStatus();

        logger.debug("{}: Time client: poll complete. Delta {} seconds.", timeCluster.getZigBeeAddress(),
                currentTimeDelta);
        return true;
    }

    /**
     * Performs a time synchronisation on the remote device.
     * <p>
     * Note that this method will block and is intended to be run within a worker thread.
     *
     * @return true if the time was set successfully
     * @throws ExecutionException
     * @throws InterruptedException
     */
    protected boolean doUpdate() throws InterruptedException, ExecutionException {
        logger.debug("{}: Time client: synchronisation start", timeCluster.getZigBeeAddress());

        ZigBeeUtcTime currentTime = timeCluster.getTime(0);
        if (currentTime == null) {
            logger.debug("{}: Time client: synchronisation failed getting time", timeCluster.getZigBeeAddress());
            return false;
        }
        lastRequestedTime = ZigBeeUtcTime.now();

        lastTimeDelta = (int) (lastRequestedTime.getEpochSecond() - currentTime.getEpochSecond());

        CommandResult result = timeCluster.setTime(ZigBeeUtcTime.now()).get();
        if (!result.isSuccess()) {
            logger.debug("{}: Time client: synchronisation failed setting time", timeCluster.getZigBeeAddress());
            return false;
        }
        updateDrift(lastRequestedTime, currentTime);
        lastUpdate = ZigBeeUtcTime.now();
        driftMap.put(lastUpdate, currentTime);

        // Set the synchronised bit in the remote time status attribute
        timeCluster.setTimeStatus(TimeStatusBitmap.SYNCHRONIZED.getKey());

        updateStatus();

        // Has DST data been updated since we last set the time?
        if (!dstUpdated) {
            timeCluster.setDstStart(dstStart);
            timeCluster.setDstEnd(dstEnd);
            timeCluster.setDstShift(dstOffset);
            dstUpdated = true;
        }
        lastSetTime = timeCluster.getLastSetTime(0);

        // Do a poll to update our knowledge
        // TODO: Some devices don't actually seem to update their clock.
        // We could check here to see if the delta has changed.
        currentTime = timeCluster.getTime(0);
        if (currentTime == null) {
            logger.debug("{}: Time client: poll failed getting time", timeCluster.getZigBeeAddress());
            return false;
        }
        lastRequestedTime = ZigBeeUtcTime.now();
        currentTimeDelta = (int) (lastRequestedTime.getEpochSecond() - currentTime.getEpochSecond());

        // TODO: Set the ValidUntilTime attribute based on the calculated drift, plus margin

        logger.debug("{}: Time client: synchronisation complete. Delta {} seconds.", timeCluster.getZigBeeAddress(),
                currentTimeDelta);
        return true;
    }

    private void updateStatus() {
        lastStatus = timeCluster.getTimeStatus(0);
        synchronized (statusBitmap) {
            statusBitmap.clear();
            for (TimeStatusBitmap bitmap : TimeStatusBitmap.values()) {
                if ((lastStatus & bitmap.getKey()) != 0) {
                    statusBitmap.add(bitmap);
                }
            }
        }
    }

    private void updateDrift(ZigBeeUtcTime lastLocalTime, ZigBeeUtcTime lastRemoteTime) {
        double duration = 0;
        double drift = 0;
        boolean first = true;
        ZigBeeUtcTime lastSet = null;
        ZigBeeUtcTime lastTime = null;
        for (Entry<ZigBeeUtcTime, ZigBeeUtcTime> driftSet : driftMap.entrySet()) {
            if (first) {
                lastSet = driftSet.getKey();
                lastTime = driftSet.getValue();
                continue;
            }

            int deltaDuration = (int) (driftSet.getKey().getEpochSecond() - lastSet.getEpochSecond());
            int deltaDrift = (int) (driftSet.getValue().getEpochSecond() - lastTime.getEpochSecond());
            if (deltaDuration > MINIMUM_USABLE_DELTA_DURATION || deltaDrift > MINIMUM_USABLE_DELTA_DRIFT) {
                duration += deltaDuration;
                drift += deltaDrift;
            }

            lastSet = driftSet.getKey();
            lastTime = driftSet.getValue();
        }

        int deltaDuration = (int) (lastLocalTime.getEpochSecond() - lastSet.getEpochSecond());
        int deltaDrift = (int) (lastRemoteTime.getEpochSecond() - lastTime.getEpochSecond());
        if (deltaDuration > MINIMUM_USABLE_DELTA_DURATION || deltaDrift > MINIMUM_USABLE_DELTA_DRIFT) {
            duration += deltaDuration;
            drift += deltaDrift;
        }

        dailyDriftRate = (drift / duration * 86400.0) - 86400.0;
    }
}

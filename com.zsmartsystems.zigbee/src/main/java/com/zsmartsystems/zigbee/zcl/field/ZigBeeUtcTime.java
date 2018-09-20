/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.field;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Representation of a ZigBee UTC time type.
 * <p>
 * UTCTime is an unsigned 32-bit value representing the number of seconds since 0 hours, 0 minutes, 0 seconds, on the
 * 1st of January, 2000 UTC (Universal Coordinated Time). The value that represents an invalid value of this type is
 * 0xffffffff.
 * <p>
 * This class is immutable and thread-safe.
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeUtcTime implements Comparable<ZigBeeUtcTime> {
    /**
     * Seconds between 1/1/1970 and 1/1/2000
     */
    private static final long JAVA_OFFSET = 946684800;

    /**
     * The ZigBee value transferred when the time is invalid
     */
    private static final long INVALID_TIME = 0xffffffff;

    /**
     * ISO 8601 date formatter
     */
    private static final DateFormat dfIso8601 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

    /**
     * The time value
     */
    private final long zigbeeSecond;

    /**
     * Constructor returning an invalid or unknown time value
     */
    public ZigBeeUtcTime() {
        this.zigbeeSecond = INVALID_TIME;
    }

    /**
     * Private constructor creating the {@link ZigBeeUtcTime} given epoch time of the ZigBee reference of
     * 2000-01-01T00:00:00Z.
     *
     * @param zigbeeSecond the number of seconds since 2000-01-01T00:00:00Z.
     */
    private ZigBeeUtcTime(long zigbeeSecond) {
        this.zigbeeSecond = zigbeeSecond;
    }

    /**
     * Obtains the current {@link ZigBeeUtcTime} from the system clock.
     *
     * @return {@link ZigBeeUtcTime}
     */
    public static ZigBeeUtcTime now() {
        return new ZigBeeUtcTime(System.currentTimeMillis() / 1000 - JAVA_OFFSET);
    }

    /**
     * Returns a new {@link ZigBeeUtcTime} with the specified amount added.
     *
     * @param amountToAdd the number of seconds to add
     * @return a copy of this {@link ZigBeeUtcTime} with the specified amount added.
     */
    public ZigBeeUtcTime plus(long amountToAdd) {
        return new ZigBeeUtcTime(getZigBeeSecond() + amountToAdd);
    }

    /**
     * Returns a new {@link ZigBeeUtcTime} with the specified amount subtracted.
     *
     * @param amountToSubtract the number of seconds to subtract
     * @return a copy of this {@link ZigBeeUtcTime} with the specified amount subtracted.
     */
    public ZigBeeUtcTime minus(long amountToSubtract) {
        return new ZigBeeUtcTime(getZigBeeSecond() - amountToSubtract);
    }

    /**
     * Obtains an instance of {@link ZigBeeUtcTime} using seconds from the epoch of 1970-01-01T00:00:00Z.
     *
     * @param epochSecond the seconds since 1970-01-01T00:00:00Z
     * @return {@link ZigBeeUtcTime}
     */
    public static ZigBeeUtcTime ofEpochSecond(long epochSecond) {
        return new ZigBeeUtcTime(epochSecond - JAVA_OFFSET);
    }

    /**
     * Obtains an instance of {@link ZigBeeUtcTime} using seconds from the epoch of 2000-01-01T00:00:00Z.
     *
     * @param zigbeeSecond the number of seconds since 2000-01-01T00:00:00Z
     * @return {@link ZigBeeUtcTime}
     */
    public static ZigBeeUtcTime ofZigBeeSecond(long zigbeeSecond) {
        return new ZigBeeUtcTime(zigbeeSecond);
    }

    /**
     * Gets the current ZigBee second. This is referenced to the epoch of 2000-01-01T00:00:00Z.
     *
     * @return the number of seconds referenced to the epoch of 2000-01-01T00:00:00Z.
     */
    public long getZigBeeSecond() {
        return zigbeeSecond;
    }

    /**
     * Gets the current Epoch second. This is referenced to the epoch of 1970-01-01T00:00:00Z.
     *
     * @return the number of seconds referenced to the epoch of 1970-01-01T00:00:00Z.
     */
    public long getEpochSecond() {
        if (zigbeeSecond == INVALID_TIME) {
            return INVALID_TIME;
        }

        return zigbeeSecond + JAVA_OFFSET;
    }

    /**
     * Checks if this instant is before the specified instant. This will return false if either time is invalid.
     *
     * @param testTime the {@link ZigBeeUtcTime} to test against
     * @return true if the testTime is before this instant
     */
    public boolean isBefore(ZigBeeUtcTime testTime) {
        if (zigbeeSecond == INVALID_TIME || testTime.getZigBeeSecond() == INVALID_TIME) {
            return false;
        }
        return zigbeeSecond < testTime.getZigBeeSecond();
    }

    /**
     * Checks if this instant is after the specified instant. This will return false if either time is invalid.
     *
     * @param testTime the {@link ZigBeeUtcTime} to test against
     * @return true if the testTime is after this instant
     */
    public boolean isAfter(ZigBeeUtcTime testTime) {
        if (zigbeeSecond == INVALID_TIME || testTime.getZigBeeSecond() == INVALID_TIME) {
            return false;
        }
        return zigbeeSecond > testTime.getZigBeeSecond();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (zigbeeSecond ^ (zigbeeSecond >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ZigBeeUtcTime other = (ZigBeeUtcTime) obj;
        if (zigbeeSecond != other.zigbeeSecond) {
            return false;
        }
        return true;
    }

    /**
     * A string representation of this {@link ZigBeeUtcTime} using ISO-8601 representation.
     */
    @Override
    public String toString() {
        if (zigbeeSecond == INVALID_TIME) {
            return "INVALID_TIME";
        }
        return dfIso8601.format(new Date(getEpochSecond() * 1000));
    }

    @Override
    public int compareTo(ZigBeeUtcTime otherTime) {
        return Long.compare(otherTime.zigbeeSecond, zigbeeSecond);
    }
}

/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.internal;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.ZigBeeAddress;
import com.zsmartsystems.zigbee.ZigBeeChannel;
import com.zsmartsystems.zigbee.ZigBeeChannelMask;
import com.zsmartsystems.zigbee.zdo.command.ManagementNetworkUpdateNotify;

/**
 * This class caches the results from a channel scan
 *
 * @author Chris Jackson
 *
 */
public class ChannelScanCacheRecord {
    /**
     * The {@link Logger}.
     */
    private final Logger logger = LoggerFactory.getLogger(ChannelScanCacheRecord.class);

    private final ZigBeeAddress address;

    private final Long time;

    private final Map<ZigBeeChannel, Integer> scanRecord;

    public ChannelScanCacheRecord(ManagementNetworkUpdateNotify networkUpdate) {
        time = System.currentTimeMillis();
        scanRecord = new TreeMap<ZigBeeChannel, Integer>();
        address = networkUpdate.getSourceAddress();

        ZigBeeChannelMask channelMask = new ZigBeeChannelMask(networkUpdate.getScannedChannels());
        if (channelMask.getChannels().size() != networkUpdate.getEnergyValues().size()) {
            logger.debug("Scan contains {} records but {} channels. Data inconsistent.",
                    networkUpdate.getEnergyValues().size(), channelMask.getChannels().size());
            throw new IllegalArgumentException("Number of scan records is inconsistent with channel count.");
        }

        Iterator<ZigBeeChannel> channelIterator = channelMask.getChannels().iterator();
        for (Integer scanValue : networkUpdate.getEnergyValues()) {
            scanRecord.put(channelIterator.next(), scanValue);
        }
    }

    public Map<ZigBeeChannel, Integer> getScanRecord() {
        return scanRecord;
    }

    /**
     * @return the {@link ZigBeeAddress} of the device generating the scan
     */
    public ZigBeeAddress getAddress() {
        return address;
    }

    /**
     * @return the time the scan was received
     */
    public Long getTime() {
        return time;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(250);
        builder.append("ChannelScanCacheRecord [scanRecord=");
        boolean first = true;
        for (ZigBeeChannel channel : scanRecord.keySet()) {
            if (first == false) {
                builder.append(", ");
            }
            builder.append(channel);
            builder.append('=');
            builder.append(scanRecord.get(channel));
            first = false;
        }
        builder.append(']');
        return builder.toString();
    }

}

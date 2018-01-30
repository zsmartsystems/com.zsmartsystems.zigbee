/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.internal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.ZigBeeChannel;
import com.zsmartsystems.zigbee.ZigBeeChannelMask;
import com.zsmartsystems.zigbee.ZigBeeCommand;
import com.zsmartsystems.zigbee.ZigBeeCommandListener;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.zdo.command.ManagementNetworkUpdateNotify;

/**
 * Each router or coordinator is responsible for tracking transmit failures using the TransmitFailure field in the
 * neighbor table and also keeping a NIB counter for total transmissions attempted. Once the total transmissions
 * attempted is over 20,if the transmit failures exceeds 25% of the messages sent, the device may have detected
 * interference on the channel in use. The device is then responsible for taking the following steps:
 * <p>
 * <ol>
 * <li>Conduct an energy scan on all channels within the current PHY. If this energy scan does not indicate higher
 * energy on the current channel then other channels, no action is taken. The device should continue to operate as
 * normal and the message counters are not reset. However, repeated energy scans are not desirable as the device is off
 * the network during these scans and therefore implementations should limit how often a device with failures conducts
 * energy scans.
 * <li>If the energy scan does indicate increased energy on the channel in use, a Mgmt_NWK_Update_notify should be sent
 * to the Network Manager to indicate interference is present. This report is sent as an APS Unicast with
 * acknowledgement and once the acknowledgment is received the total transmit and transmit failure counters are reset to
 * zero.
 * <li>To avoid a device with communication problems from constantly sending reports to the network manager, the device
 * should not send a Mgmt_NWK_Update_notify more than 4 times per hour.
 * </ol>
 * <p>
 * Upon receipt of an unsolicited Mgmt_NWK_Update_notify, the network manager must evaluate if a channel change is
 * required in the network. The specific mechanisms the network manager uses to decide upon a channel change are left to
 * the implementers. It is expected that implementers will apply different methods to best determine when a channel
 * change is required and how to select the most appropriate channel
 * <p>
 * The network manager may do the following:
 * <p>
 * <ol>
 * <li>Wait and evaluate if other reports from other devices are received. This may be appropriate if there are no other
 * failures reported. In this case the network manager should add the reporting device to a list of devices that have
 * reported interference. The number of devices on such a list would depend on the size of the network. The network
 * manager can age devices out of this list.
 * <li>Request other interference reports using the Mgmt_NWK_Update_req command. This may be done if other failures have
 * been reported or the network manager device itself has failures and a channel change may be desired. The network
 * manager may request data from the list of devices that have reported interference plus other randomly selected
 * routers in the network. The network manager should not request an update from the device that has just reported
 * interference since this data is fresh already.
 * <li>Upon receipt of the Mgmt_NWK_Update_notify, the network manager shall determine if a channel change is required
 * using whatever implementation specific mechanisms are considered appropriate. The network manager device with just
 * one channel allowed in the apsChannelMask parameter must not issue the Mgmt_Nwk_Update_Req command to request other
 * devices to change the current channel. However, the network manager may report channel quality issues to the
 * application.
 * <li>If the above data indicate a channel change should be considered, the network manager shall select a single
 * channel based on the Mgmt_NWK_Update_notify based on the lowest energy. This is the proposed new channel. If this new
 * channel does not have an energy level below an acceptable threshold, a channel change should not be done.
 * Additionally, a new channel shall not belong to a PHY different from the one on which a network manager is operating
 * now.
 * <li>Prior to changing channels, the network manager should store the energy scan value as the last energy scan value
 * and the failure rate from the existing channel as the last failure rate. These values are useful to allow comparison
 * of the failure rate and energy level on the previous channel to evaluate if the network is causing its own
 * interference.
 * <li>The network manager should broadcast a Mgmt_NWK_Update_req notifying devices of the new channel. The broadcast
 * shall be to all devices with RxOnWhenIdle equal to TRUE. The network manager is responsible for incrementing the
 * nwkUpdateId parameter from the NIB and including it in the Mgmt_NWK_Update_req. The network manager shall set a timer
 * based on the value of apsChannelTimer upon issue of a Mgmt_NWK_Update_req that changes channels and shall not issue
 * another such command until this timer expires. However, during this period, the network manager can complete the
 * above analysis. However, instead of changing channels, the network manager would report to the local application
 * using Mgmt_NWK_Update_notify and the application can force a channel change using the Mgmt_NWK_Update_req.
 * </ol>
 * <p>
 * Upon receipt of a Mgmt_NWK_Update_req with a change of channels, the local network manager shall set a timer equal to
 * the nwkNetworkBroadcastDeliveryTime and shall switch channels upon expiration of this timer. Each node shall also
 * increment the nwkUpdateId parameter and also reset the total transmit count and the transmit failure counters.
 * <p>
 * For devices with RxOnWhenIdle equals FALSE, any network channel change will not be received. On these devices or
 * routers that have lost the network, an active scan shall be conducted on the apsChannelMask list in the APS IB using
 * the extended PANID to find the network. If the extended PANID is found on different channels, the device should
 * select the channel with the higher value in the nwkUpdateId parameter. If the extended PANID is not found using
 * the apsChannelMask list, a scan should be completed using all channels within the current PHY.
 * <p>
 * This class monitors the {@link ManagementNetworkUpdateNotify} messages from devices to establish the best channel to
 * be used throughout the network.
 * <p>
 * To decide the appropriate channel, the following rules are used -:
 * <ul>
 * <li>The average of each channel from all scans in the {@link #scanCache} is calculated.
 * <li>If a channel from any scan within the network is is higher than {@link #unusableRssi} then this channel will be
 * discarded from consideration even if it might be the best average level.
 * <li>If the average from the best channel is better than {@link #updateRssi} dB compare to the existing channel, the
 * channel will be updated.
 * </ul>
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeNetworkChannelManager implements ZigBeeCommandListener {
    /**
     * The {@link Logger}.
     */
    private final Logger logger = LoggerFactory.getLogger(ZigBeeNetworkChannelManager.class);

    /**
     * The {@link ZigBeeNetworkManager} the channel manager is to monitor
     */
    private final ZigBeeNetworkManager networkManager;

    /**
     * The current list of channels to choose from
     */
    private final ZigBeeChannelMask channelMask = new ZigBeeChannelMask();

    /**
     * The default period of time allowed between channel updates in milliseconds.
     */
    private final Long DEFAULT_UPDATE_PERIOD = 3600000L;

    /**
     * The default RSSI level used to trigger a channel update in dB
     */
    private final int DEFAULT_UPDATE_RSSI = 6;

    /**
     * The default RSSI level where a channel is considered unusable
     */
    private final int DEFAULT_UNUSABLE_RSSI = -80;

    /**
     * The default period to maintain scan records in the cache used to generate the best channel.
     */
    private final Long DEFAULT_CACHE_PERIOD = 86400000L;

    /**
     * The RSSI level used to change channels. There must be a channel that is better than the current channel by at
     * least this level for the system to change channels.
     */
    private int updateRssi = DEFAULT_UPDATE_RSSI;

    /**
     * The RSSI level where a channel is considered unusable, and any channel exceeding this level within the network
     * will not be used.
     */
    private int unusableRssi = DEFAULT_UNUSABLE_RSSI;

    /**
     * The minimum period of time allowed between channel updates. This helps ensure that if the environment is very
     * dynamic, the channel will not change too often.
     */
    private Long updatePeriod = DEFAULT_UPDATE_PERIOD;

    /**
     * The period to maintain scan records in the {@link scanCache} used to generate the best channel.
     */
    private Long cachePeriod = DEFAULT_CACHE_PERIOD;

    /**
     * List of {@link ChannelScanCacheRecord} received and used to decide the current best channel over the l
     */
    private final List<ChannelScanCacheRecord> scanCache = new ArrayList<ChannelScanCacheRecord>();

    /**
     * Creates a channel manager for the {@link ZigBeeNetworkManager}
     *
     * @param networkManager the {@link ZigBeeNetworkManager} the channel manager is to monitor
     */
    ZigBeeNetworkChannelManager(ZigBeeNetworkManager networkManager) {
        this.networkManager = networkManager;

        networkManager.addCommandListener(this);
    }

    @Override
    public void commandReceived(ZigBeeCommand command) {
        if (command instanceof ManagementNetworkUpdateNotify) {
            ManagementNetworkUpdateNotify updateNotify = (ManagementNetworkUpdateNotify) command;
            scanCache.add(new ChannelScanCacheRecord(updateNotify));
        }
    }

    /**
     * Sets the list of channels current allowed to be used for channel selection
     *
     * @param channelBitmap the {@link ZigBeeChannelMask} of channels
     */
    public void setChannelMask(ZigBeeChannelMask channelMask) {
        this.channelMask.setChannelMask(channelMask.getChannelMask());
    }

    /**
     * @return the updatePeriod
     */
    public Long getUpdatePeriod() {
        return updatePeriod;
    }

    /**
     * @param updatePeriod the updatePeriod to set
     */
    public void setUpdatePeriod(Long updatePeriod) {
        this.updatePeriod = updatePeriod;
    }

    /**
     * @return the updateRssi
     */
    public int getUpdateRssi() {
        return updateRssi;
    }

    /**
     * @param updateRssi the updateRssi to set
     */
    public void setUpdateRssi(int updateRssi) {
        this.updateRssi = updateRssi;
    }

    /**
     * @return the cachePeriod
     */
    public Long getCachePeriod() {
        return cachePeriod;
    }

    /**
     * @param cachePeriod the cachePeriod to set
     */
    public void setCachePeriod(Long cachePeriod) {
        this.cachePeriod = cachePeriod;
    }

    /**
     * @return the unusableRssi
     */
    public int getUnusableRssi() {
        return unusableRssi;
    }

    /**
     * @param unusableRssi the unusableRssi to set
     */
    public void setUnusableRssi(int unusableRssi) {
        this.unusableRssi = unusableRssi;
    }

    /**
     * Returns the current best channel to be used in the network or {@link ZigBeeChannel#UNKNOWN} if insufficient scan
     * data is available
     *
     * @return the current best {@link ZigBeeChannel#UNKNOWN} in the network
     */
    public ZigBeeChannel getCurrentBestChannel() {
        Map<ZigBeeChannel, List<Integer>> channelData = new TreeMap<ZigBeeChannel, List<Integer>>();
        ZigBeeChannel bestChannel = ZigBeeChannel.UNKNOWN;

        Long timeout = System.currentTimeMillis() - cachePeriod;
        Iterator<ChannelScanCacheRecord> scanIterator = scanCache.iterator();
        while (scanIterator.hasNext()) {
            ChannelScanCacheRecord scanRecord = scanIterator.next();

            // Check if the record is too old and remove
            if (scanRecord.getTime() < timeout) {
                scanIterator.remove();
                continue;
            }

            // Add all scan data into a per channel list for easier manipulation
            for (Map.Entry<ZigBeeChannel, Integer> channel : scanRecord.getScanRecord().entrySet()) {
                if (channelData.get(channel.getKey()) == null) {
                    channelData.put(channel.getKey(), new ArrayList<Integer>());
                }

                channelData.get(channel.getKey()).add(channel.getValue());
            }
        }

        int totalSamples = 0;
        int totalChannels = 0;

        int bestLevel = Integer.MAX_VALUE;
        StringBuilder builder = new StringBuilder(100);

        // Loop through every channel to find the best one
        for (Map.Entry<ZigBeeChannel, List<Integer>> channel : channelData.entrySet()) {
            totalChannels++;

            int cntLevel = 0;
            int avgLevel = 0;
            int minLevel = Integer.MAX_VALUE;
            int maxLevel = Integer.MIN_VALUE;
            for (Integer level : channel.getValue()) {
                cntLevel++;
                avgLevel += level;
                if (level < minLevel) {
                    minLevel = level;
                }
                if (level > maxLevel) {
                    maxLevel = level;
                }
            }
            int average = avgLevel / cntLevel;
            totalSamples += cntLevel;

            if (totalChannels > 1) {
                builder.append(", ");
            }
            builder.append(channel.getKey());
            builder.append("=[");
            builder.append(minLevel);
            builder.append('<');
            builder.append(average);
            builder.append('>');
            builder.append(maxLevel);
            builder.append(']');
            if (maxLevel >= unusableRssi) {
                builder.append('^');
            }
            if (!channelMask.containsChannel(channel.getKey())) {
                builder.append('*');
                continue;
            }

            if (maxLevel < unusableRssi && average < bestLevel) {
                bestLevel = average;
                bestChannel = channel.getKey();
            }
        }

        logger.debug("Best channel is {} with level {}. {} channels and {} samples checked: {}", bestChannel, bestLevel,
                totalChannels, totalSamples, builder.toString());
        return bestChannel;
    }

}

/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.zcl.ZclAttribute;
import com.zsmartsystems.zigbee.zcl.ZclCluster;
import com.zsmartsystems.zigbee.zcl.protocol.ZclClusterType;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * <b>Diagnostics</b> cluster implementation (<i>Cluster ID 0x0B05</i>).
 * <p>
 * The diagnostics cluster provides access to information regarding the operation of the
 * ZigBee stack over time. This information is useful to installers and other network
 * administrators who wish to know how a particular device is functioning on the network.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-26T21:33:25Z")
public class ZclDiagnosticsCluster extends ZclCluster {
    /**
     * The ZigBee Cluster Library Cluster ID
     */
    public static final int CLUSTER_ID = 0x0B05;

    /**
     * The ZigBee Cluster Library Cluster Name
     */
    public static final String CLUSTER_NAME = "Diagnostics";

    // Attribute constants
    /**
     * An attribute that is incremented each time the device resets. A reset is defined as any
     * time the device restarts. This is not the same as a reset to factory defaults, which
     * should clear this and all values.
     */
    public static final int ATTR_NUMBEROFRESETS = 0x0000;
    /**
     * This attribute keeps track of the number of writes to persistent memory. Each time that
     * the device stores a token in persistent memory it will increment this value.
     */
    public static final int ATTR_PERSISTENTMEMORYWRITES = 0x0001;
    /**
     * A counter that is incremented each time the MAC layer receives a broadcast.
     */
    public static final int ATTR_MACRXBCAST = 0x0100;
    /**
     * A counter that is incremented each time the MAC layer transmits a broadcast.
     */
    public static final int ATTR_MACTXBCAST = 0x0101;
    /**
     * A counter that is incremented each time the MAC layer receives a unicast.
     */
    public static final int ATTR_MACRXUCAST = 0x0102;
    /**
     * A counter that is incremented each time the MAC layer transmits a unicast.
     */
    public static final int ATTR_MACTXUCAST = 0x0103;
    public static final int ATTR_MACTXUCASTRETRY = 0x0104;
    public static final int ATTR_MACTXUCASTFAIL = 0x0105;
    public static final int ATTR_APSRXBCAST = 0x0106;
    public static final int ATTR_APSTXBCAST = 0x0107;
    public static final int ATTR_APSRXUCAST = 0x0108;
    public static final int ATTR_APSTXUCASTSUCCESS = 0x0109;
    public static final int ATTR_APSTXUCASTRETRY = 0x010A;
    public static final int ATTR_APSTXUCASTFAIL = 0x010B;
    public static final int ATTR_ROUTEDISCINITIATED = 0x010C;
    public static final int ATTR_NEIGHBORADDED = 0x010D;
    public static final int ATTR_NEIGHBORREMOVED = 0x010E;
    public static final int ATTR_NEIGHBORSTALE = 0x010F;
    public static final int ATTR_JOININDICATION = 0x0110;
    public static final int ATTR_CHILDMOVED = 0x0111;
    public static final int ATTR_NWKFCFAILURE = 0x0112;
    public static final int ATTR_APSFCFAILURE = 0x0113;
    public static final int ATTR_APSUNAUTHORIZEDKEY = 0x0114;
    public static final int ATTR_NWKDECRYPTFAILURES = 0x0115;
    public static final int ATTR_APSDECRYPTFAILURES = 0x0116;
    public static final int ATTR_PACKETBUFFERALLOCATEFAILURES = 0x0117;
    public static final int ATTR_RELAYEDUCAST = 0x0118;
    public static final int ATTR_PHYTOMACQUEUELIMITREACHED = 0x0119;
    public static final int ATTR_PACKETVALIDATEDROPCOUNT = 0x011A;
    public static final int ATTR_AVERAGEMACRETRYPERAPSMESSAGESENT = 0x011B;
    public static final int ATTR_LASTMESSAGELQI = 0x011C;
    public static final int ATTR_LASTMESSAGERSSI = 0x011D;

    @Override
    protected Map<Integer, ZclAttribute> initializeAttributes() {
        Map<Integer, ZclAttribute> attributeMap = new ConcurrentHashMap<>(32);

        attributeMap.put(ATTR_NUMBEROFRESETS, new ZclAttribute(ZclClusterType.DIAGNOSTICS, ATTR_NUMBEROFRESETS, "Number Of Resets", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_PERSISTENTMEMORYWRITES, new ZclAttribute(ZclClusterType.DIAGNOSTICS, ATTR_PERSISTENTMEMORYWRITES, "Persistent Memory Writes", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_MACRXBCAST, new ZclAttribute(ZclClusterType.DIAGNOSTICS, ATTR_MACRXBCAST, "MAC Rx Bcast", ZclDataType.UNSIGNED_32_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_MACTXBCAST, new ZclAttribute(ZclClusterType.DIAGNOSTICS, ATTR_MACTXBCAST, "MAC Tx Bcast", ZclDataType.UNSIGNED_32_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_MACRXUCAST, new ZclAttribute(ZclClusterType.DIAGNOSTICS, ATTR_MACRXUCAST, "MAC Rx Ucast", ZclDataType.UNSIGNED_32_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_MACTXUCAST, new ZclAttribute(ZclClusterType.DIAGNOSTICS, ATTR_MACTXUCAST, "MAC Tx Ucast", ZclDataType.UNSIGNED_32_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_MACTXUCASTRETRY, new ZclAttribute(ZclClusterType.DIAGNOSTICS, ATTR_MACTXUCASTRETRY, "MAC Tx Ucast Retry", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_MACTXUCASTFAIL, new ZclAttribute(ZclClusterType.DIAGNOSTICS, ATTR_MACTXUCASTFAIL, "MAC Tx Ucast Fail", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_APSRXBCAST, new ZclAttribute(ZclClusterType.DIAGNOSTICS, ATTR_APSRXBCAST, "APS Rx Bcast", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_APSTXBCAST, new ZclAttribute(ZclClusterType.DIAGNOSTICS, ATTR_APSTXBCAST, "APS Tx Bcast", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_APSRXUCAST, new ZclAttribute(ZclClusterType.DIAGNOSTICS, ATTR_APSRXUCAST, "APS Rx Ucast", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_APSTXUCASTSUCCESS, new ZclAttribute(ZclClusterType.DIAGNOSTICS, ATTR_APSTXUCASTSUCCESS, "APS Tx Ucast Success", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_APSTXUCASTRETRY, new ZclAttribute(ZclClusterType.DIAGNOSTICS, ATTR_APSTXUCASTRETRY, "APS Tx Ucast Retry", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_APSTXUCASTFAIL, new ZclAttribute(ZclClusterType.DIAGNOSTICS, ATTR_APSTXUCASTFAIL, "APS Tx Ucast Fail", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_ROUTEDISCINITIATED, new ZclAttribute(ZclClusterType.DIAGNOSTICS, ATTR_ROUTEDISCINITIATED, "Route Disc Initiated", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_NEIGHBORADDED, new ZclAttribute(ZclClusterType.DIAGNOSTICS, ATTR_NEIGHBORADDED, "Neighbor Added", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_NEIGHBORREMOVED, new ZclAttribute(ZclClusterType.DIAGNOSTICS, ATTR_NEIGHBORREMOVED, "Neighbor Removed", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_NEIGHBORSTALE, new ZclAttribute(ZclClusterType.DIAGNOSTICS, ATTR_NEIGHBORSTALE, "Neighbor Stale", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_JOININDICATION, new ZclAttribute(ZclClusterType.DIAGNOSTICS, ATTR_JOININDICATION, "Join Indication", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_CHILDMOVED, new ZclAttribute(ZclClusterType.DIAGNOSTICS, ATTR_CHILDMOVED, "Child Moved", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_NWKFCFAILURE, new ZclAttribute(ZclClusterType.DIAGNOSTICS, ATTR_NWKFCFAILURE, "NWK FC Failure", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_APSFCFAILURE, new ZclAttribute(ZclClusterType.DIAGNOSTICS, ATTR_APSFCFAILURE, "APS FC Failure", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_APSUNAUTHORIZEDKEY, new ZclAttribute(ZclClusterType.DIAGNOSTICS, ATTR_APSUNAUTHORIZEDKEY, "APS Unauthorized Key", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_NWKDECRYPTFAILURES, new ZclAttribute(ZclClusterType.DIAGNOSTICS, ATTR_NWKDECRYPTFAILURES, "NWK Decrypt Failures", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_APSDECRYPTFAILURES, new ZclAttribute(ZclClusterType.DIAGNOSTICS, ATTR_APSDECRYPTFAILURES, "APS Decrypt Failures", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_PACKETBUFFERALLOCATEFAILURES, new ZclAttribute(ZclClusterType.DIAGNOSTICS, ATTR_PACKETBUFFERALLOCATEFAILURES, "Packet Buffer Allocate Failures", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_RELAYEDUCAST, new ZclAttribute(ZclClusterType.DIAGNOSTICS, ATTR_RELAYEDUCAST, "Relayed Ucast", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_PHYTOMACQUEUELIMITREACHED, new ZclAttribute(ZclClusterType.DIAGNOSTICS, ATTR_PHYTOMACQUEUELIMITREACHED, "Phy To MAC Queue Limit Reached", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_PACKETVALIDATEDROPCOUNT, new ZclAttribute(ZclClusterType.DIAGNOSTICS, ATTR_PACKETVALIDATEDROPCOUNT, "Packet Validate Drop Count", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_AVERAGEMACRETRYPERAPSMESSAGESENT, new ZclAttribute(ZclClusterType.DIAGNOSTICS, ATTR_AVERAGEMACRETRYPERAPSMESSAGESENT, "Average MAC Retry Per APS Message Sent", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_LASTMESSAGELQI, new ZclAttribute(ZclClusterType.DIAGNOSTICS, ATTR_LASTMESSAGELQI, "Last Message LQI", ZclDataType.UNSIGNED_8_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_LASTMESSAGERSSI, new ZclAttribute(ZclClusterType.DIAGNOSTICS, ATTR_LASTMESSAGERSSI, "Last Message RSSI", ZclDataType.SIGNED_8_BIT_INTEGER, true, true, false, false));

        return attributeMap;
    }

    /**
     * Default constructor to create a Diagnostics cluster.
     *
     * @param zigbeeEndpoint the {@link ZigBeeEndpoint} this cluster is contained within
     */
    public ZclDiagnosticsCluster(final ZigBeeEndpoint zigbeeEndpoint) {
        super(zigbeeEndpoint, CLUSTER_ID, CLUSTER_NAME);
    }

    /**
     * Get the <i>Number Of Resets</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * An attribute that is incremented each time the device resets. A reset is defined as any
     * time the device restarts. This is not the same as a reset to factory defaults, which
     * should clear this and all values.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getNumberOfResetsAsync() {
        return read(attributes.get(ATTR_NUMBEROFRESETS));
    }

    /**
     * Synchronously get the <i>Number Of Resets</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * An attribute that is incremented each time the device resets. A reset is defined as any
     * time the device restarts. This is not the same as a reset to factory defaults, which
     * should clear this and all values.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getNumberOfResets(final long refreshPeriod) {
        if (attributes.get(ATTR_NUMBEROFRESETS).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_NUMBEROFRESETS).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_NUMBEROFRESETS));
    }

    /**
     * Set reporting for the <i>Number Of Resets</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * An attribute that is incremented each time the device resets. A reset is defined as any
     * time the device restarts. This is not the same as a reset to factory defaults, which
     * should clear this and all values.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setNumberOfResetsReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_NUMBEROFRESETS), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Persistent Memory Writes</i> attribute [attribute ID <b>0x0001</b>].
     * <p>
     * This attribute keeps track of the number of writes to persistent memory. Each time that
     * the device stores a token in persistent memory it will increment this value.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPersistentMemoryWritesAsync() {
        return read(attributes.get(ATTR_PERSISTENTMEMORYWRITES));
    }

    /**
     * Synchronously get the <i>Persistent Memory Writes</i> attribute [attribute ID <b>0x0001</b>].
     * <p>
     * This attribute keeps track of the number of writes to persistent memory. Each time that
     * the device stores a token in persistent memory it will increment this value.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getPersistentMemoryWrites(final long refreshPeriod) {
        if (attributes.get(ATTR_PERSISTENTMEMORYWRITES).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_PERSISTENTMEMORYWRITES).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_PERSISTENTMEMORYWRITES));
    }

    /**
     * Set reporting for the <i>Persistent Memory Writes</i> attribute [attribute ID <b>0x0001</b>].
     * <p>
     * This attribute keeps track of the number of writes to persistent memory. Each time that
     * the device stores a token in persistent memory it will increment this value.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setPersistentMemoryWritesReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_PERSISTENTMEMORYWRITES), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>MAC Rx Bcast</i> attribute [attribute ID <b>0x0100</b>].
     * <p>
     * A counter that is incremented each time the MAC layer receives a broadcast.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getMacRxBcastAsync() {
        return read(attributes.get(ATTR_MACRXBCAST));
    }

    /**
     * Synchronously get the <i>MAC Rx Bcast</i> attribute [attribute ID <b>0x0100</b>].
     * <p>
     * A counter that is incremented each time the MAC layer receives a broadcast.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getMacRxBcast(final long refreshPeriod) {
        if (attributes.get(ATTR_MACRXBCAST).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_MACRXBCAST).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_MACRXBCAST));
    }

    /**
     * Set reporting for the <i>MAC Rx Bcast</i> attribute [attribute ID <b>0x0100</b>].
     * <p>
     * A counter that is incremented each time the MAC layer receives a broadcast.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setMacRxBcastReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_MACRXBCAST), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>MAC Tx Bcast</i> attribute [attribute ID <b>0x0101</b>].
     * <p>
     * A counter that is incremented each time the MAC layer transmits a broadcast.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getMacTxBcastAsync() {
        return read(attributes.get(ATTR_MACTXBCAST));
    }

    /**
     * Synchronously get the <i>MAC Tx Bcast</i> attribute [attribute ID <b>0x0101</b>].
     * <p>
     * A counter that is incremented each time the MAC layer transmits a broadcast.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getMacTxBcast(final long refreshPeriod) {
        if (attributes.get(ATTR_MACTXBCAST).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_MACTXBCAST).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_MACTXBCAST));
    }

    /**
     * Set reporting for the <i>MAC Tx Bcast</i> attribute [attribute ID <b>0x0101</b>].
     * <p>
     * A counter that is incremented each time the MAC layer transmits a broadcast.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setMacTxBcastReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_MACTXBCAST), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>MAC Rx Ucast</i> attribute [attribute ID <b>0x0102</b>].
     * <p>
     * A counter that is incremented each time the MAC layer receives a unicast.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getMacRxUcastAsync() {
        return read(attributes.get(ATTR_MACRXUCAST));
    }

    /**
     * Synchronously get the <i>MAC Rx Ucast</i> attribute [attribute ID <b>0x0102</b>].
     * <p>
     * A counter that is incremented each time the MAC layer receives a unicast.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getMacRxUcast(final long refreshPeriod) {
        if (attributes.get(ATTR_MACRXUCAST).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_MACRXUCAST).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_MACRXUCAST));
    }

    /**
     * Set reporting for the <i>MAC Rx Ucast</i> attribute [attribute ID <b>0x0102</b>].
     * <p>
     * A counter that is incremented each time the MAC layer receives a unicast.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setMacRxUcastReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_MACRXUCAST), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>MAC Tx Ucast</i> attribute [attribute ID <b>0x0103</b>].
     * <p>
     * A counter that is incremented each time the MAC layer transmits a unicast.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getMacTxUcastAsync() {
        return read(attributes.get(ATTR_MACTXUCAST));
    }

    /**
     * Synchronously get the <i>MAC Tx Ucast</i> attribute [attribute ID <b>0x0103</b>].
     * <p>
     * A counter that is incremented each time the MAC layer transmits a unicast.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getMacTxUcast(final long refreshPeriod) {
        if (attributes.get(ATTR_MACTXUCAST).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_MACTXUCAST).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_MACTXUCAST));
    }

    /**
     * Set reporting for the <i>MAC Tx Ucast</i> attribute [attribute ID <b>0x0103</b>].
     * <p>
     * A counter that is incremented each time the MAC layer transmits a unicast.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setMacTxUcastReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_MACTXUCAST), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>MAC Tx Ucast Retry</i> attribute [attribute ID <b>0x0104</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getMacTxUcastRetryAsync() {
        return read(attributes.get(ATTR_MACTXUCASTRETRY));
    }

    /**
     * Synchronously get the <i>MAC Tx Ucast Retry</i> attribute [attribute ID <b>0x0104</b>].
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getMacTxUcastRetry(final long refreshPeriod) {
        if (attributes.get(ATTR_MACTXUCASTRETRY).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_MACTXUCASTRETRY).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_MACTXUCASTRETRY));
    }

    /**
     * Set reporting for the <i>MAC Tx Ucast Retry</i> attribute [attribute ID <b>0x0104</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setMacTxUcastRetryReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_MACTXUCASTRETRY), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>MAC Tx Ucast Fail</i> attribute [attribute ID <b>0x0105</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getMacTxUcastFailAsync() {
        return read(attributes.get(ATTR_MACTXUCASTFAIL));
    }

    /**
     * Synchronously get the <i>MAC Tx Ucast Fail</i> attribute [attribute ID <b>0x0105</b>].
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getMacTxUcastFail(final long refreshPeriod) {
        if (attributes.get(ATTR_MACTXUCASTFAIL).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_MACTXUCASTFAIL).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_MACTXUCASTFAIL));
    }

    /**
     * Set reporting for the <i>MAC Tx Ucast Fail</i> attribute [attribute ID <b>0x0105</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setMacTxUcastFailReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_MACTXUCASTFAIL), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>APS Rx Bcast</i> attribute [attribute ID <b>0x0106</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getApsRxBcastAsync() {
        return read(attributes.get(ATTR_APSRXBCAST));
    }

    /**
     * Synchronously get the <i>APS Rx Bcast</i> attribute [attribute ID <b>0x0106</b>].
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getApsRxBcast(final long refreshPeriod) {
        if (attributes.get(ATTR_APSRXBCAST).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_APSRXBCAST).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_APSRXBCAST));
    }

    /**
     * Set reporting for the <i>APS Rx Bcast</i> attribute [attribute ID <b>0x0106</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setApsRxBcastReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_APSRXBCAST), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>APS Tx Bcast</i> attribute [attribute ID <b>0x0107</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getApsTxBcastAsync() {
        return read(attributes.get(ATTR_APSTXBCAST));
    }

    /**
     * Synchronously get the <i>APS Tx Bcast</i> attribute [attribute ID <b>0x0107</b>].
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getApsTxBcast(final long refreshPeriod) {
        if (attributes.get(ATTR_APSTXBCAST).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_APSTXBCAST).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_APSTXBCAST));
    }

    /**
     * Set reporting for the <i>APS Tx Bcast</i> attribute [attribute ID <b>0x0107</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setApsTxBcastReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_APSTXBCAST), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>APS Rx Ucast</i> attribute [attribute ID <b>0x0108</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getApsRxUcastAsync() {
        return read(attributes.get(ATTR_APSRXUCAST));
    }

    /**
     * Synchronously get the <i>APS Rx Ucast</i> attribute [attribute ID <b>0x0108</b>].
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getApsRxUcast(final long refreshPeriod) {
        if (attributes.get(ATTR_APSRXUCAST).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_APSRXUCAST).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_APSRXUCAST));
    }

    /**
     * Set reporting for the <i>APS Rx Ucast</i> attribute [attribute ID <b>0x0108</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setApsRxUcastReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_APSRXUCAST), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>APS Tx Ucast Success</i> attribute [attribute ID <b>0x0109</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getApsTxUcastSuccessAsync() {
        return read(attributes.get(ATTR_APSTXUCASTSUCCESS));
    }

    /**
     * Synchronously get the <i>APS Tx Ucast Success</i> attribute [attribute ID <b>0x0109</b>].
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getApsTxUcastSuccess(final long refreshPeriod) {
        if (attributes.get(ATTR_APSTXUCASTSUCCESS).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_APSTXUCASTSUCCESS).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_APSTXUCASTSUCCESS));
    }

    /**
     * Set reporting for the <i>APS Tx Ucast Success</i> attribute [attribute ID <b>0x0109</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setApsTxUcastSuccessReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_APSTXUCASTSUCCESS), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>APS Tx Ucast Retry</i> attribute [attribute ID <b>0x010A</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getApsTxUcastRetryAsync() {
        return read(attributes.get(ATTR_APSTXUCASTRETRY));
    }

    /**
     * Synchronously get the <i>APS Tx Ucast Retry</i> attribute [attribute ID <b>0x010A</b>].
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getApsTxUcastRetry(final long refreshPeriod) {
        if (attributes.get(ATTR_APSTXUCASTRETRY).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_APSTXUCASTRETRY).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_APSTXUCASTRETRY));
    }

    /**
     * Set reporting for the <i>APS Tx Ucast Retry</i> attribute [attribute ID <b>0x010A</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setApsTxUcastRetryReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_APSTXUCASTRETRY), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>APS Tx Ucast Fail</i> attribute [attribute ID <b>0x010B</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getApsTxUcastFailAsync() {
        return read(attributes.get(ATTR_APSTXUCASTFAIL));
    }

    /**
     * Synchronously get the <i>APS Tx Ucast Fail</i> attribute [attribute ID <b>0x010B</b>].
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getApsTxUcastFail(final long refreshPeriod) {
        if (attributes.get(ATTR_APSTXUCASTFAIL).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_APSTXUCASTFAIL).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_APSTXUCASTFAIL));
    }

    /**
     * Set reporting for the <i>APS Tx Ucast Fail</i> attribute [attribute ID <b>0x010B</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setApsTxUcastFailReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_APSTXUCASTFAIL), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Route Disc Initiated</i> attribute [attribute ID <b>0x010C</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getRouteDiscInitiatedAsync() {
        return read(attributes.get(ATTR_ROUTEDISCINITIATED));
    }

    /**
     * Synchronously get the <i>Route Disc Initiated</i> attribute [attribute ID <b>0x010C</b>].
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getRouteDiscInitiated(final long refreshPeriod) {
        if (attributes.get(ATTR_ROUTEDISCINITIATED).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_ROUTEDISCINITIATED).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_ROUTEDISCINITIATED));
    }

    /**
     * Set reporting for the <i>Route Disc Initiated</i> attribute [attribute ID <b>0x010C</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setRouteDiscInitiatedReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_ROUTEDISCINITIATED), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Neighbor Added</i> attribute [attribute ID <b>0x010D</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getNeighborAddedAsync() {
        return read(attributes.get(ATTR_NEIGHBORADDED));
    }

    /**
     * Synchronously get the <i>Neighbor Added</i> attribute [attribute ID <b>0x010D</b>].
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getNeighborAdded(final long refreshPeriod) {
        if (attributes.get(ATTR_NEIGHBORADDED).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_NEIGHBORADDED).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_NEIGHBORADDED));
    }

    /**
     * Set reporting for the <i>Neighbor Added</i> attribute [attribute ID <b>0x010D</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setNeighborAddedReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_NEIGHBORADDED), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Neighbor Removed</i> attribute [attribute ID <b>0x010E</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getNeighborRemovedAsync() {
        return read(attributes.get(ATTR_NEIGHBORREMOVED));
    }

    /**
     * Synchronously get the <i>Neighbor Removed</i> attribute [attribute ID <b>0x010E</b>].
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getNeighborRemoved(final long refreshPeriod) {
        if (attributes.get(ATTR_NEIGHBORREMOVED).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_NEIGHBORREMOVED).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_NEIGHBORREMOVED));
    }

    /**
     * Set reporting for the <i>Neighbor Removed</i> attribute [attribute ID <b>0x010E</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setNeighborRemovedReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_NEIGHBORREMOVED), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Neighbor Stale</i> attribute [attribute ID <b>0x010F</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getNeighborStaleAsync() {
        return read(attributes.get(ATTR_NEIGHBORSTALE));
    }

    /**
     * Synchronously get the <i>Neighbor Stale</i> attribute [attribute ID <b>0x010F</b>].
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getNeighborStale(final long refreshPeriod) {
        if (attributes.get(ATTR_NEIGHBORSTALE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_NEIGHBORSTALE).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_NEIGHBORSTALE));
    }

    /**
     * Set reporting for the <i>Neighbor Stale</i> attribute [attribute ID <b>0x010F</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setNeighborStaleReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_NEIGHBORSTALE), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Join Indication</i> attribute [attribute ID <b>0x0110</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getJoinIndicationAsync() {
        return read(attributes.get(ATTR_JOININDICATION));
    }

    /**
     * Synchronously get the <i>Join Indication</i> attribute [attribute ID <b>0x0110</b>].
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getJoinIndication(final long refreshPeriod) {
        if (attributes.get(ATTR_JOININDICATION).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_JOININDICATION).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_JOININDICATION));
    }

    /**
     * Set reporting for the <i>Join Indication</i> attribute [attribute ID <b>0x0110</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setJoinIndicationReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_JOININDICATION), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Child Moved</i> attribute [attribute ID <b>0x0111</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getChildMovedAsync() {
        return read(attributes.get(ATTR_CHILDMOVED));
    }

    /**
     * Synchronously get the <i>Child Moved</i> attribute [attribute ID <b>0x0111</b>].
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getChildMoved(final long refreshPeriod) {
        if (attributes.get(ATTR_CHILDMOVED).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_CHILDMOVED).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_CHILDMOVED));
    }

    /**
     * Set reporting for the <i>Child Moved</i> attribute [attribute ID <b>0x0111</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setChildMovedReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_CHILDMOVED), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>NWK FC Failure</i> attribute [attribute ID <b>0x0112</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getNwkFcFailureAsync() {
        return read(attributes.get(ATTR_NWKFCFAILURE));
    }

    /**
     * Synchronously get the <i>NWK FC Failure</i> attribute [attribute ID <b>0x0112</b>].
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getNwkFcFailure(final long refreshPeriod) {
        if (attributes.get(ATTR_NWKFCFAILURE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_NWKFCFAILURE).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_NWKFCFAILURE));
    }

    /**
     * Set reporting for the <i>NWK FC Failure</i> attribute [attribute ID <b>0x0112</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setNwkFcFailureReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_NWKFCFAILURE), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>APS FC Failure</i> attribute [attribute ID <b>0x0113</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getApsFcFailureAsync() {
        return read(attributes.get(ATTR_APSFCFAILURE));
    }

    /**
     * Synchronously get the <i>APS FC Failure</i> attribute [attribute ID <b>0x0113</b>].
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getApsFcFailure(final long refreshPeriod) {
        if (attributes.get(ATTR_APSFCFAILURE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_APSFCFAILURE).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_APSFCFAILURE));
    }

    /**
     * Set reporting for the <i>APS FC Failure</i> attribute [attribute ID <b>0x0113</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setApsFcFailureReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_APSFCFAILURE), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>APS Unauthorized Key</i> attribute [attribute ID <b>0x0114</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getApsUnauthorizedKeyAsync() {
        return read(attributes.get(ATTR_APSUNAUTHORIZEDKEY));
    }

    /**
     * Synchronously get the <i>APS Unauthorized Key</i> attribute [attribute ID <b>0x0114</b>].
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getApsUnauthorizedKey(final long refreshPeriod) {
        if (attributes.get(ATTR_APSUNAUTHORIZEDKEY).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_APSUNAUTHORIZEDKEY).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_APSUNAUTHORIZEDKEY));
    }

    /**
     * Set reporting for the <i>APS Unauthorized Key</i> attribute [attribute ID <b>0x0114</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setApsUnauthorizedKeyReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_APSUNAUTHORIZEDKEY), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>NWK Decrypt Failures</i> attribute [attribute ID <b>0x0115</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getNwkDecryptFailuresAsync() {
        return read(attributes.get(ATTR_NWKDECRYPTFAILURES));
    }

    /**
     * Synchronously get the <i>NWK Decrypt Failures</i> attribute [attribute ID <b>0x0115</b>].
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getNwkDecryptFailures(final long refreshPeriod) {
        if (attributes.get(ATTR_NWKDECRYPTFAILURES).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_NWKDECRYPTFAILURES).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_NWKDECRYPTFAILURES));
    }

    /**
     * Set reporting for the <i>NWK Decrypt Failures</i> attribute [attribute ID <b>0x0115</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setNwkDecryptFailuresReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_NWKDECRYPTFAILURES), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>APS Decrypt Failures</i> attribute [attribute ID <b>0x0116</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getApsDecryptFailuresAsync() {
        return read(attributes.get(ATTR_APSDECRYPTFAILURES));
    }

    /**
     * Synchronously get the <i>APS Decrypt Failures</i> attribute [attribute ID <b>0x0116</b>].
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getApsDecryptFailures(final long refreshPeriod) {
        if (attributes.get(ATTR_APSDECRYPTFAILURES).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_APSDECRYPTFAILURES).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_APSDECRYPTFAILURES));
    }

    /**
     * Set reporting for the <i>APS Decrypt Failures</i> attribute [attribute ID <b>0x0116</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setApsDecryptFailuresReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_APSDECRYPTFAILURES), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Packet Buffer Allocate Failures</i> attribute [attribute ID <b>0x0117</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPacketBufferAllocateFailuresAsync() {
        return read(attributes.get(ATTR_PACKETBUFFERALLOCATEFAILURES));
    }

    /**
     * Synchronously get the <i>Packet Buffer Allocate Failures</i> attribute [attribute ID <b>0x0117</b>].
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getPacketBufferAllocateFailures(final long refreshPeriod) {
        if (attributes.get(ATTR_PACKETBUFFERALLOCATEFAILURES).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_PACKETBUFFERALLOCATEFAILURES).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_PACKETBUFFERALLOCATEFAILURES));
    }

    /**
     * Set reporting for the <i>Packet Buffer Allocate Failures</i> attribute [attribute ID <b>0x0117</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setPacketBufferAllocateFailuresReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_PACKETBUFFERALLOCATEFAILURES), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Relayed Ucast</i> attribute [attribute ID <b>0x0118</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getRelayedUcastAsync() {
        return read(attributes.get(ATTR_RELAYEDUCAST));
    }

    /**
     * Synchronously get the <i>Relayed Ucast</i> attribute [attribute ID <b>0x0118</b>].
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getRelayedUcast(final long refreshPeriod) {
        if (attributes.get(ATTR_RELAYEDUCAST).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_RELAYEDUCAST).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_RELAYEDUCAST));
    }

    /**
     * Set reporting for the <i>Relayed Ucast</i> attribute [attribute ID <b>0x0118</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setRelayedUcastReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_RELAYEDUCAST), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Phy To MAC Queue Limit Reached</i> attribute [attribute ID <b>0x0119</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPhyToMacQueueLimitReachedAsync() {
        return read(attributes.get(ATTR_PHYTOMACQUEUELIMITREACHED));
    }

    /**
     * Synchronously get the <i>Phy To MAC Queue Limit Reached</i> attribute [attribute ID <b>0x0119</b>].
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getPhyToMacQueueLimitReached(final long refreshPeriod) {
        if (attributes.get(ATTR_PHYTOMACQUEUELIMITREACHED).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_PHYTOMACQUEUELIMITREACHED).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_PHYTOMACQUEUELIMITREACHED));
    }

    /**
     * Set reporting for the <i>Phy To MAC Queue Limit Reached</i> attribute [attribute ID <b>0x0119</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setPhyToMacQueueLimitReachedReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_PHYTOMACQUEUELIMITREACHED), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Packet Validate Drop Count</i> attribute [attribute ID <b>0x011A</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPacketValidateDropCountAsync() {
        return read(attributes.get(ATTR_PACKETVALIDATEDROPCOUNT));
    }

    /**
     * Synchronously get the <i>Packet Validate Drop Count</i> attribute [attribute ID <b>0x011A</b>].
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getPacketValidateDropCount(final long refreshPeriod) {
        if (attributes.get(ATTR_PACKETVALIDATEDROPCOUNT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_PACKETVALIDATEDROPCOUNT).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_PACKETVALIDATEDROPCOUNT));
    }

    /**
     * Set reporting for the <i>Packet Validate Drop Count</i> attribute [attribute ID <b>0x011A</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setPacketValidateDropCountReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_PACKETVALIDATEDROPCOUNT), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Average MAC Retry Per APS Message Sent</i> attribute [attribute ID <b>0x011B</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getAverageMacRetryPerApsMessageSentAsync() {
        return read(attributes.get(ATTR_AVERAGEMACRETRYPERAPSMESSAGESENT));
    }

    /**
     * Synchronously get the <i>Average MAC Retry Per APS Message Sent</i> attribute [attribute ID <b>0x011B</b>].
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getAverageMacRetryPerApsMessageSent(final long refreshPeriod) {
        if (attributes.get(ATTR_AVERAGEMACRETRYPERAPSMESSAGESENT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_AVERAGEMACRETRYPERAPSMESSAGESENT).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_AVERAGEMACRETRYPERAPSMESSAGESENT));
    }

    /**
     * Set reporting for the <i>Average MAC Retry Per APS Message Sent</i> attribute [attribute ID <b>0x011B</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setAverageMacRetryPerApsMessageSentReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_AVERAGEMACRETRYPERAPSMESSAGESENT), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Last Message LQI</i> attribute [attribute ID <b>0x011C</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getLastMessageLqiAsync() {
        return read(attributes.get(ATTR_LASTMESSAGELQI));
    }

    /**
     * Synchronously get the <i>Last Message LQI</i> attribute [attribute ID <b>0x011C</b>].
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getLastMessageLqi(final long refreshPeriod) {
        if (attributes.get(ATTR_LASTMESSAGELQI).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_LASTMESSAGELQI).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_LASTMESSAGELQI));
    }

    /**
     * Set reporting for the <i>Last Message LQI</i> attribute [attribute ID <b>0x011C</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setLastMessageLqiReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_LASTMESSAGELQI), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Last Message RSSI</i> attribute [attribute ID <b>0x011D</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getLastMessageRssiAsync() {
        return read(attributes.get(ATTR_LASTMESSAGERSSI));
    }

    /**
     * Synchronously get the <i>Last Message RSSI</i> attribute [attribute ID <b>0x011D</b>].
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getLastMessageRssi(final long refreshPeriod) {
        if (attributes.get(ATTR_LASTMESSAGERSSI).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_LASTMESSAGERSSI).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_LASTMESSAGERSSI));
    }

    /**
     * Set reporting for the <i>Last Message RSSI</i> attribute [attribute ID <b>0x011D</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setLastMessageRssiReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_LASTMESSAGERSSI), minInterval, maxInterval, reportableChange);
    }
}

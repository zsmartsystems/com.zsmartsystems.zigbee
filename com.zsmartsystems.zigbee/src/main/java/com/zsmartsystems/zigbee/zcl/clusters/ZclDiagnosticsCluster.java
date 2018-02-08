/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.zcl.ZclAttribute;
import com.zsmartsystems.zigbee.zcl.ZclCluster;
import com.zsmartsystems.zigbee.zcl.protocol.ZclClusterType;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import java.util.Calendar;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;

/**
 * <b>Diagnostics</b> cluster implementation (<i>Cluster ID 0x0B05</i>).
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
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
     */
    public static final int ATTR_MACRXBCAST = 0x0100;
    /**
     */
    public static final int ATTR_MACTXBCAST = 0x0101;
    /**
     */
    public static final int ATTR_MACRXUCAST = 0x0102;
    /**
     */
    public static final int ATTR_MACTXUCAST = 0x0103;
    /**
     */
    public static final int ATTR_MACTXUCASTRETRY = 0x0104;
    /**
     */
    public static final int ATTR_MACTXUCASTFAIL = 0x0105;
    /**
     */
    public static final int ATTR_APSRXBCAST = 0x0106;
    /**
     */
    public static final int ATTR_APSTXBCAST = 0x0107;
    /**
     */
    public static final int ATTR_APSRXUCAST = 0x0108;
    /**
     */
    public static final int ATTR_APSTXUCASTSUCCESS = 0x0109;
    /**
     */
    public static final int ATTR_APSTXUCASTRETRY = 0x010A;
    /**
     */
    public static final int ATTR_APSTXUCASTFAIL = 0x010B;
    /**
     */
    public static final int ATTR_ROUTEDISCINITIATED = 0x010C;
    /**
     */
    public static final int ATTR_NEIGHBORADDED = 0x010D;
    /**
     */
    public static final int ATTR_NEIGHBORREMOVED = 0x010E;
    /**
     */
    public static final int ATTR_NEIGHBORSTALE = 0x010F;
    /**
     */
    public static final int ATTR_JOININDICATION = 0x0110;
    /**
     */
    public static final int ATTR_CHILDMOVED = 0x0111;
    /**
     */
    public static final int ATTR_NWKFCFAILURE = 0x0112;
    /**
     */
    public static final int ATTR_APSFCFAILURE = 0x0113;
    /**
     */
    public static final int ATTR_APSUNAUTHORIZEDKEY = 0x0114;
    /**
     */
    public static final int ATTR_NWKDECRYPTFAILURES = 0x0115;
    /**
     */
    public static final int ATTR_APSDECRYPTFAILURES = 0x0116;
    /**
     */
    public static final int ATTR_PACKETBUFFERALLOCATEFAILURES = 0x0117;
    /**
     */
    public static final int ATTR_RELAYEDUCAST = 0x0118;
    /**
     */
    public static final int ATTR_PHYTOMACQUEUELIMITREACHED = 0x0119;
    /**
     */
    public static final int ATTR_PACKETVALIDATEDROPCOUNT = 0x011A;
    /**
     */
    public static final int ATTR_AVERAGEMACRETRYPERAPSMESSAGESENT = 0x011B;
    /**
     */
    public static final int ATTR_LASTMESSAGELQI = 0x011C;
    /**
     */
    public static final int ATTR_LASTMESSAGERSSI = 0x011D;

    // Attribute initialisation
    protected Map<Integer, ZclAttribute> initializeAttributes() {
        Map<Integer, ZclAttribute> attributeMap = new ConcurrentHashMap<Integer, ZclAttribute>(30);

        attributeMap.put(ATTR_MACRXBCAST, new ZclAttribute(ZclClusterType.DIAGNOSTICS, ATTR_MACRXBCAST, "MacRxBcast", ZclDataType.UNSIGNED_32_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_MACTXBCAST, new ZclAttribute(ZclClusterType.DIAGNOSTICS, ATTR_MACTXBCAST, "MacTxBcast", ZclDataType.UNSIGNED_32_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_MACRXUCAST, new ZclAttribute(ZclClusterType.DIAGNOSTICS, ATTR_MACRXUCAST, "MacRxUcast", ZclDataType.UNSIGNED_32_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_MACTXUCAST, new ZclAttribute(ZclClusterType.DIAGNOSTICS, ATTR_MACTXUCAST, "MacTxUcast", ZclDataType.UNSIGNED_32_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_MACTXUCASTRETRY, new ZclAttribute(ZclClusterType.DIAGNOSTICS, ATTR_MACTXUCASTRETRY, "MacTxUcastRetry", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_MACTXUCASTFAIL, new ZclAttribute(ZclClusterType.DIAGNOSTICS, ATTR_MACTXUCASTFAIL, "MacTxUcastFail", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_APSRXBCAST, new ZclAttribute(ZclClusterType.DIAGNOSTICS, ATTR_APSRXBCAST, "APSRxBcast", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_APSTXBCAST, new ZclAttribute(ZclClusterType.DIAGNOSTICS, ATTR_APSTXBCAST, "APSTxBcast", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_APSRXUCAST, new ZclAttribute(ZclClusterType.DIAGNOSTICS, ATTR_APSRXUCAST, "APSRxUcast", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_APSTXUCASTSUCCESS, new ZclAttribute(ZclClusterType.DIAGNOSTICS, ATTR_APSTXUCASTSUCCESS, "APSTxUcastSuccess", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_APSTXUCASTRETRY, new ZclAttribute(ZclClusterType.DIAGNOSTICS, ATTR_APSTXUCASTRETRY, "APSTxUcastRetry", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_APSTXUCASTFAIL, new ZclAttribute(ZclClusterType.DIAGNOSTICS, ATTR_APSTXUCASTFAIL, "APSTxUcastFail", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_ROUTEDISCINITIATED, new ZclAttribute(ZclClusterType.DIAGNOSTICS, ATTR_ROUTEDISCINITIATED, "RouteDiscInitiated", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_NEIGHBORADDED, new ZclAttribute(ZclClusterType.DIAGNOSTICS, ATTR_NEIGHBORADDED, "NeighborAdded", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_NEIGHBORREMOVED, new ZclAttribute(ZclClusterType.DIAGNOSTICS, ATTR_NEIGHBORREMOVED, "NeighborRemoved", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_NEIGHBORSTALE, new ZclAttribute(ZclClusterType.DIAGNOSTICS, ATTR_NEIGHBORSTALE, "NeighborStale", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_JOININDICATION, new ZclAttribute(ZclClusterType.DIAGNOSTICS, ATTR_JOININDICATION, "JoinIndication", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_CHILDMOVED, new ZclAttribute(ZclClusterType.DIAGNOSTICS, ATTR_CHILDMOVED, "ChildMoved", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_NWKFCFAILURE, new ZclAttribute(ZclClusterType.DIAGNOSTICS, ATTR_NWKFCFAILURE, "NWKFCFailure", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_APSFCFAILURE, new ZclAttribute(ZclClusterType.DIAGNOSTICS, ATTR_APSFCFAILURE, "APSFCFailure", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_APSUNAUTHORIZEDKEY, new ZclAttribute(ZclClusterType.DIAGNOSTICS, ATTR_APSUNAUTHORIZEDKEY, "APSUnauthorizedKey", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_NWKDECRYPTFAILURES, new ZclAttribute(ZclClusterType.DIAGNOSTICS, ATTR_NWKDECRYPTFAILURES, "NWKDecryptFailures", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_APSDECRYPTFAILURES, new ZclAttribute(ZclClusterType.DIAGNOSTICS, ATTR_APSDECRYPTFAILURES, "APSDecryptFailures", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_PACKETBUFFERALLOCATEFAILURES, new ZclAttribute(ZclClusterType.DIAGNOSTICS, ATTR_PACKETBUFFERALLOCATEFAILURES, "PacketBufferAllocateFailures", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_RELAYEDUCAST, new ZclAttribute(ZclClusterType.DIAGNOSTICS, ATTR_RELAYEDUCAST, "RelayedUcast", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_PHYTOMACQUEUELIMITREACHED, new ZclAttribute(ZclClusterType.DIAGNOSTICS, ATTR_PHYTOMACQUEUELIMITREACHED, "PhytoMACqueuelimitreached", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_PACKETVALIDATEDROPCOUNT, new ZclAttribute(ZclClusterType.DIAGNOSTICS, ATTR_PACKETVALIDATEDROPCOUNT, "PacketValidatedropcount", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_AVERAGEMACRETRYPERAPSMESSAGESENT, new ZclAttribute(ZclClusterType.DIAGNOSTICS, ATTR_AVERAGEMACRETRYPERAPSMESSAGESENT, "AverageMACRetryPerAPSMessageSent", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_LASTMESSAGELQI, new ZclAttribute(ZclClusterType.DIAGNOSTICS, ATTR_LASTMESSAGELQI, "LastMessageLQI", ZclDataType.UNSIGNED_8_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_LASTMESSAGERSSI, new ZclAttribute(ZclClusterType.DIAGNOSTICS, ATTR_LASTMESSAGERSSI, "LastMessageRSSI", ZclDataType.SIGNED_8_BIT_INTEGER, true, true, false, false));

        return attributeMap;
    }

    /**
     * Default constructor to create a Diagnostics cluster.
     *
     * @param zigbeeManager {@link ZigBeeNetworkManager}
     * @param zigbeeEndpoint the {@link ZigBeeEndpoint}
     */
    public ZclDiagnosticsCluster(final ZigBeeNetworkManager zigbeeManager, final ZigBeeEndpoint zigbeeEndpoint) {
        super(zigbeeManager, zigbeeEndpoint, CLUSTER_ID, CLUSTER_NAME);
    }


    /**
     * Get the <i>MacRxBcast</i> attribute [attribute ID <b>256</b>].
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
     * Synchronously get the <i>MacRxBcast</i> attribute [attribute ID <b>256</b>].
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
     * Get the <i>MacTxBcast</i> attribute [attribute ID <b>257</b>].
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
     * Synchronously get the <i>MacTxBcast</i> attribute [attribute ID <b>257</b>].
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
     * Get the <i>MacRxUcast</i> attribute [attribute ID <b>258</b>].
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
     * Synchronously get the <i>MacRxUcast</i> attribute [attribute ID <b>258</b>].
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
     * Get the <i>MacTxUcast</i> attribute [attribute ID <b>259</b>].
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
     * Synchronously get the <i>MacTxUcast</i> attribute [attribute ID <b>259</b>].
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
     * Get the <i>MacTxUcastRetry</i> attribute [attribute ID <b>260</b>].
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
     * Synchronously get the <i>MacTxUcastRetry</i> attribute [attribute ID <b>260</b>].
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
     * Get the <i>MacTxUcastFail</i> attribute [attribute ID <b>261</b>].
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
     * Synchronously get the <i>MacTxUcastFail</i> attribute [attribute ID <b>261</b>].
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
     * Get the <i>APSRxBcast</i> attribute [attribute ID <b>262</b>].
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
     * Synchronously get the <i>APSRxBcast</i> attribute [attribute ID <b>262</b>].
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
     * Get the <i>APSTxBcast</i> attribute [attribute ID <b>263</b>].
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
     * Synchronously get the <i>APSTxBcast</i> attribute [attribute ID <b>263</b>].
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
     * Get the <i>APSRxUcast</i> attribute [attribute ID <b>264</b>].
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
     * Synchronously get the <i>APSRxUcast</i> attribute [attribute ID <b>264</b>].
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
     * Get the <i>APSTxUcastSuccess</i> attribute [attribute ID <b>265</b>].
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
     * Synchronously get the <i>APSTxUcastSuccess</i> attribute [attribute ID <b>265</b>].
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
     * Get the <i>APSTxUcastRetry</i> attribute [attribute ID <b>266</b>].
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
     * Synchronously get the <i>APSTxUcastRetry</i> attribute [attribute ID <b>266</b>].
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
     * Get the <i>APSTxUcastFail</i> attribute [attribute ID <b>267</b>].
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
     * Synchronously get the <i>APSTxUcastFail</i> attribute [attribute ID <b>267</b>].
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
     * Get the <i>RouteDiscInitiated</i> attribute [attribute ID <b>268</b>].
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
     * Synchronously get the <i>RouteDiscInitiated</i> attribute [attribute ID <b>268</b>].
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
     * Get the <i>NeighborAdded</i> attribute [attribute ID <b>269</b>].
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
     * Synchronously get the <i>NeighborAdded</i> attribute [attribute ID <b>269</b>].
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
     * Get the <i>NeighborRemoved</i> attribute [attribute ID <b>270</b>].
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
     * Synchronously get the <i>NeighborRemoved</i> attribute [attribute ID <b>270</b>].
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
     * Get the <i>NeighborStale</i> attribute [attribute ID <b>271</b>].
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
     * Synchronously get the <i>NeighborStale</i> attribute [attribute ID <b>271</b>].
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
     * Get the <i>JoinIndication</i> attribute [attribute ID <b>272</b>].
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
     * Synchronously get the <i>JoinIndication</i> attribute [attribute ID <b>272</b>].
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
     * Get the <i>ChildMoved</i> attribute [attribute ID <b>273</b>].
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
     * Synchronously get the <i>ChildMoved</i> attribute [attribute ID <b>273</b>].
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
     * Get the <i>NWKFCFailure</i> attribute [attribute ID <b>274</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getNwkfcFailureAsync() {
        return read(attributes.get(ATTR_NWKFCFAILURE));
    }


    /**
     * Synchronously get the <i>NWKFCFailure</i> attribute [attribute ID <b>274</b>].
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
    public Integer getNwkfcFailure(final long refreshPeriod) {
        if (attributes.get(ATTR_NWKFCFAILURE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_NWKFCFAILURE).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_NWKFCFAILURE));
    }

    /**
     * Get the <i>APSFCFailure</i> attribute [attribute ID <b>275</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getApsfcFailureAsync() {
        return read(attributes.get(ATTR_APSFCFAILURE));
    }


    /**
     * Synchronously get the <i>APSFCFailure</i> attribute [attribute ID <b>275</b>].
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
    public Integer getApsfcFailure(final long refreshPeriod) {
        if (attributes.get(ATTR_APSFCFAILURE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_APSFCFAILURE).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_APSFCFAILURE));
    }

    /**
     * Get the <i>APSUnauthorizedKey</i> attribute [attribute ID <b>276</b>].
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
     * Synchronously get the <i>APSUnauthorizedKey</i> attribute [attribute ID <b>276</b>].
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
     * Get the <i>NWKDecryptFailures</i> attribute [attribute ID <b>277</b>].
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
     * Synchronously get the <i>NWKDecryptFailures</i> attribute [attribute ID <b>277</b>].
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
     * Get the <i>APSDecryptFailures</i> attribute [attribute ID <b>278</b>].
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
     * Synchronously get the <i>APSDecryptFailures</i> attribute [attribute ID <b>278</b>].
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
     * Get the <i>PacketBufferAllocateFailures</i> attribute [attribute ID <b>279</b>].
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
     * Synchronously get the <i>PacketBufferAllocateFailures</i> attribute [attribute ID <b>279</b>].
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
     * Get the <i>RelayedUcast</i> attribute [attribute ID <b>280</b>].
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
     * Synchronously get the <i>RelayedUcast</i> attribute [attribute ID <b>280</b>].
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
     * Get the <i>PhytoMACqueuelimitreached</i> attribute [attribute ID <b>281</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPhytoMaCqueuelimitreachedAsync() {
        return read(attributes.get(ATTR_PHYTOMACQUEUELIMITREACHED));
    }


    /**
     * Synchronously get the <i>PhytoMACqueuelimitreached</i> attribute [attribute ID <b>281</b>].
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
    public Integer getPhytoMaCqueuelimitreached(final long refreshPeriod) {
        if (attributes.get(ATTR_PHYTOMACQUEUELIMITREACHED).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_PHYTOMACQUEUELIMITREACHED).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_PHYTOMACQUEUELIMITREACHED));
    }

    /**
     * Get the <i>PacketValidatedropcount</i> attribute [attribute ID <b>282</b>].
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getPacketValidatedropcountAsync() {
        return read(attributes.get(ATTR_PACKETVALIDATEDROPCOUNT));
    }


    /**
     * Synchronously get the <i>PacketValidatedropcount</i> attribute [attribute ID <b>282</b>].
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
    public Integer getPacketValidatedropcount(final long refreshPeriod) {
        if (attributes.get(ATTR_PACKETVALIDATEDROPCOUNT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_PACKETVALIDATEDROPCOUNT).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_PACKETVALIDATEDROPCOUNT));
    }

    /**
     * Get the <i>AverageMACRetryPerAPSMessageSent</i> attribute [attribute ID <b>283</b>].
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
     * Synchronously get the <i>AverageMACRetryPerAPSMessageSent</i> attribute [attribute ID <b>283</b>].
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
     * Get the <i>LastMessageLQI</i> attribute [attribute ID <b>284</b>].
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
     * Synchronously get the <i>LastMessageLQI</i> attribute [attribute ID <b>284</b>].
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
     * Get the <i>LastMessageRSSI</i> attribute [attribute ID <b>285</b>].
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
     * Synchronously get the <i>LastMessageRSSI</i> attribute [attribute ID <b>285</b>].
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
}

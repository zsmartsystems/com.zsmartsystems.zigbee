/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.Future;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.security.ZigBeeKey;
import com.zsmartsystems.zigbee.zcl.ZclAttribute;
import com.zsmartsystems.zigbee.zcl.ZclCluster;
import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.clusters.greenpower.GpCommissioningNotification;
import com.zsmartsystems.zigbee.zcl.clusters.greenpower.GpNotification;
import com.zsmartsystems.zigbee.zcl.clusters.greenpower.GpNotificationResponse;
import com.zsmartsystems.zigbee.zcl.clusters.greenpower.GpPairing;
import com.zsmartsystems.zigbee.zcl.clusters.greenpower.GpPairingConfiguration;
import com.zsmartsystems.zigbee.zcl.clusters.greenpower.GpPairingConfigurationGroupList;
import com.zsmartsystems.zigbee.zcl.clusters.greenpower.GpPairingSearch;
import com.zsmartsystems.zigbee.zcl.clusters.greenpower.GpProxyCommissioningMode;
import com.zsmartsystems.zigbee.zcl.clusters.greenpower.GpProxyTableRequest;
import com.zsmartsystems.zigbee.zcl.clusters.greenpower.GpProxyTableResponse;
import com.zsmartsystems.zigbee.zcl.clusters.greenpower.GpResponse;
import com.zsmartsystems.zigbee.zcl.clusters.greenpower.GpSinkCommissioningMode;
import com.zsmartsystems.zigbee.zcl.clusters.greenpower.GpSinkTableRequest;
import com.zsmartsystems.zigbee.zcl.clusters.greenpower.GpSinkTableResponse;
import com.zsmartsystems.zigbee.zcl.clusters.greenpower.GpTranslationTableRequest;
import com.zsmartsystems.zigbee.zcl.clusters.greenpower.GpTranslationTableUpdate;
import com.zsmartsystems.zigbee.zcl.clusters.greenpower.GpTranslationTableUpdateTranslation;
import com.zsmartsystems.zigbee.zcl.clusters.greenpower.GpTunnelingStop;
import com.zsmartsystems.zigbee.zcl.clusters.greenpower.ZclGreenPowerCommand;
import com.zsmartsystems.zigbee.zcl.field.ByteArray;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * <b>Green Power</b> cluster implementation (<i>Cluster ID 0x0021</i>).
 * <p>
 * The Green Power cluster defines the format of the commands exchanged when handling GPDs.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2022-05-28T21:15:34Z")
public class ZclGreenPowerCluster extends ZclCluster {
    /**
     * The ZigBee Cluster Library Cluster ID
     */
    public static final int CLUSTER_ID = 0x0021;

    /**
     * The ZigBee Cluster Library Cluster Name
     */
    public static final String CLUSTER_NAME = "Green Power";

    // Attribute constants
    /**
     * Maximum number of Proxy Table entries this node can hold. Any proxy type shall support at
     * least five Proxy Table entries. The recommended number of the Proxy Table entries for a
     * Basic Proxy is twenty.
     */
    public static final int ATTR_GPPMAXPROXYTABLEENTRIES = 0x0010;
    /**
     * The Proxy Table attribute contains the information on GPDs active in the system and the
     * corresponding sinks. Proxy Table is a read-only attribute. Generic ZCL commands
     * cannot be used to create/modify or remove Proxy Table entries. If required, e.g. for
     * CT-based commissioning, the GP Pairing command of the Green Power cluster can be used
     * for that purpose.
     * <p>
     * The Proxy Table shall be persistently stored across restarts, OTA upgrades and power
     * cycles.
     */
    public static final int ATTR_PROXYTABLE = 0x0011;
    /**
     * This attribute defines the maximum number of retransmissions in case a GP Notification
     * Response command is not received from a particular sink for full unicast GP
     * Notification command.
     */
    public static final int ATTR_GPPNOTIFICATIONRETRYNUMBER = 0x0012;
    /**
     * This attribute defines the time to wait for GP Notification Response command after
     * sending full unicast GP Notification command.
     */
    public static final int ATTR_GPPNOTIFICATIONRETRYTIMER = 0x0013;
    /**
     * This attribute defines the maximum value the Search Counter can take, before it rolls
     * over.
     */
    public static final int ATTR_GPPMAXSEARCHCOUNTER = 0x0014;
    /**
     * The gppBlockedGPDID attribute contains the information on GPDs active in the vicinity
     * of the network node, but not belonging to the system.
     */
    public static final int ATTR_GPPBLOCKEDGPDID = 0x0015;
    /**
     * The gppFunctionality attribute indicates support of the GP functionality by this
     * device. Any 1-bit sub-field set to 0b1 indicates that this functionality is supported;
     * set to 0b0 indicates that this functionality is not implemented. The reserved
     * sub-fields and sub-fields for any non-applicable functionality shall also be set to
     * 0b0.
     */
    public static final int ATTR_GPPFUNCTIONALITY = 0x0016;
    /**
     * The gppActiveFunctionality attribute indicates which GP functionality supported by
     * this device is currently enabled. Any 1-bit sub-field set to 0b1 indicates that this
     * functionality is supported and enabled; set to 0b0 indicates that this functionality
     * is disabled or not implemented.
     */
    public static final int ATTR_GPPACTIVEFUNCTIONALITY = 0x0017;
    /**
     * The gpSharedSecurityKeyType attribute stores the key type of the shared security key.
     * The gpSharedSecurityKeyType attribute can take the following values: 0b000 (no key),
     * 0b001 (NWK key), 0b010 (GP group key), 0b011 (NWK-key derived GP group key) and 0b111
     * (Derived individual GPD key).
     */
    public static final int ATTR_GPCLIENTSHAREDSECURITYKEYTYPE = 0x0020;
    /**
     * The gpSharedSecurityKey attribute stores the shared security key of the key type as
     * indicated in the gpSecurityKeyType attribute. It can take any value.
     */
    public static final int ATTR_GPCLIENTSHAREDSECURITYKEY = 0x0021;
    /**
     * The gpLinkKey attribute stores the Link Key, used to encrypt the key transmitted in the
     * Commissioning GPDF and Commissioning Reply GPDF. By default, it has the value of the
     * default ZigBee Trust Center Link Key (TC-LK), 'ZigbeeAlliance09'. Then, storing of
     * the gpLinkKey may be omitted.
     */
    public static final int ATTR_GPCLIENTLINKKEY = 0x0022;
    /**
     * The gpsMaxSinkTableEntries attribute is one octet in length, and it contains the
     * maximum number of Sink Table entries that can be stored by this sink.
     */
    public static final int ATTR_GPSMAXSINKTABLEENTRIES = 0x0000;
    /**
     * The Sink Table attribute contains the pairings configured for this sink. Sink Table is a
     * read-only attribute. Generic ZCL commands cannot be used to create/modify or remove
     * Sink Table entries. If required, e.g. for CT-based commissioning, the GP Pairing
     * Configuration command of the Green Power cluster can be used for that purpose.
     */
    public static final int ATTR_SINKTABLE = 0x0001;
    /**
     * The gpsCommunicationMode attribute contains the communication mode required by this
     * sink.
     */
    public static final int ATTR_GPSCOMMUNICATIONMODE = 0x0002;
    /**
     * The gpsCommissioningExitMode attribute contains the information on commissioning
     * mode exit requirements of this sink.
     */
    public static final int ATTR_GPSCOMMISSIONINGEXITMODE = 0x0003;
    /**
     * The gpsCommissioningWindow attribute contains the information on the time, in
     * seconds, during which this sink accepts pairing changes (additions/removals). The
     * default value is 180 seconds.
     */
    public static final int ATTR_GPSCOMMISSIONINGWINDOW = 0x0004;
    /**
     * The gpsSecurityLevel attribute contains the minimum security level this sink
     * requires the paired GPDs to support.
     */
    public static final int ATTR_GPSSECURITYLEVEL = 0x0005;
    /**
     * The gpsFunctionality attribute indicates support of the GP functionality by this
     * device. Any 1-bit subfield set to 0b1 indicates that this functionality is supported;
     * set to 0b0 indicates that this functionality is not implemented.
     */
    public static final int ATTR_GPSFUNCTIONALITY = 0x0006;
    /**
     * The gpsActiveFunctionality attribute indicates which GP functionality supported by
     * this device is currently enabled. Any 1-bit sub-field set to 0b1 indicates that this
     * functionality is supported and enabled; set to 0b0 indicates that this functionality
     * is disabled or not implemented.
     */
    public static final int ATTR_GPSACTIVEFUNCTIONALITY = 0x0007;
    /**
     * The gpSharedSecurityKeyType attribute stores the key type of the shared security key.
     * The gpSharedSecurityKeyType attribute can take the following values: 0b000 (no key),
     * 0b001 (NWK key), 0b010 (GP group key), 0b011 (NWK-key derived GP group key) and 0b111
     * (Derived individual GPD key).
     */
    public static final int ATTR_GPSERVERSHAREDSECURITYKEYTYPE = 0x0020;
    /**
     * The gpSharedSecurityKey attribute stores the shared security key of the key type as
     * indicated in the gpSecurityKeyType attribute. It can take any value.
     */
    public static final int ATTR_GPSERVERSHAREDSECURITYKEY = 0x0021;
    /**
     * The gpLinkKey attribute stores the Link Key, used to encrypt the key transmitted in the
     * Commissioning GPDF and Commissioning Reply GPDF. By default, it has the value of the
     * default ZigBee Trust Center Link Key (TC-LK), 'ZigbeeAlliance09'. Then, storing of
     * the gpLinkKey may be omitted.
     */
    public static final int ATTR_GPSERVERLINKKEY = 0x0022;

    @Override
    protected Map<Integer, ZclAttribute> initializeClientAttributes() {
        Map<Integer, ZclAttribute> attributeMap = super.initializeClientAttributes();

        attributeMap.put(ATTR_GPPMAXPROXYTABLEENTRIES, new ZclAttribute(this, ATTR_GPPMAXPROXYTABLEENTRIES, "Gpp Max Proxy Table Entries", ZclDataType.UNSIGNED_8_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_PROXYTABLE, new ZclAttribute(this, ATTR_PROXYTABLE, "Proxy Table", ZclDataType.LONG_OCTET_STRING, true, true, false, false));
        attributeMap.put(ATTR_GPPNOTIFICATIONRETRYNUMBER, new ZclAttribute(this, ATTR_GPPNOTIFICATIONRETRYNUMBER, "Gpp Notification Retry Number", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, true, true));
        attributeMap.put(ATTR_GPPNOTIFICATIONRETRYTIMER, new ZclAttribute(this, ATTR_GPPNOTIFICATIONRETRYTIMER, "Gpp Notification Retry Timer", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, true, true));
        attributeMap.put(ATTR_GPPMAXSEARCHCOUNTER, new ZclAttribute(this, ATTR_GPPMAXSEARCHCOUNTER, "Gpp Max Search Counter", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, true, true));
        attributeMap.put(ATTR_GPPBLOCKEDGPDID, new ZclAttribute(this, ATTR_GPPBLOCKEDGPDID, "Gpp Blocked Gpd ID", ZclDataType.LONG_OCTET_STRING, true, true, false, false));
        attributeMap.put(ATTR_GPPFUNCTIONALITY, new ZclAttribute(this, ATTR_GPPFUNCTIONALITY, "Gpp Functionality", ZclDataType.BITMAP_24_BIT, true, true, false, false));
        attributeMap.put(ATTR_GPPACTIVEFUNCTIONALITY, new ZclAttribute(this, ATTR_GPPACTIVEFUNCTIONALITY, "Gpp Active Functionality", ZclDataType.BITMAP_24_BIT, true, true, false, false));
        attributeMap.put(ATTR_GPCLIENTSHAREDSECURITYKEYTYPE, new ZclAttribute(this, ATTR_GPCLIENTSHAREDSECURITYKEYTYPE, "Gp Client Shared Security Key Type", ZclDataType.BITMAP_8_BIT, false, true, true, true));
        attributeMap.put(ATTR_GPCLIENTSHAREDSECURITYKEY, new ZclAttribute(this, ATTR_GPCLIENTSHAREDSECURITYKEY, "Gp Client Shared Security Key", ZclDataType.SECURITY_KEY, false, true, true, true));
        attributeMap.put(ATTR_GPCLIENTLINKKEY, new ZclAttribute(this, ATTR_GPCLIENTLINKKEY, "Gp Client Link Key", ZclDataType.SECURITY_KEY, false, true, true, true));

        return attributeMap;
    }

    @Override
    protected Map<Integer, ZclAttribute> initializeServerAttributes() {
        Map<Integer, ZclAttribute> attributeMap = super.initializeServerAttributes();

        attributeMap.put(ATTR_GPSMAXSINKTABLEENTRIES, new ZclAttribute(this, ATTR_GPSMAXSINKTABLEENTRIES, "Gps Max Sink Table Entries", ZclDataType.UNSIGNED_8_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_SINKTABLE, new ZclAttribute(this, ATTR_SINKTABLE, "Sink Table", ZclDataType.LONG_OCTET_STRING, true, true, false, false));
        attributeMap.put(ATTR_GPSCOMMUNICATIONMODE, new ZclAttribute(this, ATTR_GPSCOMMUNICATIONMODE, "Gps Communication Mode", ZclDataType.BITMAP_8_BIT, false, true, true, true));
        attributeMap.put(ATTR_GPSCOMMISSIONINGEXITMODE, new ZclAttribute(this, ATTR_GPSCOMMISSIONINGEXITMODE, "Gps Commissioning Exit Mode", ZclDataType.BITMAP_8_BIT, false, true, true, true));
        attributeMap.put(ATTR_GPSCOMMISSIONINGWINDOW, new ZclAttribute(this, ATTR_GPSCOMMISSIONINGWINDOW, "Gps Commissioning Window", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, true, true));
        attributeMap.put(ATTR_GPSSECURITYLEVEL, new ZclAttribute(this, ATTR_GPSSECURITYLEVEL, "Gps Security Level", ZclDataType.BITMAP_8_BIT, false, true, true, true));
        attributeMap.put(ATTR_GPSFUNCTIONALITY, new ZclAttribute(this, ATTR_GPSFUNCTIONALITY, "Gps Functionality", ZclDataType.BITMAP_24_BIT, true, true, false, false));
        attributeMap.put(ATTR_GPSACTIVEFUNCTIONALITY, new ZclAttribute(this, ATTR_GPSACTIVEFUNCTIONALITY, "Gps Active Functionality", ZclDataType.BITMAP_24_BIT, true, true, false, false));
        attributeMap.put(ATTR_GPSERVERSHAREDSECURITYKEYTYPE, new ZclAttribute(this, ATTR_GPSERVERSHAREDSECURITYKEYTYPE, "Gp Server Shared Security Key Type", ZclDataType.BITMAP_8_BIT, false, true, true, true));
        attributeMap.put(ATTR_GPSERVERSHAREDSECURITYKEY, new ZclAttribute(this, ATTR_GPSERVERSHAREDSECURITYKEY, "Gp server Shared Security Key", ZclDataType.SECURITY_KEY, false, true, true, true));
        attributeMap.put(ATTR_GPSERVERLINKKEY, new ZclAttribute(this, ATTR_GPSERVERLINKKEY, "Gp Server Link Key", ZclDataType.SECURITY_KEY, false, true, true, true));

        return attributeMap;
    }

    @Override
    protected Map<Integer, Class<? extends ZclCommand>> initializeServerCommands() {
        Map<Integer, Class<? extends ZclCommand>> commandMap = new ConcurrentSkipListMap<>();

        commandMap.put(0x0000, GpNotificationResponse.class);
        commandMap.put(0x0001, GpPairing.class);
        commandMap.put(0x0002, GpProxyCommissioningMode.class);
        commandMap.put(0x0006, GpResponse.class);
        commandMap.put(0x000A, GpSinkTableResponse.class);
        commandMap.put(0x000B, GpProxyTableRequest.class);

        return commandMap;
    }

    @Override
    protected Map<Integer, Class<? extends ZclCommand>> initializeClientCommands() {
        Map<Integer, Class<? extends ZclCommand>> commandMap = new ConcurrentSkipListMap<>();

        commandMap.put(0x0000, GpNotification.class);
        commandMap.put(0x0001, GpPairingSearch.class);
        commandMap.put(0x0003, GpTunnelingStop.class);
        commandMap.put(0x0004, GpCommissioningNotification.class);
        commandMap.put(0x0005, GpSinkCommissioningMode.class);
        commandMap.put(0x0007, GpTranslationTableUpdate.class);
        commandMap.put(0x0008, GpTranslationTableRequest.class);
        commandMap.put(0x0009, GpPairingConfiguration.class);
        commandMap.put(0x000A, GpSinkTableRequest.class);
        commandMap.put(0x000B, GpProxyTableResponse.class);

        return commandMap;
    }

    /**
     * Default constructor to create a Green Power cluster.
     *
     * @param zigbeeEndpoint the {@link ZigBeeEndpoint} this cluster is contained within
     */
    public ZclGreenPowerCluster(final ZigBeeEndpoint zigbeeEndpoint) {
        super(zigbeeEndpoint, CLUSTER_ID, CLUSTER_NAME);
    }

    /**
     * Sends a {@link ZclGreenPowerCommand} and returns the {@link Future} to the result which will complete when the remote
     * device response is received, or the request times out.
     *
     * @param command the {@link ZclGreenPowerCommand} to send
     * @return the command result future
     */
    public Future<CommandResult> sendCommand(ZclGreenPowerCommand command) {
        return super.sendCommand(command);
    }

    /**
     * Sends a response to the command. This method sets all the common elements of the response based on the command -
     * eg transactionId, direction, address...
     *
     * @param command the {@link ZclGreenPowerCommand} to which the response is being sent
     * @param response the {@link ZclGreenPowerCommand} to send
     */
    public Future<CommandResult> sendResponse(ZclGreenPowerCommand command, ZclGreenPowerCommand response) {
        return super.sendResponse(command, response);
    }

    /**
     * Get the <i>Gps Max Sink Table Entries</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * The gpsMaxSinkTableEntries attribute is one octet in length, and it contains the
     * maximum number of Sink Table entries that can be stored by this sink.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getGpsMaxSinkTableEntriesAsync() {
        return read(serverAttributes.get(ATTR_GPSMAXSINKTABLEENTRIES));
    }

    /**
     * Synchronously get the <i>Gps Max Sink Table Entries</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * The gpsMaxSinkTableEntries attribute is one octet in length, and it contains the
     * maximum number of Sink Table entries that can be stored by this sink.
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
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getGpsMaxSinkTableEntries(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_GPSMAXSINKTABLEENTRIES).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_GPSMAXSINKTABLEENTRIES).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_GPSMAXSINKTABLEENTRIES));
    }

    /**
     * Set reporting for the <i>Gps Max Sink Table Entries</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * The gpsMaxSinkTableEntries attribute is one octet in length, and it contains the
     * maximum number of Sink Table entries that can be stored by this sink.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #setReporting(int attributeId, int minInterval, int maxInterval, Object reportableChange)}
     */
    @Deprecated
    public Future<CommandResult> setGpsMaxSinkTableEntriesReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_GPSMAXSINKTABLEENTRIES), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Sink Table</i> attribute [attribute ID <b>0x0001</b>].
     * <p>
     * The Sink Table attribute contains the pairings configured for this sink. Sink Table is a
     * read-only attribute. Generic ZCL commands cannot be used to create/modify or remove
     * Sink Table entries. If required, e.g. for CT-based commissioning, the GP Pairing
     * Configuration command of the Green Power cluster can be used for that purpose.
     * <p>
     * The attribute is of type {@link ByteArray}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getSinkTableAsync() {
        return read(serverAttributes.get(ATTR_SINKTABLE));
    }

    /**
     * Synchronously get the <i>Sink Table</i> attribute [attribute ID <b>0x0001</b>].
     * <p>
     * The Sink Table attribute contains the pairings configured for this sink. Sink Table is a
     * read-only attribute. Generic ZCL commands cannot be used to create/modify or remove
     * Sink Table entries. If required, e.g. for CT-based commissioning, the GP Pairing
     * Configuration command of the Green Power cluster can be used for that purpose.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link ByteArray}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link ByteArray} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public ByteArray getSinkTable(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_SINKTABLE).isLastValueCurrent(refreshPeriod)) {
            return (ByteArray) serverAttributes.get(ATTR_SINKTABLE).getLastValue();
        }

        return (ByteArray) readSync(serverAttributes.get(ATTR_SINKTABLE));
    }

    /**
     * Set reporting for the <i>Sink Table</i> attribute [attribute ID <b>0x0001</b>].
     * <p>
     * The Sink Table attribute contains the pairings configured for this sink. Sink Table is a
     * read-only attribute. Generic ZCL commands cannot be used to create/modify or remove
     * Sink Table entries. If required, e.g. for CT-based commissioning, the GP Pairing
     * Configuration command of the Green Power cluster can be used for that purpose.
     * <p>
     * The attribute is of type {@link ByteArray}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #setReporting(int attributeId, int minInterval, int maxInterval)}
     */
    @Deprecated
    public Future<CommandResult> setSinkTableReporting(final int minInterval, final int maxInterval) {
        return setReporting(serverAttributes.get(ATTR_SINKTABLE), minInterval, maxInterval);
    }

    /**
     * Set the <i>Gps Communication Mode</i> attribute [attribute ID <b>0x0002</b>].
     * <p>
     * The gpsCommunicationMode attribute contains the communication mode required by this
     * sink.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param gpsCommunicationMode the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setGpsCommunicationMode(final Integer value) {
        return write(serverAttributes.get(ATTR_GPSCOMMUNICATIONMODE), value);
    }

    /**
     * Get the <i>Gps Communication Mode</i> attribute [attribute ID <b>0x0002</b>].
     * <p>
     * The gpsCommunicationMode attribute contains the communication mode required by this
     * sink.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getGpsCommunicationModeAsync() {
        return read(serverAttributes.get(ATTR_GPSCOMMUNICATIONMODE));
    }

    /**
     * Synchronously get the <i>Gps Communication Mode</i> attribute [attribute ID <b>0x0002</b>].
     * <p>
     * The gpsCommunicationMode attribute contains the communication mode required by this
     * sink.
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
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getGpsCommunicationMode(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_GPSCOMMUNICATIONMODE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_GPSCOMMUNICATIONMODE).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_GPSCOMMUNICATIONMODE));
    }

    /**
     * Set the <i>Gps Commissioning Exit Mode</i> attribute [attribute ID <b>0x0003</b>].
     * <p>
     * The gpsCommissioningExitMode attribute contains the information on commissioning
     * mode exit requirements of this sink.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param gpsCommissioningExitMode the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setGpsCommissioningExitMode(final Integer value) {
        return write(serverAttributes.get(ATTR_GPSCOMMISSIONINGEXITMODE), value);
    }

    /**
     * Get the <i>Gps Commissioning Exit Mode</i> attribute [attribute ID <b>0x0003</b>].
     * <p>
     * The gpsCommissioningExitMode attribute contains the information on commissioning
     * mode exit requirements of this sink.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getGpsCommissioningExitModeAsync() {
        return read(serverAttributes.get(ATTR_GPSCOMMISSIONINGEXITMODE));
    }

    /**
     * Synchronously get the <i>Gps Commissioning Exit Mode</i> attribute [attribute ID <b>0x0003</b>].
     * <p>
     * The gpsCommissioningExitMode attribute contains the information on commissioning
     * mode exit requirements of this sink.
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
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getGpsCommissioningExitMode(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_GPSCOMMISSIONINGEXITMODE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_GPSCOMMISSIONINGEXITMODE).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_GPSCOMMISSIONINGEXITMODE));
    }

    /**
     * Set the <i>Gps Commissioning Window</i> attribute [attribute ID <b>0x0004</b>].
     * <p>
     * The gpsCommissioningWindow attribute contains the information on the time, in
     * seconds, during which this sink accepts pairing changes (additions/removals). The
     * default value is 180 seconds.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param gpsCommissioningWindow the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setGpsCommissioningWindow(final Integer value) {
        return write(serverAttributes.get(ATTR_GPSCOMMISSIONINGWINDOW), value);
    }

    /**
     * Get the <i>Gps Commissioning Window</i> attribute [attribute ID <b>0x0004</b>].
     * <p>
     * The gpsCommissioningWindow attribute contains the information on the time, in
     * seconds, during which this sink accepts pairing changes (additions/removals). The
     * default value is 180 seconds.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getGpsCommissioningWindowAsync() {
        return read(serverAttributes.get(ATTR_GPSCOMMISSIONINGWINDOW));
    }

    /**
     * Synchronously get the <i>Gps Commissioning Window</i> attribute [attribute ID <b>0x0004</b>].
     * <p>
     * The gpsCommissioningWindow attribute contains the information on the time, in
     * seconds, during which this sink accepts pairing changes (additions/removals). The
     * default value is 180 seconds.
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
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getGpsCommissioningWindow(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_GPSCOMMISSIONINGWINDOW).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_GPSCOMMISSIONINGWINDOW).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_GPSCOMMISSIONINGWINDOW));
    }

    /**
     * Set the <i>Gps Security Level</i> attribute [attribute ID <b>0x0005</b>].
     * <p>
     * The gpsSecurityLevel attribute contains the minimum security level this sink
     * requires the paired GPDs to support.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param gpsSecurityLevel the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setGpsSecurityLevel(final Integer value) {
        return write(serverAttributes.get(ATTR_GPSSECURITYLEVEL), value);
    }

    /**
     * Get the <i>Gps Security Level</i> attribute [attribute ID <b>0x0005</b>].
     * <p>
     * The gpsSecurityLevel attribute contains the minimum security level this sink
     * requires the paired GPDs to support.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getGpsSecurityLevelAsync() {
        return read(serverAttributes.get(ATTR_GPSSECURITYLEVEL));
    }

    /**
     * Synchronously get the <i>Gps Security Level</i> attribute [attribute ID <b>0x0005</b>].
     * <p>
     * The gpsSecurityLevel attribute contains the minimum security level this sink
     * requires the paired GPDs to support.
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
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getGpsSecurityLevel(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_GPSSECURITYLEVEL).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_GPSSECURITYLEVEL).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_GPSSECURITYLEVEL));
    }

    /**
     * Get the <i>Gps Functionality</i> attribute [attribute ID <b>0x0006</b>].
     * <p>
     * The gpsFunctionality attribute indicates support of the GP functionality by this
     * device. Any 1-bit subfield set to 0b1 indicates that this functionality is supported;
     * set to 0b0 indicates that this functionality is not implemented.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getGpsFunctionalityAsync() {
        return read(serverAttributes.get(ATTR_GPSFUNCTIONALITY));
    }

    /**
     * Synchronously get the <i>Gps Functionality</i> attribute [attribute ID <b>0x0006</b>].
     * <p>
     * The gpsFunctionality attribute indicates support of the GP functionality by this
     * device. Any 1-bit subfield set to 0b1 indicates that this functionality is supported;
     * set to 0b0 indicates that this functionality is not implemented.
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
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getGpsFunctionality(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_GPSFUNCTIONALITY).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_GPSFUNCTIONALITY).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_GPSFUNCTIONALITY));
    }

    /**
     * Set reporting for the <i>Gps Functionality</i> attribute [attribute ID <b>0x0006</b>].
     * <p>
     * The gpsFunctionality attribute indicates support of the GP functionality by this
     * device. Any 1-bit subfield set to 0b1 indicates that this functionality is supported;
     * set to 0b0 indicates that this functionality is not implemented.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #setReporting(int attributeId, int minInterval, int maxInterval)}
     */
    @Deprecated
    public Future<CommandResult> setGpsFunctionalityReporting(final int minInterval, final int maxInterval) {
        return setReporting(serverAttributes.get(ATTR_GPSFUNCTIONALITY), minInterval, maxInterval);
    }

    /**
     * Get the <i>Gps Active Functionality</i> attribute [attribute ID <b>0x0007</b>].
     * <p>
     * The gpsActiveFunctionality attribute indicates which GP functionality supported by
     * this device is currently enabled. Any 1-bit sub-field set to 0b1 indicates that this
     * functionality is supported and enabled; set to 0b0 indicates that this functionality
     * is disabled or not implemented.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getGpsActiveFunctionalityAsync() {
        return read(serverAttributes.get(ATTR_GPSACTIVEFUNCTIONALITY));
    }

    /**
     * Synchronously get the <i>Gps Active Functionality</i> attribute [attribute ID <b>0x0007</b>].
     * <p>
     * The gpsActiveFunctionality attribute indicates which GP functionality supported by
     * this device is currently enabled. Any 1-bit sub-field set to 0b1 indicates that this
     * functionality is supported and enabled; set to 0b0 indicates that this functionality
     * is disabled or not implemented.
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
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getGpsActiveFunctionality(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_GPSACTIVEFUNCTIONALITY).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_GPSACTIVEFUNCTIONALITY).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_GPSACTIVEFUNCTIONALITY));
    }

    /**
     * Set reporting for the <i>Gps Active Functionality</i> attribute [attribute ID <b>0x0007</b>].
     * <p>
     * The gpsActiveFunctionality attribute indicates which GP functionality supported by
     * this device is currently enabled. Any 1-bit sub-field set to 0b1 indicates that this
     * functionality is supported and enabled; set to 0b0 indicates that this functionality
     * is disabled or not implemented.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #setReporting(int attributeId, int minInterval, int maxInterval)}
     */
    @Deprecated
    public Future<CommandResult> setGpsActiveFunctionalityReporting(final int minInterval, final int maxInterval) {
        return setReporting(serverAttributes.get(ATTR_GPSACTIVEFUNCTIONALITY), minInterval, maxInterval);
    }

    /**
     * Set the <i>Gp Server Shared Security Key Type</i> attribute [attribute ID <b>0x0020</b>].
     * <p>
     * The gpSharedSecurityKeyType attribute stores the key type of the shared security key.
     * The gpSharedSecurityKeyType attribute can take the following values: 0b000 (no key),
     * 0b001 (NWK key), 0b010 (GP group key), 0b011 (NWK-key derived GP group key) and 0b111
     * (Derived individual GPD key).
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param gpServerSharedSecurityKeyType the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setGpServerSharedSecurityKeyType(final Integer value) {
        return write(serverAttributes.get(ATTR_GPSERVERSHAREDSECURITYKEYTYPE), value);
    }

    /**
     * Get the <i>Gp Server Shared Security Key Type</i> attribute [attribute ID <b>0x0020</b>].
     * <p>
     * The gpSharedSecurityKeyType attribute stores the key type of the shared security key.
     * The gpSharedSecurityKeyType attribute can take the following values: 0b000 (no key),
     * 0b001 (NWK key), 0b010 (GP group key), 0b011 (NWK-key derived GP group key) and 0b111
     * (Derived individual GPD key).
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getGpServerSharedSecurityKeyTypeAsync() {
        return read(serverAttributes.get(ATTR_GPSERVERSHAREDSECURITYKEYTYPE));
    }

    /**
     * Synchronously get the <i>Gp Server Shared Security Key Type</i> attribute [attribute ID <b>0x0020</b>].
     * <p>
     * The gpSharedSecurityKeyType attribute stores the key type of the shared security key.
     * The gpSharedSecurityKeyType attribute can take the following values: 0b000 (no key),
     * 0b001 (NWK key), 0b010 (GP group key), 0b011 (NWK-key derived GP group key) and 0b111
     * (Derived individual GPD key).
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
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getGpServerSharedSecurityKeyType(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_GPSERVERSHAREDSECURITYKEYTYPE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_GPSERVERSHAREDSECURITYKEYTYPE).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_GPSERVERSHAREDSECURITYKEYTYPE));
    }

    /**
     * Set the <i>Gp server Shared Security Key</i> attribute [attribute ID <b>0x0021</b>].
     * <p>
     * The gpSharedSecurityKey attribute stores the shared security key of the key type as
     * indicated in the gpSecurityKeyType attribute. It can take any value.
     * <p>
     * The attribute is of type {@link ZigBeeKey}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param gpServerSharedSecurityKey the {@link ZigBeeKey} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setGpServerSharedSecurityKey(final ZigBeeKey value) {
        return write(serverAttributes.get(ATTR_GPSERVERSHAREDSECURITYKEY), value);
    }

    /**
     * Get the <i>Gp server Shared Security Key</i> attribute [attribute ID <b>0x0021</b>].
     * <p>
     * The gpSharedSecurityKey attribute stores the shared security key of the key type as
     * indicated in the gpSecurityKeyType attribute. It can take any value.
     * <p>
     * The attribute is of type {@link ZigBeeKey}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getGpServerSharedSecurityKeyAsync() {
        return read(serverAttributes.get(ATTR_GPSERVERSHAREDSECURITYKEY));
    }

    /**
     * Synchronously get the <i>Gp server Shared Security Key</i> attribute [attribute ID <b>0x0021</b>].
     * <p>
     * The gpSharedSecurityKey attribute stores the shared security key of the key type as
     * indicated in the gpSecurityKeyType attribute. It can take any value.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link ZigBeeKey}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link ZigBeeKey} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public ZigBeeKey getGpServerSharedSecurityKey(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_GPSERVERSHAREDSECURITYKEY).isLastValueCurrent(refreshPeriod)) {
            return (ZigBeeKey) serverAttributes.get(ATTR_GPSERVERSHAREDSECURITYKEY).getLastValue();
        }

        return (ZigBeeKey) readSync(serverAttributes.get(ATTR_GPSERVERSHAREDSECURITYKEY));
    }

    /**
     * Set the <i>Gp Server Link Key</i> attribute [attribute ID <b>0x0022</b>].
     * <p>
     * The gpLinkKey attribute stores the Link Key, used to encrypt the key transmitted in the
     * Commissioning GPDF and Commissioning Reply GPDF. By default, it has the value of the
     * default ZigBee Trust Center Link Key (TC-LK), 'ZigbeeAlliance09'. Then, storing of
     * the gpLinkKey may be omitted.
     * <p>
     * The attribute is of type {@link ZigBeeKey}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param gpServerLinkKey the {@link ZigBeeKey} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #writeAttribute(int attributeId, Object value)}
     */
    @Deprecated
    public Future<CommandResult> setGpServerLinkKey(final ZigBeeKey value) {
        return write(serverAttributes.get(ATTR_GPSERVERLINKKEY), value);
    }

    /**
     * Get the <i>Gp Server Link Key</i> attribute [attribute ID <b>0x0022</b>].
     * <p>
     * The gpLinkKey attribute stores the Link Key, used to encrypt the key transmitted in the
     * Commissioning GPDF and Commissioning Reply GPDF. By default, it has the value of the
     * default ZigBee Trust Center Link Key (TC-LK), 'ZigbeeAlliance09'. Then, storing of
     * the gpLinkKey may be omitted.
     * <p>
     * The attribute is of type {@link ZigBeeKey}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getGpServerLinkKeyAsync() {
        return read(serverAttributes.get(ATTR_GPSERVERLINKKEY));
    }

    /**
     * Synchronously get the <i>Gp Server Link Key</i> attribute [attribute ID <b>0x0022</b>].
     * <p>
     * The gpLinkKey attribute stores the Link Key, used to encrypt the key transmitted in the
     * Commissioning GPDF and Commissioning Reply GPDF. By default, it has the value of the
     * default ZigBee Trust Center Link Key (TC-LK), 'ZigbeeAlliance09'. Then, storing of
     * the gpLinkKey may be omitted.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link ZigBeeKey}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link ZigBeeKey} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public ZigBeeKey getGpServerLinkKey(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_GPSERVERLINKKEY).isLastValueCurrent(refreshPeriod)) {
            return (ZigBeeKey) serverAttributes.get(ATTR_GPSERVERLINKKEY).getLastValue();
        }

        return (ZigBeeKey) readSync(serverAttributes.get(ATTR_GPSERVERLINKKEY));
    }

    /**
     * The Gp Notification
     * <p>
     * The GP Notification command is generated by the proxy (or a sink capable of Sink
     * Table-based forwarding) to forward the received Data GPDF to the paired sinks.
     * <p>
     * On receipt of the GP Notification command, a device is informed about a GPDF forwarded by
     * a proxy. Also the device which received this frame is informed of bidirectional
     * communication capability of the sender.
     *
     * @param options {@link Integer} Options
     * @param gpdSrcId {@link Integer} Gpd Src ID
     * @param gpdIeee {@link IeeeAddress} Gpd IEEE
     * @param gpdEndpoint {@link Integer} Gpd Endpoint
     * @param gpdSecurityFrameCounter {@link Integer} Gpd Security Frame Counter
     * @param gpdCommandId {@link Integer} Gpd Command ID
     * @param gpdCommandPayload {@link ByteArray} Gpd Command Payload
     * @param gppShortAddress {@link Integer} Gpp Short Address
     * @param gppDistance {@link Integer} Gpp Distance
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.gpNotification(parameters ...)</code>
     * with <code>cluster.sendCommand(new gpNotification(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> gpNotification(Integer options, Integer gpdSrcId, IeeeAddress gpdIeee, Integer gpdEndpoint, Integer gpdSecurityFrameCounter, Integer gpdCommandId, ByteArray gpdCommandPayload, Integer gppShortAddress, Integer gppDistance) {
        GpNotification command = new GpNotification();

        // Set the fields
        command.setOptions(options);
        command.setGpdSrcId(gpdSrcId);
        command.setGpdIeee(gpdIeee);
        command.setGpdEndpoint(gpdEndpoint);
        command.setGpdSecurityFrameCounter(gpdSecurityFrameCounter);
        command.setGpdCommandId(gpdCommandId);
        command.setGpdCommandPayload(gpdCommandPayload);
        command.setGppShortAddress(gppShortAddress);
        command.setGppDistance(gppDistance);

        return sendCommand(command);
    }

    /**
     * The Gp Pairing Search
     * <p>
     * The GP Pairing Search command is generated when the proxy needs to discover pairing
     * information for a particular GPD.
     * <p>
     * On receipt of this command, the device is informed about a proxy requesting pairing
     * information on particular GPD.
     *
     * @param options {@link Integer} Options
     * @param gpdSrcId {@link Integer} Gpd Src ID
     * @param gpdIeee {@link IeeeAddress} Gpd IEEE
     * @param endpoint {@link Integer} Endpoint
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.gpPairingSearch(parameters ...)</code>
     * with <code>cluster.sendCommand(new gpPairingSearch(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> gpPairingSearch(Integer options, Integer gpdSrcId, IeeeAddress gpdIeee, Integer endpoint) {
        GpPairingSearch command = new GpPairingSearch();

        // Set the fields
        command.setOptions(options);
        command.setGpdSrcId(gpdSrcId);
        command.setGpdIeee(gpdIeee);
        command.setEndpoint(endpoint);

        return sendCommand(command);
    }

    /**
     * The Gp Tunneling Stop
     * <p>
     * This command is sent to prevent other proxies from also forwarding GP Notifications to
     * the sinks requiring full unicast communication mode.
     *
     * @param options {@link Integer} Options
     * @param gpdSrcId {@link Integer} Gpd Src ID
     * @param gpdIeee {@link IeeeAddress} Gpd IEEE
     * @param endpoint {@link Integer} Endpoint
     * @param gpdSecurityFrameCounter {@link Integer} Gpd Security Frame Counter
     * @param gppShortAddress {@link Integer} Gpp Short Address
     * @param gppDistance {@link Integer} Gpp Distance
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.gpTunnelingStop(parameters ...)</code>
     * with <code>cluster.sendCommand(new gpTunnelingStop(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> gpTunnelingStop(Integer options, Integer gpdSrcId, IeeeAddress gpdIeee, Integer endpoint, Integer gpdSecurityFrameCounter, Integer gppShortAddress, Integer gppDistance) {
        GpTunnelingStop command = new GpTunnelingStop();

        // Set the fields
        command.setOptions(options);
        command.setGpdSrcId(gpdSrcId);
        command.setGpdIeee(gpdIeee);
        command.setEndpoint(endpoint);
        command.setGpdSecurityFrameCounter(gpdSecurityFrameCounter);
        command.setGppShortAddress(gppShortAddress);
        command.setGppDistance(gppDistance);

        return sendCommand(command);
    }

    /**
     * The Gp Commissioning Notification
     * <p>
     * The GP Commissioning Notification command is used by the proxy in commissioning mode to
     * forward commissioning data to the sink(s).
     * <p>
     * On receipt of the GP Commissioning Notification command, a device is informed about a
     * GPD device seeking to manage a pairing. Also the device which received this frame is
     * informed of bidirectional commissioning capability of the sender.
     *
     * @param options {@link Integer} Options
     * @param gpdSrcId {@link Integer} Gpd Src ID
     * @param gpdIeee {@link IeeeAddress} Gpd IEEE
     * @param endpoint {@link Integer} Endpoint
     * @param gpdSecurityFrameCounter {@link Integer} Gpd Security Frame Counter
     * @param gpdCommandId {@link Integer} Gpd Command ID
     * @param gpdCommandPayload {@link ByteArray} Gpd Command Payload
     * @param gppShortAddress {@link Integer} Gpp Short Address
     * @param gppLink {@link Integer} Gpp Link
     * @param mic {@link Integer} Mic
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.gpCommissioningNotification(parameters ...)</code>
     * with <code>cluster.sendCommand(new gpCommissioningNotification(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> gpCommissioningNotification(Integer options, Integer gpdSrcId, IeeeAddress gpdIeee, Integer endpoint, Integer gpdSecurityFrameCounter, Integer gpdCommandId, ByteArray gpdCommandPayload, Integer gppShortAddress, Integer gppLink, Integer mic) {
        GpCommissioningNotification command = new GpCommissioningNotification();

        // Set the fields
        command.setOptions(options);
        command.setGpdSrcId(gpdSrcId);
        command.setGpdIeee(gpdIeee);
        command.setEndpoint(endpoint);
        command.setGpdSecurityFrameCounter(gpdSecurityFrameCounter);
        command.setGpdCommandId(gpdCommandId);
        command.setGpdCommandPayload(gpdCommandPayload);
        command.setGppShortAddress(gppShortAddress);
        command.setGppLink(gppLink);
        command.setMic(mic);

        return sendCommand(command);
    }

    /**
     * The Gp Sink Commissioning Mode
     * <p>
     * The GP Sink Commissioning Mode command is generated by a remote device, e.g. a
     * Commissioning Tool, to request a sink to perform a commissioning action in a particular
     * way.
     *
     * @param options {@link Integer} Options
     * @param gpmAddrForSecurity {@link Integer} Gpm Addr For Security
     * @param gpmAddrForPairing {@link Integer} Gpm Addr For Pairing
     * @param sinkEndpoint {@link Integer} Sink Endpoint
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.gpSinkCommissioningMode(parameters ...)</code>
     * with <code>cluster.sendCommand(new gpSinkCommissioningMode(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> gpSinkCommissioningMode(Integer options, Integer gpmAddrForSecurity, Integer gpmAddrForPairing, Integer sinkEndpoint) {
        GpSinkCommissioningMode command = new GpSinkCommissioningMode();

        // Set the fields
        command.setOptions(options);
        command.setGpmAddrForSecurity(gpmAddrForSecurity);
        command.setGpmAddrForPairing(gpmAddrForPairing);
        command.setSinkEndpoint(sinkEndpoint);

        return sendCommand(command);
    }

    /**
     * The Gp Translation Table Update
     * <p>
     * This command is generated to configure the GPD Command Translation Table.
     *
     * @param options {@link Integer} Options
     * @param gpdSrcId {@link Integer} Gpd Src ID
     * @param gpdIeee {@link IeeeAddress} Gpd IEEE
     * @param endpoint {@link Integer} Endpoint
     * @param translations {@link GpTranslationTableUpdateTranslation} Translations
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.gpTranslationTableUpdate(parameters ...)</code>
     * with <code>cluster.sendCommand(new gpTranslationTableUpdate(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> gpTranslationTableUpdate(Integer options, Integer gpdSrcId, IeeeAddress gpdIeee, Integer endpoint, GpTranslationTableUpdateTranslation translations) {
        GpTranslationTableUpdate command = new GpTranslationTableUpdate();

        // Set the fields
        command.setOptions(options);
        command.setGpdSrcId(gpdSrcId);
        command.setGpdIeee(gpdIeee);
        command.setEndpoint(endpoint);
        command.setTranslations(translations);

        return sendCommand(command);
    }

    /**
     * The Gp Translation Table Request
     * <p>
     * The GP Translation Table Request is generated to request information from the GPD
     * Command Translation Table of remote device(s).
     *
     * @param startIndex {@link Integer} Start Index
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.gpTranslationTableRequest(parameters ...)</code>
     * with <code>cluster.sendCommand(new gpTranslationTableRequest(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> gpTranslationTableRequest(Integer startIndex) {
        GpTranslationTableRequest command = new GpTranslationTableRequest();

        // Set the fields
        command.setStartIndex(startIndex);

        return sendCommand(command);
    }

    /**
     * The Gp Pairing Configuration
     * <p>
     * The command is generated to configure the Sink Table of a sink, to
     * create/update/replace/remove a pairing to a GPD and/or trigger the sending of GP
     * Pairing command.
     * <p>
     * In the current version of the specification, a device shall only send GP Pairing
     * Configuration command with the Number of paired endpoints field set to 0xfe, if the
     * CommunicationMode is equal to Pre-Commissioned Groupcast.
     *
     * @param actions {@link Integer} Actions
     * @param options {@link Integer} Options
     * @param gpdSrcId {@link Integer} Gpd Src ID
     * @param gpdIeee {@link IeeeAddress} Gpd IEEE
     * @param endpoint {@link Integer} Endpoint
     * @param deviceId {@link Integer} Device ID
     * @param groupListCount {@link Integer} Group List Count
     * @param groupList {@link GpPairingConfigurationGroupList} Group List
     * @param gpdAssignedAlias {@link Integer} Gpd Assigned Alias
     * @param forwardingRadius {@link Integer} Forwarding Radius
     * @param securityOptions {@link Integer} Security Options
     * @param gpdSecurityFrameCounter {@link Integer} Gpd Security Frame Counter
     * @param gpdSecurityKey {@link ZigBeeKey} Gpd Security Key
     * @param numberOfPairedEndpoints {@link Integer} Number Of Paired Endpoints
     * @param pairedEndpoints {@link Integer} Paired Endpoints
     * @param applicationInformation {@link Integer} Application Information
     * @param manufacturerId {@link Integer} Manufacturer ID
     * @param modeId {@link Integer} Mode ID
     * @param numberOfGpdCommands {@link Integer} Number Of Gpd Commands
     * @param gpdCommandIdList {@link Integer} Gpd Command ID List
     * @param clusterIdListCount {@link Integer} Cluster ID List Count
     * @param clusterListServer {@link Integer} Cluster List Server
     * @param clusterListClient {@link Integer} Cluster List Client
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.gpPairingConfiguration(parameters ...)</code>
     * with <code>cluster.sendCommand(new gpPairingConfiguration(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> gpPairingConfiguration(Integer actions, Integer options, Integer gpdSrcId, IeeeAddress gpdIeee, Integer endpoint, Integer deviceId, Integer groupListCount, GpPairingConfigurationGroupList groupList, Integer gpdAssignedAlias, Integer forwardingRadius, Integer securityOptions, Integer gpdSecurityFrameCounter, ZigBeeKey gpdSecurityKey, Integer numberOfPairedEndpoints, Integer pairedEndpoints, Integer applicationInformation, Integer manufacturerId, Integer modeId, Integer numberOfGpdCommands, Integer gpdCommandIdList, Integer clusterIdListCount, Integer clusterListServer, Integer clusterListClient) {
        GpPairingConfiguration command = new GpPairingConfiguration();

        // Set the fields
        command.setActions(actions);
        command.setOptions(options);
        command.setGpdSrcId(gpdSrcId);
        command.setGpdIeee(gpdIeee);
        command.setEndpoint(endpoint);
        command.setDeviceId(deviceId);
        command.setGroupListCount(groupListCount);
        command.setGroupList(groupList);
        command.setGpdAssignedAlias(gpdAssignedAlias);
        command.setForwardingRadius(forwardingRadius);
        command.setSecurityOptions(securityOptions);
        command.setGpdSecurityFrameCounter(gpdSecurityFrameCounter);
        command.setGpdSecurityKey(gpdSecurityKey);
        command.setNumberOfPairedEndpoints(numberOfPairedEndpoints);
        command.setPairedEndpoints(pairedEndpoints);
        command.setApplicationInformation(applicationInformation);
        command.setManufacturerId(manufacturerId);
        command.setModeId(modeId);
        command.setNumberOfGpdCommands(numberOfGpdCommands);
        command.setGpdCommandIdList(gpdCommandIdList);
        command.setClusterIdListCount(clusterIdListCount);
        command.setClusterListServer(clusterListServer);
        command.setClusterListClient(clusterListClient);

        return sendCommand(command);
    }

    /**
     * The Gp Sink Table Request
     * <p>
     * The GP Sink Table Request command is generated to read out selected Sink Table entry(s),
     * by index or by GPD ID
     *
     * @param options {@link Integer} Options
     * @param gpdSrcId {@link Integer} Gpd Src ID
     * @param gpdIeee {@link IeeeAddress} Gpd IEEE
     * @param endpoint {@link Integer} Endpoint
     * @param index {@link Integer} Index
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.gpSinkTableRequest(parameters ...)</code>
     * with <code>cluster.sendCommand(new gpSinkTableRequest(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> gpSinkTableRequest(Integer options, Integer gpdSrcId, IeeeAddress gpdIeee, Integer endpoint, Integer index) {
        GpSinkTableRequest command = new GpSinkTableRequest();

        // Set the fields
        command.setOptions(options);
        command.setGpdSrcId(gpdSrcId);
        command.setGpdIeee(gpdIeee);
        command.setEndpoint(endpoint);
        command.setIndex(index);

        return sendCommand(command);
    }

    /**
     * The Gp Proxy Table Response
     * <p>
     * To reply with read-out Proxy Table entries, by index or by GPD ID.
     * <p>
     * Upon reception of the GP Proxy Table Request command, the device shall check if it
     * implements a Proxy Table. If not, it shall generate a ZCL Default Response command, with
     * the Status code field carrying UNSUP_CLUSTER_COMMAND. If the device implements the
     * Proxy Table, it shall prepare a GP Proxy Table Response.
     *
     * @param status {@link Integer} Status
     * @param totalNumberOfNonEmptyProxyTableEntries {@link Integer} Total Number Of Non Empty Proxy Table Entries
     * @param startIndex {@link Integer} Start Index
     * @param entriesCount {@link Integer} Entries Count
     * @param proxyTableEntries {@link Integer} Proxy Table Entries
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.gpProxyTableResponse(parameters ...)</code>
     * with <code>cluster.sendCommand(new gpProxyTableResponse(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> gpProxyTableResponse(Integer status, Integer totalNumberOfNonEmptyProxyTableEntries, Integer startIndex, Integer entriesCount, Integer proxyTableEntries) {
        GpProxyTableResponse command = new GpProxyTableResponse();

        // Set the fields
        command.setStatus(status);
        command.setTotalNumberOfNonEmptyProxyTableEntries(totalNumberOfNonEmptyProxyTableEntries);
        command.setStartIndex(startIndex);
        command.setEntriesCount(entriesCount);
        command.setProxyTableEntries(proxyTableEntries);

        return sendCommand(command);
    }

    /**
     * The Gp Notification Response
     * <p>
     * This command is generated when the sink acknowledges the reception of full unicast GP
     * Notification command. The GP Notification Response command is sent in unicast to the
     * originating proxy.
     *
     * @param options {@link Integer} Options
     * @param gpdSrcId {@link Integer} Gpd Src ID
     * @param gpdIeee {@link IeeeAddress} Gpd IEEE
     * @param gpdSecurityFrameCounter {@link Integer} Gpd Security Frame Counter
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.gpNotificationResponse(parameters ...)</code>
     * with <code>cluster.sendCommand(new gpNotificationResponse(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> gpNotificationResponse(Integer options, Integer gpdSrcId, IeeeAddress gpdIeee, Integer gpdSecurityFrameCounter) {
        GpNotificationResponse command = new GpNotificationResponse();

        // Set the fields
        command.setOptions(options);
        command.setGpdSrcId(gpdSrcId);
        command.setGpdIeee(gpdIeee);
        command.setGpdSecurityFrameCounter(gpdSecurityFrameCounter);

        return sendCommand(command);
    }

    /**
     * The Gp Pairing
     * <p>
     * The GP Pairing command is generated by the sink to manage pairing information. The GP
     * Pairing command is typically sent using network-wide broadcast. If the
     * CommunicationMode sub-field is set to 0b11, GP Pairing command may be sent in unicast to
     * the selected proxy.
     *
     * @param options {@link Integer} Options
     * @param gpdSrcId {@link Integer} Gpd Src ID
     * @param gpdIeee {@link IeeeAddress} Gpd IEEE
     * @param endpoint {@link Integer} Endpoint
     * @param sinkIeeeAddress {@link IeeeAddress} Sink IEEE Address
     * @param sinkNwkAddress {@link Integer} Sink NWK Address
     * @param sinkGroupId {@link Integer} Sink Group ID
     * @param deviceId {@link Integer} Device ID
     * @param gpdSecurityFrameCounter {@link Integer} Gpd Security Frame Counter
     * @param gpdKey {@link ZigBeeKey} Gpd Key
     * @param assignedAlias {@link Integer} Assigned Alias
     * @param forwardingRadius {@link Integer} Forwarding Radius
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.gpPairing(parameters ...)</code>
     * with <code>cluster.sendCommand(new gpPairing(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> gpPairing(Integer options, Integer gpdSrcId, IeeeAddress gpdIeee, Integer endpoint, IeeeAddress sinkIeeeAddress, Integer sinkNwkAddress, Integer sinkGroupId, Integer deviceId, Integer gpdSecurityFrameCounter, ZigBeeKey gpdKey, Integer assignedAlias, Integer forwardingRadius) {
        GpPairing command = new GpPairing();

        // Set the fields
        command.setOptions(options);
        command.setGpdSrcId(gpdSrcId);
        command.setGpdIeee(gpdIeee);
        command.setEndpoint(endpoint);
        command.setSinkIeeeAddress(sinkIeeeAddress);
        command.setSinkNwkAddress(sinkNwkAddress);
        command.setSinkGroupId(sinkGroupId);
        command.setDeviceId(deviceId);
        command.setGpdSecurityFrameCounter(gpdSecurityFrameCounter);
        command.setGpdKey(gpdKey);
        command.setAssignedAlias(assignedAlias);
        command.setForwardingRadius(forwardingRadius);

        return sendCommand(command);
    }

    /**
     * The Gp Proxy Commissioning Mode
     * <p>
     * This command is generated when the sink wishes to instruct the proxies to enter/exit
     * commissioning mode. The GP Proxy Commissioning Mode command is typically sent using
     * network-wide broadcast.
     *
     * @param options {@link Integer} Options
     * @param commissioningWindow {@link Integer} Commissioning Window
     * @param channel {@link Integer} Channel
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.gpProxyCommissioningMode(parameters ...)</code>
     * with <code>cluster.sendCommand(new gpProxyCommissioningMode(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> gpProxyCommissioningMode(Integer options, Integer commissioningWindow, Integer channel) {
        GpProxyCommissioningMode command = new GpProxyCommissioningMode();

        // Set the fields
        command.setOptions(options);
        command.setCommissioningWindow(commissioningWindow);
        command.setChannel(channel);

        return sendCommand(command);
    }

    /**
     * The Gp Response
     * <p>
     * This command is generated when sink requests to send any information to a specific GPD
     * with Rx capability.
     *
     * @param options {@link Integer} Options
     * @param tempMasterShortAddress {@link Integer} Temp Master Short Address
     * @param tempMasterTxChannel {@link Integer} Temp Master Tx Channel
     * @param gpdSrcId {@link Integer} Gpd Src ID
     * @param gpdIeee {@link IeeeAddress} Gpd IEEE
     * @param endpoint {@link Integer} Endpoint
     * @param gpdCommandId {@link Integer} Gpd Command ID
     * @param gpdCommandPayload {@link ByteArray} Gpd Command Payload
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.gpResponse(parameters ...)</code>
     * with <code>cluster.sendCommand(new gpResponse(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> gpResponse(Integer options, Integer tempMasterShortAddress, Integer tempMasterTxChannel, Integer gpdSrcId, IeeeAddress gpdIeee, Integer endpoint, Integer gpdCommandId, ByteArray gpdCommandPayload) {
        GpResponse command = new GpResponse();

        // Set the fields
        command.setOptions(options);
        command.setTempMasterShortAddress(tempMasterShortAddress);
        command.setTempMasterTxChannel(tempMasterTxChannel);
        command.setGpdSrcId(gpdSrcId);
        command.setGpdIeee(gpdIeee);
        command.setEndpoint(endpoint);
        command.setGpdCommandId(gpdCommandId);
        command.setGpdCommandPayload(gpdCommandPayload);

        return sendCommand(command);
    }

    /**
     * The Gp Sink Table Response
     * <p>
     * To selected Proxy Table entries, by index or by GPD ID.
     *
     * @param status {@link Integer} Status
     * @param totalNumberofNonEmptySinkTableEntries {@link Integer} Total Numberof Non Empty Sink Table Entries
     * @param startIndex {@link Integer} Start Index
     * @param sinkTableEntriesCount {@link Integer} Sink Table Entries Count
     * @param sinkTableEntries {@link Integer} Sink Table Entries
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.gpSinkTableResponse(parameters ...)</code>
     * with <code>cluster.sendCommand(new gpSinkTableResponse(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> gpSinkTableResponse(Integer status, Integer totalNumberofNonEmptySinkTableEntries, Integer startIndex, Integer sinkTableEntriesCount, Integer sinkTableEntries) {
        GpSinkTableResponse command = new GpSinkTableResponse();

        // Set the fields
        command.setStatus(status);
        command.setTotalNumberofNonEmptySinkTableEntries(totalNumberofNonEmptySinkTableEntries);
        command.setStartIndex(startIndex);
        command.setSinkTableEntriesCount(sinkTableEntriesCount);
        command.setSinkTableEntries(sinkTableEntries);

        return sendCommand(command);
    }

    /**
     * The Gp Proxy Table Request
     * <p>
     * To request selected Proxy Table entries, by index or by GPD ID.
     *
     * @param options {@link Integer} Options
     * @param gpdSrcId {@link Integer} Gpd Src ID
     * @param gpdIeee {@link IeeeAddress} Gpd IEEE
     * @param endpoint {@link Integer} Endpoint
     * @param index {@link Integer} Index
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.gpProxyTableRequest(parameters ...)</code>
     * with <code>cluster.sendCommand(new gpProxyTableRequest(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> gpProxyTableRequest(Integer options, Integer gpdSrcId, IeeeAddress gpdIeee, Integer endpoint, Integer index) {
        GpProxyTableRequest command = new GpProxyTableRequest();

        // Set the fields
        command.setOptions(options);
        command.setGpdSrcId(gpdSrcId);
        command.setGpdIeee(gpdIeee);
        command.setEndpoint(endpoint);
        command.setIndex(index);

        return sendCommand(command);
    }
}

package com.zsmartsystems.zigbee.zcl.clusters;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.ZigBeeDeviceAddress;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.zcl.ZclAttribute;
import com.zsmartsystems.zigbee.zcl.ZclCluster;
import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.clusters.iaswd.SquawkCommand;
import com.zsmartsystems.zigbee.zcl.clusters.iaswd.StartWarningCommand;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * <b>IAS WD</b> cluster implementation (<i>Cluster ID 0x0502</i>).
 * <p>
 * The IAS WD cluster provides an interface to the functionality of any Warning
 * Device equipment of the IAS system. Using this cluster, a ZigBee enabled CIE
 * device can access a ZigBee enabled IAS WD device and issue alarm warning
 * indications (siren, strobe lighting, etc.) when a system alarm condition is detected.
 * </p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class ZclIasWdCluster extends ZclCluster {
    // Cluster ID
    public static final int CLUSTER_ID = 0x0502;

    // Cluster Name
    public static final String CLUSTER_NAME = "IAS WD";

    // Attribute constants
    public static final int ATTR_MAXDURATION = 0x0000;
    public static final int ATTR_ZONETYPE = 0x0001;
    public static final int ATTR_ZONESTATUS = 0x0002;
    public static final int ATTR_IAS_CIE_ADDRESS = 0x0010;

    // Attribute initialisation
    protected Map<Integer, ZclAttribute> initializeAttributes() {
        Map<Integer, ZclAttribute> attributeMap = new HashMap<Integer, ZclAttribute>(4);

        attributeMap.put(ATTR_MAXDURATION, new ZclAttribute(0, "MaxDuration", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, true, false));
        attributeMap.put(ATTR_ZONETYPE, new ZclAttribute(1, "ZoneType", ZclDataType.ENUMERATION_8_BIT, true, true, false, false));
        attributeMap.put(ATTR_ZONESTATUS, new ZclAttribute(2, "ZoneStatus", ZclDataType.BITMAP_16_BIT, true, true, false, false));
        attributeMap.put(ATTR_IAS_CIE_ADDRESS, new ZclAttribute(16, "IAS_CIE_Address", ZclDataType.IEEE_ADDRESS, true, true, true, false));

        return attributeMap;
    }

    /**
     * Default constructor.
     */
    public ZclIasWdCluster(final ZigBeeNetworkManager zigbeeManager, final ZigBeeDeviceAddress zigbeeAddress) {
        super(zigbeeManager, zigbeeAddress, CLUSTER_ID, CLUSTER_NAME);
    }



    /**
     * <p>
     * Set the <i>MaxDuration</i> attribute.
     * <p>
     * <p>
     * The MaxDuration attribute specifies the maximum time in seconds that the siren
     * will sound continuously, regardless of start/stop commands.
     * </p>
     * <p>
     * The attribute is of type {@link Integer}.
     * </p>
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     * </p>
     *
     * @param maxDuration the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setMaxDuration(final Object value) {
        return write(attributes.get(ATTR_MAXDURATION), value);
    }

    /**
     * <p>
     * Get the <i>MaxDuration</i> attribute.
     * <p>
     * <p>
     * The MaxDuration attribute specifies the maximum time in seconds that the siren
     * will sound continuously, regardless of start/stop commands.
     * </p>
     * <p>
     * The attribute is of type {@link Integer}.
     * </p>
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     * </p>
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getMaxDurationAsync() {
        return read(attributes.get(ATTR_MAXDURATION));
    }


    /**
     * <p>
     * Synchronously get the <i>MaxDuration</i> attribute.
     * <p>
     * <p>
     * The MaxDuration attribute specifies the maximum time in seconds that the siren
     * will sound continuously, regardless of start/stop commands.
     * </p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link Integer}.
     * </p>
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     * </p>
     *
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getMaxDuration() {
        return (Integer) readSync(attributes.get(ATTR_MAXDURATION));
    }

    /**
     * <p>
     * Get the <i>ZoneType</i> attribute.
     * <p>
     * </p>
     * <p>
     * The attribute is of type {@link Integer}.
     * </p>
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     * </p>
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getZoneTypeAsync() {
        return read(attributes.get(ATTR_ZONETYPE));
    }


    /**
     * <p>
     * Synchronously get the <i>ZoneType</i> attribute.
     * <p>
     * </p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link Integer}.
     * </p>
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     * </p>
     *
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getZoneType() {
        return (Integer) readSync(attributes.get(ATTR_ZONETYPE));
    }

    /**
     * <p>
     * Get the <i>ZoneStatus</i> attribute.
     * <p>
     * </p>
     * <p>
     * The attribute is of type {@link Integer}.
     * </p>
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     * </p>
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getZoneStatusAsync() {
        return read(attributes.get(ATTR_ZONESTATUS));
    }


    /**
     * <p>
     * Synchronously get the <i>ZoneStatus</i> attribute.
     * <p>
     * </p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link Integer}.
     * </p>
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     * </p>
     *
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getZoneStatus() {
        return (Integer) readSync(attributes.get(ATTR_ZONESTATUS));
    }


    /**
     * <p>
     * Set the <i>IAS_CIE_Address</i> attribute.
     * <p>
     * </p>
     * <p>
     * The attribute is of type {@link Long}.
     * </p>
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     * </p>
     *
     * @param ias_Cie_Address the {@link Long} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setIas_Cie_Address(final Object value) {
        return write(attributes.get(ATTR_IAS_CIE_ADDRESS), value);
    }

    /**
     * <p>
     * Get the <i>IAS_CIE_Address</i> attribute.
     * <p>
     * </p>
     * <p>
     * The attribute is of type {@link Long}.
     * </p>
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     * </p>
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getIas_Cie_AddressAsync() {
        return read(attributes.get(ATTR_IAS_CIE_ADDRESS));
    }


    /**
     * <p>
     * Synchronously get the <i>IAS_CIE_Address</i> attribute.
     * <p>
     * </p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link Long}.
     * </p>
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     * </p>
     *
     * @return the {@link Long} attribute value, or null on error
     */
    public Long getIas_Cie_Address() {
        return (Long) readSync(attributes.get(ATTR_IAS_CIE_ADDRESS));
    }

    /**
     * The Start Warning Command
     * <p>
     * This command starts the WD operation. The WD alerts the surrounding area by
     * audible (siren) and visual (strobe) signals.
     * <br>
     * A Start Warning command shall always terminate the effect of any previous
     * command that is still current.
     * </p>
     *
     * @param header {@link Integer} Header
     * @param warningDuration {@link Integer} Warning duration
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> startWarningCommand(Integer header, Integer warningDuration) {
        StartWarningCommand command = new StartWarningCommand();

        // Set the fields
        command.setHeader(header);
        command.setWarningDuration(warningDuration);

        return send(command);
    }

    /**
     * The Squawk Command
     *
     * @param header {@link Integer} Header
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> squawkCommand(Integer header) {
        SquawkCommand command = new SquawkCommand();

        // Set the fields
        command.setHeader(header);

        return send(command);
    }

    /**
     * Add a binding for this cluster to the local node
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> bind() {
        return bind();
    }

    @Override
    public ZclCommand getCommandFromId(int commandId) {
        switch (commandId) {
            case 0: // START_WARNING_COMMAND
                return new StartWarningCommand();
            case 2: // SQUAWK_COMMAND
                return new SquawkCommand();
            default:
                return null;
        }
    }
}

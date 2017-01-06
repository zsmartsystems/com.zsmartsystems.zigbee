package com.zsmartsystems.zigbee.zcl.clusters;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.ZigBeeDeviceAddress;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.zcl.ZclAttribute;
import com.zsmartsystems.zigbee.zcl.ZclCluster;
import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.clusters.iaszone.ZoneEnrollRequestCommand;
import com.zsmartsystems.zigbee.zcl.clusters.iaszone.ZoneEnrollResponse;
import com.zsmartsystems.zigbee.zcl.clusters.iaszone.ZoneStatusChangeNotificationCommand;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * <b>IAS Zone</b> cluster implementation (<i>Cluster ID 0x0500</i>).
 * <p>
 * The IAS Zone cluster defines an interface to the functionality of an IAS security
 * zone device. IAS Zone supports up to two alarm types per zone, low battery
 * reports and supervision of the IAS network.
 * </p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class ZclIasZoneCluster extends ZclCluster {
    // Cluster ID
    public static final int CLUSTER_ID = 0x0500;

    // Cluster Name
    public static final String CLUSTER_NAME = "IAS Zone";

    // Attribute constants
    public static final int ATTR_ZONESTATE = 0x0000;
    public static final int ATTR_ZONETYPE = 0x0001;
    public static final int ATTR_ZONESTATUS = 0x0002;
    public static final int ATTR_IAS_CIE_ADDRESS = 0x0010;

    // Attribute initialisation
    protected Map<Integer, ZclAttribute> initializeAttributes() {
        Map<Integer, ZclAttribute> attributeMap = new HashMap<Integer, ZclAttribute>(4);

        attributeMap.put(ATTR_ZONESTATE, new ZclAttribute(0, "ZoneState", ZclDataType.ENUMERATION_8_BIT, true, true, false, false));
        attributeMap.put(ATTR_ZONETYPE, new ZclAttribute(1, "ZoneType", ZclDataType.ENUMERATION_8_BIT, true, true, false, false));
        attributeMap.put(ATTR_ZONESTATUS, new ZclAttribute(2, "ZoneStatus", ZclDataType.BITMAP_16_BIT, true, true, false, false));
        attributeMap.put(ATTR_IAS_CIE_ADDRESS, new ZclAttribute(16, "IAS_CIE_Address", ZclDataType.IEEE_ADDRESS, true, true, true, false));

        return attributeMap;
    }

    /**
     * Default constructor.
     */
    public ZclIasZoneCluster(final ZigBeeNetworkManager zigbeeManager, final ZigBeeDeviceAddress zigbeeAddress) {
        super(zigbeeManager, zigbeeAddress, CLUSTER_ID, CLUSTER_NAME);
    }


    /**
     * <p>
     * Get the <i>ZoneState</i> attribute.
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
    public Future<CommandResult> getZoneStateAsync() {
        return read(attributes.get(ATTR_ZONESTATE));
    }


    /**
     * <p>
     * Synchronously get the <i>ZoneState</i> attribute.
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
    public Integer getZoneState() {
        return (Integer) readSync(attributes.get(ATTR_ZONESTATE));
    }

    /**
     * <p>
     * Get the <i>ZoneType</i> attribute.
     * <p>
     * <p>
     * The Zone Type dictates the meaning of Alarm1 and Alarm2 bits of the ZoneStatus attribute
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
     * <p>
     * The Zone Type dictates the meaning of Alarm1 and Alarm2 bits of the ZoneStatus attribute
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
     * <p>
     * <br>
     * The ZoneStatus attribute is a bit map.
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
     * <p>
     * <br>
     * The ZoneStatus attribute is a bit map.
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
     * <p>
     * <br>
     * The IAS_CIE_Address attribute specifies the address that commands generated by
     * the server shall be sent to. All commands received by the server must also come
     * from this address.
     * <br>
     * It is up to the zone's specific implementation to permit or deny change (write) of
     * this attribute at specific times. Also, it is up to the zone's specific implementation
     * to implement some auto-detect for the CIE (example: by requesting the ZigBee
     * cluster discovery service to locate a Zone Server cluster.) or require the
     * intervention of a CT in order to configure this attribute during installation.
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
     * <p>
     * <br>
     * The IAS_CIE_Address attribute specifies the address that commands generated by
     * the server shall be sent to. All commands received by the server must also come
     * from this address.
     * <br>
     * It is up to the zone's specific implementation to permit or deny change (write) of
     * this attribute at specific times. Also, it is up to the zone's specific implementation
     * to implement some auto-detect for the CIE (example: by requesting the ZigBee
     * cluster discovery service to locate a Zone Server cluster.) or require the
     * intervention of a CT in order to configure this attribute during installation.
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
     * <p>
     * <br>
     * The IAS_CIE_Address attribute specifies the address that commands generated by
     * the server shall be sent to. All commands received by the server must also come
     * from this address.
     * <br>
     * It is up to the zone's specific implementation to permit or deny change (write) of
     * this attribute at specific times. Also, it is up to the zone's specific implementation
     * to implement some auto-detect for the CIE (example: by requesting the ZigBee
     * cluster discovery service to locate a Zone Server cluster.) or require the
     * intervention of a CT in order to configure this attribute during installation.
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
     * The Zone Enroll Response
     *
     * @param enrollResponseCode {@link Integer} Enroll response code
     * @param zoneId {@link Integer} Zone ID
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> zoneEnrollResponse(Integer enrollResponseCode, Integer zoneId) {
        ZoneEnrollResponse command = new ZoneEnrollResponse();

        // Set the fields
        command.setEnrollResponseCode(enrollResponseCode);
        command.setZoneId(zoneId);

        return send(command);
    }

    /**
     * The Zone Status Change Notification Command
     *
     * @param zoneStatus {@link Integer} Zone Status
     * @param extendedStatus {@link Integer} Extended Status
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> zoneStatusChangeNotificationCommand(Integer zoneStatus, Integer extendedStatus) {
        ZoneStatusChangeNotificationCommand command = new ZoneStatusChangeNotificationCommand();

        // Set the fields
        command.setZoneStatus(zoneStatus);
        command.setExtendedStatus(extendedStatus);

        return send(command);
    }

    /**
     * The Zone Enroll Request Command
     *
     * @param zoneType {@link Integer} Zone Type
     * @param manufacturerCode {@link Integer} Manufacturer Code
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> zoneEnrollRequestCommand(Integer zoneType, Integer manufacturerCode) {
        ZoneEnrollRequestCommand command = new ZoneEnrollRequestCommand();

        // Set the fields
        command.setZoneType(zoneType);
        command.setManufacturerCode(manufacturerCode);

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
            case 0: // ZONE_ENROLL_RESPONSE
                return new ZoneEnrollResponse();
            default:
                return null;
        }
    }

    @Override
    public ZclCommand getResponseFromId(int commandId) {
        switch (commandId) {
            case 0: // ZONE_STATUS_CHANGE_NOTIFICATION_COMMAND
                return new ZoneStatusChangeNotificationCommand();
            case 1: // ZONE_ENROLL_REQUEST_COMMAND
                return new ZoneEnrollRequestCommand();
            default:
                return null;
        }
    }
}

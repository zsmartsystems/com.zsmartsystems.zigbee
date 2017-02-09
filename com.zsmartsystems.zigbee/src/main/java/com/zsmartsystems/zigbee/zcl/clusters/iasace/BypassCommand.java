package com.zsmartsystems.zigbee.zcl.clusters.iasace;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

import java.util.List;
import com.zsmartsystems.zigbee.zcl.field.Unsigned8BitInteger;

/**
 * <p>
 * Bypass Command value object class.
 * <p>
 * Cluster: <b>IAS ACE</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the IAS ACE cluster.
 * <p>
 * The IAS ACE cluster defines an interface to the functionality of any Ancillary
 * Control Equipment of the IAS system. Using this cluster, a ZigBee enabled ACE
 * device can access a IAS CIE device and manipulate the IAS system, on behalf of a
 * level-2 user.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class BypassCommand extends ZclCommand {
    /**
     * Number of Zones command message field.
     */
    private Integer numberOfZones;

    /**
     * Zone IDs command message field.
     */
    private List<Unsigned8BitInteger> zoneIDs;

    /**
     * Default constructor.
     */
    public BypassCommand() {
        genericCommand = false;
        clusterId = 1281;
        commandId = 1;
        commandDirection = true;
    }

    /**
     * Gets Number of Zones.
     * @return the Number of Zones
     */
    public Integer getNumberOfZones() {
        return numberOfZones;
    }

    /**
     * Sets Number of Zones.
     * @param numberOfZones the Number of Zones
     */
    public void setNumberOfZones(final Integer numberOfZones) {
        this.numberOfZones = numberOfZones;
    }

    /**
     * Gets Zone IDs.
     * @return the Zone IDs
     */
    public List<Unsigned8BitInteger> getZoneIDs() {
        return zoneIDs;
    }

    /**
     * Sets Zone IDs.
     * @param zoneIDs the Zone IDs
     */
    public void setZoneIDs(final List<Unsigned8BitInteger> zoneIDs) {
        this.zoneIDs = zoneIDs;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(numberOfZones, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(zoneIDs, ZclDataType.N_X_UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        numberOfZones = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        zoneIDs = (List<Unsigned8BitInteger>) deserializer.deserialize(ZclDataType.N_X_UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(super.toString());
        builder.append(", numberOfZones=");
        builder.append(numberOfZones);
        builder.append(", zoneIDs=");
        builder.append(zoneIDs);
        return builder.toString();
    }

}

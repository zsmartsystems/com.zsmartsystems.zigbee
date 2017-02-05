package com.zsmartsystems.zigbee.zcl.clusters;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.ZigBeeDeviceAddress;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.zcl.ZclAttribute;
import com.zsmartsystems.zigbee.zcl.ZclCluster;
import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.clusters.onoff.OffCommand;
import com.zsmartsystems.zigbee.zcl.clusters.onoff.OnCommand;
import com.zsmartsystems.zigbee.zcl.clusters.onoff.ToggleCommand;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * <b>On/Off</b> cluster implementation (<i>Cluster ID 0x0006</i>).
 * <p>
 * Attributes and commands for switching devices between ‘On’ and ‘Off’ states.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class ZclOnOffCluster extends ZclCluster {
    // Cluster ID
    public static final int CLUSTER_ID = 0x0006;

    // Cluster Name
    public static final String CLUSTER_NAME = "On/Off";

    // Attribute constants
    public static final int ATTR_ONOFF = 0x0000;

    // Attribute initialisation
    protected Map<Integer, ZclAttribute> initializeAttributes() {
        Map<Integer, ZclAttribute> attributeMap = new HashMap<Integer, ZclAttribute>(1);

        attributeMap.put(ATTR_ONOFF, new ZclAttribute(0, "OnOff", ZclDataType.BOOLEAN, true, true, false, true));

        return attributeMap;
    }

    /**
     * Default constructor.
     */
    public ZclOnOffCluster(final ZigBeeNetworkManager zigbeeManager, final ZigBeeDeviceAddress zigbeeAddress) {
        super(zigbeeManager, zigbeeAddress, CLUSTER_ID, CLUSTER_NAME);
    }


    /**
     * <p>
     * Get the <i>OnOff</i> attribute [Attribute ID <b>0</b>].
     * <p>
     * <p>
     * The OnOff attribute has the following values: 0 = Off, 1 = On
     * <p>
     * The attribute is of type {@link Boolean}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getOnOffAsync() {
        return read(attributes.get(ATTR_ONOFF));
    }


    /**
     * <p>
     * Synchronously get the <i>OnOff</i> attribute [Attribute ID <b>0</b>].
     * <p>
     * <p>
     * The OnOff attribute has the following values: 0 = Off, 1 = On
     * <p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link Boolean}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Boolean} attribute value, or null on error
     */
    public Boolean getOnOff() {
        return (Boolean) readSync(attributes.get(ATTR_ONOFF));
    }


    /**
     * <p>
     * Configure reporting for the <i>OnOff</i> attribute [Attribute ID <b>0</b>].
     * <p>
     * <p>
     * The OnOff attribute has the following values: 0 = Off, 1 = On
     * <p>
     * The attribute is of type {@link Boolean}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval {@link int} minimum reporting period
     * @param maxInterval {@link int} maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> configOnOffReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return report(ATTR_ONOFF, minInterval, maxInterval, reportableChange);
    }

    /**
     * The Off Command
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> offCommand() {
        OffCommand command = new OffCommand();

        return send(command);
    }

    /**
     * The On Command
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> onCommand() {
        OnCommand command = new OnCommand();

        return send(command);
    }

    /**
     * The Toggle Command
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> toggleCommand() {
        ToggleCommand command = new ToggleCommand();

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
            case 0: // OFF_COMMAND
                return new OffCommand();
            case 1: // ON_COMMAND
                return new OnCommand();
            case 2: // TOGGLE_COMMAND
                return new ToggleCommand();
            default:
                return null;
        }
    }
}

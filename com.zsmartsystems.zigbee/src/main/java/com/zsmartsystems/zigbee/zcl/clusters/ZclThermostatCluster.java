package com.zsmartsystems.zigbee.zcl.clusters;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.ZigBeeDeviceAddress;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.zcl.ZclAttribute;
import com.zsmartsystems.zigbee.zcl.ZclCluster;
import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.clusters.thermostat.SetpointRaiseLowerCommand;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * <b>Thermostat</b> cluster implementation (<i>Cluster ID 0x0201</i>).
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class ZclThermostatCluster extends ZclCluster {
    // Cluster ID
    public static final int CLUSTER_ID = 0x0201;

    // Cluster Name
    public static final String CLUSTER_NAME = "Thermostat";

    // Attribute initialisation
    protected Map<Integer, ZclAttribute> initializeAttributes() {
        Map<Integer, ZclAttribute> attributeMap = new HashMap<Integer, ZclAttribute>(0);


        return attributeMap;
    }

    /**
     * Default constructor.
     */
    public ZclThermostatCluster(final ZigBeeNetworkManager zigbeeManager, final ZigBeeDeviceAddress zigbeeAddress) {
        super(zigbeeManager, zigbeeAddress, CLUSTER_ID, CLUSTER_NAME);
    }


    /**
     * The Setpoint Raise/Lower Command
     *
     * @param mode {@link Integer} Mode
     * @param amount {@link Integer} Amount
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setpointRaiseLowerCommand(Integer mode, Integer amount) {
        SetpointRaiseLowerCommand command = new SetpointRaiseLowerCommand();

        // Set the fields
        command.setMode(mode);
        command.setAmount(amount);

        return send(command);
    }

    @Override
    public ZclCommand getCommandFromId(int commandId) {
        switch (commandId) {
            case 0: // SETPOINT_RAISE_LOWER_COMMAND
                return new SetpointRaiseLowerCommand();
            default:
                return null;
        }
    }
}

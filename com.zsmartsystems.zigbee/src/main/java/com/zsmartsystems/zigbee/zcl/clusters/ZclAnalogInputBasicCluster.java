package com.zsmartsystems.zigbee.zcl.clusters;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.ZigBeeDeviceAddress;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.zcl.ZclAttribute;
import com.zsmartsystems.zigbee.zcl.ZclCluster;
import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * <b>Analog Input (Basic)</b> cluster implementation (<i>Cluster ID 0x000C</i>).
 * Code is auto-generated. Modifications may be overwritten!
 */
public class ZclAnalogInputBasicCluster extends ZclCluster {
    // Cluster ID
    public static final int CLUSTER_ID = 0x000C;

    // Cluster Name
    public static final String CLUSTER_NAME = "Analog Input (Basic)";

    // Attribute initialisation
    protected Map<Integer, ZclAttribute> initializeAttributes() {
        Map<Integer, ZclAttribute> attributeMap = new HashMap<Integer, ZclAttribute>(0);


        return attributeMap;
    }

    /**
     * Default constructor.
     */
    public ZclAnalogInputBasicCluster(final ZigBeeNetworkManager zigbeeManager, final ZigBeeDeviceAddress zigbeeAddress) {
        super(zigbeeManager, zigbeeAddress, CLUSTER_ID, CLUSTER_NAME);
    }

}

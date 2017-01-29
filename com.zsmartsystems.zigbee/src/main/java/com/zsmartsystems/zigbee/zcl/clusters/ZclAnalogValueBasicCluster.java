package com.zsmartsystems.zigbee.zcl.clusters;

import com.zsmartsystems.zigbee.ZigBeeDeviceAddress;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.zcl.ZclAttribute;
import com.zsmartsystems.zigbee.zcl.ZclCluster;
import java.util.HashMap;
import java.util.Map;

/**
 * <b>Analog Value (Basic)</b> cluster implementation (<i>Cluster ID 0x000E</i>).
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class ZclAnalogValueBasicCluster extends ZclCluster {
    // Cluster ID
    public static final int CLUSTER_ID = 0x000E;

    // Cluster Name
    public static final String CLUSTER_NAME = "Analog Value (Basic)";

    // Attribute initialisation
    protected Map<Integer, ZclAttribute> initializeAttributes() {
        Map<Integer, ZclAttribute> attributeMap = new HashMap<Integer, ZclAttribute>(0);


        return attributeMap;
    }

    /**
     * Default constructor.
     */
    public ZclAnalogValueBasicCluster(final ZigBeeNetworkManager zigbeeManager, final ZigBeeDeviceAddress zigbeeAddress) {
        super(zigbeeManager, zigbeeAddress, CLUSTER_ID, CLUSTER_NAME);
    }

}

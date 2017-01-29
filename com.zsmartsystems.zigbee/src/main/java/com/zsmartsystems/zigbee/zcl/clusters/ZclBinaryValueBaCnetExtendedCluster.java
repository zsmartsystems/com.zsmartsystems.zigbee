package com.zsmartsystems.zigbee.zcl.clusters;

import com.zsmartsystems.zigbee.ZigBeeDeviceAddress;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.zcl.ZclAttribute;
import com.zsmartsystems.zigbee.zcl.ZclCluster;
import java.util.HashMap;
import java.util.Map;

/**
 * <b>Binary Value (BACnet Extended)</b> cluster implementation (<i>Cluster ID 0x060D</i>).
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class ZclBinaryValueBaCnetExtendedCluster extends ZclCluster {
    // Cluster ID
    public static final int CLUSTER_ID = 0x060D;

    // Cluster Name
    public static final String CLUSTER_NAME = "Binary Value (BACnet Extended)";

    // Attribute initialisation
    protected Map<Integer, ZclAttribute> initializeAttributes() {
        Map<Integer, ZclAttribute> attributeMap = new HashMap<Integer, ZclAttribute>(0);


        return attributeMap;
    }

    /**
     * Default constructor.
     */
    public ZclBinaryValueBaCnetExtendedCluster(final ZigBeeNetworkManager zigbeeManager, final ZigBeeDeviceAddress zigbeeAddress) {
        super(zigbeeManager, zigbeeAddress, CLUSTER_ID, CLUSTER_NAME);
    }

}

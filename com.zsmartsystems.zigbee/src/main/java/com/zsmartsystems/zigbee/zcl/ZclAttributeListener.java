package com.zsmartsystems.zigbee.zcl;

/**
 * Attribute update listener. Listeners are called when an {@link ZclAttribute} for the Cluster is updated.
 *
 * @author Chris Jackson
 *
 */
public interface ZclAttributeListener {
    /**
     * Called when an attribute is updated
     *
     * @param attribute the {@link ZclAttribute} that has been updated
     */
    void attributeUpdated(ZclAttribute attribute);

}

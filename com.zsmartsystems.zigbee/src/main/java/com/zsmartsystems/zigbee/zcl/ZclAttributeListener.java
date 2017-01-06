package com.zsmartsystems.zigbee.zcl;

/**
 * Attribute update listener
 * 
 * @author Chris Jackson
 *
 */
public interface ZclAttributeListener {
    /**
     * Called when an attribute is updated
     */
    void AttributeUpdated(ZclAttribute attribute);

}

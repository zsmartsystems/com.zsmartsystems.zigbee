package com.zsmartsystems.zigbee.dongle.ember.ezsp.structure;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspData;

/**
 * Abstract class for Ember Structure
 * 
 * @author Chris Jackson
 *
 */
public abstract class EmberStructure extends EzspData {

    EmberStructure() {
    }

    EmberStructure(int[] inBuffer, int inPosition) {
        this.buffer = inBuffer;
        this.position = inPosition;
    }
}

package com.zsmartsystems.zigbee.zcl;

import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * This class holds the definition, and current value, of a field in a {@link ZclCommand}
 *
 * @author Chris Jackson
 *
 */
public class ZclField {
    int sequence;
    String label;
    ZclDataType type;
    Object value;

    public ZclField(int sequence, String label, ZclDataType type) {
        this.sequence = sequence;
        this.label = label;
        this.type = type;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public ZclDataType getDataType() {
        return type;
    }

    public void setDataType(ZclDataType type) {
        this.type = type;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ZclField [label=" + label + ", value=" + value + "]";
    }

}

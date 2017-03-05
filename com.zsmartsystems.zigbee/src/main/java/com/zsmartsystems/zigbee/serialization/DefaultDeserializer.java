package com.zsmartsystems.zigbee.serialization;

import java.util.ArrayList;
import java.util.List;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zdo.descriptors.NeighborTable;
import com.zsmartsystems.zigbee.zdo.descriptors.NodeDescriptor;
import com.zsmartsystems.zigbee.zdo.descriptors.PowerDescriptor;
import com.zsmartsystems.zigbee.zdo.descriptors.RoutingTable;
import com.zsmartsystems.zigbee.zdo.descriptors.SimpleDescriptor;

/**
 * The default implementation of the {@link ZigBeeDeserializer}
 *
 * @author Chris Jackson
 */
public class DefaultDeserializer implements ZigBeeDeserializer {
    private int index = 0;
    private int[] payload;

    public DefaultDeserializer(int[] payload) {
        this.payload = payload;
        this.index = 0;
    }

    @Override
    public boolean endOfStream() {
        return index >= payload.length;
    }

    @Override
    public int getPosition() {
        return index;
    }

    @Override
    public int getSize() {
        return payload.length;
    }

    @Override
    public void skip(int n) {
        index += n;
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public Object readZigBeeType(ZclDataType type) {
        Object[] value = new Object[1];
        switch (type) {
            case BOOLEAN:
                value[0] = payload[index++] == 0 ? false : true;
                break;
            case CHARACTER_STRING:
            case OCTET_STRING:
                int size = payload[index++];
                value[0] = new String(payload, index, size);
                index += size;
                break;
            case ENDPOINT:
            case BITMAP_8_BIT:
            case DATA_8_BIT:
            case ENUMERATION_8_BIT:
                value[0] = Integer.valueOf((byte) payload[index++] & 0xFF);
                break;
            case IEEE_ADDRESS:
                long result = 0;
                for (int i = 7; i >= 0; i--) {
                    result <<= 8;
                    result |= (payload[index + i] & 0xFF);
                }
                index += 8;
                value[0] = new IeeeAddress(result);
                break;
            case N_X_ATTRIBUTE_IDENTIFIER:
                break;
            case N_X_ATTRIBUTE_INFORMATION:
                break;
            case N_X_ATTRIBUTE_RECORD:
                break;
            case N_X_ATTRIBUTE_REPORT:
                break;
            case N_X_ATTRIBUTE_REPORTING_CONFIGURATION_RECORD:
                break;
            case N_X_ATTRIBUTE_SELECTOR:
                break;
            case N_X_ATTRIBUTE_STATUS_RECORD:
                break;
            case N_X_EXTENSION_FIELD_SET:
                break;
            case N_X_NEIGHBORS_INFORMATION:
                break;
            case N_X_READ_ATTRIBUTE_STATUS_RECORD:
                break;
            case N_X_NWK_ADDRESS:
            case N_X_UNSIGNED_16_BIT_INTEGER:
                int cntN16 = Integer.valueOf((byte) payload[index++] & 0xFF);
                List<Integer> arrayN16 = new ArrayList<Integer>(cntN16);
                for (int arrayIndex = 0; arrayIndex < cntN16; arrayIndex++) {
                    short s = (short) (payload[index++] + (payload[index++] << 8));
                    arrayN16.add(Integer.valueOf(s));
                }
                value[0] = arrayN16;
                break;
            case N_X_UNSIGNED_8_BIT_INTEGER:
                int cntN8 = Integer.valueOf((byte) payload[index++] & 0xFF);
                List<Integer> arrayN8 = new ArrayList<Integer>(cntN8);
                for (int arrayIndex = 0; arrayIndex < cntN8; arrayIndex++) {
                    arrayN8.add(Integer.valueOf(payload[index++]));
                }
                value[0] = arrayN8;
                break;
            case N_X_WRITE_ATTRIBUTE_RECORD:
                break;
            case N_X_WRITE_ATTRIBUTE_STATUS_RECORD:
                break;
            case NWK_ADDRESS:
            case BITMAP_16_BIT:
            case ENUMERATION_16_BIT:
            case SIGNED_16_BIT_INTEGER:
            case UNSIGNED_16_BIT_INTEGER:
                short s = (short) (payload[index++] + (payload[index++] << 8));
                if (type == ZclDataType.SIGNED_16_BIT_INTEGER) {
                    value[0] = Integer.valueOf(s);
                } else {
                    value[0] = Integer.valueOf(s & 0xFFFF);
                }
                break;
            case SIGNED_32_BIT_INTEGER:
            case UNSIGNED_32_BIT_INTEGER:
                value[0] = payload[index++] + (payload[index++] << 8) + (payload[index++] << 16)
                        + (payload[index++] << 24);
                break;
            case SIGNED_8_BIT_INTEGER:
                value[0] = Integer.valueOf((byte) payload[index++]);
                break;
            case UNSIGNED_8_BIT_INTEGER:
                value[0] = Integer.valueOf((byte) payload[index++] & 0xFF);
                break;
            case UTCTIME:
                break;
            case ROUTING_TABLE:
                RoutingTable routingTable = new RoutingTable();
                routingTable.deserialize(this);
                value[0] = routingTable;
                break;
            case NEIGHBOR_TABLE:
                NeighborTable neighborTable = new NeighborTable();
                neighborTable.deserialize(this);
                value[0] = neighborTable;
                break;
            case NODE_DESCRIPTOR:
                NodeDescriptor nodeDescriptor = new NodeDescriptor();
                nodeDescriptor.deserialize(this);
                value[0] = nodeDescriptor;
                break;
            case POWER_DESCRIPTOR:
                PowerDescriptor powerDescriptor = new PowerDescriptor();
                powerDescriptor.deserialize(this);
                value[0] = powerDescriptor;
                break;
            case SIMPLE_DESCRIPTOR:
                SimpleDescriptor simpleDescriptor = new SimpleDescriptor();
                simpleDescriptor.deserialize(this);
                value[0] = simpleDescriptor;
                break;
            default:
                throw new IllegalArgumentException("No reader defined in " + ZigBeeDeserializer.class.getSimpleName()
                        + " for " + type.toString() + " (" + type.getId() + ")");
        }
        return value[0];
    }
}

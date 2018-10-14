/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.serialization;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.zsmartsystems.zigbee.ExtendedPanId;
import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.zcl.ZclStatus;
import com.zsmartsystems.zigbee.zcl.field.ByteArray;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zdo.ZdoStatus;
import com.zsmartsystems.zigbee.zdo.field.BindingTable;
import com.zsmartsystems.zigbee.zdo.field.NeighborTable;
import com.zsmartsystems.zigbee.zdo.field.NodeDescriptor;
import com.zsmartsystems.zigbee.zdo.field.PowerDescriptor;
import com.zsmartsystems.zigbee.zdo.field.RoutingTable;
import com.zsmartsystems.zigbee.zdo.field.SimpleDescriptor;

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
    public boolean isEndOfStream() {
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
        if (index == payload.length) {
            return null;
        }

        Object[] value = new Object[1];
        switch (type) {
            case BOOLEAN:
                value[0] = payload[index++] == 0 ? false : true;
                break;
            case OCTET_STRING:
                int octetSize = payload[index++];
                value[0] = new ByteArray(payload, index, index + octetSize);
                index += octetSize;
                break;
            case CHARACTER_STRING:
                int stringSize = payload[index++];
                if (stringSize == 255) {
                    value[0] = null;
                    break;
                }
                byte[] bytes = new byte[stringSize];
                int length = stringSize;
                for (int cnt = 0; cnt < stringSize; cnt++) {
                    bytes[cnt] = (byte) payload[index + cnt];
                    if (payload[index + cnt] == 0) {
                        length = cnt;
                        break;
                    }
                }
                try {
                    value[0] = new String(Arrays.copyOfRange(bytes, 0, length), "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    value[0] = null;
                    break;
                }
                index += stringSize;
                break;
            case ENDPOINT:
            case BITMAP_8_BIT:
            case DATA_8_BIT:
            case ENUMERATION_8_BIT:
                value[0] = Integer.valueOf((byte) payload[index++] & 0xFF);
                break;
            case EXTENDED_PANID:
                int[] panId = new int[8];
                for (int iCnt = 7; iCnt >= 0; iCnt--) {
                    panId[iCnt] = payload[index + iCnt];
                }
                index += 8;
                value[0] = new ExtendedPanId(panId);
                break;
            case IEEE_ADDRESS:
                int[] address = new int[8];
                for (int iCnt = 7; iCnt >= 0; iCnt--) {
                    address[iCnt] = payload[index + iCnt];
                }
                index += 8;
                value[0] = new IeeeAddress(address);
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
            case N_X_UNSIGNED_16_BIT_INTEGER:
                int cntN16 = Integer.valueOf((byte) payload[index++] & 0xFF);
                List<Integer> arrayN16 = new ArrayList<Integer>(cntN16);
                for (int arrayIndex = 0; arrayIndex < cntN16; arrayIndex++) {
                    arrayN16.add(Integer.valueOf(payload[index++] + ((payload[index++] << 8) & 0xffff)));
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
            case X_UNSIGNED_8_BIT_INTEGER:
                int cntX8 = payload.length - index;
                List<Integer> arrayX8 = new ArrayList<Integer>(cntX8);
                for (int arrayIndex = 0; arrayIndex < cntX8; arrayIndex++) {
                    arrayX8.add(Integer.valueOf(payload[index++]));
                }
                value[0] = arrayX8;
                break;
            case N_X_ATTRIBUTE_IDENTIFIER:
                int cntX16 = (payload.length - index) / 2;
                List<Integer> arrayX16 = new ArrayList<Integer>(cntX16);
                for (int arrayIndex = 0; arrayIndex < cntX16; arrayIndex++) {
                    arrayX16.add(Integer.valueOf(payload[index++]));
                }
                value[0] = arrayX16;
                break;
            case UNSIGNED_8_BIT_INTEGER_ARRAY:
                int cnt8Array = payload.length - index;
                int[] intarray8 = new int[cnt8Array];
                for (int arrayIndex = 0; arrayIndex < cnt8Array; arrayIndex++) {
                    intarray8[arrayIndex] = payload[index++];
                }
                value[0] = intarray8;
                break;
            case N_X_WRITE_ATTRIBUTE_RECORD:
                break;
            case N_X_WRITE_ATTRIBUTE_STATUS_RECORD:
                break;
            case CLUSTERID:
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
            case UNSIGNED_24_BIT_INTEGER:
                value[0] = payload[index++] + (payload[index++] << 8) + (payload[index++] << 16);
                break;
            case BITMAP_32_BIT:
            case SIGNED_32_BIT_INTEGER:
            case UNSIGNED_32_BIT_INTEGER:
                value[0] = payload[index++] + (payload[index++] << 8) + (payload[index++] << 16)
                        + (payload[index++] << 24);
                break;
            case UNSIGNED_48_BIT_INTEGER:
                value[0] = (payload[index++]) + ((long) (payload[index++]) << 8) + ((long) (payload[index++]) << 16)
                        + ((long) (payload[index++]) << 24) + ((long) (payload[index++]) << 32)
                        + ((long) (payload[index++]) << 40);
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
            case BINDING_TABLE:
                BindingTable bindingTable = new BindingTable();
                bindingTable.deserialize(this);
                value[0] = bindingTable;
                break;
            case SIMPLE_DESCRIPTOR:
                SimpleDescriptor simpleDescriptor = new SimpleDescriptor();
                simpleDescriptor.deserialize(this);
                value[0] = simpleDescriptor;
                break;
            case ZCL_STATUS:
                value[0] = ZclStatus.getStatus(payload[index++]);
                break;
            case ZDO_STATUS:
                value[0] = ZdoStatus.getStatus(payload[index++]);
                break;
            case ZIGBEE_DATA_TYPE:
                value[0] = ZclDataType.getType(payload[index++]);
                break;
            case BYTE_ARRAY:
                int cntB8 = Integer.valueOf((byte) payload[index++] & 0xFF);
                byte[] arrayB8 = new byte[cntB8];
                for (int arrayIndex = 0; arrayIndex < cntB8; arrayIndex++) {
                    arrayB8[arrayIndex] = (byte) (payload[index++] & 0xff);
                }
                value[0] = new ByteArray(arrayB8);
                break;
            default:
                throw new IllegalArgumentException("No reader defined in " + ZigBeeDeserializer.class.getSimpleName()
                        + " for " + type.toString() + " (" + type.getId() + ")");
        }
        return value[0];
    }
}

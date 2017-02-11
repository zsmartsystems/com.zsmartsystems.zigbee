package com.zsmartsystems.zigbee.zdo.descriptors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.zsmartsystems.zigbee.zdo.descriptors.NodeDescriptor.FrequencyBandType;
import com.zsmartsystems.zigbee.zdo.descriptors.NodeDescriptor.LogicalType;
import com.zsmartsystems.zigbee.zdo.descriptors.NodeDescriptor.MacCapabilitiesType;
import com.zsmartsystems.zigbee.zdo.descriptors.NodeDescriptor.ServerCapabilitiesType;

/**
 *
 * @author Chris Jackson
 *
 */
public class NodeDescriptorTest {

    @Test
    public void testNodeDescriptor() {
        NodeDescriptor descriptor = new NodeDescriptor(0, 3333, 74, true, 6666, 0, 6, 4444, true, 8);

        assertTrue(descriptor.isComplexDescriptorAvailable());
        assertTrue(descriptor.isUserDescriptorAvailable());
        assertEquals(4444, descriptor.getTransferSize());
        assertEquals(6666, descriptor.getManufacturerCode());
        assertEquals(3333, descriptor.getBufferSize());
        assertTrue(descriptor.getFrequencyBands().contains(FrequencyBandType.FREQ_2400_MHZ));
        assertEquals(LogicalType.COORDINATOR, descriptor.getLogicalType());
        assertEquals(0, descriptor.getApsFlags());
        assertTrue(descriptor.getServerCapabilities().contains(ServerCapabilitiesType.PRIMARY_BINDING_TABLE_CACHE));
        assertTrue(descriptor.getServerCapabilities().contains(ServerCapabilitiesType.BACKUP_TRUST_CENTER));
        assertTrue(descriptor.getMacCapabilities().contains(MacCapabilitiesType.SECURITY_CAPABLE));
        assertTrue(descriptor.getMacCapabilities().contains(MacCapabilitiesType.RECEIVER_ON_WHEN_IDLE));
        assertTrue(descriptor.getMacCapabilities().contains(MacCapabilitiesType.FULL_FUNCTION_DEVICE));
    }
}

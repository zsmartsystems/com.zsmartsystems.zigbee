/**
 * Copyright (c) 2016-2024 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.zsmartsystems.zigbee.IeeeAddress;
import org.junit.Before;
import org.junit.Test;

import com.zsmartsystems.zigbee.ZigBeeAddress;
import com.zsmartsystems.zigbee.ZigBeeEndpointAddress;
import com.zsmartsystems.zigbee.zcl.clusters.general.DefaultResponse;
import com.zsmartsystems.zigbee.zcl.clusters.onoff.OnCommand;
import com.zsmartsystems.zigbee.zdo.ZdoCommand;
import com.zsmartsystems.zigbee.zdo.command.DeviceAnnounce;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZclResponseMatcherTest {
    
    private ZclTransactionMatcher matcher;
    
    @Before
    public void setup() {
        matcher = new ZclTransactionMatcher();
    }
    
    @Test
    public void testMatch() {
        ZclCommand request = createOnCommand(new ZigBeeEndpointAddress(0,1), new ZigBeeEndpointAddress(1234,5), 5);
        ZclCommand response = createDefaultResponse(request.getDestinationAddress(), request.getSourceAddress(), request.getClusterId(), 5);
        
        assertTrue(matcher.isTransactionMatch(request, response));
    }
    
    @Test
    public void testNoTransactionIDs() {
        ZclCommand request = createOnCommand(new ZigBeeEndpointAddress(0,1), new ZigBeeEndpointAddress(1234,5), null);
        ZclCommand response = createDefaultResponse(request.getDestinationAddress(), request.getSourceAddress(), request.getClusterId(), null);
        
        assertFalse(matcher.isTransactionMatch(request, response));
    }
    
    @Test
    public void testNonMatchingTransactionIds() {
        ZclCommand request = createOnCommand(new ZigBeeEndpointAddress(0,1), new ZigBeeEndpointAddress(1234,5), 5);
        ZclCommand response = createDefaultResponse(request.getDestinationAddress(), request.getSourceAddress(), request.getClusterId(), 6);
        
        assertFalse(matcher.isTransactionMatch(request, response));
    }
    
    @Test
    public void testZdoResponse() {
        ZclCommand request = createOnCommand(new ZigBeeEndpointAddress(0,1), new ZigBeeEndpointAddress(1234,5), 5);
        ZdoCommand response = new DeviceAnnounce(123, new IeeeAddress("0123456789123456"), null);
        
        assertFalse(matcher.isTransactionMatch(request, response));
    }
    
    @Test
    public void testNonMatchingAddresses() {
        ZclCommand request = createOnCommand(new ZigBeeEndpointAddress(0,1), new ZigBeeEndpointAddress(1234,5), 5);
        ZclCommand response = createDefaultResponse(new ZigBeeEndpointAddress(1234,6), request.getSourceAddress(), request.getClusterId(), 5);
        
        assertFalse(matcher.isTransactionMatch(request, response));
    }
    
    @Test
    public void testNonMatchingClusters() {
        ZclCommand request = createOnCommand(new ZigBeeEndpointAddress(0,1), new ZigBeeEndpointAddress(1234,5), 5);
        ZclCommand response = createDefaultResponse(request.getDestinationAddress(), request.getSourceAddress(), request.getClusterId(), 6);
        
        assertFalse(matcher.isTransactionMatch(request, response));
    }
    
    private ZclCommand createOnCommand(ZigBeeAddress source, ZigBeeAddress dest, Integer transactionId) {
        ZclCommand zclCommand = new OnCommand();
        zclCommand.setSourceAddress(source);
        zclCommand.setDestinationAddress(dest);
        if (transactionId != null) {
            zclCommand.setTransactionId(transactionId);
        }
        return zclCommand;
    }
    
    private ZclCommand createDefaultResponse(ZigBeeAddress source, ZigBeeAddress dest, Integer clusterId, Integer transactionId) {
        ZclCommand zclResponse = new DefaultResponse(DefaultResponse.COMMAND_ID, ZclStatus.SUCCESS);
        zclResponse.setSourceAddress(source);
        zclResponse.setDestinationAddress(dest);
        zclResponse.setClusterId(clusterId);
        if (transactionId != null) {
            zclResponse.setTransactionId(transactionId);
        }
        return zclResponse; 
    }
}

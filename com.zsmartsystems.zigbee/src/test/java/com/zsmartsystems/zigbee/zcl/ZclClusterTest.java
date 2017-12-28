/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import com.zsmartsystems.zigbee.CommandResponseMatcher;
import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.ZigBeeCommand;
import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeEndpointAddress;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNode;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportTransmit;
import com.zsmartsystems.zigbee.zcl.clusters.ZclLevelControlCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclOnOffCluster;
import com.zsmartsystems.zigbee.zcl.clusters.general.ConfigureReportingCommand;
import com.zsmartsystems.zigbee.zcl.clusters.general.ReadReportingConfigurationCommand;
import com.zsmartsystems.zigbee.zcl.field.AttributeRecord;
import com.zsmartsystems.zigbee.zcl.field.AttributeReportingConfigurationRecord;
import com.zsmartsystems.zigbee.zdo.command.BindRequest;
import com.zsmartsystems.zigbee.zdo.command.UnbindRequest;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZclClusterTest {
    ZigBeeTransportTransmit mockedTransport;
    ZigBeeNetworkManager networkManager;
    ArgumentCaptor<ZigBeeCommand> commandCapture;
    ArgumentCaptor<CommandResponseMatcher> matcherCapture;

    private void createNetworkManager() {
        mockedTransport = Mockito.mock(ZigBeeTransportTransmit.class);
        networkManager = Mockito.mock(ZigBeeNetworkManager.class);
        commandCapture = ArgumentCaptor.forClass(ZigBeeCommand.class);
        matcherCapture = ArgumentCaptor.forClass(CommandResponseMatcher.class);
        Mockito.when(networkManager.unicast(commandCapture.capture(), matcherCapture.capture())).thenReturn(null);
    }

    @Test
    public void bind() {
        createNetworkManager();

        ZigBeeNode node = new ZigBeeNode(networkManager, new IeeeAddress());
        node.setNetworkAddress(1234);
        ZigBeeEndpoint device = new ZigBeeEndpoint(networkManager, node, 5);
        ZclCluster cluster = new ZclOnOffCluster(networkManager, device);
        cluster.bind(new IeeeAddress("1234567890ABCDEF"), 11);
        assertEquals(1, commandCapture.getAllValues().size());
        ZigBeeCommand command = commandCapture.getValue();
        assertNotNull(command);
        System.out.println(command);
        assertTrue(command instanceof BindRequest);
        BindRequest bindCommand = (BindRequest) command;
        assertEquals(new ZigBeeEndpointAddress(1234, 0), bindCommand.getDestinationAddress());
        assertEquals(new IeeeAddress("1234567890ABCDEF"), bindCommand.getDstAddress());
        assertEquals(Integer.valueOf(5), bindCommand.getSrcEndpoint());
        assertEquals(Integer.valueOf(11), bindCommand.getDstEndpoint());
        assertEquals(Integer.valueOf(3), bindCommand.getDstAddrMode());
        assertEquals(Integer.valueOf(0x0021), bindCommand.getClusterId());
        assertEquals(Integer.valueOf(6), bindCommand.getBindCluster());
    }

    @Test
    public void unbind() {
        createNetworkManager();

        ZigBeeNode node = new ZigBeeNode(networkManager, new IeeeAddress());
        node.setNetworkAddress(1234);
        ZigBeeEndpoint device = new ZigBeeEndpoint(networkManager, node, 5);
        ZclCluster cluster = new ZclOnOffCluster(networkManager, device);
        cluster.unbind(new IeeeAddress("1234567890ABCDEF"), 11);
        assertEquals(1, commandCapture.getAllValues().size());
        ZigBeeCommand command = commandCapture.getValue();
        assertNotNull(command);
        System.out.println(command);
        assertTrue(command instanceof UnbindRequest);
        UnbindRequest unbindCommand = (UnbindRequest) command;
        assertEquals(new ZigBeeEndpointAddress(1234, 0), unbindCommand.getDestinationAddress());
        assertEquals(new IeeeAddress("1234567890ABCDEF"), unbindCommand.getDstAddress());
        assertEquals(Integer.valueOf(5), unbindCommand.getSrcEndpoint());
        assertEquals(Integer.valueOf(11), unbindCommand.getDstEndpoint());
        assertEquals(Integer.valueOf(3), unbindCommand.getDstAddrMode());
        assertEquals(Integer.valueOf(0x0022), unbindCommand.getClusterId());
        assertEquals(Integer.valueOf(6), unbindCommand.getBindCluster());
    }

    @Test
    public void getReporting() {
        createNetworkManager();

        ZigBeeNode node = new ZigBeeNode(networkManager, new IeeeAddress());
        node.setNetworkAddress(1234);
        ZigBeeEndpoint device = new ZigBeeEndpoint(networkManager, node, 5);
        ZclCluster cluster = new ZclOnOffCluster(networkManager, device);
        ZclAttribute attribute = cluster.getAttribute(0);
        cluster.getReporting(attribute);
        assertEquals(1, commandCapture.getAllValues().size());
        ZigBeeCommand command = commandCapture.getValue();
        assertNotNull(command);
        System.out.println(command);
        assertTrue(command instanceof ReadReportingConfigurationCommand);
        ReadReportingConfigurationCommand cfgCommand = (ReadReportingConfigurationCommand) command;
        assertEquals(1, cfgCommand.getRecords().size());
        AttributeRecord record = cfgCommand.getRecords().get(0);
        assertEquals(0, record.getAttributeIdentifier());
        assertEquals(0, record.getDirection());
    }

    @Test
    public void setReporting() {
        createNetworkManager();

        ZigBeeNode node = new ZigBeeNode(networkManager, new IeeeAddress());
        node.setNetworkAddress(1234);
        ZigBeeEndpoint device = new ZigBeeEndpoint(networkManager, node, 5);
        ZclCluster cluster = new ZclOnOffCluster(networkManager, device);
        ZclAttribute attribute = cluster.getAttribute(0);
        cluster.setReporting(attribute, 22, 33);
        assertEquals(1, commandCapture.getAllValues().size());
        ZigBeeCommand command = commandCapture.getValue();
        assertNotNull(command);
        System.out.println(command);
        assertTrue(command instanceof ConfigureReportingCommand);
        ConfigureReportingCommand cfgCommand = (ConfigureReportingCommand) command;
        assertEquals(1, cfgCommand.getRecords().size());
        AttributeReportingConfigurationRecord record = cfgCommand.getRecords().get(0);
        assertEquals(0, record.getAttributeIdentifier());
        assertEquals(0, record.getDirection());
    }

    @Test
    public void getClusterId() {
        createNetworkManager();

        ZigBeeNode node = new ZigBeeNode(networkManager, new IeeeAddress());
        node.setNetworkAddress(1234);
        ZigBeeEndpoint device = new ZigBeeEndpoint(networkManager, node, 5);
        ZclCluster cluster = new ZclOnOffCluster(networkManager, device);
        assertEquals(Integer.valueOf(6), cluster.getClusterId());
    }

    @Test
    public void getClusterName() {
        createNetworkManager();

        ZigBeeNode node = new ZigBeeNode(networkManager, new IeeeAddress());
        node.setNetworkAddress(1234);
        ZigBeeEndpoint device = new ZigBeeEndpoint(networkManager, node, 5);
        ZclCluster cluster = new ZclLevelControlCluster(networkManager, device);
        assertEquals("Level Control", cluster.getClusterName());
    }

}

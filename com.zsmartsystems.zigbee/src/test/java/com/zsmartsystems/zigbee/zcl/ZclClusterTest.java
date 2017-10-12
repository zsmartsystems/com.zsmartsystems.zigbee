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

import com.zsmartsystems.zigbee.ZigBeeCommand;
import com.zsmartsystems.zigbee.CommandResponseMatcher;
import com.zsmartsystems.zigbee.ZigBeeEndpointAddress;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportTransmit;
import com.zsmartsystems.zigbee.zcl.clusters.ZclLevelControlCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclOnOffCluster;
import com.zsmartsystems.zigbee.zcl.clusters.general.ConfigureReportingCommand;
import com.zsmartsystems.zigbee.zcl.clusters.general.ReadReportingConfigurationCommand;
import com.zsmartsystems.zigbee.zcl.field.AttributeRecord;
import com.zsmartsystems.zigbee.zcl.field.AttributeReportingConfigurationRecord;

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
    public void getReporting() {
        createNetworkManager();

        ZigBeeDevice device = new ZigBeeDevice(networkManager);
        device.setDeviceAddress(new ZigBeeDeviceAddress(1));
        ZclCluster cluster = new ZclOnOffCluster(networkManager, device);
        ZclAttribute attribute = cluster.getAttribute(0);
        cluster.getReporting(attribute);
        assertEquals(1, commandCapture.getAllValues().size());
        ZigBeeCommand command = commandCapture.getValue();
        assertNotNull(command);
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

        ZigBeeDevice device = new ZigBeeDevice(networkManager);
        device.setDeviceAddress(new ZigBeeDeviceAddress(1));
        ZclCluster cluster = new ZclOnOffCluster(networkManager, device);
        ZclAttribute attribute = cluster.getAttribute(0);
        cluster.setReporting(attribute, 22, 33);
        assertEquals(1, commandCapture.getAllValues().size());
        ZigBeeCommand command = commandCapture.getValue();
        assertNotNull(command);
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

        ZigBeeDevice device = new ZigBeeDevice(networkManager);
        device.setDeviceAddress(new ZigBeeDeviceAddress(1));
        ZclCluster cluster = new ZclOnOffCluster(networkManager, device);
        assertEquals(Integer.valueOf(6), cluster.getClusterId());
    }

    @Test
    public void getClusterName() {
        createNetworkManager();

        ZigBeeDevice device = new ZigBeeDevice(networkManager);
        device.setDeviceAddress(new ZigBeeDeviceAddress(1));
        ZclCluster cluster = new ZclLevelControlCluster(networkManager, device);
        assertEquals("Level Control", cluster.getClusterName());
    }
}

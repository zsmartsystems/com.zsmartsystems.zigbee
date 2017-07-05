package com.zsmartsystems.zigbee.zcl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import com.zsmartsystems.zigbee.Command;
import com.zsmartsystems.zigbee.CommandResponseMatcher;
import com.zsmartsystems.zigbee.ZigBeeDeviceAddress;
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
    ArgumentCaptor<Command> commandCapture;
    ArgumentCaptor<CommandResponseMatcher> matcherCapture;

    private void createNetworkManager() {
        mockedTransport = Mockito.mock(ZigBeeTransportTransmit.class);
        networkManager = Mockito.mock(ZigBeeNetworkManager.class);
        commandCapture = ArgumentCaptor.forClass(Command.class);
        matcherCapture = ArgumentCaptor.forClass(CommandResponseMatcher.class);
        Mockito.when(networkManager.unicast(commandCapture.capture(), matcherCapture.capture())).thenReturn(null);
    }

    @Test
    public void getReporting() {
        createNetworkManager();

        ZclCluster cluster = new ZclOnOffCluster(networkManager, new ZigBeeDeviceAddress(1));
        ZclAttribute attribute = cluster.getAttribute(0);
        cluster.getReporting(attribute);
        assertEquals(1, commandCapture.getAllValues().size());
        Command command = commandCapture.getValue();
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

        ZclCluster cluster = new ZclOnOffCluster(networkManager, new ZigBeeDeviceAddress(1));
        ZclAttribute attribute = cluster.getAttribute(0);
        cluster.setReporting(attribute, 22, 33);
        assertEquals(1, commandCapture.getAllValues().size());
        Command command = commandCapture.getValue();
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

        ZclCluster cluster = new ZclOnOffCluster(networkManager, new ZigBeeDeviceAddress(1));
        assertEquals(Integer.valueOf(6), cluster.getClusterId());
    }

    @Test
    public void getClusterName() {
        createNetworkManager();

        ZclCluster cluster = new ZclLevelControlCluster(networkManager, new ZigBeeDeviceAddress(1));
        assertEquals("Level Control", cluster.getClusterName());
    }
}

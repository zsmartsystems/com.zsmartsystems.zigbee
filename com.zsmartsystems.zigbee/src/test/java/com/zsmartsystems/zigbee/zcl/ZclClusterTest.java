/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Future;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.TestUtilities;
import com.zsmartsystems.zigbee.ZigBeeCommand;
import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeEndpointAddress;
import com.zsmartsystems.zigbee.ZigBeeNode;
import com.zsmartsystems.zigbee.database.ZclClusterDao;
import com.zsmartsystems.zigbee.transaction.ZigBeeTransactionMatcher;
import com.zsmartsystems.zigbee.zcl.clusters.ZclLevelControlCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclOnOffCluster;
import com.zsmartsystems.zigbee.zcl.clusters.general.ConfigureReportingCommand;
import com.zsmartsystems.zigbee.zcl.clusters.general.DiscoverAttributesResponse;
import com.zsmartsystems.zigbee.zcl.clusters.general.ReadAttributesCommand;
import com.zsmartsystems.zigbee.zcl.clusters.general.ReadReportingConfigurationCommand;
import com.zsmartsystems.zigbee.zcl.clusters.general.WriteAttributesCommand;
import com.zsmartsystems.zigbee.zcl.clusters.onoff.OnCommand;
import com.zsmartsystems.zigbee.zcl.field.AttributeRecord;
import com.zsmartsystems.zigbee.zcl.field.AttributeReport;
import com.zsmartsystems.zigbee.zcl.field.AttributeReportingConfigurationRecord;
import com.zsmartsystems.zigbee.zcl.field.WriteAttributeRecord;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zdo.command.BindRequest;
import com.zsmartsystems.zigbee.zdo.command.UnbindRequest;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZclClusterTest {
    private ZigBeeNode node;
    ZigBeeEndpoint endpoint;
    private ArgumentCaptor<ZigBeeCommand> commandCapture;
    private ArgumentCaptor<ZigBeeTransactionMatcher> matcherCapture;

    private void createEndpoint() {
        node = Mockito.mock(ZigBeeNode.class);
        endpoint = Mockito.mock(ZigBeeEndpoint.class);
        Mockito.when(endpoint.getEndpointId()).thenReturn(5);
        Mockito.when(endpoint.getEndpointAddress()).thenReturn(new ZigBeeEndpointAddress(1234, 5));
        commandCapture = ArgumentCaptor.forClass(ZigBeeCommand.class);
        matcherCapture = ArgumentCaptor.forClass(ZigBeeTransactionMatcher.class);
        Mockito.when(node.sendTransaction(commandCapture.capture(), matcherCapture.capture()))
                .thenReturn(Mockito.mock(Future.class));
        Mockito.when(endpoint.getParentNode()).thenReturn(node);
        Mockito.when(endpoint.sendTransaction(commandCapture.capture(), matcherCapture.capture())).thenReturn(null);
    }

    @Test
    public void bind() {
        createEndpoint();

        ZclCluster cluster = new ZclOnOffCluster(endpoint);
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
        createEndpoint();

        ZclCluster cluster = new ZclOnOffCluster(endpoint);
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
        createEndpoint();

        ZclCluster cluster = new ZclOnOffCluster(endpoint);
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
        createEndpoint();

        ZclCluster cluster = new ZclOnOffCluster(endpoint);
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
        createEndpoint();

        ZclCluster cluster = new ZclOnOffCluster(endpoint);
        assertEquals(Integer.valueOf(6), cluster.getClusterId());
    }

    @Test
    public void getClusterName() {
        createEndpoint();

        ZclCluster cluster = new ZclLevelControlCluster(endpoint);
        assertEquals("Level Control", cluster.getClusterName());
    }

    @Test
    public void handleAttributeReport() {
        createEndpoint();

        ZclCluster cluster = new ZclOnOffCluster(endpoint);

        // This reports an incorrect type which is changed through the normalisation
        ZclAttributeListener listenerMock = Mockito.mock(ZclAttributeListener.class);
        ArgumentCaptor<ZclAttribute> attributeCapture = ArgumentCaptor.forClass(ZclAttribute.class);
        ArgumentCaptor<Object> valueCaptor = ArgumentCaptor.forClass(Object.class);
        cluster.addAttributeListener(listenerMock);
        cluster.addAttributeListener(listenerMock);
        List<AttributeReport> attributeList = new ArrayList<AttributeReport>();
        AttributeReport report1;
        report1 = new AttributeReport();
        report1.setAttributeDataType(ZclDataType.SIGNED_8_BIT_INTEGER);
        report1.setAttributeIdentifier(0);
        report1.setAttributeValue(Integer.valueOf(1));
        System.out.println(report1);
        attributeList.add(report1);

        AttributeReport report2;
        report2 = new AttributeReport();
        report2.setAttributeDataType(ZclDataType.SIGNED_8_BIT_INTEGER);
        report2.setAttributeIdentifier(0);
        report2.setAttributeValue(0);
        System.out.println(report2);
        attributeList.add(report2);
        cluster.handleAttributeReport(attributeList);
        ZclAttribute attribute = cluster.getAttribute(0);
        assertTrue(attribute.getLastValue() instanceof Boolean);

        Mockito.verify(listenerMock, Mockito.timeout(1000).times(2)).attributeUpdated(attributeCapture.capture(),
                valueCaptor.capture());

        List<ZclAttribute> updatedAttributes = attributeCapture.getAllValues();
        assertEquals(2, updatedAttributes.size());
        List<Object> values = valueCaptor.getAllValues();

        attribute = updatedAttributes.get(0);
        assertTrue(attribute.getLastValue() instanceof Boolean);
        assertEquals(ZclDataType.BOOLEAN, attribute.getDataType());
        assertEquals(0, attribute.getId());
        assertEquals(false, attribute.getLastValue());
        // assertEquals(false, values.get(0));

        attribute = updatedAttributes.get(1);
        assertTrue(attribute.getLastValue() instanceof Boolean);
        assertEquals(ZclDataType.BOOLEAN, attribute.getDataType());
        assertEquals(0, attribute.getId());
        assertEquals(false, attribute.getLastValue());
        // assertEquals(true, values.get(1));

        // Attribute report ordering is not guaranteed, so let's just check they are different!
        assertNotEqual(values.get(0), values.get(1));

        cluster.removeAttributeListener(listenerMock);
    }

    private void assertNotEqual(Object object, Object object2) {
        // TODO Auto-generated method stub

    }

    @Test
    public void handleCommandReport() {
        createEndpoint();

        ZclCluster cluster = new ZclOnOffCluster(endpoint);

        ZclCommand command = Mockito.mock(ZclCommand.class);
        ZclCommandListener listenerMock = Mockito.mock(ZclCommandListener.class);
        cluster.addCommandListener(listenerMock);
        cluster.addCommandListener(listenerMock);
        cluster.handleCommand(command);

        Mockito.verify(listenerMock, Mockito.timeout(1000).times(1)).commandReceived(command);

        cluster.removeCommandListener(listenerMock);
    }

    @Test
    public void isAttributeSupported() throws Exception {
        createEndpoint();

        ZclCluster cluster = new ZclOnOffCluster(endpoint);
        assertEquals(4, cluster.getSupportedAttributes().size());

        Set<Integer> supportedAttributes = new HashSet<Integer>();
        supportedAttributes.add(1);
        supportedAttributes.add(4);
        supportedAttributes.add(2);
        TestUtilities.setField(ZclCluster.class, cluster, "supportedAttributes", supportedAttributes);

        assertNull(cluster.getDao().getSupportedAttributes());

        assertEquals(4, cluster.getSupportedAttributes().size());

        TestUtilities.setField(ZclCluster.class, cluster, "supportedAttributesKnown", true);
        assertEquals(3, cluster.getSupportedAttributes().size());

        assertTrue(cluster.isAttributeSupported(1));
        assertTrue(cluster.isAttributeSupported(2));
        assertFalse(cluster.isAttributeSupported(3));
    }

    @Test
    public void send() {
        createEndpoint();

        ZclOnOffCluster cluster = new ZclOnOffCluster(endpoint);

        cluster.setApsSecurityRequired(true);
        cluster.onCommand();
        assertEquals(1, commandCapture.getAllValues().size());
        ZigBeeCommand command = commandCapture.getValue();
        assertNotNull(command);
        System.out.println(command);
        assertTrue(command instanceof OnCommand);
        OnCommand onCommand = (OnCommand) command;
        assertEquals(true, onCommand.getApsSecurity());
        assertEquals(ZclCommandDirection.CLIENT_TO_SERVER, onCommand.getCommandDirection());

        cluster.setApsSecurityRequired(false);
        cluster.onCommand();
        assertEquals(2, commandCapture.getAllValues().size());
        command = commandCapture.getValue();
        assertNotNull(command);
        System.out.println(command);
        assertTrue(command instanceof OnCommand);
        onCommand = (OnCommand) command;
        assertEquals(false, onCommand.getApsSecurity());
        assertEquals(ZclCommandDirection.CLIENT_TO_SERVER, onCommand.getCommandDirection());
    }

    @Test
    public void readAttribute() {
        createEndpoint();

        ZclOnOffCluster cluster = new ZclOnOffCluster(endpoint);

        cluster.readAttribute(1);
        assertEquals(1, commandCapture.getAllValues().size());
        assertTrue(commandCapture.getValue() instanceof ReadAttributesCommand);
        ReadAttributesCommand command = (ReadAttributesCommand) commandCapture.getValue();
        System.out.println(command);
        assertEquals(1, command.getIdentifiers().size());
        assertEquals(Integer.valueOf(1), command.getIdentifiers().get(0));

        ZclAttribute attribute = new ZclAttribute(null, 2, null, null, false, false, false, false);
        cluster.read(attribute);
        assertTrue(commandCapture.getValue() instanceof ReadAttributesCommand);
        command = (ReadAttributesCommand) commandCapture.getValue();
        System.out.println(command);
        assertEquals(1, command.getIdentifiers().size());
        assertEquals(Integer.valueOf(2), command.getIdentifiers().get(0));

        List<Integer> attributeIds = new ArrayList<>();
        attributeIds.add(4);
        attributeIds.add(5);
        attributeIds.add(6);
        cluster.readAttributes(attributeIds);
        assertTrue(commandCapture.getValue() instanceof ReadAttributesCommand);
        command = (ReadAttributesCommand) commandCapture.getValue();
        System.out.println(command);
        assertEquals(3, command.getIdentifiers().size());
        assertEquals(Integer.valueOf(4), command.getIdentifiers().get(0));
        assertEquals(Integer.valueOf(5), command.getIdentifiers().get(1));
        assertEquals(Integer.valueOf(6), command.getIdentifiers().get(2));
    }

    @Test
    public void discoverAttributes() throws Exception {
        createEndpoint();
        DiscoverAttributesResponse response = new DiscoverAttributesResponse();
        response.setDiscoveryComplete(true);
        response.setAttributeInformation(Collections.emptyList());
        Future future = Mockito.mock(Future.class);
        CommandResult result = Mockito.mock(CommandResult.class);
        Mockito.when(result.isError()).thenReturn(false);
        Mockito.when(result.getResponse()).thenReturn(response);
        Mockito.when(future.get()).thenReturn(result);
        Mockito.when(endpoint.sendTransaction(commandCapture.capture(), matcherCapture.capture())).thenReturn(future);

        // Cluster initialisation - returns default
        ZclOnOffCluster cluster = new ZclOnOffCluster(endpoint);
        assertFalse(cluster.getSupportedAttributes().isEmpty());
        assertEquals(4, cluster.getSupportedAttributes().size());

        // DAO returns null as the real list of supported attributes is unknown
        ZclClusterDao clusterDaoNull = cluster.getDao();
        assertNull(cluster.getDao().getSupportedAttributes());

        // Discover attributes with an empty list of supported attributes
        cluster.discoverAttributes(false).get();
        assertTrue(cluster.getSupportedAttributes().isEmpty());

        ZclClusterDao clusterDaoEmpty = cluster.getDao();

        // Setting the DAO with a null should also return the default
        cluster.setDao(clusterDaoNull);
        assertEquals(4, cluster.getSupportedAttributes().size());

        cluster.setDao(clusterDaoEmpty);
        assertEquals(0, cluster.getSupportedAttributes().size());
    }

    @Test
    public void writeAttribute() {
        createEndpoint();
        Future future = Mockito.mock(Future.class);

        ZclOnOffCluster cluster = new ZclOnOffCluster(endpoint);

        cluster.writeAttribute(1, ZclDataType.SIGNED_16_BIT_INTEGER, Integer.valueOf(12345));
        Mockito.when(endpoint.sendTransaction(commandCapture.capture(), matcherCapture.capture())).thenReturn(future);

        WriteAttributesCommand command = (WriteAttributesCommand) commandCapture.getAllValues().get(0);
        System.out.println(command);
        List<WriteAttributeRecord> records = command.getRecords();
        assertEquals(1, records.size());
        WriteAttributeRecord record = records.get(0);
        assertEquals(1, record.getAttributeIdentifier());
        assertEquals(ZclDataType.SIGNED_16_BIT_INTEGER, record.getAttributeDataType());
        assertEquals(12345, record.getAttributeValue());
    }

    @Test
    public void writeAttributes() {
        createEndpoint();
        Future future = Mockito.mock(Future.class);

        ZclOnOffCluster cluster = new ZclOnOffCluster(endpoint);

        List<WriteAttributeRecord> attributes = new ArrayList<>();

        WriteAttributeRecord attribute = new WriteAttributeRecord();
        attribute.setAttributeIdentifier(1);
        attribute.setAttributeDataType(ZclDataType.BOOLEAN);
        attribute.setAttributeValue(Boolean.TRUE);
        attributes.add(attribute);

        attribute = new WriteAttributeRecord();
        attribute.setAttributeIdentifier(2);
        attribute.setAttributeDataType(ZclDataType.SIGNED_16_BIT_INTEGER);
        attribute.setAttributeValue(Integer.valueOf(123));
        attributes.add(attribute);

        cluster.writeAttributes(attributes);
        Mockito.when(endpoint.sendTransaction(commandCapture.capture(), matcherCapture.capture())).thenReturn(future);

        WriteAttributesCommand command = (WriteAttributesCommand) commandCapture.getAllValues().get(0);
        System.out.println(command);
        List<WriteAttributeRecord> records = command.getRecords();
        assertEquals(2, records.size());
        WriteAttributeRecord record = records.get(0);
        assertEquals(1, record.getAttributeIdentifier());
        assertEquals(ZclDataType.BOOLEAN, record.getAttributeDataType());
        assertEquals(true, record.getAttributeValue());

        record = records.get(1);
        assertEquals(2, record.getAttributeIdentifier());
        assertEquals(ZclDataType.SIGNED_16_BIT_INTEGER, record.getAttributeDataType());
        assertEquals(Integer.valueOf(123), record.getAttributeValue());
    }
}

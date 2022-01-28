/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl;

import static com.zsmartsystems.zigbee.zcl.clusters.ZclOnOffCluster.ATTR_ONOFF;
import static java.lang.Boolean.TRUE;
import static java.util.stream.Collectors.toSet;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.MockSettings;
import org.mockito.Mockito;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.TestUtilities;
import com.zsmartsystems.zigbee.ZigBeeCommand;
import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeEndpointAddress;
import com.zsmartsystems.zigbee.ZigBeeNode;
import com.zsmartsystems.zigbee.database.ZclAttributeDao;
import com.zsmartsystems.zigbee.database.ZclClusterDao;
import com.zsmartsystems.zigbee.internal.NotificationService;
import com.zsmartsystems.zigbee.transaction.ZigBeeTransactionMatcher;
import com.zsmartsystems.zigbee.zcl.clusters.ZclBasicCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclLevelControlCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclOnOffCluster;
import com.zsmartsystems.zigbee.zcl.clusters.general.ConfigureReportingCommand;
import com.zsmartsystems.zigbee.zcl.clusters.general.ConfigureReportingResponse;
import com.zsmartsystems.zigbee.zcl.clusters.general.DefaultResponse;
import com.zsmartsystems.zigbee.zcl.clusters.general.DiscoverAttributesCommand;
import com.zsmartsystems.zigbee.zcl.clusters.general.DiscoverAttributesResponse;
import com.zsmartsystems.zigbee.zcl.clusters.general.ReadAttributesCommand;
import com.zsmartsystems.zigbee.zcl.clusters.general.ReadAttributesResponse;
import com.zsmartsystems.zigbee.zcl.clusters.general.ReadReportingConfigurationCommand;
import com.zsmartsystems.zigbee.zcl.clusters.general.ReportAttributesCommand;
import com.zsmartsystems.zigbee.zcl.clusters.general.WriteAttributesCommand;
import com.zsmartsystems.zigbee.zcl.clusters.general.WriteAttributesResponse;
import com.zsmartsystems.zigbee.zcl.clusters.onoff.OnCommand;
import com.zsmartsystems.zigbee.zcl.field.AttributeRecord;
import com.zsmartsystems.zigbee.zcl.field.AttributeReport;
import com.zsmartsystems.zigbee.zcl.field.AttributeReportingConfigurationRecord;
import com.zsmartsystems.zigbee.zcl.field.ReadAttributeStatusRecord;
import com.zsmartsystems.zigbee.zcl.field.WriteAttributeRecord;
import com.zsmartsystems.zigbee.zcl.field.WriteAttributeStatusRecord;
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
    private static final int TIMEOUT = 5000;
    private static final MockSettings MOCK_SETTINGS_VERBOSE = Mockito.withSettings().verboseLogging();
    private ZigBeeNode node;
    private ZigBeeEndpoint endpoint;
    private ArgumentCaptor<ZigBeeCommand> commandCapture;
    private ArgumentCaptor<ZigBeeTransactionMatcher> matcherCapture;

    private void createEndpoint() {
        node = Mockito.mock(ZigBeeNode.class);
        endpoint = Mockito.mock(ZigBeeEndpoint.class);
        Mockito.when(endpoint.getNotificationService()).thenReturn(new NotificationService());
        Mockito.when(endpoint.getEndpointId()).thenReturn(5);
        Mockito.when(endpoint.getEndpointAddress()).thenReturn(new ZigBeeEndpointAddress(1234, 5));
        commandCapture = ArgumentCaptor.forClass(ZigBeeCommand.class);
        matcherCapture = ArgumentCaptor.forClass(ZigBeeTransactionMatcher.class);
        Mockito.when(node.sendTransaction(commandCapture.capture(), matcherCapture.capture()))
                .thenReturn(Mockito.mock(Future.class));
        Mockito.doNothing().when(node).sendTransaction(commandCapture.capture());
        Mockito.when(endpoint.getParentNode()).thenReturn(node);
        Mockito.when(endpoint.sendTransaction(commandCapture.capture(), matcherCapture.capture())).thenReturn(null);
        Mockito.doNothing().when(endpoint).sendTransaction(commandCapture.capture());
    }

    @Test
    public void sendCommand() {
        createEndpoint();

        ZclCluster cluster = new ZclOnOffCluster(endpoint);

        ReadAttributesCommand command = new ReadAttributesCommand(
                Collections.singletonList(ZclOnOffCluster.ATTR_ONOFF));
        cluster.sendCommand(command);
        assertEquals(1, commandCapture.getAllValues().size());
        ZigBeeCommand txCommand = commandCapture.getValue();

        assertEquals(Integer.valueOf(ZclOnOffCluster.CLUSTER_ID), txCommand.getClusterId());
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
        cluster.getReporting(attribute.getId());
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
        cluster.setReporting(attribute.getId(), 22, 33);
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
    public void handleAttributeReport() throws Exception {
        createEndpoint();

        ZclCluster cluster = new ZclOnOffCluster(endpoint);
        Set<ZclAttributeListener> attributeListeners = (Set<ZclAttributeListener>) TestUtilities
                .getField(ZclCluster.class, cluster, "attributeListeners");
        assertEquals(0, attributeListeners.size());

        // This reports an incorrect type which is changed through the normalisation
        ZclAttributeListener listenerMock = Mockito.mock(ZclAttributeListener.class);
        ArgumentCaptor<ZclAttribute> attributeCapture = ArgumentCaptor.forClass(ZclAttribute.class);
        ArgumentCaptor<Object> valueCaptor = ArgumentCaptor.forClass(Object.class);
        cluster.addAttributeListener(listenerMock);
        assertEquals(1, attributeListeners.size());
        cluster.addAttributeListener(listenerMock);
        assertEquals(1, attributeListeners.size());
        List<AttributeReport> attributeList = new ArrayList<AttributeReport>();
        AttributeReport report1;
        report1 = new AttributeReport();
        report1.setAttributeDataType(ZclDataType.SIGNED_8_BIT_INTEGER);
        report1.setAttributeIdentifier(0);
        report1.setAttributeValue(Integer.valueOf(1));
        System.out.println(report1);
        attributeList.add(report1);

        ReportAttributesCommand attributeReport = new ReportAttributesCommand(attributeList);
        attributeReport.setTransactionId(123);

        cluster.handleCommand(attributeReport);
        Mockito.verify(endpoint, Mockito.timeout(TIMEOUT).times(1)).sendTransaction(commandCapture.capture(), matcherCapture.capture());
        ZigBeeCommand command = commandCapture.getValue();
        System.out.println(command);
        assertTrue(command instanceof DefaultResponse);
        DefaultResponse defaultResponse = (DefaultResponse) command;
        assertEquals(Integer.valueOf(ReportAttributesCommand.COMMAND_ID), defaultResponse.getCommandIdentifier());
        assertEquals(ZclStatus.SUCCESS, defaultResponse.getStatusCode());
        assertEquals(Integer.valueOf(123), defaultResponse.getTransactionId());

        ZclAttribute attribute = cluster.getAttribute(0);
        assertTrue(attribute.getLastValue() instanceof Boolean);

        Mockito.verify(listenerMock, Mockito.timeout(TIMEOUT).times(1)).attributeUpdated(attributeCapture.capture(),
                valueCaptor.capture());

        List<ZclAttribute> updatedAttributes = attributeCapture.getAllValues();
        assertEquals(1, updatedAttributes.size());

        attribute = updatedAttributes.get(0);
        assertTrue(attribute.getLastValue() instanceof Boolean);
        assertEquals(ZclDataType.BOOLEAN, attribute.getDataType());
        assertEquals(0, attribute.getId());
        assertEquals(true, attribute.getLastValue());

        cluster.removeAttributeListener(listenerMock);
        assertEquals(0, attributeListeners.size());
    }

    @Test
    public void handleAttributeStatus() {
        createEndpoint();

        ZclCluster cluster = new ZclOnOffCluster(endpoint);

        // This reports an incorrect type which is changed through the normalisation
        ZclAttributeListener listenerMock = Mockito.mock(ZclAttributeListener.class);
        ArgumentCaptor<ZclAttribute> attributeCapture = ArgumentCaptor.forClass(ZclAttribute.class);
        ArgumentCaptor<Object> valueCaptor = ArgumentCaptor.forClass(Object.class);
        cluster.addAttributeListener(listenerMock);
        cluster.addAttributeListener(listenerMock);
        List<ReadAttributeStatusRecord> attributeList = new ArrayList<>();
        ReadAttributeStatusRecord report1;
        report1 = new ReadAttributeStatusRecord();
        report1.setStatus(ZclStatus.SUCCESS);
        report1.setAttributeDataType(ZclDataType.SIGNED_8_BIT_INTEGER);
        report1.setAttributeIdentifier(0);
        report1.setAttributeValue(Integer.valueOf(1));
        System.out.println(report1);
        attributeList.add(report1);

        ReadAttributesResponse attributeReport = new ReadAttributesResponse(attributeList);
        attributeReport.setTransactionId(56);

        cluster.handleCommand(attributeReport);
        Mockito.verify(endpoint, Mockito.timeout(TIMEOUT).times(1)).sendTransaction(commandCapture.capture(), matcherCapture.capture());
        ZigBeeCommand command = commandCapture.getValue();
        System.out.println(command);
        assertTrue(command instanceof DefaultResponse);
        DefaultResponse defaultResponse = (DefaultResponse) command;
        assertEquals(Integer.valueOf(ReadAttributesResponse.COMMAND_ID), defaultResponse.getCommandIdentifier());
        assertEquals(ZclStatus.SUCCESS, defaultResponse.getStatusCode());
        assertEquals(Integer.valueOf(56), defaultResponse.getTransactionId());

        ZclAttribute attribute = cluster.getAttribute(0);
        assertTrue(attribute.getLastValue() instanceof Boolean);

        Mockito.verify(listenerMock, Mockito.timeout(TIMEOUT).times(1)).attributeUpdated(attributeCapture.capture(),
                valueCaptor.capture());

        List<ZclAttribute> updatedAttributes = attributeCapture.getAllValues();
        assertEquals(1, updatedAttributes.size());

        attribute = updatedAttributes.get(0);
        assertTrue(attribute.getLastValue() instanceof Boolean);
        assertEquals(ZclDataType.BOOLEAN, attribute.getDataType());
        assertEquals(0, attribute.getId());
        assertEquals(true, attribute.getLastValue());

        cluster.removeAttributeListener(listenerMock);
    }

    private void assertNotEqual(Object object, Object object2) {
        // TODO Auto-generated method stub

    }

    @Test
    public void handleCommand() throws Exception {
        createEndpoint();

        ZclCluster cluster = new ZclOnOffCluster(endpoint);

        Set<ZclCommandListener> commandListeners = (Set<ZclCommandListener>) TestUtilities.getField(ZclCluster.class,
                cluster, "commandListeners");
        assertEquals(0, commandListeners.size());
        int tid = 123;
        ZclCommand command = Mockito.mock(ZclCommand.class);
        Mockito.when(command.getTransactionId()).thenReturn(tid);
        Mockito.when(command.getCommandId()).thenReturn(45);
        Mockito.when(command.isGenericCommand()).thenReturn(false);
        Mockito.when(command.isManufacturerSpecific()).thenReturn(false);
        Mockito.when(command.getCommandDirection()).thenReturn(ZclCommandDirection.CLIENT_TO_SERVER);

        ZclCommandListener listenerMock = Mockito.mock(ZclCommandListener.class);
        cluster.addCommandListener(listenerMock);
        assertEquals(1, commandListeners.size());
        cluster.addCommandListener(listenerMock);
        assertEquals(1, commandListeners.size());
        cluster.handleCommand(command);

        Mockito.verify(endpoint, Mockito.timeout(TIMEOUT).times(1)).sendTransaction(commandCapture.capture(), matcherCapture.capture());
        ZigBeeCommand response = commandCapture.getValue();
        assertTrue(response instanceof DefaultResponse);
        System.out.println(response);
        DefaultResponse defaultResponse = (DefaultResponse) response;
        assertEquals(Integer.valueOf(45), defaultResponse.getCommandIdentifier());
        assertEquals(ZclStatus.UNSUP_CLUSTER_COMMAND, defaultResponse.getStatusCode());
        assertEquals(Integer.valueOf(tid), defaultResponse.getTransactionId());

        Mockito.verify(listenerMock, Mockito.timeout(TIMEOUT).times(1)).commandReceived(command);

        Mockito.when(command.getTransactionId()).thenReturn(++tid);

        Mockito.when(command.isGenericCommand()).thenReturn(true);
        cluster.handleCommand(command);
        Mockito.verify(endpoint, Mockito.timeout(TIMEOUT).times(2)).sendTransaction(commandCapture.capture(), matcherCapture.capture());
        response = commandCapture.getValue();
        assertTrue(response instanceof DefaultResponse);
        System.out.println(response);
        defaultResponse = (DefaultResponse) response;
        assertEquals(Integer.valueOf(45), defaultResponse.getCommandIdentifier());
        assertEquals(ZclStatus.UNSUP_GENERAL_COMMAND, defaultResponse.getStatusCode());
        assertEquals(Integer.valueOf(tid), defaultResponse.getTransactionId());

        Mockito.when(command.isManufacturerSpecific()).thenReturn(true);
        Mockito.when(command.getTransactionId()).thenReturn(++tid);
        Mockito.when(command.isGenericCommand()).thenReturn(true);
        cluster.handleCommand(command);
        Mockito.verify(endpoint, Mockito.timeout(TIMEOUT).times(3)).sendTransaction(commandCapture.capture(), matcherCapture.capture());
        response = commandCapture.getValue();
        assertTrue(response instanceof DefaultResponse);
        System.out.println(response);
        defaultResponse = (DefaultResponse) response;
        assertEquals(Integer.valueOf(45), defaultResponse.getCommandIdentifier());
        assertEquals(ZclStatus.UNSUP_MANUF_GENERAL_COMMAND, defaultResponse.getStatusCode());
        assertEquals(Integer.valueOf(tid), defaultResponse.getTransactionId());

        Mockito.when(command.isGenericCommand()).thenReturn(false);
        Mockito.when(command.getTransactionId()).thenReturn(++tid);

        cluster.handleCommand(command);
        Mockito.verify(endpoint, Mockito.timeout(TIMEOUT).times(4)).sendTransaction(commandCapture.capture(), matcherCapture.capture());
        response = commandCapture.getValue();
        assertTrue(response instanceof DefaultResponse);
        System.out.println(response);
        defaultResponse = (DefaultResponse) response;
        assertEquals(Integer.valueOf(45), defaultResponse.getCommandIdentifier());
        assertEquals(ZclStatus.UNSUP_MANUF_CLUSTER_COMMAND, defaultResponse.getStatusCode());
        assertEquals(Integer.valueOf(tid), defaultResponse.getTransactionId());

        command = new ConfigureReportingResponse(null, null);
        command.setTransactionId(++tid);
        command.setDestinationAddress(new ZigBeeEndpointAddress(0, 0));
        command.setClusterId(0x0000);

        ArgumentCaptor<ZclCommand> zclCommandCapture = ArgumentCaptor.forClass(ZclCommand.class);

        cluster.handleCommand(command);
        Mockito.verify(listenerMock, Mockito.timeout(TIMEOUT).times(5)).commandReceived(zclCommandCapture.capture());
        assertEquals(command, zclCommandCapture.getValue());
        Mockito.verify(endpoint, Mockito.timeout(TIMEOUT).times(5)).sendTransaction(commandCapture.capture(), matcherCapture.capture());
        response = commandCapture.getValue();
        assertTrue(response instanceof DefaultResponse);
        System.out.println(response);
        defaultResponse = (DefaultResponse) response;
        assertEquals(ZclStatus.SUCCESS, defaultResponse.getStatusCode());

        Mockito.when(listenerMock.commandReceived(command)).thenReturn(true);
        command.setTransactionId(++tid);
        cluster.handleCommand(command);
        assertEquals(command, zclCommandCapture.getValue());
        Mockito.verify(endpoint, Mockito.timeout(TIMEOUT).times(5)).sendTransaction(commandCapture.capture(), matcherCapture.capture());

        cluster.removeCommandListener(listenerMock);
        assertEquals(0, commandListeners.size());
    }

    @Test
    public void isAttributeSupported() throws Exception {
        createEndpoint();

        ZclCluster cluster = new ZclOnOffCluster(endpoint);
        assertEquals(5, cluster.getSupportedAttributes().size());

        Set<Integer> supportedAttributes = new HashSet<Integer>();
        supportedAttributes.add(1);
        supportedAttributes.add(4);
        supportedAttributes.add(2);
        TestUtilities.setField(ZclCluster.class, cluster, "supportedAttributes", supportedAttributes);

        assertNull(cluster.getDao().getSupportedAttributes());

        assertEquals(5, cluster.getSupportedAttributes().size());

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
        cluster.readAttribute(attribute.getId());
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
        DiscoverAttributesResponse response = new DiscoverAttributesResponse(true, Collections.emptyList());
        Future future = Mockito.mock(Future.class);
        CommandResult result = Mockito.mock(CommandResult.class);
        Mockito.when(result.isError()).thenReturn(false);
        Mockito.when(result.getResponse()).thenReturn(response);
        Mockito.when(future.get()).thenReturn(result);
        Mockito.when(endpoint.sendTransaction(commandCapture.capture(), matcherCapture.capture())).thenReturn(future);

        // Cluster initialisation - returns default
        ZclOnOffCluster cluster = new ZclOnOffCluster(endpoint);
        assertFalse(cluster.getSupportedAttributes().isEmpty());
        assertEquals(5, cluster.getSupportedAttributes().size());

        // DAO returns null as the real list of supported attributes is unknown
        ZclClusterDao clusterDaoNull = cluster.getDao();
        assertNull(cluster.getDao().getSupportedAttributes());

        // Discover attributes with an empty list of supported attributes
        cluster.discoverAttributes(false).get();
        assertTrue(cluster.getSupportedAttributes().isEmpty());

        ZclClusterDao clusterDaoEmpty = cluster.getDao();

        // Setting the DAO with a null should also return the default
        cluster.setDao(clusterDaoNull);
        assertEquals(5, cluster.getSupportedAttributes().size());

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

    @Test
    public void reportAttribute() {
        createEndpoint();
        Future future = Mockito.mock(Future.class);

        ZclOnOffCluster cluster = new ZclOnOffCluster(endpoint);

        cluster.reportAttribute(1, ZclDataType.SIGNED_16_BIT_INTEGER, Integer.valueOf(12345));
        Mockito.when(endpoint.sendTransaction(commandCapture.capture(), matcherCapture.capture())).thenReturn(future);

        ReportAttributesCommand command = (ReportAttributesCommand) commandCapture.getAllValues().get(0);
        System.out.println(command);
        List<AttributeReport> records = command.getReports();
        assertEquals(1, records.size());
        AttributeReport record = records.get(0);
        assertEquals(1, record.getAttributeIdentifier());
        assertEquals(ZclDataType.SIGNED_16_BIT_INTEGER, record.getAttributeDataType());
        assertEquals(12345, record.getAttributeValue());
    }

    @Test
    public void reportAttributes() {
        createEndpoint();
        Future future = Mockito.mock(Future.class);

        ZclOnOffCluster cluster = new ZclOnOffCluster(endpoint);

        List<AttributeReport> attributes = new ArrayList<>();

        AttributeReport attribute = new AttributeReport();
        attribute.setAttributeIdentifier(1);
        attribute.setAttributeDataType(ZclDataType.BOOLEAN);
        attribute.setAttributeValue(Boolean.TRUE);
        attributes.add(attribute);

        attribute = new AttributeReport();
        attribute.setAttributeIdentifier(2);
        attribute.setAttributeDataType(ZclDataType.SIGNED_16_BIT_INTEGER);
        attribute.setAttributeValue(Integer.valueOf(123));
        attributes.add(attribute);

        cluster.reportAttributes(attributes);
        Mockito.when(endpoint.sendTransaction(commandCapture.capture(), matcherCapture.capture())).thenReturn(future);

        ReportAttributesCommand command = (ReportAttributesCommand) commandCapture.getAllValues().get(0);
        System.out.println(command);
        List<AttributeReport> records = command.getReports();
        assertEquals(2, records.size());
        AttributeReport record = records.get(0);
        assertEquals(1, record.getAttributeIdentifier());
        assertEquals(ZclDataType.BOOLEAN, record.getAttributeDataType());
        assertEquals(true, record.getAttributeValue());

        record = records.get(1);
        assertEquals(2, record.getAttributeIdentifier());
        assertEquals(ZclDataType.SIGNED_16_BIT_INTEGER, record.getAttributeDataType());
        assertEquals(Integer.valueOf(123), record.getAttributeValue());
    }

    @Test
    public void setDao() {
        // Given an On/Off cluster and the DAO created from that cluster
        createEndpoint();
        ZclOnOffCluster cluster = new ZclOnOffCluster(endpoint);
        Set<Integer> onOffClusterAttributeIds = cluster.getAttributes().stream().map(ZclAttribute::getId)
                .collect(toSet());

        ZclClusterDao clusterDao = cluster.getDao();
        Map<Integer, ZclAttributeDao> attributeDaos = clusterDao.getAttributes();

        // Add one attribute for a manufacturer-specific attribute to the DAO
        ZclAttributeDao manufacturerSpecificAttributeDao = new ZclAttributeDao();
        manufacturerSpecificAttributeDao.setId(0xF000);
        manufacturerSpecificAttributeDao.setManufacturerCode(0x1234);
        manufacturerSpecificAttributeDao.setDataType(ZclDataType.SIGNED_16_BIT_INTEGER);
        manufacturerSpecificAttributeDao.setLastValue(Double.valueOf(123));
        attributeDaos.put(manufacturerSpecificAttributeDao.getId(), manufacturerSpecificAttributeDao);

        // Set last value and implemented status for the on/off attribute in the DAO
        ZclAttributeDao onOffAttributeDao = attributeDaos.get(ATTR_ONOFF);
        onOffAttributeDao.setLastValue(TRUE);
        onOffAttributeDao.setImplemented(true);

        clusterDao.setAttributes(attributeDaos);

        // Now update the cluster from the DAO
        cluster.setDao(clusterDao);

        // Then the cluster contains all attributes from the on/off cluster, plus the manufacturer-specific attribute
        assertEquals(onOffClusterAttributeIds.size() + 1, cluster.getAttributes().size());
        for (Integer attributeId : onOffClusterAttributeIds) {
            ZclAttribute attribute = cluster.getAttribute(attributeId);
            assertNotNull(attribute);
            assertEquals(new ZclOnOffCluster(endpoint).getAttribute(attributeId).getDataType(),
                    attribute.getDataType());
            assertEquals(new ZclOnOffCluster(endpoint).getAttribute(attributeId).getMaximumReportingPeriod(),
                    attribute.getMaximumReportingPeriod());
            assertEquals(new ZclOnOffCluster(endpoint).getAttribute(attributeId).getName(), attribute.getName());
        }

        // The onOff attribute has the state set correctly
        ZclAttribute onOffAttribute = cluster.getAttribute(ATTR_ONOFF);
        assertEquals(TRUE, onOffAttribute.getLastValue());
        assertTrue(onOffAttribute.isImplemented());

        // And the manufacturer-specific attribute has the state set correctly
        ZclAttribute manufacturerSpecificAttribute = cluster.getAttribute(0xF000);
        assertEquals(manufacturerSpecificAttribute.getManufacturerCode(), Integer.valueOf(0x1234));
        assertEquals(manufacturerSpecificAttribute.getLastValue(), Integer.valueOf(123));
    }

    @Test
    public void handleDiscoverAttributes() {
        createEndpoint();

        ZclOnOffCluster cluster = new ZclOnOffCluster(endpoint);

        Set<ZclAttribute> attributes = new HashSet<>();
        attributes.add(new ZclAttribute(cluster, 1, "Attribute-1", ZclDataType.UNSIGNED_8_BIT_INTEGER, true, true,
                false, true));
        attributes.add(new ZclAttribute(cluster, 2, "Attribute-2", ZclDataType.BOOLEAN, true, true, false, true));
        attributes.add(
                new ZclAttribute(cluster, 10, "Attribute-10", ZclDataType.CHARACTER_STRING, true, true, false, true));
        attributes.add(new ZclAttribute(cluster, 11, "Attribute-11", ZclDataType.BOOLEAN, true, true, false, true));
        attributes.add(new ZclAttribute(cluster, 13, "Attribute-13", ZclDataType.BOOLEAN, true, true, false, true));

        assertFalse(attributes.iterator().next().isImplemented());
        cluster.addLocalAttributes(attributes);
        assertTrue(attributes.iterator().next().isImplemented());

        DiscoverAttributesCommand discoverCommand = new DiscoverAttributesCommand(0, 10);
        discoverCommand.setClusterId(ZclBasicCluster.CLUSTER_ID);
        discoverCommand.setSourceAddress(new ZigBeeEndpointAddress(1234));
        discoverCommand.setDestinationAddress(new ZigBeeEndpointAddress(5678));
        discoverCommand.setCommandDirection(ZclCommandDirection.CLIENT_TO_SERVER);
        discoverCommand.setTransactionId(55);

        cluster.handleCommand(discoverCommand);
        assertEquals(1, commandCapture.getAllValues().size());
        DiscoverAttributesResponse discoverResponse = (DiscoverAttributesResponse) commandCapture.getValue();
        System.out.println(discoverResponse);
        assertEquals(5, discoverResponse.getAttributeInformation().size());
        assertEquals(1, discoverResponse.getAttributeInformation().get(0).getIdentifier());
        assertEquals(ZclDataType.UNSIGNED_8_BIT_INTEGER,
                discoverResponse.getAttributeInformation().get(0).getDataType());
        assertTrue(discoverResponse.getDiscoveryComplete());

        cluster.getLocalAttribute(1).setImplemented(false);
        cluster.handleCommand(discoverCommand);
        assertEquals(2, commandCapture.getAllValues().size());
        discoverResponse = (DiscoverAttributesResponse) commandCapture.getValue();
        System.out.println(discoverResponse);
        assertEquals(4, discoverResponse.getAttributeInformation().size());
        assertEquals(2, discoverResponse.getAttributeInformation().get(0).getIdentifier());
        assertEquals(ZclDataType.BOOLEAN, discoverResponse.getAttributeInformation().get(0).getDataType());

        discoverCommand = new DiscoverAttributesCommand(3, 2);
        discoverCommand.setClusterId(ZclBasicCluster.CLUSTER_ID);
        discoverCommand.setSourceAddress(new ZigBeeEndpointAddress(1234));
        discoverCommand.setDestinationAddress(new ZigBeeEndpointAddress(5678));
        discoverCommand.setCommandDirection(ZclCommandDirection.CLIENT_TO_SERVER);
        discoverCommand.setTransactionId(55);

        cluster.handleCommand(discoverCommand);
        assertEquals(3, commandCapture.getAllValues().size());
        discoverResponse = (DiscoverAttributesResponse) commandCapture.getValue();
        System.out.println(discoverResponse);
        assertEquals(2, discoverResponse.getAttributeInformation().size());
        assertEquals(10, discoverResponse.getAttributeInformation().get(0).getIdentifier());
        assertEquals(ZclDataType.CHARACTER_STRING, discoverResponse.getAttributeInformation().get(0).getDataType());
        assertFalse(discoverResponse.getDiscoveryComplete());
    }

    @Test
    public void handleReadAttributes() {
        createEndpoint();

        ZclOnOffCluster cluster = new ZclOnOffCluster(endpoint);

        Set<ZclAttribute> attributes = new HashSet<>();
        attributes.add(new ZclAttribute(cluster, 1, "Attribute-1", ZclDataType.UNSIGNED_8_BIT_INTEGER, true, true,
                false, true));
        attributes.add(new ZclAttribute(cluster, 2, "Attribute-2", ZclDataType.BOOLEAN, true, true, false, true));
        attributes.add(
                new ZclAttribute(cluster, 10, "Attribute-10", ZclDataType.CHARACTER_STRING, true, true, false, true));
        attributes.add(new ZclAttribute(cluster, 11, "Attribute-11", ZclDataType.BOOLEAN, true, true, false, true));
        attributes.add(new ZclAttribute(cluster, 13, "Attribute-13", ZclDataType.BOOLEAN, true, true, false, true));
        cluster.addLocalAttributes(attributes);

        List<Integer> identifiers = new ArrayList<>();
        identifiers.add(2);

        ReadAttributesCommand readCommand = getReadAttributesCommand(identifiers);

        cluster.handleCommand(readCommand);
        assertEquals(1, commandCapture.getAllValues().size());
        ReadAttributesResponse readResponse = (ReadAttributesResponse) commandCapture.getValue();
        System.out.println(readResponse);
        assertEquals(Integer.valueOf(ZclOnOffCluster.CLUSTER_ID), readResponse.getClusterId());
        assertEquals(ZclCommandDirection.SERVER_TO_CLIENT, readResponse.getCommandDirection());
        assertEquals(1, readResponse.getRecords().size());
        assertEquals(Integer.valueOf(55), readResponse.getTransactionId());
        List<ReadAttributeStatusRecord> records = readResponse.getRecords();
        assertEquals(2, records.get(0).getAttributeIdentifier());
        assertNull(records.get(0).getAttributeValue());
        assertEquals(ZclStatus.INVALID_VALUE, records.get(0).getStatus());

        cluster.getLocalAttribute(2).setValue(false);

        cluster.handleCommand(readCommand);
        assertEquals(2, commandCapture.getAllValues().size());
        readResponse = (ReadAttributesResponse) commandCapture.getValue();
        System.out.println(readResponse);
        records = readResponse.getRecords();
        assertEquals(2, records.get(0).getAttributeIdentifier());
        assertEquals(false, records.get(0).getAttributeValue());
        assertEquals(ZclStatus.SUCCESS, records.get(0).getStatus());

        cluster.getLocalAttribute(13).setValue(true);

        identifiers = new ArrayList<>();
        identifiers.add(2);
        identifiers.add(13);
        identifiers.add(55);
        readCommand = getReadAttributesCommand(identifiers);

        cluster.handleCommand(readCommand);
        assertEquals(3, commandCapture.getAllValues().size());
        readResponse = (ReadAttributesResponse) commandCapture.getValue();
        System.out.println(readResponse);
        records = readResponse.getRecords();
        assertEquals(3, records.size());
        assertEquals(2, records.get(0).getAttributeIdentifier());
        assertEquals(false, records.get(0).getAttributeValue());
        assertEquals(ZclStatus.SUCCESS, records.get(0).getStatus());
        assertEquals(13, records.get(1).getAttributeIdentifier());
        assertEquals(true, records.get(1).getAttributeValue());
        assertEquals(ZclStatus.SUCCESS, records.get(1).getStatus());
        assertEquals(55, records.get(2).getAttributeIdentifier());
        assertEquals(ZclStatus.UNSUPPORTED_ATTRIBUTE, records.get(2).getStatus());
    }

    private ReadAttributesCommand getReadAttributesCommand(List<Integer> identifiers) {
        ReadAttributesCommand readCommand = new ReadAttributesCommand(identifiers);
        readCommand.setClusterId(ZclOnOffCluster.CLUSTER_ID);
        readCommand.setSourceAddress(new ZigBeeEndpointAddress(1234));
        readCommand.setDestinationAddress(new ZigBeeEndpointAddress(5678));
        readCommand.setCommandDirection(ZclCommandDirection.CLIENT_TO_SERVER);
        readCommand.setTransactionId(55);
        return readCommand;
    }

    @Test
    public void handleWriteAttributesCommand() {
        createEndpoint();

        ZclOnOffCluster cluster = new ZclOnOffCluster(endpoint);

        Set<ZclAttribute> attributes = new HashSet<>();
        attributes.add(new ZclAttribute(cluster, 1, "Attribute-1", ZclDataType.UNSIGNED_8_BIT_INTEGER, true, true,
                false, true));
        attributes.add(new ZclAttribute(cluster, 2, "Attribute-2", ZclDataType.BOOLEAN, true, true, false, true));
        attributes.add(
                new ZclAttribute(cluster, 10, "Attribute-10", ZclDataType.CHARACTER_STRING, true, true, false, true));
        attributes.add(new ZclAttribute(cluster, 11, "Attribute-11", ZclDataType.BOOLEAN, true, true, false, true));
        attributes.add(new ZclAttribute(cluster, 13, "Attribute-13", ZclDataType.BOOLEAN, true, true, false, true));
        cluster.addLocalAttributes(attributes);

        List<WriteAttributeRecord> records = new ArrayList<>();
        WriteAttributeRecord record = new WriteAttributeRecord();
        record.setAttributeIdentifier(1);
        record.setAttributeDataType(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        record.setAttributeValue(Integer.valueOf(123));
        records.add(record);
        WriteAttributesCommand command = new WriteAttributesCommand(records);
        command.setSourceAddress(new ZigBeeEndpointAddress(1234));
        command.setDestinationAddress(new ZigBeeEndpointAddress(5678));
        command.setCommandDirection(ZclCommandDirection.CLIENT_TO_SERVER);
        command.setTransactionId(55);

        cluster.handleCommand(command);
        assertEquals(1, commandCapture.getAllValues().size());
        WriteAttributesResponse response = (WriteAttributesResponse) commandCapture.getValue();
        System.out.println(response);

        // Since we send the command to the OnOffCluster we expect it to respond with its ID.  
        assertEquals(Integer.valueOf(ZclOnOffCluster.CLUSTER_ID), response.getClusterId());
        assertEquals(ZclCommandDirection.SERVER_TO_CLIENT, response.getCommandDirection());
        assertEquals(1, response.getRecords().size());
        assertEquals(Integer.valueOf(55), response.getTransactionId());
        List<WriteAttributeStatusRecord> responseRecords = response.getRecords();
        assertEquals(1, responseRecords.size());
        assertEquals(ZclStatus.SUCCESS, responseRecords.get(0).getStatus());

        record = new WriteAttributeRecord();
        record.setAttributeIdentifier(2);
        record.setAttributeDataType(ZclDataType.BOOLEAN);
        record.setAttributeValue(false);
        records.add(record);

        cluster.handleCommand(command);
        assertEquals(2, commandCapture.getAllValues().size());
        response = (WriteAttributesResponse) commandCapture.getValue();
        System.out.println(response);
        responseRecords = response.getRecords();
        assertEquals(1, responseRecords.size());
        assertEquals(ZclStatus.SUCCESS, responseRecords.get(0).getStatus());

        record = new WriteAttributeRecord();
        record.setAttributeIdentifier(3);
        record.setAttributeDataType(ZclDataType.BOOLEAN);
        record.setAttributeValue(false);
        records.add(record);

        record = new WriteAttributeRecord();
        record.setAttributeIdentifier(10);
        record.setAttributeDataType(ZclDataType.BOOLEAN);
        record.setAttributeValue(true);
        records.add(record);

        cluster.handleCommand(command);
        assertEquals(3, commandCapture.getAllValues().size());
        response = (WriteAttributesResponse) commandCapture.getValue();
        System.out.println(response);
        responseRecords = response.getRecords();
        assertEquals(4, responseRecords.size());

        assertEquals(1, responseRecords.get(0).getAttributeIdentifier());
        assertEquals(2, responseRecords.get(1).getAttributeIdentifier());
        assertEquals(3, responseRecords.get(2).getAttributeIdentifier());
        assertEquals(10, responseRecords.get(3).getAttributeIdentifier());

        assertEquals(ZclStatus.SUCCESS, responseRecords.get(0).getStatus());
        assertEquals(ZclStatus.SUCCESS, responseRecords.get(1).getStatus());
        assertEquals(ZclStatus.UNSUPPORTED_ATTRIBUTE, responseRecords.get(2).getStatus());
        assertEquals(ZclStatus.INVALID_DATA_TYPE, responseRecords.get(3).getStatus());
    }
}

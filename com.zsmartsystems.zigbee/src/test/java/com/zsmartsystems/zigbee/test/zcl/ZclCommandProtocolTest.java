package com.zsmartsystems.zigbee.test.zcl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.zsmartsystems.zigbee.serialization.DefaultDeserializer;
import com.zsmartsystems.zigbee.serialization.DefaultSerializer;
import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.clusters.doorlock.LockDoorCommand;
import com.zsmartsystems.zigbee.zcl.clusters.doorlock.UnlockDoorCommand;
import com.zsmartsystems.zigbee.zcl.clusters.general.ConfigureReportingCommand;
import com.zsmartsystems.zigbee.zcl.clusters.general.ConfigureReportingResponse;
import com.zsmartsystems.zigbee.zcl.clusters.general.DiscoverAttributesCommand;
import com.zsmartsystems.zigbee.zcl.clusters.general.DiscoverAttributesResponse;
import com.zsmartsystems.zigbee.zcl.clusters.general.ReadAttributesCommand;
import com.zsmartsystems.zigbee.zcl.clusters.general.ReadAttributesResponse;
import com.zsmartsystems.zigbee.zcl.clusters.general.ReadReportingConfigurationCommand;
import com.zsmartsystems.zigbee.zcl.clusters.general.ReadReportingConfigurationResponse;
import com.zsmartsystems.zigbee.zcl.clusters.general.ReportAttributesCommand;
import com.zsmartsystems.zigbee.zcl.clusters.general.WriteAttributesCommand;
import com.zsmartsystems.zigbee.zcl.clusters.general.WriteAttributesNoResponse;
import com.zsmartsystems.zigbee.zcl.clusters.general.WriteAttributesResponse;
import com.zsmartsystems.zigbee.zcl.clusters.general.WriteAttributesUndividedCommand;
import com.zsmartsystems.zigbee.zcl.clusters.groups.GetGroupMembershipCommand;
import com.zsmartsystems.zigbee.zcl.clusters.iasace.BypassCommand;
import com.zsmartsystems.zigbee.zcl.clusters.rssilocation.ReportRssiMeasurementsCommand;
import com.zsmartsystems.zigbee.zcl.clusters.scenes.AddSceneCommand;
import com.zsmartsystems.zigbee.zcl.field.AttributeIdentifier;
import com.zsmartsystems.zigbee.zcl.field.AttributeInformation;
import com.zsmartsystems.zigbee.zcl.field.AttributeRecord;
import com.zsmartsystems.zigbee.zcl.field.AttributeReport;
import com.zsmartsystems.zigbee.zcl.field.AttributeReportingConfigurationRecord;
import com.zsmartsystems.zigbee.zcl.field.AttributeStatusRecord;
import com.zsmartsystems.zigbee.zcl.field.ExtensionFieldSet;
import com.zsmartsystems.zigbee.zcl.field.NeighborInformation;
import com.zsmartsystems.zigbee.zcl.field.ReadAttributeStatusRecord;
import com.zsmartsystems.zigbee.zcl.field.Unsigned16BitInteger;
import com.zsmartsystems.zigbee.zcl.field.Unsigned8BitInteger;
import com.zsmartsystems.zigbee.zcl.field.WriteAttributeRecord;
import com.zsmartsystems.zigbee.zcl.field.WriteAttributeStatusRecord;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Created by tlaukkan on 6/5/2016.
 */
public class ZclCommandProtocolTest {

    @Test
    public void testLockDoorCommand() throws Exception {
        final LockDoorCommand command = new LockDoorCommand();
        command.setPinCode("123");
        testSerialization(command);
    }

    @Test
    public void testUnlockDoorCommand() throws Exception {
        final UnlockDoorCommand command = new UnlockDoorCommand();
        command.setPinCode("123");
        testSerialization(command);
    }

    @Test
    public void testDiscoverAttributesCommand() throws Exception {
        final DiscoverAttributesCommand command = new DiscoverAttributesCommand();
        command.setStartAttributeIdentifier(0);
        command.setMaximumAttributeIdentifiers(100);
        testSerialization(command);
    }

    @Test
    public void testDiscoverAttributesResponseCommand() throws Exception {
        final DiscoverAttributesResponse command = new DiscoverAttributesResponse();
        command.setCommandIdentifier(true);
        final List<AttributeInformation> list = new ArrayList<AttributeInformation>();
        final AttributeInformation data = new AttributeInformation();
        data.setAttributeIdentifier(1);
        data.setAttributeDataType(2);
        list.add(data);
        command.setInformation(list);
        testSerialization(command);
    }

    @Test
    public void testReadAttributesCommand() throws Exception {
        final ReadAttributesCommand command = new ReadAttributesCommand();
        final List<AttributeIdentifier> list = new ArrayList<AttributeIdentifier>();
        final AttributeIdentifier data = new AttributeIdentifier();
        data.setAttributeIdentifier(1);
        list.add(data);
        command.setIdentifiers(list);
        testSerialization(command);
    }

    @Test
    public void testReadAttributesResponseCommand() throws Exception {
        final ReadAttributesResponse command = new ReadAttributesResponse();
        final List<ReadAttributeStatusRecord> list = new ArrayList<ReadAttributeStatusRecord>();
        final ReadAttributeStatusRecord data = new ReadAttributeStatusRecord();
        data.setAttributeIdentifier(1);
        data.setStatus(0);
        data.setAttributeDataType(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        data.setAttributeValue(1);
        list.add(data);
        command.setRecords(list);
        testSerialization(command);
    }

    @Test
    public void testWriteAttributesCommand() throws Exception {
        final WriteAttributesCommand command = new WriteAttributesCommand();
        final List<WriteAttributeRecord> list = new ArrayList<WriteAttributeRecord>();
        final WriteAttributeRecord data = new WriteAttributeRecord();
        data.setAttributeIdentifier(1);
        data.setAttributeDataType(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        data.setAttributeValue(1);
        list.add(data);
        command.setRecords(list);
        testSerialization(command);
    }

    @Test
    public void testWriteAttributesUndividedCommand() throws Exception {
        final WriteAttributesUndividedCommand command = new WriteAttributesUndividedCommand();
        final List<WriteAttributeRecord> list = new ArrayList<WriteAttributeRecord>();
        final WriteAttributeRecord data = new WriteAttributeRecord();
        data.setAttributeIdentifier(1);
        data.setAttributeDataType(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        data.setAttributeValue(1);
        list.add(data);
        command.setRecords(list);
        testSerialization(command);
    }

    @Test
    public void testWriteAttributesNoResponseCommand() throws Exception {
        final WriteAttributesNoResponse command = new WriteAttributesNoResponse();
        final List<WriteAttributeRecord> list = new ArrayList<WriteAttributeRecord>();
        final WriteAttributeRecord data = new WriteAttributeRecord();
        data.setAttributeIdentifier(1);
        data.setAttributeDataType(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        data.setAttributeValue(1);
        list.add(data);
        command.setRecords(list);
        testSerialization(command);
    }

    @Test
    public void testWriteAttributesResponseCommand() throws Exception {
        final WriteAttributesResponse command = new WriteAttributesResponse();
        final List<WriteAttributeStatusRecord> list = new ArrayList<WriteAttributeStatusRecord>();
        final WriteAttributeStatusRecord data = new WriteAttributeStatusRecord();
        data.setStatus(1);
        data.setAttributeIdentifier(2);
        list.add(data);
        command.setRecords(list);
        testSerialization(command);
    }

    @Test
    public void testConfigureReportingCommand() throws Exception {
        final ConfigureReportingCommand command = new ConfigureReportingCommand();
        final List<AttributeReportingConfigurationRecord> list = new ArrayList<AttributeReportingConfigurationRecord>();
        final AttributeReportingConfigurationRecord data = new AttributeReportingConfigurationRecord();
        data.setAttributeIdentifier(1);
        data.setMinimumReportingInterval(2);
        data.setMaximumReportingInterval(3);
        data.setAttributeDataType(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        data.setReportableChange(1);
        data.setTimeoutPeriod(0);
        list.add(data);
        command.setRecords(list);
        testSerialization(command);
    }

    @Test
    public void testConfigureReportingResponseCommand() throws Exception {
        final ConfigureReportingResponse command = new ConfigureReportingResponse();
        final List<AttributeStatusRecord> list = new ArrayList<AttributeStatusRecord>();
        final AttributeStatusRecord data = new AttributeStatusRecord();
        data.setStatus(0);
        data.setDirection(false);
        data.setAttributeIdentifier(0);
        list.add(data);
        command.setRecords(list);
        testSerialization(command);
    }

    @Test
    public void testReadReportingConfigurationCommand() throws Exception {
        final ReadReportingConfigurationCommand command = new ReadReportingConfigurationCommand();
        final List<AttributeRecord> list = new ArrayList<AttributeRecord>();
        final AttributeRecord data = new AttributeRecord();
        data.setDirection(true);
        data.setAttributeIdentifier(1);
        list.add(data);
        command.setRecords(list);
        testSerialization(command);
    }

    @Test
    public void testReadReportingConfigurationResponseCommand() throws Exception {
        final ReadReportingConfigurationResponse command = new ReadReportingConfigurationResponse();
        final List<AttributeReportingConfigurationRecord> list = new ArrayList<AttributeReportingConfigurationRecord>();
        final AttributeReportingConfigurationRecord data = new AttributeReportingConfigurationRecord();
        data.setAttributeIdentifier(1);
        data.setMinimumReportingInterval(2);
        data.setMaximumReportingInterval(3);
        data.setAttributeDataType(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        data.setReportableChange(1);
        data.setTimeoutPeriod(0);
        list.add(data);
        command.setRecords(list);
        testSerialization(command);
    }

    @Test
    public void testReportAttributesCommand() throws Exception {
        final ReportAttributesCommand command = new ReportAttributesCommand();
        final List<AttributeReport> list = new ArrayList<AttributeReport>();
        final AttributeReport data = new AttributeReport();
        data.setAttributeIdentifier(1);
        data.setAttributeDataType(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        data.setAttributeValue(1);
        list.add(data);
        command.setReports(list);
        testSerialization(command);
    }

    @Test
    public void testAddSceneCommand() throws Exception {
        final AddSceneCommand command = new AddSceneCommand();
        command.setGroupId(1);
        command.setSceneId(2);
        command.setSceneName("test");
        command.setTransitionTime(3);
        final List<ExtensionFieldSet> list = new ArrayList<ExtensionFieldSet>();
        final ExtensionFieldSet data = new ExtensionFieldSet();
        data.setClusterId(4);
        data.setLength(1);
        data.setData(new byte[] { (byte) 6 });
        list.add(data);
        command.setExtensionFieldSets(list);
        testSerialization(command);
    }

    @Test
    public void testReportRssiMeasurementsCommand() throws Exception {
        final ReportRssiMeasurementsCommand command = new ReportRssiMeasurementsCommand();
        command.setReportingAddress(0L);
        command.setNumberOfNeighbors(1);
        final List<NeighborInformation> list = new ArrayList<NeighborInformation>();
        final NeighborInformation data = new NeighborInformation();
        data.setNeighborAddress(2);
        data.setCoordinate1(3);
        data.setCoordinate2(4);
        data.setCoordinate3(5);
        data.setRssi(6);
        data.setMeasurementCount(7);
        list.add(data);
        command.setNeighborsInformation(list);
        testSerialization(command);
    }

    @Test
    public void testBypassCommand() throws Exception {
        final BypassCommand command = new BypassCommand();
        command.setNumberOfZones(1);
        final List<Unsigned8BitInteger> list = new ArrayList<Unsigned8BitInteger>();
        final Unsigned8BitInteger data = new Unsigned8BitInteger();
        data.setValue(2);
        list.add(data);
        command.setZoneIDs(list);
        testSerialization(command);
    }

    @Test
    public void testGetGroupMembershipCommand() throws Exception {
        final GetGroupMembershipCommand command = new GetGroupMembershipCommand();
        command.setGroupCount(1);
        final List<Unsigned16BitInteger> list = new ArrayList<Unsigned16BitInteger>();
        final Unsigned16BitInteger data = new Unsigned16BitInteger();
        data.setValue(2);
        list.add(data);
        command.setGroupList(list);
        testSerialization(command);
    }

    /**
     * Tests command serialization.
     *
     * @param command the command
     * @throws IOException if IO exception occurs.
     */
    private void testSerialization(final ZclCommand command) throws IOException {
        System.out.println(command);

        DefaultSerializer serializer = new DefaultSerializer();
        final ZclFieldSerializer fieldSerializer = new ZclFieldSerializer(serializer);
        command.serialize(fieldSerializer);
        int[] payload = serializer.getPayload();
        String request = command.toString();

        DefaultDeserializer deserializer = new DefaultDeserializer(payload);
        ZclFieldDeserializer fieldDeserializer = new ZclFieldDeserializer(deserializer);

        command.deserialize(fieldDeserializer);
        String response = command.toString();

        Assert.assertEquals("Command equality after payload ZigBee serialization", request, response);
    }

}

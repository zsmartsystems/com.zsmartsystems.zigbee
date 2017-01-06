package com.zsmartsystems.zigbee.zcl.clusters;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.ZigBeeDeviceAddress;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.zcl.ZclAttribute;
import com.zsmartsystems.zigbee.zcl.ZclCluster;
import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.clusters.general.ConfigureReportingCommand;
import com.zsmartsystems.zigbee.zcl.clusters.general.ConfigureReportingResponse;
import com.zsmartsystems.zigbee.zcl.clusters.general.DefaultResponse;
import com.zsmartsystems.zigbee.zcl.clusters.general.DiscoverAttributesCommand;
import com.zsmartsystems.zigbee.zcl.clusters.general.DiscoverAttributesResponse;
import com.zsmartsystems.zigbee.zcl.clusters.general.ReadAttributesCommand;
import com.zsmartsystems.zigbee.zcl.clusters.general.ReadAttributesResponse;
import com.zsmartsystems.zigbee.zcl.clusters.general.ReadAttributesStructuredCommand;
import com.zsmartsystems.zigbee.zcl.clusters.general.ReadReportingConfigurationCommand;
import com.zsmartsystems.zigbee.zcl.clusters.general.ReadReportingConfigurationResponse;
import com.zsmartsystems.zigbee.zcl.clusters.general.ReportAttributesCommand;
import com.zsmartsystems.zigbee.zcl.clusters.general.WriteAttributesCommand;
import com.zsmartsystems.zigbee.zcl.clusters.general.WriteAttributesNoResponse;
import com.zsmartsystems.zigbee.zcl.clusters.general.WriteAttributesResponse;
import com.zsmartsystems.zigbee.zcl.clusters.general.WriteAttributesStructuredCommand;
import com.zsmartsystems.zigbee.zcl.clusters.general.WriteAttributesStructuredResponse;
import com.zsmartsystems.zigbee.zcl.clusters.general.WriteAttributesUndividedCommand;
import com.zsmartsystems.zigbee.zcl.field.*;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * <b>General</b> cluster implementation (<i>Cluster ID 0xFFFF</i>).
 * Code is auto-generated. Modifications may be overwritten!
 */
public class ZclGeneralCluster extends ZclCluster {
    // Cluster ID
    public static final int CLUSTER_ID = 0xFFFF;

    // Cluster Name
    public static final String CLUSTER_NAME = "General";

    // Attribute initialisation
    protected Map<Integer, ZclAttribute> initializeAttributes() {
        Map<Integer, ZclAttribute> attributeMap = new HashMap<Integer, ZclAttribute>(0);


        return attributeMap;
    }

    /**
     * Default constructor.
     */
    public ZclGeneralCluster(final ZigBeeNetworkManager zigbeeManager, final ZigBeeDeviceAddress zigbeeAddress) {
        super(zigbeeManager, zigbeeAddress, CLUSTER_ID, CLUSTER_NAME);
    }


    /**
     * The Read Attributes Command
     * <p>
     * The read attributes command is generated when a device wishes to determine the
     * values of one or more attributes located on another device. Each attribute
     * identifier field shall contain the identifier of the attribute to be read.
     * </p>
     *
     * @param identifiers {@link List<AttributeIdentifier>} Identifiers
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> readAttributesCommand(List<AttributeIdentifier> identifiers) {
        ReadAttributesCommand command = new ReadAttributesCommand();

        // Set the fields
        command.setIdentifiers(identifiers);

        return send(command);
    }

    /**
     * The Read Attributes Response
     * <p>
     * The read attributes response command is generated in response to a read attributes
     * or read attributes structured command. The command frame shall contain a read
     * attribute status record for each attribute identifier specified in the original read
     * attributes or read attributes structured command. For each read attribute status
     * record, the attribute identifier field shall contain the identifier specified in the
     * original read attributes or read attributes structured command.
     * </p>
     *
     * @param records {@link List<ReadAttributeStatusRecord>} Records
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> readAttributesResponse(List<ReadAttributeStatusRecord> records) {
        ReadAttributesResponse command = new ReadAttributesResponse();

        // Set the fields
        command.setRecords(records);

        return send(command);
    }

    /**
     * The Write Attributes Command
     * <p>
     * The write attributes command is generated when a device wishes to change the
     * values of one or more attributes located on another device. Each write attribute
     * record shall contain the identifier and the actual value of the attribute to be
     * written.
     * </p>
     *
     * @param records {@link List<WriteAttributeRecord>} Records
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> writeAttributesCommand(List<WriteAttributeRecord> records) {
        WriteAttributesCommand command = new WriteAttributesCommand();

        // Set the fields
        command.setRecords(records);

        return send(command);
    }

    /**
     * The Write Attributes Undivided Command
     * <p>
     * The write attributes undivided command is generated when a device wishes to
     * change the values of one or more attributes located on another device, in such a
     * way that if any attribute cannot be written (e.g. if an attribute is not implemented
     * on the device, or a value to be written is outside its valid range), no attribute
     * values are changed.
     * <br>
     * In all other respects, including generation of a write attributes response command,
     * the format and operation of the command is the same as that of the write attributes
     * command, except that the command identifier field shall be set to indicate the
     * write attributes undivided command.
     * </p>
     *
     * @param records {@link List<WriteAttributeRecord>} Records
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> writeAttributesUndividedCommand(List<WriteAttributeRecord> records) {
        WriteAttributesUndividedCommand command = new WriteAttributesUndividedCommand();

        // Set the fields
        command.setRecords(records);

        return send(command);
    }

    /**
     * The Write Attributes Response
     * <p>
     * The write attributes response command is generated in response to a write
     * attributes command.
     * </p>
     *
     * @param records {@link List<WriteAttributeStatusRecord>} Records
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> writeAttributesResponse(List<WriteAttributeStatusRecord> records) {
        WriteAttributesResponse command = new WriteAttributesResponse();

        // Set the fields
        command.setRecords(records);

        return send(command);
    }

    /**
     * The Write Attributes No Response
     * <p>
     * The write attributes no response command is generated when a device wishes to
     * change the value of one or more attributes located on another device but does not
     * require a response. Each write attribute record shall contain the identifier and the
     * actual value of the attribute to be written.
     * </p>
     *
     * @param records {@link List<WriteAttributeRecord>} Records
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> writeAttributesNoResponse(List<WriteAttributeRecord> records) {
        WriteAttributesNoResponse command = new WriteAttributesNoResponse();

        // Set the fields
        command.setRecords(records);

        return send(command);
    }

    /**
     * The Configure Reporting Command
     * <p>
     * The Configure Reporting command is used to configure the reporting mechanism
     * for one or more of the attributes of a cluster.
     * <br>
     * The individual cluster definitions specify which attributes shall be available to this
     * reporting mechanism, however specific implementations of a cluster may make
     * additional attributes available.
     * </p>
     *
     * @param records {@link List<AttributeReportingConfigurationRecord>} Records
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> configureReportingCommand(List<AttributeReportingConfigurationRecord> records) {
        ConfigureReportingCommand command = new ConfigureReportingCommand();

        // Set the fields
        command.setRecords(records);

        return send(command);
    }

    /**
     * The Configure Reporting Response
     * <p>
     * The Configure Reporting Response command is generated in response to a
     * Configure Reporting command.
     * </p>
     *
     * @param records {@link List<AttributeStatusRecord>} Records
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> configureReportingResponse(List<AttributeStatusRecord> records) {
        ConfigureReportingResponse command = new ConfigureReportingResponse();

        // Set the fields
        command.setRecords(records);

        return send(command);
    }

    /**
     * The Read Reporting Configuration Command
     * <p>
     * The Read Reporting Configuration command is used to read the configuration
     * details of the reporting mechanism for one or more of the attributes of a cluster.
     * </p>
     *
     * @param records {@link List<AttributeRecord>} Records
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> readReportingConfigurationCommand(List<AttributeRecord> records) {
        ReadReportingConfigurationCommand command = new ReadReportingConfigurationCommand();

        // Set the fields
        command.setRecords(records);

        return send(command);
    }

    /**
     * The Read Reporting Configuration Response
     * <p>
     * The Read Reporting Configuration Response command is used to respond to a
     * Read Reporting Configuration command.
     * </p>
     *
     * @param records {@link List<AttributeReportingConfigurationRecord>} Records
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> readReportingConfigurationResponse(List<AttributeReportingConfigurationRecord> records) {
        ReadReportingConfigurationResponse command = new ReadReportingConfigurationResponse();

        // Set the fields
        command.setRecords(records);

        return send(command);
    }

    /**
     * The Report Attributes Command
     * <p>
     * The report attributes command is used by a device to report the values of one or
     * more of its attributes to another device, bound a priori. Individual clusters, defined
     * elsewhere in the ZCL, define which attributes are to be reported and at what
     * interval.
     * </p>
     *
     * @param reports {@link List<AttributeReport>} Reports
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> reportAttributesCommand(List<AttributeReport> reports) {
        ReportAttributesCommand command = new ReportAttributesCommand();

        // Set the fields
        command.setReports(reports);

        return send(command);
    }

    /**
     * The Default Response
     * <p>
     * The default response command is generated when a device receives a unicast
     * command, there is no other relevant response specified for the command, and
     * either an error results or the Disable default response bit of its Frame control field
     * is set to 0.
     * </p>
     *
     * @param commandIdentifier {@link Integer} Command identifier
     * @param statusCode {@link Integer} Status code
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> defaultResponse(Integer commandIdentifier, Integer statusCode) {
        DefaultResponse command = new DefaultResponse();

        // Set the fields
        command.setCommandIdentifier(commandIdentifier);
        command.setStatusCode(statusCode);

        return send(command);
    }

    /**
     * The Discover Attributes Command
     * <p>
     * The discover attributes command is generated when a remote device wishes to
     * discover the identifiers and types of the attributes on a device which are supported
     * within the cluster to which this command is directed.
     * </p>
     *
     * @param startAttributeIdentifier {@link Integer} Start attribute identifier
     * @param maximumAttributeIdentifiers {@link Integer} Maximum attribute identifiers
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> discoverAttributesCommand(Integer startAttributeIdentifier, Integer maximumAttributeIdentifiers) {
        DiscoverAttributesCommand command = new DiscoverAttributesCommand();

        // Set the fields
        command.setStartAttributeIdentifier(startAttributeIdentifier);
        command.setMaximumAttributeIdentifiers(maximumAttributeIdentifiers);

        return send(command);
    }

    /**
     * The Discover Attributes Response
     * <p>
     * The discover attributes response command is generated in response to a discover
     * attributes command.
     * </p>
     *
     * @param commandIdentifier {@link Boolean} Command identifier
     * @param information {@link List<AttributeInformation>} Information
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> discoverAttributesResponse(Boolean commandIdentifier, List<AttributeInformation> information) {
        DiscoverAttributesResponse command = new DiscoverAttributesResponse();

        // Set the fields
        command.setCommandIdentifier(commandIdentifier);
        command.setInformation(information);

        return send(command);
    }

    /**
     * The Read Attributes Structured Command
     * <p>
     * The read attributes command is generated when a device wishes to determine the
     * values of one or more attributes, or elements of attributes, located on another
     * device. Each attribute identifier field shall contain the identifier of the attribute to
     * be read.
     * </p>
     *
     * @param attributeSelectors {@link Object} Attribute selectors
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> readAttributesStructuredCommand(Object attributeSelectors) {
        ReadAttributesStructuredCommand command = new ReadAttributesStructuredCommand();

        // Set the fields
        command.setAttributeSelectors(attributeSelectors);

        return send(command);
    }

    /**
     * The Write Attributes Structured Command
     * <p>
     * The write attributes structured command is generated when a device wishes to
     * change the values of one or more attributes located on another device. Each write
     * attribute record shall contain the identifier and the actual value of the attribute, or
     * element thereof, to be written.
     * </p>
     *
     * @param attributeSelectors {@link Object} Attribute selectors
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> writeAttributesStructuredCommand(Object attributeSelectors) {
        WriteAttributesStructuredCommand command = new WriteAttributesStructuredCommand();

        // Set the fields
        command.setAttributeSelectors(attributeSelectors);

        return send(command);
    }

    /**
     * The Write Attributes Structured Response
     * <p>
     * The write attributes structured response command is generated in response to a
     * write attributes structured command.
     * </p>
     *
     * @param records {@link List<WriteAttributeStatusRecord>} Records
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> writeAttributesStructuredResponse(List<WriteAttributeStatusRecord> records) {
        WriteAttributesStructuredResponse command = new WriteAttributesStructuredResponse();

        // Set the fields
        command.setRecords(records);

        return send(command);
    }

    @Override
    public ZclCommand getCommandFromId(int commandId) {
        switch (commandId) {
            case 0: // READ_ATTRIBUTES_COMMAND
                return new ReadAttributesCommand();
            case 1: // READ_ATTRIBUTES_RESPONSE
                return new ReadAttributesResponse();
            case 2: // WRITE_ATTRIBUTES_COMMAND
                return new WriteAttributesCommand();
            case 3: // WRITE_ATTRIBUTES_UNDIVIDED_COMMAND
                return new WriteAttributesUndividedCommand();
            case 4: // WRITE_ATTRIBUTES_RESPONSE
                return new WriteAttributesResponse();
            case 5: // WRITE_ATTRIBUTES_NO_RESPONSE
                return new WriteAttributesNoResponse();
            case 6: // CONFIGURE_REPORTING_COMMAND
                return new ConfigureReportingCommand();
            case 7: // CONFIGURE_REPORTING_RESPONSE
                return new ConfigureReportingResponse();
            case 8: // READ_REPORTING_CONFIGURATION_COMMAND
                return new ReadReportingConfigurationCommand();
            case 9: // READ_REPORTING_CONFIGURATION_RESPONSE
                return new ReadReportingConfigurationResponse();
            case 10: // REPORT_ATTRIBUTES_COMMAND
                return new ReportAttributesCommand();
            case 11: // DEFAULT_RESPONSE
                return new DefaultResponse();
            case 12: // DISCOVER_ATTRIBUTES_COMMAND
                return new DiscoverAttributesCommand();
            case 13: // DISCOVER_ATTRIBUTES_RESPONSE
                return new DiscoverAttributesResponse();
            case 14: // READ_ATTRIBUTES_STRUCTURED_COMMAND
                return new ReadAttributesStructuredCommand();
            case 15: // WRITE_ATTRIBUTES_STRUCTURED_COMMAND
                return new WriteAttributesStructuredCommand();
            case 16: // WRITE_ATTRIBUTES_STRUCTURED_RESPONSE
                return new WriteAttributesStructuredResponse();
            default:
                return null;
        }
    }
}

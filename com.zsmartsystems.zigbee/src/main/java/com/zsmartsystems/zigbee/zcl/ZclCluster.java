/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeEndpointAddress;
import com.zsmartsystems.zigbee.database.ZclAttributeDao;
import com.zsmartsystems.zigbee.database.ZclClusterDao;
import com.zsmartsystems.zigbee.internal.NotificationService;
import com.zsmartsystems.zigbee.zcl.clusters.general.ConfigureReportingCommand;
import com.zsmartsystems.zigbee.zcl.clusters.general.ConfigureReportingResponse;
import com.zsmartsystems.zigbee.zcl.clusters.general.DefaultResponse;
import com.zsmartsystems.zigbee.zcl.clusters.general.DiscoverAttributesCommand;
import com.zsmartsystems.zigbee.zcl.clusters.general.DiscoverAttributesExtended;
import com.zsmartsystems.zigbee.zcl.clusters.general.DiscoverAttributesExtendedResponse;
import com.zsmartsystems.zigbee.zcl.clusters.general.DiscoverAttributesResponse;
import com.zsmartsystems.zigbee.zcl.clusters.general.DiscoverCommandsGenerated;
import com.zsmartsystems.zigbee.zcl.clusters.general.DiscoverCommandsGeneratedResponse;
import com.zsmartsystems.zigbee.zcl.clusters.general.DiscoverCommandsReceived;
import com.zsmartsystems.zigbee.zcl.clusters.general.DiscoverCommandsReceivedResponse;
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
import com.zsmartsystems.zigbee.zcl.clusters.general.ZclGeneralCommand;
import com.zsmartsystems.zigbee.zcl.field.AttributeInformation;
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
 * Base class for the ZCL Cluster
 *
 * @author Chris Jackson
 *
 */
public abstract class ZclCluster {
    /**
     * The logger
     */
    private Logger logger = LoggerFactory.getLogger(ZclCluster.class);

    /**
     * The ClusterRevision global attribute is mandatory for all cluster instances, client
     * and server, conforming to ZCL revision 6 (ZCL6) and later ZCL revisions. This cluster
     * attribute represents the revision of the cluster specification that has been
     * implemented. An implementation of a cluster specification before ZCL6 shall have an
     * assumed cluster revision of 0. The initial value for the ClusterRevision attribute
     * shall be 1. The ClusterRevision attribute shall be incremented and associated with
     * each approved revision and release of a cluster specification.
     */
    public static final int ATTR_CLUSTERREVISION = 0xFFFD;
    /**
     * When reporting requires sending multiple Report Attributes commands, this attribute
     * should be included in the last attribute record, to indicate that all required
     * attributes have been reported, or that there are still attributes pending to be
     * reported
     */
    public static final int ATTR_ATTRIBUTEREPORTINGSTATUS = 0xFFFE;

    /**
     * The {@link ZigBeeEndpoint} to which this cluster belongs
     */
    private final ZigBeeEndpoint zigbeeEndpoint;

    /**
     * The ZCL cluster ID for this cluster
     */
    protected int clusterId;

    /**
     * The name of this cluster
     */
    protected final String clusterName;

    /**
     * Defines if the remote is a client (true) or server (false)
     * The definition of the direction is based on the remote being the server. If it is really
     * a server, then we need to reverse direction
     */
    private boolean isClient = false;

    /**
     * The list of supported attributes in the remote device for this cluster.
     * After initialisation, the list will contain an empty list. Once a successful call to
     * {@link #discoverAttributes()} has been made, the list will reflect the attributes supported by the remote device.
     */
    private final Set<Integer> supportedAttributes = new HashSet<>();

    /**
     * A boolean used to record if the list of supported attributes has been recovered from the remote device. This is
     * used to record the validity of {@link #supportedAttributes}
     */
    private boolean supportedAttributesKnown = false;

    /**
     * The list of supported commands that the remote device can generate
     */
    private final Set<Integer> supportedCommandsReceived = new HashSet<>();

    /**
     * The list of supported commands that the remote device can receive
     */
    private final Set<Integer> supportedCommandsGenerated = new HashSet<>();

    /**
     * Set of listeners to receive notifications when an attribute updates its value
     */
    private final Set<ZclAttributeListener> attributeListeners = new CopyOnWriteArraySet<>();

    /**
     * Set of listeners to receive notifications when a command is received
     */
    private final Set<ZclCommandListener> commandListeners = new CopyOnWriteArraySet<>();

    /**
     * Map of client attributes supported by the cluster. This contains all attributes, even if they are not supported
     * by the remote device. To check what attributes are supported by the remove device, use the
     * {@link #discoverAttributes()} method followed by the {@link #getSupportedAttributes()} method.
     */
    protected Map<Integer, ZclAttribute> clientAttributes = initializeClientAttributes();

    /**
     * Map of server attributes supported by the cluster. This contains all attributes, even if they are not supported
     * by the remote device. To check what attributes are supported by the remove device, use the
     * {@link #discoverAttributes()} method followed by the {@link #getSupportedAttributes()} method.
     */
    protected Map<Integer, ZclAttribute> serverAttributes = initializeServerAttributes();

    /**
     * Map of server side commands supported by the cluster. This contains all server commands, even if they are not
     * supported by the remote device.
     */
    protected Map<Integer, Class<? extends ZclCommand>> serverCommands = initializeServerCommands();

    /**
     * Map of client side commands supported by the cluster. This contains all client commands, even if they are not
     * supported by the remote device.
     */
    protected Map<Integer, Class<? extends ZclCommand>> clientCommands = initializeClientCommands();

    /**
     * Map of the generic commands as implemented by all clusters
     */
    protected static Map<Integer, Class<? extends ZclCommand>> genericCommands = new HashMap<>();

    /**
     * Set of generic commands internally handled by this class
     */
    private static Set<Class<? extends ZclCommand>> supportedGenericCommands = new HashSet<>();

    /**
     * The manufacturer specific code, or null if this is not a manufacturer specific cluster
     */
    protected Integer manufacturerCode;

    /**
     * The {@link ZclAttributeNormalizer} is used to normalize attribute data types to ensure that data types are
     * consistent with the ZCL definition. This ensures that the application can rely on consistent and deterministic
     * data type when listening to attribute updates.
     */
    private final ZclAttributeNormalizer normalizer;

    /**
     * If this cluster requires all frames to have APS security applied, then this will be true. Any frames not secured
     * with the link key will be rejected and all frames sent will use APS encryption.
     */
    private boolean apsSecurityRequired = false;

    static {
        genericCommands.put(0x0000, ReadAttributesCommand.class);
        genericCommands.put(0x0001, ReadAttributesResponse.class);
        genericCommands.put(0x0002, WriteAttributesCommand.class);
        genericCommands.put(0x0003, WriteAttributesUndividedCommand.class);
        genericCommands.put(0x0004, WriteAttributesResponse.class);
        genericCommands.put(0x0005, WriteAttributesNoResponse.class);
        genericCommands.put(0x0006, ConfigureReportingCommand.class);
        genericCommands.put(0x0007, ConfigureReportingResponse.class);
        genericCommands.put(0x0008, ReadReportingConfigurationCommand.class);
        genericCommands.put(0x0009, ReadReportingConfigurationResponse.class);
        genericCommands.put(0x000A, ReportAttributesCommand.class);
        genericCommands.put(0x000B, DefaultResponse.class);
        genericCommands.put(0x000C, DiscoverAttributesCommand.class);
        genericCommands.put(0x000D, DiscoverAttributesResponse.class);
        genericCommands.put(0x000E, ReadAttributesStructuredCommand.class);
        genericCommands.put(0x000F, WriteAttributesStructuredCommand.class);
        genericCommands.put(0x0010, WriteAttributesStructuredResponse.class);
        genericCommands.put(0x0011, DiscoverCommandsReceived.class);
        genericCommands.put(0x0012, DiscoverCommandsReceivedResponse.class);
        genericCommands.put(0x0013, DiscoverCommandsGenerated.class);
        genericCommands.put(0x0014, DiscoverCommandsGeneratedResponse.class);
        genericCommands.put(0x0015, DiscoverAttributesExtended.class);
        genericCommands.put(0x0016, DiscoverAttributesExtendedResponse.class);

        supportedGenericCommands.add(ReadAttributesCommand.class);
        supportedGenericCommands.add(ReadAttributesResponse.class);
        supportedGenericCommands.add(WriteAttributesResponse.class);
        supportedGenericCommands.add(ConfigureReportingResponse.class);
        supportedGenericCommands.add(ReadReportingConfigurationResponse.class);
        supportedGenericCommands.add(ReportAttributesCommand.class);
        supportedGenericCommands.add(DiscoverAttributesCommand.class);
        supportedGenericCommands.add(DiscoverAttributesResponse.class);
        supportedGenericCommands.add(DiscoverCommandsReceivedResponse.class);
        supportedGenericCommands.add(DiscoverCommandsGeneratedResponse.class);
    }

    /**
     * Abstract method called when the cluster starts to initialise the list of client attributes defined in this
     * cluster by the cluster library
     *
     * @return a {@link Map} of all attributes this cluster is known to support
     */
    protected Map<Integer, ZclAttribute> initializeClientAttributes() {
        Map<Integer, ZclAttribute> attributeMap = new ConcurrentSkipListMap<>();

        attributeMap.put(ATTR_CLUSTERREVISION, new ZclAttribute(this, ATTR_CLUSTERREVISION, "Cluster Revision",
                ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_ATTRIBUTEREPORTINGSTATUS, new ZclAttribute(this, ATTR_ATTRIBUTEREPORTINGSTATUS,
                "Attribute Reporting Status", ZclDataType.ENUMERATION_16_BIT, true, true, false, false));

        return attributeMap;
    }

    /**
     * Abstract method called when the cluster starts to initialise the list of server attributes defined in this
     * cluster by the cluster library
     *
     * @return a {@link Map} of all attributes this cluster is known to support
     */
    protected Map<Integer, ZclAttribute> initializeServerAttributes() {
        Map<Integer, ZclAttribute> attributeMap = new ConcurrentSkipListMap<>();

        attributeMap.put(ATTR_CLUSTERREVISION, new ZclAttribute(this, ATTR_CLUSTERREVISION, "Cluster Revision",
                ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_ATTRIBUTEREPORTINGSTATUS, new ZclAttribute(this, ATTR_ATTRIBUTEREPORTINGSTATUS,
                "Attribute Reporting Status", ZclDataType.ENUMERATION_16_BIT, true, true, false, false));

        return attributeMap;
    }

    /**
     * Abstract method called when the cluster starts to initialise the list of server side commands defined in this
     * cluster by the cluster library
     *
     * @return a {@link Map} of all server side commands this cluster is known to support
     */
    protected Map<Integer, Class<? extends ZclCommand>> initializeServerCommands() {
        return new ConcurrentHashMap<>(0);
    }

    /**
     * Abstract method called when the cluster starts to initialise the list of client side commands defined in this
     * cluster by the cluster library
     *
     * @return a {@link Map} of all client side commands this cluster is known to support
     */
    protected Map<Integer, Class<? extends ZclCommand>> initializeClientCommands() {
        return new ConcurrentHashMap<>(0);
    }

    /**
     * Creates a cluster
     *
     * @param zigbeeEndpoint the {@link ZigBeeEndpoint} to which the cluster belongs
     * @param clusterId the 16 bit cluster identifier
     * @param clusterName the cluster name
     */
    protected ZclCluster(ZigBeeEndpoint zigbeeEndpoint, int clusterId, String clusterName) {
        this.zigbeeEndpoint = zigbeeEndpoint;
        this.clusterId = clusterId;
        this.clusterName = clusterName;
        this.normalizer = new ZclAttributeNormalizer();
    }

    /**
     * Sends a {@link ZclCommand} and returns the {@link Future} to the result which will complete when the remote
     * device response is received, or the request times out.
     *
     * @param command the {@link ZclCommand} to send
     * @return the command result future
     */
    protected Future<CommandResult> sendCommand(ZclCommand command) {
        if (isClient()) {
            command.setCommandDirection(ZclCommandDirection.SERVER_TO_CLIENT);
        }

        command.setClusterId(clusterId);
        command.setApsSecurity(apsSecurityRequired);

        return zigbeeEndpoint.sendTransaction(command, new ZclTransactionMatcher());
    }

    /**
     * Sends a {@link ZclGeneralCommand} and returns the {@link Future} to the result which will complete when the
     * remote device response is received, or the request times out.
     *
     * @param command the {@link ZclGeneralCommand} to send
     * @return the command result future
     */
    public Future<CommandResult> sendCommand(ZclGeneralCommand command) {
        return sendCommand((ZclCommand) command);
    }

    /**
     * Sends a response to the command. This method sets all the common elements of the response based on the command -
     * eg transactionId, direction, address...
     *
     * @param command the {@link ZclCommand} to which the response is being sent
     * @param response the {@link ZclCommand} to send
     * @return the response result future
     */
    protected Future<CommandResult> sendResponse(ZclCommand command, ZclCommand response) {
        response.setDestinationAddress(command.getSourceAddress());
        response.setCommandDirection(command.getCommandDirection().getResponseDirection());
        response.setTransactionId(command.getTransactionId());
        response.setDisableDefaultResponse(true);

        if (isManufacturerSpecific()) {
            response.setManufacturerCode(getManufacturerCode());
        }

        response.setApsSecurity(apsSecurityRequired);

        return sendCommand(response);
    }

    /**
     * Sends a default response to the client
     *
     * @param command the {@link ZclCommand} to which we are responding
     * @param status the {@link ZclStatus} to send in the response
     */
    public void sendDefaultResponse(ZclCommand command, ZclStatus status) {
        ZclCommand response = createDefaultResponse(command, status);
        if (response == null) {
            return;
        }

        sendResponse(command, response);
    }

    /**
     * Sends a default response to the client
     *
     * @param transactionId the transaction ID to use in the response
     * @param commandIdentifier the command identifier to which this is a response
     * @param status the {@link ZclStatus} to send in the response
     * @param manufacturerCode the manufacturer code to set in the response (or null, if the command is not
     *            manufacturer-specific, or if the cluster is itself manufacturer-specific)
     * @deprecated use sendDefaultResponse(ZclCommand command, ZclStatus status)
     */
    @Deprecated
    public void sendDefaultResponse(Integer transactionId, Integer commandIdentifier, ZclStatus status,
            Integer manufacturerCode) {
        DefaultResponse defaultResponse = new DefaultResponse();
        defaultResponse.setTransactionId(transactionId);
        defaultResponse.setCommandIdentifier(commandIdentifier);
        defaultResponse.setDestinationAddress(zigbeeEndpoint.getEndpointAddress());
        defaultResponse.setClusterId(clusterId);
        defaultResponse.setStatusCode(status);

        if (isManufacturerSpecific()) {
            defaultResponse.setManufacturerCode(getManufacturerCode());
        } else if (manufacturerCode != null) {
            defaultResponse.setManufacturerCode(manufacturerCode);
        }

        sendCommand(defaultResponse);
    }

    /**
     * Sends a default response to the client
     *
     * @param transactionId the transaction ID to use in the response
     * @param commandIdentifier the command identifier to which this is a response
     * @param status the {@link ZclStatus} to send in the response
     * @deprecated use sendDefaultResponse(ZclCommand command, ZclStatus status)
     */
    @Deprecated
    public void sendDefaultResponse(Integer transactionId, Integer commandIdentifier, ZclStatus status) {
        sendDefaultResponse(transactionId, commandIdentifier, status, null);
    }

    /**
     * Write an attribute
     *
     * @param attributeId the attribute ID to write
     * @param dataType the {@link ZclDataType} of the object
     * @param value the value to set (as {@link Object})
     * @return command future {@link CommandResult}
     */
    public Future<CommandResult> writeAttribute(final int attributeId, final ZclDataType dataType, final Object value) {
        logger.debug("{}: Writing {} cluster {}, attribute {}, value {}, as dataType {}",
                zigbeeEndpoint.getIeeeAddress(), (isClient ? "Client" : "Server"), clusterId, attributeId, value,
                dataType);
        final WriteAttributeRecord attributeIdentifier = new WriteAttributeRecord();
        attributeIdentifier.setAttributeIdentifier(attributeId);
        attributeIdentifier.setAttributeDataType(dataType);
        attributeIdentifier.setAttributeValue(value);
        return writeAttributes(Collections.singletonList(attributeIdentifier));
    }

    /**
     * Writes a number of attributes in a single command
     *
     * @param attributes a List of {@link WriteAttributeRecord}s with the attribute ID, type and value
     * @return command future {@link CommandResult}
     */
    public Future<CommandResult> writeAttributes(List<WriteAttributeRecord> attributes) {
        final WriteAttributesCommand command = new WriteAttributesCommand(attributes);
        command.setClusterId(clusterId);
        command.setDestinationAddress(zigbeeEndpoint.getEndpointAddress());

        ZclAttribute manufacturerSpecificAttribute = null;
        for (WriteAttributeRecord attributeRecord : attributes) {
            ZclAttribute attribute = getAttribute(attributeRecord.getAttributeIdentifier());
            if (attribute != null) {
                if (attribute.isManufacturerSpecific()) {
                    manufacturerSpecificAttribute = attribute;
                    break;
                }
            }
        }

        if (isManufacturerSpecific()) {
            command.setManufacturerCode(getManufacturerCode());
        } else if (manufacturerSpecificAttribute != null) {
            command.setManufacturerCode(manufacturerSpecificAttribute.getManufacturerCode());
        }

        return sendCommand(command);
    }

    /**
     * Read an attribute given the attribute ID. This method will always send a {@link ReadAttributesCommand} to the
     * remote device.
     *
     * @param attributeId the integer attribute ID to read
     * @return command future
     */
    public Future<CommandResult> readAttribute(final int attributeId) {
        return readAttributes(Collections.singletonList(attributeId));
    }

    /**
     * Read a number of attributes given a list of attribute IDs. Care must be taken not to request too many attributes
     * so as to exceed the allowable frame length
     *
     * @param attributeIds List of attribute identifiers to read
     * @return command future
     */
    public Future<CommandResult> readAttributes(final List<Integer> attributeIds) {
        final ReadAttributesCommand command = new ReadAttributesCommand(attributeIds);

        command.setClusterId(clusterId);
        command.setDestinationAddress(zigbeeEndpoint.getEndpointAddress());

        if (!attributeIds.isEmpty() && isManufacturerSpecific()) {
            command.setManufacturerCode(getManufacturerCode());
        } else if (areAttributesManufacturerSpecific(attributeIds)) {
            command.setManufacturerCode(getAttribute(attributeIds.get(0)).getManufacturerCode());
        }

        return sendCommand(command);
    }

    /**
     * Read an attribute from the remote cluster
     *
     * @param attributeId the attribute id to read
     * @return and object containing the value, or null
     */
    protected Object readAttributeValue(final int attributeId) {
        CommandResult result = null;
        try {
            Future<CommandResult> readAttributeFuture = readAttribute(attributeId);
            if (readAttributeFuture != null) {
                result = readAttributeFuture.get();
            }
        } catch (InterruptedException e) {
            logger.debug("readAttributeValue interrupted");
            return null;
        } catch (ExecutionException e) {
            logger.debug("readAttributeValue exception ", e);
            return null;
        }

        if (result == null || !result.isSuccess()) {
            return null;
        }

        ReadAttributesResponse response = result.getResponse();
        if (response.getRecords().get(0).getStatus() != ZclStatus.SUCCESS) {
            return null;
        }

        // If we don't know this attribute, then just return the received data
        if (getAttribute(attributeId) == null) {
            return response.getRecords().get(0).getAttributeValue();
        }

        return normalizer.normalizeZclData(getAttribute(attributeId).getDataType(),
                response.getRecords().get(0).getAttributeValue());
    }

    /**
     * Report an attribute
     *
     * @param attributeId the attribute ID to write
     * @param dataType the {@link ZclDataType} of the object
     * @param value the value to set (as {@link Object})
     * @return command future {@link CommandResult}
     */
    public Future<CommandResult> reportAttribute(final int attributeId, final ZclDataType dataType,
            final Object value) {
        logger.debug("{}: Reporting {} cluster {}, attribute {}, value {}, as dataType {}",
                zigbeeEndpoint.getIeeeAddress(), (isClient ? "Client" : "Server"), clusterId, attributeId, value,
                dataType);
        final AttributeReport attributeIdentifier = new AttributeReport();
        attributeIdentifier.setAttributeIdentifier(attributeId);
        attributeIdentifier.setAttributeDataType(dataType);
        attributeIdentifier.setAttributeValue(value);
        return reportAttributes(Collections.singletonList(attributeIdentifier));
    }

    /**
     * Writes a number of attributes in a single command
     *
     * @param attributes a List of {@link WriteAttributeRecord}s with the attribute ID, type and value
     * @return command future {@link CommandResult}
     */
    public Future<CommandResult> reportAttributes(List<AttributeReport> attributes) {
        final ReportAttributesCommand command = new ReportAttributesCommand(attributes);
        command.setClusterId(clusterId);
        command.setDestinationAddress(zigbeeEndpoint.getEndpointAddress());

        ZclAttribute manufacturerSpecificAttribute = null;
        for (AttributeReport attributeRecord : attributes) {
            ZclAttribute attribute = getAttribute(attributeRecord.getAttributeIdentifier());
            if (attribute != null) {
                if (attribute.isManufacturerSpecific()) {
                    manufacturerSpecificAttribute = attribute;
                    break;
                }
            }
        }

        if (isManufacturerSpecific()) {
            command.setManufacturerCode(getManufacturerCode());
        } else if (manufacturerSpecificAttribute != null) {
            command.setManufacturerCode(manufacturerSpecificAttribute.getManufacturerCode());
        }

        return sendCommand(command);
    }

    /**
     * Configures the reporting for the specified attribute ID for discrete attributes.
     * <p>
     * <b>minInterval</b>:
     * The minimum reporting interval field is 16 bits in length and shall contain the
     * minimum interval, in seconds, between issuing reports of the specified attribute.
     * If minInterval is set to 0x0000, then there is no minimum limit, unless one is
     * imposed by the specification of the cluster using this reporting mechanism or by
     * the applicable profile.
     * <p>
     * <b>maxInterval</b>:
     * The maximum reporting interval field is 16 bits in length and shall contain the
     * maximum interval, in seconds, between issuing reports of the specified attribute.
     * If maxInterval is set to 0xffff, then the device shall not issue reports for the specified
     * attribute, and the configuration information for that attribute need not be
     * maintained.
     *
     * <b>reportableChange</b>:
     * The reportable change field shall contain the minimum change to the attribute that
     * will result in a report being issued. This field is of variable length. For attributes
     * with 'analog' data type the field has the same data type as the attribute. The sign (if any) of the reportable
     * change field is ignored.
     *
     * @param attributeId the attribute ID to configure reporting
     * @param minInterval the minimum reporting interval
     * @param maxInterval the maximum reporting interval
     * @param reportableChange the minimum change required to report an update
     * @return command future {@link CommandResult}
     */
    public Future<CommandResult> setReporting(final int attributeId, final int minInterval, final int maxInterval,
            Object reportableChange) {
        return setReporting(getAttribute(attributeId), minInterval, maxInterval, reportableChange);
    }

    /**
     * Configures the reporting for the specified attribute ID for discrete attributes.
     * <p>
     * <b>minInterval</b>:
     * The minimum reporting interval field is 16 bits in length and shall contain the
     * minimum interval, in seconds, between issuing reports of the specified attribute.
     * If minInterval is set to 0x0000, then there is no minimum limit, unless one is
     * imposed by the specification of the cluster using this reporting mechanism or by
     * the applicable profile.
     * <p>
     * <b>maxInterval</b>:
     * The maximum reporting interval field is 16 bits in length and shall contain the
     * maximum interval, in seconds, between issuing reports of the specified attribute.
     * If maxInterval is set to 0xffff, then the device shall not issue reports for the specified
     * attribute, and the configuration information for that attribute need not be
     * maintained.
     *
     * @param attributeId the attribute ID to configure reporting
     * @param minInterval the minimum reporting interval
     * @param maxInterval the maximum reporting interval
     * @return command future {@link CommandResult}
     */
    public Future<CommandResult> setReporting(final int attributeId, final int minInterval, final int maxInterval) {
        return setReporting(getAttribute(attributeId), minInterval, maxInterval, null);
    }

    /**
     * Gets the reporting configuration for an attribute
     *
     * @param attributeId the attribute on which to get the reporting configuration
     * @return command future {@link CommandResult}
     */
    public Future<CommandResult> getReporting(final int attributeId) {
        AttributeRecord record = new AttributeRecord();
        record.setAttributeIdentifier(attributeId);
        record.setDirection(0);
        final ReadReportingConfigurationCommand command = new ReadReportingConfigurationCommand(
                Collections.singletonList(record));
        command.setClusterId(clusterId);
        command.setDestinationAddress(zigbeeEndpoint.getEndpointAddress());

        if (isManufacturerSpecific()) {
            command.setManufacturerCode(getManufacturerCode());
        } else if (getAttribute(attributeId).isManufacturerSpecific()) {
            command.setManufacturerCode(getAttribute(attributeId).getManufacturerCode());
        }

        return sendCommand(command);
    }

    /**
     * Gets all the attributes supported by this cluster This will return all attributes, even if they are not actually
     * supported by the device. The user should check to see if this is implemented.
     * <p>
     * This will return either the list of client or server attributes, depending on the cluster.
     *
     * @return {@link Set} containing all {@link ZclAttributes} available in this cluster
     */
    public Collection<ZclAttribute> getAttributes() {
        if (isClient) {
            return Collections.unmodifiableCollection(clientAttributes.values());
        } else {
            return Collections.unmodifiableCollection(serverAttributes.values());
        }
    }

    /**
     * Gets an attribute given the attribute ID
     *
     * @param attributeId the attribute ID
     * @return the {@link ZclAttribute}
     */
    public ZclAttribute getAttribute(int attributeId) {
        if (isClient) {
            return clientAttributes.get(attributeId);
        } else {
            return serverAttributes.get(attributeId);
        }
    }

    /**
     * Gets a local attribute given the attribute ID
     *
     * @param attributeId the attribute ID
     * @return the {@link ZclAttribute}
     */
    public ZclAttribute getLocalAttribute(int attributeId) {
        if (isClient) {
            return serverAttributes.get(attributeId);
        } else {
            return clientAttributes.get(attributeId);
        }
    }

    /**
     * Gets all the local attributes supported by this cluster This will return all attributes, even if they are not
     * actually supported by the device. The user should check to see if this is implemented.
     * <p>
     * This will return either the list of client or server attributes, depending on the cluster.
     *
     * @return {@link Set} containing all local {@link ZclAttributes} available in this cluster
     */
    public Collection<ZclAttribute> getLocalAttributes() {
        if (isClient) {
            return Collections.unmodifiableCollection(serverAttributes.values());
        } else {
            return Collections.unmodifiableCollection(clientAttributes.values());
        }
    }

    /**
     * Gets the cluster ID for this cluster
     *
     * @return the cluster ID as {@link Integer}
     */
    public Integer getClusterId() {
        return clusterId;
    }

    /**
     * Gets the cluster name for this cluster
     *
     * @return the cluster name as {@link String}
     */
    public String getClusterName() {
        return clusterName;
    }

    /**
     * Returns the ZigBee address of this cluster
     *
     * @return the {@link ZigBeeEndpointAddress} of the cluster
     */
    public ZigBeeEndpointAddress getZigBeeAddress() {
        return zigbeeEndpoint.getEndpointAddress();
    }

    /**
     * Sets the server flag for this cluster. This means the cluster is listed in the devices input cluster list
     *
     */
    public void setServer() {
        isClient = false;
    }

    /**
     * Gets the state of the server flag. If the cluster is a server this will return true
     *
     * @return true if the cluster can act as a server
     */
    public boolean isServer() {
        return !isClient;
    }

    /**
     * Sets the client flag for this cluster. This means the cluster is listed in the devices output cluster list
     *
     */
    public void setClient() {
        isClient = true;
    }

    /**
     * Gets the state of the client flag. If the cluster is a client this will return true
     *
     * @return true if the cluster can act as a client
     */
    public boolean isClient() {
        return isClient;
    }

    /**
     * Sets APS security requirement on or off for this cluster. If APS security is required, all outgoing frames will
     * be APS secured, and any incoming frames without APS security will be ignored.
     *
     * @param requireApsSecurity true if APS security is required for this cluster
     */
    public void setApsSecurityRequired(boolean requireApsSecurity) {
        this.apsSecurityRequired = requireApsSecurity;
    }

    /**
     * If APS security is required, all outgoing frames will be APS secured, and any incoming frames without APS
     * security will be ignored.
     *
     * @return true if APS security is required for this cluster
     */
    public boolean getApsSecurityRequired() {
        return apsSecurityRequired;
    }

    /**
     * Adds a binding from the cluster to the destination {@link ZigBeeEndpoint}.
     *
     * @param address the destination {@link IeeeAddress}
     * @param endpointId the destination endpoint ID
     * @return Command future
     */
    public Future<CommandResult> bind(IeeeAddress address, int endpointId) {
        final BindRequest command = new BindRequest(zigbeeEndpoint.getIeeeAddress(), zigbeeEndpoint.getEndpointId(),
                clusterId, 3, address, endpointId);
        command.setDestinationAddress(new ZigBeeEndpointAddress(zigbeeEndpoint.getEndpointAddress().getAddress()));
        // The transaction is not sent to the Endpoint of this cluster, but to the ZDO endpoint 0 directly.
        return zigbeeEndpoint.getParentNode().sendTransaction(command, command);
    }

    /**
     * Removes a binding from the cluster to the destination {@link ZigBeeEndpoint}.
     *
     * @param address the destination {@link IeeeAddress}
     * @param endpointId the destination endpoint ID
     * @return Command future
     */
    public Future<CommandResult> unbind(IeeeAddress address, int endpointId) {
        final UnbindRequest command = new UnbindRequest(zigbeeEndpoint.getIeeeAddress(), zigbeeEndpoint.getEndpointId(),
                clusterId, 3, address, endpointId);
        command.setDestinationAddress(new ZigBeeEndpointAddress(zigbeeEndpoint.getEndpointAddress().getAddress()));
        // The transaction is not sent to the Endpoint of this cluster, but to the ZDO endpoint 0 directly.
        return zigbeeEndpoint.getParentNode().sendTransaction(command, command);
    }

    /**
     * Gets the list of attributes supported by this device.
     * After initialisation, the list will contain all standard attributes defined by ZCL, so is not customised to the
     * specific device. Once a successful call to {@link #discoverAttributes()} has been made, the list will reflect the
     * attributes supported by the remote device.
     *
     * @return {@link Set} of {@link Integer} containing the list of supported attributes
     */
    public Set<Integer> getSupportedAttributes() {
        synchronized (supportedAttributes) {
            if (!supportedAttributesKnown) {
                if (isClient) {
                    return new TreeSet<>(clientAttributes.keySet());
                } else {
                    return new TreeSet<>(serverAttributes.keySet());
                }
            }

            return new TreeSet<>(supportedAttributes);
        }
    }

    /**
     * Checks if the cluster supports a specified attribute ID.
     * Note that if {@link #discoverAttributes(boolean)} has not been called, this method will return false.
     *
     * @param attributeId the attribute to check
     * @return true if the attribute is known to be supported, otherwise false
     */
    public boolean isAttributeSupported(int attributeId) {
        synchronized (supportedAttributes) {
            return supportedAttributes.contains(attributeId);
        }
    }

    /**
     * Discovers the list of attributes supported by the cluster on the remote device.
     * <p>
     * If the discovery has already been completed, and rediscover is false, then the future will complete immediately
     * and the user can use existing results. Normally there should not be a need to set rediscover to true.
     * <p>
     * This method returns a future to a boolean. Upon success the caller should call {@link #getSupportedAttributes()}
     * to get the list of supported attributes or {@link #isAttributeSupported(int)} to test if a single attribute is
     * supported.
     *
     * @param rediscover true to perform a discovery even if it was previously completed
     * @return {@link Future} returning a {@link Boolean}
     */
    public Future<Boolean> discoverAttributes(final boolean rediscover) {
        return discoverAttributes(rediscover, null);
    }

    /**
     * Discovers the list of attributes supported by the cluster on the remote device.
     * <p>
     * If the discovery has already been completed, and rediscover is false, then the future will complete immediately
     * and the user can use existing results. Normally there should not be a need to set rediscover to true.
     * <p>
     * This method returns a future to a boolean. Upon success the caller should call {@link #getSupportedAttributes()}
     * to get the list of supported attributes.
     * <p>
     * If the cluster is not manufacturer-specific, discovery of manufacturer-specific attributes for a specific
     * manufacturer can be triggered via the method parameter 'manufacturerCode'.
     *
     * @param rediscover true to perform a discovery even if it was previously completed
     * @param manufacturerCode set to non-null value to perform a discovery of manufacturer-specific attributes
     * @return {@link Future} returning a {@link Boolean}
     */
    public Future<Boolean> discoverAttributes(final boolean rediscover, final Integer manufacturerCode) {
        RunnableFuture<Boolean> future = new FutureTask<Boolean>(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                // Synchronise the request to avoid multiple simultaneous requests to this update of the list on this
                // cluster which would cause errors consolidating the responses
                synchronized (supportedAttributes) {
                    // If we don't want to rediscover, and we already have the list of attributes, then return
                    if (!rediscover && supportedAttributesKnown) {
                        return true;
                    }

                    int index = 0;
                    boolean complete = false;
                    Set<AttributeInformation> attributes = new HashSet<>();

                    do {
                        final DiscoverAttributesCommand command = new DiscoverAttributesCommand(index, 10);
                        command.setClusterId(clusterId);
                        command.setDestinationAddress(zigbeeEndpoint.getEndpointAddress());

                        if (isManufacturerSpecific()) {
                            command.setManufacturerCode(getManufacturerCode());
                        } else if (manufacturerCode != null) {
                            command.setManufacturerCode(manufacturerCode);
                        }

                        CommandResult result = sendCommand(command).get();
                        if (result.isError()) {
                            return false;
                        }

                        DiscoverAttributesResponse response = (DiscoverAttributesResponse) result.getResponse();
                        complete = response.getDiscoveryComplete();
                        if (response.getAttributeInformation() != null
                                && !response.getAttributeInformation().isEmpty()) {
                            attributes.addAll(response.getAttributeInformation());
                            index = Collections.max(attributes).getIdentifier() + 1;
                        }
                    } while (!complete);

                    supportedAttributes.clear();
                    for (ZclAttribute attribute : getAttributes()) {
                        // Set all remote attributes as unimplemented
                        attribute.setImplemented(false);
                    }
                    for (AttributeInformation attributeInfo : attributes) {
                        // Set the remote attribute as implemented
                        ZclAttribute attribute = getAttribute(attributeInfo.getIdentifier());
                        if (attribute != null) {
                            attribute.setImplemented(true);
                        }
                        supportedAttributes.add(attributeInfo.getIdentifier());
                    }
                }
                supportedAttributesKnown = true;
                return true;
            }
        });

        // Create the thread to execute it
        new Thread(future, "DiscoverAttributes-" + zigbeeEndpoint.getIeeeAddress()).start();
        return future;
    }

    /**
     * Returns a list of all the commands the remote device can receive. This will be an empty list if the
     * {@link #discoverCommandsReceived()} method has not completed.
     *
     * @return a {@link Set} of command IDs the device supports
     */
    public Set<Integer> getSupportedCommandsReceived() {
        synchronized (supportedCommandsReceived) {
            return new TreeSet<>(supportedCommandsReceived);
        }
    }

    /**
     * Checks if the cluster supports a specified received command ID.
     * Note that if {@link #discoverCommandsReceived(boolean)} has not been called, this method will return false.
     *
     * @param commandId the attribute to check
     * @return true if the command is known to be supported, otherwise false
     */
    public boolean isReceivedCommandSupported(int commandId) {
        synchronized (supportedCommandsReceived) {
            return supportedCommandsReceived.contains(commandId);
        }
    }

    /**
     * Discovers the list of commands received by the cluster on the remote device. If the discovery is successful,
     * users should call {@link ZclCluster#getSupportedCommandsReceived()} to get the list of supported commands.
     * <p>
     * If the discovery has already been completed, and rediscover is false, then the future will complete immediately
     * and the user can use existing results. Normally there should not be a need to set rediscover to true.
     * <p>
     * Will not discover manufacturer-specific commands unless the cluster itself is manufacturer-specific.
     *
     * @param rediscover true to perform a discovery even if it was previously completed
     * @return Command future {@link Boolean} with the success of the discovery
     */
    public Future<Boolean> discoverCommandsReceived(final boolean rediscover) {
        return discoverCommandsReceived(rediscover, null);
    }

    /**
     * Discovers the list of commands received by the cluster on the remote device. If the discovery is successful,
     * users should call {@link ZclCluster#getSupportedCommandsReceived()} to get the list of supported commands.
     * <p>
     * If the discovery has already been completed, and rediscover is false, then the future will complete immediately
     * and the user can use existing results. Normally there should not be a need to set rediscover to true.
     * <p>
     * If the cluster is not manufacturer-specific, discovery of manufacturer-specific commands for a specific
     * manufacturer can be triggered via the method parameter 'manufacturerCode'.
     *
     * @param rediscover true to perform a discovery even if it was previously completed
     * @param manufacturerCode set to non-null value to perform a discovery of manufacturer-specific commands
     * @return Command future {@link Boolean} with the success of the discovery
     */
    public Future<Boolean> discoverCommandsReceived(final boolean rediscover, final Integer manufacturerCode) {
        RunnableFuture<Boolean> future = new FutureTask<Boolean>(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                // Synchronise the request to avoid multiple simultaneous requests to this update the list on this
                // cluster which would cause errors consolidating the responses
                synchronized (supportedCommandsReceived) {
                    // If we don't want to rediscover, and we already have the list of attributes, then return
                    if (!rediscover && !supportedCommandsReceived.isEmpty()) {
                        return true;
                    }

                    int index = 0;
                    boolean complete = false;
                    Set<Integer> commands = new HashSet<>();

                    do {
                        final DiscoverCommandsReceived command = new DiscoverCommandsReceived(index, 20);
                        command.setClusterId(clusterId);
                        command.setDestinationAddress(zigbeeEndpoint.getEndpointAddress());

                        if (isManufacturerSpecific()) {
                            command.setManufacturerCode(getManufacturerCode());
                        } else if (manufacturerCode != null) {
                            command.setManufacturerCode(manufacturerCode);
                        }

                        CommandResult result = sendCommand(command).get();
                        if (result.isError()) {
                            return false;
                        }

                        DiscoverCommandsReceivedResponse response = (DiscoverCommandsReceivedResponse) result
                                .getResponse();
                        complete = response.getDiscoveryComplete();
                        if (response.getCommandIdentifiers() != null) {
                            commands.addAll(response.getCommandIdentifiers());
                            index = Collections.max(commands) + 1;
                        }
                    } while (!complete);

                    supportedCommandsReceived.clear();
                    supportedCommandsReceived.addAll(commands);
                }
                return true;
            }
        });

        // start the thread to execute it
        new Thread(future, "DiscoverCommandsReceived-" + zigbeeEndpoint.getIeeeAddress()).start();
        return future;
    }

    /**
     * Returns a list of all the commands the remote device can generate. This will be an empty list if the
     * {@link #discoverCommandsGenerated()} method has not completed.
     *
     * @return a {@link Set} of command IDs the device supports
     */
    public Set<Integer> getSupportedCommandsGenerated() {
        synchronized (supportedCommandsGenerated) {
            return new TreeSet<>(supportedCommandsGenerated);
        }
    }

    /**
     * Checks if the cluster supports a specified generated command ID.
     * Note that if {@link #discoverCommandsGenerated(boolean)} has not been called, this method will return false.
     *
     * @param commandId the attribute to check
     * @return true if the command is known to be supported, otherwise false
     */
    public boolean isGeneratedCommandSupported(int commandId) {
        synchronized (supportedCommandsGenerated) {
            return supportedCommandsGenerated.contains(commandId);
        }
    }

    /**
     * Discovers the list of commands generated by the cluster on the remote device If the discovery is successful,
     * users should call {@link ZclCluster#getSupportedCommandsGenerated()} to get the list of supported commands.
     * <p>
     * If the discovery has already been completed, and rediscover is false, then the future will complete immediately
     * and the user can use existing results. Normally there should not be a need to set rediscover to true.
     * <p>
     * Will not discover manufacturer-specific commands unless the cluster itself is manufacturer-specific.
     *
     * @param rediscover true to perform a discovery even if it was previously completed
     * @return Command future {@link Boolean} with the success of the discovery
     */
    public Future<Boolean> discoverCommandsGenerated(final boolean rediscover) {
        return discoverCommandsGenerated(rediscover, null);
    }

    /**
     * Discovers the list of commands generated by the cluster on the remote device If the discovery is successful,
     * users should call {@link ZclCluster#getSupportedCommandsGenerated()} to get the list of supported commands.
     * <p>
     * If the discovery has already been completed, and rediscover is false, then the future will complete immediately
     * and the user can use existing results. Normally there should not be a need to set rediscover to true.
     * <p>
     * If the cluster is not manufacturer-specific, discovery of manufacturer-specific commands for a specific
     * manufacturer can be triggered via the method parameter 'manufacturerCode'.
     *
     * @param rediscover true to perform a discovery even if it was previously completed
     * @param manufacturerCode set to non-null value to perform a discovery of manufacturer-specific commands
     * @return Command future {@link Boolean} with the success of the discovery
     */
    public Future<Boolean> discoverCommandsGenerated(final boolean rediscover, final Integer manufacturerCode) {
        RunnableFuture<Boolean> future = new FutureTask<Boolean>(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                // Synchronise the request to avoid multiple simultaneous requests to this update the list on this
                // cluster which would cause errors consolidating the responses
                synchronized (supportedCommandsGenerated) {
                    // If we don't want to rediscover, and we already have the list of attributes, then return
                    if (!rediscover && !supportedCommandsGenerated.isEmpty()) {
                        return true;
                    }
                    int index = 0;
                    boolean complete = false;
                    Set<Integer> commands = new HashSet<>();

                    do {
                        final DiscoverCommandsGenerated command = new DiscoverCommandsGenerated(index, 20);
                        command.setClusterId(clusterId);
                        command.setDestinationAddress(zigbeeEndpoint.getEndpointAddress());

                        if (isManufacturerSpecific()) {
                            command.setManufacturerCode(getManufacturerCode());
                        } else if (manufacturerCode != null) {
                            command.setManufacturerCode(manufacturerCode);
                        }

                        CommandResult result = sendCommand(command).get();
                        if (result.isError()) {
                            return false;
                        }

                        DiscoverCommandsGeneratedResponse response = (DiscoverCommandsGeneratedResponse) result
                                .getResponse();
                        complete = response.getDiscoveryComplete();
                        if (response.getCommandIdentifiers() != null) {
                            commands.addAll(response.getCommandIdentifiers());
                            index = Collections.max(commands) + 1;
                        }
                    } while (!complete);

                    supportedCommandsGenerated.clear();
                    supportedCommandsGenerated.addAll(commands);
                }

                return true;
            }
        });

        // start the thread to execute it
        new Thread(future, "DiscoverCommandsGenerated-" + zigbeeEndpoint.getIeeeAddress()).start();
        return future;
    }

    /**
     * Adds a {@link ZclAttributeListener} to receive reports when a remote attribute is updated
     *
     * @param listener the {@link ZclAttributeListener} to add
     */
    public void addAttributeListener(ZclAttributeListener listener) {
        logger.trace("{}: ZclCluster.addAttributeListener adding {}", zigbeeEndpoint.getEndpointAddress(), listener);
        attributeListeners.add(listener);
    }

    /**
     * Remove an attribute listener from the cluster.
     *
     * @param listener callback listener implementing {@link ZclAttributeListener} to remove
     */
    public void removeAttributeListener(final ZclAttributeListener listener) {
        logger.trace("{}: ZclCluster.removeAttributeListener removing {}", zigbeeEndpoint.getEndpointAddress(),
                listener);
        attributeListeners.remove(listener);
    }

    /**
     * Notify attribute listeners of an updated remote {@link ZclAttribute}.
     *
     * @param attribute the {@link ZclAttribute} to notify
     * @param value the current value of the attribute
     */
    private void notifyAttributeListener(final ZclAttribute attribute, final Object value) {
        for (final ZclAttributeListener listener : attributeListeners) {
            getNotificationService().execute(new Runnable() {
                @Override
                public void run() {
                    logger.trace("{}: ZclCluster.notifyAttributeListener {} of {} with value {}",
                            zigbeeEndpoint.getEndpointAddress(), listener, attribute, value);

                    try {
                        listener.attributeUpdated(attribute, value);
                    } catch (Exception e) {
                        logger.warn("{}: Exception when notifying attribute listener {} of {} with value {}",
                                zigbeeEndpoint.getEndpointAddress(), listener, attribute, value, e);
                    }
                }
            });
        }
    }

    /**
     * Adds a {@link ZclCommandListener} to receive commands.
     * <p>
     * Command listeners are expected to return promptly when called - if there is significant processing to perform
     * then this should be handled in a separate thread.
     *
     * @param listener the {@link ZclCommandListener} to add
     */
    public void addCommandListener(ZclCommandListener listener) {
        logger.trace("{}: ZclCluster.addCommandListener({})", zigbeeEndpoint.getEndpointAddress(), listener);

        synchronized (commandListeners) {
            commandListeners.add(listener);
        }
    }

    /**
     * Remove a {@link ZclCommandListener} from the cluster.
     *
     * @param listener callback listener implementing {@link ZclCommandListener} to remove
     */
    public void removeCommandListener(final ZclCommandListener listener) {
        logger.trace("{}: ZclCluster.removeCommandListener({})", zigbeeEndpoint.getEndpointAddress(), listener);

        synchronized (commandListeners) {
            commandListeners.remove(listener);
        }
    }

    /**
     * Notify command listeners of an received {@link ZclCommand}.
     *
     * @param command the {@link ZclCommand} to notify
     * @return true if at least one of the command handlers has responded to the command
     */
    private boolean notifyCommandListener(final ZclCommand command) {
        final AtomicBoolean response = new AtomicBoolean();
        CountDownLatch latch;
        synchronized (commandListeners) {
            latch = new CountDownLatch(commandListeners.size());
            for (final ZclCommandListener listener : commandListeners) {
                getNotificationService().execute(new Runnable() {
                    @Override
                    public void run() {
                        logger.trace("{}: ZclCluster.notifyCommandListener {} of {}",
                                zigbeeEndpoint.getEndpointAddress(), listener, command);

                        if (listener.commandReceived(command)) {
                            response.set(true);
                            while (latch.getCount() > 0) {
                                latch.countDown();
                            }
                        } else {
                            latch.countDown();
                        }
                    }
                });
            }
        }

        try {
            // TODO: Set the timer properly
            if (latch.await(1, TimeUnit.SECONDS)) {
                logger.trace("{}: ZclCluster.notifyCommandListener LATCH Done - {}",
                        zigbeeEndpoint.getEndpointAddress(), response.get());
                return response.get();
            } else {
                logger.trace("{}: ZclCluster.notifyCommandListener LATCH Timeout, remaining = {}",
                        zigbeeEndpoint.getEndpointAddress(), latch.getCount());
                return response.get();
            }
        } catch (InterruptedException e) {
            logger.trace("{}: ZclCluster.notifyCommandListener LATCH Interrupted, remaining = {}",
                    zigbeeEndpoint.getEndpointAddress(), latch.getCount());
            return response.get();
        }
    }

    /**
     * Process the {@link ReadAttributesCommand} to read a local attribute.
     *
     * @param command the {@link ReadAttributesCommand}
     */
    private void handleReadAttributes(ReadAttributesCommand command) {
        Map<Integer, ZclAttribute> localAttributes;
        if (isClient) {
            localAttributes = serverAttributes;
        } else {
            localAttributes = clientAttributes;
        }

        List<ReadAttributeStatusRecord> attributeRecords = new ArrayList<>();
        for (int attributeId : command.getIdentifiers()) {
            ReadAttributeStatusRecord record = new ReadAttributeStatusRecord();
            record.setAttributeIdentifier(attributeId);

            if (localAttributes.containsKey(attributeId) && localAttributes.get(attributeId) != null
                    && localAttributes.get(attributeId).isImplemented()) {
                if (localAttributes.get(attributeId).getLastValue() != null) {
                    record.setStatus(ZclStatus.SUCCESS);
                    record.setAttributeDataType(localAttributes.get(attributeId).getDataType());
                    record.setAttributeValue(localAttributes.get(attributeId).getLastValue());
                } else {
                    record.setStatus(ZclStatus.INVALID_VALUE);
                }
            } else {
                record.setStatus(ZclStatus.UNSUPPORTED_ATTRIBUTE);
            }

            attributeRecords.add(record);
        }

        ReadAttributesResponse response = new ReadAttributesResponse(attributeRecords);
        response.setClusterId(clusterId);
        sendResponse(command, response);
    }

    /**
     * Process the {@link DiscoverAttributesCommand} to discover supported local attributes.
     * This will return all local attributes that are marked as implemented.
     *
     * @param command the {@link DiscoverAttributesCommand} to respond to
     */
    private void handleDiscoverAttributes(DiscoverAttributesCommand command) {
        List<AttributeInformation> attributeInformation = new ArrayList<>();
        for (ZclAttribute attribute : getLocalAttributes()) {
            if (!attribute.isImplemented() || attribute.getId() < command.getStartAttributeIdentifier()) {
                continue;
            }

            AttributeInformation attributeInfo = new AttributeInformation();
            attributeInfo.setIdentifier(attribute.getId());
            attributeInfo.setDataType(attribute.getDataType());
            attributeInformation.add(attributeInfo);

            if (attributeInformation.size() >= command.getMaximumAttributeIdentifiers()) {
                break;
            }
        }

        DiscoverAttributesResponse response = new DiscoverAttributesResponse(
                attributeInformation.size() == getLocalAttributes().size(), attributeInformation);
        sendResponse(command, response);
    }

    /**
     * Processes a list of attribute reports for this cluster
     *
     * @param command the received {@link ReportAttributesCommand}
     */
    private void handleAttributeReport(ReportAttributesCommand command) {
        logger.trace("{}: ZclCluster.handleAttributeReport({})", zigbeeEndpoint.getEndpointAddress(),
                command.getReports());
        for (AttributeReport report : command.getReports()) {
            updateAttribute(report.getAttributeIdentifier(), report.getAttributeValue());
        }
        sendDefaultResponse(command, ZclStatus.SUCCESS);
    }

    /**
     * Processes a list of attribute status reports for this cluster
     *
     * @param command the received {@link ReadAttributesResponse}
     */
    private void handleAttributeStatus(ReadAttributesResponse command) {
        logger.trace("{}: ZclCluster.handleAttributeStatus({})", zigbeeEndpoint.getEndpointAddress(),
                command.getRecords());
        for (ReadAttributeStatusRecord record : command.getRecords()) {
            if (record.getStatus() != ZclStatus.SUCCESS) {
                logger.debug("{}: Error reading {} attribute {} in cluster {} - {}",
                        zigbeeEndpoint.getEndpointAddress(), (isClient ? "client" : "server"),
                        record.getAttributeIdentifier(), clusterId, record.getStatus());
                continue;
            }

            updateAttribute(record.getAttributeIdentifier(), record.getAttributeValue());
        }
        sendDefaultResponse(command, ZclStatus.SUCCESS);
    }

    /**
     * Processes a list of attribute reports for this cluster
     *
     * @param command the received {@link WriteAttributesCommand}
     */
    private void handleWriteAttributesCommand(WriteAttributesCommand command) {
        logger.trace("{}: ZclCluster.handleWriteAttributesCommand({})", zigbeeEndpoint.getEndpointAddress(),
                command.getRecords());
        boolean success = true;
        List<WriteAttributeStatusRecord> attributeStatus = new ArrayList<>();
        for (WriteAttributeRecord requestRecord : command.getRecords()) {
            ZclAttribute attribute = getLocalAttribute(requestRecord.getAttributeIdentifier());
            WriteAttributeStatusRecord responseRecord = new WriteAttributeStatusRecord();
            responseRecord.setAttributeIdentifier(requestRecord.getAttributeIdentifier());

            if (attribute == null || !attribute.isImplemented()) {
                responseRecord.setStatus(ZclStatus.UNSUPPORTED_ATTRIBUTE);
                success = false;
            } else if (attribute.getDataType() != requestRecord.getAttributeDataType()) {
                responseRecord.setStatus(ZclStatus.INVALID_DATA_TYPE);
                success = false;
            } else {
                attribute.setValue(
                        normalizer.normalizeZclData(attribute.getDataType(), requestRecord.getAttributeValue()));
                responseRecord.setStatus(ZclStatus.SUCCESS);
                logger.debug("{}: Local attribute {} updated to {} for cluster {}", zigbeeEndpoint.getEndpointAddress(),
                        attribute.getId(), attribute.getLastValue(), clusterId);
            }

            attributeStatus.add(responseRecord);
        }

        WriteAttributesResponse response = new WriteAttributesResponse(attributeStatus);
        if (success) {
            // If all attributes are written successfully, then we only need to return SUCCESS
            WriteAttributeStatusRecord responseRecord = new WriteAttributeStatusRecord();
            responseRecord.setStatus(ZclStatus.SUCCESS);
            attributeStatus.clear();
            attributeStatus.add(responseRecord);
        }
        sendResponse(command, response);
    }

    private void updateAttribute(int attributeId, Object attributeValue) {
        logger.trace("{}: Attribute {} in {} cluster {} updated to {}", zigbeeEndpoint.getEndpointAddress(),
                attributeId, (isClient ? "Client" : "Server"), clusterId, attributeValue);
        ZclAttribute attribute = getAttribute(attributeId);
        if (attribute == null) {
            logger.debug("{}: Unknown {} attribute in {} cluster {}", zigbeeEndpoint.getEndpointAddress(),
                    (isClient ? "Client" : "Server"), attributeId, clusterId);
        } else {
            Object value = normalizer.normalizeZclData(attribute.getDataType(), attributeValue);
            attribute.updateValue(value);
            notifyAttributeListener(attribute, value);
        }
    }

    /**
     * Processes a command received in this cluster. This is called from the node so we already know that the command is
     * addressed to this endpoint and this cluster.
     *
     * @param command the received {@link ZclCommand}
     */
    public void handleCommand(ZclCommand command) {
        logger.trace("{}: ZclCluster.handleCommand({})", zigbeeEndpoint.getEndpointAddress(), command);
        if (command instanceof ReportAttributesCommand) {
            handleAttributeReport((ReportAttributesCommand) command);
            return;
        }

        if (command instanceof ReadAttributesResponse) {
            handleAttributeStatus((ReadAttributesResponse) command);
            return;
        }

        if (command instanceof ReadAttributesCommand) {
            handleReadAttributes((ReadAttributesCommand) command);
            return;
        }

        if (command instanceof DiscoverAttributesCommand) {
            handleDiscoverAttributes((DiscoverAttributesCommand) command);
            return;
        }

        if (command instanceof WriteAttributesCommand) {
            handleWriteAttributesCommand((WriteAttributesCommand) command);
            return;
        }

        ZclStatus responseStatus;

        // If this is a specific cluster command, pass the command to the cluster command handler
        if (notifyCommandListener(command)) {
            return;
        } else if (supportedGenericCommands.contains(command.getClass())) {
            responseStatus = ZclStatus.SUCCESS;
        } else if (command.isManufacturerSpecific()) {
            if (command.isGenericCommand()) {
                responseStatus = ZclStatus.UNSUP_MANUF_GENERAL_COMMAND;
            } else {
                responseStatus = ZclStatus.UNSUP_MANUF_CLUSTER_COMMAND;
            }
        } else {
            if (command.isGenericCommand()) {
                responseStatus = ZclStatus.UNSUP_GENERAL_COMMAND;
            } else {
                responseStatus = ZclStatus.UNSUP_CLUSTER_COMMAND;
            }
        }

        sendDefaultResponse(command, responseStatus);
    }

    /**
     * Gets a command from the command ID (ie a command from client to server). If no command with the requested id is
     * found, null is returned.
     *
     * @param zclFrameType the {@link ZclFrameType} of the command
     * @param commandId the command ID
     * @return the {@link ZclCommand} or null if no command was found.
     */
    public ZclCommand getCommandFromId(ZclFrameType zclFrameType, int commandId) {
        if (zclFrameType == ZclFrameType.CLUSTER_SPECIFIC_COMMAND) {
            return getCommand(commandId, clientCommands);
        } else {
            return getCommand(commandId, genericCommands);
        }
    }

    /**
     * Gets a response from the command ID (ie a command from server to client). If no command with the requested id is
     * found, null is returned.
     *
     * @param zclFrameType the {@link ZclFrameType} of the command
     * @param commandId the command ID
     * @return the {@link ZclCommand} or null if no command was found.
     */
    public ZclCommand getResponseFromId(ZclFrameType zclFrameType, int commandId) {
        if (zclFrameType == ZclFrameType.CLUSTER_SPECIFIC_COMMAND) {
            return getCommand(commandId, serverCommands);
        } else {
            return getCommand(commandId, genericCommands);
        }
    }

    private ZclCommand getCommand(int commandId, Map<Integer, Class<? extends ZclCommand>> commands) {
        if (!commands.containsKey(commandId)) {
            return null;
        }

        try {
            return commands.get(commandId).getConstructor().newInstance();
        } catch (Exception e) {
            logger.debug("Error instantiating cluster command {}, id={}", clusterName, commandId);
        }
        return null;
    }

    /**
     * Returns a Data Acquisition Object for this cluster. This is a clean class recording the state of the primary
     * fields of the cluster for persistence purposes.
     *
     * @return the {@link ZclClusterDao}
     */
    public ZclClusterDao getDao() {
        ZclClusterDao dao = new ZclClusterDao();

        dao.setClusterId(clusterId);
        dao.setClient(isClient);
        dao.setManufacturerCode(manufacturerCode);
        if (supportedAttributesKnown) {
            dao.setSupportedAttributes(Collections.unmodifiableSet(new HashSet<>(supportedAttributes)));
        }
        dao.setSupportedCommandsGenerated(Collections.unmodifiableSet(new HashSet<>(supportedCommandsGenerated)));
        dao.setSupportedCommandsReceived(Collections.unmodifiableSet(new HashSet<>(supportedCommandsReceived)));
        Collection<ZclAttribute> daoZclAttributes;
        if (isClient) {
            daoZclAttributes = clientAttributes.values();
        } else {
            daoZclAttributes = serverAttributes.values();
        }

        Map<Integer, ZclAttributeDao> daoAttributes = new HashMap<>();
        for (ZclAttribute attribute : daoZclAttributes) {
            daoAttributes.put(attribute.getId(), attribute.getDao());
        }
        dao.setAttributes(daoAttributes);
        return dao;
    }

    /**
     * Sets the state of the cluster from a {@link ZclClusterDao} which has been restored from a persisted state.
     *
     * @param dao the {@link ZclClusterDao} to restore
     */
    public void setDao(ZclClusterDao dao) {
        clusterId = dao.getClusterId();
        isClient = dao.getClient();
        manufacturerCode = dao.getManufacturerCode();
        supportedAttributesKnown = dao.getSupportedAttributes() != null;
        if (supportedAttributesKnown) {
            supportedAttributes.addAll(dao.getSupportedAttributes());
        }
        supportedCommandsGenerated.addAll(dao.getSupportedCommandsGenerated());
        supportedCommandsReceived.addAll(dao.getSupportedCommandsReceived());

        Map<Integer, ZclAttribute> attributes = isClient ? clientAttributes : serverAttributes;

        for (ZclAttributeDao daoAttribute : dao.getAttributes().values()) {
            // Normalize the data to protect against the users serialisation system restoring incorrect data classes
            daoAttribute
                    .setLastValue(normalizer.normalizeZclData(daoAttribute.getDataType(), daoAttribute.getLastValue()));

            ZclAttribute attribute = attributes.get(daoAttribute.getId());
            if (attribute == null || daoAttribute.getManufacturerCode() != null) {
                attribute = new ZclAttribute();
                attribute.setDao(this, daoAttribute);
                attributes.put(daoAttribute.getId(), attribute);
            } else {
                attribute.setDynamicStateFromDao(daoAttribute);
            }
        }
    }

    //
    // DEPRECATED METHODS TO BE REMOVED in version 1.3.0
    //

    /**
     * Read an {@link ZclAttribute}
     *
     * @param attribute the {@link ZclAttribute} to read
     * @return the returned attribute object or null on error
     * @deprecated from 1.2.0 use {@link #readAttributeValue(ZclAttribute, Long)}. Method will be removed in 1.3.0.
     */
    @Deprecated
    protected Object readSync(final ZclAttribute attribute) {
        logger.debug("readSync request: {}", attribute);
        CommandResult result;
        try {
            result = read(attribute).get();
        } catch (InterruptedException e) {
            logger.debug("readSync interrupted");
            return null;
        } catch (ExecutionException e) {
            logger.debug("readSync exception ", e);
            return null;
        }

        if (!result.isSuccess()) {
            return null;
        }

        ReadAttributesResponse response = result.getResponse();
        if (response.getRecords().get(0).getStatus() == ZclStatus.SUCCESS) {
            ReadAttributeStatusRecord attributeRecord = response.getRecords().get(0);
            return normalizer.normalizeZclData(attribute.getDataType(), attributeRecord.getAttributeValue());
        }

        return null;
    }

    /**
     * Write an attribute
     *
     * @param attribute the attribute ID to write
     * @param dataType the {@link ZclDataType} of the object
     * @param value the value to set (as {@link Object})
     * @return command future {@link CommandResult}
     * @deprecated from 1.2.0 use {@link #writeAttribute}. Method will be removed in 1.3.0.
     */
    @Deprecated
    public Future<CommandResult> write(final int attribute, final ZclDataType dataType, final Object value) {
        return writeAttribute(attribute, dataType, value);
    }

    /**
     * Write an attribute
     *
     * @param attribute the {@link ZclAttribute} to write
     * @param value the value to set (as {@link Object})
     * @return command future {@link CommandResult}
     * @deprecated from 1.2.0 use {@link #writeAttribute}. Method will be removed in 1.3.0.
     */
    @Deprecated
    public Future<CommandResult> write(final ZclAttribute attribute, final Object value) {
        return writeAttribute(attribute.getId(), attribute.getDataType(), value);
    }

    /**
     * Read an attribute given the attribute ID
     *
     * @param attribute the integer attribute ID to read
     * @return command future
     * @deprecated from 1.2.0 use {@link #readAttribute(int)}. Method will be removed in 1.3.0.
     */
    @Deprecated
    public Future<CommandResult> read(final int attribute) {
        return read(Collections.singletonList(attribute));
    }

    /**
     * Read an attribute
     *
     * @param attribute the {@link ZclAttribute} to read
     * @return command future
     * @deprecated from 1.2.0 use {@link #readAttribute(ZclAttribute)}. Method will be removed in 1.3.0.
     */
    @Deprecated
    public Future<CommandResult> read(final ZclAttribute attribute) {
        return read(attribute.getId());
    }

    /**
     * Read a number of attributes given a list of attribute IDs. Care must be taken not to request too many attributes
     * so as to exceed the allowable frame length
     *
     * @param attributes List of attribute identifiers to read
     * @return command future
     * @deprecated from 1.2.0 use {@link #readAttributes(List)}. Method will be removed in 1.3.0.
     */
    @Deprecated
    public Future<CommandResult> read(final List<Integer> attributes) {
        return readAttributes(attributes);
    }

    /**
     * Configures the reporting for the specified attribute ID for analog attributes.
     * <p>
     * <b>minInterval</b>:
     * The minimum reporting interval field is 16 bits in length and shall contain the
     * minimum interval, in seconds, between issuing reports of the specified attribute.
     * If minInterval is set to 0x0000, then there is no minimum limit, unless one is
     * imposed by the specification of the cluster using this reporting mechanism or by
     * the applicable profile.
     * <p>
     * <b>maxInterval</b>:
     * The maximum reporting interval field is 16 bits in length and shall contain the
     * maximum interval, in seconds, between issuing reports of the specified attribute.
     * If maxInterval is set to 0xffff, then the device shall not issue reports for the specified
     * attribute, and the configuration information for that attribute need not be
     * maintained.
     * <p>
     * <b>reportableChange</b>:
     * The reportable change field shall contain the minimum change to the attribute that
     * will result in a report being issued. This field is of variable length. For attributes
     * with 'analog' data type the field has the same data type as the attribute. The sign (if any) of the reportable
     * change field is ignored.
     *
     * @param attribute the {@link ZclAttribute} to configure reporting
     * @param minInterval the minimum reporting interval
     * @param maxInterval the maximum reporting interval
     * @param reportableChange the minimum change required to report an update
     * @return command future {@link CommandResult}
     * @deprecated from 1.2.0 use {@link ZclCluster#setReporting(int, int, int, Object)} or
     *             {@link ZclAttribute.setReporting} methods. This will be removed in 1.3.0
     */
    @Deprecated
    public Future<CommandResult> setReporting(final ZclAttribute attribute, final int minInterval,
            final int maxInterval, final Object reportableChange) {
        final ConfigureReportingCommand command = new ConfigureReportingCommand();
        command.setClusterId(clusterId);

        final AttributeReportingConfigurationRecord record = new AttributeReportingConfigurationRecord();
        record.setDirection(0);
        record.setAttributeIdentifier(attribute.getId());
        record.setAttributeDataType(attribute.getDataType());
        record.setMinimumReportingInterval(minInterval);
        record.setMaximumReportingInterval(maxInterval);
        record.setReportableChange(reportableChange);
        record.setTimeoutPeriod(0);
        command.setRecords(Collections.singletonList(record));
        command.setDestinationAddress(zigbeeEndpoint.getEndpointAddress());

        if (isManufacturerSpecific()) {
            command.setManufacturerCode(getManufacturerCode());
        } else if (attribute.isManufacturerSpecific()) {
            command.setManufacturerCode(attribute.getManufacturerCode());
        }

        return sendCommand(command);
    }

    /**
     * Configures the reporting for the specified attribute ID for discrete attributes.
     * <p>
     * <b>minInterval</b>:
     * The minimum reporting interval field is 16 bits in length and shall contain the
     * minimum interval, in seconds, between issuing reports of the specified attribute.
     * If minInterval is set to 0x0000, then there is no minimum limit, unless one is
     * imposed by the specification of the cluster using this reporting mechanism or by
     * the applicable profile.
     * <p>
     * <b>maxInterval</b>:
     * The maximum reporting interval field is 16 bits in length and shall contain the
     * maximum interval, in seconds, between issuing reports of the specified attribute.
     * If maxInterval is set to 0xffff, then the device shall not issue reports for the specified
     * attribute, and the configuration information for that attribute need not be
     * maintained.
     *
     * @param attribute the {@link ZclAttribute} to configure reporting
     * @param minInterval the minimum reporting interval
     * @param maxInterval the maximum reporting interval
     * @return command future {@link CommandResult}
     * @deprecated from 1.2.0 use {@link ZclCluster#setReporting(int, int, int)} or {@link ZclAttribute.setReporting}
     *             methods. This will be removed in 1.3.0
     */
    @Deprecated
    public Future<CommandResult> setReporting(final ZclAttribute attribute, final int minInterval,
            final int maxInterval) {
        return setReporting(attribute, minInterval, maxInterval, null);
    }

    /**
     * Gets the reporting configuration for an attribute
     *
     * @param attribute the {@link ZclAttribute} on which to get the reporting configuration
     * @return command future {@link CommandResult}
     * @deprecated from 1.2.0 use {@link ZclAttribute.getReporting} methods. This will be removed in 1.3.0
     */
    @Deprecated
    public Future<CommandResult> getReporting(final ZclAttribute attribute) {
        final ReadReportingConfigurationCommand command = new ReadReportingConfigurationCommand();
        command.setClusterId(clusterId);
        AttributeRecord record = new AttributeRecord();
        record.setAttributeIdentifier(attribute.getId());
        record.setDirection(0);
        command.setRecords(Collections.singletonList(record));
        command.setDestinationAddress(zigbeeEndpoint.getEndpointAddress());

        return sendCommand(command);
    }

    /**
     * Indicates whether this is a manufacturer-specific attribute. Default is not manufacturer-specific.
     */
    public boolean isManufacturerSpecific() {
        return getManufacturerCode() != null;
    }

    /**
     * Returns the manufacturer code; must be non-null for manufacturer-specific clusters.
     */
    public Integer getManufacturerCode() {
        return manufacturerCode;
    }

    /**
     * Adds additional attributes to the cluster (like, e.g., manufacturer-specific attributes).
     * <p>
     * Any attributes added here will be marked as implemented.
     *
     * @param attributes the attributes which should be added to the cluster
     */
    public void addAttributes(Set<ZclAttribute> attributes) {
        for (ZclAttribute attribute : attributes) {
            attribute.setImplemented(true);
            if (isClient) {
                clientAttributes.put(attribute.getId(), attribute);
            } else {
                serverAttributes.put(attribute.getId(), attribute);
            }
        }
    }

    /**
     * Adds additional attributes to the local cluster (like, e.g., manufacturer-specific attributes).
     * <p>
     * Any attributes added here will be marked as implemented.
     *
     * @param attributes the attributes which should be added to the cluster
     */
    public void addLocalAttributes(Set<ZclAttribute> attributes) {
        for (ZclAttribute attribute : attributes) {
            attribute.setImplemented(true);
            if (isClient) {
                serverAttributes.put(attribute.getId(), attribute);
            } else {
                clientAttributes.put(attribute.getId(), attribute);
            }
        }
    }

    /**
     * Adds additional client commands to the cluster (like, e.g., manufacturer-specific commands).
     *
     * @param commands the client commands which should be added to the cluster
     */
    public void addClientCommands(Map<Integer, Class<? extends ZclCommand>> commands) {
        clientCommands.putAll(commands);
    }

    /**
     * Adds additional server commands to the cluster (like, e.g., manufacturer-specific commands).
     *
     * @param commands the server commands which should be added to the cluster
     */
    public void addServerCommands(Map<Integer, Class<? extends ZclCommand>> commands) {
        serverCommands.putAll(commands);
    }

    /**
     * Checks if the list of attributes contains only manufacturer specific attributes
     *
     * @param attributeIds the list of attribute IDs to check
     * @return true if the attributes list contains only manufacturer specific attributes
     */
    private boolean areAttributesManufacturerSpecific(List<Integer> attributeIds) {
        for (Integer attributeId : attributeIds) {
            if (getAttribute(attributeId) == null || !getAttribute(attributeId).isManufacturerSpecific()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Generates a {@link DefaultResponse} to the supplied {@link ZclCommand} with the requested {@link ZclStatus} code.
     * If the {@link ZclCommand} does not require a response, this method will return null.
     *
     * @param command the received {@link ZclCommand} to which the response is being generated
     * @param status the {@link ZclStatus} to return in the response
     * @return the {@link DefaultResponse} ready to send or null if it is not required
     */
    public static DefaultResponse createDefaultResponse(ZclCommand command, ZclStatus status) {
        if (command.isDisableDefaultResponse()) {
            return null;
        }
        DefaultResponse defaultResponse = new DefaultResponse(command.getCommandId(), status);
        defaultResponse.setTransactionId(command.getTransactionId());
        defaultResponse.setDestinationAddress(command.getDestinationAddress());
        defaultResponse.setClusterId(command.getClusterId());
        defaultResponse.setApsSecurity(command.getApsSecurity());

        if (command.isManufacturerSpecific()) {
            defaultResponse.setManufacturerCode(command.getManufacturerCode());
        }

        return defaultResponse;
    }

    public NotificationService getNotificationService() {
        return zigbeeEndpoint.getNotificationService();
    }

}

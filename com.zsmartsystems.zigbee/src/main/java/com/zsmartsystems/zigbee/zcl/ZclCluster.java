package com.zsmartsystems.zigbee.zcl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.ZigBeeDeviceAddress;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.zcl.clusters.general.ReadAttributesResponse;
import com.zsmartsystems.zigbee.zcl.field.AttributeReport;
import com.zsmartsystems.zigbee.zcl.field.ReadAttributeStatusRecord;

/**
 * Base class for the ZCL Cluster
 *
 * @author Chris Jackson
 *
 */
public abstract class ZclCluster {

    private final ZigBeeNetworkManager zigbeeManager;
    private final ZigBeeDeviceAddress zigbeeAddress;
    protected final int clusterId;
    protected final String clusterName;

    private boolean isClient = false;
    private boolean isServer = false;

    private final List<ZclAttributeListener> attributeListeners = new ArrayList<ZclAttributeListener>();

    protected Map<Integer, ZclAttribute> attributes = initializeAttributes();

    protected abstract Map<Integer, ZclAttribute> initializeAttributes();

    public ZclCluster(ZigBeeNetworkManager zigbeeManager, ZigBeeDeviceAddress zigbeeAddress, int clusterId,
            String clusterName) {
        this.zigbeeManager = zigbeeManager;
        this.zigbeeAddress = zigbeeAddress;
        this.clusterId = clusterId;
        this.clusterName = clusterName;
    }

    protected Future<CommandResult> send(ZclCommand command) {
        command.setDestinationAddress(zigbeeAddress);
        // command.setDestinationEndpoint(zigbeeDevice.getEndpoint());
        return zigbeeManager.unicast(command);
    }

    /**
     * Read an attribute
     *
     * @param attribute the {@link ZclAttribute} to read
     * @return command future
     */
    protected Future<CommandResult> read(final ZclAttribute attribute) {
        return zigbeeManager.read(this, attribute);
    }

    /**
     * Read an attribute
     *
     * @param attribute the {@link ZclAttribute} to read
     * @return
     */
    protected Object readSync(final ZclAttribute attribute) {
        CommandResult result;
        try {
            result = zigbeeManager.read(this, attribute).get();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

        if (!result.isSuccess()) {
            return null;
        }

        ReadAttributesResponse response = result.getResponse();
        if (response.getRecords().get(0).getStatus() == 0) {
            return response.getRecords().get(0).getAttributeValue();
        }

        return null;
    }

    /**
     * Write an attribute
     *
     * @param attribute the {@link ZclAttribute} to write
     * @param value the value to set (as {@link Object})
     * @return command future
     */
    protected Future<CommandResult> write(ZclAttribute attribute, final Object value) {
        return zigbeeManager.write(this, attribute, value);
    }

    public Future<CommandResult> report(final int attributeId, final int minInterval, final int maxInterval,
            final Object reportableChange) {
        return zigbeeManager.report(zigbeeAddress, clusterId, attributeId, minInterval, maxInterval, reportableChange);
    }

    /**
     * Gets all the attributes supported by this cluster This will return all
     * attributes, even if they are not actually supported by the device. The
     * user should check to see if this is implemented.
     *
     * @return {@link Set} containing all {@link ZclAttributes} available in
     *         this cluster
     */
    public Set<ZclAttribute> getAttributes() {
        Set<ZclAttribute> attr = new HashSet<ZclAttribute>();
        attr.addAll(attributes.values());
        return attr;
    }

    /**
     * Gets an attribute from the attribute ID
     *
     * @param id
     *            the attribute ID
     * @return the {@link ZclAttribute}
     */
    public ZclAttribute getAttribute(int id) {
        return attributes.get(id);
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
     * @return the {@link ZigBeeDeviceAddress} of the cluster
     */
    public ZigBeeDeviceAddress getZigBeeAddress() {
        return zigbeeAddress;
    }

    /**
     * Sets the server flag for this cluster. This means the cluster is listed
     * in the devices input cluster list
     *
     * @param isServer
     *            true if this is a server
     */
    public void setServer(boolean isServer) {
        this.isServer = isServer;
    }

    /**
     * Gets the state of the server flag. If the cluster is a server this will
     * return true
     *
     * @return true if the cluster can act as a server
     */
    public boolean isServer() {
        return isServer;
    }

    /**
     * Sets the client flag for this cluster. This means the cluster is listed
     * in the devices output cluster list
     *
     * @param isServer
     *            true if this is a client
     */
    public void setClient(boolean isClient) {
        this.isClient = isClient;
    }

    /**
     * Gets the state of the client flag. If the cluster is a client this will
     * return true
     *
     * @return true if the cluster can act as a client
     */
    public boolean isClient() {
        return isClient;
    }

    public void addAttributeListener(ZclAttributeListener listener) {
        // Don't add more than once.
        if (attributeListeners.contains(listener)) {
            return;
        }
        attributeListeners.add(listener);
    }

    public void removeAttributeListener(ZclAttributeListener listener) {
        attributeListeners.remove(listener);
    }

    private void notifyAttributeListener(ZclAttribute attribute) {
        for (ZclAttributeListener listener : attributeListeners) {
            listener.AttributeUpdated(attribute);
        }
    }

    /**
     * Processes a list of attribute reports for this cluster
     *
     * @param reports
     *            {@List} of {@link AttributeReport}
     */
    public void handleAttributeReport(List<AttributeReport> reports) {
        for (AttributeReport report : reports) {
            ZclAttribute attribute = attributes.get(report.getAttributeIdentifier());
            attribute.updateValue(report.getAttributeValue());
            notifyAttributeListener(attribute);
        }
    }

    /**
     * Processes a list of attribute status reports for this cluster
     *
     * @param reports
     *            {@List} of {@link ReadAttributeStatusRecord}
     */
    public void handleAttributeStatus(List<ReadAttributeStatusRecord> records) {
        for (ReadAttributeStatusRecord record : records) {
            ZclAttribute attribute = attributes.get(record.getAttributeIdentifier());
            attribute.updateValue(record.getAttributeValue());
            notifyAttributeListener(attribute);
        }
    }

    /**
     * Gets a command from the command ID (ie a command from client to server). If no command with the requested id is
     * found, null is returned.
     * 
     * @param commandId the command ID
     * @return the {@link ZclCommand} or null if no command found.
     */
    public ZclCommand getCommandFromId(int commandId) {
        return null;
    }

    /**
     * Gets a response from the command ID (ie a command from server to client). If no command with the requested id is
     * found, null is returned.
     * 
     * @param commandId the command ID
     * @return the {@link ZclCommand} or null if no command found.
     */
    public ZclCommand getResponseFromId(int commandId) {
        return null;
    }
}

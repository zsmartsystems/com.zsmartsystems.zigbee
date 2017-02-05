package com.zsmartsystems.zigbee.zcl.clusters;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.ZigBeeDeviceAddress;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.zcl.ZclAttribute;
import com.zsmartsystems.zigbee.zcl.ZclCluster;
import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.clusters.identify.IdentifyCommand;
import com.zsmartsystems.zigbee.zcl.clusters.identify.IdentifyQueryCommand;
import com.zsmartsystems.zigbee.zcl.clusters.identify.IdentifyQueryResponse;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * <b>Identify</b> cluster implementation (<i>Cluster ID 0x0003</i>).
 * <p>
 * Attributes and commands to put a device into an Identification mode (e.g. flashing
 * a light), that indicates to an observer – e.g. an installer - which of several devices
 * it is, also to request any device that is identifying itself to respond to the initiator.
 * <br>
 * Note that this cluster cannot be disabled, and remains functional regardless of the
 * setting of the DeviceEnable attribute in the Basic cluster.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class ZclIdentifyCluster extends ZclCluster {
    // Cluster ID
    public static final int CLUSTER_ID = 0x0003;

    // Cluster Name
    public static final String CLUSTER_NAME = "Identify";

    // Attribute constants
    public static final int ATTR_IDENTIFYTIME = 0x0000;

    // Attribute initialisation
    protected Map<Integer, ZclAttribute> initializeAttributes() {
        Map<Integer, ZclAttribute> attributeMap = new HashMap<Integer, ZclAttribute>(1);

        attributeMap.put(ATTR_IDENTIFYTIME, new ZclAttribute(0, "IdentifyTime", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, true, false));

        return attributeMap;
    }

    /**
     * Default constructor.
     */
    public ZclIdentifyCluster(final ZigBeeNetworkManager zigbeeManager, final ZigBeeDeviceAddress zigbeeAddress) {
        super(zigbeeManager, zigbeeAddress, CLUSTER_ID, CLUSTER_NAME);
    }



    /**
     * <p>
     * Set the <i>IdentifyTime</i> attribute [Attribute ID <b>0</b>].
     * <p>
     * <p>
     * The IdentifyTime attribute specifies the remaining length of time, in seconds, that
     * the device will continue to identify itself.
     * <br>
     * If this attribute is set to a value other than 0x0000 then the device shall enter its
     * identification procedure, in order to indicate to an observer which of several
     * devices it is. It is recommended that this procedure consists of flashing a light
     * with a period of 0.5 seconds. The IdentifyTime attribute shall be decremented
     * every second.
     * <br>
     * If this attribute reaches or is set to the value 0x0000 then the device shall
     * terminate its identification procedure.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param identifyTime the {@link Integer} attribute value to be set
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setIdentifyTime(final Object value) {
        return write(attributes.get(ATTR_IDENTIFYTIME), value);
    }

    /**
     * <p>
     * Get the <i>IdentifyTime</i> attribute [Attribute ID <b>0</b>].
     * <p>
     * <p>
     * The IdentifyTime attribute specifies the remaining length of time, in seconds, that
     * the device will continue to identify itself.
     * <br>
     * If this attribute is set to a value other than 0x0000 then the device shall enter its
     * identification procedure, in order to indicate to an observer which of several
     * devices it is. It is recommended that this procedure consists of flashing a light
     * with a period of 0.5 seconds. The IdentifyTime attribute shall be decremented
     * every second.
     * <br>
     * If this attribute reaches or is set to the value 0x0000 then the device shall
     * terminate its identification procedure.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getIdentifyTimeAsync() {
        return read(attributes.get(ATTR_IDENTIFYTIME));
    }


    /**
     * <p>
     * Synchronously get the <i>IdentifyTime</i> attribute [Attribute ID <b>0</b>].
     * <p>
     * <p>
     * The IdentifyTime attribute specifies the remaining length of time, in seconds, that
     * the device will continue to identify itself.
     * <br>
     * If this attribute is set to a value other than 0x0000 then the device shall enter its
     * identification procedure, in order to indicate to an observer which of several
     * devices it is. It is recommended that this procedure consists of flashing a light
     * with a period of 0.5 seconds. The IdentifyTime attribute shall be decremented
     * every second.
     * <br>
     * If this attribute reaches or is set to the value 0x0000 then the device shall
     * terminate its identification procedure.
     * <p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getIdentifyTime() {
        return (Integer) readSync(attributes.get(ATTR_IDENTIFYTIME));
    }

    /**
     * The Identify Command
     * <p>
     * The identify command starts or stops the receiving device identifying itself.
     *
     * @param identifyTime {@link Integer} Identify Time
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> identifyCommand(Integer identifyTime) {
        IdentifyCommand command = new IdentifyCommand();

        // Set the fields
        command.setIdentifyTime(identifyTime);

        return send(command);
    }

    /**
     * The Identify Query Command
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> identifyQueryCommand() {
        IdentifyQueryCommand command = new IdentifyQueryCommand();

        return send(command);
    }

    /**
     * The Identify Query Response
     * <p>
     * The identify query response command is generated in response to receiving an
     * Identify Query command in the case that the device is currently identifying itself.
     *
     * @param identifyTime {@link Integer} Identify Time
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> identifyQueryResponse(Integer identifyTime) {
        IdentifyQueryResponse command = new IdentifyQueryResponse();

        // Set the fields
        command.setIdentifyTime(identifyTime);

        return send(command);
    }

    /**
     * Add a binding for this cluster to the local node
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> bind() {
        return bind();
    }

    @Override
    public ZclCommand getCommandFromId(int commandId) {
        switch (commandId) {
            case 0: // IDENTIFY_COMMAND
                return new IdentifyCommand();
            case 1: // IDENTIFY_QUERY_COMMAND
                return new IdentifyQueryCommand();
            default:
                return null;
        }
    }

    @Override
    public ZclCommand getResponseFromId(int commandId) {
        switch (commandId) {
            case 0: // IDENTIFY_QUERY_RESPONSE
                return new IdentifyQueryResponse();
            default:
                return null;
        }
    }
}

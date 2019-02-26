/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.zcl.ZclAttribute;
import com.zsmartsystems.zigbee.zcl.ZclCluster;
import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.clusters.keyestablishment.ConfirmKeyDataRequestCommand;
import com.zsmartsystems.zigbee.zcl.clusters.keyestablishment.ConfirmKeyResponse;
import com.zsmartsystems.zigbee.zcl.clusters.keyestablishment.EphemeralDataRequestCommand;
import com.zsmartsystems.zigbee.zcl.clusters.keyestablishment.EphemeralDataResponse;
import com.zsmartsystems.zigbee.zcl.clusters.keyestablishment.InitiateKeyEstablishmentRequestCommand;
import com.zsmartsystems.zigbee.zcl.clusters.keyestablishment.InitiateKeyEstablishmentResponse;
import com.zsmartsystems.zigbee.zcl.clusters.keyestablishment.TerminateKeyEstablishment;
import com.zsmartsystems.zigbee.zcl.field.ByteArray;
import com.zsmartsystems.zigbee.zcl.protocol.ZclClusterType;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * <b>Key Establishment</b> cluster implementation (<i>Cluster ID 0x0800</i>).
 * <p>
 * This cluster provides attributes and commands to perform mutual authentication and
 * establish keys between two ZigBee devices.
 * <p>
 * All Key Establishment messages should be sent with APS retries enabled. A failure to receive
 * an ACK in a timely manner can be seen as a failure of key establishment. No Terminate Key
 * Establishment should be sent to the partner of device that has timed out the operation.
 * <p>
 * The initiator can initiate the key establishment with any active endpoint on the responder
 * device that supports the key establishment cluster. The endpoint can be either
 * preconfigured or discovered, for example, by using ZDO Match_Desc_req. A link key
 * successfully established using key establishment is valid for all endpoints on a
 * particular device. The responder shall respond to the initiator using the source endpoint
 * of the initiator's messages as the destination endpoint of the responder's messages.
 * <p>
 * It is expected that the time it takes to perform the various cryptographic computations of
 * the key establishment cluster may vary greatly based on the device. Therefore rather than
 * set static timeouts, the Initiate Key Establishment Request and Response messages will
 * contain approximate values for how long the device will take to generate the ephemeral data
 * and how long the device will take to generate confirm key message. A device performing key
 * establishment can use this information in order to choose a reasonable timeout for its
 * partner during those operations. The timeout should also take into consideration the time
 * it takes for a message to traverse the network including APS retries. A minimum transmission
 * time of 2 seconds is recommended.
 * <p>
 * For the Initiate Key Establishment Response message, it is recommended the initiator wait
 * at least 2 seconds before timing out the operation. It is not expected that generating an
 * Initiate Key Establishment Response will take significant time compared to generating the
 * Ephemeral Data and Confirm Key messages.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-26T20:57:36Z")
public class ZclKeyEstablishmentCluster extends ZclCluster {
    /**
     * The ZigBee Cluster Library Cluster ID
     */
    public static final int CLUSTER_ID = 0x0800;

    /**
     * The ZigBee Cluster Library Cluster Name
     */
    public static final String CLUSTER_NAME = "Key Establishment";

    // Attribute constants
    /**
     * The KeyEstablishmentSuite attribute is 16-bits in length and specifies all the
     * cryptographic schemes for key establishment on the device. A device shall set the
     * corresponding bit to 1 for every cryptographic scheme that is supports. All other
     * cryptographic schemes and reserved bits shall be set to 0.
     */
    public static final int ATTR_CLIENTKEYESTABLISHMENTSUITE = 0x0000;
    /**
     * The KeyEstablishmentSuite attribute is 16-bits in length and specifies all the
     * cryptographic schemes for key establishment on the device. A device shall set the
     * corresponding bit to 1 for every cryptographic scheme that is supports. All other
     * cryptographic schemes and reserved bits shall be set to 0.
     */
    public static final int ATTR_SERVERKEYESTABLISHMENTSUITE = 0x0000;

    @Override
    protected Map<Integer, ZclAttribute> initializeAttributes() {
        Map<Integer, ZclAttribute> attributeMap = new ConcurrentHashMap<>(1);

        attributeMap.put(ATTR_SERVERKEYESTABLISHMENTSUITE, new ZclAttribute(ZclClusterType.KEY_ESTABLISHMENT, ATTR_SERVERKEYESTABLISHMENTSUITE, "Server Key Establishment Suite", ZclDataType.ENUMERATION_16_BIT, true, true, false, false));

        return attributeMap;
    }

    @Override
    protected Map<Integer, Class<? extends ZclCommand>> initializeServerCommands() {
        Map<Integer, Class<? extends ZclCommand>> commandMap = new ConcurrentHashMap<>(4);

        commandMap.put(0x0000, InitiateKeyEstablishmentResponse.class);
        commandMap.put(0x0001, EphemeralDataResponse.class);
        commandMap.put(0x0002, ConfirmKeyResponse.class);
        commandMap.put(0x0003, TerminateKeyEstablishment.class);

        return commandMap;
    }

    @Override
    protected Map<Integer, Class<? extends ZclCommand>> initializeClientCommands() {
        Map<Integer, Class<? extends ZclCommand>> commandMap = new ConcurrentHashMap<>(3);

        commandMap.put(0x0000, InitiateKeyEstablishmentRequestCommand.class);
        commandMap.put(0x0001, EphemeralDataRequestCommand.class);
        commandMap.put(0x0002, ConfirmKeyDataRequestCommand.class);

        return commandMap;
    }

    /**
     * Default constructor to create a Key Establishment cluster.
     *
     * @param zigbeeEndpoint the {@link ZigBeeEndpoint} this cluster is contained within
     */
    public ZclKeyEstablishmentCluster(final ZigBeeEndpoint zigbeeEndpoint) {
        super(zigbeeEndpoint, CLUSTER_ID, CLUSTER_NAME);
    }

    /**
     * Get the <i>Client Key Establishment Suite</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * The KeyEstablishmentSuite attribute is 16-bits in length and specifies all the
     * cryptographic schemes for key establishment on the device. A device shall set the
     * corresponding bit to 1 for every cryptographic scheme that is supports. All other
     * cryptographic schemes and reserved bits shall be set to 0.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getClientKeyEstablishmentSuiteAsync() {
        return read(attributes.get(ATTR_CLIENTKEYESTABLISHMENTSUITE));
    }

    /**
     * Synchronously get the <i>Client Key Establishment Suite</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * The KeyEstablishmentSuite attribute is 16-bits in length and specifies all the
     * cryptographic schemes for key establishment on the device. A device shall set the
     * corresponding bit to 1 for every cryptographic scheme that is supports. All other
     * cryptographic schemes and reserved bits shall be set to 0.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getClientKeyEstablishmentSuite(final long refreshPeriod) {
        if (attributes.get(ATTR_CLIENTKEYESTABLISHMENTSUITE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_CLIENTKEYESTABLISHMENTSUITE).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_CLIENTKEYESTABLISHMENTSUITE));
    }

    /**
     * Set reporting for the <i>Client Key Establishment Suite</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * The KeyEstablishmentSuite attribute is 16-bits in length and specifies all the
     * cryptographic schemes for key establishment on the device. A device shall set the
     * corresponding bit to 1 for every cryptographic scheme that is supports. All other
     * cryptographic schemes and reserved bits shall be set to 0.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setClientKeyEstablishmentSuiteReporting(final int minInterval, final int maxInterval) {
        return setReporting(attributes.get(ATTR_CLIENTKEYESTABLISHMENTSUITE), minInterval, maxInterval);
    }

    /**
     * Get the <i>Server Key Establishment Suite</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * The KeyEstablishmentSuite attribute is 16-bits in length and specifies all the
     * cryptographic schemes for key establishment on the device. A device shall set the
     * corresponding bit to 1 for every cryptographic scheme that is supports. All other
     * cryptographic schemes and reserved bits shall be set to 0.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getServerKeyEstablishmentSuiteAsync() {
        return read(attributes.get(ATTR_SERVERKEYESTABLISHMENTSUITE));
    }

    /**
     * Synchronously get the <i>Server Key Establishment Suite</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * The KeyEstablishmentSuite attribute is 16-bits in length and specifies all the
     * cryptographic schemes for key establishment on the device. A device shall set the
     * corresponding bit to 1 for every cryptographic scheme that is supports. All other
     * cryptographic schemes and reserved bits shall be set to 0.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getServerKeyEstablishmentSuite(final long refreshPeriod) {
        if (attributes.get(ATTR_SERVERKEYESTABLISHMENTSUITE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_SERVERKEYESTABLISHMENTSUITE).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_SERVERKEYESTABLISHMENTSUITE));
    }

    /**
     * Set reporting for the <i>Server Key Establishment Suite</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * The KeyEstablishmentSuite attribute is 16-bits in length and specifies all the
     * cryptographic schemes for key establishment on the device. A device shall set the
     * corresponding bit to 1 for every cryptographic scheme that is supports. All other
     * cryptographic schemes and reserved bits shall be set to 0.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setServerKeyEstablishmentSuiteReporting(final int minInterval, final int maxInterval) {
        return setReporting(attributes.get(ATTR_SERVERKEYESTABLISHMENTSUITE), minInterval, maxInterval);
    }

    /**
     * The Initiate Key Establishment Request Command
     * <p>
     * The Initiate Key Establishment Request command allows a device to initiate key
     * establishment with another device. The sender will transmit its identity information
     * and key establishment protocol information to the receiving device.
     * <p>
     * If the device does not currently have the resources to respond to a key establishment
     * request it shall send a Terminate Key Establishment command with the result value set to
     * NO_RESOURCES and the Wait Time field shall be set to an approximation of the time that
     * must pass before the device will have the resources to process a new Key Establishment
     * Request.
     * <p>
     * If the device can process this request, it shall check the Issuer field of the device's
     * implicit certificate. If the Issuer field does not contain a value that corresponds to a
     * known Certificate Authority, the device shall send a Terminate Key Establishment
     * command with the result set to UNKNOWN_ISSUER.
     * <p>
     * If the device accepts the request it shall send an Initiate Key Establishment Response
     * command containing its own identity information. The device should verify the
     * certificate belongs to the address that the device is communicating with. The binding
     * between the identity of the communicating device and its address is verifiable using
     * out-of-band method.
     *
     * @param keyEstablishmentSuite {@link Integer} Key Establishment Suite
     * @param ephemeralDataGenerateTime {@link Integer} Ephemeral Data Generate Time
     * @param confirmKeyGenerateTime {@link Integer} Confirm Key Generate Time
     * @param identity {@link ByteArray} Identity
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> initiateKeyEstablishmentRequestCommand(Integer keyEstablishmentSuite, Integer ephemeralDataGenerateTime, Integer confirmKeyGenerateTime, ByteArray identity) {
        InitiateKeyEstablishmentRequestCommand command = new InitiateKeyEstablishmentRequestCommand();

        // Set the fields
        command.setKeyEstablishmentSuite(keyEstablishmentSuite);
        command.setEphemeralDataGenerateTime(ephemeralDataGenerateTime);
        command.setConfirmKeyGenerateTime(confirmKeyGenerateTime);
        command.setIdentity(identity);

        return send(command);
    }

    /**
     * The Ephemeral Data Request Command
     * <p>
     * The Ephemeral Data Request command allows a device to communicate its ephemeral data to
     * another device and request that the device send back its own ephemeral data.
     *
     * @param ephemeralData {@link ByteArray} Ephemeral Data
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> ephemeralDataRequestCommand(ByteArray ephemeralData) {
        EphemeralDataRequestCommand command = new EphemeralDataRequestCommand();

        // Set the fields
        command.setEphemeralData(ephemeralData);

        return send(command);
    }

    /**
     * The Confirm Key Data Request Command
     * <p>
     * The Confirm Key Request command allows the initiator sending device to confirm the key
     * established with the responder receiving device based on performing a cryptographic
     * hash using part of the generated keying material and the identities and ephemeral data
     * of both parties.
     *
     * @param secureMessageAuthenticationCode {@link ByteArray} Secure Message Authentication Code
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> confirmKeyDataRequestCommand(ByteArray secureMessageAuthenticationCode) {
        ConfirmKeyDataRequestCommand command = new ConfirmKeyDataRequestCommand();

        // Set the fields
        command.setSecureMessageAuthenticationCode(secureMessageAuthenticationCode);

        return send(command);
    }

    /**
     * The Initiate Key Establishment Response
     * <p>
     * The Initiate Key Establishment Response command allows a device to respond to a device
     * requesting the initiation of key establishment with it. The sender will transmit its
     * identity information and key establishment protocol information to the receiving
     * device.
     *
     * @param requestedKeyEstablishmentSuite {@link Integer} Requested Key Establishment Suite
     * @param ephemeralDataGenerateTime {@link Integer} Ephemeral Data Generate Time
     * @param confirmKeyGenerateTime {@link Integer} Confirm Key Generate Time
     * @param identity {@link ByteArray} Identity
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> initiateKeyEstablishmentResponse(Integer requestedKeyEstablishmentSuite, Integer ephemeralDataGenerateTime, Integer confirmKeyGenerateTime, ByteArray identity) {
        InitiateKeyEstablishmentResponse command = new InitiateKeyEstablishmentResponse();

        // Set the fields
        command.setRequestedKeyEstablishmentSuite(requestedKeyEstablishmentSuite);
        command.setEphemeralDataGenerateTime(ephemeralDataGenerateTime);
        command.setConfirmKeyGenerateTime(confirmKeyGenerateTime);
        command.setIdentity(identity);

        return send(command);
    }

    /**
     * The Ephemeral Data Response
     * <p>
     * The Ephemeral Data Response command allows a device to communicate its ephemeral data
     * to another device that previously requested it.
     *
     * @param ephemeralData {@link ByteArray} Ephemeral Data
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> ephemeralDataResponse(ByteArray ephemeralData) {
        EphemeralDataResponse command = new EphemeralDataResponse();

        // Set the fields
        command.setEphemeralData(ephemeralData);

        return send(command);
    }

    /**
     * The Confirm Key Response
     * <p>
     * The Confirm Key Response command allows the responder to verify the initiator has
     * derived the same secret key. This is done by sending the initiator a cryptographic hash
     * generated using the keying material and the identities and ephemeral data of both
     * parties.
     *
     * @param secureMessageAuthenticationCode {@link ByteArray} Secure Message Authentication Code
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> confirmKeyResponse(ByteArray secureMessageAuthenticationCode) {
        ConfirmKeyResponse command = new ConfirmKeyResponse();

        // Set the fields
        command.setSecureMessageAuthenticationCode(secureMessageAuthenticationCode);

        return send(command);
    }

    /**
     * The Terminate Key Establishment
     * <p>
     * The Terminate Key Establishment command may be sent by either the initiator or
     * responder to indicate a failure in the key establishment exchange.
     *
     * @param statusCode {@link Integer} Status Code
     * @param waitTime {@link Integer} Wait Time
     * @param keyEstablishmentSuite {@link Integer} Key Establishment Suite
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> terminateKeyEstablishment(Integer statusCode, Integer waitTime, Integer keyEstablishmentSuite) {
        TerminateKeyEstablishment command = new TerminateKeyEstablishment();

        // Set the fields
        command.setStatusCode(statusCode);
        command.setWaitTime(waitTime);
        command.setKeyEstablishmentSuite(keyEstablishmentSuite);

        return send(command);
    }
}

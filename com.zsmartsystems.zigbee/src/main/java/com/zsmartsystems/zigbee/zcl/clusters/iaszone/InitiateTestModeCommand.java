/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.iaszone;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Initiate Test Mode Command value object class.
 * <p>
 * Cluster: <b>IAS Zone</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the IAS Zone cluster.
 * <p>
 * Certain IAS Zone servers may have operational configurations that could be configured OTA
 * or locally on the device. This command enables them to be remotely placed into a test mode so
 * that the user or installer may configure their field of view, sensitivity, and other
 * operational parameters. They may also verify the placement and proper operation of the IAS
 * Zone server, which may have been placed in a difficult to reach location (i.e., making a
 * physical input on the device impractical to trigger). <br> Another use case for this command
 * is large deployments, especially commercial and industrial, where placing the entire IAS
 * system into test mode instead of a single IAS Zone server is infeasible due to the
 * vulnerabilities that might arise. This command enables only a single IAS Zone server to be
 * placed into test mode. <br> The biggest limitation of this command is that most IAS Zone
 * servers today are battery-powered sleepy nodes that cannot reliably receive commands.
 * However, implementers may decide to program an IAS Zone server by factory default to
 * maintain a limited duration of normal polling upon initialization/joining to a new
 * network. Some IAS Zone servers may also have AC mains power and are able to receive commands.
 * Some types of IAS Zone servers that may benefit from this command are: motion sensors and fire
 * sensor/smoke alarm listeners (i.e., a device that listens for a non-communicating fire
 * sensor to alarm and communicates this to the IAS CIE).
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class InitiateTestModeCommand extends ZclCommand {
    /**
     * Test Mode Duration command message field.
     * <p>
     * Specifies the duration, in seconds, for which the IAS Zone server shall operate in its
     * test mode.
     */
    private Integer testModeDuration;

    /**
     * Current Zone Sensitivity Level command message field.
     * <p>
     * Specifies the sensitivity level the IAS Zone server shall use for the duration of the
     * Test Mode and with which it must update its CurrentZoneSensitivityLevel attribute.
     * <p>
     * The permitted values of Current Zone Sensitivity Level are shown defined for the
     * CurrentZoneSensitivityLevel Attribute.
     */
    private Integer currentZoneSensitivityLevel;

    /**
     * Default constructor.
     */
    public InitiateTestModeCommand() {
        genericCommand = false;
        clusterId = 0x0500;
        commandId = 2;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Gets Test Mode Duration.
     * <p>
     * Specifies the duration, in seconds, for which the IAS Zone server shall operate in its
     * test mode.
     *
     * @return the Test Mode Duration
     */
    public Integer getTestModeDuration() {
        return testModeDuration;
    }

    /**
     * Sets Test Mode Duration.
     * <p>
     * Specifies the duration, in seconds, for which the IAS Zone server shall operate in its
     * test mode.
     *
     * @param testModeDuration the Test Mode Duration
     */
    public void setTestModeDuration(final Integer testModeDuration) {
        this.testModeDuration = testModeDuration;
    }

    /**
     * Gets Current Zone Sensitivity Level.
     * <p>
     * Specifies the sensitivity level the IAS Zone server shall use for the duration of the
     * Test Mode and with which it must update its CurrentZoneSensitivityLevel attribute.
     * <p>
     * The permitted values of Current Zone Sensitivity Level are shown defined for the
     * CurrentZoneSensitivityLevel Attribute.
     *
     * @return the Current Zone Sensitivity Level
     */
    public Integer getCurrentZoneSensitivityLevel() {
        return currentZoneSensitivityLevel;
    }

    /**
     * Sets Current Zone Sensitivity Level.
     * <p>
     * Specifies the sensitivity level the IAS Zone server shall use for the duration of the
     * Test Mode and with which it must update its CurrentZoneSensitivityLevel attribute.
     * <p>
     * The permitted values of Current Zone Sensitivity Level are shown defined for the
     * CurrentZoneSensitivityLevel Attribute.
     *
     * @param currentZoneSensitivityLevel the Current Zone Sensitivity Level
     */
    public void setCurrentZoneSensitivityLevel(final Integer currentZoneSensitivityLevel) {
        this.currentZoneSensitivityLevel = currentZoneSensitivityLevel;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(testModeDuration, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(currentZoneSensitivityLevel, ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        testModeDuration = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        currentZoneSensitivityLevel = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(109);
        builder.append("InitiateTestModeCommand [");
        builder.append(super.toString());
        builder.append(", testModeDuration=");
        builder.append(testModeDuration);
        builder.append(", currentZoneSensitivityLevel=");
        builder.append(currentZoneSensitivityLevel);
        builder.append(']');
        return builder.toString();
    }

}

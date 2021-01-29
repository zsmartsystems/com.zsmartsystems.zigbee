/**
 * Copyright (c) 2016-2021 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.Future;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.zcl.ZclAttribute;
import com.zsmartsystems.zigbee.zcl.ZclCluster;
import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclStatus;
import com.zsmartsystems.zigbee.zcl.clusters.scenes.AddSceneCommand;
import com.zsmartsystems.zigbee.zcl.clusters.scenes.AddSceneResponse;
import com.zsmartsystems.zigbee.zcl.clusters.scenes.CopySceneCommand;
import com.zsmartsystems.zigbee.zcl.clusters.scenes.CopySceneResponse;
import com.zsmartsystems.zigbee.zcl.clusters.scenes.EnhancedAddSceneCommand;
import com.zsmartsystems.zigbee.zcl.clusters.scenes.EnhancedAddSceneResponse;
import com.zsmartsystems.zigbee.zcl.clusters.scenes.EnhancedViewSceneCommand;
import com.zsmartsystems.zigbee.zcl.clusters.scenes.EnhancedViewSceneResponse;
import com.zsmartsystems.zigbee.zcl.clusters.scenes.GetSceneMembershipCommand;
import com.zsmartsystems.zigbee.zcl.clusters.scenes.GetSceneMembershipResponse;
import com.zsmartsystems.zigbee.zcl.clusters.scenes.RecallSceneCommand;
import com.zsmartsystems.zigbee.zcl.clusters.scenes.RemoveAllScenesCommand;
import com.zsmartsystems.zigbee.zcl.clusters.scenes.RemoveAllScenesResponse;
import com.zsmartsystems.zigbee.zcl.clusters.scenes.RemoveSceneCommand;
import com.zsmartsystems.zigbee.zcl.clusters.scenes.RemoveSceneResponse;
import com.zsmartsystems.zigbee.zcl.clusters.scenes.StoreSceneCommand;
import com.zsmartsystems.zigbee.zcl.clusters.scenes.StoreSceneResponse;
import com.zsmartsystems.zigbee.zcl.clusters.scenes.ViewSceneCommand;
import com.zsmartsystems.zigbee.zcl.clusters.scenes.ViewSceneResponse;
import com.zsmartsystems.zigbee.zcl.clusters.scenes.ZclScenesCommand;
import com.zsmartsystems.zigbee.zcl.field.ExtensionFieldSet;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * <b>Scenes</b> cluster implementation (<i>Cluster ID 0x0005</i>).
 * <p>
 * The scenes cluster provides attributes and commands for setting up and recalling scenes.
 * Each scene corresponds to a set of stored values of specified attributes for one or more
 * clusters on the same end point as the scenes cluster.
 * <p>
 * In most cases scenes are associated with a particular group ID. Scenes may also exist without
 * a group, in which case the value 0x0000 replaces the group ID. Note that extra care is required
 * in these cases to avoid a scene ID collision, and that commands related to scenes without a
 * group may only be unicast, i.e.: they may not be multicast or broadcast.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2021-01-29T21:31:36Z")
public class ZclScenesCluster extends ZclCluster {
    /**
     * The ZigBee Cluster Library Cluster ID
     */
    public static final int CLUSTER_ID = 0x0005;

    /**
     * The ZigBee Cluster Library Cluster Name
     */
    public static final String CLUSTER_NAME = "Scenes";

    // Attribute constants
    /**
     * The SceneCount attribute specifies the number of scenes currently in the device's
     * scene table.
     */
    public static final int ATTR_SCENECOUNT = 0x0000;
    /**
     * The CurrentScene attribute holds the Scene ID of the scene last invoked.
     */
    public static final int ATTR_CURRENTSCENE = 0x0001;
    /**
     * The CurrentGroup attribute holds the Group ID of the scene last invoked, or 0x0000 if the
     * scene last invoked is not associated with a group.
     */
    public static final int ATTR_CURRENTGROUP = 0x0002;
    /**
     * The SceneValid attribute indicates whether the state of the device corresponds to that
     * associated with the CurrentScene and CurrentGroup attributes. TRUE indicates that
     * these attributes are valid, FALSE indicates that they are not valid.
     * <p>
     * Before a scene has been stored or recalled, this attribute is set to FALSE. After a
     * successful Store Scene or Recall Scene command it is set to TRUE. If, after a scene is
     * stored or recalled, the state of the device is modified, this attribute is set to FALSE.
     */
    public static final int ATTR_SCENEVALID = 0x0003;
    /**
     * The most significant bit of the NameSupport attribute indicates whether or not scene
     * names are supported. A value of 1 indicates that they are supported, and a value of 0
     * indicates that they are not supported.
     */
    public static final int ATTR_NAMESUPPORT = 0x0004;
    /**
     * The LastConfiguredBy attribute is 64-bits in length and specifies the IEEE address of
     * the device that last configured the scene table.
     * <p>
     * The value 0xffffffffffffffff indicates that the device has not been configured, or
     * that the address of the device that last configured the scenes cluster is not known.
     */
    public static final int ATTR_LASTCONFIGUREDBY = 0x0005;

    @Override
    protected Map<Integer, ZclAttribute> initializeClientAttributes() {
        Map<Integer, ZclAttribute> attributeMap = new ConcurrentSkipListMap<>();

        return attributeMap;
    }

    @Override
    protected Map<Integer, ZclAttribute> initializeServerAttributes() {
        Map<Integer, ZclAttribute> attributeMap = new ConcurrentSkipListMap<>();

        attributeMap.put(ATTR_SCENECOUNT, new ZclAttribute(this, ATTR_SCENECOUNT, "Scene Count", ZclDataType.UNSIGNED_8_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_CURRENTSCENE, new ZclAttribute(this, ATTR_CURRENTSCENE, "Current Scene", ZclDataType.UNSIGNED_8_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_CURRENTGROUP, new ZclAttribute(this, ATTR_CURRENTGROUP, "Current Group", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_SCENEVALID, new ZclAttribute(this, ATTR_SCENEVALID, "Scene Valid", ZclDataType.BOOLEAN, true, true, false, false));
        attributeMap.put(ATTR_NAMESUPPORT, new ZclAttribute(this, ATTR_NAMESUPPORT, "Name Support", ZclDataType.BITMAP_8_BIT, true, true, false, false));
        attributeMap.put(ATTR_LASTCONFIGUREDBY, new ZclAttribute(this, ATTR_LASTCONFIGUREDBY, "Last Configured By", ZclDataType.IEEE_ADDRESS, false, true, false, false));

        return attributeMap;
    }

    @Override
    protected Map<Integer, Class<? extends ZclCommand>> initializeServerCommands() {
        Map<Integer, Class<? extends ZclCommand>> commandMap = new ConcurrentSkipListMap<>();

        commandMap.put(0x0000, AddSceneResponse.class);
        commandMap.put(0x0001, ViewSceneResponse.class);
        commandMap.put(0x0002, RemoveSceneResponse.class);
        commandMap.put(0x0003, RemoveAllScenesResponse.class);
        commandMap.put(0x0004, StoreSceneResponse.class);
        commandMap.put(0x0006, GetSceneMembershipResponse.class);
        commandMap.put(0x0040, EnhancedAddSceneResponse.class);
        commandMap.put(0x0041, EnhancedViewSceneResponse.class);
        commandMap.put(0x0042, CopySceneResponse.class);

        return commandMap;
    }

    @Override
    protected Map<Integer, Class<? extends ZclCommand>> initializeClientCommands() {
        Map<Integer, Class<? extends ZclCommand>> commandMap = new ConcurrentSkipListMap<>();

        commandMap.put(0x0000, AddSceneCommand.class);
        commandMap.put(0x0001, ViewSceneCommand.class);
        commandMap.put(0x0002, RemoveSceneCommand.class);
        commandMap.put(0x0003, RemoveAllScenesCommand.class);
        commandMap.put(0x0004, StoreSceneCommand.class);
        commandMap.put(0x0005, RecallSceneCommand.class);
        commandMap.put(0x0006, GetSceneMembershipCommand.class);
        commandMap.put(0x0040, EnhancedAddSceneCommand.class);
        commandMap.put(0x0041, EnhancedViewSceneCommand.class);
        commandMap.put(0x0042, CopySceneCommand.class);

        return commandMap;
    }

    /**
     * Default constructor to create a Scenes cluster.
     *
     * @param zigbeeEndpoint the {@link ZigBeeEndpoint} this cluster is contained within
     */
    public ZclScenesCluster(final ZigBeeEndpoint zigbeeEndpoint) {
        super(zigbeeEndpoint, CLUSTER_ID, CLUSTER_NAME);
    }

    /**
     * Sends a {@link ZclScenesCommand} and returns the {@link Future} to the result which will complete when the remote
     * device response is received, or the request times out.
     *
     * @param command the {@link ZclScenesCommand} to send
     * @return the command result future
     */
    public Future<CommandResult> sendCommand(ZclScenesCommand command) {
        return super.sendCommand(command);
    }

    /**
     * Sends a response to the command. This method sets all the common elements of the response based on the command -
     * eg transactionId, direction, address...
     *
     * @param command the {@link ZclScenesCommand} to which the response is being sent
     * @param response the {@link ZclScenesCommand} to send
     */
    public void sendResponse(ZclScenesCommand command, ZclScenesCommand response) {
        super.sendResponse(command, response);
    }

    /**
     * Get the <i>Scene Count</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * The SceneCount attribute specifies the number of scenes currently in the device's
     * scene table.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getSceneCountAsync() {
        return read(serverAttributes.get(ATTR_SCENECOUNT));
    }

    /**
     * Synchronously get the <i>Scene Count</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * The SceneCount attribute specifies the number of scenes currently in the device's
     * scene table.
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
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getSceneCount(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_SCENECOUNT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_SCENECOUNT).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_SCENECOUNT));
    }

    /**
     * Set reporting for the <i>Scene Count</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * The SceneCount attribute specifies the number of scenes currently in the device's
     * scene table.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #setReporting(int attributeId, int minInterval, int maxInterval, Object reportableChange)}
     */
    @Deprecated
    public Future<CommandResult> setSceneCountReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_SCENECOUNT), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Current Scene</i> attribute [attribute ID <b>0x0001</b>].
     * <p>
     * The CurrentScene attribute holds the Scene ID of the scene last invoked.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getCurrentSceneAsync() {
        return read(serverAttributes.get(ATTR_CURRENTSCENE));
    }

    /**
     * Synchronously get the <i>Current Scene</i> attribute [attribute ID <b>0x0001</b>].
     * <p>
     * The CurrentScene attribute holds the Scene ID of the scene last invoked.
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
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getCurrentScene(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_CURRENTSCENE).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_CURRENTSCENE).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_CURRENTSCENE));
    }

    /**
     * Set reporting for the <i>Current Scene</i> attribute [attribute ID <b>0x0001</b>].
     * <p>
     * The CurrentScene attribute holds the Scene ID of the scene last invoked.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #setReporting(int attributeId, int minInterval, int maxInterval, Object reportableChange)}
     */
    @Deprecated
    public Future<CommandResult> setCurrentSceneReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_CURRENTSCENE), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Current Group</i> attribute [attribute ID <b>0x0002</b>].
     * <p>
     * The CurrentGroup attribute holds the Group ID of the scene last invoked, or 0x0000 if the
     * scene last invoked is not associated with a group.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getCurrentGroupAsync() {
        return read(serverAttributes.get(ATTR_CURRENTGROUP));
    }

    /**
     * Synchronously get the <i>Current Group</i> attribute [attribute ID <b>0x0002</b>].
     * <p>
     * The CurrentGroup attribute holds the Group ID of the scene last invoked, or 0x0000 if the
     * scene last invoked is not associated with a group.
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
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getCurrentGroup(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_CURRENTGROUP).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_CURRENTGROUP).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_CURRENTGROUP));
    }

    /**
     * Set reporting for the <i>Current Group</i> attribute [attribute ID <b>0x0002</b>].
     * <p>
     * The CurrentGroup attribute holds the Group ID of the scene last invoked, or 0x0000 if the
     * scene last invoked is not associated with a group.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #setReporting(int attributeId, int minInterval, int maxInterval, Object reportableChange)}
     */
    @Deprecated
    public Future<CommandResult> setCurrentGroupReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(serverAttributes.get(ATTR_CURRENTGROUP), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>Scene Valid</i> attribute [attribute ID <b>0x0003</b>].
     * <p>
     * The SceneValid attribute indicates whether the state of the device corresponds to that
     * associated with the CurrentScene and CurrentGroup attributes. TRUE indicates that
     * these attributes are valid, FALSE indicates that they are not valid.
     * <p>
     * Before a scene has been stored or recalled, this attribute is set to FALSE. After a
     * successful Store Scene or Recall Scene command it is set to TRUE. If, after a scene is
     * stored or recalled, the state of the device is modified, this attribute is set to FALSE.
     * <p>
     * The attribute is of type {@link Boolean}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getSceneValidAsync() {
        return read(serverAttributes.get(ATTR_SCENEVALID));
    }

    /**
     * Synchronously get the <i>Scene Valid</i> attribute [attribute ID <b>0x0003</b>].
     * <p>
     * The SceneValid attribute indicates whether the state of the device corresponds to that
     * associated with the CurrentScene and CurrentGroup attributes. TRUE indicates that
     * these attributes are valid, FALSE indicates that they are not valid.
     * <p>
     * Before a scene has been stored or recalled, this attribute is set to FALSE. After a
     * successful Store Scene or Recall Scene command it is set to TRUE. If, after a scene is
     * stored or recalled, the state of the device is modified, this attribute is set to FALSE.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Boolean}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Boolean} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Boolean getSceneValid(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_SCENEVALID).isLastValueCurrent(refreshPeriod)) {
            return (Boolean) serverAttributes.get(ATTR_SCENEVALID).getLastValue();
        }

        return (Boolean) readSync(serverAttributes.get(ATTR_SCENEVALID));
    }

    /**
     * Set reporting for the <i>Scene Valid</i> attribute [attribute ID <b>0x0003</b>].
     * <p>
     * The SceneValid attribute indicates whether the state of the device corresponds to that
     * associated with the CurrentScene and CurrentGroup attributes. TRUE indicates that
     * these attributes are valid, FALSE indicates that they are not valid.
     * <p>
     * Before a scene has been stored or recalled, this attribute is set to FALSE. After a
     * successful Store Scene or Recall Scene command it is set to TRUE. If, after a scene is
     * stored or recalled, the state of the device is modified, this attribute is set to FALSE.
     * <p>
     * The attribute is of type {@link Boolean}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #setReporting(int attributeId, int minInterval, int maxInterval)}
     */
    @Deprecated
    public Future<CommandResult> setSceneValidReporting(final int minInterval, final int maxInterval) {
        return setReporting(serverAttributes.get(ATTR_SCENEVALID), minInterval, maxInterval);
    }

    /**
     * Get the <i>Name Support</i> attribute [attribute ID <b>0x0004</b>].
     * <p>
     * The most significant bit of the NameSupport attribute indicates whether or not scene
     * names are supported. A value of 1 indicates that they are supported, and a value of 0
     * indicates that they are not supported.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getNameSupportAsync() {
        return read(serverAttributes.get(ATTR_NAMESUPPORT));
    }

    /**
     * Synchronously get the <i>Name Support</i> attribute [attribute ID <b>0x0004</b>].
     * <p>
     * The most significant bit of the NameSupport attribute indicates whether or not scene
     * names are supported. A value of 1 indicates that they are supported, and a value of 0
     * indicates that they are not supported.
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
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public Integer getNameSupport(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_NAMESUPPORT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) serverAttributes.get(ATTR_NAMESUPPORT).getLastValue();
        }

        return (Integer) readSync(serverAttributes.get(ATTR_NAMESUPPORT));
    }

    /**
     * Set reporting for the <i>Name Support</i> attribute [attribute ID <b>0x0004</b>].
     * <p>
     * The most significant bit of the NameSupport attribute indicates whether or not scene
     * names are supported. A value of 1 indicates that they are supported, and a value of 0
     * indicates that they are not supported.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #setReporting(int attributeId, int minInterval, int maxInterval)}
     */
    @Deprecated
    public Future<CommandResult> setNameSupportReporting(final int minInterval, final int maxInterval) {
        return setReporting(serverAttributes.get(ATTR_NAMESUPPORT), minInterval, maxInterval);
    }

    /**
     * Get the <i>Last Configured By</i> attribute [attribute ID <b>0x0005</b>].
     * <p>
     * The LastConfiguredBy attribute is 64-bits in length and specifies the IEEE address of
     * the device that last configured the scene table.
     * <p>
     * The value 0xffffffffffffffff indicates that the device has not been configured, or
     * that the address of the device that last configured the scenes cluster is not known.
     * <p>
     * The attribute is of type {@link IeeeAddress}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.2.0, replaced by {@link #readAttribute(int attributeId)}
     */
    @Deprecated
    public Future<CommandResult> getLastConfiguredByAsync() {
        return read(serverAttributes.get(ATTR_LASTCONFIGUREDBY));
    }

    /**
     * Synchronously get the <i>Last Configured By</i> attribute [attribute ID <b>0x0005</b>].
     * <p>
     * The LastConfiguredBy attribute is 64-bits in length and specifies the IEEE address of
     * the device that last configured the scene table.
     * <p>
     * The value 0xffffffffffffffff indicates that the device has not been configured, or
     * that the address of the device that last configured the scenes cluster is not known.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link IeeeAddress}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link IeeeAddress} attribute value, or null on error
     * @deprecated As of release 1.2.0, replaced by {@link #ZclAttribute#readValue(long refreshPeriod)}
     */
    @Deprecated
    public IeeeAddress getLastConfiguredBy(final long refreshPeriod) {
        if (serverAttributes.get(ATTR_LASTCONFIGUREDBY).isLastValueCurrent(refreshPeriod)) {
            return (IeeeAddress) serverAttributes.get(ATTR_LASTCONFIGUREDBY).getLastValue();
        }

        return (IeeeAddress) readSync(serverAttributes.get(ATTR_LASTCONFIGUREDBY));
    }

    /**
     * The Add Scene Command
     * <p>
     * The Add Scene command shall be addressed to a single device (not a group).
     *
     * @param groupId {@link Integer} Group ID
     * @param sceneId {@link Integer} Scene ID
     * @param transitionTime {@link Integer} Transition Time
     * @param sceneName {@link String} Scene Name
     * @param extensionFieldSets {@link List<ExtensionFieldSet>} Extension Field Sets
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.addSceneCommand(parameters ...)</code>
     * with <code>cluster.sendCommand(new addSceneCommand(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> addSceneCommand(Integer groupId, Integer sceneId, Integer transitionTime, String sceneName, List<ExtensionFieldSet> extensionFieldSets) {
        AddSceneCommand command = new AddSceneCommand();

        // Set the fields
        command.setGroupId(groupId);
        command.setSceneId(sceneId);
        command.setTransitionTime(transitionTime);
        command.setSceneName(sceneName);
        command.setExtensionFieldSets(extensionFieldSets);

        return sendCommand(command);
    }

    /**
     * The View Scene Command
     * <p>
     * The View Scene command shall be addressed to a single device (not a group).
     *
     * @param groupId {@link Integer} Group ID
     * @param sceneId {@link Integer} Scene ID
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.viewSceneCommand(parameters ...)</code>
     * with <code>cluster.sendCommand(new viewSceneCommand(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> viewSceneCommand(Integer groupId, Integer sceneId) {
        ViewSceneCommand command = new ViewSceneCommand();

        // Set the fields
        command.setGroupId(groupId);
        command.setSceneId(sceneId);

        return sendCommand(command);
    }

    /**
     * The Remove Scene Command
     * <p>
     * The Remove All Scenes may be addressed to a single device or to a group.
     *
     * @param groupId {@link Integer} Group ID
     * @param sceneId {@link Integer} Scene ID
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.removeSceneCommand(parameters ...)</code>
     * with <code>cluster.sendCommand(new removeSceneCommand(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> removeSceneCommand(Integer groupId, Integer sceneId) {
        RemoveSceneCommand command = new RemoveSceneCommand();

        // Set the fields
        command.setGroupId(groupId);
        command.setSceneId(sceneId);

        return sendCommand(command);
    }

    /**
     * The Remove All Scenes Command
     * <p>
     * The Remove All Scenes may be addressed to a single device or to a group.
     *
     * @param groupId {@link Integer} Group ID
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.removeAllScenesCommand(parameters ...)</code>
     * with <code>cluster.sendCommand(new removeAllScenesCommand(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> removeAllScenesCommand(Integer groupId) {
        RemoveAllScenesCommand command = new RemoveAllScenesCommand();

        // Set the fields
        command.setGroupId(groupId);

        return sendCommand(command);
    }

    /**
     * The Store Scene Command
     * <p>
     * The Store Scene command may be addressed to a single device or to a group.
     *
     * @param groupId {@link Integer} Group ID
     * @param sceneId {@link Integer} Scene ID
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.storeSceneCommand(parameters ...)</code>
     * with <code>cluster.sendCommand(new storeSceneCommand(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> storeSceneCommand(Integer groupId, Integer sceneId) {
        StoreSceneCommand command = new StoreSceneCommand();

        // Set the fields
        command.setGroupId(groupId);
        command.setSceneId(sceneId);

        return sendCommand(command);
    }

    /**
     * The Recall Scene Command
     * <p>
     * The Recall Scene command may be addressed to a single device or to a group.
     *
     * @param groupId {@link Integer} Group ID
     * @param sceneId {@link Integer} Scene ID
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.recallSceneCommand(parameters ...)</code>
     * with <code>cluster.sendCommand(new recallSceneCommand(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> recallSceneCommand(Integer groupId, Integer sceneId) {
        RecallSceneCommand command = new RecallSceneCommand();

        // Set the fields
        command.setGroupId(groupId);
        command.setSceneId(sceneId);

        return sendCommand(command);
    }

    /**
     * The Get Scene Membership Command
     * <p>
     * The Get Scene Membership command can be used to find an unused scene number within the
     * group when no commissioning tool is in the network, or for a commissioning tool to get
     * used scenes for a group on a single device or on all devices in the group.
     *
     * @param groupId {@link Integer} Group ID
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.getSceneMembershipCommand(parameters ...)</code>
     * with <code>cluster.sendCommand(new getSceneMembershipCommand(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> getSceneMembershipCommand(Integer groupId) {
        GetSceneMembershipCommand command = new GetSceneMembershipCommand();

        // Set the fields
        command.setGroupId(groupId);

        return sendCommand(command);
    }

    /**
     * The Enhanced Add Scene Command
     * <p>
     * The Enhanced Add Scene command allows a scene to be added using a finer scene transition
     * time than the Add Scene command.
     *
     * @param groupId {@link Integer} Group ID
     * @param sceneId {@link Integer} Scene ID
     * @param transitionTime {@link Integer} Transition Time
     * @param sceneName {@link String} Scene Name
     * @param extensionFieldSets {@link List<ExtensionFieldSet>} Extension Field Sets
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.enhancedAddSceneCommand(parameters ...)</code>
     * with <code>cluster.sendCommand(new enhancedAddSceneCommand(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> enhancedAddSceneCommand(Integer groupId, Integer sceneId, Integer transitionTime, String sceneName, List<ExtensionFieldSet> extensionFieldSets) {
        EnhancedAddSceneCommand command = new EnhancedAddSceneCommand();

        // Set the fields
        command.setGroupId(groupId);
        command.setSceneId(sceneId);
        command.setTransitionTime(transitionTime);
        command.setSceneName(sceneName);
        command.setExtensionFieldSets(extensionFieldSets);

        return sendCommand(command);
    }

    /**
     * The Enhanced View Scene Command
     * <p>
     * The Enhanced View Scene command allows a scene to be retrieved using a finer scene
     * transition time than the View Scene command.
     *
     * @param groupId {@link Integer} Group ID
     * @param sceneId {@link Integer} Scene ID
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.enhancedViewSceneCommand(parameters ...)</code>
     * with <code>cluster.sendCommand(new enhancedViewSceneCommand(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> enhancedViewSceneCommand(Integer groupId, Integer sceneId) {
        EnhancedViewSceneCommand command = new EnhancedViewSceneCommand();

        // Set the fields
        command.setGroupId(groupId);
        command.setSceneId(sceneId);

        return sendCommand(command);
    }

    /**
     * The Copy Scene Command
     * <p>
     * The Copy Scene command allows a device to efficiently copy scenes from one group/scene
     * identifier pair to another group/scene identifier pair.
     *
     * @param mode {@link Integer} Mode
     * @param groupIdFrom {@link Integer} Group ID From
     * @param sceneIdFrom {@link Integer} Scene ID From
     * @param groupIdTo {@link Integer} Group ID To
     * @param sceneIdTo {@link Integer} Scene ID To
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.copySceneCommand(parameters ...)</code>
     * with <code>cluster.sendCommand(new copySceneCommand(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> copySceneCommand(Integer mode, Integer groupIdFrom, Integer sceneIdFrom, Integer groupIdTo, Integer sceneIdTo) {
        CopySceneCommand command = new CopySceneCommand();

        // Set the fields
        command.setMode(mode);
        command.setGroupIdFrom(groupIdFrom);
        command.setSceneIdFrom(sceneIdFrom);
        command.setGroupIdTo(groupIdTo);
        command.setSceneIdTo(sceneIdTo);

        return sendCommand(command);
    }

    /**
     * The Add Scene Response
     *
     * @param status {@link ZclStatus} Status
     * @param groupId {@link Integer} Group ID
     * @param sceneId {@link Integer} Scene ID
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.addSceneResponse(parameters ...)</code>
     * with <code>cluster.sendCommand(new addSceneResponse(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> addSceneResponse(ZclStatus status, Integer groupId, Integer sceneId) {
        AddSceneResponse command = new AddSceneResponse();

        // Set the fields
        command.setStatus(status);
        command.setGroupId(groupId);
        command.setSceneId(sceneId);

        return sendCommand(command);
    }

    /**
     * The View Scene Response
     *
     * @param status {@link ZclStatus} Status
     * @param groupId {@link Integer} Group ID
     * @param sceneId {@link Integer} Scene ID
     * @param transitionTime {@link Integer} Transition Time
     * @param sceneName {@link String} Scene Name
     * @param extensionFieldSets {@link List<ExtensionFieldSet>} Extension Field Sets
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.viewSceneResponse(parameters ...)</code>
     * with <code>cluster.sendCommand(new viewSceneResponse(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> viewSceneResponse(ZclStatus status, Integer groupId, Integer sceneId, Integer transitionTime, String sceneName, List<ExtensionFieldSet> extensionFieldSets) {
        ViewSceneResponse command = new ViewSceneResponse();

        // Set the fields
        command.setStatus(status);
        command.setGroupId(groupId);
        command.setSceneId(sceneId);
        command.setTransitionTime(transitionTime);
        command.setSceneName(sceneName);
        command.setExtensionFieldSets(extensionFieldSets);

        return sendCommand(command);
    }

    /**
     * The Remove Scene Response
     *
     * @param status {@link ZclStatus} Status
     * @param groupId {@link Integer} Group ID
     * @param sceneId {@link Integer} Scene ID
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.removeSceneResponse(parameters ...)</code>
     * with <code>cluster.sendCommand(new removeSceneResponse(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> removeSceneResponse(ZclStatus status, Integer groupId, Integer sceneId) {
        RemoveSceneResponse command = new RemoveSceneResponse();

        // Set the fields
        command.setStatus(status);
        command.setGroupId(groupId);
        command.setSceneId(sceneId);

        return sendCommand(command);
    }

    /**
     * The Remove All Scenes Response
     *
     * @param status {@link ZclStatus} Status
     * @param groupId {@link Integer} Group ID
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.removeAllScenesResponse(parameters ...)</code>
     * with <code>cluster.sendCommand(new removeAllScenesResponse(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> removeAllScenesResponse(ZclStatus status, Integer groupId) {
        RemoveAllScenesResponse command = new RemoveAllScenesResponse();

        // Set the fields
        command.setStatus(status);
        command.setGroupId(groupId);

        return sendCommand(command);
    }

    /**
     * The Store Scene Response
     *
     * @param status {@link ZclStatus} Status
     * @param groupId {@link Integer} Group ID
     * @param sceneId {@link Integer} Scene ID
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.storeSceneResponse(parameters ...)</code>
     * with <code>cluster.sendCommand(new storeSceneResponse(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> storeSceneResponse(ZclStatus status, Integer groupId, Integer sceneId) {
        StoreSceneResponse command = new StoreSceneResponse();

        // Set the fields
        command.setStatus(status);
        command.setGroupId(groupId);
        command.setSceneId(sceneId);

        return sendCommand(command);
    }

    /**
     * The Get Scene Membership Response
     *
     * @param status {@link ZclStatus} Status
     * @param capacity {@link Integer} Capacity
     * @param groupId {@link Integer} Group ID
     * @param sceneCount {@link Integer} Scene Count
     * @param sceneList {@link List<Integer>} Scene List
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.getSceneMembershipResponse(parameters ...)</code>
     * with <code>cluster.sendCommand(new getSceneMembershipResponse(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> getSceneMembershipResponse(ZclStatus status, Integer capacity, Integer groupId, Integer sceneCount, List<Integer> sceneList) {
        GetSceneMembershipResponse command = new GetSceneMembershipResponse();

        // Set the fields
        command.setStatus(status);
        command.setCapacity(capacity);
        command.setGroupId(groupId);
        command.setSceneCount(sceneCount);
        command.setSceneList(sceneList);

        return sendCommand(command);
    }

    /**
     * The Enhanced Add Scene Response
     *
     * @param status {@link ZclStatus} Status
     * @param groupId {@link Integer} Group ID
     * @param sceneId {@link Integer} Scene ID
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.enhancedAddSceneResponse(parameters ...)</code>
     * with <code>cluster.sendCommand(new enhancedAddSceneResponse(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> enhancedAddSceneResponse(ZclStatus status, Integer groupId, Integer sceneId) {
        EnhancedAddSceneResponse command = new EnhancedAddSceneResponse();

        // Set the fields
        command.setStatus(status);
        command.setGroupId(groupId);
        command.setSceneId(sceneId);

        return sendCommand(command);
    }

    /**
     * The Enhanced View Scene Response
     *
     * @param status {@link ZclStatus} Status
     * @param groupId {@link Integer} Group ID
     * @param sceneId {@link Integer} Scene ID
     * @param transitionTime {@link Integer} Transition Time
     * @param sceneName {@link String} Scene Name
     * @param extensionFieldSets {@link List<ExtensionFieldSet>} Extension Field Sets
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.enhancedViewSceneResponse(parameters ...)</code>
     * with <code>cluster.sendCommand(new enhancedViewSceneResponse(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> enhancedViewSceneResponse(ZclStatus status, Integer groupId, Integer sceneId, Integer transitionTime, String sceneName, List<ExtensionFieldSet> extensionFieldSets) {
        EnhancedViewSceneResponse command = new EnhancedViewSceneResponse();

        // Set the fields
        command.setStatus(status);
        command.setGroupId(groupId);
        command.setSceneId(sceneId);
        command.setTransitionTime(transitionTime);
        command.setSceneName(sceneName);
        command.setExtensionFieldSets(extensionFieldSets);

        return sendCommand(command);
    }

    /**
     * The Copy Scene Response
     *
     * @param status {@link ZclStatus} Status
     * @param groupId {@link Integer} Group ID
     * @param sceneId {@link Integer} Scene ID
     * @return the {@link Future<CommandResult>} command result future
     * @deprecated As of release 1.3.0.
     * Use extended ZclCommand class constructors to instantiate the command
     * and {@link #sendCommand} or {@link #sendResponse} to send the command.
     * This provides further control when sending the command by allowing customisation
     * of the command (for example by disabling the <i>DefaultResponse</i>.
     * <p>
     * e.g. replace <code>cluster.copySceneResponse(parameters ...)</code>
     * with <code>cluster.sendCommand(new copySceneResponse(parameters ...))</code>
     */
    @Deprecated
    public Future<CommandResult> copySceneResponse(ZclStatus status, Integer groupId, Integer sceneId) {
        CopySceneResponse command = new CopySceneResponse();

        // Set the fields
        command.setStatus(status);
        command.setGroupId(groupId);
        command.setSceneId(sceneId);

        return sendCommand(command);
    }
}

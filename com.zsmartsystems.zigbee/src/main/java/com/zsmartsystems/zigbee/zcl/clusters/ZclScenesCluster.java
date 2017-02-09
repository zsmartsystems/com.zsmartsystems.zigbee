package com.zsmartsystems.zigbee.zcl.clusters;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.ZigBeeDeviceAddress;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.zcl.ZclAttribute;
import com.zsmartsystems.zigbee.zcl.ZclCluster;
import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.clusters.scenes.AddSceneCommand;
import com.zsmartsystems.zigbee.zcl.clusters.scenes.AddSceneResponse;
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
import com.zsmartsystems.zigbee.zcl.field.*;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * <b>Scenes</b> cluster implementation (<i>Cluster ID 0x0005</i>).
 * <p>
 * The scenes cluster provides attributes and commands for setting up and recalling
 * scenes. Each scene corresponds to a set of stored values of specified attributes for
 * one or more clusters on the same end point as the scenes cluster.
 * <br>
 * In most cases scenes are associated with a particular group ID. Scenes may also
 * exist without a group, in which case the value 0x0000 replaces the group ID. Note
 * that extra care is required in these cases to avoid a scene ID collision, and that
 * commands related to scenes without a group may only be unicast, i.e.: they may
 * not be multicast or broadcast.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class ZclScenesCluster extends ZclCluster {
    // Cluster ID
    public static final int CLUSTER_ID = 0x0005;

    // Cluster Name
    public static final String CLUSTER_NAME = "Scenes";

    // Attribute constants
    public static final int ATTR_SCENECOUNT = 0x0000;
    public static final int ATTR_CURRENTSCENE = 0x0001;
    public static final int ATTR_CURRENTGROUP = 0x0002;
    public static final int ATTR_SCENEVALID = 0x0003;
    public static final int ATTR_NAMESUPPORT = 0x0004;
    public static final int ATTR_LASTCONFIGUREDBY = 0x0005;

    // Attribute initialisation
    protected Map<Integer, ZclAttribute> initializeAttributes() {
        Map<Integer, ZclAttribute> attributeMap = new HashMap<Integer, ZclAttribute>(6);

        attributeMap.put(ATTR_SCENECOUNT, new ZclAttribute(0, "SceneCount", ZclDataType.UNSIGNED_8_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_CURRENTSCENE, new ZclAttribute(1, "CurrentScene", ZclDataType.UNSIGNED_8_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_CURRENTGROUP, new ZclAttribute(2, "CurrentGroup", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, false));
        attributeMap.put(ATTR_SCENEVALID, new ZclAttribute(3, "SceneValid", ZclDataType.BOOLEAN, true, true, false, false));
        attributeMap.put(ATTR_NAMESUPPORT, new ZclAttribute(4, "NameSupport", ZclDataType.BITMAP_8_BIT, true, true, false, false));
        attributeMap.put(ATTR_LASTCONFIGUREDBY, new ZclAttribute(5, "LastConfiguredBy", ZclDataType.IEEE_ADDRESS, false, true, false, false));

        return attributeMap;
    }

    /**
     * Default constructor.
     */
    public ZclScenesCluster(final ZigBeeNetworkManager zigbeeManager, final ZigBeeDeviceAddress zigbeeAddress) {
        super(zigbeeManager, zigbeeAddress, CLUSTER_ID, CLUSTER_NAME);
    }


    /**
     * <p>
     * Get the <i>SceneCount</i> attribute [attribute ID <b>0</b>].
     * <p>
     * The SceneCount attribute specifies the number of scenes currently in the device's
     * scene table.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getSceneCountAsync() {
        return read(attributes.get(ATTR_SCENECOUNT));
    }


    /**
     * <p>
     * Synchronously get the <i>SceneCount</i> attribute [attribute ID <b>0</b>].
     * <p>
     * The SceneCount attribute specifies the number of scenes currently in the device's
     * scene table.
     * <p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getSceneCount() {
        return (Integer) readSync(attributes.get(ATTR_SCENECOUNT));
    }

    /**
     * <p>
     * Get the <i>CurrentScene</i> attribute [attribute ID <b>1</b>].
     * <p>
     * <br>
     * The CurrentScene attribute holds the Scene ID of the scene last invoked.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getCurrentSceneAsync() {
        return read(attributes.get(ATTR_CURRENTSCENE));
    }


    /**
     * <p>
     * Synchronously get the <i>CurrentScene</i> attribute [attribute ID <b>1</b>].
     * <p>
     * <br>
     * The CurrentScene attribute holds the Scene ID of the scene last invoked.
     * <p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getCurrentScene() {
        return (Integer) readSync(attributes.get(ATTR_CURRENTSCENE));
    }

    /**
     * <p>
     * Get the <i>CurrentGroup</i> attribute [attribute ID <b>2</b>].
     * <p>
     * <br>
     * The CurrentGroup attribute holds the Group ID of the scene last invoked, or
     * 0x0000 if the scene last invoked is not associated with a group.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getCurrentGroupAsync() {
        return read(attributes.get(ATTR_CURRENTGROUP));
    }


    /**
     * <p>
     * Synchronously get the <i>CurrentGroup</i> attribute [attribute ID <b>2</b>].
     * <p>
     * <br>
     * The CurrentGroup attribute holds the Group ID of the scene last invoked, or
     * 0x0000 if the scene last invoked is not associated with a group.
     * <p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getCurrentGroup() {
        return (Integer) readSync(attributes.get(ATTR_CURRENTGROUP));
    }

    /**
     * <p>
     * Get the <i>SceneValid</i> attribute [attribute ID <b>3</b>].
     * <p>
     * <br>
     * The SceneValid attribute indicates whether the state of the device corresponds to
     * that associated with the CurrentScene and CurrentGroup attributes. TRUE
     * indicates that these attributes are valid, FALSE indicates that they are not valid.
     * <br>
     * Before a scene has been stored or recalled, this attribute is set to FALSE. After a
     * successful Store Scene or Recall Scene command it is set to TRUE. If, after a
     * scene is stored or recalled, the state of the device is modified, this attribute is set to
     * FALSE.
     * <p>
     * The attribute is of type {@link Boolean}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getSceneValidAsync() {
        return read(attributes.get(ATTR_SCENEVALID));
    }


    /**
     * <p>
     * Synchronously get the <i>SceneValid</i> attribute [attribute ID <b>3</b>].
     * <p>
     * <br>
     * The SceneValid attribute indicates whether the state of the device corresponds to
     * that associated with the CurrentScene and CurrentGroup attributes. TRUE
     * indicates that these attributes are valid, FALSE indicates that they are not valid.
     * <br>
     * Before a scene has been stored or recalled, this attribute is set to FALSE. After a
     * successful Store Scene or Recall Scene command it is set to TRUE. If, after a
     * scene is stored or recalled, the state of the device is modified, this attribute is set to
     * FALSE.
     * <p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link Boolean}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Boolean} attribute value, or null on error
     */
    public Boolean getSceneValid() {
        return (Boolean) readSync(attributes.get(ATTR_SCENEVALID));
    }

    /**
     * <p>
     * Get the <i>NameSupport</i> attribute [attribute ID <b>4</b>].
     * <p>
     * <br>
     * The most significant bit of the NameSupport attribute indicates whether or not
     * scene names are supported. A value of 1 indicates that they are supported, and a
     * value of 0 indicates that they are not supported.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getNameSupportAsync() {
        return read(attributes.get(ATTR_NAMESUPPORT));
    }


    /**
     * <p>
     * Synchronously get the <i>NameSupport</i> attribute [attribute ID <b>4</b>].
     * <p>
     * <br>
     * The most significant bit of the NameSupport attribute indicates whether or not
     * scene names are supported. A value of 1 indicates that they are supported, and a
     * value of 0 indicates that they are not supported.
     * <p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getNameSupport() {
        return (Integer) readSync(attributes.get(ATTR_NAMESUPPORT));
    }

    /**
     * <p>
     * Get the <i>LastConfiguredBy</i> attribute [attribute ID <b>5</b>].
     * <p>
     * <br>
     * The LastConfiguredBy attribute is 64-bits in length and specifies the IEEE address
     * of the device that last configured the scene table.
     * <br>
     * The value 0xffffffffffffffff indicates that the device has not been configured, or
     * that the address of the device that last configured the scenes cluster is not known.
     * <p>
     * The attribute is of type {@link Long}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getLastConfiguredByAsync() {
        return read(attributes.get(ATTR_LASTCONFIGUREDBY));
    }


    /**
     * <p>
     * Synchronously get the <i>LastConfiguredBy</i> attribute [attribute ID <b>5</b>].
     * <p>
     * <br>
     * The LastConfiguredBy attribute is 64-bits in length and specifies the IEEE address
     * of the device that last configured the scene table.
     * <br>
     * The value 0xffffffffffffffff indicates that the device has not been configured, or
     * that the address of the device that last configured the scenes cluster is not known.
     * <p>
     * This method will block until the response is received or a timeout occurs.
     * <p>
     * The attribute is of type {@link Long}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Long} attribute value, or null on error
     */
    public Long getLastConfiguredBy() {
        return (Long) readSync(attributes.get(ATTR_LASTCONFIGUREDBY));
    }

    /**
     * The Add Scene Command
     * <p>
     * The Add Scene command shall be addressed to a single device (not a group).
     *
     * @param groupId {@link Integer} Group ID
     * @param sceneId {@link Integer} Scene ID
     * @param transitionTime {@link Integer} Transition time
     * @param sceneName {@link String} Scene Name
     * @param extensionFieldSets {@link List<ExtensionFieldSet>} Extension field sets
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> addSceneCommand(Integer groupId, Integer sceneId, Integer transitionTime, String sceneName, List<ExtensionFieldSet> extensionFieldSets) {
        AddSceneCommand command = new AddSceneCommand();

        // Set the fields
        command.setGroupId(groupId);
        command.setSceneId(sceneId);
        command.setTransitionTime(transitionTime);
        command.setSceneName(sceneName);
        command.setExtensionFieldSets(extensionFieldSets);

        return send(command);
    }

    /**
     * The View Scene Command
     * <p>
     * The View Scene command shall be addressed to a single device (not a group).
     *
     * @param groupId {@link Integer} Group ID
     * @param sceneId {@link Integer} Scene ID
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> viewSceneCommand(Integer groupId, Integer sceneId) {
        ViewSceneCommand command = new ViewSceneCommand();

        // Set the fields
        command.setGroupId(groupId);
        command.setSceneId(sceneId);

        return send(command);
    }

    /**
     * The Remove Scene Command
     * <p>
     * The Remove All Scenes may be addressed to a single device or to a group.
     *
     * @param groupId {@link Integer} Group ID
     * @param sceneId {@link Integer} Scene ID
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> removeSceneCommand(Integer groupId, Integer sceneId) {
        RemoveSceneCommand command = new RemoveSceneCommand();

        // Set the fields
        command.setGroupId(groupId);
        command.setSceneId(sceneId);

        return send(command);
    }

    /**
     * The Remove All Scenes Command
     * <p>
     * The Remove All Scenes may be addressed to a single device or to a group.
     *
     * @param groupId {@link Integer} Group ID
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> removeAllScenesCommand(Integer groupId) {
        RemoveAllScenesCommand command = new RemoveAllScenesCommand();

        // Set the fields
        command.setGroupId(groupId);

        return send(command);
    }

    /**
     * The Store Scene Command
     * <p>
     * The Store Scene command may be addressed to a single device or to a group.
     *
     * @param groupId {@link Integer} Group ID
     * @param sceneId {@link Integer} Scene ID
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> storeSceneCommand(Integer groupId, Integer sceneId) {
        StoreSceneCommand command = new StoreSceneCommand();

        // Set the fields
        command.setGroupId(groupId);
        command.setSceneId(sceneId);

        return send(command);
    }

    /**
     * The Recall Scene Command
     * <p>
     * The Recall Scene command may be addressed to a single device or to a group.
     *
     * @param groupId {@link Integer} Group ID
     * @param sceneId {@link Integer} Scene ID
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> recallSceneCommand(Integer groupId, Integer sceneId) {
        RecallSceneCommand command = new RecallSceneCommand();

        // Set the fields
        command.setGroupId(groupId);
        command.setSceneId(sceneId);

        return send(command);
    }

    /**
     * The Get Scene Membership Command
     * <p>
     * The Get Scene Membership command can be used to find an unused scene
     * number within the group when no commissioning tool is in the network, or for a
     * commissioning tool to get used scenes for a group on a single device or on all
     * devices in the group.
     *
     * @param groupId {@link Integer} Group ID
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getSceneMembershipCommand(Integer groupId) {
        GetSceneMembershipCommand command = new GetSceneMembershipCommand();

        // Set the fields
        command.setGroupId(groupId);

        return send(command);
    }

    /**
     * The Add Scene Response
     *
     * @param status {@link Integer} Status
     * @param groupId {@link Integer} Group ID
     * @param sceneId {@link Integer} Scene ID
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> addSceneResponse(Integer status, Integer groupId, Integer sceneId) {
        AddSceneResponse command = new AddSceneResponse();

        // Set the fields
        command.setStatus(status);
        command.setGroupId(groupId);
        command.setSceneId(sceneId);

        return send(command);
    }

    /**
     * The View Scene Response
     *
     * @param status {@link Integer} Status
     * @param groupId {@link Integer} Group ID
     * @param sceneId {@link Integer} Scene ID
     * @param transitionTime {@link Integer} Transition time
     * @param sceneName {@link String} Scene Name
     * @param extensionFieldSets {@link List<ExtensionFieldSet>} Extension field sets
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> viewSceneResponse(Integer status, Integer groupId, Integer sceneId, Integer transitionTime, String sceneName, List<ExtensionFieldSet> extensionFieldSets) {
        ViewSceneResponse command = new ViewSceneResponse();

        // Set the fields
        command.setStatus(status);
        command.setGroupId(groupId);
        command.setSceneId(sceneId);
        command.setTransitionTime(transitionTime);
        command.setSceneName(sceneName);
        command.setExtensionFieldSets(extensionFieldSets);

        return send(command);
    }

    /**
     * The Remove Scene Response
     *
     * @param status {@link Integer} Status
     * @param groupId {@link Integer} Group ID
     * @param sceneId {@link Integer} Scene ID
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> removeSceneResponse(Integer status, Integer groupId, Integer sceneId) {
        RemoveSceneResponse command = new RemoveSceneResponse();

        // Set the fields
        command.setStatus(status);
        command.setGroupId(groupId);
        command.setSceneId(sceneId);

        return send(command);
    }

    /**
     * The Remove All Scenes Response
     *
     * @param status {@link Integer} Status
     * @param groupId {@link Integer} Group ID
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> removeAllScenesResponse(Integer status, Integer groupId) {
        RemoveAllScenesResponse command = new RemoveAllScenesResponse();

        // Set the fields
        command.setStatus(status);
        command.setGroupId(groupId);

        return send(command);
    }

    /**
     * The Store Scene Response
     *
     * @param status {@link Integer} Status
     * @param groupId {@link Integer} Group ID
     * @param sceneId {@link Integer} Scene ID
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> storeSceneResponse(Integer status, Integer groupId, Integer sceneId) {
        StoreSceneResponse command = new StoreSceneResponse();

        // Set the fields
        command.setStatus(status);
        command.setGroupId(groupId);
        command.setSceneId(sceneId);

        return send(command);
    }

    /**
     * The Get Scene Membership Response
     *
     * @param status {@link Integer} Status
     * @param capacity {@link Integer} Capacity
     * @param groupId {@link Integer} Group ID
     * @param sceneCount {@link Integer} Scene count
     * @param sceneList {@link List<Unsigned8BitInteger>} Scene list
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getSceneMembershipResponse(Integer status, Integer capacity, Integer groupId, Integer sceneCount, List<Unsigned8BitInteger> sceneList) {
        GetSceneMembershipResponse command = new GetSceneMembershipResponse();

        // Set the fields
        command.setStatus(status);
        command.setCapacity(capacity);
        command.setGroupId(groupId);
        command.setSceneCount(sceneCount);
        command.setSceneList(sceneList);

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
            case 0: // ADD_SCENE_COMMAND
                return new AddSceneCommand();
            case 1: // VIEW_SCENE_COMMAND
                return new ViewSceneCommand();
            case 2: // REMOVE_SCENE_COMMAND
                return new RemoveSceneCommand();
            case 3: // REMOVE_ALL_SCENES_COMMAND
                return new RemoveAllScenesCommand();
            case 4: // STORE_SCENE_COMMAND
                return new StoreSceneCommand();
            case 5: // RECALL_SCENE_COMMAND
                return new RecallSceneCommand();
            case 6: // GET_SCENE_MEMBERSHIP_COMMAND
                return new GetSceneMembershipCommand();
            default:
                return null;
        }
    }

    @Override
    public ZclCommand getResponseFromId(int commandId) {
        switch (commandId) {
            case 0: // ADD_SCENE_RESPONSE
                return new AddSceneResponse();
            case 1: // VIEW_SCENE_RESPONSE
                return new ViewSceneResponse();
            case 2: // REMOVE_SCENE_RESPONSE
                return new RemoveSceneResponse();
            case 3: // REMOVE_ALL_SCENES_RESPONSE
                return new RemoveAllScenesResponse();
            case 4: // STORE_SCENE_RESPONSE
                return new StoreSceneResponse();
            case 5: // GET_SCENE_MEMBERSHIP_RESPONSE
                return new GetSceneMembershipResponse();
            default:
                return null;
        }
    }
}

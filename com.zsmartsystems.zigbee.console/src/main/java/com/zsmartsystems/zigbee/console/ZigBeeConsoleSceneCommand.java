/**
 * Copyright (c) 2016-2021 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.zcl.ZclStatus;
import com.zsmartsystems.zigbee.zcl.clusters.ZclScenesCluster;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.ZclColorControlExtensionField;
import com.zsmartsystems.zigbee.zcl.clusters.levelcontrol.ZclLevelControlExtensionField;
import com.zsmartsystems.zigbee.zcl.clusters.onoff.ZclOnOffExtensionField;
import com.zsmartsystems.zigbee.zcl.clusters.scenes.AddSceneCommand;
import com.zsmartsystems.zigbee.zcl.clusters.scenes.EnhancedAddSceneCommand;
import com.zsmartsystems.zigbee.zcl.clusters.scenes.EnhancedViewSceneCommand;
import com.zsmartsystems.zigbee.zcl.clusters.scenes.EnhancedViewSceneResponse;
import com.zsmartsystems.zigbee.zcl.clusters.scenes.RecallSceneCommand;
import com.zsmartsystems.zigbee.zcl.clusters.scenes.StoreSceneCommand;
import com.zsmartsystems.zigbee.zcl.clusters.scenes.ViewSceneCommand;
import com.zsmartsystems.zigbee.zcl.clusters.scenes.ViewSceneResponse;
import com.zsmartsystems.zigbee.zcl.clusters.scenes.ZclScenesCommand;
import com.zsmartsystems.zigbee.zcl.field.ExtensionFieldSet;

/**
 * Handles management of scenes.
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeConsoleSceneCommand extends ZigBeeConsoleAbstractCommand {
    @Override
    public String getCommand() {
        return "scene";
    }

    @Override
    public String getDescription() {
        return "Provides scene support functions";
    }

    @Override
    public String getSyntax() {
        return "ENDPOINT [INFO | ADD | VIEW | STORE | RECALL] [GROUPID SCENEID TRANSITION_TIME NAME EXTENSION_FIELDS]";
    }

    @Override
    public String getHelp() {
        return "";
    }

    @Override
    public void process(ZigBeeNetworkManager networkManager, String[] args, PrintStream out)
            throws IllegalArgumentException {
        if (args.length < 2) {
            throw new IllegalArgumentException("Incorrect number of arguments.");
        }

        final ZigBeeEndpoint endpoint = getEndpoint(networkManager, args[1]);
        ZclScenesCluster scenesCluster = (ZclScenesCluster) endpoint.getInputCluster(ZclScenesCluster.CLUSTER_ID);
        if (scenesCluster == null) {
            throw new IllegalArgumentException(
                    "Endpoint " + endpoint.getEndpointAddress() + " does not support scenes!");
        }

        switch (args[2].toUpperCase()) {
            case "INFO":
                info(networkManager, endpoint, args, out);
                break;
            case "STORE":
                storeScene(networkManager, endpoint, args, out);
                break;
            case "ADD":
            case "EADD":
                addScene(networkManager, endpoint, args, out);
                break;
            case "REMOVE":
                break;
            case "REMOVEALL":
                break;
            case "VIEW":
            case "EVIEW":
                viewScene(networkManager, endpoint, args, out);
                break;
            case "RECALL":
                recallScene(networkManager, endpoint, args, out);
                break;

            default:
                throw new IllegalArgumentException("Invalid command option " + args[2]);
        }
    }

    private void info(ZigBeeNetworkManager networkManager, ZigBeeEndpoint endpoint, String[] args,
            PrintStream out) {
        ZclScenesCluster scenesCluster = (ZclScenesCluster) endpoint.getInputCluster(ZclScenesCluster.CLUSTER_ID);
        Integer sceneCount = (Integer) scenesCluster.getAttribute(ZclScenesCluster.ATTR_SCENECOUNT)
                .readValue(Long.MAX_VALUE);
        Integer nameSupport = (Integer) scenesCluster.getAttribute(ZclScenesCluster.ATTR_NAMESUPPORT)
                .readValue(Long.MAX_VALUE);

        out.println("Scenes stored in scene table : " + (sceneCount == null ? "Unsupported" : sceneCount));
        out.println("Names supported              : " + (nameSupport == null ? "Unsupported"
                : (nameSupport == 0 ? "Unsupported" : "Supported")));
    }

    private void addScene(ZigBeeNetworkManager networkManager, ZigBeeEndpoint endpoint, String[] args,
            PrintStream out) {
        int groupId = parseInteger(args[3]);
        int sceneId = parseInteger(args[4]);
        int transitionTime = parseInteger(args[5]);
        String sceneName = args[6];

        List<ExtensionFieldSet> extensionFields = new ArrayList<>();
        for (int cnt = 7; cnt < args.length; cnt++) {
            String tmp = args[cnt].substring(args[cnt].indexOf("[") + 1, args[cnt].indexOf("]"));
            if (tmp == null) {
                throw new IllegalArgumentException("Invalid extension field.");
            }

            String[] extensionFieldString = tmp.split(",");
            if (extensionFieldString == null) {
                throw new IllegalArgumentException("Invalid extension field.");
            }

            ExtensionFieldSet extensionField = null;

            if (args[cnt].toLowerCase().startsWith("onoff[")) {
                extensionField = getOnOffScene(extensionFieldString);
            } else if (args[cnt].toLowerCase().startsWith("level[")) {
                extensionField = getLevelControlScene(extensionFieldString);
            } else if (args[cnt].toLowerCase().startsWith("color[")) {
                extensionField = getColorControlScene(extensionFieldString);
            }

            if (extensionField == null) {

            }
            out.println("Adding to scene: " + extensionField);
            extensionFields.add(extensionField);
        }

        ZclScenesCommand addScene;
        if (args[2].equalsIgnoreCase("ADD")) {
            addScene = new AddSceneCommand(groupId, sceneId, transitionTime, sceneName, extensionFields);
        } else {
            addScene = new EnhancedAddSceneCommand(groupId, sceneId, transitionTime, sceneName,
                    extensionFields);
        }
        ZclScenesCluster scenesCluster = (ZclScenesCluster) endpoint.getInputCluster(ZclScenesCluster.CLUSTER_ID);
        scenesCluster.sendCommand(addScene);
    }

    private void viewScene(ZigBeeNetworkManager networkManager, ZigBeeEndpoint endpoint, String[] args,
            PrintStream out) {
        int groupId = parseInteger(args[3]);
        int sceneId = parseInteger(args[4]);

        ZclScenesCommand viewScene;
        if (args[2].equalsIgnoreCase("VIEW")) {
            viewScene = new ViewSceneCommand(groupId, sceneId);
        } else {
            viewScene = new EnhancedViewSceneCommand(groupId, sceneId);
        }
        ZclScenesCluster scenesCluster = (ZclScenesCluster) endpoint.getInputCluster(ZclScenesCluster.CLUSTER_ID);
        try {
            CommandResult result = scenesCluster.sendCommand(viewScene).get();
            if (result.isSuccess()) {
                ZclStatus status;
                String sceneName;
                Integer transitionTime;
                List<ExtensionFieldSet> extensionFields;

                if (args[2].equalsIgnoreCase("VIEW")) {
                    ViewSceneResponse response = (ViewSceneResponse) result.getResponse();

                    status = ZclStatus.getStatus(response.getStatus());
                    sceneId = response.getSceneId();
                    groupId = response.getGroupId();
                    sceneName = response.getSceneName();
                    transitionTime = response.getTransitionTime();
                    extensionFields = response.getExtensionFieldSets();
                } else {
                    EnhancedViewSceneResponse response = (EnhancedViewSceneResponse) result.getResponse();

                    status = ZclStatus.getStatus(response.getStatus());
                    sceneId = response.getSceneId();
                    groupId = response.getGroupId();
                    sceneName = response.getSceneName();
                    transitionTime = response.getTransitionTime();
                    extensionFields = response.getExtensionFieldSets();
                }

                out.println("Scene Status   :" + status);
                out.println("Scene ID       :" + sceneId);
                out.println("Group ID       :" + groupId);
                out.println("Scene Name     :" + sceneName);
                out.println("Transition Time:" + transitionTime);

                for (ExtensionFieldSet extensionField : extensionFields) {
                    out.println(extensionField);
                }
            } else {
            }
        } catch (InterruptedException | ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void storeScene(ZigBeeNetworkManager networkManager, ZigBeeEndpoint endpoint, String[] args,
            PrintStream out) {
        int groupId = parseInteger(args[3]);
        int sceneId = parseInteger(args[4]);

        ZclScenesCluster scenesCluster = (ZclScenesCluster) endpoint.getInputCluster(ZclScenesCluster.CLUSTER_ID);

        StoreSceneCommand store = new StoreSceneCommand(groupId, sceneId);
        scenesCluster.sendCommand(store);
    }

    private void recallScene(ZigBeeNetworkManager networkManager, ZigBeeEndpoint endpoint, String[] args,
            PrintStream out) {
        int groupId = parseInteger(args[3]);
        int sceneId = parseInteger(args[4]);

        ZclScenesCluster scenesCluster = (ZclScenesCluster) endpoint.getInputCluster(ZclScenesCluster.CLUSTER_ID);

        RecallSceneCommand recall = new RecallSceneCommand(groupId, sceneId);
        scenesCluster.sendCommand(recall);
    }

    private ZclOnOffExtensionField getOnOffScene(String[] scene) {
        Boolean onOff = Boolean.parseBoolean(scene[0]);
        return new ZclOnOffExtensionField(onOff);
    }

    private ZclLevelControlExtensionField getLevelControlScene(String[] scene) {
        Integer currentLevel = Integer.parseInt(scene[0]);
        Integer currentFrequency = Integer.parseInt(scene[1]);

        return new ZclLevelControlExtensionField(currentLevel, currentFrequency);
    }

    private ZclColorControlExtensionField getColorControlScene(String[] scene) {
        Integer currentX = Integer.parseInt(scene[0]);
        Integer currentY = Integer.parseInt(scene[1]);
        Integer currentHue = Integer.parseInt(scene[2]);
        Integer currentSaturation = Integer.parseInt(scene[3]);
        Integer colorLoopActive = Integer.parseInt(scene[4]);
        Integer colorLoopDirection = Integer.parseInt(scene[5]);
        Integer colorLoopTime = Integer.parseInt(scene[6]);
        Integer colorTemperature = Integer.parseInt(scene[7]);
        return new ZclColorControlExtensionField(currentX, currentY, currentHue, currentSaturation, colorLoopActive,
                colorLoopDirection, colorLoopTime, colorTemperature);
    }
}

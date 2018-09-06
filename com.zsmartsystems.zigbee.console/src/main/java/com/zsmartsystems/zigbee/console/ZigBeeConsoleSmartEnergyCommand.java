/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console;

import java.io.PrintStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.app.seclient.SmartEnergyClient;
import com.zsmartsystems.zigbee.security.ZigBeeCbkeProvider;
import com.zsmartsystems.zigbee.zcl.clusters.ZclMeteringCluster;
import com.zsmartsystems.zigbee.zcl.clusters.metering.GetProfileResponse;
import com.zsmartsystems.zigbee.zcl.clusters.metering.GetProfileStatusEnum;
import com.zsmartsystems.zigbee.zcl.clusters.metering.IntervalPeriodEnum;
import com.zsmartsystems.zigbee.zcl.clusters.metering.RequestFastPollModeResponse;
import com.zsmartsystems.zigbee.zcl.protocol.ZclClusterType;

/**
 * Provides a command line interface to the {@link SmartEnergyClient} and related features
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeConsoleSmartEnergyCommand extends ZigBeeConsoleAbstractCommand {
    @Override
    public String getCommand() {
        return "smartenergy";
    }

    @Override
    protected ZigBeeConsoleArgument initializeArguments() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getDescription() {
        return "Provides detailed information about Smart Energy system.";
    }

    @Override
    public String getSyntax() {
        return "[GETPROFILE | FASTPOLL] [endpoint] [period]";
    }

    @Override
    public String getHelp() {
        return "";
    }

    @Override
    public void process(ZigBeeNetworkManager networkManager, String[] args, PrintStream out)
            throws IllegalArgumentException {

        if (args.length == 1) {
            printClientState(networkManager, out);
            return;
        }

        switch (args[1].toLowerCase()) {
            case "getprofile":
                getProfile(networkManager, args, out);
                break;
            case "fastpoll":
                fastPoll(networkManager, args, out);
                break;
            default:
                throw new IllegalArgumentException("Unknown Smart Energy command: " + args[1].toUpperCase());
        }
    }

    private void printClientState(ZigBeeNetworkManager networkManager, PrintStream out) {
        SmartEnergyClient sepClient = (SmartEnergyClient) networkManager.getExtension(SmartEnergyClient.class);
        if (sepClient == null) {
            throw new IllegalArgumentException("Smart Energy extension is not running.");
        }

        ZigBeeCbkeProvider cbkeProvider = sepClient.getCbkeProvider();
        out.println("Supported security suites   : " + cbkeProvider.getSupportedCryptoSuites());
        out.println("Discovery state             : " + sepClient.getDiscoveryState());
        out.println("Last TC Keep-Alive response : " + sepClient.getLastKeepAlive());
    }

    private void getProfile(ZigBeeNetworkManager networkManager, String[] args, PrintStream out) {
        if (args.length < 3) {
            throw new IllegalArgumentException("Invalid number of arguments");
        }

        final ZigBeeEndpoint endpoint = getEndpoint(networkManager, args[2]);

        long repeatPeriod = 0;
        int repeatCycles = 10;
        for (int i = 3; i < args.length; i++) {
            String cmd[] = args[i].split("=");
            if (cmd != null && cmd.length == 2) {
                switch (cmd[0].toLowerCase()) {
                    case "period":
                        repeatPeriod = parseInteger(cmd[1]);
                        break;
                    case "cycles":
                        repeatCycles = parseInteger(cmd[1]);
                        break;
                    default:
                        break;
                }
            }
        }

        if (repeatPeriod != 0) {
            out.println("Polling profile on " + endpoint.getEndpointAddress() + " every " + repeatPeriod
                    + " seconds. Press any key to stop...");
        }

        ZclMeteringCluster cluster = (ZclMeteringCluster) endpoint.getInputCluster(ZclClusterType.METERING.getId());
        if (cluster == null) {
            throw new IllegalArgumentException(
                    "Metering cluster not found on endpoint " + endpoint.getEndpointAddress());
        }

        try {
            for (int cnt = 0; cnt < repeatCycles; cnt++) {
                CommandResult result = cluster.getProfile(0, new Calendar.Builder().setInstant(0).build(), 1).get();
                GetProfileResponse response = (GetProfileResponse) result.getResponse();
                if (response.getStatus() != 0) {
                    out.println(
                            "Error returned from getProfile: " + GetProfileStatusEnum.getByValue(response.getStatus()));
                    break;
                }

                Map<String, Object> objects = new HashMap<>();
                objects.put("endTime", response.getEndTime());
                objects.put("intervalPeriod", IntervalPeriodEnum.getByValue(response.getProfileIntervalPeriod()));
                objects.put("periodsDelivered", response.getProfileIntervalPeriod());
                out.println(propertiesToJson(objects));
                out.println(response.toString());

                if (repeatPeriod == 0) {
                    break;
                }
                Thread.sleep(repeatPeriod * 1000);
            }
        } catch (InterruptedException | ExecutionException e) {
            out.println(e.getClass().getSimpleName() + " exception while polling profile!");
        }
    }

    private void fastPoll(ZigBeeNetworkManager networkManager, String[] args, PrintStream out) {
        if (args.length < 3) {
            throw new IllegalArgumentException("Invalid number of arguments");
        }

        final ZigBeeEndpoint endpoint = getEndpoint(networkManager, args[2]);
        final int period = parseInteger(args[3]);

        ZclMeteringCluster cluster = (ZclMeteringCluster) endpoint.getInputCluster(ZclClusterType.METERING.getId());
        if (cluster == null) {
            throw new IllegalArgumentException(
                    "Metering cluster not found on endpoint " + endpoint.getEndpointAddress());
        }

        try {
            CommandResult response = cluster.requestFastPollMode(period, 15).get();
            if (response.isError()) {
                out.println("Error response when sending fast poll request");
                return;
            }
            RequestFastPollModeResponse fastPollResponse = (RequestFastPollModeResponse) response.getResponse();

        } catch (InterruptedException | ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public String propertiesToJson(Map<String, Object> properties) {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("{");
        boolean first = true;
        for (Map.Entry<String, Object> entry : properties.entrySet()) {
            if (!first) {
                jsonBuilder.append(",");
            }
            first = false;

            jsonBuilder.append('\"');
            jsonBuilder.append(entry.getKey());
            jsonBuilder.append("\":\"");
            jsonBuilder.append(entry.getValue());
            jsonBuilder.append('\"');
        }
        jsonBuilder.append("}");
        return jsonBuilder.toString();
    }
}

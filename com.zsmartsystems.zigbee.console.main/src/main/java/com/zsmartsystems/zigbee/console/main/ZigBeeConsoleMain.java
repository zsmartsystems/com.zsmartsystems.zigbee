/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console.main;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.log4j.xml.DOMConfigurator;

import com.zsmartsystems.zigbee.ExtendedPanId;
import com.zsmartsystems.zigbee.ZigBeeChannel;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeProfileType;
import com.zsmartsystems.zigbee.ZigBeeStatus;
import com.zsmartsystems.zigbee.app.basic.ZigBeeBasicServerExtension;
import com.zsmartsystems.zigbee.app.discovery.ZigBeeDiscoveryExtension;
import com.zsmartsystems.zigbee.app.iasclient.ZigBeeIasCieExtension;
import com.zsmartsystems.zigbee.app.otaserver.ZigBeeOtaUpgradeExtension;
import com.zsmartsystems.zigbee.console.ZigBeeConsoleCommand;
import com.zsmartsystems.zigbee.console.ember.EmberConsoleMmoHashCommand;
import com.zsmartsystems.zigbee.console.ember.EmberConsoleNcpAddressTableCommand;
import com.zsmartsystems.zigbee.console.ember.EmberConsoleNcpChildrenCommand;
import com.zsmartsystems.zigbee.console.ember.EmberConsoleNcpConfigurationCommand;
import com.zsmartsystems.zigbee.console.ember.EmberConsoleNcpCountersCommand;
import com.zsmartsystems.zigbee.console.ember.EmberConsoleNcpHandlerCommand;
import com.zsmartsystems.zigbee.console.ember.EmberConsoleNcpMfglibCommand;
import com.zsmartsystems.zigbee.console.ember.EmberConsoleNcpMulticastCommand;
import com.zsmartsystems.zigbee.console.ember.EmberConsoleNcpPolicyCommand;
import com.zsmartsystems.zigbee.console.ember.EmberConsoleNcpRoutingCommand;
import com.zsmartsystems.zigbee.console.ember.EmberConsoleNcpScanCommand;
import com.zsmartsystems.zigbee.console.ember.EmberConsoleNcpStateCommand;
import com.zsmartsystems.zigbee.console.ember.EmberConsoleNcpTokenCommand;
import com.zsmartsystems.zigbee.console.ember.EmberConsoleNcpValueCommand;
import com.zsmartsystems.zigbee.console.ember.EmberConsoleNcpVersionCommand;
import com.zsmartsystems.zigbee.console.ember.EmberConsoleSecurityStateCommand;
import com.zsmartsystems.zigbee.console.ember.EmberConsoleTransientKeyCommand;
import com.zsmartsystems.zigbee.console.telegesis.TelegesisConsoleSecurityStateCommand;
import com.zsmartsystems.zigbee.console.zstack.ZstackConsoleNcpSecurityCommand;
import com.zsmartsystems.zigbee.console.zstack.ZstackConsoleNcpStateCommand;
import com.zsmartsystems.zigbee.database.ZigBeeNetworkDataStore;
import com.zsmartsystems.zigbee.dongle.cc2531.ZigBeeDongleTiCc2531;
import com.zsmartsystems.zigbee.dongle.conbee.ZigBeeDongleConBee;
import com.zsmartsystems.zigbee.dongle.ember.ZigBeeDongleEzsp;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspConfigId;
import com.zsmartsystems.zigbee.dongle.telegesis.ZigBeeDongleTelegesis;
import com.zsmartsystems.zigbee.dongle.xbee.ZigBeeDongleXBee;
import com.zsmartsystems.zigbee.dongle.zstack.ZigBeeDongleZstack;
import com.zsmartsystems.zigbee.security.ZigBeeKey;
import com.zsmartsystems.zigbee.serial.ZigBeeSerialPort;
import com.zsmartsystems.zigbee.serialization.DefaultDeserializer;
import com.zsmartsystems.zigbee.serialization.DefaultSerializer;
import com.zsmartsystems.zigbee.transport.ConcentratorConfig;
import com.zsmartsystems.zigbee.transport.ConcentratorType;
import com.zsmartsystems.zigbee.transport.TransportConfig;
import com.zsmartsystems.zigbee.transport.TransportConfigOption;
import com.zsmartsystems.zigbee.transport.TrustCentreJoinMode;
import com.zsmartsystems.zigbee.transport.ZigBeePort;
import com.zsmartsystems.zigbee.transport.ZigBeePort.FlowControl;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportTransmit;
import com.zsmartsystems.zigbee.zcl.clusters.ZclBallastConfigurationCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclBasicCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclBinaryInputBasicCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclColorControlCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclDiagnosticsCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclElectricalMeasurementCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclGroupsCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclIasZoneCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclIdentifyCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclLevelControlCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclMeteringCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclOnOffCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclOtaUpgradeCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclPollControlCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclPowerConfigurationCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclPressureMeasurementCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclScenesCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclTemperatureMeasurementCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclThermostatCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclWindowCoveringCluster;

/**
 * The ZigBee test console. Simple console used for testing the framework.
 *
 * @author Chris Jackson
 */
public class ZigBeeConsoleMain {
    /**
     * Private constructor to disable constructing main class.
     */
    private ZigBeeConsoleMain() {
    }

    /**
     * The main method.
     *
     * @param args the command arguments
     */
    public static void main(final String[] args) {
        DOMConfigurator.configure("./log4j.xml");

        final String serialPortName;
        final String dongleName;
        final int serialBaud;
        final int defaultProfileId;

        final Set<Integer> supportedClientClusters = new TreeSet<>();
        supportedClientClusters.addAll(Stream
                .of(ZclBasicCluster.CLUSTER_ID, ZclIdentifyCluster.CLUSTER_ID, ZclGroupsCluster.CLUSTER_ID,
                        ZclPowerConfigurationCluster.CLUSTER_ID, ZclScenesCluster.CLUSTER_ID,
                        ZclPollControlCluster.CLUSTER_ID, ZclOnOffCluster.CLUSTER_ID, ZclLevelControlCluster.CLUSTER_ID,
                        ZclColorControlCluster.CLUSTER_ID, ZclPressureMeasurementCluster.CLUSTER_ID,
                        ZclTemperatureMeasurementCluster.CLUSTER_ID, ZclIasZoneCluster.CLUSTER_ID,
                        ZclThermostatCluster.CLUSTER_ID, ZclWindowCoveringCluster.CLUSTER_ID,
                        ZclMeteringCluster.CLUSTER_ID, ZclElectricalMeasurementCluster.CLUSTER_ID,
                        ZclDiagnosticsCluster.CLUSTER_ID, ZclPowerConfigurationCluster.CLUSTER_ID,
                        ZclBallastConfigurationCluster.CLUSTER_ID, ZclOtaUpgradeCluster.CLUSTER_ID,
                        ZclBinaryInputBasicCluster.CLUSTER_ID, 1000)
                .collect(Collectors.toSet()));

        final Set<Integer> supportedServerClusters = new TreeSet<>();
        supportedServerClusters.addAll(Stream
                .of(ZclBasicCluster.CLUSTER_ID, ZclIdentifyCluster.CLUSTER_ID, ZclGroupsCluster.CLUSTER_ID,
                        ZclScenesCluster.CLUSTER_ID, ZclPowerConfigurationCluster.CLUSTER_ID,
                        ZclPollControlCluster.CLUSTER_ID, ZclOnOffCluster.CLUSTER_ID, ZclLevelControlCluster.CLUSTER_ID,
                        ZclColorControlCluster.CLUSTER_ID, ZclPressureMeasurementCluster.CLUSTER_ID,
                        ZclOtaUpgradeCluster.CLUSTER_ID, ZclMeteringCluster.CLUSTER_ID,
                        ZclElectricalMeasurementCluster.CLUSTER_ID,
                        ZclDiagnosticsCluster.CLUSTER_ID, ZclBallastConfigurationCluster.CLUSTER_ID,
                        ZclTemperatureMeasurementCluster.CLUSTER_ID, ZclIasZoneCluster.CLUSTER_ID,
                        ZclWindowCoveringCluster.CLUSTER_ID, ZclBinaryInputBasicCluster.CLUSTER_ID, 1000)
                .collect(Collectors.toSet()));

        final TransportConfig transportOptions = new TransportConfig();
        boolean resetNetwork;
        FlowControl flowControl = null;

        Options options = new Options();
        options.addOption(Option.builder("d").longOpt("dongle").hasArg().argName("dongle type")
                .desc("Set the dongle type to use (EMBER | CC2531 | ZSTACK | TELEGESIS | CONBEE | XBEE)").required()
                .build());
        options.addOption(Option.builder("p").longOpt("port").argName("port name").hasArg().desc("Set the port")
                .required().build());
        options.addOption(
                Option.builder("b").longOpt("baud").hasArg().argName("baud").desc("Set the port baud rate").required().build());
        options.addOption(Option.builder("f").longOpt("flow").hasArg().argName("type")
                .desc("Set the flow control (none | hardware | software)").build());
        options.addOption(Option.builder("c").longOpt("channel").hasArg().argName("channel id")
                .desc("Set the ZigBee channel ID").build());
        options.addOption(
                Option.builder("a").longOpt("pan").hasArg().argName("PAN ID").desc("Set the ZigBee PAN ID").build());
        options.addOption(
                Option.builder("e").longOpt("epan").hasArg().argName("EPAN ID").desc("Set the ZigBee EPAN ID").build());
        options.addOption(Option.builder("n").longOpt("nwkkey").hasArg().argName("key")
                .desc("Set the ZigBee Network key (defaults to random value)").build());
        options.addOption(Option.builder("o").longOpt("nwkkeyoutcnt").hasArg().argName("counter")
                .desc("Set the ZigBee Network key outgoing frame counter").build());
        options.addOption(Option.builder("l").longOpt("linkkey").hasArg().argName("key")
                .desc("Set the ZigBee Link key (defaults to well known ZHA key)").build());
        options.addOption(Option.builder("t").longOpt("linkkeyoutcnt").hasArg().argName("counter")
                .desc("Set the ZigBee Link key outgoing frame counter").build());
        options.addOption(Option.builder("h").longOpt("profile").hasArg().argName("profile")
                .desc("Set the default profile ID").build());
        options.addOption(Option.builder("r").longOpt("reset").desc("Reset the ZigBee dongle").build());
        options.addOption(Option.builder("?").longOpt("help").desc("Print usage information").build());

        CommandLine cmdline;
        try {
            CommandLineParser parser = new DefaultParser();
            cmdline = parser.parse(options, args);

            if (cmdline.hasOption("help")) {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("zigbeeconsole", options);
                return;
            }

            if (!cmdline.hasOption("dongle")) {
                System.err.println("Dongle must be specified with the 'dongle' option");
                return;
            }
            if (!cmdline.hasOption("port")) {
                System.err.println("Serial port must be specified with the 'port' option");
                return;
            }
            if (cmdline.hasOption("profile")) {
                defaultProfileId = parseDecimalOrHexInt(cmdline.getOptionValue("profile"));
            } else {
                defaultProfileId = ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION.getKey();
            }

            dongleName = cmdline.getOptionValue("dongle");
            serialPortName = cmdline.getOptionValue("port");
            serialBaud = parseDecimalOrHexInt(cmdline.getOptionValue("baud"));
            resetNetwork = cmdline.hasOption("reset");

            if (cmdline.hasOption("flow")) {
                switch (cmdline.getOptionValue("flow").toLowerCase()) {
                    case "software":
                        flowControl = FlowControl.FLOWCONTROL_OUT_XONOFF;
                        break;
                    case "hardware":
                        flowControl = FlowControl.FLOWCONTROL_OUT_RTSCTS;
                        break;
                    case "none":
                        flowControl = FlowControl.FLOWCONTROL_OUT_NONE;
                        break;
                    default:
                        System.err.println(
                                "Unknown flow control option used: " + cmdline.getOptionValue("flow").toLowerCase());
                        return;
                }
            }
        } catch (ParseException exp) {
            System.err.println("Parsing command line failed.  Reason: " + exp.getMessage());
            return;
        }

        // Default the flow control based on the dongle
        if (flowControl == null) {
            switch (dongleName.toUpperCase()) {
                case "EMBER":
                    flowControl = FlowControl.FLOWCONTROL_OUT_RTSCTS;
                    break;
                default:
                    flowControl = FlowControl.FLOWCONTROL_OUT_NONE;
                    break;
            }
        }

        final ZigBeePort serialPort = new ZigBeeSerialPort(serialPortName, serialBaud, flowControl);

        System.out.println("Initialising ZigBee console...");

        List<Class<? extends ZigBeeConsoleCommand>> commands = new ArrayList<>();

        final ZigBeeTransportTransmit dongle;
        if (dongleName.toUpperCase().equals("CC2531")) {
            dongle = new ZigBeeDongleTiCc2531(serialPort);
            transportOptions.addOption(TransportConfigOption.RADIO_TX_POWER, 3);
        } else if (dongleName.toUpperCase().equals("ZSTACK")) {
            dongle = new ZigBeeDongleZstack(serialPort);
            transportOptions.addOption(TransportConfigOption.RADIO_TX_POWER, 3);

            commands.add(ZstackConsoleNcpStateCommand.class);
            commands.add(ZstackConsoleNcpSecurityCommand.class);
        } else if (dongleName.toUpperCase().equals("EMBER")) {
            ZigBeeDongleEzsp emberDongle = new ZigBeeDongleEzsp(serialPort);
            dongle = emberDongle;

            emberDongle.updateDefaultConfiguration(EzspConfigId.EZSP_CONFIG_ADDRESS_TABLE_SIZE, 16);
            emberDongle.updateDefaultConfiguration(EzspConfigId.EZSP_CONFIG_SOURCE_ROUTE_TABLE_SIZE, 100);
            emberDongle.updateDefaultConfiguration(EzspConfigId.EZSP_CONFIG_APS_UNICAST_MESSAGE_COUNT, 16);
            emberDongle.updateDefaultConfiguration(EzspConfigId.EZSP_CONFIG_NEIGHBOR_TABLE_SIZE, 24);

            transportOptions.addOption(TransportConfigOption.RADIO_TX_POWER, 8);

            // Configure the concentrator
            // Max Hops defaults to system max
            ConcentratorConfig concentratorConfig = new ConcentratorConfig();
            concentratorConfig.setType(ConcentratorType.HIGH_RAM);
            concentratorConfig.setMaxFailures(8);
            concentratorConfig.setMaxHops(0);
            concentratorConfig.setRefreshMinimum(60);
            concentratorConfig.setRefreshMaximum(3600);
            transportOptions.addOption(TransportConfigOption.CONCENTRATOR_CONFIG, concentratorConfig);

            // Add transport specific console commands
            commands.add(EmberConsoleNcpAddressTableCommand.class);
            commands.add(EmberConsoleNcpChildrenCommand.class);
            commands.add(EmberConsoleNcpConfigurationCommand.class);
            commands.add(EmberConsoleNcpCountersCommand.class);
            commands.add(EmberConsoleTransientKeyCommand.class);
            commands.add(EmberConsoleMmoHashCommand.class);
            commands.add(EmberConsoleNcpPolicyCommand.class);
            commands.add(EmberConsoleNcpStateCommand.class);
            commands.add(EmberConsoleNcpValueCommand.class);
            commands.add(EmberConsoleNcpVersionCommand.class);
            commands.add(EmberConsoleSecurityStateCommand.class);
            commands.add(EmberConsoleNcpScanCommand.class);
            commands.add(EmberConsoleNcpMfglibCommand.class);
            commands.add(EmberConsoleNcpHandlerCommand.class);
            commands.add(EmberConsoleNcpTokenCommand.class);
            commands.add(EmberConsoleNcpRoutingCommand.class);
            commands.add(EmberConsoleNcpMulticastCommand.class);

            emberDongle.setEmberNcpResetProvider(new EmberNcpHardwareReset());
        } else if (dongleName.toUpperCase().equals("XBEE")) {
            dongle = new ZigBeeDongleXBee(serialPort);
        } else if (dongleName.toUpperCase().equals("CONBEE")) {
            dongle = new ZigBeeDongleConBee(serialPort);
        } else if (dongleName.toUpperCase().equals("TELEGESIS")) {
            ZigBeeDongleTelegesis telegesisDongle = new ZigBeeDongleTelegesis(serialPort);
            telegesisDongle.setTelegesisPassword("password");
            dongle = telegesisDongle;

            Set<Integer> clusters = new HashSet<Integer>();
            clusters.add(ZclIasZoneCluster.CLUSTER_ID);
            transportOptions.addOption(TransportConfigOption.SUPPORTED_OUTPUT_CLUSTERS, clusters);

            commands.add(TelegesisConsoleSecurityStateCommand.class);
        } else {
            dongle = null;
        }

        if (dongle == null) {
            System.out.println("Dongle unable to be opened.");
            return;
        }

        ZigBeeNetworkManager networkManager = new ZigBeeNetworkManager(dongle);

        ZigBeeNetworkDataStore dataStore = new ZigBeeDataStore(dongleName);
        if (resetNetwork) {
            // networkStateSerializer.remove();
        }
        networkManager.setNetworkDataStore(dataStore);
        networkManager.setSerializer(DefaultSerializer.class, DefaultDeserializer.class);
        final ZigBeeConsole console = new ZigBeeConsole(networkManager, dongle, commands);

        // Initialise the network
        ZigBeeStatus initResponse = networkManager.initialize();
        System.out.println("networkManager.initialize returned " + initResponse);
        if (initResponse != ZigBeeStatus.SUCCESS) {
            console.start();
            System.out.println("Console closed.");
            return;
        }

        System.out.println("PAN ID          = " + networkManager.getZigBeePanId());
        System.out.println("Extended PAN ID = " + networkManager.getZigBeeExtendedPanId());
        System.out.println("Channel         = " + networkManager.getZigBeeChannel());

        if (resetNetwork == true) {
            ZigBeeKey nwkKey;
            ZigBeeKey linkKey;
            ExtendedPanId extendedPan;
            Integer channel;
            int pan;

            if (cmdline.hasOption("channel")) {
                channel = parseDecimalOrHexInt(cmdline.getOptionValue("channel"));
            } else {
                channel = -1;
            }
            if (cmdline.hasOption("pan")) {
                pan = parseDecimalOrHexInt(cmdline.getOptionValue("pan"));
            } else {
                pan = 1;
            }
            if (cmdline.hasOption("epan")) {
                extendedPan = new ExtendedPanId(cmdline.getOptionValue("epan"));
            } else {
                extendedPan = new ExtendedPanId();
            }

            if (cmdline.hasOption("nwkkey")) {
                nwkKey = new ZigBeeKey(cmdline.getOptionValue("nwkkey"));
            } else {
                nwkKey = ZigBeeKey.createRandom();
            }
            if (cmdline.hasOption("linkkey")) {
                linkKey = new ZigBeeKey(cmdline.getOptionValue("linkkey"));
            } else {
                linkKey = new ZigBeeKey(new int[] { 0x5A, 0x69, 0x67, 0x42, 0x65, 0x65, 0x41, 0x6C, 0x6C, 0x69, 0x61,
                        0x6E, 0x63, 0x65, 0x30, 0x39 });
            }

            if (cmdline.hasOption("nwkkeyoutcnt")) {
                nwkKey.setOutgoingFrameCounter(parseDecimalOrHexInt(cmdline.getOptionValue("nwkkeyoutcnt")));
            }
            if (cmdline.hasOption("linkkeyoutcnt")) {
                linkKey.setOutgoingFrameCounter(parseDecimalOrHexInt(cmdline.getOptionValue("linkkeyoutcnt")));
            }

            System.out.println("*** Resetting network");
            System.out.println("  * Channel                = " + channel);
            System.out.println("  * PAN ID                 = " + pan);
            System.out.println("  * Extended PAN ID        = " + extendedPan);
            System.out.println("  * Link Key               = " + linkKey);
            if (linkKey.hasOutgoingFrameCounter()) {
                System.out.println("  * Link Key Frame Cnt     = " + linkKey.getOutgoingFrameCounter());
            }
            System.out.println("  * Network Key            = " + nwkKey);
            if (nwkKey.hasOutgoingFrameCounter()) {
                System.out.println("  * Network Key Frame Cnt  = " + nwkKey.getOutgoingFrameCounter());
            }

            networkManager.setZigBeeChannel(ZigBeeChannel.create(channel));
            networkManager.setZigBeePanId(pan);
            networkManager.setZigBeeExtendedPanId(extendedPan);
            networkManager.setZigBeeNetworkKey(nwkKey);
            networkManager.setZigBeeLinkKey(linkKey);
        }

        networkManager.setDefaultProfileId(defaultProfileId);

        transportOptions.addOption(TransportConfigOption.TRUST_CENTRE_JOIN_MODE, TrustCentreJoinMode.TC_JOIN_SECURE);

        // Add the default ZigBeeAlliance09 HA link key

        transportOptions.addOption(TransportConfigOption.TRUST_CENTRE_LINK_KEY, new ZigBeeKey(new int[] { 0x5A, 0x69,
                0x67, 0x42, 0x65, 0x65, 0x41, 0x6C, 0x6C, 0x69, 0x61, 0x6E, 0x63, 0x65, 0x30, 0x39 }));
        // transportOptions.addOption(TransportConfigOption.TRUST_CENTRE_LINK_KEY, new ZigBeeKey(new int[] { 0x41, 0x61,
        // 0x8F, 0xC0, 0xC8, 0x3B, 0x0E, 0x14, 0xA5, 0x89, 0x95, 0x4B, 0x16, 0xE3, 0x14, 0x66 }));

        dongle.updateTransportConfig(transportOptions);

        // Add the extensions to the network
        networkManager.addExtension(new ZigBeeIasCieExtension());
        networkManager.addExtension(new ZigBeeOtaUpgradeExtension());
        networkManager.addExtension(new ZigBeeBasicServerExtension());

        ZigBeeDiscoveryExtension discoveryExtension = new ZigBeeDiscoveryExtension();
        discoveryExtension.setUpdateMeshPeriod(0);
        discoveryExtension.setUpdateOnChange(false);
        networkManager.addExtension(discoveryExtension);

        supportedClientClusters.stream().forEach(clusterId -> networkManager.addSupportedClientCluster(clusterId));
        supportedServerClusters.stream().forEach(clusterId -> networkManager.addSupportedServerCluster(clusterId));

        if (networkManager.startup(resetNetwork) != ZigBeeStatus.SUCCESS) {
            System.out.println("ZigBee console starting up ... [FAIL]");
        } else {
            System.out.println("ZigBee console starting up ... [OK]");
        }

        networkManager.addSupportedCluster(ZclIasZoneCluster.CLUSTER_ID);

        if (dongleName.equalsIgnoreCase("CC2531")) {
            ZigBeeDongleTiCc2531 tiDongle = (ZigBeeDongleTiCc2531) dongle;
            tiDongle.setLedMode(1, false);
            tiDongle.setLedMode(2, false);
        }

        console.start();

        System.out.println("Console closed.");
    }

    /**
     * Parse decimal or hexadecimal integer.
     *
     * @param strVal the string value to parse
     * @return the parsed integer value
     */
    private static int parseDecimalOrHexInt(String strVal) {
        int radix = 10;
        String number = strVal;
        if (number.startsWith("0x")) {
            number = number.substring(2);
            radix = 16;
        }
        return Integer.parseInt(number, radix);
    }
}

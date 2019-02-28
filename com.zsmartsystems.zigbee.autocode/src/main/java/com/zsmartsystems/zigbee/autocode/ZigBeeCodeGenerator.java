/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.autocode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import com.zsmartsystems.zigbee.autocode.xml.ZigBeeXmlCluster;
import com.zsmartsystems.zigbee.autocode.xml.ZigBeeXmlConstant;
import com.zsmartsystems.zigbee.autocode.xml.ZigBeeXmlGlobal;
import com.zsmartsystems.zigbee.autocode.xml.ZigBeeXmlParser;

/**
 * The main entry point to the code generator.
 *
 * @author Chris Jackson (zsmartsystems.com)
 *
 */
public class ZigBeeCodeGenerator {

    public static void main(final String[] args) {
        String sourceRootPath = "target/src/main/java/";
        String outRootPath = "../com.zsmartsystems.zigbee/src/main/java/";

        deleteRecursive(new File(sourceRootPath));

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        String generatedDate = dateFormat.format(new Date());

        ZigBeeXmlParser zclParser = new ZigBeeXmlParser();
        zclParser.addFile("src/main/resources/XXXX_General.xml");

        zclParser.addFile("src/main/resources/0000_Basic.xml");
        zclParser.addFile("src/main/resources/0001_PowerConfiguration.xml");
        zclParser.addFile("src/main/resources/0003_Identify.xml");
        zclParser.addFile("src/main/resources/0004_Groups.xml");
        zclParser.addFile("src/main/resources/0005_Scenes.xml");
        zclParser.addFile("src/main/resources/0006_OnOff.xml");
        zclParser.addFile("src/main/resources/0007_OnOffSwitchConfiguration.xml");
        zclParser.addFile("src/main/resources/0008_LevelControl.xml");
        zclParser.addFile("src/main/resources/0009_Alarms.xml");
        zclParser.addFile("src/main/resources/000A_Time.xml");
        zclParser.addFile("src/main/resources/000B_RssiLocation.xml");
        zclParser.addFile("src/main/resources/000F_BinaryInputBasic.xml");
        zclParser.addFile("src/main/resources/0012_MultistateInputBasic.xml");
        zclParser.addFile("src/main/resources/0013_MultistateOutputBasic.xml");
        zclParser.addFile("src/main/resources/0014_MultistateValueBasic.xml");
        zclParser.addFile("src/main/resources/0019_OtaUpgrade.xml");
        zclParser.addFile("src/main/resources/0020_PollControl.xml");

        zclParser.addFile("src/main/resources/0101_DoorLock.xml");
        zclParser.addFile("src/main/resources/0102_WindowCovering.xml");

        zclParser.addFile("src/main/resources/0201_Thermostat.xml");
        zclParser.addFile("src/main/resources/0202_FanControl.xml");
        zclParser.addFile("src/main/resources/0203_DehumidificationControl.xml");
        zclParser.addFile("src/main/resources/0204_ThermostatUserInterfaceConfiguration.xml");

        zclParser.addFile("src/main/resources/0300_ColorControl.xml");

        zclParser.addFile("src/main/resources/0400_IlluminanceMeasurement.xml");
        zclParser.addFile("src/main/resources/0401_IlluminanceLevelSensing.xml");
        zclParser.addFile("src/main/resources/0402_TemperatureMeasurement.xml");
        zclParser.addFile("src/main/resources/0403_PressureMeasurement.xml");
        zclParser.addFile("src/main/resources/0404_FlowMeasurement.xml");
        zclParser.addFile("src/main/resources/0405_RelativeHumidityMeasurement.xml");
        zclParser.addFile("src/main/resources/0406_OccupancySensing.xml");

        zclParser.addFile("src/main/resources/0500_IasZone.xml");
        zclParser.addFile("src/main/resources/0501_IasAce.xml");
        zclParser.addFile("src/main/resources/0502_IasWd.xml");

        zclParser.addFile("src/main/resources/0700_Price.xml");
        zclParser.addFile("src/main/resources/0701_DemandResponseAndLoadControl.xml");
        zclParser.addFile("src/main/resources/0702_Metering.xml");
        zclParser.addFile("src/main/resources/0703_Messaging.xml");
        zclParser.addFile("src/main/resources/0704_SmartEnergyTunneling.xml");
        zclParser.addFile("src/main/resources/0705_Prepayment.xml");
        zclParser.addFile("src/main/resources/0800_KeyEstablishment.xml");

        zclParser.addFile("src/main/resources/0B04_ElectricalMeasurement.xml");
        zclParser.addFile("src/main/resources/0B05_Diagnostics.xml");

        List<ZigBeeXmlCluster> zclClusters = zclParser.parseClusterConfiguration();

        ZigBeeXmlParser zdoParser = new ZigBeeXmlParser();
        zdoParser.addFile("src/main/resources/XXXX_ZigBeeDeviceObject.xml");

        List<ZigBeeXmlCluster> zdoClusters = zdoParser.parseClusterConfiguration();

        // Process all enums, bitmaps and structures first so we have a consolidated list.
        // We use this later when generating the imports in the cluster and command classes.
        List<ZigBeeXmlCluster> allClusters = new ArrayList<>();
        allClusters.addAll(zclClusters);
        allClusters.addAll(zdoClusters);
        ZigBeeZclDependencyGenerator typeGenerator = new ZigBeeZclDependencyGenerator(allClusters);
        Map<String, String> zclTypes = typeGenerator.getDependencyMap();
        Set<String> dataTypes = typeGenerator.getZclTypeMap();

        new ZigBeeZclClusterGenerator(zclClusters, generatedDate, zclTypes);
        new ZigBeeZclCommandGenerator(zclClusters, generatedDate, zclTypes);
        new ZigBeeZclConstantGenerator(zclClusters, generatedDate, zclTypes);
        new ZigBeeZclStructureGenerator(zclClusters, generatedDate, zclTypes);
        new ZigBeeZclClusterTypeGenerator(zclClusters, generatedDate, zclTypes);
        new ZigBeeZclDataTypeGenerator(dataTypes, generatedDate);

        new ZigBeeZclCommandGenerator(zdoClusters, generatedDate, zclTypes);

        zclParser = new ZigBeeXmlParser();
        zclParser.addFile("src/main/resources/zigbee_constants.xml");
        ZigBeeXmlGlobal globals = zclParser.parseGlobalConfiguration();

        for (ZigBeeXmlConstant constant : globals.constants) {
            new ZigBeeZclConstantGenerator(constant, generatedDate);
        }

        String inRootPath = sourceRootPath.substring(0, sourceRootPath.length() - 1);
        compareFiles(inRootPath, outRootPath, "");

        new ZigBeeZclReadmeGenerator(zclClusters);
    }

    private static boolean fileCompare(String file1, String file2) throws IOException {
        File f = new File(file1);
        if (!f.exists()) {
            return false;
        }
        f = new File(file2);
        if (!f.exists()) {
            return false;
        }

        BufferedReader reader1 = new BufferedReader(new FileReader(file1));
        BufferedReader reader2 = new BufferedReader(new FileReader(file2));

        String line1 = reader1.readLine();
        String line2 = reader2.readLine();

        boolean areEqual = true;

        int lineNum = 1;

        while (line1 != null || line2 != null) {
            if (line1 == null || line2 == null) {
                areEqual = false;

                break;
            } else if (!line1.startsWith("@Generated") && !line1.equalsIgnoreCase(line2)) {
                areEqual = false;

                break;
            }

            line1 = reader1.readLine();
            line2 = reader2.readLine();

            lineNum++;
        }

        if (areEqual) {
            System.out.println("Two files have same content.");
        } else {
            System.out.println("Two files have different content. They differ at line " + lineNum);
            System.out.println("File1 has " + line1 + " and File2 has " + line2 + " at line " + lineNum);
        }

        reader1.close();
        reader2.close();

        return areEqual;
    }

    private static void copyFile(String source, String dest) throws IOException {
        File target = new File(dest);

        File parent = target.getParentFile();
        if (!parent.exists() && !parent.mkdirs()) {
            throw new IllegalStateException("Couldn't create dir: " + parent);
        }

        if (target.exists()) {
            Files.delete(new File(dest).toPath());
        }

        Files.copy(new File(source).toPath(), new File(dest).toPath());
    }

    private static void compareFiles(String inFolder, String outFolder, String folder) {
        File[] files = new File(inFolder + folder).listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                compareFiles(inFolder, outFolder, folder + "/" + file.getName());
            } else {
                System.out.println("File: " + folder + "/" + file.getName());
                try {
                    if (!fileCompare(inFolder + folder + "/" + file.getName(),
                            outFolder + folder + "/" + file.getName())) {
                        copyFile(inFolder + folder + "/" + file.getName(), outFolder + folder + "/" + file.getName());
                        System.out.println("File: " + folder + "/" + file.getName() + " updated");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static boolean deleteRecursive(File path) {
        boolean ret = true;
        if (path.isDirectory()) {
            for (File f : path.listFiles()) {
                ret = ret && deleteRecursive(f);
            }
        }
        return ret && path.delete();
    }

}

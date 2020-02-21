/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.autocode;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.List;

import com.zsmartsystems.zigbee.autocode.xml.ZigBeeXmlCluster;

/**
 *
 * @author Chris Jackson (zsmartsystems.com)
 *
 */
public class ZigBeeZclReadmeGenerator extends ZigBeeBaseClassGenerator {
    final String TABLE1 = "| ID | Cluster | Description |";
    final String TABLE2 = "|----|---------|-------------|";
    final String README_MD = "../README.md";

    ZigBeeZclReadmeGenerator(List<ZigBeeXmlCluster> clusters) {
        FileInputStream fstream;
        try {
            fstream = new FileInputStream(README_MD);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
            String mdContents = "";

            // Read File Line By Line
            while ((strLine = br.readLine()) != null) {
                if (strLine.equals(TABLE1)) {
                    while ((strLine = br.readLine()) != null) {
                        if (!strLine.startsWith("|")) {
                            break;
                        }
                    }
                    mdContents += writeTable(clusters);
                    mdContents += strLine + "\n";

                    continue;
                }

                mdContents += strLine + "\n";
            }

            // Close the input stream
            fstream.close();

            PrintStream fos;

            fos = new PrintStream(README_MD,"UTF-8");
            fos.print(mdContents);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String writeTable(List<ZigBeeXmlCluster> clusters) {
        String mdContents = "";
        mdContents += TABLE1 + "\n";
        mdContents += TABLE2 + "\n";
        for (ZigBeeXmlCluster cluster : clusters) {
            // Suppress GENERAL cluster as it's not really a cluster!
            if (cluster.name.equalsIgnoreCase("GENERAL")) {
                continue;
            }

            mdContents += "| " + String.format("%04X", cluster.code);
            mdContents += " | " + stringToConstant(cluster.name);

            if (cluster.description != null) {
                mdContents += " | " + cluster.description.get(0).description;
            } else {
                mdContents += " |";

            }
            mdContents += " |\n";
        }

        return mdContents;
    }
}

/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.autocode;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.zsmartsystems.zigbee.autocode.xml.ZigBeeXmlAttribute;
import com.zsmartsystems.zigbee.autocode.xml.ZigBeeXmlCluster;
import com.zsmartsystems.zigbee.autocode.xml.ZigBeeXmlCommand;
import com.zsmartsystems.zigbee.autocode.xml.ZigBeeXmlConstant;
import com.zsmartsystems.zigbee.autocode.xml.ZigBeeXmlField;
import com.zsmartsystems.zigbee.autocode.xml.ZigBeeXmlStructure;

/**
 *
 * @author Chris Jackson (zsmartsystems.com)
 *
 */
public class ZigBeeZclDependencyGenerator extends ZigBeeBaseClassGenerator {
    private Map<String, String> dependencies = new HashMap<>();
    private Set<String> zclTypes = new HashSet<>();

    ZigBeeZclDependencyGenerator(List<ZigBeeXmlCluster> clusters) {
        for (ZigBeeXmlCluster cluster : clusters) {
            try {
                generateZclClusterDependencies(cluster, packageRoot);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private void generateZclClusterDependencies(ZigBeeXmlCluster cluster, String packageRootPrefix) throws IOException {
        if (cluster.constants != null) {
            for (final ZigBeeXmlConstant constant : cluster.constants) {
                final String packageRoot = packageRootPrefix + packageZclProtocolCommand + "."
                        + stringToLowerCamelCase(cluster.name).replace("_", "").toLowerCase();

                final String className = constant.className;

                String lastClass = dependencies.put(className, packageRoot + "." + className);
                if (lastClass != null) {
                    throw new IllegalStateException(
                            "Duplicate class definition: " + lastClass + " with " + packageRoot + "." + className);
                }
            }
        }

        if (cluster.structures != null) {
            for (final ZigBeeXmlStructure structure : cluster.structures) {
                final String packageRoot = packageRootPrefix + packageZclProtocolCommand + "."
                        + stringToLowerCamelCase(cluster.name).replace("_", "").toLowerCase();

                final String className = structure.className;

                String lastClass = dependencies.put(className, packageRoot + "." + className);
                if (lastClass != null) {
                    throw new IllegalStateException(
                            "Duplicate class definition: " + lastClass + " with " + packageRoot + "." + className);
                }

                for (ZigBeeXmlField field : structure.fields) {
                    zclTypes.add(field.type);
                }
            }
        }

        if (cluster.commands != null) {
            for (final ZigBeeXmlCommand command : cluster.commands) {
                for (ZigBeeXmlField field : command.fields) {
                    zclTypes.add(field.type);
                }
            }
        }

        if (cluster.attributes != null) {
            for (ZigBeeXmlAttribute attribute : cluster.attributes) {
                zclTypes.add(attribute.type);
            }
        }

    }

    public Map<String, String> getDependencyMap() {
        return dependencies;
    }

    public Set<String> getZclTypeMap() {
        return zclTypes;
    }

}

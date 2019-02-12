/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.autocode;

import com.zsmartsystems.zigbee.autocode.xml.*;

import java.util.*;

/**
 *
 * @author Chris Jackson (zsmartsystems.com)
 *
 */
public class ZigBeeZclDependencyGenerator extends ZigBeeBaseClassGenerator {

    private Map<String, String> dependencies = new HashMap<>();
    private Set<String> zclTypes = new HashSet<>();

    ZigBeeZclDependencyGenerator(String sourceRootPath, String licenseText, List<ZigBeeXmlCluster> clusters) {
        super(sourceRootPath, licenseText);
        for (ZigBeeXmlCluster cluster : clusters) {
            generateZclClusterDependencies(cluster, packageRoot);
        }
    }

    private void generateZclClusterDependencies(ZigBeeXmlCluster cluster, String packageRootPrefix) {
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

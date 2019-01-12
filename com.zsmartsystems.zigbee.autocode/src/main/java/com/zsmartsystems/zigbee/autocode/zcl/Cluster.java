/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.autocode.zcl;

import java.util.List;
import java.util.TreeMap;

/**
 * Created by tlaukkan on 4/10/2016.
 */
public class Cluster {
    public int clusterId;
    public List<String> clusterDescription;
    public String clusterName;
    public String clusterType;
    public String nameUpperCamelCase;
    public String nameLowerCamelCase;
    public TreeMap<Integer, Command> received = new TreeMap<Integer, Command>();
    public TreeMap<Integer, Command> generated = new TreeMap<Integer, Command>();
    public TreeMap<Integer, Attribute> attributes = new TreeMap<Integer, Attribute>();
}

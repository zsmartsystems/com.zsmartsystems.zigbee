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

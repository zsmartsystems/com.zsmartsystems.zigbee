package com.zsmartsystems.zigbee.autocode.zcl;

import java.util.List;
import java.util.TreeMap;

/**
 * Created by tlaukkan on 4/10/2016.
 */
public class Context {

    public List<String> lines;

    public Profile profile;
    public Cluster cluster;
    public Command command;

    public boolean received;
    public boolean generated;
    public boolean attribute;

    public TreeMap<String, DataType> dataTypes = new TreeMap<String, DataType>();
    public TreeMap<Integer, Profile> profiles = new TreeMap<Integer, Profile>();

}

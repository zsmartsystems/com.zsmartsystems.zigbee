package com.zsmartsystems.zigbee.autocode.zcl;

import java.util.List;
import java.util.TreeMap;

/**
 * Created by tlaukkan on 4/10/2016.
 */
public class Command {
    public int commandId;
    public String commandLabel;
    public List<String> commandDescription;
    public String commandType;
    public String dataType;
    public String dataTypeClass;
    public String nameUpperCamelCase;
    public String nameLowerCamelCase;

    public TreeMap<Integer, Field> fields = new TreeMap<Integer, Field>();
}

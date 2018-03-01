package com.zsmartsystems.zigbee.dongle.xbee.autocode.xml;

import java.util.List;

/**
 *
 * @author Chris Jackson
 *
 */
public class Command {
    public String name;
    public String command;
    public Integer id;
    public String description;
    public List<ParameterGroup> command_parameters;
    public List<ParameterGroup> response_parameters;
    public boolean getter;
    public boolean setter;
}

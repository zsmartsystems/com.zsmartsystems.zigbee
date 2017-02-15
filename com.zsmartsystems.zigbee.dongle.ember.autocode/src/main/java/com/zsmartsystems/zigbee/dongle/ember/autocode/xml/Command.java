package com.zsmartsystems.zigbee.dongle.ember.autocode.xml;

import java.util.List;

/**
 *
 * @author Chris Jackson
 *
 */
public class Command {
    public String name;
    public Integer id;
    public String description;
    public List<Parameter> command_parameters;
    public List<Parameter> response_parameters;
}

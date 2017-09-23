package com.zsmartsystems.zigbee.dongle.telegesis.autocode.xml;

import java.util.List;

/**
 *
 * @author Chris Jackson
 *
 */
public class Command {
    public String name;
    public Integer cmdClass;
    public Integer id;
    public String description;
    public List<ParameterGroup> command_parameters;
    public List<ParameterGroup> response_parameters;
}

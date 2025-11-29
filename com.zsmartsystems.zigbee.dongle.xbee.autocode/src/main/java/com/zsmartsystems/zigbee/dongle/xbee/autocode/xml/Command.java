/**
 * Copyright (c) 2016-2025 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
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

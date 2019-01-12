/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.autocode.zcl;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author tlaukkan
 * @author Chris Jackson
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

    public String responseCommand;
    // public String responseRequest;
    // public String responseResponse;

    public Map<String, String> responseMatchers;

    public TreeMap<Integer, Field> fields = new TreeMap<Integer, Field>();
}

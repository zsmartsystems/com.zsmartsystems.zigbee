/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.autocode.zcl;

import java.util.List;
import java.util.Map;

public class Attribute {
    public String attributeLabel;
    public List<String> attributeDescription;
    public String attributeType;
    public String dataType;
    public String dataTypeClass;
    public String nameUpperCamelCase;
    public String nameLowerCamelCase;
    public String attributeAccess;
    public String attributeReporting;
    public String enumName;
    public Integer attributeId;
    public String attributeImplementation;
    public Map<Integer, String> valueMap;
}

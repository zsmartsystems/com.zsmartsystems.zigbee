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

/**
 * Created by tlaukkan on 4/10/2016.
 */
public class Field {
    public int fieldId;
    public String fieldLabel;
    public String fieldType;
    public String dataType;
    public String dataTypeClass;
    public String nameUpperCamelCase;
    public String nameLowerCamelCase;
    public String listSizer;
    public boolean completeOnZero;
    public String condition;
    public String conditionOperator;
    public List<String> description;
    public Map<Integer, String> valueMap;
}

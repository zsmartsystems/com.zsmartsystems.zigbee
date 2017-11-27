package com.zsmartsystems.zigbee.autocode.zcl;

import java.util.List;

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
}

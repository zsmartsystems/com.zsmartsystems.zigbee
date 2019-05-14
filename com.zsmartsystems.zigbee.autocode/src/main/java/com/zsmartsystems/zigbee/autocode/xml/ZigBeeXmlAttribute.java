/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.autocode.xml;

import java.math.BigInteger;
import java.util.List;

/**
 *
 * @author Chris Jackson (zsmartsystems.com)
 *
 */
public class ZigBeeXmlAttribute {
    public String name;
    public List<ZigBeeXmlDescription> description;
    public Integer code;
    public Integer arrayStart;
    public Integer arrayCount;
    public Integer arrayStep;
    public String type;
    public String implementationClass;
    public String side;
    public Boolean optional;
    public Boolean writable;
    public Boolean reportable;
    public BigInteger minimumValue;
    public BigInteger maximumValue;
    public BigInteger defaultValue;
}

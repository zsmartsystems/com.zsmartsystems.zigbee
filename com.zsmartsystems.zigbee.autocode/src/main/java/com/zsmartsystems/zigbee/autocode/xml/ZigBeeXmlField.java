/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.autocode.xml;

import java.util.List;

/**
 *
 * @author Chris Jackson (zsmartsystems.com)
 *
 */
public class ZigBeeXmlField {
    public String name;
    public List<ZigBeeXmlDescription> description;
    public String type;
    public String implementationClass;
    public Boolean array;
    public String sizer;
    public ZigBeeXmlCondition condition;
    public boolean completeOnZero;
}

/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.zstack.autocode.xml;

import java.util.List;

/**
 *
 * @author Chris Jackson
 *
 */
public class Enumeration {
    public String name;
    public String description;
    public String subsystem;
    public String format;
    public List<Value> values;
    public String data_type;
    public boolean fullyDefined;
}

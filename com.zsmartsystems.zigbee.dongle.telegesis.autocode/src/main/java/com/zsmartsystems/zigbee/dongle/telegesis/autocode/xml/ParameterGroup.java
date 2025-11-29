/**
 * Copyright (c) 2016-2025 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.telegesis.autocode.xml;

import java.util.List;

/**
 *
 * @author Chris Jackson
 *
 */
public class ParameterGroup {
    public String prompt;
    public Boolean multiple;
    public List<Parameter> parameters;
    public String name;
    public Boolean required;
    public Boolean complete;
}

/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.autocode.xml;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author Chris Jackson (zsmartsystems.com)
 *
 */
public class ZigBeeXmlCommand {
    public Integer code;
    public String source;
    public Optional<String> responseTo;
    public String name;
    public List<ZigBeeXmlDescription> description;
    public List<ZigBeeXmlField> fields;
    public ZigBeeXmlResponse response;
}

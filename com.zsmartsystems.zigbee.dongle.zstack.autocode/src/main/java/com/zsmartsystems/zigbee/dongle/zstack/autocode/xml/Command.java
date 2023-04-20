/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
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
public class Command {
    public String name;
    public String subsystem;
    public Integer id;
    public String description;
    public ZstackRequestType requestType;
    public List<Parameter> request_parameters;
    public List<Parameter> response_parameters;

    public enum ZstackRequestType {
        SYNC,
        ASYNC,
        ASYNCMD,
        BOOT
    }
}

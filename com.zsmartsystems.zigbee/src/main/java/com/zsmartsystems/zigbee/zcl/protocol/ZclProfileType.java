/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.protocol;

/**
 * Code is auto-generated. Modifications may be overwritten!
 */
public enum ZclProfileType {
    HOME_AUTOMATION(260, "Home Automation");

    private final int id;
    private final String label;

    ZclProfileType(final int id, final String label) {
        this.id = id;
        this.label = label;
    }

    public int getId() { return id; }
    public String getLabel() { return label; }
    public String toString() { return label; }

}

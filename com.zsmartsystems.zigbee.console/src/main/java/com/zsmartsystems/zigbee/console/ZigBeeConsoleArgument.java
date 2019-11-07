/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeConsoleArgument {
    private final ZigBeeConsoleArgumentType type;
    private final String constant;
    private final String description;
    private final boolean optional;
    private final List<ZigBeeConsoleArgument> next;

    private ZigBeeConsoleArgument lastChain;

    ZigBeeConsoleArgument(ZigBeeConsoleArgumentType type, String constant, String description, boolean optional) {
        this.type = type;
        this.constant = constant;
        this.description = description;
        this.optional = optional;
        this.next = new ArrayList<>();
    }

    /**
     * @return the type
     */
    public ZigBeeConsoleArgumentType getType() {
        return type;
    }

    /**
     * @return the constant
     */
    public String getConstant() {
        return constant;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return the optional
     */
    public boolean isOptional() {
        return optional;
    }

    /**
     * @return the next argument
     */
    public boolean hasSuboptions() {
        return next.size() > 1;
    }

    /**
     * @return the next argument
     */
    public List<ZigBeeConsoleArgument> getSuboptions() {
        return next;
    }

    /**
     *
     * @param name
     * @return
     */
    public ZigBeeConsoleArgument getSuboption(String name) {
        for (ZigBeeConsoleArgument suboption : next) {
            if (name.equalsIgnoreCase(suboption.getConstant())) {
                return suboption;
            }
        }

        return null;
    }

    /**
     * @return the next argument
     */
    public ZigBeeConsoleArgument getNext() {
        if (next.isEmpty()) {
            return null;
        }
        return next.get(0);
    }

    public void chain(ZigBeeConsoleArgument next) {
        if (lastChain == null) {
            lastChain = this;
        }
        lastChain.setNext(next);
        lastChain = next;
    }

    public void chainSuboption(ZigBeeConsoleArgument next) {
        if (lastChain == null) {
            lastChain = this;
        }
        lastChain.addSuboption(next);
    }

    private void setNext(ZigBeeConsoleArgument next) {
        this.next.clear();
        this.next.add(next);
    }

    private void addSuboption(ZigBeeConsoleArgument next) {
        this.next.add(next);
    }
}

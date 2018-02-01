/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.transport;

/**
 * Defines a the configuration for the ZigBee Concentrator
 *
 * @author Chris Jackson
 *
 */
public class ConcentratorConfig {

    /**
     * Defines the {@link ConcentratorType}
     */
    private ConcentratorType type;

    /**
     * The minimum time between MTORR transmissions
     */
    private int refreshMinimum;

    /**
     * The maximum time between MTORR transmissions
     */
    private int refreshMaximum;

    /**
     * The maximum number of hops the MTORR will be sent to
     */
    private int maxHops;

    /**
     * Maximum number of errors that will trigger a re-broadcast of the MTORR
     */
    private int maxFailures;

    /**
     * @return the type
     */
    public ConcentratorType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(ConcentratorType type) {
        this.type = type;
    }

    /**
     * @return the refreshMinimum
     */
    public int getRefreshMinimum() {
        return refreshMinimum;
    }

    /**
     * @param refreshMinimum the refreshMinimum to set
     */
    public void setRefreshMinimum(int refreshMinimum) {
        this.refreshMinimum = refreshMinimum;
    }

    /**
     * @return the refreshMaximum
     */
    public int getRefreshMaximum() {
        return refreshMaximum;
    }

    /**
     * @param refreshMaximum the refreshMaximum to set
     */
    public void setRefreshMaximum(int refreshMaximum) {
        this.refreshMaximum = refreshMaximum;
    }

    /**
     * @return the maxHops
     */
    public int getMaxHops() {
        return maxHops;
    }

    /**
     * @param maxHops the maxHops to set
     */
    public void setMaxHops(int maxHops) {
        this.maxHops = maxHops;
    }

    /**
     * @return the maxFailures
     */
    public int getMaxFailures() {
        return maxFailures;
    }

    /**
     * @param maxFailures the maxFailures to set
     */
    public void setMaxFailures(int maxFailures) {
        this.maxFailures = maxFailures;
    }

}

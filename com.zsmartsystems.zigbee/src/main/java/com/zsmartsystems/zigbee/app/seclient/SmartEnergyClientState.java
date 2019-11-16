/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.app.seclient;

/**
 * Defines the state machine states for the Smart Energy client
 *
 * @author Chris Jackson
 *
 */
public enum SmartEnergyClientState {
    IDLE,
    DISCOVER_TRUST_CENTRE,
    DISCOVER_KEY_ESTABLISHMENT_CLUSTER,
    PERFORM_KEY_ESTABLISHMENT,
    DISCOVER_METERING_SERVERS,
    DISCOVER_KEEP_ALIVE,
    DISCOVER_KEEP_ALIVE_TIMEOUT,
    KEEP_ALIVE,
    FATAL
}

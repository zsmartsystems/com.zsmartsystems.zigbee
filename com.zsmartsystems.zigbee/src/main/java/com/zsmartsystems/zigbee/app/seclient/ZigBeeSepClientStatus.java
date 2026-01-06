/**
 * Copyright (c) 2016-2026 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.app.seclient;

/**
 * Defines the status of the Smart Energy Profile network
 *
 * @author Chris Jackson
 *
 */
public enum ZigBeeSepClientStatus {
    /**
     * The Smart Energy client is initialising and performing any key exchange required.
     */
    INITIALIZING,
    /**
     * The Smart Energy client is connected to the server and operating normally.
     */
    CONNECTED,
    /**
     * The Smart Energy client is disconnected from the server.
     */
    DISCONNECTED,
    /**
     * The Smart Energy client is disconnected from the server and the application should leave the network.
     */
    FATAL_ERROR
}

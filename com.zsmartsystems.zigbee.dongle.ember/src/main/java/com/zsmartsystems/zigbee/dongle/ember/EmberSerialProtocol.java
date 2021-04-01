/**
 * Copyright (c) 2016-2021 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember;

/**
 * Definition of the low level communication protocol types used to communicate with the Ember NCP.
 *
 * @author Chris Jackson
 *
 */
public enum EmberSerialProtocol {
    /**
     * No protocol - used for testing
     */
    NONE,
    /**
     * Serial Peripheral Interface
     */
    SPI,
    /**
     * Asynchronous Serial Handler V2
     */
    ASH2
}

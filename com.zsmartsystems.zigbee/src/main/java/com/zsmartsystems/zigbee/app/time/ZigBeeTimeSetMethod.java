/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.app.time;

/**
 * Enumeration defining if the time has been synchronised via the client or server
 *
 * @author Chris Jackson
 *
 */
public enum ZigBeeTimeSetMethod {
    NONE,

    CLIENT,

    SERVER;
}

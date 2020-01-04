/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.security;

/**
 * Enumeration of crypto suites supported by ZigBee
 *
 * @author Chris Jackson
 *
 */
public enum ZigBeeCryptoSuites {
    /**
     * ECC Curve 163k1.
     * Used in Smart Energy V1.0
     */
    ECC_163K1,

    /**
     * ECC Curve 283k1.
     * Used in Smart Energy V1.2
     */
    ECC_283K1
}

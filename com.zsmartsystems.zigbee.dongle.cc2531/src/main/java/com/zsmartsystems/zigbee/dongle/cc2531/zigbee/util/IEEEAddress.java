/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
/*
   Copyright 2008-2013 CNR-ISTI, http://isti.cnr.it
   Institute of Information Science and Technologies
   of the Italian National Research Council


   See the NOTICE file distributed with this work for additional
   information regarding copyright ownership

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/

package com.zsmartsystems.zigbee.dongle.cc2531.zigbee.util;

import java.util.StringTokenizer;

/**
 * @author <a href="mailto:stefano.lenzi@isti.cnr.it">Stefano "Kismet" Lenzi</a>
 * @author <a href="mailto:francesco.furfari@isti.cnr.it">Francesco Furfari</a>
 * @author Chris Jackson
 */
public class IEEEAddress {

    private IEEEAddress() {
    }

    public static final long fromColonNotation(String ieee) {
        StringTokenizer scanner = new StringTokenizer(ieee, ":");
        long result = 0;
        while (scanner.hasMoreTokens()) {
            int octect = Integer.parseInt(scanner.nextToken(), 16);
            result = (result << 8) + (octect & 0xFF);
        }
        return result;
    }

    public static final String toString(long ieee) {
        return toColonNotation(ieee);
    }

    private static final String toColonNotation(long ieee) {
        String padding = "0000000000000000";
        String hex = Long.toHexString(ieee);
        if (hex.length() < 16) {
            hex = padding.substring(0, 16 - hex.length()) + hex;
        }
        hex = hex.toUpperCase();
        String result = hex.substring(0, 2);
        for (int i = 1; i <= 7; i++) {
            hex = hex.substring(2);
            result = result + ":" + hex.substring(0, 2);
        }
        return result;
    }

}

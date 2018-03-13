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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="mailto:stefano.lenzi@isti.cnr.it">Stefano "Kismet" Lenzi</a>
 */
public class ThreadUtils {

    private final static Logger logger = LoggerFactory.getLogger(ThreadUtils.class);

    private ThreadUtils() {
    }

    /**
     * Wait for x ms even if interrupt are sent to the thread waiting
     *
     * @param time the number of ms to wait
     */
    public static final void waitNonPreemptive(long time) {
        final long end = System.currentTimeMillis() + time;
        do {
            try {
                final long delta = Math.max(end - System.currentTimeMillis(), 0);
                logger.trace("{} waiting for {}ms", Thread.currentThread(), delta);
                Thread.sleep(delta);
            } catch (InterruptedException ignored) {
            }
        } while (end > System.currentTimeMillis());
    }

}

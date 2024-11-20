/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.zstack.api;

/**
 * Base class for all ZStack commands
 *
 * @author Chris Jackson
 *
 */
public abstract class ZstackCommand {
    /**
     * Definitions of API subsystems
     */
    protected static int ZSTACK_RPC = 0x00;
    protected static int ZSTACK_SYS = 0x01;
    protected static int ZSTACK_MAC = 0x02;
    protected static int ZSTACK_AF = 0x04;
    protected static int ZSTACK_ZDO = 0x05;
    protected static int ZSTACK_SAPI = 0x06;
    protected static int ZSTACK_UTIL = 0x07;
    protected static int ZSTACK_APP = 0x09;
    protected static int ZSTACK_SBL = 0x0D;
    protected static int ZSTACK_APP_CNF = 0x0F;

    /**
     * Flag denoting if this command/request is done synchronously
     */
    protected boolean synchronousCommand = false;

    /**
     * Returns true if this request/response is performed synchronously
     *
     * @return true if the request requires asynchronous response
     */
    public boolean isSynchronous() {
        return synchronousCommand;
    }
}

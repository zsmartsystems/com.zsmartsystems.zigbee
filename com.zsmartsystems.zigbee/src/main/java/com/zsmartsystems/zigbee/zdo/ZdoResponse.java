/**
 * Copyright (c) 2016-2021 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zdo;

/**
 * Abstract class for ZDO response commands.
 *
 * @author Chris Jackson
 */
public abstract class ZdoResponse extends ZdoCommand {
    /**
     * The response status.
     */
    protected ZdoStatus status;

    /**
     * Gets the response status
     *
     * @return the response status
     */
    public ZdoStatus getStatus() {
        return status;
    }

    /**
     * Sets the response status
     *
     * @param status the response status as {@link int}
     */
    public void setStatus(ZdoStatus status) {
        this.status = status;
    }
}

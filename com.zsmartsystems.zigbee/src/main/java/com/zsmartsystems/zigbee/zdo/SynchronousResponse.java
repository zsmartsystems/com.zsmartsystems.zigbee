/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zdo;

/**
 * Synchronous response.
 *
 * @author Tommi S.E. Laukkanen
 */
public class SynchronousResponse {
    /**
     * The response status.
     */
    private int status;
    /**
     * The response type.
     */
    private String type;

    public SynchronousResponse() {
    }

    public SynchronousResponse(int status, String type) {
        this.status = status;
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Synchronous Response: status=" + status + ", type='" + type + '\'';
    }
}

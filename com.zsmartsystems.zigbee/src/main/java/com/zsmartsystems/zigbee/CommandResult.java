/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee;

import com.zsmartsystems.zigbee.zcl.ZclStatus;
import com.zsmartsystems.zigbee.zcl.clusters.general.DefaultResponse;
import com.zsmartsystems.zigbee.zdo.ZdoResponse;

/**
 * Value class containing command response.
 *
 * @author Tommi S.E. Laukkanen
 * @author Chris Jackson
 */
public class CommandResult {

    /**
     * The response command.
     */
    private final ZigBeeCommand response;
    /**
     * The message.
     */
    private final String message;

    /**
     * Constructor which sets the received response command or null if timeout occurs..
     *
     * @param response the response command.
     */
    public CommandResult(final ZigBeeCommand response) {
        this.response = response;
        this.message = null;
    }

    /**
     * Constructor for message situations. Setting a message indicates a failure.
     *
     * @param message the message
     */
    public CommandResult(final String message) {
        this.response = null;
        this.message = message;
    }

    /**
     * Constructor for timeout situations.
     */
    public CommandResult() {
        this.response = null;
        this.message = null;
    }

    /**
     * Checks whether command execution was successful.
     *
     * @return TRUE if command execution was successful.
     */
    public boolean isSuccess() {
        return !(isTimeout() || isError());
    }

    /**
     * Checks whether command timed out.
     *
     * @return TRUE if timeout occurred
     */
    public boolean isTimeout() {
        return response == null && message == null;
    }

    /**
     * Checks if message status code was received in default response.
     *
     * @return the message status code
     */
    public boolean isError() {
        if (hasStatusCode()) {
            return getStatusCode() != 0;
        } else if (message != null) {
            return true;
        } else {
            return response == null;
        }
    }

    /**
     * Check if default response was received.
     *
     * @return TRUE if default response was received
     */
    private boolean hasStatusCode() {
        if (response != null) {
            return response instanceof DefaultResponse || response instanceof ZdoResponse;
        } else {
            return false;
        }
    }

    /**
     * Gets status code received in default response.
     *
     * @return the status code
     */
    public Integer getStatusCode() {
        if (hasStatusCode()) {
            if (response instanceof DefaultResponse) {
                return ((DefaultResponse) response).getStatusCode().getId();
            } else {
                return ((ZdoResponse) response).getStatus().getId();
            }
        } else {
            return 0xffff;
        }
    }

    /**
     * Gets the received response.
     *
     * @return the received response {@link ZigBeeCommand}
     */
    public <C extends ZigBeeCommand> C getResponse() {
        return (C) response;
    }

    /**
     * Gets error or timeout message.
     *
     * @return the message
     */
    public String getMessage() {
        if (isTimeout()) {
            return "Timeout.";
        }
        if (hasStatusCode()) {
            return ZclStatus.getStatus((byte) (int) getStatusCode()).getDescription();
        } else {
            return message;
        }
    }

    @Override
    public String toString() {
        if (isSuccess()) {
            return "success";
        } else if (isTimeout()) {
            return "timeout";
        } else {
            final ZclStatus status = ZclStatus.getStatus((byte) getStatusCode().intValue());
            return "message: " + status.name() + "(0x" + Integer.toHexString(status.getId()) + ", "
                    + status.getDescription() + ")";
        }
    }
}

/**
 * Copyright (c) 2016-2025 by the respective copyright holders.
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
    private final ZigBeeStatus commandSentSuccessfully;

    /**
     * Constructor which sets the received response command and if the command was sent successfully.
     *
     * @param commandSentSuccessfully if the command was sent successfully
     * @param response the response command
     */

    public CommandResult(final ZigBeeStatus commandSentSuccessfully, final ZigBeeCommand response) {
        this.commandSentSuccessfully = commandSentSuccessfully;
        this.response = response;
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
        return commandSentSuccessfully != ZigBeeStatus.SUCCESS;
    }

    /**
     * Checks if command was successful, or resulted in an error.
     * <p>
     * Note that as the status code is not present in all ZCL frames or responses, this method will only check the
     * status code on ZDO responses. The application should check the status code of ZCL responses as it knows the
     * context.
     *
     * @return true if the command resulted in an error
     */
    public boolean isError() {
        if (commandSentSuccessfully == ZigBeeStatus.SUCCESS) {
            if (hasStatusCode()) {
                return getStatusCode() != 0;
            } else {
                return false;
            }
        } else {
            return true;
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
     * @return the status code as {@link Integer}
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
    public <ZC extends ZigBeeCommand> ZC getResponse() {
        @SuppressWarnings("unchecked")
        ZC typedResponse = (ZC) response;

        return typedResponse;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(60);
        builder.append("CommandResult [");
        if (isSuccess()) {
            builder.append("SUCCESS, ");
            builder.append(response);
        } else if (isTimeout()) {
            builder.append("TIMEOUT");
        } else {
            final ZclStatus status = ZclStatus.getStatus((byte) getStatusCode().intValue());
            builder.append("ERROR (");
            builder.append(status.name());
            builder.append(String.format(",0x%02X), ", status.getId()));
            builder.append(response);
        }
        builder.append(']');
        return builder.toString();
    }
}

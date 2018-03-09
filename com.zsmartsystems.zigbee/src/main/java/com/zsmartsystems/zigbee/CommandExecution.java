/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee;

import java.util.concurrent.Future;

/**
 * Command execution value object.
 * 
 * @author Tommi S.E. Laukkanen
 */
public class CommandExecution {
    /**
     * The command start time.
     */
    private long startTime;
    /**
     * The command.
     */
    private ZigBeeCommand command;
    /**
     * The future.
     */
    private Future<CommandResult> future;
    /**
     * The command response listener.
     */
    private ZigBeeCommandListener commandListener;

    /**
     * Constructor which sets future and command listener.
     *
     * @param startTime the start time
     * @param command the command
     * @param future the future
     */
    public CommandExecution(long startTime, ZigBeeCommand command, Future<CommandResult> future) {
        this.startTime = startTime;
        this.command = command;
        this.future = future;
    }

    /**
     * The start time.
     *
     * @return
     */
    public long getStartTime() {
        return startTime;
    }

    /**
     * The command.
     *
     * @return
     */
    public ZigBeeCommand getCommand() {
        return command;
    }

    /**
     * Gets future.
     *
     * @return the future
     */
    public Future<CommandResult> getFuture() {
        return future;
    }

    /**
     * Sets command listener.
     *
     * @param commandListener the command listener
     */
    public void setCommandListener(ZigBeeCommandListener commandListener) {
        this.commandListener = commandListener;
    }

    /**
     * Gets command listener.
     *
     * @return the command listener
     */
    public ZigBeeCommandListener getCommandListener() {
        return commandListener;
    }
}

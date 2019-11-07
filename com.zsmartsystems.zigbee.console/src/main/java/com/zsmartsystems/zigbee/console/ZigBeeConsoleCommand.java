/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console;

import java.io.PrintStream;
import java.util.concurrent.ExecutionException;

import com.zsmartsystems.zigbee.ZigBeeNetworkManager;

/**
 * Interface defining the console command
 *
 * @author Chris Jackson
 *
 */
public interface ZigBeeConsoleCommand {
    /**
     * Get command string.
     *
     * @return the command string
     */
    public String getCommand();

    /**
     * Get command description.
     *
     * @return the command description
     */
    public String getDescription();

    /**
     * Get command syntax.
     *
     * @return the command syntax
     */
    public String getSyntax();

    /**
     * Gets the list of arguments that are applicable for this command. This is defined in a prescriptive way such that
     * it can be machine interpreted.
     *
     * @return the {@link ZigBeeConsoleArgument} defining the possible arguments for this command
     */
    public ZigBeeConsoleArgument getArguments();

    /**
     * Get detailed command help.
     *
     * @return the command help
     */
    public String getHelp();

    /**
     * Processes console command.
     *
     * @param networkManager the {@link ZigBeeNetworkManager}
     * @param args the command arguments. arg[0] is the command
     * @param out the output PrintStream
     * @return true if the command executed successfully
     * @throws IllegalArgumentException if any commands are incorrectly formatted, or the incorrect number of commands
     *             are provided
     * @throws IllegalStateException if the network is not in a state that allows the command to be executed
     * @throws InterruptedException if the command was interrupted
     * @throws ExecutionException if the command failed
     */
    public void process(final ZigBeeNetworkManager networkManager, final String[] args, PrintStream out)
            throws IllegalArgumentException, IllegalStateException, ExecutionException, InterruptedException;
}

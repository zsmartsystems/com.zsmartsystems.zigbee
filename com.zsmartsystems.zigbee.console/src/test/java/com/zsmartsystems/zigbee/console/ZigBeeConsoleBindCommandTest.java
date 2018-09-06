/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console;

import org.junit.Test;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeConsoleBindCommandTest {
    @Test
    public void getSyntax() {
        ZigBeeConsoleBindCommand command = new ZigBeeConsoleBindCommand();

        System.out.println(command.getSyntax());
    }
}

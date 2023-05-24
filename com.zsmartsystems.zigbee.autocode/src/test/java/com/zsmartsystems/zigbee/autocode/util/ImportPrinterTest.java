/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.autocode.util;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

/**
*
* @author Victor Toni
*
*/
public class ImportPrinterTest {

    private static final List<String> IMPORTS = Arrays.asList(
            "java.util.HashMap",
            "java.util.HashSet",
            "java.util.List",
            "javax.annotation.Generated",
            "org.slf4j.Logger",
            "org.slf4j.LoggerFactory",
            "com.zsmartsystems.zigbee.console",
            "com.zsmartsystems.zigbee.zcl",
            "com.zsmartsystems.zigbee.zdo",
            "com.zsmartsystems.zigbee.zdo.field"
    );

    private static final String EXPECTED =
            "import java.util.HashMap;" + System.lineSeparator() +
            "import java.util.HashSet;" + System.lineSeparator() +
            "import java.util.List;" + System.lineSeparator() +
            System.lineSeparator() +
            "import javax.annotation.Generated;" + System.lineSeparator() +
            System.lineSeparator() +
            "import org.slf4j.Logger;" + System.lineSeparator() +
            "import org.slf4j.LoggerFactory;" + System.lineSeparator() +
            System.lineSeparator() +
            "import com.zsmartsystems.zigbee.console;" + System.lineSeparator() +
            "import com.zsmartsystems.zigbee.zcl;" + System.lineSeparator() +
            "import com.zsmartsystems.zigbee.zdo;" + System.lineSeparator() +
            "import com.zsmartsystems.zigbee.zdo.field;" + System.lineSeparator()
            ;

    @Test
    public void testOutput() throws UnsupportedEncodingException {
        ImportSet importSet = new ImportSet();

        List<String> importList = new ArrayList<>(IMPORTS);
        Collections.shuffle(importList);

        importList.forEach(importSet::add);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream, true, "UTF-8");

        ImportPrinter.outputImports(importSet, printStream);
        String output = outputStream.toString();

        assertEquals(EXPECTED, output);
    }

}

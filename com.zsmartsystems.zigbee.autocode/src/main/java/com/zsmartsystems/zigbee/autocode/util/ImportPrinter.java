/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.autocode.util;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles the output of given {@link ImportSet}s and supports grouping of according to the groups specified by the
 * {@link ImportSet}.
 *
 * @author Victor Toni
 *
 */
public class ImportPrinter {

    public static void outputImports(ImportSet importSet, PrintStream out) {
        List<String> importList = new ArrayList<>();
        importSet.stream().forEach(importList::add);

        String[] groupPrefixes = importSet.groupPrefixes().collect(Collectors.toList()).toArray(new String[0]);
        groupAndOutputImports(importList, out, groupPrefixes);
    }

    public static void groupAndOutputImports(Collection<String> completeImportList, PrintStream out, String ...groupPrefixes) {
        List<String> remainingImports = new ArrayList<>(completeImportList);

        List<List<String>> importListGroups = new ArrayList<>();
        for (String groupPrefix : groupPrefixes) {
            List<String> importListGroup = remainingImports
                    .stream()
                    .filter(importClass -> importClass.startsWith(groupPrefix))
                    .collect(Collectors.toList());

            if (!importListGroup.isEmpty()) {
                importListGroups.add(importListGroup);
                remainingImports.removeAll(importListGroup);
            }
        }

        importListGroups.add(remainingImports);

        boolean first = true;
        for (List<String> importListGroup :importListGroups) {
            if (!importListGroup.isEmpty()) {
                if (first) {
                    first = false;
                } else {
                    out.println();
                }

                outputImportGroup(importListGroup, out);
            }
        }
    }

    public static void outputImportGroup(Collection<String> importListGroup, PrintStream out) {
        for (final String importClass : importListGroup) {
            out.println("import " + importClass + ";");
        }
    }

}

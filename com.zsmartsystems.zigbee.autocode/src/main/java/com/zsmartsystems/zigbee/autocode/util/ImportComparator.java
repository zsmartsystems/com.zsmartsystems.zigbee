/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.autocode.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

/**
 * Class implementing the {@link Comparator} interface to allow sorting of imports as groups.
 *
 * @author Victor Toni
 *
 */
public class ImportComparator implements Comparator<String> {
    private final List<String> groupPrefixes = new ArrayList<>();

    public ImportComparator(String ...groupPrefixes) {
        this(Arrays.asList(groupPrefixes));
   }

    public ImportComparator(Collection<String> groupPrefixes) {
        this.groupPrefixes.addAll(groupPrefixes);
   }

    @Override
    public int compare(String importClass1, String importClass2) {
        // check all known groupPrefixes first
        for (String groupPrefix : groupPrefixes) {
            if (importClass1.startsWith(groupPrefix)) {
                if (importClass2.startsWith(groupPrefix)) {
                    // both of same type
                    return importClass1.compareTo(importClass2);
                }

                // importClass1 with groupPrefix goes first
                // importClass2 does not have groupPrefix
                return -1;
            }

            // importClass1 does not have groupPrefix
            // importClass2 with groupPrefix goes first
            if (importClass2.startsWith(groupPrefix)) {
                return 1;
            }
        }

        // no groupPrefix matched, fall back to regular string comparison
        return importClass1.compareTo(importClass2);
    }

}

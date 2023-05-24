/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.autocode.util;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;

/**
 * Class allowing creating set of unique imports. Also allows to provide grouping information for sorting and output.
 *
 * @author Victor Toni
 *
 */
public class ImportSet {
    private final Set<String> groupPrefixes = new LinkedHashSet<>();

    private final Set<String> importClasses;

    public ImportSet() {
        this(
            "java.",
            "javax.",
            "org.slf4j."
        );
    }

    public ImportSet(String ...groupPrefixes) {
        for (String groupPrefix : groupPrefixes) {
            String trimmedGroupPrefix = groupPrefix.trim();
            if (0 < trimmedGroupPrefix.length()) {
                this.groupPrefixes.add(trimmedGroupPrefix);
            }
        }

        Comparator<String> importComparator = new ImportComparator(this.groupPrefixes);
        importClasses = new TreeSet<>(importComparator);
    }

    public boolean add(String importClass) {
        return importClasses.add(importClass);
    }

    public boolean contains(String importClass) {
        return importClasses.contains(importClass);
    }

    public int size() {
        return importClasses.size();
    }

    public boolean isEmpty() {
        return importClasses.isEmpty();
    }

    public void clear() {
        importClasses.clear();
    }

    public Stream<String> stream() {
        return importClasses.stream();
    }

    public Stream<String> groupPrefixes() {
        return this.groupPrefixes.stream();
    }

}

/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.field;

import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

/**
 *
 * @author Chris Jackson
 *
 */
public class AttributeInformationTest {
    @Test
    public void compareTo() {
        Set<AttributeInformation> infoSet = new HashSet<AttributeInformation>();
        AttributeInformation attr;

        attr = new AttributeInformation();
        attr.setIdentifier(10);
        infoSet.add(attr);
        attr = new AttributeInformation();
        attr.setIdentifier(9);
        infoSet.add(attr);
        attr = new AttributeInformation();
        attr.setIdentifier(6);
        infoSet.add(attr);

        assertEquals(3, infoSet.size());
        assertEquals(10, Collections.max(infoSet).getIdentifier());
    }
}

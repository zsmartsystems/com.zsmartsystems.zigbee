/**
 * Copyright (c) 2016-2021 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.transaction;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeTransactionProfileTest {
    @Test
    public void test() {
        ZigBeeTransactionProfile profile = new ZigBeeTransactionProfile();

        profile.setInterTransactionDelay(234);
        assertEquals(234, profile.getInterTransactionDelay());

        profile.setMaxOutstandingTransactions(5);
        assertEquals(5, profile.getMaxOutstandingTransactions());

        profile.setMaxRetries(11);
        assertEquals(11, profile.getMaxRetries());

        System.out.println(profile.toString());

        profile = new ZigBeeTransactionProfile(1, 2, 3);

        assertEquals(1, profile.getMaxRetries());
        assertEquals(2, profile.getMaxOutstandingTransactions());
        assertEquals(3, profile.getInterTransactionDelay());

        System.out.println(profile.toString());
    }
}

/**
 * Copyright (c) 2016-2024 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember;

import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import com.zsmartsystems.zigbee.dongle.ember.internal.EzspProtocolHandler;
import com.zsmartsystems.zigbee.dongle.ember.internal.transaction.EzspTransaction;
import com.zsmartsystems.zigbee.security.ZigBeeCryptoSuites;

/**
 *
 * @author Chris Jackson
 *
 */
public class EmberCbkeExchangeTest {

    @Test
    public void setClientServer() {
        ZigBeeDongleEzsp dongle = Mockito.mock(ZigBeeDongleEzsp.class);
        EzspProtocolHandler protocolHandler = Mockito.mock(EzspProtocolHandler.class);
        Mockito.when(dongle.getProtocolHandler()).thenReturn(protocolHandler);
        Mockito.when(protocolHandler.sendEzspTransaction(ArgumentMatchers.any(EzspTransaction.class)))
                .thenReturn(Mockito.mock(EzspTransaction.class));
        EmberCbkeProvider cbkeProvider = new EmberCbkeProvider(dongle);
        EmberCbkeExchange cbkeExchange = new EmberCbkeExchange(cbkeProvider, false);

        assertNull(cbkeExchange.getCertificate());
        cbkeExchange.setCryptoSuite(ZigBeeCryptoSuites.ECC_163K1);
    }

}

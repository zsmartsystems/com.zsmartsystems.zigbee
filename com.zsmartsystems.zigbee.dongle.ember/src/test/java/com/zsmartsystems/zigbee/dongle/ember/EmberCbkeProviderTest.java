/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import com.zsmartsystems.zigbee.dongle.ember.internal.EzspProtocolHandler;
import com.zsmartsystems.zigbee.dongle.ember.internal.transaction.EzspTransaction;
import com.zsmartsystems.zigbee.security.ZigBeeCbkeExchange;

/**
 *
 * @author Chris Jackson
 *
 */
public class EmberCbkeProviderTest {

    @Test
    public void setClientServer() {
        ZigBeeDongleEzsp dongle = Mockito.mock(ZigBeeDongleEzsp.class);
        EzspProtocolHandler protocolHandler = Mockito.mock(EzspProtocolHandler.class);
        Mockito.when(dongle.getProtocolHandler()).thenReturn(protocolHandler);
        Mockito.when(protocolHandler.sendEzspTransaction(ArgumentMatchers.any(EzspTransaction.class)))
                .thenReturn(Mockito.mock(EzspTransaction.class));
        EmberCbkeProvider cbkeProvider = new EmberCbkeProvider(dongle);

        ZigBeeCbkeExchange cbkeExchange = cbkeProvider.getCbkeKeyExchangeInitiator();
        assertNotNull(cbkeExchange);
        assertNull(cbkeProvider.getCbkeKeyExchangeInitiator());
        assertNull(cbkeProvider.getCbkeKeyExchangeResponder());

        assertEquals(null, cbkeProvider.clearTemporaryDataMaybeStoreLinkKey283k1(false));
        assertNotNull(cbkeProvider.getCbkeKeyExchangeResponder());
    }

}

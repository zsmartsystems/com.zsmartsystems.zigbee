/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.app.seclient;

import static org.junit.Assert.assertFalse;

import org.junit.Test;
import org.mockito.Mockito;

import com.zsmartsystems.zigbee.security.ZigBeeCbkeProvider;
import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.clusters.ZclKeyEstablishmentCluster;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZclKeyEstablishmentClientTest {

    @Test
    public void test() {
        System.out.println("--- " + Thread.currentThread().getStackTrace()[1].getMethodName());
        SmartEnergyClient seClient = Mockito.mock(SmartEnergyClient.class);
        ZclKeyEstablishmentCluster keCluster = Mockito.mock(ZclKeyEstablishmentCluster.class);

        ZclKeyEstablishmentClient keClient = new ZclKeyEstablishmentClient(seClient, keCluster);

        Mockito.verify(keCluster, Mockito.times(1)).addCommandListener(keClient);
        keClient.setCbkeProvider(Mockito.mock(ZigBeeCbkeProvider.class));

        assertFalse(keClient.commandReceived(Mockito.mock(ZclCommand.class)));

        keClient.stop();

        keClient.shutdown();
        Mockito.verify(keCluster, Mockito.times(1)).removeCommandListener(keClient);
    }
}

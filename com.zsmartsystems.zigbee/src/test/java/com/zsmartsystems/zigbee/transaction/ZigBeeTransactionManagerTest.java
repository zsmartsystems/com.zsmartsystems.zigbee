/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.transaction;

import static org.junit.Assert.assertNotNull;

import java.util.concurrent.Future;

import org.junit.Test;
import org.mockito.Mockito;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.ZigBeeCommand;
import com.zsmartsystems.zigbee.ZigBeeEndpointAddress;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNode;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportProgressState;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeTransactionManagerTest {
    private static int TIMEOUT = 5000;

    @Test
    public void sendTransaction() {
        ZigBeeNetworkManager networkManager = Mockito.mock(ZigBeeNetworkManager.class);
        ZigBeeCommand command = Mockito.mock(ZigBeeCommand.class);
        Mockito.when(command.getDestinationAddress()).thenReturn(new ZigBeeEndpointAddress(123));
        ZigBeeTransactionMatcher responseMatcher = Mockito.mock(ZigBeeTransactionMatcher.class);
        ZigBeeNode node = Mockito.mock(ZigBeeNode.class);
        Mockito.when(node.getIeeeAddress()).thenReturn(new IeeeAddress("1234567890ABCDEF"));

        Mockito.when(networkManager.getNode(123)).thenReturn(node);

        ZigBeeTransactionManager transactionManager = new ZigBeeTransactionManager(networkManager);

        transactionManager.sendTransaction(command);

        Future<CommandResult> cmdResult = transactionManager.sendTransaction(command, responseMatcher);
        assertNotNull(cmdResult);

        ZigBeeCommand unknownCommand = Mockito.mock(ZigBeeCommand.class);
        Mockito.when(unknownCommand.getDestinationAddress()).thenReturn(new ZigBeeEndpointAddress(456));
        cmdResult = transactionManager.sendTransaction(unknownCommand, responseMatcher);
        // assertNull(cmdResult);
    }

    @Test
    public void receive() {
        ZigBeeNetworkManager networkManager = Mockito.mock(ZigBeeNetworkManager.class);
        ZigBeeTransactionManager transactionManager = new ZigBeeTransactionManager(networkManager);

        ZigBeeTransaction transactionListener = Mockito.mock(ZigBeeTransaction.class);

        transactionManager.addTransactionListener(transactionListener);

        ZigBeeCommand command = Mockito.mock(ZigBeeCommand.class);

        transactionManager.receive(command);
        Mockito.verify(transactionListener, Mockito.timeout(TIMEOUT)).commandReceived(command);

        transactionManager.receiveCommandStatus(123, ZigBeeTransportProgressState.RX_ACK);
        Mockito.verify(transactionListener, Mockito.timeout(TIMEOUT))
                .commandStatusReceived(ZigBeeTransportProgressState.RX_ACK, 123);

        transactionManager.removeTransactionListener(null);
        transactionManager.removeTransactionListener(transactionListener);
        transactionManager.removeTransactionListener(transactionListener);

        // Send another command and make sure the commandReceived method is not called again
        transactionManager.receive(command);
        Mockito.verify(transactionListener, Mockito.times(1)).commandReceived(command);
    }

}

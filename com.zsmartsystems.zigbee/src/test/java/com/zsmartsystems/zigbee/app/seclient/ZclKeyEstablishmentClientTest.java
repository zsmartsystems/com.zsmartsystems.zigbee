/**
 * Copyright (c) 2016-2021 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.app.seclient;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.TestUtilities;
import com.zsmartsystems.zigbee.ZigBeeStatus;
import com.zsmartsystems.zigbee.app.seclient.ZclKeyEstablishmentClient.KeyEstablishmentState;
import com.zsmartsystems.zigbee.security.CerticomCbkeCertificate;
import com.zsmartsystems.zigbee.security.ZigBeeCbkeCertificate;
import com.zsmartsystems.zigbee.security.ZigBeeCbkeExchange;
import com.zsmartsystems.zigbee.security.ZigBeeCbkeProvider;
import com.zsmartsystems.zigbee.security.ZigBeeCryptoSuites;
import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.clusters.ZclKeyEstablishmentCluster;
import com.zsmartsystems.zigbee.zcl.clusters.keyestablishment.EphemeralDataRequestCommand;
import com.zsmartsystems.zigbee.zcl.clusters.keyestablishment.InitiateKeyEstablishmentResponse;
import com.zsmartsystems.zigbee.zcl.clusters.keyestablishment.KeyEstablishmentStatusEnum;
import com.zsmartsystems.zigbee.zcl.clusters.keyestablishment.TerminateKeyEstablishment;
import com.zsmartsystems.zigbee.zcl.clusters.keyestablishment.ZclKeyEstablishmentCommand;
import com.zsmartsystems.zigbee.zcl.field.ByteArray;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZclKeyEstablishmentClientTest {
    private final static int TIMEOUT = 5000;

    @Test
    public void testStartShutdown() {
        System.out.println("--- " + Thread.currentThread().getStackTrace()[1].getMethodName());
        SmartEnergyClient seClient = Mockito.mock(SmartEnergyClient.class);
        ZclKeyEstablishmentCluster keCluster = Mockito.mock(ZclKeyEstablishmentCluster.class);
        IeeeAddress ieeeAddress = Mockito.mock(IeeeAddress.class);

        ZclKeyEstablishmentClient keClient = new ZclKeyEstablishmentClient(ieeeAddress, seClient, keCluster);

        Mockito.verify(keCluster, Mockito.times(1)).addCommandListener(keClient);
        keClient.setCbkeProvider(Mockito.mock(ZigBeeCbkeProvider.class));

        assertFalse(keClient.commandReceived(Mockito.mock(ZclCommand.class)));

        keClient.stop();

        keClient.shutdown();
        Mockito.verify(keCluster, Mockito.times(1)).removeCommandListener(keClient);
    }

    @Test
    public void HandleTerminateKeyEstablishment() {
        System.out.println("--- " + Thread.currentThread().getStackTrace()[1].getMethodName());
        SmartEnergyClient seClient = Mockito.mock(SmartEnergyClient.class);
        ZclKeyEstablishmentCluster keCluster = Mockito.mock(ZclKeyEstablishmentCluster.class);

        IeeeAddress ieeeAddress = Mockito.mock(IeeeAddress.class);

        ZclKeyEstablishmentClient keClient = new ZclKeyEstablishmentClient(ieeeAddress, seClient, keCluster);

        TerminateKeyEstablishment command = new TerminateKeyEstablishment(
                KeyEstablishmentStatusEnum.BAD_MESSAGE.getKey(), 1, 1);
        command.setCommandDirection(ZclCommandDirection.SERVER_TO_CLIENT);
        assertTrue(keClient.commandReceived(command));

        command = new TerminateKeyEstablishment(KeyEstablishmentStatusEnum.UNKNOWN_ISSUER.getKey(), 1, 1);
        command.setCommandDirection(ZclCommandDirection.SERVER_TO_CLIENT);
        assertTrue(keClient.commandReceived(command));

        command.setCommandDirection(ZclCommandDirection.CLIENT_TO_SERVER);
        assertFalse(keClient.commandReceived(command));
    }

    @Test
    public void HandleInitiateKeyEstablishmentResponseShortKey() throws Exception {
        System.out.println("--- " + Thread.currentThread().getStackTrace()[1].getMethodName());
        SmartEnergyClient seClient = Mockito.mock(SmartEnergyClient.class);
        ZclKeyEstablishmentCluster keCluster = Mockito.mock(ZclKeyEstablishmentCluster.class);
        IeeeAddress ieeeAddress = Mockito.mock(IeeeAddress.class);

        ArgumentCaptor<ZclKeyEstablishmentCommand> commandArgumentCaptor = ArgumentCaptor
                .forClass(ZclKeyEstablishmentCommand.class);
        Mockito.when(keCluster.sendCommand(commandArgumentCaptor.capture())).thenReturn(null);

        ZclKeyEstablishmentClient keClient = new ZclKeyEstablishmentClient(ieeeAddress, seClient, keCluster);
        TestUtilities.setField(ZclKeyEstablishmentClient.class, keClient, "state",
                ZclKeyEstablishmentClient.KeyEstablishmentState.INITIATE_REQUEST);

        ByteArray identity = new ByteArray(new int[47]);

        ZigBeeCbkeExchange cbkeExchange = Mockito.mock(ZigBeeCbkeExchange.class);
        Mockito.when(cbkeExchange.getCryptoSuite()).thenReturn(ZigBeeCryptoSuites.ECC_163K1);
        Mockito.when(cbkeExchange.getCertificate()).thenReturn(new ByteArray(new int[48]));
        TestUtilities.setField(ZclKeyEstablishmentClient.class, keClient, "cbkeExchange", cbkeExchange);

        InitiateKeyEstablishmentResponse command = new InitiateKeyEstablishmentResponse(1, 0, 0, identity);
        command.setCommandDirection(ZclCommandDirection.SERVER_TO_CLIENT);
        assertTrue(keClient.commandReceived(command));

        Mockito.verify(keCluster, Mockito.timeout(TIMEOUT).times(1))
                .sendCommand(ArgumentMatchers.any(ZclKeyEstablishmentCommand.class));

        // State gets reset back to UNINITIALISED after the FAILURE
        Mockito.verify(seClient, Mockito.timeout(TIMEOUT)).keyEstablishmentCallback(ZigBeeStatus.FAILURE, 0);
        assertEquals(KeyEstablishmentState.UNINITIALISED,
                TestUtilities.getField(ZclKeyEstablishmentClient.class, keClient, "state"));

        // Verify the responses
        assertEquals(1, commandArgumentCaptor.getAllValues().size());

        TerminateKeyEstablishment terminateKeyEstablishment = (TerminateKeyEstablishment) commandArgumentCaptor
                .getAllValues().get(0);
        assertEquals(Integer.valueOf(KeyEstablishmentStatusEnum.BAD_MESSAGE.getKey()),
                terminateKeyEstablishment.getStatusCode());
        assertEquals(Integer.valueOf(1), terminateKeyEstablishment.getKeyEstablishmentSuite());
        assertEquals(Integer.valueOf(10), terminateKeyEstablishment.getWaitTime());
    }

    @Test
    public void HandleInitiateKeyEstablishmentResponseLongKey() throws Exception {
        System.out.println("--- " + Thread.currentThread().getStackTrace()[1].getMethodName());
        SmartEnergyClient seClient = Mockito.mock(SmartEnergyClient.class);
        ZclKeyEstablishmentCluster keCluster = Mockito.mock(ZclKeyEstablishmentCluster.class);
        IeeeAddress ieeeAddress = new IeeeAddress("0000000000000000");

        ZclKeyEstablishmentClient keClient = new ZclKeyEstablishmentClient(ieeeAddress, seClient, keCluster);
        TestUtilities.setField(ZclKeyEstablishmentClient.class, keClient, "state",
                ZclKeyEstablishmentClient.KeyEstablishmentState.INITIATE_REQUEST);

        ByteArray identity = new ByteArray(new int[58]);
        ByteArray local = new ByteArray(new int[48]);

        ZigBeeCbkeExchange cbkeExchange = Mockito.mock(ZigBeeCbkeExchange.class);
        Mockito.when(cbkeExchange.getCryptoSuite()).thenReturn(ZigBeeCryptoSuites.ECC_163K1);
        TestUtilities.setField(ZclKeyEstablishmentClient.class, keClient, "cbkeExchange", cbkeExchange);

        Mockito.when(cbkeExchange.getCertificate()).thenReturn(local);
        Mockito.when(cbkeExchange.getCbkeEphemeralData()).thenReturn(local);

        InitiateKeyEstablishmentResponse command = new InitiateKeyEstablishmentResponse(
                1, 0, 0, identity);
        command.setCommandDirection(ZclCommandDirection.SERVER_TO_CLIENT);
        assertTrue(keClient.commandReceived(command));

        Mockito.verify(keCluster, Mockito.timeout(TIMEOUT).times(1))
                .sendCommand(ArgumentMatchers.any(EphemeralDataRequestCommand.class));
    }

    @Test
    public void HandleInitiateKeyEstablishmentResponseFraudulent() throws Exception {
        System.out.println("--- " + Thread.currentThread().getStackTrace()[1].getMethodName());
        SmartEnergyClient seClient = Mockito.mock(SmartEnergyClient.class);
        ZclKeyEstablishmentCluster keCluster = Mockito.mock(ZclKeyEstablishmentCluster.class);
        IeeeAddress ieeeAddress = new IeeeAddress("1111111111111111");

        ArgumentCaptor<ZclKeyEstablishmentCommand> commandArgumentCaptor = ArgumentCaptor
                .forClass(ZclKeyEstablishmentCommand.class);
        Mockito.when(keCluster.sendCommand(commandArgumentCaptor.capture())).thenReturn(null);

        ZclKeyEstablishmentClient keClient = new ZclKeyEstablishmentClient(ieeeAddress, seClient, keCluster);
        TestUtilities.setField(ZclKeyEstablishmentClient.class, keClient, "state",
                ZclKeyEstablishmentClient.KeyEstablishmentState.INITIATE_REQUEST);

        ZigBeeCbkeExchange cbkeExchange = Mockito.mock(ZigBeeCbkeExchange.class);
        Mockito.when(cbkeExchange.getCryptoSuite()).thenReturn(ZigBeeCryptoSuites.ECC_163K1);
        TestUtilities.setField(ZclKeyEstablishmentClient.class, keClient, "cbkeExchange", cbkeExchange);

        ByteArray identity = new ByteArray(new int[48]);
        ByteArray local = new ByteArray(new int[48]);

        ZigBeeCbkeProvider cbkeProvider = Mockito.mock(ZigBeeCbkeProvider.class);
        Mockito.when(cbkeExchange.getCertificate()).thenReturn(local);
        Mockito.when(cbkeExchange.getCbkeEphemeralData()).thenReturn(local);
        keClient.setCbkeProvider(cbkeProvider);

        InitiateKeyEstablishmentResponse command = new InitiateKeyEstablishmentResponse(1, 0, 0, identity);
        command.setCommandDirection(ZclCommandDirection.SERVER_TO_CLIENT);
        assertTrue(keClient.commandReceived(command));

        Mockito.verify(keCluster, Mockito.timeout(TIMEOUT).times(1))
                .sendCommand(ArgumentMatchers.any(ZclKeyEstablishmentCommand.class));

        // State gets reset back to UNINITIALISED after the FAILURE
        Mockito.verify(seClient, Mockito.timeout(TIMEOUT)).keyEstablishmentCallback(ZigBeeStatus.FAILURE, 0);
        assertEquals(KeyEstablishmentState.UNINITIALISED,
                TestUtilities.getField(ZclKeyEstablishmentClient.class, keClient, "state"));

        // Verify the responses
        assertEquals(1, commandArgumentCaptor.getAllValues().size());

        TerminateKeyEstablishment terminateKeyEstablishment = (TerminateKeyEstablishment) commandArgumentCaptor
                .getAllValues().get(0);
        assertEquals(Integer.valueOf(KeyEstablishmentStatusEnum.BAD_MESSAGE.getKey()),
                terminateKeyEstablishment.getStatusCode());
        assertEquals(Integer.valueOf(1), terminateKeyEstablishment.getKeyEstablishmentSuite());
        assertEquals(Integer.valueOf(10), terminateKeyEstablishment.getWaitTime());
    }

    @Test
    public void HandleInitiateKeyEstablishmentResponseInvalidSuite() throws Exception {
        System.out.println("--- " + Thread.currentThread().getStackTrace()[1].getMethodName());
        SmartEnergyClient seClient = Mockito.mock(SmartEnergyClient.class);
        ZclKeyEstablishmentCluster keCluster = Mockito.mock(ZclKeyEstablishmentCluster.class);
        IeeeAddress ieeeAddress = new IeeeAddress("1111111111111111");

        ZclKeyEstablishmentClient keClient = new ZclKeyEstablishmentClient(ieeeAddress, seClient, keCluster);
        TestUtilities.setField(ZclKeyEstablishmentClient.class, keClient, "state",
                ZclKeyEstablishmentClient.KeyEstablishmentState.INITIATE_REQUEST);

        ZigBeeCbkeExchange cbkeExchange = Mockito.mock(ZigBeeCbkeExchange.class);
        Mockito.when(cbkeExchange.getCryptoSuite()).thenReturn(ZigBeeCryptoSuites.ECC_163K1);
        Mockito.when(cbkeExchange.getCertificate()).thenReturn(new ByteArray(new int[48]));
        TestUtilities.setField(ZclKeyEstablishmentClient.class, keClient, "cbkeExchange", cbkeExchange);

        ByteArray identity = new ByteArray(new int[48]);

        InitiateKeyEstablishmentResponse command = new InitiateKeyEstablishmentResponse(1, 0, 0, identity);
        command.setCommandDirection(ZclCommandDirection.SERVER_TO_CLIENT);
        assertTrue(keClient.commandReceived(command));

        // State gets reset back to UNINITIALISED after the FAILURE
        Mockito.verify(seClient, Mockito.timeout(TIMEOUT)).keyEstablishmentCallback(ZigBeeStatus.FAILURE, 0);
        assertEquals(KeyEstablishmentState.UNINITIALISED,
                TestUtilities.getField(ZclKeyEstablishmentClient.class, keClient, "state"));
    }

    @Test
    public void InitiateKeyEstablishmentResponseInvalidIssuer() throws Exception {
        System.out.println("--- " + Thread.currentThread().getStackTrace()[1].getMethodName());
        SmartEnergyClient seClient = Mockito.mock(SmartEnergyClient.class);
        ZclKeyEstablishmentCluster keCluster = Mockito.mock(ZclKeyEstablishmentCluster.class);
        IeeeAddress ieeeAddress = new IeeeAddress("0022A300001731F3");

        ArgumentCaptor<ZclKeyEstablishmentCommand> commandArgumentCaptor = ArgumentCaptor
                .forClass(ZclKeyEstablishmentCommand.class);
        Mockito.when(keCluster.sendCommand(commandArgumentCaptor.capture())).thenReturn(null);

        ZclKeyEstablishmentClient keClient = new ZclKeyEstablishmentClient(ieeeAddress, seClient, keCluster);
        TestUtilities.setField(ZclKeyEstablishmentClient.class, keClient, "state",
                ZclKeyEstablishmentClient.KeyEstablishmentState.INITIATE_REQUEST);

        ByteArray certificateByteArray = new ByteArray(new int[] { 0x02, 0x00, 0xCA, 0xA2, 0x5B, 0x4B, 0xEE, 0xDE, 0x65,
                0xC3, 0x13, 0x9A, 0x5C, 0x3B, 0xC4, 0x0C, 0x9A, 0xD1, 0x53, 0x85, 0x4A, 0x27, 0x00, 0x22, 0xA3, 0x00,
                0x00, 0x17, 0x31, 0xF3, 0x54, 0x45, 0x53, 0x54, 0x55, 0x45, 0x43, 0x41, 0x01, 0x09, 0x10, 0x83, 0x01,
                0x23, 0x45, 0x67, 0x89, 0x0A });
        ZigBeeCbkeProvider cbkeProvider = Mockito.mock(ZigBeeCbkeProvider.class);
        Mockito.when(cbkeProvider.getEphemeralDataGenerateTime()).thenReturn(44);
        Mockito.when(cbkeProvider.getConfirmKeyGenerateTime()).thenReturn(55);
        Mockito.verify(keCluster, Mockito.times(1)).addCommandListener(keClient);
        keClient.setCbkeProvider(cbkeProvider);

        ZigBeeCbkeExchange cbkeExchange = Mockito.mock(ZigBeeCbkeExchange.class);
        Mockito.when(cbkeExchange.getCryptoSuite()).thenReturn(ZigBeeCryptoSuites.ECC_163K1);
        Mockito.when(cbkeExchange.getCertificate()).thenReturn(certificateByteArray);
        TestUtilities.setField(ZclKeyEstablishmentClient.class, keClient, "cbkeExchange", cbkeExchange);

        assertFalse(keClient.commandReceived(Mockito.mock(ZclCommand.class)));

        ZigBeeCbkeCertificate remoteCert = new CerticomCbkeCertificate(
                "CAPubKey:0200fde8a7f3d1084224962a4e7c54e69ac3f04da6b8DeviceImplicitCert:0200caa15b4beede65c3139a5c3bc40c9ad153854a270022a300001731f354455354534543410109108301234567890aPrivateKeyReconstructionData:019fcc486fc46980ab4a612725b36f005edff075feDevicePublicKey:020366d312a0abf55654ead3e1624c31faed89c3bb20");
        InitiateKeyEstablishmentResponse command = new InitiateKeyEstablishmentResponse(
                1, 22, 33, new ByteArray(remoteCert.getCertificate()));
        command.setCommandDirection(ZclCommandDirection.SERVER_TO_CLIENT);
        assertTrue(keClient.commandReceived(command));

        Mockito.verify(keCluster, Mockito.timeout(TIMEOUT).times(1))
                .sendCommand(ArgumentMatchers.any(ZclKeyEstablishmentCommand.class));

        // State gets reset back to UNINITIALISED after the FAILURE
        Mockito.verify(seClient, Mockito.timeout(TIMEOUT)).keyEstablishmentCallback(ZigBeeStatus.FAILURE, 0);
        assertEquals(KeyEstablishmentState.UNINITIALISED,
                TestUtilities.getField(ZclKeyEstablishmentClient.class, keClient, "state"));

        // Verify the responses
        assertEquals(1, commandArgumentCaptor.getAllValues().size());

        TerminateKeyEstablishment terminateKeyEstablishment = (TerminateKeyEstablishment) commandArgumentCaptor
                .getAllValues().get(0);
        assertEquals(Integer.valueOf(KeyEstablishmentStatusEnum.UNKNOWN_ISSUER.getKey()),
                terminateKeyEstablishment.getStatusCode());
        assertEquals(Integer.valueOf(1), terminateKeyEstablishment.getKeyEstablishmentSuite());
        assertEquals(Integer.valueOf(10), terminateKeyEstablishment.getWaitTime());
    }

}

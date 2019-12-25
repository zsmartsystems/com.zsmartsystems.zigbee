/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
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
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.TestUtilities;
import com.zsmartsystems.zigbee.ZigBeeStatus;
import com.zsmartsystems.zigbee.app.seclient.ZclKeyEstablishmentClient.KeyEstablishmentState;
import com.zsmartsystems.zigbee.security.CerticomCbkeCertificate;
import com.zsmartsystems.zigbee.security.ZigBeeCbkeCertificate;
import com.zsmartsystems.zigbee.security.ZigBeeCbkeProvider;
import com.zsmartsystems.zigbee.security.ZigBeeCryptoSuites;
import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.clusters.ZclKeyEstablishmentCluster;
import com.zsmartsystems.zigbee.zcl.clusters.keyestablishment.InitiateKeyEstablishmentResponse;
import com.zsmartsystems.zigbee.zcl.clusters.keyestablishment.KeyEstablishmentStatusEnum;
import com.zsmartsystems.zigbee.zcl.clusters.keyestablishment.TerminateKeyEstablishment;
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

        TerminateKeyEstablishment command = new TerminateKeyEstablishment();
        command.setStatusCode(KeyEstablishmentStatusEnum.BAD_MESSAGE.getKey());
        command.setCommandDirection(ZclCommandDirection.SERVER_TO_CLIENT);
        assertTrue(keClient.commandReceived(command));

        command.setStatusCode(KeyEstablishmentStatusEnum.UNKNOWN_ISSUER.getKey());
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

        ZclKeyEstablishmentClient keClient = new ZclKeyEstablishmentClient(ieeeAddress, seClient, keCluster);
        TestUtilities.setField(ZclKeyEstablishmentClient.class, keClient, "state",
                ZclKeyEstablishmentClient.KeyEstablishmentState.INITIATE_REQUEST);
        TestUtilities.setField(ZclKeyEstablishmentClient.class, keClient, "cryptoSuite", ZigBeeCryptoSuites.ECC_163K1);

        ByteArray identity = new ByteArray(new int[47]);
        ByteArray local = new ByteArray(new int[48]);

        ZigBeeCbkeProvider cbkeProvider = Mockito.mock(ZigBeeCbkeProvider.class);
        Mockito.when(cbkeProvider.getCertificate(ZigBeeCryptoSuites.ECC_163K1)).thenReturn(local);
        keClient.setCbkeProvider(cbkeProvider);

        InitiateKeyEstablishmentResponse command = new InitiateKeyEstablishmentResponse();
        command.setCommandDirection(ZclCommandDirection.SERVER_TO_CLIENT);
        command.setConfirmKeyGenerateTime(0);
        command.setEphemeralDataGenerateTime(0);
        command.setRequestedKeyEstablishmentSuite(1);
        command.setIdentity(identity);
        assertTrue(keClient.commandReceived(command));

        Mockito.verify(keCluster, Mockito.timeout(TIMEOUT))
                .terminateKeyEstablishment(KeyEstablishmentStatusEnum.BAD_MESSAGE.getKey(), 10, 1);

        // State gets reset back to UNINITIALISED after the FAILURE
        Mockito.verify(seClient, Mockito.timeout(TIMEOUT)).keyEstablishmentCallback(ZigBeeStatus.FAILURE, 0);
        assertEquals(KeyEstablishmentState.UNINITIALISED,
                TestUtilities.getField(ZclKeyEstablishmentClient.class, keClient, "state"));
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
        TestUtilities.setField(ZclKeyEstablishmentClient.class, keClient, "cryptoSuite", ZigBeeCryptoSuites.ECC_163K1);

        ByteArray identity = new ByteArray(new int[58]);
        ByteArray local = new ByteArray(new int[48]);

        ZigBeeCbkeProvider cbkeProvider = Mockito.mock(ZigBeeCbkeProvider.class);
        Mockito.when(cbkeProvider.getCertificate(ZigBeeCryptoSuites.ECC_163K1)).thenReturn(local);
        Mockito.when(cbkeProvider.getCbkeEphemeralData(ZigBeeCryptoSuites.ECC_163K1)).thenReturn(local);
        keClient.setCbkeProvider(cbkeProvider);

        InitiateKeyEstablishmentResponse command = new InitiateKeyEstablishmentResponse();
        command.setCommandDirection(ZclCommandDirection.SERVER_TO_CLIENT);
        command.setConfirmKeyGenerateTime(0);
        command.setEphemeralDataGenerateTime(0);
        command.setRequestedKeyEstablishmentSuite(1);
        command.setIdentity(identity);
        assertTrue(keClient.commandReceived(command));

        Mockito.verify(keCluster, Mockito.timeout(TIMEOUT)).ephemeralDataRequestCommand(ArgumentMatchers.any());
    }

    @Test
    public void HandleInitiateKeyEstablishmentResponseFraudulent() throws Exception {
        System.out.println("--- " + Thread.currentThread().getStackTrace()[1].getMethodName());
        SmartEnergyClient seClient = Mockito.mock(SmartEnergyClient.class);
        ZclKeyEstablishmentCluster keCluster = Mockito.mock(ZclKeyEstablishmentCluster.class);
        IeeeAddress ieeeAddress = new IeeeAddress("1111111111111111");

        ZclKeyEstablishmentClient keClient = new ZclKeyEstablishmentClient(ieeeAddress, seClient, keCluster);
        TestUtilities.setField(ZclKeyEstablishmentClient.class, keClient, "state",
                ZclKeyEstablishmentClient.KeyEstablishmentState.INITIATE_REQUEST);
        TestUtilities.setField(ZclKeyEstablishmentClient.class, keClient, "cryptoSuite", ZigBeeCryptoSuites.ECC_163K1);

        ByteArray identity = new ByteArray(new int[48]);
        ByteArray local = new ByteArray(new int[48]);

        ZigBeeCbkeProvider cbkeProvider = Mockito.mock(ZigBeeCbkeProvider.class);
        Mockito.when(cbkeProvider.getCertificate(ZigBeeCryptoSuites.ECC_163K1)).thenReturn(local);
        Mockito.when(cbkeProvider.getCbkeEphemeralData(ZigBeeCryptoSuites.ECC_163K1)).thenReturn(local);
        keClient.setCbkeProvider(cbkeProvider);

        InitiateKeyEstablishmentResponse command = new InitiateKeyEstablishmentResponse();
        command.setCommandDirection(ZclCommandDirection.SERVER_TO_CLIENT);
        command.setConfirmKeyGenerateTime(0);
        command.setEphemeralDataGenerateTime(0);
        command.setRequestedKeyEstablishmentSuite(1);
        command.setIdentity(identity);
        assertTrue(keClient.commandReceived(command));

        Mockito.verify(keCluster, Mockito.timeout(TIMEOUT))
                .terminateKeyEstablishment(KeyEstablishmentStatusEnum.BAD_MESSAGE.getKey(), 10, 1);

        // State gets reset back to UNINITIALISED after the FAILURE
        Mockito.verify(seClient, Mockito.timeout(TIMEOUT)).keyEstablishmentCallback(ZigBeeStatus.FAILURE, 0);
        assertEquals(KeyEstablishmentState.UNINITIALISED,
                TestUtilities.getField(ZclKeyEstablishmentClient.class, keClient, "state"));
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
        TestUtilities.setField(ZclKeyEstablishmentClient.class, keClient, "cryptoSuite", ZigBeeCryptoSuites.ECC_283K1);

        ByteArray identity = new ByteArray(new int[48]);

        ZigBeeCbkeProvider cbkeProvider = Mockito.mock(ZigBeeCbkeProvider.class);
        keClient.setCbkeProvider(cbkeProvider);

        InitiateKeyEstablishmentResponse command = new InitiateKeyEstablishmentResponse();
        command.setCommandDirection(ZclCommandDirection.SERVER_TO_CLIENT);
        command.setConfirmKeyGenerateTime(0);
        command.setEphemeralDataGenerateTime(0);
        command.setRequestedKeyEstablishmentSuite(1);
        command.setIdentity(identity);
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

        ZclKeyEstablishmentClient keClient = new ZclKeyEstablishmentClient(ieeeAddress, seClient, keCluster);
        TestUtilities.setField(ZclKeyEstablishmentClient.class, keClient, "state",
                ZclKeyEstablishmentClient.KeyEstablishmentState.INITIATE_REQUEST);
        TestUtilities.setField(ZclKeyEstablishmentClient.class, keClient, "cryptoSuite", ZigBeeCryptoSuites.ECC_163K1);

        ByteArray certificateByteArray = new ByteArray(new int[] { 0x02, 0x00, 0xCA, 0xA2, 0x5B, 0x4B, 0xEE, 0xDE, 0x65,
                0xC3, 0x13, 0x9A, 0x5C, 0x3B, 0xC4, 0x0C, 0x9A, 0xD1, 0x53, 0x85, 0x4A, 0x27, 0x00, 0x22, 0xA3, 0x00,
                0x00, 0x17, 0x31, 0xF3, 0x54, 0x45, 0x53, 0x54, 0x55, 0x45, 0x43, 0x41, 0x01, 0x09, 0x10, 0x83, 0x01,
                0x23, 0x45, 0x67, 0x89, 0x0A });
        ZigBeeCbkeProvider cbkeProvider = Mockito.mock(ZigBeeCbkeProvider.class);
        Mockito.when(cbkeProvider.getEphemeralDataGenerateTime()).thenReturn(44);
        Mockito.when(cbkeProvider.getConfirmKeyGenerateTime()).thenReturn(55);
        Mockito.when(cbkeProvider.getCertificate(ZigBeeCryptoSuites.ECC_163K1)).thenReturn(certificateByteArray);
        Mockito.verify(keCluster, Mockito.times(1)).addCommandListener(keClient);
        keClient.setCbkeProvider(cbkeProvider);

        assertFalse(keClient.commandReceived(Mockito.mock(ZclCommand.class)));

        ZigBeeCbkeCertificate remoteCert = new CerticomCbkeCertificate(
                "CAPubKey:0200fde8a7f3d1084224962a4e7c54e69ac3f04da6b8DeviceImplicitCert:0200caa15b4beede65c3139a5c3bc40c9ad153854a270022a300001731f354455354534543410109108301234567890aPrivateKeyReconstructionData:019fcc486fc46980ab4a612725b36f005edff075feDevicePublicKey:020366d312a0abf55654ead3e1624c31faed89c3bb20");
        InitiateKeyEstablishmentResponse command = new InitiateKeyEstablishmentResponse();
        command.setRequestedKeyEstablishmentSuite(1);
        command.setIdentity(new ByteArray(remoteCert.getCertificate()));
        command.setEphemeralDataGenerateTime(22);
        command.setConfirmKeyGenerateTime(33);
        command.setCommandDirection(ZclCommandDirection.SERVER_TO_CLIENT);
        assertTrue(keClient.commandReceived(command));

        Mockito.verify(keCluster, Mockito.timeout(TIMEOUT))
                .terminateKeyEstablishment(KeyEstablishmentStatusEnum.UNKNOWN_ISSUER.getKey(), 10, 1);

        // State gets reset back to UNINITIALISED after the FAILURE
        Mockito.verify(seClient, Mockito.timeout(TIMEOUT)).keyEstablishmentCallback(ZigBeeStatus.FAILURE, 0);
        assertEquals(KeyEstablishmentState.UNINITIALISED,
                TestUtilities.getField(ZclKeyEstablishmentClient.class, keClient, "state"));
    }

}

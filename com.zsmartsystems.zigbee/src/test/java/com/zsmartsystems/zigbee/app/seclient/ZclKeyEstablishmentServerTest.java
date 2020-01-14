/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.app.seclient;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.TestUtilities;
import com.zsmartsystems.zigbee.app.seclient.ZclKeyEstablishmentServer.KeyEstablishmentState;
import com.zsmartsystems.zigbee.security.CerticomCbkeCertificate;
import com.zsmartsystems.zigbee.security.ZigBeeCbkeCertificate;
import com.zsmartsystems.zigbee.security.ZigBeeCbkeExchange;
import com.zsmartsystems.zigbee.security.ZigBeeCbkeProvider;
import com.zsmartsystems.zigbee.security.ZigBeeCryptoSuites;
import com.zsmartsystems.zigbee.zcl.ZclAttribute;
import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.clusters.ZclKeyEstablishmentCluster;
import com.zsmartsystems.zigbee.zcl.clusters.keyestablishment.ConfirmKeyDataRequestCommand;
import com.zsmartsystems.zigbee.zcl.clusters.keyestablishment.ConfirmKeyResponse;
import com.zsmartsystems.zigbee.zcl.clusters.keyestablishment.EphemeralDataRequestCommand;
import com.zsmartsystems.zigbee.zcl.clusters.keyestablishment.EphemeralDataResponse;
import com.zsmartsystems.zigbee.zcl.clusters.keyestablishment.InitiateKeyEstablishmentRequestCommand;
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
public class ZclKeyEstablishmentServerTest {
    private final static int TIMEOUT = 5000;

    @Test
    public void InitiateKeyEstablishmentRequestCommandSuccess() throws Exception {
        TestUtilities.outputTestHeader();
        ZclKeyEstablishmentCluster keCluster = Mockito.mock(ZclKeyEstablishmentCluster.class);
        IeeeAddress ieeeAddress = new IeeeAddress("0022A300001731F3");

        ZclKeyEstablishmentServer keServer = new ZclKeyEstablishmentServer(ieeeAddress, keCluster);

        ArgumentCaptor<ZclKeyEstablishmentCommand> commandArgumentCaptor = ArgumentCaptor
                .forClass(ZclKeyEstablishmentCommand.class);
        Mockito.when(keCluster.sendCommand(commandArgumentCaptor.capture())).thenReturn(null);

        ByteArray certificateByteArray = new ByteArray(new int[] { 0x02, 0x00, 0xCA, 0xA1, 0x5B, 0x4B, 0xEE, 0xDE, 0x65,
                0xC3, 0x13, 0x9A, 0x5C, 0x3B, 0xC4, 0x0C, 0x9A, 0xD1, 0x53, 0x85, 0x4A, 0x27, 0x00, 0x22, 0xA3, 0x00,
                0x00, 0x17, 0x31, 0xF3, 0x54, 0x45, 0x53, 0x54, 0x53, 0x45, 0x43, 0x41, 0x01, 0x09, 0x10, 0x83, 0x01,
                0x23, 0x45, 0x67, 0x89, 0x0A });
        ZigBeeCbkeProvider cbkeProvider = Mockito.mock(ZigBeeCbkeProvider.class);
        Mockito.when(cbkeProvider.getEphemeralDataGenerateTime()).thenReturn(44);
        Mockito.when(cbkeProvider.getConfirmKeyGenerateTime()).thenReturn(55);
        keServer.setCbkeProvider(cbkeProvider);

        ZigBeeCbkeExchange cbkeExchange = Mockito.mock(ZigBeeCbkeExchange.class);
        Mockito.when(cbkeExchange.getCryptoSuite()).thenReturn(ZigBeeCryptoSuites.ECC_163K1);
        Mockito.when(cbkeExchange.getCertificate()).thenReturn(certificateByteArray);
        Mockito.when(cbkeProvider.getCbkeKeyExchangeResponder()).thenReturn(cbkeExchange);

        assertFalse(keServer.commandReceived(Mockito.mock(ZclCommand.class)));

        ZigBeeCbkeCertificate remoteCert = new CerticomCbkeCertificate(
                "CAPubKey:0200fde8a7f3d1084224962a4e7c54e69ac3f04da6b8DeviceImplicitCert:0200caa15b4beede65c3139a5c3bc40c9ad153854a270022a300001731f354455354534543410109108301234567890aPrivateKeyReconstructionData:019fcc486fc46980ab4a612725b36f005edff075feDevicePublicKey:020366d312a0abf55654ead3e1624c31faed89c3bb20");
        InitiateKeyEstablishmentRequestCommand initiateCommand = new InitiateKeyEstablishmentRequestCommand(
                1, 22, 33, new ByteArray(remoteCert.getCertificate()));
        initiateCommand.setCommandDirection(ZclCommandDirection.CLIENT_TO_SERVER);
        assertTrue(keServer.commandReceived(initiateCommand));

        Mockito.verify(cbkeExchange, Mockito.timeout(TIMEOUT)).getCertificate();

        Mockito.verify(keCluster, Mockito.timeout(TIMEOUT).times(1)).sendCommand(ArgumentMatchers.any());

        // Send the command again - this time we're in the wrong state so we get an error
        assertTrue(keServer.commandReceived(initiateCommand));

        Mockito.verify(keCluster, Mockito.timeout(TIMEOUT).times(2)).sendCommand(ArgumentMatchers.any());

        // State gets reset back to UNINITIALISED after the FAILURE
        assertEquals(KeyEstablishmentState.UNINITIALISED,
                TestUtilities.getField(ZclKeyEstablishmentServer.class, keServer, "keyEstablishmentState"));

        // Verify the responses
        assertEquals(2, commandArgumentCaptor.getAllValues().size());

        InitiateKeyEstablishmentResponse initiateKeyEstablishmentResponse = (InitiateKeyEstablishmentResponse) commandArgumentCaptor
                .getAllValues().get(0);
        assertEquals(Integer.valueOf(1), initiateKeyEstablishmentResponse.getRequestedKeyEstablishmentSuite());
        assertEquals(Integer.valueOf(44), initiateKeyEstablishmentResponse.getEphemeralDataGenerateTime());
        assertEquals(Integer.valueOf(55), initiateKeyEstablishmentResponse.getConfirmKeyGenerateTime());
        assertEquals(certificateByteArray, initiateKeyEstablishmentResponse.getIdentity());

        TerminateKeyEstablishment terminateKeyEstablishment = (TerminateKeyEstablishment) commandArgumentCaptor
                .getAllValues().get(1);
        assertEquals(Integer.valueOf(KeyEstablishmentStatusEnum.BAD_MESSAGE.getKey()),
                terminateKeyEstablishment.getStatusCode());
        assertEquals(Integer.valueOf(1), terminateKeyEstablishment.getKeyEstablishmentSuite());
        assertEquals(Integer.valueOf(20), terminateKeyEstablishment.getWaitTime());
    }

    @Test
    public void InitiateKeyEstablishmentRequestCommandUnknownIssuer() throws Exception {
        TestUtilities.outputTestHeader();
        ZclKeyEstablishmentCluster keCluster = Mockito.mock(ZclKeyEstablishmentCluster.class);
        IeeeAddress ieeeAddress = new IeeeAddress("1111111111111111");

        ZclKeyEstablishmentServer keServer = new ZclKeyEstablishmentServer(ieeeAddress, keCluster);

        ArgumentCaptor<ZclKeyEstablishmentCommand> commandArgumentCaptor = ArgumentCaptor
                .forClass(ZclKeyEstablishmentCommand.class);
        Mockito.when(keCluster.sendCommand(commandArgumentCaptor.capture())).thenReturn(null);

        ByteArray certificateByteArray = new ByteArray(new int[] { 0x02, 0x00, 0xCA, 0xA2, 0x5B, 0x4B, 0xEE, 0xDE, 0x65,
                0xC3, 0x13, 0x9A, 0x5C, 0x3B, 0xC4, 0x0C, 0x9A, 0xD1, 0x53, 0x85, 0x4A, 0x27, 0x00, 0x22, 0xA3, 0x00,
                0x00, 0x17, 0x31, 0xF3, 0x54, 0x45, 0x53, 0x54, 0x55, 0x45, 0x43, 0x41, 0x01, 0x09, 0x10, 0x83, 0x01,
                0x23, 0x45, 0x67, 0x89, 0x0A });
        ZigBeeCbkeProvider cbkeProvider = Mockito.mock(ZigBeeCbkeProvider.class);
        Mockito.when(cbkeProvider.getEphemeralDataGenerateTime()).thenReturn(44);
        Mockito.when(cbkeProvider.getConfirmKeyGenerateTime()).thenReturn(55);
        Mockito.verify(keCluster, Mockito.times(1)).addCommandListener(keServer);
        keServer.setCbkeProvider(cbkeProvider);

        ZigBeeCbkeExchange cbkeExchange = Mockito.mock(ZigBeeCbkeExchange.class);
        Mockito.when(cbkeExchange.getCryptoSuite()).thenReturn(ZigBeeCryptoSuites.ECC_163K1);
        Mockito.when(cbkeExchange.getCertificate()).thenReturn(certificateByteArray);
        Mockito.when(cbkeProvider.getCbkeKeyExchangeResponder()).thenReturn(cbkeExchange);

        assertFalse(keServer.commandReceived(Mockito.mock(ZclCommand.class)));

        ZigBeeCbkeCertificate remoteCert = new CerticomCbkeCertificate(
                "CAPubKey:0200fde8a7f3d1084224962a4e7c54e69ac3f04da6b8DeviceImplicitCert:0200caa15b4beede65c3139a5c3bc40c9ad153854a270022a300001731f354455354534543410109108301234567890aPrivateKeyReconstructionData:019fcc486fc46980ab4a612725b36f005edff075feDevicePublicKey:020366d312a0abf55654ead3e1624c31faed89c3bb20");
        InitiateKeyEstablishmentRequestCommand initiateCommand = new InitiateKeyEstablishmentRequestCommand(
                1, 22, 33, new ByteArray(remoteCert.getCertificate()));
        initiateCommand.setCommandDirection(ZclCommandDirection.CLIENT_TO_SERVER);
        assertTrue(keServer.commandReceived(initiateCommand));

        Mockito.verify(keCluster, Mockito.timeout(TIMEOUT).times(1)).sendCommand(ArgumentMatchers.any());

        // State gets reset back to UNINITIALISED after the FAILURE
        assertEquals(KeyEstablishmentState.UNINITIALISED,
                TestUtilities.getField(ZclKeyEstablishmentServer.class, keServer, "keyEstablishmentState"));

        // Verify the responses
        assertEquals(1, commandArgumentCaptor.getAllValues().size());

        TerminateKeyEstablishment terminateKeyEstablishment = (TerminateKeyEstablishment) commandArgumentCaptor
                .getAllValues().get(0);
        assertEquals(Integer.valueOf(KeyEstablishmentStatusEnum.UNKNOWN_ISSUER.getKey()),
                terminateKeyEstablishment.getStatusCode());
        assertEquals(Integer.valueOf(1), terminateKeyEstablishment.getKeyEstablishmentSuite());
        assertEquals(Integer.valueOf(10), terminateKeyEstablishment.getWaitTime());
    }

    @Test
    public void HandleEphemeralDataRequestUninitialised() throws Exception {
        TestUtilities.outputTestHeader();
        ZclKeyEstablishmentCluster keCluster = Mockito.mock(ZclKeyEstablishmentCluster.class);
        IeeeAddress ieeeAddress = new IeeeAddress("1111111111111111");

        ZclKeyEstablishmentServer keServer = new ZclKeyEstablishmentServer(ieeeAddress, keCluster);

        ArgumentCaptor<ZclKeyEstablishmentCommand> commandArgumentCaptor = ArgumentCaptor
                .forClass(ZclKeyEstablishmentCommand.class);
        Mockito.when(keCluster.sendCommand(commandArgumentCaptor.capture())).thenReturn(null);

        ZigBeeCbkeExchange cbkeExchange = Mockito.mock(ZigBeeCbkeExchange.class);
        TestUtilities.setField(ZclKeyEstablishmentServer.class, keServer, "cbkeExchange", cbkeExchange);

        EphemeralDataRequestCommand command = new EphemeralDataRequestCommand(Mockito.mock(ByteArray.class));
        command.setCommandDirection(ZclCommandDirection.CLIENT_TO_SERVER);
        assertTrue(keServer.commandReceived(command));

        // State is not initialised
        Mockito.verify(keCluster, Mockito.timeout(TIMEOUT).times(1)).sendCommand(ArgumentMatchers.any());

        Mockito.verify(cbkeExchange, Mockito.timeout(TIMEOUT).times(1)).completeKeyExchange(false);

        // State gets reset back to UNINITIALISED after the FAILURE
        assertEquals(KeyEstablishmentState.UNINITIALISED,
                TestUtilities.getField(ZclKeyEstablishmentServer.class, keServer, "keyEstablishmentState"));

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
    public void HandleConfirmKeyRequestUninitialised() throws Exception {
        TestUtilities.outputTestHeader();
        ZclKeyEstablishmentCluster keCluster = Mockito.mock(ZclKeyEstablishmentCluster.class);
        IeeeAddress ieeeAddress = new IeeeAddress("1111111111111111");

        ZclKeyEstablishmentServer keServer = new ZclKeyEstablishmentServer(ieeeAddress, keCluster);

        ArgumentCaptor<ZclKeyEstablishmentCommand> commandArgumentCaptor = ArgumentCaptor
                .forClass(ZclKeyEstablishmentCommand.class);
        Mockito.when(keCluster.sendCommand(commandArgumentCaptor.capture())).thenReturn(null);

        ZigBeeCbkeExchange cbkeExchange = Mockito.mock(ZigBeeCbkeExchange.class);
        TestUtilities.setField(ZclKeyEstablishmentServer.class, keServer, "cbkeExchange", cbkeExchange);

        ConfirmKeyDataRequestCommand command = new ConfirmKeyDataRequestCommand(Mockito.mock(ByteArray.class));
        command.setCommandDirection(ZclCommandDirection.CLIENT_TO_SERVER);
        assertTrue(keServer.commandReceived(command));

        // State is not initialised
        Mockito.verify(keCluster, Mockito.timeout(TIMEOUT).times(1)).sendCommand(ArgumentMatchers.any());

        Mockito.verify(cbkeExchange, Mockito.timeout(TIMEOUT).times(1)).completeKeyExchange(false);

        // State gets reset back to UNINITIALISED after the FAILURE
        assertEquals(KeyEstablishmentState.UNINITIALISED,
                TestUtilities.getField(ZclKeyEstablishmentServer.class, keServer, "keyEstablishmentState"));

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
    public void HandleConfirmKeyRequestBadKey() throws Exception {
        TestUtilities.outputTestHeader();
        ZclKeyEstablishmentCluster keCluster = Mockito.mock(ZclKeyEstablishmentCluster.class);
        IeeeAddress ieeeAddress = new IeeeAddress("1111111111111111");

        ZclKeyEstablishmentServer keServer = new ZclKeyEstablishmentServer(ieeeAddress, keCluster);
        TestUtilities.setField(ZclKeyEstablishmentServer.class, keServer, "keyEstablishmentState",
                KeyEstablishmentState.CONFIRM_KEY_REQUEST);

        ArgumentCaptor<ZclKeyEstablishmentCommand> commandArgumentCaptor = ArgumentCaptor
                .forClass(ZclKeyEstablishmentCommand.class);
        Mockito.when(keCluster.sendCommand(commandArgumentCaptor.capture())).thenReturn(null);

        ZigBeeCbkeExchange cbkeExchange = Mockito.mock(ZigBeeCbkeExchange.class);
        Mockito.when(cbkeExchange.getCryptoSuite()).thenReturn(ZigBeeCryptoSuites.ECC_163K1);
        Mockito.when(cbkeExchange.getCertificate()).thenReturn(new ByteArray(new int[48]));
        TestUtilities.setField(ZclKeyEstablishmentServer.class, keServer, "cbkeExchange", cbkeExchange);

        ByteArray localMacButeArray = new ByteArray(new int[] { 1, 2, 3, 4, 5, 6, 7, 8 });
        ByteArray remoteMacButeArray = new ByteArray(new int[] { 8, 7, 6, 5, 4, 3, 2, 1 });

        ZigBeeCbkeProvider cbkeProvider = Mockito.mock(ZigBeeCbkeProvider.class);
        Mockito.when(cbkeExchange.getResponderMac()).thenReturn(localMacButeArray);
        keServer.setCbkeProvider(cbkeProvider);

        ConfirmKeyDataRequestCommand command = new ConfirmKeyDataRequestCommand(remoteMacButeArray);
        command.setCommandDirection(ZclCommandDirection.CLIENT_TO_SERVER);
        assertTrue(keServer.commandReceived(command));

        Mockito.verify(keCluster, Mockito.timeout(TIMEOUT).times(1)).sendCommand(ArgumentMatchers.any());

        // State gets reset back to UNINITIALISED after the FAILURE
        assertEquals(KeyEstablishmentState.UNINITIALISED,
                TestUtilities.getField(ZclKeyEstablishmentServer.class, keServer, "keyEstablishmentState"));

        // Verify the responses
        assertEquals(1, commandArgumentCaptor.getAllValues().size());

        TerminateKeyEstablishment terminateKeyEstablishment = (TerminateKeyEstablishment) commandArgumentCaptor
                .getAllValues().get(0);
        assertEquals(Integer.valueOf(KeyEstablishmentStatusEnum.BAD_KEY_CONFIRM.getKey()),
                terminateKeyEstablishment.getStatusCode());
        assertEquals(Integer.valueOf(1), terminateKeyEstablishment.getKeyEstablishmentSuite());
        assertEquals(Integer.valueOf(10), terminateKeyEstablishment.getWaitTime());
    }

    @Test
    public void HandleTerminateKeyEstablishment() throws Exception {
        TestUtilities.outputTestHeader();
        ZclKeyEstablishmentCluster keCluster = Mockito.mock(ZclKeyEstablishmentCluster.class);
        IeeeAddress ieeeAddress = new IeeeAddress("1111111111111111");

        ZclKeyEstablishmentServer keServer = new ZclKeyEstablishmentServer(ieeeAddress, keCluster);

        ArgumentCaptor<ZclKeyEstablishmentCommand> commandArgumentCaptor = ArgumentCaptor
                .forClass(ZclKeyEstablishmentCommand.class);
        Mockito.when(keCluster.sendCommand(commandArgumentCaptor.capture())).thenReturn(null);

        ZigBeeCbkeExchange cbkeExchange = Mockito.mock(ZigBeeCbkeExchange.class);
        TestUtilities.setField(ZclKeyEstablishmentServer.class, keServer, "cbkeExchange", cbkeExchange);

        TerminateKeyEstablishment command = new TerminateKeyEstablishment(
                KeyEstablishmentStatusEnum.BAD_MESSAGE.getKey(), 20, 1);
        command.setCommandDirection(ZclCommandDirection.CLIENT_TO_SERVER);
        assertTrue(keServer.commandReceived(command));

        command.setCommandDirection(ZclCommandDirection.SERVER_TO_CLIENT);
        assertFalse(keServer.commandReceived(command));

        Mockito.verify(cbkeExchange, Mockito.timeout(TIMEOUT).times(1)).completeKeyExchange(false);

        // State gets reset back to UNINITIALISED after the FAILURE
        assertEquals(KeyEstablishmentState.UNINITIALISED,
                TestUtilities.getField(ZclKeyEstablishmentServer.class, keServer, "keyEstablishmentState"));
    }

    @Test
    public void Success() throws Exception {
        TestUtilities.outputTestHeader();
        ZclKeyEstablishmentCluster keCluster = Mockito.mock(ZclKeyEstablishmentCluster.class);
        IeeeAddress ieeeAddress = new IeeeAddress("0022A300001731F3");

        ZclKeyEstablishmentServer keServer = new ZclKeyEstablishmentServer(ieeeAddress, keCluster);

        ArgumentCaptor<ZclKeyEstablishmentCommand> commandArgumentCaptor = ArgumentCaptor
                .forClass(ZclKeyEstablishmentCommand.class);
        Mockito.when(keCluster.sendCommand(commandArgumentCaptor.capture())).thenReturn(null);

        keServer.setCryptoSuite(ZigBeeCryptoSuites.ECC_163K1);

        ByteArray certificateByteArray = new ByteArray(new int[] { 0x02, 0x00, 0xCA, 0xA1, 0x5B, 0x4B, 0xEE, 0xDE, 0x65,
                0xC3, 0x13, 0x9A, 0x5C, 0x3B, 0xC4, 0x0C, 0x9A, 0xD1, 0x53, 0x85, 0x4A, 0x27, 0x00, 0x22, 0xA3, 0x00,
                0x00, 0x17, 0x31, 0xF3, 0x54, 0x45, 0x53, 0x54, 0x53, 0x45, 0x43, 0x41, 0x01, 0x09, 0x10, 0x83, 0x01,
                0x23, 0x45, 0x67, 0x89, 0x0A });
        ByteArray ephemeralDataByteArray = new ByteArray(
                new int[] { 0x02, 0x00, 0xCA, 0xA1, 0x5B, 0x4B, 0xEE, 0xDE, 0x65 });
        ByteArray secureMessageAuthenticationCodeByteArray = new ByteArray(
                new int[] { 0x02, 0x00, 0xCA, 0xA1, 0x5B, 0x4B, 0xEE, 0xDE, 0x65 });

        Set<ZigBeeCryptoSuites> cryptoSuites = new HashSet<>();
        cryptoSuites.add(ZigBeeCryptoSuites.ECC_163K1);
        ZigBeeCbkeProvider cbkeProvider = Mockito.mock(ZigBeeCbkeProvider.class);
        Mockito.when(cbkeProvider.getAvailableCryptoSuites()).thenReturn(cryptoSuites);
        Mockito.when(cbkeProvider.getEphemeralDataGenerateTime()).thenReturn(44);
        Mockito.when(cbkeProvider.getConfirmKeyGenerateTime()).thenReturn(55);
        Mockito.verify(keCluster, Mockito.times(1)).addCommandListener(keServer);
        keServer.setCbkeProvider(cbkeProvider);

        ZigBeeCbkeExchange cbkeExchange = Mockito.mock(ZigBeeCbkeExchange.class);
        Mockito.when(cbkeExchange.getCryptoSuite()).thenReturn(ZigBeeCryptoSuites.ECC_163K1);
        Mockito.when(cbkeExchange.getCertificate()).thenReturn(certificateByteArray);
        Mockito.when(cbkeProvider.getCbkeKeyExchangeResponder()).thenReturn(cbkeExchange);

        Mockito.when(cbkeExchange.getCbkeEphemeralData()).thenReturn(ephemeralDataByteArray);
        Mockito.when(cbkeExchange.getResponderMac()).thenReturn(secureMessageAuthenticationCodeByteArray);
        Mockito.when(cbkeExchange.getInitiatorMac()).thenReturn(secureMessageAuthenticationCodeByteArray);

        ZclAttribute attribute = Mockito.mock(ZclAttribute.class);
        keServer.setCryptoSuite(ZigBeeCryptoSuites.ECC_283K1);
        keServer.setCryptoSuite(ZigBeeCryptoSuites.ECC_163K1);
        Mockito.when(keCluster.getLocalAttribute(ZclKeyEstablishmentCluster.ATTR_SERVERKEYESTABLISHMENTSUITE))
                .thenReturn(attribute);
        keServer.setCryptoSuite(ZigBeeCryptoSuites.ECC_163K1);
        Mockito.verify(attribute).setValue(1);
        assertNull(keServer.getCryptoSuite());

        assertFalse(keServer.commandReceived(Mockito.mock(ZclCommand.class)));

        ZigBeeCbkeCertificate remoteCert = new CerticomCbkeCertificate(
                "CAPubKey:0200fde8a7f3d1084224962a4e7c54e69ac3f04da6b8DeviceImplicitCert:0200caa15b4beede65c3139a5c3bc40c9ad153854a270022a300001731f354455354534543410109108301234567890aPrivateKeyReconstructionData:019fcc486fc46980ab4a612725b36f005edff075feDevicePublicKey:020366d312a0abf55654ead3e1624c31faed89c3bb20");
        InitiateKeyEstablishmentRequestCommand initiateCommand = new InitiateKeyEstablishmentRequestCommand(
                1, 22, 33, new ByteArray(remoteCert.getCertificate()));
        assertTrue(keServer.commandReceived(initiateCommand));

        Mockito.verify(cbkeExchange, Mockito.timeout(TIMEOUT)).getCertificate();

        Mockito.verify(keCluster, Mockito.timeout(TIMEOUT).times(1)).sendCommand(ArgumentMatchers.any());

        assertEquals(ZigBeeCryptoSuites.ECC_163K1, keServer.getCryptoSuite());

        EphemeralDataRequestCommand dataRequest = new EphemeralDataRequestCommand(ephemeralDataByteArray);
        assertTrue(keServer.commandReceived(dataRequest));

        Mockito.verify(cbkeExchange, Mockito.timeout(TIMEOUT)).getCbkeEphemeralData();
        Mockito.verify(keCluster, Mockito.timeout(TIMEOUT).times(2)).sendCommand(ArgumentMatchers.any());

        ConfirmKeyDataRequestCommand confirmKey = new ConfirmKeyDataRequestCommand(
                secureMessageAuthenticationCodeByteArray);
        assertTrue(keServer.commandReceived(confirmKey));

        Mockito.verify(cbkeExchange, Mockito.timeout(TIMEOUT)).getResponderMac();
        Mockito.verify(keCluster, Mockito.timeout(TIMEOUT).times(3)).sendCommand(ArgumentMatchers.any());

        keServer.shutdown();
        Mockito.verify(keCluster, Mockito.times(1)).removeCommandListener(keServer);

        assertEquals(3, commandArgumentCaptor.getAllValues().size());

        InitiateKeyEstablishmentResponse initiateKeyEstablishmentResponse = (InitiateKeyEstablishmentResponse) commandArgumentCaptor
                .getAllValues().get(0);
        System.out.println(initiateKeyEstablishmentResponse);
        assertEquals(Integer.valueOf(1), initiateKeyEstablishmentResponse.getRequestedKeyEstablishmentSuite());
        assertEquals(Integer.valueOf(44), initiateKeyEstablishmentResponse.getEphemeralDataGenerateTime());
        assertEquals(Integer.valueOf(55), initiateKeyEstablishmentResponse.getConfirmKeyGenerateTime());
        assertEquals(certificateByteArray, initiateKeyEstablishmentResponse.getIdentity());

        EphemeralDataResponse ephemeralDataResponse = (EphemeralDataResponse) commandArgumentCaptor
                .getAllValues().get(1);
        System.out.println(ephemeralDataResponse);
        assertEquals(ephemeralDataByteArray, ephemeralDataResponse.getEphemeralData());

        ConfirmKeyResponse confirmKeyResponse = (ConfirmKeyResponse) commandArgumentCaptor
                .getAllValues().get(2);
        System.out.println(confirmKeyResponse);
        assertEquals(secureMessageAuthenticationCodeByteArray, confirmKeyResponse.getSecureMessageAuthenticationCode());

    }

}

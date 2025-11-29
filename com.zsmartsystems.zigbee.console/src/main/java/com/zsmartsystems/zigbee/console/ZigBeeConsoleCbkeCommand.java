/**
 * Copyright (c) 2016-2025 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console;

import java.io.PrintStream;

import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeStatus;
import com.zsmartsystems.zigbee.app.seclient.SmartEnergyClient;
import com.zsmartsystems.zigbee.security.CerticomCbkeCertificate;
import com.zsmartsystems.zigbee.security.ZigBeeCbkeCertificate;
import com.zsmartsystems.zigbee.security.ZigBeeCbkeProvider;

/**
 * Handles management of Certificate Based Key Exchange certificates and key updates.
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeConsoleCbkeCommand extends ZigBeeConsoleAbstractCommand {
    @Override
    public String getCommand() {
        return "cbke";
    }

    @Override
    public String getDescription() {
        return "Provides CBKE support functions";
    }

    @Override
    public String getSyntax() {
        return "[SET CERTIFICATE] [UPDATE ENDPOINT]";
    }

    @Override
    public String getHelp() {
        return "";
    }

    @Override
    public void process(ZigBeeNetworkManager networkManager, String[] args, PrintStream out)
            throws IllegalArgumentException {
        if (args.length < 2 || args.length > 3) {
            throw new IllegalArgumentException("Incorrect number of arguments.");
        }

        switch (args[1].toUpperCase()) {
            case "SET":
                setCertificate(networkManager, args[2], out);
                break;
            case "UPDATE":
                updateKey(networkManager, args[2], out);
                break;
            default:
                throw new IllegalArgumentException("Invalid command option " + args[1]);
        }
    }

    private void setCertificate(ZigBeeNetworkManager networkManager, String certificateString, PrintStream out) {
        ZigBeeCbkeCertificate certificate = new CerticomCbkeCertificate(certificateString);

        SmartEnergyClient seClient = (SmartEnergyClient) networkManager.getExtension(SmartEnergyClient.class);
        if (seClient == null) {
            throw new IllegalStateException("Smart Energy not found");
        }

        ZigBeeCbkeProvider certificateProvider = seClient.getCbkeProvider();
        ZigBeeStatus result = certificateProvider.addCertificate(certificate);

        out.println("Certificate type " + certificate.getCryptoSuite() + " completed with state " + result);
    }

    private void updateKey(ZigBeeNetworkManager networkManager, String endpointString, PrintStream out) {
        ZigBeeEndpoint endpoint = getEndpoint(networkManager, endpointString);

        SmartEnergyClient seClient = (SmartEnergyClient) networkManager.getExtension(SmartEnergyClient.class);
        if (seClient == null) {
            throw new IllegalArgumentException("Smart Energy Client not found in network.");
        }

        ZigBeeStatus result = seClient.startKeyExchange(endpoint.getEndpointAddress());
        out.println(
                "Starting CBKE key update with " + endpoint.getEndpointAddress() + " completed with state " + result);
    }
}

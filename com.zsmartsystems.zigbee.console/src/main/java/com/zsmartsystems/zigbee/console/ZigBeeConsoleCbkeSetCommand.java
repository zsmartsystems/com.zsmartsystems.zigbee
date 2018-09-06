/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console;

import java.io.PrintStream;

import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.app.seclient.SmartEnergyClient;
import com.zsmartsystems.zigbee.security.CerticomCbkeCertificate;
import com.zsmartsystems.zigbee.security.ZigBeeCbkeCertificate;
import com.zsmartsystems.zigbee.security.ZigBeeCbkeProvider;

/**
 * Handles management of link keys
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeConsoleCbkeSetCommand extends ZigBeeConsoleAbstractCommand {
    @Override
    public String getCommand() {
        return "cbke";
    }

    @Override
    protected ZigBeeConsoleArgument initializeArguments() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getDescription() {
        return "Sets the CBKE certificate key int the from the join code";
    }

    @Override
    public String getSyntax() {
        return "CERTIFICATE";
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

        ZigBeeCbkeCertificate certificate = new CerticomCbkeCertificate(args[1]);

        SmartEnergyClient seClient = (SmartEnergyClient) networkManager.getExtension(SmartEnergyClient.class);
        if (seClient == null) {
            throw new IllegalStateException("Smart Energy not found");
        }

        ZigBeeCbkeProvider certificateProvider = seClient.getCbkeProvider();
        boolean result = certificateProvider.addCertificate(certificate);

        out.println("Certificate type " + certificate.getCryptoSuite() + " was " + (result ? "" : "not") + " set.");
    }
}

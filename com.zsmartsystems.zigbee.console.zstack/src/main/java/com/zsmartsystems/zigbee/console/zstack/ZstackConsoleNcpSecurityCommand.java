/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console.zstack;

import java.io.PrintStream;

import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.dongle.zstack.ZstackNcp;
import com.zsmartsystems.zigbee.dongle.zstack.api.sys.ZstackConfigId;
import com.zsmartsystems.zigbee.dongle.zstack.api.sys.ZstackNwkKeyDesc;
import com.zsmartsystems.zigbee.dongle.zstack.api.sys.ZstackSecMgrEntry;
import com.zsmartsystems.zigbee.dongle.zstack.api.util.ZstackUtilGetNvInfoSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.internal.serializer.ZstackDeserializer;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZstackConsoleNcpSecurityCommand extends ZstackConsoleAbstractCommand {
    private static int INVALID_NODE_ADDR = 0xFFFE;

    @Override
    public String getCommand() {
        return "ncpsecurity";
    }

    @Override
    public String getDescription() {
        return "Gets the NCP security information.";
    }

    @Override
    public String getSyntax() {
        return "";
    }

    @Override
    public String getHelp() {
        return "";
    }

    @Override
    public void process(ZigBeeNetworkManager networkManager, String[] args, PrintStream out)
            throws IllegalArgumentException {
        ZstackNcp ncp = getZstackNcp(networkManager);

        ZstackUtilGetNvInfoSrsp nvInfo = ncp.getNvDeviceInfo();
        int[] nwkKeyConfig = ncp.readConfiguration(ZstackConfigId.ZCD_NV_NWKKEY);
        int[] altKeyInfo = ncp.readConfiguration(ZstackConfigId.ZCD_NV_NWK_ALTERN_KEY_INFO);
        int[] nwkActiveKeyInfo = ncp.readConfiguration(ZstackConfigId.ZCD_NV_NWK_ACTIVE_KEY_INFO);

        int[] apsKeyTable = ncp.readConfiguration(ZstackConfigId.ZCD_NV_APS_LINK_KEY_TABLE);
        // apsKeyTable contains a 2 byte header which is an integer defining the number of entries
        ZstackDeserializer deserializer = new ZstackDeserializer(apsKeyTable);
        int apsKeyTableLen = deserializer.deserializeUInt16();
        for (int cnt = 0; cnt < apsKeyTableLen; cnt++) {
            ZstackSecMgrEntry secMgrEntry = new ZstackSecMgrEntry();
            secMgrEntry.deserialize(deserializer);
        }

        if (nvInfo == null) {
            out.println("NV Device info            : ERROR");
        } else {
            out.println("Preconfigured Network Key : " + nvInfo.getPreConfigKey());
        }

        if (nwkKeyConfig == null) {
            out.println("NWK Key Info              : Not Supported");
        } else {
            out.println("NWK Key Info              : TODO: " + hexDump(nwkKeyConfig));
        }

        if (altKeyInfo != null) {
            deserializer = new ZstackDeserializer(nwkActiveKeyInfo);
            ZstackNwkKeyDesc nwkActiveKey = new ZstackNwkKeyDesc();
            nwkActiveKey.deserialize(deserializer);
            out.println("Active NWK Key Info       : Sequence=" + nwkActiveKey.getKeySeqNum() + ", Key="
                    + nwkActiveKey.getKey());
        }

        if (altKeyInfo != null) {
            deserializer = new ZstackDeserializer(nwkActiveKeyInfo);
            ZstackNwkKeyDesc altKeyDesc = new ZstackNwkKeyDesc();
            altKeyDesc.deserialize(deserializer);
            out.println("Alt NWK Key Info          : Sequence=" + altKeyDesc.getKeySeqNum() + ", Key="
                    + altKeyDesc.getKey());
        }

        if (apsKeyTable == null) {
            out.println("APS Key Info              : Not Supported");
        } else {
            out.println("APS Key Info              : TODO: " + hexDump(apsKeyTable));
        }
    }

}

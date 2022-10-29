/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console.zstack;

import java.io.PrintStream;

import com.zsmartsystems.zigbee.ZigBeeChannelMask;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.dongle.zstack.ZstackNcp;
import com.zsmartsystems.zigbee.dongle.zstack.api.sys.ZstackConfigId;
import com.zsmartsystems.zigbee.dongle.zstack.api.sys.ZstackSysPingSreq;
import com.zsmartsystems.zigbee.dongle.zstack.api.sys.ZstackSysPingSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.sys.ZstackSysVersionSreq;
import com.zsmartsystems.zigbee.dongle.zstack.api.sys.ZstackSysVersionSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.util.ZstackUtilGetDeviceInfoSreq;
import com.zsmartsystems.zigbee.dongle.zstack.api.util.ZstackUtilGetDeviceInfoSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.util.ZstackUtilGetNvInfoSreq;
import com.zsmartsystems.zigbee.dongle.zstack.api.util.ZstackUtilGetNvInfoSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.zdo.ZstackZdoExtNwkInfoSreq;
import com.zsmartsystems.zigbee.dongle.zstack.api.zdo.ZstackZdoExtNwkInfoSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.internal.ZstackProtocolHandler;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZstackConsoleNcpStateCommand extends ZstackConsoleAbstractCommand {
    @Override
    public String getCommand() {
        return "ncpstate";
    }

    @Override
    public String getDescription() {
        return "Gets the NCP network state.";
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
        ncpState(networkManager, out);
    }

    private void ncpState(ZigBeeNetworkManager networkManager, PrintStream out) {
        final ZstackProtocolHandler handler = getProtocolHandler(networkManager);

        ZstackUtilGetNvInfoSrsp nvInfo = handler.sendTransaction(new ZstackUtilGetNvInfoSreq(), ZstackUtilGetNvInfoSrsp.class);
        ZstackUtilGetDeviceInfoSrsp deviceInfo = handler.sendTransaction(new ZstackUtilGetDeviceInfoSreq(), ZstackUtilGetDeviceInfoSrsp.class);
        ZstackSysVersionSrsp ncpVersion = handler.sendTransaction(new ZstackSysVersionSreq(), ZstackSysVersionSrsp.class);
        ZstackSysPingSrsp pingResponse = handler.sendTransaction(new ZstackSysPingSreq(), ZstackSysPingSrsp.class);
        ZstackZdoExtNwkInfoSrsp networkInfo = handler.sendTransaction(new ZstackZdoExtNwkInfoSreq(), ZstackZdoExtNwkInfoSrsp.class);

        final ZstackNcp ncp = new ZstackNcp(handler);

        int[] userDesc = ncp.readConfiguration(ZstackConfigId.ZCD_NV_USERDESC);
        int[] bdbDeviceOnNwk = ncp.readConfiguration(ZstackConfigId.ZCD_NV_BDBNODEISONANETWORK);
        int[] concentratorRadius = ncp.readConfiguration(ZstackConfigId.ZCD_NV_CONCENTRATOR_RADIUS);
        int[] concentratorEnable = ncp.readConfiguration(ZstackConfigId.ZCD_NV_CONCENTRATOR_ENABLE);
        int[] concentratorRc = ncp.readConfiguration(ZstackConfigId.ZCD_NV_CONCENTRATOR_RC);
        int[] concentratorDiscovery = ncp.readConfiguration(ZstackConfigId.ZCD_NV_CONCENTRATOR_DISCOVERY);
        int[] routeExpiry = ncp.readConfiguration(ZstackConfigId.ZCD_NV_ROUTE_EXPIRY_TIME);
        int[] stackProfile = ncp.readConfiguration(ZstackConfigId.ZCD_NV_STACK_PROFILE);
        int[] tcAddress = ncp.readConfiguration(ZstackConfigId.ZCD_NV_TRUSTCENTER_ADDR);

        out.println("BDB Device On Network            : " + hex2Boolean(bdbDeviceOnNwk));
        out.println("Stack Profile                    : " + hex2Uint8(stackProfile));
        out.println("Trust Centre Address             : " + hex2Uint8(routeExpiry));

        out.println("Route Expiry                     : " + hex2Uint16(tcAddress));

        out.println("Concentrator Enabled             : " + hex2Boolean(concentratorEnable));
        out.println("Concentrator Radius              : " + hex2Uint8(concentratorRadius));
        out.println("Concentrator Discovery Enabled   : " + hex2Boolean(concentratorDiscovery));
        out.println("Concentrator Route Cache Enabled : " + hex2Boolean(concentratorRc));

        if (deviceInfo == null) {
            out.println("Device info                      : ERROR");
        } else {
            out.println("Device State                     : " + deviceInfo.getDeviceState());
            out.println("NWK Address                      : " + deviceInfo.getShortAddr());
            out.println("IEEE Address                     : " + deviceInfo.getIeeeAddress());
        }

        if (nvInfo == null) {
            out.println("NV Device info                   : ERROR");
        } else {
            ZigBeeChannelMask channels = new ZigBeeChannelMask(nvInfo.getScanChannels());
            out.println("PAN ID                           : " + nvInfo.getPanId());
            out.println("Scan Channel                     : " + channels);
            out.println("Preconfigured Key                : " + nvInfo.getPreConfigKey());
            out.println("NV IEEE Address                  : " + nvInfo.getIeeeAddress());
        }

        if (ncpVersion == null) {
            out.println("NCP Version                      : ERROR");
        } else {
            out.println("Firmware version                 : " + ncpVersion.getMajorRel() + "."
                    + ncpVersion.getMinorRel() + "." + ncpVersion.getMaintRel());
            out.println("Product version                  : " + ncpVersion.getProduct());
            out.println("Transport version                : " + ncpVersion.getTransportRev());
        }

        if (pingResponse == null) {
            out.println("NCP API Capabilities             : ERROR");
        } else {
            out.println("NCP API Capabilities             : " + pingResponse.getCapabilities());
        }

        if (networkInfo == null) {
            out.println("NCP NWK Info                   : ERROR");
        } else {
            out.println("NWK Info PanId                   : " + networkInfo.getPanId());
            out.println("NWK Info Extended PanId          : " + networkInfo.getExtendedPanId());
            out.println("NWK Info Current Channel         : " + networkInfo.getChannel());
            out.println("NWK Info NwkAddress              : " + networkInfo.getShortAddress());
        }

        out.println("User Description                 : " + hex2String(userDesc));
    }
}

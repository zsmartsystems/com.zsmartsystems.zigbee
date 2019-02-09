/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zdo.command;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zdo.ZdoResponse;

/**
 * Management Network Discovery Response value object class.
 * <p>
 * <p>
 * The Mgmt_NWK_Disc_rsp is generated in response to an Mgmt_NWK_Disc_req. If this
 * management command is not supported, a status of NOT_SUPPORTED shall be returned and all
 * parameter fields after the Status field shall be omitted. Otherwise, the Remote Device
 * shall implement the following process. <br> Upon receipt of and after support for the
 * Mgmt_NWK_Disc_req has been verified, the Remote Device shall issue an
 * NLME-NETWORKDISCOVERY.request primitive using the ScanChannels and ScanDuration
 * parameters, supplied in the Mgmt_NWK_Disc_req command. Upon receipt of the
 * NLME-NETWORK-DISCOVERY.confirm primitive, the Remote Device shall report the results,
 * starting with the StartIndex element, via the Mgmt_NWK_Disc_rsp command. The NetworkList
 * field shall contain whole NetworkList records, until the limit on MSDU size, i.e.,
 * aMaxMACFrameSize, is reached. The number of results reported shall be set in the
 * NetworkListCount.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class ManagementNetworkDiscoveryResponse extends ZdoResponse {
    /**
     * Default constructor.
     */
    public ManagementNetworkDiscoveryResponse() {
        clusterId = 0x8030;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(37);
        builder.append("ManagementNetworkDiscoveryResponse [");
        builder.append(super.toString());
        builder.append(']');
        return builder.toString();
    }

}

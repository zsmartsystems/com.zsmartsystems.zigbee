/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zdo.ZdoResponse;

/**
 * Management Network Update Notify value object class.
 * <p>
 * The Mgmt_NWK_Update_notify is provided to enable ZigBee devices to report
 * the condition on local channels to a network manager. The scanned channel list is
 * the report of channels scanned and it is followed by a list of records, one for each
 * channel scanned, each record including one byte of the energy level measured
 * during the scan, or 0xff if there is too much interference on this channel.
 * <br>
 * When sent in response to a Mgmt_NWK_Update_req command the status field
 * shall represent the status of the request. When sent unsolicited the status field
 * shall be set to SUCCESS.
 * A Status of NOT_SUPPORTED indicates that the request was directed to a device
 * which was not the ZigBee Coordinator or that the ZigBee Coordinator does not
 * support End Device Binding. Otherwise, End_Device_Bind_req processing is
 * performed as described below, including transmission of the
 * End_Device_Bind_rsp.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class ManagementNetworkUpdateNotify extends ZdoResponse {
    /**
     * Default constructor.
     */
    public ManagementNetworkUpdateNotify() {
        clusterId = 0x8038;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("ManagementNetworkUpdateNotify [");
        builder.append(super.toString());
        builder.append("]");
        return builder.toString();
    }

}

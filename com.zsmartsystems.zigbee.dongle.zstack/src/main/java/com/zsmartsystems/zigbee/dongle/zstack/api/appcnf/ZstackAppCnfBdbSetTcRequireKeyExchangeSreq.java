/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.zstack.api.appcnf;

import com.zsmartsystems.zigbee.dongle.zstack.api.ZstackFrameRequest;
import com.zsmartsystems.zigbee.dongle.zstack.api.rpc.ZstackRpcSreqErrorSrsp;

/**
 * Class to implement the Z-Stack command <b>APP_CNF_BDB_SET_TC_REQUIRE_KEY_EXCHANGE</b>.
 * <p>
 * Sets the policy flag on Trust Center device to mandate or not the TCLK exchange procedure.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson
 */
public class ZstackAppCnfBdbSetTcRequireKeyExchangeSreq extends ZstackFrameRequest {

    /**
     * The bdbTrustCenterRequireKeyExchange attribute specifies whether the Trust Center requires a joining device to exchange
     * its initial link key with a new link key generated by the Trust Center. If bdbTrustCenterRequireKeyExchange is equal to TRUE,
     * the joining node must undergo the link key exchange procedure; failure to exchange the link key will result in the node being
     * removed from the network. If bdbTrustCenterRequireKeyExchange is equal to FALSE, the Trust Center will permit the joining
     * node to remain on the network without exchanging its initial link key. This attribute is used by ZigBee coordinator nodes.
     */
    private boolean trustCenterRequireKeyExchange;

    /**
     * Request constructor
     */
    public ZstackAppCnfBdbSetTcRequireKeyExchangeSreq() {
        synchronousCommand = true;
    }

    /**
     * The bdbTrustCenterRequireKeyExchange attribute specifies whether the Trust Center requires a joining device to exchange
     * its initial link key with a new link key generated by the Trust Center. If bdbTrustCenterRequireKeyExchange is equal to TRUE,
     * the joining node must undergo the link key exchange procedure; failure to exchange the link key will result in the node being
     * removed from the network. If bdbTrustCenterRequireKeyExchange is equal to FALSE, the Trust Center will permit the joining
     * node to remain on the network without exchanging its initial link key. This attribute is used by ZigBee coordinator nodes.
     *
     * @return the current trustCenterRequireKeyExchange as {@link boolean}
     */
    public boolean getTrustCenterRequireKeyExchange() {
        return trustCenterRequireKeyExchange;
    }

    /**
     * The bdbTrustCenterRequireKeyExchange attribute specifies whether the Trust Center requires a joining device to exchange
     * its initial link key with a new link key generated by the Trust Center. If bdbTrustCenterRequireKeyExchange is equal to TRUE,
     * the joining node must undergo the link key exchange procedure; failure to exchange the link key will result in the node being
     * removed from the network. If bdbTrustCenterRequireKeyExchange is equal to FALSE, the Trust Center will permit the joining
     * node to remain on the network without exchanging its initial link key. This attribute is used by ZigBee coordinator nodes.
     *
     * @param trustCenterRequireKeyExchange the TrustCenterRequireKeyExchange to set as {@link boolean}
     */
    public void setTrustCenterRequireKeyExchange(boolean trustCenterRequireKeyExchange) {
        this.trustCenterRequireKeyExchange = trustCenterRequireKeyExchange;
    }

    @Override
    public boolean matchSreqError(ZstackRpcSreqErrorSrsp response) {
        return (((response.getReqCmd0() & 0x1F) == ZSTACK_APP_CNF) && (response.getReqCmd1() == 0x09));
    }

    @Override
    public int[] serialize() {
        // Serialize the header
        serializeHeader(ZSTACK_SREQ, ZSTACK_APP_CNF, 0x09);

        // Serialize the fields
        serializeBoolean(trustCenterRequireKeyExchange);
        return getPayload();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(70);
        builder.append("ZstackAppCnfBdbSetTcRequireKeyExchangeSreq [trustCenterRequireKeyExchange=");
        builder.append(trustCenterRequireKeyExchange);
        builder.append(']');
        return builder.toString();
    }
}

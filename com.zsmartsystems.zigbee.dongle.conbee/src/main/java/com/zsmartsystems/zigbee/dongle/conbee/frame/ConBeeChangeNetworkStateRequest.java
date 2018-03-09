/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.conbee.frame;

/**
 * The following two behaviors are possible:
 * <ol>
 * <li>NET_OFFLINE→NET_JOINING→NET_CONNECTED
 * <li>NET_OFFLINE→NET_JOINING→NET_OFFLINE
 * </ol>
 * The second transition may occur when the device can’t join a network, due to invalid parameters or because the
 * network is not opened — which, in ZigBee terms, means no node in the network has its ‘Permit Join’ flag set.
 *
 * @author Chris Jackson
 *
 */
public class ConBeeChangeNetworkStateRequest extends ConBeeFrameRequest {
    private ConBeeNetworkState state;

    public void setState(ConBeeNetworkState state) {
        this.state = state;
    }

    @Override
    public int[] getOutputBuffer() {
        super.getOutputBuffer();

        serializeUInt8(CHANGE_NETWORK_STATE);
        serializeUInt8(sequence);
        serializeUInt8(0);
        serializeUInt16(6);
        serializeUInt8(state.ordinal());

        return copyOutputBuffer();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(40);
        builder.append("ChangeNetworkStateRequest [sequence=");
        builder.append(sequence);
        builder.append(']');
        return builder.toString();
    }
}

/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.conbee.frame;

/**
 *
 * @author Chris Jackson
 *
 */
public class ConBeeDeviceState {
    private final ConBeeNetworkState networkState;
    private final boolean dataConfirm;
    private final boolean dataIndication;
    private final boolean dataRequest;
    private final boolean configChanged;

    ConBeeDeviceState(int state) {
        networkState = ConBeeNetworkState.values()[state & 0x03];
        dataConfirm = (state & 0x04) != 0;
        dataIndication = (state & 0x08) != 0;
        configChanged = (state & 0x10) != 0;
        dataRequest = (state & 0x20) != 0;
    }

    /**
     * @return the networkState
     */
    public ConBeeNetworkState getNetworkState() {
        return networkState;
    }

    /**
     * @return the dataConfirm
     */
    public boolean isDataConfirm() {
        return dataConfirm;
    }

    /**
     * @return the dataIndication
     */
    public boolean isDataIndication() {
        return dataIndication;
    }

    /**
     * @return the dataRequest
     */
    public boolean isDataRequest() {
        return dataRequest;
    }

    /**
     * @return the configChanged
     */
    public boolean isConfigChanged() {
        return configChanged;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(120);
        builder.append("DeviceState [networkState=");
        builder.append(networkState);
        builder.append(", dataConfirm=");
        builder.append(dataConfirm);
        builder.append(", dataIndication=");
        builder.append(dataIndication);
        builder.append(", dataRequest=");
        builder.append(dataRequest);
        builder.append(", configChanged=");
        builder.append(configChanged);
        builder.append(']');
        return builder.toString();
    }
}

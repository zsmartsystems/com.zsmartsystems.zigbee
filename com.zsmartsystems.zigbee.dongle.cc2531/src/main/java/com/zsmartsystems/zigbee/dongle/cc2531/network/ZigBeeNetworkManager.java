/*
   Copyright 2008-2013 CNR-ISTI, http://isti.cnr.it
   Institute of Information Science and Technologies
   of the Italian National Research Council


   See the NOTICE file distributed with this work for additional
   information regarding copyright ownership

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/

package com.zsmartsystems.zigbee.dongle.cc2531.network;

import com.zsmartsystems.zigbee.ZigBeePort;
import com.zsmartsystems.zigbee.dongle.cc2531.network.impl.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.dongle.cc2531.network.model.DriverStatus;
import com.zsmartsystems.zigbee.dongle.cc2531.network.model.NetworkMode;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.af.AF_DATA_CONFIRM;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.af.AF_DATA_REQUEST;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.af.AF_REGISTER;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.af.AF_REGISTER_SRSP;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_ACTIVE_EP_REQ;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_ACTIVE_EP_RSP;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_BIND_REQ;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_BIND_RSP;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_IEEE_ADDR_REQ;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_IEEE_ADDR_RSP;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_MGMT_LQI_REQ;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_MGMT_LQI_RSP;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_NODE_DESC_REQ;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_NODE_DESC_RSP;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_POWER_DESC_REQ;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_POWER_DESC_RSP;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_SIMPLE_DESC_REQ;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_SIMPLE_DESC_RSP;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_UNBIND_REQ;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_UNBIND_RSP;

/**
 * @author <a href="mailto:stefano.lenzi@isti.cnr.it">Stefano "Kismet" Lenzi - ISTI-CNR</a>
 * @author <a href="mailto:manlio.bacco@isti.cnr.it">Manlio Bacco - ISTI-CNR</a>
 * @author <a href="mailto:christopherhattonuk@gmail.com">Chris Hatton</a>
 * @author <a href="mailto:chris@cd-jackson.com>Chris Jackson</a>
 */
public interface ZigBeeNetworkManager {
    /**
     * Sets network mode.
     *
     * @param networkMode the network mode
     */
    void setZigBeeNodeMode(NetworkMode networkMode);

    /**
     * Sets serial port.
     *
     * @param port the serial port.
     */
    void setSerialPort(ZigBeePort port);

    /**
     * Starts up network manager.
     *
     * @return
     */
    boolean startup();

    /**
     * Shuts down network manager.
     */
    void shutdown();

    /**
     * Sends ZDO_IEEE_ADDR_REQ.
     *
     * @param request the ZDO_IEEE_ADDR_REQ
     * @return ZDO_IEEE_ADDR_RSP
     */
    ZDO_IEEE_ADDR_RSP sendZDOIEEEAddressRequest(ZDO_IEEE_ADDR_REQ request);

    /**
     * Sends ZDO_NODE_DESC_REQ.
     *
     * @param request the ZDO_NODE_DESC_REQ
     * @return ZDO_NODE_DESC_RSP
     */
    ZDO_NODE_DESC_RSP sendZDONodeDescriptionRequest(ZDO_NODE_DESC_REQ request);

    /**
     * Sends ZDO_POWER_DESC_REQ.
     *
     * @param request the ZDO_POWER_DESC_REQ
     * @return ZDO_POWER_DESC_RSP
     */
    ZDO_POWER_DESC_RSP sendZDOPowerDescriptionRequest(ZDO_POWER_DESC_REQ request);

    /**
     * Sends ZDO_ACTIVE_EP_REQ.
     *
     * @param request the ZDO_ACTIVE_EP_REQ
     * @return ZDO_ACTIVE_EP_RSP
     */
    ZDO_ACTIVE_EP_RSP sendZDOActiveEndPointRequest(ZDO_ACTIVE_EP_REQ request);

    /**
     * Sends ZDO_SIMPLE_DESC_REQ.
     *
     * @param request the ZDO_SIMPLE_DESC_REQ
     * @return ZDO_SIMPLE_DESC_RSP
     */
    ZDO_SIMPLE_DESC_RSP sendZDOSimpleDescriptionRequest(ZDO_SIMPLE_DESC_REQ request);

    /**
     * Sends AF_REGISTER.
     *
     * @param request the AF_REGISTER
     * @return AF_REGISTER_SRSP
     */
    AF_REGISTER_SRSP sendAFRegister(AF_REGISTER request);

    /**
     * Sends AF_DATA_REQUEST.
     *
     * @param request the AF_DATA_REQUEST
     * @return AF_DATA_CONFIRM
     */
    AF_DATA_CONFIRM sendAFDataRequest(AF_DATA_REQUEST request);

    /**
     * Sends ZDO_BIND_REQ
     *
     * @param request the ZDO_BIND_REQ
     * @return ZDO_BIND_RSP
     */
    ZDO_BIND_RSP sendZDOBind(ZDO_BIND_REQ request);

    /**
     * Sends ZDO_UNBIND_REQ.
     *
     * @param request the ZDO_UNBIND_REQ
     * @return ZDO_UNBIND_RSP
     */
    ZDO_UNBIND_RSP sendZDOUnbind(ZDO_UNBIND_REQ request);

    /**
     * Removes application framework message listener.
     *
     * @param listener the listener
     * @return true if remove was successful
     */
    boolean removeAFMessageListener(ApplicationFrameworkMessageListener listener);

    /**
     * Add application framework message listener.
     *
     * @param listener the listener
     * @return true if add was successful
     */
    boolean addAFMessageListener(ApplicationFrameworkMessageListener listener);

    /**
     * Send LQI request cluster and wait for its response
     * <p>
     * This method is used for the discovering of {@link ZigBeeEndpoint}
     *
     * @return the answer to the request or null in case of an error
     */
    ZDO_MGMT_LQI_RSP sendLQIRequest(ZDO_MGMT_LQI_REQ request);

    /**
     * <b>WARNING</b>: This method may have to wait for the initialization of the ZigBee network
     * thus, it may be quite slow or end up in a deadlock of the application
     *
     * @return The long representing the IEEE Address of coordinator of the ZigBee network in use, or -1 if and only if
     *         the method failed
     */
    long getCurrentExtendedPanId();

    /**
     * <b>WARNING</b>: This method may have to wait for the initialization of the ZigBee network
     * thus, it may be quite slow or end up in a deadlock of the application
     *
     * @return The long representing the IEEE Address of ZigBee device in use, or -1 if and only if the method failed
     */
    long getIeeeAddress();

    /**
     * <b>WARNING</b>: This method may have to wait for the initialization of the ZigBee network
     * thus, it may be quite slow or end up in a deadlock of the application
     *
     * @return The panId of ZigBee network configured in use, or -1 if and only if the method failed
     */
    int getCurrentPanId();

    /**
     * <b>WARNING</b>: This method may have to wait for the initialization of the ZigBee network
     * thus, it may be quite slow or end up in a deadlock of the application
     *
     * @return The ZigBee network channel in use, or -1 if and only if the method failed
     */
    int getCurrentChannel();

    boolean setNetworkKey(byte[] networkKey);

    public boolean setZigBeeExtendedPanId(long extendedPanId);

    boolean setDistributeNetworkKey(boolean distributeNetworkKey);

    boolean setSecurityMode(int securityMode);

    boolean setZigBeeChannel(int channel);

    /**
     * Set the PAN ID
     *
     * @param panId
     * @return
     */
    boolean setZigBeePanId(int panId);

    /**
     * <b>WARNING</b>: This method may have to wait for the initialization of the ZigBee network
     * thus, it may be quite slow or end up in a deadlock of the application
     *
     * @return The int representation of the {@link NetworkMode} in use, or -1 if and only if the method failed
     */
    int getZigBeeNodeMode();

    /**
     * @return The current status of the driver
     */
    DriverStatus getDriverStatus();

    /**
     * Adds asynchronous command listener.
     *
     * @param asynchronousCommandListener the asynchronous command listener
     */
    void addAsynchronousCommandListener(AsynchronousCommandListener asynchronousCommandListener);
}
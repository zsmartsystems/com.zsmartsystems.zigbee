/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.internal.otaserver;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.ZigBeeCommand;
import com.zsmartsystems.zigbee.ZigBeeCommandListener;
import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeException;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNode;
import com.zsmartsystems.zigbee.zcl.clusters.otaupgrade.ImageNotifyCommand;
import com.zsmartsystems.zigbee.zcl.clusters.otaupgrade.ImagePageRequestCommand;
import com.zsmartsystems.zigbee.zcl.clusters.otaupgrade.QueryNextImageRequestCommand;
import com.zsmartsystems.zigbee.zcl.clusters.otaupgrade.UpgradeEndRequestCommand;
import com.zsmartsystems.zigbee.zdo.field.NodeDescriptor;

/**
 * This class implements the logic to implement the Over The Air (OTA) server for a ZigBee node.
 * <p>
 * OTA upgrade messages do not differ from typical ZigBee APS messages so the upgrade process should
 * not interrupt the general network operation.
 * <p>
 * OTA Upgrade cluster commands, the frame control value shall follow the description below:
 * <ul>
 * <li>Frame type is 0x01: commands are cluster specific (not a global command).
 * <li>Manufacturer specific is 0x00: commands are not manufacturer specific.
 * <li>Direction: shall be either 0x00 (client->server) or 0x01 (server->client) depending on the commands.
 * <li>Disable default response is 0x00 for all OTA request commands sent from client to server:
 * default response command shall be sent when the server receives OTA Upgrade cluster
 * request commands that it does not support or in case an error case happens. A detailed
 * explanation of each error case along with its recommended action is described for each OTA
 * cluster command.
 * <li>Disable default response is 0x01 for all OTA response commands (sent from server to client)
 * and for broadcast/multicast Image Notify command: default response command is not sent
 * when the client receives a valid OTA Upgrade cluster response commands or when it receives
 * broadcast or multicast Image Notify command. However, if a client receives invalid OTA
 * Upgrade cluster response command, a default response shall be sent. A detailed explanation of
 * each error case along with its recommended action is described for each OTA cluster
 * command.
 * </ul>
 * <p>
 * Process overview
 * <ul>
 * <li>Server sends <i>Image Notify</i> [optional]
 * <li>Client sends <i>Query Next Image Request</i>
 * <li>Server sends <i>Query Next Image Response</i>
 * <li>Client sends <i>Image Block/Page Request</i>
 * <li>Server sends <i>Image Block Response</i>
 * <li>Client sends <i>Image Block/Page Request</i>
 * <li>Server sends <i>Image Block Response</i>
 * <li>... repeat to end of transfer
 * <li>Client sends <i>Upgrade End Request</i>
 * <li>Server sends <i>Upgrade End Response</i>
 * </ul>
 * <p>
 * This class uses the {@link ZigBeeOtaCluster} which provides the low level commands.
 *
 * @author Chris Jackson
 */
class ZigBeeOtaServer implements ZigBeeCommandListener {
    /**
     * The logger.
     */
    private final Logger logger = LoggerFactory.getLogger(ZigBeeOtaServer.class);

    /**
     * The parameter is part of Image Notify Command sent by the upgrade server. The parameter indicates
     * whether the client receiving Image Notify Command should send in Query Next Image Request
     * command or not.
     * <p>
     * The server chooses the parameter value between 1 and 100 (inclusively) and includes it in the Image
     * Notify Command. On receipt of the command, the client will examine other information (the
     * manufacturer code and image type) to determine if they match its own values. If they do not, it shall
     * discard the command and no further processing shall continue. If they do match then it will determine
     * whether or not it should query the upgrade server. It does this by randomly choosing a number
     * between 1 and 100 and comparing it to the value of the QueryJitter parameter received. If it is less than
     * or equal to the QueryJitter value from the server, it shall continue with the query process. If not, then it
     * shall discard the command and no further processing shall continue.
     * <p>
     * By using the QueryJitter parameter, it prevents a single notification of a new OTA upgrade image from
     * flooding the upgrade server with requests from clients. Default value is 50.
     */
    private Integer queryJitter;

    /**
     * A value that indicates the length of the OTA image data included in the (Image Block Response)
     * command payload sent from the server to client. Default value is 0xff.
     */
    private Integer dataSize = 0xff;

    /**
     * The {@link ZigBeeNetworkManager}
     */
    private final ZigBeeNetworkManager networkManager;

    /**
     * The {@link ZigBeeEndpoint} to which this server process belongs
     */
    private final ZigBeeEndpoint device;

    /**
     * The node descriptor for this device
     */
    private NodeDescriptor nodeDescriptor;

    /**
     * Callback to receive status updates on the progress of a transfer
     */
    private final ZigBeeOtaStatusCallback callback;

    /**
     * The current firmware for this node.
     */
    private ZigBeeOtaFile otaFile = null;

    /**
     * Constructor taking the {@link ZigBeeNode} that is to be updated.
     *
     * @param networkManager the {@link ZigBeeNetworkManager}
     * @param node the {@link ZigBeeNode} to be updated
     * @param callback A (@link ZigBeeOtaStatusCallback} to receive status and progress updates
     */
    public ZigBeeOtaServer(final ZigBeeNetworkManager networkManager, final ZigBeeEndpoint device,
            final ZigBeeOtaStatusCallback callback) {
        this.networkManager = networkManager;
        this.device = device;
        this.callback = callback;

        // queryJitter needs to be a random value between 1 and 100
        this.queryJitter = new Random().nextInt(100);

        ZigBeeNode node = networkManager.getNode(device.getIeeeAddress());
        nodeDescriptor = node.getNodeDescriptor();

        // Register a listener with the node to receive cluster messages
        networkManager.addCommandListener(this);
    }

    /**
     * Sets the firmware file for this node and send a notification to the device.
     * <p>
     * The file must confirm to the standard file format containing the OTA Header, upgrade image, signer certificate,
     * signature.
     *
     * @param otaFile the current firmware version for this node
     */
    public void setFirmware(ZigBeeOtaFile otaFile) {
        this.otaFile = otaFile;
        notifyClient();
    }

    /**
     * The purpose of sending Image Notify command is so the server has a way to notify client devices of
     * when the OTA upgrade images are available for them. It eliminates the need for ZR client devices
     * having to check with the server periodically of when the new images are available. However, all client
     * devices still need to send in Query Next Image Request command in order to officially start the OTA
     * upgrade process.
     */
    public void notifyClient() {
        // Only send the notify if the file is set
        if (otaFile == null) {
            return;
        }

        ImageNotifyCommand notifyCommand = new ImageNotifyCommand();
        notifyCommand.setQueryJitter(queryJitter);
        notifyCommand.setDestinationAddress(device.getDeviceAddress());
        notifyCommand.setManufacturerCode(nodeDescriptor.getManufacturerCode());
        notifyCommand.setImageType(0xffff);

        try {
            networkManager.sendCommand(notifyCommand);
        } catch (ZigBeeException e) {
            logger.debug("Exception sending OTA notify client message: ", e);
        }
    }

    /**
     * Sets the data size used in sending data to the client. Allowable range is 0 to 255.
     * <p>
     * A value that indicates the length of the OTA image data included in the (Image Block Response)
     * command payload sent from the server to client.
     *
     * @param dataSize the size of each data packet (0 to 255)
     */
    public void setDataSize(int dataSize) {
        this.dataSize = dataSize;
    }

    /**
     * Handle a received {@link QueryNextImageRequestCommand} command
     */
    private void handleQueryNextImage() {

    }

    /**
     * Handle a received {@link ImagePageRequestCommand} command
     */
    private void handleImagePageRequest() {

    }

    /**
     * Handle a received {@link UpgradeEndRequestCommand} command
     */
    private void handleUpgradeEndRequest() {

    }

    @Override
    public void commandReceived(final ZigBeeCommand command) {
        // This gets called for all received commands
        // Check if it's our address
        if (command.getSourceAddress() != device.getDeviceAddress()) {
            return;
        }

        if (command instanceof QueryNextImageRequestCommand) {
            handleQueryNextImage();
        }

        if (command instanceof ImagePageRequestCommand) {
            handleImagePageRequest();
        }

        if (command instanceof UpgradeEndRequestCommand) {
            handleUpgradeEndRequest();
        }
    }

}
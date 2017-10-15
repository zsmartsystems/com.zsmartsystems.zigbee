/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.otaserver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.ZigBeeCommand;
import com.zsmartsystems.zigbee.ZigBeeCommandListener;
import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeException;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNode;
import com.zsmartsystems.zigbee.internal.NotificationService;
import com.zsmartsystems.zigbee.zcl.ZclStatus;
import com.zsmartsystems.zigbee.zcl.clusters.ZclOtaUpgradeCluster;
import com.zsmartsystems.zigbee.zcl.clusters.general.DefaultResponse;
import com.zsmartsystems.zigbee.zcl.clusters.otaupgrade.ImageBlockCommand;
import com.zsmartsystems.zigbee.zcl.clusters.otaupgrade.ImageBlockResponse;
import com.zsmartsystems.zigbee.zcl.clusters.otaupgrade.ImageNotifyCommand;
import com.zsmartsystems.zigbee.zcl.clusters.otaupgrade.ImagePageCommand;
import com.zsmartsystems.zigbee.zcl.clusters.otaupgrade.QueryNextImageCommand;
import com.zsmartsystems.zigbee.zcl.clusters.otaupgrade.QueryNextImageResponse;
import com.zsmartsystems.zigbee.zcl.clusters.otaupgrade.UpgradeEndCommand;
import com.zsmartsystems.zigbee.zcl.clusters.otaupgrade.UpgradeEndResponse;

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
 * Users can register with {@link #addListener} to receive {@link ZigBeeOtaStatusCallback} calls when the status
 * changes.
 * <p>
 * Upgrade lifecycle overview -:
 * <ul>
 * <li>Server instantiated. Status set to {@link ZigBeeOtaServerStatus#OTA_UNINITIALISED}.
 * <li>{@link #setFirmware(ZigBeeOtaFile)} is called to set the firmware. Status set to
 * {@link ZigBeeOtaServerStatus#OTA_WAITING}
 * <li>Server sends <i>Image Notify</i> when the firmware is set.
 * <li>User can call {@link #notifyClient()} periodically to send <i>Image Notify</i>.
 * <li>Client sends <i>Query Next Image Request</i>. Status set to
 * {@link ZigBeeOtaServerStatus#OTA_TRANSFER_IN_PROGRESS}.
 * <li>Server sends <i>Query Next Image Response</i>
 * <li>Client sends <i>Image Block/Page Request</i>
 * <li>Server sends <i>Image Block Response</i>
 * <li>Client sends <i>Image Block/Page Request</i>
 * <li>Server sends <i>Image Block Response</i>
 * <li>... repeat to end of transfer
 * <li>Client sends <i>Upgrade End Request</i>. Status set to {@link ZigBeeOtaServerStatus#OTA_TRANSFER_COMPLETE}.
 * <li>Server waits for {@link #completeUpgrade()} to be called unless
 * <li>Server sends <i>Upgrade End Response</i>. Status set to {@link ZigBeeOtaServerStatus#OTA_UPGRADE_COMPLETE}
 * <li>When new firmware becomes available, the process begins from the top.
 * </ul>
 * <p>
 * This class uses the {@link ZigBeeOtaCluster} which provides the low level commands.
 *
 * @author Chris Jackson
 */
public class ZigBeeOtaServer implements ZigBeeServer {
    /**
     * A static Thread pool is used here to ensure that we don't end up with large numbers of page requests
     * spawning multiple threads. This should ensure a level of pacing if we had a lot of devices on the network that
     * suddenly wanted to upgrade using page requests at the same time.
     */
    private static ScheduledExecutorService pageScheduledExecutor = Executors.newScheduledThreadPool(1);

    /**
     * The logger.
     */
    private final Logger logger = LoggerFactory.getLogger(ZigBeeOtaServer.class);

    /**
     * The current {@link ZigBeeOtaServerStatus} associated with this server.
     */
    private ZigBeeOtaServerStatus status;

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
    private ZigBeeNetworkManager networkManager;

    /**
     * The {@link ZigBeeEndpoint} to which this server process belongs
     */
    private final ZigBeeEndpoint device;

    /**
     * Callback to receive status updates on the progress of a transfer
     */
    private final ZigBeeOtaStatusCallback callback;

    /**
     * The current firmware for this node.
     */
    private ZigBeeOtaFile otaFile = null;

    /**
     * A boolean defining the autoUpgrade state. If true, the server will automatically upgrade
     * the firmware in a device once the transfer is complete. If false, the user must explicitly
     * call {@link #completeUpgrade()} to complete the upgrade.
     */
    private boolean autoUpgrade = false;

    /**
     * Our page scheduler task
     */
    private ScheduledFuture<?> scheduledPageTask;

    /**
     * A list of listeners to receive status callbacks
     */
    private List<ZigBeeOtaStatusCallback> statusListeners = Collections
            .unmodifiableList(new ArrayList<ZigBeeOtaStatusCallback>());

    /**
     * Constructor taking the {@link ZigBeeNode} that is to be updated.
     *
     * @param networkManager the {@link ZigBeeNetworkManager}
     * @param node the {@link ZigBeeNode} to be updated
     * @param callback A (@link ZigBeeOtaStatusCallback} to receive status and progress updates
     */
    public ZigBeeOtaServer(final ZigBeeNetworkManager networkManager, final ZigBeeEndpoint device) {
        this.networkManager = networkManager;
        this.device = device;

        status = ZigBeeOtaServerStatus.OTA_UNINITIALISED;

        // queryJitter needs to be a random value between 1 and 100
        this.queryJitter = new Random().nextInt(100);
    }

    @Override
    public boolean serverStartup(final ZigBeeNetworkManager networkManager, final ZigBeeNode zigbeeNode) {
        this.networkManager = networkManager;
        this.zigbeeNode = zigbeeNode;

        // Find the endpoint that contains the OTA cluster
        this.device = null;
        for (ZigBeeDevice device : networkManager.getNodeDevices(zigbeeNode.getIeeeAddress())) {
            if (device.getOutputClusterIds().contains(ZclOtaUpgradeCluster.CLUSTER_ID)) {
                this.device = device;
            }
        }
        if (this.device == null) {
            // No endpoint supporting the OTA client was found on this node
            return false;
        }

        return true;
    }

    @Override
    public void serverShutdown() {
    }

    @Override
    public int getClusterId() {
        return ZclOtaUpgradeCluster.CLUSTER_ID;
    }

    /**
     * Add a listener to receive status callbacks on the OTA server status.
     *
     * @param listener the {@link ZigBeeOtaStatusCallback} to receive the status
     */
    public void addListener(final ZigBeeOtaStatusCallback listener) {
        if (listener == null) {
            return;
        }
        synchronized (this) {
            final List<ZigBeeOtaStatusCallback> modifiedListeners = new ArrayList<ZigBeeOtaStatusCallback>(
                    statusListeners);
            modifiedListeners.add(listener);
            statusListeners = Collections.unmodifiableList(modifiedListeners);
        }
    }

    /**
     * Remove a listener from receiving status callbacks on the OTA server status.
     *
     * @param listener the {@link ZigBeeOtaStatusCallback} to stop receiving status callbacks
     */
    public void removeListener(final ZigBeeOtaStatusCallback listener) {
        synchronized (this) {
            final List<ZigBeeOtaStatusCallback> modifiedListeners = new ArrayList<ZigBeeOtaStatusCallback>(
                    statusListeners);
            modifiedListeners.remove(listener);
            statusListeners = Collections.unmodifiableList(modifiedListeners);
        }
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
        updateStatus(ZigBeeOtaServerStatus.OTA_WAITING);

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
        notifyCommand.setManufacturerCode(otaFile.getManufacturerCode());
        notifyCommand.setImageType(otaFile.getImageType());
        notifyCommand.setNewFileVersion(otaFile.getFileVersion());
        notifyCommand.setPayloadType(0);

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
     * Tells the server to automatically upgrade the firmware once the transfer is completed.
     * If autoUpgrade is not set, then the user must explicitly call {@link #doUpgrade} once the server
     * state has reached {@link ZigBeeOtaServerStatus#OTA_TRANSFER_COMPLETE}.
     *
     * @param autoUpgrade boolean defining the autoUpgrade state
     */
    public void setAutoUpgrade(boolean autoUpgrade) {
        this.autoUpgrade = autoUpgrade;
    }

    /**
     * Instruct the server to complete the upgrade. This call will fail if the server state is no
     * not {@link ZigBeeOtaServerStatus#OTA_TRANSFER_COMPLETE}.
     *
     * @return true if the upgrade is in a state to be completed, otherwise false.
     */
    public boolean completeUpgrade() {
        if (status != ZigBeeOtaServerStatus.OTA_TRANSFER_COMPLETE) {
            return false;
        }

        UpgradeEndResponse upgradeEndResponse = new UpgradeEndResponse();
        upgradeEndResponse.setDestinationAddress(device.getDeviceAddress());
        upgradeEndResponse.setImageType(otaFile.getImageType());
        upgradeEndResponse.setManufacturerCode(otaFile.getManufacturerCode());
        upgradeEndResponse.setFileVersion(otaFile.getFileVersion());

        try {
            networkManager.sendCommand(upgradeEndResponse);
        } catch (ZigBeeException e) {
            logger.debug("Exception sending OTA upgrade end message: ", e);
        }

        return false;
    }

    private void updateStatus(final ZigBeeOtaServerStatus updatedStatus) {
        synchronized (this) {
            // Notify the listeners
            for (final ZigBeeOtaStatusCallback statusListener : statusListeners) {
                NotificationService.execute(new Runnable() {
                    @Override
                    public void run() {
                        statusListener.otaStatusUpdate(updatedStatus);
                    }
                });
            }
        }

    }

    /**
     * Sends a default response to the client
     *
     * @param status the {@link ZclStatus} to send in the response
     */
    private void sendDefaultResponse(ZclStatus status) {
        DefaultResponse defaultResponse = new DefaultResponse();
        defaultResponse.setDestinationAddress(device.getDeviceAddress());
        defaultResponse.setClusterId(ZclOtaUpgradeCluster.CLUSTER_ID);
        defaultResponse.setStatusCode(status);

        try {
            networkManager.sendCommand(defaultResponse);
        } catch (ZigBeeException e) {
            logger.debug("Exception sending OTA default response message: ", e);
        }
    }

    /**
     * Sends an image block to the client
     *
     * @param fileOffset the offset into the {@link ZigBeeOtaFile} to send the block
     * @param maximumDataSize the maximum data size the client can accept
     * @return the number of bytes sent
     */
    private int sendImageBlock(int fileOffset, int maximumDataSize) {
        ImageBlockResponse imageBlockResponse = new ImageBlockResponse();
        imageBlockResponse.setDestinationAddress(device.getDeviceAddress());
        imageBlockResponse.setImageType(otaFile.getImageType());
        imageBlockResponse.setManufacturerCode(otaFile.getManufacturerCode());
        imageBlockResponse.setFileVersion(otaFile.getFileVersion());
        imageBlockResponse.setFileOffset(fileOffset);
        imageBlockResponse.setImageData(otaFile.getImageData(fileOffset, Math.min(dataSize, maximumDataSize)));

        logger.debug("{} OTA Data: Sending {} bytes at offset {}", device.getDeviceAddress(),
                imageBlockResponse.getImageData().size());

        try {
            networkManager.sendCommand(imageBlockResponse);
            return imageBlockResponse.getImageData().size();
        } catch (ZigBeeException e) {
            logger.debug("Exception sending OTA image block message: ", e);
            return 0;
        }
    }

    /**
     * Handles the sending of {@link ImageBlockResponse} following a {@link ImagePageCommand}
     *
     * @param pageOffset the starting offset for the page
     * @param pageLength the length of the page
     * @param maximumDataSize the maximum size of each data block
     * @param responseSpacing the delay between each block response in milliseconds
     */
    private void doPageResponse(final int pageOffset, final int pageLength, final int maximumDataSize,
            final int responseSpacing) {

        class PageSender implements Runnable {
            private int pagePosition;
            private final int pageEnd;
            private final int maximumDataSize;

            PageSender(final int pageOffset, final int pageLength, final int maximumDataSize) {
                this.pagePosition = pageOffset;
                this.pageEnd = pageOffset + pageLength;
                this.maximumDataSize = maximumDataSize;
            }

            @Override
            public void run() {
                // Send the block response
                // TODO: Ideally we should disable APS retry for page requests
                int dataSent = sendImageBlock(pagePosition, maximumDataSize);

                // If dataSent is 0, then we either reached the end of file, or there was an error
                // Either way, let's abort
                if (dataSent == 0) {
                    pagePosition = Integer.MAX_VALUE;
                } else {
                    pagePosition += dataSent;
                }

                // Have we reached the end of the page?
                if (pagePosition > pageEnd) {
                    synchronized (pageScheduledExecutor) {
                        scheduledPageTask.cancel(false);
                        scheduledPageTask = null;
                    }
                }
            }
        }
        ;

        synchronized (pageScheduledExecutor) {
            // Stop our task if it's running
            if (scheduledPageTask != null) {
                scheduledPageTask.cancel(true);
            }

            // Start the new task
            PageSender pageSender = new PageSender(pageOffset, pageLength, maximumDataSize);
            scheduledPageTask = pageScheduledExecutor.scheduleAtFixedRate(pageSender, responseSpacing, responseSpacing,
                    TimeUnit.MILLISECONDS);
        }

    }

    /**
     * Handle a received {@link QueryNextImageCommand} command.
     * This sends information on the firmware that is currently set in this server.
     *
     * @param command the received {@link QueryNextImageCommand}
     */
    private void handleQueryNextImage(QueryNextImageCommand command) {
        // Check that the file attributes are consistent with the file we have
        if (otaFile == null || command.getManufacturerCode() != otaFile.getManufacturerCode()
                || command.getImageType() != otaFile.getImageType()) {
            logger.debug("{} OTA Error: Request is inconsistent with OTA file.", device.getDeviceAddress());
            sendDefaultResponse(ZclStatus.NO_IMAGE_AVAILABLE);
            return;
        }

        // If the request contains a hardware version, and the OTA file also has the hardware restriction
        // then perform a check
        if (command.getHardwareVersion() != null && otaFile.getMinimumHardware() != null
                && otaFile.getMaximumHardware() != null) {
            if (command.getHardwareVersion() < otaFile.getMinimumHardware()
                    || command.getHardwareVersion() > otaFile.getMaximumHardware()) {
                sendDefaultResponse(ZclStatus.NO_IMAGE_AVAILABLE);
                return;
            }
        }

        // Update the state as we're starting
        updateStatus(ZigBeeOtaServerStatus.OTA_TRANSFER_IN_PROGRESS);

        QueryNextImageResponse nextImageResponse = new QueryNextImageResponse();
        nextImageResponse.setDestinationAddress(device.getDeviceAddress());
        nextImageResponse.setImageSize(otaFile.getImageSize());
        nextImageResponse.setImageType(otaFile.getImageType());
        nextImageResponse.setManufacturerCode(otaFile.getManufacturerCode());
        nextImageResponse.setFileVersion(otaFile.getFileVersion());

        try {
            networkManager.sendCommand(nextImageResponse);
        } catch (ZigBeeException e) {
            logger.debug("Exception sending OTA next image response message: ", e);
        }
    }

    /**
     * Handle a received {@link ImagePageCommand} command.
     * This will respond with a whole page (potentially a number of image blocks)
     *
     * @param command the received {@link ImagePageCommand}
     */
    private void handleImagePageRequest(ImagePageCommand command) {
        // No current support for device specific requests
        if (command.getFieldControl() != 0) {
            logger.debug("{} OTA Error: No file is set.", device.getDeviceAddress());
            sendDefaultResponse(ZclStatus.UNSUP_CLUSTER_COMMAND);
            return;
        }

        // Check that the file attributes are consistent with the file we have
        if (otaFile == null || command.getManufacturerCode() != otaFile.getManufacturerCode()
                || command.getFileVersion() != otaFile.getFileVersion()
                || command.getImageType() != otaFile.getImageType()) {
            logger.debug("{} OTA Error: Request is inconsistent with OTA file.", device.getDeviceAddress());
            sendDefaultResponse(ZclStatus.NO_IMAGE_AVAILABLE);
            return;
        }

        // Check that the offset is within bounds of the image data
        if (command.getFileOffset() > otaFile.getImageSize()) {
            logger.debug("{} OTA Error: Requested offset is larger than file ({}>{})", device.getDeviceAddress(),
                    command.getFileOffset(), otaFile.getImageSize());
            sendDefaultResponse(ZclStatus.MALFORMED_COMMAND);
            return;
        }

        doPageResponse(command.getFileOffset(), command.getPageSize(), command.getMaximumDataSize(),
                command.getResponseSpacing());
    }

    /**
     * Handle a received {@link ImageBlockCommand} command.
     * This will respond with a single image block.
     *
     * @param command the received {@link ImageBlockCommand}
     */
    private void handleImageBlockRequest(ImageBlockCommand command) {
        // No current support for device specific requests
        if (command.getFieldControl() != 0) {
            logger.debug("{} OTA Error: No file is set.", device.getDeviceAddress());
            sendDefaultResponse(ZclStatus.UNSUP_CLUSTER_COMMAND);
            return;
        }

        // Check that the file attributes are consistent with the file we have
        if (otaFile == null || command.getManufacturerCode() != otaFile.getManufacturerCode()
                || command.getFileVersion() != otaFile.getFileVersion()
                || command.getImageType() != otaFile.getImageType()) {
            logger.debug("{} OTA Error: Request is inconsistent with OTA file.", device.getDeviceAddress());
            sendDefaultResponse(ZclStatus.NO_IMAGE_AVAILABLE);
            return;
        }

        // Check that the offset is within bounds of the image data
        if (command.getFileOffset() > otaFile.getImageSize()) {
            logger.debug("{} OTA Error: Requested offset is larger than file ({}>{})", device.getDeviceAddress(),
                    command.getFileOffset(), otaFile.getImageSize());
            sendDefaultResponse(ZclStatus.MALFORMED_COMMAND);
            return;
        }

        // Send the block response
        sendImageBlock(command.getFileOffset(), command.getMaximumDataSize());
    }

    /**
     * Handle a received {@link UpgradeEndCommand} command.
     * If autoUpgrade is set, then we immediately send the {@link UpgradeEndResponse}
     *
     * @param command the received {@link UpgradeEndCommand}
     */
    private void handleUpgradeEndRequest(UpgradeEndCommand command) {
        // Check that the file attributes are consistent with the file we have
        if (otaFile == null || command.getManufacturerCode() != otaFile.getManufacturerCode()
                || command.getFileVersion() != otaFile.getFileVersion()
                || command.getImageType() != otaFile.getImageType()) {
            logger.debug("{} OTA Error: Request is inconsistent with OTA file.", device.getDeviceAddress());
            sendDefaultResponse(ZclStatus.NO_IMAGE_AVAILABLE);
            return;
        }

        updateStatus(ZigBeeOtaServerStatus.OTA_TRANSFER_COMPLETE);
        if (autoUpgrade) {
            completeUpgrade();
        }
    }

    @Override
    public void commandReceived(final ZigBeeCommand command) {
        // This gets called for all received commands
        // Check if it's our address
        if (command.getSourceAddress() != device.getDeviceAddress()) {
            return;
        }

        if (command instanceof QueryNextImageCommand) {
            handleQueryNextImage((QueryNextImageCommand) command);
            return;
        }

        if (command instanceof ImageBlockCommand) {
            handleImageBlockRequest((ImageBlockCommand) command);
            return;
        }

        if (command instanceof ImagePageCommand) {
            handleImagePageRequest((ImagePageCommand) command);
            return;
        }

        if (command instanceof UpgradeEndCommand) {
            handleUpgradeEndRequest((UpgradeEndCommand) command);
            return;
        }

        if (command instanceof MatchDescriptorRequest) {

        }
    }

}

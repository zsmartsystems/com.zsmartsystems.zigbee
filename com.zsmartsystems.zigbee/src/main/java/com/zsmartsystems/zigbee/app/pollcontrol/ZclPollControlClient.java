package com.zsmartsystems.zigbee.app.pollcontrol;

import com.zsmartsystems.zigbee.ZigBeeStatus;
import com.zsmartsystems.zigbee.app.ZigBeeApplication;
import com.zsmartsystems.zigbee.zcl.ZclCluster;
import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclCommandListener;
import com.zsmartsystems.zigbee.zcl.clusters.ZclPollControlCluster;
import com.zsmartsystems.zigbee.zcl.clusters.pollcontrol.CheckInCommand;
import com.zsmartsystems.zigbee.zcl.clusters.pollcontrol.CheckInResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZclPollControlClient implements ZigBeeApplication, ZclCommandListener {

    /**
     * The logger.
     */
    private final Logger logger = LoggerFactory.getLogger(ZclPollControlClient.class);

    private ZclPollControlExtension extension;

    private ZclPollControlCluster cluster;

    public ZclPollControlClient(ZclPollControlExtension extension) {
        this.extension = extension;
    }

    @Override
    public ZigBeeStatus appStartup(final ZclCluster cluster) {
        this.cluster = (ZclPollControlCluster) cluster;
        cluster.addCommandListener(this);
        return ZigBeeStatus.SUCCESS;
    }

    @Override
    public void appShutdown() {
        cluster.removeCommandListener(this);
    }

    @Override
    public int getClusterId() {
        return ZclPollControlCluster.CLUSTER_ID;
    }

    @Override
    public boolean commandReceived(final ZclCommand command) {
        if (command instanceof CheckInCommand) {
            logger.info("[ZclPollControlClient] Received command [" + command.toString() + "]");
            CheckInResponse response = new CheckInResponse(extension.getTimeout() >= 0, extension.getTimeout());
            cluster.sendResponse((CheckInCommand) command, response);
            return true;
        }
        return false;
    }
}

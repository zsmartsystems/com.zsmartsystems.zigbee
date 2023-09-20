package com.zsmartsystems.zigbee.console.main;

import com.zsmartsystems.zigbee.ZigBeeStatus;
import com.zsmartsystems.zigbee.app.ZigBeeApplication;
import com.zsmartsystems.zigbee.zcl.ZclCluster;
import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclCommandListener;
import com.zsmartsystems.zigbee.zcl.clusters.ZclOnOffCluster;
import com.zsmartsystems.zigbee.zcl.clusters.onoff.ZclOnOffCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZclOnOffServer implements ZigBeeApplication, ZclCommandListener {

    /**
     * The logger.
     */
    private final Logger logger = LoggerFactory.getLogger(ZclOnOffServer.class);

    private ZclOnOffCluster cluster;

    @Override
    public ZigBeeStatus appStartup(final ZclCluster cluster) {
        this.cluster = (ZclOnOffCluster) cluster;
        cluster.addCommandListener(this);
        return ZigBeeStatus.SUCCESS;
    }

    @Override
    public void appShutdown() {
        cluster.removeCommandListener(this);
    }

    @Override
    public int getClusterId() {
        return ZclOnOffCluster.CLUSTER_ID;
    }

    @Override
    public boolean commandReceived(final ZclCommand command) {
        if (command instanceof ZclOnOffCommand) {
            logger.info("[ZclOnOffServer] Received command [" + command.toString() + "]");
            return true;
        }
        return false;
    }
}

/**
 * Copyright (c) 2016-2025 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.zsmartsystems.zigbee.database.ZigBeeNetworkBackupDao;
import com.zsmartsystems.zigbee.database.ZigBeeNetworkDataStore;
import com.zsmartsystems.zigbee.database.ZigBeeNetworkDatabaseManager;
import com.zsmartsystems.zigbee.database.ZigBeeNodeDao;

/**
 * @author Chris Jackson
 */
public class ZigBeeBackupManager {
    private final ZigBeeNetworkManager networkManager;
    private final ZigBeeNetworkDatabaseManager databaseManager;

    /**
     * Instantiates the {@link ZigBeeBackupManager} class
     *
     * @param networkManager
     * @param databaseManager
     */
    public ZigBeeBackupManager(ZigBeeNetworkManager networkManager, ZigBeeNetworkDatabaseManager databaseManager) {
        this.networkManager = networkManager;
        this.databaseManager = databaseManager;
    }

    /**
     * Creates a backup of the {@link ZigBeeNetworkManager}, storing it in the {@link ZigBeeNetworkDataStore}
     *
     * @return a unique {@link UUID} referencing the backup
     */
    public UUID createBackup() {
        ZigBeeNetworkBackupDao backup = new ZigBeeNetworkBackupDao();

        backup.setUuid(UUID.randomUUID());
        backup.setDate(new Date());

        backup.setPan(networkManager.getZigBeePanId());
        backup.setEpan(networkManager.getZigBeeExtendedPanId());
        backup.setChannel(networkManager.getZigBeeChannel());
        backup.setNetworkKey(networkManager.getZigBeeNetworkKey());
        backup.setLinkKey(networkManager.getZigBeeLinkKey());

        Set<ZigBeeNodeDao> nodesDao = new HashSet<>();
        for (ZigBeeNode node : networkManager.getNodes()) {
            nodesDao.add(node.getDao());
        }
        backup.setNodes(nodesDao);

        return databaseManager.writeBackup(backup) ? backup.getUuid() : null;
    }

    /**
     * Restores the backup referenced from the provided {@link UUID}.
     *
     * @param uuid the unique {@link UUID} referencing the backup to restore
     * @return
     */
    public boolean restoreBackup(UUID uuid) {
        return false;
    }

}

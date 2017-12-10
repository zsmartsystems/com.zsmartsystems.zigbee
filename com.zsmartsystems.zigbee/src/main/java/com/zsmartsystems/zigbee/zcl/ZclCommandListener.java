/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl;

/**
 * Command listener. Listeners are called when an {@link ZclCommand} for the Cluster is received.
 *
 * @author Chris Jackson
 *
 */
public interface ZclCommandListener {
    /**
     * Called when a {@link ZclCommand} is received for this cluster
     *
     * @param command the {@link ZclCommand} that has been received
     */
    void commandReceived(ZclCommand command);

}

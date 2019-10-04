/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl;

/**
 * Command listener. Listeners are called when an {@link ZclCommand} for the Cluster is received.
 * <p>
 * Command listeners are expected to return promptly when called - if there is significant processing to perform
 * then this should be handled in a separate thread.
 *
 * @author Chris Jackson
 *
 */
public interface ZclCommandListener {
    /**
     * Called when a {@link ZclCommand} is received for this cluster.
     * <p>
     * Command listeners are expected to return promptly when called - if there is significant processing to perform
     * then this should be handled in a separate thread.
     *
     * @param command the {@link ZclCommand} that has been received
     * @return true if the handler has, or will send a response to this command
     */
    boolean commandReceived(ZclCommand command);
}

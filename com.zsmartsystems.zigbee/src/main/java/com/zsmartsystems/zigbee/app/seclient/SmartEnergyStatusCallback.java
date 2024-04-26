/**
 * Copyright (c) 2016-2024 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.app.seclient;

/**
 * Defines a callback interface to receive updates on the state of the Smart Energy network
 *
 * @author Chris Jackson
 *
 */
public interface SmartEnergyStatusCallback {

    /**
     * Updates the listener on the status of the Smart Energy network
     * 
     * @param updatedStatus the current {@link ZigBeeSepClientStatus}
     */
    public void sepStatusUpdate(ZigBeeSepClientStatus updatedStatus);
}

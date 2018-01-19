/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.app.otaserver;

/**
 * This interface defines status callback updates for Over The Air update progress
 *
 * @author Chris Jackson
 */
public interface ZigBeeOtaStatusCallback {

    /**
     * Provides a callback following the status change of the OTA server
     *
     * @param status the updated {@link ZigBeeOtaServerStatus}
     * @param percent the current percent complete of the transfer
     */
    void otaStatusUpdate(ZigBeeOtaServerStatus status, int percent);

}

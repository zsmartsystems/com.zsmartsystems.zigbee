/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.internal.otaserver;

import com.zsmartsystems.zigbee.zcl.ZclStatus;

/**
 * This interface defines status callback updates for Over The Air update progress
 *
 * @author Chris Jackson
 */
interface ZigBeeOtaStatusCallback {

    /**
     * Provides a callback following the statuc change of an OTA update
     */
    void otaStatus(ZclStatus status);

    /**
     * Provides a callback with the progress of an OTA update (ie percentage complete
     */
    void otaProgress(int percentComplete);

}

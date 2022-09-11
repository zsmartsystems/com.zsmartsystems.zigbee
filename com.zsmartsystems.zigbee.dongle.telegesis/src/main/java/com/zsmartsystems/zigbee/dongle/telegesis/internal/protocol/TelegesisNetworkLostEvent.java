/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol;


/**
 * Class to implement the Telegesis command <b>Network Lost</b>.
 * <p>
 * End Device has lost contact with Parent
 * <p>
 * This class provides methods for processing Telegesis AT API commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class TelegesisNetworkLostEvent extends TelegesisFrame implements TelegesisEvent {

    @Override
    public void deserialize(int[] data) {
        initialiseDeserializer(data);

    }

    @Override
    public String toString() {
        return "TelegesisNetworkLostEvent []";
    }
}

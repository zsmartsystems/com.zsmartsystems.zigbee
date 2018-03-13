/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl;

/**
 * Attribute update listener. Listeners are called when an {@link ZclAttribute} for the Cluster is updated.
 *
 * @author Chris Jackson
 *
 */
public interface ZclAttributeListener {
    /**
     * Called when an attribute is updated
     *
     * @param attribute the {@link ZclAttribute} that has been updated
     */
    void attributeUpdated(ZclAttribute attribute);
}

/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl;

/**
 * Enumeration of the frame type sub-field for the {@link ZclHeader}.
 * <p>
 * Specifies if this is a <i>generic</i> command used across the entire profile {@link #ENTIRE_PROFILE_COMMAND},
 * or a command that is specific to a single cluster {@link #CLUSTER_SPECIFIC_COMMAND}.
 *
 * @author Chris Jackson
 *
 */
public enum ZclFrameType {
    /**
     * Command is specific to a cluster
     */
    CLUSTER_SPECIFIC_COMMAND,
    /**
     * Command acts across the entire profile
     */
    ENTIRE_PROFILE_COMMAND;
}

/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.basic;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 *
 * @author Chris Jackson
 *
 */
public class GenericDeviceTypeEnumTest {
    @Test
    public void test() {
        GenericDeviceTypeEnum value = GenericDeviceTypeEnum.valueOf("WALL_SWITCH");
        assertEquals(225, value.getKey());

        assertEquals(GenericDeviceTypeEnum.LINEAR_FLUORESCENT, GenericDeviceTypeEnum.getByValue(4));
    }
}

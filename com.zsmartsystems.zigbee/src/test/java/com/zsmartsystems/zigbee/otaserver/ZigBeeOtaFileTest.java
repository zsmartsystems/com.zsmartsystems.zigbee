/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.otaserver;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import com.zsmartsystems.zigbee.ZigBeeStackType;
import com.zsmartsystems.zigbee.otaserver.ZigBeeOtaFile;
import com.zsmartsystems.zigbee.zcl.field.ByteArray;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeOtaFileTest {
    @Test
    public void testOpenFile() throws IOException {
        File file = new File("./src/test/resource/test_ota_file.zigbee");
        ZigBeeOtaFile otaFile = new ZigBeeOtaFile(file);

        System.out.println(otaFile);

        assertEquals(Integer.valueOf(0x1234), otaFile.getManufacturerCode());
        assertEquals(Integer.valueOf(0x0006), otaFile.getImageType());
        assertEquals(Integer.valueOf(0x12345678), otaFile.getFileVersion());
        assertEquals(ZigBeeStackType.ZIGBEE_PRO, otaFile.getStackVersion());
        assertEquals("A.String", otaFile.getHeaderString());
        assertEquals(Integer.valueOf(72), otaFile.getImageSize());
        assertEquals(Integer.valueOf(16), otaFile.getImageDataSize());
        assertEquals(new ByteArray(new byte[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 }),
                otaFile.getImageData(0, 16));
    }
}

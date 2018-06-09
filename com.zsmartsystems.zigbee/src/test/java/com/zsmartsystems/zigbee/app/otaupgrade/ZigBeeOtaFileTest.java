/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.app.otaupgrade;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.Test;

import com.zsmartsystems.zigbee.ZigBeeStackType;
import com.zsmartsystems.zigbee.app.otaserver.ZigBeeOtaFile;
import com.zsmartsystems.zigbee.zcl.field.ByteArray;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeOtaFileTest {
    @Test
    public void testOpenFile() throws IOException {
        Path file = FileSystems.getDefault().getPath("./src/test/resource/", "test_ota_file.test");
        byte[] fileData = Files.readAllBytes(file);
        ZigBeeOtaFile otaFile = new ZigBeeOtaFile(fileData);

        System.out.println(otaFile);

        assertEquals(Integer.valueOf(0x1234), otaFile.getManufacturerCode());
        assertEquals(Integer.valueOf(0x0006), otaFile.getImageType());
        assertEquals(Integer.valueOf(0x12345678), otaFile.getFileVersion());
        assertEquals(ZigBeeStackType.ZIGBEE_PRO, otaFile.getStackVersion());
        assertEquals("A.String", otaFile.getHeaderString());
        assertEquals(Integer.valueOf(78), otaFile.getImageSize());
        assertEquals(new ByteArray(new byte[] { 0x1E, (byte) 0xF1, (byte) 0xEE, 0x0B }), otaFile.getImageData(0, 4));
        assertEquals(new ByteArray(new byte[] { 0x00, 0x01, 0x02, 0x03, 0x04 }), otaFile.getImageData(62, 5));
    }
}

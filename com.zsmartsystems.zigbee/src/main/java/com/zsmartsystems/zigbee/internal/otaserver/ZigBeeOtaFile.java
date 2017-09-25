/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.internal.otaserver;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Defines a ZigBee Over The Air upgrade file.
 * This class will read each tag from the file
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeOtaFile {
    private List<ZigBeeOtaBaseTag> tagList;

    public ZigBeeOtaFile(File file) {
        if (file == null) {
            throw new IllegalArgumentException("File can not be null.");
        }

        // Create the list to hold all the tags
        tagList = new ArrayList<ZigBeeOtaBaseTag>();

        int totalBytesRead = 0;
        byte[] result;
        InputStream input;
        try {
            input = new BufferedInputStream(new FileInputStream(file));

            while (totalBytesRead < result.length) {
                int bytesRemaining = result.length - totalBytesRead;
                // input.read() returns -1, 0, or more :
                int bytesRead = input.read(result, totalBytesRead, bytesRemaining);
                if (bytesRead > 0) {
                    totalBytesRead = totalBytesRead + bytesRead;
                }
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

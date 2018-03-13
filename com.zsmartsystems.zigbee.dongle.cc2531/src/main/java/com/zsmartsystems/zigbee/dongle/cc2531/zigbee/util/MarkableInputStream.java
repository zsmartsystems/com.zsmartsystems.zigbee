/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
/*
   Copyright 2008-2013 CNR-ISTI, http://isti.cnr.it
   Institute of Information Science and Technologies
   of the Italian National Research Council


   See the NOTICE file distributed with this work for additional
   information regarding copyright ownership

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
package com.zsmartsystems.zigbee.dongle.cc2531.zigbee.util;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author <a href="mailto:stefano.lenzi@isti.cnr.it">Stefano "Kismet" Lenzi</a>
 */
public class MarkableInputStream extends InputStream {

    private final InputStream in;
    private CircularBufferInt buffer;
    private int idx;

    public MarkableInputStream(final InputStream input) {
        this.in = input;
    }

    @Override
    public int available() throws IOException {

        if (buffer == null || idx >= buffer.size()) {
            return in.available();
        } else {
            return buffer.size();
        }
    }

    @Override
    public synchronized void mark(int size) {
        if (size == 0) {
            /*
             * Removing the mark, in case it was set
             */
            buffer = null;
        } else if (buffer == null) {
            /*
             * If the mark was not set we create a buffer for store it
             */
            buffer = new CircularBufferInt(size, true);
            idx = buffer.size();
        } else if (size > buffer.slots()) {
            /*
             * The mark was set so we have should copy unread data to the new buffer
             */
            buffer = new CircularBufferInt(buffer.toArray(), size, true);
        }
    }

    @Override
    public boolean markSupported() {
        return true;
    }

    @Override
    public synchronized void reset() throws IOException {

        idx = 0;
    }

    @Override
    public int read() throws IOException {

        if (buffer == null) {
            /*
             * No mark set so reading from the stream
             */
            return in.read();
        } else if (idx < buffer.size()) {
            /*
             * Mark was set and also buffer was reset, and it has not been empty yet
             */
            idx += 1;
            return buffer.remove();
        } else if (idx >= buffer.size()) {
            /*
             * Mark was set, but buffer has been exhausted
             */
            int val = in.read();
            idx += 1;
            buffer.add(val);
            return val;
        }
        /*
         * Unreachable code
         */
        return -1;
    }

    @Override
    public synchronized void close() throws IOException {

        buffer = null;
        in.close();
    }

}

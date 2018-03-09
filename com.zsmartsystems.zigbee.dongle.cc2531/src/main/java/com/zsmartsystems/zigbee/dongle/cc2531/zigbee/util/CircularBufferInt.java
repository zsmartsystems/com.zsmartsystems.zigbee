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

/**
 * @author <a href="mailto:stefano.lenzi@isti.cnr.it">Stefano "Kismet" Lenzi</a>
 */
public class CircularBufferInt {

    private final int[] buffer;
    private int head;
    private int tail;
    private int used;

    private final boolean overwrite;

    public CircularBufferInt(final int size, final boolean cyclic) {
        buffer = new int[size];
        overwrite = cyclic;
        tail = 0;
        head = 0;
        used = 0;
    }

    public CircularBufferInt(final int[] data, final int size, final boolean cyclic) {
        buffer = new int[size];
        overwrite = cyclic;
        if (!cyclic && data.length > size) {
            throw new IndexOutOfBoundsException("Trying to copy " + data.length + " to non-cyclic buffer of " + size
                    + "." + "You should either increase the size of the buffer or set it as cyclic");
        } else if (cyclic && data.length > size) {
            for (tail = 0; tail < buffer.length; tail++) {
                buffer[tail] = data[data.length - buffer.length + tail];
            }
        } else {
            for (tail = 0; tail < data.length; tail++) {
                buffer[tail] = data[tail];
            }
        }
        head = 0;
        used = tail;
    }

    public int slots() {
        return buffer.length;
    }

    public int size() {
        return used;
    }

    public boolean isFull() {
        return used == buffer.length;
    }

    public boolean isEmpty() {
        return used == 0;
    }

    public void add(int value) {
        if (isFull()) {
            if (overwrite) {
                remove();
            } else {
                throw new IndexOutOfBoundsException("Trying to add data to a full circular buffer, if you need it to "
                        + "overwrite its own data please create it with cyclic=true");
            }
        }
        buffer[tail] = value;
        tail = (tail + 1) % buffer.length;
        used += 1;
    }

    public int get(int idx) {
        if (idx >= size() || idx < 0) {
            throw new IndexOutOfBoundsException("Trying to get data from " + idx + " but size is " + size());
        }
        return buffer[(head + idx) % buffer.length];
    }

    public int remove() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Trying to remove data from an empty circular buffer");
        }
        final int val = buffer[head];
        head = (head + 1) % buffer.length;
        used -= 1;
        return val;
    }

    public int[] toArray() {
        int[] values = new int[size()];
        int i = 0;
        for (int j = head; j < buffer.length; j++) {
            values[i] = buffer[j];
            i += 1;
        }
        for (int j = 0; j < tail; j++) {
            values[i] = buffer[j];
            i += 1;
        }
        return values;
    }
}

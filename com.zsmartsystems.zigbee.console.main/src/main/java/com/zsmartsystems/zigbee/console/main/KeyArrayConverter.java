/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console.main;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.zsmartsystems.zigbee.security.ZigBeeKey;

/**
 * Converter for XStream to serialise the {@link ZigBeeKey} key array as a string
 *
 * @author Chris Jackson
 *
 */
public class KeyArrayConverter implements Converter {
    @Override
    public boolean canConvert(Class clazz) {
        return clazz.equals(int[].class);
    }

    @Override
    public void marshal(Object value, HierarchicalStreamWriter writer, MarshallingContext context) {
        StringBuilder builder = new StringBuilder();

        int[] arrayValue = (int[]) value;
        for (int cnt = 0; cnt < 16; cnt++) {
            builder.append(String.format("%02X", arrayValue[cnt]));
        }

        writer.setValue(builder.toString());
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        int[] key = new int[16];
        char enc[] = reader.getValue().toCharArray();
        for (int i = 0; i < enc.length; i += 2) {
            StringBuilder curr = new StringBuilder(2);
            curr.append(enc[i]).append(enc[i + 1]);
            key[i / 2] = Integer.parseInt(curr.toString(), 16);
        }

        return key;
    }

}

/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
/*
   Copyright 2008-2013 Andrew Rapp, http://code.google.com/p/xbee-api/

   Copyright 2008-2013 ITACA-TSB, http://www.tsb.upv.es/
   Instituto Tecnologico de Aplicaciones de Comunicacion
   Avanzadas - Grupo Tecnologias para la Salud y el
   Bienestar (TSB)


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
 * @author <a href="mailto:andrew.rapp@gmail.com">Andrew Rapp</a>
 * @author <a href="mailto:alfiva@aaa.upv.es">Alvaro Fides Valero</a>
 */
public class DoubleByte {

    private int msb;
    private int lsb;

    public DoubleByte() {

    }

    /**
     * Decomposes a 16bit int into high and low bytes
     *
     * @param val
     */
    public DoubleByte(int val) {
        if (val > 0xFFFF || val < 0) {
            throw new IllegalArgumentException("value is out of range");
        }

        // split address into high and low bytes
        msb = val >> 8;
        lsb = val & 0xFF;
    }

    /**
     * Constructs a 16bit value from two bytes (high and low)
     *
     * @param msb
     * @param lsb
     */
    public DoubleByte(int msb, int lsb) {

        if (msb > 0xFF || lsb > 0xFF) {
            throw new IllegalArgumentException("msb or lsb are out of range");
        }

        this.msb = msb;
        this.lsb = lsb;
    }

    public int getMsb() {
        return msb;
    }

    public int getLsb() {
        return lsb;
    }

    public int get16BitValue() {
        return (this.msb << 8) + this.lsb;
    }

    public void setMsb(int msb) {
        this.msb = msb;
    }

    public void setLsb(int lsb) {
        this.lsb = lsb;
    }

    @Override
    public String toString() {
        String MSB = Integer.toHexString(msb);
        if (MSB.length() == 1) {
            MSB = "0" + MSB;
        }
        String LSB = Integer.toHexString(lsb);
        if (LSB.length() == 1) {
            LSB = "0" + LSB;
        }

        return "0x" + MSB + LSB;

    }
}

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

import java.util.Objects;

/**
 * Represents a double byte XBeeApi Address.
 *
 * @author <a href="mailto:andrew.rapp@gmail.com">Andrew Rapp</a>
 * @author <a href="mailto:alfiva@aaa.upv.es">Alvaro Fides Valero</a>
 */
public class ZToolAddress16 extends ZToolAddress {

    public static final ZToolAddress16 BROADCAST = new ZToolAddress16(0xFF, 0xFF);
    public static final ZToolAddress16 ZNET_BROADCAST = new ZToolAddress16(0xFF, 0xFE);
    public static final ZToolAddress16 ZCZR_BROADCAST = new ZToolAddress16(0xFF, 0xFC);

    private DoubleByte doubleByte = new DoubleByte();

    /**
     * Provide address as msb byte and lsb byte
     *
     * @param msb
     * @param lsb
     */
    public ZToolAddress16(int msb, int lsb) {
        this.doubleByte.setMsb(msb);
        this.doubleByte.setLsb(lsb);
    }

    public ZToolAddress16(int[] arr) {
        this.doubleByte.setMsb(arr[0]);
        this.doubleByte.setLsb(arr[1]);
    }

    public ZToolAddress16() {

    }

    public int get16BitValue() {
        return this.doubleByte.get16BitValue();
    }

    public int getMsb() {
        return this.doubleByte.getMsb();
    }

    public void setMsb(int msb) {
        this.doubleByte.setMsb(msb);
    }

    public int getLsb() {
        return this.doubleByte.getLsb();
    }

    public void setLsb(int lsb) {
        this.doubleByte.setLsb(lsb);
    }

    @Override
    public int hashCode() {
        return Objects.hash(doubleByte);
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        } else {
            try {
                ZToolAddress16 addr = (ZToolAddress16) o;

                return (this.getLsb() == addr.getLsb() && this.getMsb() == addr.getMsb());
            } catch (Exception e) {
                return false;
            }
        }
    }

    @Override
    public byte[] getAddress() {
        return new byte[] { (byte) this.doubleByte.getMsb(), (byte) this.doubleByte.getLsb() };
    }
}

/*
   Copyright 2008-2013 Andrew Rapp, http://code.google.com/p/xbee-api/

   Copyright 2008-2013 CNR-ISTI, http://isti.cnr.it
   Institute of Information Science and Technologies
   of the Italian National Research Council

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

package com.zsmartsystems.zigbee.dongle.cc2531.network.packet;

import com.zsmartsystems.zigbee.dongle.cc2531.zigbee.util.ByteUtils;
import com.zsmartsystems.zigbee.dongle.cc2531.zigbee.util.DoubleByte;
import com.zsmartsystems.zigbee.dongle.cc2531.zigbee.util.Integers;

/**
 * @author <a href="mailto:andrew.rapp@gmail.com">Andrew Rapp</a>
 * @author <a href="mailto:stefano.lenzi@isti.cnr.it">Stefano "Kismet" Lenzi</a>
 * @author <a href="mailto:alfiva@aaa.upv.es">Alvaro Fides Valero</a>
 */
public class ZToolPacket {

    public final static int PAYLOAD_START_INDEX = 4;

    public enum CommandType {
        POLL,
        SREQ,
        AREQ,
        SRSP
    }

    public enum CommandSubsystem {
        RESERVED_0,
        SYS,
        RESERVED_1,
        RESERVED_2,
        AF,
        ZDO,
        ZB
    }

    // private final static Logger log = Logger.getLogger(ZToolPacket.class);
    public final static int START_BYTE = 0xFE;
    protected int[] packet;
    private int LEN;
    private DoubleByte CMD;
    private int FCS;
    private boolean error = false;
    private String errorMsg;
    private CommandType type = null;
    private CommandSubsystem subsystem = null;

    /**
     * I started off using bytes but quickly realized that java bytes are signed, so effectively only 7 bits.
     * We should be able to use int instead.
     *
     */ // PROTECTED?
    public ZToolPacket() {
    }

    // PROTECTED?
    public ZToolPacket(DoubleByte ApiId, int[] frameData) {
        this.buildPacket(ApiId, frameData);
    }

    public void buildPacket(DoubleByte ApiId, int[] frameData) {
        // packet size is start byte + len byte + 2 cmd bytes + data + checksum byte
        packet = new int[frameData.length + 5];
        packet[0] = START_BYTE;

        // note: if checksum is not correct, XBee won't send out packet or return error. ask me how I know.
        // checksum is always computed on pre-escaped packet
        Checksum checksum = new Checksum();
        // Packet length does not include escape bytes
        this.LEN = frameData.length;
        packet[1] = this.LEN;
        checksum.addByte(packet[1]);
        // msb Cmd0 -> Type & Subsystem
        packet[2] = ApiId.getMsb();
        checksum.addByte(packet[2]);
        // lsb Cmd1 -> PROFILE_ID_HOME_AUTOMATION
        packet[3] = ApiId.getLsb();
        checksum.addByte(packet[3]);
        this.CMD = ApiId;
        // data
        for (int i = 0; i < frameData.length; i++) {
            if (!ByteUtils.isByteValue(frameData[i])) {
                throw new RuntimeException("Value is greater than one byte: " + frameData[i] + " ("
                        + Integer.toHexString(frameData[i]) + ")");
            }
            packet[PAYLOAD_START_INDEX + i] = frameData[i];
            checksum.addByte(packet[PAYLOAD_START_INDEX + i]);
        }
        // set last byte as checksum
        checksum.compute();
        this.FCS = checksum.getChecksum();
        packet[packet.length - 1] = this.FCS;

    }

    public CommandType getCommandType() {
        if (type != null) {
            return type;
        }
        type = CommandType.values()[(packet[2] & 0x60) >> 5];
        return type;
    }

    public CommandSubsystem getCommandSubsystem() {
        if (subsystem != null) {
            return subsystem;
        }
        subsystem = CommandSubsystem.values()[packet[2] & 0x1F];
        return subsystem;
    }

    public int[] getPacket() {
        return packet;
    }

    public int getLEN() {
        return LEN;
    }

    public DoubleByte getCMD() {
        return this.CMD;
    }

    public short getCommandId() {
        return Integers.shortFromInts(packet, 2, 3);
    }

    public int getFCS() {
        return this.FCS;
    }

    public void setFCS(int fcs) {
        this.FCS = fcs;
    }

    public static boolean isSpecialByte(int b) {
        if (b == ZToolPacket.START_BYTE) {
            return true;
        }

        return false;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return "Packet: length=" + LEN + ", apiId=" + ByteUtils.toBase16(CMD.getMsb()) + " "
                + ByteUtils.toBase16(CMD.getLsb()) + ", data=" + ByteUtils.toBase16(packet) + ", checksum="
                + ByteUtils.toBase16(FCS) + ", error=" + error + (error == true ? (", errorMessage=" + errorMsg) : "");
    }
}

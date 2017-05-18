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

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.dongle.cc2531.zigbee.util.ByteUtils;
import com.zsmartsystems.zigbee.dongle.cc2531.zigbee.util.MarkableInputStream;

/**
 * @author <a href="mailto:andrew.rapp@gmail.com">Andrew Rapp</a>
 * @author <a href="mailto:alfiva@aaa.upv.es">Alvaro Fides Valero</a>
 * @author <a href="mailto:stefano.lenzi@isti.cnr.it">Stefano "Kismet" Lenzi</a>
 * @author <a href="mailto:michele.girolami@isti.cnr.it">Michele Girolami</a>
 * @author <a href="mailto:tommi.s.e.laukkanen@gmail.com">Tommi S.E. Laukkanen</a>
 * @author Chris Jackson
 */
public class ZToolPacketParser implements Runnable {
    /**
     * The logger.
     */
    private final static Logger logger = LoggerFactory.getLogger(ZToolPacketParser.class);
    /**
     * The packet handler.
     */
    private ZToolPacketHandler packetHandler;
    /**
     * The input stream.
     */
    private final InputStream inputStream;
    /**
     * The parser parserThread.
     */
    private Thread parserThread = null;
    /**
     * Flag reflecting that parser has been closed and parser parserThread should exit.
     */
    private boolean close = false;

    /**
     * Construct which sets input stream where the packet is read from the and handler
     * which further processes the received packet.
     *
     * @param inputStream the input stream
     * @param packetHandler the packet handler
     */
    public ZToolPacketParser(final InputStream inputStream, final ZToolPacketHandler packetHandler) {
        logger.trace("Creating ZToolPacketParser");
        if (inputStream.markSupported()) {
            this.inputStream = inputStream;
        } else {
            logger.trace("Provided InputStream {} doesn't provide the mark()/reset() feature, "
                    + "wrapping it up as BufferedInputStream", inputStream.getClass());
            this.inputStream = new MarkableInputStream(inputStream);
        }
        this.packetHandler = packetHandler;

        parserThread = new Thread(this, "ZToolPacketParser");
        parserThread.setDaemon(true);
        parserThread.start();
    }

    /**
     * Run method executed by the parser thread.
     */
    @Override
    public void run() {
        logger.trace("ZToolPacketParser parserThread started");
        while (!close) {
            try {
                int val = inputStream.read();
                if (val == ZToolPacket.START_BYTE) {
                    inputStream.mark(256);
                    final ZToolPacketStream packetStream = new ZToolPacketStream(inputStream);
                    final ZToolPacket response = packetStream.parsePacket();

                    logger.trace("Response is {} -> {}", response.getClass().getSimpleName(), response);
                    if (response.isError()) {
                        logger.debug("Received a BAD PACKET {}", response.getPacket());
                        inputStream.reset();
                        continue;
                    }

                    packetHandler.handlePacket(response);
                } else if (val != -1) {
                    // Log if not end of stream.
                    logger.debug("Discarded stream: expected start byte but received {}", ByteUtils.toBase16(val));
                }
            } catch (final IOException e) {
                if (!close) {
                    packetHandler.error(e);
                    close = true;
                }
            }
        }
        logger.debug("ZToolPacketParser parserThread exited.");
    }

    /**
     * Set the close flag to true.
     */
    public void setClosing() {
        this.close = true;
    }

    /**
     * Requests parser thread to shutdown.
     */
    public void close() {
        this.close = true;
        try {
            parserThread.interrupt();
            parserThread.join();
        } catch (InterruptedException e) {
            logger.warn("Interrupted in packet parser thread shutdown join.");
        }
    }

    /**
     * Checks if parser thread is alive.
     *
     * @return true if parser thread is alive.
     */
    public boolean isAlive() {
        return parserThread != null && parserThread.isAlive();
    }

}

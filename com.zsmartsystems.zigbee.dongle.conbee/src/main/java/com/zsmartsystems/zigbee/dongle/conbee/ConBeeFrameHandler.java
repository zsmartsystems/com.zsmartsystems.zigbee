package com.zsmartsystems.zigbee.dongle.conbee;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Frame parser for ConBee SLIP protocol.
 *
 * @author Chris Jackson
 *
 */
public class ConBeeFrameHandler {
    /**
     * The logger.
     */
    private final Logger logger = LoggerFactory.getLogger(ConBeeFrameHandler.class);

    /**
     * The receive timeout settings - min/initial/max - defined in milliseconds
     */
    private final int T_RX_ACK_MIN = 400;
    private final int T_RX_ACK_INIT = 1600;
    private final int T_RX_ACK_MAX = 3200;
    private int receiveTimeout = T_RX_ACK_INIT;

    /**
     * Maximum number of consecutive timeouts allowed while waiting to receive
     * an ACK
     */
    private final int ACK_TIMEOUTS = 4;
    private int retries = 0;

    /**
     * Maximum number of DATA frames we can transmit without an ACK
     */
    private final int TX_WINDOW = 1;

    private long sentTime;

    private final int ASH_CANCEL_BYTE = 0x1A;
    private final int ASH_FLAG_BYTE = 0x7E;

    private final int ASH_MAX_LENGTH = 131;

    /**
     * The queue of {@link EzspFrameRequest} frames waiting to be sent
     */
    private final Queue<ConBeeFrame> sendQueue = new LinkedList<ConBeeFrame>();

    private ExecutorService executor = Executors.newCachedThreadPool();
    private final List<ConBeeListener> transactionListeners = new ArrayList<ConBeeListener>();

    /**
     * The output stream.
     */
    private final OutputStream outputStream;

    /**
     * The parser parserThread.
     */
    private Thread parserThread = null;

    /**
     * Flag reflecting that parser has been closed and parser parserThread
     * should exit.
     */
    private boolean close = false;

    /**
     * Construct which sets input stream where the packet is read from the and
     * handler which further processes the received packet.
     *
     * @param inputStream
     *            the input stream
     * @param outputStream
     *            the output stream
     * @param frameHandler
     *            the packet handler
     */
    public ConBeeFrameHandler(final InputStream inputStream, final OutputStream outputStream) {
        this.outputStream = outputStream;
        // this.inputStream = inputStream;

        parserThread = new Thread("ConBeeFrameHandler") {
            @Override
            public void run() {
                logger.trace("ConBeeFrameHandler thread started");

                int exceptionCnt = 0;
                int[] inputBuffer = new int[ASH_MAX_LENGTH];
                int inputCount = 0;
                boolean inputError = false;

                while (!close) {
                    try {
                        int val = inputStream.read();
                        logger.trace("CONBEE RX: " + String.format("%02X", val));
                        if (val == ASH_CANCEL_BYTE) {
                            inputCount = 0;
                            inputError = false;

                            continue;
                        } else if (val == ASH_FLAG_BYTE) {

                        } else if (val != -1) {
                            if (inputCount >= ASH_MAX_LENGTH) {
                                inputCount = 0;
                                inputError = true;
                            }
                            inputBuffer[inputCount++] = val;
                        }
                    } catch (final IOException e) {
                        logger.error("ConBeeFrameHandler IOException: ", e);

                        if (exceptionCnt++ > 10) {
                            logger.error("ConBeeFrameHandler exception count exceeded");
                            close = true;
                        }
                    }
                }
                logger.debug("AshFrameHandler exited.");
            }
        };

        parserThread.setDaemon(true);
        parserThread.start();
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

    // Synchronize this method to ensure a packet gets sent as a block
    private synchronized void outputFrame(ConBeeFrame ashFrame) {
        // Send the data
        try {
            for (int b : ashFrame.getOutputBuffer()) {
                // result.append(" ");
                // result.append(String.format("%02X", b));
                // logger.debug("ASH TX: " + String.format("%02X", b));
                outputStream.write(b);
            }
        } catch (IOException e) {
            logger.debug(e.getMessage());
        }

        // logger.debug(result.toString());
        // logger.debug("--> TX ASH frame: {}", ashFrame);

    }

    /**
     * Add an EZSP frame to the send queue. The sendQueue is a FIFO queue.
     * This method queues a {@link EzspFrameRequest} frame without waiting for a response and
     * no transaction management is performed.
     *
     * @param transaction
     *            {@link EzspFrameRequest}
     */
    public void queueFrame(ConBeeFrame request) {
        sendQueue.add(request);

        // logger.debug("TX EZSP queue: {}", sendQueue.size());

        sendNextFrame();
    }

    interface ConBeeListener {
        boolean transactionEvent(EzspFrameResponse ezspResponse);
    }
}

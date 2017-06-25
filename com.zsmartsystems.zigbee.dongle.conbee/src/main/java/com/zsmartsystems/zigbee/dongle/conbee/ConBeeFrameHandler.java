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

import com.zsmartsystems.zigbee.dongle.conbee.frame.ConBeeFrame;

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

    private final static int SLIP_ESC = 0xDB;
    private final static int SLIP_ESC_END = 0xDC;
    private final static int SLIP_ESC_ESC = 0xDD;
    private final static int SLIP_END = 0xC0;

    private final int SLIP_MAX_LENGTH = 131;

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
                int[] inputBuffer = new int[SLIP_MAX_LENGTH];
                int inputCount = 0;
                boolean inputError = false;

                boolean escaped = false;

                while (!close) {
                    try {
                        int val = inputStream.read();
                        logger.trace("CONBEE RX: " + String.format("%02X", val));
                        if (val == SLIP_ESC) {
                            escaped = true;
                        } else if (val == SLIP_END) {
                            // Frame complete

                            inputCount = 0;
                        } else if (escaped) {
                            escaped = false;
                            switch (val) {
                                case SLIP_ESC_END:
                                    inputBuffer[inputCount++] = SLIP_END;
                                    break;
                                case SLIP_ESC_ESC:
                                    inputBuffer[inputCount++] = SLIP_ESC;
                                    break;
                                default:
                                    inputBuffer[inputCount++] = val;
                                    break;
                            }
                        } else if (val != -1) {
                            if (inputCount >= SLIP_MAX_LENGTH) {
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
                logger.debug("ConBeeFrameHandler exited.");
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
    private synchronized void outputFrame(ConBeeFrame frame) {
        // Send the data
        try {
            for (int val : frame.getOutputBuffer()) {
                // result.append(" ");
                // result.append(String.format("%02X", b));
                // logger.debug("ASH TX: " + String.format("%02X", b));
                switch (val) {
                    case SLIP_END:
                        outputStream.write(SLIP_ESC);
                        outputStream.write(SLIP_ESC_END);
                        break;
                    case SLIP_ESC:
                        outputStream.write(SLIP_ESC);
                        outputStream.write(SLIP_ESC_ESC);
                        break;
                    default:
                        outputStream.write(val);
                        break;
                }
            }
        } catch (IOException e) {
            logger.debug(e.getMessage());
        }
    }

    /**
     * Add a frame to the send queue. The sendQueue is a FIFO queue.
     * This method queues a {@link ConBeeFrame} frame without waiting for a response and
     * no transaction management is performed.
     *
     * @param transaction
     *            {@link EzspFrameRequest}
     */
    public void queueFrame(ConBeeFrame request) {
        sendQueue.add(request);

        // logger.debug("TX EZSP queue: {}", sendQueue.size());

        outputFrame(request);
    }

    interface ConBeeListener {
        boolean transactionEvent(ConBeeFrame response);
    }
}

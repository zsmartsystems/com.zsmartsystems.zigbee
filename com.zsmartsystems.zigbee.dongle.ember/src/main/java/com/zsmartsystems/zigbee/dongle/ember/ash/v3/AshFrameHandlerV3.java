package com.zsmartsystems.zigbee.dongle.ember.ash.v3;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.dongle.ember.EzspFrameHandler;
import com.zsmartsystems.zigbee.dongle.ember.ash.AshFrame;
import com.zsmartsystems.zigbee.dongle.ember.ash.AshFrame.FrameType;
import com.zsmartsystems.zigbee.dongle.ember.ash.AshFrameHandler;

/**
 * Implements the ASHv3 protocol defined in the document
 * UG115: ASHv3 Protocol Reference
 *
 * @author Chris Jackson
 *
 */
public class AshFrameHandlerV3 extends AshFrameHandler {
    /**
     * The logger.
     */
    private final Logger logger = LoggerFactory.getLogger(AshFrameHandlerV3.class);

    public AshFrameHandlerV3(EzspFrameHandler frameHandler) {
        super(frameHandler);
    }

    @Override
    protected int[] getPacket() throws IOException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected AshFrame getAshFrame(FrameType frameType) {
        switch (frameType) {
            case ACK:
                break;
            case DATA:
                break;
            case NAK:
                break;
            case RST:
                break;
            case RSTACK:
                break;
            default:
                logger.debug("Attempt to create unsupported ASHv2 frame {}", frameType);
                break;
        }

        return null;
    }

    @Override
    protected AshFrame createAshFrame(int[] buffer) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected int[] getOutputBuffer(AshFrame frame) {
        // TODO Auto-generated method stub
        return null;
    }
}

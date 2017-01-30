
package org.bubblecloud.zigbee.v3;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.ZigBeeDevice;
import com.zsmartsystems.zigbee.ZigBeeNetworkDeviceListener;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeePort;
import com.zsmartsystems.zigbee.ZigBeeTransportTransmit;
import com.zsmartsystems.zigbee.dongle.cc2531.ZigBeeDongleTiCc2531;

/**
 * Test class for local ZigBee API.
 */
public class ZigBeeApiCc2531ImplTest {
    /**
     * The LOGGER.
     */
    private final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ZigBeeApiCc2531ImplTest.class);

    /**
     * Tests local ZigBee API.
     */
    @Test
    @Ignore
    public void testZigBeeApiLocal() {
        final ZigBeePort port = null;// = new SerialPortImpl("COM5");
        final ZigBeeTransportTransmit dongle = new ZigBeeDongleTiCc2531(port);

        ZigBeeNetworkManager networkManager = new ZigBeeNetworkManager(dongle);

        networkManager.addNetworkDeviceListener(new ZigBeeNetworkDeviceListener() {
            @Override
            public void deviceAdded(ZigBeeDevice device) {
                LOGGER.info("Device added: " + device);
            }

            @Override
            public void deviceUpdated(ZigBeeDevice device) {
                LOGGER.info("Device updated: " + device);
            }

            @Override
            public void deviceRemoved(ZigBeeDevice device) {
                LOGGER.info("Device removed: " + device);
            }
        });

        networkManager.startup();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        final List<ZigBeeDevice> devices = networkManager.getDevices();
        for (final ZigBeeDevice device : devices) {
            LOGGER.info(device.toString());
        }

        networkManager.shutdown();
        port.close();
    }

}

package com.zsmartsystems.zigbee.console;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.xml.DOMConfigurator;
import org.bouncycastle.util.encoders.Hex;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeePort;
import com.zsmartsystems.zigbee.ZigBeeTransportTransmit;
import com.zsmartsystems.zigbee.dongle.cc2531.ZigBeeDongleTiCc2531;
import com.zsmartsystems.zigbee.dongle.ember.ZigBeeDongleEzsp;
import com.zsmartsystems.zigbee.serial.SerialPortImpl;
import com.zsmartsystems.zigbee.serialization.DefaultDeserializer;
import com.zsmartsystems.zigbee.serialization.DefaultSerializer;

/**
 * The ZigBee gateway console. Simple console used as an example and test application.
 *
 * @author Tommi S.E. Laukkanen
 * @author Chris Jackson
 */
public class ZigBeeConsoleMain {
    /**
     * The {@link org.slf4j.Logger}.
     */
    private final static org.slf4j.Logger logger = LoggerFactory.getLogger(ZigBeeConsoleMain.class);
    /**
     * The usage.
     */
    public static final String USAGE = "Syntax: java -jar zigbee4java-serialPort.jar [EMBER|CC2531] SERIALPORT SERIALBAUD CHANNEL PAN NETWORK_KEY RESET";

    /**
     * Private constructor to disable constructing main class.
     */
    private ZigBeeConsoleMain() {
    }

    /**
     * The main method.
     *
     * @param args the command arguments
     */
    public static void main(final String[] args) {
        DOMConfigurator.configure("./log4j.xml");

        final String serialPortName;
        final String dongleName;
        final int serialBaud;
        final int channel;
        final int pan;
        final byte[] networkKey;
        final boolean resetNetwork;
        try {
            dongleName = args[0];
            serialPortName = args[1];
            serialBaud = Integer.parseInt(args[2]);
            channel = Integer.parseInt(args[3]);
            pan = parseDecimalOrHexInt(args[4]);

            if (!StringUtils.isEmpty(System.getenv("ZIGBEE_NETWORK_KEY"))) {
                logger.info("ZigBee network key defined by environment variable.");
                networkKey = Hex.decode(System.getenv("ZIGBEE_NETWORK_KEY"));
            } else if (args[5].equals("00000000000000000000000000000000")) {
                logger.info("ZigBee network key left as default according to command argument.");
                networkKey = null;
            } else {
                logger.info("ZigBee network key defined by command argument.");
                networkKey = Hex.decode(args[5]);
            }
            if (networkKey != null && networkKey.length != 16) {
                logger.warn("ZigBee network key length should be 16 bytes.");
                return;
            }

            resetNetwork = args[6].equals("true");
        } catch (final Throwable t) {
            t.printStackTrace();
            System.out.println(USAGE);
            return;
        }

        final ZigBeePort serialPort = new SerialPortImpl(serialPortName, serialBaud);

        System.out.println("Initialising console...");

        final ZigBeeTransportTransmit dongle;
        if (dongleName.toUpperCase().equals("CC2531")) {
            dongle = new ZigBeeDongleTiCc2531(serialPort);
        } else if (dongleName.toUpperCase().equals("EMBER")) {
            dongle = new ZigBeeDongleEzsp(serialPort);
        } else {
            dongle = null;
        }

        if (dongle == null) {
            System.out.println("Dongle not opened.");
            System.out.println(USAGE);
            return;
        }

        ZigBeeNetworkManager networkManager = new ZigBeeNetworkManager(dongle);

        networkManager.setSerializer(DefaultSerializer.class, DefaultDeserializer.class);
        final ZigBeeConsole console = new ZigBeeConsole(networkManager, dongle);

        // Initialise the network
        networkManager.initialize();

        System.out.println("PAN ID          = " + networkManager.getZigBeePanId());
        System.out.println("Extended PAN ID = " + String.format("%08X", networkManager.getZigBeeExtendedPanId()));
        System.out.println("Channel         = " + networkManager.getZigBeeChannel());

        if (resetNetwork == true) {
            // networkManager.setZigBeeChannel(channel);
            // networkManager.setZigBeePanId(pan);
            // networkManager.setZigBeeSecurityKey(networkKey);
        }

        console.start();
    }

    /**
     * Parse decimal or hexadecimal integer.
     *
     * @param s the string
     * @return the parsed integer value
     */
    private static int parseDecimalOrHexInt(String s) {
        int radix = 10;
        String number = s;
        if (number.startsWith("0x")) {
            number = number.substring(2);
            radix = 16;
        }
        return Integer.parseInt(number, radix);
    }
}

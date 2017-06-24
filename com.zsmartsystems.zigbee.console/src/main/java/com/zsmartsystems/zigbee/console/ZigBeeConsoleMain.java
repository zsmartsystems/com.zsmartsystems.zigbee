package com.zsmartsystems.zigbee.console;

import org.apache.log4j.xml.DOMConfigurator;
import org.bouncycastle.util.encoders.Hex;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.ExtendedPanId;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNetworkStateSerializer;
import com.zsmartsystems.zigbee.dongle.cc2531.ZigBeeDongleTiCc2531;
import com.zsmartsystems.zigbee.dongle.ember.ZigBeeDongleEzsp;
import com.zsmartsystems.zigbee.serial.SerialPortImpl;
import com.zsmartsystems.zigbee.serialization.DefaultDeserializer;
import com.zsmartsystems.zigbee.serialization.DefaultSerializer;
import com.zsmartsystems.zigbee.transport.ZigBeePort;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportTransmit;

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
    public static final String USAGE = "Syntax: java -jar zigbee4java-serialPort.jar [EMBER|CC2531] SERIALPORT SERIALBAUD CHANNEL PAN EPAN NETWORK_KEY RESET";

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
        final ExtendedPanId extendedPan;
        final int[] networkKey;
        boolean resetNetwork;
        try {
            dongleName = args[0];
            serialPortName = args[1];
            serialBaud = Integer.parseInt(args[2]);
            channel = Integer.parseInt(args[3]);
            pan = parseDecimalOrHexInt(args[4]);
            extendedPan = new ExtendedPanId(args[5]);

            if (args[6].equals("00000000000000000000000000000000")) {
                logger.info("ZigBee network key left as default according to command argument.");
                networkKey = null;
            } else {
                logger.info("ZigBee network key defined by command argument.");
                byte[] key = Hex.decode(args[6]);
                networkKey = new int[16];
                int cnt = 0;
                for (byte value : key) {
                    networkKey[cnt++] = value & 0xff;
                }
            }
            if (networkKey != null && networkKey.length != 16) {
                logger.warn("ZigBee network key length should be 16 bytes.");
                return;
            }

            resetNetwork = args[7].equals("true");
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

        ZigBeeNetworkStateSerializer networkStateSerializer = new ZigBeeNetworkStateSerializerImpl();
        networkManager.setNetworkStateSerializer(networkStateSerializer);
        networkManager.setSerializer(DefaultSerializer.class, DefaultDeserializer.class);
        final ZigBeeConsole console = new ZigBeeConsole(networkManager, dongle);

        // Initialise the network
        networkManager.initialize();

        System.out.println("PAN ID          = " + networkManager.getZigBeePanId());
        System.out.println("Extended PAN ID = " + networkManager.getZigBeeExtendedPanId());
        System.out.println("Channel         = " + networkManager.getZigBeeChannel());

        if (resetNetwork == true) {
            System.out.println("*** Resetting network");
            System.out.println("  * Channel          = " + channel);
            System.out.println("  * PAN ID           = " + pan);
            System.out.println("  * Extended PAN ID  = " + extendedPan);

            networkManager.setZigBeeChannel(channel);
            networkManager.setZigBeePanId(pan);
            networkManager.setZigBeeExtendedPanId(extendedPan);
            if (networkKey != null) {
                networkManager.setZigBeeSecurityKey(networkKey);
            }
        }

        if (!networkManager.startup(resetNetwork)) {
            System.out.println("ZigBee API starting up ... [FAIL]");
            return;
        } else {
            System.out.println("ZigBee API starting up ... [OK]");
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

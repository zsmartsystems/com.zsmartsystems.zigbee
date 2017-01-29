package com.zsmartsystems.zigbee.dongle.ember.ezsp;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import com.zsmartsystems.zigbee.dongle.ember.ash.AshFrameData;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberNodeType;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberStatus;

/**
 * The EmberZNet Serial Protocol (EZSP) is the protocol used by a host
 * application processor to interact with the EmberZNet PRO stack running on a
 * Network CoProcessor (NCP).
 * <p>
 * Reference: UG100: EZSP Reference Guide
 * <p>
 * An EZSP Frame is made up as follows -:
 * <ul>
 * <li>Sequence : 1 byte sequence number
 * <li>Frame Control: 1 byte
 * <li>Frame ID : 1 byte
 * <li>Parameters : variable length
 * </ul>
 *
 * @author Chris Jackson
 *
 */
public abstract class EzspFrame extends EzspData {
    protected EmberStatus emberStatus = EmberStatus.EMBER_UNINTIALISED;

    protected static final int FRAME_ID_ADD_ENDPOINT = 0x02;
    protected static final int FRAME_ID_CHILD_JOIN_HANDLER = 0x23;
    // protected static final int FRAME_ID_CLEAR_BINDING_TABLE = 0x2A;
    // protected static final int FRAME_ID_DELETE_BINDING = 0x2D;
    // protected static final int FRAME_ID_ENERGY_SCAN_REQUEST = 0x9C;
    protected static final int FRAME_ID_ENERGY_SCAN_RESULT_HANDLER = 0x48;
    protected static final int FRAME_ID_FORM_NETWORK = 0x1E;
    // protected static final int FRAME_ID_GET_BINDING = 0x2C;
    // protected static final int FRAME_ID_GET_CHILD_DATA = 0x4A;
    protected static final int FRAME_ID_GET_CONFIGURATION_VALUE = 0x52;
    // protected static final int FRAME_ID_GET_EIU64 = 0x26;
    // protected static final int FRAME_ID_GET_NEIGHBOR = 0x79;
    protected static final int FRAME_ID_GET_NETWORK_PARAMETERS = 0x28;
    // protected static final int FRAME_ID_GET_NODE_ID = 0x27;
    // protected static final int FRAME_ID_GET_PARENT_CHILD_PARAMETERS = 0x29;
    // protected static final int FRAME_ID_GET_POLICY = 0x56;
    // protected static final int FRAME_ID_GET_ROUTE_TABLE_ENTRY = 0x7B;
    // protected static final int FRAME_ID_ID_CONFLICT_HANDLER = 0x7C;
    // protected static final int FRAME_ID_INCOMING_MESSAGE_HANDLER = 0x45;
    // protected static final int FRAME_ID_INCOMING_ROUTE_ERROR_HANDLER = 0x80;
    // protected static final int FRAME_ID_INCOMING_ROUTE_RECORD_HANDLER = 0x59;
    // protected static final int FRAME_ID_INVALID_COMMAND = 0x62;
    // protected static final int FRAME_ID_JOIN_NETWORK = 0x1F;
    // protected static final int FRAME_ID_LEAVE_NETWORK = 0x20;
    // protected static final int FRAME_ID_LOOKUP_EUI64_BY_NODE_ID = 0x61;
    // protected static final int FRAME_ID_MAXIMUM_PAYLOAD_LENGTH = 0x33;
    // protected static final int FRAME_ID_MESSAGE_SENT_HANDLER = 0x3F;
    // protected static final int FRAME_ID_NEIGHBOR_COUNT = 0x7A;
    protected static final int FRAME_ID_NETWORK_FOUND_HANDLER = 0x1B;
    protected static final int FRAME_ID_NETWORK_INIT = 0x17;
    // protected static final int FRAME_ID_NETWORK_INIT_EXTENDED = 0x70;
    // protected static final int FRAME_ID_NETWORK_STATE = 0x18;
    protected static final int FRAME_ID_PERMIT_JOINING = 0x22;
    // protected static final int FRAME_ID_READ_AND_CLEAR_COUNTERS = 0x65;
    // protected static final int FRAME_ID_READ_COUNTERS = 0xF1;
    // protected static final int FRAME_ID_REMOVE_DEVICE = 0xA8;
    protected static final int FRAME_ID_SCAN_COMPLETE_HANDLER = 0x1C;
    // protected static final int FRAME_ID_SEND_BROADCAST = 0x36;
    // protected static final int FRAME_ID_SEND_MULTICAST = 0x38;
    protected static final int FRAME_ID_SEND_UNICAST = 0x34;
    // protected static final int FRAME_ID_SET_BINDING = 0x2B;
    // protected static final int FRAME_ID_SET_CONFIGURATION_VALUE = 0x53;
    protected static final int FRAME_ID_SET_INITIAL_SECURITY_STATE = 0x68;
    protected static final int FRAME_ID_STACK_STATUS_HANDLER = 0x19;
    protected static final int FRAME_ID_START_SCAN = 0x1A;
    // protected static final int FRAME_ID_STOP_SCAN = 0x1D;
    protected static final int FRAME_ID_VERSION = 0x00;

    protected int sequenceNumber;
    protected int frameControl;
    protected final int frameId;
    protected boolean isResponse = false;

    private static Map<Integer, Class<?>> ezspHandlerMap = new HashMap<Integer, Class<?>>();
    static {
        ezspHandlerMap.put(FRAME_ID_ADD_ENDPOINT, EzspAddEndpointResponse.class);
        ezspHandlerMap.put(FRAME_ID_CHILD_JOIN_HANDLER, EzspChildJoinHandlerResponse.class);
        ezspHandlerMap.put(FRAME_ID_ENERGY_SCAN_RESULT_HANDLER, EzspEnergyScanResultHandlerResponse.class);
        ezspHandlerMap.put(FRAME_ID_FORM_NETWORK, EzspFormNetworkResponse.class);
        ezspHandlerMap.put(FRAME_ID_GET_CONFIGURATION_VALUE, EzspGetConfigurationValueResponse.class);
        ezspHandlerMap.put(FRAME_ID_GET_NETWORK_PARAMETERS, EzspGetNetworkParametersResponse.class);
        ezspHandlerMap.put(FRAME_ID_NETWORK_FOUND_HANDLER, EzspNetworkFoundHandlerResponse.class);
        ezspHandlerMap.put(FRAME_ID_NETWORK_INIT, EzspNetworkInitResponse.class);
        ezspHandlerMap.put(FRAME_ID_PERMIT_JOINING, EzspPermitJoiningResponse.class);
        ezspHandlerMap.put(FRAME_ID_SCAN_COMPLETE_HANDLER, EzspScanCompleteHandlerResponse.class);
        ezspHandlerMap.put(FRAME_ID_SET_INITIAL_SECURITY_STATE, EzspSetInitialSecurityStateResponse.class);
        ezspHandlerMap.put(FRAME_ID_STACK_STATUS_HANDLER, EzspStackStatusHandlerResponse.class);
        ezspHandlerMap.put(FRAME_ID_START_SCAN, EzspStartScanResponse.class);
        ezspHandlerMap.put(FRAME_ID_VERSION, EzspVersionResponse.class);
    }

    /**
     * Constructor used to create an outgoing frame
     *
     * @param frameId
     */
    EzspFrame(int frameId) {
        this.frameId = frameId;
    }

    /**
     * Constructor used to create a received frame
     *
     * @param inputBuffer
     */
    public EzspFrame(int[] inputBuffer) {
        this.buffer = inputBuffer;

        this.sequenceNumber = inputBuffer[0];
        this.frameControl = inputBuffer[1];
        this.frameId = inputBuffer[2];
        this.isResponse = (inputBuffer[1] & 0x80) != 0;
    }

    /**
     * Sets the 8 bit transaction sequence number
     *
     * @param sequenceNumber
     */
    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    /**
     * Gets the 8 bit transaction sequence number
     *
     * @return sequence number
     */
    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public boolean isResponse() {
        return isResponse;
    }

    public int getFrameId() {
        return frameId;
    }

    /**
     * Returns the current status of the transaction. If the transaction has not
     * been sent to the NCP, it will be EMBER_UNINITIALISED. Once a response has
     * been received, this will reflect the status received from the NCP if the
     * NCP provides a status with the response, otherwise the status will be set
     * to EMBER_SUCCESS.
     *
     * @return {@link EmberStatus} of the transaction
     */
    public EmberStatus getEmberStatus() {
        return emberStatus;
    }

    protected EmberStatus inputEmberStatus() {
        return EmberStatus.getEmberStatus(buffer[position++]);
    }

    protected EmberNodeType inputEmberNodeType() {
        return EmberNodeType.getEmberNodeType(buffer[position++]);
    }

    protected boolean initialiseEzspResponse(int[] inputBuffer) {
        buffer = inputBuffer;
        position = 0;

        // Check the sequence number
        if (inputBuffer[0] != sequenceNumber) {
            return false;
        }

        // Make sure this is a response
        if ((inputBuffer[1] & 0x80) == 0) {
            return false;
        }

        position = 3;

        // Default the status to success. This can be overridden if the response
        // provides a status
        emberStatus = EmberStatus.EMBER_SUCCESS;

        return true;
    }

    protected boolean initialEzspResponse(EzspFrame response) {
        // Make sure this is a response
        if (response.isResponse() == false) {
            return false;
        }

        // Check the sequence number
        if (response.getSequenceNumber() != sequenceNumber) {
            return false;
        }

        // Default the status to success. This can be overridden if the response
        // provides a status
        emberStatus = EmberStatus.EMBER_SUCCESS;

        return true;
    }

    public static EzspFrameResponse createHandler(AshFrameData data) {
        Class<?> ezspClass = ezspHandlerMap.get(data.getDataBuffer()[2]);
        if (ezspClass == null) {
            return null;
        }

        Constructor<?> ctor;
        try {
            ctor = ezspClass.getConstructor(int[].class);
            EzspFrameResponse ezspFrame = (EzspFrameResponse) ctor.newInstance(data.getDataBuffer());
            return ezspFrame;
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }
}

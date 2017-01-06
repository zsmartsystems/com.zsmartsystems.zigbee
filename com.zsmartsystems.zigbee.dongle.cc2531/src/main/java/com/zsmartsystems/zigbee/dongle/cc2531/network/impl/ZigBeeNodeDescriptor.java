package com.zsmartsystems.zigbee.dongle.cc2531.network.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_NODE_DESC_RSP;

/**
 * This implements the Node Descriptor.
 * <p>
 * The node descriptor contains information about the capabilities of the ZigBee
 * node and is mandatory for each node.
 * <p>
 * The node descriptor may be requested during service discovery to gain
 * information about the device.
 * <p>
 * There shall be only one node descriptor in a node.
 * 
 * @author <a href="mailto:chris@cd-jackson.com">Chris Jackson</a>
 */
public class ZigBeeNodeDescriptor {
	private LOGICAL_TYPE logicalType;
	private int manufacturerCode;
	private int maximumBufferSize;
	private int maximumTransferSize;
	private List<MAC_CAPABILITY> macCapabilities;
	private List<SERVER_MASK> serverMask;

	/**
	 * The logical type field of the node descriptor is three bits in length and
	 * specifies the device type of the ZigBee node.
	 *
	 */
	enum LOGICAL_TYPE {
		COORDINATOR(0), ROUTER(1), END_DEVICE(2), UNKNOWN(-1);

		private static Map<Integer, LOGICAL_TYPE> mapping;
		private int key;

		private LOGICAL_TYPE(int key) {
			this.key = key;
		}

		private static void initMapping() {
			mapping = new HashMap<Integer, LOGICAL_TYPE>();
			for (LOGICAL_TYPE s : values()) {
				mapping.put(s.key, s);
			}
		}

		/**
		 * Lookup function based on the code. Returns null if the code does not
		 * exist.
		 * 
		 * @param i
		 *            the code to lookup
		 * @return enumeration value of the mode type.
		 */
		public static LOGICAL_TYPE getType(int i) {
			if (mapping == null) {
				initMapping();
			}
			return mapping.get(i);
		}

		/**
		 * Gets the key for this enum
		 * 
		 * @return the key
		 */
		public int getKey() {
			return key;
		}
	}

	/**
	 * The server mask field of the node descriptor is sixteen bits in length,
	 * with bit settings signifying the system server capabilities of this node.
	 */
	enum SERVER_MASK {
		PRIMARY_TRUST_CENTRE(1), BACKUP_TRUST_CENTRE(2), PRIMARY_BINDING_TABLE(
				4), BACKUP_BINDING_TABLE(8), PRIMARY_DISCOVERY_CACHE(16);

		private static Map<Integer, SERVER_MASK> mapping;
		private int key;

		private SERVER_MASK(int key) {
			this.key = key;
		}

		private static void initMapping() {
			mapping = new HashMap<Integer, SERVER_MASK>();
			for (SERVER_MASK s : values()) {
				mapping.put(s.key, s);
			}
		}

		/**
		 * Lookup function based on the code. Returns null if the code does not
		 * exist.
		 * 
		 * @param i
		 *            the code to lookup
		 * @return enumeration value of the mode type.
		 */
		public static SERVER_MASK getType(int i) {
			if (mapping == null) {
				initMapping();
			}
			return mapping.get(i);
		}

		/**
		 * Gets the key for this enum
		 * 
		 * @return the key
		 */
		public int getKey() {
			return key;
		}
	}

	/**
	 * The MAC capability flags field is eight bits in length and specifies the
	 * node capabilities, as required by the IEEE 802.15.4-2003 MAC sub-layer
	 *
	 */
	enum MAC_CAPABILITY {
		PAN_COORDINATOR(1), FFD(2), MAINS_POWER(4), RX_ALWAYS_ON(8), SECURITY_CAPABLE(
				64);

		private static Map<Integer, MAC_CAPABILITY> mapping;
		private int key;

		private MAC_CAPABILITY(int key) {
			this.key = key;
		}

		private static void initMapping() {
			mapping = new HashMap<Integer, MAC_CAPABILITY>();
			for (MAC_CAPABILITY s : values()) {
				mapping.put(s.key, s);
			}
		}

		/**
		 * Lookup function based on the code. Returns null if the code does not
		 * exist.
		 * 
		 * @param i
		 *            the code to lookup
		 * @return enumeration value of the mode type.
		 */
		public static MAC_CAPABILITY getType(int i) {
			if (mapping == null) {
				initMapping();
			}
			return mapping.get(i);
		}

		/**
		 * Gets the key for this enum
		 * 
		 * @return the key
		 */
		public int getKey() {
			return key;
		}
	}

    @SuppressWarnings("unused")
    private ZigBeeNodeDescriptor() {
        // required for Jackson deserialization
    }

	public ZigBeeNodeDescriptor(ZDO_NODE_DESC_RSP descriptorResponse) {
		logicalType = LOGICAL_TYPE.getType(descriptorResponse.NodeType);
		if (logicalType == null) {
			logicalType = LOGICAL_TYPE.UNKNOWN;
		}

		macCapabilities = new ArrayList<MAC_CAPABILITY>();
		for (MAC_CAPABILITY entry : MAC_CAPABILITY.values()) {
			if ((entry.getKey() & descriptorResponse.Capabilities) != 0) {
				macCapabilities.add(entry);
			}
		}

		serverMask = new ArrayList<SERVER_MASK>();
		for (SERVER_MASK entry : SERVER_MASK.values()) {
			if ((entry.getKey() & descriptorResponse.ServerMask) != 0) {
				serverMask.add(entry);
			}
		}

		manufacturerCode = descriptorResponse.ManufacturerCode.get16BitValue();
		maximumBufferSize = descriptorResponse.BufferSize;
		maximumTransferSize = descriptorResponse.TransferSize.get16BitValue();
	}

	/**
	 * Returns the logical type of this node
	 * 
	 * @return {@link LOGICAL_TYPE}
	 */
	public LOGICAL_TYPE getLogicalType() {
		return logicalType;
	}

	/**
	 * The manufacturer code field of the node descriptor is sixteen bits in
	 * length and specifies a manufacturer code that is allocated by the ZigBee
	 * Alliance, relating the manufacturer to the device.
	 * 
	 * @return the manufacturer code
	 */
	public int getManufacturerCode() {
		return manufacturerCode;
	}

	/**
	 * The maximum buffer size field of the node descriptor is eight bits in
	 * length, with a valid range of 0x00-0x7f.
	 * <p>
	 * This field specifies the maximum size, in octets, of the network
	 * sub-layer data unit (NSDU) for this node. This is the maximum size of
	 * data or commands passed to or from the application by the application
	 * support sub-layer, before any fragmentation or re-assembly.
	 * <p>
	 * This field can be used as a high-level indication for network management.
	 * 
	 * @return the maximum buffer size
	 */
	public int getMaximumBufferSize() {
		return maximumBufferSize;
	}

	/**
	 * The maximum transfer size field of the node descriptor is sixteen bits in
	 * length, with a valid range of 0x0000-0x7fff.
	 * <p>
	 * This field specifies the maximum size, in octets, of the application
	 * sub-layer data unit (ASDU) that can be transferred to this node in one
	 * single message transfer. This value can exceed the value of the node
	 * maximum buffer size field (see sub-clause 2.3.2.3.8) through the use of
	 * fragmentation.
	 * 
	 * @return the maximum transfer size
	 */
	public int getMaximumTransferSize() {
		return maximumTransferSize;
	}

	/**
	 * The MAC capability flags field is eight bits in length and specifies the
	 * node capabilities, as required by the IEEE 802.15.4-2003 MAC sub-layer.
	 * <p>
	 * The alternate PAN coordinator sub-field is one bit in length and shall be
	 * set to 1 if this node is capable of becoming a PAN coordinator.
	 * Otherwise, the alternative PAN coordinator sub-field shall be set to 0.
	 * <p>
	 * The device type sub-field is one bit in length and shall be set to 1 if
	 * this node is a full function device (FFD). Otherwise, the device type
	 * sub-field shall be set to 0, indicating a reduced function device (RFD).
	 * <p>
	 * The power source sub-field is one bit in length and shall be set to 1 if
	 * the current power source is mains power. Otherwise, the power source
	 * sub-field shall be set to 0. This information is derived from the node
	 * current power source field of the node power descriptor.
	 * <p>
	 * The security capability sub-field is one bit in length and shall be set
	 * to 1 if the device is capable of sending and receiving frames. Otherwise,
	 * the security capability sub-field shall be set to 0.
	 * <p>
	 * The receiver on when idle sub-field is one bit in length and shall be set
	 * to 1 if the device does not disable its receiver to conserve power during
	 * idle periods. Otherwise, the receiver on when idle sub-field shall be set
	 * to 0 The allocate address sub-field is one bit in length and shall be set
	 * to 0 or 1.
	 * 
	 * @return array containing all {@link MAC_CAPABILITY}
	 */
	public List<MAC_CAPABILITY> getMacCapabilities() {
		return macCapabilities;
	}

	/**
	 * The server mask field of the node descriptor is sixteen bits in length,
	 * with bit settings signifying the system server capabilities of this node.
	 * It is used to facilitate discovery of particular system servers by other
	 * nodes on the system.
	 * 
	 * @return array containing all {@link SERVER_MASK}
	 */
	public List<SERVER_MASK> getServerMask() {
		return serverMask;
	}
}

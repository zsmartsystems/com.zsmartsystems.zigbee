package com.zsmartsystems.zigbee.dongle.ember.ezsp.structure;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.serializer.EzspDeserializer;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.serializer.EzspSerializer;

public class EmberKeyStruct {
	
	private int bitmask;
	private int type;
	private EmberKeyData key;
	private int outgoingFrameCounter;
	private int incomingFrameCounter;
	private int sequenceNumber;
	private IeeeAddress partnerEUI64;
	
	
    /**
     * Default Constructor
     */
    public EmberKeyStruct() {
    }

    public EmberKeyStruct(EzspDeserializer deserializer) {
        deserialize(deserializer);
    }	
	
    /**
     * Getter / Setter 
     */
	
	
	public int getBitmask() {
		return bitmask;
	}
	public void setBitmask(int bitmask) {
		this.bitmask = bitmask;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public EmberKeyData getKey() {
		return key;
	}
	public void setKey(EmberKeyData key) {
		this.key = key;
	}
	public int getOutgoingFrameCounter() {
		return outgoingFrameCounter;
	}
	public void setOutgoingFrameCounter(int outgoingFrameCounter) {
		this.outgoingFrameCounter = outgoingFrameCounter;
	}
	public int getIncomingFrameCounter() {
		return incomingFrameCounter;
	}
	public void setIncomingFrameCounter(int incomingFrameCounter) {
		this.incomingFrameCounter = incomingFrameCounter;
	}
	public int getSequenceNumber() {
		return sequenceNumber;
	}
	public void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	public IeeeAddress getPartnerEUI64() {
		return partnerEUI64;
	}
	public void setPartnerEUI64(IeeeAddress partnerEUI64) {
		this.partnerEUI64 = partnerEUI64;
	}
	
    /**
     * Serialise the contents of the EZSP structure.
     *
     * @param serializer the {@link EzspSerializer} used to serialize
     */
    public int[] serialize(EzspSerializer serializer) {
        // Serialize the fields
        serializer.serializeUInt16(bitmask);
        serializer.serializeUInt8(type);
        serializer.serializeEmberKeyData(key);
        serializer.serializeUInt32(outgoingFrameCounter);
        serializer.serializeUInt32(incomingFrameCounter);
        serializer.serializeUInt8(sequenceNumber);
        serializer.serializeEmberEui64(partnerEUI64);
        return serializer.getPayload();
    }

    /**
     * Deserialise the contents of the EZSP structure.
     *
     * @param deserializer the {@link EzspDeserializer} used to deserialize
     */
    public void deserialize(EzspDeserializer deserializer) {
        // Deserialize the fields
        bitmask = deserializer.deserializeUInt16();
        type = deserializer.deserializeUInt8();
        key = deserializer.deserializeEmberKeyData();
        outgoingFrameCounter = deserializer.deserializeUInt32();
        incomingFrameCounter = deserializer.deserializeUInt32();
        sequenceNumber = deserializer.deserializeUInt8();
        partnerEUI64 = deserializer.deserializeEmberEui64();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(40);
        builder.append("EmberKeyStruct [bitmask=");
        builder.append(EmberKeyStructBitmask.getEmberKeyStructBitmask(bitmask));
        builder.append(", type=");
        builder.append(EmberKeyType.getEmberKeyType(type));
        builder.append(", key=");
        builder.append(key.toString());
        builder.append(", outgoingFrameCounter=");
        builder.append(outgoingFrameCounter);
        builder.append(", incomingFrameCounter=");
        builder.append(incomingFrameCounter);
        builder.append(", sequenceNumber=");
        builder.append(sequenceNumber);
        builder.append(", partnerEUI64=");
        builder.append(partnerEUI64.toString());
        builder.append(']');
        return builder.toString();
    }	
	

}

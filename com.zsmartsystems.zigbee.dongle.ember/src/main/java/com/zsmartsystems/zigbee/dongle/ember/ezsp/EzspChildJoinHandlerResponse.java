package com.zsmartsystems.zigbee.dongle.ember.ezsp;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberNodeType;

/**
 * Indicates that a child has joined or left.
 *
 * @author Chris Jackson
 *
 */
public class EzspChildJoinHandlerResponse extends EzspFrameResponse {
    /**
     * The index of the child of interest
     */
    private int index;

    /**
     * True if the child is joining. False the child is leaving.
     */
    private boolean joining;

    /**
     * The node ID of the child.
     */
    private int childId;

    /**
     * The EUI64 of the child.
     */
    private IeeeAddress childEui64;

    /**
     * The node type of the child.
     */
    private EmberNodeType childType;

    /**
     * 
     * @param inputBuffer
     *            the received response data
     */
    public EzspChildJoinHandlerResponse(int[] inputBuffer) {
        super(inputBuffer);

        index = inputUInt8();
        joining = inputBool();
        childId = inputUInt16();
        childEui64 = inputEmberEui64();
        childType = inputEmberNodeType();

        emberStatus = inputEmberStatus();
    }

    /**
     * @return the index
     */
    public int getIndex() {
        return index;
    }

    /**
     * @param index
     *            the index to set
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * @return the joining
     */
    public boolean getJoining() {
        return joining;
    }

    /**
     * @param joining
     *            the joining to set
     */
    public void setJoining(boolean joining) {
        this.joining = joining;
    }

    /**
     * @return the childId
     */
    public int getChildId() {
        return childId;
    }

    /**
     * @param childId
     *            the childId to set
     */
    public void setChildId(int childId) {
        this.childId = childId;
    }

    /**
     * @return the childEui64
     */
    public IeeeAddress getChildEui64() {
        return childEui64;
    }

    /**
     * @param childEui64
     *            the childEui64 to set
     */
    public void setChildEui64(IeeeAddress childEui64) {
        this.childEui64 = childEui64;
    }

    /**
     * @return the childType
     */
    public EmberNodeType getChildType() {
        return childType;
    }

    /**
     * @param childType
     *            the childType to set
     */
    public void setChildType(EmberNodeType childType) {
        this.childType = childType;
    }

    @Override
    public String toString() {
        return "EzspChildJoinHandlerResponse [index=" + index + ", joining=" + joining + ", childId=" + childId
                + ", childEui64=" + childEui64 + ", childType=" + childType + "]";
    }
}

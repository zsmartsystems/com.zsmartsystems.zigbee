package com.zsmartsystems.zigbee.dongle.ember.greenpower;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberGpAddress;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberGpSinkListEntry;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberGpSinkTableEntryStatus;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberKeyData;
import com.zsmartsystems.zigbee.greenpower.VirtualSinkEntry;

public class EzspGpSinkTableEntry implements VirtualSinkEntry {
	
    private EmberGpSinkTableEntryStatus status;
    
    private EmberGpAddress gpd;

    private int deviceId;

    private EmberGpSinkListEntry[] sinkList;

    private int assignedAlias;

    private int groupcastRadius;

    private int securityOptions;

    private int gpdSecurityFrameCounter;

    private EmberKeyData gpdKey;

    public EzspGpSinkTableEntry() {
    }

    /**
     * Internal status of the sink table entry.
     * <p>
     * EZSP type is <i>EmberGpSinkTableEntryStatus</i> - Java type is {@link EmberGpSinkTableEntryStatus}
     *
     * @return the current status as {@link EmberGpSinkTableEntryStatus}
     */
    public EmberGpSinkTableEntryStatus getStatus() {
        return status;
    }

    /**
     * Internal status of the sink table entry.
     *
     * @param status the status to set as {@link EmberGpSinkTableEntryStatus}
     */
    public void setStatus(EmberGpSinkTableEntryStatus status) {
        this.status = status;
    }

    /**
     * The addressing info of the GPD.
     * <p>
     * EZSP type is <i>EmberGpAddress</i> - Java type is {@link EmberGpAddress}
     *
     * @return the current gpd as {@link EmberGpAddress}
     */
    public EmberGpAddress getGpd() {
        return gpd;
    }

    /**
     * The addressing info of the GPD.
     *
     * @param gpd the gpd to set as {@link EmberGpAddress}
     */
    public void setGpd(EmberGpAddress gpd) {
        this.gpd = gpd;
    }

    /**
     * The device id for the GPD.
     * <p>
     * EZSP type is <i>uint8_t</i> - Java type is {@link int}
     *
     * @return the current deviceId as {@link int}
     */
    public int getDeviceId() {
        return deviceId;
    }

    /**
     * The device id for the GPD.
     *
     * @param deviceId the deviceId to set as {@link int}
     */
    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * The list of sinks (hardcoded to 2 which is the spec minimum).
     * <p>
     * EZSP type is <i>EmberGpSinkListEntry[]</i> - Java type is {@link EmberGpSinkListEntry}
     *
     * @return the current sinkList as {@link EmberGpSinkListEntry}
     */
    public EmberGpSinkListEntry[] getSinkList() {
        return sinkList;
    }

    /**
     * The list of sinks (hardcoded to 2 which is the spec minimum).
     *
     * @param sinkList the sinkList to set as {@link EmberGpSinkListEntry}
     */
    public void setSinkList(EmberGpSinkListEntry[] sinkList) {
        this.sinkList = sinkList;
    }

    /**
     * The assigned alias for the GPD.
     * <p>
     * EZSP type is <i>EmberNodeId</i> - Java type is {@link int}
     *
     * @return the current assignedAlias as {@link int}
     */
    public int getAssignedAlias() {
        return assignedAlias;
    }

    /**
     * The assigned alias for the GPD.
     *
     * @param assignedAlias the assignedAlias to set as {@link int}
     */
    public void setAssignedAlias(int assignedAlias) {
        this.assignedAlias = assignedAlias;
    }

    /**
     * The groupcast radius.
     * <p>
     * EZSP type is <i>uint8_t</i> - Java type is {@link int}
     *
     * @return the current groupcastRadius as {@link int}
     */
    public int getGroupcastRadius() {
        return groupcastRadius;
    }

    /**
     * The groupcast radius.
     *
     * @param groupcastRadius the groupcastRadius to set as {@link int}
     */
    public void setGroupcastRadius(int groupcastRadius) {
        this.groupcastRadius = groupcastRadius;
    }

    /**
     * The security options field.
     * <p>
     * EZSP type is <i>uint8_t</i> - Java type is {@link int}
     *
     * @return the current securityOptions as {@link int}
     */
    public int getSecurityOptions() {
        return securityOptions;
    }

    /**
     * The security options field.
     *
     * @param securityOptions the securityOptions to set as {@link int}
     */
    public void setSecurityOptions(int securityOptions) {
        this.securityOptions = securityOptions;
    }

    /**
     * The security frame counter of the GPD.
     * <p>
     * EZSP type is <i>EmberGpSecurityFrameCounter</i> - Java type is {@link int}
     *
     * @return the current gpdSecurityFrameCounter as {@link int}
     */
    public int getGpdSecurityFrameCounter() {
        return gpdSecurityFrameCounter;
    }

    /**
     * The security frame counter of the GPD.
     *
     * @param gpdSecurityFrameCounter the gpdSecurityFrameCounter to set as {@link int}
     */
    public void setGpdSecurityFrameCounter(int gpdSecurityFrameCounter) {
        this.gpdSecurityFrameCounter = gpdSecurityFrameCounter;
    }

    /**
     * The key to use for GPD.
     * <p>
     * EZSP type is <i>EmberKeyData</i> - Java type is {@link EmberKeyData}
     *
     * @return the current gpdKey as {@link EmberKeyData}
     */
    public EmberKeyData getGpdKey() {
        return gpdKey;
    }

    /**
     * The key to use for GPD.
     *
     * @param gpdKey the gpdKey to set as {@link EmberKeyData}
     */
    public void setGpdKey(EmberKeyData gpdKey) {
        this.gpdKey = gpdKey;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(274);
        builder.append("EmberGpSinkTableEntry [status=");
        builder.append(status);
        builder.append(", options=");
        builder.append(", gpd=");
        builder.append(gpd);
        builder.append(", deviceId=");
        builder.append(deviceId);
        builder.append(", sinkList=");
        builder.append('{');
        if (sinkList == null) {
            builder.append("null");
        } else {
            for (int cnt = 0; cnt < sinkList.length; cnt++) {
                if (cnt != 0) {
                    builder.append(' ');
                }
                builder.append(sinkList[cnt].toString());
            }
        }
        builder.append('}');
        builder.append(", assignedAlias=");
        builder.append(assignedAlias);
        builder.append(", groupcastRadius=");
        builder.append(groupcastRadius);
        builder.append(", securityOptions=");
        builder.append(securityOptions);
        builder.append(", gpdSecurityFrameCounter=");
        builder.append(gpdSecurityFrameCounter);
        builder.append(", gpdKey=");
        builder.append(gpdKey);
        builder.append(']');
        return builder.toString();
    }
}

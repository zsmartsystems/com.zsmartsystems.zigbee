package com.zsmartsystems.zigbee.dongle.ember.ezsp.structure;

import java.util.ArrayList;
import java.util.List;

import com.zsmartsystems.zigbee.ZigBeeApsHeader;

/**
 * Defines the ZigBee APS frame parameters.
 *
 * @author Chris Jackson
 *
 */
public class EmberApsFrame extends EmberStructure {

    /**
     * The application profile ID that describes the format of the message.
     */
    private int profileId;

    /**
     * The cluster ID for this message.
     */
    private int clusterId;

    /**
     * The source endpoint.
     */
    private int sourceEndpoint;

    /**
     * The destination endpoint.
     */
    private int destinationEndpoint;

    /**
     * A bitmask of options.
     */
    private List<EmberApsOption> options = new ArrayList<EmberApsOption>();

    /**
     * The group ID for this message, if it is multicast mode.
     */
    private int groupId;

    /**
     * The sequence number.
     */
    private int sequence;

    public EmberApsFrame() {
    }

    public EmberApsFrame(int[] inBuffer, int inPosition) {
        super(inBuffer, inPosition);

        profileId = inputUInt16();
        clusterId = inputUInt16();
        sourceEndpoint = inputUInt8();
        destinationEndpoint = inputUInt8();
        int optionsValue = inputUInt16();
        groupId = inputUInt16();
        sequence = inputUInt8();

        for (EmberApsOption option : EmberApsOption.values()) {
            if ((optionsValue & option.getKey()) != 0) {
                options.add(option);
            }
        }
    }

    /**
     * Create {@link EmberApsFrame} from {@link ZigBeeApsHeader}
     *
     * @param apsHeader the {@link EmberApsFrame} from the framework
     */
    public EmberApsFrame(ZigBeeApsHeader apsHeader) {
        clusterId = apsHeader.getCluster();
        profileId = apsHeader.getProfile();
        sourceEndpoint = apsHeader.getSourceEndpoint();
        destinationEndpoint = apsHeader.getDestinationEndpoint();
    }

    @Override
    public int[] getOutputBuffer() {
        resetOutputBuffer();

        int optionValue = 0;
        for (EmberApsOption option : options) {
            optionValue |= option.getKey();
        }

        outputUInt16(profileId);
        outputUInt16(clusterId);
        outputUInt8(sourceEndpoint);
        outputUInt8(destinationEndpoint);
        outputUInt16(optionValue);
        outputUInt16(groupId);
        outputUInt8(sequence);

        return super.getOutputBuffer();
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public int getClusterId() {
        return clusterId;
    }

    public void setClusterId(int clusterId) {
        this.clusterId = clusterId;
    }

    public int getSourceEndpoint() {
        return sourceEndpoint;
    }

    public void setSourceEndpoint(int sourceEndpoint) {
        this.sourceEndpoint = sourceEndpoint;
    }

    public int getDestinationEndpoint() {
        return destinationEndpoint;
    }

    public void setDestinationEndpoint(int destinationEndpoint) {
        this.destinationEndpoint = destinationEndpoint;
    }

    public List<EmberApsOption> getOptions() {
        return options;
    }

    public void setOptions(List<EmberApsOption> options) {
        this.options = options;
    }

    public void addOption(EmberApsOption option) {
        if (this.options.contains(option)) {
            return;
        }

        this.options.add(option);
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    @Override
    public String toString() {
        return "EmberApsFrame [profileId=" + profileId + ", clusterId=" + clusterId + ", sourceEndpoint="
                + sourceEndpoint + ", destinationEndpoint=" + destinationEndpoint + ", options=" + options
                + ", groupId=" + groupId + ", sequence=" + sequence + "]";
    }
}

package com.zsmartsystems.zigbee.zdo.descriptors;

import java.util.HashSet;
import java.util.Set;

import com.zsmartsystems.zigbee.serialization.ZigBeeDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * The node power descriptor gives a dynamic indication of the power status of the
 * node and is mandatory for each node. There shall be only one node power
 * descriptor in a node.
 *
 * @author Chris Jackson
 *
 */
public class PowerDescriptor {
    public enum PowerSourceType {
        /**
         * Mains power - continuous
         */
        MAINS,
        /**
         * Rechargable battery
         */
        RECHARGABLE_BATTERY,
        /**
         * Disposable battery
         */
        DISPOSABLE_BATTERY,
        /**
         * Default for unknown
         */
        UNKNOWN
    }

    public enum PowerLevelType {
        /**
         * Power level is critical - battery should be changed immediately
         */
        CRITICAL,
        /**
         * Power is low (below 33%)
         */
        LOW,
        /**
         * Power is medium (33% to 66%)
         */
        MEDIUM,
        /**
         * Power is full (above 66%)
         */
        FULL,
        /**
         * Default for unknown
         */
        UNKNOWN
    }

    /**
     * The current power mode field of the node power descriptor is four bits in length
     * and specifies the current sleep/power-saving mode of the node.
     */
    public enum CurrentPowerModeType {
        /**
         * Receiver synchronized with the receiver on when idle subfield
         * of the node descriptor.
         */
        RECEIVER_ON_IDLE,
        /**
         * Receiver comes on periodically as defined by the node
         * power descriptor.
         */
        RECEIVER_ON_PERIODICALLY,
        /**
         * Receiver comes on when stimulated, e.g. by a user pressing a
         * button.
         */
        RECEIVER_ON_STIMULATED,
        /**
         * Default for unknown
         */
        UNKNOWN
    }

    private CurrentPowerModeType currentPowerMode;
    private Set<PowerSourceType> availablePowerSources;;
    private PowerSourceType currentPowerSource;
    private PowerLevelType powerLevel;

    public PowerDescriptor() {
        // Default constructor - does nothing
    }

    /**
     *
     * @param currentPowerMode {@link CurrentPowerModeType}
     * @param availablePowerSources {@link Set} of available {@link PowerSourceType}
     * @param currentPowerSource {@linkPowerSourceType }
     * @param powerLevel {@link PowerLevelType}
     */
    public PowerDescriptor(final CurrentPowerModeType currentPowerMode,
            final Set<PowerSourceType> availablePowerSources, final PowerSourceType currentPowerSource,
            final PowerLevelType powerLevel) {
        this.currentPowerMode = currentPowerMode;
        this.availablePowerSources = availablePowerSources;
        this.currentPowerSource = currentPowerSource;
        this.powerLevel = powerLevel;
    }

    /**
     * Creates a PowerDescriptor
     *
     * @param currentPowerMode
     * @param availablePowerSources
     * @param currentPowerSource
     * @param powerLevel
     */
    public PowerDescriptor(int currentPowerMode, int availablePowerSources, int currentPowerSource, int powerLevel) {
        setCurrentPowerMode(currentPowerMode);
        setAvailablePowerSources(availablePowerSources);
        setCurrentPowerSource(currentPowerSource);
        setCurrentPowerLevel(powerLevel);
    }

    public void setCurrentPowerSource(int currentPowerSource) {
        switch (currentPowerSource) {
            case 0x01:
                this.currentPowerSource = PowerSourceType.MAINS;
                break;
            case 0x02:
                this.currentPowerSource = PowerSourceType.RECHARGABLE_BATTERY;
                break;
            case 0x04:
                this.currentPowerSource = PowerSourceType.DISPOSABLE_BATTERY;
                break;
            default:
                this.currentPowerSource = PowerSourceType.UNKNOWN;
                break;
        }
    }

    public void setAvailablePowerSources(int availablePowerSources) {
        this.availablePowerSources = new HashSet<PowerSourceType>();
        if ((availablePowerSources & 0x01) != 0) {
            this.availablePowerSources.add(PowerSourceType.MAINS);
        }
        if ((availablePowerSources & 0x02) != 0) {
            this.availablePowerSources.add(PowerSourceType.RECHARGABLE_BATTERY);
        }
        if ((availablePowerSources & 0x04) != 0) {
            this.availablePowerSources.add(PowerSourceType.DISPOSABLE_BATTERY);
        }
    }

    public void setCurrentPowerLevel(int powerLevel) {
        switch (powerLevel) {
            case 0xc:
                this.powerLevel = PowerLevelType.FULL;
                break;
            case 0x8:
                this.powerLevel = PowerLevelType.MEDIUM;
                break;
            case 0x4:
                this.powerLevel = PowerLevelType.LOW;
                break;
            case 0x0:
                this.powerLevel = PowerLevelType.CRITICAL;
                break;
            default:
                this.powerLevel = PowerLevelType.UNKNOWN;
                break;
        }
    }

    /**
     * Returns the current receiver power mode enumeration
     *
     * @return the current {@link CurrentPowerModeType}
     */
    public CurrentPowerModeType getCurrentPowerMode() {
        return currentPowerMode;
    }

    public void setCurrentPowerMode(int currentPowerMode) {
        switch (currentPowerMode) {
            case 0x00:
                this.currentPowerMode = CurrentPowerModeType.RECEIVER_ON_IDLE;
                break;
            case 0x01:
                this.currentPowerMode = CurrentPowerModeType.RECEIVER_ON_PERIODICALLY;
                break;
            case 0x02:
                this.currentPowerMode = CurrentPowerModeType.RECEIVER_ON_STIMULATED;
                break;
            default:
                this.currentPowerMode = CurrentPowerModeType.UNKNOWN;
                break;
        }
    }

    /**
     * Returns a set of the the available power sources enumeration
     *
     * @return the {@link Set} of current {@link PowerSourceType}
     */
    public Set<PowerSourceType> getAvailablePowerSources() {
        return availablePowerSources;
    }

    /**
     * Returns the current power source enumeration
     *
     * @return the current {@link PowerSourceType}
     */
    public PowerSourceType getCurrentPowerSource() {
        return currentPowerSource;
    }

    /**
     * Returns the current power level enumeration
     *
     * @return the current {@link PowerLevelType}
     */
    public PowerLevelType getPowerLevel() {
        return powerLevel;
    }

    /**
     * Serialise the contents of the structure.
     *
     * @param serializer the {@link ZclFieldSerializer} used to serialize
     */
    public int[] serialize(ZclFieldSerializer serializer) {
        // Serialize the fields

        return serializer.getPayload();
    }

    /**
     * Deserialise the contents of the structure.
     *
     * @param deserializer the {@link ZigBeeDeserializer} used to deserialize
     */
    public void deserialize(ZigBeeDeserializer deserializer) {
        // Deserialize the fields
        int byte1 = (int) deserializer.readZigBeeType(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        int byte2 = (int) deserializer.readZigBeeType(ZclDataType.UNSIGNED_8_BIT_INTEGER);

        setCurrentPowerMode(byte1 & 0x0f);
        setAvailablePowerSources(byte1 >> 4 & 0x0f);
        setCurrentPowerSource(byte2 & 0x0f);
        setCurrentPowerLevel(byte2 >> 4 & 0x0f);
    }

    @Override
    public String toString() {
        return currentPowerMode + ", " + availablePowerSources + ", " + currentPowerSource + ", " + powerLevel;
    }
}

package com.zsmartsystems.zigbee.zdo.descriptors;

import java.util.HashSet;
import java.util.Set;

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

    private final CurrentPowerModeType currentPowerMode;
    private final Set<PowerSourceType> availablePowerSources;;
    private final PowerSourceType currentPowerSource;
    private final PowerLevelType powerLevel;

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

        switch (powerLevel) {
            case 0xc0:
                this.powerLevel = PowerLevelType.FULL;
                break;
            case 0x80:
                this.powerLevel = PowerLevelType.MEDIUM;
                break;
            case 0x40:
                this.powerLevel = PowerLevelType.LOW;
                break;
            case 0x00:
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

    @Override
    public String toString() {
        return currentPowerMode + ", " + availablePowerSources + ", " + currentPowerSource + ", " + powerLevel;
    }
}

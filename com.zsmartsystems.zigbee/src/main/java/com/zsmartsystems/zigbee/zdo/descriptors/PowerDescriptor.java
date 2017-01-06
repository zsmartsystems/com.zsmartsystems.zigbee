package com.zsmartsystems.zigbee.zdo.descriptors;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author Chris Jackson
 *
 */
public class PowerDescriptor {
    public enum PowerSourceType {
        Mains,
        RechargableBattery,
        DisposableBattery
    }

    public enum PowerLevelType {
        Critical,
        Low,
        Medium,
        Full
    }

    public enum CurrentPowerModeType {
    }

    private final int currentPowerMode;
    private final Set<PowerSourceType> availablePowerSources;;
    private final PowerSourceType currentPowerSource;
    private final PowerLevelType powerLevel;

    public PowerDescriptor(final int currentPowerMode, final Set<PowerSourceType> availablePowerSources,
            final PowerSourceType currentPowerSource, final PowerLevelType powerLevel) {
        this.currentPowerMode = currentPowerMode;
        this.availablePowerSources = availablePowerSources;
        this.currentPowerSource = currentPowerSource;
        this.powerLevel = powerLevel;
    }

    public PowerDescriptor(int currentMode, int availablePowerSources, int currentPowerSource, int powerLevel) {
        this.currentPowerMode = currentMode;

        switch (currentPowerSource) {
            case 0x01:
                this.currentPowerSource = PowerSourceType.Mains;
                break;
            case 0x02:
                this.currentPowerSource = PowerSourceType.RechargableBattery;
                break;
            case 0x04:
                this.currentPowerSource = PowerSourceType.DisposableBattery;
                break;
            default:
                this.currentPowerSource = null;
                break;
        }

        this.availablePowerSources = new HashSet<PowerSourceType>();
        if ((availablePowerSources & 0x01) != 0) {
            this.availablePowerSources.add(PowerSourceType.Mains);
        }
        if ((availablePowerSources & 0x02) != 0) {
            this.availablePowerSources.add(PowerSourceType.RechargableBattery);
        }
        if ((availablePowerSources & 0x04) != 0) {
            this.availablePowerSources.add(PowerSourceType.DisposableBattery);
        }

        switch (powerLevel) {
            case 0xc0:
                this.powerLevel = PowerLevelType.Full;
                break;
            case 0x80:
                this.powerLevel = PowerLevelType.Medium;
                break;
            case 0x40:
                this.powerLevel = PowerLevelType.Low;
                break;
            case 0x00:
                this.powerLevel = PowerLevelType.Critical;
                break;
            default:
                this.powerLevel = null;
                break;
        }
    }

    public int getCurrentPowerMode() {
        return currentPowerMode;
    }

    public Set<PowerSourceType> getAvailablePowerSources() {
        return availablePowerSources;
    }

    public PowerSourceType getCurrentPowerSource() {
        return currentPowerSource;
    }

    public PowerLevelType getPowerLevel() {
        return powerLevel;
    }

    @Override
    public String toString() {
        return currentPowerMode + ", " + availablePowerSources + ", " + currentPowerSource + ", " + powerLevel;
    }
}

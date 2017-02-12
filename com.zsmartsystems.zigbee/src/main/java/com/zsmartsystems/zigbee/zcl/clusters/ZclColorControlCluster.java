package com.zsmartsystems.zigbee.zcl.clusters;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.ZigBeeDeviceAddress;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.zcl.ZclAttribute;
import com.zsmartsystems.zigbee.zcl.ZclCluster;
import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.MoveColorCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.MoveHueCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.MoveSaturationCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.MoveToColorCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.MoveToColorTemperatureCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.MoveToHueAndSaturationCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.MoveToHueCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.MoveToSaturationCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.StepColorCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.StepHueCommand;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.StepSaturationCommand;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * <b>Color control</b> cluster implementation (<i>Cluster ID 0x0300</i>).
 * <p>
 * This cluster provides an interface for changing the color of a light. Color is
 * specified according to the Commission Internationale de l'Éclairage (CIE)
 * specification CIE 1931 Color Space, [B4]. Color control is carried out in terms of
 * x,y values, as defined by this specification.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class ZclColorControlCluster extends ZclCluster {
    // Cluster ID
    public static final int CLUSTER_ID = 0x0300;

    // Cluster Name
    public static final String CLUSTER_NAME = "Color control";

    // Attribute constants
    public static final int ATTR_CURRENTHUE = 0x0000;
    public static final int ATTR_CURRENTSATURATION = 0x0001;
    public static final int ATTR_REMAININGTIME = 0x0002;
    public static final int ATTR_CURRENTX = 0x0003;
    public static final int ATTR_CURRENTY = 0x0004;
    public static final int ATTR_DRIFTCOMPENSATION = 0x0005;
    public static final int ATTR_COMPENSATIONTEXT = 0x0006;
    public static final int ATTR_COLORTEMPERATURE = 0x0007;
    public static final int ATTR_COLORMODE = 0x0008;

    // Attribute initialisation
    protected Map<Integer, ZclAttribute> initializeAttributes() {
        Map<Integer, ZclAttribute> attributeMap = new HashMap<Integer, ZclAttribute>(9);

        attributeMap.put(ATTR_CURRENTHUE, new ZclAttribute(0, "CurrentHue", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, false, true));
        attributeMap.put(ATTR_CURRENTSATURATION, new ZclAttribute(1, "CurrentSaturation", ZclDataType.UNSIGNED_8_BIT_INTEGER, false, true, false, true));
        attributeMap.put(ATTR_REMAININGTIME, new ZclAttribute(2, "RemainingTime", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, false));
        attributeMap.put(ATTR_CURRENTX, new ZclAttribute(3, "CurrentX", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, true));
        attributeMap.put(ATTR_CURRENTY, new ZclAttribute(4, "CurrentY", ZclDataType.UNSIGNED_16_BIT_INTEGER, true, true, false, true));
        attributeMap.put(ATTR_DRIFTCOMPENSATION, new ZclAttribute(5, "DriftCompensation", ZclDataType.ENUMERATION_8_BIT, false, true, false, false));
        attributeMap.put(ATTR_COMPENSATIONTEXT, new ZclAttribute(6, "CompensationText", ZclDataType.CHARACTER_STRING, false, true, false, false));
        attributeMap.put(ATTR_COLORTEMPERATURE, new ZclAttribute(7, "ColorTemperature", ZclDataType.UNSIGNED_16_BIT_INTEGER, false, true, false, true));
        attributeMap.put(ATTR_COLORMODE, new ZclAttribute(8, "ColorMode", ZclDataType.ENUMERATION_8_BIT, false, true, false, false));

        return attributeMap;
    }

    /**
     * Default constructor.
     */
    public ZclColorControlCluster(final ZigBeeNetworkManager zigbeeManager, final ZigBeeDeviceAddress zigbeeAddress) {
        super(zigbeeManager, zigbeeAddress, CLUSTER_ID, CLUSTER_NAME);
    }


    /**
     * Get the <i>CurrentHue</i> attribute [attribute ID <b>0</b>].
     * <p>
     * The CurrentHue attribute contains the current hue value of the light. It is updated
     * as fast as practical during commands that change the hue.
     * <p>
     * The hue in degrees shall be related to the CurrentHue attribute by the relationship
     * Hue = CurrentHue x 360 / 254 (CurrentHue in the range 0 - 254 inclusive)
     * <p>
     * If this attribute is implemented then the CurrentSaturation and ColorMode
     * attributes shall also be implemented.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getCurrentHueAsync() {
        return read(attributes.get(ATTR_CURRENTHUE));
    }


    /**
     * Synchronously get the <i>CurrentHue</i> attribute [attribute ID <b>0</b>].
     * <p>
     * The CurrentHue attribute contains the current hue value of the light. It is updated
     * as fast as practical during commands that change the hue.
     * <p>
     * The hue in degrees shall be related to the CurrentHue attribute by the relationship
     * Hue = CurrentHue x 360 / 254 (CurrentHue in the range 0 - 254 inclusive)
     * <p>
     * If this attribute is implemented then the CurrentSaturation and ColorMode
     * attributes shall also be implemented.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getCurrentHue(final long refreshPeriod) {
        if(refreshPeriod > 0 && attributes.get(ATTR_CURRENTHUE).getLastReportTime() != null) {
            long refreshTime = Calendar.getInstance().getTimeInMillis() - refreshPeriod;
            if(attributes.get(ATTR_CURRENTHUE).getLastReportTime().getTimeInMillis() < refreshTime) {
                return (Integer) attributes.get(ATTR_CURRENTHUE).getLastValue();
            }
        }

        return (Integer) readSync(attributes.get(ATTR_CURRENTHUE));
    }


    /**
     * Set reporting for the <i>CurrentHue</i> attribute [attribute ID <b>0</b>].
     * <p>
     * The CurrentHue attribute contains the current hue value of the light. It is updated
     * as fast as practical during commands that change the hue.
     * <p>
     * The hue in degrees shall be related to the CurrentHue attribute by the relationship
     * Hue = CurrentHue x 360 / 254 (CurrentHue in the range 0 - 254 inclusive)
     * <p>
     * If this attribute is implemented then the CurrentSaturation and ColorMode
     * attributes shall also be implemented.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param minInterval {@link int} minimum reporting period
     * @param maxInterval {@link int} maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setCurrentHueReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_CURRENTHUE), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>CurrentSaturation</i> attribute [attribute ID <b>1</b>].
     * <p>
     * The CurrentSaturation attribute holds the current saturation value of the light. It is
     * <p>
     * updated as fast as practical during commands that change the saturation.
     * The saturation shall be related to the CurrentSaturation attribute by the
     * relationship
     * Saturation = CurrentSaturation/254 (CurrentSaturation in the range 0 - 254 inclusive)
     * If this attribute is implemented then the CurrentHue and ColorMode attributes
     * shall also be implemented.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getCurrentSaturationAsync() {
        return read(attributes.get(ATTR_CURRENTSATURATION));
    }


    /**
     * Synchronously get the <i>CurrentSaturation</i> attribute [attribute ID <b>1</b>].
     * <p>
     * The CurrentSaturation attribute holds the current saturation value of the light. It is
     * <p>
     * updated as fast as practical during commands that change the saturation.
     * The saturation shall be related to the CurrentSaturation attribute by the
     * relationship
     * Saturation = CurrentSaturation/254 (CurrentSaturation in the range 0 - 254 inclusive)
     * If this attribute is implemented then the CurrentHue and ColorMode attributes
     * shall also be implemented.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getCurrentSaturation(final long refreshPeriod) {
        if(refreshPeriod > 0 && attributes.get(ATTR_CURRENTSATURATION).getLastReportTime() != null) {
            long refreshTime = Calendar.getInstance().getTimeInMillis() - refreshPeriod;
            if(attributes.get(ATTR_CURRENTSATURATION).getLastReportTime().getTimeInMillis() < refreshTime) {
                return (Integer) attributes.get(ATTR_CURRENTSATURATION).getLastValue();
            }
        }

        return (Integer) readSync(attributes.get(ATTR_CURRENTSATURATION));
    }


    /**
     * Set reporting for the <i>CurrentSaturation</i> attribute [attribute ID <b>1</b>].
     * <p>
     * The CurrentSaturation attribute holds the current saturation value of the light. It is
     * <p>
     * updated as fast as practical during commands that change the saturation.
     * The saturation shall be related to the CurrentSaturation attribute by the
     * relationship
     * Saturation = CurrentSaturation/254 (CurrentSaturation in the range 0 - 254 inclusive)
     * If this attribute is implemented then the CurrentHue and ColorMode attributes
     * shall also be implemented.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param minInterval {@link int} minimum reporting period
     * @param maxInterval {@link int} maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setCurrentSaturationReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_CURRENTSATURATION), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>RemainingTime</i> attribute [attribute ID <b>2</b>].
     * <p>
     * The RemainingTime attribute holds the time remaining, in 1/10ths of a second,
     * <p>
     * until the currently active command will be complete.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getRemainingTimeAsync() {
        return read(attributes.get(ATTR_REMAININGTIME));
    }


    /**
     * Synchronously get the <i>RemainingTime</i> attribute [attribute ID <b>2</b>].
     * <p>
     * The RemainingTime attribute holds the time remaining, in 1/10ths of a second,
     * <p>
     * until the currently active command will be complete.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getRemainingTime(final long refreshPeriod) {
        if(refreshPeriod > 0 && attributes.get(ATTR_REMAININGTIME).getLastReportTime() != null) {
            long refreshTime = Calendar.getInstance().getTimeInMillis() - refreshPeriod;
            if(attributes.get(ATTR_REMAININGTIME).getLastReportTime().getTimeInMillis() < refreshTime) {
                return (Integer) attributes.get(ATTR_REMAININGTIME).getLastValue();
            }
        }

        return (Integer) readSync(attributes.get(ATTR_REMAININGTIME));
    }

    /**
     * Get the <i>CurrentX</i> attribute [attribute ID <b>3</b>].
     * <p>
     * The CurrentX attribute contains the current value of the normalized chromaticity
     * <p>
     * value x, as defined in the CIE xyY Color Space. It is updated as fast as practical
     * during commands that change the color.
     * <p>
     * The value of x shall be related to the CurrentX attribute by the relationship
     * <p>
     * x = CurrentX / 65535 (CurrentX in the range 0 to 65279 inclusive)
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getCurrentXAsync() {
        return read(attributes.get(ATTR_CURRENTX));
    }


    /**
     * Synchronously get the <i>CurrentX</i> attribute [attribute ID <b>3</b>].
     * <p>
     * The CurrentX attribute contains the current value of the normalized chromaticity
     * <p>
     * value x, as defined in the CIE xyY Color Space. It is updated as fast as practical
     * during commands that change the color.
     * <p>
     * The value of x shall be related to the CurrentX attribute by the relationship
     * <p>
     * x = CurrentX / 65535 (CurrentX in the range 0 to 65279 inclusive)
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getCurrentX(final long refreshPeriod) {
        if(refreshPeriod > 0 && attributes.get(ATTR_CURRENTX).getLastReportTime() != null) {
            long refreshTime = Calendar.getInstance().getTimeInMillis() - refreshPeriod;
            if(attributes.get(ATTR_CURRENTX).getLastReportTime().getTimeInMillis() < refreshTime) {
                return (Integer) attributes.get(ATTR_CURRENTX).getLastValue();
            }
        }

        return (Integer) readSync(attributes.get(ATTR_CURRENTX));
    }


    /**
     * Set reporting for the <i>CurrentX</i> attribute [attribute ID <b>3</b>].
     * <p>
     * The CurrentX attribute contains the current value of the normalized chromaticity
     * <p>
     * value x, as defined in the CIE xyY Color Space. It is updated as fast as practical
     * during commands that change the color.
     * <p>
     * The value of x shall be related to the CurrentX attribute by the relationship
     * <p>
     * x = CurrentX / 65535 (CurrentX in the range 0 to 65279 inclusive)
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval {@link int} minimum reporting period
     * @param maxInterval {@link int} maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setCurrentXReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_CURRENTX), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>CurrentY</i> attribute [attribute ID <b>4</b>].
     * <p>
     * The CurrentY attribute contains the current value of the normalized chromaticity
     * <p>
     * value y, as defined in the CIE xyY Color Space. It is updated as fast as practical
     * during commands that change the color.
     * <p>
     * The value of y shall be related to the CurrentY attribute by the relationship
     * <p>
     * y = CurrentY / 65535 (CurrentY in the range 0 to 65279 inclusive)
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getCurrentYAsync() {
        return read(attributes.get(ATTR_CURRENTY));
    }


    /**
     * Synchronously get the <i>CurrentY</i> attribute [attribute ID <b>4</b>].
     * <p>
     * The CurrentY attribute contains the current value of the normalized chromaticity
     * <p>
     * value y, as defined in the CIE xyY Color Space. It is updated as fast as practical
     * during commands that change the color.
     * <p>
     * The value of y shall be related to the CurrentY attribute by the relationship
     * <p>
     * y = CurrentY / 65535 (CurrentY in the range 0 to 65279 inclusive)
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getCurrentY(final long refreshPeriod) {
        if(refreshPeriod > 0 && attributes.get(ATTR_CURRENTY).getLastReportTime() != null) {
            long refreshTime = Calendar.getInstance().getTimeInMillis() - refreshPeriod;
            if(attributes.get(ATTR_CURRENTY).getLastReportTime().getTimeInMillis() < refreshTime) {
                return (Integer) attributes.get(ATTR_CURRENTY).getLastValue();
            }
        }

        return (Integer) readSync(attributes.get(ATTR_CURRENTY));
    }


    /**
     * Set reporting for the <i>CurrentY</i> attribute [attribute ID <b>4</b>].
     * <p>
     * The CurrentY attribute contains the current value of the normalized chromaticity
     * <p>
     * value y, as defined in the CIE xyY Color Space. It is updated as fast as practical
     * during commands that change the color.
     * <p>
     * The value of y shall be related to the CurrentY attribute by the relationship
     * <p>
     * y = CurrentY / 65535 (CurrentY in the range 0 to 65279 inclusive)
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval {@link int} minimum reporting period
     * @param maxInterval {@link int} maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setCurrentYReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_CURRENTY), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>DriftCompensation</i> attribute [attribute ID <b>5</b>].
     * <p>
     * The DriftCompensation attribute indicates what mechanism, if any, is in use for
     * <p>
     * compensation for color/intensity drift over time.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getDriftCompensationAsync() {
        return read(attributes.get(ATTR_DRIFTCOMPENSATION));
    }


    /**
     * Synchronously get the <i>DriftCompensation</i> attribute [attribute ID <b>5</b>].
     * <p>
     * The DriftCompensation attribute indicates what mechanism, if any, is in use for
     * <p>
     * compensation for color/intensity drift over time.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getDriftCompensation(final long refreshPeriod) {
        if(refreshPeriod > 0 && attributes.get(ATTR_DRIFTCOMPENSATION).getLastReportTime() != null) {
            long refreshTime = Calendar.getInstance().getTimeInMillis() - refreshPeriod;
            if(attributes.get(ATTR_DRIFTCOMPENSATION).getLastReportTime().getTimeInMillis() < refreshTime) {
                return (Integer) attributes.get(ATTR_DRIFTCOMPENSATION).getLastValue();
            }
        }

        return (Integer) readSync(attributes.get(ATTR_DRIFTCOMPENSATION));
    }

    /**
     * Get the <i>CompensationText</i> attribute [attribute ID <b>6</b>].
     * <p>
     * The CompensationText attribute holds a textual indication of what mechanism, if
     * <p>
     * any, is in use to compensate for color/intensity drift over time.
     * <p>
     * The attribute is of type {@link String}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getCompensationTextAsync() {
        return read(attributes.get(ATTR_COMPENSATIONTEXT));
    }


    /**
     * Synchronously get the <i>CompensationText</i> attribute [attribute ID <b>6</b>].
     * <p>
     * The CompensationText attribute holds a textual indication of what mechanism, if
     * <p>
     * any, is in use to compensate for color/intensity drift over time.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link String}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link String} attribute value, or null on error
     */
    public String getCompensationText(final long refreshPeriod) {
        if(refreshPeriod > 0 && attributes.get(ATTR_COMPENSATIONTEXT).getLastReportTime() != null) {
            long refreshTime = Calendar.getInstance().getTimeInMillis() - refreshPeriod;
            if(attributes.get(ATTR_COMPENSATIONTEXT).getLastReportTime().getTimeInMillis() < refreshTime) {
                return (String) attributes.get(ATTR_COMPENSATIONTEXT).getLastValue();
            }
        }

        return (String) readSync(attributes.get(ATTR_COMPENSATIONTEXT));
    }

    /**
     * Get the <i>ColorTemperature</i> attribute [attribute ID <b>7</b>].
     * <p>
     * The ColorTemperature attribute contains a scaled inverse of the current value of
     * <p>
     * the color temperature. It is updated as fast as practical during commands that
     * change the color.
     * <p>
     * The color temperature value in Kelvins shall be related to the ColorTemperature
     * attribute by the relationship
     * <p>
     * Color temperature = 1,000,000 / ColorTemperature (ColorTemperature in the
     * range 1 to 65279 inclusive, giving a color temperature range from 1,000,000
     * Kelvins to 15.32 Kelvins).
     * <p>
     * The value ColorTemperature = 0 indicates an undefined value. The value
     * ColorTemperature = 65535 indicates an invalid value.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getColorTemperatureAsync() {
        return read(attributes.get(ATTR_COLORTEMPERATURE));
    }


    /**
     * Synchronously get the <i>ColorTemperature</i> attribute [attribute ID <b>7</b>].
     * <p>
     * The ColorTemperature attribute contains a scaled inverse of the current value of
     * <p>
     * the color temperature. It is updated as fast as practical during commands that
     * change the color.
     * <p>
     * The color temperature value in Kelvins shall be related to the ColorTemperature
     * attribute by the relationship
     * <p>
     * Color temperature = 1,000,000 / ColorTemperature (ColorTemperature in the
     * range 1 to 65279 inclusive, giving a color temperature range from 1,000,000
     * Kelvins to 15.32 Kelvins).
     * <p>
     * The value ColorTemperature = 0 indicates an undefined value. The value
     * ColorTemperature = 65535 indicates an invalid value.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getColorTemperature(final long refreshPeriod) {
        if(refreshPeriod > 0 && attributes.get(ATTR_COLORTEMPERATURE).getLastReportTime() != null) {
            long refreshTime = Calendar.getInstance().getTimeInMillis() - refreshPeriod;
            if(attributes.get(ATTR_COLORTEMPERATURE).getLastReportTime().getTimeInMillis() < refreshTime) {
                return (Integer) attributes.get(ATTR_COLORTEMPERATURE).getLastValue();
            }
        }

        return (Integer) readSync(attributes.get(ATTR_COLORTEMPERATURE));
    }


    /**
     * Set reporting for the <i>ColorTemperature</i> attribute [attribute ID <b>7</b>].
     * <p>
     * The ColorTemperature attribute contains a scaled inverse of the current value of
     * <p>
     * the color temperature. It is updated as fast as practical during commands that
     * change the color.
     * <p>
     * The color temperature value in Kelvins shall be related to the ColorTemperature
     * attribute by the relationship
     * <p>
     * Color temperature = 1,000,000 / ColorTemperature (ColorTemperature in the
     * range 1 to 65279 inclusive, giving a color temperature range from 1,000,000
     * Kelvins to 15.32 Kelvins).
     * <p>
     * The value ColorTemperature = 0 indicates an undefined value. The value
     * ColorTemperature = 65535 indicates an invalid value.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param minInterval {@link int} minimum reporting period
     * @param maxInterval {@link int} maximum reporting period
     * @param reportableChange {@link Object} delta required to trigger report
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setColorTemperatureReporting(final int minInterval, final int maxInterval, final Object reportableChange) {
        return setReporting(attributes.get(ATTR_COLORTEMPERATURE), minInterval, maxInterval, reportableChange);
    }

    /**
     * Get the <i>ColorMode</i> attribute [attribute ID <b>8</b>].
     * <p>
     * The ColorMode attribute indicates which attributes are currently determining the
     * <p>
     * color of the device
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getColorModeAsync() {
        return read(attributes.get(ATTR_COLORMODE));
    }


    /**
     * Synchronously get the <i>ColorMode</i> attribute [attribute ID <b>8</b>].
     * <p>
     * The ColorMode attribute indicates which attributes are currently determining the
     * <p>
     * color of the device
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is OPTIONAL
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getColorMode(final long refreshPeriod) {
        if(refreshPeriod > 0 && attributes.get(ATTR_COLORMODE).getLastReportTime() != null) {
            long refreshTime = Calendar.getInstance().getTimeInMillis() - refreshPeriod;
            if(attributes.get(ATTR_COLORMODE).getLastReportTime().getTimeInMillis() < refreshTime) {
                return (Integer) attributes.get(ATTR_COLORMODE).getLastValue();
            }
        }

        return (Integer) readSync(attributes.get(ATTR_COLORMODE));
    }

    /**
     * The Move to Hue Command
     *
     * @param hue {@link Integer} Hue
     * @param direction {@link Integer} Direction
     * @param transitionTime {@link Integer} Transition time
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> moveToHueCommand(Integer hue, Integer direction, Integer transitionTime) {
        MoveToHueCommand command = new MoveToHueCommand();

        // Set the fields
        command.setHue(hue);
        command.setDirection(direction);
        command.setTransitionTime(transitionTime);

        return send(command);
    }

    /**
     * The Move Hue Command
     *
     * @param moveMode {@link Integer} Move mode
     * @param rate {@link Integer} Rate
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> moveHueCommand(Integer moveMode, Integer rate) {
        MoveHueCommand command = new MoveHueCommand();

        // Set the fields
        command.setMoveMode(moveMode);
        command.setRate(rate);

        return send(command);
    }

    /**
     * The Step Hue Command
     *
     * @param stepMode {@link Integer} Step mode
     * @param stepSize {@link Integer} Step size
     * @param transitionTime {@link Integer} Transition time
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> stepHueCommand(Integer stepMode, Integer stepSize, Integer transitionTime) {
        StepHueCommand command = new StepHueCommand();

        // Set the fields
        command.setStepMode(stepMode);
        command.setStepSize(stepSize);
        command.setTransitionTime(transitionTime);

        return send(command);
    }

    /**
     * The Move to Saturation Command
     *
     * @param saturation {@link Integer} Saturation
     * @param transitionTime {@link Integer} Transition time
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> moveToSaturationCommand(Integer saturation, Integer transitionTime) {
        MoveToSaturationCommand command = new MoveToSaturationCommand();

        // Set the fields
        command.setSaturation(saturation);
        command.setTransitionTime(transitionTime);

        return send(command);
    }

    /**
     * The Move Saturation Command
     *
     * @param moveMode {@link Integer} Move mode
     * @param rate {@link Integer} Rate
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> moveSaturationCommand(Integer moveMode, Integer rate) {
        MoveSaturationCommand command = new MoveSaturationCommand();

        // Set the fields
        command.setMoveMode(moveMode);
        command.setRate(rate);

        return send(command);
    }

    /**
     * The Step Saturation Command
     *
     * @param stepMode {@link Integer} Step mode
     * @param stepSize {@link Integer} Step size
     * @param transitionTime {@link Integer} Transition time
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> stepSaturationCommand(Integer stepMode, Integer stepSize, Integer transitionTime) {
        StepSaturationCommand command = new StepSaturationCommand();

        // Set the fields
        command.setStepMode(stepMode);
        command.setStepSize(stepSize);
        command.setTransitionTime(transitionTime);

        return send(command);
    }

    /**
     * The Move to Hue and Saturation Command
     *
     * @param hue {@link Integer} Hue
     * @param saturation {@link Integer} Saturation
     * @param transitionTime {@link Integer} Transition time
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> moveToHueAndSaturationCommand(Integer hue, Integer saturation, Integer transitionTime) {
        MoveToHueAndSaturationCommand command = new MoveToHueAndSaturationCommand();

        // Set the fields
        command.setHue(hue);
        command.setSaturation(saturation);
        command.setTransitionTime(transitionTime);

        return send(command);
    }

    /**
     * The Move to Color Command
     *
     * @param colorX {@link Integer} ColorX
     * @param colorY {@link Integer} ColorY
     * @param transitionTime {@link Integer} Transition time
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> moveToColorCommand(Integer colorX, Integer colorY, Integer transitionTime) {
        MoveToColorCommand command = new MoveToColorCommand();

        // Set the fields
        command.setColorX(colorX);
        command.setColorY(colorY);
        command.setTransitionTime(transitionTime);

        return send(command);
    }

    /**
     * The Move Color Command
     *
     * @param rateX {@link Integer} RateX
     * @param rateY {@link Integer} RateY
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> moveColorCommand(Integer rateX, Integer rateY) {
        MoveColorCommand command = new MoveColorCommand();

        // Set the fields
        command.setRateX(rateX);
        command.setRateY(rateY);

        return send(command);
    }

    /**
     * The Step Color Command
     *
     * @param stepX {@link Integer} StepX
     * @param stepY {@link Integer} StepY
     * @param transitionTime {@link Integer} Transition time
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> stepColorCommand(Integer stepX, Integer stepY, Integer transitionTime) {
        StepColorCommand command = new StepColorCommand();

        // Set the fields
        command.setStepX(stepX);
        command.setStepY(stepY);
        command.setTransitionTime(transitionTime);

        return send(command);
    }

    /**
     * The Move to Color Temperature Command
     *
     * @param colorTemperature {@link Integer} Color Temperature
     * @param transitionTime {@link Integer} Transition time
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> moveToColorTemperatureCommand(Integer colorTemperature, Integer transitionTime) {
        MoveToColorTemperatureCommand command = new MoveToColorTemperatureCommand();

        // Set the fields
        command.setColorTemperature(colorTemperature);
        command.setTransitionTime(transitionTime);

        return send(command);
    }

    /**
     * Add a binding for this cluster to the local node
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> bind() {
        return bind();
    }

    @Override
    public ZclCommand getCommandFromId(int commandId) {
        switch (commandId) {
            case 0: // MOVE_TO_HUE_COMMAND
                return new MoveToHueCommand();
            case 1: // MOVE_HUE_COMMAND
                return new MoveHueCommand();
            case 2: // STEP_HUE_COMMAND
                return new StepHueCommand();
            case 3: // MOVE_TO_SATURATION_COMMAND
                return new MoveToSaturationCommand();
            case 4: // MOVE_SATURATION_COMMAND
                return new MoveSaturationCommand();
            case 5: // STEP_SATURATION_COMMAND
                return new StepSaturationCommand();
            case 6: // MOVE_TO_HUE_AND_SATURATION_COMMAND
                return new MoveToHueAndSaturationCommand();
            case 7: // MOVE_TO_COLOR_COMMAND
                return new MoveToColorCommand();
            case 8: // MOVE_COLOR_COMMAND
                return new MoveColorCommand();
            case 9: // STEP_COLOR_COMMAND
                return new StepColorCommand();
            case 10: // MOVE_TO_COLOR_TEMPERATURE_COMMAND
                return new MoveToColorTemperatureCommand();
            default:
                return null;
        }
    }
}

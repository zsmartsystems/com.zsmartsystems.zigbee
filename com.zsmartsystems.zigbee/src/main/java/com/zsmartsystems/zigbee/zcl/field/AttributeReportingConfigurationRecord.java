package com.zsmartsystems.zigbee.zcl.field;

import com.zsmartsystems.zigbee.serialization.ZigBeeDeserializer;
import com.zsmartsystems.zigbee.serialization.ZigBeeSerializer;
import com.zsmartsystems.zigbee.zcl.ZclListItemField;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Read Attribute Status Record field.
 * 
 * @author Tommi S.E. Laukkanen
 * @author Chris Jackson
 */
public class AttributeReportingConfigurationRecord implements ZclListItemField {
    /**
     * The direction.
     */
    private int direction;
    /**
     * The attribute identifier.
     */
    private int attributeIdentifier;
    /**
     * The attribute data type.
     */
    private ZclDataType attributeDataType;
    /**
     * The minimum reporting interval.
     */
    private int minimumReportingInterval;
    /**
     * The maximum reporting interval.
     */
    private int maximumReportingInterval;
    /**
     * The reportable change.
     */
    private Object reportableChange;
    /**
     * The maximum reporting interval.
     */
    private int timeoutPeriod;

    /**
     * Gets direction.
     *
     * @return the direction
     */
    public int getDirection() {
        return direction;
    }

    /**
     * Sets direction.
     *
     * @param direction the direction
     */
    public void setDirection(int direction) {
        this.direction = direction;
    }

    /**
     * Gets attribute data type.
     *
     * @return the attribute data type
     */
    public ZclDataType getAttributeDataType() {
        return attributeDataType;
    }

    /**
     * Sets attribute data type.
     *
     * @param attributeDataType the attribute data type
     */
    public void setAttributeDataType(ZclDataType attributeDataType) {
        this.attributeDataType = attributeDataType;
    }

    /**
     * Gets attribute identifier.
     *
     * @return the attribute identifier
     */
    public int getAttributeIdentifier() {
        return attributeIdentifier;
    }

    /**
     * Sets attribute identifier.
     *
     * @param attributeIdentifier the attribute identifier
     */
    public void setAttributeIdentifier(int attributeIdentifier) {
        this.attributeIdentifier = attributeIdentifier;
    }

    /**
     * Gets maximum reporting interval.
     *
     * @return the maximum reporting interval
     */
    public int getMaximumReportingInterval() {
        return maximumReportingInterval;
    }

    /**
     * Sets maximum reporting interval.
     *
     * @param maximumReportingInterval the maximum reporting interval
     */
    public void setMaximumReportingInterval(int maximumReportingInterval) {
        this.maximumReportingInterval = maximumReportingInterval;
    }

    /**
     * Gets minimum reporting interval.
     *
     * @return the minimum reporting interval
     */
    public int getMinimumReportingInterval() {
        return minimumReportingInterval;
    }

    /**
     * Sets minimum reporting interval.
     *
     * @param minimumReportingInterval the minimum reporting interval
     */
    public void setMinimumReportingInterval(int minimumReportingInterval) {
        this.minimumReportingInterval = minimumReportingInterval;
    }

    /**
     * Gets reportable change.
     *
     * @return the reportable change
     */
    public Object getReportableChange() {
        return reportableChange;
    }

    /**
     * Sets reportable change.
     *
     * @param reportableChange the reportable change
     */
    public void setReportableChange(Object reportableChange) {
        this.reportableChange = reportableChange;
    }

    /**
     * Gets timeout period.
     *
     * @return the timeout period
     */
    public int getTimeoutPeriod() {
        return timeoutPeriod;
    }

    /**
     * Sets timeout period.
     *
     * @param timeoutPeriod the timeout period
     */
    public void setTimeoutPeriod(int timeoutPeriod) {
        this.timeoutPeriod = timeoutPeriod;
    }

    @Override
    public void serialize(final ZigBeeSerializer serializer) {
        serializer.appendZigBeeType(direction, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.appendZigBeeType(attributeIdentifier, ZclDataType.UNSIGNED_16_BIT_INTEGER);

        if (direction == 1) {
            // CASE OF ATTRIBUTE CONFIGURATION SENT TO CLIENT
            // Size of: Direction + Attribute Id + Timeout
            serializer.appendZigBeeType(timeoutPeriod, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        } else {
            serializer.appendZigBeeType(attributeDataType.getId(), ZclDataType.UNSIGNED_8_BIT_INTEGER);
            serializer.appendZigBeeType(minimumReportingInterval, ZclDataType.UNSIGNED_16_BIT_INTEGER);
            serializer.appendZigBeeType(maximumReportingInterval, ZclDataType.UNSIGNED_16_BIT_INTEGER);

            if (attributeDataType.isAnalog()) {
                serializer.appendZigBeeType(reportableChange, attributeDataType);
            }
        }
    }

    @Override
    public void deserialize(final ZigBeeDeserializer deserializer) {
        direction = (int) deserializer.readZigBeeType(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        attributeIdentifier = (int) deserializer.readZigBeeType(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        if (direction == 1) {
            // CASE OF ATTRIBUTE CONFIGURATION SENT TO CLIENT
            // Size of: Direction + Attribute Id + Timeout
            timeoutPeriod = (int) deserializer.readZigBeeType(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        } else {
            attributeDataType = ZclDataType
                    .getType((int) deserializer.readZigBeeType(ZclDataType.UNSIGNED_8_BIT_INTEGER));
            minimumReportingInterval = (int) deserializer.readZigBeeType(ZclDataType.UNSIGNED_16_BIT_INTEGER);
            maximumReportingInterval = (int) deserializer.readZigBeeType(ZclDataType.UNSIGNED_16_BIT_INTEGER);
            if (attributeDataType.isAnalog()) {
                // CASE OF ATTRIBUTE CONFIGURATION SENT TO SERVER OF A ANALOG ATTRIBUTE
                // Size of: Direction + Attribute Id + Data Type + Minimum + Maximum + Change
                reportableChange = deserializer.readZigBeeType(attributeDataType);
            }
        }
    }

    @Override
    public String toString() {
        return "Attribute Reporting Configuration Record " + "attributeDataType=" + attributeDataType
                + ", attributeIdentifier=" + attributeIdentifier + ", minimumReportingInterval="
                + minimumReportingInterval + ", maximumReportingInterval=" + maximumReportingInterval
                + ", reportableChange=" + reportableChange + ", timeoutPeriod=" + timeoutPeriod;
    }
}

/**
 * Copyright (c) 2016-2025 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.greenpower;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.clusters.greenpower.GpTranslationTableUpdateTranslation;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Gp Translation Table Update value object class.
 * <p>
 * Cluster: <b>Green Power</b>. Command ID 0x07 is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Green Power cluster.
 * <p>
 * This command is generated to configure the GPD Command Translation Table.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-12-25T10:11:19Z")
public class GpTranslationTableUpdate extends ZclGreenPowerCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0021;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x07;

    /**
     * Options command message field.
     */
    private Integer options;

    /**
     * Gpd Src ID command message field.
     */
    private Integer gpdSrcId;

    /**
     * Gpd IEEE command message field.
     */
    private IeeeAddress gpdIeee;

    /**
     * Endpoint command message field.
     */
    private Integer endpoint;

    /**
     * Translations command message field.
     */
    private GpTranslationTableUpdateTranslation translations;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public GpTranslationTableUpdate() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param options {@link Integer} Options
     * @param gpdSrcId {@link Integer} Gpd Src ID
     * @param gpdIeee {@link IeeeAddress} Gpd IEEE
     * @param endpoint {@link Integer} Endpoint
     * @param translations {@link GpTranslationTableUpdateTranslation} Translations
     */
    public GpTranslationTableUpdate(
            Integer options,
            Integer gpdSrcId,
            IeeeAddress gpdIeee,
            Integer endpoint,
            GpTranslationTableUpdateTranslation translations) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;

        this.options = options;
        this.gpdSrcId = gpdSrcId;
        this.gpdIeee = gpdIeee;
        this.endpoint = endpoint;
        this.translations = translations;
    }

    /**
     * Gets Options.
     *
     * @return the Options
     */
    public Integer getOptions() {
        return options;
    }

    /**
     * Sets Options.
     *
     * @param options the Options
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setOptions(final Integer options) {
        this.options = options;
    }

    /**
     * Gets Gpd Src ID.
     *
     * @return the Gpd Src ID
     */
    public Integer getGpdSrcId() {
        return gpdSrcId;
    }

    /**
     * Sets Gpd Src ID.
     *
     * @param gpdSrcId the Gpd Src ID
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setGpdSrcId(final Integer gpdSrcId) {
        this.gpdSrcId = gpdSrcId;
    }

    /**
     * Gets Gpd IEEE.
     *
     * @return the Gpd IEEE
     */
    public IeeeAddress getGpdIeee() {
        return gpdIeee;
    }

    /**
     * Sets Gpd IEEE.
     *
     * @param gpdIeee the Gpd IEEE
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setGpdIeee(final IeeeAddress gpdIeee) {
        this.gpdIeee = gpdIeee;
    }

    /**
     * Gets Endpoint.
     *
     * @return the Endpoint
     */
    public Integer getEndpoint() {
        return endpoint;
    }

    /**
     * Sets Endpoint.
     *
     * @param endpoint the Endpoint
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setEndpoint(final Integer endpoint) {
        this.endpoint = endpoint;
    }

    /**
     * Gets Translations.
     *
     * @return the Translations
     */
    public GpTranslationTableUpdateTranslation getTranslations() {
        return translations;
    }

    /**
     * Sets Translations.
     *
     * @param translations the Translations
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setTranslations(final GpTranslationTableUpdateTranslation translations) {
        this.translations = translations;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(options, ZclDataType.BITMAP_16_BIT);
        serializer.serialize(gpdSrcId, ZclDataType.UNSIGNED_32_BIT_INTEGER);
        serializer.serialize(gpdIeee, ZclDataType.IEEE_ADDRESS);
        serializer.serialize(endpoint, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        translations.serialize(serializer);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        options = deserializer.deserialize(ZclDataType.BITMAP_16_BIT);
        gpdSrcId = deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
        gpdIeee = deserializer.deserialize(ZclDataType.IEEE_ADDRESS);
        endpoint = deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        translations = new GpTranslationTableUpdateTranslation();
        translations.deserialize(deserializer);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(169);
        builder.append("GpTranslationTableUpdate [");
        builder.append(super.toString());
        builder.append(", options=");
        builder.append(options);
        builder.append(", gpdSrcId=");
        builder.append(gpdSrcId);
        builder.append(", gpdIeee=");
        builder.append(gpdIeee);
        builder.append(", endpoint=");
        builder.append(endpoint);
        builder.append(", translations=");
        builder.append(translations);
        builder.append(']');
        return builder.toString();
    }

}

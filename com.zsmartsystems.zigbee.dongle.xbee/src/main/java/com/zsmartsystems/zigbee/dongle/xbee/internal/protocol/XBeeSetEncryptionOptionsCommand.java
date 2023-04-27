/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.xbee.internal.protocol;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Class to implement the XBee command <b>Set Encryption Options</b>.
 * <p>
 * AT Command <b>EO</b></p>Set or read the encryption enable setting.
 * <p>
 * This class provides methods for processing XBee API commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class XBeeSetEncryptionOptionsCommand extends XBeeFrame implements XBeeCommand {
    /**
     */
    private Integer frameId;

    /**
     */
    private List<EncryptionOptions> encryptionOptions = new ArrayList<EncryptionOptions>();

    /**
     *
     * @param frameId the frameId to set as {@link Integer}
     */
    public void setFrameId(Integer frameId) {
        this.frameId = frameId;
    }

    /**
     *
     * @param encryptionOptions the encryptionOptions to add to the Set as {@link EncryptionOptions}
     */
    public void addEncryptionOptions(EncryptionOptions encryptionOptions) {
        this.encryptionOptions.add(encryptionOptions);
    }

    /**
     *
     * @param encryptionOptions the encryptionOptions to remove to the Set as {@link EncryptionOptions}
     */
    public void removeEncryptionOptions(EncryptionOptions encryptionOptions) {
        this.encryptionOptions.remove(encryptionOptions);
    }

    /**
     *
     * @param encryptionOptions the encryptionOptions to set to the Set as {@link EncryptionOptions}
     */
    public void setEncryptionOptions(Collection<EncryptionOptions> encryptionOptions) {
        this.encryptionOptions.addAll(encryptionOptions);
    }

    @Override
    public int[] serialize() {
        // Serialize the command fields
        serializeCommand(0x08);
        serializeInt8(frameId);
        serializeAtCommand("EO");
        serializeEncryptionOptions(encryptionOptions);

        return getPayload();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(391);
        // First present the command parameters...
        // Then the responses later if they are available
        builder.append("XBeeSetEncryptionOptionsCommand [frameId=");
        builder.append(frameId);
        builder.append(", encryptionOptions=");
        builder.append(encryptionOptions);
        builder.append(']');
        return builder.toString();
    }
}

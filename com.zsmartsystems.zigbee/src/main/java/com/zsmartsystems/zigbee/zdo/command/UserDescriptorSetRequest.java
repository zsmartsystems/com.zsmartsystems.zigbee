package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zdo.ZdoRequest;

/**
 * User Descriptor Set Request value object class.
 * <p>
 * The User_Desc_set command is generated from a local device wishing to
 * configure the user descriptor on a remote device. This command shall be unicast
 * either to the remote device itself or to an alternative device that contains the
 * discovery information of the remote device.
 * <br>
 * The local device shall generate the User_Desc_set command using the format
 * illustrated in Table 2.55. The NWKAddrOfInterest field shall contain the network
 * address of the remote device for which the user descriptor is to be configured and
 * the UserDescription field shall contain the ASCII character string that is to be
 * configured in the user descriptor. Characters with ASCII codes numbered 0x00
 * through 0x1f are not permitted to be included in this string.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class UserDescriptorSetRequest extends ZdoRequest {
    /**
     * Default constructor.
     */
    public UserDescriptorSetRequest() {
        clusterId = 0x0014;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("UserDescriptorSetRequest ");
        builder.append(super.toString());
        return builder.toString();
    }

}

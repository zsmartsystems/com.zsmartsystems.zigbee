package com.zsmartsystems.zigbee.zdo;

import com.zsmartsystems.zigbee.Command;
import com.zsmartsystems.zigbee.CommandResponseMatcher;

/**
 * A ZDO response matcher requiring a specific packet response.
 * <p>
 * The matcher will return true if the the response packet is a specific {@link ZdoResponse}
 * and the response source address matches the destination of the request.
 *
 * @author Chris Jackson
 */
public class ZdoPacketResponseMatcher implements CommandResponseMatcher {
    private Class<?> requiredResponse;

    public ZdoPacketResponseMatcher(Class<?> requiredResponse) {
        this.requiredResponse = requiredResponse;

    }

    @Override
    public boolean isMatch(Command request, Command response) {
        if (response.getClass() == requiredResponse) {
            return ((ZdoRequest) request).getDestinationAddress() == ((ZdoResponse) response).getSourceAddress();
        } else {
            return false;
        }
    }
}

package com.zsmartsystems.zigbee.zdo;

import com.zsmartsystems.zigbee.Command;
import com.zsmartsystems.zigbee.CommandResponseMatcher;
import com.zsmartsystems.zigbee.zdo.command.SimpleDescriptorRequest;
import com.zsmartsystems.zigbee.zdo.command.SimpleDescriptorResponse;

/**
 * A ZDO response matcher requiring a specific packet response.
 * <p>
 * The matcher will return true if the the response packet is a specific {@link ZdoResponse}
 * and the response source address matches the destination of the request.
 *
 * @author Chris Jackson
 */
public class ZdoSimpleDescriptorResponseMatcher implements CommandResponseMatcher {

    @Override
    public boolean isMatch(Command request, Command response) {
        if (response instanceof SimpleDescriptorResponse) {
            return ((((ZdoRequest) request).getDestinationAddress() == ((ZdoResponse) response).getSourceAddress())
                    && ((SimpleDescriptorRequest) request).getEndpoint() == ((SimpleDescriptorResponse) response)
                            .getEndpoint());
        } else {
            return false;
        }
    }
}

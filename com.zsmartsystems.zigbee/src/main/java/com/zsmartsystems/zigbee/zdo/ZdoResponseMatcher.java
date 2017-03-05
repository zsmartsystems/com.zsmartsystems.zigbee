package com.zsmartsystems.zigbee.zdo;

import com.zsmartsystems.zigbee.Command;
import com.zsmartsystems.zigbee.CommandResponseMatcher;

/**
 * The ZDO response matcher.
 * <p>
 * The matcher will return true if the the response packet is a {@link ZdoResponse}
 * and the response source address matches the destination of the request.
 *
 * @author Tommi S.E. Laukkanen
 * @author Chris Jackson
 */
public class ZdoResponseMatcher implements CommandResponseMatcher {

    @Override
    public boolean isMatch(Command request, Command response) {
        if (response instanceof ZdoResponse) {
            return ((ZdoRequest) request).getDestinationAddress().equals(((ZdoResponse) response).getSourceAddress());
        } else {
            return false;
        }
    }
}

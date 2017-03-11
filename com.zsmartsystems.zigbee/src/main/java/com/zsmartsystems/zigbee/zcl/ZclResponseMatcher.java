package com.zsmartsystems.zigbee.zcl;

import com.zsmartsystems.zigbee.Command;
import com.zsmartsystems.zigbee.CommandResponseMatcher;

/**
 * The ZCL response matcher.
 * <p>
 * Implements {@link CommandResponseMatcher} to check if a ZCL transaction matches a request.
 * The matcher will return true if the request and response transaction IDs match.
 *
 * @author Tommi S.E. Laukkanen
 * @author Chris Jackson
 */
public class ZclResponseMatcher implements CommandResponseMatcher {

    @Override
    public boolean isMatch(Command request, Command response) {
        if (response instanceof ZclCommand && ((ZclCommand) request).getTransactionId() != null) {
            final int transactionId = ((ZclCommand) request).getTransactionId();
            return Integer.valueOf(transactionId).equals(((ZclCommand) response).getTransactionId());
        } else {
            return false;
        }
    }
}

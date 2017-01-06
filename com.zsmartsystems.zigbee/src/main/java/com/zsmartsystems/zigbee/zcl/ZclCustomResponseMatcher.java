package com.zsmartsystems.zigbee.zcl;

import com.zsmartsystems.zigbee.Command;
import com.zsmartsystems.zigbee.CommandResponseMatcher;
import com.zsmartsystems.zigbee.zcl.clusters.general.DefaultResponse;

/**
 * <p>
 * ZCL custom response matcher.
 * </p>
 * <p>
 * Implements {@link CommandResponseMatcher} to check if a ZCL transaction matches a request.
 * The matcher will return true if the request and response transaction IDs match.
 * If the response matches the {@link DefaultResponse} class, then the status code is mustn't be 0.
 * </p>
 *
 * @author Tommi S.E. Laukkanen
 * @author Chris Jackson
 */
public class ZclCustomResponseMatcher implements CommandResponseMatcher {
    @Override
    public boolean isMatch(Command request, Command response) {
        if (((ZclCommand) request).getTransactionId() != null) {
            final int transactionId = ((ZclCommand) request).getTransactionId();
            if (new Integer(transactionId).equals(((ZclCommand) response).getTransactionId())) {
                if (response instanceof DefaultResponse) {
                    if (((DefaultResponse) response).getStatusCode() == 0) {
                        return false; // Default response success another response incoming, skip this one.
                    } else {
                        return true; // Default response failure, return this one.
                    }
                } else {
                    return true; // This is the actual response, return this one.
                }
            } else {
                return false; // Transaction ID mismatch.
            }
        } else {
            return false; // Transaction ID not set in original command.
        }
    }
}

package com.zsmartsystems.zigbee;

/**
 * Defines the interface for the response matcher
 *
 * @author Tommi S.E. Laukkanen
 */
public interface CommandResponseMatcher {
    /**
     * Matches request and response.
     *
     * @param request the request {@link Command}
     * @param response the response {@link Command}
     * @return true if request matches response
     */
    boolean isMatch(Command request, Command response);
}

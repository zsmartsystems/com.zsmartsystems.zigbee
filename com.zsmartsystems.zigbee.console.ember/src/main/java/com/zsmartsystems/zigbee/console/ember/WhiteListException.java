package com.zsmartsystems.zigbee.console.ember;

public class WhiteListException extends RuntimeException {


    public WhiteListException() {
        super("Whitelist is not supported.");
    }
}

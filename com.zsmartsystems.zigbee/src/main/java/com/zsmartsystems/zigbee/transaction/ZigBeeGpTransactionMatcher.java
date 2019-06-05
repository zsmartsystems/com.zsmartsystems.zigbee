package com.zsmartsystems.zigbee.transaction;

import com.zsmartsystems.zigbee.greenpower.GpCommand;

public interface ZigBeeGpTransactionMatcher {
	
	boolean isTransactionMatch(GpCommand request, GpCommand response);
}

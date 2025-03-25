package com.zsmartsystems.zigbee.dongle.ember;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberJoinDecision;

public interface EmberTrustCenterJoinListener {
	
	void emberTrustCenterJoinPacketReceived(String macGateway, String macDevice, EmberJoinDecision decision);

}

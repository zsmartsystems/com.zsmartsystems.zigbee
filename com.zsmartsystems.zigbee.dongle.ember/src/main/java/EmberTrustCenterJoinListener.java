

import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberJoinDecision;

public interface EmberTrustCenterJoinListener {
	
	public void emberTrustCenterJoinPacketReceived(String macGateway, String macDevice, EmberJoinDecision decision);

}

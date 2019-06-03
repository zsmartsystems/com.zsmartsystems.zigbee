//package com.zsmartsystems.zigbee;
//
//import com.zsmartsystems.zigbee.aps.ZigBeeApsFrame;
//import com.zsmartsystems.zigbee.transport.ZigBeeTransportProgressState;
//import com.zsmartsystems.zigbee.transport.ZigBeeTransportReceive;
//import com.zsmartsystems.zigbee.transport.ZigBeeTransportState;
//import com.zsmartsystems.zigbee.transport.ZigBeeTransportTransmit;
//
//public class ZigBeeGreenPowerManager extends ZigBeeNetworkManager{
//	/*
//	*transport layer implementation. example: a dongle
//	*/
//	private final ZigBeeTransportTransmit transport;
//	
//	public ZigBeeGreenPowerManager(final ZigBeeTransportTransmit transport) {
//        this.transport = transport;
//
//        transport.setZigBeeTransportReceive(this);
//    }
//	
//	@Override
//	public void receiveCommand(ZigBeeApsFrame apsFrame) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void setTransportState(ZigBeeTransportState state) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void nodeStatusUpdate(ZigBeeNodeStatus deviceStatus, Integer networkAddress, IeeeAddress ieeeAddress) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void receiveCommandState(int msgTag, ZigBeeTransportProgressState state) {
//		// TODO Auto-generated method stub
//		
//	}
//
//}

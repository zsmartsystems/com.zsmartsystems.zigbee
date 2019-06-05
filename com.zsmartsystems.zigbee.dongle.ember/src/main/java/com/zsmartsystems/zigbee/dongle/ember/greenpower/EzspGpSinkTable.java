package com.zsmartsystems.zigbee.dongle.ember.greenpower;

import java.util.Map;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberGpAddress;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberStatus;
import com.zsmartsystems.zigbee.greenpower.VirtualSink;
import com.zsmartsystems.zigbee.greenpower.VirtualSinkEntry;

public class EzspGpSinkTable implements VirtualSink{

	private EmberStatus status;
	
	private Map<Integer, EzspGpSinkTableEntry> entries;
	
	@Override
	public VirtualSinkEntry getEntry(int index) {
		return entries.get(index);
	}

	@Override
	public int lookup(Object address) {
		if(entries.size()==0) return -1;
		
		if (address instanceof EmberGpAddress) {
			for (EzspGpSinkTableEntry sinkEntry : entries.values()) {
				if(sinkEntry.getAddress() == address) {
					return sinkEntry.getIndex();
				}
			}
		}
		return -1;		
	}

	@Override
	public boolean setEntry(int index, VirtualSinkEntry entry) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeEntry(int index) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int findOrAllocateEntry(Object address) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void clearAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

}

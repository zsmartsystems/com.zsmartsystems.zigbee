package com.zsmartsystems.zigbee.dongle.ember.greenpower;

import java.util.Collections;
import java.util.Map;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberGpAddress;
import com.zsmartsystems.zigbee.greenpower.GpAddress;
import com.zsmartsystems.zigbee.greenpower.VirtualSink;
import com.zsmartsystems.zigbee.greenpower.VirtualSinkEntry;

public class EzspGpSinkTable implements VirtualSink{

	/**
	 * Status boolean, true if the table was initialized.
	 * maybe usable to prevent anything to be done before initialization, eventually useless.
	 * TODO possibly add status verification to the other commands and create a sinkTableStatus Enum (see doc).
	 */
	private boolean status;
	
	private Map<Integer, EzspGpSinkTableEntry> entries;
	
	public EzspGpSinkTable() {
		entries = Collections.emptyMap();
	}
	
	@Override
	public VirtualSinkEntry getEntry(Integer index) {
		return entries.get(index);
	}

	@Override
	public int lookup(GpAddress address) {
		if(entries.isEmpty()) {
			System.out.println("table vide.\n");
			return -1;
		}
		
		if (address instanceof EmberGpAddress) {
			for (int index : entries.keySet()) {
				if(address.equals(entries.get(index).getAddress())) {
					System.out.println("GG ! \n\n");
					return index;
				}
			}
		}
		System.out.println("juste nul\n\n");
		return -1;		
	}

	@Override
	public boolean setEntry(Integer index, VirtualSinkEntry entry) {
		//checks if the entry was allocated first
		if (!entries.containsKey(index)) {
			return false;
		}
		entries.put(index,(EzspGpSinkTableEntry) entry);
		return false;
	}

	@Override
	public void removeEntry(Integer index) {
		
		entries.remove(index);
	}

	@Override
	public int findOrAllocateEntry(GpAddress address) {
		if (lookup(address)!=-1) {
			return lookup(address);
		}
		int index = 0;
		while (true) {
			if(!entries.containsKey(index)) {
				break;
			}
			index++;
		}
		return index;		
	}

	@Override
	public void clearAll() {
		entries.clear();
	}

	@Override
	public void init() {
		status=true;
	}

	/**
	 * Gets the current status of the sink table.
	 * 
	 * @return the status.
	 */
	public boolean getStatus() {
		return this.status;
	}
}

package com.zsmartsystems.zigbee.greenpower;

public interface VirtualSink {

	public VirtualSinkEntry getEntry(Integer index);
	
	//TODO find a more specific class than Object.
	public int lookup(Object address);
	
	public boolean setEntry(Integer index, VirtualSinkEntry entry);
	
	//TODO add return to indicate success or failure ?
	public void removeEntry(Integer index);
	
	public int findOrAllocateEntry(Object address);
	
	//TODO same as removeEntry.
	public void clearAll();
	
	//not usefull ?
	public void init();
}

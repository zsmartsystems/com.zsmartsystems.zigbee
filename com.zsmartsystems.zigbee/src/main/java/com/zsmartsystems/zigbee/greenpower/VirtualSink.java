package com.zsmartsystems.zigbee.greenpower;

public interface VirtualSink {

	public VirtualSinkEntry getEntry(Integer index);
	
	public int lookup(GpAddress address);
	
	public boolean setEntry(Integer index, VirtualSinkEntry entry);
	
	public void removeEntry(Integer index);
	
	public int findOrAllocateEntry(GpAddress address);
	
	public void clearAll();
	
	//not usefull unless verification of initialization is used.
	public void init();
	
	public boolean getStatus();
	
	public boolean test();
	
	public VirtualSinkEntry getNewEntry();
}


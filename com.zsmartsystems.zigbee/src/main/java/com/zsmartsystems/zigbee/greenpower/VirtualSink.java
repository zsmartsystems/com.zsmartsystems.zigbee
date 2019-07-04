/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
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


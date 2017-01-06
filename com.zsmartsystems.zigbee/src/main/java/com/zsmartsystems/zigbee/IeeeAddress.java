package com.zsmartsystems.zigbee;

/**
 * Represents an IEEE address
 * 
 * @author Chris Jackson
 *
 */
public class IeeeAddress {
    private long address;

    public IeeeAddress(long address) {
        this.address = address;
    }

    public IeeeAddress(String address) {
        this.address = Long.parseLong(address, 16);
    }

    public long getLong() {
        return address;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!IeeeAddress.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final IeeeAddress other = (IeeeAddress) obj;
        if (other.getLong() == address) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("%08X", address);
    }
}

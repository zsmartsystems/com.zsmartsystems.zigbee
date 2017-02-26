package com.zsmartsystems.zigbee;

/**
 * Represents a 64 bit IEEE network address
 *
 * @author Chris Jackson
 *
 */
public class IeeeAddress {
    private long address;

    /**
     * Create an {@link IeeeAddress} from a {@link Long}
     *
     * @param address the address as a {@link Long}
     */
    public IeeeAddress(long address) {
        this.address = address;
    }

    /**
     * Create an {@link IeeeAddress} from a {@link String}
     *
     * @param address the address as a {@link String}
     */
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
        return (other.getLong() == address) ? true : false;
    }

    @Override
    public String toString() {
        return String.format("%016X", address);
    }
}

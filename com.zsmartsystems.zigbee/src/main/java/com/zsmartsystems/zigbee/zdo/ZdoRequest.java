package com.zsmartsystems.zigbee.zdo;

/**
 * Abstract class for ZDO response commands.
 *
 * @author Chris Jackson
 */
public abstract class ZdoRequest extends ZdoCommand {
    /**
     * Destination address;
     */
    // protected int destinationAddress;

    /**
     * Gets destination address.
     *
     * @return the destination address
     */
    // public int getDestinationAddress() {
    // return destinationAddress;
    // }

    /**
     * Set the destination address
     *
     * @param destinationAddress the destination address as {@link int}
     */
    // public void setDestinationAddress(int destinationAddress) {
    // this.destinationAddress = destinationAddress;
    // }

    // @Override
    // public String toString() {
    // final StringBuilder builder = new StringBuilder();
    // builder.append(": destinationAddress=");
    // builder.append(destinationAddress);
    // return builder.toString();
    // }
}

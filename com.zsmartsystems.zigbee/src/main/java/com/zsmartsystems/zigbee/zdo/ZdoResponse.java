package com.zsmartsystems.zigbee.zdo;

/**
 * Abstract class for ZDO response commands.
 *
 * @author Chris Jackson
 */
public abstract class ZdoResponse extends ZdoCommand {

    /**
     * Source address;
     */
    // protected int sourceAddress;

    /**
     * The response status.
     */
    protected ZdoStatus status;

    /**
     * Gets source address.
     *
     * @return the destination address
     */
    // public int getSourceAddress() {
    // return sourceAddress;
    // }

    /**
     * Set the source address
     *
     * @param sourceAddress the source address as {@link int}
     */
    // public void setSourceAddress(int sourceAddress) {
    // this.sourceAddress = sourceAddress;
    // }

    /**
     * Gets the response status
     *
     * @return the response status
     */
    public ZdoStatus getStatus() {
        return status;
    }

    /**
     * Sets the response status
     *
     * @param status the response status as {@link int}
     */
    public void setStatus(ZdoStatus status) {
        this.status = status;
    }

    // @Override
    // public String toString() {
    // final StringBuilder builder = new StringBuilder();
    // builder.append(": sourceAddress=");
    // builder.append(sourceAddress);
    // return builder.toString();
    // }
}

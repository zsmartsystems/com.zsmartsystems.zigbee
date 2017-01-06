package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zdo.ZdoCommand;
import com.zsmartsystems.zigbee.zdo.ZdoResponse;
import com.zsmartsystems.zigbee.zdo.descriptors.NodeDescriptor;

/**
 * NodeDescriptorResponse.
 * 
 * @author Tommi S.E. Laukkanen
 */
public class NodeDescriptorResponse extends ZdoCommand implements ZdoResponse {
    /**
     * this field indicates either SUCCESS or FAILURE.
     */
    public int status;
    /**
     * the message's source network address.
     */
    public int sourceAddress;
    /**
     * Device's short address of this Node descriptor.
     */
    public int networkAddress;

    private NodeDescriptor nodeDescriptor;

    public NodeDescriptorResponse() {
    }

    public NodeDescriptorResponse(int status, int sourceAddress, int networkAddress, NodeDescriptor nodeDescriptor) {
        this.status = status;
        this.sourceAddress = sourceAddress;
        this.networkAddress = networkAddress;
        this.nodeDescriptor = nodeDescriptor;
    }

    @Override
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public int getSourceAddress() {
        return sourceAddress;
    }

    public void setSourceAddress(int sourceAddress) {
        this.sourceAddress = sourceAddress;
    }

    public int getNetworkAddress() {
        return networkAddress;
    }

    public void setNetworkAddress(int networkAddress) {
        this.networkAddress = networkAddress;
    }

    public NodeDescriptor getNodeDescriptor() {
        return nodeDescriptor;
    }

    @Override
    public String toString() {
        return "Node Descriptor Response " + "status=" + status + ", sourceAddress=" + sourceAddress
                + ", networkAddress=" + networkAddress + ", nodeDescriptor=" + nodeDescriptor;
    }
}

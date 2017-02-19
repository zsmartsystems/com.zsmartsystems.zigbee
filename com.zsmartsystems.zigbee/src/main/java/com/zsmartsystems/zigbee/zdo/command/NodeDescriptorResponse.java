package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zdo.ZdoResponse;
import com.zsmartsystems.zigbee.zdo.descriptors.NodeDescriptor;

/**
 * NodeDescriptorResponse.
 *
 * @author Tommi S.E. Laukkanen
 * @author Chris Jackson
 */
public class NodeDescriptorResponse extends ZdoResponse {

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
        return "Node Descriptor Response: status=" + status + ", sourceAddress=" + sourceAddress + ", networkAddress="
                + networkAddress + ", nodeDescriptor=" + nodeDescriptor;
    }
}

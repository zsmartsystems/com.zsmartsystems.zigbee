/**
 * Copyright (c) 2016-2021 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zdo;

import com.zsmartsystems.zigbee.zdo.command.*;


/**
 * Enumeration of ZDP commands
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 *
 * @author Chris Jackson
 */
public enum ZdoCommandType {
    /**
     * Active Endpoints Request
     * <p>
     * See {@link ActiveEndpointsRequest}
     */
    ACTIVE_ENDPOINTS_REQUEST(0x0005, ActiveEndpointsRequest.class),
    /**
     * Active Endpoints Response
     * <p>
     * See {@link ActiveEndpointsResponse}
     */
    ACTIVE_ENDPOINTS_RESPONSE(0x8005, ActiveEndpointsResponse.class),
    /**
     * Active Endpoint Store Request
     * <p>
     * See {@link ActiveEndpointStoreRequest}
     */
    ACTIVE_ENDPOINT_STORE_REQUEST(0x0019, ActiveEndpointStoreRequest.class),
    /**
     * Active Endpoint Store Response
     * <p>
     * See {@link ActiveEndpointStoreResponse}
     */
    ACTIVE_ENDPOINT_STORE_RESPONSE(0x8019, ActiveEndpointStoreResponse.class),
    /**
     * Backup Bind Table Request
     * <p>
     * See {@link BackupBindTableRequest}
     */
    BACKUP_BIND_TABLE_REQUEST(0x0027, BackupBindTableRequest.class),
    /**
     * Backup Bind Table Response
     * <p>
     * See {@link BackupBindTableResponse}
     */
    BACKUP_BIND_TABLE_RESPONSE(0x8027, BackupBindTableResponse.class),
    /**
     * Backup Source Bind Request
     * <p>
     * See {@link BackupSourceBindRequest}
     */
    BACKUP_SOURCE_BIND_REQUEST(0x0029, BackupSourceBindRequest.class),
    /**
     * Bind Register
     * <p>
     * See {@link BindRegister}
     */
    BIND_REGISTER(0x0023, BindRegister.class),
    /**
     * Bind Register Response
     * <p>
     * See {@link BindRegisterResponse}
     */
    BIND_REGISTER_RESPONSE(0x8023, BindRegisterResponse.class),
    /**
     * Bind Request
     * <p>
     * See {@link BindRequest}
     */
    BIND_REQUEST(0x0021, BindRequest.class),
    /**
     * Bind Response
     * <p>
     * See {@link BindResponse}
     */
    BIND_RESPONSE(0x8021, BindResponse.class),
    /**
     * Cache Request
     * <p>
     * See {@link CacheRequest}
     */
    CACHE_REQUEST(0x0037, CacheRequest.class),
    /**
     * Complex Descriptor Request
     * <p>
     * See {@link ComplexDescriptorRequest}
     */
    COMPLEX_DESCRIPTOR_REQUEST(0x0010, ComplexDescriptorRequest.class),
    /**
     * Complex Descriptor Response
     * <p>
     * See {@link ComplexDescriptorResponse}
     */
    COMPLEX_DESCRIPTOR_RESPONSE(0x8010, ComplexDescriptorResponse.class),
    /**
     * Device Announce
     * <p>
     * See {@link DeviceAnnounce}
     */
    DEVICE_ANNOUNCE(0x0013, DeviceAnnounce.class),
    /**
     * Discovery Cache Request
     * <p>
     * See {@link DiscoveryCacheRequest}
     */
    DISCOVERY_CACHE_REQUEST(0x0012, DiscoveryCacheRequest.class),
    /**
     * Discovery Cache Response
     * <p>
     * See {@link DiscoveryCacheResponse}
     */
    DISCOVERY_CACHE_RESPONSE(0x8012, DiscoveryCacheResponse.class),
    /**
     * Discovery Store Request Request
     * <p>
     * See {@link DiscoveryStoreRequestRequest}
     */
    DISCOVERY_STORE_REQUEST_REQUEST(0x0016, DiscoveryStoreRequestRequest.class),
    /**
     * Discovery Store Response
     * <p>
     * See {@link DiscoveryStoreResponse}
     */
    DISCOVERY_STORE_RESPONSE(0x8016, DiscoveryStoreResponse.class),
    /**
     * End Device Bind Request
     * <p>
     * See {@link EndDeviceBindRequest}
     */
    END_DEVICE_BIND_REQUEST(0x0020, EndDeviceBindRequest.class),
    /**
     * End Device Bind Response
     * <p>
     * See {@link EndDeviceBindResponse}
     */
    END_DEVICE_BIND_RESPONSE(0x8020, EndDeviceBindResponse.class),
    /**
     * Extended Active Endpoint Request
     * <p>
     * See {@link ExtendedActiveEndpointRequest}
     */
    EXTENDED_ACTIVE_ENDPOINT_REQUEST(0x001E, ExtendedActiveEndpointRequest.class),
    /**
     * Extended Active Endpoint Response
     * <p>
     * See {@link ExtendedActiveEndpointResponse}
     */
    EXTENDED_ACTIVE_ENDPOINT_RESPONSE(0x801E, ExtendedActiveEndpointResponse.class),
    /**
     * Extended Simple Descriptor Request
     * <p>
     * See {@link ExtendedSimpleDescriptorRequest}
     */
    EXTENDED_SIMPLE_DESCRIPTOR_REQUEST(0x001D, ExtendedSimpleDescriptorRequest.class),
    /**
     * Extended Simple Descriptor Response
     * <p>
     * See {@link ExtendedSimpleDescriptorResponse}
     */
    EXTENDED_SIMPLE_DESCRIPTOR_RESPONSE(0x801D, ExtendedSimpleDescriptorResponse.class),
    /**
     * Find Node Cache Request
     * <p>
     * See {@link FindNodeCacheRequest}
     */
    FIND_NODE_CACHE_REQUEST(0x001C, FindNodeCacheRequest.class),
    /**
     * Find Node Cache Response
     * <p>
     * See {@link FindNodeCacheResponse}
     */
    FIND_NODE_CACHE_RESPONSE(0x801C, FindNodeCacheResponse.class),
    /**
     * IEEE Address Request
     * <p>
     * See {@link IeeeAddressRequest}
     */
    IEEE_ADDRESS_REQUEST(0x0001, IeeeAddressRequest.class),
    /**
     * IEEE Address Response
     * <p>
     * See {@link IeeeAddressResponse}
     */
    IEEE_ADDRESS_RESPONSE(0x8001, IeeeAddressResponse.class),
    /**
     * Management Bind Request
     * <p>
     * See {@link ManagementBindRequest}
     */
    MANAGEMENT_BIND_REQUEST(0x0033, ManagementBindRequest.class),
    /**
     * Management Bind Response
     * <p>
     * See {@link ManagementBindResponse}
     */
    MANAGEMENT_BIND_RESPONSE(0x8033, ManagementBindResponse.class),
    /**
     * Management Cache Response
     * <p>
     * See {@link ManagementCacheResponse}
     */
    MANAGEMENT_CACHE_RESPONSE(0x8037, ManagementCacheResponse.class),
    /**
     * Management Direct Join Request
     * <p>
     * See {@link ManagementDirectJoinRequest}
     */
    MANAGEMENT_DIRECT_JOIN_REQUEST(0x0035, ManagementDirectJoinRequest.class),
    /**
     * Management Direct Join Response
     * <p>
     * See {@link ManagementDirectJoinResponse}
     */
    MANAGEMENT_DIRECT_JOIN_RESPONSE(0x8035, ManagementDirectJoinResponse.class),
    /**
     * Management Leave Request
     * <p>
     * See {@link ManagementLeaveRequest}
     */
    MANAGEMENT_LEAVE_REQUEST(0x0034, ManagementLeaveRequest.class),
    /**
     * Management Leave Response
     * <p>
     * See {@link ManagementLeaveResponse}
     */
    MANAGEMENT_LEAVE_RESPONSE(0x8034, ManagementLeaveResponse.class),
    /**
     * Management LQI Request
     * <p>
     * See {@link ManagementLqiRequest}
     */
    MANAGEMENT_LQI_REQUEST(0x0031, ManagementLqiRequest.class),
    /**
     * Management LQI Response
     * <p>
     * See {@link ManagementLqiResponse}
     */
    MANAGEMENT_LQI_RESPONSE(0x8031, ManagementLqiResponse.class),
    /**
     * Management Network Discovery
     * <p>
     * See {@link ManagementNetworkDiscovery}
     */
    MANAGEMENT_NETWORK_DISCOVERY(0x0030, ManagementNetworkDiscovery.class),
    /**
     * Management Network Discovery Response
     * <p>
     * See {@link ManagementNetworkDiscoveryResponse}
     */
    MANAGEMENT_NETWORK_DISCOVERY_RESPONSE(0x8030, ManagementNetworkDiscoveryResponse.class),
    /**
     * Management Network Update Notify
     * <p>
     * See {@link ManagementNetworkUpdateNotify}
     */
    MANAGEMENT_NETWORK_UPDATE_NOTIFY(0x8038, ManagementNetworkUpdateNotify.class),
    /**
     * Management Permit Joining Request
     * <p>
     * See {@link ManagementPermitJoiningRequest}
     */
    MANAGEMENT_PERMIT_JOINING_REQUEST(0x0036, ManagementPermitJoiningRequest.class),
    /**
     * Management Permit Joining Response
     * <p>
     * See {@link ManagementPermitJoiningResponse}
     */
    MANAGEMENT_PERMIT_JOINING_RESPONSE(0x8036, ManagementPermitJoiningResponse.class),
    /**
     * Management Routing Request
     * <p>
     * See {@link ManagementRoutingRequest}
     */
    MANAGEMENT_ROUTING_REQUEST(0x0032, ManagementRoutingRequest.class),
    /**
     * Management Routing Response
     * <p>
     * See {@link ManagementRoutingResponse}
     */
    MANAGEMENT_ROUTING_RESPONSE(0x8032, ManagementRoutingResponse.class),
    /**
     * Match Descriptor Request
     * <p>
     * See {@link MatchDescriptorRequest}
     */
    MATCH_DESCRIPTOR_REQUEST(0x0006, MatchDescriptorRequest.class),
    /**
     * Match Descriptor Response
     * <p>
     * See {@link MatchDescriptorResponse}
     */
    MATCH_DESCRIPTOR_RESPONSE(0x8006, MatchDescriptorResponse.class),
    /**
     * Network Address Request
     * <p>
     * See {@link NetworkAddressRequest}
     */
    NETWORK_ADDRESS_REQUEST(0x0000, NetworkAddressRequest.class),
    /**
     * Network Address Response
     * <p>
     * See {@link NetworkAddressResponse}
     */
    NETWORK_ADDRESS_RESPONSE(0x8000, NetworkAddressResponse.class),
    /**
     * Network Update Request
     * <p>
     * See {@link NetworkUpdateRequest}
     */
    NETWORK_UPDATE_REQUEST(0x0038, NetworkUpdateRequest.class),
    /**
     * Node Descriptor Request
     * <p>
     * See {@link NodeDescriptorRequest}
     */
    NODE_DESCRIPTOR_REQUEST(0x0002, NodeDescriptorRequest.class),
    /**
     * Node Descriptor Response
     * <p>
     * See {@link NodeDescriptorResponse}
     */
    NODE_DESCRIPTOR_RESPONSE(0x8002, NodeDescriptorResponse.class),
    /**
     * Node Descriptor Store Request
     * <p>
     * See {@link NodeDescriptorStoreRequest}
     */
    NODE_DESCRIPTOR_STORE_REQUEST(0x0017, NodeDescriptorStoreRequest.class),
    /**
     * Node Descriptor Store Response
     * <p>
     * See {@link NodeDescriptorStoreResponse}
     */
    NODE_DESCRIPTOR_STORE_RESPONSE(0x8017, NodeDescriptorStoreResponse.class),
    /**
     * Power Descriptor Request
     * <p>
     * See {@link PowerDescriptorRequest}
     */
    POWER_DESCRIPTOR_REQUEST(0x0003, PowerDescriptorRequest.class),
    /**
     * Power Descriptor Response
     * <p>
     * See {@link PowerDescriptorResponse}
     */
    POWER_DESCRIPTOR_RESPONSE(0x8003, PowerDescriptorResponse.class),
    /**
     * Power Descriptor Store Request
     * <p>
     * See {@link PowerDescriptorStoreRequest}
     */
    POWER_DESCRIPTOR_STORE_REQUEST(0x0018, PowerDescriptorStoreRequest.class),
    /**
     * Power Descriptor Store Response
     * <p>
     * See {@link PowerDescriptorStoreResponse}
     */
    POWER_DESCRIPTOR_STORE_RESPONSE(0x8018, PowerDescriptorStoreResponse.class),
    /**
     * Recover Bind Table Request
     * <p>
     * See {@link RecoverBindTableRequest}
     */
    RECOVER_BIND_TABLE_REQUEST(0x0028, RecoverBindTableRequest.class),
    /**
     * Recover Bind Table Response
     * <p>
     * See {@link RecoverBindTableResponse}
     */
    RECOVER_BIND_TABLE_RESPONSE(0x8028, RecoverBindTableResponse.class),
    /**
     * Recover Source Bind Request
     * <p>
     * See {@link RecoverSourceBindRequest}
     */
    RECOVER_SOURCE_BIND_REQUEST(0x002A, RecoverSourceBindRequest.class),
    /**
     * Recover Source Bind Response
     * <p>
     * See {@link RecoverSourceBindResponse}
     */
    RECOVER_SOURCE_BIND_RESPONSE(0x8029, RecoverSourceBindResponse.class),
    /**
     * Remove Backup Bind Entry Response
     * <p>
     * See {@link RemoveBackupBindEntryResponse}
     */
    REMOVE_BACKUP_BIND_ENTRY_RESPONSE(0x8026, RemoveBackupBindEntryResponse.class),
    /**
     * Remove Backup Bind Table Request
     * <p>
     * See {@link RemoveBackupBindTableRequest}
     */
    REMOVE_BACKUP_BIND_TABLE_REQUEST(0x0026, RemoveBackupBindTableRequest.class),
    /**
     * Remove Node Cache
     * <p>
     * See {@link RemoveNodeCache}
     */
    REMOVE_NODE_CACHE(0x801B, RemoveNodeCache.class),
    /**
     * Remove Node Cache Request
     * <p>
     * See {@link RemoveNodeCacheRequest}
     */
    REMOVE_NODE_CACHE_REQUEST(0x001B, RemoveNodeCacheRequest.class),
    /**
     * Replace Device Request
     * <p>
     * See {@link ReplaceDeviceRequest}
     */
    REPLACE_DEVICE_REQUEST(0x0024, ReplaceDeviceRequest.class),
    /**
     * Replace Device Response
     * <p>
     * See {@link ReplaceDeviceResponse}
     */
    REPLACE_DEVICE_RESPONSE(0x8024, ReplaceDeviceResponse.class),
    /**
     * Simple Descriptor Request
     * <p>
     * See {@link SimpleDescriptorRequest}
     */
    SIMPLE_DESCRIPTOR_REQUEST(0x0004, SimpleDescriptorRequest.class),
    /**
     * Simple Descriptor Response
     * <p>
     * See {@link SimpleDescriptorResponse}
     */
    SIMPLE_DESCRIPTOR_RESPONSE(0x8004, SimpleDescriptorResponse.class),
    /**
     * Simple Descriptor Store
     * <p>
     * See {@link SimpleDescriptorStore}
     */
    SIMPLE_DESCRIPTOR_STORE(0x001A, SimpleDescriptorStore.class),
    /**
     * Simple Descriptor Store Response
     * <p>
     * See {@link SimpleDescriptorStoreResponse}
     */
    SIMPLE_DESCRIPTOR_STORE_RESPONSE(0x801A, SimpleDescriptorStoreResponse.class),
    /**
     * Store Backup Bind Entry Request
     * <p>
     * See {@link StoreBackupBindEntryRequest}
     */
    STORE_BACKUP_BIND_ENTRY_REQUEST(0x0025, StoreBackupBindEntryRequest.class),
    /**
     * Store Backup Bind Entry Response
     * <p>
     * See {@link StoreBackupBindEntryResponse}
     */
    STORE_BACKUP_BIND_ENTRY_RESPONSE(0x8025, StoreBackupBindEntryResponse.class),
    /**
     * System Server Discovery Request
     * <p>
     * See {@link SystemServerDiscoveryRequest}
     */
    SYSTEM_SERVER_DISCOVERY_REQUEST(0x0015, SystemServerDiscoveryRequest.class),
    /**
     * Unbind Request
     * <p>
     * See {@link UnbindRequest}
     */
    UNBIND_REQUEST(0x0022, UnbindRequest.class),
    /**
     * Unbind Response
     * <p>
     * See {@link UnbindResponse}
     */
    UNBIND_RESPONSE(0x8022, UnbindResponse.class),
    /**
     * User Descriptor Conf
     * <p>
     * See {@link UserDescriptorConf}
     */
    USER_DESCRIPTOR_CONF(0x8014, UserDescriptorConf.class),
    /**
     * User Descriptor Request
     * <p>
     * See {@link UserDescriptorRequest}
     */
    USER_DESCRIPTOR_REQUEST(0x0011, UserDescriptorRequest.class),
    /**
     * User Descriptor Response
     * <p>
     * See {@link UserDescriptorResponse}
     */
    USER_DESCRIPTOR_RESPONSE(0x8011, UserDescriptorResponse.class),
    /**
     * User Descriptor Set Request
     * <p>
     * See {@link UserDescriptorSetRequest}
     */
    USER_DESCRIPTOR_SET_REQUEST(0x0014, UserDescriptorSetRequest.class),
    /**
     * Parent Announce
     * <p>
     * See {@link ParentAnnounce}
     */
    PARENT_ANNOUNCE(0x001F, ParentAnnounce.class);

    private final int clusterId;
    private final Class<? extends ZdoCommand> commandClass;

    ZdoCommandType(final int clusterId, final Class<? extends ZdoCommand> commandClass) {
        this.clusterId = clusterId;
        this.commandClass = commandClass;
    }

    public int getClusterId() {
        return clusterId;
    }

    public Class<? extends ZdoCommand> getCommandClass() {
        return commandClass;
    }

    public static ZdoCommandType getValueById(final int clusterId) {
        for (final ZdoCommandType value : values()) {
            if(value.clusterId == clusterId) {
                return value;
            }
        }
        return null;
    }
}

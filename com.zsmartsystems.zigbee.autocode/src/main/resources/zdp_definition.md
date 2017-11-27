# ZigBee Device Profile [0x0000]

ZigBee Device Profile commands

# General

## General [0x0000]

### Received

#### Network Address Request [0x0000]

The NWK_addr_req is generated from a Local Device wishing to inquire as to the
16-bit address of the Remote Device based on its known IEEE address. The
destination addressing on this command shall be unicast or broadcast to all
devices for which macRxOnWhenIdle = TRUE.

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|IEEEAddr                   |IEEE Address               |
|RequestType                |Unsigned 8-bit integer     |
|StartIndex                 |Unsigned 8-bit integer     |

##### RequestType
Request type for this command:
0x00 – Single device response
0x01 – Extended response
0x02-0xFF – reserved

##### Expected Response
Packet: Network Address Response
Match: IEEEAddr == IEEEAddrRemoteDev


#### IEEE Address Request [0x0001]

The IEEE_addr_req is generated from a Local Device wishing to inquire as to the
64-bit IEEE address of the Remote Device based on their known 16-bit address.
The destination addressing on this command shall be unicast.

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|NWKAddrOfInterest          |NWK address                |
|RequestType                |Unsigned 8-bit integer     |
|StartIndex                 |Unsigned 8-bit integer     |

##### Expected Response
Packet: IEEE Address Response
Match: NWKAddrOfInterest == NWKAddrRemoteDev

#### Node Descriptor Request [0x0002]

The Node_Desc_req command is generated from a local device wishing to inquire
as to the node descriptor of a remote device. This command shall be unicast either
to the remote device itself or to an alternative device that contains the discovery
information of the remote device.

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|NWKAddrOfInterest          |NWK address                |

##### Expected Response
Packet: Node Descriptor Response
Match: NWKAddrOfInterest == NWKAddrOfInterest


#### Power Descriptor Request [0x0003]

The Power_Desc_req command is generated from a local device wishing to
inquire as to the power descriptor of a remote device. This command shall be
unicast either to the remote device itself or to an alternative device that contains
the discovery information of the remote device.

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|NWKAddrOfInterest          |NWK address         |

##### Expected Response
Packet: Power Descriptor Response
Match: NWKAddrOfInterest == NWKAddrOfInterest


#### Simple Descriptor Request [0x0004]

The Simple_Desc_req command is generated from a local device wishing to
inquire as to the simple descriptor of a remote device on a specified endpoint. This
command shall be unicast either to the remote device itself or to an alternative
device that contains the discovery information of the remote device.

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|NWKAddrOfInterest          |NWK address                |
|Endpoint                   |Unsigned 8-bit integer     |

##### Expected Response
Packet: Simple Descriptor Response
Match: NWKAddrOfInterest == NWKAddrOfInterest
Match: Endpoint == SimpleDescriptor.Endpoint

#### Active Endpoints Request [0x0005]

The Active_EP_req command is generated from a local device wishing to acquire
the list of endpoints on a remote device with simple descriptors. This command
shall be unicast either to the remote device itself or to an alternative device that
contains the discovery information of the remote device.

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|NWKAddrOfInterest          |NWK address                |

##### Expected Response
Packet: Active Endpoints Response
Match: NWKAddrOfInterest == NWKAddrOfInterest


#### Match Descriptor Request [0x0006]

The Match_Desc_req command is generated from a local device wishing to find
remote devices supporting a specific simple descriptor match criterion. This
command shall either be broadcast to all devices for which macRxOnWhenIdle =
TRUE, or unicast. If the command is unicast, it shall be directed either to the
remote device itself or to an alternative device that contains the discovery
information of the remote device.

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|NWKAddrOfInterest          |NWK address                |
|ProfileID                  |Unsigned 16-bit integer    |
|InClusterCount             |Unsigned 8-bit integer     |
|InClusterList              |ClusterId[InClusterCount]  |
|OutClusterCount            |Unsigned 8-bit integer     |
|OutClusterList             |ClusterId[OutClusterCount] |


#### Complex Descriptor Request [0x0010]

The Complex_Desc_req command is generated from a local device wishing to
inquire as to the complex descriptor of a remote device. This command shall be
unicast either to the remote device itself or to an alternative device that contains
the discovery information of the remote device.

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|NWKAddrOfInterest          |NWK address         |

##### Expected Response
Packet: Complex Descriptor Response
Match: NWKAddrOfInterest == NWKAddrOfInterest


#### User Descriptor Request [0x0011]

The User_Desc_req command is generated from a local device wishing to inquire
as to the user descriptor of a remote device. This command shall be unicast either
to the remote device itself or to an alternative device that contains the discovery
information of the remote device.

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|NWKAddrOfInterest          |NWK address         |

##### Expected Response
Packet: User Descriptor Response
Match: NWKAddrOfInterest == NWKAddrOfInterest


#### Discovery Cache Request [0x0012]

The Discovery_Cache_req is provided to enable devices on the network to locate
a Primary Discovery Cache device on the network. The destination addressing on
this primitive shall be broadcast to all devices for which macRxOnWhenIdle =
TRUE.

#### Device Announce [0x0013]

The Device_annce is provided to enable ZigBee devices on the network to notify
other ZigBee devices that the device has joined or re-joined the network,
identifying the device's 64-bit IEEE address and new 16-bit NWK address, and
informing the Remote Devices of the capability of the ZigBee device. This
command shall be invoked for all ZigBee end devices upon join or rejoin. This
command may also be invoked by ZigBee routers upon join or rejoin as part of
NWK address conflict resolution. The destination addressing on this primitive is
broadcast to all devices for which macRxOnWhenIdle = TRUE.

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|NWKAddrOfInterest          |NWK address                |
|IEEEAddr                   |IEEE Address               |
|Capability                 |Bitmap 8-bit               |


#### User Descriptor Set Request [0x0014]

The User_Desc_set command is generated from a local device wishing to
configure the user descriptor on a remote device. This command shall be unicast
either to the remote device itself or to an alternative device that contains the
discovery information of the remote device.

The local device shall generate the User_Desc_set command using the format
illustrated in Table 2.55. The NWKAddrOfInterest field shall contain the network
address of the remote device for which the user descriptor is to be configured and
the UserDescription field shall contain the ASCII character string that is to be
configured in the user descriptor. Characters with ASCII codes numbered 0x00
through 0x1f are not permitted to be included in this string.

#### System Server Discovery Request [0x0015]

The System_Server_Discovery_req is generated from a Local Device wishing to
discover the location of a particular system server or servers as indicated by the
ServerMask parameter. The destination addressing on this request is "broadcast to
all devices for which macRxOnWhenIdle = TRUE".

#### Discovery Store Request Request [0x0016]

The Discovery_store_req is provided to enable ZigBee end devices on the
network to request storage of their discovery cache information on a Primary
Discovery Cache device. Included in the request is the amount of storage space
the Local Device requires.

#### Node Descriptor Store Request [0x0017]

The Node_Desc_store_req is provided to enable ZigBee end devices on the
network to request storage of their Node Descriptor on a Primary Discovery
Cache device which has previously received a SUCCESS status from a
Discovery_store_req to the same Primary Discovery Cache device. Included in
this request is the Node Descriptor the Local Device wishes to cache. 

#### Power Descriptor Store Request [0x0018]

The Power_Desc_store_req is provided to enable ZigBee end devices on the
network to request storage of their Power Descriptor on a Primary Discovery
Cache device which has previously received a SUCCESS status from a
Discovery_store_req to the same Primary Discovery Cache device. Included in
this request is the Power Descriptor the Local Device wishes to cache.

#### Active Endpoint Store Request [0x0019]

The Active_EP_store_req is provided to enable ZigBee end devices on the
network to request storage of their list of Active Endpoints on a Primary
Discovery Cache device which has previously received a SUCCESS status from a
Discovery_store_req to the same Primary Discovery Cache device. Included in
this request is the count of Active Endpoints the Local Device wishes to cache and
the endpoint list itself. 

#### Simple Descriptor Store [0x001A]

The Simple_desc_store_req is provided to enable ZigBee end devices on the
network to request storage of their list of Simple Descriptors on a Primary
Discovery Cache device which has previously received a SUCCESS status from a
Discovery_store_req to the same Primary Discovery Cache device. Note that each
Simple Descriptor for every active endpoint on the Local Device must be
individually uploaded to the Primary Discovery Cache device via this command
to enable cached discovery. Included in this request is the length of the Simple
Descriptor the Local Device wishes to cache and the Simple Descriptor itself. The
endpoint is a field within the Simple Descriptor and is accessed by the Remote
Device to manage the discovery cache information for the Local Device. 

#### Remove Node Cache Request [0x001B]

The Remove_node_cache_req is provided to enable ZigBee devices on the
network to request removal of discovery cache information for a specified ZigBee
end device from a Primary Discovery Cache device. The effect of a successful
Remove_node_cache_req is to undo a previously successful Discovery_store_req
and additionally remove any cache information stored on behalf of the specified
ZigBee end device on the Primary Discovery Cache device. 

#### Find Node Cache Request [0x001C]

The Find_node_cache_req is provided to enable ZigBee devices on the network to
broadcast to all devices for which macRxOnWhenIdle = TRUE a request to find a
device on the network that holds discovery information for the device of interest,
as specified in the request parameters. The effect of a successful
Find_node_cache_req is to have the Primary Discovery Cache device, holding
discovery information for the device of interest, unicast a Find_node_cache_rsp
back to the Local Device. Note that, like the NWK_addr_req, only the device
meeting this criteria shall respond to the request generated by
Find_node_cache_req. 

#### Extended Simple Descriptor Request [0x001D]

The Extended_Simple_Desc_req command is generated from a local device
wishing to inquire as to the simple descriptor of a remote device on a specified
endpoint. This command shall be unicast either to the remote device itself or to an
alternative device that contains the discovery information of the remote device.
The Extended_Simple_Desc_req is intended for use with devices which employ a
larger number of application input or output clusters than can be described by the
Simple_Desc_req. 

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|NWKAddrOfInterest          |NWK address         |
|Endpoint                   |Unsigned 8-bit integer     |
|StartIndex                 |Unsigned 8-bit integer     |

#### Extended Active Endpoint Request [0x001E]

The Extended_Active_EP_req command is generated from a local device wishing
to acquire the list of endpoints on a remote device with simple descriptors. This
command shall be unicast either to the remote device itself or to an alternative
device that contains the discovery information of the remote device. The
Extended_Active_EP_req is used for devices which support more active
endpoints than can be returned by a single Active_EP_req.

The NWKAddrOfInterest field shall contain the network address of the remote device for
which the active endpoint list is required. The StartIndex field shall be set in the
request to enable retrieval of lists of active endpoints from devices whose list exceeds
the size of a single ASDU and where fragmentation is not supported.

#### End Device Bind Request [0x0020]

The End_Device_Bind_req is generated from a Local Device wishing to perform
End Device Bind with a Remote Device. The End_Device_Bind_req is generated,
typically based on some user action like a button press. The destination addressing
on this command shall be unicast, and the destination address shall be that of the
ZigBee Coordinator.

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|BindingTarget              |NWK Address                |
|SrcAddress                 |IEEE address               |
|SrcEndpoint                |Unsigned 8-bit integer     |
|ProfileID                  |Unsigned 16-bit integer    |
|InClusterCount             |Unsigned 8-bit integer     |
|InClusterList              |ClusterId[InClusterCount]  |
|OutClusterCount            |Unsigned 8-bit integer     |
|OutClusterList             |ClusterId[OutClusterCount] |


#### Bind Request [0x0021]

The Bind_req is generated from a Local Device wishing to create a Binding Table
entry for the source and destination addresses contained as parameters. The
destination addressing on this command shall be unicast only, and the destination
address shall be that of a Primary binding table cache or to the SrcAddress itself.
The Binding Manager is optionally supported on the source device (unless that
device is also the ZigBee Coordinator) so that device shall issue a
NOT_SUPPORTED status to the Bind_req if not supported.

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|SrcAddress                 |IEEE address               |
|SrcEndpoint                |Unsigned 8-bit integer     |
|BindCluster                |Unsigned 16-bit integer    |
|DstAddrMode                |Unsigned 8-bit integer     |
|DstAddress                 |IEEE address               |
|DstEndpoint                |Unsigned 8-bit integer     |

#### Unbind Request [0x0022]

The Unbind_req is generated from a Local Device wishing to remove a Binding
Table entry for the source and destination addresses contained as parameters. The
destination addressing on this command shall be unicast only and the destination
address must be that of the a Primary binding table cache or the SrcAddress.

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|SrcAddress                 |IEEE address               |
|SrcEndpoint                |Unsigned 8-bit integer     |
|ClusterID                  |Unsigned 16-bit integer    |
|DstAddrMode                |Unsigned 8-bit integer     |
|DstAddress                 |IEEE address               |
|DstEndpoint                |Unsigned 8-bit integer     |

#### Bind Register [0x0023]

The Bind_Register_req is generated from a Local Device and sent to a primary
binding table cache device to register that the local device wishes to hold its own
binding table entries. The destination addressing mode for this request is unicast.

#### Replace Device Request [0x0024]

The Replace_Device_req is intended for use by a special device such as a
Commissioning tool and is sent to a primary binding table cache device to change
all binding table entries which match OldAddress and OldEndpoint as specified.
Note that OldEndpoint = 0 has special meaning and signifies that only the address
needs to be matched. The endpoint in the binding table will not be changed in this
case and so NewEndpoint is ignored. The processing changes all binding table
entries for which the source address is the same as OldAddress and, if
OldEndpoint is non-zero, for which the source endpoint is the same as
OldEndpoint. It shall also change all binding table entries which have the
destination address the same as OldAddress and, if OldEndpoint is non-zero, the
destination endpoint the same as OldEndpoint. The destination addressing mode
for this request is unicast. 

#### Store Backup Bind Entry Request [0x0025]

The Store_Bkup_Bind_Entry_req is generated from a local primary binding table
cache and sent to a remote backup binding table cache device to request backup
storage of the entry. It will be generated whenever a new binding table entry has
been created by the primary binding table cache. The destination addressing mode
for this request is unicast. 

#### Remove Backup Bind Table Request [0x0026]

The Remove_Bkup_Bind_Entry_req is generated from a local primary binding
table cache and sent to a remote backup binding table cache device to request
removal of the entry from backup storage. It will be generated whenever a binding
table entry has been unbound by the primary binding table cache. The destination
addressing mode for this request is unicast. 

#### Backup Bind Table Request [0x0027]

The Backup_Bind_Table_req is generated from a local primary binding table
cache and sent to the remote backup binding table cache device to request backup
storage of its entire binding table. The destination addressing mode for this
request is unicast. 

#### Recover Bind Table Request [0x0028]

The Recover_Bind_Table_req is generated from a local primary binding table
cache and sent to a remote backup binding table cache device when it wants a
complete restore of the binding table. The destination addressing mode for this
request is unicast.

#### Backup Source Bind Request [0x0029]

The Backup_Source_Bind_req is generated from a local primary binding table
cache and sent to a remote backup binding table cache device to request backup
storage of its entire source table. The destination addressing mode for this request
is unicast. 

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|SourceTableEntries         |Unsigned 16-bit integer    |
|StartIndex                 |Unsigned 16-bit integer    |
|SourceTableListCount       |Unsigned 16-bit integer    |
|SourceTableList            |N X IEEE Address           |


#### Recover Source Bind Request [0x002A]

The Recover_Source_Bind_req is generated from a local primary binding table
cache and sent to the remote backup binding table cache device when it wants a
complete restore of the source binding table. The destination addressing mode for
this request is unicast.

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|StartIndex                 |Unsigned 8-bit integer     |

#### Management Network Discovery [0x0030]

The Mgmt_NWK_Disc_req is generated from a Local Device requesting that the
Remote Device execute a Scan to report back networks in the vicinity of the Local
Device. The destination addressing on this command shall be unicast.

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|ScanChannels               |Bitmap 32-bit              |
|ScanDuration               |Unsigned 8-bit integer     |
|StartIndex                 |Unsigned 8-bit integer     |

#### Management LQI Request [0x0031]

The Mgmt_Lqi_req is generated from a Local Device wishing to obtain a
neighbor list for the Remote Device along with associated LQI values to each
neighbor. The destination addressing on this command shall be unicast only and
the destination address must be that of a ZigBee Coordinator or ZigBee Router.

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|StartIndex                 |Unsigned 8-bit integer     |

##### Expected Response
Packet: Management LQI Response
Match: DestinationAddress == SourceAddress 

#### Management Routing Request [0x0032]

The Mgmt_Rtg_req is generated from a Local Device wishing to retrieve the
contents of the Routing Table from the Remote Device. The destination
addressing on this command shall be unicast only and the destination address
must be that of the ZigBee Router or ZigBee Coordinator.

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|StartIndex                 |Unsigned 8-bit integer     |

##### Expected Response
Packet: Management Routing Response
Match: DestinationAddress == SourceAddress 


#### Management Bind Request [0x0033]

The Mgmt_Bind_req is generated from a Local Device wishing to retrieve the
contents of the Binding Table from the Remote Device. The destination
addressing on this command shall be unicast only and the destination address
must be that of a Primary binding table cache or source device holding its own
binding table.

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|StartIndex                 |Unsigned 8-bit integer     |

#### Management Leave Request [0x0034]

The Mgmt_Leave_req is generated from a Local Device requesting that a Remote
Device leave the network or to request that another device leave the network. The
Mgmt_Leave_req is generated by a management application which directs the
request to a Remote Device where the NLME-LEAVE.request is to be executed
using the parameter supplied by Mgmt_Leave_req. 

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|DeviceAddress              |IEEE Address               |
|RemoveChildren_Rejoin      |Boolean                    |

##### Expected Response
Packet: Management Leave Response
Match: DestinationAddress == SourceAddress 


#### Management Direct Join Request [0x0035]

The Mgmt_Direct_Join_req is generated from a Local Device requesting that a
Remote Device permit a device designated by DeviceAddress to join the network
directly. The Mgmt_Direct_Join_req is generated by a management application
which directs the request to a Remote Device where the NLME-DIRECTJOIN.request
is to be executed using the parameter supplied by
Mgmt_Direct_Join_req.

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|DeviceAddress              |IEEE Address               |
|CapabilityInformation      |Bitmap 8-bit               |


#### Management Permit Joining Request [0x0036]

The Mgmt_Permit_Joining_req is generated from a Local Device requesting that
a remote device or devices allow or disallow association. The
Mgmt_Permit_Joining_req is generated by a management application or
commissioning tool which directs the request to a remote device(s) where the
NLME-PERMIT-JOINING.request is executed using the PermitDuration
parameter supplied by Mgmt_Permit_Joining_req. Additionally, if the remote
device is the Trust Center and TC_Significance is set to 1, the Trust Center
authentication policy will be affected. The addressing may be unicast or
"broadcast to all routers and coordinator".

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|PermitDuration             |Unsigned 8-bit integer     |
|TC_Significance            |Boolean                    |

#### Cache Request [0x0037]

The Mgmt_Cache_req is provided to enable ZigBee devices on the network to
retrieve a list of ZigBee End Devices registered with a Primary Discovery Cache
device. The destination addressing on this primitive shall be unicast.

#### Network Update Request [0x0038]

This command is provided to allow updating of network configuration parameters
or to request information from devices on network conditions in the local
operating environment. The destination addressing on this primitive shall be
unicast or broadcast to all devices for which macRxOnWhenIdle = TRUE.

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|ScanChannels               |Bitmap 32-bit              |
|ScanDuration               |Unsigned 8-bit integer     |
|ScanCount                  |Unsigned 8-bit integer     |
|nwkUpdateId                |Unsigned 8-bit integer     |
|nwkManagerAddr             |NWK Address                | 


#### Network Address Response [0x8000]

The NWK_addr_rsp is generated by a Remote Device in response to a
NWK_addr_req command inquiring as to the NWK address of the Remote Device
or the NWK address of an address held in a local discovery cache. The
destination addressing on this command is unicast.

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Status                     |Zdo Status                 |
|IEEEAddrRemoteDev          |IEEE Address               |
|NWKAddrRemoteDev           |NWK address                |
|NumAssocDev                |Unsigned 8-bit integer     |
|StartIndex                 |Unsigned 8-bit integer     |
|NWKAddrAssocDevList        |NWK Address[NumAssocDev]   | 


#### IEEE Address Response [0x8001]

The IEEE_addr_rsp is generated by a Remote Device in response to an
IEEE_addr_req command inquiring as to the 64-bit IEEE address of the Remote
Device or the 64-bit IEEE address of an address held in a local discovery cache.
The destination addressing on this command shall be unicast. 

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Status                     |Zdo Status                 |
|IEEEAddrRemoteDev          |IEEE Address               |
|NWKAddrRemoteDev           |NWK address                |
|NumAssocDev[0]             |Unsigned 8-bit integer     |
|StartIndex                 |Unsigned 8-bit integer     |
|NWKAddrAssocDevList        |NWK Address[NumAssocDev]   | 


#### Node Descriptor Response [0x8002]

The Node_Desc_rsp is generated by a remote device in response to a
Node_Desc_req directed to the remote device. This command shall be unicast to
the originator of the Node_Desc_req command.

The NWKAddrOfInterest field shall match that specified in the original
Node_Desc_req command. If the NWKAddrOfInterest field matches the network
address of the remote device, it shall set the Status field to
SUCCESS and include its node descriptor in the NodeDescriptor field.

If the NWKAddrOfInterest field does not match the network address of the
remote device and it is an end device, it shall set the Status field to
INV_REQUESTTYPE and not include the NodeDescriptor field. If the
NWKAddrOfInterest field does not match the network address of the remote
device and it is the coordinator or a router, it shall determine whether the
NWKAddrOfInterest field matches the network address of one of its children. If
the NWKAddrOfInterest field does not match the network address of one of the
children of the remote device, it shall set the Status field to
DEVICE_NOT_FOUND and not include the NodeDescriptor field. If the
NWKAddrOfInterest matches the network address of one of the children of the
remote device, it shall determine whether a node descriptor for that device is
available. If a node descriptor is not available for the child indicated by the
NWKAddrOfInterest field, the remote device shall set the Status field to
NO_DESCRIPTOR and not include the NodeDescriptor field. If a node descriptor
is available for the child indicated by the NWKAddrOfInterest field, the remote
device shall set the Status field to SUCCESS and include the node descriptor of
the matching child device in the NodeDescriptor field. 

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Status                     |Zdo Status                 |
|NWKAddrOfInterest          |NWK address                |
|NodeDescriptor             |Node Descriptor            |

#### Power Descriptor Response [0x8003]

The Power_Desc_rsp is generated by a remote device in response to a
Power_Desc_req directed to the remote device. This command shall be unicast to
the originator of the Power_Desc_req command.

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Status                     |Zdo Status                  |
|NWKAddrOfInterest          |NWK address                |
|PowerDescriptor            |Power Descriptor           |

#### Simple Descriptor Response [0x8004]

The Simple_Desc_rsp is generated by a remote device in response to a
Simple_Desc_req directed to the remote device. This command shall be unicast to
the originator of the Simple_Desc_req command.

|Field Name                     |Data Type                  |
|-------------------------------|---------------------------|
|Status                         |Zdo Status                 |
|NWKAddrOfInterest              |NWK address                |
|Length                         |Unsigned 8-bit integer     |
|SimpleDescriptor               |Simple Descriptor          |


#### Active Endpoints Response [0x8005]

The Active_EP_rsp is generated by a remote device in response to an
Active_EP_req directed to the remote device. This command shall be unicast to
the originator of the Active_EP_req command.

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Status                     |Zdo Status                 |
|NWKAddrOfInterest          |NWK address                |
|ActiveEPCnt                |Unsigned 8-bit integer     |
|ActiveEPList               |Endpoint[ActiveEPCnt]      |


#### Match Descriptor Response [0x8006]

The Match_Desc_rsp is generated by a remote device in response to a
Match_Desc_req either broadcast or directed to the remote device. This command
shall be unicast to the originator of the Match_Desc_req command.

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Status                     |Zdo Status                 |
|NWKAddrOfInterest          |NWK address                |
|MatchLength                |Unsigned 8-bit integer     |
|MatchList                  |Endpoint[MatchLength]      |


#### Complex Descriptor Response [0x8010]

The Complex_Desc_rsp is generated by a remote device in response to a
Complex_Desc_req directed to the remote device. This command shall be unicast
to the originator of the Complex_Desc_req command.

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Status                     |Zdo Status                 |
|NWKAddrOfInterest          |NWK address                |
|Length                     |Unsigned 8-bit integer     |
|ComplexDescriptor          |Complex Descriptor         |


#### User Descriptor Response [0x8011]

The User_Desc_rsp is generated by a remote device in response to a
User_Desc_req directed to the remote device. This command shall be unicast to
the originator of the User_Desc_req command.

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Status                     |Zdo Status                 |
|NWKAddrOfInterest          |NWK address                |
|Length                     |Unsigned 8-bit integer     |
|UserDescriptor             |User Descriptor            |

#### Discovery Cache Response [0x8012]

The Discovery_Cache_rsp is generated by Primary Discovery Cache devices
receiving the Discovery_Cache_req. Remote Devices which are not Primary
Discovery Cache devices (as designated in its Node Descriptor) should not
respond to the Discovery_Cache_req command. 

#### User Descriptor Conf [0x8014]

The User_Desc_conf is generated by a remote device in response to a
User_Desc_set directed to the remote device. This command shall be unicast to
the originator of the User_Desc_set command.


#### Discovery Store Response [0x8016]

The Discovery_store_rsp is provided to notify a Local Device of the request status
from a Primary Discovery Cache device. Included in the response is a status code
to notify the Local Device whether the request is successful (the Primary Cache
Device has space to store the discovery cache data for the Local Device), whether
the request is unsupported (meaning the Remote Device is not a Primary
Discovery Cache device), or insufficient space exists.

#### Node Descriptor Store Response [0x8017]

The Node_store_rsp is provided to notify a Local Device of the request status
from a Primary Discovery Cache device. Included in the response is a status code
to notify the Local Device whether the request is successful (the Primary Cache
Device has space to store the discovery cache data for the Local Device), whether
the request is not supported (meaning the Remote Device is not a Primary
Discovery Cache device), or insufficient space exists. 

#### Power Descriptor Store Response [0x8018]

The Power_Desc_store_rsp is provided to notify a Local Device of the request
status from a Primary Discovery Cache device. Included in the response is a status
code to notify the Local Device whether the request is successful (the Primary
Cache Device has space to store the discovery cache data for the Local Device),
whether the request is not supported (meaning the Remote Device is not a Primary
Discovery Cache device), or insufficient space exists.

#### Active Endpoint Store Response [0x8019]

The Active_EP_store_rsp is provided to notify a Local Device of the request
status from a Primary Discovery Cache device. Included in the response is a status
code to notify the Local Device whether the request is successful (the Primary
Cache Device has space to store the discovery cache data for the Local Device),
the request is not supported (meaning the Remote Device is not a Primary
Discovery Cache device), or insufficient space exists. 

#### Simple Descriptor Store Response [0x801a]

The Simple_Desc_store_rsp is provided to notify a Local Device of the request
status from a Primary Discovery Cache device. Included in the response is a status
code to notify the Local Device whether the request is successful (the Primary
Cache Device has space to store the discovery cache data for the Local Device),
the request is not supported (meaning the Remote Device is not a Primary
Discovery Cache device), or insufficient space exists.

#### Remove Node Cache [0x801b]

The Remove_node_cache_rsp is provided to notify a Local Device of the request
status from a Primary Discovery Cache device. Included in the response is a status
code to notify the Local Device whether the request is successful (the Primary
Cache Device has removed the discovery cache data for the indicated device of
interest), or the request is not supported (meaning the Remote Device is not a
Primary Discovery Cache device).

#### Find Node Cache Response [0x801C]

The Find_node_cache_rsp is provided to notify a Local Device of the successful
discovery of the Primary Discovery Cache device for the given NWKAddr and
IEEEAddr fields supplied in the request, or to signify that the device of interest is
capable of responding to discovery requests. The Find_node_cache_rsp shall be
generated only by Primary Discovery Cache devices holding discovery
information for the NWKAddr and IEEEAddr in the request or the device of
interest itself and all other Remote Devices shall not supply a response. 

#### Extended Simple Descriptor Response [0x801D]

The Extended_Simple_Desc_rsp is generated by a remote device in response to an
Extended_Simple_Desc_req directed to the remote device. This command shall
be unicast to the originator of the Extended_Simple_Desc_req command.

#### Extended Active Endpoint Response [0x801E]

The Extended_Active_EP_rsp is generated by a remote device in response to an
Extended_Active_EP_req directed to the remote device. This command shall be
unicast to the originator of the Extended_Active_EP_req command.

#### End Device Bind Response [0x8020]

The End_Device_Bind_rsp is generated by the ZigBee Coordinator in response to
an End_Device_Bind_req and contains the status of the request. This command
shall be unicast to each device involved in the bind attempt, using the
acknowledged data service.

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Status                     |Zdo Status                 |


#### Bind Response [0x8021]

The Bind_rsp is generated in response to a Bind_req. If the Bind_req is processed
and the Binding Table entry committed on the Remote Device, a Status of
SUCCESS is returned. If the Remote Device is not a Primary binding table cache
or the SrcAddress, a Status of NOT_SUPPORTED is returned. The supplied
endpoint shall be checked to determine whether it falls within the specified range.
If it does not, a Status of INVALID_EP shall be returned. If the Remote Device is
the Primary binding table cache or SrcAddress but does not have Binding Table
resources for the request, a Status of TABLE_FULL is returned.

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Status                     |Zdo Status                 |

#### Unbind Response [0x8022]

The Unbind_rsp is generated in response to an Unbind_req. If the Unbind_req is
processed and the corresponding Binding Table entry is removed from the Remote
Device, a Status of SUCCESS is returned. If the Remote Device is not the ZigBee
Coordinator or the SrcAddress, a Status of NOT_SUPPORTED is returned. The
supplied endpoint shall be checked to determine whether it falls within the
specified range. If it does not, a Status of INVALID_EP shall be returned If the
Remote Device is the ZigBee Coordinator or SrcAddress but does not have a
Binding Table entry corresponding to the parameters received in the request, a
Status of NO_ENTRY is returned.

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Status                     |Zdo Status                 |


#### Bind Register Response [0x8023]

The Bind_Register_rsp is generated from a primary binding table cache device in
response to a Bind_Register_req and contains the status of the request. This
command shall be unicast to the requesting device.

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Status                     |Zdo Status                 |
|BindingTableEntries        |Unsigned 16-bit integer    |
|BindingTableListCount      |Unsigned 16-bit integer    |
|BindingTableList           |N x Binding Table[BindingTableListCount] |

#### Replace Device Response [0x8024]

The Replace_Device_rsp is generated from a primary binding table cache device
in response to a Replace_Device_req and contains the status of the request. This
command shall be unicast to the requesting device. If the device receiving the
Replace_Device_req is not a primary binding table cache, a Status of
NOT_SUPPORTED is returned. The primary binding table cache shall search its
binding table for entries whose source address and source endpoint, or whose
destination address and destination endpoint match OldAddress and OldEndpoint,
as described in the text for Replace_Device_req. It shall change these entries to
have NewAddress and possibly NewEndpoint. It shall then return a response of
SUCCESS.

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Status                     |Zdo Status                 |

#### Store Backup Bind Entry Response [0x8025]

The Store_Bkup_Bind_Entry_rsp is generated from a backup binding table cache
device in response to a Store_Bkup_Bind_Entry_req from a primary binding table
cache, and contains the status of the request. This command shall be unicast to the
requesting device. If the remote device is not a backup binding table cache, it shall
return a status of NOT_SUPPORTED. If the originator of the request is not
recognized as a primary binding table cache, it shall return a status of
INV_REQUESTTYPE. Otherwise, the backup binding table cache shall add the
binding entry to its binding table and return a status of SUCCESS. If there is no
room, it shall return a status of TABLE_FULL.

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Status                     |Zdo Status                 |

#### Remove Backup Bind Entry Response [0x8026]

The Remove_Bkup_Bind_Entry_rsp is generated from a backup binding table
cache device in response to a Remove_Bkup_Bind_Entry_req from the primary
binding table cache and contains the status of the request. This command shall be
unicast to the requesting device. If the remote device is not a backup binding table
cache, it shall return a status of NOT_SUPPORTED. If the originator of the
request is not recognized as a primary binding table cache, it shall return a status
of INV_REQUESTTYPE. Otherwise, the backup binding table cache shall delete
the binding entry from its binding table and return a status of SUCCESS. If the
entry is not found, it shall return a status of NO_ENTRY.

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Status                     |Zdo Status                 |
|EntryCount                 |Unsigned 16-bit integer    |

#### Backup Bind Table Response [0x8027]

The Backup_Bind_Table_rsp is generated from a backup binding table cache
device in response to a Backup_Bind_Table_req from a primary binding table
cache and contains the status of the request. This command shall be unicast to the
requesting device. If the remote device is not a backup binding table cache, it shall
return a status of NOT_SUPPORTED. If the originator of the request is not
recognized as a primary binding table cache, it shall return a status of
INV_REQUESTTYPE. Otherwise, the backup binding table cache shall
overwrite the binding entries in its binding table starting with StartIndex and
continuing for BindingTableListCount entries. If this exceeds its table size, it shall
fill in as many entries as possible and return a status of TABLE_FULL and the
EntryCount parameter will be the number of entries in the table. Otherwise, it
shall return a status of SUCCESS and EntryCount will be equal to StartIndex +
BindingTableListCount from Backup_Bind_Table_req.

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Status                     |Zdo Status                 |

#### Recover Bind Table Response [0x8028]

The Recover_Bind_Table_rsp is generated from a backup binding table cache
device in response to a Recover_Bind_Table_req from a primary binding table
cache and contains the status of the request. This command shall be unicast to the
requesting device. If the responding device is not a backup binding table cache, it
shall return a status of NOT_SUPPORTED. If the originator of the request is not
recognized as a primary binding table cache it shall return a status of
INV_REQUESTTYPE. Otherwise, the backup binding table cache shall prepare a
list of binding table entries from its backup beginning with StartIndex. It will fit in
as many entries as possible into a Recover_Bind_Table_rsp command and return a
status of SUCCESS. If StartIndex is more than the number of entries in the
Binding table, a status of NO_ENTRY is returned. For a successful response,
BindingTableEntries is the total number of entries in the backup binding table, and
BindingTableListCount is the number of entries which is being returned in the
response. 

#### Backup Source Bind Response [0x8029]

The Backup_Source_Bind_rsp is generated from a backup binding table cache
device in response to a Backup_Source_Bind_req from a primary binding table
cache and contains the status of the request. This command shall be unicast to the
requesting device. If the remote device is not a backup binding table cache, it shall
return a status of NOT_SUPPORTED. If the originator of the request is not
recognized as a primary binding table cache, it shall return a status of
INV_REQUESTTYPE. Otherwise, the backup binding table cache shall
overwrite its backup source binding table starting with StartIndex and continuing
for BindingTableListCount entries. If this exceeds its table size, it shall return a
status of TABLE_FULL. Otherwise it shall return a status of SUCCESS.

#### Recover Source Bind Response [0x8029]

The Recover_Source_Bind_rsp is generated from a backup binding table cache
device in response to a Recover_Source_Bind_req from a primary binding table
cache and contains the status of the request. This command shall be unicast to the
requesting device. If the responding device is not a backup binding table cache, it
shall return a status of NOT_SUPPORTED. If the originator of the request is not
recognized as a primary binding table cache, it shall return a status of
INV_REQUESTTYPE. Otherwise, the backup binding table cache shall prepare a
list of binding table entries from its backup beginning with StartIndex. It will fit in
as many entries as possible into a Recover_Source_Bind_rsp command and return
a status of SUCCESS. If StartIndex is more than the number of entries in the
Source table, a status of NO_ENTRY is returned. For a successful response,
SourceTableEntries is the total number of entries in the backup source table, and
SourceTableListCount is the number of entries which is being returned in the
response.

#### Management Network Discovery Response [0x8030]

The Mgmt_NWK_Disc_rsp is generated in response to an
Mgmt_NWK_Disc_req. If this management command is not supported, a status
of NOT_SUPPORTED shall be returned and all parameter fields after the Status
field shall be omitted. Otherwise, the Remote Device shall implement the
following process.

Upon receipt of and after support for the Mgmt_NWK_Disc_req has been
verified, the Remote Device shall issue an NLME-NETWORKDISCOVERY.request
primitive using the ScanChannels and ScanDuration
parameters, supplied in the Mgmt_NWK_Disc_req command. Upon receipt of the
NLME-NETWORK-DISCOVERY.confirm primitive, the Remote Device shall
report the results, starting with the StartIndex element, via the
Mgmt_NWK_Disc_rsp command. The NetworkList field shall contain whole
NetworkList records, until the limit on
MSDU size, i.e., aMaxMACFrameSize, is reached. The number of
results reported shall be set in the NetworkListCount.

#### Management LQI Response [0x8031]

The Mgmt_Lqi_rsp is generated in response to an Mgmt_Lqi_req. If this
management command is not supported, a status of NOT_SUPPORTED shall be
returned and all parameter fields after the Status field shall be omitted. Otherwise,
the Remote Device shall implement the following processing.

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Status                     |Zdo Status                 |
|NeighborTableEntries       |Unsigned 8-bit integer     |
|StartIndex                 |Unsigned 8-bit integer     |
|NeighborTableListCount     |Unsigned 8-bit integer     |
|NeighborTableList          |Neighbor Table[NeighborTableListCount]|


#### Management Routing Response [0x8032]

The Mgmt_Rtg_rsp is generated in response to an Mgmt_Rtg_req. If this
management command is not supported, a status of NOT_SUPPORTED shall be
returned and all parameter fields after the Status field shall be omitted. Otherwise,
the Remote Device shall implement the following processing.

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Status                     |Zdo Status                 |
|RoutingTableEntries        |Unsigned 8-bit integer     |
|StartIndex                 |Unsigned 8-bit integer     |
|RoutingTableListCount      |Unsigned 8-bit integer     |
|RoutingTableList           |Routing Table[RoutingTableListCount]|


#### Management Bind Response [0x8033]

The Mgmt_Bind_rsp is generated in response to a Mgmt_Bind_req. If this
management command is not supported, a status of NOT_SUPPORTED shall be
returned and all parameter fields after the Status field shall be omitted. Otherwise,
the Remote Device shall implement the following processing.

|Field Name                 |Data Type                              |
|---------------------------|---------------------------------------|
|Status                     |Zdo Status                             |
|BindingTableEntries        |Unsigned 8-bit integer                 |
|StartIndex                 |Unsigned 8-bit integer                 |
|BindingTableListCount      |Unsigned 8-bit integer                 |
|BindingTableList           |Binding Table[BindingTableListCount]   |

#### Management Leave Response [0x8034]

The Mgmt_Leave_rsp is generated in response to a Mgmt_Leave_req. If this
management command is not supported, a status of NOT_SUPPORTED shall be
returned. Otherwise, the Remote Device shall implement the following
processing.

Upon receipt of and after support for the Mgmt_Leave_req has been verified, the
Remote Device shall execute the NLME-LEAVE.request to disassociate from the
currently associated network. The Mgmt_Leave_rsp shall contain the same status
that was contained in the NLME-LEAVE.confirm primitive.

Once a device has disassociated, it may execute pre-programmed logic to perform
NLME-NETWORK-DISCOVERY and NLME-JOIN to join/re-join a network. 


|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Status                     |Zdo Status                 |

#### Management Direct Join Response [0x8035]

The Mgmt_Direct_Join_rsp is generated in response to a Mgmt_Direct_Join_req.
If this management command is not supported, a status of NOT_SUPPORTED
shall be returned. Otherwise, the Remote Device shall implement the following
processing.

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Status                     |Zdo Status                 |

#### Management Permit Joining Response [0x8036]

The Mgmt_Permit_Joining_rsp is generated in response to a unicast
Mgmt_Permit_Joining_req. In the description which follows, note that no
response shall be sent if the Mgmt_Permit_Joining_req was received as a
broadcast to all routers. If this management command is not permitted by the
requesting device, a status of INVALID_REQUEST shall be returned. Upon
receipt and after support for Mgmt_Permit_Joining_req has been verified, the
Remote Device shall execute the NLME-PERMIT-JOINING.request. The
Mgmt_Permit-Joining_rsp shall contain the same status that was contained in the
NLME-PERMIT-JOINING.confirm primitive. 

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Status                     |Zdo Status                 |


#### Management Cache Response [0x8037]

The Mgmt_Cache_rsp is generated in response to an Mgmt_Cache_req. If this
management command is not supported, or the Remote Device is not a Primary
Cache Device, a status of NOT_SUPPORTED shall be returned and all parameter
fields after the Status field shall be omitted. Otherwise, the Remote Device shall
implement the following processing. Upon receipt of the Mgmt_Cache_req and
after support for the Mgmt_Cache_req has been verified, the Remote Device shall
access an internally maintained list of registered ZigBee End Devices utilizing the
discovery cache on this Primary Discovery Cache device. The entries reported
shall be those, starting with StartIndex and including whole DiscoveryCacheList
records until the limit on MSDU size, i.e., aMaxMACFrameSize, is reached. Within
the Mgmt_Cache_rsp command, the
DiscoveryCacheListEntries field shall represent the total number of registered
entries in the Remote Device. The parameter DiscoveryCacheListCount shall be
the number of entries reported in the DiscoveryCacheList field of the
Mgmt_Cache_rsp command. 

#### Management Network Update Notify [0x8038]

The Mgmt_NWK_Update_notify is provided to enable ZigBee devices to report
the condition on local channels to a network manager. The scanned channel list is
the report of channels scanned and it is followed by a list of records, one for each
channel scanned, each record including one byte of the energy level measured
during the scan, or 0xff if there is too much interference on this channel.

When sent in response to a Mgmt_NWK_Update_req command the status field
shall represent the status of the request. When sent unsolicited the status field
shall be set to SUCCESS.
A Status of NOT_SUPPORTED indicates that the request was directed to a device
which was not the ZigBee Coordinator or that the ZigBee Coordinator does not
support End Device Binding. Otherwise, End_Device_Bind_req processing is
performed as described below, including transmission of the
End_Device_Bind_rsp.

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Status                     |Zdo Status                 |
|ScannedChannels            |Unsigned 32-bit integer    |
|TotalTransmissions         |Unsigned 16-bit integer    |
|TransmissionFailures       |Unsigned 16-bit integer    |
|ScannedChannelsListCount   |Unsigned 8-bit integer     |
|EnergyValues               |Unsigned 8-bit integer[ScannedChannelsListCount] |

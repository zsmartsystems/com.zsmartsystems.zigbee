# ZigBee Home Automation [0x0104]

Home Automation ZigBee cluster library protocol description is used to code generate cluster specific command serialization classes.

# General

## General [0xFFFF]

### Received

#### Read Attributes Command [0x00]

The read attributes command is generated when a device wishes to determine the
values of one or more attributes located on another device. Each attribute
identifier field shall contain the identifier of the attribute to be read. 

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Identifiers                |N X Attribute identifier   |

#### Read Attributes Response Command [0x01]

The read attributes response command is generated in response to a read attributes
or read attributes structured command. The command frame shall contain a read
attribute status record for each attribute identifier specified in the original read
attributes or read attributes structured command. For each read attribute status
record, the attribute identifier field shall contain the identifier specified in the
original read attributes or read attributes structured command.

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Records                    |N X Read attribute status record |

#### Write Attributes Command [0x02]

The write attributes command is generated when a device wishes to change the
values of one or more attributes located on another device. Each write attribute
record shall contain the identifier and the actual value of the attribute to be
written.

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Records                    |N X Write attribute record |

#### Write Attributes Undivided Command [0x03]

The write attributes undivided command is generated when a device wishes to
change the values of one or more attributes located on another device, in such a
way that if any attribute cannot be written (e.g. if an attribute is not implemented
on the device, or a value to be written is outside its valid range), no attribute
values are changed.

In all other respects, including generation of a write attributes response command,
the format and operation of the command is the same as that of the write attributes
command, except that the command identifier field shall be set to indicate the
write attributes undivided command.

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Records                    |N X Write attribute record |

#### Write Attributes Response Command [0x04]

The write attributes response command is generated in response to a write
attributes command.

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Records                    |N X Write attribute status record |

#### Write Attributes No Response Command [0x05]

The write attributes no response command is generated when a device wishes to
change the value of one or more attributes located on another device but does not
require a response. Each write attribute record shall contain the identifier and the
actual value of the attribute to be written. 

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Records                    |N X Write attribute record |

#### Configure Reporting Command [0x06]

The Configure Reporting command is used to configure the reporting mechanism
for one or more of the attributes of a cluster.

The individual cluster definitions specify which attributes shall be available to this
reporting mechanism, however specific implementations of a cluster may make
additional attributes available.

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Records                    |N X Attribute reporting configuration record|

#### Configure Reporting Response Command [0x07]

The Configure Reporting Response command is generated in response to a
Configure Reporting command. 

|Field Name                 |Data Type                    |
|---------------------------|-----------------------------|
|Status                     |Zcl Status [status-response] |
|Records                    |N X Attribute status record  |


##### Status
Status is only provided if the command was successful, and the 
attribute status records are not included for successfully
written attributes, in order to save bandwidth.

##### Records
Note that attribute status records are not included for successfully
configured attributes in order to save bandwidth.  In the case of successful
configuration of all attributes, only a single attribute status record SHALL
be included in the command, with the status field set to SUCCESS and the direction and
attribute identifier fields omitted.

#### Read Reporting Configuration Command [0x08]

The Read Reporting Configuration command is used to read the configuration
details of the reporting mechanism for one or more of the attributes of a cluster. 

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Records                    |N X Attribute record       |

#### Read Reporting Configuration Response Command [0x09]

The Read Reporting Configuration Response command is used to respond to a
Read Reporting Configuration command.

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Records                    |N X Attribute reporting configuration record|

#### Report Attributes Command [0x0a]

The report attributes command is used by a device to report the values of one or
more of its attributes to another device, bound a priori. Individual clusters, defined
elsewhere in the ZCL, define which attributes are to be reported and at what
interval.

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Reports                    |N X Attribute report       |

#### Default Response Command [0x0b]

The default response command is generated when a device receives a unicast
command, there is no other relevant response specified for the command, and
either an error results or the Disable default response bit of its Frame control field
is set to 0.

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Command identifier         |Unsigned 8-bit integer     |
|Status code                |Zcl Status                 |

#### Discover Attributes Command [0x0c]

The discover attributes command is generated when a remote device wishes to
discover the identifiers and types of the attributes on a device which are supported
within the cluster to which this command is directed. 

|Field Name                    |Data Type                  |
|------------------------------|---------------------------|
|Start attribute identifier    |Unsigned 16-bit integer    |
|Maximum attribute identifiers |Unsigned 8-bit integer     |

##### Start attribute identifier
The start attribute identifier field is 16 bits in length and specifies the value
of the identifier at which to begin the attribute discovery.

##### Maximum attribute identifiers
The  maximum attribute identifiers field is 8 bits in length and specifies the
maximum number of attribute identifiers that are to be returned in the resulting
Discover Attributes Response command.

#### Discover Attributes Response Command [0x0d]

The discover attributes response command is generated in response to a discover
attributes command. 

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Discovery Complete         |Boolean                    |
|Attribute Information      |N X Attribute information  |


##### Discovery Complete
The discovery complete field is a Boolean field. A value of 0 indicates that there
are more attributes to be discovered that have an attribute identifier value greater
than the last attribute identifier in the last attribute information field. A value
of 1 indicates that there are no more attributes to be discovered.

##### Attribute Identifier
The attribute identifier field SHALL contain the identifier of a discovered attribute.
Attributes SHALL be included in ascending order, starting with the lowest attribute
identifier that is greater than or equal to the start attribute identifier field of the
received Discover Attributes command.

#### Read Attributes Structured Command [0x0e]

The read attributes command is generated when a device wishes to determine the
values of one or more attributes, or elements of attributes, located on another
device. Each attribute identifier field shall contain the identifier of the attribute to
be read.

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Attribute selectors        |N X Attribute selector     |

#### Write Attributes Structured Command [0x0f]

The write attributes structured command is generated when a device wishes to
change the values of one or more attributes located on another device. Each write
attribute record shall contain the identifier and the actual value of the attribute, or
element thereof, to be written.

|Field Name                 |Data Type                    |
|---------------------------|-----------------------------|
|Status                     |Zcl Status [status-response] |
|Attribute selectors        |N X Attribute selector       |


##### Status
Status is only provided if the command was successful, and the 
attribute selector records are not included for successfully
written attributes, in order to save bandwidth.

##### Attribute selectors
Note that write attribute status records are not included for successfully
written attributes, in order to save bandwidth. In the case of successful 
writing of all attributes, only a single  write attribute status record
SHALL be included in the command, with the status field set to SUCCESS and the
attribute identifier and selector fields omitted.

#### Write Attributes Structured Response Command [0x10]

The write attributes structured response command is generated in response to a
write attributes structured command.

|Field Name                 |Data Type                         |
|---------------------------|----------------------------------|
|Status                     |Zcl Status[status-response]       |
|Records                    |N X Write attribute status record |

##### Status
Status is only provided if the command was successful, and the write
attribute status records are not included for successfully
written attributes, in order to save bandwidth.

##### Records
Note that write attribute status records are not included for successfully
written attributes, in order to save bandwidth.  In the case of successful
writing of all attributes, only a single write attribute status record
SHALL be included in the command, with the status field set to SUCCESS and the
attribute identifier field omitted.

#### Discover Commands Received [0x11]

The Discover Commands Received command is generated when a remote device wishes to discover the
optional and mandatory commands the cluster to which this command is sent can process.

|Field Name                   |Data Type                  |
|-----------------------------|---------------------------|
|Start command identifier     |Unsigned 8-bit integer     |
|Maximum command identifiers  |Unsigned 8-bit integer     |


#### Discover Commands Received Response [0x12]

The Discover Commands Received Response is generated in response to a Discover Commands Received
command. 

|Field Name                   |Data Type                    |
|-----------------------------|-----------------------------|
|Discovery complete           |Boolean                      |
|Command identifiers          |X Unsigned 8-bit integer     |


#### Discover Commands Generated [0x13]

The Discover Commands Generated command is generated when a remote device wishes to discover the
commands that a cluster may generate on the device to which this command is directed.

|Field Name                   |Data Type                  |
|-----------------------------|---------------------------|
|Start command identifier     |Unsigned 8-bit integer     |
|Maximum command identifiers  |Unsigned 8-bit integer     |

#### Discover Commands Generated Response [0x14]

The Discover Commands Generated Response is generated in response to a Discover Commands Generated
command.

|Field Name                   |Data Type                    |
|-----------------------------|-----------------------------|
|Discovery complete           |Boolean                      |
|Command identifiers          |X Unsigned 8-bit integer     |


#### Discover Attributes Extended [0x15]

The Discover Attributes Extended command is generated when a remote device wishes to discover the
identifiers and types of the attributes on a device which are supported within the cluster to which this
command is directed, including whether the attribute is readable, writeable or reportable.

|Field Name                   |Data Type                  |
|-----------------------------|---------------------------|
|Start attribute identifier   |Unsigned 16-bit integer    |
|Maximum attribute identifiers|Unsigned 8-bit integer     |


#### Discover Attributes Extended Response [0x16]

The Discover Attributes Extended Response command is generated in response to a Discover Attributes
Extended command. 

|Field Name                   |Data Type                          |
|-----------------------------|-----------------------------------|
|Discovery complete           |Boolean                            |
|Attribute Information        |N x Extended Attribute Information |



## Basic [0x0000]

### Attributes

|Id     |Name                 |Type                       |Access     |Implement |Reporting |
|-------|---------------------|---------------------------|-----------|----------|----------|
|0x0000 |ZCLVersion           |Unsigned 8-bit integer     |Read Only  |Mandatory |          |
|0x0001 |ApplicationVersion   |Unsigned 8-bit integer     |Read Only  |Mandatory |          |
|0x0002 |StackVersion         |Unsigned 8-bit integer     |Read Only  |Mandatory |          |
|0x0003 |HWVersion            |Unsigned 8-bit integer     |Read Only  |Mandatory |          |
|0x0004 |ManufacturerName     |Character string           |Read Only  |Mandatory |          |
|0x0005 |ModelIdentifier      |Character string           |Read Only  |Mandatory |          |
|0x0006 |DateCode             |Character string           |Read Only  |Mandatory |          |
|0x0007 |PowerSource          |8-bit enumeration          |Read Only  |Mandatory |          |
|0x0010 |LocationDescription  |Character string           |Read/Write |Mandatory |          |
|0x0011 |PhysicalEnvironment  |8-bit enumeration          |Read/Write |Mandatory |          |
|0x0012 |DeviceEnabled        |Boolean                    |Read/Write |Mandatory |          |
|0x0013 |AlarmMask            |8-bit bitmap               |Read/Write |Mandatory |          |
|0x0014 |DisableLocalConfig   |8-bit bitmap               |Read/Write |Mandatory |          |
|0x4000 |SWBuildID            |Character string           |Read Only  |Optional  |          |

#### ZCLVersion Attribute
The ZCLVersion attribute is 8 bits in length and specifies the version number of
the ZigBee Cluster Library that all clusters on this endpoint conform to. 

#### ApplicationVersion Attribute
The ApplicationVersion attribute is 8 bits in length and specifies the version
number of the application software contained in the device. The usage of this
attribute is manufacturer dependent.

#### StackVersion Attribute
The StackVersion attribute is 8 bits in length and specifies the version number
of the implementation of the ZigBee stack contained in the device. The usage of
this attribute is manufacturer dependent.

#### HWVersion Attribute
The HWVersion attribute is 8 bits in length and specifies the version number of
the hardware of the device. The usage of this attribute is manufacturer dependent.

#### ManufacturerName Attribute
The ManufacturerName attribute is a maximum of 32 bytes in length and specifies
the name of the manufacturer as a ZigBee character string. 

#### ModelIdentifier Attribute
The ModelIdentifier attribute is a maximum of 32 bytes in length and specifies the 
model number (or other identifier) assigned by the manufacturer as a ZigBee character string. 

#### DateCode Attribute
The DateCode attribute is a ZigBee character string with a maximum length of 16 bytes.
The first 8 characters specify the date of manufacturer of the device in international
date notation according to ISO 8601, i.e. YYYYMMDD, e.g. 20060814.

#### PowerSource Attribute
The PowerSource attribute is 8 bits in length and specifies the source(s) of power
available to the device. Bits b0–b6 of this attribute represent the primary power
source of the device and bit b7 indicates whether the device has a secondary power
source in the form of a battery backup. 

|Id     |Name                      |
|-------|--------------------------|
|0x0000 |Unknown                   |
|0x0001 |Mains Single Phase        |
|0x0002 |Mains Three Phase         |
|0x0003 |Battery                   |
|0x0004 |DC Source                 |
|0x0005 |Emergency Mains Constant  |
|0x0006 |Emergency Mains Changeover|



#### LocationDescription Attribute
The LocationDescription attribute is a maximum of 16 bytes in length and describes
the physical location of the device as a ZigBee character string. 

#### PhysicalEnvironment Attribute
The PhysicalEnvironment attribute is 8 bits in length and specifies the type of
physical environment in which the device will operate. 

|Id     |Name                      |
|-------|--------------------------|
|0x0000 |Unknown                   |
|0x0001 |Atrium                    |
|0x0002 |Bar                       |
|0x0003 |Courtyard                 |
|0x0004 |Bathroom                  |
|0x0005 |edroom                    |

#### DeviceEnabled Attribute
The DeviceEnabled attribute is a boolean and specifies whether the device is enabled
or disabled. 

#### AlarmMask Attribute
The AlarmMask attribute is 8 bits in length and specifies which of a number of general 
alarms may be generated.

#### DisableLocalConfig Attribute
The DisableLocalConfig attribute allows a number of local device configuration
functions to be disabled.

The intention of this attribute is to allow disabling of any local configuration
user interface, for example to prevent reset or binding buttons being activated by
unauthorised persons in a public building.

#### SWBuildID Attribute
The SWBuildIDattribute represents a detailed, manufacturer-specific reference to the version of the software.


### Received

#### Reset to Factory Defaults Command [0x00]
On receipt of this command, the device resets all the attributes of all its clusters
to their factory defaults. Note that ZigBee networking functionality,bindings, groups
or other persistent data are not affected by this command

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|

### Generated

No cluster specific commands.

## Power configuration [0x0001]
Attributes for determining detailed information about a device’s power source(s),
and for configuring under/over voltage alarms.

### Attributes

|Id     |Name                      |Type                       |Access     |Implement |Reporting |
|-------|--------------------------|---------------------------|-----------|----------|----------|
|0x0000 |MainsVoltage              |Unsigned 16-bit integer    |Read only  |Optional  |          |
|0x0001 |MainsFrequency            |Unsigned 16-bit integer    |Read only  |Optional  |          |
|0x0010 |MainsAlarmMask            |8-bit Bitmap               |Read/Write |Optional  |          |
|0x0011 |MainsVoltageMinThreshold  |Unsigned 16-bit integer    |Read/Write |Optional  |          |
|0x0012 |MainsVoltageMaxThreshold  |Unsigned 16-bit integer    |Read/Write |Optional  |          |
|0x0013 |MainsVoltageDwellTripPoint|Unsigned 16-bit integer    |Read/Write |Optional  |          |
|0x0020 |BatteryVoltage            |Unsigned 8-bit integer     |Read       |Optional  |          |
|0x0021 |BatteryPercentageRemaining   |Unsigned 8-bit integer     |Read       |Optional  |Mandatory |
|0x0030 |BatteryManufacturer          |Character string           |Read/Write |Optional  |          |
|0x0031 |BatterySize                  |8-bit Enumeration          |Read/Write |Optional  |          |
|0x0032 |BatteryAHrRating             |Unsigned 16-bit integer    |Read/Write |Optional  |          |
|0x0033 |BatteryQuantity              |Unsigned 8-bit integer     |Read/Write |Optional  |          |
|0x0034 |BatteryRatedVoltage          |Unsigned 8-bit integer     |Read/Write |Optional  |          |
|0x0035 |BatteryAlarmMask             |8-bit Bitmap               |Read/Write |Optional  |          |
|0x0036 |BatteryVoltageMinThreshold   |Unsigned 8-bit integer     |Read/Write |Optional  |          |
|0x0037 |BatteryVoltageThreshold1     |Unsigned 8-bit integer     |Read/Write |Optional  |          |
|0x0038 |BatteryVoltageThreshold2     |Unsigned 8-bit integer     |Read/Write |Optional  |          |
|0x0039 |BatteryVoltageThreshold3     |Unsigned 8-bit integer     |Read/Write |Optional  |          |
|0x003A |BatteryPercentageMinThreshold|Unsigned 8-bit integer     |Read/Write |Optional  |          |
|0x003B |BatteryPercentageThreshold1  |Unsigned 8-bit integer     |Read/Write |Optional  |          |
|0x003C |BatteryPercentageThreshold2  |Unsigned 8-bit integer     |Read/Write |Optional  |          |
|0x003D |BatteryPercentageThreshold3  |Unsigned 8-bit integer     |Read/Write |Optional  |          |
|0x003E |BatteryAlarmState            |32-bit Bitmap              |Read       |Optional  |          |



#### MainsVoltage Attribute
The MainsVoltage attribute is 16-bits in length and specifies the actual (measured)
RMS voltage (or DC voltage in the case of a DC supply) currently applied to the
device, measured in units of 100mV. 

####  MainsFrequency Attribute
The MainsFrequency attribute is 8-bits in length and represents the frequency, in
Hertz, of the mains as determined by the device as follows:-

MainsFrequency = 0.5 x measured frequency

Where 2 Hz <= measured frequency <= 506 Hz, corresponding to a

MainsFrequency in the range 1 to 0xfd.

The maximum resolution this format allows is 2 Hz.
The following special values of MainsFrequency apply.
<li>0x00 indicates a frequency that is too low to be measured.</li>
<li>0xfe indicates a frequency that is too high to be measured.</li>
<li>0xff indicates that the frequency could not be measured.</li>

#### MainsAlarmMask Attribute
The MainsAlarmMask attribute is 8-bits in length and specifies which mains
alarms may be generated. A ‘1’ in each bit position enables the alarm. 

#### MainsVoltageMinThreshold Attribute
The MainsVoltageMinThreshold attribute is 16-bits in length and specifies the
lower alarm threshold, measured in units of 100mV, for the MainsVoltage
attribute. The value of this attribute shall be less than MainsVoltageMaxThreshold.

If the value of MainsVoltage drops below the threshold specified by
MainsVoltageMinThreshold, the device shall start a timer to expire after
MainsVoltageDwellTripPoint seconds. If the value of this attribute increases to
greater than or equal to MainsVoltageMinThreshold before the timer expires, the
device shall stop and reset the timer. If the timer expires, an alarm shall be
generated.

The Alarm Code field included in the generated alarm shall be 0x00.

If this attribute takes the value 0xffff then this alarm shall not be generated.

#### MainsVoltageMaxThreshold Attribute
The MainsVoltageMaxThreshold attribute is 16-bits in length and specifies the
upper alarm threshold, measured in units of 100mV, for the MainsVoltage
attribute. The value of this attribute shall be greater than
MainsVoltageMinThreshold.

If the value of MainsVoltage rises above the threshold specified by
MainsVoltageMaxThreshold, the device shall start a timer to expire after
MainsVoltageDwellTripPoint seconds. If the value of this attribute drops to lower
than or equal to MainsVoltageMaxThreshold before the timer expires, the device
shall stop and reset the timer. If the timer expires, an alarm shall be generated.

The Alarm Code field included in the generated alarm shall be 0x01.

If this attribute takes the value 0xffff then this alarm shall not be generated.

#### MainsVoltageDwellTripPoint Attribute
The MainsVoltageDwellTripPoint attribute is 16-bits in length and specifies the
length of time, in seconds that the value of MainsVoltage may exist beyond either
of its thresholds before an alarm is generated.

If this attribute takes the value 0xffff then the associated alarms shall not be
generated.

#### BatteryVoltage Attribute
The BatteryVoltage attribute is 8-bits in length and specifies the current actual
(measured) battery voltage, in units of 100mV.
The value 0xff indicates an invalid or unknown reading. 

#### BatteryManufacturer Attribute
The BatteryManufacturer attribute is a maximum of 16 bytes in length and
specifies the name of the battery manufacturer as a ZigBee character string. 

#### BatterySize Attribute
The BatterySize attribute is an enumeration which specifies the type of battery
being used by the device.

|Id     |Name                      |
|-------|--------------------------|
|0x0000 |No Battery                |
|0x0001 |Build In                  |
|0x0002 |Other                     |
|0x0003 |AA  Cell                  |
|0x0004 |AAA Cell                  |
|0x0005 |C Cell                    |
|0x0006 |D Cell                    |
|0x0007 |CR2 Cell                  |
|0x0008 |CR123A Cell               |
|0x00FF |Unknown                   |


#### BatteryAHrRating Attribute
The BatteryAHrRating attribute is 16-bits in length and specifies the Ampere-hour
rating of the battery, measured in units of 10mAHr.

#### BatteryQuantity Attribute
The BatteryQuantity attribute is 8-bits in length and specifies the number of
battery cells used to power the device.

#### BatteryRatedVoltage Attribute
The BatteryRatedVoltage attribute is 8-bits in length and specifies the rated
voltage of the battery being used in the device, measured in units of 100mV.

#### BatteryAlarmMask Attribute
The BatteryAlarmMask attribute is 8-bits in length and specifies which battery
alarms may be generated.

#### BatteryVoltageMinThreshold Attribute
The BatteryVoltageMinThreshold attribute is 8-bits in length and specifies the low
voltage alarm threshold, measured in units of 100mV, for the BatteryVoltage
attribute. 

If the value of BatteryVoltage drops below the threshold specified by
BatteryVoltageMinThreshold an alarm shall be generated.

The Alarm Code field included in the generated alarm shall be 0x10.

If this attribute takes the value 0xff then this alarm shall not be generated.

### Received

No cluster specific commands.

### Generated

No cluster specific commands.

## Device Temperature Configuration [0x0002]

### Received

No cluster specific commands.

### Generated

No cluster specific commands.

## Identify [0x0003]
Attributes and commands to put a device into an Identification mode (e.g. flashing
a light), that indicates to an observer – e.g. an installer - which of several devices
it is, also to request any device that is identifying itself to respond to the initiator.

Note that this cluster cannot be disabled, and remains functional regardless of the
setting of the DeviceEnable attribute in the Basic cluster.

### Attributes

|Id     |Name                 |Type                       |Access     |Implement |Reporting |
|-------|---------------------|---------------------------|-----------|----------|----------|
|0x0000 |IdentifyTime         |Unsigned 16-bit integer    |Read/Write |Mandatory |          |

#### IdentifyTime Attribute
The IdentifyTime attribute specifies the remaining length of time, in seconds, that
the device will continue to identify itself.

If this attribute is set to a value other than 0x0000 then the device shall enter its
identification procedure, in order to indicate to an observer which of several
devices it is. It is recommended that this procedure consists of flashing a light
with a period of 0.5 seconds. The IdentifyTime attribute shall be decremented
every second.

If this attribute reaches or is set to the value 0x0000 then the device shall
terminate its identification procedure.

### Received

#### Identify Command [0x00]
The identify command starts or stops the receiving device identifying itself. 

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Identify Time              |Unsigned 16-bit integer    |

#### Identify Query Command [0x01]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|

### Generated

#### Identify Query Response Command [0x00]
The identify query response command is generated in response to receiving an
Identify Query command in the case that the device is currently identifying itself. 

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Identify Time              |Unsigned 16-bit integer    |

## Groups [0x0004]
The ZigBee specification provides the capability for group addressing. That is,
any endpoint on any device may be assigned to one or more groups, each labeled
with a 16-bit identifier (0x0001 – 0xfff7), which acts for all intents and purposes
like a network address. Once a group is established, frames, sent using the
APSDE-DATA.request primitive and having a DstAddrMode of 0x01, denoting
group addressing, will be delivered to every endpoint assigned to the group
address named in the DstAddr parameter of the outgoing APSDE-DATA.request
primitive on every device in the network for which there are such endpoints.

Management of group membership on each device and endpoint is implemented
by the APS, but the over-the-air messages that allow for remote management and
commissioning of groups are defined here in the cluster library on the theory that,
while the basic group addressing facilities are integral to the operation of the
stack, not every device will need or want to implement this management cluster.
Furthermore, the placement of the management commands here allows developers
of proprietary profiles to avoid implementing the library cluster but still exploit
group addressing

### Received

#### Add Group Command [0x00]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Group ID                   |Unsigned 16-bit integer    |
|Group Name                 |Character string           |

#### View Group Command [0x01]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Group ID                   |Unsigned 16-bit integer    |

#### Get Group Membership Command [0x02]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Group count                |Unsigned 8-bit integer     |
|Group list                 |N X Unsigned 16-bit integer|

#### Remove Group Command [0x03]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Group ID                   |Unsigned 16-bit integer    |

#### Remove All Groups Command [0x04]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|

#### Add Group If Identifying Command [0x05]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Group ID                   |Unsigned 16-bit integer    |
|Group Name                 |Character string           |

### Generated

#### Add Group Response Command [0x00]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Status                     |8-bit enumeration          |
|Group ID                   |Unsigned 16-bit integer    |

#### View Group Response Command [0x01]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Status                     |8-bit enumeration          |
|Group ID                   |Unsigned 16-bit integer    |
|Group Name                 |Character string           |

#### Get Group Membership Response Command [0x02]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Capacity                   |Unsigned 8-bit integer     |
|Group count                |Unsigned 8-bit integer     |
|Group list                 |N X Unsigned 16-bit integer|

#### Remove Group Response Command [0x03]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Status                     |8-bit enumeration          |
|Group ID                   |Unsigned 16-bit integer    |

## Scenes [0x0005]
The scenes cluster provides attributes and commands for setting up and recalling
scenes. Each scene corresponds to a set of stored values of specified attributes for
one or more clusters on the same end point as the scenes cluster.

In most cases scenes are associated with a particular group ID. Scenes may also
exist without a group, in which case the value 0x0000 replaces the group ID. Note
that extra care is required in these cases to avoid a scene ID collision, and that
commands related to scenes without a group may only be unicast, i.e.: they may
not be multicast or broadcast.

### Attributes

|Id     |Name                 |Type                       |Access     |Implement |Reporting |
|-------|---------------------|---------------------------|-----------|----------|----------|
|0x0000 |SceneCount           |Unsigned 8-bit integer     |Read only  |Mandatory |          |
|0x0001 |CurrentScene         |Unsigned 8-bit integer     |Read only  |Mandatory |          |
|0x0002 |CurrentGroup         |Unsigned 16-bit integer    |Read only  |Mandatory |          |
|0x0003 |SceneValid           |Boolean                    |Read only  |Mandatory |          |
|0x0004 |NameSupport          |8-bit bitmap               |Read only  |Mandatory |          |
|0x0005 |LastConfiguredBy     |IEEE Address               |Read only  |Optional  |          |

#### SceneCount Attribute
The SceneCount attribute specifies the number of scenes currently in the device's
scene table.

#### CurrentScene Attribute
The CurrentScene attribute holds the Scene ID of the scene last invoked.

#### CurrentGroup Attribute
The CurrentGroup attribute holds the Group ID of the scene last invoked, or
0x0000 if the scene last invoked is not associated with a group.

#### SceneValid Attribute
The SceneValid attribute indicates whether the state of the device corresponds to
that associated with the CurrentScene and CurrentGroup attributes. TRUE
indicates that these attributes are valid, FALSE indicates that they are not valid.

Before a scene has been stored or recalled, this attribute is set to FALSE. After a
successful Store Scene or Recall Scene command it is set to TRUE. If, after a
scene is stored or recalled, the state of the device is modified, this attribute is set to
FALSE. 

#### NameSupport Attribute
The most significant bit of the NameSupport attribute indicates whether or not
scene names are supported. A value of 1 indicates that they are supported, and a
value of 0 indicates that they are not supported.

#### LastConfiguredBy Attribute
The LastConfiguredBy attribute is 64-bits in length and specifies the IEEE address
of the device that last configured the scene table.

The value 0xffffffffffffffff indicates that the device has not been configured, or
that the address of the device that last configured the scenes cluster is not known.


### Received

#### Add Scene Command [0x00]
The Add Scene command shall be addressed to a single device (not a group).

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Group ID                   |Unsigned 16-bit integer    |
|Scene ID                   |Unsigned 8-bit integer     |
|Transition time            |Unsigned 16-bit integer    |
|Scene Name                 |Character string           |
|Extension field sets       |N X Extension field set    |

#### View Scene Command [0x01]
The View Scene command shall be addressed to a single device (not a group).

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Group ID                   |Unsigned 16-bit integer    |
|Scene ID                   |Unsigned 8-bit integer     |

#### Remove Scene Command [0x02]
The Remove All Scenes may be addressed to a single device or to a group.

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Group ID                   |Unsigned 16-bit integer    |
|Scene ID                   |Unsigned 8-bit integer     |

#### Remove All Scenes Command [0x03]
The Remove All Scenes may be addressed to a single device or to a group.

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Group ID                   |Unsigned 16-bit integer    |

#### Store Scene Command [0x04]
The Store Scene command may be addressed to a single device or to a group.

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Group ID                   |Unsigned 16-bit integer    |
|Scene ID                   |Unsigned 8-bit integer     |

#### Recall Scene Command [0x05]
The Recall Scene command may be addressed to a single device or to a group. 

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Group ID                   |Unsigned 16-bit integer    |
|Scene ID                   |Unsigned 8-bit integer     |

#### Get Scene Membership Command [0x06]
The Get Scene Membership command can be used to find an unused scene
number within the group when no commissioning tool is in the network, or for a
commissioning tool to get used scenes for a group on a single device or on all
devices in the group.

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Group ID                   |Unsigned 16-bit integer    |

### Generated

#### Add Scene Response Command [0x00]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Status                     |8-bit enumeration          |
|Group ID                   |Unsigned 16-bit integer    |
|Scene ID                   |Unsigned 8-bit integer     |

#### View Scene Response Command [0x01]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Status                     |8-bit enumeration          |
|Group ID                   |Unsigned 16-bit integer    |
|Scene ID                   |Unsigned 8-bit integer     |
|Transition time            |Unsigned 16-bit integer    |
|Scene Name                 |Character string           |
|Extension field sets       |N X Extension field set    |

#### Remove Scene Response Command [0x02]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Status                     |8-bit enumeration          |
|Group ID                   |Unsigned 16-bit integer    |
|Scene ID                   |Unsigned 8-bit integer     |

#### Remove All Scenes Response Command [0x03]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Status                     |8-bit enumeration          |
|Group ID                   |Unsigned 16-bit integer    |

#### Store Scene Response Command [0x04]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Status                     |8-bit enumeration          |
|Group ID                   |Unsigned 16-bit integer    |
|Scene ID                   |Unsigned 8-bit integer     |

#### Get Scene Membership Response Command [0x05]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Status                     |8-bit enumeration          |
|Capacity                   |Unsigned 8-bit integer     |
|Group ID                   |Unsigned 16-bit integer    |
|Scene count                |Unsigned 8-bit integer     |
|Scene list                 |N x Unsigned 8-bit integer |

## On/Off [0x0006]
Attributes and commands for switching devices between ‘On’ and ‘Off’ states. 

### Attributes

|Id     |Name                 |Type                       |Access     |Implement |Reporting |
|-------|---------------------|---------------------------|-----------|----------|----------|
|0x0000 |OnOff                |Boolean                    |Read Only  |Mandatory |Mandatory |
|0x4000 |GlobalSceneControl   |Boolean                    |Read       |          |          |
|0x4001 |OffTime              |Unsigned 16-bit integer    |Read/Write |          |          |
|0x4002 |OffWaitTime          |Unsigned 16-bit integer    |Read/Write |          |          |


#### OnOff Attribute
The OnOff attribute has the following values: 0 = Off, 1 = On

#### GlobalSceneControl Attribute
In order to support the use case where the user gets back the last setting of the devices (e.g. level settings for lamps), a global scene is introduced which is stored when the devices are turned off and recalled when the devices are turned on. The global scene is defined as the scene that is stored with group identifier 0 and scene identifier 0.

The GlobalSceneControl attribute is defined in order to prevent a second off command storing the all-devices-off situation as a global scene, and to prevent a second on command destroying the current settings by going back to the global scene.

The GlobalSceneControl attribute SHALL be set to TRUE after the reception of a command which causes the OnOff attribute to be set to TRUE, such as a standard On command, a Move to level (with on/off) command, a Recall scene command or a On with recall global scene command.

The GlobalSceneControl attribute is set to FALSE after reception of a Off with effect command.

#### OnTime Attribute
The OnTime attribute specifies the length of time (in 1/10ths second) that the “on” state SHALL be maintained before automatically transitioning to the “off” state when using the On with timed off command. If this attribute is set to 0x0000 or 0xffff, the device SHALL remain in its current state.

#### OffWaitTime Attribute
The OffWaitTime attribute specifies the length of time (in 1/10ths second) that the “off” state SHALL be guarded to prevent an on command turning the device back to its “on” state (e.g., when leaving a room, the lights are turned off but an occupancy sensor detects the leaving person and attempts to turn the lights back on). If this attribute is set to 0x0000, the device SHALL remain in its current state.

### Received

#### Off Command [0x00]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|

#### On Command [0x01]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|

#### Toggle Command [0x02]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|

#### Off With Effect Command [0x40]
The Off With Effect command allows devices to be turned off using enhanced ways of fading.

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Effect Identifier          |Unsigned 8-bit integer     |
|Effect Variant             |Unsigned 8-bit integer     |

##### Effect Identifier Field
The Effect Identifier field is 8-bits in length and specifies the fading effect to use when
switching the device off.

|Id     |Name                      |
|-------|--------------------------|
|0x0000 |Delayed All Off           |
|0x0001 |Dying Light               |

##### Effect Variant Field
The Effect Variant field is 8-bits in length and is used to indicate which variant of the
effect, indicated in the Effect Identifier field, SHOULD be triggered. If a device does not
support the given variant, it SHALL use the default variant. This field is dependent on the
value of the Effect Identifier field.


#### On With Recall Global Scene Command [0x41]

The On With Recall Global Scene command allows the recall of the settings when the device was turned off.

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|

#### On With Timed Off Command [0x42]

The On With Timed Off command allows devices to be turned on for a specific duration
with a guarded off duration so that SHOULD the device be subsequently switched off,
further On With Timed Off commands, received during this time, are prevented from
turning the devices back on. Note that the device can be periodically re-kicked by
subsequent On With Timed Off commands, e.g., from an on/off sensor.

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|On Off Control             |Unsigned 8-bit integer     |
|On Time                    |Unsigned 16-bit integer    |
|Off Wait Time              |Unsigned 16-bit integer    |

##### On Off Control Field
The On/Off Control field is 8-bits in length and contains information on how the device is to be operated.

##### On Time Field
The On Time field is 16 bits in length and specifies the length of time (in 1/10ths second)
that the device is to remain  “on”,  i.e., with its OnOffattribute equal to 0x01,
before automatically turning “off”. This field SHALL be specified in the range 0x0000–0xfffe.

##### Off Time Wait Field
The Off Wait Time field is 16 bits in length and specifies the length of time (in 1/10ths second)
that the device SHALL remain “off”, i.e., with its OnOffattribute equal to 0x00, and guarded to
prevent an on command turning the device back “on”. This field SHALL be specified in the range 0x0000–0xfffe.

### Generated

No cluster specific commands.

## On/off Switch Configuration [0x0007]
Attributes and commands for configuring On/Off switching devices

### Attributes

|Id     |Name                 |Type                       |Access     |Implement |Reporting |
|-------|---------------------|---------------------------|-----------|----------|----------|
|0x0000 |SwitchType           |8-bit enumeration          |Read Only  |Mandatory |          |
|0x0010 |SwitchActions        |8-bit enumeration          |Read Write |Mandatory |          |

#### SwitchType Attribute
The SwitchTypeattribute  specifies  the  basic  functionality  of  the  On/Off  switching  device.

|Id     |Name                      |
|-------|--------------------------|
|0x0000 |Toggle                    |
|0x0001 |Momentary                 |
|0x0002 |Multifunction             |

#### SwitchActions Attribute

The SwitchActions attribute is 8 bits in length and specifies the commands of the On/Off cluster
to be generated when the switch moves between its two states

|Id     |Name                      |
|-------|--------------------------|
|0x0000 |On                        |
|0x0001 |Off                       |
|0x0002 |Toggle                    |


### Received

No cluster specific commands.

### Generated

No cluster specific commands.

## Level Control [0x0008]
This cluster provides an interface for controlling a characteristic of a device that
can be set to a level, for example the brightness of a light, the degree of closure of
a door, or the power output of a heater.


### Attributes

|Id     |Name                 |Type                       |Access     |Implement |Reporting |
|-------|---------------------|---------------------------|-----------|----------|----------|
|0x0000 |CurrentLevel         |Unsigned 8-bit integer     |Read Only  |Mandatory |Mandatory |
|0x0001 |RemainingTime        |Unsigned 16-bit integer    |Read Only  |Optional  |          |
|0x0010 |OnOffTransitionTime  |Unsigned 16-bit integer    |Read/Write |Optional  |          |
|0x0011 |OnLevel              |Unsigned 8-bit integer     |Read/Write |Optional  |          |
|0x0012 |OnTransitionTime     |Unsigned 16-bit integer    |Read/Write |Optional  |          |
|0x0013 |OffTransitionTime    |Unsigned 16-bit integer    |Read/Write |Optional  |          |
|0x0014 |DefaultMoveRate      |Unsigned 16-bit integer    |Read/Write |Optional  |          |

#### CurrentLevel Attribute
The CurrentLevel attribute represents the current level of this device. The
meaning of 'level' is device dependent. Value is between 0 and 254.

#### RemainingTime Attribute
The RemainingTime attribute represents the time remaining until the current
command is complete - it is specified in 1/10ths of a second. 

#### OnOffTransitionTime Attribute
The OnOffTransitionTime attribute represents the time taken to move to or from
the target level when On of Off commands are received by an On/Off cluster on
the same endpoint. It is specified in 1/10ths of a second.

The actual time taken should be as close to OnOffTransitionTime as the device is
able. N.B. If the device is not able to move at a variable rate, the
OnOffTransitionTime attribute should not be implemented. 

#### OnLevel Attribute
The OnLevel attribute determines the value that the CurrentLevel attribute is set to
when the OnOff attribute of an On/Off cluster on the same endpoint is set to On. If
the OnLevel attribute is not implemented, or is set to 0xff, it has no effect. 

#### OnTransitionTime Attribute
The OnTransitionTime attribute represents the time taken to move the current level from the
minimum level to the maximum level when an On command is received by an On/Off cluster on
the same endpoint.  It is specified in 10ths of a second.  If this command is not implemented,
or contains a value of 0xffff, the OnOffTransitionTime will be used instead.

#### OffTransitionTime Attribute
The OffTransitionTime attribute represents the time taken to move the current level from the
maximum level to the minimum level when an Off command is received by an On/Off cluster on
the same endpoint.  It is specified in 10ths of a second.  If this command is not implemented,
or contains a value of 0xffff, the OnOffTransitionTime will be used instead.

#### DefaultMoveRate Attribute
The DefaultMoveRate attribute determines the movement rate, in units per second, when a Move
command is received with a Rate parameter of 0xFF.

### Received

#### Move to Level Command [0x00]
On receipt of this command, a device SHALL move from its current level to the 
value given in the Level field. The meaning of ‘level’ is device dependent –e.g.,
for a light it MAY mean brightness level.The movement SHALL be as continuous as
technically practical, i.e., not a step function, and the time taken to move to
the new level SHALL be equal to the value of the Transition time field, in tenths
of a second, or as close to this as the device is able.If the Transition time field
takes the value 0xffff then the time taken to move to the new level SHALL instead
be determined by the OnOffTransitionTimeattribute. If OnOffTransitionTime, which is
an optional attribute, is not present, the device SHALL move to its new level as fast
as it is able.

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Level                      |Unsigned 8-bit integer     |
|Transition time            |Unsigned 16-bit integer    |

#### Move Command [0x01]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Move mode                  |8-bit enumeration          |
|Rate                       |Unsigned 8-bit integer     |

#### Step Command [0x02]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Step mode                  |8-bit enumeration          |
|Step size                  |Unsigned 8-bit integer     |
|Transition time            |Unsigned 16-bit integer    |

#### Stop Command [0x03]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|

#### Move to Level (with On/Off) Command [0x04]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Level                      |Unsigned 8-bit integer     |
|Transition time            |Unsigned 16-bit integer    |

#### Move (with On/Off) Command [0x05]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Move mode                  |8-bit enumeration          |
|Rate                       |Unsigned 8-bit integer     |

#### Step (with On/Off) Command [0x06]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Step mode                  |8-bit enumeration          |
|Step size                  |Unsigned 8-bit integer     |
|Transition time            |Unsigned 16-bit integer    |

#### Stop 2 Command [0x07]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|

### Generated

No cluster specific commands.

## Alarms [0x0009]
Attributes and commands for sending alarm notifications and configuring alarm
functionality.

Alarm conditions and their respective alarm codes are described in individual
clusters, along with an alarm mask field. Where not masked, alarm notifications
are reported to subscribed targets using binding.

Where an alarm table is implemented, all alarms, masked or otherwise, are
recorded and may be retrieved on demand.

Alarms may either reset automatically when the conditions that cause are no
longer active, or may need to be explicitly reset.

### Attributes
|Id     |Name                 |Type                       |Access     |Implement |Reporting |
|-------|---------------------|---------------------------|-----------|----------|----------|
|0x0000 |AlarmCount           |Unsigned 16-bit integer    |Read Only  |Optional  |          |

#### AlarmCount Attribute
The AlarmCount attribute is 16-bits in length and specifies the number of entries
currently in the alarm table. This attribute shall be specified in the range 0x00 to
the maximum defined in the profile using this cluster.

If alarm logging is not implemented this attribute shall always take the value
0x00.

### Received

#### Reset Alarm Command [0x00]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Alarm code                 |8-bit enumeration          |
|Cluster identifier         |Unsigned 16-bit integer    |

#### Reset All Alarms Command [0x01]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|

#### Get Alarm Command [0x02]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|

#### Reset Alarm Log Command [0x03]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|

### Generated

#### Alarm Command [0x00]
The alarm command signals an alarm situation on the sending device.

An alarm command is generated when a  cluster  which has alarm functionality detects an alarm
condition, e.g., an attribute has taken on a value that is outside a ‘safe’ range. The details
are given by individual cluster specifications.

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Alarm code                 |8-bit enumeration          |
|Cluster identifier         |Unsigned 16-bit integer    |

#### Get Alarm Response Command [0x01]
If there is at least one alarm record in the alarm table then the status field is set to SUCCESS.
The alarm code, cluster identifier and time stamp fields SHALL all be present and SHALL take their
values from the item in the alarm table that they are reporting.If there  are  no more  alarms logged
in the  alarm table  then the  status field is set  to NOT_FOUND  and the alarm code, cluster
identifier and time stamp fields SHALL be omitted.

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Status                     |8-bit enumeration          |
|Alarm code                 |8-bit enumeration          |
|Cluster identifier         |Unsigned 16-bit integer    |
|Timestamp                  |Unsigned 32-bit integer    |

## Time [0x000a]

### Attributes
|Id     |Name                 |Type                       |Access     |Implement |Reporting |
|-------|---------------------|---------------------------|-----------|----------|----------|
|0x0000 |Time                 |UTCTime                    |Read/Write |Mandatory |          |
|0x0001 |TimeStatus           |Unsigned 16-bit integer    |Read/Write |Optional  |          |
|0x0002 |TimeZone             |Signed 32-bit integer      |Read/Write |Optional  |          |
|0x0003 |DstStart             |Unsigned 32-bit integer    |Read/Write |Optional  |          |
|0x0004 |DstEnd               |Unsigned 32-bit integer    |Read/Write |Optional  |          |
|0x0005 |DstShift             |Signed 32-bit integer      |Read/Write |Optional  |          |
|0x0006 |StandardTime         |Signed 32-bit integer      |Read Only  |Optional  |          |
|0x0007 |LocalTime            |Signed 32-bit integer      |Read Only  |Optional  |          |

#### Time Attribute
The Time attribute is 32-bits in length and holds the time value of a real time
clock. This attribute has data type UTCTime, but note that it may not actually be
synchronised to UTC - see discussion of the TimeStatus attribute below.

If the Master bit of the TimeStatus attribute has a value of 0, writing to this
attribute shall set the real time clock to the written value, otherwise it cannot be
written. The value 0xffffffff indicates an invalid time.

#### TimeStatus Attribute
The TimeStatus attribute holds a number of bit fields.

#### TimeZone Attribute
The TimeZone attribute indicates the local time zone, as a signed offset in seconds
from the Time attribute value. The value 0xffffffff indicates an invalid time zone. 

#### DstStart Attribute
The DstStart attribute indicates the DST start time in seconds. The value 0xffffffff
indicates an invalid DST start time.

#### DstEnd Attribute
The DstEnd attribute indicates the DST end time in seconds. The value 0xffffffff
indicates an invalid DST end time.

Note that the three attributes DstStart, DstEnd and DstShift are optional, but if any
one of them is implemented the other two must also be implemented.
Note that this attribute should be set to a new value once every year.

Note that this attribute should be set to a new value once every year, and should be
written synchronously with the DstStart attribute.

#### DstEnd Attribute
The DstEnd attribute indicates the DST end time in seconds. The value 0xffffffff
indicates an invalid DST end time.

Note that this attribute should be set to a new value once every year, and should be
written synchronously with the DstStart attribute

#### DstShift Attribute
The DstShift attribute represents a signed offset in seconds from the standard time,
to be applied between the times DstStart and DstEnd to calculate the Local Time.
The value 0xffffffff indicates an invalid DST shift.

The range of this attribute is +/- one day. Note that the actual range of DST values
employed by countries is much smaller than this, so the manufacturer has the
option to impose a smaller range. 

#### StandardTime Attribute
A device may derive the time by reading the Time and TimeZone attributes
and adding them together. If implemented however, the optional StandardTime
attribute indicates this time directly. The value 0xffffffff indicates an invalid
Standard Time. 

#### LocalTime Attribute
A device may derive the time by reading the Time, TimeZone, DstStart, DstEnd
and DstShift attributes and performing the calculation. If implemented however,
the optional LocalTime attribute indicates this time directly. The value 0xffffffff
indicates an invalid Local Time.

### Received

No cluster specific commands.

### Generated

## RSSI Location [0x000b]

### Attributes
|Id     |Name                  |Type                       |Access     |Implement |Reporting |
|-------|----------------------|---------------------------|-----------|----------|----------|
|0x0000 |LocationType          |8-bit Data                 |Read only  |Mandatory |          |
|0x0001 |LocationMethod        |8-bit Enumeration          |Read only  |Mandatory |          |
|0x0002 |LocationAge           |Unsigned 16-bit Integer    |Read only  |Optional  |          |
|0x0003 |QualityMeasure        |Unsigned 8-bit Integer     |Read only  |Optional  |          |
|0x0004 |NumberOfDevices       |Unsigned 8-bit Integer     |Read only  |Optional  |          |
|0x0010 |Coordinate1           |Signed 16-bit integer      |Read/Write |Mandatory |          |
|0x0011 |Coordinate2           |Signed 16-bit integer      |Read/Write |Mandatory |          |
|0x0012 |Coordinate3           |Signed 16-bit integer      |Read/Write |Optional  |          |
|0x0013 |Power                 |Signed 16-bit integer      |Read/Write |Mandatory |          |
|0x0014 |PathLossExponent      |Signed 16-bit integer      |Read/Write |Mandatory |          |
|0x0015 |ReportingPeriod       |Signed 16-bit integer      |Read/Write |Optional  |          |
|0x0016 |CalculationPeriod     |Signed 16-bit integer      |Read/Write |Optional  |          |
|0x0017 |NumberRSSIMeasurements|Signed 16-bit integer      |Read/Write |Optional  |          |

#### LocationType Attribute
The LocationType attribute is 8 bits long and is divided into bit fields.

#### LocationMethod Attribute

#### LocationAge Attribute
The LocationAge attribute indicates the amount of time, measured in seconds, that
has transpired since the location information was last calculated. This attribute is
not valid if the Absolute bit of the LocationType attribute is set to one. 

#### QualityMeasure Attribute
The QualityMeasure attribute is a measure of confidence in the corresponding
location information. The higher the value, the more confident the transmitting
device is in the location information. A value of 0x64 indicates complete (100%)
confidence and a value of 0x00 indicates zero confidence. (Note: no fixed
confidence metric is mandated – the metric may be application and manufacturer
dependent).

This field is not valid if the Absolute bit of the LocationType attribute is set to one. 

#### NumberOfDevices Attribute
The NumberOfDevices attribute is the number of devices whose location data
were used to calculate the last location value. This attribute is related to the
QualityMeasure attribute. 

#### Coordinate1 Attributes
The Coordinate1, Coordinate2 and Coordinate3 attributes are signed 16-bit
integers, and represent orthogonal linear coordinates x, y, z in meters as follows.

x = Coordinate1 / 10, y = Coordinate2 / 10, z = Coordinate3 / 10

The range of x is -3276.7 to 3276.7 meters, corresponding to Coordinate1
between 0x8001 and 0x7fff. The same range applies to y and z. A value of
0x8000 for any of the coordinates indicates that the coordinate is unknown.

#### Coordinate2 Attributes
The Coordinate1, Coordinate2 and Coordinate3 attributes are signed 16-bit
integers, and represent orthogonal linear coordinates x, y, z in meters as follows.

x = Coordinate1 / 10, y = Coordinate2 / 10, z = Coordinate3 / 10

The range of x is -3276.7 to 3276.7 meters, corresponding to Coordinate1
between 0x8001 and 0x7fff. The same range applies to y and z. A value of
0x8000 for any of the coordinates indicates that the coordinate is unknown.

#### Coordinate3 Attributes
The Coordinate1, Coordinate2 and Coordinate3 attributes are signed 16-bit
integers, and represent orthogonal linear coordinates x, y, z in meters as follows.

x = Coordinate1 / 10, y = Coordinate2 / 10, z = Coordinate3 / 10

The range of x is -3276.7 to 3276.7 meters, corresponding to Coordinate1
between 0x8001 and 0x7fff. The same range applies to y and z. A value of
0x8000 for any of the coordinates indicates that the coordinate is unknown.

#### Power Attribute
The Power attribute specifies the value of the average power P0, measured in
dBm, received at a reference distance of one meter from the transmitter.

P0 = Power / 100

A value of 0x8000 indicates that Power is unknown.

#### PathLossExponent Attribute
The PathLossExponent attribute specifies the value of the Path Loss Exponent n,
an exponent that describes the rate at which the signal power decays with
increasing distance from the transmitter.

n = PathLossExponent / 100

A value of 0xffff indicates that PathLossExponent is unknown.

#### ReportingPeriod Attribute
The ReportingPeriod attribute specifies the time in seconds between successive
reports of the device's location by means of the Location Data Notification
command. The minimum value this attribute can take is specified by the profile in
use. If ReportingPeriod is zero, the device does not automatically report its
location. Note that location information can always be polled at any time.

#### CalculationPeriod Attribute
The CalculationPeriod attribute specifies the time in seconds between successive
calculations of the device's location. If CalculationPeriod is less than the
physically possible minimum period that the calculation can be performed, the
calculation will be repeated as frequently as possible.

#### NumberRSSIMeasurements Attribute
The NumberRSSIMeasurements attribute specifies the number of RSSI
measurements to be used to generate one location estimate. The measurements are
averaged to improve accuracy. NumberRSSIMeasurements must be greater than or
equal to 1.

### Received

#### Set Absolute Location Command [0x00]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Coordinate 1               |Signed 16-bit integer      |
|Coordinate 2               |Signed 16-bit integer      |
|Coordinate 3               |Signed 16-bit integer      |
|Power                      |Signed 16-bit integer      |
|Path Loss Exponent         |Unsigned 16-bit integer    |

#### Set Device Configuration Command [0x01]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Power                      |Signed 16-bit integer      |
|Path Loss Exponent         |Unsigned 16-bit integer    |
|Calculation Period         |Unsigned 16-bit integer    |
|Number RSSI Measurements   |Unsigned 8-bit integer     |
|Reporting Period           |Unsigned 16-bit integer    |

#### Get Device Configuration Command [0x02]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Target Address             |IEEE Address               |

#### Get Location Data Command [0x03]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Header                     |8-bit bitmap               |
|Number Responses           |Unsigned 8-bit integer     |
|Target Address             |IEEE address               |

#### RSSI Response Command [0x04]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Replying Device            |IEEE address               |
|Coordinate 1               |Signed 16-bit integer      |
|Coordinate 2               |Signed 16-bit integer      |
|Coordinate 3               |Signed 16-bit integer      |
|RSSI                       |Signed 8-bit integer       |
|Number RSSI Measurements   |Unsigned 8-bit Integer     |

#### Send Pings Command [0x05]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Target Address             |IEEE address               |
|Number RSSI Measurements   |Unsigned 8-bit Integer     |
|Calculation Period         |Unsigned 16-bit integer    |

#### Anchor Node Announce Command [0x06]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Anchor Node Address        |IEEE address               |
|Coordinate 1               |Signed 16-bit integer      |
|Coordinate 2               |Signed 16-bit integer      |
|Coordinate 3               |Signed 16-bit integer      |

### Generated

#### Device Configuration Response Command [0x00]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Status                     |8-bit enumeration          |
|Power                      |Signed 16-bit integer      |
|Path Loss Exponent         |Unsigned 16-bit integer    |
|Calculation Period         |Unsigned 16-bit integer    |
|Number RSSI Measurements   |Unsigned 8-bit integer     |
|Reporting Period           |Unsigned 16-bit integer    |

#### Location Data Response Command [0x01]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Status                     |8-bit enumeration          |
|Location Type              |8-bit Data                 |
|Coordinate 1               |Signed 16-bit integer      |
|Coordinate 2               |Signed 16-bit integer      |
|Coordinate 3               |Signed 16-bit integer      |
|Power                      |Signed 16-bit integer      |
|Path Loss Exponent         |Unsigned 16-bit integer    |
|Location Method            |8-bit enumeration          |
|Quality Measure            |Unsigned 8-bit integer     |
|Location Age               |Unsigned 16-bit integer    |

#### Location Data Notification Command [0x02]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Location Type              |8-bit Data                 |
|Coordinate 1               |Signed 16-bit integer      |
|Coordinate 2               |Signed 16-bit integer      |
|Coordinate 3               |Signed 16-bit integer      |
|Power                      |Signed 16-bit integer      |
|Path Loss Exponent         |Unsigned 16-bit integer    |
|Location Method            |8-bit enumeration          |
|Quality Measure            |Unsigned 8-bit integer     |
|Location Age               |Unsigned 16-bit integer    |

#### Compact Location Data Notification Command [0x03]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|

#### RSSI Ping Command [0x04]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Location Type              |8-bit Data                 |

#### RSSI Request Command [0x05]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|

#### Report RSSI Measurements Command [0x06]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Reporting Address          |IEEE address               |
|Number of Neighbors        |Unsigned 8-bit integer     |
|Neighbors Information      |N X Neighbors information  |

#### Request Own Location Command [0x07]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Requesting Address         |IEEE address               |

## Analog Input (Basic) [0x000c]

### Received

No cluster specific commands.

### Generated

No cluster specific commands.

## Analog Output (Basic) [0x000d]

### Received

No cluster specific commands.

### Generated

No cluster specific commands.

## Analog Value (Basic) [0x000e]

### Received

No cluster specific commands.

### Generated

No cluster specific commands.

## Binary Input (Basic) [0x000f]

### Received

No cluster specific commands.

### Generated

No cluster specific commands.

## Binary Output (Basic) [0x0010]

### Received

No cluster specific commands.

### Generated

No cluster specific commands.

## Binary Value (Basic) [0x0011]

### Received

No cluster specific commands.

### Generated

## Multistate Input (Basic) [0x0012]

The Multistate Input (Basic) cluster provides an interface for reading the value of a
multistate measurement and accessing various characteristics of that measurement. The
cluster is typically used to implement a sensor that measures a physical quantity that
can take on one of a number of discrete states.

### Attributes
|Id     |Name                  |Type                       |Access     |Implement |Reporting |
|-------|----------------------|---------------------------|-----------|----------|----------|
|0x000E |StateText             |Character string           |Read/Write |Optional  |          |
|0x001C |Description           |Character string           |Read/Write |Optional  |          |
|0x004A |NumberOfStates        |Unsigned 16-bit Integer    |Read/Write |Mandatory |          |
|0x0051 |OutOfService          |Boolean                    |Read/Write |Mandatory |          |
|0x0055 |PresentValue          |Unsigned 16-bit Integer    |Read/Write |Mandatory |          |
|0x0067 |Reliability           |8-bit enumeration          |Read/Write |Optional  |          |
|0x006F |StatusFlags           |8-bit bitmap               |Read only  |Mandatory |          |
|0x0100 |ApplicationType       |Signed 32-bit integer      |Read only  |Optional  |          |

#### StateText Attribute
This  attribute, of type Array of Character strings, holds descriptions of all possible
states of a multistate PresentValue.  The number of descriptions matches the number of states
defined in the NumberOfStates property. The PresentValue, interpreted as an integer, serves as
an index into the array. If the size of this array is changed, the NumberOfStates property SHALL
also be changed to the same value. The character set used SHALL be ASCII, and the attribute
SHALL contain a maximum of 16 characters, which SHALL be printable but are otherwise unrestricted.

#### Description Attribute
The Description attribute, of type Character string, MAY be used to hold a description
of the usage of the input, output or value, as appropriate to the cluster. The character
set used SHALL be ASCII, and the attribute SHALL contain a maximum of 16 characters,
which SHALL be printable but are otherwise unrestricted.

#### NumberOfStates Attribute
This attribute, of type Unsigned 16-bit integer, defines the number of states that a multistate
PresentValue MAY have. The NumberOfStates property SHALL always have a value greater than zero. 
If the value of this property is changed, the size of the StateText array, if present, SHALL also
be changed to the same value. The states are numbered consecutively, starting with 1.

#### OutOfService Attribute
The OutOfService attribute, of type Boolean, indicates whether (TRUE) or not (FALSE) the physical
input, output or value that the cluster represents is not in service. For an Input cluster, when
OutOfService is TRUE the PresentValue attribute is decoupled from the physical input and  will
not track changes to the  physical input. For an Output cluster, when OutOfService is TRUE the
PresentValue attribute is decoupled from the physical output, so changes to PresentValue will not
affect the physical output. For a Value cluster, when OutOfService is TRUE the PresentValue attribute
MAY be written to freely by software local to the device that the cluster resides on.

#### PresentValue Attribute
The PresentValue attribute indicates the current value of the input, output or
value, as appropriate  for the cluster. For Analog clusters it is of type single precision, for Binary
clusters it is of type  Boolean, and for multistate clusters it is of type Unsigned 16-bit integer. The
PresentValue attribute of an input cluster SHALL be writable when OutOfService is TRUE. When the PriorityArray
attribute is implemented, writing to PresentValue SHALL be equivalent to writing to element 16 of PriorityArray,
i.e., with a priority of 16.

#### Reliability Attribute
The Reliability attribute, of type 8-bit enumeration, provides an indication of whether
the PresentValueor the operation of the physical input, output or value in question (as
appropriate for the cluster) is “reliable” as far as can be determined and, if not, why
not. The Reliability attribute MAY have any of the following values:

NO-FAULT-DETECTED (0)
OVER-RANGE (2)
UNDER-RANGE (3)
OPEN-LOOP (4)
SHORTED-LOOP (5)
UNRELIABLE-OTHER (7)
PROCESS-ERROR (8)
MULTI-STATE-FAULT (9)
CONFIGURATION-ERROR (10)

|Id     |Name                      |
|-------|--------------------------|
|0x0000 |NO-FAULT-DETECTED         |
|0x0002 |OVER-RANGE                |
|0x0003 |UNDER-RANGE               |
|0x0004 |OPEN-LOOP                 |
|0x0005 |SHORTED-LOOP              |
|0x0007 |UNRELIABLE-OTHER          |
|0x0008 |PROCESS-ERROR             |
|0x0009 |MULTI-STATE-FAULT         |
|0x000A |CONFIGURATION-ERROR       |

#### StatusFlags Attribute
This attribute, of type bitmap, represents four Boolean flags that indicate the general “health”
of the analog sensor. Three of the flags are associated with the values of other optional attributes
of this cluster. A more detailed status could be determined by reading the optional attributes (if
supported) that are linked to these flags. The relationship between individual flags is not defined. 

The four flags are Bit 0 = IN_ALARM, Bit 1 = FAULT, Bit 2 = OVERRIDDEN, Bit 3 = OUT OF SERVICE

where:

IN_ALARM -Logical FALSE (0) if the EventStateattribute has a value of NORMAL, otherwise logical TRUE (1).
This bit is always 0 unless the cluster implementing the EventState attribute is implemented on the same
endpoint.

FAULT -Logical TRUE (1) if the Reliability attribute is present and does not have a value of NO FAULT DETECTED,
otherwise logical FALSE (0).

OVERRIDDEN -Logical TRUE (1) if the cluster has been overridden by some  mechanism local to the device. 
Otherwise, the value is logical FALSE (0). In this context, for an input cluster, “overridden” is taken
to mean that the PresentValue and Reliability(optional) attributes are no longer tracking changes to the
physical input. For an Output cluster, “overridden” is taken to mean that the physical output is no longer
tracking changes to the PresentValue attribute and the Reliability attribute is no longer a reflection of
the physical output. For a Value cluster, “overridden” is taken to mean that the PresentValue attribute is
not writeable.

OUT OF SERVICE -Logical TRUE (1) if the OutOfService attribute has a value of TRUE, otherwise
logical FALSE (0).

|Id     |Name                      |
|-------|--------------------------|
|0x0001 |IN_ALARM                  |
|0x0002 |FAULT                     |
|0x0004 |OVERRIDDEN                |
|0x0008 |OUT OF SERVICE            |


#### ApplicationType Attribute
The ApplicationType attribute is an unsigned 32 bit integer that indicates the specific
application usage for this cluster. (Note: This attribute has no BACnet equivalent).
ApplicationType is subdivided into Group, Type and an Index number, as follows.

Group = Bits 24 -31 An indication of the cluster this attribute is part of.

Type = Bits 16 -23 For Analog clusters, the physical quantity that the Present Value attribute
of the cluster represents. For Binary and Multistate clusters, the application usage domain.

Index = Bits 0 -15The specific application usage of the cluster. 

### Received

No cluster specific commands.

### Generated

No cluster specific commands.

## Multistate Output (Basic) [0x0013]
The Multistate Output (Basic) cluster provides an interface for setting the value of an output
that can take one of a number of discrete values, and accessing characteristics of that value. 

### Attributes
|Id     |Name                  |Type                       |Access     |Implement |Reporting |
|-------|----------------------|---------------------------|-----------|----------|----------|
|0x000E |StateText             |Character string           |Read/Write |Optional  |          |
|0x001C |Description           |Character string           |Read/Write |Optional  |          |
|0x004A |NumberOfStates        |Unsigned 16-bit Integer    |Read/Write |Mandatory |          |
|0x0051 |OutOfService          |Boolean                    |Read/Write |Mandatory |          |
|0x0055 |PresentValue          |Unsigned 16-bit Integer    |Read/Write |Mandatory |          |
|0x0067 |Reliability           |8-bit enumeration          |Read/Write |Optional  |          |
|0x0068 |RelinquishDefault     |Unsigned 16-bit Integer    |Read/Write |Optional  |          |
|0x006F |StatusFlags           |8-bit bitmap               |Read only  |Mandatory |          |
|0x0100 |ApplicationType       |Signed 32-bit integer      |Read only  |Optional  |          |

#### StateText Attribute
This  attribute, of type Array of Character strings, holds descriptions of all possible
states of a multistate PresentValue.  The number of descriptions matches the number of states
defined in the NumberOfStates property. The PresentValue, interpreted as an integer, serves as
an index into the array. If the size of this array is changed, the NumberOfStates property SHALL
also be changed to the same value. The character set used SHALL be ASCII, and the attribute
SHALL contain a maximum of 16 characters, which SHALL be printable but are otherwise unrestricted.

#### Description Attribute
The Description attribute, of type Character string, MAY be used to hold a description
of the usage of the input, output or value, as appropriate to the cluster. The character
set used SHALL be ASCII, and the attribute SHALL contain a maximum of 16 characters,
which SHALL be printable but are otherwise unrestricted.

#### NumberOfStates Attribute
This attribute, of type Unsigned 16-bit integer, defines the number of states that a multistate
PresentValue MAY have. The NumberOfStates property SHALL always have a value greater than zero. 
If the value of this property is changed, the size of the StateText array, if present, SHALL also
be changed to the same value. The states are numbered consecutively, starting with 1.

#### OutOfService Attribute
The OutOfService attribute, of type Boolean, indicates whether (TRUE) or not (FALSE) the physical
input, output or value that the cluster represents is not in service. For an Input cluster, when
OutOfService is TRUE the PresentValue attribute is decoupled from the physical input and  will
not track changes to the  physical input. For an Output cluster, when OutOfService is TRUE the
PresentValue attribute is decoupled from the physical output, so changes to PresentValue will not
affect the physical output. For a Value cluster, when OutOfService is TRUE the PresentValue attribute
MAY be written to freely by software local to the device that the cluster resides on.

#### PresentValue Attribute
The PresentValue attribute indicates the current value of the input, output or
value, as appropriate  for the cluster. For Analog clusters it is of type single precision, for Binary
clusters it is of type  Boolean, and for multistate clusters it is of type Unsigned 16-bit integer. The
PresentValue attribute of an input cluster SHALL be writable when OutOfService is TRUE. When the PriorityArray
attribute is implemented, writing to PresentValue SHALL be equivalent to writing to element 16 of PriorityArray,
i.e., with a priority of 16.

#### Reliability Attribute
The Reliability attribute, of type 8-bit enumeration, provides an indication of whether
the PresentValueor the operation of the physical input, output or value in question (as
appropriate for the cluster) is “reliable” as far as can be determined and, if not, why
not. The Reliability attribute MAY have any of the following values:

NO-FAULT-DETECTED (0)
OVER-RANGE (2)
UNDER-RANGE (3)
OPEN-LOOP (4)
SHORTED-LOOP (5)
UNRELIABLE-OTHER (7)
PROCESS-ERROR (8)
MULTI-STATE-FAULT (9)
CONFIGURATION-ERROR (10)

|Id     |Name                      |
|-------|--------------------------|
|0x0000 |NO-FAULT-DETECTED         |
|0x0002 |OVER-RANGE                |
|0x0003 |UNDER-RANGE               |
|0x0004 |OPEN-LOOP                 |
|0x0005 |SHORTED-LOOP              |
|0x0007 |UNRELIABLE-OTHER          |
|0x0008 |PROCESS-ERROR             |
|0x0009 |MULTI-STATE-FAULT         |
|0x000A |CONFIGURATION-ERROR       |

#### RelinquishDefault Attribute
The RelinquishDefault attribute is the default value to be used for the PresentValue
attribute when all elements of the PriorityArray attribute are marked as invalid.

#### StatusFlags Attribute
This attribute, of type bitmap, represents four Boolean flags that indicate the general “health”
of the analog sensor. Three of the flags are associated with the values of other optional attributes
of this cluster. A more detailed status could be determined by reading the optional attributes (if
supported) that are linked to these flags. The relationship between individual flags is not defined. 

The four flags are Bit 0 = IN_ALARM, Bit 1 = FAULT, Bit 2 = OVERRIDDEN, Bit 3 = OUT OF SERVICE

where:

IN_ALARM -Logical FALSE (0) if the EventStateattribute has a value of NORMAL, otherwise logical TRUE (1).
This bit is always 0 unless the cluster implementing the EventState attribute is implemented on the same
endpoint.

FAULT -Logical TRUE (1) if the Reliability attribute is present and does not have a value of NO FAULT DETECTED,
otherwise logical FALSE (0).

OVERRIDDEN -Logical TRUE (1) if the cluster has been overridden by some  mechanism local to the device. 
Otherwise, the value is logical FALSE (0). In this context, for an input cluster, “overridden” is taken
to mean that the PresentValue and Reliability(optional) attributes are no longer tracking changes to the
physical input. For an Output cluster, “overridden” is taken to mean that the physical output is no longer
tracking changes to the PresentValue attribute and the Reliability attribute is no longer a reflection of
the physical output. For a Value cluster, “overridden” is taken to mean that the PresentValue attribute is
not writeable.

OUT OF SERVICE -Logical TRUE (1) if the OutOfService attribute has a value of TRUE, otherwise
logical FALSE (0).

|Id     |Name                      |
|-------|--------------------------|
|0x0001 |IN_ALARM                  |
|0x0002 |FAULT                     |
|0x0004 |OVERRIDDEN                |
|0x0008 |OUT OF SERVICE            |


#### ApplicationType Attribute
The ApplicationType attribute is an unsigned 32 bit integer that indicates the specific
application usage for this cluster. (Note: This attribute has no BACnet equivalent).
ApplicationType is subdivided into Group, Type and an Index number, as follows.

Group = Bits 24 -31 An indication of the cluster this attribute is part of.

Type = Bits 16 -23 For Analog clusters, the physical quantity that the Present Value attribute
of the cluster represents. For Binary and Multistate clusters, the application usage domain.

Index = Bits 0 -15 The specific application usage of the cluster. 


### Received

No cluster specific commands.

### Generated

No cluster specific commands.

## Multistate Value (Basic) [0x0014]
The Multistate Value (Basic) cluster provides an interface for setting a multistate
value, typically used as a control system parameter, and accessing characteristics of that value.

### Attributes
|Id     |Name                  |Type                       |Access     |Implement |Reporting |
|-------|----------------------|---------------------------|-----------|----------|----------|
|0x000E |StateText             |Character string           |Read/Write |Optional  |          |
|0x001C |Description           |Character string           |Read/Write |Optional  |          |
|0x004A |NumberOfStates        |Unsigned 16-bit Integer    |Read/Write |Mandatory |          |
|0x0051 |OutOfService          |Boolean                    |Read/Write |Mandatory |          |
|0x0055 |PresentValue          |Unsigned 16-bit Integer    |Read/Write |Mandatory |          |
|0x0067 |Reliability           |8-bit enumeration          |Read/Write |Optional  |          |
|0x0068 |RelinquishDefault     |Unsigned 16-bit Integer    |Read/Write |Optional  |          |
|0x006F |StatusFlags           |8-bit bitmap               |Read only  |Mandatory |          |
|0x0100 |ApplicationType       |Signed 32-bit integer      |Read only  |Optional  |          |

#### StateText Attribute
This  attribute, of type Array of Character strings, holds descriptions of all possible
states of a multistate PresentValue.  The number of descriptions matches the number of states
defined in the NumberOfStates property. The PresentValue, interpreted as an integer, serves as
an index into the array. If the size of this array is changed, the NumberOfStates property SHALL
also be changed to the same value. The character set used SHALL be ASCII, and the attribute
SHALL contain a maximum of 16 characters, which SHALL be printable but are otherwise unrestricted.

#### Description Attribute
The Description attribute, of type Character string, MAY be used to hold a description
of the usage of the input, output or value, as appropriate to the cluster. The character
set used SHALL be ASCII, and the attribute SHALL contain a maximum of 16 characters,
which SHALL be printable but are otherwise unrestricted.

#### NumberOfStates Attribute
This attribute, of type Unsigned 16-bit integer, defines the number of states that a multistate
PresentValue MAY have. The NumberOfStates property SHALL always have a value greater than zero. 
If the value of this property is changed, the size of the StateText array, if present, SHALL also
be changed to the same value. The states are numbered consecutively, starting with 1.

#### OutOfService Attribute
The OutOfService attribute, of type Boolean, indicates whether (TRUE) or not (FALSE) the physical
input, output or value that the cluster represents is not in service. For an Input cluster, when
OutOfService is TRUE the PresentValue attribute is decoupled from the physical input and  will
not track changes to the  physical input. For an Output cluster, when OutOfService is TRUE the
PresentValue attribute is decoupled from the physical output, so changes to PresentValue will not
affect the physical output. For a Value cluster, when OutOfService is TRUE the PresentValue attribute
MAY be written to freely by software local to the device that the cluster resides on.

#### PresentValue Attribute
The PresentValue attribute indicates the current value of the input, output or
value, as appropriate for the cluster. For Analog clusters it is of type single precision, for Binary
clusters it is of type  Boolean, and for multistate clusters it is of type Unsigned 16-bit integer. The
PresentValue attribute of an input cluster SHALL be writable when OutOfService is TRUE. When the PriorityArray
attribute is implemented, writing to PresentValue SHALL be equivalent to writing to element 16 of PriorityArray,
i.e., with a priority of 16.

#### Reliability Attribute
The Reliability attribute, of type 8-bit enumeration, provides an indication of whether
the PresentValueor the operation of the physical input, output or value in question (as
appropriate for the cluster) is “reliable” as far as can be determined and, if not, why
not. The Reliability attribute MAY have any of the following values:

NO-FAULT-DETECTED (0)
OVER-RANGE (2)
UNDER-RANGE (3)
OPEN-LOOP (4)
SHORTED-LOOP (5)
UNRELIABLE-OTHER (7)
PROCESS-ERROR (8)
MULTI-STATE-FAULT (9)
CONFIGURATION-ERROR (10)

|Id     |Name                      |
|-------|--------------------------|
|0x0000 |NO-FAULT-DETECTED         |
|0x0002 |OVER-RANGE                |
|0x0003 |UNDER-RANGE               |
|0x0004 |OPEN-LOOP                 |
|0x0005 |SHORTED-LOOP              |
|0x0007 |UNRELIABLE-OTHER          |
|0x0008 |PROCESS-ERROR             |
|0x0009 |MULTI-STATE-FAULT         |
|0x000A |CONFIGURATION-ERROR       |

#### RelinquishDefault Attribute
The RelinquishDefault attribute is the default value to be used for the PresentValue
attribute when all elements of the PriorityArray attribute are marked as invalid.

#### StatusFlags Attribute
This attribute, of type bitmap, represents four Boolean flags that indicate the general “health”
of the analog sensor. Three of the flags are associated with the values of other optional attributes
of this cluster. A more detailed status could be determined by reading the optional attributes (if
supported) that are linked to these flags. The relationship between individual flags is not defined. 

The four flags are Bit 0 = IN_ALARM, Bit 1 = FAULT, Bit 2 = OVERRIDDEN, Bit 3 = OUT OF SERVICE

where:

IN_ALARM -Logical FALSE (0) if the EventStateattribute has a value of NORMAL, otherwise logical TRUE (1).
This bit is always 0 unless the cluster implementing the EventState attribute is implemented on the same
endpoint.

FAULT -Logical TRUE (1) if the Reliability attribute is present and does not have a value of NO FAULT DETECTED,
otherwise logical FALSE (0).

OVERRIDDEN -Logical TRUE (1) if the cluster has been overridden by some  mechanism local to the device. 
Otherwise, the value is logical FALSE (0). In this context, for an input cluster, “overridden” is taken
to mean that the PresentValue and Reliability(optional) attributes are no longer tracking changes to the
physical input. For an Output cluster, “overridden” is taken to mean that the physical output is no longer
tracking changes to the PresentValue attribute and the Reliability attribute is no longer a reflection of
the physical output. For a Value cluster, “overridden” is taken to mean that the PresentValue attribute is
not writeable.

OUT OF SERVICE -Logical TRUE (1) if the OutOfService attribute has a value of TRUE, otherwise
logical FALSE (0).

|Id     |Name                      |
|-------|--------------------------|
|0x0001 |IN_ALARM                  |
|0x0002 |FAULT                     |
|0x0004 |OVERRIDDEN                |
|0x0008 |OUT OF SERVICE            |


#### ApplicationType Attribute
The ApplicationType attribute is an unsigned 32 bit integer that indicates the specific
application usage for this cluster. (Note: This attribute has no BACnet equivalent).
ApplicationType is subdivided into Group, Type and an Index number, as follows.

Group = Bits 24 -31 An indication of the cluster this attribute is part of.

Type = Bits 16 -23 For Analog clusters, the physical quantity that the Present Value attribute
of the cluster represents. For Binary and Multistate clusters, the application usage domain.

Index = Bits 0 -15The specific application usage of the cluster. 


### Received

No cluster specific commands.

### Generated

No cluster specific commands.

## Commissioning [0x0015]

### Received

#### Restart Device Command [0x00]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Option                     |8-bit bitmap               |
|Delay                      |Unsigned 8-bit integer     |
|Jitter                     |Unsigned 8-bit integer     |

#### Save Startup Parameters Command [0x01]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Option                     |8-bit bitmap               |
|Index                      |Unsigned 8-bit integer     |

#### Restore Startup Parameters Command [0x02]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Option                     |8-bit bitmap               |
|Index                      |Unsigned 8-bit integer     |

#### Reset Startup Parameters Command [0x03]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Option                     |8-bit bitmap               |
|Index                      |Unsigned 8-bit integer     |

### Generated

#### Restart Device Response Response Command [0x00]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Status                     |8-bit enumeration          |

#### Save Startup Parameters Response Command [0x01]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Status                     |8-bit enumeration          |

#### Restore Startup Parameters Response Command [0x02]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Status                     |8-bit enumeration          |

#### Reset Startup Parameters Response Command [0x03]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Status                     |8-bit enumeration          |

## Poll Control [0x0020]

This cluster provides a mechanism for the management of an end device’s MAC Data Request rate.
For the purposes of this cluster, the term “poll” always refers to the sending of a MAC Data
Request from the end device to the end device’s parent. This cluster can be used for instance
by a configuration device to make an end device responsive for a certain period of time so that
the device can be managed by the controller. This cluster is composed of a client and server. The end device implements the server side of this cluster. The server side contains several attributes related to the MAC Data Request rate for the device. The client side implements commands used to manage the poll rate for the device. The end device which implements the server side of this cluster sends a query to the client on a predetermined interval to see if the client would like to manage the poll period of the end device in question. When the client side of the cluster hears from the server it has the opportunity to respond with configuration data to either put the end device in a short poll mode or let the end device continue to function normally.

### Attributes

|Id     |Name                       |Type                       |Access     |Implement |Reporting |
|-------|---------------------------|---------------------------|-----------|----------|----------|
|0x0000 |CheckinInterval            |Unsigned 32-bit integer    |Read Write |Mandatory |Mandatory |
|0x0001 |LongPollInterval           |Unsigned 32-bit integer    |Read       |Mandatory |Mandatory |
|0x0002 |ShortPollInterval          |Unsigned 16-bit integer    |Read       |Mandatory |Mandatory |
|0x0003 |FastPollTimeout            |Unsigned 16-bit integer    |Read       |Mandatory |Mandatory |
|0x0004 |CheckinIntervalMin         |Unsigned 32-bit integer    |Read       |          |          |
|0x0005 |LongPollIntervalMin        |Unsigned 32-bit integer    |Read       |          |          |
|0x0006 |FastPollTimeoutMin         |Unsigned 32-bit integer    |Read       |          |          |

#### CheckinInterval Attribute

The Poll Control server is responsible for checking in with the poll control client periodically to see if the poll control  client wants to modify the poll rate of the poll control server.  This is due to the fact that  the  PollControl server is implemented on an end device that MAY have an unpredictable sleep-wake cycle. The CheckinInterval represents the default amount of time between check-ins by the poll control server with the poll control client. The CheckinInterval is measured in quarter-seconds. A value of 0 indicates that the Poll Control Server is turned off and the poll control server will not check-in with the poll control client. The Poll Control Server checks in with the Poll Control Client by sending a Checkin command to the Client. This value SHOULDbe longer than the LongPoll Interval attribute. If the Client writes an invalid attribute value (Example: Out of Range or a value smaller than the optional Check-inIntervalMinattribute value or a value smaller than the LongPollInterval attribute value), the Server SHOULD return Write Attributes Response with an error status not equal to ZCL_SUCCESS. The Poll Control Client will hold onto the actions or messages for the Poll Control Server at the application level until the Poll Control Server checks in with the Poll Control Client.

#### LongPollInterval Attribute

An end device that implements the Poll Control server MAY optionally expose a LongPollInterval attribute. 
The Long Poll Interval represents the maximum amount of time in quarter-seconds between MAC Data Requests
from the end device to its parent. 

The LongPollInterval defines the frequency of polling that an end device does when it is NOT in fast poll mode.  The LongPollInterval SHOULD be longer than the ShortPollInterval attribute but shorter than the CheckinInterval attribute.A  value of 0xffffffff is reserved to indicate that the device does not have or does not know its long poll interval

#### ShortPollInterval Attribute

An  end  device  that  implements  the  Poll  Control  server MAY optionally  expose the ShortPollInterval attribute.  The ShortPollIntervalrepresents  the  number  of  quarterseconds  that  an  end  device  waits  between MAC Data Requests to its parent when it is expecting data (i.e.,in fast poll mode).

#### FastPollTimeout Attribute

The FastPollTimeout attribute represents the number of quarterseconds that an end device will stay in fast poll mode by default. It is suggested that the FastPollTimeoutattribute value be greater than 7.68 seconds.The Poll Control Cluster  Client MAYoverride  this  value  by  indicating  a  different  value  in  the  Fast  Poll Duration argument in the Check-in Response command. If the Client writes a value out of range or greater  than  the  optional FastPollTimeoutMax attribute  value  if  supported, the Server SHOULD return a  Write  Attributes  Response with a status of  INVALID_VALUE30.  An  end  device  that implements the  Poll Control server can be  put into a  fast poll  mode during  which it will send MAC Data Requests  to  its  parent  at  the  frequency  of  its  configured ShortPollInterval attribute.  During this  period  of time, fast polling is considered active. When the device goes into fast poll mode, it is required to send MAC DataRequests to its parent at an accelerated rate and is thus more responsive on the network and can receive data asynchronously from the device implementing the Poll Control Cluster Client.

#### CheckinIntervalMin Attribute

The Poll Control Server MAY optionally provide its own minimum value for the Check-inInterval to protect against the Check-inInterval being set too low and draining the battery on the end device implementing the Poll Control Server.

#### LongPollIntervalMin Attribute

The Poll Control Server MAYoptionally provide its own minimum value for the LongPollIntervalto protect against  another  device  setting  the  value  to  too  short  a  time  resulting  in  an  inadvertent  power  drain  on  the device.

#### FastPollTimeoutMin Attribute

The Poll Control Server MAY optionally provide its own maximum value for the FastPollTimeout to avoid it being set to too high a value resulting in an inadvertent power drain on the device.

### Received

#### Check In Response [0x00]
The Check-in Response is sent in response to the receipt of a Check-in command. The Check-in Response is used by the Poll Control Client to indicate whether it would like the device implementing the Poll Control Cluster Server to go into a fast poll mode and for how long. If the Poll Control Cluster Client indicates that it would like the device to go into a fast poll mode, it is responsible for telling the device to stop fast polling when it is done sending messages to the fast polling device.

If the Poll Control Server receives a Check-In Response from a client for which there is no binding (unbound), it SHOULD respond with a Default Response with a status value indicating ACTION_DENIED.

If the Poll Control Server receives a Check-In Response from a client for which there is a binding (bound) with an invalid fast poll interval it SHOULD respond with a Default Response with status INVALID_VALUE.

If the Poll Control Server receives a Check-In Response from a bound client after temporary fast poll mode is completed it SHOULD respond with a Default Response with a status value indicating TIMEOUT.

In all of the above cases, the Server SHALL respond with a Default Response not equal to ZCL_SUCCESS.

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
| Start Fast Polling        |Boolean                    |
| Fast Poll Timeout         |Unsigned 16-bit integer    |

##### Start Fast Polling Field
This Boolean value indicates whether or not the Poll Control Server device SHOULD begin fast polling or not. If the Start Fast Polling value is true, the server device is EXPECTED to begin fast polling until the Fast Poll Timeout has expired. If the Start Fast Polling argument is false, the Poll Control Server MAY continue in normal operation and is not required to go into fast poll mode.

##### Fast Poll Timeout Field
The Fast Poll Timeout value indicates the number of quarterseconds during which the device SHOULD continue fast polling. If the Fast Poll Timeout value is 0, the device is EXPECTED to continue fast polling until the amount of time indicated it the FastPollTimeout attribute has elapsed or it receives a Fast Poll Stop command. If the Start Fast Polling argument is false, the Poll Control Server MAY ignore the Fast Poll Timeout argument.

The Fast Poll Timeout argument temporarily overrides the FastPollTimeout attribute on the Poll Control Cluster Server for the fast poll mode induced by the Check-in Response command. This value is not EXPECTED to overwrite the stored value in the FastPollTimeout attribute.

If the FastPollTimeout parameter in the CheckInResponse command is greater than the FastPollTimeoutMax attribute value, the Server Device SHALL respond with a default response of error status not equal to ZCL_SUCCESS. It is suggested to use the Error Status of ZCL_INVALID_FIELD.

#### Fast Poll Stop Command [0x01]

The Fast Poll Stop command is used to stop the fast poll mode initiated by the Check-in response. The Fast Poll Stop command has no payload.

If the Poll Control Server receives a Fast Poll Stop from an unbound client it SHOULD send back a DefaultResponse with a value field indicating “ACTION_DENIED” . The Server SHALL respond with a DefaultResponse not equal to ZCL_SUCCESS.

If the Poll Control Server receives a Fast Poll Stop command from a bound client but it is unable to stop fast polling due to the fact that there is another bound client which has requested that polling continue it SHOULD respond with a Default Response with a status of “ACTION_DENIED”

If a Poll Control Server receives a Fast Poll Stop command from a bound client but it is not FastPolling it SHOULD respond with a Default Response with a status of ACTION_DENIED.

#### Set Long Poll Interval Command [0x02]
The Set Long Poll Interval command is used to set the Read Only LongPollInterval attribute.

When the Poll Control Server receives the Set Long Poll Interval Command, it SHOULD check its internal minimal limit and the attributes relationship if the new Long Poll Interval is acceptable. If the new value is acceptable, the new value SHALL be saved to the LongPollInterval attribute. If the new value is not acceptable, the Poll Control Server SHALL send a default response of INVALID_VALUE and the LongPollInterval attribute value is not updated.

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
| New Long Poll Interval    |Unsigned 16-bit integer    |

#### Set Short Poll Interval Command [0x03]
The Set Short Poll Interval command is used to set the Read Only ShortPollInterval attribute.

When the Poll Control Server receives the Set Short Poll Interval Command, it SHOULD check its internal minimal limit and the attributes relationship if the new Short Poll Interval is acceptable. If the new value is acceptable, the new value SHALL be saved to the ShortPollInterval attribute. If the new value is not acceptable, the Poll Control Server SHALL send a default response of INVALID_VALUE and the ShortPollInterval attribute value is not updated.

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
| New Short Poll Interval   |Unsigned 16-bit integer    |

### Generated

#### Check In Command [0x00]
The Poll Control Cluster server sends out a Check-in command to the devices to which it is paired based on the server’s Check-inInterval attribute. It does this to find out if any of the Poll Control Cluster Clients with which it is paired are interested in having it enter fast poll mode so that it can be managed. This request is sent out based on either the Check-inInterval, or the next Check-in value in the Fast Poll Stop Request generated by the Poll Control Cluster Client.

The Check-in command expects a Check-in Response command to be sent back from the Poll Control Client. If the Poll Control Server does not receive a Check-in response back from the Poll Control Client up to 7.68 seconds it is free to return to polling according to the LongPollInterval.

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|


# Closures
## Shade Configuration [0x0100]

### Received

### Generated

No cluster specific commands.

## Door Lock [0x0101]

### Received

#### Lock Door Command [0x00]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Pin code                   |Octet string               |

#### Unlock Door Command [0x01]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Pin code                   |Octet string               |

### Generated

#### Lock Door Response Command [0x00]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Status                     |8-bit enumeration          |

#### Unlock Door Response Command [0x01]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Status                     |8-bit enumeration          |

# HVAC

## Pump Configuration and Control [0x0200]

### Received

No cluster specific commands.

### Generated

No cluster specific commands.

## Thermostat [0x0201]

### Attributes

|Id     |Name                       |Type                       |Access     |Implement |Reporting |
|-------|---------------------------|---------------------------|-----------|----------|----------|
|0x0000 |LocalTemperature           |Unsigned 16-bit integer    |Read       |Mandatory |Mandatory |
|0x0001 |OutdoorTemperature         |Unsigned 16-bit integer    |Read       |Optional  |          |
|0x0002 |Occupancy                  |Bitmap 8-bit               |Read       |Optional  |          |
|0x0003 |AbsMinHeatSetpointLimit    |Unsigned 16-bit integer    |Read       |Optional  |          |
|0x0004 |AbsMaxHeatSetpointLimit    |Unsigned 16-bit integer    |Read       |Optional  |          |
|0x0005 |AbsMinCoolSetpointLimit    |Unsigned 16-bit integer    |Read       |Optional  |          |
|0x0006 |AbsMaxCoolSetpointLimit    |Unsigned 16-bit integer    |Read       |Optional  |          |
|0x0007 |PICoolingDemand            |Unsigned 8-bit integer     |Read       |Optional  |Mandatory |
|0x0008 |PIHeatingDemand            |Unsigned 8-bit integer     |Read       |Optional  |Mandatory |
|0x0009 |HVACSystemTypeConfiguration|Bitmap 8-bit               |Read       |Optional  |          |
|0x0010 |LocalTemperatureCalibration|Unsigned 8-bit integer     |Read       |Optional  |          |
|0x0011 |OccupiedCoolingSetpoint    |Unsigned 16-bit integer    |Read       |Mandatory |          |
|0x0012 |OccupiedHeatingSetpoint    |Unsigned 16-bit integer    |Read       |Mandatory |          |
|0x0013 |UnoccupiedCoolingSetpoint  |Unsigned 16-bit integer    |Read       |Optional  |          |
|0x0014 |UnoccupiedHeatingSetpoint  |Unsigned 16-bit integer    |Read       |Optional  |          |
|0x0015 |MinHeatSetpointLimit       |Unsigned 16-bit integer    |Read       |Optional  |          |
|0x0016 |MaxHeatSetpointLimit       |Unsigned 16-bit integer    |Read       |Optional  |          |
|0x0017 |MinCoolSetpointLimit       |Unsigned 16-bit integer    |Read       |Optional  |          |
|0x0018 |MaxCoolSetpointLimit       |Unsigned 16-bit integer    |Read       |Optional  |          |
|0x0019 |MinSetpointDeadBand        |Unsigned 8-bit integer     |Read       |Optional  |          |
|0x001A |RemoteSensing              |Bitmap 8-bit               |Read       |Optional  |          |
|0x001B |ControlSequenceOfOperation |Enumeration 8-bit          |Read       |Mandatory |          |
|0x001C |SystemMode                 |Enumeration 8-bit          |Read       |Mandatory |          |
|0x001D |AlarmMask                  |Enumeration 8-bit          |Read       |Optional  |          |
|0x001E |ThermostatRunningMode      |Enumeration 8-bit          |Read       |Optional  |          |
|0x0044 |ACErrorCode                |Bitmap 32-bit              |Read       |Optional  |          |

#### LocalTemperature Attribute
LocalTemperature represents the temperature in degrees Celsius, as measured locally.

#### OutdoorTemperature Attribute
OutdoorTemperature represents the temperature in degrees Celsius, as measured locally.

#### Occupancy Attribute
Occupancy specifies whether the heated/cooled space is occupied or not

#### AbsMinHeatSetpointLimit Attribute
The MinHeatSetpointLimit attribute specifies the absolute minimum level that the heating setpoint MAY be
set to. This is a limitation imposed by the manufacturer. 

#### AbsMaxHeatSetpointLimit Attribute
The MaxHeatSetpointLimit attribute specifies the absolute maximum level that the heating setpoint MAY be 
set to. This is a limitation imposed by the manufacturer. 

#### AbsMinCoolSetpointLimit Attribute
The MinCoolSetpointLimit attribute specifies the absolute minimum level that the cooling setpoint MAY be
set to. This is a limitation imposed by the manufacturer. 

#### AbsMaxCoolSetpointLimit Attribute
The MaxCoolSetpointLimit attribute specifies the absolute maximum level that the cooling setpoint MAY be
set to. This is a limitation imposed by the manufacturer. 

#### PICoolingDemand Attribute
The PICoolingDemandattribute is 8 bits in length and specifies the level of cooling demanded by the PI
(proportional  integral) control loop in use by the thermostat (if any), in percent.  This value is 0 when the
thermostat is in “off” or “heating” mode.

#### PIHeatingDemand Attribute
The PIHeatingDemand attribute is 8 bits in length and specifies the level of heating demanded by the PI
(proportional  integral) control loop in use by the thermostat (if any), in percent.  This value is 0 when the
thermostat is in “off” or “cooling” mode.

#### ACErrorCode Attribute
This indicates the type of errors encountered within the Mini Split AC. Error values are reported with four bytes
values. Each bit within the four bytes indicates the unique error.

### Received

#### Setpoint Raise/Lower Command [0x00]

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Mode                       |8-bit enumeration          |
|Amount                     |Signed 8-bit integer       |

#### Set Weekly Schedule [0x01]

The set weekly schedule command is used to update the thermostat weekly set point schedule from a management system.
If the thermostat already has a weekly set point schedule programmed then it SHOULD replace each daily set point set
as it receives the updates from the management system. For example if the thermostat has 4 set points for every day of
the week and is sent a Set Weekly Schedule command with one set point for Saturday then the thermostat SHOULD remove
all 4 set points for Saturday and replace those with the updated set point but leave all other days unchanged.

If the schedule is larger than what fits in one ZigBee frame or contains more than 10 transitions, the schedule SHALL
then be sent using multipleSet Weekly Schedule Commands.

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Number of Transitions      |Enumeration 8-bit          |
|Day of Week                |Enumeration 8-bit          |
|Mode                       |Enumeration 8-bit          |
|Transition                 |Unsigned 16-bit integer    |
|Heat Set                   |Unsigned 16-bit integer    |
|Cool Set                   |Unsigned 16-bit integer    |


#### Get Weekly Schedule [0x02]

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Days To Return             |Bitmap 8-bit               |
|Mode To Return             |Bitmap 8-bit               |


#### Clear Weekly Schedule [0x03]

#### Get Relay Status Log [0x04]

The Get Relay Status Log command is used to query the thermostat internal relay status log. This command has no payload.

The log storing order is First in First Out (FIFO) when the log is generated and stored into the Queue.

The first record in the log (i.e., the oldest) one, is the first to be replaced when there is a new record and there is
no more space in the log. Thus, the newest record will overwrite the oldest one if there is no space left.

The log storing order is Last In First Out (LIFO) when the log is being retrieved from the Queue by a client device.
Once the "Get Relay Status Log Response" frame is sent by the Server, the "Unread Entries" attribute
SHOULD be decremented to indicate the number of unread records that remain in the queue.

If the "Unread Entries"attribute reaches zero and the Client sends a new "Get Relay Status Log Request", the Server
MAY send one of the following items as a response:

i) resend the last Get Relay Status Log Response
or
ii) generate new log record at the time of request and send Get Relay Status Log Response with the new data

### Generated

#### Get Weekly Schedule Response [0x00]

|Number of Transitions      |Enumeration 8-bit          |
|Day of Week                |Enumeration 8-bit          |
|Mode                       |Enumeration 8-bit          |
|Transition                 |Unsigned 16-bit integer    |
|Heat Set                   |Unsigned 16-bit integer    |
|Cool Set                   |Unsigned 16-bit integer    |


#### Get Relay Status Log Response [0x01]

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Time of day                |Unsigned 16-bit integer    |
|Relay Status               |Bitmap 8-bit               |
|Local Temperature          |Unsigned 16-bit integer    |
|Humidity                   |Unsigned 8-bit integer     |
|Setpoint                   |Unsigned 16-bit integer    |
|Unread Entries             |Unsigned 16-bit integer    |


## Fan Control [0x0202]

### Received

No cluster specific commands.

### Generated

No cluster specific commands.

## Dehumidification Control [0x0203]

### Received

No cluster specific commands.

### Generated

No cluster specific commands.

## Thermostat User Interface Configuration [0x0204]

### Received

No cluster specific commands.

### Generated

No cluster specific commands.

# Lighting

## Color Control [0x0300]
This cluster provides an interface for changing the color of a light. Color is
specified according to the Commission Internationale de l'Éclairage (CIE)
specification CIE 1931 Color Space, [B4]. Color control is carried out in terms of
x,y values, as defined by this specification. 

### Attributes
|Id     |Name                       |Type                       |Access     |Implement |Reporting |
|-------|---------------------------|---------------------------|-----------|----------|----------|
|0x0000 |CurrentHue                 |Unsigned 8-bit Integer     |Read only  |Optional  |Mandatory |
|0x0001 |CurrentSaturation          |Unsigned 8-bit Integer     |Read only  |Optional  |Mandatory |
|0x0002 |RemainingTime              |Unsigned 16-bit Integer    |Read only  |Optional  |          |
|0x0003 |CurrentX                   |Unsigned 16-bit Integer    |Read only  |Mandatory |Mandatory |
|0x0004 |CurrentY                   |Unsigned 16-bit Integer    |Read only  |Mandatory |Mandatory |
|0x0005 |DriftCompensation          |8-bit Enumeration          |Read only  |Optional  |          |
|0x0006 |CompensationText           |Character string           |Read only  |Optional  |          |
|0x0007 |ColorTemperature           |Unsigned 16-bit Integer    |Read only  |Optional  |Mandatory |
|0x0008 |ColorMode                  |8-bit Enumeration          |Read only  |Optional  |          |
|0x4000 |EnhancedCurrentHue         |Unsigned 16-bit Integer    |Read only  |Optional  |Mandatory |
|0x4001 |EnhancedColorMode          |8-bit Enumeration          |Read only  |Optional  |          |
|0x4002 |ColorLoopActive            |Unsigned 8-bit Integer     |Read only  |Optional  |          |
|0x4003 |ColorLoopDirection         |Unsigned 8-bit Integer     |Read only  |Optional  |          |
|0x4004 |ColorLoopTime              |Unsigned 16-bit Integer    |Read only  |Optional  |          |
|0x4005 |ColorLoopStartHue          |Unsigned 16-bit Integer    |Read only  |Optional  |          |
|0x4006 |ColorLoopStoredHue         |Unsigned 16-bit Integer    |Read only  |Optional  |          |
|0x400A |ColorCapabilities          |16-bit Bitmap              |Read only  |Optional  |          |
|0x400B |ColorTemperatureMin        |Unsigned 16-bit Integer    |Read only  |Optional  |          |
|0x400C |ColorTemperatureMax        |Unsigned 16-bit Integer    |Read only  |Optional  |          |


#### CurrentHue Attribute
The CurrentHue attribute contains the current hue value of the light. It is updated
as fast as practical during commands that change the hue.

The hue in degrees shall be related to the CurrentHue attribute by the relationship
Hue = CurrentHue x 360 / 254 (CurrentHue in the range 0 - 254 inclusive)

If this attribute is implemented then the CurrentSaturation and ColorMode
attributes shall also be implemented.

#### CurrentSaturation Attribute
The CurrentSaturation attribute holds the current saturation value of the light. It is
updated as fast as practical during commands that change the saturation.
The saturation shall be related to the CurrentSaturation attribute by the
relationship
Saturation = CurrentSaturation/254 (CurrentSaturation in the range 0 - 254 inclusive)
If this attribute is implemented then the CurrentHue and ColorMode attributes
shall also be implemented.

#### RemainingTime Attribute
The RemainingTime attribute holds the time remaining, in 1/10ths of a second,
until the currently active command will be complete.

#### CurrentX Attribute
The CurrentX attribute contains the current value of the normalized chromaticity
value x, as defined in the CIE xyY Color Space. It is updated as fast as practical
during commands that change the color.

The value of x shall be related to the CurrentX attribute by the relationship

x = CurrentX / 65535 (CurrentX in the range 0 to 65279 inclusive)

#### CurrentY Attribute
The CurrentY attribute contains the current value of the normalized chromaticity
value y, as defined in the CIE xyY Color Space. It is updated as fast as practical
during commands that change the color.

The value of y shall be related to the CurrentY attribute by the relationship

y = CurrentY / 65535 (CurrentY in the range 0 to 65279 inclusive)

#### DriftCompensation Attribute
The DriftCompensation attribute indicates what mechanism, if any, is in use for
compensation for color/intensity drift over time.

#### CompensationText Attribute
The CompensationText attribute holds a textual indication of what mechanism, if
any, is in use to compensate for color/intensity drift over time.

#### ColorTemperature Attribute
The ColorTemperature attribute contains a scaled inverse of the current value of
the color temperature. It is updated as fast as practical during commands that
change the color.

The color temperature value in Kelvins shall be related to the ColorTemperature
attribute by the relationship

Color temperature = 1,000,000 / ColorTemperature (ColorTemperature in the
range 1 to 65279 inclusive, giving a color temperature range from 1,000,000
Kelvins to 15.32 Kelvins).

The value ColorTemperature = 0 indicates an undefined value. The value
ColorTemperature = 65535 indicates an invalid value. 

#### ColorMode Attribute
The ColorMode attribute indicates which attributes are currently determining the color of the device.
If either the CurrentHue or CurrentSaturation attribute is implemented, this attribute SHALL also be
implemented, otherwise it is optional. The value of the ColorMode attribute cannot be written directly
- it is set upon reception of another command in to the appropriate mode for that command.

|Id     |Name                              |
|-------|----------------------------------|
|0x0000 |CurrentHue and CurrentSaturation  |
|0x0001 |CurrentX and CurrentY             |
|0x0002 |ColorTemperature                  |


#### EnhancedCurrentHue Attribute
The EnhancedCurrentHueattribute represents non-equidistant steps along the CIE 1931 color
triangle, and it provides 16-bits precision. The upper 8 bits of this attribute SHALL be
used as an index in the implementation specific XY lookup table to provide the non-equidistance
steps (see the ZLL test specification for an example).  The lower 8 bits SHALL be used to
interpolate between these steps in a linear way in order to provide color zoom for the user.

#### EnhancedColorMode Attribute
The EnhancedColorModeattribute specifies which attributes are currently determining the color of the device.
To provide compatibility with standard ZCL, the original ColorModeattribute SHALLindicate ‘CurrentHueand CurrentSaturation’
when the light uses the EnhancedCurrentHueattribute.
 
|Id     |Name                                     |
|-------|-----------------------------------------|
|0x0000 |CurrentHue and CurrentSaturation         |
|0x0001 |CurrentX and CurrentY                    |
|0x0002 |ColorTemperature                         |
|0x0002 |EnhancedCurrentHue and CurrentSaturation |

#### ColorCapabilities Attribute
The ColorCapabilitiesattribute specifies the color capabilities of the device supporting the
color control cluster.

Note:The support of the CurrentXand CurrentYattributes is mandatory regardless of color capabilities.

|Id     |Name                |
|-------|--------------------|
|0x0001 |Hue and Saturation  |
|0x0002 |Enhanced Hue        |
|0x0004 |Color Loop          |
|0x0008 |XY Attribute        |
|0x0010 |Color Temperature   |

#### ColorLoopActive Attribute
The ColorLoopActive attribute specifies the current active status of the color loop.
If this attribute has the value 0x00, the color loop SHALLnot be active. If this attribute
has the value 0x01, the color loop SHALL be active. All other values (0x02 – 0xff) are reserved.

#### ColorLoopDirection Attribute
The ColorLoopDirection attribute specifies the current direction of the color loop.
If this attribute has the value 0x00, the EnhancedCurrentHue attribute SHALL be decremented.
If this attribute has the value 0x01, the EnhancedCurrentHue attribute SHALL be incremented.
All other values (0x02 – 0xff) are reserved.

#### ColorLoopTime Attribute
The ColorLoopTime attribute specifies the number of seconds it SHALL take to perform a full
color loop, i.e.,to cycle all values of the EnhancedCurrentHue attribute (between 0x0000 and 0xffff).

#### ColorLoopStartHue Attribute
The ColorLoopStartEnhancedHueattribute specifies the value of the EnhancedCurrentHue attribute
from which the color loop SHALL be started.

#### ColorLoopStoredHue Attribute
The ColorLoopStoredEnhancedHue attribute specifies the value of the EnhancedCurrentHue attribute
before the color loop was started. Once the color loop is complete, the EnhancedCurrentHue
attribute SHALL be restored to this value.

#### ColorTemperatureMin Attribute
The ColorTempPhysicalMinMiredsattribute indicates the minimum mired value
supported by the hardware. ColorTempPhysicalMinMiredscorresponds to the maximum
color temperature in kelvins supported by the hardware.
ColorTempPhysicalMinMireds ≤ ColorTemperatureMireds

#### ColorTemperatureMax Attribute
The ColorTempPhysicalMaxMiredsattribute indicates the maximum mired value
supported by the hard-ware. ColorTempPhysicalMaxMiredscorresponds to the minimum
color temperature in kelvins supported by the hardware.
ColorTemperatureMireds ≤ ColorTempPhysicalMaxMireds.

### Received

#### Move to Hue Command [0x00]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Hue                        |Unsigned 8-bit integer     |
|Direction                  |8-bit enumeration          |
|Transition time            |Unsigned 16-bit integer    |

#### Move Hue Command [0x01]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Move mode                  |8-bit enumeration          |
|Rate                       |Unsigned 8-bit integer     |

#### Step Hue Command [0x02]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Step mode                  |8-bit enumeration          |
|Step size                  |Unsigned 8-bit integer     |
|Transition time            |Unsigned 8-bit integer     |

#### Move to Saturation Command [0x03]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Saturation                 |Unsigned 8-bit integer     |
|Transition time            |Unsigned 16-bit integer    |

#### Move Saturation Command [0x04]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Move mode                  |8-bit enumeration          |
|Rate                       |Unsigned 8-bit integer     |

#### Step Saturation Command [0x05]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Step mode                  |8-bit enumeration          |
|Step size                  |Unsigned 8-bit integer     |
|Transition time            |Unsigned 8-bit integer     |

#### Move to Hue and Saturation Command [0x06]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Hue                        |Unsigned 8-bit integer     |
|Saturation                 |Unsigned 8-bit integer     |
|Transition time            |Unsigned 16-bit integer    |

#### Move to Color Command [0x07]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|ColorX                     |Unsigned 16-bit integer    |
|ColorY                     |Unsigned 16-bit integer    |
|Transition time            |Unsigned 16-bit integer    |

#### Move Color Command [0x08]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|RateX                      |Signed 16-bit integer      |
|RateY                      |Signed 16-bit integer      |

#### Step Color Command [0x09]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|StepX                      |Signed 16-bit integer      |
|StepY                      |Signed 16-bit integer      |
|Transition time            |Unsigned 16-bit integer    |

#### Move to Color Temperature Command [0x0a]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Color Temperature          |Unsigned 16-bit integer    |
|Transition time            |Unsigned 16-bit integer    |

#### Enhanced Move To Hue Command [0x40]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Hue                        |Unsigned 16-bit integer    |
|Direction                  |8-bit enumeration          |
|Transition time            |Unsigned 16-bit integer    |

#### Enhanced Step Hue Command [0x41]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Step Mode                  |8-bit enumeration          |
|Step Size                  |Unsigned 16-bit integer    |
|Transition time            |Unsigned 16-bit integer    |

#### Enhanced Move To Hue and Saturation Command [0x42]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Hue                        |Unsigned 16-bit integer    |
|Saturation                 |8-bit enumeration          |
|Transition time            |Unsigned 16-bit integer    |

#### Color Loop Set Command [0x43]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Update Flags               |8-bit bitmap               |
|Action                     |8-bit enumeration          |
|Direction                  |8-bit enumeration          |
|Transition time            |Unsigned 16-bit integer    |
|Start Hue                  |Unsigned 16-bit integer    |


### Generated

No cluster specific commands.

## Ballast Configuration [0x0301]

### Received

No cluster specific commands.

### Generated

No cluster specific commands.

# Measurement and Sensing

## Illuminance measurement [0x0400]
The cluster provides an interface to illuminance measurement functionality,
including configuration and provision of notifications of illuminance
measurements.

### Attributes
|Id     |Name                  |Type                       |Access     |Implement |Reporting |
|-------|----------------------|---------------------------|-----------|----------|----------|
|0x0000 |MeasuredValue         |Unsigned 16-bit Integer    |Read only  |Mandatory |Mandatory |
|0x0001 |MinMeasuredValue      |Unsigned 16-bit Integer    |Read only  |Mandatory |          |
|0x0002 |MaxMeasuredValue      |Unsigned 16-bit Integer    |Read only  |Mandatory |          |
|0x0003 |Tolerance             |Unsigned 16-bit Integer    |Read only  |Optional  |Mandatory |
|0x0004 |LightSensorType       |8-bit Enumeration          |Read only  |Optional  |          |

#### MeasuredValue Attribute
MeasuredValue represents the Illuminance in Lux (symbol lx) as follows:-

MeasuredValue = 10,000 x log10 Illuminance + 1

Where 1 lx <= Illuminance <=3.576 Mlx, corresponding to a MeasuredValue in
the range 1 to 0xfffe.

The following special values of MeasuredValue apply.
<li>0x0000 indicates a value of Illuminance that is too low to be measured.</li>
<li>0xffff indicates that the Illuminance measurement is invalid.</li>

#### MinMeasuredValue Attribute
The MinMeasuredValue attribute indicates the minimum value of MeasuredValue
that can be measured. A value of 0xffff indicates that this attribute is not defined.

#### MaxMeasuredValue Attribute
The MaxMeasuredValue attribute indicates the maximum value of MeasuredValue
that can be measured. A value of 0xffff indicates that this attribute is not defined.

MaxMeasuredValue shall be greater than MinMeasuredValue.

MinMeasuredValue and MaxMeasuredValue define the range of the sensor.

#### Tolerance Attribute
The Tolerance attribute indicates the magnitude of the possible error that is
associated with MeasuredValue . The true value is located in the range
(MeasuredValue – Tolerance) to (MeasuredValue + Tolerance).

#### LightSensorType Attribute
The LightSensorType attribute specifies the electronic type of the light sensor.

### Received

No cluster specific commands.

### Generated

No cluster specific commands.

## Illuminance level sensing [0x0401]
The cluster provides an interface to illuminance level sensing functionality,
including configuration and provision of notifications of whether the illuminance
is within, above or below a target band.

### Attributes
|Id     |Name                  |Type                       |Access     |Implement |Reporting |
|-------|----------------------|---------------------------|-----------|----------|----------|
|0x0000 |LevelStatus           |8-bit Enumeration          |Read only  |Mandatory |Mandatory |
|0x0001 |LightSensorType       |8-bit Enumeration          |Read only  |Optional  |          |
|0x0010 |IlluminanceTargetLevel|Unsigned 16-bit Integer    |Read only  |Optional  |          |

#### LevelStatus Attribute
The LevelStatus attribute indicates whether the measured illuminance is above,
below, or within a band around IlluminanceTargetLevel .

#### LightSensorType Attribute
The LightSensorType attribute specifies the electronic type of the light sensor.

#### IlluminanceTargetLevel Attribute
The IlluminanceTargetLevel attribute specifies the target illuminance level. This
target level is taken as the centre of a 'dead band', which must be sufficient in
width, with hysteresis bands at both top and bottom, to provide reliable
notifications without 'chatter'. Such a dead band and hysteresis bands must be
provided by any implementation of this cluster. (N.B. Manufacturer specific
attributes may be provided to configure these).

IlluminanceTargetLevel represents illuminance in Lux (symbol lx) as follows:

IlluminanceTargetLevel = 10,000 x log10 Illuminance

Where 1 lx <= Illuminance <=3.576 Mlx, corresponding to a MeasuredValue in
the range 0 to 0xfffe.

A value of 0xffff indicates that this attribute is not valid.

### Received

No cluster specific commands.

### Generated

No cluster specific commands.

## Temperature measurement [0x0402]

### Attributes
|Id     |Name                  |Type                       |Access     |Implement |Reporting |
|-------|----------------------|---------------------------|-----------|----------|----------|
|0x0000 |MeasuredValue         |Signed 16-bit Integer      |Read only  |Mandatory |Mandatory |
|0x0001 |MinMeasuredValue      |Signed 16-bit Integer      |Read only  |Mandatory |          |
|0x0002 |MaxMeasuredValue      |Signed 16-bit Integer      |Read only  |Mandatory |          |
|0x0003 |Tolerance             |Unsigned 16-bit Integer    |Read only  |Optional  |Mandatory |

#### MeasuredValue Attribute
MeasuredValue represents the temperature in degrees Celsius as follows:-
MeasuredValue = 100 x temperature in degrees Celsius.

Where -273.15°C <= temperature <= 327.67 ºC, corresponding to a

MeasuredValue in the range 0x954d to 0x7fff. The maximum resolution this
format allows is 0.01 ºC.

A MeasuredValue of 0x8000 indicates that the temperature measurement is
invalid.

MeasuredValue is updated continuously as new measurements are made.

#### MinMeasuredValue Attribute
The MinMeasuredValue attribute indicates the minimum value of MeasuredValue
that is capable of being measured. A MinMeasuredValue of 0x8000 indicates that
the minimum value is unknown.

#### MaxMeasuredValue Attribute
The MaxMeasuredValue attribute indicates the maximum value of MeasuredValue
that is capable of being measured.

MaxMeasuredValue shall be greater than MinMeasuredValue. 

MinMeasuredValue and MaxMeasuredValue define the range of the sensor.

A MaxMeasuredValue of 0x8000 indicates that the maximum value is unknown.

#### Tolerance Attribute
The Tolerance attribute indicates the magnitude of the possible error that is
associated with MeasuredValue . The true value is located in the range
(MeasuredValue – Tolerance) to (MeasuredValue + Tolerance).

### Received

No cluster specific commands.

### Generated

No cluster specific commands.

## Pressure measurement [0x0403]
The cluster provides an interface to pressure measurement functionality,
including configuration and provision of notifications of pressure measurements.

### Attributes
|Id     |Name                  |Type                       |Access     |Implement |Reporting |
|-------|----------------------|---------------------------|-----------|----------|----------|
|0x0000 |MeasuredValue         |Signed 16-bit Integer      |Read only  |Mandatory |Mandatory |
|0x0001 |MinMeasuredValue      |Signed 16-bit Integer      |Read only  |Mandatory |          |
|0x0002 |MaxMeasuredValue      |Signed 16-bit Integer      |Read only  |Mandatory |Mandatory |
|0x0003 |Tolerance             |Unsigned 16-bit Integer    |Read only  |Optional  |          |
|0x0010 |ScaledValue           |Signed 16-bit Integer      |Read only  |Optional  |Mandatory |
|0x0011 |MinScaledValue        |Signed 16-bit Integer      |Read only  |Optional  |          |
|0x0012 |MaxScaledValue        |Signed 16-bit Integer      |Read only  |Optional  |          |
|0x0013 |ScaledTolerance       |Unsigned 16-bit Integer    |Read only  |Optional  |Mandatory |
|0x0014 |Scale                 |Unsigned 8-bit Integer     |Read only  |Optional  |          |



#### MeasuredValue Attribute
MeasuredValue represents the pressure in kPa as follows:-

MeasuredValue = 10 x Pressure

Where -3276.7 kPa <= Pressure <= 3276.7 kPa, corresponding to a
MeasuredValue in the range 0x8001 to 0x7fff.

Note:- The maximum resolution this format allows is 0.1 kPa.

A MeasuredValue of 0x8000 indicates that the pressure measurement is invalid.
MeasuredValue is updated continuously as new measurements are made.

#### MinMeasuredValue Attribute
The MinMeasuredValue attribute indicates the minimum value of MeasuredValue
that can be measured. A value of 0x8000 means this attribute is not defined.

#### MaxMeasuredValue Attribute
The MaxMeasuredValue attribute indicates the maximum value of MeasuredValue
that can be measured. A value of 0x8000 means this attribute is not defined.

MaxMeasuredValue shall be greater than MinMeasuredValue.

MinMeasuredValue and MaxMeasuredValue define the range of the sensor.

#### Tolerance Attribute
The Tolerance attribute indicates the magnitude of the possible error that is
associated with MeasuredValue . The true value is located in the range
(MeasuredValue – Tolerance) to (MeasuredValue + Tolerance).

### Received

No cluster specific commands.

### Generated

No cluster specific commands.

## Flow measurement [0x0404]
The server cluster provides an interface to flow measurement functionality,
including configuration and provision of notifications of flow measurements.

### Attributes
|Id     |Name                  |Type                       |Access     |Implement |Reporting |
|-------|----------------------|---------------------------|-----------|----------|----------|
|0x0000 |MeasuredValue         |Unsigned 16-bit Integer    |Read only  |Mandatory |Mandatory |
|0x0001 |MinMeasuredValue      |Unsigned 16-bit Integer    |Read only  |Mandatory |          |
|0x0002 |MaxMeasuredValue      |Unsigned 16-bit Integer    |Read only  |Mandatory |          |
|0x0003 |Tolerance             |Unsigned 16-bit Integer    |Read only  |Optional  |Mandatory |

#### MeasuredValue Attribute
MeasuredValue represents the flow in m3/h as follows:-

MeasuredValue = 10 x Flow

Where 0 m3/h <= Flow <= 6,553.4 m3

/h, corresponding to a MeasuredValue in the
range 0 to 0xfffe.

The maximum resolution this format allows is 0.1 m3/h.

A MeasuredValue of 0xffff indicates that the pressure measurement is invalid.

MeasuredValue is updated continuously as new measurements are made.

#### MinMeasuredValue Attribute
The MinMeasuredValue attribute indicates the minimum value of MeasuredValue
that can be measured. A value of 0xffff means this attribute is not defined

#### MaxMeasuredValue Attribute
The MaxMeasuredValue attribute indicates the maximum value of MeasuredValue
that can be measured. A value of 0xffff means this attribute is not defined.

MaxMeasuredValue shall be greater than MinMeasuredValue.

MinMeasuredValue and MaxMeasuredValue define the range of the sensor

#### Tolerance Attribute
The Tolerance attribute indicates the magnitude of the possible error that is
associated with MeasuredValue . The true value is located in the range
(MeasuredValue – Tolerance) to (MeasuredValue + Tolerance).

### Received

No cluster specific commands.

### Generated

No cluster specific commands.

## Relative humidity measurement [0x0405]
The server cluster provides an interface to relative humidity measurement
functionality, including configuration and provision of notifications of relative
humidity measurements.

### Attributes
|Id     |Name                  |Type                       |Access     |Implement |Reporting |
|-------|----------------------|---------------------------|-----------|----------|----------|
|0x0000 |MeasuredValue         |Unsigned 16-bit Integer    |Read only  |Mandatory |Mandatory |
|0x0001 |MinMeasuredValue      |Unsigned 16-bit Integer    |Read only  |Mandatory |          |
|0x0002 |MaxMeasuredValue      |Unsigned 16-bit Integer    |Read only  |Mandatory |          |
|0x0003 |Tolerance             |Unsigned 16-bit Integer    |Read only  |Optional  |Mandatory |

#### MeasuredValue Attribute
MeasuredValue represents the relative humidity in % as follows:-

MeasuredValue = 100 x Relative humidity

Where 0% <= Relative humidity <= 100%, corresponding to a MeasuredValue in
the range 0 to 0x2710.

The maximum resolution this format allows is 0.01%.

A MeasuredValue of 0xffff indicates that the measurement is invalid.

MeasuredValue is updated continuously as new measurements are made.

#### MinMeasuredValue Attribute
The MinMeasuredValue attribute indicates the minimum value of MeasuredValue
that can be measured. A value of 0xffff means this attribute is not defined

#### MaxMeasuredValue Attribute
The MaxMeasuredValue attribute indicates the maximum value of MeasuredValue
that can be measured. A value of 0xffff means this attribute is not defined.

MaxMeasuredValue shall be greater than MinMeasuredValue. 

MinMeasuredValue and MaxMeasuredValue define the range of the sensor.

#### Tolerance Attribute
The Tolerance attribute indicates the magnitude of the possible error that is
associated with MeasuredValue . The true value is located in the range
(MeasuredValue – Tolerance) to (MeasuredValue + Tolerance).

### Received

No cluster specific commands.

### Generated

No cluster specific commands.

## Occupancy sensing [0x0406]
The cluster provides an interface to occupancy sensing functionality,
including configuration and provision of notifications of occupancy status. 

### Attributes
|Id     |Name                                   |Type                       |Access     |Implement |Reporting |
|-------|---------------------------------------|---------------------------|-----------|----------|----------|
|0x0000 |Occupancy                              |8-bit Bitmap               |Read only  |Mandatory |Mandatory |
|0x0001 |OccupancySensorType                    |8-bit Enumeration          |Read only  |Mandatory |          |
|0x0010 |PIROccupiedToUnoccupiedDelay           |Unsigned 8-bit Integer     |Read/Write |Optional  |          |
|0x0011 |PIRUnoccupiedToOccupiedDelay           |Unsigned 8-bit Integer     |Read/Write |Optional  |          |
|0x0020 |UltraSonicOccupiedToUnoccupiedDelay    |Unsigned 8-bit Integer     |Read/Write |Optional  |          |
|0x0021 |UltraSonicUnoccupiedToOccupiedDelay    |Unsigned 8-bit Integer     |Read/Write |Optional  |          |
|0x0022 |UltrasonicUnoccupiedToOccupiedThreshold|Unsigned 8-bit Integer     |Read/Write |Optional  |          |

#### Occupancy Attribute
The Occupancy attribute is a bitmap.

Bit 0 specifies the sensed occupancy as follows: 1 = occupied, 0 = unoccupied.
All other bits are reserved.

#### OccupancySensorType Attribute
The OccupancySensorType attribute specifies the type of the occupancy sensor.

#### PIROccupiedToUnoccupiedDelay Attribute
The PIROccupiedToUnoccupiedDelay attribute is 8-bits in length and specifies
the time delay, in seconds, before the PIR sensor changes to its occupied state
when the sensed area becomes unoccupied. This attribute, along with
PIRUnoccupiedToOccupiedTime, may be used to reduce sensor 'chatter' when
used in an area where occupation changes frequently.

#### PIRUnoccupiedToOccupiedDelay Attribute
The PIRUnoccupiedToOccupiedDelay attribute is 8-bits in length and specifies
the time delay, in seconds, before the PIR sensor changes to its unoccupied state
when the sensed area becomes occupied.

#### UltraSonicOccupiedToUnoccupiedDelay Attribute
The UltraSonicOccupiedToUnoccupiedTime attribute specifies the time delay, in
seconds, before the ultrasonic sensor changes to its occupied state when the
sensed area becomes unoccupied. This attribute, along with
UltraSonicUnoccupiedToOccupiedTime, may be used to reduce sensor 'chatter'
when used in an area where occupation changes frequently.

#### UltraSonicUnoccupiedToOccupiedDelay Attribute
The UltraSonicUnoccupiedToOccupiedTime attribute specifies the time delay, in
seconds, before the ultrasonic sensor changes to its unoccupied state when the
sensed area becomes occupied.


### Received

No cluster specific commands.

### Generated

No cluster specific commands.

# Security and Safety

## IAS Zone [0x0500]
The IAS Zone cluster defines an interface to the functionality of an IAS security
zone device. IAS Zone supports up to two alarm types per zone, low battery
reports and supervision of the IAS network. 

### Attributes

|Id     |Name                                        |Type                       |Access     |Implement |Reporting |
|-------|--------------------------------------------|---------------------------|-----------|----------|----------|
|0x0000 |ZoneState                                   |8-bit Enumeration          |Read only  |Mandatory |          |
|0x0001 |ZoneType                                    |16-bit Enumeration         |Read only  |Mandatory |          |
|0x0002 |ZoneStatus                                  |16-bit Bitmap              |Read only  |Mandatory |          |
|0x0010 |IASCIEAddress                               |IEEE Address               |Read/Write |Mandatory |          |
|0x0011 |ZoneID                                      |Unsigned 8-bit Integer     |Read/Write |Mandatory |          |
|0x0012 |NumberOfZoneSensitivityLevelsSupported      |Unsigned 8-bit Integer     |Read only  |Optional  |          |
|0x0013 |CurrentZoneSensitivityLevel                 |Unsigned 8-bit Integer     |Read/Write |Optional  |          |

#### ZoneState Attribute
The Zone State attribute defines if the device is currently enrolled with a CIE or not.

|Id     |Name                      |
|-------|--------------------------|
|0x0000 |Not Enrolled              |
|0x0001 |Enrolled                  |

#### ZoneType Attribute
The Zone Type dictates the meaning of Alarm1 and Alarm2 bits of the ZoneStatus attribute 

|Id     |Name                      |
|-------|--------------------------|
|0x0000 |Standard CIE              |
|0x000D |Motion Sensor             |
|0x0015 |Contact Switch            |
|0x0028 |Fire Sensor               |
|0x002A |Water Sensor              |
|0x002B |CO Sensor                 |
|0x002C |Personal Emergency Device |
|0x002D |Vibration Movement Sensor |
|0x010F |Remote Control            |
|0x0115 |Key Fob                   |
|0x021D |Key Pad                   |
|0x0225 |Standard Warning Device   |
|0x0226 |Glass Break Sensor        |
|0x0229 |Security Repeater         |

#### ZoneStatus Attribute
The ZoneStatus attribute is a bit map. Each bit defines the state of an alarm.

#### IASCIEAddress Attribute
The IAS_CIE_Address attribute specifies the address that commands generated by
the server shall be sent to. All commands received by the server must also come
from this address.

It is up to the zone's specific implementation to permit or deny change (write) of
this attribute at specific times. Also, it is up to the zone's specific implementation
to implement some auto-detect for the CIE (example: by requesting the ZigBee
cluster discovery service to locate a Zone Server cluster.) or require the
intervention of a CT in order to configure this attribute during installation.

#### ZoneID Attribute
A unique reference number allocated by the CIE at zone enrollment time.

Used by IAS devices to reference specific zones when communicating with the CIE. The ZoneID of each zone stays fixed until that zone is unenrolled.

#### NumberOfZoneSensitivityLevelsSupported Attribute
Provides the total number of sensitivity levels supported by the IAS Zone server. The purpose of this attribute is to support devices that can be configured to be more or less sensitive (e.g., motion sensor). It provides IAS Zone clients with the range of sensitivity levels that are supported so they MAY be presented to the user for configuration.

The values 0x00 and 0x01 are reserved because a device that has zero or one sensitivity level SHOULD NOT support this attribute because no configuration of the IAS Zone server’s sensitivity level is possible.

The meaning of each sensitivity level is manufacturer-specific. However, the sensitivity level of the IAS Zone server SHALL become more sensitive as they ascend. For example, if the server supports three sen- sitivity levels, then the value of this attribute would be 0x03 where 0x03 is more sensitive than 0x02, which is more sensitive than 0x01.

#### CurrentZoneSensitivityLevel Attribute
Allows an IAS Zone client to query and configure the IAS Zone server’s sensitivity level. Please see NumberOfZoneSensitivityLevelsSupported Attribute for more detail on how to interpret this attribute.

The default value 0x00 is the device’s default sensitivity level as configured by the manufacturer. It MAY correspond to the same sensitivity as another value in the NumberOfZoneSensitivityLevelsSupported, but this is the default sensitivity to be used if the CurrentZoneSensitivityLevel attribute is not otherwise configured by an IAS Zone client.


### Received

#### Zone Enroll Response Command [0x00]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Enroll response code       |8-bit Enumeration          |
|Zone ID                    |Unsigned 8-bit Integer     |

##### Enroll response code

|Id     |Name                      |
|-------|--------------------------|
|0x0000 |Success                   |
|0x0001 |Not Supported             |
|0x0002 |No Enroll Permit          |
|0x0003 |Too Many Zones            |

#### Initiate Normal Operation Mode Command [0x01]
Used to tell the IAS Zone server to commence normal operation mode.

Upon receipt, the IAS Zone server SHALL commence normal operational mode.

Any configurations and changes made (e.g., CurrentZoneSensitivityLevel attribute) to the IAS Zone server SHALL be retained.

Upon commencing normal operation mode, the IAS Zone server SHALL send a Zone Status Change Notification command updating the ZoneStatus attribute Test bit to zero (i.e., “operation mode”).


#### Initiate Test Mode Command [0x02]
Certain IAS Zone servers MAY have operational configurations that could be configured OTA or locally on the device. This command enables them to be remotely placed into a test mode so that the user or installer MAY configure their field of view, sensitivity, and other operational parameters. They MAY also verify the placement and proper operation of the IAS Zone server, which MAY have been placed in a difficult to reach location (i.e., making a physical input on the device impractical to trigger).

Another use case for this command is large deployments, especially commercial and industrial, where placing the entire IAS system into test mode instead of a single IAS Zone server is infeasible due to the vulnerabilities that might arise. This command enables only a single IAS Zone server to be placed into test mode.

The biggest limitation of this command is that most IAS Zone servers today are battery-powered sleepy nodes that cannot reliably receive commands. However, implementers MAY decide to program an IAS Zone server by factory default to maintain a limited duration of normal polling upon initialization/joining to a new network. Some IAS Zone servers MAY also have AC mains power and are able to receive commands. Some types of IAS Zone servers that MAY benefit from this command are: motion sensors and fire sensor/smoke alarm listeners (i.e., a device that listens for a non-communicating fire sensor to alarm and communicates this to the IAS CIE).

|Field Name                     |Data Type                  |
|-------------------------------|---------------------------|
|Test Mode Duration             |Unsigned 8-bit Integer     |
|Current Zone Sensitivity Level |Unsigned 8-bit Integer     |

##### Test Mode Duration Field
Specifies the duration, in seconds, for which the IAS Zone server SHALL operate in its test mode.

##### Current Zone Sensitivity Level Field
Specifies the sensitivity level the IAS Zone server SHALL use for the duration of the Test Mode and with which it must update its CurrentZoneSensitivityLevel attribute.

The permitted values of Current Zone Sensitivity Level are shown defined for the CurrentZoneSensitivityLevel Attribute.

### Generated

#### Zone Status Change Notification Command [0x00]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Zone Status                |16-bit Enumeration         |
|Extended Status            |8-bit Enumeration          |

#### Zone Enroll Request Command [0x01]
The Zone Enroll Request command is generated when a device embodying the Zone server cluster wishes
to be  enrolled as an active  alarm device. It  must do this immediately it has joined the network
(during commissioning).

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Zone Type                  |16-bit Enumeration         |
|Manufacturer Code          |Unsigned 16-bit Integer    |

## IAS ACE [0x0501]
The IAS ACE cluster defines an interface to the functionality of any Ancillary
Control Equipment of the IAS system. Using this cluster, a ZigBee enabled ACE
device can access a IAS CIE device and manipulate the IAS system, on behalf of a
level-2 user.

### Attributes

### Received

#### Arm Command [0x00]
On receipt of this command, the receiving device sets its arm mode according to the value of the Arm Mode field. It
is not guaranteed that an Arm command will succeed. Based on the current state of
the IAS CIE, and its related devices, the command can be rejected. The device SHALL generate an Arm Response command
to indicate the resulting armed state

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Arm Mode                   |8-bit Enumeration          |
|Arm/Disarm Code            |Character String           |
|Zone ID                    |Unsigned 8-bit Integer     |

##### Arm Mode

|Id     |Name                      |
|-------|--------------------------|
|0x0000 |Disarm                    |
|0x0001 |Arm Day                   |
|0x0002 |Arm Night                 |
|0x0003 |Arm All Zones             |


##### Arm/Disarm Code
The Arm/DisarmCode SHALL be a code entered into the ACE client (e.g., security keypad) or system by the
user upon arming/disarming. The server MAY validate the Arm/Disarm Code received from the IAS ACE client
in Arm command payload before arming or disarming the system. If the client does not have the capability
to input an Arm/Disarm Code (e.g., keyfob),or the system does not require one, the client SHALL a transmit
a string with a length of zero.

There is no minimum or maximum length to the Arm/Disarm Code; however, the
Arm/Disarm Code SHOULD be between four and eight alphanumeric characters in length.

The string encoding SHALL be UTF-8.

##### Zone ID
Zone ID is the index of the Zone in the CIE's zone table. If none is programmed, the Zone
ID default value SHALL be indicated in this field.

#### Bypass Command [0x01]

Provides IAS ACE clients with a method to send zone bypass requests to the IAS ACE server.
Bypassed zones MAYbe faulted or in alarm but will not trigger the security system to go into alarm.
For example, a user MAYwish to allow certain windows in his premises protected by an IAS Zone server to
be left open while the user leaves the premises. The user could bypass the IAS Zone server protecting
the window on his IAS ACE client (e.g., security keypad), and if the IAS ACE server indicates that zone is
successfully by-passed, arm his security system while he is away.

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Number of Zones            |Unsigned 8-bit integer     |
|Zone IDs                   |N x Unsigned 8-bit integer |
|Arm/Disarm Code            |Character String           |


##### Arm/Disarm Code 
The Arm/DisarmCode SHALL be a code entered into the ACE client (e.g., security keypad) or system by the
user upon arming/disarming. The server MAY validate the Arm/Disarm Code received from the IAS ACE client
in Arm command payload before arming or disarming the system. If the client does not have the capability
to input an Arm/Disarm Code (e.g., keyfob),or the system does not require one, the client SHALL a transmit
a string with a length of zero.

#### Emergency Command [0x02]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|

#### Fire Command [0x03]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|

#### Panic Command [0x04]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|

#### Get Zone ID Map Command [0x05]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|

#### Get Zone Information Command [0x06]

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Zone ID                    |Unsigned 8-bit Integer     |

#### Get Panel Status Command [0x07]
This command is used by ACE clients to request an update to the status (e.g., security
system arm state) of the ACE server (i.e., the IAS CIE). In particular, this command is
useful for battery-powered ACE clients with polling rates longer than the ZigBee standard
check-in rate.

On receipt of this command, the ACE server responds with the status of the security system.
The IAS ACE server SHALL generate a Get Panel Status Response command.

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|

#### Get Bypassed Zone List Command [0x08]
Provides IAS ACE clients with a way to retrieve the list of zones to be bypassed. This provides them with the ability
to provide greater local functionality (i.e., at the IAS ACE client) for users to modify the Bypassed Zone List and reduce
communications to the IAS ACE server when trying to arm the CIE security system.

#### Get Zone Status Command [0x09]
This command is used by ACE clients to request an update of the status of the IAS Zone devices managed by the ACE server
(i.e., the IAS CIE). In particular, this command is useful for battery-powered ACE clients with polling rates longer than
the ZigBee standard check-in rate. The command is similar to the Get Attributes Supported command in that it specifies a
starting Zone ID and a number of Zone IDs for which information is requested. Depending on the number of IAS Zone devices
managed by the IAS ACE server, sending the Zone Status of all zones MAY not fit into a single Get ZoneStatus Response command.
IAS ACE clients MAY need to send multiple Get Zone Status commands in order to get the information they seek.

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Starting Zone ID           |Unsigned 8-bit Integer     |
|Max Zone IDs               |Unsigned 8-bit Integer     |
|Zone Status Mask Flag      |Boolean                    |
|Zone Status Mask           |16-bit bitmap              |

##### Starting Zone ID
Specifies the starting Zone ID at which the IAS Client would like to obtain zone status information.

##### Max Zone IDs
Specifies the maximum number of Zone IDs and corresponding Zone Statuses that are to be returned by the IAS ACE server
when it responds with a Get Zone Status Response command

##### Zone Status Mask Flag
Functions as a query operand with the Zone Status Mask field. If set to zero (i.e., FALSE), the IAS ACE server SHALL include all Zone
IDs and their status, regardless of their Zone Status when it responds with a Get Zone Status Response command. If set to one (i.e., TRUE),
the IAS ACE server SHALL include only those Zone IDs whose Zone Status attribute is equal to one or more of the Zone Statuses requested
in the Zone Status Mask field of the Get Zone Status command.

Use of Zone Status Mask Flag and Zone Status Mask fields allow a client to obtain updated information for the subset of Zone IDs
they’re interested in, which is beneficial when the number of IAS Zone devices in a system is large.

##### Zone Status Mask
Coupled with the Zone Status Mask Flag field, functions as a mask to enable IAS ACE clients to get information about the Zone IDs whose
ZoneStatus attribute is equal to any of the bits indicated by the IAS ACE client in the Zone Status Mask field. The format of this field
is the same as the ZoneStatus attribute in the IAS Zone cluster. Per the Zone Status Mask Flag field, IAS ACE servers SHALL respond with
only the Zone IDs whose ZoneStatus attributes are equal to at least one of the Zone Status bits set in the Zone Status Mask field requested
by the IAS ACE client.For example, if the Zone Status Mask field set to “0x0003” would match IAS Zones whose ZoneStatus attributes are
0x0001, 0x0002, and 0x0003.

In other words, if a logical 'AND' between the Zone Status Mask field and the IAS Zone’s ZoneStatus attribute yields a non-zero result, 
the IAS ACE server SHALL include that IAS Zone in the Get Zone Status Response command

### Generated

#### Arm Response Command [0x00]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Arm Notification           |8-bit enumeration          |

##### Arm Notification

|Id     |Name                      |
|-------|--------------------------|
|0x0000 |All Zones Disarmed        |
|0x0001 |Day Zones Armed           |
|0x0002 |Night Zones Armed         |
|0x0003 |All Zones Armed           |
|0x0004 |Invalid Arm Code          |
|0x0005 |Not Ready To Arm          |
|0x0006 |Already Disarmed          |

#### Get Zone ID Map Response Command [0x01]
The 16 fields of the payload indicate whether each of the Zone IDs from 0 to 0xff is allocated or not. If bit n
of Zone ID Map section N is set to 1, then Zone ID (16 x N + n ) is allocated, else it is not allocated

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Zone ID Map section 0      |16-bit bitmap              |
|Zone ID Map section 1      |16-bit bitmap              |
|Zone ID Map section 2      |16-bit bitmap              |
|Zone ID Map section 3      |16-bit bitmap              |
|Zone ID Map section 4      |16-bit bitmap              |
|Zone ID Map section 5      |16-bit bitmap              |
|Zone ID Map section 6      |16-bit bitmap              |
|Zone ID Map section 7      |16-bit bitmap              |
|Zone ID Map section 8      |16-bit bitmap              |
|Zone ID Map section 9      |16-bit bitmap              |
|Zone ID Map section 10     |16-bit bitmap              |
|Zone ID Map section 11     |16-bit bitmap              |
|Zone ID Map section 12     |16-bit bitmap              |
|Zone ID Map section 13     |16-bit bitmap              |
|Zone ID Map section 14     |16-bit bitmap              |
|Zone ID Map section 15     |16-bit bitmap              |

#### Get Zone Information Response Command [0x02]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Zone ID                    |Unsigned 8-bit integer     |
|Zone Type                  |16-bit Enumeration         |
|IEEE address               |IEEE address               |
|Zone Label                 |Character String           |

##### Zone Label
Provides the ZoneLabel stored in the IAS CIE. If none is programmed, the IAS ACE server SHALL transmit a string with a length
of zero.There is no minimum or maximum length to the Zone Label field; however, the Zone Label SHOULD be between 16 to 24
alphanumeric characters in length.

The string encoding SHALL be UTF-8.

#### Zone Status Changed Command [0x03]
This command updates ACE clients in the system of changes to zone status recorded by the ACE server (e.g., IAS CIE device).
An IAS ACE server SHOULD send a Zone Status Changed command upon a change to an IAS Zone device’s ZoneStatus that it manages (i.e.,
IAS ACE server SHOULD send a Zone Status Changed command upon receipt of a Zone Status Change Notification command).

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Zone ID                    |Unsigned 8-bit Integer     |
|Zone Status                |16-bit enumeration         |
|Audible Notification       |8-bit enumeration          |
|Zone Label                 |Character String           |

##### Zone ID
The index of the Zone in the CIE’s zone table (Table 8-11). If none  is programmed, the  ZoneID  attribute default
value SHALL be indicated in this field.

##### Zone Label
Provides the ZoneLabel stored in the IAS CIE. If none is programmed, the IAS ACE server SHALL transmit a string with a length
of zero. There is no minimum or maximum length to the Zone Label field; however, the Zone Label SHOULD be between 16 to 24
alphanumeric characters in length.

#### Panel Status Changed Command [0x04]
This command updates ACE clients in the system of changes to panel status recorded by the ACE server (e.g., IAS CIE
device).Sending the Panel Status Changed command (vs.the Get Panel Status and Get Panel Status Response method) is
generally useful only when there are IAS ACE clients that data poll within the retry timeout of the network (e.g., less than
7.68 seconds).

An IAS ACE server SHALL send a Panel Status Changed command upon a change to the IAS CIE’s panel status (e.g.,
Disarmed to Arming Away/Stay/Night, Arming Away/Stay/Night to Armed, Armed to Disarmed) as defined in the Panel Status field.

When Panel Status is Arming Away/Stay/Night, an IAS ACE server SHOULD send Panel Status Changed commands every second in order to
update the Seconds Remaining. In some markets (e.g., North America), the final 10 seconds of the Arming Away/Stay/Night sequence
requires a separate audible notification (e.g., a double tone).

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Panel Status               |8-bit enumeration          |
|Seconds Remaining          |Unsigned 8-bit Integer     |
|Audible Notification       |8-bit enumeration          |
|Alarm Status               |8-bit enumeration          |

##### Panel Status
|Id     |Name                      |
|-------|--------------------------|
|0x0000 |Panel Disarmed            |
|0x0001 |Armed Stay                |
|0x0002 |Armed Night               |
|0x0003 |Armed Away                |
|0x0004 |Exit Delay                |
|0x0005 |Entry Delay               |
|0x0006 |Not Ready to Arm          |
|0x0007 |In Alarm                  |
|0x0008 |Arming Stay               |
|0x0009 |Arming Night              |
|0x000A |Arming Away               |

##### Seconds Remaining Parameter
Indicates the number of seconds remaining for  the server to be in the state indicated in the PanelStatus parameter.
The SecondsRemaining parameter SHALL be provided if the PanelStatus parameter has a value of 0x04 (Exit delay) or 0x05 (Entry delay).

The default value SHALL be 0x00.

##### Alarm Status
|Id     |Name                      |
|-------|--------------------------|
|0x0000 |No Alarm                  |
|0x0001 |Burglar                   |
|0x0002 |Fire                      |
|0x0003 |Emergency                 |
|0x0004 |Police Panic              |
|0x0005 |Fire Panic                |
|0x0006 |Emergency Panic           |

#### Get Panel Status Response Command [0x05]
This command updates requesting IAS ACE clients in the system of changes to the security panel status recorded by
the ACE server (e.g., IAS CIE device).

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Panel Status               |8-bit enumeration          |
|Seconds Remaining          |Unsigned 8-bit Integer     |
|Audible Notification       |8-bit enumeration          |
|Alarm Status               |8-bit enumeration          |


##### Panel Status
Defines the current status of the alarm panel.

|Id     |Name                      |
|-------|--------------------------|
|0x0000 |Panel Disarmed            |
|0x0001 |Armed Stay                |
|0x0002 |Armed Night               |
|0x0003 |Armed Away                |
|0x0004 |Exit Delay                |
|0x0005 |Entry Delay               |
|0x0006 |Not Ready to Arm          |
|0x0007 |In Alarm                  |
|0x0008 |Arming Stay               |
|0x0009 |Arming Night              |
|0x000A |Arming Away               |

##### Seconds Remaining
Indicates the number of seconds remaining for  the server to be in the state indicated in the PanelStatus parameter.
The SecondsRemaining parameter SHALL be provided if the PanelStatus parameter has a value of 0x04 (Exit delay) or 0x05 (Entry delay).

The default value SHALL be 0x00.

##### Audible Notification
Provide the ACE client with information on which type of audible notification it SHOULD make for the zone status change. This field is useful for telling the ACE client to play a standard chime or other audio indication or to mute and not sound an audible notification at all. This field also allows manufacturers to create additional audible alert types (e.g., dog barking, windchimes, conga drums) to enable users to customise their system.

##### Alarm Status
Provides the ACE client with information on the type of alarm the panel is in if its Panel Status field indicates it is “in alarm.” This field MAY be useful for ACE clients to display or otherwise initiate notification for users.

|Id     |Name                      |
|-------|--------------------------|
|0x0000 |No Alarm                  |
|0x0001 |Burglar                   |
|0x0002 |Fire                      |
|0x0003 |Emergency                 |
|0x0004 |Police Panic              |
|0x0005 |Fire Panic                |
|0x0006 |Emergency Panic           |

#### Set Bypassed Zone List Command [0x06]
Sets the list of bypassed zones on the IAS ACE client. This command can be sent either as a response to the
GetBypassedZoneList command or unsolicited when the list of bypassed zones changes on the ACE server.

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Zone ID                    |N x Unsigned 8-bit Integer |

##### Zone ID
Zone ID is the index of the Zone in the CIE's zone table and is an array of Zone IDs for each zone that is bypassed
where X is equal to the value of the Number of Zones field. There is no order imposed by the numbering of the Zone ID
field in this command payload. IAS ACE servers SHOULD provide the array of Zone IDs in ascending order.

#### Bypass Response Command [0x07]
Provides the response of the security panel to the request from the IAS ACE client to bypass zones via a Bypass command.

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Bypass Result              |N x Unsigned 8-bit Integer |

##### Bypass Result
An array of Zone IDs for each zone requested to be bypassed via the Bypass command where X is equal to the value of
the Number of Zones field. The order of results for Zone IDs SHALL be the same as the order of Zone IDs sent in
the Bypass command by the IAS ACE client.

|Id     |Name                      |
|-------|--------------------------|
|0x0000 |Zone Bypassed             |
|0x0001 |Zone Not Bypassed         |
|0x0002 |Not Allowed               |
|0x0003 |Invalid Zone Id           |
|0x0004 |Unknown Zone Id           |
|0x0005 |Invalid Arm Code          |


#### Get Zone Status Response [0x08]

This command updates requesting IAS ACE clients in the system of changes to the IAS Zone server statuses recorded
by the ACE server (e.g., IAS CIE device).

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Zone Status Complete       |Boolean                    |
|Number of zones            |Unsigned 8-bit Integer     |
|Ias Ace Zone Status        |Unsigned 8-bit Integer     |Structure[Number Of Zones] |
|Zone Id                    |Unsigned 8-bit Integer     |TODO
|Zone Status                |16-bit bitmap              |TODO

##### Zone Status Complete 
Indicates whether there are additional Zone IDs managed by the IAS ACE Server with Zone Status information to be obtained.
A value of zero (i.e. FALSE) indicates there are additional Zone IDs for which Zone Status information is available and
that the IAS ACE client SHOULD send another Get Zone Status command.A value of one (i.e. TRUE) indicates there are no
more Zone IDs for the IAS ACE client to query and the IAS ACE client has received all the Zone Status information for all
IAS Zones managed by the IAS ACE server.

The IAS ACE client SHOULD NOT typically send another Get Zone Status command.

## IAS WD [0x0502]
The IAS WD cluster provides an interface to the functionality of any Warning
Device equipment of the IAS system. Using this cluster, a ZigBee enabled CIE
device can access a ZigBee enabled IAS WD device and issue alarm warning
indications (siren, strobe lighting, etc.) when a system alarm condition is detected.

### Attributes

|Id     |Name                 |Type                       |Access     |Implement |Reporting |
|-------|---------------------|---------------------------|-----------|----------|----------|
|0x0000 |MaxDuration          |Unsigned 16-bit Integer    |Read/Write |Mandatory |          |
|0x0001 |ZoneType             |8-bit Enumeration          |Read only  |Mandatory |          |
|0x0002 |ZoneStatus           |16-bit Bitmap              |Read only  |Mandatory |          |
|0x0010 |IAS_CIE_Address      |IEEE Address               |Read/Write |Mandatory |          |

#### MaxDuration Attribute
The MaxDuration attribute specifies the maximum time in seconds that the siren
will sound continuously, regardless of start/stop commands.

### Received

#### Start Warning Command [0x00]
This command starts the WD operation. The WD alerts the surrounding area by
audible (siren) and visual (strobe) signals.

A Start Warning command shall always terminate the effect of any previous
command that is still current.

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Header                     |8-bit data                 |
|Warning duration           |Unsigned 16-bit integer    |

#### Squawk Command [0x02]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Header                     |8-bit data                 |

### Generated

# Protocol Interfaces

## Generic Tunnel [0x0600]
### Received
### Generated
## BACnet Protocol Tunnel [0x0601]
### Received
### Generated
## Analog Input (BACnet Regular) [0x0602] 
### Received 
### Generated 
## Analog Input (BACnet Extended) [0x0603] 
### Received 
### Generated 
## Analog Output (BACnet Regular) [0x0604] 
### Received 
### Generated 
## Analog Output (BACnet Extended) [0x0605] 
### Received 
### Generated 
## Analog Value (BACnet Regular) [0x0606] 
### Received 
### Generated 
## Analog Value (BACnet Extended) [0x0607] 
### Received 
### Generated 
## Binary Input (BACnet Regular) [0x0608] 
### Received 
### Generated 
## Binary Input (BACnet Extended) [0x0609] 
### Received 
### Generated 
## Binary Output (BACnet Regular) [0x060a] 
### Received 
### Generated 
## Binary Output (BACnet Extended) [0x060b] 
### Received 
### Generated 
## Binary Value (BACnet Regular) [0x060c] 
### Received 
### Generated 
## Binary Value (BACnet Extended) [0x060d] 
### Received 
### Generated 
## Multistate Input (BACnet Regular) [0x060e] 
### Received 
### Generated 
## Multistate Input (BACnet Extended) [0x060f] 
### Received 
### Generated 
## Multistate Output (BACnet Regular) [0x0610] 
### Received 
### Generated 
## Multistate Output (BACnet Extended) [0x0611] 
### Received 
### Generated 
## Multistate Value (BACnet Regular) [0x0612] 
### Received 
### Generated 
## Multistate Value (BACnet Extended) [0x0613] 
### Received 
### Generated

# Smart Energy

## Price [0x0700]
### Received
### Generated

## Demand Response and Load Control [0x0701] 
### Received 
### Generated

## Metering [0x0702]
### Received 
### Generated

## Messaging [0x0703]
### Received 
### Generated

## Tunneling [0x0704] 
### Received
### Generated

## Key Establishment [0x0800]
### Received
### Generated

# Appliances
## Appliance Control [0x001B]
### Received
### Generated

## Appliance Identification [0x0B00]
### Received
### Generated

## Appliance Events and Alerts [0x0B02]
### Received
### Generated

## Appliance Statistics [0x0B03]
### Received
### Generated

## Electrical Measurement [0x0B04]
This cluster provides a mechanism for querying data about the electrical properties as measured
by the device. This cluster may be implemented on any device type and be implemented on a per-endpoint
basis. For example, a power  strip device could represent each outlet on a  different endpoint and
report electrical  information for each individual outlet. The only caveat is that if you implement
an attribute that has an associated multiplier and divisor, then you must implement the associated
multiplier and divisor attributes. For example if you implement DCVoltage, you must also implement
DCVoltageMultiplier and DCVoltageDivisor.

If you are interested in reading information about the power supply or battery level on the device,
please see the Power Configuration cluster.

### Attributes

|Id     |Name                             |Type                       |Access     |Implement |Reporting |
|-------|---------------------------------|---------------------------|-----------|----------|----------|
|0x0000 |MeasurementType                  |32-bit Bitmap              |Read only  |Mandatory |          |
|0x0300 |ACFrequency                      |Unsigned 16-bit Integer    |Read only  |Optional  |          |
|0x0304 |TotalActivePower                 |Signed 32-bit Integer      |Read only  |Optional  |          |
|0x0305 |TotalReactivePower               |Signed 32-bit Integer      |Read only  |Optional  |          |
|0x0306 |TotalApparentPower               |Unsigned 32-bit Integer    |Read only  |Optional  |          |
|0x0505 |RMSVoltage                       |Unsigned 16-bit Integer    |Read only  |Optional  |          |
|0x0508 |RMSCurrent                       |Unsigned 16-bit Integer    |Read only  |Optional  |          |
|0x050B |ActivePower                      |Signed 16-bit Integer      |Read only  |Optional  |          |
|0x0600 |ACVoltageMultiplier              |Unsigned 16-bit Integer    |Read only  |Optional  |          |
|0x0601 |ACVoltageDivisor                 |Unsigned 16-bit Integer    |Read only  |Optional  |          |
|0x0602 |ACCurrentMultiplier              |Unsigned 16-bit Integer    |Read only  |Optional  |          |
|0x0603 |ACCurrentDivisor                 |Unsigned 16-bit Integer    |Read only  |Optional  |          |
|0x0604 |ACPowerMultiplier                |Unsigned 16-bit Integer    |Read only  |Optional  |          |
|0x0605 |ACPowerDivisor                   |Unsigned 16-bit Integer    |Read only  |Optional  |          |



#### MeasurementType Attribute
This attribute indicates a device’s measurement capabilities. This will be indicated by setting
the desire measurement bits to 1.

|Id     |Name                      |
|-------|--------------------------|
|0x0000 |AC Active Measurement     |
|0x0001 |AC Reactive Measurement   |
|0x0002 |AC Apparent Measurement   |
|0x0004 |Phase A Measurement       |
|0x0008 |Phase B Measurement       |
|0x0010 |Phase C Measurement       |
|0x0020 |DC Measurement            |
|0x0040 |Harmonics Measurement     |
|0x0080 |Power Quality Measurement |

####  ACFrequency Attribute
The ACFrequency attribute represents the most recent AC Frequency reading in Hertz (Hz).
If the frequency cannot be measured, a value of 0xFFFF is returned. 

#### TotalActivePower Attribute
Active power represents the current demand of active power delivered or received at the
premises, in kW. Positive values indicate power delivered to the premises where negative
values indicate power received from the premises. In case if device is capable of measuring
multi elements or phases then this will be net active power value.

#### TotalReactivePower Attribute
Reactive power represents the  current demand of reactive power delivered or 
received at the premises, in kVAr. Positive values indicate power delivered to
the premises where negative values indicate power received from the premises. In
case if device is capable of measuring multi elements or phases then this will be net reactive
power value.

#### TotalApparentPower Attribute
Represents the current demand of apparent power, in kVA. In case if device is capable of
measuring multi elements or phases then this will be net apparent power value.

#### RMSVoltage Attribute
Represents the  most recent RMS voltage reading in Volts (V). If the RMS voltage cannot be
measured, a value of 0xFFFF is returned.

#### RMSCurrent Attribute
Represents the most recent RMS current reading in Amps (A). If the power cannot be measured,
a value of 0xFFFF is returned. 

#### ActivePower Attribute
Represents the single phase or Phase A, current demand of active power delivered or received at
the premises, in Watts (W). Positive values indicate power delivered to the premises where negative
values indicate power received from the premises.

#### ACCurrentMultiplier Attribute
Provides a value to be multiplied against the InstantaneousCurrent and RMSCurrentattributes. 
his attribute must be used in conjunction with the ACCurrentDivisorattribute. 0x0000 is an invalid value for this attribute.

#### ACCurrentDivisor Attribute
Provides  a  value  to  be  divided  against the ACCurrent, InstantaneousCurrent and
RMSCurrentattributes. This attribute must be used in conjunction with the ACCurrentMultiplierattribute
0x0000 is an invalid value for this attribute.
 
#### ACPowerMultiplier Attribute
Provides a value to be multiplied against the InstantaneousPower and ActivePowerattributes.
This attribute must be used in conjunction with the ACPowerDivisorattribute. 0x0000 is an invalid
value for this attribute

#### ACPowerDivisor Attribute
Provides a value to be divided against the InstantaneousPower and ActivePowerattributes.
This  attribute must be used in conjunction with the ACPowerMultiplierattribute. 0x0000 is an
invalid value for this attribute.
 
### Received
### Generated

## Diagnostics [0x0B05]

### Attributes

|Id     |Name                             |Type                       |Access     |Implement |Reporting |
|-------|---------------------------------|---------------------------|-----------|----------|----------|
|0x0100 |MacRxBcast                       |Unsigned 32-bit Integer    |Read only  |Mandatory |          |
|0x0101 |MacTxBcast                       |Unsigned 32-bit Integer    |Read only  |Mandatory |          |
|0x0102 |MacRxUcast                       |Unsigned 32-bit Integer    |Read only  |Mandatory |          |
|0x0103 |MacTxUcast                       |Unsigned 32-bit Integer    |Read only  |Mandatory |          |
|0x0104 |MacTxUcastRetry                  |Unsigned 16-bit Integer    |Read only  |Mandatory |          |
|0x0105 |MacTxUcastFail                   |Unsigned 16-bit Integer    |Read only  |Mandatory |          |
|0x0106 |APSRxBcast                       |Unsigned 16-bit Integer    |Read only  |Mandatory |          |
|0x0107 |APSTxBcast                       |Unsigned 16-bit Integer    |Read only  |Mandatory |          |
|0x0108 |APSRxUcast                       |Unsigned 16-bit Integer    |Read only  |Mandatory |          |
|0x0109 |APSTxUcastSuccess                |Unsigned 16-bit Integer    |Read only  |Mandatory |          |
|0x010A |APSTxUcastRetry                  |Unsigned 16-bit Integer    |Read only  |Mandatory |          |
|0x010B |APSTxUcastFail                   |Unsigned 16-bit Integer    |Read only  |Mandatory |          |
|0x010C |RouteDiscInitiated               |Unsigned 16-bit Integer    |Read only  |Mandatory |          |
|0x010D |NeighborAdded                    |Unsigned 16-bit Integer    |Read only  |Mandatory |          |
|0x010E |NeighborRemoved                  |Unsigned 16-bit Integer    |Read only  |Mandatory |          |
|0x010F |NeighborStale                    |Unsigned 16-bit Integer    |Read only  |Mandatory |          |
|0x0110 |JoinIndication                   |Unsigned 16-bit Integer    |Read only  |Mandatory |          |
|0x0111 |ChildMoved                       |Unsigned 16-bit Integer    |Read only  |Mandatory |          |
|0x0112 |NWKFCFailure                     |Unsigned 16-bit Integer    |Read only  |Mandatory |          |
|0x0113 |APSFCFailure                     |Unsigned 16-bit Integer    |Read only  |Mandatory |          |
|0x0114 |APSUnauthorizedKey               |Unsigned 16-bit Integer    |Read only  |Mandatory |          |
|0x0115 |NWKDecryptFailures               |Unsigned 16-bit Integer    |Read only  |Mandatory |          |
|0x0116 |APSDecryptFailures               |Unsigned 16-bit Integer    |Read only  |Mandatory |          |
|0x0117 |PacketBufferAllocateFailures     |Unsigned 16-bit Integer    |Read only  |Mandatory |          |
|0x0118 |RelayedUcast                     |Unsigned 16-bit Integer    |Read only  |Mandatory |          |
|0x0119 |PhytoMACqueuelimitreached        |Unsigned 16-bit Integer    |Read only  |Mandatory |          |
|0x011A |PacketValidatedropcount          |Unsigned 16-bit Integer    |Read only  |Mandatory |          |
|0x011B |AverageMACRetryPerAPSMessageSent |Unsigned 16-bit Integer    |Read only  |Mandatory |          |
|0x011C |LastMessageLQI                   |Unsigned 8-bit Integer     |Read only  |Mandatory |          |
|0x011D |LastMessageRSSI                  |Signed 8-bit Integer       |Read only  |Mandatory |          |

### Received
### Generated

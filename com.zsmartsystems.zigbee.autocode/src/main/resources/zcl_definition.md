# Home Automation [0x0104]

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

#### LocationDescription Attribute
The LocationDescription attribute is a maximum of 16 bytes in length and describes
the physical location of the device as a ZigBee character string. 

#### PhysicalEnvironment Attribute
The PhysicalEnvironment attribute is 8 bits in length and specifies the type of
physical environment in which the device will operate. 

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


### Received

#### Reset to Factory Defaults Command [0x00]
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
|0x4000 |GlobalSceneControl   |Boolean                    |Read/Write |          |Mandatory |
|0x4001 |OffTime              |Unsigned 16-bit integer    |Read/Write |          |Mandatory |
|0x4002 |OffWaitTime          |Unsigned 16-bit integer    |Read/Write |          |Mandatory |



#### OnOff Attribute
The OnOff attribute has the following values: 0 = Off, 1 = On

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

### Generated

No cluster specific commands.

## On/off Switch Configuration [0x0007]
Attributes and commands for configuring On/Off switching devices

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

### Received

No cluster specific commands.

### Generated

No cluster specific commands.

## Multistate Output (Basic) [0x0013]

### Received

No cluster specific commands.

### Generated

No cluster specific commands.

## Multistate Value (Basic) [0x0014]

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

# Closures
## Shade Configuration [0x0100]

### Received

No cluster specific commands.

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

|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Number of Transitions      |8-bit Enumeration          |
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

## Color control [0x0300]
This cluster provides an interface for changing the color of a light. Color is
specified according to the Commission Internationale de l'Éclairage (CIE)
specification CIE 1931 Color Space, [B4]. Color control is carried out in terms of
x,y values, as defined by this specification. 

### Attributes
|Id     |Name                  |Type                       |Access     |Implement |Reporting |
|-------|----------------------|---------------------------|-----------|----------|----------|
|0x0000 |CurrentHue            |Unsigned 8-bit Integer     |Read only  |Optional  |Mandatory |
|0x0001 |CurrentSaturation     |Unsigned 8-bit Integer     |Read only  |Optional  |Mandatory |
|0x0002 |RemainingTime         |Unsigned 16-bit Integer    |Read only  |Optional  |          |
|0x0003 |CurrentX              |Unsigned 16-bit Integer    |Read only  |Mandatory |Mandatory |
|0x0004 |CurrentY              |Unsigned 16-bit Integer    |Read only  |Mandatory |Mandatory |
|0x0005 |DriftCompensation     |8-bit Enumeration          |Read only  |Optional  |          |
|0x0006 |CompensationText      |Character string           |Read only  |Optional  |          |
|0x0007 |ColorTemperature      |Unsigned 16-bit Integer    |Read only  |Optional  |Mandatory |
|0x0008 |ColorMode             |8-bit Enumeration          |Read only  |Optional  |          |


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

0x00 CurrentHue and CurrentSaturation
0x01 CurrentX and CurrentY
0x02 ColorTemperatureMireds

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

#### PIROccupiedToUnoccupiedTime Attribute
The PIROccupiedToUnoccupiedDelay attribute is 8-bits in length and specifies
the time delay, in seconds, before the PIR sensor changes to its occupied state
when the sensed area becomes unoccupied. This attribute, along with
PIRUnoccupiedToOccupiedTime, may be used to reduce sensor 'chatter' when
used in an area where occupation changes frequently.

#### PIRUnoccupiedToOccupiedTime Attribute
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
|0x0011 |ZoneID                                      |Unsigned 8-bit Integer     |Read only  |Mandatory |          |
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
|0x0028 |Water Sensor              |
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
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Arm Mode                   |8-bit Enumeration          |

#### Bypass Command [0x01]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Number of Zones            |Unsigned 8-bit integer     |
|Zone IDs                   |N X Unsigned 8-bit integer |

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

### Generated

#### Arm Response Command [0x00]
|Field Name                 |Data Type                  |
|---------------------------|---------------------------|
|Arm Notification           |8-bit enumeration          |

#### Get Zone ID Map Response Command [0x01]
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

 [![Travis Build Status](https://travis-ci.org/zsmartsystems/com.zsmartsystems.zigbee.svg?branch=master)](https://travis-ci.org/zsmartsystems/com.zsmartsystems.zigbee) [![Codacy Coverage Badge](https://api.codacy.com/project/badge/Coverage/b3e149e7838947c9967f50ff3b2a01eb)](https://www.codacy.com/app/zsmartsystems/com-zsmartsystems-zigbee?utm_source=github.com&utm_medium=referral&utm_content=zsmartsystems/com.zsmartsystems.zigbee&utm_campaign=Badge_Coverage) [![Codacy Static Analyses Badge](https://api.codacy.com/project/badge/Grade/b3e149e7838947c9967f50ff3b2a01eb)](https://www.codacy.com/app/zsmartsystems/com-zsmartsystems-zigbee?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=zsmartsystems/com.zsmartsystems.zigbee&amp;utm_campaign=Badge_Grade) [![CLA Assistant Badge](https://cla-assistant.io/readme/badge/zsmartsystems/com.zsmartsystems.zigbee)](https://cla-assistant.io/zsmartsystems/com.zsmartsystems.zigbee) [ ![Download](https://api.bintray.com/packages/zsmartsystems/com.zsmartsystems/zigbee/images/download.svg) ](https://bintray.com/zsmartsystems/com.zsmartsystems/zigbee/_latestVersion) [![Total alerts](https://img.shields.io/lgtm/alerts/g/zsmartsystems/com.zsmartsystems.zigbee.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/zsmartsystems/com.zsmartsystems.zigbee/alerts/)

# Overview

This project aims to provide a ZigBee compatible framework written in Java and compatible with Android. It provides a ZigBee Cluster Library implementation, and network management functions, aiming to provide a full featured ZigBee framework that can be implemented within end systems. This repository aims to provide a high quality framework, with quality documentation and a thorough set of test cases to ensure against regression.

Packages are broken down to provide the main ZigBee framework, separate packages for dongles, and a console application that allows full use of the framework. The bundles include OSGi headers for use within an OSGi framework.

Minimum Android support is currently API19, Android 4.4 (KitKat). It is highly recommended to move to Android 8 as this may become the minimum requirement in the future to allow the framework to use the newer classes available from API26.
 
## Features

Features include -:

* ZigBee Cluster Library
* Over-The-Air firmware upgrade

### ZigBee Cluster Library

The framework includes a ZigBee Cluster Library that is auto-generated from an XML definition file.

The following clusters are currently supported -:

| ID | Cluster | Description |
|----|---------|-------------|
| 0000 | BASIC | This cluster supports an interface to the node or physical device. It provides attributes and commands for determining basic information, setting user information such as location, and resetting to factory defaults. |
| 0001 | POWER_CONFIGURATION | Attributes for determining detailed information about a device’s power source(s), and for configuring under/over voltage alarms. |
| 0003 | IDENTIFY | Attributes and commands to put a device into an Identification mode (e.g. flashing a light), that indicates to an observer – e.g. an installer - which of several devices it is, also to request any device that is identifying itself to respond to the initiator. |
| 0004 | GROUPS | The ZigBee specification provides the capability for group addressing. That is, any endpoint on any device may be assigned to one or more groups, each labeled with a 16-bit identifier (0x0001 – 0xfff7), which acts for all intents and purposes like a network address. Once a group is established, frames, sent using the APSDE-DATA.request primitive and having a DstAddrMode of 0x01, denoting group addressing, will be delivered to every endpoint assigned to the group address named in the DstAddr parameter of the outgoing APSDE-DATA.request primitive on every device in the network for which there are such endpoints. |
| 0005 | SCENES | The scenes cluster provides attributes and commands for setting up and recalling scenes. Each scene corresponds to a set of stored values of specified attributes for one or more clusters on the same end point as the scenes cluster. |
| 0006 | ON_OFF | Attributes and commands for switching devices between ‘On’ and ‘Off’ states. |
| 0007 | ON_OFF_SWITCH_CONFIGURATION | Attributes and commands for configuring On/Off switching devices |
| 0008 | LEVEL_CONTROL | This cluster provides an interface for controlling a characteristic of a device that can be set to a level, for example the brightness of a light, the degree of closure of a door, or the power output of a heater. |
| 0009 | ALARMS | Attributes and commands for sending alarm notifications and configuring alarm functionality. |
| 000A | TIME | This cluster provides a basic interface to a real-time clock. The clock time MAY be read and also written, in order to synchronize the clock (as close as practical) to a time standard. This time standard is the number of seconds since 0 hrs 0 mins 0 sec on 1st January 2000 UTC (Universal Coordinated Time). |
| 000B | RSSI_LOCATION | This cluster provides a means for exchanging Received Signal Strength Indication (RSSI) information among one hop devices as well as messages to report RSSI data to a centralized device that collects all the RSSI data in the network. |
| 000C | ANALOG_INPUT_BASIC | The Analog Input (Basic) cluster provides an interface for reading the value of an analog measurement and accessing various characteristics of that measurement. The cluster is typically used to implement a sensor that measures an analog physical quantity. |
| 000F | BINARY_INPUT_BASIC | The Binary Input (Basic) cluster provides an interface for reading the value of a binary measurement and accessing various characteristics of that measurement. The cluster is typically used to implement a sensor that measures a two-state physical quantity. |
| 0012 | MULTISTATE_INPUT_BASIC | The Multistate Input (Basic) cluster provides an interface for reading the value of a multistate measurement and accessing various characteristics of that measurement. The cluster is typically used to implement a sensor that measures a physical quantity that can take on one of a number of discrete states. |
| 0013 | MULTISTATE_OUTPUT_BASIC | The Multistate Output (Basic) cluster provides an interface for setting the value of an output that can take one of a number of discrete values, and accessing characteristics of that value. |
| 0014 | MULTISTATE_VALUE_BASIC | The Multistate Value (Basic) cluster provides an interface for setting a multistate value, typically used as a control system parameter, and accessing characteristics of that value. |
| 0019 | OTA_UPGRADE | The cluster provides a standard way to upgrade devices in the network via OTA messages. Thus the upgrade process MAY be performed between two devices from different manufacturers. Devices are required to have application bootloader and additional memory space in order to successfully implement the cluster. |
| 0020 | POLL_CONTROL | This cluster provides a mechanism for the management of an end device’s MAC Data Request rate. For the purposes of this cluster, the term “poll” always refers to the sending of a MAC Data Request from the end device to the end device’s parent. This cluster can be used for instance by a configuration device to make an end device responsive for a certain period of time so that the device can be managed by the controller. This cluster is composed of a client and server. The end device implements the server side of this cluster. The server side contains several attributes related to the MAC Data Request rate for the device. The client side implements commands used to manage the poll rate for the device. The end device which implements the server side of this cluster sends a query to the client on a predetermined interval to see if the client would like to manage the poll period of the end device in question. When the client side of the cluster hears from the server it has the opportunity to respond with configuration data to either put the end device in a short poll mode or let the end device continue to function normally. |
| 0101 | DOOR_LOCK | The door lock cluster provides an interface to a generic way to secure a door. The physical object that provides the locking functionality is abstracted from the cluster. The cluster has a small list of mandatory attributes and functions and a list of optional features. |
| 0102 | WINDOW_COVERING | Provides an interface for controlling and adjusting automatic window coverings. |
| 0201 | THERMOSTAT | This cluster provides an interface to the functionality of a thermostat. |
| 0202 | FAN_CONTROL | This cluster specifies an interface to control the speed of a fan as part of a heating / cooling system. |
| 0203 | DEHUMIDIFICATION_CONTROL | This cluster provides an interface to dehumidification functionality. |
| 0204 | THERMOSTAT_USER_INTERFACE_CONFIGURATION | This cluster provides an interface to allow configuration of the user interface for a thermostat, or a thermostat controller device, that supports a keypad and LCD screen. |
| 0300 | COLOR_CONTROL | This cluster provides an interface for changing the color of a light. Color is specified according to the Commission Internationale de l'Éclairage (CIE) specification CIE 1931 Color Space. Color control is carried out in terms of x,y values, as defined by this specification. |
| 0400 | ILLUMINANCE_MEASUREMENT | The cluster provides an interface to illuminance measurement functionality, including configuration and provision of notifications of illuminance measurements. |
| 0401 | ILLUMINANCE_LEVEL_SENSING | The cluster provides an interface to illuminance level sensing functionality, including configuration and provision of notifications of whether the illuminance is within, above or below a target band. |
| 0402 | TEMPERATURE_MEASUREMENT | The server cluster provides an interface to temperature measurement functionality, including configuration and provision of notifications of temperature measurements. |
| 0403 | PRESSURE_MEASUREMENT | The cluster provides an interface to pressure measurement functionality, including configuration and provision of notifications of pressure measurements. |
| 0404 | FLOW_MEASUREMENT | The server cluster provides an interface to flow measurement functionality, including configuration and provision of notifications of flow measurements. |
| 0405 | RELATIVE_HUMIDITY_MEASUREMENT | The server cluster provides an interface to relative humidity measurement functionality, including configuration and provision of notifications of relative humidity measurements. |
| 0406 | OCCUPANCY_SENSING | The cluster provides an interface to occupancy sensing functionality, including configuration and provision of notifications of occupancy status. |
| 0500 | IAS_ZONE | The IAS Zone cluster defines an interface to the functionality of an IAS security zone device. IAS Zone supports up to two alarm types per zone, low battery reports and supervision of the IAS network. |
| 0501 | IAS_ACE | The IAS ACE cluster defines an interface to the functionality of any Ancillary Control Equipment of the IAS system. Using this cluster, a ZigBee enabled ACE device can access a IAS CIE device and manipulate the IAS system, on behalf of a level-2 user. |
| 0502 | IAS_WD | The IAS WD cluster provides an interface to the functionality of any Warning Device equipment of the IAS system. Using this cluster, a ZigBee enabled CIE device can access a ZigBee enabled IAS WD device and issue alarm warning indications (siren, strobe lighting, etc.) when a system alarm condition is detected. |
| 0700 | PRICE | The Price Cluster provides the mechanism for communicating Gas, Energy, or Water pricing information within the premises. This pricing information is distributed to the ESI from either the utilities or from regional energy providers. The ESI conveys the information (via the Price Cluster mechanisms) to other Smart Energy devices. |
| 0701 | DEMAND_RESPONSE_AND_LOAD_CONTROL | This cluster provides an interface to the functionality of Smart Energy Demand Response and Load Control. Devices targeted by this cluster include thermostats and devices that support load control. |
| 0702 | METERING | The Metering Cluster provides a mechanism to retrieve usage information from Electric, Gas, Water, and potentially Thermal metering devices. These devices can operate on either battery or mains power, and can have a wide variety of sophistication. The Metering Cluster is designed to provide flexibility while limiting capabilities to a set number of metered information types. More advanced forms or data sets from metering devices will be supported in the Smart Energy Tunneling Cluster |
| 0703 | MESSAGING | This cluster provides an interface for passing text messages between ZigBee devices. Messages are expected to be delivered via the ESI and then unicast to all individually registered devices implementing the Messaging Cluster on the ZigBee network, or just made available to all devices for later pickup. Nested and overlapping messages are not allowed. The current active message will be replaced if a new message is received by the ESI. |
| 0704 | SMART_ENERGY_TUNNELING | The tunneling cluster provides an interface for tunneling protocols. It is comprised of commands and attributes required to transport any existing metering communication protocol within the payload of standard ZigBee frames (including the handling of issues such as addressing, fragmentation and flow control). Examples for such protocols are DLMS/COSEM, IEC61107, ANSI C12, M-Bus, ClimateTalk etc. |
| 0705 | PREPAYMENT | The Prepayment Cluster provides the facility to pass messages relating to the accounting functionality of a meter between devices on the HAN. It allows for the implementation of a system conforming to the set of standards relating to Payment Electricity Meters (IEC 62055) and also for the case where the accounting function is remote from the meter. Prepayment is used in situations where the supply of a service may be interrupted or enabled under the control of the meter or system in relation to a payment tariff. The accounting process may be within the meter or elsewhere in the system. The amount of available credit is decremented as the service is consumed and is incremented through payments made by the consumer. Such a system allows the consumer to better manage their energy consumption and reduces the risk of bad debt owing to the supplier. |
| 0800 | KEY_ESTABLISHMENT | This cluster provides attributes and commands to perform mutual authentication and establish keys between two ZigBee devices. |
| 0B04 | ELECTRICAL_MEASUREMENT | This cluster provides a mechanism for querying data about the electrical properties as measured by the device. This cluster may be implemented on any device type and be implemented on a per-endpoint basis. For example, a power  strip device could represent each outlet on a  different endpoint and report electrical  information for each individual outlet. The only caveat is that if you implement an attribute that has an associated multiplier and divisor, then you must implement the associated multiplier and divisor attributes. For example if you implement DCVoltage, you must also implement DCVoltageMultiplier and DCVoltageDivisor. |
| 0B05 | DIAGNOSTICS | The diagnostics cluster provides access to information regarding the operation of the ZigBee stack over time. This information is useful to installers and other network administrators who wish to know how a particular device is functioning on the network. |

# Packages

The framework implements a package structure that allows efficient use of re-usable components in a number of different applications.

| Package                                           | Description                                          |
|---------------------------------------------------|------------------------------------------------------|
|com.zsmartsystems.zigbee                           |The main framework and cluster library implementation |
|com.zsmartsystems.zigbee.autocode                  |Code generator for the ZigBee cluster library classes |
|com.zsmartsystems.zigbee.dongle.cc2531             |Dongle driver for the Texas Instruments ZNP CC2531    |
|com.zsmartsystems.zigbee.dongle.conbee             |Dongle driver for the Dresden Electronics Conbee      |
|com.zsmartsystems.zigbee.dongle.ember              |Dongle driver for the Silabs EZSP Network Co-Processor|
|com.zsmartsystems.zigbee.dongle.ember.autocode     |Code generator for the Ember NCP dongle commands      |
|com.zsmartsystems.zigbee.dongle.telegesis          |Dongle driver for the Telegesis dongle                |
|com.zsmartsystems.zigbee.dongle.telegesis.autocode |Code generator for the Telegesis dongle commands      |
|com.zsmartsystems.zigbee.dongle.xbee               |Dongle driver for the Digi XBee dongle                | 
|com.zsmartsystems.zigbee.dongle.xbee.autocode      |Code generator for the XBee dongle commands           |
|com.zsmartsystems.zigbee.console                   |Console commands for the general framework            |
|com.zsmartsystems.zigbee.console.ember             |Console commands for the Silabs Ember NCP             |
|com.zsmartsystems.zigbee.console.main              |Main CLI console application                          |
|com.zsmartsystems.zigbee.serial                    |Serial driver implementation                          |
|com.zsmartsystems.zigbee.test                      |Overall tests and code coverage                       |

## Testing

The framework incorporates a lot of unit testing, ensuring real data received from devices can be correctly decoded. When an error is detected following operation with real devices, a test case is normally added to reproduce the error and then it is fixed. 

## Logging

A log viewer to decode the logs and present them in a usable format is available [here](http://www.cd-jackson.com/index.php/openhab/zigbee-log-viewer). This provides filtering of data at different levels and filtering by node address.

## ZigBee Stack

## Dongles

### Silicon Labs Ember EM35x / EFR32

The library supports the Silicon Labs EZSP protocol using ASH or SPI protocols over a serial interface. The implementation of the SPI protocol assumes that the SPI provides a TTY-like software interface to the application, or is otherwise abstracted via the ```ZigBeePort``` interface.  

It is worth noting that EM3588 devices that have an embedded USB core will likely work with any baud rate, where dongles using external USB interface (eg CP2102 used with an EM3581) will likely require a specific baud rate.

Currently there are two main NCP images - one that supports hardware flow control with a baud rate of 115200, and one that supports software flow control with a rate of 57600.

#### Ember NCP configuration

The library provide a standard set of configuration constants to configure the NCP for use as a coordinator. There are two methods available in the Ember driver to manipulate the configuration maps ```updateDefaultConfiguration``` and ```updateDefaultPolicy```. The configuration is sent to the NCP during the initialisation sequence which is performed when calling the ```ZigBeeNetworkManager.initialize()``` method, so any changes to configuration must be performed prior to this.

The Ember dongle driver includes a public method ```getEmberNcp()``` which returns a ```EmberNcp``` class. This class provides high level methods for interacting directly with the NCP.

#### Ember MfgLib use

The library provides access to the ```mfglib``` functions in the NCP to facilitate device testing. To use this function, create the ```ZigBeeDongleEzsp``` and then call the ```getEmberMfglib(EmberMfglibListener)``` method to get the ```EmberMfglib``` class and also set the callback listener. The ```EmberMfglib``` class provides access to the ```mfglib``` functions.

Note that this can only be used if the dongle is not configured for use in the network (ie ```initialize``` has not been called).

The [com.zsmartsystems.zigbee.sniffer](https://github.com/zsmartsystems/com.zsmartsystems.zigbee.sniffer) project is an example of the use of these features to provide a network sniffer to route frames to [Wireshark](https://www.wireshark.org/).

### Texas Instruments CC2531

The library supports the Texas Instruments ZNP protocol over a serial interface.

### Telegesis ETRX3

The library supports the Telegesis AT protocol over a serial interface. Currently implemented against R309C. Note that some dongles such as the Qivicon Funkstick may use PID/VID codes that require special drivers or they will not be detected as a serial port.

Under Linux you can add the Qivicon dongle without a custom made kernel with the following command -:

``` 
echo 10c4 89fb > /sys/bus/usb-serial/drivers/cp210x/new_id
```

### ConBee / RaspBee

The library supports the Dresden Electronics RaspBee and ConBee dongles. Note that this requires some further work.

### XBee

The XBee S2C XStick is supported.

## Tested Hardware

The framework has been tested against many different devices - many lights, motion sensors, temperature/humidity/light sensors, plug sockets...

## ZigBee Dongles and Chipsets

The following table provides a summary of some of the dongles / chipsets that are available on the market and their support within the library. Receive sensitivity and transmit power are the main parameters affecting RF performance - it should be noted that regulations may reduce transmit power in some areas of the world and other factors can also impact performance. 

| Model                 | Support         | Receive     | Transmit     | Antenna  |
|-----------------------|-----------------|-------------|--------------|----------|
| Xbee XU-Z11           | Yes             | -90dBm      | +4.5dBm      | Internal |
| EM358                 | Yes (EZSP)      | -100dBm     | +8.0dBm      | Internal |
| EFR32                 | Yes (EZSP)      |             |              |          | 
| **EM358LR**           | Yes (EZSP)      | -103dBm     | **+20.0dBm** | Internal |
| MGM111                | Yes (EZSP)      | -99dBm      | +10.0dBm     | Internal |
| RaspBee               | Yes (CONBEE)    | **-105dBm** | +8.7dBm      | Internal |
| ConBee                | Yes (CONBEE)    | **-105dBm** | +8.7dBm      | Internal |
| CC2530                | Yes (ZNP)       | -97dBm      | +4.5dBm      |          |
| CC2531                | Yes (ZNP)       | -97dBm      | +4.5dBm      |          |
| CC2538                | Yes (ZNP)       | -97dBm      | +7.0dBm      |          |
| CC2650                | Yes (ZNP)       | -100dBm     | +5.0dBm      |          |
| ATSAMR21              | No              | -99dBm      | +4.0dBm      |          |
| JN5169                | No              | -96dBm      | +10.0dBm     |          |
| HUSBZB-1              | Yes (EZSP)      |             |              | Internal |
| Telegesis ETRX3       | Yes (Telegesis) |             |              | Internal |
| Qivicon Funkstick     | Yes (Telegesis) |             |              | Internal |

* Receive: Defines the typical receive performance. A lower number is best.
* Transmit: Defines the maximum output power. A higher number is best.

# Application Extensions

The framework includes optional functional applications to support higher layer functionality. Extensions implement the ```ZigBeeNetworkExtension``` interface and are registered with the network manager with the ```ZigBeeNetworkManager.addExtension()``` method. Extensions provide the top level network manager functionality and are normally augmented with lower level client/server functions associated with specific clusters. These client/server applications implement the ```ZigBeeApplication``` interface and are registered with the endpoint with the ```ZigBeeEndpoint.addApplication()``` method.

Currently implemented extensions include -:

* Discovery Extension (```ZigBeeDiscoveryExtension```)
* Network Mesh Monitor Extension (```ZigBeeDiscoveryExtension```)
* IAS CIE client (```ZigBeeIasCieExtension```)
* OTA Upgrade Server (```ZigBeeOtaUpgradeExtension```)
* Basic Cluster Server (```ZigBeeBasicServerExtension```)

These provide basic functionality and can be extended as required to meet the application needs.

## Overview

Extensions may provide different levels of functionality - an extension may be as simple as configuring the framework to work with a specific feature, or could provide a detailed application.

An example of a simple extension is the ```ZigBeeOtaUpgradeExtension``` which simply listens for new devices on the network, and adds the ```ZclOtaUpgradeServer``` to the endpoint.

A more complex extension could for example handle the CIE IAS zones, providing a cross device implementation to coordinate the allocation of zones and handling of alarms.

The lifecycle of an extension is as follows -:

* ```extensionInitialize``` is called when the extension is first registered
* ```extensionStartup``` is called when the network is online and the extension may run operationally.
* ```extensionShutdown``` is called when the extension is closed. The framework will do this when it is shutting down.

Extensions should normally register as a ```ZigBeeNetworkNodeListener``` to get notified when a node is discovered or removed from the network so that they can add support to the node. The extension will then register a client or server application with the endpoint. The client/server application may register for callbacks with the endpoint (or node).

Extension may want to register a supported cluster with the ```ZigBeeNetworkManager.addSupportedCluster()``` method so that the services provided are discoverable.

Client/Server implementations will normally be linked to a specific cluster and provide the application logic for the cluster. The client/server implementation class should be named with the same name as the cluster, but exchanging ```Cluster``` for ```Client``` or ```Server``` (eg a server supporting the ```ZclOtaUpgradeCluster``` would be named ```ZclOtaUpgradeServer```).

# Console Application

A console application is provided as part of the package. This application allows full use and testing of the framework. It can be used to test any new functionality without the added complexity of application level integration, or may be used as a stand-alone ZigBee configuration tool.

All commands implement the ```ZigBeeConsoleCommand``` interface, providing an easily extendible command system.

The command handlers used in the console application are in the package ```com.zsmartsystems.zigbee.console```. This is separate from the main console application, and this allows the command handlers to be incorporated into other frameworks.

Command handlers for commands specific to each dongle implementation are in the package ```com.zsmartsystems.zigbee.console.abc``` (where abc is the name of the dongle). These commands allow access to non standard API relating solely to each dongle.

Command handlers take a set of arguments as provided by the user and will throw ```IllegalArgumentException``` if there are any errors with arguments, or ```IllegalStateException``` if there are any issues with the network state that prevent the command execution.

## Starting the Console

The console application takes the following parameters -:

| Option                      | Description                                                               |
|-----------------------------|---------------------------------------------------------------------------|
|-?,--help                    | Print usage information                                                   |
|-a,--pan <PAN ID>            | Set the ZigBee PAN ID                                                     |
|-b,--baud <baud>             | Set the port baud rate                                                    |
|-c,--channel <channel id>    | Set the ZigBee channel ID                                                 |
|-d,--dongle <dongle type>    | Set the dongle type to use (EMBER | CC2531 | TELEGESIS | CONBEE | XBEE)   |
|-e,--epan <EPAN ID>          | Set the ZigBee EPAN ID                                                    |
|-f,--flow <type>             | Set the flow control (NONE | HARDWARE | SOFTWARE)                         |
|-l,--linkkey <key>           | Set the ZigBee Link key (defaults to well known ZHA key)                  |
|-n,--nwkkey <key>            | Set the ZigBee Network key (defaults to randon value)                     |
|-o,--nwkkeyoutcnt <counter>  | Set the ZigBee Network key outgoing frame counter                         |
|-p,--port <port name>        | Set the port                                                              |
|-t,--linkkeyoutcnt <counter> | Set the ZigBee Link key outgoing frame counter                            |
|-r,--reset                   | Reset the ZigBee dongle                                                   |

The ```-dongle``` and ```-port``` options are always required. Most dongles will also require ```-baud```, and may require ```-flow```, although this may be hard coded for dongles that do not have this option.

If ```-reset``` is used, you should normally also set the ```-channel```, ```-pan```, ```-epan``` and ```-nwkkey``` options.  If not set, defaults will be used to allow the system to start, however these may be random and may not allow you to use a network sniffer or other tools where some of this information may be required. The ```-linkkey``` may also be set, but will default to the well known ZHA ```ZigBeeAlliance09``` key if not set.

Decimal configuration values such as ```pan``` may be specified in decimal, or hexadecimal by prepending ```0x``` to the value (eg ```0x2000```).

Example -:

```
-dongle EMBER -port /dev/tty.usbserial-FTD6C0AF -baud 115200 -flow software -channel 11 -pan 0x2000 -epan 987654321 -nwkkey AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA -reset
```

## Console Commands

### General Commands

Note that the console is currently being refactored and this readme only documents the commands that have been migrated. For a full list of commands, use the _help_ command in the console.

| Command         | Description                                                                           |
|-----------------|---------------------------------------------------------------------------------------|
|join             |Enable or disable network join                                                         |
|leave            |Remove a node from the network                                                         |
|nodelist         |Lists the known nodes in the network                                                   |
|node             |Provides detailed information about a node                                             |
|endpoint         |Provides detailed information about an endpoint                                        |
|info             |Get basic info about a device                                                          |
|read             |Read an attribute                                                                      |
|write            |Write an attribute                                                                     |
|bind             |Binds a device to another device                                                       |
|unbind           |Unbinds a device from another device                                                   |
|bindtable        |Reads and displays the binding table from a node                                       |
|attsupported     |Check what attributes are supported within a cluster                                   |
|subscribe        |Subscribe to attribute reports                                                         |
|unsubscribe      |Unsubscribe from attribute reports                                                     |
|reportcfg        |Read the reporting configuration of an attribute                                       |
|otaupgrade       |Provides information about device Over The Air upgrade server status                   |
|installkey       |Adds an install key to the dongle                                                      |
|linkkey          |Sets the link key int the dongle, optionally computing the MMO Hash from the join code |
|netstart         |Join or Form a network as a router or coordinator                                      |
|netbackup        |Backup or restores the state of the dongle                                             |
|discovery        |Gets information on the network discovery tasks                                        |


### Ember NCP Commands

The following commands are available if the transport layer is using the Silabs Ember NCP.

| Command         | Description                                                                  |
|-----------------|------------------------------------------------------------------------------|
|ncpchildren      |Gets the NCP child information                                                |
|ncpconfig        |Read or write an NCP configuration value                                      |
|ncpscan          |Performs a scan, looking for other networks, or energy levels on each channel |
|ncpcounters      |Gets the NCP debug counters                                                   |
|ncpstate         |Gets the NCP network state and optionally brings the network up or down       |
|ncpvalue         |Read or write an NCP memory value                                             |
|ncpversion       |Gets the NCP firmware version                                                 |
|ncpsecuritystate |Gets the current NCP security state and configuration                         |
|ncptransientkey  |Adds a transient link key to the NCP                                          |
|ncpmmohash       |Uses the NCP to perform the MMO hash                                          |

### Telegesis Commands

The following commands are available if the transport layer is using the Telegesis dongle.

| Command         | Description                                                                  |
|-----------------|------------------------------------------------------------------------------|
|ncpsecuritystate |Gets the current NCP security state and configuration                         |

# Contributing

* Code style should use [standard naming conventions](https://www.thoughtco.com/using-java-naming-conventions-2034199)
* Codacy static testing should pass.
* Run the findBugs goal and check that you have not introduced any bugs into your code. FindBugs reports are generated with the ```mvn site``` goal, and reports are located in the ```target/site/findbugs.html``` file.
* Please consider raising issues before working on an enhancement to provide some coordination with other contributors.
* Keep PRs short - try and keep a single PR per enhancement. This makes tracking and reviewing easier.
* Contributions must be supported with tests. Ideally, you should aim to acheive full coverage of the code changes. Code coverage reports are located in the ```com.zsmartsystems.zigbee.test/target/site/jacoco-aggregate``` folder and opening the ```index.html``` file.
* Code must be formatted using the Eclipse code formatter provided in the project.
* Contributions must be your own and you must agree with the license.
* You must sign the PR and commits and must agree to the [Contributor License Agreement](https://cla-assistant.io/zsmartsystems/com.zsmartsystems.zigbee).

## Maven

The repositories below may be used for the RELEASE and SNAPSHOT versions respectively -:

```
<repository>
    <id>bintray-zsmartsystems-com.zsmartsystems</id>
    <name>zsmartsystems-com.zsmartsystems</name>
    <url>https://api.bintray.com/maven/zsmartsystems/com.zsmartsystems/zigbee/;publish=1</url>
</repository>
<snapshotRepository>
    <id>snapshots-zsmartsystems-com.zsmartsystems</id>
    <name>zsmartsystems-com.zsmartsystems</name>
    <url>https://oss.jfrog.org/artifactory/oss-snapshot-local/</url>
</snapshotRepository>
```

```
<dependency>
    <groupId>com.zsmartsystems.zigbee</groupId>
    <artifactId>com.zsmartsystems.zigbee</artifactId>
    <version>1.x.x</version>
    <type>pom</type>
</dependency>
```

### Maven Goals

* To build: ```mvn clean install```
* To prepeare a new release: ```mvn release:prepare```
* To perform a new release: ```mvn release:perform```
* To bump the version: ```mvn release:update-versions```
* To format the license header: ```mvn license:format```
* To produce the findbugs (etc) reports: ```mvn site```

## Gradle

```
gradlew clean build
```

Gradle build dependencies:

```
dependencies
{
    compile 'com.zsmartsystems.zigbee:com.zsmartsystems.zigbee:1.x.x'
}
```

## Note: 

Change 1.x.x to desired/latest version (24.12.2018 1.x.x -> 1.1.7)

# License

The code is licensed under [Eclipse Public License](https://www.eclipse.org/legal/epl-v10.html). Refer to the [license file](LICENSE) for further information.

Some parts of this code are from [zigbee4java](https://github.com/tlaukkan/zigbee4java) which in turn is derived from [ZB4O](http://zb4osgi.aaloa.org/) projects which are licensed under the [Apache-2 license](https://www.apache.org/licenses/LICENSE-2.0). These are being refactored out to ensure the contributions to this reportisory are understood.

## ZigBee Documentation

Some documentation used to create parts of this framework is copyright © ZigBee Alliance, Inc. All rights Reserved. The following copyright notice is copied from the ZigBee documentation.

Elements of ZigBee Alliance specifications may be subject to third party intellectual property rights, including without limitation, patent, copyright or trademark rights (such a third party may or may not be a member of ZigBee). ZigBee is not responsible and shall not be held responsible in any manner for identifying or failing to identify any or all such third party intellectual property rights.

No right to use any ZigBee name, logo or trademark is conferred herein. Use of any ZigBee name, logo or trademark requires membership in the ZigBee Alliance and compliance with the ZigBee Logo and Trademark Policy and related ZigBee policies.

This document and the information contained herein are provided on an “AS IS” basis and ZigBee DISCLAIMS ALL WARRANTIES EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO (A) ANY WARRANTY THAT THE USE OF THE INFORMATION HEREIN WILL NOT INFRINGE ANY RIGHTS OF THIRD PARTIES (INCLUDING WITHOUT LIMITATION ANY INTELLECTUAL PROPERTY RIGHTS INCLUDING PATENT, COPYRIGHT OR TRADEMARK RIGHTS) OR (B) ANY IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, TITLE OR NONINFRINGEMENT. IN NO EVENT WILL ZIGBEE BE LIABLE FOR ANY LOSS OF PROFITS, LOSS OF BUSINESS, LOSS OF USE OF DATA, INTERRUPTION OF BUSINESS, OR FOR ANY OTHER DIRECT, INDIRECT, SPECIAL OR EXEMPLARY, INCIDENTIAL, PUNITIVE OR CONSEQUENTIAL DAMAGES OF ANY KIND, IN CONTRACT OR IN TORT, IN CONNECTION WITH THIS DOCUMENT OR THE INFORMATION CONTAINED HEREIN, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH LOSS OR DAMAGE.

All Company, brand and product names may be trademarks that are the sole property of their respective owners.

## Dongle Documentation

Some documentation used to implement dongle drivers is copywrite to the respective holders, including Silicon Labs, Texas Instruments, Dresden Electronics, Digi International.

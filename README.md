[![Travis Build Status](https://travis-ci.org/zsmartsystems/com.zsmartsystems.zigbee.svg?branch=master)](https://travis-ci.org/zsmartsystems/com.zsmartsystems.zigbee) [![Codacy Coverage Badge](https://api.codacy.com/project/badge/Coverage/b3e149e7838947c9967f50ff3b2a01eb)](https://www.codacy.com/app/zsmartsystems/com-zsmartsystems-zigbee?utm_source=github.com&utm_medium=referral&utm_content=zsmartsystems/com.zsmartsystems.zigbee&utm_campaign=Badge_Coverage) [![Codacy Static Analyses Badge](https://api.codacy.com/project/badge/Grade/b3e149e7838947c9967f50ff3b2a01eb)](https://www.codacy.com/app/zsmartsystems/com-zsmartsystems-zigbee?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=zsmartsystems/com.zsmartsystems.zigbee&amp;utm_campaign=Badge_Grade) [![CLA Assistant Badge](https://cla-assistant.io/readme/badge/zsmartsystems/com.zsmartsystems.zigbee)](https://cla-assistant.io/zsmartsystems/com.zsmartsystems.zigbee) [ ![Download](https://api.bintray.com/packages/zsmartsystems/com.zsmartsystems/zigbee/images/download.svg) ](https://bintray.com/zsmartsystems/com.zsmartsystems/zigbee/_latestVersion)

# Overview

This project aims to provide a ZigBee Cluster Library written in Java. Packages are broken down to provide the main ZigBee  framework, separate packages for dongles, and a console application that allows full use of the framework.

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
  
### Texas Instruments CC2531

The library supports the Texas Instruments ZNP protocol over a serial interface.

### Silicon Labs Ember EM35x / EFR32

The library supports the Silicon Labs EZSP protocol using ASH over a serial interface.

It is worth noting that EM3588 devices that have an embedded USB core will likely work with any baud rate, where dongles using external USB interface (eg CP2102 used with an EM3581) will likely require a specific baud rate. This has been noted on the HUSBZB-1 which embeds an EM3581 and requires a rate of 57600.

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

The following table provides a summary of some of the dongles / chipsets that are available on the market nd their support within the library. Receive sensitivity and transmit power are the main parameters affecting RF performance - it should be noted that regulations may reduce transmit power in some areas of the world and other factors can also impact performance. 
 
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

## Applications

The framework includes functional applications to support higher layer functionality. This includes -:

* IAS CIE client
* OTA Upgrade Server

These provide minimal functionality and can be extended as required.

# Console Application

A console application is provided as part of the package. This application allows full use and testing of the framework. It can be used to test any new functionality without the added complexity of application level integration, or may be used as a stand-alone ZigBee configuration tool.

All commands implement the ```ZigBeeConsoleCommand``` interface, providing an easily extendible command system.

The command handlers used in the console application are in the package ```com.zsmartsystems.zigbee.console```. This is separate from the main console application, and this allows the command handlers to be incorporated into other frameworks.

Command handlers for commands specific to each dongle implementation are in the package ```com.zsmartsystems.zigbee.console.xxx``` (where xxx is the name of the dongle). These commands allow access to non standard API relating solely to each dongle.
 
Command handlers take a set of arguments as provided by the user and will throw ```IllegalArgumentException``` if there are any errors with arguments, or ```IllegalStateException``` if there are any issues with the network state that prevent the command execution.

## General Commands
Note that the console is currently being refactored and this readme only documents the commands that have been migrated. For a full list of commands, use the _help_ command in the console.

| Command         | Description                                           |
|-----------------|-------------------------------------------------------|
|join             |Enable or disable network join                         |
|leave            |Remove a node from the network                         |
|nodelist         |Lists the known nodes in the network                   |
|node             |Provides detailed information about a node             |
|endpoint         |Provides detailed information about an endpoint        |
|info             |Get basic info about a device                          |
|read             |Read an attribute                                      |
|write            |Write an attribute                                     |
|bind             |Binds a device to another device                       |
|unbind           |Unbinds a device from another device                   |
|bindtable        |Reads and displays the binding table from a node       |
|attsupported     |Check what attributes are supported within a cluster   |
|subscribe        |Subscribe to attribute reports                         |
|unsubscribe      |Unsubscribe from attribute reports                     |
|reportcfg        |Read the reporting configuration of an attribute       |


## Ember NCP Commands
The following commands are available if the transport layer is using the Silabs Ember NCP.

| Command         | Description                                           |
|-----------------|-------------------------------------------------------|
|ncpchildren      |Gets the NCP child information                         |
|ncpcounters      |Gets the NCP debug counters                            |
|ncpstate         |Gets the NCP network state                             |
|ncpversion       |Gets the NCP firmware version                          |
|ncpnetworkparms  |Gets the current NCP network parameters                |
|ncpsecuritystate |Gets the current NCP security state and configuration  |

## Telegesis Commands
The following commands are available if the transport layer is using the Telegesis dongle.

| Command         | Description                                           |
|-----------------|-------------------------------------------------------|
|                 |                                                       |

# Contributing

* Code style should use [standard naming conventions](https://www.thoughtco.com/using-java-naming-conventions-2034199)
* Codacy static testing should pass.
* Run the findBugs goal and check that you have not introduced any bugs into your code. FindBugs reports are generated with the ```mvn site``` goal, and reports are located in the ```target/site/findbugs.html``` file.
* Please consider raising issues before working on an enhancement to provide some coordination with other contributors.
* Keep PRs short - try and keep a single PR per enhancement. This makes tracking and reviewing easier.
* Contributions must be supported with tests.
* Code must be formatted using the Eclipse code formatter provided in the project.
* Contributions must be your own and you must agree with the license.
* You must sign the PR and commits and must agree to the [Contributor License Agreement](https://cla-assistant.io/zsmartsystems/com.zsmartsystems.zigbee).
 
## Maven goals
 
* To build: ```mvn clean install```
* To prepeare a new release: ```mvn release:prepare```
* To perform a new release: ```mvn release:perform```
* To bump the version: ```mvn release:update-versions```
* To format the license header: ```mvn license:format```
* To produce the findbugs (etc) reports: ```mvn site```

# License

The code is licensed under [Eclipse Public License](https://www.eclipse.org/legal/epl-v10.html). Some parts of this code are from [zigbee4java](https://github.com/tlaukkan/zigbee4java) which in turn is derived from [ZB4O](http://zb4osgi.aaloa.org/) projects which are licensed under the [Apache-2 license](https://www.apache.org/licenses/LICENSE-2.0). Refer to the [license file](LICENSE) for further information.

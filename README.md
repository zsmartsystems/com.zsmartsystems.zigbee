[![Travis Build Status](https://travis-ci.org/zsmartsystems/com.zsmartsystems.zigbee.svg?branch=master)](https://travis-ci.org/zsmartsystems/com.zsmartsystems.zigbee) [![Codacy Coverage Badge](https://api.codacy.com/project/badge/Coverage/b3e149e7838947c9967f50ff3b2a01eb)](https://www.codacy.com/app/zsmartsystems/com-zsmartsystems-zigbee?utm_source=github.com&utm_medium=referral&utm_content=zsmartsystems/com.zsmartsystems.zigbee&utm_campaign=Badge_Coverage) [![Codacy Static Analyses Badge](https://api.codacy.com/project/badge/Grade/b3e149e7838947c9967f50ff3b2a01eb)](https://www.codacy.com/app/zsmartsystems/com-zsmartsystems-zigbee?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=zsmartsystems/com.zsmartsystems.zigbee&amp;utm_campaign=Badge_Grade) [![CLA Assistant Badge](https://cla-assistant.io/readme/badge/zsmartsystems/com.zsmartsystems.zigbee)](https://cla-assistant.io/zsmartsystems/com.zsmartsystems.zigbee) [ ![Download](https://api.bintray.com/packages/zsmartsystems/com.zsmartsystems/zigbee/images/download.svg) ](https://bintray.com/zsmartsystems/com.zsmartsystems/zigbee/_latestVersion)

# Overview

This project aims to provide a ZigBee Cluster Library written in Java. Packages are broken down to provide the main ZigBee framework, separate packages for dongles, and a test application.

# Packages

## ZigBee Stack

## Dongles
  
### Texas Instruments CC2531

The library supports the Texas Instruments ZNP protocol over a serial interface.

### Silicon Labs Ember EM35x

The library supports the Silicon Labs EZSP protocol using ASH over a serial interface.

It is worth noting that EM3588 devices that have an embedded USB core will likely work with any baud rate, where dongles using external USB interface (eg CP2102 used with an EM3581) will likely require a specific baud rate. This has been noted on the HUSBZB-1 which embeds an EM3581 and requires a rate of 57600.

### Telegesis ETRX3

The library supports the Telegesis AT protocol over a serial interface. Currently implemented against R309. Note that some dongles such as the Qivicon Funkstick may use PID/VID codes that require special drivers or they will not be detected as a serial port.

Under Linux you can add the Qivicon dongle without a custom made kernel with the following command -:

``` 
echo 10c4 89fb > /sys/bus/usb-serial/drivers/cp210x/new_id
```

### ConBee / RaspBee

The library supports the Dresden Electronics RaspBee and ConBee dongles. Note that this requires some further work.
 
## Tested Hardware
 
## ZigBee Dongles and Chipsets

The following table provides a summary of some of the dongles / chipsets that are available on the market nd their support within the library. Receive sensitivity and transmit power are the main parameters affecting RF performance - it should be noted that regulations may reduce transmit power in some areas of the world and other factors can also impact performance. 
 
| Model                 | Support         | Receive     | Transmit     | Antenna  |
|-----------------------|-----------------|-------------|--------------|----------|
| Xbee XU-Z11           | No              | -90dBm      | +4.5dBm      | Internal |
| EM358                 | Yes (EZSP)      | -100dBm     | +8.0dBm      | Internal |
| **EM358LR**           | Yes (EZSP)      | -103dBm     | **+20.0dBm** | Internal |
| MGM111                | Yes (EZSP)      | -99dBm      | +10dBm       | Internal |
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

* Receive: Defines the typical receive performance. A smaller number is best.
* Transmit: Defines the maximum output power. A larger number is best.

## Applications

The framework includes functional applications to support higher layer functionality. This includes -:

* IAS CIE client
* OTA Upgrade Server

These provide minimal functionality and can be extended as required.

# Contributing

* Code style should use [standard naming conventions](https://www.thoughtco.com/using-java-naming-conventions-2034199)
* Codacy static testing should pass.
* Please consider raising issues before working on an enhancement to provide some coordination.
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

# License

The code is licensed under [Eclipse Public License](https://www.eclipse.org/legal/epl-v10.html). Some parts of this code are from [zigbee4java](https://github.com/tlaukkan/zigbee4java) which in turn is derived from [ZB4O](http://zb4osgi.aaloa.org/) projects which are licensed under the [Apache-2 license](https://www.apache.org/licenses/LICENSE-2.0). Refer to the [license file](LICENSE) for further information.

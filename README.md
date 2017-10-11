[![Travis Build Status](https://travis-ci.org/zsmartsystems/com.zsmartsystems.zigbee.svg?branch=master)](https://travis-ci.org/zsmartsystems/com.zsmartsystems.zigbee) [![Codacy Coverage Badge](https://api.codacy.com/project/badge/Coverage/b3e149e7838947c9967f50ff3b2a01eb)](https://www.codacy.com/app/zsmartsystems/com-zsmartsystems-zigbee?utm_source=github.com&utm_medium=referral&utm_content=zsmartsystems/com.zsmartsystems.zigbee&utm_campaign=Badge_Coverage) [![Codacy Static Analyses Badge](https://api.codacy.com/project/badge/Grade/b3e149e7838947c9967f50ff3b2a01eb)](https://www.codacy.com/app/zsmartsystems/com-zsmartsystems-zigbee?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=zsmartsystems/com.zsmartsystems.zigbee&amp;utm_campaign=Badge_Grade)

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

The library supports the Telegesis AT protocol over a serial interface. Currently implemented against R309.

## Tested Hardware
 
## ZigBee Dongles and Chipsets

The following table provides a summary of some of the dongles / chipsets that are available on the market nd their support within the library. Receive sensitivity and transmit power are the main parameters affecting RF performance - it should be noted that regulations may reduce transmit power in some areas of the world and other factors can also impact performance. 
 
| Model                 | Support         | Receive     | Transmit     | Antenna  |
|-----------------------|-----------------|-------------|--------------|----------|
| Xbee XU-Z11           | No              | -90dBm      | +4.5dBm      | Internal |
| SiLabs EM358          | Yes (EZSP)      | -100dBm     | +8.0dBm      | Internal |
| SiLabs **EM358LR**    | Yes (EZSP)      | -103dBm     | **+20.0dBm** | Internal |
| SiLabs MGM111         | Yes (EZSP)      | -99dBm      | +10dBm       | Internal |
| Dresden ConBee        | No              | **-105dBm** | +8.7dBm      | Internal |
| TI CC2530             | Yes (ZNP)       | -97dBm      | +4.5dBm      |          |
| TI CC2531             | Yes (ZNP)       | -97dBm      | +4.5dBm      |          |
| TI CC2538             | Yes (ZNP)       | -97dBm      | +7.0dBm      |          |
| TI CC2650             | Yes (ZNP)       | -100dBm     | +5.0dBm      |          |
| Atmel ATSAMR21        | No              | -99dBm      | +4.0dBm      |          |
| NXP JN5169            | No              | -96dBm      | +10.0dBm     |          |
| HUSBZB-1              | Yes (EZSP)      |             |              | Internal |
| Telegesis ETRX3       | Yes             |             |              | Internal |
| Qivicon Funkstick     | Yes (Telegesis) |             |              | Internal |

* Receive: Defines the typical receive performance. A smaller number is best.
* Transmit: Defines the maximum output power. A larger number is best.
 

# Contributing

* Codacy static testing should pass.
* Contributions must be supported with tests.
* Contributions must be your own and you must agree with the license.

# License

The code is licensed under [Eclipse Public License](https://www.eclipse.org/legal/epl-v10.html). Some parts of this code are from [zigbee4java](https://github.com/tlaukkan/zigbee4java) which in turn is derived from [ZB4O](http://zb4osgi.aaloa.org/) projects which are licensed under the [Apache-2 license](https://www.apache.org/licenses/LICENSE-2.0). Refer to the [license file](LICENSE) for further information.

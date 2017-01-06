/*
   Copyright 2008-2013 CNR-ISTI, http://isti.cnr.it
   Institute of Information Science and Technologies
   of the Italian National Research Council


   See the NOTICE file distributed with this work for additional
   information regarding copyright ownership

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/

package com.zsmartsystems.zigbee;

/**
 * Root exception for all the code related to ZigBee<br>
 * the BaseDriver should use ZibeeBasedriverException by including<br>
 * communication exception as nested Throwable<br>
 * <p/>
 * The ZCL bundle should use ZigBeeClusterException<br>
 * and HA Drive should use ZigBeeDeviceException.
 *
 * @author <a href="mailto:stefano.lenzi@isti.cnr.it">Stefano "Kismet" Lenzi</a>
 * @author <a href="mailto:francesco.furfari@isti.cnr.it">Francesco Furfari</a>
 */
public class ZigBeeException extends Exception {

    public ZigBeeException(String msg) {
        super(msg);
    }

    public ZigBeeException(Throwable ex) {
        super(ex);
    }

    public ZigBeeException(String msg, Throwable ex) {
        super(msg, ex);
    }
}

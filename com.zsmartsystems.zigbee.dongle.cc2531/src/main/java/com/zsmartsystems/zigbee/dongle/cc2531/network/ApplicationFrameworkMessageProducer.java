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

package com.zsmartsystems.zigbee.dongle.cc2531.network;

/**
 * @author <a href="mailto:stefano.lenzi@isti.cnr.it">Stefano "Kismet" Lenzi</a>
 * @author <a href="mailto:francesco.furfari@isti.cnr.it">Francesco Furfari</a>
 */
public interface ApplicationFrameworkMessageProducer {
    /**
     * Adds application framework message consumer.
     * 
     * @param consumer the consumer
     * @return true if add was successful.
     */
    boolean addAFMessageConsumer(ApplicationFrameworkMessageConsumer consumer);

    /**
     * Removes application framework message consumer.
     * 
     * @param consumer the consumer
     * @return true if remove was successful.
     */
    boolean removeAFMessageConsumer(ApplicationFrameworkMessageConsumer consumer);
}

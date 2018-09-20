/**
 * The Time extension provides a centralised time management utility. It consists of a {@link ZigBeeTimeClient}
 * {@link ZigBeeTimeServer} and {@link ZigBeeTimeExtension}.
 * <p>
 * The main manager is the {@link ZigBeeTimeExtension} class. This provides a centralised interface for all
 * time services. It handles the instantiation of the single {@link ZigBeeTimeServer} which in turn is responsible
 * for responding to requests from remote devices for time information. The {@link ZigBeeTimeServer} acts as a
 * master time source on the network. In addition, the {@link ZigBeeTimeExtension} instantiates a
 * {@link ZigBeeTimeClient}s
 * to interface with each remote client.
 * <p>
 * The {@link ZigBeeTimeExtension} will periodically poll the remote clients, checking their current time against
 * the system master time. If the time is outside of an allowable delta, it will be set. Daylight Saving Time can
 * also be centrally managed through the {@link ZigBeeTimeExtension}, which will in turn be set consistently for
 * requests via the {@link ZigBeeTimeClient} or {@link ZigBeeTimeServer}.
 *
 */
package com.zsmartsystems.zigbee.app.time;

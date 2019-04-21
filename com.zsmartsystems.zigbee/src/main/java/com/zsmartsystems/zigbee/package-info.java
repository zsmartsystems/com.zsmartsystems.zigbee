/**
 * <h2>API Overview</h2>
 * In general, the framework tries to have a good level of documentation for each class and the purpose of this
 * section is to provide an overview of the main framework interfaces at each level from a general user perspective to
 * aid in getting started.
 * <p>
 * The framework provides an object oriented approach to interfacing with the ZigBee network, and each element in the
 * hierarchy of an end device provides methods to interface to the device.
 *
 * <h3>ZigBeeNetworkManager</h3>
 * The {@link ZigBeeNetworkManager} class provides the network level functionality such as the methods to configure the
 * network and interact with {@link ZigBeeNode}s.
 * <p>
 * {@link ZigBeeNetworkManager} provides the {@link ZigBeeNetworkManager#getNode(Integer)} and
 * {@link ZigBeeNetworkManager#getNode(IeeeAddress)} methods to get a specific node given its short or long address, and
 * the {@link ZigBeeNetworkManager#getNodes()} method to get the full list of nodes.
 * <p>
 * The {@link ZigBeeNetworkManager} provides notification callbacks for the following events -:
 * <ul>
 * <li>Stack status changes (eg ONLINE to OFFLINE). Register with
 * {@link ZigBeeNetworkManager#addNetworkStateListener(ZigBeeNetworkStateListener)}.
 * <li>Node updates. Register with {@link ZigBeeNetworkManager#addNetworkNodeListener(ZigBeeNetworkNodeListener)}.
 * </ul>
 *
 * <h3>ZigBeeNode</h3>
 * The {@link ZigBeeNode} class provides node level functions. A ZigBee node supports one or more endpoints which
 * provide different logical devices. A node supports a single ZigBee Device Object (ZDO) at endpoint 0, and this
 * provides low level functionality for the node.
 * <p>
 * {@link ZigBeeNode} provides the {@link com.zsmartsystems.zigbee.ZigBeeNode#getEndpoint(int)} method to get a specific
 * {@link ZigBeeEndpoint}s supported by the node, or {@link com.zsmartsystems.zigbee.ZigBeeNode#getEndpoints()} to get a
 * list of all endpoints.
 *
 * <h3>ZigBeeEndpoint</h3>
 * The {@link ZigBeeEndpoint} class provides endpoint level functions. A ZigBee endpoint supports {@link ZigBeeCluster}s
 * which provide application level functionality. Two lists of clusters are provided in an endpoint - input clusters
 * (for the server side functionality) and output clusters (for client side functionality). Methods are provided in the
 * endpoint to access these lists through {@link ZigBeeEndpoint#getInputCluster(int)} and
 * {@link ZigBeeEndpoint#getOutputCluster(int)}. There are also corresponding methods to get the full list of supported
 * input and output clusters.
 *
 * <h3>ZclCluster</h3>
 * The {@link com.zsmartsystems.zigbee.zcl.ZclCluster} class is an abstract class that provides the core methods for the
 * cluster. Each cluster in the
 * ZigBee Cluster library is implemented in a separate class which extends {@link ZclCluster} with the specific
 * functions of that cluster.
 * <p>
 * A cluster provides specific {@link ZclCommand}s that implement the functionality required (eg to turn a light on and
 * off in the case of the {@link com.zsmartsystems.zigbee.zcl.clusters.ZclOnOffCluster}).
 * {@link com.zsmartsystems.zigbee.zcl.ZclCommand} is itself extended for each command in the library (eg with the
 * {@link com.zsmartsystems.zigbee.zcl.clusters.onoff.OnCommand} and
 * {@link com.zsmartsystems.zigbee.zcl.clusters.onoff.OffCommand} classes.
 * <p>
 * A cluster also supports {@link ZclAttribute}s which are discrete data points within the cluster. An attribute may be
 * used to configure the functionality of a cluster, or it may be used to read sensor data (eg temperature) or the
 * current state of a light bulb.
 * <p>
 * Methods are available to get an attribute, or to read and write to an attribute - eg
 * {@link com.zsmartsystems.zigbee.zcl.ZclCluster#getAttribute(int)},
 * {@link com.zsmartsystems.zigbee.zcl.ZclCluster#readAttribute(int)} and
 * {@link com.zsmartsystems.zigbee.zcl.ZclCluster#write(int, com.zsmartsystems.zigbee.zcl.protocol.ZclDataType, Object)}.
 * <p>
 * It is worth noting that some clusters may support different attributes depending on whether they are a client or
 * server. In this case the above methods will return the attribute given the current configuration of the cluster.
 * <p>
 * The {@link com.zsmartsystems.zigbee.zcl.ZclCluster} class also provides listener methods to notify the user when
 * commands are received, or attributes are updated. These can be registered using the
 * {@link com.zsmartsystems.zigbee.zcl.ZclCluster#addCommandListener(com.zsmartsystems.zigbee.zcl.ZclCommandListener)}
 * and
 * {@link com.zsmartsystems.zigbee.zcl.ZclCluster#addAttributeListener(com.zsmartsystems.zigbee.zcl.ZclAttributeListener)}
 * methods.
 * <p>
 * In order to configure a device to automatically sends updates of an attribute, reporting must be configured. This is
 * done with the {@link com.zsmartsystems.zigbee.zcl.ZclCluster#setReporting(int, int, int)} method. Note that the bind
 * must also be performed at node level.
 *
 * <h3>ZclAttribute</h3>
 * As described above, an {@link com.zsmartsystems.zigbee.zcl.ZclAttribute} is a discrete data point within the cluster
 * and may be used for different functionality. Some clusters will only have a small number of attributes, while others
 * may have hundreds. As seen above, the {@link com.zsmartsystems.zigbee.zcl.ZclCluster} class contains methods for
 * reading and writing attribute data given the attribute ID, and also methods for retrieving the
 * {@link com.zsmartsystems.zigbee.zcl.ZclAttribute} class itself.
 * <p>
 * The {@link com.zsmartsystems.zigbee.zcl.ZclAttribute} class also provides methods for directly interacting with the
 * attribute data in the device using the {@link com.zsmartsystems.zigbee.zcl.ZclAttribute#read()} and
 * {@link com.zsmartsystems.zigbee.zcl.ZclAttribute#write()} methods. These methods will send the relevant commands to
 * the device to read the current data value. In addition, the
 * {@link com.zsmartsystems.zigbee.zcl.ZclAttribute#readValue(long)} method will return the last known value if it was
 * updated more recently than the specified number of milliseconds - if not, it will request the value from the device.
 * <p>
 * The {@link com.zsmartsystems.zigbee.zcl.ZclAttribute} class provides methods to set the reporting using the
 * {@link com.zsmartsystems.zigbee.zcl.ZclAttribute#setReporting(int, int)} and
 * {@link com.zsmartsystems.zigbee.zcl.ZclAttribute#setReporting(int, int, Object)} methods. Note that the latter is
 * used for attributes that are defined as analog attributes and it allows the setting of a "report on change" value.
 */

package com.zsmartsystems.zigbee;

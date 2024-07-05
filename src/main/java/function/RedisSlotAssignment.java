package function;

import redis.clients.jedis.*;
import redis.clients.jedis.exceptions.JedisException;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RedisSlotAssignment {

    public static void main(String[] args) {
        // Replace the following with the IP addresses and ports of your target nodes
        Set<HostAndPort> targetNodes = new HashSet<>();
        targetNodes.add(new HostAndPort("172.16.0.1", 6379));
        targetNodes.add(new HostAndPort("172.16.0.2", 6379));

        try (JedisCluster jedisCluster = new JedisCluster(targetNodes)) {
            // Get the total number of slots (16384)
            int totalSlots = 16384;

            // Calculate the number of slots to be assigned to each target node
            int slotsPerNode = totalSlots / targetNodes.size();

            // Initialize the slot number to be assigned to the target nodes
            int currentSlot = 0;

            // Iterate through the target nodes and assign slots
            for (HostAndPort node : targetNodes) {
                try (Jedis jedis = new Jedis(node.getHost(), node.getPort())) {
                    for (int i = currentSlot; i < currentSlot + slotsPerNode; i++) {
                        jedis.clusterSetSlotImporting(i, node.toString()); // Replace with the actual node ID
                    }
                    currentSlot += slotsPerNode;
                }
            }

            System.out.println("Slots have been assigned to the specified nodes.");

        } catch (JedisException e) {
            System.err.println("Error: " + e.getMessage());
            System.err.println("Error: " + e.getMessage());
        }
    }
}

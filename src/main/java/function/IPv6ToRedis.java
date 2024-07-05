package function;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import redis.clients.jedis.Jedis;

public class IPv6ToRedis {
    public static void ipv6ToRedis(String ipv6) throws UnknownHostException {
        // Parse the IPv6 address into an InetAddress object
        InetAddress addr = InetAddress.getByName(ipv6);

        // Ignore the first 4 bytes (i.e., the subnet prefix)
        byte[] bytes = addr.getAddress();
        byte[] ipv6Bytes = new byte[bytes.length - 4];
        System.arraycopy(bytes, 4, ipv6Bytes, 0, ipv6Bytes.length);

        // Convert the byte array to a ByteBuffer
        ByteBuffer buffer = ByteBuffer.wrap(ipv6Bytes);
        buffer.order(ByteOrder.BIG_ENDIAN);

        // Extract the two 32-bit integers
        int hi = buffer.getInt();
        int lo = buffer.getInt();

        // Pack the two integers into a 64-bit long
        long num = ((long) hi << 32) | (lo & 0xFFFFFFFFL);

        // Store the long in Redis
        System.out.println(num);
    }

    public static void main(String[] args) throws UnknownHostException {
        ipv6ToRedis("2001:db8:85a3::8a2e:370:7334");
    }
}

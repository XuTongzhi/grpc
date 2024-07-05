package function;

import java.math.BigInteger;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class IpToLongConverter {
    public static void main(String[] args) {
//        String ip = "112.0.59.73";
//        String[] ipParts = ip.split("\\.");
//
//        if (ipParts.length == 4) {
//            long a = Long.parseLong(ipParts[0]);
//            long b = Long.parseLong(ipParts[1]);
//            long c = Long.parseLong(ipParts[2]);
//            long d = Long.parseLong(ipParts[3]);
//
//            long retVal = (a << 24) + (b << 16) + (c << 8) + d;
//
//            System.out.println("IP Address: " + ip);
//            System.out.println("Converted Value: " + retVal);
//        } else {
//            System.out.println("Invalid IP address format");
//        }
//        String num = "2408:893a:1140:1e42:1783:771c:6d16:bb16";
        String num = "1.192.84.0";
        BigInteger result = ipToNum(num);

        System.out.println(result);
        System.out.println(result.toString());
    }


    public static BigInteger ipToNum(String ipAddress) {
        try {
            InetAddress inetAddress = InetAddress.getByName(ipAddress);

            if (inetAddress instanceof Inet6Address) {
                // IPv6
                byte[] ipv6Bytes = inetAddress.getAddress();
                return new BigInteger(1, ipv6Bytes); // 1表示正数
            } else {
                // IPv4
                byte[] ipv4Bytes = inetAddress.getAddress();
                return new BigInteger(1,ipv4Bytes);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return BigInteger.valueOf(-1); // 出现异常时返回-1或其他错误标志
        }
    }
}


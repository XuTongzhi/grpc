package function;

import iputils.IpUtil;

import java.io.UnsupportedEncodingException;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.UnknownHostException;

public class decoded {
    public static void main(String[] args) {
//        String v4 = "172.23.224.1";
//        tov4(v4);
        long b = (2L << 31);
//        System.out.println(b);
        String ipv6Address = "fdea:42c4:376a::1b1";
        String ipv6Address2 = "2001:250:c1c:ffff:ffff:ffff:ffff:ffff";

//        42540535068798139097482791503257403392
//        42540535068796930171663176874082697216
        System.out.println(IpUtil.ipv6ToInt(ipv6Address2));

    }
    public static void tov4(String v4){

        String[] parts = v4.split("\\.");
        long result = 0;
        for (int i = 0; i < parts.length; i++) {
            result += (long) (Integer.parseInt(parts[i]) * Math.pow(256, 3 - i));
        }
        System.out.println(result);
    }
}

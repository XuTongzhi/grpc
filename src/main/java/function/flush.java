package function;

import com.alibaba.fastjson.JSON;
import redis.clients.jedis.Jedis;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;


public class flush {
    public static void main(String[] args) throws InterruptedException, UnsupportedEncodingException {
//        Jedis jedis = new Jedis("127.0.0.1", 6379);
//        String autoincr_key = "brief1:309";

//        String a = "221.197.222.60";
//        String[] split = a.split("\\.");
//        Long[] integers
//                = Arrays.stream(split).map(Long::valueOf).toArray(Long[]::new);
//        long b = (integers[0] << 24) + (integers[1] << 16) + (integers[2] << 8) + integers[3];
//        System.out.println(b);
        String res = "[\"\\u4fdd\\u7559\\u5730\\u5740\", \"\\u4fdd\\u7559\\u5730\\u5740\", \"*\", \"000000000000\"]";
        System.out.println(JSON.parseArray(res));


//
        String ipv6 = "fdea:42c4:376a:0:d46f:e365:50ba:c5e7";
        long num = ipv6ToNum(ipv6);
        System.out.println(ipv6 + " in decimal format is " + num);


    }
    public static long ipv6ToNum(String ipv6) {
        long num = 0;
        for (String s : ipv6.split(":")) {
            if (s.equals("")) {
                continue;
            }
            num = num * 65536 + Integer.parseInt(s, 16);
        }
        return num;
    }
}

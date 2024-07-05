package function;

import redis.clients.jedis.*;
import java.util.Map;
import java.util.Set;


public class flushAllPipelined {
    public static void main(String[] args) throws InterruptedException {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        Map<String, String> stringStringMap = jedis.hgetAll(" deal_id:IQIYI#100000#3#8#5#M364C");
        for (Map.Entry<String, String> stringStringEntry : stringStringMap.entrySet()) {
            System.out.println(stringStringEntry.getKey());
        }
    }
}

package function;

import com.opencsv.CSVReader;
import org.apache.commons.codec.digest.DigestUtils;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;


public class DeleteRedisKeysFromCSV {
    public static void main(String[] args) throws Exception {
        String arg = "/home/fs_alex/w_0620_oaid.csv";
        String arg1 = "/home/fs_alex/x_0620_oaid.csv";
        System.out.println("1234");
        CSVReader reader = new CSVReader(new FileReader(arg));
        CSVReader reader2 = new CSVReader(new FileReader(arg1));
        Set<HostAndPort> jedisClusterNode = new HashSet<HostAndPort>();
        jedisClusterNode.add(new HostAndPort("172.16.0.1", 6379));
        jedisClusterNode.add(new HostAndPort("172.16.0.2", 6379));
        jedisClusterNode.add(new HostAndPort("172.16.0.3", 6379));
        jedisClusterNode.add(new HostAndPort("172.16.0.4", 6379));
        JedisCluster jedisCluster = new JedisCluster(jedisClusterNode);
//        Jedis jedis = new Jedis("127.0.0.1", 6379);
//        String[] header = reader.readNext(); // Skip header row
        String[] row;
        while ((row = reader.readNext()) != null) {
            String key = row[0];
            if (!isMD5(key)) {
                key = DigestUtils.md5Hex(key);
            }
            jedisCluster.sadd("ta:325:320200", key);
        }
        while ((row = reader2.readNext()) != null) {
            String key = row[0];
            if (!isMD5(key)) {
                key = DigestUtils.md5Hex(key);
            }
            jedisCluster.sadd("ta:325:610100",key);
        }
    }
    public static boolean isMD5(String input) {
        String md5Pattern = "^[a-fA-F0-9]{32}$";
        return Pattern.matches(md5Pattern, input);
    }
}

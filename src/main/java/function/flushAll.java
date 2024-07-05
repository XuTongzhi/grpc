package function;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class flushAll {
    public static void main(String[] args) throws InterruptedException {
        String arg = args[0];

        Set<HostAndPort> jedisClusterNode = new HashSet<HostAndPort>();
        jedisClusterNode.add(new HostAndPort("172.16.0.1", 6379));
        jedisClusterNode.add(new HostAndPort("172.16.0.2", 6379));
        jedisClusterNode.add(new HostAndPort("172.16.0.3", 6379));
        JedisCluster jedisCluster = new JedisCluster(jedisClusterNode);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String starttime = sdf.format(System.currentTimeMillis());
        System.out.println("开始时间：" + starttime);
        String fileName = "/home/fs_alex/script/ipv4/result/ta_1130.txt";

        try {
            FileReader reader = new FileReader(fileName);
            BufferedReader bReader = new BufferedReader(reader);
            String line;

            int id = 1;
            //偏移量
            boolean offset = false;
            //字段名
            String field = "id_1130";
            //key
            String key = "ta:1130";

            while ((line = bReader.readLine()) != null) {
                    jedisCluster.hdel(line, field);
                    jedisCluster.setbit(key, id, offset);
                    //自增id
                    id++;
            }
            bReader.close();
            reader.close();
            String endtime = sdf.format(System.currentTimeMillis());
            System.out.println("结束时间：" + endtime);
            Date date1 = sdf.parse(starttime);
            Date date2 = sdf.parse(endtime);
            long diff = date2.getTime() - date1.getTime();
            long s = diff / 1000;
            System.out.println("耗时：" + s + "秒");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

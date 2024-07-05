package node;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class node2 {
    public static void main(String[] args) throws FileNotFoundException, ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String starttime = sdf.format(System.currentTimeMillis());
        System.out.println("开始时间：" + starttime);
        String node = "/home/fs_alex/script/ipv4/result/node2.txt";
        String ip = "172.16.0.2";
        int id = 3334135;
        Jedis jedis = new Jedis(ip, 6379);
        try {
            FileReader reader = new FileReader(node);
            BufferedReader bReader = new BufferedReader(reader);
            String line;
            boolean offset = true;
            String field = "id_1130";
            String key = "ta:1130";
            // 使用pipeline来提高性能
            Pipeline pipeline = jedis.pipelined();
            while ((line = bReader.readLine()) != null) {
                Response<Boolean> hexists = pipeline.hexists(line, field);
                pipeline.sync();
                if (!hexists.get()) {
                    pipeline.hset(line, field, Integer.toString(id));
                    pipeline.setbit(key, id, offset);
                    id++;
                }
            }
            pipeline.sync();
            bReader.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String endtime = sdf.format(System.currentTimeMillis());
        System.out.println("结束时间：" + endtime);
        Date date1 = sdf.parse(starttime);
        Date date2 = sdf.parse(endtime);
        long diff = date2.getTime() - date1.getTime();
        long s = diff / 1000;
        System.out.println("耗时：" + s + "秒");
    }
}

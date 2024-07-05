package function;

import redis.clients.jedis.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class InsertToRedisPipelined {
    public static void main(String[] args) throws FileNotFoundException, ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String starttime = sdf.format(System.currentTimeMillis());
        System.out.println("开始时间：" + starttime);

        String[] nodes = {"/home/fs_alex/script/ipv4/result/node1.txt",
                "/home/fs_alex/script/ipv4/result/node2.txt",
                "/home/fs_alex/script/ipv4/result/node3.txt"};
        String[] ips = {"172.16.0.1", "172.16.0.2", "172.16.0.3"};
        String arg = args[0];
        int id = Integer.parseInt(arg);
        for (int i = 0; i < nodes.length; i++) {
            Jedis jedis = new Jedis(ips[i], 6379);
            try {
                FileReader reader = new FileReader(nodes[i]);
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
            System.out.println("第"+ i + "个节点结束");
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


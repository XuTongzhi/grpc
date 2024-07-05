package function;

import redis.clients.jedis.Jedis;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class bykv {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost", 6379);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String starttime = sdf.format(System.currentTimeMillis());
        System.out.println("开始时间：" + starttime);
//        String fileName = "/home/fs_alex/script/ipv4/result/device.txt";
        String fileName = "C:\\Users\\Administrator\\Desktop\\device.txt";
        try {
            FileReader reader = new FileReader(fileName);
            BufferedReader bReader = new BufferedReader(reader);
//            String arg = args[0];
            String line;
//            int id = Integer.parseInt(arg);
            int id = 1;
//            boolean offset = true;
            while ((line = bReader.readLine()) != null) {
                    jedis.set(line, String.valueOf(id));
                    id++;
            }
            bReader.close();
            reader.close();
            String endtime = sdf.format(System.currentTimeMillis());
            System.out.println("结束时间：" + endtime);
            Date date1 = sdf.parse(starttime);
            Date date2 = sdf.parse(endtime);
            long diff = date2.getTime() - date1.getTime();
            long s = diff/1000;
            System.out.println("耗时："+s+"秒");
        } catch (Exception e) {
            System.out.println(args);
            e.printStackTrace();
        }
    }
}

package function;

import redis.clients.jedis.Jedis;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class testspeed {
    public static void main(String[] args) throws ParseException {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        long sum = 0;
        for (int i = 0; i < 100000; i++) {
            String starttime = sdf.format(System.currentTimeMillis());
//        System.out.println("开始时间：" + starttime);
//            jedis.hgetAll("deal_id:22901826");
            jedis.sismember("myset","banana");
//        jedis.get("587BCE6560A1C335039F116238B97848");
//        jedis.hget("ip:mykey","587BCE6560A1C335039F116238B97848");
//        jedis.hget("deal_id:22901826","daily_re");
//        jedis.hget("deal_id:22901826","imp");
//        jedis.hget("deal_id:22901826","daily_imp");
//        jedis.hget("deal_id:22901826", "ptr");
//        jedis.hget("deal_id:22901826", "brfid");
//        jedis.hget("deal_id:22901826", "crids");
//        jedis.hget("deal_id:22901826", "cr_weight");
//        jedis.hget("deal_id:22901826", "bid_price");
//        jedis.hget("deal_id:22901826", "imp_limit");
//        jedis.hget("deal_id:22901826", "rtr");
//        jedis.hget("deal_id:22901826", "ta_codes");
//        jedis.hget("deal_id:22901826", "budget");
//        jedis.hget("deal_id:22901826", "daily_budget");
//        jedis.hget("deal_id:22901826", "speed");
//        jedis.hget("deal_id:22901826", "frequency_cap_id");
//        jedis.hget("deal_id:22901826", "target_freq");
//        jedis.hget("deal_id:22901826", "bid");
            String endtime = sdf.format(System.currentTimeMillis());
            Date date1 = sdf.parse(starttime);
            Date date2 = sdf.parse(endtime);
            long diff = date2.getTime() - date1.getTime();
            sum += diff;
//            System.out.println("耗时：" + diff + "毫秒");
        }
        System.out.println(sum);
    }
}

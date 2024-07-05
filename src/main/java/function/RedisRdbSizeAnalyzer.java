package function;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.params.ScanParams;
import redis.clients.jedis.resps.ScanResult;

public class RedisRdbSizeAnalyzer {
    private static final Logger logger = LoggerFactory.getLogger(RedisRdbSizeAnalyzer.class);

    public static void main(String[] args) {
        JedisPool jedisPool = new JedisPool("127.0.0.1", 6379);

        try (Jedis jedis = jedisPool.getResource()) {
            // 使用 Jedis 连接 Redis 服务器

            // 统计不同类型的键的大小
            long stringSize = 0;
            long md5Size = 0;
            long bitmapSize = 0;
            long hashSize = 0;
            long otherSize = 0;

            // 设置 SCAN 的初始游标和匹配模式
            String cursor = "0";
            ScanParams params = new ScanParams().match("*"); // 匹配所有键

            do {
                // 执行 SCAN 命令
                ScanResult<String> scanResult = jedis.scan(cursor, params);

                // 获取当前批次的键列表
                for (String key : scanResult.getResult()) {
                    String type = jedis.type(key);
                    long keySize = jedis.memoryUsage(key);
                    // 根据不同类型的键进行分类
                    if (key.startsWith("deal_id:") || key.startsWith("crid:") || key.startsWith("media_pid:") || key.matches("\\{.*\\}.*")) {
                        hashSize += keySize;
                        System.out.println("投放配置:" + key);
//                        logger.info("投放配置:" + key);
                    } else if (key.startsWith("audience_freq:")) {
                        bitmapSize += keySize;
                        System.out.println("频次:" + key);
//                        logger.info("频次:" + key);
                    } else if (type.equals("string")) {
                        stringSize += keySize;
                        String md5deviceId = DigestUtils.md5Hex(key);
                        jedis.set(md5deviceId,"1");
                        md5Size += jedis.memoryUsage(md5deviceId);
                        System.out.println("deviceId:" + key);
//                        logger.info("deviceId:" + key);
                    } else {
                        otherSize += keySize;
                        System.out.println("其他：" + key);
//                        logger.info("其他：" + key);
                    }
                }

                // 更新游标
                cursor = scanResult.getCursor();
            } while (!"0".equals(cursor));

            // 计算各类型的大小占比
            long totalSize = stringSize + bitmapSize + hashSize + otherSize;
            double stringSizePercentage = (double) stringSize / totalSize * 100;
            double bitmapSizePercentage = (double) bitmapSize / totalSize * 100;
            double hashSizePercentage = (double) hashSize / totalSize * 100;
            double otherSizePercentage = (double) otherSize / totalSize * 100;
            double md5SizePercentage = (double) md5Size / totalSize * 100;

            // 打印结果
            System.out.println("Total Size: " + totalSize + " bytes");
            System.out.println("String Size: " + stringSize + " bytes (" + stringSizePercentage + "%)");
            System.out.println("Bitmap Size: " + bitmapSize + " bytes (" + bitmapSizePercentage + "%)");
            System.out.println("Hash Size: " + hashSize + " bytes (" + hashSizePercentage + "%)");
            System.out.println("Other Size: " + otherSize + " bytes (" + otherSizePercentage + "%)");
            System.out.println("Md5 Size: " + md5Size + " bytes (" + md5SizePercentage + "%)");
        }
    }
}

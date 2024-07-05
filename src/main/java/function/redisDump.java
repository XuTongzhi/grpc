package function;

import net.whitbeck.rdbparser.*;


import java.io.*;


public class redisDump {
    public static void main(String[] args) throws IOException {
//        InputStream inputStream = new FileInputStream("dump.rdb");
//        Jedis jedis = new Jedis("localhost", 6379);
//        byte[] dump = jedis.dump("deal_id:108743");
//        String s = new String(dump, StandardCharsets.UTF_8);
//        jedis.del("deal_id:108743");
//        String restore = jedis.restore("deal_id:108743", 0, dump);
        try (RdbParser parser = new RdbParser("C:\\Users\\Administrator\\Desktop\\dump.rdb")) {
            Entry e;
            while ((e = parser.readNext()) != null) {
                switch (e.getType()) {

                    case SELECT_DB:
                        System.out.println("Processing DB: " + ((SelectDb)e).getId());
                        System.out.println("------------");
                        break;

                    case EOF:
                        System.out.print("End of file. Checksum: ");
                        for (byte b : ((Eof)e).getChecksum()) {
                            System.out.print(String.format("%02x", b & 0xff));
                        }
                        System.out.println();
                        System.out.println("------------");
                        break;

                    case KEY_VALUE_PAIR:
                        System.out.println("Key value pair");
                        KeyValuePair kvp = (KeyValuePair)e;
                        System.out.println("Key: " + new String(kvp.getKey(), "ASCII"));
                        Long expireTime = kvp.getExpireTime();
                        if (expireTime != null) {
                            System.out.println("Expire time (ms): " + expireTime);
                        }
                        System.out.println("Value type: " + kvp.getValueType());
                        System.out.print("Values: ");
                        for (byte[] val : kvp.getValues()) {
                            System.out.print(new String(val, "ASCII") + " ");
                        }
                        System.out.println();
                        System.out.println("------------");
                        break;
                }
            }
        }
        System.out.println();
    }
}

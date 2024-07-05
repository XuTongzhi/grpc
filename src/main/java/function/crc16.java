package function;

import redis.clients.jedis.util.JedisClusterCRC16;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class crc16 {
    public static void main(String[] args) throws IOException {

//        System.out.println(27534 % 16384);

        String fileName = "C:\\Users\\Administrator\\Desktop\\device.txt";
        FileReader reader = new FileReader(fileName);
        BufferedReader bReader = new BufferedReader(reader);
        String device;
        FileWriter writer = new FileWriter("C:\\Users\\Administrator\\Desktop\\node1.txt");
        FileWriter writer1 = new FileWriter("C:\\Users\\Administrator\\Desktop\\node2.txt");
        FileWriter writer2 = new FileWriter("C:\\Users\\Administrator\\Desktop\\node3.txt");
        while ((device = bReader.readLine()) != null) {
            int crc16 = JedisClusterCRC16.getCRC16(device);
            int duration = crc16 % 16384;
//            System.out.println("solt: " + slot);
//            System.out.println("device: " + device);
            if (duration >= 0 && duration <= 5461) {
                device += "\n";
                writer1.write(device);
            } else if (duration > 5461 && duration <= 10922) {
                device += "\n";
                writer.write(device);
            } else if (duration > 10922 && duration <= 16383) {
                device += "\n";
                writer2.write(device);
            }
        }

    }
}


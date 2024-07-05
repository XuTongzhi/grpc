package function;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.regex.Pattern;

public class URLWriter {
    public static void main(String[] args) {
        String a = "\"/it.htm?sp=3&crid=115636&ext=mcid%3D115636%26brfid%3D354%26ip%3D171.15.165.125%26akey%3Db9e9147ae2791e3217ebaee407d8d4fb%26deals%3D90023888887%26mac%3D__MAC__%26reqid%3D656CAD9Eke2uaaxipbdq400000%26m%3Dtencent%26kt%3Dipua%26crid%3D115636%26os%3D3";
        String decode = URLDecoder.decode(a);


        String deviceId = "656A06EDsxrrea5inyekc0000000";
//        String deviceId = "f8487147264a5c5d935221481d22f6d3";
        System.out.println(isMD5(deviceId));
//        System.out.println(decode);
    }



    public static boolean isMD5(String input) {
        String md5Pattern = "^[a-fA-F0-9]{32}$";
        return Pattern.matches(md5Pattern, input);
    }
}


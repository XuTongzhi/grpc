package function;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Locale;
import java.util.regex.Pattern;

public class md5 {

    public static boolean isMD5(String input) {
        String md5Pattern = "^[a-fA-F0-9]{32}$";
        return Pattern.matches(md5Pattern, input);
    }


    public static void main(String[] args) {
        String deviceId = "387269DA0EEAA22D5430AC086561AA8A";
        String s = deviceId.toLowerCase(Locale.ROOT);
        System.out.println(isMD5(s));
    }
}

package function;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class url {
    public static void main(String[] args) {


        String encodedURL = "IQIYI#410100#10#7#15#Q384";

        // 进行 URL 解码
        String decodedURL = encodeURL(encodedURL);

        // 输出解码后的 URL
        System.out.println("Decoded URL: " + decodedURL);
    }
    public static String decodeURL(String encodedURL) {
        try {
            // 使用 UTF-8 字符集进行解码，你也可以选择其他字符集
            return URLDecoder.decode(encodedURL, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            // 处理异常，这里简单地打印异常信息
            e.printStackTrace();
            return null;
        }
    }
    public static String encodeURL(String originalURL) {
        try {
            // 使用 UTF-8 字符集进行编码，你也可以选择其他字符集
            return URLEncoder.encode(originalURL, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            // 处理异常，这里简单地打印异常信息
            e.printStackTrace();
            return null;
        }
    }
}

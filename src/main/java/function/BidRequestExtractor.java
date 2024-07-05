package function;

import ads_serving.IqiyiBidding;
import com.google.protobuf.CodedInputStream;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class BidRequestExtractor {
    public static void main(String[] args) throws IOException {
        String encodedRequestBody = "...";  // 你的编码后的数据
        String hexString = urlDecode(encodedRequestBody);
        byte[] bytes = hexStringToByteArray(hexString);
        CodedInputStream codedInputStream = CodedInputStream.newInstance(bytes);
        IqiyiBidding.BidRequest bidRequest = IqiyiBidding.BidRequest.parseFrom(codedInputStream);

        if (bidRequest != null) {
            // 输出 BidRequest 对象的字段
            System.out.println("BidRequest object: " + bidRequest);
        }
    }

    private static String urlDecode(String encodedData) {
        try {
            return URLDecoder.decode(encodedData, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static byte[] hexStringToByteArray(String hexString) {
        int len = hexString.length();
        byte[] bytes = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            bytes[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
                    + Character.digit(hexString.charAt(i + 1), 16));
        }
        return bytes;
    }
}


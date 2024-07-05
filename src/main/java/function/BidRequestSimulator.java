package function;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.protobuf.InvalidProtocolBufferException;
import ads_serving.IqiyiBidding;

public class BidRequestSimulator {

    public static void main(String[] args) {
        try {
            // Simulate the encoded request body
            String encodedRequestBody = "%0A%2089299dbb2cf855f2a0456f09c9c06264%12%12%0A%107daac83a336ff472%1A%BA%02%08%02Z%B5%022%0A..."; // Your encoded request body here
            String hexString = urlDecode(encodedRequestBody);
            byte[] bytes = hexStringToByteArray(hexString);

            // Create HttpURLConnection and send POST request
            URL url = new URL("http://example.com"); // Replace with the actual URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-protobuf");
            connection.setDoOutput(true);
            try (OutputStream os = connection.getOutputStream()) {
                os.write(bytes);
            }

            // Read response
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream is = connection.getInputStream();
                IqiyiBidding.BidRequest bidRequest = IqiyiBidding.BidRequest.parseFrom(is);
                System.out.println("Received BidRequest object: " + bidRequest);
            } else {
                System.out.println("Request failed with response code: " + responseCode);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String urlDecode(String encodedData) {
        try {
            return java.net.URLDecoder.decode(encodedData, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }
}


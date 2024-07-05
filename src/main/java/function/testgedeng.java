//package function;
//
//import com.alibaba.fastjson.JSONObject;
//import org.apache.commons.codec.digest.DigestUtils;
//
//import java.io.IOException;
//import java.net.URI;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
//
//public class testgedeng {
//    public static void main(String[] args) {
//        long currentTimeMillis = System.currentTimeMillis();
//        JSONObject body1 = JSONObject.parseObject("{\"data_type\":{\"inputPath1\":\"bi_file://a2bdace54f0b2a75612dd8a521293043|phone_number_md5\"},\"name\":\"tempTask1698219335552\"}");
//        HttpClient httpClient = HttpClient.newHttpClient();
//        String partnerId = "9ae47a84faa17e76d14344b2f64d97b8";
//        String url = "https://115.231.234.230:58888/td/openApi/audience/addPrivateAudience";
//
//        String xtoken = DigestUtils.md5Hex(body1.toJSONString()+currentTimeMillis+partnerId+"8d8ef9c2ecbfff51368b65f2deed98b9");
//
//        // 创建 HttpRequest 实例
//        HttpRequest httpRequest = HttpRequest.newBuilder()
//                .uri(URI.create(url))
//                .header("Content-Type", "application/json")
//                .header("partnerId", partnerId)
//                .header("timestamp", String.valueOf(currentTimeMillis))
//                .header("x-token", xtoken)
//                .POST(HttpRequest.BodyPublishers.ofString(body1.toJSONString()))
//                .build();
//
//        try {
//            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
//
//            if (httpResponse.statusCode() == 200) {
//                System.out.println("请求成功!");
//                System.out.println(httpResponse.body());
//            } else {
//                System.out.printf("请求失败，状态码：%d%n", httpResponse.statusCode());
//            }
//        } catch (IOException | InterruptedException e) {
//            System.err.println("发送请求时发生错误: " + e.getMessage());
//        }
//    }
//}

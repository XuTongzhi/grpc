package function;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;



public class testurl {
    public static void main(String[] args)  {
        token5();

    }
    public static void token1(){
        JSONObject body = JSONObject.parseObject("{\"validate\":true,\"type\":\"oaid_md5\"}");
//        JSONObject body = JSONObject.parseObject("{\"validate\":true}");
        long timestamp = System.currentTimeMillis();
        String s = String.valueOf(timestamp);
        System.out.println(s);
        String partnerId = "bada376e9d5bb5b06cd38af73a8befa1";
        String secretKey = "06d03fd09ecd6f9768f828b26bd6f17f";

        System.out.println(body.toJSONString());
        String xToken = DigestUtils.md5Hex(body.toJSONString() + s + partnerId + secretKey);
        System.out.println(xToken);
    }
    public static void token2(){
//        JSONObject body = JSONObject.parseObject("{\"data_type\":{\"inputPath1\":\"bi_file://374c0fc4fde15af7fc21d021e52c0749|mac_md5\"},\"name\":\"test\"}");
        JSONObject body = JSONObject.parseObject("{\"name\":\"test_oaid_md5\",\"data_type\":{\"inputPath1\":\"bi_file://f9c76ea41bc86c2fbe002e9746a592b2|oaid_md5\"}}");
        long timestamp = System.currentTimeMillis();
        String s = String.valueOf(timestamp);
        System.out.println(s);
        String partnerId = "bada376e9d5bb5b06cd38af73a8befa1";
        String secretKey = "06d03fd09ecd6f9768f828b26bd6f17f";

        System.out.println(body.toJSONString());
        String xToken = DigestUtils.md5Hex(body.toJSONString() + s + partnerId + secretKey);
        System.out.println(xToken);
    }

    public static void token3(){
        JSONObject body = JSONObject.parseObject("{\"conditions\":{\"tags\":{\"contain\":[[{\"foreignCode\":\"PIN01010100\"}]]}}}");
        long timestamp = System.currentTimeMillis();
        String s = String.valueOf(timestamp);
        System.out.println(s);
        String partnerId = "9ae47a84faa17e76d14344b2f64d97b8";
        String secretKey = "f0403d567fea398b3acb6a8d4b58201e";

        System.out.println(body.toJSONString());
        String xToken = DigestUtils.md5Hex(body.toJSONString() + s + partnerId + secretKey);
        System.out.println(xToken);
    }

    public static void token4(){
//        long currentTimeMillis = System.currentTimeMillis();
//        System.out.println(currentTimeMillis);
        JSONObject body1 = JSONObject.parseObject("{\"data_type\":{\"inputPath1\":\"bi_file://eff86ce99633d3ef571309afdd8c0122|mac_md5\"},\"name\":\"test\"}");
//        String currentTimeMillis = "1706692621513";
        String currentTimeMillis = "1706693089074";
        String partnerId = "9ae47a84faa17e76d14344b2f64d97b8";
        String secretKey = "fddf3193c3bf5d58562d0f3fcb6d0b50";

        String xtoken = DigestUtils.md5Hex(body1.toJSONString()+currentTimeMillis+partnerId+secretKey);
        System.out.println(xtoken);
    }

    public static void token5(){
        JSONObject body = JSONObject.parseObject("{\"groupId\":281290}");
        long timestamp = System.currentTimeMillis();
        String s = String.valueOf(timestamp);
        System.out.println(s);
        String partnerId = "bada376e9d5bb5b06cd38af73a8befa1";
        String secretKey = "06d03fd09ecd6f9768f828b26bd6f17f";

        System.out.println(body.toJSONString());
        String xToken = DigestUtils.md5Hex(body.toJSONString() + s + partnerId + secretKey);
        System.out.println(xToken);
    }

    public static void token6(){
        JSONObject body = JSONObject.parseObject("{}");
        long timestamp = System.currentTimeMillis();
        String s = String.valueOf(timestamp);
        System.out.println(s);
        String partnerId = "9ae47a84faa17e76d14344b2f64d97b8";
        String secretKey = "fabb15bd9f3d90fce4c0a42a329417c6";

        System.out.println(body.toJSONString());
        String xToken = DigestUtils.md5Hex(body.toJSONString() + s + partnerId + secretKey);
        System.out.println(xToken);
    }

    public static void token7(){
        String user_code = "shupan_test";
        String auth_code = "mghptm3#";
        long timestamp = System.currentTimeMillis();
        System.out.println(timestamp);
        String sign1= DigestUtils.md5Hex(user_code + DigestUtils.md5Hex(String.valueOf(timestamp)));
        String sign2= DigestUtils.md5Hex(user_code + auth_code + DigestUtils.md5Hex(String.valueOf(timestamp)));
        System.out.println(sign1);
        System.out.println(sign2);
    }
}

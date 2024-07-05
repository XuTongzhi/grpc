//package function;
//
//import iputils.AuthUtil;
//import iputils.HttpClient;
//
//public class RTQCode {
//    public static void main(String[] args) {
//        String accessKeyId="57b017ac48bee631469b";
//        String accessKeySecret="57b017ac48bee631469b1847b46dbc4f";
//        String method="POST";
//        String uri="/RTQ/has";
//        String ip="rtq_bj.mm.nequal.com";
//        String nonce="25388533";  //随机数
//        String ts="1683616164004";  //13位时间戳
//        String body="[{ \"id\":\"E445EC9E-1395-4367-A3C2-C0F2B42F7243\", \"dealId\": [\"1146299\"], \"idType\":\"idfa\" }]";
//        String[] paras = {};
//        String s= AuthUtil.HMACSHA1_SIGN_BUILDURL(accessKeyId, accessKeySecret, method, uri, nonce, ts, paras, body);
//        String url="http://"+ip+uri+s;  //生成url
//        String result= HttpClient.post(url, body);  //查询结果
//        System.out.println("============"+result);
//    }
//}

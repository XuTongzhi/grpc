//package function;
//
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpHeaders;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.ContentType;
//import org.apache.http.entity.protobuf.ProtobufEntity;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.util.EntityUtils;
//
//public class ProtobufPostExample {
//    public static void main(String[] args) throws Exception {
//        // 创建 HttpClient 实例
//        HttpClient client = HttpClients.createDefault();
//
//        // 创建请求体
//        MyProtos.MyMessage.Builder builder = MyProtos.MyMessage.newBuilder();
//        builder.setId(1234);
//        builder.setName("test");
//        MyProtos.MyMessage myMessage = builder.build();
//
//        // 创建 POST 请求
//        HttpPost post = new HttpPost("https://example.com/api");
//        post.setHeader(HttpHeaders.CONTENT_TYPE, "application/x-protobuf");
//
//        // 设置请求体
//        HttpEntity entity = new ProtobufEntity(myMessage);
//        post.setEntity(entity);
//
//        // 发送请求并获取响应
//        HttpResponse response = client.execute(post);
//        HttpEntity responseEntity = response.getEntity();
//
//        // 处理响应
//        if (responseEntity != null) {
//            byte[] data = EntityUtils.toByteArray(responseEntity);
//            // 处理响应数据
//        }
//    }
//}

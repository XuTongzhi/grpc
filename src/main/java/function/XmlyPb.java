//package function;
//
//import java.io.IOException;
//
//import com.google.protobuf.RtbProtos;
//import org.apache.http.HttpEntity;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.ByteArrayEntity;
//import org.apache.http.entity.ContentType;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.util.EntityUtils;
//
//public class XmlyPb {
//    public static void main(String[] args) throws IOException {
//        String dealid = "2446208";
//        String url = "http://127.0.0.1:8000";
//        RtbProtos.BidRequest.Builder reqBuilder = RtbProtos.BidRequest.newBuilder();
//        RtbProtos.BidRequest.Imp.Builder impBuilder = RtbProtos.BidRequest.Imp.newBuilder();
//        reqBuilder.setId("12451081203433");
//        reqBuilder.setApiVersion("v2.0");
//        impBuilder.setId("12451081203433244585101");
//        impBuilder.setTagid("28");
//        impBuilder.setTemplateid("3022");
//        impBuilder.addDealid(dealid);
//        impBuilder.setDate("2023-04-03");
//        impBuilder.setBrightscreen(true);
//
//        reqBuilder.setDevice(RtbProtos.BidRequest.Device.newBuilder()
//                .setUa("Mozilla/5.0 (iPhone; CPU iPhone OS 14_8_1 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/15E148")
//                .setIp("58.243.250.146")
//                .setGeo(RtbProtos.BidRequest.Device.Geo.newBuilder()
//                        .setLat(0)
//                        .setLon(0))
////                .setImeimd5("3BED654C26E1B300C28BFB57516A564D")
////                .setAndroididmd5("4FC2648ADCB015BFBA5967382B30C13E")
//                .setOpenudidmd5("D41D8CD98F00B204E9800998ECF8427E")
//                .setMacmd5("6792101BB32AEA40547278091B1CF100")
//                .setIdfamd5("6792101BB32AEA40547278091B1CF100")
//                .setDevicetype(1)
//                .setH(2208)
//                .setW(1242)
//                .setOs("iOS")
//                .setOsv("14.8.1")
//                .setCarrier(1)
//                .setConnectiontype(2)
//                .setMake("iPhone")
//                .setOaidmd5("")
//                .setModel("iPhone10-2")
//                .setIdfa("79E9400B-71E2-4AB1-89F1-BB203ED86326")
//                .setIdfalimit(1)
//        .setBootMark("1667522880.691231")
//        .setUpdateMark("1581696366.433985916")
//        .setIpv6("{\"pdp_ip1\":[\"2408:8546:5c80:cdc:143d:49a9:df18:a130\",\"2408:8546:5c80:cdc:dd0a:9c81:8b1e:d6f9\"]}")
//        .addCaids(0,RtbProtos.BidRequest.Device.CAID.newBuilder().setCaid("4f85601f1625c7c9eceff94bd7fd4e02").setVersion("20220111"))
//        .addCaids(1,RtbProtos.BidRequest.Device.CAID.newBuilder().setCaid("61ccae5eeb46cd9eee53041d40cb8fe3").setVersion("20211207"))
//        .setOsUpdateTime("1638036338.706776"));
//
//        reqBuilder.setUser(RtbProtos.BidRequest.User.newBuilder());
//
//        reqBuilder.setApp(RtbProtos.BidRequest.App.newBuilder()
//                .setName("喜马拉雅FM")
//                .setPkgname("com.gemd.iting")
//        .addCategory("电台")
//        .addCategory("有声书")
//        .addCategory("娱乐"));
//
//        reqBuilder.addImp(impBuilder);
//
//        RtbProtos.BidResponse.Builder respBuilder = RtbProtos.BidResponse.newBuilder();
//        RtbProtos.BidResponse.SeatBid.Builder seatBidBuilder = RtbProtos.BidResponse.SeatBid.newBuilder();
//        RtbProtos.BidResponse.Bid.Builder bidBuilder = RtbProtos.BidResponse.Bid.newBuilder();
//
//        seatBidBuilder.addBid(bidBuilder);
//        respBuilder.addSeatbid(seatBidBuilder);
//
//        RtbProtos.BidRequest req = reqBuilder.build();
//        System.out.println(req);
//        byte[] requestBytes = req.toByteArray();
//        System.out.println(requestBytes);
//
////        String jsonRequest = JsonFormat.printer().print(req);
//        HttpClient httpClient = HttpClients.createDefault();
//        HttpPost postRequest = new HttpPost(url + "/ximalaya.htm");
//        postRequest.addHeader("Content-Type", "application/x-protobuf");
//        ByteArrayEntity requestEntity = new ByteArrayEntity(requestBytes, ContentType.APPLICATION_OCTET_STREAM);
//        postRequest.setEntity(requestEntity);
////        StringEntity requestEntity = new StringEntity(jsonRequest, ContentType.APPLICATION_JSON);
////        postRequest.setEntity(requestEntity);
//        HttpEntity responseEntity = httpClient.execute(postRequest).getEntity();
//        byte[] binaryResponse = EntityUtils.toByteArray(responseEntity);
//        RtbProtos.BidResponse resp = RtbProtos.BidResponse.parseFrom(binaryResponse);
//        System.out.println(resp);
//    }
//
//}
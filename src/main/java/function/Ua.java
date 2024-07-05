package function;

import nl.basjes.parse.useragent.UserAgent;
import nl.basjes.parse.useragent.UserAgentAnalyzer;

public class Ua {
    public static void main(String[] args) {
        UserAgentAnalyzer uaa = UserAgentAnalyzer
                .newBuilder()
                .hideMatcherLoadStats()
//                .withField("DeviceBrand")
//                .withField("OperatingSystemClass")
//                .withField("OperatingSystemName")
//                .withField("OperatingSystemVersion")
//                .withField("AgentName")
                .withCache(10000)
                .build();
        String ua = "Youku;11.0.40;Android;10;COL-AL10";
        UserAgent agent = uaa.parse(ua);
        for (String fieldName: agent.getAvailableFieldNamesSorted()) {
            System.out.println(fieldName + " = " + agent.getValue(fieldName));
        }
    }
}

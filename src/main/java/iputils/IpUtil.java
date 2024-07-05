package iputils;


import java.math.BigInteger;

public class IpUtil {

    private static long modnum = (2L << 31);
    private static BigInteger res = BigInteger.valueOf(modnum);

    /**ipv4字符串转为long
     *
     */
    public static long ipToLong(String ipv4){
        String[] splits = ipv4.split("\\.");
        long l = 0;
        l = l + (Long.valueOf(splits[0], 10)) << 24;
        l = l + (Long.valueOf(splits[1], 10)) << 16;
        l = l + (Long.valueOf(splits[2], 10)) << 8;
        l = l + (Long.valueOf(splits[3], 10));
        return l;
    }

    /**long转为ipv4字符串
     *
     */
    public static String longToIp(long l){
        String ip = "";
        ip = ip + (l >>> 24);
        ip = ip + "." +((0x00ffffff & l) >>> 16);
        ip = ip + "." +((0x0000ffff & l) >>> 8);
        ip = ip + "." +(0x000000ff & l);
        return ip;
    }

    /**ipv6字符串转BigInteger数
     *
     */
    public static BigInteger ipv6ToInt(String ipv6){
        int compressIndex = ipv6.indexOf("::");
        if (compressIndex != -1){
            String part1s = ipv6.substring(0, compressIndex);
            String part2s = ipv6.substring(compressIndex + 1);
            BigInteger part1 = getBigInteger(part1s);
            BigInteger part2 = getBigInteger(part2s);
            int part1hasDot = 0;
            char ch[] = part1s.toCharArray();
            for(char c : ch){
                if(c == ':'){
                    part1hasDot++;
                }
            }
            System.out.println(part1.shiftLeft(16 * (7 - part1hasDot)).add(part2));
            return part1.shiftLeft(16 * (7 - part1hasDot)).add(part2).mod(res);
        }else {
            System.out.println(getBigInteger(ipv6));
            return getBigInteger(ipv6).mod(res);
        }
    }

    private static BigInteger getBigInteger(String ipv6) {
        String[] str = ipv6.split(":");
        BigInteger big = BigInteger.ZERO;
        for(int i = 0; i < str.length; i++){
            //::1
            if(str[i].isEmpty()){
                str[i] = "0";
            }
            big = big.add(BigInteger.valueOf(Long.valueOf(str[i], 16)).shiftLeft(16 * (str.length - i - 1)));
        }
        return big;
    }

    /**BigInteger数 转为ipv6字符串
     *
     */
    public static String intToIpv6(BigInteger big){
        String str = "";
        BigInteger ff = BigInteger.valueOf(0xffff);
        for (int i = 0; i < 8; i++){
            str = big.and(ff).toString(16) + ":" + str;
            big = big.shiftRight(16);
        }
        //去掉最后的：号
        str = str.substring(0, str.length() - 1);
        return str.replaceFirst("(^|:)(0+(:|$)){2,8}", "::");
    }

    /**将精简的ipv6地址扩展为全长度的ipv6地址
     *
     */
    public static String completeIpv6(String strIpv6){
        BigInteger big = ipv6ToInt(strIpv6);
        String str = big.toString(16);
        String completeIpv6Str = "";
        while(str.length() != 32){
            str = "0" + str;
        }
        for (int i = 0; i <= str.length(); i += 4){
            completeIpv6Str += str.substring(i, i + 4);
            if ((i + 4) == str.length()){
                break;
            }
            completeIpv6Str += ":";
        }
        return completeIpv6Str;
    }
}

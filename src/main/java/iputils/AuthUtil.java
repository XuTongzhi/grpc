//package iputils;
//
//import javax.crypto.Mac;
//import javax.crypto.SecretKey;
//import javax.crypto.spec.SecretKeySpec;
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//import java.security.MessageDigest;
//import java.util.Arrays;
//
//public class AuthUtil {
//
//	public static String md5Encrypt(String srcStr){
//		return encrypt("MD5", srcStr);
//	}
//
//	public static String sha1Encrypt(String srcStr){
//		return encrypt("SHA-1", srcStr);
//	}
//
//	public static String sha256Encrypt(String srcStr){
//		return encrypt("SHA-256", srcStr);
//	}
//
//	public static String sha384Encrypt(String srcStr){
//		return encrypt("SHA-384", srcStr);
//	}
//
//	public static String sha512Encrypt(String srcStr){
//		return encrypt("SHA-512", srcStr);
//	}
//
//	private static final String MAC_NAME = "HmacSHA1";
//    private static final String ENCODING = "UTF-8";
//    private static final String CUR_VERSION = "1";
//    public static final String METHOD_GET = "GET";
//    public static final String METHOD_POST = "POST";
//    public static final String MV="2";
//
//    private static byte [] HmacSHA1Encrypt(String accessSecretKey, String encryptText)
//    {
//		try {
//			//StringBuilder result = new StringBuilder();
//
//			byte[] data = accessSecretKey.getBytes(ENCODING);
//			// 根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称
//			SecretKey secretKey = new SecretKeySpec(data, MAC_NAME);
//			// 生成一个指定 Mac 算法 的 Mac 对象
//			Mac mac = Mac.getInstance(MAC_NAME);
//			// 用给定密钥初始化 Mac 对象
//			mac.init(secretKey);
//
//			byte[] text = encryptText.getBytes(ENCODING);
//			// 完成 Mac 操作
//			byte[] bytes = mac.doFinal(text);
////			for (byte b : bytes) {
////				String hex = Integer.toHexString(b & 0xFF);
////				if (hex.length() == 1)
////					result.append("0");
////				result.append(hex);
////			}
////			return result.toString();
//			return bytes;
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}
//	}
//
//	private static String encrypt(String algorithm, String srcStr) {
//		try {
//			StringBuilder result = new StringBuilder();
//			MessageDigest md = MessageDigest.getInstance(algorithm);
//			byte[] bytes = md.digest(srcStr.getBytes("utf-8"));
//			for (byte b :bytes) {
//				String hex = Integer.toHexString(b&0xFF);
//				if (hex.length() == 1)
//					result.append("0");
//				result.append(hex);
//			}
//			return result.toString();
//		}
//		catch (Exception e) {
//			throw new RuntimeException(e);
//		}
//	}
//
//	@SuppressWarnings("restriction")
//	public static String base64Encode(byte[] bstr){
//		   return new sun.misc.BASE64Encoder().encode(bstr);
//	}
//	@SuppressWarnings("restriction")
//	public static byte[] base64Decode(String str) {
//		byte[] bt = null;
//		try {
//			sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
//			bt = decoder.decodeBuffer(str);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		return bt;
//	}
//	/**
//	 *
//	 * @param accessKeySecret 秘钥
//	 * @param method 方法名
//	 * @param uri 访问后缀地址
//	 * @param nonce 随机数字
//	 * @param ts 时间戳
//	 * @param paras URL上的参数
//	 * @param body http的body内容，没有填"" 或 null
//	 * @return base64签名
//	 */
//	public static String HMACSHA1_SIGN_BASE64( String accessKeySecret, String method, String uri, String nonce, String ts, String [] paras, String body) {
//
//		StringBuilder encryptText = new StringBuilder();
//		encryptText
//		.append(method)
//		.append(uri)
//		.append(nonce)
//		.append(ts);
//		Arrays.sort(paras);
//		for(String para: paras) {
//			encryptText.append(para);
//		}
//		if(body!=null && !"".equals(body))
//			encryptText.append(body);
//		byte[] tmp = HmacSHA1Encrypt(accessKeySecret, encryptText.toString());
//		//System.out.println(encryptText.toString());
//		String sign = base64Encode(tmp);
//		System.out.println(sign);
//		//System.out.println("accessKeySecret=" + accessKeySecret + ", method=" + method + ", uri=" + uri + ", nonce=" + nonce + ", ts=" + ts + ", body=" + body + ", paras size=" + paras.length);
//		return sign;
//	}
//	/**
//	 *
//	 * @param accessKeySecret 秘钥
//	 * @param method 方法名
//	 * @param uri 访问后缀地址
//	 * @param nonce 随机数字
//	 * @param ts 时间戳
//	 * @param paras URL上的参数
//	 * @param body http的body内容，没有填"" 或 null
//	 * @return urlencode后的base64签名
//	 */
//	public static String HMACSHA1_SIGN_BASE64_URLENCODE( String accessKeySecret, String method, String uri, String nonce, String ts, String [] paras, String body ) {
//		String r = null;
//		try {
//			String base64 = HMACSHA1_SIGN_BASE64(accessKeySecret, method, uri, nonce, ts, paras, body);
//			r = java.net.URLEncoder.encode(base64,"utf-8");
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//		return r;
//	}
//	/**
//	 *
//	 * @param accessKeySecret 秘钥
//	 * @param method 方法名
//	 * @param uri 访问后缀地址
//	 * @param nonce 随机数字
//	 * @param ts 时间戳
//	 * @param paras URL上的参数
//	 * @param body http的body内容，没有填"" 或 null
//	 * @return 获得带签名和其他参数信息的url
//	 */
//	public static String HMACSHA1_SIGN_BUILDURL( String accessKeyId, String accessKeySecret, String method, String uri, String nonce, String ts, String [] paras , String body) {
//		String s = HMACSHA1_SIGN_BASE64_URLENCODE(accessKeySecret, method, uri, nonce, ts, paras, body);
//		StringBuilder parasUri= new StringBuilder();
//		for(String para: paras) {
//			String [] m = para.split("=");
//			parasUri
//			.append("&")
//			.append(m[0])
//			.append("=");
//			String p = null;
//			try {
//				p = java.net.URLEncoder.encode(m[1] ,"utf-8");
//			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
//			}
//			parasUri.append(p);
//		}
//		StringBuilder finaluri = new StringBuilder();
//		finaluri
//		.append("?s=")
//		.append(s)
//		.append("&a=")
//		.append(accessKeyId)
//		.append("&n=")
//		.append(nonce)
//		.append("&t=")
//		.append(ts)
//		.append("&v=")
//		.append(CUR_VERSION)
//		.append("&mv=")
//		.append(MV)
//		.append(parasUri);
//		return finaluri.toString();
//	}
//}
//
//
//
//

//package study.exhange.api.osl;
//
//import com.alibaba.fastjson.JSONObject;
//import com.google.common.hash.Hasher;
//import com.google.common.hash.Hashing;
//import java.io.IOException;
//import java.io.InputStream;
//import java.nio.charset.StandardCharsets;
//import java.security.KeyManagementException;
//import java.security.NoSuchAlgorithmException;
//import java.util.Base64;
//import javax.net.ssl.SSLContext;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.io.IOUtils;
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpStatus;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
//import org.apache.http.entity.ContentType;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//
///**
// * http utils
// *
// * @author chao
// * @since 2018-04-19
// */
//@Slf4j
//public class HttpUtils {
//
//    private static final byte[] SECRET = "".getBytes();
//    private static final String KEY = "";
//
//    public static void main(String[] args) {
//        String host = "https://trade.osl.com/";
//        String path = "api/3/account";
//
//        JSONObject map = new JSONObject();
//        map.put("nonce", genNonce());
//        map.put("userUuid", KEY);
//
//        String mapString = map.toJSONString();
//        String data = path + "\0" + mapString;
//        String sign = sign(Base64.getDecoder().decode(SECRET), data);
//
//        SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(getSSLContextTLS2());
//        CloseableHttpClient client = HttpClients.custom().setSSLSocketFactory(socketFactory).build();
//
//        HttpPost httpPost = new HttpPost(host + path);
//        httpPost.setHeader("Rest-Key", KEY);
//        httpPost.setHeader("Rest-Sign", sign);
//
//        HttpEntity httpEntity = new StringEntity(mapString, ContentType.APPLICATION_JSON);
//        httpPost.setEntity(httpEntity);
//        try {
//            CloseableHttpResponse response = client.execute(httpPost);
//            int statusCode = response.getStatusLine().getStatusCode();
//            if (statusCode == HttpStatus.SC_OK) {
//                try (InputStream in = response.getEntity().getContent()) {
//                    String result = IOUtils.toString(in, StandardCharsets.UTF_8.name());
//                    System.out.println(result);
//                }
//            } else {
//                log.error("https post fail:statusCode:" + statusCode);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static String sign(byte[] key, String data) {
//        Hasher hasher = Hashing.hmacSha512(key).newHasher();
//        hasher.putString(data, StandardCharsets.UTF_8);
//        return Base64.getEncoder().encodeToString(hasher.hash().asBytes());
//    }
//
//    private static long genNonce() {
//        return System.currentTimeMillis();
//    }
//
//    private static volatile SSLContext sslContextTLS2 = null;
//
//    /**
//     * paypal need TLSv1.2
//     */
//    private static SSLContext createSSLContextTLS2() {
//        SSLContext sslContext = null;
//        try {
//            sslContext = SSLContext.getInstance("TLSv1.2");
//            sslContext.init(null, null, null);
//        } catch (NoSuchAlgorithmException | KeyManagementException e) {
//            e.printStackTrace();
//        }
//        return sslContext;
//    }
//
//    private static SSLContext getSSLContextTLS2() {
//        if (sslContextTLS2 == null) {
//            sslContextTLS2 = createSSLContextTLS2();
//        }
//        return sslContextTLS2;
//    }
//
//}

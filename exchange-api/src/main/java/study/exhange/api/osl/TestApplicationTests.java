//package study.exhange.api.osl;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.alibaba.fastjson.serializer.SerializerFeature;
//import com.google.common.hash.Hasher;
//import com.google.common.hash.Hashing;
//import java.net.URI;
//import java.nio.charset.StandardCharsets;
//import java.util.Base64;
//import java.util.HashMap;
//import java.util.LinkedHashMap;
//import java.util.Map;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.web.client.RestTemplate;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class TestApplicationTests {
//
//    // chao
//    private static final byte[] SECRET = "".getBytes();
//    private static final String KEY = "";
//    private static final String OSL_HOST = "https://trade.osl.com/";
//
//    @Autowired
//    private RestTemplate restTemplate;
//
//    @Test
//    public void contextLoads() {
//
//        //============== support currency and currency pair ==================
////        String result = restTemplate.getForObject("https://trade.osl.com/api/3/currencyStatic", String.class);
////        System.out.println(result);
//
//        //============== Get account information ==================
////        String path = "api/3/account";
////        String mapString = rqAccount(path);
////        HttpEntity request = new HttpEntity<>(mapString, genTextPlainHeaders(path + "\0" + mapString));
////        try {
////            String result = restTemplate.postForObject(new URI(OSL_HOST + path), request, String.class);
////            System.out.println(result);
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
//
//        //============== transaction list ==================
////        String path = "api/3/transaction/list";
////        String mapString = rqTransactionList(path);
////        HttpEntity<String> request = new HttpEntity<>(mapString, genTextPlainHeaders(path + "\0" + mapString));
////        try {
////            String result = restTemplate.postForObject(new URI(OSL_HOST + path), request, String.class);
////            System.out.println(result);
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
//
//        //============== order list ==================
////        String path = "api/3/order/list";
////        String mapString = rqOrderList(path);
////        HttpEntity<String> request = new HttpEntity<>(mapString, genTextPlainHeaders(path + "\0" + mapString));
////        try {
////            String result = restTemplate.postForObject(new URI(OSL_HOST + path), request, String.class);
////            System.out.println(result);
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
//
//        //============== order new ==================
////        String path = "api/3/order/new";
////        String mapString = rqOrderNew(path);
////        HttpEntity<String> request = new HttpEntity<>(mapString, genHttpHeaders(path + "\0" + mapString));
////        try {
////            String result = restTemplate.postForObject(new URI(OSL_HOST + path), request, String.class);
////            System.out.println(result);
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
//
//        //============== retail quote ==================
//        String result = null;
//        String path = "api/3/retail/quote";
//        String amount = "0.08";
//
//        String mapString = rqRetailQuote(path, amount);
//        //HttpEntity<String> request = new HttpEntity<>(mapString, genHttpHeaders(path + "\0" + mapString));
//        HttpEntity<String> request = new HttpEntity<>(mapString, genTextPlainHeaders(path + "\0" + mapString));
//        try {
//            result = restTemplate.postForObject(new URI(OSL_HOST + path), request, String.class);
//            System.out.println(result);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        if (result != null) {
//            JSONObject quoteResult = JSONObject.parseObject(result);
//            if ("OK".equals(quoteResult.getString("resultCode"))) {
//                String quoteId = quoteResult.getJSONObject("quoteResponse").getString("quoteId");
//
//                //============== retail quote ==================
//                String pathTrade = "api/3/retail/trade";
//                String tradeMapString = rqRetailTrade(pathTrade, quoteId, amount);
//                //HttpEntity<String> request = new HttpEntity<>(mapString, genHttpHeaders(path + "\0" + mapString));
//                HttpEntity<String> tradeRequest = new HttpEntity<>(tradeMapString, genTextPlainHeaders(pathTrade + "\0" + tradeMapString));
//                try {
//                    String tradeResult = restTemplate.postForObject(new URI(OSL_HOST + pathTrade), tradeRequest, String.class);
//                    System.out.println(tradeResult);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//        //============== order info ==================
////        String path = "api/3/order/info";
////        String mapString = rqOrderInfo(path);
////        HttpEntity<String> request = new HttpEntity<>(mapString, genTextPlainHeaders(path + "\0" + mapString));
////        try {
////            String result = restTemplate.postForObject(new URI(OSL_HOST + path), request, String.class);
////            System.out.println(result);
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
//
//        //============== apiDoc list ==================
////        String path = "api/3/apiDoc/list";
////        String mapString = rqOrderInfo(path);
////        HttpEntity<String> request = new HttpEntity<>(mapString, genHttpHeaders(path + "\0" + mapString));
////        try {
////            String result = restTemplate.postForObject(new URI(OSL_HOST + path), request, String.class);
////            System.out.println(result);
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
//
//        //========================= v2 currency_pair/money/order/add===========
//        //String path = "btcusd/money/order/add";
////        String path = "btcusd/money/order/quote";
////        MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<>();
////        multiValueMap.add("nonce", genNonce());
////        multiValueMap.add("type", "bid");
////        multiValueMap.add("amount", 5 * 100000000);
////
////        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(multiValueMap, genHttpHeaders(path + "\0" + multiValueMap.toString()));
////        try {
////            String result = restTemplate.postForObject(new URI(OSL_HOST + "api/2/" + path), request, String.class);
////            System.out.println(result);
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
//    }
//
//    /**
//     * 账户信息
//     */
//    private String rqAccount(String path) {
//        Map<String, Object> map = new HashMap<>();
//        map.put("nonce", genNonce());
//        map.put("userUuid", KEY);
//
//        String data = JSON.toJSONString(map);
//        System.out.println("path:" + path + "\ndata:" + data);
//        return data;
//    }
//
//    /**
//     * 交易列表
//     */
//    private String rqTransactionList(String path) {
//        Map<String, Object> map = new HashMap<>();
//        long nonce = genNonce();
//        map.put("nonce", nonce);
//        map.put("userUuid", KEY);
//        map.put("from", (nonce / 1000 - (24 * 3600 * 1000)));
//        map.put("to", nonce / 1000);
//        map.put("max", 10);
//        map.put("offset", 0);
//        map.put("lang", "en-US");
//        String data = JSON.toJSONString(map);
//        System.out.println("path:" + path + "\ndata:" + data);
//        return data;
//    }
//
//    private String rqOrderList(String path) {
//        Map<String, Object> map = new HashMap<>();
//        map.put("nonce", genNonce());
//        map.put("userUuid", KEY);
//        map.put("activeOnly", false);
//        map.put("offset", 0);
//        map.put("max", 10);
//        String data = JSON.toJSONString(map);
//        System.out.println("path:" + path + "\ndata:" + data);
//        return data;
//    }
//
//    private String rqOrderNew(String path) {
//        Map<String, Object> map = new HashMap<>();
//        Map<String, Object> order = new HashMap<>();
//        long nonce = genNonce();
//        map.put("nonce", nonce);
//        map.put("tonce", nonce);
//        map.put("userUuid", KEY);
//        map.put("order", order);
//        order.put("orderType", "limit");
//        order.put("tradedCurrency", "BTC");
//        order.put("settlementCurrency", "USD");
//        order.put("tradedCurrencyAmount", "0.1");
////        order.put("settlementCurrencyAmount", "3000");
////        order.put("tradeSide", "buy");
//        order.put("buyTradedCurrency", true);
//        order.put("limitPriceInSettlementCurrency", "3735.12");
////        order.put("immediateOrCancel", false);
////        order.put("hiddenOrder", false);
////        order.put("fillOrKill", "fill");
//        String data = JSON.toJSONString(map, SerializerFeature.WriteBigDecimalAsPlain);
//        System.out.println("path:" + path + "\ndata:" + data);
//        return data;
//    }
//
//    /**
//     * 零售交易，先获取价格，再交易
//     *
//     * @param path request path
//     * @see <a href="https://bctv3.docs.apiary.io/#reference/retail-trading/retailquote">retail quote</a>
//     */
//    private String rqRetailQuote(String path, String amount) {
//        Map<String, Object> map = new LinkedHashMap<>();
//        Map<String, Object> quoteRequest = new LinkedHashMap<>();
//        long nonce = genNonce();
//        map.put("nonce", nonce);
////        map.put("userUuid", KEY);
//        map.put("quoteRequest", quoteRequest);
//        quoteRequest.put("tradedCurrency", "BTC");
//        quoteRequest.put("settlementCurrency", "USD");
//        quoteRequest.put("tradedCurrencyAmount", amount);
////        quoteRequest.put("settlementCurrencyAmount", "2000");
//        // 	true for buy currency
//        quoteRequest.put("buyTradedCurrency", false);
//        quoteRequest.put("isIndicativeQuote", false);
//        String data = JSON.toJSONString(map, SerializerFeature.WriteBigDecimalAsPlain);
//        System.out.println("path:" + path + "\ndata:" + data);
//        return data;
//    }
//
//    /**
//     * 零售交易
//     *
//     * @param path request path
//     * @param quoteId rqRetailQuote return quoteId
//     */
//    private String rqRetailTrade(String path, String quoteId, String amount) {
//        Map<String, Object> map = new LinkedHashMap<>();
//        Map<String, Object> tradeRequest = new LinkedHashMap<>();
//        long nonce = genNonce();
//        map.put("nonce", nonce);
////        map.put("userUuid", KEY);
//        map.put("tradeRequest", tradeRequest);
//        tradeRequest.put("quoteId", quoteId);
//        tradeRequest.put("amount", amount);
//        String data = JSON.toJSONString(map, SerializerFeature.WriteBigDecimalAsPlain);
//        System.out.println("path:" + path + "\ndata:" + data);
//        return data;
//    }
//
//    private String rqOrderInfo(String path) {
//        Map<String, Object> map = new HashMap<>();
//        long nonce = genNonce();
//        map.put("nonce", nonce);
//        map.put("tonce", nonce);
//        map.put("userUuid", KEY);
//        map.put("orderId", "565f2cdf-3f34-4881-bc80-46584c2c6b8d");
//        String data = JSON.toJSONString(map);
//        System.out.println("path:" + path + "\ndata:" + data);
//        return data;
//    }
//
//    private String rqApiDocList(String path) {
//        Map<String, Object> map = new HashMap<>();
//        long nonce = genNonce();
//        map.put("nonce", nonce);
//        map.put("userUuid", KEY);
//        map.put("locale", "zh_CN");
//        String data = JSON.toJSONString(map);
//        System.out.println("path:" + path + "\ndata:" + data);
//        return data;
//    }
//
//    private String sign(byte[] key, String data) {
////        Mac mac = null;
////        try {
////            mac = Mac.getInstance("HmacSHA512");
////        } catch (NoSuchAlgorithmException e) {
////            e.printStackTrace();
////        }
////        SecretKey secretKey = new SecretKeySpec(key, "HmacSHA512");
////        try {
////            mac.init(secretKey);
////        } catch (InvalidKeyException e) {
////            e.printStackTrace();
////        }
////        mac.update(data.getBytes());
////        return Base64.getEncoder().encodeToString(mac.doFinal()).trim();
//        Hasher hasher = Hashing.hmacSha512(key).newHasher();
//        hasher.putString(data, StandardCharsets.UTF_8);
//        return Base64.getEncoder().encodeToString(hasher.hash().asBytes());
//    }
//
//    private long genNonce() {
//        return System.currentTimeMillis() * 1000;
//    }
//
//    private HttpHeaders genHttpHeaders(String data) {
//        String sign = sign(Base64.getDecoder().decode(SECRET), data);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
//        headers.set("Rest-Key", KEY);
//        headers.set("Rest-Sign", sign);
//
//        System.out.println("sign:" + sign);
//
//        return headers;
//    }
//
//    private HttpHeaders genFormHttpHeaders(String data) {
//        String sign = sign(Base64.getDecoder().decode(SECRET), data);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//        headers.set("Rest-Key", KEY);
//        headers.set("Rest-Sign", sign);
//
//        System.out.println("sign:" + sign);
//
//        return headers;
//    }
//
//    private HttpHeaders genTextPlainHeaders(String data) {
//        String sign = sign(Base64.getDecoder().decode(SECRET), data);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.TEXT_PLAIN);
//        headers.set("Rest-Key", KEY);
//        headers.set("Rest-Sign", sign);
//
//        System.out.println("sign:" + sign);
//
//        return headers;
//    }
//}
//

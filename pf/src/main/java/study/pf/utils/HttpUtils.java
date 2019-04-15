package study.pf.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import javax.net.ssl.SSLContext;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * http utils
 *
 * @author chao
 * @since 2018-04-19
 */
public class HttpUtils {

    private static SSLContext sslContextTLS2 = null;

    /**
     * paypal need TLSv1.2
     */
    private static SSLContext createSSLContextTLS2() {
        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance("TLSv1.2");
            sslContext.init(null, null, null);
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
        }
        return sslContext;
    }

    private static SSLContext getSSLContextTLS2() {
        if (sslContextTLS2 == null) {
            sslContextTLS2 = createSSLContextTLS2();
        }
        return sslContextTLS2;
    }

    /**
     * https post request
     */
    public static String doHttpsPost(String url, Map<String, String> params, ContentType contentType, String charset) {
        SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(getSSLContextTLS2());
        CloseableHttpClient client = HttpClients.custom().setSSLSocketFactory(socketFactory).build();

        HttpPost httpPost = new HttpPost(url);

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        sb.deleteCharAt(sb.length() - 1);

        HttpEntity httpEntity = new StringEntity(sb.toString(), contentType);
        httpPost.setEntity(httpEntity);
        try {
            CloseableHttpResponse response = client.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                try (InputStream in = response.getEntity().getContent()) {
                    return IOUtils.toString(in, charset);
                }
            } else {
                //log.error("https post fail:statusCode:" + statusCode);
            }
        } catch (IOException e) {
            //log.error("执行Post请求" + url + "时，发生异常！", e);
        }
        return null;
    }

    /**
     * https post request
     */
    public static String doHttpsGet(String url, Map<String, String> params, String charset) {
        /*
         * append url
         */
        StringBuilder buffer = new StringBuilder(url);
        try {
            if (params != null) {
                if (url.indexOf('?') != -1) { // 连接参数
                    buffer.append("&");
                } else {
                    buffer.append("?");
                }
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    if (!buffer.toString().endsWith("?") && !buffer.toString().endsWith("&")) { // 已经添加过参数
                        buffer.append("&");
                    }
                    buffer.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8.name()));
                    buffer.append("=");
                    buffer.append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8.name()));
                }
            }
        } catch (UnsupportedEncodingException e) {
            //log.error("Encoding not supported");
            return null;
        }

        SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(getSSLContextTLS2());
        CloseableHttpClient client = HttpClients.custom().setSSLSocketFactory(socketFactory).build();
        HttpGet httpGet = new HttpGet(buffer.toString());
        try {
            CloseableHttpResponse response = client.execute(httpGet);
            int httpStatus = response.getStatusLine().getStatusCode();
            if (httpStatus == HttpStatus.SC_OK) {
                try (InputStream in = response.getEntity().getContent()) {
                    return IOUtils.toString(in, charset);
                }
            } else {
                //log.error("https get fail:statusCode:" + httpStatus);
            }
        } catch (IOException e) {
            //log.error("执行Get请求" + url + "时，发生异常！", e);
        }
        return null;
    }
    
    public static String postBody(String url, String body) {
        // 实例化httpClient
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 实例化post方法
        HttpPost httpPost = new HttpPost(url);
        // 结果
        CloseableHttpResponse response = null;
        String content = null;
        httpPost.setHeader("Content-Type" , "application/json");
        try {
            // 将参数给post方法
            if (body != null) {
                StringEntity stringEntity = new StringEntity(body , "UTF-8"); 
                httpPost.setEntity(stringEntity);
            }
            // 执行post方法
            response = httpclient.execute(httpPost);
            int httpStatus = response.getStatusLine().getStatusCode();
            if (httpStatus == HttpStatus.SC_OK) {
                try {
                    content = EntityUtils.toString(response.getEntity(), "UTF-8");
                } finally {
                    response.close();
                }
            }else{
            	//log.error("https get fail:statusCode:" + httpStatus);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return content;
    }
}

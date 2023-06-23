

package study.pf.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * GPTUtils
 *
 * @author xiachao
 * @date 2023/4/9
 */
public class GPTUtils {

    public static void main(String[] args) {
        String authorization = "Bearer " + "xxx";
        //String url = "https://api.openai.com/v1/models";
        String url = "https://api.openai.com/v1/chat/completions";
        Map<String, String> headers = new HashMap<>(16);
        headers.put("Authorization", authorization);
        headers.put("Content-Type", "application/json");

        Map<String, Object> params = new HashMap<>(16);
        params.put("model", "gpt-3.5-turbo");
        JSONArray messages = new JSONArray();
        JSONObject message = new JSONObject();
        message.put("role", "user");
        message.put("content", "Say this is a test!");
        messages.add(message);
        params.put("messages", messages);
        params.put("temperature", 0.7);
        String body = JSONObject.toJSONString(params);
        System.out.println(body);
        String result = HttpUtils.postBodyWithHeaders(url, body, headers);

        System.out.println(result);
    }

}

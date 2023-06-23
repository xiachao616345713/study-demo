//package study.pf.service.impl;
//
//import com.alibaba.fastjson.JSONObject;
//import java.nio.charset.StandardCharsets;
//import java.util.LinkedHashMap;
//import java.util.Map;
//import org.apache.commons.lang3.StringUtils;
//import study.pf.service.FacebookService;
//import study.pf.utils.HttpUtils;
//
///**
// * facebook
// *
// * @author chao
// * @since 2018-05-10
// */
//public class FacebookServiceImpl implements FacebookService {
//    /**
//     * debug token url
//     */
//    private static final String DEBUG_TOKEN_URL = "https://graph.facebook.com/debug_token";
//    /**
//     * me url
//     */
//    private static final String ME_URL = "https://graph.facebook.com/me";
//
//    /**
//     * application access_token
//     */
//    //private static final String ACCESS_TOKEN = PropKit.appMap().get("fb.access_token");
//    private static final String ACCESS_TOKEN = "fb.access_token";
//    /**
//     * client_id
//     */
//    //private static final String CLIENT_ID = PropKit.appMap().get("fb.client_id");
//    private static final String CLIENT_ID = "fb.client_id";
//
//    /**
//     * validate facebook token
//     *
//     * @param inputToken need validate token
//     * @return result
//     */
//    @Override
//    public boolean debugToken(String inputToken) {
//        Map<String, String> map = new LinkedHashMap<>();
//        map.put("input_token", inputToken);
//        map.put("access_token", ACCESS_TOKEN);
//        String ret = HttpUtils.doHttpsGet(DEBUG_TOKEN_URL, map, StandardCharsets.UTF_8.name());
//        System.out.println(ret);
//        if (StringUtils.isNotBlank(ret)) {
//            JSONObject json = JSONObject.parseObject(ret);
//            if (json != null && json.containsKey("data")) {
//                json = json.getJSONObject("data");
//                if (json.getBooleanValue("is_valid")) {
//                    return CLIENT_ID.equals(json.get("app_id"));
//                }
//            }
//        }
//        return false;
//    }
//
//    /**
//     * get user info(parameter fields)
//     *
//     * @param accessToken 用户访问口令
//     * @return user info
//     */
//    @Override
//    public JSONObject me(String accessToken){
//        Map<String, String> map = new LinkedHashMap<>();
//        map.put("fields","id,name,picture,email");
//        map.put("access_token",accessToken);
//        String ret = HttpUtils.doHttpsGet(ME_URL, map, StandardCharsets.UTF_8.name());
//        if (StringUtils.isNotBlank(ret)) {
//            return JSONObject.parseObject(ret);
//        }
//        return null;
//    }
//}

package study.pf.service;

import com.alibaba.fastjson.JSONObject;

/**
 * facebook
 *
 * @author chao
 * @since 2018-05-10
 */
public interface FacebookService {

    /**
     * validate facebook token
     *
     * @param inputToken need validate token
     * @return result
     */
    boolean debugToken(String inputToken);

    /**
     * get user info(parameter fields)
     *
     * @param accessToken 用户访问口令
     * @return user info
     */
    JSONObject me(String accessToken);
}

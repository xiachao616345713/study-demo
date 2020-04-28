package rpc;

import com.alibaba.fastjson.JSON;
import java.nio.charset.StandardCharsets;

/**
 * method
 *
 * @author xiachao
 * @date 2019/11/27
 */
public class MyMethod {

    public byte[] handler(String method, Object... parameter) {
        String result = JSON.toJSONString(ResultGenerator.genOkResult("hello world"));
        return toByte(result);
    }

    private byte[] toByte(String result) {
        return result.getBytes(StandardCharsets.UTF_8);
    }

}

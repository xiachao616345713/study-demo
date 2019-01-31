package study.exhange.api.websocket;

import com.alibaba.fastjson.JSONObject;
import java.net.URI;
import java.nio.ByteBuffer;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import study.exhange.api.utils.CommonUtil;

/**
 * @author chao
 * @since 2018-12-25
 */
public class HitbtcClient extends WebSocketClient {

    public static final String WSS_URL = "wss://api.hitbtc.com/api/2/ws";

    public HitbtcClient(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake handshakeData) {
        System.out.println("open:" + JSONObject.toJSONString(handshakeData));

        JSONObject tickerRoom = new JSONObject();
        tickerRoom.put("id", "ETHBTC");
        tickerRoom.put("method", "subscribeTicker");
        JSONObject params = new JSONObject();
        params.put("symbol", "ETHBTC");
        tickerRoom.put("params", params);
        this.send(tickerRoom.toJSONString());

//        JSONObject tickerRoom = new JSONObject();
//        tickerRoom.put("id", "ETHBTC");
//        tickerRoom.put("method", "subscribeCandles");
//        JSONObject params = new JSONObject();
//        params.put("symbol", "ETHBTC");
//        params.put("period", "M1");
//        tickerRoom.put("params", params);
//        this.send(tickerRoom.toJSONString());
    }

    @Override
    public void onMessage(String message) {
        System.out.println("message:" + message);
    }

    @Override
    public void onMessage(ByteBuffer bytes) {
        try {
            String message = new String(CommonUtil.gzipDecompress(CommonUtil.base64Decode(bytes.array())), "UTF-8");
            System.out.println(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("code:" + code + ",reason:" + reason + ",remote：" + remote);
    }

    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
    }

    @Override
    public boolean isClosed() {
        return super.isClosed();
    }
}

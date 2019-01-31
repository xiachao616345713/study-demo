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
public class OkexClient extends WebSocketClient {

    private static final String WSS_URL = "wss://real.okex.com:10440/ws/v1";

    public OkexClient(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake handshakeData) {
        System.out.println("open:" + JSONObject.toJSONString(handshakeData));

//        JSONObject room = new JSONObject();
//        room.put("event", "addChannel");
//        room.put("channel", String.format("ok_sub_spot_%s_ticker", "ltc_btc"));
//        this.send(room.toJSONString());

        JSONObject room = new JSONObject();
        room.put("event", "addChannel");
        room.put("channel", String.format("ok_sub_spot_%s_kline_1min", "ltc_btc"));
        this.send(room.toJSONString());

//        JSONObject room = new JSONObject();
//        room.put("event", "addChannel");
//        room.put("channel", String.format("ok_sub_futureusd_%s_ticker_%s", "btc", "this_week"));
//        this.send(room.toJSONString());
    }

    @Override
    public void onMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void onMessage(ByteBuffer bytes) {
        try {
            String message = new String(CommonUtil.deflateDecompress(bytes.array()), "UTF-8");
            System.out.println("message:" + message);
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

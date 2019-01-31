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
public class BitfinexClient extends WebSocketClient {

    public static final String WSS_URL = "wss://api.bitfinex.com/ws/";

    public BitfinexClient(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake handshakeData) {
        System.out.println("open:" + JSONObject.toJSONString(handshakeData));

        JSONObject room = new JSONObject();
        room.put("event", "subscribe");
        room.put("channel", "ticker");
        room.put("pair", "BTCUSD");
        this.send(room.toJSONString());

//        JSONObject room = new JSONObject();
//        room.put("channel", "candles");
//        room.put("event", "subscribe");
//        room.put("key", "trade:1m:tETHBTC");
//        this.send(room.toJSONString());
//
//        JSONObject room = new JSONObject();
//        room.put("channel", "book");
//        room.put("event", "subscribe");
//        room.put("symbol", "tGOTEUR");
//        //tickerRoom.put("precision", "p0");
//        //tickerRoom.put("frequency", "2sec");
//        this.send(room.toJSONString());
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

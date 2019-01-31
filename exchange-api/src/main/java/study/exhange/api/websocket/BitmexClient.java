package study.exhange.api.websocket;

import com.alibaba.fastjson.JSONObject;
import java.net.URI;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

/**
 * @author chao
 * @since 2018-12-25
 */
public class BitmexClient extends WebSocketClient {

    public static final String WSS_URL = "wss://www.bitmex.com/realtime?subscribe=tradeBin1m:XBTUSD";
    //public static final String WSS_URL = "wss://www.bitmex.com/realtime?subscribe=quote:XBTUSD,trade:XBTUSD";
    //public static final String WSS_URL = "wss://www.bitmex.com/realtime?subscribe=quote:XRPZ18";
    //public static final String WSS_URL = "wss://www.bitmex.com/realtime?subscribe=trade:XRPZ18";
    //public static final String WSS_URL = "wss://www.bitmex.com/realtime?subscribe=quoteBin1d:ADAZ18";

    public BitmexClient(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake handshakeData) {
        System.out.println("open:" + JSONObject.toJSONString(handshakeData));
    }

    @Override
    public void onMessage(String message) {
        System.out.println(message);
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

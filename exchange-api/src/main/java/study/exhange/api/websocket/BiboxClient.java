package study.exhange.api.websocket;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.channels.NotYetConnectedException;
import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.framing.Framedata;
import org.java_websocket.handshake.ServerHandshake;
import study.exhange.api.utils.CommonUtil;

/**
 * @author chao
 * @since 2018-12-25
 */
public class BiboxClient extends WebSocketClient {

    public static final String WSS_URL = "wss://push.bibox.com/";

    public BiboxClient(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake handshakeData) {
        System.out.println("open:" + JSONObject.toJSONString(handshakeData));

        JSONObject klineRoom = new JSONObject();
        klineRoom.put("event", "addChannel");
//      klineRoom.put("channel", String.format("bibox_sub_spot_%s_kline_%s", "BTC_USDT", "1min"));
        klineRoom.put("channel", String.format("bibox_sub_spot_%s_ticker", "BTC_USDT"));
        this.send(klineRoom.toJSONString());
    }

    @Override
    public void onMessage(String message) {
        try {
            //ticker
            JSONObject jsonObject = JSONArray.parseArray(message).getJSONObject(0);
            if ("1".equals(jsonObject.getString("data_type"))) {
                String tick = new String(CommonUtil.gzipDecompress(CommonUtil.base64Decode(jsonObject.getString("data"))), "UTF-8");

                System.out.println("tick:" + tick);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onMessage(ByteBuffer bytes) {
        System.out.println("receive ByteBuffer");
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

    @Override
    public void sendPing() throws NotYetConnectedException {
        System.out.println("ping xxxxxxx");
        send("{\"ping\":" + System.currentTimeMillis() + "}");
    }

    @Override
    public void onWebsocketPong(WebSocket conn, Framedata f) {
        System.out.println("onWebsocketPong xxxxxxx");
        super.onWebsocketPong(conn, f);
    }

    @Override
    public void onWebsocketPing(WebSocket conn, Framedata f) {
        System.out.println("onWebsocketPing xxxxxxx");
        super.onWebsocketPing(conn, f);
    }
}

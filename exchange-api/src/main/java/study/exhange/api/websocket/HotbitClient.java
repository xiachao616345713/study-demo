package study.exhange.api.websocket;

import com.alibaba.fastjson.JSONObject;
import java.net.URI;
import java.nio.channels.NotYetConnectedException;
import java.util.Collections;
import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.framing.Framedata;
import org.java_websocket.handshake.ServerHandshake;

/**
 * @author chao
 * @since 2018-12-25
 */
public class HotbitClient extends WebSocketClient {

    public static final String WSS_URL = "wss://ws.hotbit.io";

    public HotbitClient(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake handshakeData) {
        System.out.println("open:" + JSONObject.toJSONString(handshakeData));

        JSONObject room = new JSONObject();
        room.put("method", "depth.subscribe");
        Object[] objects = new Object[3];
        objects[1] = 1;
        objects[2] = "0.00000001";
        objects[0] = "ETHBTC";
        room.put("params", objects);
        room.put("id", 100);
        System.out.println(room.toJSONString());
        this.send(room.toJSONString());

        objects[0] = "BTCUSDT";
        room.put("params", objects);
        room.put("id", 100);
        System.out.println(room.toJSONString());
        this.send(room.toJSONString());

        objects[0] = "ETHUSDT";
        room.put("params", objects);
        room.put("id", 100);
        System.out.println(room.toJSONString());
        this.send(room.toJSONString());
    }

    @Override
    public void onMessage(String message) {
        System.out.println("message:" + message);
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
        JSONObject room = new JSONObject();
        room.put("method", "server.ping");
        room.put("params", Collections.EMPTY_LIST);
        room.put("id", 100);
        System.out.println(room.toJSONString());
        this.send(room.toJSONString());
    }

    @Override
    public void onWebsocketPong(WebSocket conn, Framedata f) {
        System.out.println(f.getOpcode().toString() + "-" + new String(f.getPayloadData().array()));
        super.onWebsocketPong(conn, f);
    }

    @Override
    public void onWebsocketPing(WebSocket conn, Framedata f) {
        System.out.println(f.getOpcode().toString() + new String(f.getPayloadData().array()));
        super.onWebsocketPing(conn, f);
    }
}

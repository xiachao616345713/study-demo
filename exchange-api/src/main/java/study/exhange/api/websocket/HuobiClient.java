package study.exhange.api.websocket;

import com.alibaba.fastjson.JSONObject;
import java.net.URI;
import java.nio.ByteBuffer;
import org.apache.commons.lang3.StringUtils;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import study.exhange.api.utils.CommonUtil;

/**
 * @author chao
 * @since 2018-12-25
 */
public class HuobiClient extends WebSocketClient {

    public static final String WSS_URL = "wss://api.huobi.pro/ws";

    public HuobiClient(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake handshakeData) {
        System.out.println("open:" + JSONObject.toJSONString(handshakeData));

        JSONObject room = new JSONObject();
        room.put("id", 123);
        room.put("sub", "market.btcusdt.kline.1min");
        send(room.toJSONString());
        //room.put("sub", String.format("market.%s.depth.%s", "btcusdt", "step0"));
        //room.put("sub", String.format("market.%s.detail", "btcusdt"));
        //room.put("sub", "market.tickers");
//                // ticker
//                room.put("sub", String.format("market.%s.detail", "xrpusdt"));
//                send(room.toJSONString());
//                // depth
//                room.put("sub", String.format("market.%s.depth.step0", "xrpusdt"));
//                send(room.toJSONString());
    }

    @Override
    public void onMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void onMessage(ByteBuffer bytes) {
        try {
            String message = new String(CommonUtil.gzipDecompress(bytes.array()), "UTF-8");
            JSONObject jsonObject = JSONObject.parseObject(message);
            String op = jsonObject.getString("op");
            String errCode = jsonObject.getString("err-code");
            if (!StringUtils.isEmpty(message)) {
                if (message.indexOf("ping") > 0) {
                    String pong = jsonObject.toString();
                    send(pong.replace("ping", "pong"));
                } else if ("auth".equals(op)) {
                    //鉴权结果
                    if ("0".equals(errCode)) {
                        System.out.println(message);
                    } else {
                        //鉴权成功发送sub 请求
                        //sendSub("accounts", "12123");
                    }
                } else {
                    System.out.println("message:" + message);

                    //depth
//                    JSONObject tick = jsonObject.getJSONObject("tick");
//                    if (tick != null) {
//                        JSONArray bid = tick.getJSONArray("bids").getJSONArray(0);
//                        JSONArray ask = tick.getJSONArray("asks").getJSONArray(0);
//                        data.put("BidPrice1", bid.get(0));
//                        data.put("BidVolume1", bid.get(1));
//                        data.put("AskPrice1", ask.get(0));
//                        data.put("AskVolume1", ask.get(1));
//
//                        System.out.println(JSONObject.toJSONString(data));
//                    }
                }
            }
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

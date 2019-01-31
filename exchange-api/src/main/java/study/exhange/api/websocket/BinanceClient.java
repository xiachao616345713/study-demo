package study.exhange.api.websocket;

import com.alibaba.fastjson.JSONObject;
import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import study.exhange.api.utils.CommonUtil;

/**
 * @author chao
 * @since 2018-12-25
 */
public class BinanceClient extends WebSocketClient {

    public static final String WSS_URL = "wss://stream.binance.com:9443/ws/btcusdt@ticker";
    //public static final String WSS_URL = "wss://stream.binance.com:9443/ws/btcusdt@kline_1m";
    //public static final String WSS_URL = "wss://stream.binance.com:9443/ws/btcusdt@ticker";
    //public static final String WSS_URL = "wss://stream.binance.com:9443/stream?streams=ethbtc@ticker/btcusdt@kline_m";

    public BinanceClient(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake handshakeData) {
        System.out.println("open:" + JSONObject.toJSONString(handshakeData));
    }

    @Override
    public void onMessage(String message) {
        Long tempE = JSONObject.parseObject(message).getLong("E");
        LocalDateTime tradeDateTime = CommonUtil.convertMilliSeconds(tempE);
        System.out.println(DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss")
                .format(tradeDateTime) + "." + tradeDateTime.getNano() / (1000 * 1000) + ":" + message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("code:" + code + ",reason:" + reason + ",remote" + remote);
    }

    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
    }
}

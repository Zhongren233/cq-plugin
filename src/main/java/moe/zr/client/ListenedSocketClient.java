package moe.zr.client;

import com.alibaba.fastjson.JSON;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import moe.zr.handler.MessageHandlerCenter;
import moe.zr.vo.in.Message;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
@Slf4j
public class ListenedSocketClient extends WebSocketClient {

    public ListenedSocketClient(URI serverUri) {
        super(serverUri);
    }


    @Override
    public void onOpen(ServerHandshake serverHandshake) {
       log.info("连接成功");
    }

    @Override
    public void onMessage(String s) {
        Message message = JSON.parseObject(s, Message.class);
        MessageHandlerCenter.onMessage(message);
    }

    @SneakyThrows
    @Override
    public void onClose(int i, String s, boolean b) {
        log.warn("丢失连接");
        new Thread(this::reconnect).start();
    }

    @Override
    public void onError(Exception e) {
        if (!(e instanceof NullPointerException)){
           log.error(e.getMessage());
        }
    }


}

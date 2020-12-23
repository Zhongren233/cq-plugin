package moe.zr.client;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import moe.zr.vo.in.Result;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
@Slf4j
public class APISocketClient extends WebSocketClient {

    public APISocketClient(URI serverUri) {
        super(serverUri);
    }


    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        log.info("连接成功");
    }

    @Override
    public void onMessage(String s) {
        Result result = JSON.parseObject(s, Result.class);
        logResult(result);
        log.info("{} 状态: {}",result.getEcho(),result.getStatus());
    }

    public void logResult(Result result){
        if (result.getRetcode()!=0) {
            log.warn("message_id: {} 发送失败",result);
        }
    }

    @Override
    public void onClose(int i, String s, boolean b) {
        new Thread(this::reconnect).start();
    }

    @Override
    public void onError(Exception e) {
        if (!(e instanceof NullPointerException)) {
            log.error(e.getMessage());
        }
    }
}

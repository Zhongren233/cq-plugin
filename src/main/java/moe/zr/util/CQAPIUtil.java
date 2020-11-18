package moe.zr.util;

import com.alibaba.fastjson.JSON;
import moe.zr.client.APISocketClient;
import moe.zr.vo.CQAPI;
import moe.zr.vo.in.Message;
import moe.zr.vo.out.SendMessage;

import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Function;

public class CQAPIUtil {
    public static APISocketClient socketClient;

    public static void setSocketClient(APISocketClient socketClient) {
        CQAPIUtil.socketClient = socketClient;
    }

    public static Consumer<SendMessage> send = (message -> {
        CQAPI send_msg = new CQAPI().setAction("send_msg").setParams(message).setEcho(UUID.randomUUID().toString());
        String s = JSON.toJSONString(send_msg);
        socketClient.send(s);
    });
    public static Function<Message, SendMessage> castMessage = (SendMessage::new);

/*

    public static void holdHands(Message message) {
    send.accept(castMessage.apply(message).setMessage("[CQ:image,file=2ee882b144dee9ce28e5da681626f529.image]"));
    }

 */
}
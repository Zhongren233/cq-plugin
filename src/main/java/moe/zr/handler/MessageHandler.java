package moe.zr.handler;

import lombok.extern.slf4j.Slf4j;
import moe.zr.util.CQAPIUtil;
import moe.zr.vo.in.Message;

@Slf4j
public class MessageHandler {

    public static void onMessage(Message message) {
        if (message.getMessage() == null) return;
        log.info("群 {} 内 {}({}) 的消息:{}",
                message.getGroup_id(),
                message.getSender().getNickname(),
                message.getSender().getUser_id(),
                message.getMessage());
        if (message.getMessage().contains("朋友")) {
            CQAPIUtil.ridicule(message);
        }

        if (message.getMessage().contains("牵手")) {
            CQAPIUtil.holdHands(message);
        }
    }


}

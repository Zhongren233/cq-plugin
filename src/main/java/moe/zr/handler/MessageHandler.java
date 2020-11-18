package moe.zr.handler;

import lombok.extern.slf4j.Slf4j;
import moe.zr.util.CQAPIUtil;
import moe.zr.vo.CQAPI;
import moe.zr.vo.in.Message;

@Slf4j
public class MessageHandler {

    public static void onMessage(Message message) {
        if (message.getMessage() == null) return;
        log.info(message.toString());
        if (message.getMessage().contains("朋友")) {
            CQAPIUtil.ridicule(message);
        }

        if (message.getMessage().contains("牵手")) {
            CQAPIUtil.holdHands(message);
        }
    }


}

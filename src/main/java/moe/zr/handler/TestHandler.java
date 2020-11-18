package moe.zr.handler;

import lombok.extern.slf4j.Slf4j;
import moe.zr.annotation.Handler;
import moe.zr.annotation.MessageContains;
import moe.zr.annotation.MessageStartWith;
import moe.zr.util.CQAPIUtil;
import moe.zr.vo.in.Message;

@Handler
@Slf4j
public class TestHandler {
    @MessageContains("朋友")
    public static void ridicule(Message m) {
        CQAPIUtil.send.accept(
                CQAPIUtil.castMessage.apply(m)
                        .setMessage("[CQ:image,file=7b754c81dffa4018ac179eac8805c062.image]")
        );
    }

    @MessageStartWith("牵手")
    public static void holdHands(Message m) {
        CQAPIUtil.send.accept(
                CQAPIUtil.castMessage.apply(m)
                        .setMessage("[CQ:image,file=2ee882b144dee9ce28e5da681626f529.image]")
        );
    }


}
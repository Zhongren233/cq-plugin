package moe.zr.handler;

import com.sapher.youtubedl.YoutubeDLException;
import lombok.extern.slf4j.Slf4j;
import moe.zr.annotation.Command;
import moe.zr.annotation.MessageStartWith;
import moe.zr.service.YoutubeService;
import moe.zr.util.CQAPIUtil;
import moe.zr.vo.in.Message;

import javax.lang.model.element.VariableElement;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 管理用的
 */
@Slf4j
public class AdminHandler {
    @Command("status")
    public static void status(Message m) {
        int commandSize = MessageHandlerCenter.getCommandMapping().size();
        ThreadPoolExecutor executorService = MessageHandlerCenter.getExecutorService();
        int poolSize = executorService.getPoolSize();
        String s =
                "状态:ok\n"+
                "指令数量:" + commandSize + "\n" +
                "线程池大小:" + poolSize;
        CQAPIUtil.sendMessage(m, s);
    }
}

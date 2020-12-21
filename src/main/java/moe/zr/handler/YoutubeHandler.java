package moe.zr.handler;

import com.sapher.youtubedl.YoutubeDLException;
import moe.zr.annotation.Handler;
import moe.zr.annotation.MessageStartWith;
import moe.zr.service.YoutubeService;
import moe.zr.util.CQAPIUtil;
import moe.zr.vo.in.Message;

/**
 * 此类用来处理Youtube相关逻辑
 */
@Handler
public class YoutubeHandler {
    @MessageStartWith("https://www.youtube.com/watch?")
    public static void downLoadAudio(Message message) {
        String url = message.getMessage();
        String str;
        try {
            str = YoutubeService.downloadAudio(url);
        } catch (YoutubeDLException e) {
            e.printStackTrace();
            str = e.getMessage();
        }
        CQAPIUtil.send.accept(
                CQAPIUtil.castMessage.apply(message)
                        .setMessage(str)
        );
    }
}

package moe.zr.handler;

import com.sapher.youtubedl.YoutubeDLException;
import lombok.extern.slf4j.Slf4j;
import moe.zr.annotation.MessageStartWith;
import moe.zr.service.YoutubeService;
import moe.zr.util.CQAPIUtil;
import moe.zr.vo.in.Message;

/**
 * 此类用来处理Youtube相关逻辑
 */
@Slf4j
public class YoutubeHandler {
    public static void downLoadAudio(Message message) {
        String url = message.getMessage();
        CQAPIUtil.send.accept(
                CQAPIUtil.castMessage.apply(message)
                        .setMessage("开始下载" + url)
        );
        String str;
        try {
            String filename = YoutubeService.downloadAudio(url);
            str = "下载成功,地址为http://down.akina.xyz/" + filename;
        } catch (YoutubeDLException e) {
            e.printStackTrace();
            str = "发生错误";
            str += e.getMessage();
        }
        CQAPIUtil.send.accept(
                CQAPIUtil.castMessage.apply(message)
                        .setMessage(str)
        );
    }

    @MessageStartWith("https://www.youtube.com/watch?")
    public static void normalLink(Message message) {
        log.info("normalLink");
        downLoadAudio(message);
    }

    @MessageStartWith("https://youtu.be/")
    public static void shortLink(Message message) {
        log.info("shortLink");
        downLoadAudio(message);
    }

}

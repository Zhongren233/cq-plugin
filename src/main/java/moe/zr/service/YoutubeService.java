package moe.zr.service;

import com.sapher.youtubedl.YoutubeDL;
import com.sapher.youtubedl.YoutubeDLException;
import com.sapher.youtubedl.YoutubeDLRequest;
import com.sapher.youtubedl.YoutubeDLResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

/**
 * 静态块初始化地址
 */
@Slf4j
public class YoutubeService {
    public static String path;

    static {
        String property = System.getProperty("user.dir");
        path = property + "/down";
        File file = new File(path);
        if (file.exists()) {
            boolean mkdir = file.mkdir();
            log.info("检测到./down目录不存在,自动创建");
        }
    }

    public static String downloadAudio(String url) throws YoutubeDLException {
        YoutubeDLRequest youtubeDLRequest = new YoutubeDLRequest(url, path);
        youtubeDLRequest.setOption("format", 140);
        youtubeDLRequest.setOption("id");
        YoutubeDLResponse execute = YoutubeDL.execute(youtubeDLRequest);
        String out = execute.getOut();
        String s = out.split("\n")[1];
        System.out.println(s);
        return s.split(": ")[1];
    }
}

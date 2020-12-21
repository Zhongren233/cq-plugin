package moe.zr.service;

import com.sapher.youtubedl.YoutubeDL;
import com.sapher.youtubedl.YoutubeDLException;
import com.sapher.youtubedl.YoutubeDLRequest;
import com.sapher.youtubedl.YoutubeDLResponse;

public class YoutubeService {
    public static String path;

    static {
        String property = System.getProperty("user.dir");
        path = property + "/down";
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

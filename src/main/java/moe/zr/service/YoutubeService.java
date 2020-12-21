package moe.zr.service;

import com.sapher.youtubedl.YoutubeDL;
import com.sapher.youtubedl.YoutubeDLException;
import com.sapher.youtubedl.YoutubeDLRequest;
import com.sapher.youtubedl.YoutubeDLResponse;

public class YoutubeService {
    public static String downloadAudio(String url) throws YoutubeDLException {
        String path = "D:/youtube-dl/down";
        YoutubeDLRequest youtubeDLRequest = new YoutubeDLRequest(url, path);
        youtubeDLRequest.setOption("format", 140);
        youtubeDLRequest.setOption("id");
        YoutubeDLResponse execute = YoutubeDL.execute(youtubeDLRequest);
        String out = execute.getOut();
        String s = out.split("\n")[1];
        return s.split(": ")[1];
    }
}

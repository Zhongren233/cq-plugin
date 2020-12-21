package moe.zr.service;

import com.sapher.youtubedl.YoutubeDL;
import com.sapher.youtubedl.YoutubeDLException;
import com.sapher.youtubedl.YoutubeDLRequest;
import com.sapher.youtubedl.YoutubeDLResponse;
import lombok.Value;

public class YoutubeService {
    private static String path = "D:/youtube-dl/down";
    public static String downloadAudio(String url) throws YoutubeDLException {
        YoutubeDLRequest youtubeDLRequest = new YoutubeDLRequest(url, path);
        youtubeDLRequest.setOption("format", 140);
        youtubeDLRequest.setOption("restrict-filenames");
        YoutubeDLResponse execute = YoutubeDL.execute(youtubeDLRequest);
        return execute.getOut();
    }
}

package moe.zr.service;

import com.sapher.youtubedl.YoutubeDLException;
import org.junit.jupiter.api.Test;

class YoutubeServiceTest {

    @Test
    public void test() throws YoutubeDLException {
        String s = YoutubeService.downloadAudio("https://www.youtube.com/watch?v=ZRnaYwx2fH4");
        System.out.println(s);
    }
}
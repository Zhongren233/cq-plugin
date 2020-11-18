package moe.zr.handler;

import moe.zr.client.ListenedSocketClient;
import org.junit.jupiter.api.Test;

import java.net.URI;

class MessageHandlerTest {

    @Test
    void repeatWithMe() {
        URI uri = URI.create("ws://localhost:6700");
        ListenedSocketClient listenedSocketClient = new ListenedSocketClient(uri);
        listenedSocketClient.connect();
        String s = "{\"auto_escape\":false,\"message\":\"hello\",\"message_type\":\"private\",\"user_id\":732713726}";
        listenedSocketClient.send(s);
    }
}
package moe.zr;

import moe.zr.client.APISocketClient;
import moe.zr.client.ListenedSocketClient;
import moe.zr.util.CQAPIUtil;

import java.net.URI;
import java.net.URISyntaxException;

public class BootMain {
    public static void main(String[] args) throws URISyntaxException, InterruptedException {
        URI event = URI.create("ws://127.0.0.1:6700/event");
        ListenedSocketClient listenedSocketClient = new ListenedSocketClient(event);
        listenedSocketClient.connect();
        URI uri = URI.create("ws://127.0.0.1:6700/api");
        APISocketClient apiSocketClient = new APISocketClient(uri);
        CQAPIUtil.setSocketClient(apiSocketClient);
        apiSocketClient.connect();
    }
}

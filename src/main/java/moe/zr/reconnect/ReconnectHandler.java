package moe.zr.reconnect;

import org.java_websocket.client.WebSocketClient;

public class ReconnectHandler {
    public static void reconnect(WebSocketClient socketClient) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                socketClient.reconnect();
            }
        });
        thread.start();
    }
}

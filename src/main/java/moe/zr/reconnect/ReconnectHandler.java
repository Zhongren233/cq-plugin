package moe.zr.reconnect;

import org.java_websocket.client.WebSocketClient;

public class ReconnectHandler {
    public static void reconnect(WebSocketClient socketClient) {
        new Thread(socketClient::reconnect).start();
    }
}

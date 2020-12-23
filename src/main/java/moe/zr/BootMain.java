package moe.zr;

import moe.zr.client.APISocketClient;
import moe.zr.client.ListenedSocketClient;
import moe.zr.util.CQAPIUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class BootMain {
    private static final Properties properties = new Properties();

    static {
        Path path = Paths.get(System.getProperty("user.dir"), "config.properties");
        File propertiesFile = path.toFile();
        try {
            properties.load(new FileInputStream(propertiesFile));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        URI event = URI.create(properties.getProperty("websocket.event.address"));
        ListenedSocketClient listenedSocketClient = new ListenedSocketClient(event);
        listenedSocketClient.connect();
        URI uri = URI.create(properties.getProperty("websocket.api.address"));
        APISocketClient apiSocketClient = new APISocketClient(uri);
        CQAPIUtil.setSocketClient(apiSocketClient);
        apiSocketClient.connect();
    }
}

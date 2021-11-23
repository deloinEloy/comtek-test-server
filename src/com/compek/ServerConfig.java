package com.compek;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ServerConfig {
    //seconds
    public static Integer TIME;
    private static final Integer TIME_DEFAULT = 10;

    public static Integer PORT;
    private static final Integer PORT_DEFAULT = 8182;

    public static void init() {
        try (InputStream input = Server.class.getClassLoader().getResourceAsStream("config.properties")) {

            if (input == null) {
                TIME = TIME_DEFAULT;
                PORT = PORT_DEFAULT;
                return;
            }

            Properties prop = new Properties();
            prop.load(input);

            TIME = Integer.parseInt(prop.getProperty("time", TIME_DEFAULT.toString()));
            PORT = Integer.parseInt(prop.getProperty("port", PORT_DEFAULT.toString()));

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

package com.compek;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Утилитарный класс для обработки подключений
 * */
public class ServerHandler {

    private final ServerSocket serverSocket;

    private final ConnectionHandler connectionHandler;
    private final TextHandler textHandler;
    private final ObjectHandler objectHandler;

    private HandlerBase currentHandler;

    public ServerHandler() throws IOException {
        serverSocket = new ServerSocket(ServerConfig.PORT);

        connectionHandler = new ConnectionHandler();
        textHandler = new TextHandler();
        objectHandler = new ObjectHandler();
        System.out.println("Server handler initialized!");
    }

    public void waitForConnection() throws IOException {
        System.out.println("ServerHandler.waitForConnection()");
        Socket socket = serverSocket.accept();

        connectionHandler.setSocket(socket);
        connectionHandler.init();

        ConnectionType connectionType = connectionHandler.getConnectionType();
        switch (connectionType) {
            case TEXT:
                textHandler.setSocket(socket);
                currentHandler = textHandler;
                break;
            case OBJECT:
                objectHandler.setSocket(socket);
                currentHandler = objectHandler;
                break;
        }
        System.out.println("ServerHandler: connectionType = " + connectionType);
    }

    public void handle() throws IOException, ClassNotFoundException {
        if (currentHandler != null) {
            currentHandler.handle();
        } else {
            throw new IllegalStateException("currentHandler == null");
        }
    }

    public void process() {
        System.out.println("ServerHandler.process()");
        currentHandler.process();
    }

    public void response() throws IOException {
        System.out.println("ServerHandler.response()");
        currentHandler.response();
    }
}

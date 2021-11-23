package com.compek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Обработчик типов подключения
 * */
public class ConnectionHandler {
    private Socket socket;
    private BufferedReader inFromClient;

    public ConnectionHandler() {
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void init() throws IOException {
        inFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public ConnectionType getConnectionType() throws IOException {
        String str = inFromClient.readLine();
        return ConnectionType.fromString(str);
    }
}

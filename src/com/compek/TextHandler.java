package com.compek;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Обработчик соединений с текстовыми данными
 * */
public class TextHandler extends HandlerBase {
    private String message;

    public TextHandler() {
    }

    @Override
    public void handle() throws IOException {
        BufferedReader inFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        message = inFromClient.readLine();
        System.out.println(message);
    }

    @Override
    public void process() {
        message += PAYLOAD + "\n";
        System.out.println(message);
    }

    @Override
    public void response() throws IOException {
        DataOutputStream outToClient = new DataOutputStream(socket.getOutputStream());
        outToClient.writeBytes(message);
    }
}

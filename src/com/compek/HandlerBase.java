package com.compek;

import java.io.IOException;
import java.net.Socket;

public abstract class HandlerBase {

    /**
     * Дополнительные данные, которые сервер возвращает в ответ клиенту
     * */
    protected static final String PAYLOAD = " PAYLOAD";

    protected Socket socket;

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    /**
     * Считать данные от клиента
     * */
    public abstract void handle() throws IOException, ClassNotFoundException;

    /**
     * Обработать считанные данные от клиента
     * */
    public abstract void process();

    /**
     * Вернуть данные клиенту
     * */
    public abstract void response() throws IOException;
}

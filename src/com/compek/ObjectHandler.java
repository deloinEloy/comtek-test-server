package com.compek;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Обработчки соединений с данными в виде объекта
 * */
public class ObjectHandler extends HandlerBase {
    private SampleObject obj;

    public ObjectHandler() {
    }

    @Override
    public void handle() throws IOException, ClassNotFoundException {
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            obj = (SampleObject) objectInputStream.readObject();
    }

    @Override
    public void process() {
        obj = new SampleObject(obj.getText() + PAYLOAD);
    }

    @Override
    public void response() throws IOException {
        ObjectOutputStream outToClient = new ObjectOutputStream(socket.getOutputStream());
        outToClient.writeObject(obj);
    }
}

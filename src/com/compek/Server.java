package com.compek;

import java.io.IOException;

import static com.compek.ServerState.*;

public class Server {
    private static ServerState currentState = null;

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        start();
    }

    public static void start() throws IOException, ClassNotFoundException, InterruptedException {
        ServerConfig.init();
        ServerHandler serverHandler = new ServerHandler();
        checkStateAndSet(IDLE);

        while (true) {
            serverHandler.waitForConnection();
            serverHandler.handle();
            checkStateAndSet(PROCESS);
            serverHandler.process();
            Thread.sleep(ServerConfig.TIME * 1000);
            checkStateAndSet(RESPONSE);
            serverHandler.response();
            checkStateAndSet(IDLE);
        }
    }

    /**
     * Проверить возможность переключения в новое состояние
     * */
    private static void checkStateAndSet(ServerState serverState) {
        if (currentState == null) {
            currentState = serverState;
            return;
        }
        switch (serverState) {
            case IDLE:
                if (currentState != RESPONSE) throw new IllegalStateException("Can't set state to " + serverState);
                break;
            case PROCESS:
                if (currentState != IDLE) throw new IllegalStateException("Can't set state to " + serverState);
                break;
            case RESPONSE:
                if (currentState != PROCESS) throw new IllegalStateException("Can't set state to " + serverState);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + serverState);
        }
        currentState = serverState;
    }
}

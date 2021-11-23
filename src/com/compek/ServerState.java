package com.compek;

public enum ServerState {
    /**
     * Ожидание данных
     * */
    IDLE,
    /**
     * Обработка данных
     * */
    PROCESS,
    /**
     * Отправка данных клиенту
     * */
    RESPONSE,
    ;
}

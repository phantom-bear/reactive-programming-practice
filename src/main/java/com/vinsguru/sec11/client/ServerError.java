package com.vinsguru.sec11.client;

public class ServerError extends RuntimeException {

    public ServerError() {
        super("server error");
    }
}

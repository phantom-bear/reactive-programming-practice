package com.vinsguru.sec11.client;

public class ClientError extends RuntimeException{


    public ClientError() {
        super("bad request");
    }
}

package com.vinsguru.sec02;

import com.vinsguru.common.Util;
import com.vinsguru.sec02.client.ExternalServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lec11NonBlockingIO {
    public static final Logger log = LoggerFactory.getLogger(Lec03MonoSubscribe.class);

    public static void main(String[] args) {
        var client = new ExternalServiceClient();
        log.info("starting");

        for(int i = 1; i <=100 ; i++){
            client.getProductName(i).subscribe(Util.subscriber());

        }

        Util.sleepSeconds(2);
    }
}

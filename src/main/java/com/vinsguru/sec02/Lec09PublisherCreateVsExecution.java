package com.vinsguru.sec02;

import com.vinsguru.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public class Lec09PublisherCreateVsExecution {
    public static final Logger log = LoggerFactory.getLogger(Lec03MonoSubscribe.class);

    public static void main(String[] args) {
        getName()
                .subscribe(Util.subscriber());
    }

    private static Mono<String> getName(){
        log.info("entered the method");
        // here what we are doing is only create a publisher,
        // if there has no publisher then it will not execute
        return Mono.fromSupplier(() -> {
            log.info("generating name");
            Util.sleepSeconds(3);
            return Util.faker().name().firstName();
        });
    }
}

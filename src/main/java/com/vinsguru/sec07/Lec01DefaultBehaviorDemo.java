package com.vinsguru.sec07;

import com.vinsguru.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
/*
    By default, the current thread is doing all the work
 */
public class Lec01DefaultBehaviorDemo {
    private static final Logger log = LoggerFactory.getLogger(Lec01DefaultBehaviorDemo.class);

    public static void main(String[] args) {
        var flux = Flux.create(fluxSink -> {
            for (var i = 0; i < 3; i++) {
                log.info("generating value: {}", i);
                fluxSink.next(i);
            }
            fluxSink.complete();
        }).doOnNext(v -> log.info("value: {}", v));

        Runnable runnable = () -> flux.subscribe(Util.subscriber("sub1"));
        Thread.ofPlatform().start(runnable);
//        flux.subscribe(Util.subscriber("sub2"));

    }
}

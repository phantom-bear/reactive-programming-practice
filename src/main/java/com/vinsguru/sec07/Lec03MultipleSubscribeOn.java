package com.vinsguru.sec07;

import com.vinsguru.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/*
    We can have multiple subscribeOn.
    The closest to the source will take the precedence!
 */
public class Lec03MultipleSubscribeOn {

    public static final Logger log = LoggerFactory.getLogger(Lec03MultipleSubscribeOn.class);

    public static void main(String[] args) {
        var flux = Flux.create(fluxSink -> {
                    for (var i = 1; i < 3; i++) {
                        log.info("generating value: {}", i);
                        fluxSink.next(i);
                    }
                    fluxSink.complete();
                })
                .subscribeOn(Schedulers.immediate())
                .doOnNext(v -> log.info("value: {}", v)).doFirst(() -> log.info("first1"))
                .subscribeOn(Schedulers.boundedElastic())
                .doFirst(() -> log.info("first2"));
        Runnable runnable1 = () -> flux.subscribe(Util.subscriber("sub1"));

        Thread.ofPlatform().start(runnable1);

        Util.sleepSeconds(2);
    }

}

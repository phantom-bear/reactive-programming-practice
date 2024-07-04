package com.vinsguru.sec02;

import com.vinsguru.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;

public class Lec10MonoDefer {
    public static final Logger log = LoggerFactory.getLogger(Lec03MonoSubscribe.class);

    public static void main(String[] args) {
//        createPublisher()
//                .subscribe(Util.subscriber());

        // the Mono.defer can delay the creation of
        // publisher creation when there has no subscriber
        Mono.defer(Lec10MonoDefer::createPublisher);
    }

    private static Mono<Integer> createPublisher(){
        log.info("Creating publisher");
        var list = List.of(1, 2, 3);
        Util.sleepSeconds(1);
        return Mono.fromSupplier(() ->sum(list));
    }

    // time-consuming business login
    private static int sum(List<Integer> list) {
        log.info("finding the sum of {}", list);
        Util.sleepSeconds(3);
        return list.stream().mapToInt(a -> a).sum();
    }
}

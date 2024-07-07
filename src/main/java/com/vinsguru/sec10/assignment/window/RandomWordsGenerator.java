package com.vinsguru.sec10.assignment.window;

import com.vinsguru.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class RandomWordsGenerator {

    public Flux<String> getBookOrders(){
        return Flux.interval(Duration.ofMillis(200))
                .map(i -> Util.faker().country().name());
    }
}

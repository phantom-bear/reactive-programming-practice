package com.vinsguru.sec09.helper;

import com.vinsguru.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Emirate {

    private static final String AIRLINE = "Emirates";

    public static Flux<Flight> getFlights(){
        return Flux.range(1, Util.faker().random().nextInt(2, 10))
                .delayElements(Duration.ofMillis(Util.faker().random().nextInt(200, 1000)))
                .map(i -> new Flight(AIRLINE, Util.faker().random().nextInt(3, 1000)))
                .transform(Util.fluxLogger(AIRLINE));
    }
}

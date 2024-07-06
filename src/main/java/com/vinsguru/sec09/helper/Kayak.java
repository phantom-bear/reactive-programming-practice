package com.vinsguru.sec09.helper;

import com.vinsguru.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Kayak {

    private static final String AIRLINE = "Emirates";

    public static Flux<Flight> getFlights() {
        return Flux.merge(AmericanAirline.getFlights(),
                        Emirate.getFlights(),
                        Qatar.getFlights())
                .take(Duration.ofSeconds(2));
    }
}

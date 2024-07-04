package com.vinsguru.sec03;

import com.github.javafaker.Faker;
import com.vinsguru.common.Util;
import reactor.core.publisher.Flux;

public class Lec05FluxRange {
    public static void main(String[] args) {
        Flux.range(1, 10)
                .subscribe(Util.subscriber());

        // quiz: generate 10 random first names

        Flux.range(1, 10)
                .map(i -> {
                    String firstName = Faker.instance().name().firstName();
                    return String.format("%s %s", firstName, i);
                }).subscribe(Util.subscriber());
    }
}

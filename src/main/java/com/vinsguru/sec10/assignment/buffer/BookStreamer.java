package com.vinsguru.sec10.assignment.buffer;

import com.vinsguru.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class BookStreamer {

    public static Flux<BookOrder> getBookOrders(){
        return Flux.interval(Duration.ofMillis(200))
                .map(i -> new BookOrder(
                        Util.faker().book().genre(),
                        Util.faker().book().title(),
                        Util.faker().number().randomDigit()
                ));
    }
}

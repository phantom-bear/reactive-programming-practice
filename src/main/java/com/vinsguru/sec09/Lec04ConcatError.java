package com.vinsguru.sec09;

import com.vinsguru.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec04ConcatError {

    private static final Logger log = LoggerFactory.getLogger(Lec04ConcatError.class);

    public static void main(String[] args) {

        demo2();
        Util.sleepSeconds(3);
    }

    private static void demo1(){
        product1()
                .concatWith(product3())
                .concatWith(product2())
                .subscribe(Util.subscriber());

    }

    private static void demo2(){
        Flux.concatDelayError(product1(), product3(), product2())
                .subscribe(Util.subscriber());
    }


    private static Flux<Integer> product1(){
        return Flux.just(1, 2, 3)
                .doOnSubscribe(s -> log.info("subscribing to producer1"))
                .delayElements(Duration.ofMillis(10));
    }

    private static Flux<Integer> product2(){
        return Flux.just(51, 52, 53)
                .doOnSubscribe(s -> log.info("subscribing to producer2"))
                .delayElements(Duration.ofMillis(10));
    }

    private static Flux<Integer> product3(){
        return Flux.error(new RuntimeException("oops"));
    }
}

package com.vinsguru.sec09;

import com.vinsguru.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec03ContactWith {
    private static final Logger log = LoggerFactory.getLogger(Lec03ContactWith.class);


    public static void main(String[] args) {

        demo3();
        Util.sleepSeconds(3);
    }

    private static void demo1(){
        product1()
                .concatWithValues(-1, 0)
                .subscribe(Util.subscriber());

    }

    private static void demo2(){
        product1()
                .concatWith(product2())
                .subscribe(Util.subscriber());

    }

    private static void demo3(){
        Flux.concat(product1(), product2())
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

}

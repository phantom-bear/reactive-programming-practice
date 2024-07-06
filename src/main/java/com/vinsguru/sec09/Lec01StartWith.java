package com.vinsguru.sec09;

import com.vinsguru.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;

public class Lec01StartWith {

    private static final Logger log = LoggerFactory.getLogger(Lec01StartWith.class);

    public static void main(String[] args) {

        demo4();
        Util.sleepSeconds(3);
    }

    private static void demo1(){
        product1()
            .startWith(-1, 0)
            .subscribe(Util.subscriber());

    }

    private static void demo2(){
        product1()
                .startWith(List.of(-2, -1, 0))
                .subscribe(Util.subscriber());

    }

    private static void demo3(){
        product1()
                .startWith(product2())
                .subscribe(Util.subscriber());

    }

    private static void demo4(){
        product1()
                .startWith(0)
                .startWith(product2())
                .startWith(List.of(49, 50))
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

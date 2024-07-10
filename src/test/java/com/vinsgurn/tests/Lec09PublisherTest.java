package com.vinsgurn.tests;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.publisher.TestPublisher;

import java.util.function.UnaryOperator;

public class Lec09PublisherTest {

    private UnaryOperator<Flux<String>> processor(){
        return flux -> flux.filter(s -> s.length() > 1)
                .map(String::toUpperCase)
                .map(s -> s + ":" + s.length());
    }

    @Test
    public void publisherTest1(){
        var publisher = TestPublisher.<String>create();
        var flux = publisher.flux();

//        flux.subscribe(Util.subscriber());
//
////        publisher.next("a", "b");
////        publisher.complete();
//
//        publisher.emit("a", "b"); // emit is about same as next(), it send the message and also emit complete signal


        StepVerifier.create(flux.transform(processor()))
                .then(() -> publisher.emit("hi", "hello"))// the runnable settled here
                .expectNext("HI:2")
                .expectNext("HELLO:5")
                .expectComplete()
                .verify();
    }


    @Test
    public void publisherTest2(){
        var publisher = TestPublisher.<String>create();
        var flux = publisher.flux();

        StepVerifier.create(flux.transform(processor()))
                .then(() -> publisher.emit("a", "b", "c"))// the runnable settled here
                .expectComplete()
                .verify();
    }
}

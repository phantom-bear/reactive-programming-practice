package com.vinsguru.sec12;

import com.vinsguru.common.Util;
import reactor.core.publisher.Sinks;

public class Lec04Multicast {


    public static void main(String[] args) {
        demo2();
    }

    private static void demo1(){
        // handle through which we would push items
        // onBackPressureBuffer - bounded queue
        var sink = Sinks.many().multicast().onBackpressureBuffer();
        // handle through which subscribers will receive items
        var flux = sink.asFlux();

        flux.subscribe(Util.subscriber("sam"));
        flux.subscribe(Util.subscriber("mike"));

        sink.tryEmitNext("hi");
        sink.tryEmitNext("how are you");
        sink.tryEmitNext("?");

        Util.sleepSeconds(2);

        flux.subscribe(Util.subscriber("jake"));
        sink.tryEmitNext("new message");

    }

    // warmup
    private static void demo2(){
        // handle through which we would push items
        // onBackPressureBuffer - bounded queue
        var sink = Sinks.many().multicast().onBackpressureBuffer();
        // handle through which subscribers will receive items
        var flux = sink.asFlux();

        // emitting messages without subscriber, all messages will be sent to backpressure queue
        sink.tryEmitNext("hi");
        sink.tryEmitNext("how are you");
        sink.tryEmitNext("?");

        Util.sleepSeconds(2);

        // when jake subscribe, it will send all messages to jake
        flux.subscribe(Util.subscriber("jake"));
        // later on subscribers will not receive previous message
        flux.subscribe(Util.subscriber("sam"));
        flux.subscribe(Util.subscriber("mike"));
        // this will be received by all subscribers
        sink.tryEmitNext("new message");

    }
}

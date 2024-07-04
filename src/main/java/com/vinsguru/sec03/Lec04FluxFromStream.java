package com.vinsguru.sec03;

import com.vinsguru.common.Util;
import reactor.core.publisher.Flux;

import java.util.List;

public class Lec04FluxFromStream {
    public static void main(String[] args) {
        var list = List.of(1, 2, 3, 4);
        var stream = list.stream();

        var flux = Flux.fromStream(stream);
        // this will throw an error, because java stream can only be used once
//        stream.forEach(System.out::println);
//        stream.forEach(System.out::println);

        // this will also throw the same error because it will consume the same stream twice
//        flux.subscribe(Util.subscriber("sub1"));
//        flux.subscribe(Util.subscriber("sub2"));

        // this will work because each individual time it will create stream
        flux = Flux.fromStream(list::stream);

        flux.subscribe(Util.subscriber("sub1"));
        flux.subscribe(Util.subscriber("sub2"));

    }
}

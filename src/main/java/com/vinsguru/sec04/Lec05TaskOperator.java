package com.vinsguru.sec04;

import com.vinsguru.common.Util;
import reactor.core.publisher.Flux;

import java.util.stream.IntStream;

// Task is similar to java stream's limit
public class Lec05TaskOperator {

    public static void main(String[] args) {
//        IntStream.range(1, 10)
//                .limit(3)
//                .forEach(System.out::println);

        taskeUnil();

    }

    private static void taske(){
        Flux.range(1, 10)
                .log("take")
                .take(3)
                .log("sub")
                .subscribe(Util.subscriber());
    }

    private static void taskeWhile(){
        Flux.range(1, 10)
                .log("take")
                .takeWhile(i -> i < 5) // stop when the condition is not met
                .log("sub")
                .subscribe(Util.subscriber());
    }

    private static void taskeUnil(){
        Flux.range(1, 10)
                .log("take")
                .takeUntil(i -> i < 5) // stop when the condition is met + allow the last item
                .log("sub")
                .subscribe(Util.subscriber());
    }
}

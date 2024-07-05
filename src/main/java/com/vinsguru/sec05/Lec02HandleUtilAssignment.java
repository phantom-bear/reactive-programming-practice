package com.vinsguru.sec05;

import com.vinsguru.common.Util;
import reactor.core.publisher.Flux;

public class Lec02HandleUtilAssignment {
    public static void main(String[] args) {
        Flux.<String>generate(synchronousSink -> synchronousSink.next(Util.faker().country().name()))
                .handle((name, sink) -> {

                    sink.next(name);
                    if (name.equalsIgnoreCase("canada")){
                        sink.complete();
                    }
                }).subscribe(Util.subscriber());
    }
}
